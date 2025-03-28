# REDIS
  据淘汰策略： 
1. volatile-lru[Least Recently Used]：从已设置过期时间的数据集（server.db[i].expires）中挑选最近最少使用的数据淘汰 
2. volatile-ttl[Time To Live]：从已设置过期时间的数据集（server.db[i].expires）中挑选马上将要过期的数据淘汰 
3. volatile-random：从已设置过期时间的数据集（server.db[i].expires）中任意选择数据淘汰 
4. volatile-lfu: 从所有设置了到期时间的 Key 中，淘汰最近最不常用使用的 Key
5. allkeys-lru：从数据集（server.db[i].dict）中挑选最近最少使用的数据淘汰 【最近最不长使用]
6. allkeys-random：从数据集（server.db[i].dict）中任意选择数据淘汰 
7. volatile-lfu: 从所有 Key 中，淘汰最近最不常用使用的 Key
8. no-enviction（驱逐）：禁止驱逐数据

Redis 服务其使用的是 惰性删除 和 定期删除 两种策略，通过配合使用这两种删除策略，服务其可以很好的合理利用CPU时间和避免浪费内存空间之间取得平衡。
    1. 惰性删除
    原理：在取出键时才对键进行过期检查，如果发现过期了就会被删除
    优点：对CPU友好，能够最大限度的节约CPU时间
    缺点：对内存不友好，过期的Key会占用内存  
    2  定期删除：
    原理：每隔一段时间就对数据库进行一次检查，删除里面的过期键。（主动删除）
    优点：
        定期删除策略每隔一段时间执行一次删除过期键操作，并通过限制删除操作执行的时长和频率来减少删除操作对 CPU 时间的影响。
        定期删除策略有效地减少了因为过期键而带来的内存浪费。
    缺点:
        如果删除操作执行的太频繁，或者执行的时间太长，定期删除策略就会退化成定时删除策略，以至于将CPU时间过多的消耗再删除过期键上面。
        如果删除操作执行的太少，或者执行的时间太短，定期删除策略又会和惰性删除策略一样，出现浪费内存的情况。

Redis 持久化
Redis 提供了不同级别的持久化方式:
RDB持久化方式能够在指定的时间间隔能对你的数据进行快照存储.
AOF持久化方式记录每次对服务器写的操作,当服务器重启的时候会重新执行这些命令来恢复原始的数据,AOF命令以redis协议追加保存每次写的操作到文件末尾.Redis还能对AOF文件进行后台重写,使得AOF文件的体积不至于过大.
如果你只希望你的数据在服务器运行的时候存在,你也可以不使用任何持久化方式.   
你也可以同时开启两种持久化方式, 在这种情况下, 当redis重启的时候会优先载入AOF文件来恢复原始的数据,因为在通常情况下AOF文件保存的数据集要比RDB文件保存的数据集要完整.
最重要的事情是了解RDB和AOF持久化方式的不同,让我们以RDB持久化方式开始:

RDB的优点
RDB是一个非常紧凑的文件,它保存了某个时间点得数据集,非常适用于数据集的备份,比如你可以在每个小时报保存一下过去24小时内的数据,同时每天保存过去30天的数据,这样即使出了问题你也可以根据需求恢复到不同版本的数据集.
RDB是一个紧凑的单一文件,很方便传送到另一个远端数据中心或者亚马逊的S3（可能加密），非常适用于灾难恢复.
RDB在保存RDB文件时父进程唯一需要做的就是fork出一个子进程,接下来的工作全部由子进程来做，父进程不需要再做其他IO操作，所以RDB持久化方式可以最大化redis的性能.
与AOF相比,在恢复大的数据集的时候，RDB方式会更快一些.

RDB的缺点
如果你希望在redis意外停止工作（例如电源中断）的情况下丢失的数据最少的话，那么RDB不适合你.虽然你可以配置不同的save时间点(例如每隔5分钟并且对数据集有100个写的操作),是Redis要完整的保存整个数据集是一个比较繁重的工作,你通常会每隔5分钟或者更久做一次完整的保存,万一在Redis意外宕机,你可能会丢失几分钟的数据.
RDB 需要经常fork子进程来保存数据集到硬盘上,当数据集比较大的时候,fork的过程是非常耗时的,可能会导致Redis在一些毫秒级内不能响应客户端的请求.如果数据集巨大并且CPU性能不是很好的情况下,这种情况会持续1秒,AOF也需要fork,但是你可以调节重写日志文件的频率来提高数据集的耐久度

3、二者优缺点
RDB存在哪些优势呢？
1). 一旦采用该方式，那么你的整个Redis数据库将只包含一个文件，这对于文件备份而言是非常完美的。比如，你可能打算每个小时归档一次最近24小时的数据，同时还要每天归档一次最近30天的数据。通过这样的备份策略，一旦系统出现灾难性故障，我们可以非常容易的进行恢复。
2). 对于灾难恢复而言，RDB是非常不错的选择。因为我们可以非常轻松的将一个单独的文件压缩后再转移到其它存储介质上。
3). 性能最大化。对于Redis的服务进程而言，在开始持久化时，它唯一需要做的只是fork出子进程，之后再由子进程完成这些持久化的工作，这样就可以极大的避免服务进程执行IO操作了。
4). 相比于AOF机制，如果数据集很大，RDB的启动效率会更高。
RDB又存在哪些劣势呢？
1). 如果你想保证数据的高可用性，即最大限度的避免数据丢失，那么RDB将不是一个很好的选择。因为系统一旦在定时持久化之前出现宕机现象，此前没有来得及写入磁盘的数据都将丢失。
2). RDB方式数据没办法做到实时持久化/秒级持久化。由于RDB是通过fork子进程来协助完成数据持久化工作的，因此，如果当数据集较大时，可能会导致整个服务器停止服务几百毫秒，甚至是1秒钟。
AOF的优势有哪些呢？
1). 该机制可以带来更高的数据安全性，即数据持久性。Redis中提供了3中同步策略，即每秒同步、每修改同步和不同步。事实上，每秒同步也是异步完成的，其效率也是非常高的，所差的是一旦系统出现宕机现象，那么这一秒钟之内修改的数据将会丢失。而每修改同步，我们可以将其视为同步持久化，即每次发生的数据变化都会被立即记录到磁盘中。可以预见，这种方式在效率上是最低的。至于无同步，无需多言，我想大家都能正确的理解它。
2). 由于该机制对日志文件的写入操作采用的是append模式，因此在写入过程中即使出现宕机现象，也不会破坏日志文件中已经存在的内容。然而如果我们本次操作只是写入了一半数据就出现了系统崩溃问题，不用担心，在Redis下一次启动之前，我们可以通过redis-check-aof工具来帮助我们解决数据一致性的问题。
3). 如果日志过大，Redis可以自动启用rewrite机制。即Redis以append模式不断的将修改数据写入到老的磁盘文件中，同时Redis还会创建一个新的文件用于记录此期间有哪些修改命令被执行。因此在进行rewrite切换时可以更好的保证数据安全性。
4). AOF包含一个格式清晰、易于理解的日志文件用于记录所有的修改操作。事实上，我们也可以通过该文件完成数据的重建。
AOF的劣势有哪些呢？
1). 对于相同数量的数据集而言，AOF文件通常要大于RDB文件。RDB 在恢复大数据集时的速度比 AOF 的恢复速度要快。
2). 根据同步策略的不同，AOF在运行效率上往往会慢于RDB。总之，每秒同步策略的效率是比较高的，同步禁用策略的效率和RDB一样高效。
二者选择的标准，就是看系统是愿意牺牲一些性能，换取更高的缓存一致性（aof），还是愿意写操作频繁的时候，不启用备份来换取更高的性能，待手动运行save的时候，再做备份（rdb）。rdb这个就更有些 eventually consistent的意思了。不过生产环境其实更多都是二者结合使用的。


4、常用配置

RDB持久化配置

Redis会将数据集的快照dump到dump.rdb文件中。此外，我们也可以通过配置文件来修改Redis服务器dump快照的频率，在打开6379.conf文件之后，我们搜索save，可以看到下面的配置信息：
save 900 1              #在900秒(15分钟)之后，如果至少有1个key发生变化，则dump内存快照。
save 300 10            #在300秒(5分钟)之后，如果至少有10个key发生变化，则dump内存快照。
save 60 10000        #在60秒(1分钟)之后，如果至少有10000个key发生变化，则dump内存快照。

AOF持久化配置
在Redis的配置文件中存在三种同步方式，它们分别是：
appendfsync always     #每次有数据修改发生时都会写入AOF文件。
appendfsync everysec  #每秒钟同步一次，该策略为AOF的缺省策略。
appendfsync no          #从不同步。高效但是数据不会被持久化。

5. redis io模型
    redis基于Reactor模式开发了自己的网络事件处理器，称之为文件事件处理器(File Event Hanlder)。
    文件事件处理器由Socket、IO多路复用程序、文件事件分派器(dispather)，事件处理器(handler)四部分组成。
    redis采用的是多路复用IO模型，单执行线程，多IO线程。然后采用epoll来实现。
    单线程是指：网络IO和 数据的读写用的是一个线程。
    redis的多线程是指： 网络数据的解析变成了多线程，但是数据的读写依然是单线程。
    epoll有诸多优点：
     1. epoll 没有最大并发连接的限制，上限是最大可以打开文件的数目，这个数字一般远大于 2048, 
     一般来说这个数目和系统内存关系很大，具体数目可以 cat /proc/sys/fs/file-max 察看。
     2. 效率提升， Epoll 最大的优点就在于它
     只管你“活跃”的连接 ，而跟连接总数无关，因此在实际的网络环境中，Epoll 的效率就会远远高于 select 和 poll 。
     3. 内存拷贝， Epoll 在这点上使用了“共享内存”，这个内存拷贝也省略了。
     
     select，poll，epoll 区别：
         select：它维护了一个数组结构 fd_set，调用 select 函数时，会从用户空间拷贝 fd_set 到内核空间，并监听是否有事件触发，有就通过无差别轮询的方式遍历找到事件触发的位置，然后执行相关的读或写操作。轮询的时间复杂度为 O(n)。
         缺点：内核对被监控的 fd_set 集合做了大小限制，最大为 1024 ；每次调用 select，都需要把 fd_set 集合从用户态拷贝到内核态，都需要在内核遍历 传递进来的所有 fd_set ，效率很低。
         poll：与 select 类似，区别是它采用的是 poll_fd 数据结构实现了一个可变长的数组，没有了最大文件描述符数量的限制。
         epoll：epoll 与 select 的不同之处在于，epoll 监听事件是否触发时，还设置了回调函数，如果事件触发，就执行回调函数，并将准备就绪的 fd 放到 readyList 中，而不需要轮询遍历所有的 fd_set 。并且 epoll 没有最大文件描述符数量的限制。在高并发情况下 epoll 能支持更多的连接。
     
     详见https://blog.csdn.net/dl674756321/article/details/105411034?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-3.channel_param&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-3.channel_param
         https://blog.csdn.net/wxy941011/article/details/80274233?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.channel_param&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.channel_param

Redis 6.0 之前的版本真的是单线程吗?
       Redis基于Reactor模式开发了网络事件处理器，
       这个处理器被称为文件事件处理器。它的组成结构为4部分：多个套接字、IO多路复用程序、文件事件分派器、事件处理器。因为文件事件分派器队列的消费是单线程的，所以Redis才叫单线程模型。
aof rewrite的流程是什么？

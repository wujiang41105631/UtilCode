1。zookeeper ：文件系统+监听通知机制
            是一个分布式的协调软件
2。zab协议：
    ZAB 协议是为分布式协调服务 ZooKeeper 专门设计的一种支持崩溃恢复的一致性协议。基于该协议，ZooKeeper 实现了一种主从模式的系统架构来保持集群中各个副本之间的数据一致性。
    zab工作场景：
        1> 消息广播模式
            1> 过半机制
            2> 预提交，收到ack，再次提交
            消息广播模式是在整个集群稳定运行时的模式。它的操作类似于两阶段提交。
            在消息的广播过程中，Leader会为每一个Follower准备一个事务队列，该队列符合FIFO原则。假设Follower接收到了客户端的写请求，写请求会被转发至Leader处理，当Leader收到客户端的写请求时，主要有以下步骤：
                为写请求的事务Proposal分配一个全局唯一递增的ID(ZXID)
                将这个事务放入每个Follower对应的事务队列，并按照FIFO顺序进行广播
                Follower接收到事务请求后，将该事务请求写入本地事务日志文件，并在写成功后给Leader返回ACK响应
                当Leader收到过半的Follower返回的ACK，便向所有的Follower发送commit请求通知Follower执行事务提交，同时Leader自身也完成事务提交
                Follower收到commit请求后，完成事务提交
        2> 崩溃恢复模式
            1> 数据同步
3。 选举：
    投自己
    接受其他服务器的选票
    PK,zxId and myId
    将pk结果发给其他人
    每个服务器自己的投票箱统计leader是否过半

PERSISTENT 持久型：创建了就会一直存在，直到被手动调用删除节点方法。
PERSISTENT_SEQUENTIAL 持久顺序型：会自动在节点路径名称后面添加一个自增的序号，如apple/iphone/macbook00000001，这种带自增序号的节点能保证输入同一路径时都能创建一个唯一的节点，可用于实现分布式队列，分布式公平锁。
EPHEMERAL 临时型:在回话结束后，节点自动删除。
EPHEMERAL_SEQUENTIAL 临时顺序型临时节点特性，路径自动添加自增序号。
CONTAINER 容器型:ookeeper会有:TGTL的意思是time to live ，他是容器节点之上的，当此类型节点之下没有子节点且未被修改的时间超过TTL后，就会被删除。要想使用该类型，必须在zookeeper的bin/zkService.sh中的启动zookeeper的java环境中设置环境变量zookeeper.extendedTypesEnabled=true（具体做法在下边），否则KeeperErrorCode = Unimplemented for /**。
PERSISTENT_SEQUENTIAL_WITH_TTL 持久顺序TTL型.就是持久TTL型加上顺序节点的特性。


zookeeper的三种角色: leader,flowller,observer


在ZAB协议的设计中，每一个进程都可能处于下面三种状态中的一种：
    LOOKING：Leader选举阶段
    FOLLOWING：Follower服务器和Leader保持同步状态
    LEADING：Leader服务器作为主进程领导状态



ZAB和Paxos区别
本质区别在于设计的目的不一样，ZAB协议主要使用来构建一个高可用的分布式数据主备系统，Paxos算法主要是用来解决数据一致性。
1. es 和solr的区别
  1> 可存储的数据量大 pb级别
  2> 延时小
  3> 扩展性强
2. es 安装
   1> 修改句柄数
   /etc/security/limits.conf 中添加
   用户名 soft nofile 65536
   用户名 hard nofile 65536
   用户名 soft nproc 4096
   用户名 hard nproc 4096
   2> /etc/security/limits.d/20-nproc.conf中修改当前用户名对应的行为
   用户名 soft nproc 4096
   3> 修改 vim /etc/sysctl.conf vm.max_map_count=655360
   执行命令sysctl -p 修改生效
   4> es 跨域访问
       http.cors.enabled :true
       http.cors.allow-login: "*"
   5> bootstrap.system_call_filter: false //这是在因为操作系统不支持SecComp，而ES5.2.2默认bootstrap.system_call_filter为true进行检测，所以导致检测失败，失败后直接导致ES不能启动。
   6> bootstrap.mlockall设为true //这个参数的目的是当你无法关闭系统的swap的时候，建议把这个参数设为true。防止在内存不够用的时候，elasticsearch的内存被交换至交换区，导致性能骤降
3. head 安装
      0.> git 下载head
      1.> 安装grunt npm install -g grunt-cli
      2.> 进入head 目录 执行cnpm install 安装依赖
      3,> 修改文件:
          head 根目录下 Gruntfile.js    connect -> server->options 下添加hostname value为 '*' 表示任何ip都可以访问
          修改 ./_site/app.js 中的localhost 改成es安装服务器IP
      4,> 防火墙开启9100端口[head http端口]
      启动  grunt/bin/grunt server
4. kibana安装[5601 http 端口]
    vi kibana.yml 修改server.host,elasticsearch.url
    启动kibana
5. 分词器
    1.功能：拆词，大小写转换等等，对每个词进行标准化。
    2.包括3部分:
        1>charactor filter: 分词前做预处理。过滤掉html标签，特殊符号转换等等。
        2>tokenizer: 分词
        3>token filter: 对每个词进行标准化
    3. es 内置分词器
       1> standard分词器：es默认分词器。她会将词汇单元转换成小写形式，去掉停用此和和标点符号。对中文的采用的方式为单字分割
       2> simple分词器：首先会通过非字母字符来分割文本字信息。然后将词汇单元统一为小写形式。该分词器会去掉数字类型的字符
       3> Whitespace分词器：仅仅是去掉空格。对字符没有lowercase化，不支持中文。并且不对生成的词汇单元做其他标准化处理。
       4> language分词器：特定语言的分词，不支持中文
6. 分词器安装
   1> 下载中文分词器的zip包
   2> 将zip包封装成插件。一般在git上直接有
   3> 将2的包执行mvn clena install -Dmaven.test.skip=true
   4> 3会生成一个zip包，将之放到es目录下的plugins/{插件名}下
   5> 对之进行解压缩。解压缩后文件夹内的所有东西，都cp到 plugins/{插件名} 根目录下
   6> 验证是否安装成功:es启动日志中会有loaded plugin
   7> 中文分词器: ik
7. es 起停
es  启动：
后台启动: ./bin/elasticsearch -d -p pid    -d daemon
杀进程  pkill -F pid
----------------------------------------------------------------------------------------
es 7.0
    1> 去掉了 type




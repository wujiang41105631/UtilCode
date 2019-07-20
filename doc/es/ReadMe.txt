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




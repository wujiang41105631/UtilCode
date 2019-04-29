#es 状态监控
## 监控状态

    GET /_cat/health?v
status:
1. Green - everything is good (cluster is fully functional)
2. Yellow - all data is available but some replicas are not yet allocated (cluster is fully functional)
3. Red - some data is not available for whatever reason (cluster is partially functional)
当集群为红色时，它将继续从可用碎片中提供搜索请求，但是您可能需要尽快修复它，因为存在未分配的碎片  


    GET /_cat/nodes?v
查看所有NODE  

    GET /_cat/indices?v
查看指标，主要涉及各个index的东西
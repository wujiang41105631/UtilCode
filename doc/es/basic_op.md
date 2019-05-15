# 创建index
PUT /fengjrblog1

PUT /customer?pretty

# index添加mapping
PUT fengjrblog1/_mappings

{
"properties": {
"type": {
"type": "keyword"
},
"name": {
"type": "text"
},
"user_name": {
"type": "keyword"
},
"email": {
"type": "keyword"
},
"content": {
"type": "text"
},
"tweeted_at": {
"type": "date"
}
}
}

# 获取刚才添加的mapping
GET fengjrblog1/_mappings

# 指定ID添加数据
PUT fengjrblog1/_doc/1
{
"type": "user",
"name": "Shay1 Banon",
"user_name": "kimchy1",
"email": "shay@kimchy.com"
}

# 不指定ID添加数据
POST fengjrblog1/_doc
{
"type": "user12",
"name": "11Shay1 Banon",
"user_name": "1kimchy1",
"email": "1shay@kimchy.com"
}

# 全表扫描
GET  fengjrblog1/_search

# 根据ID获取数据
GET fengjrblog1/_doc/1

# 根据ID更新
POST fengjrblog1/_update/1
{
"doc" : {
"user_name" : "qux"
}
}

# 批量插入
POST _bulk
{"index":{"_index":"fengjrblog1","_id":"10"}}
{"type":"user5","name":"11Shay5 Banon","user_name":"3kimchy1","email":"3shay@kimchy.com"}
{"index":{"_index":"fengjrblog1","_id":"11"}}
{"type":"user5","name":"11Shay5 Banon","user_name":"4kimchy1","email":"4shay@kimchy.com"}

# 模版创建
PUT _template/template1
{
"index_patterns":[ "index-1-*" ],
"mappings": {
"properties": {
"foo": {
"type": "keyword"
}
}
}
}

PUT _template/template2?include_type_name=true
{
"index_patterns":[ "index-2-*" ],
"mappings": {
"type": {
"properties": {
"foo": {
"type": "keyword"
}
}
}
}
}

PUT index-1-01?include_type_name=true
{
"mappings": {
"type": {
"properties": {
"bar": {
"type": "long"
}
}
}
}
}

PUT index-2-01
{
"mappings": {
"properties": {
"bar": {
"type": "long"
}
}
}
}

GET /fengjrblog1/_search
GET /fengjrblog1/_doc/TvU6Y2oBQ9fsOOXoB3qy
POST /fengjrblog1/_update/TvU6Y2oBQ9fsOOXoB3qy
{
"doc":{
"name": "Jane Doe",
"user_name":"zhangsan"
}
}
DELETE /sinablog/_doc/user-kimchy
DELETE /customer?pretty
GET /_cat/shards
GET /_cat/health?v
GET /_cat/nodes?v
GET /_cat/indices?v
GET /fengjrblog1/_count

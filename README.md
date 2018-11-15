### 软件工程项目


### API文档

#### 0x00 登录接口

* url:http://iot.wduozhi.xyz/api/user/login
* method:post

##### get

* username
* password

##### return

```
{
    "data": {
        "id": 1,
        "username": "coding",
        "nick_name": "小智e",
        "status": 1
    },
    "message": null,
    "status": "success"
}

{
    "data": null,
    "message": "passwrd error or user not exist",
    "status": "error"
}
```


#### 0x01 添加网关

* url:http://iot.wduozhi.xyz/api/gateway
* method:post

##### get

* ip
    - ip,string
* port
    - 端口,string
* description
    - 描述,string
* location
    - 地点，位置,string

##### return

```
{
    "data": {
        "id": 7,
        "ip": "127.0.0.1",
        "port": "8080",
        "description": "haha，我是网关6号",
        "location": "天马-1-3-135",
        "status": 1
    },
    "message": null,
    "status": "success"
}
```

#### 0x02 获取全部网关信息

* url:http://iot.wduozhi.xyz/api/gateway
* method:get

##### get

null

##### return

```
{
    "data": [
        {
            "id": 2,
            "ip": "127.0.0.1",
            "port": "8080",
            "description": "haha，我是网关1号",
            "location": "天马-1-3-135",
            "status": 1
        },
        {
            "id": 3,
            "ip": "127.0.0.1",
            "port": "8080",
            "description": "haha，我是网关2号",
            "location": "天马-1-3-135",
            "status": 1
        }
    ],
    "message": null,
    "status": "success"
}
```

#### 0x03 根据id获取网关信息

* url:http://iot.wduozhi.xyz/api/gateway/{1d}
* method:get

##### get

* id

##### return
```
{
    "data": {
        "id": 2,
        "ip": "127.0.0.1",
        "port": "8080",
        "description": "haha，我是网关1号",
        "location": "天马-1-3-135",
        "status": 1
    },
    "message": "",
    "status": "success"
}
```


#### 0x04 添加传感器

* url:http://iot.wduozhi.xyz/api/sensor
* method:post

##### get

* description
    - 描述,string
* location
    - 地点,string
* factory
    - 厂商,string
* install_time
    - 安装时间,时间戳
* maintenance_time
    - 维修截止时间,时间戳
* produce_date:1234567890000
    - 生产时间,时间戳
* gate_id
    - 传感器id
* classify_id
    - 传感器类型id


##### return 

```
{
    "data": {
        "id": 6,
        "description": "haha，我是网关四号",
        "location": "天马-1-3-135",
        "factory": "华美集团",
        "install_time": "2009-02-13T23:31:30.000+0000",
        "produce_date": "2009-02-13T23:31:30.000+0000",
        "maintenance_time": "2009-02-13T23:31:30.000+0000",
        "status": 1
    },
    "message": "",
    "status": "success"
}
```


#### 0x05 根据id获取传感器信息

* url:* url:http://iot.wduozhi.xyz/api/sensor/{id}
* method:get

##### get

null

##### return

```
{
    "data": {
        "id": 3,
        "description": "haha，我是网关一号",
        "location": "天马-1-3-135",
        "factory": "华美集团",
        "install_time": "2009-02-13T23:31:30.000+0000",
        "produce_date": "2009-02-13T23:31:30.000+0000",
        "maintenance_time": "2009-02-13T23:31:30.000+0000",
        "status": 1
    },
    "message": "",
    "status": "success"
}
```

#### 0x06 获取某网关下的传感器列表

* url:* url:http://iot.wduozhi.xyz/api/gateway/sensors/{id}
* method:get

##### get

gateway's id

##### return
```
{
    "data": [
        {
            "id": 2,
            "description": "haha，我是一个小小的网关",
            "location": "天马-1-3-135",
            "factory": "华美集团",
            "install_time": "2009-02-13T23:31:30.000+0000",
            "produce_date": "2009-02-13T23:31:30.000+0000",
            "maintenance_time": "2009-02-13T23:31:30.000+0000",
            "status": 1
        },
        {
            "id": 3,
            "description": "haha，我是网关一号",
            "location": "天马-1-3-135",
            "factory": "华美集团",
            "install_time": "2009-02-13T23:31:30.000+0000",
            "produce_date": "2009-02-13T23:31:30.000+0000",
            "maintenance_time": "2009-02-13T23:31:30.000+0000",
            "status": 1
        }
    ],
    "message": "",
    "status": "success"
}
```



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

#### 0x03-1 修改网关信息

* url:http://iot.wduozhi.xyz/api/gateway
* method:put

##### get
* id
    - 网关id
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
        "id": 1,
        "ip": "127.0.0.1",
        "port": "8080",
        "description": "网关1号",
        "location": "天马-1-3-135",
        "status": 0
    },
    "message": null,
    "status": "success"
}
```

#### 0x03-2 删除网关

* url:http://iot.wduozhi.xyz/api/gateway/{1d}
* method:delete

##### get

* id

##### return
```
{
    "data": null,
    "message": null,
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

#### 0x04-1 修改传感器

* url:http://iot.wduozhi.xyz/api/sensor
* method:put

##### get
* id
    - 传感器id
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

#### 0x05-1 根据id删除传感器

* url:* url:http://iot.wduozhi.xyz/api/sensor/{id}
* method:delete

##### get

id

##### return

```
{
    "data": null,
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

#### 0x07 获取某网关下的传感器类型列表 


* url:http://iot.wduozhi.xyz/api/gateway/classify/{id} 
* method:get 

##### get

gateway's id

##### reurn 

```
{
    "data": [
        {
            "id": 1,
            "name": "温度传感器",
            "status": 1,
            "sensors": [
                {
                    "id": 3,
                    "description": "haha，我是网关一号",
                    "location": "天马-1-3-135",
                    "factory": "华美集团",
                    "install_time": "2009-02-13T23:31:30.000+0000",
                    "produce_date": "2009-02-13T23:31:30.000+0000",
                    "maintenance_time": "2009-02-13T23:31:30.000+0000",
                    "status": 1
                },
                {
                    "id": 6,
                    "description": "haha，我是网关四号",
                    "location": "天马-1-3-135",
                    "factory": "华美集团",
                    "install_time": "2009-02-13T23:31:30.000+0000",
                    "produce_date": "2009-02-13T23:31:30.000+0000",
                    "maintenance_time": "2009-02-13T23:31:30.000+0000",
                    "status": 1
                }
            ]
        },
        {
            "id": 2,
            "name": "湿度传感器_1",
            "status": 1,
            "sensors": [
                {
                    "id": 4,
                    "description": "haha，我是网关二号",
                    "location": "天马-1-3-135",
                    "factory": "华美集团",
                    "install_time": "2009-02-13T23:31:30.000+0000",
                    "produce_date": "2009-02-13T23:31:30.000+0000",
                    "maintenance_time": "2009-02-13T23:31:30.000+0000",
                    "status": 1
                }
            ]
        },
        {
            "id": 3,
            "name": " 湿度传感器_2",
            "status": 1,
            "sensors": [
                {
                    "id": 5,
                    "description": "haha，我是网关三号",
                    "location": "天马-1-3-135",
                    "factory": "华美集团",
                    "install_time": "2009-02-13T23:31:30.000+0000",
                    "produce_date": "2009-02-13T23:31:30.000+0000",
                    "maintenance_time": "2009-02-13T23:31:30.000+0000",
                    "status": 1
                }
            ]
        }
    ],
    "message": "",
    "status": "success"
}
```

#### 0x08 添加传感器类型

* url:http://iot.wduozhi.xyz/api/classify 
* method:post

##### get

* name
    - 类型名称，string

##### return 

```
{
    "data": {
        "id": 4,
        "name": "湿度传感器",
        "status": 1,
        "sensors": null
    },
    "message": "",
    "status": "success"
}
```

#### 0x09 获取所有传感器类型

* url:http://iot.wduozhi.xyz/api/classify
* method:get

##### get 

null

##### return 

```
{
    "data": [
        {
            "id": 1,
            "name": "温度传感器",
            "status": 1,
            "sensors": null
        },
        {
            "id": 2,
            "name": "湿度传感器_1",
            "status": 1,
            "sensors": null
        },
        {
            "id": 3,
            "name": " 湿度传感器_2",
            "status": 1,
            "sensors": null
        },
        {
            "id": 4,
            "name": "湿度传感器_3",
            "status": 1,
            "sensors": null
        }
    ],
    "message": "",
    "status": "success"
}
```

#### 0xA 获取某传感器类型下的传感器

* url：http://iot.wduozhi.xyz/api/classify/sensors/{id}
* method:get

##### get

classify's id

##### return

```
{
    "data": [
        {
            "id": 3,
            "description": "haha，我是网关一号",
            "location": "天马-1-3-135",
            "factory": "华美集团",
            "install_time": "2009-02-13T23:31:30.000+0000",
            "produce_date": "2009-02-13T23:31:30.000+0000",
            "maintenance_time": "2009-02-13T23:31:30.000+0000",
            "status": 1
        },
        {
            "id": 6,
            "description": "haha，我是网关四号",
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


# simpleSBMB接口文档


**更新日志：**

|     日期     |                    描述                    | 操作人  |
| :--------: | :--------------------------------------:    | :--: |
| 2018-09-13 | simpleSBMB接口文档                           | xia  |

**状态码：**  

| code |            message            |
| :--: | :---------------------------: |
| 200  |             请求成功            |
| 201  |             参数异常           |
| 301  |             未登录或token失效           |
| 400  |             资源不存在         |

**域名：**  

| ／         |            域名               |备注 |
| :--:     | :---------------------------: |:--: | 
| 接口域名  |   http://api.xia.com      |     |
| 图片域名  |   http://img.xia.com      |     |


## 目录
[TOC]

##一、登录接口
> 接口地址：<u>/user/register.do</u>

* 请求方式：POST
 
* 请求参数：  

|  参数            |  类型  |  说明       | 是否必须 |
| :--:            | :--: | :--:         | :--: |
| username        | String  | 用户名    |  是   |
| password        | String  | 密码      |  是   |
* 请求示例：

```
{
	"username":"xia",
	"password":"123456"
}
```

*   响应结果示例：

```
{
    "msg": "success",
    "code": 200
}
```
* Authorization值：

```
Authorization是登录状态的标识，访问除登录接口外的接口都需要在请求头中携带Authorization值，
否则认为没有登录。
Authorization值在调用登录接口时返回在响应头中。
```
##二、登录接口
> 接口地址：<u>/user/login.do</u>

* 请求方式：POST
 
* 请求参数：  

|  参数            |  类型  |  说明       | 是否必须 |
| :--:            | :--: | :--:         | :--: |
| username        | String  | 用户名    |  是   |
| password        | String  | 密码      |  是   |
* 请求示例：

```
{
	"username":"xia",
	"password":"123456"
}
```

*   响应结果示例：

```
{
    "msg": "success",
    "code": 200,
    "data": {
        "id": 1,
        "name": "xia",
        "phoneNum": null,
        "age": 0,
        "createdTime": "2018-09-12 07:37:09",
        "updatedTime": "2018-09-12 07:37:09"
    }
}
```
* Authorization值：

```
Authorization是登录状态的标识，访问除登录接口外的接口都需要在请求头中携带Authorization值，
否则认为没有登录。
Authorization值在调用登录接口时返回在响应头中。
```


##三、添加书籍
> 接口地址：<u>/book/add.do</u>

* 请求方式：POST
 
* 请求参数：
* 
|  参数            |  类型  |  说明       | 是否必须 |
| :--:            | :--: | :--:         | :--: |
| title           | String  | 书名       |  是   |
| bookDesc        | String  | 书简介      |  否   |
| price           | String  |  价格      |  是   |

* 请求示例：
* 
```
{
	"title":"天龙八部",
	"bookDesc":"飞雪连天射白鹿，笑书神侠倚碧鸳",
	"price":"8.8"
}
```


*  响应结果示例：

```
{
    "msg": "success",
    "code": 200,
    "data": [
        {
            "id": 3,
            "title": "天龙八部",
            "bookDesc": "飞雪连天射白鹿，笑书神侠倚碧鸳",
            "price": "8.8",
            "createdTime": "2018-09-12 07:40:59",
            "updatedTime": "2018-09-12 07:40:59"
        },
        {
            "id": 1,
            "title": "天龙八部",
            "bookDesc": "飞雪连天射白鹿，笑书神侠倚碧鸳",
            "price": "8.8",
            "createdTime": "2018-09-12 07:38:03",
            "updatedTime": "2018-09-12 07:38:03"
        }
    ]
}
```


##三、获取书列表
> 接口地址：<u>/book/list.do</u>

* 请求方式：GET
 
* 请求参数：无

*  响应结果示例：

```
{
    "msg": "success",
    "code": 200,
    "data": [
        {
            "id": 3,
            "title": "天龙八部",
            "bookDesc": "飞雪连天射白鹿，笑书神侠倚碧鸳",
            "price": "8.8",
            "createdTime": "2018-09-12 07:40:59",
            "updatedTime": "2018-09-12 07:40:59"
        },
        {
            "id": 1,
            "title": "天龙八部",
            "bookDesc": "飞雪连天射白鹿，笑书神侠倚碧鸳",
            "price": "8.8",
            "createdTime": "2018-09-12 07:38:03",
            "updatedTime": "2018-09-12 07:38:03"
        }
    ]
}
```








# mysql使用说明
本项目使用的数据库是mysql。此处为补充说明，具体使用请查看项目代码。



##一、数据参数
    type: com.alibaba.druid.pool.DruidDataSource
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/xia-test
    username: root
    password: root

##二、用到的数据库表结构
项目中用到两个数据库表：
user：存储用户信息。
book：存储书籍信息。

* user表创建的sql如下：

```
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `age` int(4) DEFAULT NULL,
  `phone_num` varchar(20) DEFAULT NULL,
  `created_time` timestamp NULL DEFAULT NULL,
  `updated_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
)

```
* book表创建的sql如下：


```
CREATE TABLE `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `book_desc` varchar(500) DEFAULT NULL,
  `price` varchar(50) DEFAULT NULL,
  `created_time` timestamp NULL DEFAULT NULL,
  `updated_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) 
```




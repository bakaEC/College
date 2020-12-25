# Chapter 5.4 反弹注入

[TOC]

## 0x00 原理

	当遇到SQL注入点可执行注入，却由于无法正常显错等功能缺陷或工具猜解速度慢等效率缺陷导致无法快速高效地获取注入结果，我们通常会引入反弹注入的概念。

反弹注入：在对外网的数据库实现查询后使用opendatasource()将查询结果跨域输出到准备好的具有公网IP的相同架构数据库中，由此查询到注入的输出结果。

## 0x01 相关概念

### opendatasource() 函数	

语法: `OPENDATASOURCE(provider_name,init_string)`

1. provider_name

   注册为用于访问数据源的 OLE DB 提供程序的 PROGID 的名称。*provider_name* 的数据类型为 char，没有默认值。

2. init_string

   连接字符串，提供程序字符串。语法是以关键字值对为基础的，这些关键字值对由分号隔开，例如："keyword1=value; keyword2=value"

   例：连接地址+端口+用户名+密码+数据库名

   server=[IP],[PORT];uid=[USERID];pwd=[PASSWORD];database=[DBNAME]

### MSSQL总表查询

每个数据库创建后都会有一些系统表用来存储该数据库的一些基本信息，而每个表和视图中的每列在系统表中占一行，存储过程中的每个参数在表中也占一行。

 1. **sysobjects**

    sysobjects属于系统对象表，保存了当前数据库中所有的对象，通常在注入过程中使用其查询所有表。（用法对标MySQL中的information_schema.tables）

    sysobjects中所含的常用三列：

    |  名字 |                 含义                 |
    | ----: | :----------------------------------: |
    |  name |                 表名                 |
    |    id |               表对应ID               |
    | xtype | 对象类型,通常参数：S=系统表 U=用户表 |

 2. **syscolumns**

    syscolumns属于系统列名表，保存了当前数据库中所有的对象包含的列，通常在注入过程中使用其查询所有列。（用法对标MySQL中的information_schema.columns）

## 0x02 MSSQL报错复现过程

1. 使用布尔型显错注入找到注入点

	![](https://nc0.cdn.zkaq.cn/md/5371/a29ab993668a39fd85cc01be75d8b48e_29277.png)
	![](https://nc0.cdn.zkaq.cn/md/5371/3e29d75c53c2e323336eb86378db4fe7_65701.png)
2. order by查找字段数（三个字段）

	![](https://nc0.cdn.zkaq.cn/md/5371/5eacc5fa84b1f8daab077a0ae3ae491b_21820.png)
3. 在注入点通过sysobject总表查询表名及表ID，用xtype='U'限制查询用户表

	![](https://nc0.cdn.zkaq.cn/md/5371/97c16727e85f51293ba9811ef3cb7f22_64086.png)
4. 通过syscolumn总表及所需表admin的ID查询表内列名

	![](https://nc0.cdn.zkaq.cn/md/5371/d6a19655c0b51154e4a71e1e5f7920d3_78595.png)
5. 通过列名和表名获得信息

	![](https://nc0.cdn.zkaq.cn/md/5371/fdb032a4ebaf601b216ea03b4bfae5e4_18273.png)

## 0x03 思考与总结

1. 反弹注入的必要条件：

   有堆叠注入：能实现注入语句在解析时的分句，才能单独进行一次插入操作。

   目标有外网环境及访问权限：目标所在的数据库服务器必须能够自主访问外网，才能使opendatasource()函数生效

2. SQL语句注释方法尽量使用`--+`，`#`和`%23`只能在MySQL数据库中使用。

3. 猜输出点的时候尽量使用`NULL`进行占位，可以避免不必要的字段类型猜解。

4. 联合查询尽量使用`UNION ALL`可以避免不同表的相同信息冲突。
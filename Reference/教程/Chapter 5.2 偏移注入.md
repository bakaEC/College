# Chapter 5.2 - 偏移注入

[TOC]

---

## 0x00 原理

在SQL注入的过程中很可能遇到已知`表名`却无法得知`列名`的情况，这样的情况往往是由于这样的情况往往是由于访问系统自带库的权限不足或是不具有系统自带库的数据库系统。在我们平常使用的过程中，通过暴破只能得出一些常用弱名称，对于一些复杂的名称而言就暴破更加困难，所以我们引入了偏移注入的概念。

偏移注入是一种表内的联合查询，**实际上就是通过不断移动查询信息在回显点的输出位置来从回显点获取字段信息。**

## 0x01 注入思路

1. 判断注入点

2. `order by` 判断注入字段长度

3. 判断\暴破表名

 - MySQL：使用系统自带库"Information_schema.tables"
 - Access：使用"**AND exists(select * from [table_name])**"

4. 获取表中字段在页面上的回显点 **SELECT [n],[n],[n] from table_name**

 - 回显点可能存在于：页面文本、图片路径、链接文字等

5. 加入[table_name.\*]通过不断减少[table_name.*]前偏移测试要测试表的列字段数

6. 开始注入，不断减少\增加前后的占位字段，直到回显字段显示需要信息

 ```mysql
 UNION SELECT [n],[n],[n],[table_name.*] from [table_name]
 ```

 >偏移的位置计算：
 >
 >所需回显点-偏移列字段数=前占位字段数
 >
 >总字段数-所需回显点=后占位字段数

7. 页面查找回显字段

## 0x02 注入过程

1. **直接使用SQL语句注入，被WAF拦截**

 ![](https://nc0.cdn.zkaq.cn/md/5371/56ac3a9939c06f1f70344598851fc102_49808.png)

2. **尝试删除`?ID=`使用cookie注入，发现可行**

 ![](https://nc0.cdn.zkaq.cn/md/5371/d5fea843ca3d5c6a1d80b99290ad3174_46777.png)

3. **使用`order by`得到字段数**

 ![](https://nc0.cdn.zkaq.cn/md/5371/ac22f5930216bef412f82e087aaafbc3_93944.png)

4. **暴破表名**（省略，此处可使用"id=105 and exists(select*from [Table])放入burp暴破）

 ![](https://nc0.cdn.zkaq.cn/md/5371/35357f3cc97b3ff3276ac9324f35088c_60046.png)

5. **获取回显点**

 ![](https://nc0.cdn.zkaq.cn/md/5371/a4c16560b4c74ad070166d83dbc4aa0c_34741.png)

6. **从最后一位开始使用[table_name.*]偏移,在页面可见回显点发现最多显示要获取表的前7个字段**

 ![](https://nc0.cdn.zkaq.cn/md/5371/71718a8cfda5186894097208c2af9125_22827.png)

7. **尝试将占位信息后加上"0000",并在源码浏览窗格查找，发现一个隐藏的回显点**

 ![](https://nc0.cdn.zkaq.cn/md/5371/84ab5ef3058a881c1e3e8e2e7f1d596d_60416.png)

 ![](https://nc0.cdn.zkaq.cn/md/5371/b4e1efe58089d2047dccbbd2308ab900_85742.png)
8. **偏移表字段到此位置，得到需要的信息**********************************

 ![](https://nc0.cdn.zkaq.cn/md/5371/f48879e4792fb76edc18036d34e63913_27235.png)

 ![](https://nc0.cdn.zkaq.cn/md/5371/07f23b7b7ac82c4ff7598048fab38936_42699.png)

## 0x03 思考与要点

1. 在表字段数小于、接近偏移列字段数时，偏移注入因为**缺少偏移空间**很难生效。

2. 回显点并不一定以页面可见文字出现，由于数据库包含很多页面信息，回显点可以在标签的范围内或是标签属性等**任意地方出现**。
3. 在UNION SELECT [n],[n],***,[n] from [table_name]的过程中，如果第一个占位参数大于Cookie传参中的参数，很可能由于排序问题导致无法传参。
4. 此注入方式在MySQL和Access\mssql数据库通用，在MySQL中使用时可以使用`*`代替`table_name.*`。
5. 在Access数据库中代替MySQL数据库的**LIMIT([n],[m])**的语句是**TOP [n]**,其等价语句是**LIMIT([0],[n])**，也就是从从第一条开始取n条，此时要依条遍历整个表的话就要不断的在下取的过程中使用`order by asc`或`order by desc`进行顺序和倒序排列得到数据。
6. 写一个简单的控制台JavaScript脚本，代替繁杂的重复输入占位字段工作：
```JavaScript
var a='占位数字';for(var i=0;i<26;i++){a=a+','+String((i+1)*1000)};console.log(a);
```

![](https://nc0.cdn.zkaq.cn/md/5371/f15d4f9d03b2437d1aeab345880b0a2d_10405.png)


-----

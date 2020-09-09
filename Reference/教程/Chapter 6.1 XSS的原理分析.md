# Chapter 6.1 XSS的原理分析

[toc]

## 0x01 XSS原理

XSS的核心是用户的输入信息被作为页面信息处理。

证明自己能够完全操控网页上的JS控件。

主要应用：通过XSS窃取**Cookie**9

## 0X02 XSS功能

### XSS的主要危害:

1. **盗取Cookie**
2. 获取内网IP
3. 获取浏览器保存的明文密码
4. 截取网页屏幕
5. 网页上的键盘记录

## 0x03 XSS类型

1. 反射型
2. 存储型
3. DOM型

----

触发类型：

1. 标签触发：`<script>alert(1)</script>`
2. 伪协议触发：
   - 不大众的协议，如`weishi://` `telnet://` `php://` `javascript://`=> `javascript:alert(1)`
   - 大众协议，如`http://` `ftp://`等


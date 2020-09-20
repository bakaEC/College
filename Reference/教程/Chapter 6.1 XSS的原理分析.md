# Chapter 6.1 - 反射型XSS

[TOC]

## 0x01 XSS原理

XSS的核心：用户的输入信息被作为前端代码页面信息处理。

XSS实现表现：证明自己能够完全操控网页上的JS控件。

主要应用：通过XSS窃取**Cookie**。

## 0X02 XSS功能

### XSS的主要危害

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

## 0x04 XSS触发类型

1. 标签触发：`<script>alert(1)</script>`

2. 伪协议触发：
   - 不大众的协议，如`weishi://` `telnet://` `php://` `javascript://`=> `javascript:alert(1)`
   - 大众协议，如`http://` `ftp://`等
   
3. 事件触发：

   - 在Js标签内置属性中加入**事件属性**，条件满足时进行触发

   - 常见事件句柄：

|     属性      |        事件        |
| :-----------: | :----------------: |
|   onload    |   元素被完全加载   |
|   onerror   |    元素加载失败    |
|	oninput	 |	 元素接受文字输入|
|   onclick   |  鼠标单击某个对象  |
|   onfocus   |    元素获得焦点    |
|  onkeydown  |  某个键盘键被按下  |
| onmousedown | 某个鼠标按键被按下 |
| onmousemove |     鼠标被移动     |


----

**Cookie的意义**：

   > Cookie，类型为“**小型文本文件**”，是某些网站为了辨别用户身份，进行[Session](https://baike.baidu.com/item/Session/479100)跟踪而储存在用户本地终端上的数据（通常经过加密），由用户[客户端](https://baike.baidu.com/item/客户端/101081)计算机暂时或永久保存的信息。

   在实际运用中，Cookie要实现对某一站点用户的身份信息保存，以降低页面打扰率，提高用户跨站点访问时的便捷性。由于Cookie带有免用户主动身份验证的属性，在渗透测试过程中常常用于**伪造用户身份**。

----

## 0x05 反射型XSS注入实践

1. **使用普通的反射型XSS Javascript标签，无信息**

	![](https://nc0.cdn.zkaq.cn/md/5371/1b6347609f7f3513fe585dff6ce25fa1_30514.png)

2. **检查页面源码，发现标签`<>`被转义，输入框使用单引号`'`闭合**

	![](https://nc0.cdn.zkaq.cn/md/5371/f85227565b8034c5bebcde2ac138d39f_79811.png)

3. **添加单引号和闭合和注释，使用`oninput`属性在输入时得到回显**

	![](https://nc0.cdn.zkaq.cn/md/5371/ae0ffde741e942089a66a3db55f558b4_13787.png)
   - **也可使用`onfocus`结合`autofocus`属性实现打开页面自动触发XSS**
   	![](https://nc0.cdn.zkaq.cn/md/5371/d49a66ef8ca56083366cb9a82f4a132a_23722.png)

----


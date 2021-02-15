# Chapter 6.4 - Dom Based XSS

[TOC]

## 0x00 原理

> 在网站页面中有许多页面元素，当页面到达浏览器时浏览器会为页面创建一个顶级的Document Object文档对象，接着生成各个子文档对象，每个页面元素对应一个文档对象，每个文档对象包含属性、方法和事件。通过JS脚本可以对文档对象进行编辑从而修改页面的元素。也就是说，客户端的脚本程序可以通过DOM来动态修改页面内容，从客户端获取DOM中的数据并在本地执行。基于这个特性，就可以利用JS脚本来实现XSS漏洞的利用。

相比于存储型XSS而言，DOM型XSS主要注重于**在用户DOM中执行**的跨站攻击，*即payload会被认定为JavaScript语句被DOM结构执行，页面输出时作为前端代码执行*。通过在网页中带存储功能的注入点植入XSS代码（通常为URL中），使网页的访问者携带所需信息（如**cookie**、IP、页面操作）进入预备好的JavaScript文件，从而进行记录、控制等一系列操作。

## 0x01 拓展概念

**以下是几个关键的JS操作DOM对象语句**

document.cookie	获取cookie

document.domain	获取域名

**document.lastModified**	返回修改时间（通常用于判断是否为伪静态网页）

document.referrer	返回源链接

document.title	返回页面标题

document.getElementById()	通过ID返回元素

**document.write**("TEXT")	替代页面输出内容

- 会进行URL转码
- 通过escape()转码
- 通过unescape()解码
- 可以使用native编码格式

document.URL.indexOf("TEXT");  寻找某URL一内容所在的字符串点

document.URL.length 返回URL总长度

substring()截取字符串

**eval()**	通过Js执行(高危，可执行任意Js代码)

**.innerHTML**	改变特定元素HTML内容 

## 0x03 复现

1. **审查源码，发现聊天室标题处对应Script含有Document.write，通过修改传参初步判断存在DOM-XSS**

	![](https://nc0.cdn.zkaq.cn/md/5371/47227b0a79716ba8025fc7ffefa3074a_29377.png)
	![](https://nc0.cdn.zkaq.cn/md/5371/ccb9a8a729386eaa86e2911630ba278f_13077.png)
2. **闭合后通过简单的前端JS代码堆叠注入，测试JS执行情况**

	![](https://nc0.cdn.zkaq.cn/md/5371/ef2db6c1dc23cba8be7872e785314f53_24170.png)
3. **测试`script`标签，被WAF拦截**

	![](https://nc0.cdn.zkaq.cn/md/5371/7693e4331236c8c136c04db1a0624950_81562.png)
4. **生成XSS标签并且将其通过Ascii to Native转义**

	![](https://nc0.cdn.zkaq.cn/md/5371/a786e2ec233f1d402d02eadd8099162f_73813.png)
5. **本地测试通过，抓取到本地信息**

	![](https://nc0.cdn.zkaq.cn/md/5371/737e3efc391d205efd69c9ab3195db4c_98870.png)
6. **通过反馈上传给维护者，引诱其点击**

	![](https://nc0.cdn.zkaq.cn/md/5371/4b2662ce48e7f58bd9670a55dc2ce732_85719.png)
7. **成功获取信息**

	![](https://nc0.cdn.zkaq.cn/md/5371/fe302a04c1e82885bb60a9239fb4741d_89634.png)

## 0x04 防御及修复方法

> DOM型XSS主要是由客户端的脚本通过DOM动态地输出数据到页面而不是依赖于将数据提交给服务器端，而从客户端获得DOM中的数据在**本地执行**，因而仅从服务器端是无法防御的。

1. 避免客户端文档**重写、重定向或其他敏感操作**，同时避免使用客户端数据，这些操作尽量在服务器端使用动态页面来实现；

2. 分析和强化客户端JS代码，特别是受到用户影响的DOM对象，注意能直接修改DOM和创建HTML文件的相关函数或方法，并在输出变量到页面时先进行**编码转义**，如输出到HTML则进行HTML编码、输出到<script>则进行JS编码。

## 0X05 思考与总结

1. DOM-XSS**不经过服务端**，只通过用户本地查询数据，极难通过后台排查，不与后台服务器产生数据交互。由于是一种通过DOM操作前端代码输出的时候产生的问题，所以大部分属于反射型。
2. 由于DOM在浏览器接收到页面对象同时就已经构建完成了，所以DOM-XSS也是立即执行并且无痕迹的。
3. document.write()可以接受native编码。
4. 在某些情况下DOM-XSS的Payload可以通过location.hash()，即设置为锚部分从`#`之后的部分，因此既能让用户JS读取到该参数，又不会让该参数传入到服务器，从而避免WAF检测。location.search()也类似，可以实现把部分参数放在`?`之后。



# Chapter 6.2 - 存储型XSS

## 0x00	原理

> z

相比于反射性XSS而言，存储型XSS其实是注重于**存储在页面中**的跨站攻击(*即用户输入的数据会被存入数据库或特定文件，页面输出时作为前端代码执行*)，而不是即时地输入JavaScript代码攻击。通过在网页中带存储功能的注入点植入XSS代码，使网页的访问者携带所需信息（如**cookie**、IP、页面操作）进入预备好的JavaScript文件，从而进行记录、控制等一系列操作。

通常存储型XSS适用于页面内的储存式可交互显示点，即能被特定用户访问、存储并激发的位置（如评论板、用户面板等）。由于攻击在时间和空间上的随机性，通常使用XSS平台（即托管XSS攻击源文件的平台）进行托管，从而实现多时间多线程的值守。


---

## 0x01	渗透思路

1. **找出XSS注入点并加入XSS插件**
   
   - 在本例中使用的FineCMS存在存储型XSS漏洞，体现在当访问不存在的页面时会出现报错，服务端会将报错通过Write_log()写入错误日志中，并且在管理员访问log日志的同时触发。
2. **XSS插件获取管理员信息**
   
   - 通常由被攻击者将Cookie信息传入XSS插件所指向的XSS平台。
3. **XSS平台收集信息**
   - XSS平台通过KeepSession保留Cookie活性。

   ---

## 0x02	渗透过程

1. **检测靶场CMS，通过CMS指纹识别找到CMS通杀漏洞**（本例中为[FineCMS-错误日志存储型XSS漏洞](https://www.jianshu.com/p/200ea62486d9)）

2. **进入存储型xss靶场 在url栏输入 `?c=test&m=test`，观察404页面上方报错**

	![](https://nc0.cdn.zkaq.cn/md/5371/d39f505287ee120bf41a5346ac292ec9_75689.png)
3. **使用弹窗型Js测试```<Script>alert(1)</script>```，发现可以触发**

	![](https://nc0.cdn.zkaq.cn/md/5371/71f0a3f1f90944baae441b6d512b0894_86584.png)
4. **将传参替换为XSS平台托管的XSS插件写入**
	- 建立XSS平台项目
	![](https://nc0.cdn.zkaq.cn/md/5371/7d200a8e196d1ba692fbf6c0e48be99b_12532.png)

	![](https://nc0.cdn.zkaq.cn/md/5371/863a677a3ea7cd252a327e5bb4a7d036_52809.png)
	
	- 插入到注入点
5. **管理员访问错误日志，得到Cookie**

	![](https://nc0.cdn.zkaq.cn/md/5371/8f5b1bc66606313c1a6753ea2ec0639f_99236.png)

## 0x03	思考与要点

1. 存储型XSS大多数排查方式是**见框就插**
2. 存储型XSS利用的大多数是网站**外紧内松**：
   - 外网管控严格、内网脆弱无比
   - 用户输入的数据处理严格
   - 系统获取的数据处理简单
3. HTML实体编码 -> `htmlspecialchars()` 把PHP内容通过实体编码返回HTML页面，通过此函数可能将隐含PHP储存型XSS加入HTML页面。
4. 在XSS托管平台上通常会使用`KeepSession`模式，在此模式下平台会间隔访问抓取到的Cookie，令会话持续，Cookie保持生效。

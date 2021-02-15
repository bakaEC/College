# Chapter 7 文件上传解析漏洞
## 0x00 漏洞复现

1. **前端验证绕过**

    我们要上传php文件 题目只允许jpg，png，gif 所以先上传一个jpg文件然后抓包修改为php就行了

	![](https://nc0.cdn.zkaq.cn/md/5371/b48d1a6fd5dd3eff08c32a48507b0746_69169.png)
	![](https://nc0.cdn.zkaq.cn/md/5371/ab63f527fc252ee878625560a94edb70_64682.png)
	![](https://nc0.cdn.zkaq.cn/md/5371/364f1aadea398f66e1ddc9f845dcd9fc_71847.png)

2. **Content-Type方式绕过**

   这题也是和第一题类似 但是这是后端验证 虽然方法是一样的 都是抓包修改 但是验证方式不同

   ![](https://nc0.cdn.zkaq.cn/md/5371/eb112301a4a9a1ca47552b68991ab68d_57413.png)
   ![](https://nc0.cdn.zkaq.cn/md/5371/2f7201605383fc05a93a292497efff20_92092.png)
   ![](https://nc0.cdn.zkaq.cn/md/5371/620dedc7d4698c338b8f7c19d6eb7e57_68298.png)

3. **黑名单绕过**

   此题不允许asp aspx php jsp文件上传，但是这个考虑不够严谨，因为在默认状态下php3、php4、php5、phtml都会被解析为php的

   > 同理 jspx jspf 会被解析为jsp，asa cer aspx 会被解析为asp，exee 会被解析为exe
   ![](https://nc0.cdn.zkaq.cn/md/5371/4b7a330dbfca89f0c66dc556af6bfea2_15252.png)
   ![](https://nc0.cdn.zkaq.cn/md/5371/59c263d54d20b2a2211b66337d587e3e_43622.png)

4. **htaccess文件绕过**

   文件中```AddType application/x-httpd-php .jpg```这个指令代表着.jpg文件会当做php来解析，所以创建一个后缀为.htaccess的文件 将代码写进去

   ![](https://nc0.cdn.zkaq.cn/md/5371/de07e5e5040fdcc7a81165737e4c25bb_29450.png)
   ![](https://nc0.cdn.zkaq.cn/md/5371/f05b96027ba4e4f6da4ef2094c346a26_34615.png)

5. **大小写绕过**

   找到没有被拦截的写入方式（比如PhP5）实战情况下或者黑盒测试就要通过不断的尝试

   ![](https://nc0.cdn.zkaq.cn/md/5371/a09486fb02bc848a39ab0c2c1a53daa7_76415.png)
   ![](https://nc0.cdn.zkaq.cn/md/5371/5c0001b016f58dc200074e04ed1986a2_37845.png)

6. **文件后缀（空）绕过**

   文件名后面加入空格后上传，服务器会将空格会自动省略掉 例如`php`与`php`
   ![](https://nc0.cdn.zkaq.cn/md/5371/a91fe3c90588f18dea180562bf42ae40_86778.png)
   ![](https://nc0.cdn.zkaq.cn/md/5371/d7595671412589397b954de337bd8d65_85441.png)
   ![](https://nc0.cdn.zkaq.cn/md/5371/cd2d122d6aa9441f512e80f404d4e076_82360.png)

7. **文件后缀（点）绕过**

   文件名后面留一个`.`上传，`.`会自动省略掉
   ![](https://nc0.cdn.zkaq.cn/md/5371/ad09d47a4a76d50d95ee10af1a2f2532_42400.png)
   ![](https://nc0.cdn.zkaq.cn/md/5371/f909f8390583825d49bb1964061530ae_53332.png)

8. **::$DATA(Windows文件流)绕过**

   与上面两种方式相似，在检测时能够绕过后缀检测。[文件名]::$DATA默认不修改文件流

	![](https://nc0.cdn.zkaq.cn/md/5371/6e221f67aa9e70f772bef4a41ae529a8_81651.png)
	![](https://nc0.cdn.zkaq.cn/md/5371/4981ae37db33fc44dafccf999677a2f9_69189.png)

9. **构造文件后缀绕过**

   构造一个后缀php. .（通过源码可知它会默认去除最后一个. 然后首位去空 最后只剩下一个. 回到php）

   ![](https://nc0.cdn.zkaq.cn/md/5371/62674995e7df4cd78770d67f752cbd69_63319.png)
   ![](https://nc0.cdn.zkaq.cn/md/5371/5dc9ab998eb58e26e5f767aad1fc637b_71290.png)

10. **双写绕过**

    如果后端将检测到的危险字符替换为空（如.php替换为空），则可以尝试双写后缀名，留下一个后缀

	![](https://nc0.cdn.zkaq.cn/md/5371/8c40ebca7473f15cd19064e982ff0967_44600.png)
	![](https://nc0.cdn.zkaq.cn/md/5371/4b47078f15ec978f6a694415a6197388_49619.png)

11. **%00截断**

    虽然后端会将上传文件自动重命名，但是由于它对文件名进行了拼接，导致我们可以构建任何.php后缀的文件夹(例../upload/xxj.php%00) ，访问%00就可以绕过

	![](https://nc0.cdn.zkaq.cn/md/5371/a63cea95c6824439c63dcc9793aa87e4_15957.png)
	![](https://nc0.cdn.zkaq.cn/md/5371/1f2b59b5428bc12de4d4002beaa37768_28566.png)
	![](https://nc0.cdn.zkaq.cn/md/5371/bc5004871faa75b9c9cdbed31aa53dd5_62370.png)

12. **%00截断（二）**

    与上述截断相似，抓包请求改为POST方式提交save_path，但是POST使用文本形式%00会失效，因为POST传参不会进行URL解码。这时我们修改Hex中对应路径数据为00就能成功截断

	![](https://nc0.cdn.zkaq.cn/md/5371/e8aa514a6fe87b8e25c78213e174fb6f_95084.png)
	![](https://nc0.cdn.zkaq.cn/md/5371/587411a20ec0701a2493315a2056c9aa_58119.png)
	![](https://nc0.cdn.zkaq.cn/md/5371/883e1ab1d56b482e9b73a0449aa10511_64822.png)
	![](https://nc0.cdn.zkaq.cn/md/5371/8bcfb42c375a625f347807ffda00be61_63869.png)

13. **图片马**

    写一个木马文本文件，与图片进行合并做成一张图片马并上传

	![](https://nc0.cdn.zkaq.cn/md/5371/a3e4e012c9497d00e0448e84c06f7f86_40877.png)
	![](https://nc0.cdn.zkaq.cn/md/5371/64d509cda3c19f1dfc830aeb3c047560_20778.png)
	![](https://nc0.cdn.zkaq.cn/md/5371/1501535104959d9787d27eecffecd76a_59631.png)

14. **getimagesize图片类型绕过**

    本题限制上传jpeg/png/gif，上传后通常需要下载验证有效性

	![](https://nc0.cdn.zkaq.cn/md/5371/219e1d1d6cf19fdfaefe8d83a01c89e7_65576.png)
	![](https://nc0.cdn.zkaq.cn/md/5371/77aec0776a7dfc084e6cb1b938610cd4_58707.png)
	![](https://nc0.cdn.zkaq.cn/md/5371/0675f952f9e1236c9342caaa03be40a9_33985.png)
	![](https://nc0.cdn.zkaq.cn/md/5371/54d7a97df703611116a224fa8c2d8f10_45621.png)

15. **php_exif模块图片类型绕过**

	![](https://nc0.cdn.zkaq.cn/md/5371/7a4f890ed21b06b6ff79dc7b0a677243_42548.png)
	![](https://nc0.cdn.zkaq.cn/md/5371/67c2637ba42aa1ece19b5b7e3e05518b_24972.png)
	![](https://nc0.cdn.zkaq.cn/md/5371/fc20e2df19b6eaf48e72218a8f7bc9a8_72615.png)

16. **二次渲染绕过**

    后端会将上传的图片重新渲染，木马信息可能会被修改或者删除，推荐上传GIF格式绕过二次渲染

    > 做法：先将GIF图片进行HEX编辑，将木马信息写入000000~0000030二次渲染一般是从五行后面开始修改渲染，能很大程度防止被修改（图片可能会出现变色、信息丢失属于正常情况）。通过上传再下载，检查木马是否有效。

	![](https://nc0.cdn.zkaq.cn/md/5371/8759ca8e4b4bc01ef7210c57ece451b9_38984.png)
	![](https://nc0.cdn.zkaq.cn/md/5371/aca71de13521ccb703de26e4c38310d7_36192.png)
	![](https://nc0.cdn.zkaq.cn/md/5371/8ef374da5b2eab3085b91a13476b65b6_50095.png)

17. **条件竞争绕过**

    后端会将内容上传移动再检测，使用`file_put_contents()`函数新建文件并写入内容。在上传文件还没被后端检测后删除前访问上传文件，抓包，再跑一个上传之后php的包，当上传之后的php访问成功时会生成一个新的的php文件

    ```php
    <?php file_put_contents('baka.php','<?php e v al($_REQUEST[8]);?>)?'>
    ```

	![](https://nc0.cdn.zkaq.cn/md/5371/46fbb2a26444266edbf1d0b74438bf06_36224.png)
	![](https://nc0.cdn.zkaq.cn/md/5371/07f365389b5c5a91cc96a57e95c9990b_99638.png)
18. **move_uploaded_file()截断**
    类似于00截断，但是截的不是路径而是上传文件的名字

	![](https://nc0.cdn.zkaq.cn/md/5371/75f40b3c53fa76d138fcd94dae24360e_84160.png)
	![](https://nc0.cdn.zkaq.cn/md/5371/3e6cb2bd851379b6d92c0479cfe491dc_69473.png)
	![](https://nc0.cdn.zkaq.cn/md/5371/6fe4b8541671d95c3c510c6c1dc18df7_40856.png)
	![](https://nc0.cdn.zkaq.cn/md/5371/0f26e76cd5acc973cbf685efc8c1e7cf_38589.png)
20. **解析漏洞（一）**
    利用iis6.0的漏洞。由源码可知可上传asa文件，利用图片码的方式上传并抓包修改为asa。菜刀连接脚本类型选择asp。

	![](https://nc0.cdn.zkaq.cn/md/5371/74fafabeec3a984b03707b02190173e7_10284.png)
	![](https://nc0.cdn.zkaq.cn/md/5371/5a44914799cf7eb04b50fa37e538acd3_44961.png)
	![](https://nc0.cdn.zkaq.cn/md/5371/ea824a8de41dd37c35878c99c39273a7_28001.png)
	![](https://nc0.cdn.zkaq.cn/md/5371/72d724644511c81dac0480e31b07f7ab_63041.png)

21. **解析漏洞（二）**
    本题可用分号截断 `xxx.asp;jpg`

	![](https://nc0.cdn.zkaq.cn/md/5371/ef98c544dfe29bba2549715828754311_21662.png)
	![](https://nc0.cdn.zkaq.cn/md/5371/ea2bf3f77befca1d4ab01a14445f4978_58204.png)

22. **解析漏洞（三）**
    这个漏洞会让a.asp文件夹下的所有文件全部以asp解析执行

	![](https://nc0.cdn.zkaq.cn/md/5371/d4188ed1b030be807339f74d04167bd8_14613.png)
	![](https://nc0.cdn.zkaq.cn/md/5371/a4837e98fbdc1a4e11dc01d3be7cfc67_68675.png)

23. **解析漏洞（四）**
    cgi解析漏洞（IIS 7.5/Nginx存在）当访问`a.jpg/.php`，检测到后面的php文件不存在时，会把前面的文件作为php解析

	![](https://nc0.cdn.zkaq.cn/md/5371/4ceaeca1b00d146c30dcf864209d4607_90231.png)
	![](https://nc0.cdn.zkaq.cn/md/5371/693dd42f8f3249dd6e98bb9c101b410f_30209.png)

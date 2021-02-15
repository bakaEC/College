# Chapter 8.1 验证码绕过 

## 常见的验证码绕过场景

一、前端验证验证码，并没有后端验证。直接抓包然后进行跑数据包，反正有没有验证码的阻碍

二、验证码设置了但是并没有效验，乱输验证码也能够成功的登录（估计老板没给开发工资吧）

三、验证码可以重复使用，比如现在的验证码1111，然后虽然你登录失败后验证码会变，但是你输入1111他却判定你验证码正确（常见）https://www.uedbox.com/post/14207/

四、.验证码空值绕过，比如，我们现在抓一个包，发现登录参数是user=admin&password=admin&yzm=4123。 yzm验证码参数，但是我们如果去掉yzm的传参我们就可以绕过验证码机制，直接传参user=admin&password=admin，验证码就失效了 https://www.uedbox.com/post/22266/

五、验证码干扰过低，轻松使用脚本识别 https://www.uedbox.com/post/10085/

六：验证码会在HTML页面输出。 https://www.uedbox.com/post/16869/

七、验证码可控制，比如他的验证码包含在URL里面，是一个URL传参，我们可以把URL设置定，那么验证码可控制 https://www.uedbox.com/post/29913/

八、验证码有规则，比如是时间戳的后6位（rand函数进行随机数）

九、有万能验证码，验证码无论是什么，只要输入000000就能直接绕过

十、验证码有的时候会藏在cookie里面，分析一下是不是存在验证码的参数

十一、图片验证码，类型太少，容易识别 https://www.uedbox.com/post/24112/


## 复现过程

1. 从页面支持信息获取到CMS版本，在本地搭建后得到后台登陆地址为/cmsadmin/

	![](https://nc0.cdn.zkaq.cn/md/5371/83cc75cc83259ae0d51335ee7a61a928_89744.png)
2. 抓取登陆包

	![](https://nc0.cdn.zkaq.cn/md/5371/94f6847c7e20ca3b8dc9547c52c6555f_48676.png)
3. 在尝试错误账号密码后得到密码不匹配返回

	![](https://nc0.cdn.zkaq.cn/md/5371/d41f56703272fb58f13ea777d4c28344_10202.png)
4. 在不更改验证码改变密码时仍然得到密码不匹配返回，证明此处验证码可以绕过

	![](https://nc0.cdn.zkaq.cn/md/5371/db34b6a82079f38dd1604baf97cc8c63_64976.png)
5. 使用burp对密码进行暴破

	![](https://nc0.cdn.zkaq.cn/md/5371/050f3ef5caa108e1450371040c828d26_56844.png)
	![](https://nc0.cdn.zkaq.cn/md/5371/6eed36a096e7d9e19a2c50c698535d2b_76239.png)
6. 得到管理员账号密码

	![](https://nc0.cdn.zkaq.cn/md/5371/3d8aeb64d4981da5da57b5c0dea8dc2c_65989.png)
	![](https://nc0.cdn.zkaq.cn/md/5371/1f4e1c4066b098440ab913a944d1fee5_74025.png)
7. 审查源码，发现在根目录下的a_templetex.php文件中存在一处文件上传漏洞，可以直接将文件保存到根目录解析

	![](https://nc0.cdn.zkaq.cn/md/5371/0600e49b53789350e45d163a349affec_49976.png)
8. 在此页面抓包并且通过`Change request method`改变发包方式

	![](https://nc0.cdn.zkaq.cn/md/5371/1204f16e54866f8cc0d12512f55271f0_36981.png)
	![](https://nc0.cdn.zkaq.cn/md/5371/c19643b8704c1a24099694b6abc8f740_59661.png)
9. 更改页面传参及发包内容，验证能取得phpinfo

	![](https://nc0.cdn.zkaq.cn/md/5371/e5fc21240ae3ef35fa36add9c0e0d10b_98992.png)
10. 菜刀链接上传好的文件，得到flag

	![](https://nc0.cdn.zkaq.cn/md/5371/ecad56c793e38b400092d550aa7a2dd2_93585.png)
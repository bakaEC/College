# Chapter 8.1 验证码绕过 


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
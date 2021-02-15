# Chapter 9.2 变量覆盖

## 0x00 漏洞复现

1. 先在本地安装靶场CMS，进行代码审计（可自动审计 自动审计依靠正则表达式去搜索全文符合添加规则的内容（可以自己构建正则放入进去）

	![](https://nc0.cdn.zkaq.cn/md/5371/741d2ecabbdc2361b0ba930767f0e727_11504.png)
	![](https://nc0.cdn.zkaq.cn/md/5371/d2886132cdfd0563fd6b18f45e8395e0_45970.png)
2. 找到可以变量覆盖的点，进行具体的代码分析，本题在common.php中有出现漏洞

	![](https://nc0.cdn.zkaq.cn/md/5371/25a43ebbff25c29fa849d255ec98ca0d_41058.png)
	![](https://nc0.cdn.zkaq.cn/md/5371/24c17579da7da25d37124a1cbde56edf_73505.png)
3. 对代码函数进行分析，利用die()函数来定位可利用的点

	![](https://nc0.cdn.zkaq.cn/md/5371/5dfc82f3bccd0d6e619313b4bfe9d4d2_33836.png)
	![](https://nc0.cdn.zkaq.cn/md/5371/7171dbfe2a7d29635c96042541b58774_45980.png)
4. 全局搜索查找发现主页和管理员登录都有调用common.php，尝试获得管理员的权限

	![](https://nc0.cdn.zkaq.cn/md/5371/9f64af48a0d1a8ed66d9060bfc645c29_71794.png)
	![](https://nc0.cdn.zkaq.cn/md/5371/8276f8a1fcc51e617b422eafd192dbcd_57733.png)
5. 利用session变量覆盖，在登录成功的代码处写一个die()函数看看登录成功后的显示

	![](https://nc0.cdn.zkaq.cn/md/5371/9b8ef2a00c937e7149a0887bce38cf5f_51481.png)
	![](https://nc0.cdn.zkaq.cn/md/5371/ed450b8dc7abb567e3f307fe7f6c991a_29579.png)
6. 使用session 必须要有session_start，全局搜索session_start 找到可以利用的点。

![](https://nc0.cdn.zkaq.cn/md/5371/55291437da62c5e1fa6a145a28807694_62565.png)
7. 必须要在漏洞之前开启session_start，所以将die()函数执行完成后得到的session进行修改并加上开启session_start的文件php（这里指的是common.php）后再直接输入到url栏里。执行完成后退回到后台 发现后台可直接进入了。到此本地测试结束，进入实战靶场。

   ```
   interface/comment.php?_SESSION[duomi_ckstr]=rgyp&_SESSION[duomi_admin_id]=1&_SESSION[duomi_group_id]=1&_SESSION[duomi_admin_name]=admin
   ```

	![](https://nc0.cdn.zkaq.cn/md/5371/088d601785016fe0b7702804418c02a8_41447.png)
	![](https://nc0.cdn.zkaq.cn/md/5371/c014c2c0e61a22fc7479a37ca18a62e0_36198.png)

8. 靶场和本地一样，唯一不同就是域名开头：之前的session在靶场直接用，输入进去后退回后台就可以直接进去。flag就在后台。

	![](https://nc0.cdn.zkaq.cn/md/5371/781dd0c600b90350f26970637a0616a2_52847.png)
	![](https://nc0.cdn.zkaq.cn/md/5371/f380baf2c681c1bc7b1f453299943c3c_18490.png)
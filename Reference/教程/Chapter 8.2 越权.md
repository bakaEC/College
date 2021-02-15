# Chapter 8.2 越权

## 垂直越权漏洞复现

1. **注册账号，登录申报系统**

	![](https://nc0.cdn.zkaq.cn/md/5371/da80f56a12e198c21d6a56c47cf73265_20164.png)
2. **可见其他工单的详细信息不对此账号公开**

	![](https://nc0.cdn.zkaq.cn/md/5371/3cce9420ee67f08a6c7878a80a9852dc_85267.png)
3. **抓包分析，可见Cookie中存在两个疑似身份标识的key**

	![](https://nc0.cdn.zkaq.cn/md/5371/8ecf73799c1363f0739e1f35c72d35e7_48674.png)
4. **修改这两个key的参数，验证出当传参为1时后台认定为管理员，得到信息**

	![](https://nc0.cdn.zkaq.cn/md/5371/14ca9f52ee7d32f9d381c7acb22ea1e7_13515.png)
	![](https://nc0.cdn.zkaq.cn/md/5371/e088ff1a4f9ed8679aea99b36c7d4ff0_77378.png)
5. **尝试进入后台管理，在普通用户模式下被拒绝**

	![](https://nc0.cdn.zkaq.cn/md/5371/9ac64de4c76c9d220f02d7edd652aeff_32092.png)
6. **修改同一参数cookie（也可以使用插件减少工作量）**

	![](https://nc0.cdn.zkaq.cn/md/5371/57b12bf0b542727674c20a5720e6b0a7_43542.png)
	![](https://nc0.cdn.zkaq.cn/md/5371/aac3e48cb9f4b4ea281b91b6416fd989_36679.png)
7. **进入后台成功，在用户管理处获得cookie**

	![](https://nc0.cdn.zkaq.cn/md/5371/34d5bd9586fd4829e30ee72418b47f44_62939.png)
	![](https://nc0.cdn.zkaq.cn/md/5371/04691516ccb26488e57d9eb425b89bfd_60773.png)
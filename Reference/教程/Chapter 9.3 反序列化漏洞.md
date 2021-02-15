## Chapter 9.3 反序列化漏洞

## 0x00 漏洞复现

1. 点击check code审阅代码

	![](https://nc0.cdn.zkaq.cn/md/5371/5a5f0110b59264ea3e1d3f33d5bffd4f_27891.png)
	![](https://nc0.cdn.zkaq.cn/md/5371/70a0d3656336947a2231157c12abc911_74036.png)
2. 代码中使用了__toString()函数来返回Readme.txt里面的内容并且拼接source传参的内容，source传参又会传入当前文件的绝对地址

	![](https://nc0.cdn.zkaq.cn/md/5371/593fdcd7cfb811581dc0765e824b296c_77520.png)
3. cookie传参中有存在todos参数 定义了$c中存储todos的值 $h取$c的前32位内容 $m取$C的32位之后的所有内容 $h为md5加密过后的$m 所以综上所述得到的结论是：$c = md5($m).$m

	![](https://nc0.cdn.zkaq.cn/md/5371/22bf4d5c3d483f6ffc2eee51e8f50a52_38845.png)
4. 下面有个foreach循环遍历$todos 然后输出$todo 注意：<?=$todo?>就是<?php echo $todo;?> 是个简写 我们要找的输出位就是这里

	![](https://nc0.cdn.zkaq.cn/md/5371/34a8be763942bc94770787790d71781e_73002.png)
5. 需要构造$todos 因为flag在flag.php中 所以在本地写个readme类的内容 将$s的值转化为数组 然后得到序列化后的值
   a:1:{i:0;O:6:"readme":1:{s:6:"source";s:10:"./flag.php";}}
	![](https://nc0.cdn.zkaq.cn/md/5371/ad195c83b49015fc7dc32204afe3c8b4_48748.png)
	![](https://nc0.cdn.zkaq.cn/md/5371/f7df64d21f201e2b2830244c51c3c9b6_14498.png)
6. 然后对这串序列化值进行md5加密 得到 ```fae1710f5e51885bcf095e718ca752cc```
   拼接后：

   ```
   fae1710f5e51885bcf095e718ca752cca:1:{i:0;O:6:"readme":1:{s:6:"source";s:10:"./flag.php";}}
   ```

   url编码后：

   ```
   fae1710f5e51885bcf095e718ca752cca%3a1%3a%7bi%3a0%3bO%3a6%3a%22readme%22%3a1%3a%7bs%3a6%3a%22source%22%3bs%3a10%3a%22.%2fflag.php%22%3b%7d%7d
   ```

	![](https://nc0.cdn.zkaq.cn/md/5371/b865fa3497a9f4c2387bb8bc5f64e0e8_80564.png)
	![](https://nc0.cdn.zkaq.cn/md/5371/d553321b8393e61845fb62c33ebc6719_61273.png)
7. 抓包修改cookie，得到flag

	![](https://nc0.cdn.zkaq.cn/md/5371/ba20fba7c1a14c628cccc76646029e68_94535.png)
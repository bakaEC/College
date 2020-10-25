Dom Based XSS

通过JS对象

document.cookie获取cookie

document.domain 获取域名

document.lastModified 返回时间（通常用于判断是否为伪静态网页）

document.referrer 返回源链接

document.title 返回页面标题

document.getElementById() 通过ID返回元素



document.write("TEXT") 替代页面输出内容

- 会进行URL转码
- 通过escape()转码
- 通过unescape()解码

document.URL.indexOf("TEXT");  寻找某URL一内容所在的字符串点

document.URL.length 返回URL总长度

substring()截取字符串

eval()通过Js执行

.innerHTML改变元素内容 



document.write可以使用native编码格式





大部分通过浏览器前端获取信息，节省服务器资源

伪静态配置

DOM XSS际是将存储型XSS隐含在URL中，让被攻击者主动打开，从而获得Cookie。 
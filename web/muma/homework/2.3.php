<?php 
    $name = $_POST["username"];
    $password = $_POST["password"];
    $conn = mysqli_connect('127.0.0.1','root','root','test');   // 共用传参和链接数据库部分
   
    if(!empty($_POST['register'])) {        //点击注册按钮
        $sql = "INSERT INTO admin(username,password) VALUE ('$name','$password')";
        $result = mysqli_query($conn,$sql);
var_dump($result);
        if($result){
            echo '注册成功';
        }else{
            echo '注册失败';
        }
    } elseif(!empty($_POST['login'])) {     //点击登陆按钮
        $sql = "SELECT * FROM admin WHERE username ='$name'";
        $result = mysqli_query($conn,$sql);
        if(mysqli_fetch_array($result)['username'] == $name){
            echo "登录成功";
        }else{
            echo "登录失败!";
        }
        
    }
?>
package study;

public class Register {
    public boolean verify(String name, String pwd1, String pwd2) {
        boolean flag = false;
        if (name.length() < 3 || pwd1.length() < 6) {
            System.out.println("用户名长度不能小于3，密码长度不能小于6");

        } else if (!pwd1.equals(pwd2)) {
            System.out.println("两次密码不相同！");

        } else {
            System.out.println("注册成功！请牢记用户名与密码！");
            flag = true;
        }
        return flag;
    }
}
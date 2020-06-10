package study;

import java.util.Scanner;

public class Verify {
    public static void main(String[] args) {
        boolean fileCorrect = false;
        boolean emailCorrect = false;
        System.out.println("--欢迎进入作业提交系统--");
        Scanner input = new Scanner(System.in);
        System.out.println("请输入java的文件名");
        String fileName = input.next();
        System.out.println("请输入email");
        String email = input.next();

        // 验证文件名是否合法
        int index = fileName.lastIndexOf(".");
        if (index != -1 && index != 0 && fileName.substring(index + 1).equals("java")) {
            fileCorrect = true;
        } else {
            System.out.println("文件名无效");
        }

        // 验证邮箱是否合法
        if (email.indexOf("@") != -1 && email.indexOf(".") > email.indexOf("@")) {
            emailCorrect = true;
        } else {
            System.out.println("email无效！");

        }
        if (fileCorrect && emailCorrect) {
            System.out.println("作业提交成功");
        } else {
            System.out.println("作业提交失败");
        }

    }
}
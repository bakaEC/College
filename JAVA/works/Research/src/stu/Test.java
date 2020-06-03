package stu;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        Customers cb = new Customers();
        boolean con = true;
        Scanner input = new Scanner(System.in);
        while (con) {
            System.out.println("请输入姓名");
            String name = input.next();
            cb.addName(name);
            System.out.println("要继续吗?（y/n）");
            String user = input.next();
            if (user.equals("y"))
                con = true;
            else if (user.equals("n")) {
                con = false;
            }
        }
        cb.showNames();
        System.out.println("\n请输入要修改的客户姓名");
        String oldName = input.next();
        System.out.println("请输入新的用户名");
        String newName = input.next();
        System.out.println("*************修改结果************");
        if (cb.editName(oldName, newName) == true) {
            System.out.println("修改成功！");
            cb.showNames();
        }
        if (cb.editName(oldName, newName) == false) {
            System.out.println("修改失败！");
        }
        input.close();
    }
}

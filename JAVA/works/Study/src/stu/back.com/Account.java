package stu;

public class Account {
    private double money = 0;

    public double getMoney() {
        return money;
    }

    public void save(double s) {
        money = money + s;
        System.out.println("存款成功");
        System.out.println("当前余额为：" + getMoney());
    }

    public void deposit(double a) {
        if (money >= a) {
            money -= a;
            System.out.println("取款成功！");
            System.out.println("当前余额为：" + getMoney());
        } else {
            System.out.println("余额不足！");
            System.out.println("当前余额为：" + getMoney());
        }
    }

}
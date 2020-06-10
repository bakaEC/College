package study;

public class PrintScore {
    public static void main(String[] args) {
        int sql = 80;
        int java = 90;
        double html = 86.7;
        String score = "SQL" + sql + "\tjava:" + java + "\tHTML" + html;
        System.out.println("***********成绩单************");
        System.out.println(score);
    }
}
package Test;
import org.apache.log4j.*;
import java.util.*;



public class test {
    private  static  Logger log = Logger.getLogger(test.class.getName());
    public static void main(String[] args) {
        try {
            Scanner input = new Scanner(System.in);
            System.out.println("请输入被除数：");
            int num1 = input.nextInt();
            log.debug("输入被除数："+num1);
            System.out.println("请输入除数");
            int num2 = input.nextInt();
            log.debug("输入除数："+num2);
            System.out.println(num1+"/"+num2+"="+num1/num2);
            log.debug("输出运算结果："+num1/num2);
        }catch (Exception e){

        }
    }
}

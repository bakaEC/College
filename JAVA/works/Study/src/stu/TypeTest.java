package stu;

import java.util.Scanner;

public class TypeTest {
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		System.out.println("请输入STB");
		int STB=scan.nextInt();
		System.out.println("请输入JAVA");
		int JAVA=scan.nextInt();
		System.out.println("请输入SQL");
		int SQL=scan.nextInt();
		
		System.out.println("********************");
		System.out.println("STB\tJAVA\tSQL");
		System.out.println(STB+"\t"+JAVA+"\t"+SQL);
		System.out.println("********************");
		
		int diffen = JAVA-SQL;
		System.out.println("JAVA与SQL差："+diffen);
		double avg = (STB+JAVA+SQL)/3;
		System.out.println("平均分："+avg);
		
		
		
	}

}

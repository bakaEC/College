package stu;

import java.util.Scanner;

public class TypeTest {
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		System.out.println("������STB");
		int STB=scan.nextInt();
		System.out.println("������JAVA");
		int JAVA=scan.nextInt();
		System.out.println("������SQL");
		int SQL=scan.nextInt();
		
		System.out.println("********************");
		System.out.println("STB\tJAVA\tSQL");
		System.out.println(STB+"\t"+JAVA+"\t"+SQL);
		System.out.println("********************");
		
		int diffen = JAVA-SQL;
		System.out.println("JAVA��SQL�"+diffen);
		double avg = (STB+JAVA+SQL)/3;
		System.out.println("ƽ���֣�"+avg);
		
		
		
	}

}

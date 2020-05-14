package stu;
public class Work {

	public static void main(String[] args) {
		int sum=0;
		for(int num = 1;num <= 10;num++) {
			sum+=num;
				if(sum>20) {
					System.out.println(sum);
					break;
				}
		}
	}	
}

	

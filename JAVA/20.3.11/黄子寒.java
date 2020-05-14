package stu;


public class homework {

	public static void main(String[] args) {
		int tshirt=245;
		int shoes=570;
		int pat=320;
		float sum=tshirt*2+shoes+pat;
		System.out.println("※※※※※※※*消费单*※※※※※※※");
		System.out.println("购买物品\t单价\t个数\t金额");
		System.out.println("T恤\t￥"+tshirt+"\t2\t"+2*tshirt);
		System.out.println("网球鞋\t￥"+shoes+"\t1\t"+shoes);
		System.out.println("网球拍\t￥"+pat+"\t1\t"+pat);
		System.out.println("\n折扣：\t8折");
		System.out.println("消费总金额\t￥"+(sum*0.8));
		System.out.println("实际交费\t￥1500");
		System.out.println("找钱\t￥"+(1500-sum));
		 
	}

}

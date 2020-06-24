package com.cn.kd;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
public class DVDMgr {

	DVDSet dvd = new DVDSet();
	//初始化数据
	public void initial(){
		dvd.name[0]="罗马假日";
		dvd.state[0]=0;
		dvd.date[0]="2013-7-1";
		
		dvd.name[1] ="风声鹤唳";
		dvd.state[1]=1;
		
		dvd.name[2] ="浪漫满屋";
		dvd.state[2]=1;
		
		dvd.name[3] ="大话西游";
		dvd.state[3]=1;
	}
	//开始菜单
	public void startMenu(){
	   System.out.println("欢迎使用科大迷你DVD管理系统");
	   System.out.println("---------------------------");
	   System.out.println("1.新增DVD");
	   System.out.println("2.查看DVD");
	   System.out.println("3.删除DVD");
	   System.out.println("4.借出DVD");
	   System.out.println("5.归还DVD");
	   System.out.println("6.查看排行");
	   System.out.println("7.退       出");
	   System.out.println("---------------------------");
	   
	   System.out.println("请选择：");
	   Scanner input = new Scanner(System.in);
	   int choice=input.nextInt();
	   switch(choice){
	      case 1:
	    	  add();
	    	  System.out.println("--------------");
	    	  returnMain();
	    	  break;
	      case 2:
	    	  search();
	    	  System.out.println("--------------");
	    	  returnMain();
	    	  break;
	      case 3:
	    	  delete();
	    	  System.out.println("--------------");
	    	  returnMain();
	    	  break;
	      case 4:
	    	  lend();
	    	  System.out.println("--------------");
	    	  returnMain();
	    	  break; 
	      case 5:
	    	  returnDvd();
	    	  System.out.println("--------------");
	    	  returnMain();
	    	  break;
	      case 6:
	    	  list();
	    	  System.out.println("--------------");
	    	  returnMain();
	    	  break;    
	      case 7:
	    	  System.out.println("谢谢使用！");
	    	  break;
	   }
	}
	
	//返回主菜单
	public void returnMain(){
	   Scanner input=new Scanner(System.in);
	   System.out.println("输入0返回：");
	   if(input.nextInt()==0){
		   startMenu();
	   }else{
		   System.out.println("输入错误，异常终止！");
	   }
	}
	
	//查看DVD
	public void search(){
	  System.out.println("--------->查看DVD\n");
	  System.out.println("序号\t状态\t名称\t\t借出日期");
	  for (int i = 0; i < dvd.name.length; i++) {
		if(dvd.name[i]==null){
			break;
		}else if(dvd.state[i]==0){
			System.out.println((i+1)+"\t已借出\t"+"<<"+dvd.name[i]+
					">>\t"+dvd.date[i]);	
		}else if(dvd.state[i]==1){
			System.out.println((i+1)+"可借\t"+"<<"+dvd.name[i]+
					">>");
		}
	}
	 System.out.println("***********************************");
	 returnMain();
	}
	
	//新增DVD
	public void add(){
		Scanner input = new Scanner(System.in);
		System.out.println("----->新增DVD");
		System.out.println("请输入DVD名称：");
		String name=input.next();
		for (int i = 0; i < dvd.name.length; i++) {
			if(dvd.name[i]==null){ //查询最后一个空位置插入
				dvd.name[i]=name;
				dvd.state[i]=1;//置新增的DVD可借状态
				System.out.println("新增《"+name+"》成功！");
				break;
				
			}
		}
		System.out.println("***************************");
		returnMain();
	}
	
	//删除DVD
	public void delete(){
		Scanner input=new Scanner(System.in);
		boolean flag=false;  //标识删除成功与否
		System.out.println("----------->删除DVD\n");
		System.out.println("请输入DVD名称");
		String name = input.next();
		//遍历数组，查找相关信息
		for (int i = 0; i < dvd.name.length; i++) {
			if(dvd.name[i]!=null&&dvd.name[i].equalsIgnoreCase(name)
					&&dvd.state[i]==1){
				int j=i;
				while(dvd.name[j+1]!=null){
					dvd.name[j]=dvd.name[j+1];
					dvd.state[j]=dvd.state[j+1];
					dvd.date[j]=dvd.date[j+1];
					j++;
				}
				//最后一个不为空的元素置空
				dvd.name[j]=null;
				dvd.date[j]=null;
				System.out.println("删除《"+name+"》成功！");
				flag=true;//置位，表示删除成功
				break;
			}else if(dvd.name[i]!=null&&dvd.name[i].equalsIgnoreCase(name)
					&&dvd.state[i]==0){
				System.out.println("《"+name+"》为借出状态，不能删除！");
				flag=true;//置位
				break;
			}
		}
		if(!flag){
			System.out.println("没有找到要删除的信息！");
		}
		System.out.println("***************************");
		returnMain(); 
	}
	
	//借出DVD
	public void lend(){
		System.out.println("************借出DVD**********/n");
		Scanner input = new Scanner(System.in);
		System.out.println("请输入DVD的名称：");
		String want= input.next();
		for (int i = 0; i <dvd.name.length; i++) {
			if(dvd.name[i]==null){
				System.out.println("没有找到匹配信息！");
				break;
			}else if(dvd.name[i].equals(want)&&dvd.state[i]==1){
				dvd.state[i]=0;
				System.out.println("请输入借出日期（****年-**月-**日）");
				dvd.date[i]=input.next();
				System.out.println("借出《"+want+"》成功！");
				dvd.count[i]++;
				break;
			}else if(dvd.name[i].equals(want)&&dvd.state[i]==0){
				System.out.println("你要借《"+want+"》已被借出！请重新选择！");
				break;
			}
		}
		System.out.println("***************************");
		returnMain();
	}
	
	
	//计算日期差
	public long charge(String dstr1,String dstr2){
		long charge=0;
		SimpleDateFormat sd =new SimpleDateFormat("yyyy-MM-dd");
		try{
			Date d1=sd.parse(dstr1);
			Date d2=sd.parse(dstr2);
			charge=(d2.getTime()-d1.getTime())/(24*60*60*1000);
		}catch(ParseException e){
			e.printStackTrace();
		}
		return charge;
	}
	
	//归还DVD并计算租金
	public void returnDvd(){
		System.out.println("***************归还DVD***********\n");
		Scanner input=new Scanner(System.in);
		long loan=0;//租金
		System.out.println("请输入DVD名称：");
		String want=input.next();
		for (int i = 0; i < dvd.name.length; i++) {
			if(dvd.name[i]==null){
				System.out.println("没有找到匹配信息！");
				break;
			}else if(dvd.name[i].equals(want)&&dvd.state[i]==0){
				dvd.state[i]=1;
				System.out.println("请输入归还日期（****年-**月-**日）");
				String redate = input.next();
				//计算租金
				loan = charge(dvd.date[i],redate);
				System.out.println("\n归还《"+want+"》成功！");
				System.out.println("借出日期为："+dvd.date[i]);
				System.out.println("归还日期为："+redate);
				System.out.println("应付租金（元）："+loan);
				break;
			}else if(dvd.name[i].equals(want)&&dvd.state[i]==1){
				System.out.println("该DVD没有被借出！无法进行归还操作！");
				break;
			}
		}
		System.out.println("*****************************");
		returnMain();
	}
	
	//借出排行榜
	public void list(){
	   //定义新数组，用来存放排序后DVD信息
		String[] newname=new String[50];
		int[] newcount=new int[50];
		for(int k=0;k<dvd.name.length;k++){
			newname[k] = dvd.name[k];
			newcount[k] = dvd.count[k];
		}
	
		//利用冒泡排序算法进行排序
		for(int i=0;i<newname.length-1;i++){
			for(int j=0;j<newname.length-i-1;j++){
				if(newcount[j]>newcount[j+1]){
					int tempc=newcount[j];
					newcount[j]=newcount[j+1];
					newcount[j+1]=tempc;
					
					String tempn=newname[j];
					newname[j]=newname[j+1];
					newname[j+1]=tempn;
				}
			}
		}
		System.out.println("**********排行榜***********");
		System.out.println("次数\t名称");
		//显示排行榜信息
		for(int i =newname.length-1;i>=0;i--){
			if(newname[i]!=null){
				System.out.println(newcount[i]+"\t<<"+newname[i]+">>");
			}
		}
		System.out.println("*******************");
		returnMain();
	}
}

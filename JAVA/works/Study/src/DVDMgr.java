package com.cn.kd;
import java.util.*;
public class DVDMgr {

	DVDSet dvd = new DVDSet();
	//?????????
	public void initial(){
		dvd.name[0]="???????";
		dvd.state[0]=0;
		dvd.date[0]="2013-7-1";
		
		dvd.name[1] ="???????";
		dvd.state[1]=1;
		
		dvd.name[2] ="????????";
		dvd.state[2]=1;
		
		dvd.name[3] ="??????";
		dvd.state[3]=1;
	}
	//??????
	public void startMenu(){
	   System.out.println("????????????DVD??????");
	   System.out.println("---------------------------");
	   System.out.println("1.????DVD");
	   System.out.println("2.??DVD");
	   System.out.println("3.???DVD");
	   System.out.println("4.???DVD");
	   System.out.println("5.?íéDVD");
	   System.out.println("6.??       ??");
	   System.out.println("---------------------------");
	   
	   System.out.println("?????");
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
	    	  System.out.println("????????DVD");
	    	  System.out.println("--------------");
	    	  returnMain();
	    	  break; 
	      case 5:
	    	  System.out.println("??????íéDVD");
	    	  System.out.println("--------------");
	    	  returnMain();
	    	  break;
	      case 6:
	    	  System.out.println("§Ý§Ý????");
	    	  break;
	   }
	}
	
	//?????????
	public void returnMain(){
	   Scanner input=new Scanner(System.in);
	   System.out.println("????0?????");
	   if(input.nextInt()==0){
		   startMenu();
	   }else{
		   System.out.println("??????????????");
	   }
	}
	
	//??DVD
	public void search(){
	  System.out.println("--------->??DVD\n");
	  System.out.println("???\t??\t????\t\t???????");
	  for (int i = 0; i < dvd.name.length; i++) {
		if(dvd.name[i]==null){
			break;
		}else if(dvd.state[i]==0){
			System.out.println((i+1)+"\t????\t"+"<<"+dvd.name[i]+
					">>\t"+dvd.date[i]);	
		}else if(dvd.state[i]==1){
			System.out.println((i+1)+"???\t"+"<<"+dvd.name[i]+
					">>");
		}
	}
	 System.out.println("***********************************");
	 returnMain();
	}
	
	//????DVD
	public void add(){
		Scanner input = new Scanner(System.in);
		System.out.println("----->????DVD");
		System.out.println("??????DVD?????");
		String name=input.next();
		for (int i = 0; i < dvd.name.length; i++) {
			if(dvd.name[i]==null){ //???????????¦Ë?¨°???
				dvd.name[i]=name;
				dvd.state[i]=1;//????????DVD?????
				System.out.println("??????"+name+"???????");
				break;
				
			}
		}
		System.out.println("***************************");
		returnMain();
	}
	
	//???DVD
	public void delete(){
		Scanner input=new Scanner(System.in);
		boolean flag=false;  //????????????
		System.out.println("----------->???DVD\n");
		System.out.println("??????DVD????");
		String name = input.next();
		//???????ï…??????????
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
				//??????????????????
				dvd.name[j]=null;
				dvd.date[j]=null;
				System.out.println("?????"+name+"???????");
				flag=true;//??¦Ë???????????
				break;
			}else if(dvd.name[i]!=null&&dvd.name[i].equalsIgnoreCase(name)
					&&dvd.state[i]==0){
				System.out.println("??"+name+"???????????????????");
				flag=true;//??¦Ë
				break;
			}
		}
		if(!flag){
			System.out.println("?????????????????");
		}
		System.out.println("***************************");
		returnMain(); 
	}
	
	
}

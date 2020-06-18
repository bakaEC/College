package com.cn.kd;

import java.util.*;

import jdk.vm.ci.aarch64.AArch64.Flag;

public class DVDMgr {
	DVDSet dvd = new DVDSet();

	public void initial() {
		dvd.name[0] = "罗马假日";
		dvd.state[0] = 0;
		dvd.date[0] = "2013-7-1";

		dvd.name[1] = "风声鹤唳";
		dvd.state[1] = 1;

		dvd.name[2] = "浪漫满屋";
		dvd.state[2] = 1;

	}

	public void startMenu() {
		System.out.println("欢迎使用科大版miniDVD管理系统");
		System.out.println("---------------------");
		System.out.println("1.新增DVD");
		System.out.println("2.查看DVD");
		System.out.println("3.删除DVD");
		System.out.println("4.借出DVD");
		System.out.println("5.归还DVD");
		System.out.println("6.退         出 ");

		System.out.println("请选择: ");
		Scanner input = new Scanner(System.in);
		int choice = input.nextInt();
		switch (choice) {
			case 1:
				System.out.println("此处实现新增DVD");
				System.out.println("-----------");
				returnMain();
				break;
			case 2:
				System.out.println("此处实现查看DVD");
				System.out.println("-----------");
				returnMain();
				break;
			case 3:
				System.out.println("此处实现删除DVD");
				System.out.println("-----------");
				returnMain();
				break;
			case 4:
				System.out.println("此处实现借出DVD");
				System.out.println("-----------");
				returnMain();
				break;
			case 5:
				System.out.println("此处实现归还DVD");
				System.out.println("-----------");
				returnMain();
				break;
			case 6:
				System.out.println("谢谢使用!");
				System.out.println("-----------");
				returnMain();
				break;
		}
	}

	public void returnMain() {
		Scanner input = new Scanner(System.in);
		System.out.println("输入0返回: ");
		if (input.nextInt() == 0) {
			startMenu();
		} else {
			System.out.println("输入错误，异常终止!");
		}
	}

	public void search() {
		System.out.println("-------->查看DVD\n");
		System.out.println("序号\t状态\t名称\t\t借出日期");
		for (int i = 0; i < dvd.name.length; i++) {
			if (dvd.name[i] == null) {
				break;
			} else if (dvd.state[i] == 0) {
				System.out.println((i + 1) + "\t已借出\t《" + dvd.name + ">\t" + dvd.date[i]);
			} else if (dvd.state[i] == 1) {
				System.out.println((i + 1) + "\t可借\t《" + dvd.name + ">\t" + dvd.date[i]);
			}
		}
		System.out.println("****************************************");
		returnMain();
	}

	public void add() {
		Scanner input = new Scanner(System.in);
		System.out.println("------>新增DVD");
		System.out.println("请输入DVD名称");
		String name = input.next();
		for (int i = 0; i < dvd.name.length; i++) {
			if (dvd.name[i] == null) {
				dvd.name[i] = name;
				dvd.state[i] = 1;
				System.out.println("新增《" + name + "》成功！");
				break;
			}
		}
		System.out.println("*******************************************");
		returnMain();
	}

	public void delete() {
		Scanner input = new Scanner(System.in);
		boolean flag = false;
		System.out.println("---------->删除DVD\n");
		System.out.println("请输入DVD名称");
		String name = input.next();
		for (int i = 0; i < dvd.name.length; i++) {
			if (dvd.name[i] != null && dvd.name[i].equalsIgnoreCase(name) && dvd.state[i] == 1) {
				int j = i;
				while (dvd.name[j + 1] != null) {
					dvd.name[j] = dvd.name[j + 1];
					dvd.state[j] = dvd.state[j + 1];
					dvd.date[j] = dvd.date[j + 1];
					j++;
				}
				dvd.name[j] = null;
				dvd.date[j] = null;
				System.out.println("删除《" + name + "》成功！");
				flag = true;
				break;
			} else if (dvd.name != null && dvd.name[i].equalsIgnoreCase(name) && dvd.state[i] == 0) {
				System.out.println("《" + name + "》为借出状态，不能删除！");
				flag = true;
				break;
			}
		}
		if (!flag) {
			System.out.println("没找到要删除的信息！");
			System.out.println("****************************************************");
			returnMain();
		}
	}

}

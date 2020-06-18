package com.cn.kd;

import java.util.*;

import jdk.vm.ci.aarch64.AArch64.Flag;

public class DVDMgr {
	DVDSet dvd = new DVDSet();

	public void initial() {
		dvd.name[0] = "�������";
		dvd.state[0] = 0;
		dvd.date[0] = "2013-7-1";

		dvd.name[1] = "�������";
		dvd.state[1] = 1;

		dvd.name[2] = "��������";
		dvd.state[2] = 1;

	}

	public void startMenu() {
		System.out.println("��ӭʹ�ÿƴ��miniDVD����ϵͳ");
		System.out.println("---------------------");
		System.out.println("1.����DVD");
		System.out.println("2.�鿴DVD");
		System.out.println("3.ɾ��DVD");
		System.out.println("4.���DVD");
		System.out.println("5.�黹DVD");
		System.out.println("6.��         �� ");

		System.out.println("��ѡ��: ");
		Scanner input = new Scanner(System.in);
		int choice = input.nextInt();
		switch (choice) {
			case 1:
				System.out.println("�˴�ʵ������DVD");
				System.out.println("-----------");
				returnMain();
				break;
			case 2:
				System.out.println("�˴�ʵ�ֲ鿴DVD");
				System.out.println("-----------");
				returnMain();
				break;
			case 3:
				System.out.println("�˴�ʵ��ɾ��DVD");
				System.out.println("-----------");
				returnMain();
				break;
			case 4:
				System.out.println("�˴�ʵ�ֽ��DVD");
				System.out.println("-----------");
				returnMain();
				break;
			case 5:
				System.out.println("�˴�ʵ�ֹ黹DVD");
				System.out.println("-----------");
				returnMain();
				break;
			case 6:
				System.out.println("ллʹ��!");
				System.out.println("-----------");
				returnMain();
				break;
		}
	}

	public void returnMain() {
		Scanner input = new Scanner(System.in);
		System.out.println("����0����: ");
		if (input.nextInt() == 0) {
			startMenu();
		} else {
			System.out.println("��������쳣��ֹ!");
		}
	}

	public void search() {
		System.out.println("-------->�鿴DVD\n");
		System.out.println("���\t״̬\t����\t\t�������");
		for (int i = 0; i < dvd.name.length; i++) {
			if (dvd.name[i] == null) {
				break;
			} else if (dvd.state[i] == 0) {
				System.out.println((i + 1) + "\t�ѽ��\t��" + dvd.name + ">\t" + dvd.date[i]);
			} else if (dvd.state[i] == 1) {
				System.out.println((i + 1) + "\t�ɽ�\t��" + dvd.name + ">\t" + dvd.date[i]);
			}
		}
		System.out.println("****************************************");
		returnMain();
	}

	public void add() {
		Scanner input = new Scanner(System.in);
		System.out.println("------>����DVD");
		System.out.println("������DVD����");
		String name = input.next();
		for (int i = 0; i < dvd.name.length; i++) {
			if (dvd.name[i] == null) {
				dvd.name[i] = name;
				dvd.state[i] = 1;
				System.out.println("������" + name + "���ɹ���");
				break;
			}
		}
		System.out.println("*******************************************");
		returnMain();
	}

	public void delete() {
		Scanner input = new Scanner(System.in);
		boolean flag = false;
		System.out.println("---------->ɾ��DVD\n");
		System.out.println("������DVD����");
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
				System.out.println("ɾ����" + name + "���ɹ���");
				flag = true;
				break;
			} else if (dvd.name != null && dvd.name[i].equalsIgnoreCase(name) && dvd.state[i] == 0) {
				System.out.println("��" + name + "��Ϊ���״̬������ɾ����");
				flag = true;
				break;
			}
		}
		if (!flag) {
			System.out.println("û�ҵ�Ҫɾ������Ϣ��");
			System.out.println("****************************************************");
			returnMain();
		}
	}

}

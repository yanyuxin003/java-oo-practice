package com.controller;

import java.util.List;
import java.util.Scanner;

import com.pojo.Events;
import com.pojo.Role;
import com.twu.Main;

public class ManagerController {

	/*1.验证管理员账户密码*/
	public void managerRoot(List<Events> eventList) {
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入您的昵称：");
		String name = sc.nextLine();//管理员姓名
		System.out.println("请输入您的密码：");
		String password = sc.nextLine();//管理员密码
		//管理员账户没问题，继续操作，如果账户不对，退出
		boolean pass = checkAccount(name,password);
		if(pass) {
			//通过后，开始操作
			managerFunction(eventList,name);
		}else {
			System.out.println("抱歉，您登陆账户姓名或者密码错误，请重试：");
			Main.choose_function(eventList);
		}
	}

	/**
	 * 这样方便验证，用if判断麻烦
	 * @param name:管理员姓名
	 * @param password:管理员密码
	 * @return
	 */
	public boolean checkAccount(String name, String password) {
		if(Role.managerName.equals(name)&& Role.password.equals(password)) {
			return true;
		}
		return false;
	}

	//管理员操作
	private static void managerFunction(List<Events> eventList, String name) {
		System.out.println("你好，"+name+"，你可以：\n1.查看热搜排行榜\n2.添加热搜\n3.添加超级热搜\n4.退出");
		Scanner sc = new Scanner(System.in);
		int choose = sc.nextInt();
		switch(choose){
			case 1:
				//查看热搜排行榜
				check_hotSearch(eventList);
				//查看完了，还可以操作。
				managerFunction(eventList,name);
				break;
			case 2:
				//添加热搜，可以直接用User中的
				UserController.add_hotSearch(eventList,false);
				managerFunction(eventList,name);
				break;
			case 3:
				//添加超级热搜
				UserController.add_hotSearch(eventList,true);
				managerFunction(eventList,name);
				break;
			case 4:
				//退出
				Main.choose_function(eventList);
				break;
		}
	}


	/**
	 * 查看热搜事件排行榜
	 * @param eventList:热搜事件列表
	 */
	private static void check_hotSearch(List<Events> eventList) {
		if(eventList!=null&&eventList.size()>0) {
			//1.给热搜事件排名
			UserController.hotSearchSort(eventList);
			//2.输出热搜排行榜
			for(int i=0;i<eventList.size();i++) {
				System.out.println(String.format("%d   %s   %d",i+1,eventList.get(i).gethotSearchDescription(),eventList.get(i).getHeat()));
			}
		}else {
			System.out.println("抱歉，暂无热搜数据");
		}
	}


}

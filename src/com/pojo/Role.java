package com.pojo;

public class Role {
	String name;//名称
	int votes = 10;//票数
	public static String managerName = "admin"; 
	public static String password = "123456";

	//获取姓名、投票的get\set
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getVotes() {
		return votes;
	}
	public void setVotes(int votes) {
		this.votes = votes;
	}
	
	public Role(String name) {
		this.name=name;
	}
	
}

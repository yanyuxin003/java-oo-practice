package com.pojo;

public class Events {
	//热搜描述
	String hotSearchDescription ;
	//热度
	int heat=0;
	//排名
	int rank ;
	//
	int price=0;
	//是不是热搜
	boolean isSuperHot=false;
	
	
	public String gethotSearchDescription() {
		
		return hotSearchDescription;
	}
	public void sethotSearchDescription(String hotSearchDescription) {
		
		this.hotSearchDescription = hotSearchDescription;
	}
	public int getHeat() {
		
		return heat;
	}
	public void setHeat(int heat) {
		this.heat = heat;
	}
	public int getRank() {
		
		return rank;
	}
	public void setRank(int rank) {
		
		this.rank = rank;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public boolean isSuperHot() {
		return isSuperHot;
	}
	public void setSuperHot(boolean isSuperHot) {
		this.isSuperHot = isSuperHot;
	}
	

	
	
}

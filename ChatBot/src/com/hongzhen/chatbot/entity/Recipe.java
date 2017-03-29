package com.hongzhen.chatbot.entity;

public class Recipe {
	//菜谱标题
	private String name;
	//菜谱材料
	private String info;
	//网站图标，目前未使用该属性
	private String icon;
	//菜谱链接
	private String detailurl;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInfo() {
		return "需要的材料："+info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getDetailurl() {
		return detailurl;
	}
	public void setDetailurl(String detailurl) {
		this.detailurl = detailurl;
	}
	
	
	
}

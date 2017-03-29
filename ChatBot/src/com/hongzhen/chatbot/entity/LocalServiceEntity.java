package com.hongzhen.chatbot.entity;

import java.util.Vector;

public class LocalServiceEntity {
/*
 * 本地服务对象
 * 用于储存本地业务的关键字，URl，img等信息，为了提高反应速度，将会保存在application中
 * 未来若数据庞大，可以用户发送请求时再从数据库中检索
 */
	//int primarykey auto_increament
	private int id;
	//varchar2(100)
	private String keyword;
	//varchar2(300)
	private String url;
	//varchar2(300)
	private String img;
	//分词信息保存
	private Vector<String> splitedKeyword;
	
	private String XXX1;
	private String XXX2;
	private String XXX3;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public Vector<String> getSplitedKeyword() {
		return splitedKeyword;
	}
	public void setSplitedKeyword(Vector<String> splitedKeyword) {
		this.splitedKeyword = splitedKeyword;
	}
	public String getXXX1() {
		return XXX1;
	}
	public void setXXX1(String xXX1) {
		XXX1 = xXX1;
	}
	public String getXXX2() {
		return XXX2;
	}
	public void setXXX2(String xXX2) {
		XXX2 = xXX2;
	}
	public String getXXX3() {
		return XXX3;
	}
	public void setXXX3(String xXX3) {
		XXX3 = xXX3;
	}
	@Override
	public String toString() {
		return "LocalServiceEntity [id=" + id + ", keyword=" + keyword
				+ ", url=" + url + ", img=" + img + ", splitedKeyword="
				+ splitedKeyword + ", XXX1=" + XXX1 + ", XXX2=" + XXX2
				+ ", XXX3=" + XXX3 + "]";
	}
	
	
	
}

package com.hongzhen.chatbot.entity;

public class News {
	//新闻标题
	private String article;
	//新闻来源
	private String source;
	//网站图标，目前未使用该属性
	private String icon;
	//新闻详情链接
	private String detailurl;
	
	
	public String getArticle() {
		return article;
	}
	public void setArticle(String article) {
		this.article = article;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
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

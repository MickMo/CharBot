package com.hongzhen.chatbot.entity;

import java.util.ArrayList;
import java.util.UUID;
import java.util.Vector;

public class Massage {
	//用于储存分词后的信息
	private Vector<String> splitedSendMsg;
	//用户ID，用于上下文追踪
	private String userID = UUID.randomUUID().toString().replace("-", "");
	//需要发送的消息
	private String sendMsg;
	//返回的信息
	private String backMsg;
	//加密ID
	private String secret = "658cb2b410f9848a";
	//API ID
	private String apiKey = "8c96262f954644e99ada6fb7f42b12c8";
	
	//用于确认各搜索引擎返回数据是否有效。
	private int returnCode;
	public static final int RESULT_FOUND_CODE = 10000;
	public static final int NO_RESULT_CODE = 20000;
	
	//返回的Url，常见于查询列车信息
	private String url;

	//
	private ArrayList<News> newsList;
	
	/*
 	异常码（code） 
	 */
	public static final int RETURN_CODE_XWL = 302000;//302000 新闻类
	public static final int RETURN_CODE_CPL = 308000;//308000 菜谱类
	public static final int RETURN_CODE_LJL = 200000;//200000 链接类
	public static final int RETURN_CODE_WBL = 100000;//100000 文本类
	
	public static final int ERROR_CODE_WRONG_FORMAT = 40007;//40007 数据格式异常
	public static final int ERROR_CODE_NO_MORE_REQUEST = 40004;//40004 当天请求次数已使用完
	public static final int ERROR_CODE_EMPTY_INFO = 40002;//40002 请求内容 info 为空
	public static final int ERROR_CODE_WRONG_KEY = 40001;//40001 参数 key错误
	
	
	public String getUserID() {
		return userID;
	}
	public String getSendMsg() {
		return sendMsg;
	}
	public void setSendMsg(String sendMSg) {
		this.sendMsg = sendMSg;
	}
	public String getBackMsg() {
		return backMsg;
	}
	public void setBackMsg(String backMsg) {
		this.backMsg = backMsg;
	}
	public String getSecret() {
		return secret;
	}
	public String getApiKey() {
		return apiKey;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public ArrayList<News> getNewsList() {
		return newsList;
	}
	public void setNewsList(ArrayList<News> newsList) {
		this.newsList = newsList;
	}
	
	public void setSecret(String secret) {
		this.secret = secret;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	public int getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}
	
	
	public Vector<String> getSplitedSendMsg() {
		return splitedSendMsg;
	}
	public void setSplitedSendMsg(Vector<String> splitedSendMsg) {
		this.splitedSendMsg = splitedSendMsg;
	}
	@Override
	public String toString() {
		return "Massage [userID=" + userID + ", sendMsg=" + sendMsg
				+ ", backMsg=" + backMsg + ", secret=" + secret + ", apiKey="
				+ apiKey + ", returnCode=" + returnCode + ", url=" + url
				+ ", newsList=" + newsList + "]";
	}
	
	
	
	
}

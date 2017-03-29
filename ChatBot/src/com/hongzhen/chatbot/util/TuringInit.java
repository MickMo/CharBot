package com.hongzhen.chatbot.util;

import java.io.FileInputStream;
import java.util.Properties;

import com.hongzhen.chatbot.entity.Massage;

public class TuringInit {

	private static final TuringInit instanceBao = new TuringInit();
	public TuringInit() {
		
	}
	
	public static TuringInit getTuringInit(){
		return instanceBao;
	}
	
	
	
	public Massage init(Massage massage)throws RuntimeException {
    	//加载，secret，apiKey
    	Properties pro = new Properties();
    	FileInputStream in;
		try {
			
			String path = this.getClass().getResource("/").getPath().replace("\\","/")+"APIConfig.properties";
			in = new FileInputStream(path);
			pro.load(in);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("图灵配置文件未找到。");
		}
    	
    	
    	/*
    	 * secret：为加密码
    	 * apiKey:为图灵API ID
    	 * 均可从图灵官网获取
    	 */
    	massage.setSecret((String)pro.get("secret"));
    	massage.setApiKey((String)pro.get("apiKey"));
    	return massage;
	}
}

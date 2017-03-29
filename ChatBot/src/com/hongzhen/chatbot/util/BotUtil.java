package com.hongzhen.chatbot.util;

import java.util.List;

import com.hongzhen.chatbot.bao.localSearchEngine.LocalSearchEngineBAO;
import com.hongzhen.chatbot.bao.turing.TuringBAO;
import com.hongzhen.chatbot.bao.watson.WatsonBAO;
import com.hongzhen.chatbot.entity.LocalServiceEntity;
import com.hongzhen.chatbot.entity.Massage;

public class BotUtil extends Throwable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6426485879513028524L;
	
	private static BotUtil instance = new BotUtil();
	public BotUtil(){
	}
	
	public static BotUtil getInstance(){
		return instance;
	}
	
	public Massage send(List<LocalServiceEntity> localServiceData,Massage massage) throws RuntimeException {
		//获取本地搜索引擎实例
		LocalSearchEngineBAO localSearchEngineBAOInstance = LocalSearchEngineBAO.getLocalSearchEngineBAOInstance();
		//获取watson搜索引擎实例
		WatsonBAO watsonBAOInstance = WatsonBAO.getWatsonBAOInstance();
		//获取图灵搜索引擎实例
		TuringBAO turingBAOInstance = TuringBAO.getTuringBAOInstance();
		
		
		//本地临时变量，用于保存数据和判断搜索是否有结果
		Massage returnMassage;

		
		/*
		 * 开始查询业务
		 */
		//1使用本地检索引擎
		returnMassage =localSearchEngineBAOInstance.sendMsg(localServiceData,massage);
		
		//1.1本地检索引擎有结果
		if (returnMassage.getReturnCode()!=Massage.NO_RESULT_CODE) {
			//返回结果
			returnMassage.setBackMsg("本地："+returnMassage.getBackMsg());
			return returnMassage;
		}
		//1.2本地检索引擎无结果
		else {
			//2.使用watson检索引擎
			returnMassage = watsonBAOInstance.sendMsg(massage);
			
			//2.1watson检索引擎有结果
			if(returnMassage.getReturnCode()!=Massage.NO_RESULT_CODE){
				//返回结果
				returnMassage.setBackMsg("watson："+returnMassage.getBackMsg());
				return returnMassage;
			}
			//2.2watson检索引擎无结果
			else {
				/**
				 * 3.使用turing检索引擎
				 * 若不是连接问题，图灵引擎大部分情况下都会返回结果，所以放在最后。
				 */
				returnMassage = turingBAOInstance.sendMsg(massage);
				//3.1图灵检索引擎有结果
				if (returnMassage.getReturnCode()!=Massage.NO_RESULT_CODE) {
					//返回结果
					returnMassage.setBackMsg("图灵："+returnMassage.getBackMsg());
					return returnMassage;
				} 
				//3.2图灵检索引擎无结果，三个搜索引擎均无结果，未知错误
				else {
					massage.setBackMsg("啊噢！我我我找不到服务器了！你等会再来吧。。。");
					throw new RuntimeException("No Result Found");
				}
			}
		}
	}
}

package com.hongzhen.chatbot.bao.localSearchEngine;

import java.util.ArrayList;
import java.util.List;

import com.hongzhen.chatbot.bao.baseBAO.BaseBAO;
import com.hongzhen.chatbot.entity.LocalServiceEntity;
import com.hongzhen.chatbot.entity.Massage;
import com.hongzhen.chatbot.util.LightMDC;

public class LocalSearchEngineBAO  extends Throwable implements BaseBAO {

	private static final long serialVersionUID = -3101685740631145855L;
	
	
	private static final LocalSearchEngineBAO instanceBao = new LocalSearchEngineBAO();
	public LocalSearchEngineBAO() {
		
	}
	
	public static LocalSearchEngineBAO getLocalSearchEngineBAOInstance(){
		return instanceBao;
	}
	
	
	@Override
	public Massage sendMsg(List<LocalServiceEntity> localServiceData,Massage massage){
		//计算相似度
		ArrayList<Double> similarityList = new ArrayList<Double>();
		for (LocalServiceEntity localServiceEntity : localServiceData) {
			try {
				similarityList.add(
				(double) LightMDC.getSimilarity(localServiceEntity.getSplitedKeyword(), massage.getSplitedSendMsg())
				);
			} catch (Exception e) {
				e.printStackTrace();
				massage.setBackMsg(e.toString());
				return massage;
			}
		}
		/*
		 * 遍历相似度
		 * 若相识度中有超过50%的，返回相识度最高的结果，否则视为没有找到结果
		 */
		//记录相识度超过50%时，最大值在similarityList中的位置
		int maxSimilarityLocation = -1;
		//用以记录是否有两个候选词超过0.5的相识度，若有，说明用户输入了两个以上的关键字
		boolean flag = false;
		for (int i = 0; i < similarityList.size(); i++) {
			double temp = similarityList.get(i);
			
			//-----------------------------------------------------------
			//debug
			System.out.print(localServiceData.get(i).getKeyword()+":");
			System.out.println(temp+" ; ");
			//-----------------------------------------------------------
			
			if(temp>0.4){
				//未有最大值记录
				if(maxSimilarityLocation==-1){
					maxSimilarityLocation = i;
				}
				//若出现两个0.5以上的相似度，判定用户输入了两个关键字，本地搜索引擎无结果，使用下一个引擎搜索
				else {
					//maxSimilarityLocation = temp>similarityList.get(maxSimilarityLocation)?i:maxSimilarityLocation;
					flag = true;
					break;
				}
			}
		}
		
		/*
		 * 返回结果
		 * 若没有超过50%相识度的结果，设置无发回结果
		 * 若有，返回最大相识度对应的结果
		 */
		if(maxSimilarityLocation!=-1&&!flag){
			//获取最大相似度对应的本地服务对象
			LocalServiceEntity localServiceEntity = localServiceData.get(maxSimilarityLocation);
			//标识已找到结果，避免下一个引擎再次搜索
			massage.setReturnCode(Massage.RESULT_FOUND_CODE);
			
			//-----------------------------------------------------------
			//debug
			System.out.print("最大相似度为：");
			System.out.println(localServiceEntity.getKeyword());
			//-----------------------------------------------------------
			
			
			//将关键字传给massage，由massage传给前台
			massage.setBackMsg(localServiceEntity.getKeyword());
			//将对应的url传给massage，由massage传给前台
			massage.setUrl(localServiceEntity.getUrl());
			return massage;
		}
		//没有找到结果
		else{
			//标识没有找到结果
			massage.setReturnCode(Massage.NO_RESULT_CODE);
			return massage;
		}
	}

	@Override
	public Massage sendMsg(Massage massage) {
		// TODO Auto-generated method stub
		return null;
	}
}


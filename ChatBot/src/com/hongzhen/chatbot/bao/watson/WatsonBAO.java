package com.hongzhen.chatbot.bao.watson;

import java.util.List;

import com.hongzhen.chatbot.bao.baseBAO.BaseBAO;
import com.hongzhen.chatbot.entity.LocalServiceEntity;
import com.hongzhen.chatbot.entity.Massage;


public class WatsonBAO implements BaseBAO {
	private static final WatsonBAO instanceBao = new WatsonBAO();
	public WatsonBAO() {
		
	}
	
	public static WatsonBAO getWatsonBAOInstance(){
		return instanceBao;
	}
	
	
	@Override
	public Massage sendMsg(Massage massage) {

		massage.setReturnCode(Massage.NO_RESULT_CODE);
		return massage;
	}

	@Override
	public Massage sendMsg(List<LocalServiceEntity> localServiceData,
			Massage massage) {
		// TODO Auto-generated method stub
		return null;
	}
}

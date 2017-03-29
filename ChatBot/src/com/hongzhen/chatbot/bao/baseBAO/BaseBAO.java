package com.hongzhen.chatbot.bao.baseBAO;

import java.util.List;

import com.hongzhen.chatbot.entity.LocalServiceEntity;
import com.hongzhen.chatbot.entity.Massage;



public interface BaseBAO {

	public Massage sendMsg(Massage massage);

	Massage sendMsg(List<LocalServiceEntity> localServiceData, Massage massage);
}

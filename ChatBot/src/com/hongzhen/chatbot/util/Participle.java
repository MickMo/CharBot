package com.hongzhen.chatbot.util;

import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Vector;

import org.apache.lucene.analysis.tokenattributes.TermAttribute;
import org.wltea.analyzer.lucene.IKTokenizer;



/**
 * 分词处理
 * @author Michael Mo
 *
 */
public class Participle extends Throwable {

	private static final long serialVersionUID = 1738430550672085261L;
	private static IKTokenizer tokenizer;

	public static Vector<String> getParticiple(String str){
		
		
		Vector<String> vectorTemp = new Vector<String>();
		try {
			tokenizer = new IKTokenizer(new StringReader(str) , false);
			while(tokenizer.incrementToken()){
				TermAttribute termAtt = tokenizer.getAttribute(TermAttribute.class);
				vectorTemp.add(termAtt.term());
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("分词器错误！");
		}
		return vectorTemp;
		
	}
}

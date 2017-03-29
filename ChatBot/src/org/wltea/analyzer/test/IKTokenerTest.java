/**
 * 
 */
package org.wltea.analyzer.test;

import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.tokenattributes.TermAttribute;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKTokenizer;

import junit.framework.TestCase;

/**
 * @author 林良益
 *
 */
public class IKTokenerTest extends TestCase {
	
	@Test
	public void testLucene3Tokenizer(){
		String t = "我要去春游请假";
		IKTokenizer tokenizer = new IKTokenizer(new StringReader(t) , false);
		try {
			while(tokenizer.incrementToken()){
				TermAttribute termAtt = tokenizer.getAttribute(TermAttribute.class);
				System.out.println(termAtt.term());				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	

}

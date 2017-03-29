package com.hongzhen.chatbot.bao.turing;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hongzhen.chatbot.bao.baseBAO.BaseBAO;
import com.hongzhen.chatbot.entity.LocalServiceEntity;
import com.hongzhen.chatbot.entity.Massage;
import com.hongzhen.chatbot.entity.News;
import com.hongzhen.chatbot.entity.Recipe;



public class TuringBAO implements BaseBAO{

	private static final TuringBAO instanceBao = new TuringBAO();
	public TuringBAO() {
		
	}
	
	public static TuringBAO getTuringBAOInstance(){
		return instanceBao;
	}
	
	@Override
	public Massage sendMsg(Massage massage) {
		

			//图灵网站上的secret
			//String secret = "您的secret";
			//图灵网站上的apiKey
			//String apiKey = "您的apiKey";
			//String cmd = "你叫什么";
		
		 
			//待加密的json数据
			String data = "{\"key\":\""+massage.getApiKey()+"\",\"info\":\""+massage.getSendMsg()+"\",\"userid\":\""+massage.getUserID()+"\"}";
			//获取时间戳
			String timestamp = String.valueOf(System.currentTimeMillis());
			
			//生成密钥
			String keyParam = massage.getSecret()+timestamp+massage.getApiKey();
			String key = Md5.MD5(keyParam);
			
			//加密
			Aes mc = new Aes(key);
			data = mc.encrypt(data);		
			
			//封装请求参数
			JSONObject json = new JSONObject();
			json.put("key", massage.getApiKey());
			json.put("timestamp", timestamp);
			json.put("data", data);
			//请求图灵api
			String result = PostServer.SendPost(json.toString(), "http://www.tuling123.com/openapi/api");
			
			//返回结果例：{"code":100000,"text":"单身吧你，一看就是恍然大悟耶～"}
			
			//更改massage中的returnCode，避免MsgServlet中抛出异常
			massage.setReturnCode(Massage.RESULT_FOUND_CODE);
	
			//结果判断，非空进行下一步
			if(result!=null&&result!=""&&result!=" "){
				//解析JSON
				JSONObject obj = JSONObject.parseObject(result);
				
				
				//错误处理
				int code = Integer.parseInt((obj.get("code").toString()));
				System.out.println(code);
				if(code==(Massage.ERROR_CODE_NO_MORE_REQUEST)){
					//40004 当天请求次数已使用完
					massage.setBackMsg((String)obj.get("code")+":"+(String)obj.get("text")+"/n请我吃饭吧~");
					return massage;
				}else if (code==(Massage.ERROR_CODE_EMPTY_INFO)) {
					//40002 请求内容 info 为空
					massage.setBackMsg("啊噢！你想问我什么。。。我给忘了（请求INFO为空）");
					return massage;
				}else if (code==(Massage.ERROR_CODE_WRONG_FORMAT)) {
					//40007 数据格式异常
					massage.setBackMsg((String)obj.get("code")+":"+(String)obj.get("text"));
					return massage;
				}else if (code==(Massage.ERROR_CODE_WRONG_KEY)) {
					//40001 参数 key错误
					massage.setBackMsg((String)obj.get("code")+":"+(String)obj.get("text"));
					return massage;
				}
				
				
				//无错误消息
				else{
					if(code==(Massage.RETURN_CODE_XWL)){
						//302000 新闻类
						//获取新闻列表JSON
						JSONArray jsonArray = (JSONArray) obj.get("list");
						List<News> parseArray = new ArrayList<News>();
						for (Object object : jsonArray) {
							News parseObject = JSONObject.parseObject(object.toString(), News.class);
							parseArray.add(parseObject);
						}
						
						//新闻数据简单处理
						String newsTemp = (String)obj.get("text")+":\n";
						for (News news : parseArray) {
							newsTemp += "<a href=\""+news.getDetailurl()+"\">"+news.getArticle()+"</a>";
							newsTemp += "\n";
						}
						massage.setBackMsg(newsTemp);
						return massage;
					}
					else if(code==(Massage.RETURN_CODE_CPL)){
						//308000 菜谱类
						//获取菜谱列表JSON
						
						JSONArray jsonArray = (JSONArray) obj.get("list");
						List<Recipe> parseArray = new ArrayList<Recipe>();
						for (Object object : jsonArray) {
							Recipe parseObject = JSONObject.parseObject(object.toString(), Recipe.class);
							parseArray.add(parseObject);
						}
						
						
						//菜谱数据简单处理
						String recipeTemp = (String)obj.get("text")+":\n";
						for (Recipe recipe : parseArray) {
							recipeTemp += "<a href=\""+recipe.getDetailurl()+"\">"+recipe.getName()+"</a>";
							recipeTemp += "\n";
						}
						massage.setBackMsg(recipeTemp);
						return massage;
					}
					else if(code==(Massage.RETURN_CODE_LJL)){
						//200000 链接类
						System.out.println((String)obj.get("url"));
						massage.setBackMsg("<a href=\""+(String)obj.get("url")+"\">"+(String)obj.get("text")+"</a>");
						return massage;
					}
					else if(code==(Massage.RETURN_CODE_WBL)){
						//100000 文本类
						massage.setBackMsg((String)obj.get("text"));
						return massage;
					}
					
				}
				//
				//获取返回消息
				massage.setBackMsg((String)obj.get("text"));
			}
			//结果空，未知异常
			else{
				massage.setReturnCode(Massage.NO_RESULT_CODE);
			}
			
			
			

		return massage;
	}

	@Override
	public Massage sendMsg(List<LocalServiceEntity> localServiceData,
			Massage massage) {
		// TODO Auto-generated method stub
		return null;
	}

}

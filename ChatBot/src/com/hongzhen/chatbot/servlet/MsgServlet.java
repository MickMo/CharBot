package com.hongzhen.chatbot.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;










import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.hongzhen.chatbot.entity.LocalServiceEntity;
import com.hongzhen.chatbot.entity.Massage;
import com.hongzhen.chatbot.util.BotUtil;
import com.hongzhen.chatbot.util.JdbcUtil;
import com.hongzhen.chatbot.util.Participle;
import com.hongzhen.chatbot.util.TuringInit;

public class MsgServlet extends HttpServlet {

	private static final long serialVersionUID = -6218391821892568266L;
	
	    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    		
	    		request.setCharacterEncoding("UTF-8");
	    	
	    		BotUtil botUtil = BotUtil.getInstance();
		        HttpSession session = request.getSession(true);
		        ServletContext application=this.getServletContext(); 
		        
				//检查提交数据

		        //判断Session中消息对象是否存在，不存在则新建消息对象
		        Massage massage = (Massage) session.getAttribute("Massage");
		        if(massage==null){
		        	//初始化massage,将会自动初始化一个32位的UUID
		        	massage = new Massage();
		        	
		        	//初始化turing相关配置
		        	massage = TuringInit.getTuringInit().init(massage);
		        	//保存用户至Session
		        	request.getSession().setAttribute("Massage", massage);
		        }
		        //清除数据
		        massage.setBackMsg("");
		        massage.setSendMsg("");
		        massage.setNewsList(null);
		        massage.setReturnCode(Massage.NO_RESULT_CODE);
		        massage.setUrl(null);
		        
		        //获取发送信息并去除空格
		        massage.setSendMsg(request.getParameter("sendMsg").trim());
		        String sendMsgCheck = massage.getSendMsg();
		        
		        //如果请求为“？”，返回当前本地服务列表
		        if(sendMsgCheck.equals("help")){
		        	String localService = (String) application.getAttribute("localService");
		        	massage.setBackMsg("目前本地服务有：\n"+localService);
		        }
		      
				//msg非空检查
		        //非空，执行查询请求
		        else if(sendMsgCheck!=null&&sendMsgCheck!=""&&sendMsgCheck.length()!=0&&"?"!=sendMsgCheck&&"？"!=sendMsgCheck){
			        try {
			        	System.out.println(massage);
			        	
			        	//进去引擎查询查询
						
						@SuppressWarnings("unchecked")
						List<LocalServiceEntity> localServiceData = (List<LocalServiceEntity>) application.getAttribute("localServiceData");
						
						//语义分析预处理
						massage.setSplitedSendMsg(Participle.getParticiple(massage.getSendMsg()));
						//执行搜索
			        	massage = botUtil.send(localServiceData,massage);
			        	
					} catch (Exception e) {
						//感知搜索引擎内部异常
						massage.setBackMsg(e.toString());
						e.printStackTrace();
					}
				}
				
				//空请求，不予执行查询
				else{
					massage.setBackMsg("你给我发一堆空白我也不知道怎么回复你。。我又不是你女朋友。。");
				}
				
		        try {
		        	 //异步返回
			        response.setContentType("text/plain"); 
			        response.setCharacterEncoding("UTF-8"); 
			        // Write response body.
			        response.getWriter().write(massage.getBackMsg());
			        if(massage.getUrl()!=null&&massage.getUrl()!=""){
			        	response.getWriter().write("\n网址如下："+massage.getUrl());
			        }
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					//关闭资源
					response.getWriter().flush();
			        response.getWriter().close();
				}
		    }

			
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			login(request,response);
		}

		
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			this.doGet(request,response);
		}
		
		@Override
		public void init() throws ServletException {
			
			//查询数据库，获取当前业务关键字列表
			QueryRunner queryRuner = JdbcUtil.getQueryRuner();
			String sql = "select * from t_service";
			try {
				List<LocalServiceEntity> localServiceData = queryRuner.query(sql, new BeanListHandler<LocalServiceEntity>(LocalServiceEntity.class));
				//语义预处理，分词
				for (LocalServiceEntity localServiceEntity : localServiceData) {
					System.out.println(localServiceEntity.getKeyword());
					localServiceEntity.setSplitedKeyword(Participle.getParticiple(localServiceEntity.getKeyword()));
				}
				
				
				//获取application 
				ServletContext application=this.getServletContext();   
		        //将localServiceData放入Application
		        application.setAttribute("localServiceData", localServiceData);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}

package com.hongzhen.chatbot.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.hongzhen.chatbot.entity.LocalServiceEntity;

public class ChatbotInitServlet extends HttpServlet {

		private static final long serialVersionUID = -6218391821892568266L;
				
		private void initPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
				request.setCharacterEncoding("UTF-8");
			
				//获取application 
				ServletContext application=this.getServletContext();   
		        //获取本地服务列表
				@SuppressWarnings("unchecked")
				List<LocalServiceEntity> localServiceData = (List<LocalServiceEntity>) application.getAttribute("localServiceData");
		        
		        StringBuffer stringBuffer = new StringBuffer();
		        
				if(localServiceData==null){
					stringBuffer.append("无本地服务或数据库无法连接！");
				}else {
					for (LocalServiceEntity localServiceEntity : localServiceData) {
						stringBuffer.append(localServiceEntity.getKeyword()+" , ");
						application.setAttribute("localService", stringBuffer.toString());
					}
				}
		        
				
		        try {
		        	 //异步返回
			        response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
			        response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
			        response.getWriter().write(stringBuffer.toString());       // Write response body.
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					//关闭资源
					response.getWriter().flush();
			        response.getWriter().close();
				}
		    }
		
			
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			initPage(request,response);
		}


		
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request,response);
		}
}

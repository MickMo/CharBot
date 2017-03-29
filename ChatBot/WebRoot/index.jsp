<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <script type="text/javascript">
  	i = 0;
  
 	function sendMsg(){
 		
 		var msgText = document.getElementById("sendMsg").value;
 		var backMsg = document.getElementById("backMsg");
 				//每10次需要刷新页面
 				if(i>9){
 					alert("请刷新页面后再尝试。");
 				}else if(msgText!=""){
 		 			
					//设置Ajax连接基本信息
	 		 		var url = "<%=basePath%>"+"sendingMsg";
				    var data = "sendMsg="+msgText;
				    var method = "post";
				    
				    var ajax = createAJAX();
				    //打开连接
				    ajax.open(method,url);
					//设置AJAX请求头
					ajax.setRequestHeader("content-type","application/x-www-form-urlencoded");
					//传送数据
					ajax.send(data);
				    
				    ajax.onreadystatechange = function(){
					if(ajax.readyState == 4){
						if(ajax.status == 200){
							//NO5)从AJAX异步对象中获取服务器响应的xml文档
							var temp = backMsg.value;
							backMsg.value="You:"+msgText+"\n"+ajax.responseText+"\n"+"\n"+temp+"\n";
							document.getElementById("sendMsg").value="";
							i++;
							}
						}
					} 
				}else{
					backMsg.value="随便问问我什么吧。";
				}
 	}
 	
 	
 	function createAJAX(){
		var ajax = null;
		try{
			ajax = new ActiveXObject("microsoft.xmlhttp");
		}catch(e1){
			try{
				ajax = new XMLHttpRequest();
			}catch(e2){
				alert("你的浏览器不支持ajax，请更换浏览器");
			}
		}
		return ajax;
	}
 	
 	
  </script>
  
  <script type="text/javascript">
  	//页面加载完后，获取本地可用服务
  	window.onload = function(){
  		
  		var backMsg = document.getElementById("backMsg");
  		//设置Ajax连接基本信息
		var url = "<%=basePath%>"+"welcomeBot";
		var method = "post";
		   
		var ajax = createAJAX();
		//打开连接
		ajax.open(method,url);
		//设置AJAX请求头
		ajax.setRequestHeader("content-type","application/x-www-form-urlencoded");
		//传送数据
		ajax.send("1");
		   
		ajax.onreadystatechange = function(){
			if(ajax.status == 200){
				//NO5)从AJAX异步对象中获取服务器响应的xml文档
				backMsg.value="Welcome!输入\'help\'可以查看当前的服务列表"+"\n"+"我现在提供一下本地服务:"+"\n"+ajax.responseText+"\n";
			}else{
				alert("服务器未返回有效数据");
			}
		}
  	}
  </script>
  
  <body>
  

    <table align="center" style="width: 550px; ">
    	<tr><td align="center"><h3>Massage send to Bot</h3></td></tr>
		<tr><td align="center"><input id="sendMsg" type="text" style="width: 425px;  height: 60px"></td></tr>
		<tr>
			<td align="center">
				<button onclick="sendMsg()">Send</button>
			</td>
		</tr>
		<tr>
			<td align="center">
				<textarea  id="backMsg" style="width: 425px; height: 200px" disabled></textarea>
			</td>
    	</tr>
    </table>
	
  </body>
</html>

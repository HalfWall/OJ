<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
<meta charset="utf-8">
<title>登陆失败</title>
</head>
<style>
*{
	margin:0 auto;
	padding:0;
}
body{
	background-color:#CCC;
}
.aaa{
	width:286px;
	height:286px;
}
.tu{
	margin:0 auto;
	margin-top:120px;
	position:absolute;
	width:266px;
	height:266px;
	border-width:10px;
	border-style:solid;
	border-bottom-color:#FCF;
	border-right-color:#fcf;
	border-top-color:#FFF;
	border-left-color:#fff;
	border-radius:143px;
	overflow:hidden;
	transition:ease 0.5s;
}
img{
	position:relative;
	top:10px;
	left:10px;
	margin-top:120px;
	border-radius:133px;
	transition:ease 0.3s;
}
.zi{
	width:150px;
	height:100px;
	border-radius:133px;
	position:relative;
	left:20px;
	top:-5px;
	font-size:30px;
	visibility:hidden;
	color:#FCC;
	transition:ease 0.4s;
}
.aaa:hover .tu{
	transform:rotate(360deg);
}
img:hover{
	opacity:0.3;
}
.aaa:hover .zi{
	visibility:visible;
	color:#F6F;
}
</style>

<body>
<div class="aaa">
 <div class="tu"></div>
 <img src="assets/img/sucai.jpg" width="266" height="266">
 <div class="zi"> 登陆失败
  		  <br>
  		  ${tip2 }</div>
 <div class="zi">&nbsp;<a href="index.jsp">确定</a></div> 
</div>
</body>
</html>

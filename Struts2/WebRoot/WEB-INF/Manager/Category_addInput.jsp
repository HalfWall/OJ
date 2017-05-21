<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加输入页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	<center>
    <form action="Manager/Category_add.action" method="post">
    	题号:<input type="text" name="category.id" >
    	<br>
    	题目:<input type="text" name="category.name">
    	<br>
    	内容:<textarea name="category.context" rows="10"></textarea>
    	<br>
    	输入:<input type="text" name="category.testIn">
    	<br>
    	输出:<input type="text" name="category.testOut">
    	<br>
    	<input type="submit" value="提交">
    </form>
    <a href="Manager/Category_list.action">返回</a>	
    </center>
  </body>
</html>

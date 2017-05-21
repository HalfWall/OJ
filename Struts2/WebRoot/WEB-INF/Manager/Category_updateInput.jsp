<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>更新输入页面</title>
    
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
    <form action="Manager/Category_update" method="post">
    	<input type="hidden" name="category.id" value="<s:property value="category.id"/>">
    	题目<input type="text" name="category.name" value="<s:property value="category.name"/>">
    	<br>
    	内容<br>
    	<textarea name="category.context" ><s:property value="category.context"/></textarea>
    	<br>
    	输入<input type="text" name="category.testIn" value="<s:property value="category.testIn"/>">
    	<br>
    	输出<input type="text" name="category.testOut" value="<s:property value="category.testOut"/>">
    	<br>
    	<input type="submit" value="提交">
    </form>
    <a href="Manager/Category_list.action">返回</a>	
    </center>
  </body>
</html>

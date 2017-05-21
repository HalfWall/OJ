<%@ page language="java" import="java.util.*"  contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>提交界面</title>
    
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
  <table width="100%">
		<tr>
			<th style="color:#1A5CC8 ">
				<center>
					题目<input type="text" name="category.name"
						value="<s:property value="category.name"/>" readonly> 
					<br>
					<br>
					 内容 
					<br>
					<textarea name="category.context" readonly style="width: 667px; height: 373px">
						<s:property value="category.context" />
					</textarea>
					<br>
					输入<input type="text" name="category.testIn"
						value="<s:property value="category.testIn"/>" readonly> 
					<br>
					输出<input type="text" name="category.testOut"
						value="<s:property value="category.testOut"/>" readonly> 
					<br>
					<br>
					<a href="User/Category_list.action">返回</a>
					<hr />
				</center>
			</th>
		</tr>

		<TR>
			<TD>
				<h1 align="center">Submit Your Solution</h1>
		</TR>
		
		<TR>
			<TD align="center"><br> 
			Language&nbsp; 
			<span
				style="border:#B7CBFF 1px dashed;width:120px;height:22px;padding:0">
				<select name="language" style="width:121px;font-size:14px;font-family:Arial;background-color:F4FBFF;margin:-1px">
						<option value="0" selected>C++</option>
				</select> 
			</span>
		</TR>
		
		<TR>
			<TD align=center><br>Source Code</TD>
		</TR>
		
		<TR>
			<TD align=center><br>
			<form action="User/User_judgeCode"  method="post">
			 	<textarea name="user.code"  style="width: 667px; height: 373px"></textarea>
			 	<br>
			 	<br>
			 	<input type="submit" value="提交" >
			</form>
			</TD>
		</TR>
		
	</table>
    
	
  </body>
</html>

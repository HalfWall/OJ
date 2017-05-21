package com.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.*;

//普通用户拦截器
public class AuthInterceptor extends AbstractInterceptor{

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		System.out.println("拦截器开始执行");
		Object obj= ActionContext.getContext().getSession().get("userid");
		String strName=obj!=null?obj.toString():"";
		if(strName.equals("admin")){
			String str=arg0.invoke();
			System.out.println("拦截器结束执行");
			return str;
		}else{
			ActionContext.getContext().getSession().put("tip3","没有访问权限");
			return "error";
		}
		
	}

}

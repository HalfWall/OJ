package com.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.*;

//游客拦截器
public class AuthInterceptor2 extends AbstractInterceptor{

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		System.out.println("拦截器2开始执行");
		Object obj= ActionContext.getContext().getSession().get("userid");
		//String strName=obj==null?obj.toString():"";
		if(obj==null){//strName.equals("")
			ActionContext.getContext().getSession().put("tip3","没有访问权限");
			return "error";
		}else{
			String str=arg0.invoke();
			System.out.println("拦截器2结束执行");
			return str;
			
		}
		
	}

}

package com.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.*;

//�ο�������
public class AuthInterceptor2 extends AbstractInterceptor{

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		System.out.println("������2��ʼִ��");
		Object obj= ActionContext.getContext().getSession().get("userid");
		//String strName=obj==null?obj.toString():"";
		if(obj==null){//strName.equals("")
			ActionContext.getContext().getSession().put("tip3","û�з���Ȩ��");
			return "error";
		}else{
			String str=arg0.invoke();
			System.out.println("������2����ִ��");
			return str;
			
		}
		
	}

}

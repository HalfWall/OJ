package com.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.*;

//��ͨ�û�������
public class AuthInterceptor extends AbstractInterceptor{

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		System.out.println("��������ʼִ��");
		Object obj= ActionContext.getContext().getSession().get("userid");
		String strName=obj!=null?obj.toString():"";
		if(strName.equals("admin")){
			String str=arg0.invoke();
			System.out.println("����������ִ��");
			return str;
		}else{
			ActionContext.getContext().getSession().put("tip3","û�з���Ȩ��");
			return "error";
		}
		
	}

}

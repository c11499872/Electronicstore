package com.electronics.struts2;

import java.util.Map;


import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class LoginInterceptor implements Interceptor {

	private static final long serialVersionUID = 2732929286586294399L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Map<String, Object> session = invocation.getInvocationContext().getSession();
		Customer customer =  (Customer) session.get("customer");
		if(customer == null){
			return Action.LOGIN;
		}
		else{
			return invocation.invoke();
		}
	}
	
	@Override
	public void destroy() {
		
	}

	@Override
	public void init() {
		
	}
}
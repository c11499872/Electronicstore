package com.electronics.struts2;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

public class SessionFactory {
	
	private static Map<String, Object> session;
	
	//Private constructor to prevent class being instantiated
	private SessionFactory(){
		
	}
	
	//Return session
	public static Map<String, Object> getSessionInstance(){
		session = ActionContext.getContext().getSession();
		return session;
	}
}
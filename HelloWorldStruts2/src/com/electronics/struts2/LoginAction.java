package com.electronics.struts2;

import java.util.ArrayList;
import java.util.Map;

public class LoginAction{

   private String user;
   private String password;
   private String name;
   UserOperations uo = new UserOperations();
   ItemOperations io = new ItemOperations();
   private Map<String, Object> session = SessionFactory.getSessionInstance();
   public ArrayList<Item> itemList=new ArrayList<Item>(); 

   

public String execute() {
    
	
	   	session.put("context", user);
	   	if(uo.validLogin(user, password)){
	   	
	   	itemList = io.getAllFromItem();
            
	   	return "success";
	   	}
	   	else
	   		return "error";
   }

   public String getUser() {
      return user;
   }

   public void setUser(String user) {
      this.user = user;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }
   
   public ArrayList<Item> getItemList() {
		return itemList;
	}

	public void setItemList(ArrayList<Item> itemList) {
		this.itemList = itemList;
	}
   
}
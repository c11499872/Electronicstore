package com.electronics.struts2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {

   private String user;
   private String password;
   private String name;
   ItemOperations io = new ItemOperations();
   
   public ArrayList<Item> itemList=new ArrayList<Item>(); 

   

public String execute() {
    
	   	Map session = ActionContext.getContext().getSession();
	   	session.put("context", user);
	   	
	   	itemList = io.getAllFromItem();
            
	   	return SUCCESS;
        
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
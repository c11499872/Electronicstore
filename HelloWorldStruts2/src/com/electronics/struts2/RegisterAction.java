package com.electronics.struts2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

public class RegisterAction{

   private String user;
   private String password;
   private String name;

   private Map<String, Object> session = SessionFactory.getSessionInstance();
   
   ItemOperations io = new ItemOperations();
   
   public ArrayList<Item> itemList=new ArrayList<Item>(); 

   
   public String execute() {
	  if(user.equals("") || password.equals("") || name.equals(""))
	  {
		
     	 session.put("error", "Fields cannot be left blank");
     	 return "ERROR";
	  }
	  else{
      Connection conn = null;
      itemList = io.getAllFromItem();
      try {
         String URL = "jdbc:mysql://localhost/struts_tutorial";
         Class.forName("com.mysql.jdbc.Driver");
         conn = DriverManager.getConnection(URL, "root", "root");
         
         String sql1 = "SELECT name FROM customer WHERE user_name = ? AND password = ?";
         PreparedStatement ps = conn.prepareStatement(sql1);
         ps.setString(1, user);
         ps.setString(2, password);
         ResultSet rs = ps.executeQuery();
       
         if(!rs.next())
         {
        	
        	 session.put("error", "name already taken");
        	 return "ERROR";
        	 
         }
         else{
         String sql2 = "Insert into customer (user_name, name, password) values (?,?,?)";
         
         PreparedStatement ps2 = conn.prepareStatement(sql2);
         ps2.setString(1, name);
         ps2.setString(2, user);
         ps2.setString(3, password);
         ps2.executeUpdate();
        
         return "SUCCESS";
         }      
      } catch (Exception e) {
    	  
     	 session.put("error", "name already taken");
     	 
         return "ERROR";
      } finally {
         if (conn != null) {
            try {
               conn.close();
            } catch (Exception e) {
            }
         }
      }
	  }
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
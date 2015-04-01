package com.electronics.struts2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {

   private String user;
   private String password;
   private String name;
   
   ArrayList<Item> list=new ArrayList<Item>(); 

   public String execute() {
      String ret = ERROR;
      Connection conn = null;

      try {
         String URL = "jdbc:mysql://localhost/struts_tutorial";
         Class.forName("com.mysql.jdbc.Driver");
         conn = DriverManager.getConnection(URL, "root", "root");
         String sql = "SELECT name FROM customer WHERE";
         sql+=" user_name = ? AND password = ?";
         PreparedStatement ps = conn.prepareStatement(sql);
         ps.setString(1, user);
         ps.setString(2, password);
         ResultSet rs = ps.executeQuery();

         while (rs.next()) {
            name = rs.getString(1);
            
            try{  
            	 
            	              
            	  PreparedStatement ps2=conn.prepareStatement("select * from item");  
            	  ResultSet rs2=ps2.executeQuery();  
            	  
            	  while(rs2.next()){  
            	   Item item=new Item();  
            	   item.setTitle(rs2.getString(2));  
            	   item.setManufacturer(rs2.getString(3));  
            	   item.setPrice(rs2.getString(4));  
            	   item.setCategory(rs2.getString(5));  
            	   list.add(item);  
            	  }  
            	  
            	  //conn.close();  
            	 }catch(Exception e){
            		 e.printStackTrace();
            		 }  
            	          
            
            ret = SUCCESS;
         }
         if(ret == ERROR)
         {
        	 password = "username and password do not match";
         }
         
         
         
         
      } catch (Exception e) {
    	  
         ret = ERROR;
        
      } finally {
         if (conn != null) {
            try {
               conn.close();
            } catch (Exception e) {
            }
         }
      }
      return ret;
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
   
   public ArrayList<Item> getList() {  
	    return list;  
	}  
	public void setList(ArrayList<Item> list) {  
	    this.list = list;  
	}  
}
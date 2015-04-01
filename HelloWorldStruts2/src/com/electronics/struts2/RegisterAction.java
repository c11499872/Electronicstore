package com.electronics.struts2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.opensymphony.xwork2.ActionSupport;

public class RegisterAction extends ActionSupport {

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
         
         String sql1 = "SELECT name FROM customer WHERE";
         sql1+=" user_name = ? AND password = ?";
         PreparedStatement ps = conn.prepareStatement(sql1);
         ps.setString(1, user);
         ps.setString(2, password);
         ResultSet rs = ps.executeQuery();

         if(rs.first())
         {
        	 ret = ERROR;
        	 password = "name already taken";
         }
         else{
         String sql2 = "Insert into customer (name, user_name, password) values (?,?,?)";
         
         PreparedStatement ps2 = conn.prepareStatement(sql2);
         ps2.setString(1, name);
         ps2.setString(2, user);
         ps2.setString(3, password);
         ps2.executeUpdate();
         
         try{  
        	 
             
       	  PreparedStatement ps3=conn.prepareStatement("select * from item");  
       	  ResultSet rs3=ps3.executeQuery();  
       	  
       	  while(rs3.next()){  
       	   Item item=new Item();  
       	   item.setTitle(rs3.getString(2));  
       	   item.setManufacturer(rs3.getString(3));  
       	   item.setPrice(rs3.getString(4));  
       	   item.setCategory(rs3.getString(5));  
       	   list.add(item);  
       	  }  
       	  
       	  //conn.close();  
       	 }catch(Exception e){
       		 e.printStackTrace();
       		 }

         ret = SUCCESS;
         }
        
           
        
      } catch (Exception e) {
    	System.out.println(e);
    	System.out.println(name);
    	password = e.getMessage();
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
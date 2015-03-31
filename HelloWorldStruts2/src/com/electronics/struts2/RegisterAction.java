package com.electronics.struts2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.opensymphony.xwork2.ActionSupport;

public class RegisterAction extends ActionSupport {

   private String user;
   private String password;
   private String name;

   public String execute() {
      String ret = ERROR;
      Connection conn = null;

      try {
         String URL = "jdbc:mysql://localhost/struts_tutorial";
         Class.forName("com.mysql.jdbc.Driver");
         conn = DriverManager.getConnection(URL, "root", "root");
         String sql = "Insert into login (name, user, password) values (?,?,?)";
         
         PreparedStatement ps = conn.prepareStatement(sql);
         ps.setString(1, name);
         ps.setString(2, user);
         ps.setString(3, password);
         ps.executeUpdate();

        
           
            ret = SUCCESS;
        
      } catch (Exception e) {
    	System.out.println(e);
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
}
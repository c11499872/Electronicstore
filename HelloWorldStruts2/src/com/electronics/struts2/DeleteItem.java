package com.electronics.struts2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.opensymphony.xwork2.ActionSupport;

public class DeleteItem extends ActionSupport{

	private String title;
	ArrayList<Item> list=new ArrayList<Item>();
	
	 public String execute() {
	      String ret = ERROR;
	      Connection conn = null;

	      try {
	         String URL = "jdbc:mysql://localhost/struts_tutorial";
	         Class.forName("com.mysql.jdbc.Driver");
	         conn = DriverManager.getConnection(URL, "root", "root");
	         String sql = "DELETE FROM item WHERE title = ?";
	        
	         PreparedStatement ps = conn.prepareStatement(sql);
	         ps.setString(1, title);
	
	         ps.executeUpdate();
	         LoginAction la = new LoginAction();
        	
	         ret = SUCCESS;
	   
	      
	      } catch (Exception e) {
	    	  title = e.getMessage();
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
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}

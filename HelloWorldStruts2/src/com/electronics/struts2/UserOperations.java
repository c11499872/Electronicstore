package com.electronics.struts2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class UserOperations{

Connection conn = null;
private Map<String, Object> session = SessionFactory.getSessionInstance();
	
	public UserOperations(){
		String URL = "jdbc:mysql://localhost/struts_tutorial";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, "root", "root"); 
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public boolean validLogin(String user, String password) {
		
				PreparedStatement ps;
				try {
					ps = conn.prepareStatement("select password from customer where user_name = ?");
					ps.setString(1, user);
					ResultSet rs=ps.executeQuery();  
					rs.next();
					
						if(password.equals(rs.getString(1))){
							return true;
						}
						else
						{
							session.put("error", "username and password did not match");
							return false;
						}
						} 
				catch (SQLException e) {
					e.printStackTrace();
					return false;
					}  
				
	}
}

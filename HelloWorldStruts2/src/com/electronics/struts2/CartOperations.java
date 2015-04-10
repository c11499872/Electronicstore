package com.electronics.struts2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CartOperations {

	static ArrayList<Item> cartList=new ArrayList<Item>(); 
	
	ItemOperations io = new ItemOperations();
	static ArrayList<Item> orderList=new ArrayList<Item>(); 
	
	Connection conn = null;
	
	public CartOperations(){
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
	
	public ArrayList<Item> getItemFromCart(String user){
		cartList.clear();
			PreparedStatement ps;
			try {
				ps = conn.prepareStatement("select item from cart where user = ?");
				ps.setString(1, user);
				 
				ResultSet rs=ps.executeQuery();  
	  
					while(rs.next()){  
						cartList.add(io.getAllFromItemId(rs.getInt(1)));
					}  
				} 
			catch (SQLException e) {
				e.printStackTrace();
				}  
			
		return cartList;
	}
	
	public ArrayList<Item> addToCart(String user, String item){
		cartList.clear();
			PreparedStatement ps;
			int id = io.getIdFromTitle(item);
			try {
				  	String sql = "Insert into cart (user, item) values (?,?)";
			         
			      	ps = conn.prepareStatement(sql);
			        ps.setString(1, user);
			        ps.setInt(2, id);
			        ps.executeUpdate();
				} 
			catch (SQLException e) {
				e.printStackTrace();
				}  
			
		return cartList;
	}
	
	public double getTotal(String user){
		double total = 0.0;
		
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement("select item from cart where user = ?");
			ps.setString(1, user);
			 
			ResultSet rs=ps.executeQuery();  
  
				while(rs.next()){  
					
					total = total + io.getPrice(rs.getInt(1));
				}  
			} 
		catch (SQLException e) {
			e.printStackTrace();
			}  
		
		return total;
		
	}
	
	public boolean clearcart(String user) {
		try {
	      
	         String sql = "DELETE from cart where user = ?";
	        
	         PreparedStatement ps = conn.prepareStatement(sql);
	         ps.setString(1, user);
	
	         ps.executeUpdate();
	        
       	
	         return true;
	   
	      
	      } catch (Exception e) {
	    	  e.printStackTrace();
	         return false;
	        
	      } 
		
	}
	
	public boolean deleteItemFromCart(String title) {
		try {
			 int id = io.getIdFromTitle(title);
			
	         String sql = "DELETE from cart where item = ?";   
	         PreparedStatement ps = conn.prepareStatement(sql);
	         ps.setInt(1, id);
	
	         ps.executeUpdate();
	        
      	
	         return true;
	   
	      
	      } catch (Exception e) {
	    	  e.printStackTrace();
	         return false;
	        
	      } 
		
	}
	
	public String checkout()
	{
		String username = "test";
		orderList = getItemFromCart(username);
		
		PreparedStatement ps;
	
		try {
			  	String sql = "Insert into orders (user, item) values (?,?)";
			  	for(int i = 0; i<orderList.size()+1; i++){
			  		Item item = orderList.get(i);
			  		ps = conn.prepareStatement(sql);
			        ps.setString(1, username);
			        ps.setInt(2, io.getIdFromTitle(item.getTitle()));
			        ps.executeUpdate();
			  		
			  		
			  	}
			  	return "SUCCESS"; 
		      
			} 
		catch (SQLException e) {
			
			e.printStackTrace();
			return "ERROR";
			}  
		
	}
	
	public static ArrayList<Item> getCartList() {
		return cartList;
	}

	public static void setCartList(ArrayList<Item> cartList) {
		CartOperations.cartList = cartList;
	}

	

	

	
	
}

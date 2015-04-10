package com.electronics.struts2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class OrderOperations{

	String title, username;
	static ArrayList<Item> orderList=new ArrayList<Item>(); 
	ArrayList<Item> itemList=new ArrayList<Item>(); 
	ArrayList<Orders> orders=new ArrayList<Orders>(); 
	
	CartOperations co = new CartOperations();
	ItemOperations io = new ItemOperations();
	private Map<String, Object> session = SessionFactory.getSessionInstance();
	
	Connection conn = null;
	
	public OrderOperations(){
		username = session.get("context").toString();
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
	
	public String vieworders(){
		 orders.clear();
			PreparedStatement ps;
			try {
				ps = conn.prepareStatement("select * from orders");
				ResultSet rs=ps.executeQuery();  
	  
					while(rs.next()){  
						Orders order=new Orders(); 
						order.setId(rs.getInt(1));
						order.setUser(rs.getString(2));
						order.setTitle(io.getTitleFromId(rs.getInt(3)));
						order.setPrice(io.getPriceFromId(rs.getInt(3)));
						orders.add(order);  
					} 
					
				} 
			catch (SQLException e) {
				e.printStackTrace();
				}  
			
		return "SUCCESS";
	}
	
	public String checkout()
	{
		orderList = co.getItemFromCart(username);
		itemList = io.getAllFromItem();
		PreparedStatement ps;
	
		try {
			  	String sql = "Insert into orders (user, item) values (?,?)";
			  	for(int i = 0; i<orderList.size(); i++){
			  		Item item = orderList.get(i);
			  		ps = conn.prepareStatement(sql);
			        ps.setString(1, username);
			        ps.setInt(2, io.getIdFromTitle(item.getTitle()));
			        ps.executeUpdate();
			  		
			  		
			  	}
			  	co.clearcart(username);
			  	return "SUCCESS"; 
		      
			} 
		catch (SQLException e) {
			
			e.printStackTrace();
			return "ERROR";
			}  
		
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public ArrayList<Item> getItemList() {
		return itemList;
	}

	public void setItemList(ArrayList<Item> itemList) {
		this.itemList = itemList;
	}
	

	public ArrayList<Orders> getOrders() {
		return orders;
	}

	public void setOrders(ArrayList<Orders> orders) {
		this.orders = orders;
	}


}

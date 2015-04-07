package com.electronics.struts2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemOperations {

	static ArrayList<Item> list=new ArrayList<Item>(); 
	
	Connection conn = null;
	
	public ItemOperations(){
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
	
	public ArrayList<Item> getAllFromItem(){
		 list.clear();
			PreparedStatement ps2;
			try {
				ps2 = conn.prepareStatement("select * from item");
				ResultSet rs2=ps2.executeQuery();  
	  
					while(rs2.next()){  
						Item item=new Item();  
						item.setTitle(rs2.getString(2));  
						item.setManufacturer(rs2.getString(3));  
						item.setPrice(rs2.getString(4));  
						item.setCategory(rs2.getString(5));  
						list.add(item);  
					}  
				} 
			catch (SQLException e) {
				e.printStackTrace();
				}  
			
		return list;
	}
	
	public Item getAllFromItemId(int id){
		
			PreparedStatement ps;
			Item item=new Item(); 
			try {
					ps = conn.prepareStatement("select * from item where id = ?");
					ps.setInt(1, id);
        	
					ResultSet rs = ps.executeQuery();
		
					rs.next();  
					 
					item.setTitle(rs.getString(2));  
					item.setManufacturer(rs.getString(3));  
					item.setPrice(rs.getString(4));  
					item.setCategory(rs.getString(5));  
					  
					  
				} 
			catch (SQLException e) {
				e.printStackTrace();
				}  
			
		return item;
	}
	
	public int getIdFromTitle(String title){
		
		PreparedStatement ps;
		int id = 0;
		try {
				ps = conn.prepareStatement("select id from item where title = ?");
				ps.setString(1, title);
    	
				ResultSet rs = ps.executeQuery();
				rs.next();  
				 
				id = rs.getInt(1);  
			} 
		catch (SQLException e) {
			e.printStackTrace();
			}  
		
	return id;
}
	
	public double getPrice(int id){
		double price = 0.0;
		PreparedStatement ps;
		
		try {
			ps = conn.prepareStatement("select price from item where id = ?");
			ps.setInt(1, id);
	
			ResultSet rs = ps.executeQuery();

			rs.next();  
			 
			price = Double.parseDouble(rs.getString(1));
			  
			  
		} 
	catch (SQLException e) {
		e.printStackTrace();
		}  
		
		return price;
	}
	
	
	
	
	
	public static ArrayList<Item> getList() {
		return list;
	}

	public static void setList(ArrayList<Item> list) {
		ItemOperations.list = list;
	}
}

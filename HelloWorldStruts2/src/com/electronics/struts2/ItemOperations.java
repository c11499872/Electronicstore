package com.electronics.struts2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ItemOperations extends ActionSupport {

	String title, item;
	String price, manufacturer, category;

	static ArrayList<Item> list=new ArrayList<Item>(); 
	static ArrayList<Item> items=new ArrayList<Item>(); 
	static ArrayList<Review> reviews=new ArrayList<Review>(); 
	static ArrayList<Review> test=new ArrayList<Review>(); 
	

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
	
	public String viewItem(){
		 items.clear();
		
		
			PreparedStatement ps;
			try {
				
				ps = conn.prepareStatement("select * from item where title = ?");
				ps.setString(1, title);
				ResultSet rs=ps.executeQuery();  
	  
				rs.next();  
				
				setManufacturer(rs.getString(3));  
				setPrice(rs.getString(4));  
				setCategory(rs.getString(5));  
				
				Map session = ActionContext.getContext().getSession();
			   	session.put("title", title);
				} 
			catch (SQLException e) {
				e.printStackTrace();
				}  
			
		return SUCCESS;
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
	
	public void test(){
		 
			PreparedStatement ps;
			try {
				ps = conn.prepareStatement("select * from reviews");
				ResultSet rs=ps.executeQuery();  
	  
					while(rs.next()){  
						Review review=new Review();  
						review.setUser(rs.getString(2));  
						review.setItem(rs.getInt(3));  
						review.setComment(rs.getString(4));  
						review.setRating(rs.getInt(5));  
						reviews.add(review);  
					}  
				} 
			catch (SQLException e) {
				e.printStackTrace();
				}
			
		
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
	
	public String getTitleFromId(int id){
		
		PreparedStatement ps;
		String title = "";
		
		try {
				ps = conn.prepareStatement("select title from item where id = ?");
				ps.setInt(1, id);
    	
				ResultSet rs = ps.executeQuery();
				rs.next();  
				 
				title = rs.getString(1);  
			} 
		catch (SQLException e) {
			e.printStackTrace();
			}  
		
	return title;
}
public String getPriceFromId(int id){
		
		PreparedStatement ps;
		String price = "";
		
		try {
				ps = conn.prepareStatement("select price from item where id = ?");
				ps.setInt(1, id);
    	
				ResultSet rs = ps.executeQuery();
				rs.next();  
				 
				price = rs.getString(1);  
			} 
		catch (SQLException e) {
			e.printStackTrace();
			}  
		
	return price;
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
	
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}	
	
	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}


	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	
	public static ArrayList<Item> getList() {
		return list;
	}

	public static void setList(ArrayList<Item> list) {
		ItemOperations.list = list;
	}
	
	public static ArrayList<Item> getItems() {
		return items;
	}

	public static void setItems(ArrayList<Item> items) {
		ItemOperations.items = items;
	}
	
	public static ArrayList<Review> getReviews() {
		return reviews;
	}

	
	public static void setReviews(ArrayList<Review> reviews) {
		ItemOperations.reviews = reviews;
	}
	

	public static ArrayList<Review> getTest() {
		return test;
	}

	public static void setTest(ArrayList<Review> test) {
		ItemOperations.test = test;
	}

	
	
}

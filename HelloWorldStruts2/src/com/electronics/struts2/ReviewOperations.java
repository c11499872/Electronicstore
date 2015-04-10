package com.electronics.struts2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class ReviewOperations{

	String review, username, title;
	int rating;
	
	static ArrayList<Item> orderList=new ArrayList<Item>(); 
	ArrayList<Item> itemList=new ArrayList<Item>(); 
	static ArrayList<Review> list=new ArrayList<Review>(); 
	
	ArrayList<Orders> orders=new ArrayList<Orders>(); 
	
	CartOperations co = new CartOperations();
	ItemOperations io = new ItemOperations();
	private Map<String, Object> session = SessionFactory.getSessionInstance();
	
	Connection conn = null;
	
	public ReviewOperations(){
		username = session.get("context").toString();
		title = session.get("title").toString();
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
	
	public String addreview(){
		
		orderList = co.getItemFromCart(username);
		itemList = io.getAllFromItem();
		PreparedStatement ps;
		try {
			  	String sql = "Insert into reviews (user, item, comment, rating) values (?,?,?,?)";
			  	
			  		ps = conn.prepareStatement(sql);
			        ps.setString(1, username);
			        ps.setInt(2, io.getIdFromTitle(title));
			        ps.setString(3, review);
			        ps.setInt(4, rating);
			        ps.executeUpdate();
			  		
			  	return "SUCCESS"; 
		      
			} 
		catch (SQLException e) {
			
			e.printStackTrace();
			return "ERROR";
			}  
		
	}
	
	public ArrayList<Review> getAllFromReviews(){
		 list.clear();
			PreparedStatement ps2;
			try {
				ps2 = conn.prepareStatement("select * from reviews");
				ResultSet rs2=ps2.executeQuery();  
	  
					while(rs2.next()){  
						Review review=new Review();  
						review.setUser(rs2.getString(2));  
						review.setItem(rs2.getInt(3));  
						review.setComment(rs2.getString(4));  
						review.setRating(rs2.getInt(5));  
						list.add(review);  
					}  
				} 
			catch (SQLException e) {
				e.printStackTrace();
				}  
			
		return list;
	}
	
	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public ArrayList<Item> getItemList() {
		return itemList;
	}

	public void setItemList(ArrayList<Item> itemList) {
		this.itemList = itemList;
	}
	
	public static ArrayList<Review> getList() {
		return list;
	}

	public static void setList(ArrayList<Review> list) {
		ReviewOperations.list = list;
	}




	
}
	
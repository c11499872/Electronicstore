package com.electronics.struts2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class AddToCart extends ActionSupport{

	private String title;
	private double price;
	
	Map session = ActionContext.getContext().getSession();
	CartOperations co = new CartOperations();
	ItemOperations io = new ItemOperations();
	 
	ArrayList<Item> cartList=new ArrayList<Item>(); 
	ArrayList<Item> itemList=new ArrayList<Item>(); 
	

	public String execute() {
	     
	      co.addToCart(session.get("context").toString(), title);
	      cartList = co.getItemFromCart(session.get("context").toString());
	  	  price = co.getTotal(session.get("context").toString());
	   	  session.put("price", price);
	   	
	      return SUCCESS;
	      
	   }
	
	public String clearcart()
	{
		 itemList = io.getAllFromItem();
		 price = 0.0;
		if(co.clearcart(session.get("context").toString()))
			{
			return SUCCESS;
			}
		else
		{
			return ERROR;
		}
		
		
	}
	
	public String viewcart()
	{
		cartList = co.getItemFromCart(session.get("context").toString());
			
			return SUCCESS;
		
	}
	
	public String deleteitem()
	{
		co.deleteItemFromCart(title);
			
			return SUCCESS;
		
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public  ArrayList<Item> getCartList() {  
		 
		return cartList;  
	}  
		
	public void setCartList(ArrayList<Item> testlist) {  
		    
		this.cartList = testlist;  
		
	}  
	
	public ArrayList<Item> getItemList() {
		return itemList;
	}

	public void setItemList(ArrayList<Item> itemList) {
		this.itemList = itemList;
	}
	

}

package com.electronics.struts2;

import java.util.ArrayList;
import java.util.Map;

public class AddToCart{
	
	private String title;
	private double price;
	private String quantity;

	private Map<String, Object> session = SessionFactory.getSessionInstance();
	CartOperations co = new CartOperations();
	ItemOperations io = new ItemOperations();
	 
	ArrayList<Item> cartList=new ArrayList<Item>(); 
	ArrayList<Item> itemList=new ArrayList<Item>(); 
	

	public String execute() {
	     
		  title = session.get("title").toString();
		  int quant = Integer.parseInt(quantity);
	      co.addToCart(session.get("context").toString(), title);
	      cartList = co.getItemFromCart(session.get("context").toString());
	  	  price = co.getTotal(session.get("context").toString());
	   	  session.put("price", price);
	   	  session.put("quantity", quant);
	      return "SUCCESS";
	      
	   }
	
	public String clearcart()
	{
		 itemList = io.getAllFromItem();
		 price = 0.0;
		if(co.clearcart(session.get("context").toString()))
			{
			return "SUCCESS";
			}
		else
		{
			return "ERROR";
		}
		
		
	}
	
	public String viewcart()
	{
		cartList = co.getItemFromCart(session.get("context").toString());
			
			return "SUCCESS";
		
	}
	
	public String deleteitem()
	{
		co.deleteItemFromCart(title);
			
			return "SUCCESS";
		
	}
	

	public String checkout()
	{
		co.checkout();
			
			return "SUCCESS";
		
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
	

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
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

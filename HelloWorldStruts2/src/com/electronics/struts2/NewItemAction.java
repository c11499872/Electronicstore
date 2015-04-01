package com.electronics.struts2;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.opensymphony.xwork2.ActionSupport;

public class NewItemAction extends ActionSupport {

   private String title;
   private String manufacturer;
   private String price;
   private String category;
  // private File image;
  // String password;
   ArrayList<Item> list=new ArrayList<Item>(); 

   public String execute() {
      String ret = ERROR;
      Connection conn = null;

      try {
    	 FileInputStream fis = null;
         String URL = "jdbc:mysql://localhost/struts_tutorial";
         Class.forName("com.mysql.jdbc.Driver");
         conn = DriverManager.getConnection(URL, "root", "root");
         String sql = "insert into item (title, manufacturer, price, category) values (?, ?, ?, ?)";
        
         //fis = new FileInputStream(image);
        
         PreparedStatement ps = conn.prepareStatement(sql);
         ps.setString(1, title);
         ps.setString(2, manufacturer);
         ps.setString(3, price);
         ps.setString(4, category);
         //ps.setBlob(5, fis, (int) image.length());
         ps.executeUpdate();
         
         try{  
        	 
             
          	  PreparedStatement ps3=conn.prepareStatement("select * from item");  
          	  ResultSet rs3=ps3.executeQuery();  
          	  
          	  while(rs3.next()){  
          	   Item item=new Item();  
          	   item.setTitle(rs3.getString(2));  
          	   item.setManufacturer(rs3.getString(3));  
          	   item.setPrice(rs3.getString(4));  
          	   item.setCategory(rs3.getString(5));  
          	   list.add(item);  
          	  }  
          	  
          	  //conn.close();  
          	 }catch(Exception e){
          		 e.printStackTrace();
          		 }
         
         ret = SUCCESS;

      } catch (Exception e) {
    	  title = e.getMessage();
         ret = ERROR;
         
      } finally {
         if (conn != null) {
            try {
               conn.close();
            } catch (Exception e) {
            	title = e.getMessage();
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

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public ArrayList<Item> getList() {  
	    return list;  
	}  
	public void setList(ArrayList<Item> list) {  
	    this.list = list;  
	}  


  
}
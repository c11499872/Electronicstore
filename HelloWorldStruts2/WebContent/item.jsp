<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="display" uri="http://displaytag.sf.net"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Item</title>
</head>
<body>

<h1>Item details:</h1>

	<table>
		<tr>
			<th>Title:</th>
			<th>Manufacturer:</th>
			<th>Price:</th>
			<th>Category:</th>
		</tr>
		<tr>
			<td><s:property value="title"/></td>
			<td><s:property value="manufacturer"/></td>
			<td><s:property value="price"/></td>
			<td><s:property value="category"/></td>
		</tr>
	</table>
	<br />
	<s:form action="addtocart" property = "title" paramId = "title">
		<label>Order Quantity</label>
		<select name="quantity">
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
			<option value="6">6</option>
		</select>
		<br />
		<s:submit type="button">Add To Cart</s:submit>
	</s:form>
	
	<br />
	
	Add review:
<s:form action="addReview" property = "title" paramId = "title">
		<label>Rating</label>
		<select name="rating">
			<option value="1">1*</option>
			<option value="2">2*</option>
			<option value="3">3*</option>
			<option value="4">4*</option>
			<option value="5">5*</option>
		</select>
		<s:textfield name="review"></s:textfield>
		<s:submit type="button">Leave Review</s:submit>
	</s:form>
	<br />
	<h2>Reviews</h2>
	
	
</body>
</html>
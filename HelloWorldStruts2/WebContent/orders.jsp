<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<html>
<head>
<title>Orders</title>
</head>
<body>
 
 Orders: <br/> 

	<display:table name="orders" pagesize="5" requestURI="" sort = "list" style="margin-left:300px;">
	    <display:column property="id" title="Item id" sortable="true"  style="width:100px;"/>
		<display:column media="html" property="title" title="Item" sortable="true" href="reviewitem.action" paramId="title" style="width:100px;"/>
		<display:column property="price" title="Price" sortable="true" style="width:100px;"/>
		</display:table>
<br/>



</body>
</html>
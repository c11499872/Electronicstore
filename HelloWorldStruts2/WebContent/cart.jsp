<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<display:table name="cartList" class="AddToCart" pagesize="5" requestURI="" sort = "list" style="margin-left:300px;">
		<display:column property="title" title="Title" sortable="true" style="width:100px;"/>
		<display:column property="price" title="Price" sortable="true" style="width:100px;"/>
		<display:column media="html" property="title" title="Delete item" href="deletecartitem.action" paramId="title" style="width:100px;"/>
		</display:table>
		
		Total: <s:property value="#session.price" />
		
		<a href="clearcart.action">Clear Cart</a>
	
</body>
</html>
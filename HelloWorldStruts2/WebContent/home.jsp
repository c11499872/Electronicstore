<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<html>
<head>
<title>Home</title>
</head>
<body>
   Welcome <s:property value="#session.context" />, this is the home screen. 
   <br/><br/>
  All Items: <br/> 

	<display:table name="itemList" pagesize="5" requestURI="" sort = "list" style="margin-left:300px;">
		<display:column property="title" title="Title" href="viewitem.action" paramId="title" sortable="true" style="width:100px;"/>
		<display:column property="manufacturer" title="Manufacturer" sortable="true" style="width:100px;"/>
		<display:column property="price" title="Price" sortable="true" style="width:100px;"/>
		<display:column property="category" title="Category" sortable="true" style="width:100px;"/>
		<s:if test="#session.context=='admin'">
	    <display:column media="html" property="title" title="Delete item" href="deleteitem.action" paramId="title" style="width:100px;"/>
	</s:if>
	</display:table>
 

<br/>
<s:if test="#session.context=='admin'">
			<a href="newItem.jsp">Add item</a>
		</s:if>

<a href="viewcart.action">View Cart</a>
<a href="vieworders.action">My Orders</a>

</body>
</html>
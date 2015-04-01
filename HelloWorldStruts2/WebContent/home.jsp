<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>Home</title>
</head>
<body>
   Welcome <s:property value="name"/>, this is the home screen.
   <br/><br/>
  All Items: <br/> 
<s:iterator  value="list">  
<fieldset>  
Title: <s:property value="title"/><br/>  
Manufacturer: <s:property value="manufacturer"/><br/>  
Price: <s:property value="price"/><br/>  
Category: <s:property value="category"/><br/>  
</fieldset>  
</s:iterator> 
<br/>
 <a href="newItem.jsp">Add item</a>
</body>
</html>
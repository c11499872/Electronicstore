<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<form action="newitemaction" method="post">
        
        Title:<br/><input type="text" name="title"/><br/>
        Manufacturer:<br/><input type="text" name="manufacturer"/><br/>
      	Price:<br/><input type="text" name="price"/><br/>
      	Category:<br/><input type="text" name="category"/><br/>
     
    <br /> <input type="submit" value="addItem"/>	


</form>
</body>
</html>
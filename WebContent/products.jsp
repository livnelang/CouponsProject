<%@ page language="java" contentType="text/html; charset=windows-1255"
    import="java.util.*,il.ac.shenkar.samples.model.*"
    pageEncoding="windows-1255"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
<title>Insert title here</title>
</head>
<body>
<table>
<tr>
	<th>id</th><th>price</th><th>name</th>
</tr>
<%
Collection products = (Collection)request.getAttribute("products");
Iterator iterator = products.iterator();
while(iterator.hasNext())
{
	Coupon coupon = (Coupon)iterator.next();
%>
	<tr>
	<td><%= coupon.getId() %></td>
	<td><%= coupon.getName() %></td>
	<td><%= coupon.getDescription() %></td>
	</tr>
	
<% 
}

%>

</table>

</body>
</html>
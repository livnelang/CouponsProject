<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Coupons Entry</title>
		<meta charset="UTF-8">
		</head>
		<body>
		<header>
				<h1 >Add Coupon Page</h1>
				<br>
			</header>
			<form action="/CouponsProject/controller/addproduct" method="get">
			id: <input type="text" name="c_id" />
			<br/>
			name: <input type="text" name="c_name" />
			<br/>
			description: <input type="text" name="c_des" />
			<br/>
			submit: <input type="submit" value="add coupon" />
			</form>
			
			<%
			boolean added = false;
			try{		
			added = (boolean)request.getAttribute("addcoupon");
			}
			
			catch(Exception e) {
				e.printStackTrace();
			}
			if(added)
			{
				out.println("coupon was added");
				
			}
			
			%>
			
			
			
			
			</body>
			</html>
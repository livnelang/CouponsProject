<%@ page language="java" contentType="text/html; charset=windows-1255"
    import="java.util.*,il.ac.shenkar.samples.model.*" 
    pageEncoding="windows-1255"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Coupons Project</title>

    <!-- Bootstrap-CSS & General CSS -->
    <link rel="stylesheet" type="text/css" href="../css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="../css/style.css">
    <script src="../js/jquery-2.1.1.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/admin_js.js"></script>
  </head>
  
<!--  Body Content -->
  <body>
  <jsp:useBean id="cart" class="il.ac.shenkar.samples.model.ShoppingCart" scope="session"/>
  <header>
<!-- The Navigation Bar -->
<nav class="navbar navbar-default" role="navigation">
   <div class="navbar-header">
      <a class="navbar-brand" href="/CouponsProject/index.jsp">CouponsProject</a>
   </div>

      <ul class="nav navbar-nav">
         <li><a href="/CouponsProject/controller/coupons">Available Coupons</a></li>
         <li><a href="/CouponsProject/controller/mycartentry">My Coupons</a></li>
         <li><a href="/CouponsProject/controller/admin">Connect As Admin</a></li>
          <li><p class="navbar-text right_li">2014 Java EE Project</p></li>	
          <li><button class="white navbar-text" type="button"><span class="glyphicon glyphicon-home"></span></button></li>
          </ul>
</nav>	
</header>		

<!-- BootStrap Panel Table Showing Our Coupons -->
<div class="panel panel-primary coupouns_panel">
  <div class="panel-heading">
    <ul class="admin-ul">
    	<li>Stored Coupons</li>
    	<li class="dropdown pull-right"> 
                <div class="btn-group">
			    <button class="btn btn-primary">Select Category</button>
			    <button class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
			    <span class="caret"></span>
			    </button>
			    <ul class="dropdown-menu">
			    <c:forEach var="cat" items="${catgs}">
			    <li><a href="/CouponsProject/controller/category?catgry=${cat}">${cat}</a></li>	
			    </c:forEach>
			    <li><a href="/CouponsProject/controller/category?catgry=${cat}">Nearest Coupons</a></li>	
			    </ul>
			    </div>
            </ul>
  </div>
   <table class="table">
   <tr>
      <th class="col-sm-1">Coupon Name</th><th  class="col-sm-1">Description</th class="col-sm-1"><th class="col-sm-1">Quantity</th>
      </tr>
      <% 
      	System.out.println("inside mycoupons.jsp: "+cart);
      	
      	Map<Integer,ShoppingCartLine> lines =  cart.getLines();
      	//Iterator it = lines	.iterator();
      	
		for(ShoppingCartLine value : lines.values())
		{
		//ShoppingCartLine sp = (ShoppingCvalue;
		System.out.println("it: "+value);
		
		%>
		<tr>
		<td><%= value.getC1().getName() %></td>
		<td><%= value.getC1().getDescription() %></td>
		<td><%= value.getAmount() %></td>
		</tr>
	
		<% 
		}

	 %>

   </table>
</div>

  </body>
</html>
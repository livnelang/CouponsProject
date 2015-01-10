<%@ page language="java" contentType="text/html; charset=windows-1255"
    import="java.util.*,il.ac.shenkar.samples.model.*" 
    pageEncoding="windows-1255"%>
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
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
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
          <!--  <ul class="nav navbar-nav navbar-right">
          <li><p class="navbar-text">2014 Java EE Project</p></li>
          <li><button class="white navbar-text" type="button"><span class="glyphicon glyphicon-home"></span></button></li>
          
          </ul>	
          -->

</nav>	
</header>		

<!-- BootStrap Panel Table Showing Our Coupons -->
<div class="panel panel-info coupouns_panel">
  <div class="panel-heading">
    <h3 class="panel-title">My Coupons</h3>
  </div>
   <table class="table">
      <th class="col-sm-1">Coupon Name</th><th  class="col-sm-1">Description</th class="col-sm-1"><th class="col-sm-1">Quantity</th>
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
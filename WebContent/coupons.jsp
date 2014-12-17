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

    <!-- Bootstrap -->
    <link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="../js/bootstrap.min.js"></script>
  </head>
  <body>
  
<!-- The Navigation Bar -->
<nav class="navbar navbar-default" role="navigation">
   <div class="navbar-header">
      <a class="navbar-brand" href="/CouponsProject/index.html">CouponsProject</a>
   </div>
   <div>
      <ul class="nav navbar-nav">
         <li class="active"><a href="/CouponsProject/controller/coupons">Watch Your Coupons</a></li>
         <li><a href="/CouponsProject/addcoupon.jsp">Add Coupon</a></li>
         <li><a href="#">Get Coupon</a></li>
         <li><a href="/CouponsProject/controller/getCookies">Get Cookies</a></li>
         <li class="dropdown">
            <a href="/CouponsProject/controller/addcoupon" class="dropdown-toggle" data-toggle="dropdown">
               Java 
               <b class="caret"></b>
            </a>
            <ul class="dropdown-menu">
               <li><a href="#">jmeter</a></li>
               <li><a href="#">EJB</a></li>
               <li><a href="#">Jasper Report</a></li>
               <li class="divider"></li>
               <li><a href="#">Separated link</a></li>
               <li class="divider"></li>
               <li><a href="#">One more separated link</a></li>
            </ul>
         </li>
      </ul>
   </div>
</nav>

<!-- BootStrap Panel Table Showing Our Coupons -->
<div class="panel panel-default">
   <div class="panel-heading">Sored Coupons</div>
   <table class="table">
      <th>Id</th><th>Name</th><th>Description</th>
      <% 
      	Collection products = (Collection)request.getAttribute("coupons");
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
</div>

<!-- The Footer Bar -->
<div class="navbar navbar-default navbar-fixed-bottom">
      <div class="container">
      <a href="/CouponsProject/index.html">
      <button type="button" class="navbar-btn btn-info btn pull-left">
      <span class="glyphicon glyphicon-home"></span>
      
   	  </button>
   	  </a>
        <p class="navbar-text">2014 Java EE Project</p>
        <button type="button" class="navbar-btn btn-info btn pull-right">About</button>
      </div>	
    </div>

    
  </body>
</html>
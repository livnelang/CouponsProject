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
		<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
		 <link rel="stylesheet" type="text/css" href="css/style.css">
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
		<!-- Include all compiled plugins (below), or include individual files as needed -->
		<script src="js/bootstrap.min.js"></script>
	  </head>

	<!--  Body Content -->
	  <body>
	  <header>
	<!-- The Navigation Bar -->
	<nav class="navbar navbar-default" role="navigation">
	   <div class="navbar-header">
		  <a class="navbar-brand" href="/CouponsProject/index.jsp">CouponsProject</a>
	   </div>

		  <ul class="nav navbar-nav">
			 <li><a href="/CouponsProject/controller/coupons">Available Coupons</a></li>
			 <li><a href="/CouponsProject/controller/mycartentry">My Coupons</a></li>
			 <li><a href="/CouponsProject/adminentry.jsp">Connect As Admin</a></li>
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


	<div class="alert alert-dismissable alert-info entry_text">
	<strong>Hello ! </strong> <br> <br>

	 Welcome to our coupons managment project,<br>
	 Out application enable you to watch different coupons by choice, <br>
	 adding them to your cart and follow them in your session.		  <br>
	 Manager side can edit, delete, or adding coupons after login.	  <br><br>
	 
	 Enjoy <br><br>
	 
	</div>	

	  
	<div class="col-xs-2 alert alert-dismissable alert-info col">
	  <strong>Response time:</strong> <br>
	  <%
	  String time = (String)request.getAttribute("c_time");
	  if(time!=null) {
		  time.toString();
	  }
	  else{
		  %>
		  sddsasd
		<%   
	  }
	  %>
	  
	  
	</div>
	  </body>
	</html>
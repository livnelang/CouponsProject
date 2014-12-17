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
    <link href="/CouponsProject/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="css/style.css" rel="stylesheet">
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
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


			
	<!--  Form for Adding A Coupon -->
	<form class="form-horizontal" role="form" action="/CouponsProject/controller/addcoupon" method="get">
	<div class="form-group">
      <label for="id" class="col-sm-2 control-label">Coupon ID</label>
      <div class="col-sm-2">
         <input type="text" class="form-control" name="c_id" 
            placeholder="Enter random id">
      </div>
   </div>		
   <div class="form-group">
      <label for="firstname" class="col-sm-2 control-label">Coupon Name</label>
      <div class="col-sm-2">
         <input type="text" class="form-control" name="c_name" 
            placeholder="Enter Name">
      </div>
   </div>
   <div class="form-group">
      <label for="lastname" class="col-sm-2 control-label">Description</label>
      <div class="col-sm-2">
         <input type="text" class="form-control" name="c_des" 
            placeholder="Enter Coupon Description">
      </div>
   </div>

   <div class="form-group">
      <div class="col-sm-offset-2 col-sm-10">
         <button type="submit" class="btn btn-default">Add Coupon</button>
      </div>
   </div>
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
				%>
				<h1>coupon was added</h1>
			<% 
			}
			else 
			{
				%>
				<h1>coupon wasnt added</h1>
				<% 
			}
			
			%>



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
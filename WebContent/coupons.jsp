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
    <h3 class="panel-title">Stored Coupons</h3>
  </div>
   <table class="table">
      <th class="col-sm-1">Id</th><th  class="col-sm-1">Name</th class="col-sm-1"><th class="col-sm-1">Description</th><th>Expiration</th><th>Purchase</th>
      <% 
      	Collection coupons = (Collection)request.getAttribute("coupons");
      	if(coupons ==null){
      		out.write("DB Is Empty ");
      	}
     	// if there are coupons & Collection isnt null
      	else {
		Iterator iterator = coupons.iterator();
		while(iterator.hasNext())
		{
		Coupon coupon = (Coupon)iterator.next();
		%>
		<tr>
		<td><%= coupon.getId() %></td>
		<td><%= coupon.getName() %></td>
		<td><%= coupon.getDescription() %></td>
		<td><%= coupon.getDate() %></td>
		<td><a href="/CouponsProject/controller/mycart?c_id=<%= coupon.getId() %>">Add To Cart</a></td>
		</tr>
	
		<% 
		}
		%>

	 <% 
		 }
      	%>


   </table>
</div>
	
<div id="time_div" class="col-xs-2 alert alert-dismissable alert-info col">
	  <strong>Response time:</strong> <br>
	  
		<script type="text/javascript"> 
	  	$( document ).ready(function() {
	  		var time = <%=request.getAttribute("ctime")%>;
	  	console.log( time );
	  	});
		</script>
	  <%
	  String time = null;
	  time = (String)request.getAttribute("ctime");
	  System.out.println("controller took:"+time);
	  if(time!=null) {
		  %>
		  
		<%=  time%>
		 
		 <% 
	  }
	
	  %>
</div>
  </body>
</html>
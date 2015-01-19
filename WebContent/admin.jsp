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
    <script src="../js/jquery-2.1.1.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
    <script src="../js/admin_js.js"></script>
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
<div class="panel panel-primary coupouns_panel">
  <div class="panel-heading">
  	<ul class="admin-ul">
    	<li>Admin Control Panel</li>
    	<li class="dropdown pull-right"> 
                <div class="btn-group">
			    <button class="btn btn-primary">Action</button>
			    <button class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
			    <span class="caret"></span>
			    </button>
			    <ul class="dropdown-menu">
			    <li><a href="/CouponsProject/adminController/addpage">Add Coupon</a></li>
			    <li><a href="#">Add business</a></li>
			    <li><a href="#">Change Password</a></li>
			    </ul>
			    </div>
            </ul>
        </li>
    </ul>
  </div>
   <table class="table">
      <th class="col-sm-1">Id</th><th  class="col-sm-1">Name</th class="col-sm-1"><th class="col-sm-1">Description</th><th class="col-sm-3">Expiration</th><th class="col-sm-1">Edit</th><th class="col-sm-1">Delete</th>
      <% 
      	MySQLCouponsDAO myDAo = (MySQLCouponsDAO)request.getAttribute("inventory");
      	Collection coupons = myDAo.getInstance().getCoupons();
      	if(coupons ==null){
      		out.write("No Coupons Arrived");
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
		<td><button type="button" name=<%=coupon.getId() %>  class="black_gliph editbt"><span class="glyphicon glyphicon-list-alt"></span></button></td>
		<td><button class="black_gliph removebt" name=<%=coupon.getId() %> type="button"><span class="glyphicon glyphicon-floppy-remove"></span></button></td>
		</tr>
		
	
		<% 
		}
		%>

	 <% 
		 }
      	%>


   </table>
</div>


</body>
</html>
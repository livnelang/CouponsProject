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
    <link rel="stylesheet" type="text/css" href="./css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="./css/style.css">
    <script src="./js/jquery-2.1.1.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
    <script src="./js/admin_js.js"></script>
  </head>
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
			
	<!--  Admin Control Panel -->
	<div class="panel panel-primary coupouns_panel">
  <div class="panel-heading">
  	<ul class="admin-ul">
    	<li>Add Coupon</li>
    	<li class="dropdown pull-right"> 
                <div class="btn-group">
			    <button class="btn btn-primary">Action</button>
			    <button class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
			    <span class="caret"></span>
			    </button>
			    <ul class="dropdown-menu">
			    <li><a href="../addcoupon.jsp">Add Coupon</a></li>
			    <li><a href="#">Add business</a></li>
			    <li><a href="#">Change Password</a></li>
			    </ul>
			    </div>
            </ul>
 	</div>
	<div class="panel-body">
	<form name="addForm" class="edit_form" role="form" action="/CouponsProject/controller/addcoupon" method="get" onsubmit="return addCouponValidateForm()">	
   	 <div class="form-group col-xs-3 user-list">
      <label for="firstname">Coupon Id</label>
         <input type="text" class="form-control" name="c_id" 
            placeholder="Enter Id">
   	</div>
   
   <div class="form-group col-xs-3 user-list">
      <label for="firstname">Coupon Name</label>
         <input type="text" class="form-control" name="c_name" 
            placeholder="Enter Name">
   </div>
   <div class="form-group col-xs-3 user-list">
      <label for="lastname">Description</label>
         <input type="text" class="form-control" name="c_des"
            placeholder="Enter Coupon Description">
  	</div>
  		<div class="form-group col-xs-3 user-list">
	      <label for="description">Category</label>
	         <input type="text" class="form-control" name="c_cat" 
	            placeholder="Enter Coupon Category">
	  	</div>
	  		<div class="form-group col-xs-3 user-list">
	      <label for="longitude">Longitude</label>
	         <input type="text" class="form-control" name="c_ltude"
	            placeholder="Enter Coupon Longitude">
	  		</div>
	  			<div class="form-group col-xs-3 user-list">
		      		<label for="latitude">Latitude</label>
		         		<input type="text" class="form-control" name="c_latude"
		            placeholder="Enter Coupon Latitude">
	  			</div>
			  		<div class="form-group col-xs-5 user-list">
			      	 <label for="lastname">Exipry Date Time</label>
			          <div class='input-group date' id='datetimepicker1'>
			          <input type="text" value="" class="form-control" name="exp_date" placeholder="Enter: yyyy/mm/dd hh:mm" />
			        	<span id="time_click" class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
			         	</span>
			         	</div>
			  		</div>

  		
   <div class="form-group col-xs-3 user-list">
   <button type="submit" class="btn btn-default">Add Coupon  &nbsp<span class="glyphicon glyphicon-hand-right"></span></button>
         
   </div>  
	</form>
</div>

</div>
			
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
  </body>
</html>
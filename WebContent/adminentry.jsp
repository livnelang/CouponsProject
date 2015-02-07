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
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
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
</nav>	
	</header>
	
	
<div class="panel panel-info coupouns_panel">
  <div class="panel-heading">
    <h3 class="panel-title">Administrator Entry</h3>
  </div>
  <div class="panel-body">
  	<div id="lefted_form">
		<form action="/CouponsProject/adminController/login_request" method="post" class="admin_form">
	            <div class="col-xs-6 user-list">
	                <div class="input-group">
	                    <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
	                    <input type="text" name="name" class="form-control" placeholder="Username">
	                </div>
	            </div>
	            <div class="col-xs-6 user-list">
	                <div class="input-group">
	                	<span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
	                    <input type="password" name="pwd" class="form-control" placeholder="Password">
	                </div>
	            </div>
	             <div class="col-xs-3 user-list">
	            <button type="submit" class="btn btn-primary">Login</button>
	            </div>
	    </form>	
    </div>
    	
    	<article id="admin_response">
    	</article>
    	<%
    	int  access  = 0 ;
    	try {
	    	Object o = request.getAttribute("login_failed") ;
	    	access = (Integer.parseInt(o.toString()));
    		}
    	
    	catch (Exception e) {
			// set the request page a false variable attempt
			// & redirect to try again
			e.printStackTrace();
		}
	    	if ( access < 0 ) {	
	    		%>
	    		<script>
	    		window.onload = function() {
	    		console.log("attempt failed");
	    		document.getElementById("admin_response").style.backgroundImage  = "url('../img/access_denied.png')";
	    			}
	    	
    		</script>
    		
    		<% 
    		
    	}
    	%>
    
   </div>
    </div>

    
</body>
</html>
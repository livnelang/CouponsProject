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
      <a class="navbar-brand" href="../controller/index">CouponsProject</a>
   </div>

      <ul class="nav navbar-nav">
         <li><a href="/CouponsProject/controller/coupons">Available Coupons</a></li>
         <li><a href="/CouponsProject/controller/mycartentry">My Coupons</a></li>
         <li><a href="/CouponsProject/controller/admin">Connect As Admin</a></li>
          <li><p class="navbar-text right_li">2014 Java EE Project</p></li>	
          <li><button onClick="location.href='../controller/index'" class="white navbar-text" type="button"><span class="glyphicon glyphicon-home"></span></button></li>
          </ul>
</nav>	
	</header>
	
	<!-- BootStrap Panel Table Showing Our Coupons -->
<div class="panel panel-primary coupouns_panel">
  <div class="panel-heading">
  	<ul class="admin-ul">
    	<li>Active Http Sessions</li>
    </ul>
  </div>
   <table class="table">
    
    <%@ taglib uri="/WEB-INF/tld/db_coupons.tld" prefix="abelski" %>	
   <abelski:sessionstag sessions="${sessions}"  >
    </abelski:sessionstag> 

   </table>
</div>


</body>
</html>
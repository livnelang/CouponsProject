<%@page import="java.io.PrintWriter"%>
<%@page import="java.io.Console"%>
<%@ page language="java" contentType="text/html; charset=windows-1255"
    import="java.util.*,il.ac.shenkar.samples.model.*,java.text.DecimalFormat"
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
</nav>	
</header>

<!-- BootStrap Panel Table Showing Our Coupons -->

<div class="panel panel-primary coupouns_panel">
  <div class="panel-heading">
    <ul class="admin-ul">
    	<li>Coupons Next To You </li>
    	<li class="dropdown pull-right"> 
                <div class="btn-group">
			    <button class="btn btn-primary">Action</button>
			    <button class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
			    <span class="caret"></span>
			    </button>
			    <ul class="dropdown-menu">
			    <c:forEach var="cat" items="${catgs}">
			    <li><a href="/CouponsProject/controller/category?catgry=${cat}">${cat}</a></li>	
			    </c:forEach>
			    <li><a href="/CouponsProject/location_coupons.jsp">Nearest Coupons</a></li>	
			    </ul>
			    </div>
            </ul>
        </li>
    </ul>
  </div>
   <table class="table">

      <th class="col-sm-1">Id</th>
      <th  class="col-sm-1">Name</th class="col-sm-1">
      <th class="col-sm-1">Description</th>
      <th class="col-sm-1">Category</th>
      <th class="col-sm-1">Longitude</th>
      <th class="col-sm-1">Latitude</th>
      <th class="col-sm-1">Distance</th>
      <th>Expiration</th><th>Purchase</th>
      
      
     <% 
     	Collection<Double> dist = (Collection<Double>)request.getAttribute("distances");
     	Iterator iterator = dist.iterator();
     	Double d;
     %>         
    <c:forEach var="cpn" items="${coupons}">
    <tr>
    <td>${cpn.getId()}</td>
    <td>${cpn.getName()}</td>
    <td>${cpn.getDescription()}</td>
    <td>${cpn.getCategory()}</td>
    <td>${cpn.getLongitude()}</td>
    <td>${cpn.getLatitude()}</td>
    <td style="font-weight: bold; color:brown;">
    	<%d=(Double)iterator.next();
    	  DecimalFormat df = new DecimalFormat("#.##");  
    	  d=Double.valueOf( df.format(d) );
    	%>
    	
    	<%=d %>
    </td>
    <td>${cpn.getDate()}</td>
   	<td><a href=/CouponsProject/controller/mycart?c_id=${cpn.getId()}>Add To Cart</a></td>
    </tr>
    </c:forEach>
     
   </table>
</div>
	

</div>
  </body>
</html>
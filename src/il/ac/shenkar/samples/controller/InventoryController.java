package il.ac.shenkar.samples.controller;


import il.ac.shenkar.samples.model.Coupon;
import il.ac.shenkar.samples.model.CouponException;
import il.ac.shenkar.samples.model.InventoryException;
import il.ac.shenkar.samples.model.LocationManager;
import il.ac.shenkar.samples.model.MD5Manager;
import il.ac.shenkar.samples.model.MySQLCouponsDAO;
import il.ac.shenkar.samples.model.ShoppingCart;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;

/**
 * Servlet implementation class InventoryController
 */
//@WebServlet("/controller/*")
public class InventoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static int count = 0;
	private static String client_name=null;
	private static String client_id=null;
	private boolean admincredit = false;
	static Logger logger = Logger.getLogger(InventoryController.class);  		  // Main Logger

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InventoryController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    /**
     * Initialize the class 
     * by setting the inventory Attribute
     */
    public void init() {
    	try {
			getServletContext().setAttribute("inventory", MySQLCouponsDAO.getInstance());
			logger.info(new Date().toString());
			logger.info("InventoryController In Construction ..");
			logger.info(" ");
			} 
    	catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getPathInfo();
		if (path !=null  ) {
		logger.info("http GetMethod - Referer Is :"+ request.getHeader("referer"));
		}
		RequestDispatcher dispatcher = null;

		count++;
		
		
		/**
		 * Display Available Coupons
		 */	
		if(path.endsWith("coupons"))
		{
			try {
				MySQLCouponsDAO inventory =  (MySQLCouponsDAO) getServletContext().getAttribute("inventory");
				
				request.setAttribute("coupons", inventory.getCoupons());
				request.setAttribute("catgs", inventory.getCategories());
				dispatcher = getServletContext().getRequestDispatcher("/coupons.jsp");
				dispatcher.include(request, response);
			}
			/*catch(InventoryException e) {
				dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
				dispatcher.forward(request,response);
			}*/ catch (CouponException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}
		
		/**
		 * Coupons Chosen By Category
		 * Gets the category parameter
		 * and return array of coupons
		 */
		else if(path.endsWith("category"))
		{
			try {
				MySQLCouponsDAO inventory =  (MySQLCouponsDAO) getServletContext().getAttribute("inventory");
				String catg = request.getParameter("catgry");
				if (catg != null) {
					Collection<Coupon> catgarray = inventory.getCategoryCoupons(catg);
					if(catgarray!= null){
						System.out.println("Size of category array returned: "+catgarray.size());
						request.setAttribute("coupons", catgarray);
					}
				}
				request.setAttribute("catgs", inventory.getCategories());
				dispatcher = getServletContext().getRequestDispatcher("/coupons.jsp");
				dispatcher.include(request, response);
			}
			
			catch (CouponException e) {
				dispatcher = getServletContext().getRequestDispatcher("/404-page.jsp");
				dispatcher.include(request, response);
				e.printStackTrace();
			}
			
			
			
		}
		
		
		
		
		
		
		/**
		 * Added Item Clicked
		 */
		else if(path.endsWith("mycart"))
		{
			int productId = Integer.parseInt(request.getParameter("c_id"));
			try {
				// getting the Inventory object
				MySQLCouponsDAO inventory =  (MySQLCouponsDAO) getServletContext().getAttribute("inventory");
				// adding product to shopping cart
				// we first need to verify that a shopping cart already exists
				// on the session
				HttpSession session = request.getSession();
				if(session.getAttribute("cart")==null) {
					session.setAttribute("cart", new ShoppingCart());
				}
				// check to see whether we put catgs for the user
				if ( session.getAttribute("catgs")==null ) {
					session.setAttribute("catgs", inventory.getCategories());
				}
				//adding the product to the shopping cart
				ShoppingCart cart = (ShoppingCart)session.getAttribute("cart");
				
					cart.addShoppingCartLine(inventory.getCoupon(productId));
					
			} catch (CouponException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// Going to turn it to mycoupons.jsp page and show purchased coupons
			dispatcher = getServletContext().getRequestDispatcher("/mycoupons.jsp");
			dispatcher.include(request, response);
		}
		
		/**
		 * Redirects to the session stored Coupons
		 */
		else if(path.endsWith("mycartentry")) {
			// Going to turn it to mycoupons.jsp page and show purchased coupons
			HttpSession session = request.getSession();
			if(session.getAttribute("cart")==null) {
				System.out.println("null cart");
			}
			
			// Check whether wev'e got a cart in session, if we do then update validate coupons
			if ( session.getAttribute("cart")!=null )  {
				// get cart from session
				ShoppingCart cart = (ShoppingCart)session.getAttribute("cart");
				// update lines
				cart.update_lines();
			}
			
			dispatcher = getServletContext().getRequestDispatcher("/mycoupons.jsp");
			dispatcher.include(request, response);
		}
		
		
		
		/**
		 * If client wants Coupons By Location 
		 * controller gets 2 parameters
		 * Longitude, Latitude & return 
		 * nearest coupons
		 */
		else if(path.endsWith("location")) {
			// Going to turn it to mycoupons.jsp page and show purchased coupons
			HttpSession session = request.getSession();
			if(session.getAttribute("cart")==null) {
				System.out.println("null cart");
			}
			
			dispatcher = getServletContext().getRequestDispatcher("/mycoupons.jsp");
			dispatcher.include(request, response);
		}

		/**
		 * CHECK WHETHER ADMIN CREDIT IS GIVEN 
		 * WITH BOOLEAN VALUES,
		 * IF TRUE THEN REDIRECT TO 
		 * ADMIN PAGE
		 */
		else if(path.endsWith("admin")) {
			try {
				boolean admin_connected = false;
				HttpSession session = request.getSession();
				admin_connected = (boolean) session.getAttribute("admin_log");
				if(admin_connected){
					dispatcher = getServletContext().getRequestDispatcher("/admin.jsp");
					dispatcher.forward(request, response);
				}
					else {
						dispatcher = getServletContext().getRequestDispatcher("/adminentry.jsp");
						dispatcher.include(request, response);
					}
				}
			
			catch (Exception e) {
				dispatcher = getServletContext().getRequestDispatcher("/adminentry.jsp");
				dispatcher.forward(request, response);
				e.printStackTrace();
			}
		}

		
		/**
		 * Redirects to 404 page 
		 */
		else {
			dispatcher = getServletContext().getRequestDispatcher("/404-page.jsp");
			dispatcher.forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getPathInfo();
		RequestDispatcher dispatcher = null;
		
		
		/**
		 * Get Client request to find coupons 
		 * By Location,
		 * paramters expected: longitude, latitude
		 */
		if(path.endsWith("location")) {
			float ltude;
			float lat;
			ArrayList<Coupon> coupon_array = new ArrayList<Coupon>();
			LocationManager locman = new LocationManager();
			try {
				ltude = Float.parseFloat(request.getParameter("longitude"));
				lat = Float.parseFloat(request.getParameter("latitude"));
				MySQLCouponsDAO inventory =  (MySQLCouponsDAO) getServletContext().getAttribute("inventory");
				coupon_array = (ArrayList<Coupon>) locman.Compute_Distance(inventory.getCoupons(), ltude, lat);
				request.setAttribute("coupons", coupon_array);
				request.setAttribute("distances", locman.getDistance_array());
				//request.setAttribute("catgs", inventory.getCategories());
				dispatcher = getServletContext().getRequestDispatcher("/location_view.jsp");
				dispatcher.include(request, response);
			}
			catch (CouponException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		/**
		 * Redirects to 404 page 
		 */
		else {
			dispatcher = getServletContext().getRequestDispatcher("/404-page.jsp");
			dispatcher.forward(request, response);
		}
	}

}

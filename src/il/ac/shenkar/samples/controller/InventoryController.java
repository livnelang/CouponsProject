package il.ac.shenkar.samples.controller;


import il.ac.shenkar.samples.model.Coupon;
import il.ac.shenkar.samples.model.CouponException;
import il.ac.shenkar.samples.model.InventoryException;
import il.ac.shenkar.samples.model.MD5Manager;
import il.ac.shenkar.samples.model.MySQLCouponsDAO;
import il.ac.shenkar.samples.model.ShoppingCart;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
@WebServlet("/controller/*")
public class InventoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static int count = 0;
	private static String client_name=null;
	private static String client_id=null;
	private boolean admincredit = false;
	private Logger logger=null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InventoryController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    public void init() {
    	try {
			getServletContext().setAttribute("inventory", MySQLCouponsDAO.getInstance());
			logger = Logger.getRootLogger();
			BasicConfigurator.configure();
			logger.setLevel(Level.OFF);
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
		//logger.info("Request has come, path: "+path);
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
				dispatcher.forward(request, response);
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
		 * Admin addCoupon page
		 * ets all input parameters
		 * and set new Coupon on Data Base
		 */
		else if(path.endsWith("addcoupon"))
		{
			Coupon c1=null;
			HttpSession session = request.getSession();
			try {
				int _id = Integer.parseInt(request.getParameter("c_id"));
				String name = request.getParameter("c_name");
				String desc = request.getParameter("c_des");
				String catg = request.getParameter("c_cat");
				int ltude =  (int) session.getAttribute("c_ltude");
				int latude =  (int) session.getAttribute("c_latude");
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm");
				Date d = simpleDateFormat.parse(request.getParameter("exp_date"));
				c1 = new Coupon(_id,name,desc,catg,ltude,latude,d);
			}
			
			catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
				request.setAttribute("addcoupon", MySQLCouponsDAO.getInstance().addCoupon(c1));
				if( (boolean) request.getAttribute("addcoupon")) {
					logger.info("Coupon id: "+request.getParameter("c_id")+" was added !");
				}
				dispatcher = getServletContext().getRequestDispatcher("/admin.jsp");
				dispatcher.forward(request, response);
			} catch (CouponException e) {
				// TODO Auto-generated catch block	
				e.printStackTrace();
			}
			
		}
		
		/**
		 * Incoming updated coupon 
		 * gets parameters from request, 
		 * and update with MySQLDAO instance
		 */
		else if(path.endsWith("updatecoupon"))
		{
			Coupon c1=null;
			HttpSession session = request.getSession();
			try {
				int _id =  (int) session.getAttribute("coupon_for_edit");
				String name = request.getParameter("c_name");
				String desc = request.getParameter("c_des");
				String catg = request.getParameter("c_cat");
				int ltude =  (int) session.getAttribute("c_ltude");
				int latude =  (int) session.getAttribute("c_latude");
				System.out.println(request.getParameter("exp_date"));
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm");
				Date d = simpleDateFormat.parse(request.getParameter("exp_date"));
				c1 = new Coupon(_id,name,desc,catg,ltude,latude,d);
			}
			catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			try {
				request.setAttribute("updated_coupon", MySQLCouponsDAO.getInstance().updateCoupon(c1));
				if( (boolean) request.getAttribute("updated_coupon")) {
					logger.info("Coupon id: "+(int) session.getAttribute("coupon_for_edit")+" was updated !");
				}
				dispatcher = getServletContext().getRequestDispatcher("/admin.jsp");
				dispatcher.forward(request, response);
			} catch (CouponException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		/**
		 * Delete Coupon Treat
		 * Delete by coupon Id
		 */
		else if(path.endsWith("deletecoupon"))
		{
			try {
				boolean admin_connected = false;
				HttpSession session = request.getSession();
				admin_connected = (boolean) session.getAttribute("admin_log");
				if(admin_connected){
					int coupon_id = Integer.parseInt(request.getParameter("c_id"));
					if(MySQLCouponsDAO.getInstance().deleteCoupon(coupon_id)) {
						logger.info("Coupon id: "+request.getParameter("c_id")+" was deleted !");
					}
					dispatcher = getServletContext().getRequestDispatcher("/admin.jsp");
					dispatcher.forward(request, response);
				}
					else {
						dispatcher = getServletContext().getRequestDispatcher("/admin.jsp");
						dispatcher.forward(request, response);
					}
				}
			
			catch (Exception e) {
				dispatcher = getServletContext().getRequestDispatcher("/adminentry.jsp");
				dispatcher.forward(request, response);
				e.printStackTrace();
			}
		}
		
		
		
		
		/**
		 * Added Item Clicked
		 */
		else if(path.endsWith("mycart"))
		{
			int productId = Integer.parseInt(request.getParameter("c_id"));
			// getting the Inventory object
			MySQLCouponsDAO inventory =  (MySQLCouponsDAO) getServletContext().getAttribute("inventory");
			// adding product to shopping cart
			// we first need to verify that a shopping cart already exists
			// on the session
			HttpSession session = request.getSession();
			if(session.getAttribute("cart")==null) {
				session.setAttribute("cart", new ShoppingCart());
			}
			//adding the product to the shopping cart
			ShoppingCart cart = (ShoppingCart)session.getAttribute("cart");
			try {
				cart.addShoppingCartLine(inventory.getCoupon(productId));
			} catch (CouponException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// Going to turn it to mycoupons.jsp page and show purchased coupons
			dispatcher = getServletContext().getRequestDispatcher("/mycoupons.jsp");
			dispatcher.forward(request, response);
			
			
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
			
			dispatcher = getServletContext().getRequestDispatcher("/mycoupons.jsp");
			dispatcher.forward(request, response);
		}
		
		
		
		/**
		 * Take care of Admin attempt to enter the system
		 */
		else if(path.endsWith("login_request")) {
			
			try {
			// extract session parameters for check
			String user = request.getParameter("name");
			String password = request.getParameter("pwd");
			MD5Manager md5 = new MD5Manager();
			if(user.equals("admin")){
				admincredit = md5.passAuthentication(password);
				}
			// if return false --> then redirect to errorPage
				else {
					dispatcher = getServletContext().getRequestDispatcher("/404-page.jsp");
					dispatcher.forward(request, response);
				}
			
			// if return positive --> then redirect to AdminPage
			if(admincredit) {
				HttpSession session = request.getSession();
				// set administration credit with true boolean variable
				session.setAttribute("admin_log", true);
				dispatcher = getServletContext().getRequestDispatcher("/admin.jsp");
				dispatcher.forward(request, response);
				}
			
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			/*dispatcher = getServletContext().getRequestDispatcher("/mycoupons.jsp");
			dispatcher.forward(request, response);*/
			
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
						dispatcher.forward(request, response);
					}
				}
			
			catch (Exception e) {
				dispatcher = getServletContext().getRequestDispatcher("/adminentry.jsp");
				dispatcher.forward(request, response);
				e.printStackTrace();
			}
			
		}
		
		/**
		 * Treat Edit request from admin 
		 */
		else if(path.endsWith("edit_coupon")) {
			try {
				boolean admin_connected = false;
				HttpSession session = request.getSession();
				admin_connected = (boolean) session.getAttribute("admin_log");
				if(admin_connected){
					int edit_coupon = Integer.parseInt(request.getParameter("c_id"));
					session.setAttribute("coupon_for_edit", edit_coupon);
					request.setAttribute("coupon_for_edit",MySQLCouponsDAO.getInstance().getCoupon(edit_coupon));
					System.out.println("arriived coupon for edit: "+edit_coupon);
					dispatcher = getServletContext().getRequestDispatcher("/editcoupon.jsp");
					dispatcher.forward(request, response);
				}
					else {
						dispatcher = getServletContext().getRequestDispatcher("/adminentry.jsp");
						dispatcher.forward(request, response);
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
		doGet(request,response);
	}

}

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
			String name = request.getParameter("c_name");
			String desc = request.getParameter("c_des");
			int _id = Integer.parseInt(request.getParameter("c_id"));
			Date d = null;
			try {
				d = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(request.getParameter("expiry_date"));
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Coupon c1 = new Coupon(_id,name,desc,d);
			
			try {
				request.setAttribute("addcoupon", MySQLCouponsDAO.getInstance().addCoupon(c1));
				dispatcher = getServletContext().getRequestDispatcher("/addcoupon.jsp");
				dispatcher.forward(request, response);
			} catch (CouponException e) {
				// TODO Auto-generated catch block
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

package il.ac.shenkar.samples.controller;


import il.ac.shenkar.samples.model.Coupon;
import il.ac.shenkar.samples.model.CouponException;
import il.ac.shenkar.samples.model.InventoryException;
import il.ac.shenkar.samples.model.MySQLCouponsDAO;
import il.ac.shenkar.samples.model.ShoppingCart;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

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
		 */
		else if(path.endsWith("addcoupon"))
		{
			String name = request.getParameter("c_name");
			String desc = request.getParameter("c_des");
			int _id = Integer.parseInt(request.getParameter("c_id"));
			Coupon c1 = new Coupon(_id,name,desc);
			
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
		
		else if(path.endsWith("mycartentry")) {
	
			
			// Going to turn it to mycoupons.jsp page and show purchased coupons
						dispatcher = getServletContext().getRequestDispatcher("/mycoupons.jsp");
						dispatcher.forward(request, response);
			
		}
		
		
		/*
		else if(path.endsWith("getCookies"))
		{
			PrintWriter out = response.getWriter();
			Cookie[] vec = request.getCookies();
			//int l = vec.length;
			out.println("Counter: ");
			out.println(count);			
			out.flush();
			
		}
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

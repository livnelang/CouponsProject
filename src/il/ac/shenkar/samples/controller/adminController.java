package il.ac.shenkar.samples.controller;

import il.ac.shenkar.samples.listeners.SessionCounterListener;
import il.ac.shenkar.samples.model.Coupon;
import il.ac.shenkar.samples.model.CouponException;
import il.ac.shenkar.samples.model.MD5Manager;
import il.ac.shenkar.samples.model.MySQLCouponsDAO;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import org.apache.log4j.Logger;

/**
 * Servlet implementation class adminController
 */
//@WebServlet("/adminController/*")
public class adminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger logger = Logger.getLogger(adminController.class);  		  // Main Logger
	private boolean admincredit = false;

       			
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adminController() {
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
		
		
		/**
		 * After admin clicked add at drop down menu
		 * and got accepted - we will
		 * redirect him to addcoupon.jsp page
		 */
		if(path.endsWith("addpage"))
		{
			try {
				HttpSession session = request.getSession();
				// checks whether the admin is logged or not
				if ( (boolean) session.getAttribute("admin_log") ) {
					dispatcher = getServletContext().getRequestDispatcher("/addcoupon.jsp");
					dispatcher.forward(request, response);
				}
					else {
						dispatcher = getServletContext().getRequestDispatcher("/adminentry.jsp");
						dispatcher.include(request, response);
					}
					
				}
			catch (Exception e) {
				dispatcher = getServletContext().getRequestDispatcher("/404.jsp");
				dispatcher.forward(request, response);
				e.printStackTrace();
			}
			
		}
		
		
		/**
		 * If Admin clicked Log Out from his Control Panel
		 * well set admincredit to false,
		 * and the session variable also
		 * and redirect him to home page
		 */
		if(path.endsWith("logout"))
		{
			try {
				HttpSession session = request.getSession();
				if(admincredit) {
					admincredit = false;
					session.setAttribute("admin_log", false);
					dispatcher = getServletContext().getRequestDispatcher("/adminentry.jsp");
					dispatcher.forward(request, response);
					}
			}
				
			
				catch (Exception e) {
					dispatcher = getServletContext().getRequestDispatcher("/404-page.jsp");
					dispatcher.forward(request, response);
				}
		}
		
		/**
		 * Treat Edit request from admin 
		 */
		else if(path.endsWith("edit_coupon")) {
			try {
				HttpSession session = request.getSession();
				// checks whether the admin is logged or not
				if(admincredit){
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
		 * Delete Coupon Treat
		 * Delete by coupon Id
		 */
		else if(path.endsWith("deletecoupon"))
		{
			HttpSession session = request.getSession();
			try {
				// checks whether the admin is logged or not
				if(admincredit){
					int coupon_id = Integer.parseInt(request.getParameter("c_id"));
					if(MySQLCouponsDAO.getInstance().deleteCoupon(coupon_id)) {
						logger.info("Coupon id: "+coupon_id+" was deleted !");
					}
					dispatcher = getServletContext().getRequestDispatcher("/admin.jsp");
					dispatcher.forward(request, response);
				}
					else {
						dispatcher = getServletContext().getRequestDispatcher("/admin.jsp");
						dispatcher.forward(request, response);
					}
				}
			
			catch (CouponException e) {
				dispatcher = getServletContext().getRequestDispatcher("/404-page.jsp");
				dispatcher.forward(request, response);
				e.printStackTrace();
			}
			
			catch (Exception e) {
				dispatcher = getServletContext().getRequestDispatcher("/404-page.jsp");
				dispatcher.forward(request, response);
				e.printStackTrace();
			}
			
			
		}
		
		
		/**
		 * If Admin want to see All Active Sessions
		 * Then redirect him to sessions.jsp page
		 */
		if(path.endsWith("activesessions"))
		{
			try {
				// checks whether the admin is logged or not
				if(admincredit){
				HttpSession session = request.getSession();
				// Gets All Http Sessions & send back to request
				
				SessionCounterListener sl = new SessionCounterListener();
				
				request.setAttribute("sessions", SessionCounterListener.getActivesessions());
				System.out.print("after setting sessions");
				dispatcher = getServletContext().getRequestDispatcher("/sessions.jsp");
				dispatcher.include(request, response);
				}
					else {
						dispatcher = getServletContext().getRequestDispatcher("/adminentry.jsp");
						dispatcher.forward(request, response);
					}
					
				}
			catch (Exception e) {
				dispatcher = getServletContext().getRequestDispatcher("/404.jsp");
				dispatcher.forward(request, response);
				e.printStackTrace();
			}
			
		}
		
	}

	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getPathInfo();
		RequestDispatcher dispatcher = null;
		
		/**
		 * Take care of Admin attempt to enter the system
		 * If Details Are Correct -> put True value in 'admincredit' field
		 */
		if(path.endsWith("login_request")) {
			
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
					// set the request page a false variable attempt
					// & redirect to try again
					logger.debug("user has tried to log as admin !");
					request.setAttribute("login_failed", -1);
					dispatcher = getServletContext().getRequestDispatcher("/adminentry.jsp");
					dispatcher.forward(request, response);
				}
			
			// if return positive --> then redirect to AdminPage
			if(admincredit) {
				logger.debug(new Date().toString());
				logger.debug("admin has logged in !");
				HttpSession session = request.getSession();
				// set administration credit with true boolean variable
				session.setAttribute("admin_log", true);
				dispatcher = getServletContext().getRequestDispatcher("/admin.jsp");
				dispatcher.include(request, response);
				}
			
			// if return false --> then redirect to errorPage
			else {
				// set the request page a false variable attempt
				// & redirect to try again
				logger.debug("user has tried to log as admin !");
				request.setAttribute("login_failed", -1);
				dispatcher = getServletContext().getRequestDispatcher("/adminentry.jsp");
				dispatcher.forward(request, response);
			}
			
			}
			catch (Exception e) {
				// set the request page a false variable attempt
				// & redirect to try again
				request.setAttribute("login_failed", -1);
				e.printStackTrace();
				System.out.println(e.toString());
				dispatcher = getServletContext().getRequestDispatcher("/adminentry.jsp");
				dispatcher.include(request, response);
			}
			
			
			/*dispatcher = getServletContext().getRequestDispatcher("/mycoupons.jsp");
			dispatcher.forward(request, response);*/
			
		}
		
		/**
		 * Admin addCoupon page
		 * gets all input parameters
		 * and set new Coupon on Data Base
		 */
		else if(path.endsWith("addcoupon"))
		{
			Coupon c1=null;
			try {
				// checks whether the admin is logged or not
				if ( !admincredit ) {
					dispatcher = getServletContext().getRequestDispatcher("/adminentry.jsp");
					dispatcher.include(request, response);
				}	
				int _id = Integer.parseInt(request.getParameter("c_id"));
				String name = request.getParameter("c_name");
				String desc = request.getParameter("c_des");
				String catg = request.getParameter("c_cat");
				int ltude =   Integer.parseInt(request.getParameter("c_ltude"));
				int latude =  Integer.parseInt(request.getParameter("c_latude"));
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				Date d = simpleDateFormat.parse(request.getParameter("exp_date"));
				c1 = new Coupon(_id,name,desc,catg,ltude,latude,d);
				
				request.setAttribute("addcoupon", MySQLCouponsDAO.getInstance().addCoupon(c1));
				if( (boolean) request.getAttribute("addcoupon")) {
					logger.info("Coupon id: "+request.getParameter("c_id")+" was added !");
				}
				dispatcher = getServletContext().getRequestDispatcher("/admin.jsp");
				dispatcher.include(request, response);
			}
			
			catch (ParseException e1) {
				// TODO Auto-generated catch block
				logger.info("Problem With New Coupon variables: "+e1.toString());
				dispatcher = getServletContext().getRequestDispatcher("/404-page.jsp");
				dispatcher.forward(request, response);
			}
			

			 catch (CouponException e) {
				logger.info("Problem With Adding A New Coupon");
				dispatcher = getServletContext().getRequestDispatcher("/404-page.jsp");
				dispatcher.forward(request, response);
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
				// checks whether the admin is logged or not
				if ( !admincredit ) {
					dispatcher = getServletContext().getRequestDispatcher("/adminentry.jsp");
					dispatcher.include(request, response);
				}	
				int _id =  (int) session.getAttribute("coupon_for_edit");
				String name = request.getParameter("c_name");
				String desc = request.getParameter("c_des");
				String catg = request.getParameter("c_cat");
				int ltude =   Integer.parseInt(request.getParameter("c_ltude"));
				int latude =  Integer.parseInt(request.getParameter("c_latude"));
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				Date d = simpleDateFormat.parse(request.getParameter("exp_date"));
				c1 = new Coupon(_id,name,desc,catg,ltude,latude,d);
			}
			catch (ParseException e1) {
				dispatcher = getServletContext().getRequestDispatcher("/404-page.jsp");
				dispatcher.forward(request, response);
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
				dispatcher = getServletContext().getRequestDispatcher("/404-page.jsp");
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

}

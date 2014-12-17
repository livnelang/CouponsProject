package il.ac.shenkar.samples.controller;


import il.ac.shenkar.samples.model.Coupon;
import il.ac.shenkar.samples.model.CouponException;
import il.ac.shenkar.samples.model.InventoryException;
import il.ac.shenkar.samples.model.MySQLCouponsDAO;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getPathInfo();
		RequestDispatcher dispatcher = null;
		if(client_name==null) {
			client_name = request.getCookies()[0].getName();
		}
		count++;
		
		
		
		if(path.endsWith("coupons"))
		{
			
			try {
				request.setAttribute("coupons", MySQLCouponsDAO.getInstance().getCoupons());
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
		
		else if(path.endsWith("getCookies"))
		{
			PrintWriter out = response.getWriter();
			Cookie[] vec = request.getCookies();
			//int l = vec.length;
			out.println("Counter: ");
			out.println(count);			
			out.flush();
			
		}
		
		

		
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

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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InventoryController
 */
@WebServlet("/controller/*")
public class InventoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		
		else if(path.endsWith("addproduct"))
		{
			String name = request.getParameter("c_name");
			String desc = request.getParameter("c_des");
			int _id = Integer.parseInt(request.getParameter("c_id"));
			Coupon c1 = new Coupon(_id,name,desc);
			
			try {
				request.setAttribute("addcoupon", MySQLCouponsDAO.getInstance().addCoupon(c1));
			} catch (CouponException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dispatcher = getServletContext().getRequestDispatcher("/formentry.jsp");
			dispatcher.forward(request, response);
		}
		
	/*	else if(path.endsWith("formentry")) {
			dispatcher = getServletContext().getRequestDispatcher("/formentry.jsp");
		}*/
		
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

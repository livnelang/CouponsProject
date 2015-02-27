package il.ac.shenkar.samples.model;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

/**
 * Servlet Filter implementation class ResponseTimerFilter
 * Will Calculate controller response time
 */
public class ResponseTimerFilter implements Filter {
	  protected FilterConfig config;
	  RequestDispatcher dispatcher = null;
	  
	  
	  public void init(FilterConfig config) throws ServletException {
	    this.config = config;
	  }

	  public void destroy() {
	  }
	  
	  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	      throws ServletException, IOException {
		  String path = ((HttpServletRequest) request).getPathInfo();
		  PrintWriter out = response.getWriter();
		  out.write("<!DOCTYPE html>"+
		  "<html lang=\"en\">"+
		  "<head>"+
		    "<meta charset=\"utf-8\">"+
		    "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">"+
		    "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">"+
		    "<title>Coupons Project</title>"+

		    "<!-- Bootstrap-CSS & General CSS -->"+
		   "<link rel=\"stylesheet\" type=\"text/css\" href=\"../css/bootstrap.css\">"+
		    "<link rel=\"stylesheet\" type=\"text/css\" href=\"../css/style.css\">"+
		    "<script src=\"../js/jquery-2.1.1.min.js\"></script>"+
		    "<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js\"></script>"+
		    "<script src=\"../js/admin_js.js\"></script>"+
		    "</head>");

	    long startTime = System.currentTimeMillis();
	    chain.doFilter(request, response);	    
	    long elapsed = System.currentTimeMillis() - startTime;
	    out.write("<div class=\"col-xs-2 alert alert-dismissable alert-info col\">"+
	  "<strong>Response time: "+elapsed+" ms</strong> <br> </div>");
	    out.flush();
	  }
	}

package il.ac.shenkar.samples.model;

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
	    long startTime = System.currentTimeMillis();
	    chain.doFilter(request, response);
	    long elapsed = System.currentTimeMillis() - startTime;
	    String name = "servlet";
	    if (request instanceof HttpServletRequest) {
	      name = ((HttpServletRequest) request).getRequestURI();
	    }
	    
	    // Send to Request the time controller had to response
	    request.setAttribute("ctime", elapsed);
	    ((RequestDispatcher) request).forward(request, response);
	    System.out.println(request.toString());
	    config.getServletContext().setAttribute("c_time", elapsed);
	    config.getServletContext().log(name + " took " + elapsed + " ms");
	  }
	}

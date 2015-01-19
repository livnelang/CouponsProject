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
		  CharResponseWrapper wrapper = new CharResponseWrapper(
		  (HttpServletResponse)response);  
	    long startTime = System.currentTimeMillis();
	    chain.doFilter(request, response);	    
	    long elapsed = System.currentTimeMillis() - startTime;
	    
	    CharArrayWriter caw = new CharArrayWriter();
	    caw.write("<div class=\"col-xs-2 alert alert-dismissable alert-info col\">"+
	    		  "<strong>Response time: "+elapsed+" ms</strong> <br> </div>");
	    response.setContentLength(caw.toString().getBytes().length);
	    out = wrapper.getWriter(); 
		//out.write("hello");
	    //out.println(wrapper.toString());
	   // out.println("Time Elapsed: "+elapsed + "ms");
	    System.out.println("time elapsed: "+elapsed);
	    out.write("<div class=\"col-xs-2 alert alert-dismissable alert-info col\">"+
	  "<strong>Response time: "+elapsed+" ms</strong> <br> </div>");
	  
	    out.flush();
	  }
	}

package il.ac.shenkar.samples.model;

import java.io.CharArrayWriter;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * ResponseWrapper Class,
 * uses to keep the original response alive
 * use especially with Filters. 
 * @author Livne
 *
 */
public class CharResponseWrapper extends
HttpServletResponseWrapper {
	
private CharArrayWriter output;

public CharResponseWrapper(HttpServletResponse response){
	   super(response);
	   output = new CharArrayWriter();
	}
public String toString() {
   return output.toString();
}

public PrintWriter getWriter(){
   return new PrintWriter(output);
	}
}
package il.ac.shenkar.samples.model;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;


public class SessionsTag extends SimpleTagSupport {
		
	private HashMap<String, HttpSession> sessions;
	
	public void setSessions(HashMap<String, HttpSession> values)
	{
		sessions = values;
		System.out.println("values size: " +values.size());

	}

	public void doTag() throws JspException, IOException
	{
		
		JspWriter out = getJspContext().getOut();
		
		System.out.println("inside SessionsTag");

			Iterator iterator = sessions.entrySet().iterator();
			System.out.println("sessions size: " +sessions.size());
			
			for(Map.Entry<String, HttpSession> entry : sessions.entrySet() )
			{
				System.out.println("sessions size: " +sessions.size());
				HttpSession current_session = entry.getValue();
				ArrayList<String> attributes = new ArrayList<String>();
				//attributes = (ArrayList<String>) current_session.getAttributeNames().;
				out.print(
			// print only the session Id
			"<tr>"+
			"<th style=\"color:red;\" class=\"col-sm-1\">Session Id</th> <th style=\"color:red;\" class=\"col-sm-1\">"	+entry.getKey()+"</th>"+
			"</tr>"+
			"<tr> <th class=\"col-sm-1\">Attribute Name</th> <th class=\"col-sm-1\">Attribute Value</th> </tr>"
			);
			// Now, print all session attributes
			String att;
			for(Object o : Collections.list(current_session.getAttributeNames() )) {
				att = current_session.getAttributeNames().nextElement();
				out.print(
			"<tr>"+ 
			"<td>"+o.toString()+"</td><td>"+current_session.getAttribute(o.toString().toString())+"</td>"+
			"</tr>");
				
				
				}
			}		
	}

}
	
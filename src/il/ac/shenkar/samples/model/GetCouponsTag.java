package il.ac.shenkar.samples.model;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class GetCouponsTag extends SimpleTagSupport {
	
	private Collection<Coupon> coupons;
	public void setCoupons(Collection<Coupon> cVal)
	{
		coupons = cVal;
	}
	
	public void doTag() throws JspException, IOException
	{
		
		JspWriter out = getJspContext().getOut();

			Iterator iterator = coupons.iterator();
			while(iterator.hasNext())
			{
				Coupon coupon = (Coupon)iterator.next();
				out.print(
	    		
			"<tr>"+
			"<td>"+coupon.getId()+"</td>"+
			"<td>"+coupon.getName()+"</td>"+
			"<td>"+coupon.getDescription()+"</td>"+
			"<td>"+coupon.getCategory()+"</td>"+
			"<td>"+coupon.getLongitude()+"</td>"+
			"<td>"+coupon.getLatitude()+"</td>"+
			"<td>"+coupon.getDate()+"</td>"+
			"<td><a href=/CouponsProject/controller/mycart?c_id="+coupon.getId()+">Add To Cart</a></td>"+
			"</tr>");
			}		
	}

}
	
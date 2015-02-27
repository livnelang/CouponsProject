package il.ac.shenkar.samples.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

/**
 * Created by Livne Lang & Yaron Israeli
 */

/**
 * DateManager Class
 * uses to compare dates
 * matching between coupon date
 * and system time date
 * @author Livne
 *
 */
public class DateManager {
	
	/**
	 * DateManager Constructor
	 * No arguments
	 */
	public DateManager() { };
	
	
	/**
	 * validatedCoupons Function
	 * Helps us to get the valide coupons only
	 * @param cpns
	 * @return Collection<Coupon>
	 * @throws CouponException
	 */
	public ArrayList<Coupon> validatedCoupons(Collection<Coupon> cpns) throws CouponException 
    {
		// updated array to return
		ArrayList<Coupon> coupon_array = new ArrayList<Coupon>();
		// gets current timestamp from system
		String currentimeStamp = new SimpleDateFormat("yyyy-mm-dd HH:mm").format(Calendar.getInstance().getTime());
		// A timestamp format to compare
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm");
		// Global CurrentDate var
		Date currentdate = null;
		// will be our current date
		for (Coupon coupon : cpns) {
			// current time
			currentdate =  new Date();
			// now ready to compare
			boolean r = coupon.getDate().after(currentdate);
			if( r   ) {
				coupon_array.add(coupon);
			}
		}
		
		
		
		return coupon_array;
    }

}

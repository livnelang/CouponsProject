package il.ac.shenkar.samples.model;
import java.util.*;

/**
 * Created by Livne Lang & Yaron Israeli
 */

/**
 * Coupons Data Access Object
 * represents the DAO Interface for coupon actions & 
 * Management
 * @author Livne
 *
 */
public interface ICouponsDAO
{
	/**
	 * Add a coupon to our database
	 * Admin Action
	 * @param coupon
	 * @return
	 * @throws CouponException
	 */
    public boolean addCoupon(Coupon coupon) throws CouponException;
    
    /**
	 * Make a collection of coupons
	 * from database
	 * @param coupon
	 * @return
	 * @throws CouponException
	 */
    public Collection<Coupon> getCoupons() throws CouponException;
    
    /**
     * Bring back a coupon object
     * search by Id
     * @param id
     * @return
     * @throws CouponException
     */
    public Coupon getCoupon(int id) throws CouponException;
    
    /**
     * Delete a Coupon from database
     * Admin Action
     * @param id
     * @return
     * @throws CouponException
     */
    public boolean deleteCoupon(int id) throws CouponException;
    
    /**
     * Edit/Update specific Coupon
     * Admin Action
     * @param coupon
     * @return
     * @throws CouponException
     */
    public boolean updateCoupon(Coupon coupon) throws CouponException;
    
    
}

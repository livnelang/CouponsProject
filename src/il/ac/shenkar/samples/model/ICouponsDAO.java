package il.ac.shenkar.samples.model;
import java.util.*;

/**
 * Created by Livne Lang
 */
public interface ICouponsDAO
{
    public boolean addCoupon(Coupon coupon) throws CouponException;
    public Collection<Coupon> getCoupons() throws CouponException;
    public Coupon getCoupon(int id) throws CouponException;
    
    public boolean deleteCoupon(int id) throws CouponException;
    public boolean updateCoupon(Coupon coupon) throws CouponException;
    
    
}

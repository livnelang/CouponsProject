package il.ac.shenkar.samples.model;


public class Main {

    public static void main(String[] args) {
	    ICouponsDAO dao = MySQLCouponsDAO.getInstance();
        try
        {
            /*dao.addCoupon(new Coupon(12543,"hit las falafelia","get 2 falalfes for 10shekels"));
            dao.addCoupon(new Coupon(12544,"shenkar gaga pizzzza","get 2 pizpaz for 1"));
            dao.addCoupon(new Coupon(12545,"lam lam","buy 1 shake for 1 ice cream"));*/
        	
          //  dao.addCoupon(new Coupon(12544,"Here","We Go"));
        	//	dao.getCoupon(12545);
        	 //   dao.updateCoupon(new Coupon(12545, "this is ","sparta"));   // Executes updateCoupon(Coupon)

         //   System.out.println(dao.getCoupons());   			 	 		  // Executes getCoupons()
            boolean removed_coupon = dao.deleteCoupon(12546);
            
        }
        catch(CouponException e)
        {
            e.printStackTrace();
        }
    }
}

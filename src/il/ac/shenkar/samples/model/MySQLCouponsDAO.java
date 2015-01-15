package il.ac.shenkar.samples.model;

import il.ac.shenkar.samples.model.MySQLCouponsDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

//import javax.persistence.Query;







import java.util.Map;

import javax.transaction.SystemException;
import javax.transaction.Transaction;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;


/**
 * Created by Livne Lang
 */
public class MySQLCouponsDAO implements ICouponsDAO
{
	private Map<Integer,Coupon> coupons;

	
    static
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    private static MySQLCouponsDAO instance;
    
    /**
     * Singleton Implementation
     * @return instance
     */
    public static MySQLCouponsDAO getInstance()
    {
        if(instance==null)
        {
            instance = new MySQLCouponsDAO();
        }
        return instance;
    }

    private MySQLCouponsDAO() {
    	coupons = new HashMap<Integer, Coupon>();
    }

    /**
     * Hibernate getCoupons
     */
    public Collection<Coupon> getCoupons() throws CouponException 
    {
    			ArrayList<Coupon> coupon_array = new ArrayList<Coupon>();
    			//creating factory for getting sessions
    			SessionFactory factory = new AnnotationConfiguration().configure().buildSessionFactory();
   
    			//creating a new session for getting all products
    			Session anotherSession = factory.openSession();
    			anotherSession.beginTransaction();
    			List<Coupon> products = anotherSession.createQuery("from Coupon").list();
    			System.out.println("There are " + products.size() + " product(s)");
    			Iterator i = products.iterator();
    			while(i.hasNext()) 
    			{
    				//System.out.println(i.next());
    				Coupon c = (Coupon) i.next();
    				coupon_array.add(c);
    				coupons.put(c.getId(),c);
    			}
    			anotherSession.close();	
    			return coupon_array;
    }
    
    
    /**
     * Hibernate addCoupon(Coupon ob)
     */
    public boolean addCoupon(Coupon ob) throws CouponException
    {
    		//creating factory for getting sessions
    		SessionFactory factory = new AnnotationConfiguration().configure().buildSessionFactory();
    		//creating a new session for getting all products
    		Session anotherSession = factory.openSession();
    		anotherSession.beginTransaction();
        try
        {
        	anotherSession.save(ob);
        	anotherSession.getTransaction().commit();
        	anotherSession.close();
            
        }
        catch(HibernateException e)
        {
        	
            e.printStackTrace();
           // throw new CouponException("problem with adding a coupon");
            return false;
        }
       
        return true;
    }
    
    /**
     * Hibernate getCoupon(int id)
     */
    public Coupon getCoupon(int id) throws CouponException
    {
    	Coupon c1=null;
    	//creating factory for getting sessions
		SessionFactory factory = new AnnotationConfiguration().configure().buildSessionFactory();
		//creating a new session for getting all products
		Session anotherSession = factory.openSession();
		anotherSession.beginTransaction();
        
        try
        {
        	c1 = (Coupon) anotherSession.get(Coupon.class, id);
        	    
        }

        catch(HibernateException e)
        {
            e.printStackTrace();
            throw new CouponException("problem with getting a coupon"); 
        }
        
        return c1;


    }
    
    
    /**
     * Hibernate deleteCoupon(int id)
     */
	public boolean deleteCoupon(int id) throws CouponException
    {

		int succeed = 0;
		
		//creating factory for getting sessions
		SessionFactory factory = new AnnotationConfiguration().configure().buildSessionFactory();
		//creating a new session for getting all products
		Session anotherSession = factory.openSession();
		anotherSession.beginTransaction();
		Coupon c2 = null;
		
			try
			{
				//Retrieve detailed Coupon
				c2 = (Coupon) anotherSession.get(Coupon.class, id);
				//Add delete to session
				anotherSession.delete(c2);
				// Commit The Session
				anotherSession.getTransaction().commit();
				
			}
			catch (HibernateException e)
			{
				e.printStackTrace();
				System.out.println(e.getMessage());
				
				Transaction tx = (Transaction) anotherSession.getTransaction();
				if (((org.hibernate.Transaction) tx).isActive())
					try {
						tx.rollback();
					} catch (IllegalStateException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SystemException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
			finally
			{
				if (c2 == null)
				{
					return false;
				}
				if(anotherSession!=null) anotherSession.close();
			}

		// Check whether item deleted or not
		if(succeed==1)
		{
			return true;
		}
		
		else
		{ 
			return false;
		}

    }
    
    
	/**
     * Hibernate updateCoupon(Coupon coupon)
     */
    public boolean updateCoupon(Coupon coupon) throws CouponException
    {
    	//creating factory for getting sessions
    	SessionFactory factory = new AnnotationConfiguration().configure().buildSessionFactory();
    	//creating a new session for getting all products
    	Session anotherSession = factory.openSession();
    	anotherSession.beginTransaction();
    	    
        try
        {
        	anotherSession.update(coupon);
        	anotherSession.getTransaction().commit();
               
        }
    	
        catch(Exception e)
        {
        	
            e.printStackTrace();
            return false;
        }
        
        return true;

    }
    
    /**
     * Public Function to return all coupon categories
     * @return ArrayList<String> -- ARRAY OF CATEGORIES
     * @throws CouponException 
     */
    public ArrayList<String> getCategories() throws CouponException{
    	ArrayList<String> cats = new ArrayList<String>();
    	ArrayList<Coupon> coupon_array = (ArrayList<Coupon>) this.getCoupons();
    	for (Coupon coupon : coupon_array) {
    		// checks whether certain category already in the new List
			if (cats.contains(coupon.getCategory())) {
				continue;
			}
				else {
					cats.add(coupon.getCategory());
				}
		}
    	// return the categories array
    	return cats;
    }
}

package il.ac.shenkar.samples.model;

import il.ac.shenkar.samples.controller.InventoryController;

import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

public class ShoppingCart {
	private Map<Integer,ShoppingCartLine> lines = new Hashtable<Integer,ShoppingCartLine>();
	static Logger logger = Logger.getLogger(ShoppingCart.class); 

	/**
	 * ShoppingCart Constructor
	 * @param lines
	 */
	public ShoppingCart() 
	{
		super();
	}
	
	/**
	 * Add Another purchase line
	 * @param s
	 */
	public void addShoppingCartLine(Coupon c)
	{
		if(lines.containsKey(c.getId())) {
			//a shopping cart line for this product already exists
			System.out.println("Coupon already added");
			ShoppingCartLine line = lines.get(c.getId());
			line.setAmount(line.getAmount()+1);			
		}
		else {
			//we need to create a new shopping cart line
			System.out.println("New Coupon added");
			
			ShoppingCartLine line = new ShoppingCartLine(c, 1);
			lines.put(c.getId(), line);
			System.out.println(lines.size());
		}
	}
	
	/**
	 * return all coupons lines
	 * @return
	 */
	public Map<Integer,ShoppingCartLine> getLines() {
		return this.lines;
	}
	
	/**
	 * Cart public Function to check
	 * which Coupons update and which off date,
	 * coupon thats off-date will be removed from cart
	 */
	public void update_lines() {
		for(Map.Entry<Integer, ShoppingCartLine> entry : lines.entrySet() ) {
			// Get the Coupon from current line
			Coupon c1 = entry.getValue().getC1();
			// set current time
			Date currentdate = new Date();
		   // compare dates
			boolean r = c1.getDate().after(currentdate);
			// if coupon is offdate - remove from lines
			if( !r   ) {
				lines.remove(c1.getId());
				logger.info("Coupon Id: "+c1.getName()+" is Off-Date and removed from cart ");
			}
		}
	}

	@Override
	public String toString() {
		return "ShoppingCart [lines=" + lines.toString() + "]";
	}
	


	

}

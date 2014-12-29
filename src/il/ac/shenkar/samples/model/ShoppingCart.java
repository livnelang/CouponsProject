package il.ac.shenkar.samples.model;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class ShoppingCart {
	private Map<Integer,ShoppingCartLine> lines = new Hashtable<Integer,ShoppingCartLine>();

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
	 * Total Purchased lines
	 * @return
	 */

	

}

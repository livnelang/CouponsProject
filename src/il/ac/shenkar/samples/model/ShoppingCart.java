package il.ac.shenkar.samples.model;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
	private Map<Coupon,ShoppingCartLine> lines;

	/**
	 * ShoppingCart Constructor
	 * @param lines
	 */
	public ShoppingCart() 
	{
		super();
		this.lines = new HashMap<Coupon, ShoppingCartLine>();
	}
	
	/**
	 * Add Another purchase line
	 * @param s
	 */
	public void addShoppingCartLine(Coupon c)
	{
		if(lines.containsKey(c)) {
			//a shopping cart line for this product already exists
			ShoppingCartLine line = lines.get(c);
			line.setAmount(line.getAmount()+1);			
		}
		else {
			//we need to create a new shopping cart line
			ShoppingCartLine line = new ShoppingCartLine(c, 1);
			lines.put(c, line);
		}
	}
	
	/**
	 * return all coupons lines
	 * @return
	 */
	public Map<Coupon,ShoppingCartLine> getLines() {
		return this.lines;
	}
	
	/**
	 * Total Purchased lines
	 * @return
	 */

	

}

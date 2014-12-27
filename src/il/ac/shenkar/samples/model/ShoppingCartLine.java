package il.ac.shenkar.samples.model;

public class ShoppingCartLine {
	private Coupon c1;
	private int amount;
	
	/**
	 * ShoppingCartLine Constructor
	 * @param c1
	 * @param amount
	 */
	public ShoppingCartLine(Coupon c1, int amount) {
		super();
		this.setCoupon(c1);
		this.setAmount(amount);
	}
	
	public String getProductName()
	{
		return c1.getName();
	}
	
	public Coupon getC1() {
		return c1;
	}

	public void setCoupon(Coupon c1) {
		this.c1 = c1;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getAmount()
	{
		return this.amount;
	}
	

	
	

}

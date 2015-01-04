package il.ac.shenkar.samples.model;

import java.util.Date;

/**
 * Created by Livne Lang
 */

/**
 * Coupon class
 * represents the coupon object with id, name,
 * description & expiration date
 * @author Livne
 *
 */
public class Coupon
{
    private int id;
    private String name;
    private String description;
    private Date date;
    
    /**
     * Coupon Default Constructor
     */
    public Coupon() 
    {
    	
    }
    
    /**
     * Coupon constructor
     * @param id
     * @param name
     * @param description
     * @param date
     */
    public Coupon(int id, String name, String description,Date date)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
    }
    
    /**
     * Id Getter
     * returns Coupon Id
     * @return
     */
    public int getId()
    {
        return id;
    }
    
    /**
     * Name Getter
     * returns Coupon Name
     * @return
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Id Setter
     * Set Coupon Id
     * @return
     */
    public void setId(int id) {
		this.id = id;
	}
    
    /**
     * Name Setter
     * Set Coupon Name
     * @return
     */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
     * Description Setter
     * Set Coupon Description
     * @return
     */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
     * Description Getter
     * returns Coupon Description
     * @return
     */
	public String getDescription()
    {
        return description;
    }
	
	/**
     * Date Getter
     * returns Coupon Date
     * @return
     */
	public Date getDate() {
		return date;
	}
	
	/**
     * Date Setter
     * Set Coupon Date
     * @return
     */
	public void setDate(Date date) {
		this.date = date;
	}
	
	@Override
	public String toString() 
	{
		return "Coupon [id=" + id + ", name=" + name + ", description="
				+ description + " , expiration=" + date + "]";
	}
    
}

package il.ac.shenkar.samples.model;

import java.util.Date;

/**
 * Created by Livne Lang
 */

/**
 * Coupon class
 * represents the coupon object with id, name,
 * description, longitude, latitude &
 * & expiration date
 * @author Livne
 *
 */
public class Coupon
{
    private int id;
    private String name;
    private String description;
    private String category;
    private int longitude;
    private int latitude;
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
     * @param category
     * @param longitude
     * @param latitude
     * @param date
     */
    public Coupon(int id, String name, String description,String category, int longitude,int latitude,Date date)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.date = date;
        this.longitude = longitude;
        this.latitude = latitude;
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
     * @return Date
     */
	public Date getDate() {
		
		return date;
	}
	
	/**
     * Date Setter
     * Set Coupon Date
     */
	public void setDate(Date date) {
		System.out.println(date.toString());
		this.date = date;
	}
	
	
	/**
     * Category Getter
     * returns Coupon Category
     * @return String
     */
	public String getCategory() {
		return category;
	}
	
	/**
     * Category Setter
     * Set Coupon Category
     * @return void
     */
	public void setCategory(String category) {
		this.category = category;
	}
	
	/**
     * Longitude Getter
     * returns Coupon Longitude
     * @return int
     */
	public int getLongitude() {
		return longitude;
	}

	/**
     * Longitude Setter
     * Set Coupon Longitude
     * @return void
     */
	public void setLongitude(int longitude) {
		this.longitude = longitude;
	}
	
	/**
     * Latitude Getter
     * returns Coupon Latitude
     * @return int
     */
	public int getLatitude() {
		return latitude;
	}
	
	/**
     * Latitude Setter
     * Set Coupon Latitude
     * @return void
     */
	public void setLatitude(int latitude) {
		this.latitude = latitude;
	}

	@Override
	public String toString() 
	{
		return "Coupon [id=" + id + ", name=" + name + ", description="
				+ description + " , expiration=" + date + "]";
	}
    
}

package il.ac.shenkar.samples.model;

/**
 * Created by Livne Lang
 */
public class Coupon
{
    private int id;
    private String name;
    private String description;
    
    public Coupon() 
    {
    	
    }
    
    public Coupon(int id, String name, String description)
    {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    public int getId()
    {
        return id;
    }
    public String getName()
    {
        return name;
    }
    public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescription()
    {
        return description;
    }
	@Override
	public String toString() 
	{
		return "Coupon [id=" + id + ", name=" + name + ", description="
				+ description + "]";
	}
    
}

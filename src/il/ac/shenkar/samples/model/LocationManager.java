package il.ac.shenkar.samples.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * LocationManager Class
 * Uses us to compute distance between client
 * coordinates & to Coupon coordinates
 * @author Livne
 *
 */
public class LocationManager {
	private List<Double> distance_array=null;
	
	/**
	 * List<Double> distances getter
	 * @return
	 */
	public List<Double> getDistance_array() {
		return distance_array;
	}
	
	/**
	 * List<Double> distances setter
	 */
	public void setDistance_array(List<Double> distance_array) {
		this.distance_array = distance_array;
	}

	/**
	 * LocationManager Constructor
	 * No arguments
	 */
	public LocationManager() {};
	
	/**
	 * Compute_Distance Function
	 * gets a collection of coupons 
	 * and calculate their distance,
	 * put back in the map and pass it on to the Sort Function
	 * brings back sorted Collection
	 * @param cpns
	 * @param lng
	 * @param lat
	 */
	public Collection<Coupon> Compute_Distance(Collection<Coupon> cpns, float lng, float lat) {
		// map that collect coupons by <coupon.id,distance>
		HashMap<Double,Coupon> dist_coupon_map = new HashMap<Double,Coupon>();
		float distance = 0;
		for (Coupon coupon : cpns) {
			dist_coupon_map.put(getDistance(lng, coupon.getLongitude(), lat, coupon.getLatitude()),coupon);	
		}
		
		return SortCoupons(dist_coupon_map);
		
	}
	
	 /**
	  * SortCoupons Function 
	  * This function will sort our array by the shortest to 
	  * longest distance coupons from the client
	  * and return him sorted array
	  * @param cpns
	  * @return
	  */
	@SuppressWarnings("unchecked")
	public Collection<Coupon> SortCoupons(HashMap<Double,Coupon> dist_coupon_map) {
		// updated array to return
		ArrayList<Coupon> coupon_array = new ArrayList<Coupon>();
		// a collection of distance 
		List<Double> disted = new ArrayList<Double>(dist_coupon_map.keySet());
		// iterate over the collection and make bubble sort
		// use a Coparator
		Comparator<Double> comparator = new Comparator<Double>() {
			@Override
			public int compare(Double o1, Double o2) {
				if (o1 > o2) return 1;
				if (o1 < o2) return -1;
				return 0;
			}
		};
		// now well get a sorted List
		Collections.sort(disted, comparator); // use the comparator as much as u want
		// iterate it according to the map, and insert by increasing order the new array
		for(Double d : disted) {
			coupon_array.add(dist_coupon_map.get(d));
		}
		// set our private array - for returning sorted calculations 
		this.setDistance_array(disted);
		// coupon_array is sorted
		return coupon_array;
	}
	
	/**
	 * getDistance Function 
	 * Calculate 2 Points Distance
	 * @param x2
	 * @param x1
	 * @param y2
	 * @param y1
	 * @return Double number
	 */
	public double getDistance(float x2,float x1, float y2, float y1) {
		double d = 0;
		d = ( (Math.pow((x2 - x1),2)) + (Math.pow((y2 - y1),2)) );
		d = Math.sqrt(d);
		return d;
	}
	

}

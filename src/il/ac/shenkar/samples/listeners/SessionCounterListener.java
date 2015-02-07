package il.ac.shenkar.samples.listeners;
import il.ac.shenkar.samples.model.Coupon;
import il.ac.shenkar.samples.model.MySQLCouponsDAO;

import java.util.HashMap;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Session Counter Class
 * Counts How Many Session Are In Our Web App
 * @author Livne
 *
 */
public class SessionCounterListener implements HttpSessionListener {
	
	//private static SessionCounterListener instance;
	private static int totalActiveSessions;
	private static HashMap<String, HttpSession> activesessions = new HashMap<String, HttpSession>();
	
	
	public SessionCounterListener() { }
	
	 
	 
	  
	  public static HashMap<String, HttpSession> getActivesessions() {
		return activesessions;
	}

	public static void setActivesessions(HashMap<String, HttpSession> activesessions) {
		SessionCounterListener.activesessions = activesessions;
	}

	/**
	   * Sessions Count Getter
	   * @return
	   */
	  public static int getTotalActiveSession(){
		return totalActiveSessions;
	  }
	 
	  /**
	   * This Function will help us count the number 
	   * of active sessions & Adding them to our map
	   */
	  @Override
	  public void sessionCreated(HttpSessionEvent incoming_session) {
		totalActiveSessions++;
		activesessions.put(incoming_session.getSession().getId(), incoming_session.getSession());
		System.out.println("sessionCreated - add one session into counter");
	  }
	 
	  /**
	   * Function to lower by 1 the number of sessions,
	   * and erase session from the session map
	   */
	  @Override
	  public void sessionDestroyed(HttpSessionEvent arg0) {
		totalActiveSessions--;
		activesessions.remove(arg0.getSession().getId());
		System.out.println("sessionDestroyed - deduct one session from counter");
	  }	
	}
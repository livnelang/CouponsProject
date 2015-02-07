package il.ac.shenkar.samples.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Represents the MD5 Logic Controller
 * using for getting DB pass
 * Hashing password
 * Equation between passwords
 * @author Livne
 *
 */
public class MD5Manager {
	
	/**
	 * MD5 Default Constructor
	 */
	public MD5Manager(){
		
	}
	
	/**
	 * extract the admin password from users TABLE 
	 * That lies in our DB
	 */
	@SuppressWarnings({ "resource", "finally" })
	public String getAdminPass() {
		Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        String name = null;
        String hashed_password = null;

        try
        {
            //create connection
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/LivneDataBase",
                    "john",
                    "deer");

            //add the coupon to the coupons table
            statement = connection.createStatement();
            String text = "SELECT username, password FROM users";
            System.out.println("text="+text);
            rs = statement.executeQuery(text);
            while(rs.next())
            {
            	if( rs.getString("username").equals("admin") ) {
            		return rs.getString("password");
            	}
            }
            return null;
        }
        catch(SQLException e)
        {

            e.printStackTrace();
        }
        finally
        {
            if(rs!=null)
            {
                try
                {
                    rs.close();
                }
                catch(SQLException e) {}
            }
            if(statement!=null)
            {
                try
                {
                    statement.close();
                }
                catch(SQLException e) {}
            }
            if(connection!=null)
            {
                try
                {
                    connection.close();
                }
                catch(SQLException e) {}
            }
		
        }
        return null;
	}
	
	
	/**
	 * Verify the user string password that hashed,
	 * next to DB Encrypted Password
	 * @param income_pass
	 * @return true if there is a match
	 * @return false if mismatch
	 */
	public boolean passAuthentication(String income_pass) {
		String db_pass = this.getAdminPass();
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(income_pass.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            return false;
        }
        
        // if password match
        if (db_pass.equals(generatedPassword)){
        	return true;
        }
        
        // if passwords mismatch
		return false;

	}
	
	

}

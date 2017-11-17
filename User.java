
package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


/**
 * @author Pragya
 **/
public abstract class User {
	protected String email;
	protected String pwd;
	protected String type;
	
	/**
	 * Constructor
	 * 
	 * @param e - Email id of user
	 * @param pwd - Password of user
	 * @param t - Type of user
	 */
	public User(String e, String pwd, String t) {
		email=e;
		this.pwd=pwd;
		type=t;
	}
	
	/**
	 * Getter Function
	 * 
	 * @return email
	 */
	public String getemail()
	{
		return email;
	}
	
	
	/**
	 * @param e - Email id 
	 * @param p - Password
	 * 
	 * @return true if the user input email id and password match with a record in the users table in the database.
	 */
	public boolean AuthenticateUser(String e, String p) {
		 try
	        {
	            Class.forName("java.sql.DriverManager");
	            Connection con=(Connection) DriverManager.getConnection(
	                    "jdbc:mysql://localhost:3306/project","root","tapeied");
	            Statement stmt=(Statement) con.createStatement();
	            String q="Select password from users where BINARY email='"+e+"';";
	            ResultSet rs=stmt.executeQuery(q);
	            if(rs.next())
	            {
	            	System.out.println(p);
	            	System.out.println(rs.getString("password"));
	            	if(p.equals(rs.getString("password")))
	            	{
	            		return true;
	            	}
	            }
	           
	        }
	        catch(Exception exp)
	        {
	        	System.out.println(exp.getMessage());
	        }
		 return false;
	}
	
	/**
	 * @return the ResultSet to View all Room Bookings from bookings table in database
	 * @abstract method 
	 */
	public abstract ResultSet ViewRoomBookings();

}

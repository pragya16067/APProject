package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


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
	 * @return name of user
	 */
	public abstract String getName();
	
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
	                    "jdbc:mysql://localhost:3306/project","root","30july1998");
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
	 * Change password of any user type
	 * @param s
	 */
	public void changePassword(String s) {
		String e=this.email;
		try
		{
			Class.forName("java.sql.DriverManager");
	        Connection con=(Connection) DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/project","root","30july1998");
	        Statement stmt=(Statement) con.createStatement();
	        String q="update users set password='"+s+"'where email='"+e+"';";
	        stmt.executeUpdate(q);
	        
		}
		catch(Exception exp)
		{
			System.out.println(exp.getMessage());
		}
		
	}
	/**
	 * Returns all the bookings till date.
	 * @return
	 */
	public ArrayList<Classrooms> accroom()
	{
		ArrayList<Classrooms> avail = new ArrayList<Classrooms>();
		try
		{Class.forName("java.sql.DriverManager");
	    Connection con=(Connection) DriverManager.getConnection(
	            "jdbc:mysql://localhost:3306/project","root","30july1998");
	    Statement stmt=(Statement) con.createStatement();
	    
	    String q = "Select * from bookings ;";
	      System.out.println(q);
	 	  ResultSet rs1 = stmt.executeQuery(q);
	 	   
	    	
	    	while(rs1.next())
	    	{
	    		avail.add(new Classrooms(rs1.getString("RoomNo"),0,rs1.getString("Day"),rs1.getString("CourseCode"),rs1.getTime("Start")+"-"+rs1.getTime("End")));
	    	
	    	}
	    	for(int i=0;i<avail.size();i++)
	    	{
	    		String q1 = "Select Capacity from rooms where RoomNo ='"+avail.get(i).RoomNo+"';";
	    		rs1 = stmt.executeQuery(q1);
	    		if(rs1.next())
	    		{
	    			avail.get(i).setCapacity(rs1.getInt("Capacity"));
	    		}
	    		else
	    		{
	    			avail.get(i).setCapacity(0);
	    		}
	    		rs1.next();
	    	}
	    	return avail;
	    
	    
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		//System.out.println("useless");
		avail.add(new Classrooms("---",0,"---","------","00-00"));
		return avail;
	}
	/**
	 * Get all the bookings till dated based on the room entered by the user
	 * @param Room
	 * @return
	 */
	public ArrayList<Classrooms> accroom(String Room)
	{
		ArrayList<Classrooms> avail = new ArrayList<Classrooms>();
		try
		{Class.forName("java.sql.DriverManager");
	    Connection con=(Connection) DriverManager.getConnection(
	            "jdbc:mysql://localhost:3306/project","root","30july1998");
	    Statement stmt=(Statement) con.createStatement();
	    String q = "Select * from rooms where RoomNo = '"+Room+"';";
	    System.out.println(q);
	    ResultSet rs = stmt.executeQuery(q);
	    rs.next();
	    int c = rs.getInt("Capacity");
	    System.out.println("Cap"+c);
	    
	    if(!rs.next())
	    { q = "Select * from bookings where RoomNo = '"+Room+"';";
	      System.out.println(q);
	 	   ResultSet rs1 = stmt.executeQuery(q);
	 	   
	    	if(!rs1.next())
	    {  
	    	Classrooms cl =new Classrooms(Room,c,"Available","------","00-00");
	    	avail.add(cl);
	    	return avail;
	    }
	    else
	    {
	    	do
	    	{
	    		avail.add(new Classrooms(Room,c,rs1.getString("Day"),rs1.getString("CourseCode"),rs1.getTime("Start")+"-"+rs1.getTime("End")));
	    	
	    	}
	    	while(rs1.next());
	    	return avail;
	    }
	    }
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		//System.out.println("useless");
		avail.add(new Classrooms(Room,0,"---","------","00-00"));
		return avail;
	}
	/**
	 * Find all booking based on the given parameters
	 * @param Room
	 * @param Date
	 * @param Day
	 * @return
	 */
	public ArrayList<Classrooms> accroom(String Room,String Date,String Day)
	{
		ArrayList<Classrooms> avail = new ArrayList<Classrooms>();
		try
		{Class.forName("java.sql.DriverManager");
	    Connection con=(Connection) DriverManager.getConnection(
	            "jdbc:mysql://localhost:3306/project","root","30july1998");
	    Statement stmt=(Statement) con.createStatement();
	    String q = "Select * from rooms where RoomNo = '"+Room+"';";
	    System.out.println(q);
	    ResultSet rs = stmt.executeQuery(q);
	    rs.next();
	    int c = rs.getInt("Capacity");
	    System.out.println("Cap"+c);
	    
	    if(!rs.next())
	    { q = "Select * from bookings where RoomNo = '"+Room+"' and (Day = '"+Date+"' or Day ='"+Day+"');";
	      System.out.println(q);
	 	   ResultSet rs1 = stmt.executeQuery(q);
	 	   
	    	if(!rs1.next())
	    {  
	    	Classrooms cl =new Classrooms(Room,c,"Available","------","00-00");
	    	avail.add(cl);
	    	return avail;
	    }
	    else
	    {
	    	do
	    	{
	    		avail.add(new Classrooms(Room,c,Date,rs1.getString("CourseCode"),rs1.getTime("Start")+"-"+rs1.getTime("End")));
	    	
	    	}
	    	while(rs1.next());
	    	return avail;
	    }
	    }
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		//System.out.println("useless");
		avail.add(new Classrooms(Room,0,"---","------","00-00"));
		return avail;
	}
	/**
	 * Find all bookings with given parameters
	 * @param Date
	 * @param Day
	 * @return
	 */
	public ArrayList<Classrooms> accroom(String Date,String Day)
	{
		ArrayList<Classrooms> avail = new ArrayList<Classrooms>();
		try
		{Class.forName("java.sql.DriverManager");
	    Connection con=(Connection) DriverManager.getConnection(
	            "jdbc:mysql://localhost:3306/project","root","30july1998");
	    Statement stmt=(Statement) con.createStatement();
	    
	    String  q = "Select * from bookings where (Day = '"+Date+"' or Day ='"+Day+"');";
	      System.out.println(q);
	 	   ResultSet rs1 = stmt.executeQuery(q);
	 	   
	    if(!rs1.next())
	    {  
	    	Classrooms cl =new Classrooms("---",0,"Available","------","00-00");
	    	avail.add(cl);
	    	return avail;
	    }
	    else
	    {
	    	do
	    	{
	    		avail.add(new Classrooms(rs1.getString("RoomNo"),0,Date,rs1.getString("CourseCode"),rs1.getInt("Start")+"-"+rs1.getInt("End")));
	    	
	    	}
	    	while(rs1.next());
	    	for(int i=0;i<avail.size();i++)
	    	{
	    		String q1 = "Select Capacity from rooms where RoomNo ='"+avail.get(i).RoomNo+"';";
	    		rs1 = stmt.executeQuery(q1);
	    		if(rs1.next())
	    		{
	    			avail.get(i).setCapacity(rs1.getInt("Capacity"));
	    		}
	    		else
	    		{
	    			avail.get(i).setCapacity(0);
	    		}
	    		
	    	}
	    	return avail;
	    	
	    }
	    }
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		//System.out.println("useless");
		avail.add(new Classrooms("---",0,"---","------","00-00"));
		return avail;
	}
	/**
	 * Fin all bookings with given parameter
	 * @param stime
	 * @return
	 */
	public ArrayList<Classrooms> accroomt(String stime)
	{
		ArrayList<Classrooms> avail = new ArrayList<Classrooms>();
		try
		{Class.forName("java.sql.DriverManager");
	    Connection con=(Connection) DriverManager.getConnection(
	            "jdbc:mysql://localhost:3306/project","root","30july1998");
	    Statement stmt=(Statement) con.createStatement();
	    String time[] = stime.split("-");
	    String  q = "Select * from bookings where ((Start <='"+time[0]+"' and End >'"+time[0]+"') or (Start < '"+time[1]+"' and End >'"+time[1]+"'));";
	      System.out.println(q);
	 	   ResultSet rs1 = stmt.executeQuery(q);
	 	  
	    if(!rs1.next())
	    {  
	    	Classrooms cl =new Classrooms("---",0,"Available","------",stime);
	    	avail.add(cl);
	    	return avail;
	    }
	    else
	    {
	    	do
	    	{
	    		avail.add(new Classrooms(rs1.getString("RoomNo"),0,rs1.getString("Day"),rs1.getString("CourseCode"),rs1.getTime("Start")+"-"+rs1.getTime("End")));
	    	
	    	}
	    	while(rs1.next());
	    	for(int i=0;i<avail.size();i++)
	    	{
	    		String q1 = "Select Capacity from rooms where RoomNo ='"+avail.get(i).RoomNo+"';";
	    		rs1 = stmt.executeQuery(q1);
	    		if(rs1.next())
	    		{
	    			avail.get(i).setCapacity(rs1.getInt("Capacity"));
	    		}
	    		else
	    		{
	    			avail.get(i).setCapacity(0);
	    		}
	    		
	    	}
	    	return avail;
	    	
	    }
	    }
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		//System.out.println("useless");
		avail.add(new Classrooms("---",0,"---","------","00-00"));
		return avail;
	}
	/**
	 * Find all bookings with given parameters
	 * @param Room
	 * @param time
	 * @return
	 */
	public ArrayList<Classrooms> accroomt(String Room,String time)
	{
		ArrayList<Classrooms> avail = new ArrayList<Classrooms>();
		try
		{Class.forName("java.sql.DriverManager");
	    Connection con=(Connection) DriverManager.getConnection(
	            "jdbc:mysql://localhost:3306/project","root","30july1998");
	    Statement stmt=(Statement) con.createStatement();
	    String q = "Select * from rooms where RoomNo = '"+Room+"';";
	    System.out.println(q);
	    ResultSet rs = stmt.executeQuery(q);
	    rs.next();
	    int c = rs.getInt("Capacity");
	    System.out.println("Cap"+c);
	    String[] Time = time.split("-");
	    if(!rs.next())
	    { q = "Select * from bookings where ((Start <='"+Time[0]+"' and End >'"+Time[0]+"') or (Start < '"+Time[1]+"' and End >'"+Time[1]+"')) and RoomNo = '"+Room+"';";
	      System.out.println(q);
	 	   ResultSet rs1 = stmt.executeQuery(q);
	 	   
	    	if(!rs1.next())
	    {  
	    	Classrooms cl =new Classrooms(Room,c,"Available","------",time);
	    	avail.add(cl);
	    	return avail;
	    }
	    else
	    {
	    	do
	    	{
	    		avail.add(new Classrooms(Room,c,rs1.getString("Day"),rs1.getString("CourseCode"),rs1.getTime("Start")+"-"+rs1.getTime("End")));
	    	
	    	}
	    	while(rs1.next());
	    	return avail;
	    }
	    }
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		//System.out.println("useless");
		avail.add(new Classrooms(Room,0,"---","------","00-00"));
		return avail;
	}
	/**
	 * Find all room bookings with given parameters
	 * @param Room
	 * @param Date
	 * @param Day
	 * @param time
	 * @return
	 */
	public ArrayList<Classrooms> accroom(String Room,String Date, String Day,String time)
	{
		ArrayList<Classrooms> avail = new ArrayList<Classrooms>();
		try
		{Class.forName("java.sql.DriverManager");
	    Connection con=(Connection) DriverManager.getConnection(
	            "jdbc:mysql://localhost:3306/project","root","30july1998");
	    Statement stmt=(Statement) con.createStatement();
	    String q = "Select * from rooms where RoomNo = '"+Room+"';";
	    System.out.println(q);
	    ResultSet rs = stmt.executeQuery(q);
	    rs.next();
	    int c = rs.getInt("Capacity");
	    System.out.println("Cap"+c);
	    String[] Time = time.split("-");
	    if(!rs.next())
	    { q = "Select * from bookings where ((Start <='"+Time[0]+"' and End >'"+Time[0]+"') or (Start < '"+Time[1]+"' and End >'"+Time[0]+"')) and (Day = '"+Date+"' or Day ='"+Day+"') and RoomNo = '"+Room+"';" ;
	      System.out.println(q);
	 	   ResultSet rs1 = stmt.executeQuery(q);
	 	   
	    	if(!rs1.next())
	    {  
	    	Classrooms cl =new Classrooms(Room,c,"Available","------",time);
	    	avail.add(cl);
	    	return avail;
	    }
	    else
	    {
	    	do
	    	{
	    		avail.add(new Classrooms(Room,c,rs1.getString("Day"),rs1.getString("CourseCode"),rs1.getTime("Start")+"-"+rs1.getTime("End")));
	    	
	    	}
	    	while(rs1.next());
	    	return avail;
	    }
	    }
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		//System.out.println("useless");
		avail.add(new Classrooms(Room,0,"---","------","00-00"));
		return avail;
	}
	/**
	 * @return the ResultSet to View all Room Bookings from bookings table in database
	 * @abstract method 
	 */
	public abstract ResultSet ViewRoomBookings();

}
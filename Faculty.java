package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Faculty extends User{
	
	/**
	 * Constructor  of faculty 
	 * @param e email
	 * @param pwd password
	 * @param t = faculty
	 */
	public Faculty(String e, String pwd, String t) {
		super(e,pwd,"Faculty");
	}
	
	/* 
	 * @see application.User#getName()
	 */
	public String getName() {
		String e=this.email;
		String[] s=e.split("@");
		String Name=s[0];
		return Name.toUpperCase();
	}
	/**
	 * get the name of the courses taught by faculty
	 * @return
	 */
	public String Courses(){
		String s ="";
		try
		{
			Class.forName("java.sql.DriverManager");
	        Connection con=(Connection) DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/project","root","30july1998");
	        Statement stmt=(Statement) con.createStatement();
	        String q = "Select CourseCode,CourseName from courses where faculty Like Concat ('%','"+getName()+"','%');";
	        System.out.println(q);
	        ResultSet rs = stmt.executeQuery(q);
	        
	        while(rs.next())
	        {
	        	s+=(rs.getString("CourseName")+"("+rs.getString("CourseCode")+");");
	        }
		}
		catch(Exception ex)
		{
			
		}
		return s;
		
	}
	/**
	 * get coursecode of all the courses taught by that faculty
	 * @return
	 */
	public ArrayList<String> GetCourse()
	{
		ArrayList<String> s = new ArrayList<String>();
		try
		{
			Class.forName("java.sql.DriverManager");
	        Connection con=(Connection) DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/project","root","30july1998");
	        Statement stmt=(Statement) con.createStatement();
	        String q = "Select CourseCode from courses where faculty Like Concat ('%','"+getName()+"','%');";
	        System.out.println(q);
	        ResultSet rs = stmt.executeQuery(q);
	        
	        while(rs.next())
	        {
	        	s.add(rs.getString("CourseCode"));
	        }
		}
		catch(Exception ex)
		{
			
		}
		return s;
	}
	/**
	 * Book Room by admin based on the given parameter and also check that none of the bookings made coincides.
	 * @param Code
	 * @param Room
	 * @param STime
	 * @param ETime
	 * @param Day
	 * @param Capacity
	 * @param dayname
	 * @return
	 */
	public boolean BookRoom(String Code, String Room, String STime, String ETime, String Day ,int Capacity,String dayname)
	{
		try
		{
			Class.forName("java.sql.DriverManager");
	        Connection con=(Connection) DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/project","root","30july1998");
	        Statement stmt=(Statement) con.createStatement();
	        
	        //Select * from bookings where Roomno = "C21" and Day ="Monday" and Start =11 and End =12;
	        String q ="Select * from bookings where RoomNo = '"+Room+"' and (Day ='"+Day+"'or Day ='"+dayname+"') and ((Start <='"+STime+"' and End >'"+STime+"') or (Start < '"+ETime+"' and End >'"+ETime+"'));";
	       System.out.println(q);
	        ResultSet rs = stmt.executeQuery(q);
	        //System.out.println(rs.next());
	        if(!rs.next())
	        {
	        	String q2 ="Select Capacity from rooms where RoomNo = '"+Room+"';";
	        	System.out.println(q2);
	        	rs = stmt.executeQuery(q2);
	        	rs.next();
	        	int cap = rs.getInt("Capacity");
	        	if(Capacity <= cap)
	        	{
	        	String q1 ="Insert into bookings values ('"+Room+"', '"+Code+"','"+Day+"','"+STime +"', '"+ETime+"', 'Faculty' );";
	        	 System.out.println(q1);
	        	stmt.executeUpdate(q1);
	        	return true;
	        	}
	        	else
	        	{
	        		return false;
	        	}
	        	
	        }
	        else
	        {
	        	return false;
	        }
	        
	        
		}
		catch(Exception exp)
		{
			System.out.println(exp.getMessage());
		}
		return false;
		
	}
	
	public ArrayList<Bookings> GetBooking()
	{
		ArrayList<Bookings> book = new ArrayList<Bookings>();
		try
		{
			Class.forName("java.sql.DriverManager");
	        Connection con=(Connection) DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/project","root","30july1998");
	        Statement stmt=(Statement) con.createStatement();
	        ArrayList<String> a = GetCourse();
	        String course ="CourseCode ='";
	        for(int i=0;i<a.size();i++)
	        {
	        	course+=(a.get(i)+"'");
	        	if(i!=a.size()-1)
	        	{
	        		course+=("or CourseCode ='");
	        	}
	        }
	        String q ="Select * from bookings where (type = 'Faculty' or type ='Student') and ("+course+");";
	        System.out.println(q);
	        ResultSet rs = stmt.executeQuery(q);
	        while(rs.next())
	        {
	        	book.add(new Bookings(rs.getString("CourseCode"),rs.getString("RoomNo"),rs.getString("Day"),rs.getTime("Start")+"-"+rs.getTime("end")));
	        }
	        return book;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return book;
	}
	/**
	 * Is used to cancel bookings made by the faculty for his/her course 
	 * the booking to be cancelled is passed as parameter
	 * @param b
	 */
	public void CancelBooking(Bookings b)
	{
		try
		{
			Class.forName("java.sql.DriverManager");
	        Connection con=(Connection) DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/project","root","30july1998");
	        Statement stmt=(Statement) con.createStatement();
	        String q = "Delete from Bookings where CourseCode = '"+b.getPurpose1()+"' and RoomNo ='"+b.getRoomN()+"' and Day ='"+b.getDateN()+"' and Start = '"+b.getTimeN().split("-")[0]+"' and End ='"+b.getTimeN().split("-")[1]+"';";
	        System.out.println(q);
	        stmt.executeUpdate(q);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	/* 
	 * @see application.User#ViewRoomBookings()
	 */
	public ResultSet ViewRoomBookings() {
		ResultSet rs=null;
		try
		{
			Class.forName("java.sql.DriverManager");
	        Connection con=(Connection) DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/project","root","30july1998");
	        Statement stmt=(Statement) con.createStatement();
	        String q="Select password from users where email='"+this.email+"';";
	        rs=stmt.executeQuery(q);
	        return rs;
		}
		catch(Exception exp)
		{
			System.out.println(exp.getMessage());
		}
		return rs;
	}
}
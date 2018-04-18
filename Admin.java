package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


/**
 * @author Tanya Raj
 *
 */
public class Admin extends User{
	
	/**
	 * Constructor
	 * @param e email
	 * @param pwd password
	 * @param t = "admin"
	 */
	public Admin(String e, String pwd, String t) {
		super(e,pwd,"Admin");
	}
	
	/**
	 * Returns Name 
	 * @return
	 */
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
	 * Get Student Requests which are Pending
	 * @return
	 */
	public ArrayList<Request> GetRequests()
	{
		ArrayList<Request> req = new ArrayList<Request>();
		try
		{
			Class.forName("java.sql.DriverManager");
	        Connection con=(Connection) DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/project","root","tapeied");
	        Statement stmt=(Statement) con.createStatement();
	        String q = "Select * from requests where status ='Pending';";
	        ResultSet rs = stmt.executeQuery(q);
	       while(rs.next())
	        { 
	    	
			System.out.println(rs.getString("purpose"));
	    	Request r = new Request(rs.getInt("Rid"),rs.getString("purpose"),rs.getString("room"),rs.getInt("capacity"),rs.getDate("dateReq").toString(),rs.getTime("sTimeReq").toString(),rs.getTime("eTimeReq").toString());
	        req.add(r);
	
	    	//System.out.println(req.get(0).getpurpose());
	        }
	      
	        //System.out.println(req.size());
	        return req;
	        
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		return req;
	}
	
	/**
	 * Checks if a request is not pending for more than 5 days, if yes then reject request.
	 */
	public void Checkdays()
	{
		try
		{
			Class.forName("java.sql.DriverManager");
	        Connection con=(Connection) DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/project","root","tapeied");
	        Statement stmt=(Statement) con.createStatement();
	        String q="Select * from requests ;";
	        ResultSet rs=stmt.executeQuery(q);
	        if(rs.next()) {
	        	String status=rs.getString("status");
	        	
	        	if(status.equals("Pending"))
	        	{
	        		Calendar cal = Calendar.getInstance();
	        		Date d1=cal.getTime();
	        		int Rid = rs.getInt("rid");
	        		Date d2=rs.getDate("StartDate");
	        		long diff = (d1.getTime() - d2.getTime()) / (1000 * 60 * 60 * 24);
	        		
	        		if(diff > 5)
	        		{
	        			String q2="Update requests set status='Rejected' where rid="+Rid+";";
	        			stmt.executeUpdate(q2);
	        			System.out.println(q2);
	        		}
	        		
	        	}
	        }
		}
		catch(Exception exp)
		{
			System.out.println(exp.getMessage());
		}
		
	}
	/**
	 * Accept Pending Request of the Student
	 * Also Checks if the request coincides with any previous bookings
	 * @param rq
	 * @return
	 */
	public String AcceptRequest(Request rq)
	{
		try
		{
			Class.forName("java.sql.DriverManager");
	        Connection con=(Connection) DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/project","root","tapeied");
	        Statement stmt=(Statement) con.createStatement();
	        
	        System.out.println(rq.getDateN());
	        String d[] = rq.getDateN().split("-");
	        String dates = d[2]+" "+d[1]+" "+d[0];
	        String date = rq.getDateN();
	        String time = rq.getsTime();
	        String etime = rq.geteTime();
	        LocalDate date1 = LocalDate.of(Integer.parseInt(d[0]),Integer.parseInt(d[1]) , Integer.parseInt(d[2]));
	        String day = date1.getDayOfWeek().name();
	        String q = "Select * from bookings where RoomNo = '"+rq.getRoomN()+"' and (Day = '"+dates+"' or Day ='"+day+"') and ((Start <='"+time+"' and End >'"+time+"') or (Start < '"+etime+"' and End >'"+etime+"'));";
	        System.out.println(q);
	        ResultSet rs = stmt.executeQuery(q);
	        if(!rs.next())
	        {
	        q = "Select * from rooms where RoomNo = '"+rq.getRoomN()+"';";
	        System.out.println(q);
	        rs = stmt.executeQuery(q);
	        if(rs.next())
	        	
	        {  System.out.println(rs.getInt("Capacity")>=rq.getCapacityN());
	        	if(rs.getInt("Capacity")>=rq.getCapacityN())
	        		
	        	{    rs.next();
	        		 q = "Update requests set status = 'Accepted' where rid="+rq.getRid()+";";
	    	        System.out.println(q);
	    	        stmt.executeUpdate(q);
	        		q = "insert into bookings values ('"+rq.getRoomN()+"', '"+rq.getPurpose()+"', '"+dates+"', '"+rq.getsTime()+"','"+rq.geteTime()+"', 'Admin' );";
	        		System.out.println(q);
	        		stmt.executeUpdate(q);
	        		
	        	}
	        	else
	        	{
	        		System.out.println("room ");
	        		return "Room is of Low capacity! Cannot Book";
	        	}
	       
	        }
	        }
	        else
	        {
	        	return "Room Not available on given date and time! Cannot Book";
	        }
	        return "";
	        
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		return "";
	}
	
	/**
	 * Reject Pending Requests of the student
	 * @param rq
	 * 
	 */
	public void RejectRequest(Request rq)
	{
		try
		{
			Class.forName("java.sql.DriverManager");
	        Connection con=(Connection) DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/project","root","tapeied");
	        Statement stmt=(Statement) con.createStatement();
	        String q = "Update requests set status = 'Rejected' where rid="+rq.getRid()+";";
	        stmt.executeUpdate(q);
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}
	/**
	 * Room Booking by the Admin, also checks if the booking coincides with any previous bookings, if no then adds
	 * to the booking table.
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
	                "jdbc:mysql://localhost:3306/project","root","tapeied");
	        Statement stmt=(Statement) con.createStatement();
	        
	        //Select * from bookings where Roomno = "C21" and Day ="Monday" and Start =11 and End =12;
	        String q ="Select * from bookings where RoomNo = '"+Room+"' and (Day ='"+Day+"'or Day ='"+dayname+"') and((Start <='"+STime+"' and End >'"+STime+"') or (Start < '"+ETime+"' and End >'"+ETime+"'));";
	       System.out.println(q);
	        ResultSet rs = stmt.executeQuery(q);
	        //System.out.println(rs.next());
	        if(!rs.next())
	        {
	        	String q2 ="Select Capacity from rooms where RoomNo = '"+Room+"';";
	        	rs = stmt.executeQuery(q2);
	        	rs.next();
	        	int cap = rs.getInt("Capacity");
	        	if(Capacity <= cap)
	        	{
	        	String q1 ="Insert into bookings values ('"+Room+"', '"+Code+"','"+Day+"','"+STime +"', '"+ETime+"', 'Admin' );";
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
	
	/**
	 * Returns an arraylist of all the bookings made by the admin or the student.
	 * @return
	 */
	public ArrayList<Bookings> GetBooking()
	{
		ArrayList<Bookings> book = new ArrayList<Bookings>();
		try
		{
			Class.forName("java.sql.DriverManager");
	        Connection con=(Connection) DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/project","root","tapeied");
	        Statement stmt=(Statement) con.createStatement();
	        String q ="Select * from bookings where (type = 'Admin' or type = 'Student');";
	        ResultSet rs = stmt.executeQuery(q);
	        while(rs.next())
	        {
	        	book.add(new Bookings(rs.getString("CourseCode"),rs.getString("RoomNo"),rs.getString("Day"),rs.getTime("Start")+"-"+rs.getTime("end")));
	        }
	        return book;
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		return book;
	}
	/**
	 * This functions cancels any selected booking passed as parameter by the admin and
	 *  hence remove it from the database.
	 * @param b
	 */
	public void CancelBooking(Bookings b)
	{
		try
		{
			Class.forName("java.sql.DriverManager");
	        Connection con=(Connection) DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/project","root","tapeied");
	        Statement stmt=(Statement) con.createStatement();
	        String q = "Delete from Bookings where CourseCode = '"+b.getPurpose1()+"' and RoomNo ='"+b.getRoomN()+"' and Day ='"+b.getDateN()+"' and Start = '"+b.getTimeN().split("-")[0]+"' and End ='"+b.getTimeN().split("-")[1]+"';";
	        System.out.println(q);
	        stmt.executeUpdate(q);
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}
	
	
	/* 
	 * Abstract class definition
	 * @see application.User#ViewRoomBookings()
	 */
	public ResultSet ViewRoomBookings() {
		ResultSet rs=null;
		try
		{
			Class.forName("java.sql.DriverManager");
	        Connection con=(Connection) DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/project","root","tapeied");
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
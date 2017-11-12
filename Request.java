package application;

import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;

public class Request {
	private String DateTimeRequested;
	private int Rid;
	private String Purpose;
	private String RoomN;
	private int capacity;
	private String date;
	private String sTime;
	private String eTime;
	private String day;
	public Request(int id, String p, String r, int cap, String tR, String d, String st, String et ) {
		Rid=id;
		Purpose=p;
		RoomN=r;
		capacity=cap;
		DateTimeRequested=tR;
		date=d;
		sTime=st;
		eTime=et;
	}
	
	public Request(int id, String p, String r, int cap, String d, String st, String et ) {
		Rid=id;
		Purpose=p;
		RoomN=r;
		capacity=cap;
		date=d;
		sTime=st;
		eTime=et;
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Calendar cal = Calendar.getInstance();
		DateTimeRequested = (dateFormat.format(cal.getTime()));
		 
	}
	public void setday(int dayn)
	{
		
	}
	public Integer getRid()
	{
		return Rid;
	}
	public String getPurpose()
	{
		return Purpose;
	}
	public String getRoomN()
	{
		return RoomN;
	}
	public Integer getCapacityN()
	{
		return capacity;
	}
	public String getDateN()
	{
		return date;
	}
	public String getsTime()
	{
		return sTime;
	}
	
	public String geteTime()
	{
		return eTime;
	}
	public String getTimeN()
	{
		return sTime+"-"+eTime;
	}
	public String getRequestedDateTime() {
		return DateTimeRequested;
	}
	
	
	public String getStatus() {
		ResultSet rs=null;
		try
		{
			Class.forName("java.sql.DriverManager");
	        Connection con=(Connection) DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/project","root","tapeied");
	        Statement stmt=(Statement) con.createStatement();
	        String q="Select * from requests where rid='"+this.Rid+"';";
	        rs=stmt.executeQuery(q);
	        if(rs.next()) {
	        	String status=rs.getString("status");
	        	if(status.toLowerCase().equals("accepted"))
	        	{
	        		return "Accepted";
	        	}
	        	else if(status.toLowerCase().equals("rejected"))
	        	{
	        		return "Rejected";
	        	}
	        	else
	        	{
	        		//gets the current date and time
	        		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	        		Calendar cal = Calendar.getInstance();
	        		String curDate = (dateFormat.format(cal.getTime()));
	        		Date d1=cal.getTime();
	        		
	        		Date d2=rs.getDate("StartDate");
	        		long diff = (d1.getTime() - d2.getTime()) / (1000 * 60 * 60 * 24);
	        		
	        		if(diff > 5)
	        		{
	        			String q2="Update requests set status='Rejected' where rid="+this.Rid+";";
	        			stmt.executeUpdate(q2);
	        			return "Rejected";
	        		}
	        		else
	        		{
	        			return "Pending";
	        		}
	        	}
	        }
		}
		catch(Exception exp)
		{
			System.out.println(exp.getMessage());
		}
		return "Don't Know";
	}

}
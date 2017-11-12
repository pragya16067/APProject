package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Student extends User{
	
	public Student(String e, String pwd, String t) {
		super(e,pwd,"Student");
	}
	
	public String getName() {
		String e=this.email;
		String[] s=e.split("@");
		String Name=s[0].substring(0, s[0].length()-5);
		return Name.toUpperCase();
	}
	
	public String getBatch() {
		String e=this.email;
		String[] s=e.split("@");
		String batch="20"+s[0].substring(s[0].length()-5, s[0].length()-3);
		return batch;
	}
	
	public String getRno() {
		String e=this.email;
		String[] s=e.split("@");
		String rno="20"+s[0].substring(s[0].length()-5, s[0].length());
		return rno;
	}
	
	public void changePassword(String s) {
		String e=this.email;
		try
		{
			Class.forName("java.sql.DriverManager");
	        Connection con=(Connection) DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/project","root","tapeied");
	        Statement stmt=(Statement) con.createStatement();
	        String q="update users set password='"+s+"'where email='"+e+"';";
	        stmt.executeUpdate(q);
	        
		}
		catch(Exception exp)
		{
			System.out.println(exp.getMessage());
		}
		
	}
	
	public void AddCourses() {
		
	}
	
	public ResultSet ViewCourses() {
		ResultSet rs=null;
		try
		{
			Class.forName("java.sql.DriverManager");
	        Connection con=(Connection) DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/project","root","tapeied");
	        Statement stmt=(Statement) con.createStatement();
	        String q="Select CoursesTaken from students where email='"+this.email+"';";
	        rs=stmt.executeQuery(q);
	        return rs;
	        
		}
		catch(Exception exp)
		{
			System.out.println(exp.getMessage());
		}
		return rs;
	}
	
	public ResultSet SearchCourses(String searchCode) {
		ResultSet rs=null;
		try
		{
			Class.forName("java.sql.DriverManager");
	        Connection con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","tapeied");
	        Statement stmt=(Statement) con.createStatement();
	        String q="Select CourseCode,CourseName,Faculty,Credits from courses where LCase(PostConditions) like '%"+searchCode.toLowerCase()+"%';";
	        rs=stmt.executeQuery(q);
	        return rs;
	        
		}
		catch(Exception exp)
		{
			System.out.println(exp.getMessage());
		}
		return rs;
	}
	
	public void MakeRequest (String p, String r, int cap, String d, String st, String et ) {
		try
		{
			Class.forName("java.sql.DriverManager");
	        Connection con=(Connection) DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/project","root","tapeied");
	        Statement stmt=(Statement) con.createStatement();
	        String q="Select Max(rid) from requests;";
	        ResultSet rs=stmt.executeQuery(q);
	        rs.next();
	        int id= rs.getInt("Max(rid)")+1;
	        Request req = new Request(id,p,r,cap,d,st,et);
	        
	        //CODE TO INSERT A NEW REQUEST INTO THE REQUESTS TABLE AND UPDATE THE REQUESTS COL OF THE STUDENT TABLE
	        
	        String qReq = "Insert into requests values ("+id+",'"+p+"','"+r+"',"+cap+",'Pending','"+d+"','"+st+"','"+et+"','"+req.getDateTimeRequested()+"');";
	        stmt.executeUpdate(qReq);
	        
	        String SReq = "Update students set Requests = CONCAT(Requests,'"+id+";') where email='"+ this.email +"';";
	        stmt.executeUpdate(SReq);
		}
		catch(Exception exp)
		{
			System.out.println(exp.getMessage());
		}
	}
	
	public ArrayList<Request> ViewRequests() {
		ArrayList<Request> requests=new ArrayList<Request> ();
		ResultSet rs=null;
		try
		{
			Class.forName("java.sql.DriverManager");
	        Connection con=(Connection) DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/project","root","tapeied");
	        Statement stmt=(Statement) con.createStatement();
	        String q="Select Requests from students where email='"+this.email+"';";
	        rs=stmt.executeQuery(q);
	        if(rs.next())
	        {
	        	
	        	String[] rids = rs.getString("Requests").split(";");
	        	for(int i=0; i<rids.length; i++)
	        	{
	        		String q1="Select * from requests where rid="+Integer.parseInt(rids[i])+";";
	        		ResultSet r=stmt.executeQuery(q1);
	        		if(r.next())
	        		{
		        		Request req=new Request(r.getInt("rid"),r.getString("purpose"),r.getString("room"),r.getInt("capacity"),
		        				r.getString("StartDate"),r.getString("dateReq"),r.getString("sTimeReq"),r.getString("eTimeReq"));
		        		requests.add(req);
	        		}
	        	}
	        	return requests;
	        }
	        
	        
		}
		catch(Exception exp)
		{
			System.out.println(exp.getMessage());
		}
		return requests;
	}
	
	public ResultSet ViewRoomBookings() {
		ResultSet rs=null;
		try
		{
			Class.forName("java.sql.DriverManager");
	        Connection con=(Connection) DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/project","root","tapeied");
	        Statement stmt=(Statement) con.createStatement();
	        String q="Select * from users where email='"+this.email+"';";
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

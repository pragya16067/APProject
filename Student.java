package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;

/**
 * @author Tanya Raj
 *
 */
public class Student extends User{
	
	/**
	 * Constructor of Student
	 * @param e
	 * @param pwd
	 * @param t
	 */
	public Student(String e, String pwd, String t) {
		super(e,pwd,"Student");
	}
	
	/**
	 * Returns name of the student
	 * @return
	 */
	public String getName() {
		String e=this.email;
		String[] s=e.split("@");
		String Name=s[0].substring(0, s[0].length()-5);
		return Name.toUpperCase();
	}
	
	/**
	 * Returns batch from student's rollno provided in their e-mail-id
	 * @return
	 */
	public String getBatch() {
		String e=this.email;
		String[] s=e.split("@");
		String batch="20"+s[0].substring(s[0].length()-5, s[0].length()-3);
		return batch;
	}
	
	/**
	 * Returns Roll no of student
	 * @return
	 */
	public String getRno() {
		String e=this.email;
		String[] s=e.split("@");
		String rno="20"+s[0].substring(s[0].length()-5, s[0].length());
		return rno;
	}
	
	
	
	/**
	 * Check selected course by student to its courses taken table if it doesn't coincides with any of their
	 * already taken courses.
	 * @param c
	 * @return
	 */
	public boolean okToAddCourse(Course c) {
		try
		{
			Class.forName("java.sql.DriverManager");
	        Connection con=(Connection) DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/project","root","tapeied");
	        Statement stmt=(Statement) con.createStatement();
	        //To check if timings are not clashing with any of students existing courses
	        
	        String st,et;
	        
	        String q="Select CoursesTaken from students where email='"+this.email+"';";
	        ResultSet rs=stmt.executeQuery(q);
	        String codes="(";
	        if(rs.next())
	        {
	        	String[] cCode=rs.getString("CoursesTaken").split(";");
	        	
	        	if(cCode.length==5)
	        		return false;
	        	else if(cCode.length==0)
	        		return true;
	        	else
	        	{
	        	
		        	for(int i=0; i<cCode.length-1; i++)
		        	{
		        		codes=codes+"'"+cCode[i]+"',";
		        	}
		        	codes=codes+"'"+cCode[cCode.length-1]+"')";   
	        	}
	        
	        }
	        
	        //Check for timetable clashes with the courses taken by student
	        
		        q="Select Day, Start, End from bookings where CourseCode='"+c.getCourseCode()+"';";
		        rs=stmt.executeQuery(q);
		        
		        while(rs.next()) {
		        	System.out.println("I got a record");
		        	String day=rs.getString("Day").toLowerCase();
		        	
		        	if(day.equals("monday") || day.equals("tuesday") || day.equals("wednesday") || day.equals("thursday") || day.equals("friday"))
		        	{
		        		
		        	}
		        	else
		        	{
		        		Date date=rs.getDate("Day");
		        		day = new SimpleDateFormat("EEEE").format(date).toLowerCase();
		        	}
		        	st=rs.getString("Start");
	        		et=rs.getString("End");
	        		
	        		//check for clashes
	        		q="select * from bookings where Day ='"+day+"' and ((Start <= '"+st+"' and End >= '"+st+"') or (Start <= '"+et+"' and End >= '"+et+"')) and CourseCode in "+codes+" ;";
	        		System.out.println(q);
	        		ResultSet rs2=stmt.executeQuery(q);
	        		if(!rs2.next())
	        		{
	        			System.out.println("No clashes found");
	        			return true;
	        		}
	        		else
	        		{
	        			System.out.println(rs2.getFetchSize()+" clashes found");
	        			return false;
	        		}
		        }
	        
	        
		}
		catch(Exception exp)
		{
			System.out.println("okToAddCourse "+exp.getMessage());
		}
		return true;
	}
	
	/**
	 * AddCourses to the respective students table of courses.
	 * @param c
	 */
	public void AddCourse(Course c) {
		String e=this.email;
		try
		{
			Class.forName("java.sql.DriverManager");
	        Connection con=(Connection) DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/project","root","tapeied");
	        Statement stmt=(Statement) con.createStatement();
	        String code=c.getCourseCode();
	        String q="update students set CoursesTaken = CONCAT(CoursesTaken,'"+code+";') where email='"+e+"';";
	        stmt.executeUpdate(q);
	        
		}
		catch(Exception exp)
		{
			System.out.println(exp.getMessage());
		}
	}
	
	/**
	 * Drop the selected course in parameter if it is not one of  the mandatory courses and also remove
	 * it from the courses taken by respective students table.
	 * @param CourseName
	 */
	public void DropCourse(String CourseName) {
		try
		{
			Class.forName("java.sql.DriverManager");
	        Connection con=(Connection) DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/project","root","tapeied");
	        Statement stmt=(Statement) con.createStatement();
	        String q="Select CourseCode from courses where LCASE(CourseName)='"+ CourseName +"';";
	        ResultSet rs=stmt.executeQuery(q);
	        if(rs.next())
	        {
	        	String CCode=rs.getString("CourseCode");
	        	q="Select CoursesTaken from students where email='"+this.email+"';";
	        	ResultSet list=stmt.executeQuery(q);
	        	if(list.next()) {
	        		String[] codes= list.getString("CoursesTaken").split(";");
	        		///Find the index which has the coursecode as that which has to be dropped, then delete it and update table
	        		int delIndex=-1;
	        		for(int i=0; i<codes.length; i++)
	        		{
	        			if(codes[i].equals(CCode)) {
	        				delIndex=i;
	        				break;
	        			}
	        		}
	        		if(delIndex==-1)
	        		{
	        			return;
	        		}
	        		else
	        		{
	        			String newCourseList= "";
	        			for(int i=0; i<codes.length; i++)
		        		{
		        			if(i!=delIndex) {
		        				newCourseList=newCourseList+codes[i]+";";
		        			}
		        			else
		        				continue;
		        		}
	        			q="Update students set CoursesTaken='"+newCourseList+"' where email='"+this.email+"';";
	        			stmt.executeUpdate(q);
	        			JOptionPane.showMessageDialog(null, "The selected course has been dropped");
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
	 * Returns the result set of all the courses taken by the student.
	 * @return
	 */
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
	
	/**
	 * Retruns the arraylist of all the added courses to the students courses table.
	 * @return
	 */
	public ArrayList<String> getCourseList() {
		ArrayList<String> c=new ArrayList<String> ();
		ResultSet rs=null;
		try
		{
			Class.forName("java.sql.DriverManager");
	        Connection con=(Connection) DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/project","root","tapeied");
	        Statement stmt=(Statement) con.createStatement();
	        String q="Select CoursesTaken from students where email='"+this.email+"';";
	        rs=stmt.executeQuery(q);
	        if(rs.next())
	        {
	        	String[] cCode=rs.getString("CoursesTaken").split(";");
	        	for(int i=0; i<cCode.length; i++)
	        	{
	        		String q2 = "Select CourseName from courses where CourseCode='"+cCode[i]+"';";
	        		ResultSet r = stmt.executeQuery(q2);
	        		if(r.next())
	        		{
	        			c.add(r.getString("CourseName"));
	        		}
	        	}
	        }
	        return c;
		}
		catch(Exception exp)
		{
			System.out.println(exp.getMessage());
		}
		return c;
	}
	
	/**
	 * Returns the resultset of all the searched courses by the passed keyword as oarameter by the user.
	 * @param searchCode
	 * @return
	 */
	public ResultSet SearchCourses(String searchCode) {
		ResultSet rs2=null;
		try
		{
			Class.forName("java.sql.DriverManager");
	        Connection con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","tapeied");
	        Statement stmt=(Statement) con.createStatement();
	        String q="Select CoursesTaken from students where email='"+this.email+"';";
	        ResultSet rs=stmt.executeQuery(q);
	        String codes="(";
	        if(rs.next())
	        {
	        	String[] cCode=rs.getString("CoursesTaken").split(";");
	        	
	        	for(int i=0; i<cCode.length-1; i++)
	        	{
	        		codes=codes+"'"+cCode[i]+"',";
	        	}
	        	codes=codes+"'"+cCode[cCode.length-1]+"'";   
	        
		        codes=codes+")";
		        q="Select CourseCode,CourseName,Faculty,Credits from courses where LCase(PostConditions) like '%"+searchCode.toLowerCase()+"%' and CourseCode not in "+codes+" ;";
		        System.out.println(q);
		        rs2=stmt.executeQuery(q);
		        return rs2;
	        }
	        else
	        {
	        	q="Select CourseCode,CourseName,Faculty,Credits from courses where LCase(PostConditions) like '%"+searchCode.toLowerCase()+"%' ;";
		        System.out.println(q);
	        	rs2=stmt.executeQuery(q);
		        return rs2;
	        }
	        
		}
		catch(Exception exp)
		{
			System.out.println(exp.getMessage());
		}
		return rs2;
	}
	
	/**
	 * Returns personalized timetable of the student, time-venue and day and labs and tuts.
	 * @return
	 */
	public ArrayList<Timetable> getTimetable() {
		ArrayList<Timetable> timetables=new ArrayList<Timetable> ();
		try
		{
			Class.forName("java.sql.DriverManager");
	        Connection con=(Connection) DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/project","root","tapeied");
	        Statement stmt=(Statement) con.createStatement();
	        ResultSet rs=this.ViewCourses();
	        if(rs.next())
	        {
	        	String[] cCode=rs.getString("CoursesTaken").split(";");
	        	
	        	
	        		for(int i=0;i<cCode.length;i++)
	        		{
	        			String q = "Select * from timetable where CourseCode = '"+cCode[i]+"';";
	    	        	rs = stmt.executeQuery(q);
	        			
	        			if(rs.next())
	        			timetables.add(new Timetable(cCode[i],rs.getString("MondayTime$Venue"),rs.getString("TuedayTime$Venue"),rs.getString("WednesdayTime$Venue"),rs.getString("ThurdayTime$Venue"),rs.getString("FridayTime$Venue"),rs.getString("TutDay$Time$Venue"),rs.getString("LabDay$Time$Venue")));
	        		  
	        		}
	        		 System.out.println(timetables.size()+" ");
	        }
	        
	        
		}
		catch(Exception exp)
		{
			System.out.println(exp.getMessage());
		}
		return timetables;
	}
	
	public int getStudentID(String semail) {
		int sid=-1;
		try {
			Class.forName("java.sql.DriverManager");
	        Connection con=(Connection) DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/project","root","tapeied");
	        Statement stmt=(Statement) con.createStatement();
	        String q = "Select uid from users where email ='"+ semail + "';";
	        ResultSet rs = stmt.executeQuery(q);
	        if(rs.next())
	        {
	        	sid = rs.getInt("uid");
	        }
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
        return sid;
	}
	
	public ResultSet getProjects() {
		ResultSet rs = null;
		try {
			Class.forName("java.sql.DriverManager");
	        Connection con=(Connection) DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/project","root","tapeied");
	        Statement stmt=(Statement) con.createStatement();
	        String q = "Select * from Projects where StudentID ="+ this.getStudentID(this.email) + ";";
	        rs = stmt.executeQuery(q);
	        return rs;
	        
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
        return rs;
	}
	
	/**
	 * CODE TO INSERT A NEW REQUEST INTO THE REQUESTS TABLE AND UPDATE THE REQUESTS COL OF THE STUDENT TABLE
	 * @param p
	 * @param r
	 * @param cap
	 * @param d
	 * @param st
	 * @param et
	 */
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
	
	/**
	 * Student can view the status of their requests, returns an arraylist with these requests
	 * @return
	 */
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
	
	/* 
	 * Abstract method
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
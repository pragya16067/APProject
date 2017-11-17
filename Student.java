package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

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
	
	public boolean okToAddCourse(Course c) {
		try
		{
			Class.forName("java.sql.DriverManager");
	        Connection con=(Connection) DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/project","root","30july1998");
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
		        
		        while(rs.next()){
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
	
	public void AddCourse(Course c) {
		String e=this.email;
		try
		{
			Class.forName("java.sql.DriverManager");
	        Connection con=(Connection) DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/project","root","30july1998");
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
	
	public void DropCourse(String CourseName) {
		try
		{
			Class.forName("java.sql.DriverManager");
	        Connection con=(Connection) DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/project","root","30july1998");
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
	
	public ResultSet ViewCourses() {
		ResultSet rs=null;
		try
		{
			Class.forName("java.sql.DriverManager");
	        Connection con=(Connection) DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/project","root","30july1998");
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
	
	public ArrayList<String> getCourseList() {
		ArrayList<String> c=new ArrayList<String> ();
		ResultSet rs=null;
		try
		{
			Class.forName("java.sql.DriverManager");
	        Connection con=(Connection) DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/project","root","30july1998");
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
	
	public ResultSet SearchCourses(String searchCode) {
		ResultSet rs2=null;
		try
		{
			Class.forName("java.sql.DriverManager");
	        Connection con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","30july1998");
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
	
	public ArrayList<Timetable> getTimetable() {
		ArrayList<Timetable> timetables=new ArrayList<Timetable> ();
		try
		{
			Class.forName("java.sql.DriverManager");
	        Connection con=(Connection) DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/project","root","30july1998");
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
	
	public void MakeRequest (String p, String r, int cap, String d, String st, String et ) {
		try
		{
			Class.forName("java.sql.DriverManager");
	        Connection con=(Connection) DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/project","root","30july1998");
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
	                "jdbc:mysql://localhost:3306/project","root","30july1998");
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
			System.out.println("game is on");
		}
		//System.out.println("useless");
		avail.add(new Classrooms("---",0,"---","------","00-00"));
		return avail;
	}
	public ArrayList<Classrooms> accroomt(String stime)
	{
		ArrayList<Classrooms> avail = new ArrayList<Classrooms>();
		try
		{Class.forName("java.sql.DriverManager");
	    Connection con=(Connection) DriverManager.getConnection(
	            "jdbc:mysql://localhost:3306/project","root","30july1998");
	    Statement stmt=(Statement) con.createStatement();
	    String time[] = stime.split("-");
	    String  q = "Select * from bookings where ((Start <='"+time[0]+"' and End >='"+time[0]+"') or (Start < '"+time[1]+"' and End >'"+time[1]+"'));";
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
			System.out.println("game is on");
		}
		//System.out.println("useless");
		avail.add(new Classrooms("---",0,"---","------","00-00"));
		return avail;
	}
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
	    { q = "Select * from bookings where ((Start <='"+Time[0]+"' and End >='"+Time[0]+"') or (Start < '"+Time[1]+"' and End >'"+Time[1]+"')) and RoomNo ='"+Room+"';";
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
			System.out.println("game is on");
		}
		//System.out.println("useless");
		avail.add(new Classrooms(Room,0,"---","------","00-00"));
		return avail;
	}
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
	    { q = "Select * from bookings where ((Start <='"+Time[0]+"' and End >='"+Time[0]+"') or (Start < '"+Time[1]+"' and End >'"+Time[0]+"')) and (Day = '"+Date+"' or Day ='"+Day+"') and RoomNo ='"+Room+"';" ;
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
			System.out.println("game is on");
		}
		//System.out.println("useless");
		avail.add(new Classrooms(Room,0,"---","------","00-00"));
		return avail;
	}
	public ResultSet ViewRoomBookings() {
		ResultSet rs=null;
		try
		{
			Class.forName("java.sql.DriverManager");
	        Connection con=(Connection) DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/project","root","30july1998");
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
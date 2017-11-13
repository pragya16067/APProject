package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Faculty extends User{
	
	public Faculty(String e, String pwd, String t) {
		super(e,pwd,"Faculty");
	}
	
	public String getName() {
		String e=this.email;
		String[] s=e.split("@");
		String Name=s[0];
		return Name.toUpperCase();
	}
	public boolean BookRoom(String Code, String Room, String STime, String ETime, String Day ,int Capacity,String dayname)
	{
		try
		{
			Class.forName("java.sql.DriverManager");
	        Connection con=(Connection) DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/project","root","30july1998");
	        Statement stmt=(Statement) con.createStatement();
	        
	        //Select * from bookings where Roomno = "C21" and Day ="Monday" and Start =11 and End =12;
	        String q ="Select * from bookings where RoomNo = '"+Room+"' and (Day ='"+Day+"'or Day ='"+dayname+"') and ((Start <='"+STime+"' and End >='"+STime+"') or (Start < '"+ETime+"' and End >'"+ETime+"'));";
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
			System.out.println("game is on");
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
			System.out.println("game is on");
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
			System.out.println("game is on");
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
	    { q = "Select * from bookings where ((Start <='"+Time[0]+"' and End >='"+Time[0]+"') or (Start < '"+Time[1]+"' and End >'"+Time[1]+"'));";
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
	    { q = "Select * from bookings where ((Start <='"+Time[0]+"' and End >='"+Time[0]+"') or (Start < '"+Time[1]+"' and End >'"+Time[1]+"')) and (Day = '"+Date+"' or Day ='"+Day+"');" ;
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
	public ArrayList<Bookings> GetBooking()
	{
		ArrayList<Bookings> book = new ArrayList<Bookings>();
		try
		{
			Class.forName("java.sql.DriverManager");
	        Connection con=(Connection) DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/project","root","30july1998");
	        Statement stmt=(Statement) con.createStatement();
	        String q ="Select * from bookings where type = 'Faculty' or type ='Student';";
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
package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

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
	        int Stime1 = Integer.parseInt(STime);
	        int Etime1 = Integer.parseInt(ETime);
	        //Select * from bookings where Roomno = "C21" and Day ="Monday" and Start =11 and End =12;
	        String q ="Select * from bookings where RoomNo = '"+Room+"' and (Day ='"+Day+"'or Day ='"+dayname+"') and Start <="+Stime1+" and End >="+Stime1+";";
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
	        	String q1 ="Insert into bookings values ('"+Room+"', '"+Code+"','"+Day+"',"+Stime1 +", "+Etime1+" );";
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
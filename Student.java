package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

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
	
	public ResultSet SearchCourses(String searchCode) {
		ResultSet rs=null;
		try
		{
			Class.forName("java.sql.DriverManager");
	        Connection con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","30july1998");
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
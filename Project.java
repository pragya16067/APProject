package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Project {
	String Name;
	int fid;
	int sid;
	String Field;
	String Type;
	String Status;
	
	public Project(String name, int f, int s, String field, String t, String stat ) {
		Name = name;
		fid = f;
		sid = s;
		Field = field;
		Type = t;
		Status = stat;
	}
	
	public String getName() {
		return Name;
	}
	
	public int getFid() {
		return fid;
	}
	
	public int getSid() {
		return sid;
	}
	
	public String getField() {
		return Field;
	}
	
	public String getType() {
		return Type;
	}
	
	public String getStatus() {
		return Status;
	}
	
	public int getPID() {
		int pid = -1;
		try {
			Class.forName("java.sql.DriverManager");
	        Connection con=(Connection) DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/project","root","tapeied");
	        Statement stmt=(Statement) con.createStatement();
	        String q = "Select pid from projects where StudentID = "+this.sid+" and FacultyID = "+this.fid+" and Topic = '"+this.Name+"' and Fields = '"
	        		+this.Field+"' and type = '"+this.Type+"' and status = '"+this.Status+"';";
	        ResultSet rs = stmt.executeQuery(q);
	        if(rs.next())
	        {
	        	pid = rs.getInt("pid");
	        }
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
        return pid;
	}
	

}

package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Classrooms {
public String RoomNo;
public int Capacity;
public String Availbility="Select Date/Day and Time";
public String Course="-----";
public String Time;
public Classrooms(String RoomNo, int c,String Available, String Course,String Time)
{
	this.RoomNo = RoomNo;
	this.Capacity= c;
	this.Availbility = Available;
	this.Course=Course;
	this.Time = Time;
}
public String getRoomNo()
{
	return RoomNo;
}
public String getAvailbility()
{
	return Availbility;
}
public String getCourse()
{
	return Course;
}
public Integer getCapacity()
{
	return Capacity;
}
	
public String getTime()
{
	return Time;
}

public void setCapacity(int Capacity)
{
	this.Capacity = Capacity;
}

}

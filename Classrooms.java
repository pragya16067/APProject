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
public Classrooms(String RoomNo, int c,String Available, String Course)
{
	this.RoomNo = RoomNo;
	this.Capacity= c;
	this.Availbility = Available;
	this.Course=Course;
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
			

}

package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @author Tanya Raj
 *
 */
public class Classrooms {
public String RoomNo;
public int Capacity;
public String Availbility="Select Date/Day and Time";
public String Course="-----";
public String Time;
/**
 * Constructor of Classrooms class
 * @param RoomNo
 * @param c
 * @param Available
 * @param Course
 * @param Time
 */
public Classrooms(String RoomNo, int c,String Available, String Course,String Time)
{
	this.RoomNo = RoomNo;
	this.Capacity= c;
	this.Availbility = Available;
	this.Course=Course;
	this.Time = Time;
}
/**
 * All the getter function are follows
 * @return
 */
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

/**
 * this setter function sets the capacity of the classroom.
 * @param Capacity
 */
public void setCapacity(int Capacity)
{
	this.Capacity = Capacity;
}

}

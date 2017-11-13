package application;

public class Timetable {
	private String Course;
	private String Day;
	private String Stime;
	private String Etime;
	private String Room;
	
	public Timetable(String c, String d, String st, String et, String room) {
		Course=c;
		Day=d;
		Stime=st;
		Etime=et;
		Room=room;
	}
	
	public String getCourse() {
		return Course;
	}
	
	public String getDay() {
		return Day;
	}
	
	public String getStime() {
		return Stime;
	}
	
	public String getEtime() {
		return Etime;
	}
	
	public String getRoom() {
		return Room;
	}
}

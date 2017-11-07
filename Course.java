package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Course {
	private String CourseCode;
	private String CourseName;
	private String Acronym;
	private String Faculty;
	private String Credits;
	private String Type;
	private String PC;
	
	public Course(String code, String name, String a, String f, String c, String t) {
		CourseCode=code;
		CourseName=name;
		Acronym=a;
		Faculty=f;
		Credits=c;
		Type=t;
	}
	
	public String getCourseCode() {
		return CourseCode;
	}
	
	public String getCourseName() {
		return CourseName;
	}
	
	public String getAcronym() {
		return Acronym;
	}
	
	public String getFaculty() {
		return Faculty;
	}
	
	public String getCredits() {
		return Credits;
	}
	
	public String getType() {
		return Type;
	}
	
	public void setCourseCode(String c) {
		CourseCode=c;
	}
	
	
}

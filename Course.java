package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Pragya
 * 
 * Class for Course object, to store all details of a Course offered in each semester
 *
 */
public class Course {
	private String CourseCode;
	private String CourseName;
	private String Acronym;
	private String Faculty;
	private int Credits;
	private String Type;
	//private String PC;
	
	
	/**
	 * 
	 *  Constructor
	 * 
	 * @param code
	 * @param name
	 * @param a
	 * @param f
	 * @param c
	 * @param t
	 */
	public Course(String code, String name, String a, String f, int c, String t) {
		
		CourseCode=code;
		CourseName=name;
		Acronym=a;
		Faculty=f;
		Credits=c;
		Type=t;
	}
	
	/**
	 * Getter method
	 * @return String
	 */
	public String getCourseCode() {
		return CourseCode;
	}
	
	/**
	 * Getter method
	 * @return String
	 */
	public String getCourseName() {
		return CourseName;
	}
	
	/**
	 * Getter method
	 * @return String
	 */
	public String getAcronym() {
		return Acronym;
	}
	
	/**
	 * Getter method
	 * @return String
	 */
	public String getFaculty() {
		return Faculty;
	}
	
	/**
	 * Getter method
	 * @return Integer
	 */
	public int getCredits() {
		return Credits;
	}
	
	/**
	 * Getter method
	 * @return String
	 */
	public String getType() {
		return Type;
	}
	
	/**
	 * Setter method
	 * @return String
	 */
	public void setCourseCode(String c) {
		CourseCode=c;
	}
	
	
}
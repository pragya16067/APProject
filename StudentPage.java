package GUIComponents;

import application.Course;
import application.Request;
import application.Timetable;
import application.Classrooms;
import application.Project;
import javafx.scene.Node;

import application.Student;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * @author Pragya
 *
 */
public class StudentPage  implements javafx.fxml.Initializable {
	/*
	 * Implementing Singleton design pattern 
	 */
	static Student student;
	
	/**
	 * To set the static Student object of this page
	 * 
	 * @param s - Student object 
	 */
	public void setStudent(Student s) {
		student=s;
	}
	
	@FXML
	Pane TimetablePane;
	@FXML
	Pane ProfilePane;
	@FXML
	Pane CoursesPane;
	@FXML
	Pane ClassroomsPane;
	@FXML
	Pane ProjectPane;
	@FXML
	Pane AddCoursesPane,DropCoursesPane,ViewCoursesPane;
	@FXML
	Pane RequestRoomPane,ViewStatusPane,AvaibilityPane;
	@FXML
	Pane LogoutPane1,LogoutPane2,Default,ChangePasswordPane;
	
	
	@FXML
	TextField Old,TXTnewpwd1,TXTnewpwd2,SearchBox,TXTpurpose,TXTroom,TXTcapacity,TXTtimeStart,TXTtimeEnd,TXTBTime,TXTBDay;
	@FXML
	DatePicker TXTdate,TXTBDate;
	@FXML
	Label LblName, LblBatch, LblRno;
	@FXML
	Button Timetable,Profile,Courses,Classrooms,AddC,ViewC,SearchC,DropC,AddedC,DroppedC,BackC,RequestR,ViewR,AvailableR,RequestedR,BackR1,BackR2,Logout,ChangePassword,Changed,LoginA,CheckAvailB;
	@FXML
	Button Projects, AddProjectB,DisplayProjectB;
	@FXML
	TextField TXTfaculty, TXTtopic, TXTtype, TXTfield;
	
	@FXML
	TableView<Project> TBLProjects;
	@FXML
	TableColumn<Project, String> Topic;
	@FXML
	TableColumn<Project, Integer> ProjFaculty;
	@FXML
	TableColumn<Project, String> ProjType;
	@FXML
	TableColumn<Project, String> Field;
	@FXML
	TableColumn<Project, String> Status;
	
	
	@FXML
	TableView<Course> ViewCoursesTable,AddCoursesTBL,DropCoursesTable;
	@FXML
	TableColumn<Course, String> Code,CCodeCol,CCode;
	@FXML
	TableColumn<Course, String> Name,CNameCol,CName;
	@FXML
	TableColumn<Course, String> Acronym,CAcronym;
	@FXML
	TableColumn<Course, String> Faculty,CFacultyCol,CFaculty;
	@FXML
	TableColumn<Course, Integer> Credits,CCreditsCol,CCredits;
	@FXML
	TableColumn<Course, String> Type,CType;
	
	@FXML
	TableView Tblavail;
	@FXML
	ComboBox TXTBRoom;
	
	@FXML
	TableColumn<Classrooms,String> RoomNo;
	@FXML
	TableColumn<Classrooms,Integer> Capacity;
	@FXML
	TableColumn<Classrooms,String> Avail;
	@FXML
	TableColumn<Classrooms,String> Time;
	@FXML
	TableColumn<Classrooms,String> Course;
	@FXML
	TableView<Request> ReqStatusTable;
	@FXML
	TableColumn<Request, String> RoomCol,PurposeCol,ReqCol,StatusCol;
	@FXML
	TableColumn<Request, Integer> CapCol;
	
	@FXML
	TableView<Timetable> TimetableTBL;
	@FXML
	TableColumn<Timetable,String> CourseNameCol,DayCol,STimeCol,ETimeCol,TTRoomCol;
	
	/**
	 * To get all the room nos. in the institute from the rooms table in database
	 */
	public void getrooms()
	{
		try
		{
			Class.forName("java.sql.DriverManager");
	        Connection con=(Connection) DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/project","root","tapeied");
	        Statement stmt=(Statement) con.createStatement();
	        String q = "Select RoomNo from rooms ;";
	        //System.out.println(q);
	        ResultSet rs = stmt.executeQuery(q);
	        ArrayList<String> list = new ArrayList<String>();
	        while(rs.next())
	        {  //System.out.println(rs.getString("RoomNo"));
	        	list.add(rs.getString("RoomNo"));
	        	//options.add("C01");
	        }
	        ObservableList<String> options = FXCollections.observableArrayList(list);
	        TXTBRoom.setItems(options);
	        
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}
	
	
	
	@Override	
	public void initialize(URL location, ResourceBundle resources) {
		
		//Change panes to show only the ProjectPane visible
		Projects.setOnAction(new EventHandler<ActionEvent> () {
			
			@Override
			public void handle(ActionEvent event) {
				ProfilePane.setVisible(false);
				TimetablePane.setVisible(false);
				CoursesPane.setVisible(false);
				ClassroomsPane.setVisible(false);
				AddCoursesPane.setVisible(false);
				 DropCoursesPane.setVisible(false);
				 ViewCoursesPane.setVisible(false);
				 RequestRoomPane.setVisible(false);
				 ViewStatusPane.setVisible(false);
				 AvaibilityPane.setVisible(false);
				 LogoutPane1.setVisible(false);
				 LogoutPane2.setVisible(false);
				 Default.setVisible(false);
				 ChangePasswordPane.setVisible(false);
				 ProjectPane.setVisible(true);
			}
		});
		
		AddProjectB.setOnAction(new EventHandler<ActionEvent> () {
			
			@Override
			public void handle(ActionEvent event) {
				String facultyemail, topic, type, field;
				
				facultyemail = TXTfaculty.getText();
				topic = TXTtopic.getText();
				field = TXTfield.getText();
				type = TXTtype.getText();
				
				if(facultyemail.equals("") || topic.equals("") || field.equals("") || type.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please enter text in all fields!");
				}
				else {
					try {
						String semail = student.getemail();
						int sid=-1 , fid= -1;
						Class.forName("java.sql.DriverManager");
				        Connection con=(Connection) DriverManager.getConnection(
				                "jdbc:mysql://localhost:3306/project","root","tapeied");
				        Statement stmt=(Statement) con.createStatement();
				        String q = "Select uid from users where email ='"+ semail + "';";
				        ResultSet rs = stmt.executeQuery(q);
				        if(rs.next())
				        {
				        	sid = rs.getInt("uid");
				        }
				        q = "Select uid from users where email ='"+ facultyemail + "';";
				        rs = stmt.executeQuery(q);
				        if(rs.next())
				        {
				        	fid = rs.getInt("uid");
				        }
				        if(fid == -1)
				        {
				        	JOptionPane.showMessageDialog(null, "This Faculty email id is incorrect!");
				        }
				        else
				        {
				        
					        q = "SELECT MAX(pid)+1 from Projects;";
					        rs = stmt.executeQuery(q);
					        rs.next();
					        int pid = rs.getInt("MAX(pid)+1");
					        if(pid == 0)
					        	pid = 1;
					        System.out.println("value of pid is " + pid);
					        q = "Insert into Projects values ("+pid+", "+sid+", "+fid+", '"+topic+"', '"+field+"', 'Pending', '"+type+"');";
					        System.out.println(stmt.executeUpdate(q));
				        }
					}
					catch(Exception e)
					{
						System.out.println(e.getMessage());
					}
				}
				
				ProfilePane.setVisible(false);
				TimetablePane.setVisible(false);
				CoursesPane.setVisible(false);
				ClassroomsPane.setVisible(false);
				AddCoursesPane.setVisible(false);
				DropCoursesPane.setVisible(false);
				ViewCoursesPane.setVisible(false);
				 RequestRoomPane.setVisible(false);
				 ViewStatusPane.setVisible(false);
				 AvaibilityPane.setVisible(false);
				 LogoutPane1.setVisible(false);
				 LogoutPane2.setVisible(false);
				 Default.setVisible(false);
				 ChangePasswordPane.setVisible(false);
				 ProjectPane.setVisible(true);
			}
		});
		
		DisplayProjectB.setOnAction(new EventHandler<ActionEvent> () {
			
			@Override
			public void handle(ActionEvent event) {
				
				TBLProjects.setItems(null);
				TBLProjects.setEditable(true);
				ArrayList<Project> projects=new ArrayList<Project> ();
				try
				{
					
					ResultSet rs=student.getProjects();
					while(rs.next()) {
						Project p = new Project(rs.getString("Topic"), rs.getInt("FacultyID"), rs.getInt("StudentID"), rs.getString("Fields"), 
								 rs.getString("type"), rs.getString("status"));
						projects.add(p);
					}
					
				}
				catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
				
				ObservableList<Project> l=FXCollections.observableArrayList(projects);
				TBLProjects.setItems(l);
        		
        		Topic.setCellValueFactory(new PropertyValueFactory<Project,String>("Name"));
        		ProjFaculty.setCellValueFactory(new PropertyValueFactory<Project,Integer>("fid"));
        		ProjType.setCellValueFactory(new PropertyValueFactory<Project,String>("Type"));
        		Field.setCellValueFactory(new PropertyValueFactory<Project,String>("Field"));
        		Status.setCellValueFactory(new PropertyValueFactory<Project,String>("Status"));
        		
				ProfilePane.setVisible(false);
				TimetablePane.setVisible(false);
				CoursesPane.setVisible(false);
				ClassroomsPane.setVisible(false);
				AddCoursesPane.setVisible(false);
				DropCoursesPane.setVisible(false);
				ViewCoursesPane.setVisible(false);
				 RequestRoomPane.setVisible(false);
				 ViewStatusPane.setVisible(false);
				 AvaibilityPane.setVisible(false);
				 LogoutPane1.setVisible(false);
				 LogoutPane2.setVisible(false);
				 Default.setVisible(false);
				 ChangePasswordPane.setVisible(false);
				 ProjectPane.setVisible(true);
			}
		});
		
		/**
		 * Change panes to show the Change password pane
		 */
		ChangePassword.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				ProfilePane.setVisible(false);
				TimetablePane.setVisible(false);
				CoursesPane.setVisible(false);
				ClassroomsPane.setVisible(false);
				AddCoursesPane.setVisible(false);
				DropCoursesPane.setVisible(false);
				ViewCoursesPane.setVisible(false);
				 RequestRoomPane.setVisible(false);
				 ViewStatusPane.setVisible(false);
				 AvaibilityPane.setVisible(false);
				 LogoutPane1.setVisible(false);
				 LogoutPane2.setVisible(false);
				 Default.setVisible(false);
				 ChangePasswordPane.setVisible(true);
				 ProjectPane.setVisible(false);
			}
			
		});	
	
		/**
		 * Change password in the database and go back to profile pane
		 */
		Changed.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				String np1=TXTnewpwd1.getText();
				String np2=TXTnewpwd2.getText();
				String op = Old.getText();
				if(np1.equals(np2) && student.AuthenticateUser(student.getemail(), op))
				{
					student.changePassword(np1);
					JOptionPane.showMessageDialog(null, "Password Changed Succesfully");
				}
				else
				if(!student.AuthenticateUser(student.getemail(), op))
				{
					JOptionPane.showMessageDialog(null, "Your Old Password is incorrect. Please Try Again!");
					Old.setText("");
					TXTnewpwd1.setText("");
					TXTnewpwd2.setText("");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please make sure that your new passwords match");
					TXTnewpwd1.setText("");
					TXTnewpwd2.setText("");
				}
				
				ProfilePane.setVisible(true);
				TimetablePane.setVisible(false);
				CoursesPane.setVisible(false);
				ClassroomsPane.setVisible(false);
				AddCoursesPane.setVisible(false);
				DropCoursesPane.setVisible(false);
				ViewCoursesPane.setVisible(false);
				 RequestRoomPane.setVisible(false);
				 ViewStatusPane.setVisible(false);
				 AvaibilityPane.setVisible(false);
				 LogoutPane1.setVisible(false);
				 LogoutPane2.setVisible(false);
				 Default.setVisible(false);
				 ChangePasswordPane.setVisible(false);
				 ProjectPane.setVisible(false);
			}
			
		});	
	
		Timetable.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			/**
			 * Change panes and go to Timetable.fxml
			 */
				
				
				 public void handle(ActionEvent event) {
				ProfilePane.setVisible(false);
				TimetablePane.setVisible(false);
				CoursesPane.setVisible(false);
				ClassroomsPane.setVisible(false);
				AddCoursesPane.setVisible(false);
				DropCoursesPane.setVisible(false);
				ViewCoursesPane.setVisible(false);
				 RequestRoomPane.setVisible(false);
				 ViewStatusPane.setVisible(false);
				 AvaibilityPane.setVisible(false);
				 LogoutPane1.setVisible(false);
				 LogoutPane2.setVisible(false);
				 Default.setVisible(true);
				 ChangePasswordPane.setVisible(false);
				 TimetableTBL.setItems(null);
				 TimetableTBL.setEditable(true);
				 ProjectPane.setVisible(false);
				 
				 
				
						try
						{
						Parent page =  FXMLLoader.load(getClass().getResource("Timetable.fxml"));
						Scene scene = new Scene(page);
						Stage page_stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
						page_stage.setScene(scene);
						page_stage.show();
						}
						catch(Exception exp)
						{
							System.out.println(exp.getMessage());
						}
					}
				});
				 
				 
				 
		
		/**
		 * Change to profile pane 
		 */
		Profile.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				LblName.setText(student.getName());
				LblBatch.setText(student.getBatch());
				LblRno.setText(student.getRno());
				
				ProfilePane.setVisible(true);
				TimetablePane.setVisible(false);
				CoursesPane.setVisible(false);
				ClassroomsPane.setVisible(false);
				AddCoursesPane.setVisible(false);
				DropCoursesPane.setVisible(false);
				ViewCoursesPane.setVisible(false);
				RequestRoomPane.setVisible(false);
				 ViewStatusPane.setVisible(false);
				 AvaibilityPane.setVisible(false);
				 LogoutPane1.setVisible(false);
				 LogoutPane2.setVisible(false);
				 Default.setVisible(false);
				 ChangePasswordPane.setVisible(false);
				 ProjectPane.setVisible(false);
				
				
			}
			
		});	

		
		Courses.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				ProfilePane.setVisible(false);
				TimetablePane.setVisible(false);
				CoursesPane.setVisible(true);
				ClassroomsPane.setVisible(false);
				AddCoursesPane.setVisible(false);
				DropCoursesPane.setVisible(false);
				ViewCoursesPane.setVisible(false);
				RequestRoomPane.setVisible(false);
				 ViewStatusPane.setVisible(false);
				 AvaibilityPane.setVisible(false);
				 LogoutPane1.setVisible(false);
				 LogoutPane2.setVisible(false);
				 Default.setVisible(false);
				 ChangePasswordPane.setVisible(false);
				 ProjectPane.setVisible(false);
				
			}
			
		});	
		
		/**
		 * Change to display the Manage Room Bookings pane
		 */
		Classrooms.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				ProfilePane.setVisible(false);
				TimetablePane.setVisible(false);
				CoursesPane.setVisible(false);
				ClassroomsPane.setVisible(true);
				AddCoursesPane.setVisible(false);
				DropCoursesPane.setVisible(false);
				ViewCoursesPane.setVisible(false);
				RequestRoomPane.setVisible(false);
				 ViewStatusPane.setVisible(false);
				 AvaibilityPane.setVisible(false);
				 LogoutPane1.setVisible(false);
				 LogoutPane2.setVisible(false);
				 Default.setVisible(false);
				 ChangePasswordPane.setVisible(false);
				 ProjectPane.setVisible(false);
				
			}
			
		});
		
		/**
		 * Change to the Add courses pane to allow student to choose and add a new course
		 * Display in a table all the courses of the semester that a student can join
		 */
		AddC.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				ProfilePane.setVisible(false);
				TimetablePane.setVisible(false);
				CoursesPane.setVisible(false);
				ClassroomsPane.setVisible(false);
				AddCoursesPane.setVisible(true);
				DropCoursesPane.setVisible(false);
				ViewCoursesPane.setVisible(false);
				RequestRoomPane.setVisible(false);
				 ViewStatusPane.setVisible(false);
				 AvaibilityPane.setVisible(false);
				 LogoutPane1.setVisible(false);
				 LogoutPane2.setVisible(false);
				 Default.setVisible(false);
				 ChangePasswordPane.setVisible(false);
				 ProjectPane.setVisible(false);
				 
				 AddCoursesTBL.setItems(null);
				AddCoursesTBL.setEditable(true);
				ArrayList<Course> courses=new ArrayList<Course> ();
				try
				{
					
					ResultSet rs=student.SearchCourses("");
					while(rs.next()) {
						Course c=new Course(rs.getString("CourseCode"),rs.getString("CourseName"),"a", rs.getString("Faculty"),rs.getInt("Credits"),"Elective");
						courses.add(c);
					}
					
				
				}
				catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
				
				ObservableList<Course> l=FXCollections.observableArrayList(courses);
				AddCoursesTBL.setItems(l);
        		
        		CCodeCol.setCellValueFactory(new PropertyValueFactory<Course,String>("CourseCode"));
        		CNameCol.setCellValueFactory(new PropertyValueFactory<Course,String>("CourseName"));
        		CFacultyCol.setCellValueFactory(new PropertyValueFactory<Course,String>("Faculty"));
        		CCreditsCol.setCellValueFactory(new PropertyValueFactory<Course,Integer>("Credits"));
				
			}
			
		});	
		
		/**
		 * Code for the Action performed on the click of the search button in searching a new course by postconditions specifies by the user
		 */
		SearchC.setOnAction(new EventHandler<ActionEvent> () {
			
			@Override
			public void handle(ActionEvent event) {
				AddCoursesTBL.setItems(null);
				AddCoursesTBL.setEditable(true);
				ArrayList<Course> courses=new ArrayList<Course> ();
				try
				{
					
					String searchKey = (SearchBox.getText());
					ResultSet rs=student.SearchCourses(searchKey);
					while(rs.next()) {
						Course c=new Course(rs.getString("CourseCode"),rs.getString("CourseName"),"a", rs.getString("Faculty"),rs.getInt("Credits"),"Elective");
						courses.add(c);
					}
					
				
				}
				catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
				
				ObservableList<Course> l=FXCollections.observableArrayList(courses);
				AddCoursesTBL.setItems(l);
        		
        		CCodeCol.setCellValueFactory(new PropertyValueFactory<Course,String>("CourseCode"));
        		CNameCol.setCellValueFactory(new PropertyValueFactory<Course,String>("CourseName"));
        		CFacultyCol.setCellValueFactory(new PropertyValueFactory<Course,String>("Faculty"));
        		CCreditsCol.setCellValueFactory(new PropertyValueFactory<Course,Integer>("Credits"));
			}
			
		});
		
		/**
		 * Move to a new pane to View all details of the courses enrolled by a student
		 */
		ViewC.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				ProfilePane.setVisible(false);
				TimetablePane.setVisible(false);
				CoursesPane.setVisible(false);
				ClassroomsPane.setVisible(false);
				AddCoursesPane.setVisible(false);
				DropCoursesPane.setVisible(false);
				ViewCoursesPane.setVisible(true);
				RequestRoomPane.setVisible(false);
				 ViewStatusPane.setVisible(false);
				 AvaibilityPane.setVisible(false);
				 LogoutPane1.setVisible(false);
				 LogoutPane2.setVisible(false);
				 Default.setVisible(false);
				 ChangePasswordPane.setVisible(false);
				 ProjectPane.setVisible(false);
				 
				 try
				 {
					 ViewCoursesTable.setItems(null);
					 
					 Class.forName("java.sql.DriverManager");
				     Connection con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","tapeied");
				     Statement stmt=(Statement) con.createStatement();
				        
					 ResultSet rs=student.ViewCourses();
					 
					 ViewCoursesTable.setEditable(true);
		        		
					//ViewCoursesTable.setEditable(true);
		        		
					 if(rs.next())
				        {
				        	String[] s=rs.getString("CoursesTaken").split(";");
				        	ObservableList<Course> l=FXCollections.observableArrayList();
				        	for(int i=0; i<s.length; i++)
				        	{
				        		String CourseCode=s[i];
				        		String q="Select CourseCode,CourseName,Acronym,Faculty,Credits,Type from courses where CourseCode='"+CourseCode+"';";
				        		ResultSet CData= stmt.executeQuery(q);
				        		
				        		while(CData.next())
				        		{
				        			
				        			Course c=new Course(CData.getString("CourseCode"),CData.getString("CourseName"),CData.getString("Acronym"),CData.getString("Faculty"),CData.getInt("Credits"),CData.getString("Type"));
				        			
				        			l.add(c);
				        			
				        		}
				                  
				        		//System.out.println(l.get(i).getCourseCode()+" "+l.get(i).getCourseName());
				        	}	
				        		//System.out.println(l.size());
				        		ViewCoursesTable.setItems(l);
				        		
				        		Code.setCellValueFactory(new PropertyValueFactory<Course,String>("CourseCode"));
				        		Name.setCellValueFactory(new PropertyValueFactory<Course,String>("CourseName"));
				        		Acronym.setCellValueFactory(new PropertyValueFactory<Course,String>("Acronym"));
				        		Faculty.setCellValueFactory(new PropertyValueFactory<Course,String>("Faculty"));
				        		Credits.setCellValueFactory(new PropertyValueFactory<Course,Integer>("Credits"));
				        		Type.setCellValueFactory(new PropertyValueFactory<Course,String>("Type"));
				        		
				        	
				        }
					 
				 }
				 catch(Exception e)
				 {
					 System.out.println(e.getMessage());
				 }
				
			}
			
		});	
		
		/**
		 * Change to Drop Courses pane and display all courses that a student is enrolled in
		 */
		DropC.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				ProfilePane.setVisible(false);
				TimetablePane.setVisible(false);
				CoursesPane.setVisible(false);
				ClassroomsPane.setVisible(false);
				AddCoursesPane.setVisible(false);
				DropCoursesPane.setVisible(true);
				ViewCoursesPane.setVisible(false);
				RequestRoomPane.setVisible(false);
				 ViewStatusPane.setVisible(false);
				 AvaibilityPane.setVisible(false);
				 LogoutPane1.setVisible(false);
				 LogoutPane2.setVisible(false);
				 Default.setVisible(false);
				 ChangePasswordPane.setVisible(false);
				 ProjectPane.setVisible(false);
				
				 //CODE for correctly labelling all course checkboxes!!
				 ArrayList<Course> c=new ArrayList<Course> ();
				 try
				 {
					 ResultSet rs=student.ViewCourses();
					 if(rs.next()) {
						 String[] s=rs.getString("CoursesTaken").split(";");
				        	
				        	for(int i=0; i<s.length; i++)
				        	{
				        		Class.forName("java.sql.DriverManager");
							    Connection con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","tapeied");
							    Statement stmt=(Statement) con.createStatement();
				        		String CourseCode=s[i];
				        		String q="Select CourseCode,CourseName,Acronym,Faculty,Credits,Type from courses where CourseCode='"+CourseCode+"';";
				        		ResultSet CData= stmt.executeQuery(q);
				        		
				        		while(CData.next())
				        		{
				        			
				        			Course course=new Course(CData.getString("CourseCode"),CData.getString("CourseName"),CData.getString("Acronym"),CData.getString("Faculty"),CData.getInt("Credits"),CData.getString("Type"));
				        			
				        			c.add(course);
				        			
				        		}
				                  
				        		//System.out.println(l.get(i).getCourseCode()+" "+l.get(i).getCourseName());
				        	}	
				        		//System.out.println(l.size());
				        	ObservableList<Course> l=FXCollections.observableArrayList(c);
			        		DropCoursesTable.setItems(l);
			        		
			        		CCode.setCellValueFactory(new PropertyValueFactory<Course,String>("CourseCode"));
			        		CName.setCellValueFactory(new PropertyValueFactory<Course,String>("CourseName"));
			        		CAcronym.setCellValueFactory(new PropertyValueFactory<Course,String>("Acronym"));
			        		CFaculty.setCellValueFactory(new PropertyValueFactory<Course,String>("Faculty"));
			        		CCredits.setCellValueFactory(new PropertyValueFactory<Course,Integer>("Credits"));
			        		CType.setCellValueFactory(new PropertyValueFactory<Course,String>("Type"));
			        		
					 }
					 
				 }
				 catch(Exception e)
				 {
					 System.out.println(e.getMessage());
				 }
				 finally
				 {
					 
				 }
				
			}
			
		});	
		
		/**
		 * Add the course selected by a student from a table in the database, and move back to home pane
		 */
		AddedC.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				try
				{
					Course addC = (Course) AddCoursesTBL.getSelectionModel().getSelectedItem();
					
					if(student.okToAddCourse(addC))
					{
						student.AddCourse(addC);
						JOptionPane.showMessageDialog(null, "The selected course was successfully added");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Sorry you cannot add selected course due to clashes with Timetable or due to already having 5 courses");
					}
				}
				catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
				
				
				ProfilePane.setVisible(false);
				TimetablePane.setVisible(false);
				CoursesPane.setVisible(true);
				ClassroomsPane.setVisible(false);
				AddCoursesPane.setVisible(false);
				DropCoursesPane.setVisible(false);
				ViewCoursesPane.setVisible(false);
				RequestRoomPane.setVisible(false);
				 ViewStatusPane.setVisible(false);
				 AvaibilityPane.setVisible(false);
				 LogoutPane1.setVisible(false);
				 LogoutPane2.setVisible(false);
				 Default.setVisible(false);
				 ChangePasswordPane.setVisible(false);
				 ProjectPane.setVisible(false);
				
				
			}
			
		});	
		
		/**
		 * Drop the course from database, selected by the student from a TableView, and move back to home page
		 */
       DroppedC.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				//Code to Drop the selected courses from database entry
				try {
					Course dropC = (Course) DropCoursesTable.getSelectionModel().getSelectedItem();
					
					if(dropC.getType().toLowerCase().equals("mandatory"))
					{
						JOptionPane.showMessageDialog(null, "Sorry, this is a mandatory CSE 2nd year Monsoon semester Course!");
					}
					else
					{
						String CourseName=dropC.getCourseName().toLowerCase();
						student.DropCourse(CourseName);
					}
				}
				catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
				
				ProfilePane.setVisible(false);
				TimetablePane.setVisible(false);
				CoursesPane.setVisible(true);
				ClassroomsPane.setVisible(false);
				AddCoursesPane.setVisible(false);
				DropCoursesPane.setVisible(false);
				ViewCoursesPane.setVisible(false);
				RequestRoomPane.setVisible(false);
				 ViewStatusPane.setVisible(false);
				 AvaibilityPane.setVisible(false);
				 LogoutPane1.setVisible(false);
				 LogoutPane2.setVisible(false);
				 Default.setVisible(false);
				 ChangePasswordPane.setVisible(false);
				 ProjectPane.setVisible(false);
				
			}
			
		});	
       
       /**
		 * Change back to Course-related-action pane
		 */
      BackC.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				ProfilePane.setVisible(false);
				TimetablePane.setVisible(false);
				CoursesPane.setVisible(true);
				ClassroomsPane.setVisible(false);
				AddCoursesPane.setVisible(false);
				DropCoursesPane.setVisible(false);
				ViewCoursesPane.setVisible(false);
				RequestRoomPane.setVisible(false);
				 ViewStatusPane.setVisible(false);
				 AvaibilityPane.setVisible(false);
				 LogoutPane1.setVisible(false);
				 LogoutPane2.setVisible(false);
				 Default.setVisible(false);
				 ChangePasswordPane.setVisible(false);
				 ProjectPane.setVisible(false);
				
			}
			
		});
      
      /**
		 * Change to the RequestRoom Pane to request for a room booking
		 */
      RequestR.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				System.out.println("I m here");
				ProfilePane.setVisible(false);
				TimetablePane.setVisible(false);
				CoursesPane.setVisible(false);
				ClassroomsPane.setVisible(false);
				AddCoursesPane.setVisible(false);
				DropCoursesPane.setVisible(false);
				ViewCoursesPane.setVisible(false);
				RequestRoomPane.setVisible(true);
				 ViewStatusPane.setVisible(false);
				 AvaibilityPane.setVisible(false);
				 LogoutPane1.setVisible(false);
				 LogoutPane2.setVisible(false);
				 Default.setVisible(false);
				 ChangePasswordPane.setVisible(false);
				 ProjectPane.setVisible(false);
			}
			
		});	
      
      /**
		 * Move to the ViewStatus Pane to see the status of all previous requests requested by the student, i.e. whether it has been accepted, rejected by admin or is still pending action.
		 */
      ViewR.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				ProfilePane.setVisible(false);
				TimetablePane.setVisible(false);
				CoursesPane.setVisible(false);
				ClassroomsPane.setVisible(false);
				AddCoursesPane.setVisible(false);
				DropCoursesPane.setVisible(false);
				ViewCoursesPane.setVisible(false);
				RequestRoomPane.setVisible(false);
				 ViewStatusPane.setVisible(true);
				 AvaibilityPane.setVisible(false);
				 LogoutPane1.setVisible(false);
				 LogoutPane2.setVisible(false);
				 Default.setVisible(false);
				 ChangePasswordPane.setVisible(false);
				 ProjectPane.setVisible(false);
				 
				 try
				 {
					 ReqStatusTable.setItems(null);
					 ReqStatusTable.setEditable(true);
				         try
						{
							Class.forName("java.sql.DriverManager");
					        Connection con=(Connection) DriverManager.getConnection(
					                "jdbc:mysql://localhost:3306/project","root","tapeied");
					        Statement stmt=(Statement) con.createStatement();
					        String q="Select * from requests ;";
					        ResultSet rs=stmt.executeQuery(q);
					        if(rs.next()) {
					        	String status=rs.getString("status");
					        	
					        	if(status.equals("Pending"))
					        	{
					        		//gets the current date and time
					        		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
					        		Calendar cal = Calendar.getInstance();
					        		String curDate = (dateFormat.format(cal.getTime()));
					        		Date d1=cal.getTime();
					        		int Rid = rs.getInt("rid");
					        		Date d2=rs.getDate("StartDate");
					        		long diff = (d1.getTime() - d2.getTime()) / (1000 * 60 * 60 * 24);
					        		
					        		if(diff > 5)
					        		{
					        			String q2="Update requests set status='Rejected' where rid="+Rid+";";
					        			stmt.executeUpdate(q2);
					        			System.out.println(q2);
					        		}
					        		
					        	}
					        }
						}
						catch(Exception exp)
						{
							System.out.println(exp.getMessage());
						}
					 ArrayList<Request> r=student.ViewRequests();
					 ObservableList<Request> l=FXCollections.observableArrayList(r);
					 
					 ReqStatusTable.setItems(l);
					 
					 RoomCol.setCellValueFactory(new PropertyValueFactory<Request,String>("RoomN"));
					 PurposeCol.setCellValueFactory(new PropertyValueFactory<Request,String>("purpose"));
					 CapCol.setCellValueFactory(new PropertyValueFactory<Request,Integer>("CapacityN"));
					 ReqCol.setCellValueFactory(new PropertyValueFactory<Request,String>("DateTimeRequested"));
					 StatusCol.setCellValueFactory(new PropertyValueFactory<Request,String>("Status"));
					 
				 }
				 catch(Exception e)
				 {
					 System.out.println(e.getMessage());
				 }
			}
			
		});
      
      /**
		 * Change to AvailabilityPane where user can see all room bookings details
		 */
      AvailableR.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				ProfilePane.setVisible(false);
				TimetablePane.setVisible(false);
				CoursesPane.setVisible(false);
				ClassroomsPane.setVisible(false);
				AddCoursesPane.setVisible(false);
				DropCoursesPane.setVisible(false);
				ViewCoursesPane.setVisible(false);
				RequestRoomPane.setVisible(false);
				 ViewStatusPane.setVisible(false);
				 AvaibilityPane.setVisible(true);
				 LogoutPane1.setVisible(false);
				 LogoutPane2.setVisible(false);
				 Default.setVisible(false);
				 ChangePasswordPane.setVisible(false);
				 ProjectPane.setVisible(false);
				 getrooms();
			}
			
		});	
      
      /**
		 * Check availability of room bookings according to search parameters given by the student
		 */
		CheckAvailB.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				String Room = (String) TXTBRoom.getValue();
				System.out.println("room"+Room);
				System.out.println("date"+TXTBDate.getValue());
				System.out.println("day"+TXTBDay.getText());
				System.out.println("time"+TXTBTime.getText());
				ArrayList<Classrooms> list= new ArrayList<Classrooms>();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
				if(TXTBDate.getValue()==null && TXTBTime.getText().equals("") && TXTBDay.getText().equals(""))
				{
				System.out.println("1a");
				list = student.accroom(Room);
				}
				if((TXTBDate.getValue()!=null || !TXTBDay.getText().equals("")) && TXTBTime.getText().equals(""))
				{
					System.out.println("1b");
					String Date="",Day="";
					if(TXTBDate.getValue()!=null)
					{ System.out.println("1f");
						Date = TXTBDate.getValue().format(formatter);	
						Day =  TXTBDate.getValue().getDayOfWeek().name();
					}
					else
					{ System.out.println("1g");
						Day = TXTBDay.getText();
					}
				list = student.accroom(Room,Date,Day);
				}
				if((TXTBDate.getValue()!=null || !TXTBDay.getText().equals("")) && TXTBTime.getText().equals("") && Room==null)
				{
					System.out.println("1c");
					
					String Date="",Day="";
					if(TXTBDate.getValue()!=null)
					{ //System.out.println("1f");
						Date = TXTBDate.getValue().format(formatter);	
						Day =  TXTBDate.getValue().getDayOfWeek().name();
					}
					else
					{ //System.out.println("1g");
						Day = TXTBDay.getText();
					}
				list = student.accroom(Date,Day);
				}
				if(TXTBDate.getValue()==null && TXTBDay.getText().equals("") && !TXTBTime.getText().equals("") && Room!=null )
				{
					System.out.println("1d");
				String Times = TXTBTime.getText();
				list = student.accroomt(Room,Times);
				}
				if(TXTBDate.getValue()==null&& TXTBDay.getText().equals("") && !TXTBTime.getText().equals("") && Room==null)
				{
					System.out.println("1e");
				String Times = TXTBTime.getText();
				list = student.accroomt(Times);
				}
				if((TXTBDate.getValue()!=null || !TXTBDay.getText().equals("")) && !TXTBTime.getText().equals(""))
				{
					String Date="",Day="";
				if(TXTBDate.getValue()!=null)
				{ System.out.println("1f");
					Date = TXTBDate.getValue().format(formatter);	
					Day =  TXTBDate.getValue().getDayOfWeek().name();
				}
				else
				{ System.out.println("1g");
					Day = TXTBDay.getText();
				}
				
				String Times = TXTBTime.getText();
				list = student.accroom(Room,Date,Day,Times);
				}
				if(TXTBDate.getValue()==null && TXTBTime.getText().equals("") && Room==null && TXTBDay.getText().equals(""))
				{System.out.println("1h");
					JOptionPane.showMessageDialog(null, "Showing All Available records");
					list = student.accroom();
				}
				//System.out.println(list.get(0).RoomNo+" "+list.get(0).Course);
				ObservableList lists = FXCollections.observableArrayList(list);
				
				 System.out.println(list.size());
				Tblavail.setItems(lists);
				RoomNo.setCellValueFactory(new PropertyValueFactory<Classrooms,String>("RoomNo"));
				Capacity.setCellValueFactory(new PropertyValueFactory<Classrooms,Integer>("Capacity"));
				Avail.setCellValueFactory(new PropertyValueFactory<Classrooms,String>("Availbility"));
				Course.setCellValueFactory(new PropertyValueFactory<Classrooms,String>("Course"));
				Time.setCellValueFactory(new PropertyValueFactory<Classrooms,String>("Time"));
				
				TXTBRoom.getSelectionModel().clearSelection();
				TXTBDate.getEditor().clear();
				TXTBDay.setText("");
				TXTBTime.setText("");
				
				
				
			}
			
		});	
		
		/**
		 * Change back to Course-related-action pane
		 */
      BackR1.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				System.out.println("I m here");
				ProfilePane.setVisible(false);
				TimetablePane.setVisible(false);
				CoursesPane.setVisible(false);
				ClassroomsPane.setVisible(true);
				AddCoursesPane.setVisible(false);
				DropCoursesPane.setVisible(false);
				ViewCoursesPane.setVisible(false);
				RequestRoomPane.setVisible(false);
				 ViewStatusPane.setVisible(false);
				 AvaibilityPane.setVisible(false);
				 LogoutPane1.setVisible(false);
				 LogoutPane2.setVisible(false);
				 Default.setVisible(false);
				 ChangePasswordPane.setVisible(false);
				 ProjectPane.setVisible(false);
			}
			
		});	
      
      /**
		 * Change back to Course-related-action pane
		 */
      BackR2.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				ProfilePane.setVisible(false);
				TimetablePane.setVisible(false);
				CoursesPane.setVisible(false);
				ClassroomsPane.setVisible(true);
				AddCoursesPane.setVisible(false);
				DropCoursesPane.setVisible(false);
				ViewCoursesPane.setVisible(false);
				RequestRoomPane.setVisible(false);
				 ViewStatusPane.setVisible(false);
				 AvaibilityPane.setVisible(false);
				 LogoutPane1.setVisible(false);
				 LogoutPane2.setVisible(false);
				 Default.setVisible(false);
				 ChangePasswordPane.setVisible(false);
				 ProjectPane.setVisible(false);
				
				
			}
			
		});	
      
      /**
		 * Change back to home page and make the required changes in the database to accommodate a new request
		 */
      RequestedR.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				try {
					String purpose=TXTpurpose.getText();
					String room=TXTroom.getText();
					
					String date=TXTdate.getValue().toString();
					String stime=TXTtimeStart.getText();
					String etime=TXTtimeEnd.getText();
					
					if(purpose.equals("") || room.equals("") || TXTcapacity.getText().equals("") || date.equals("") || stime.equals("") || etime.equals(""))
					{
						JOptionPane.showMessageDialog(null, "Please fill all required input fields!!");
					}
					else {
						int cap=Integer.parseInt(TXTcapacity.getText());
						student.MakeRequest(purpose, room, cap, date, stime, etime);
						System.out.println(date+" "+stime);
					}
				}
				catch(Exception e) {
					System.out.println(e.getMessage());
				}
				ProfilePane.setVisible(false);
				TimetablePane.setVisible(false);
				CoursesPane.setVisible(false);
				ClassroomsPane.setVisible(true);
				AddCoursesPane.setVisible(false);
				DropCoursesPane.setVisible(false);
				ViewCoursesPane.setVisible(false);
				RequestRoomPane.setVisible(false);
				 ViewStatusPane.setVisible(false);
				 AvaibilityPane.setVisible(false);
				 LogoutPane1.setVisible(false);
				 LogoutPane2.setVisible(false);
				 Default.setVisible(false);
				 ChangePasswordPane.setVisible(false);
				 ProjectPane.setVisible(false);
			}
			
		});	
      
      /**
		 * Logout from the application and ask whether user wishes to login again or exit the application
		 */
      Logout.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				try
				{
				Parent page =  FXMLLoader.load(getClass().getResource("Logout.fxml"));
				Scene scene = new Scene(page);
				Stage page_stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
				page_stage.setScene(scene);
				page_stage.show();
				}
				catch(Exception exp)
				{
					System.out.println(exp.getMessage());
				}
			}
		});
			
    
	}	
	
	

}
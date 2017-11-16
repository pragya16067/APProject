package GUIComponents;

import application.Course;
import application.Request;
import application.Timetable;
import application.Classrooms;
import javafx.scene.Node;

import application.Student;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;

public class StudentPage  implements javafx.fxml.Initializable {
	static Student student;
	
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
	Pane AddCoursesPane,DropCoursesPane,ViewCoursesPane;
	@FXML
	Pane RequestRoomPane,ViewStatusPane,AvaibilityPane;
	@FXML
	Pane LogoutPane1,LogoutPane2,Default,ChangePasswordPane;
	
	
	@FXML
	TextField TXTnewpwd1,TXTnewpwd2,SearchBox,TXTpurpose,TXTroom,TXTcapacity,TXTtimeStart,TXTtimeEnd,TXTBTime,TXTBDay;
	@FXML
	DatePicker TXTdate,TXTBDate;
	@FXML
	Label LblName, LblBatch, LblRno;
	@FXML
	Button Timetable,Profile,Courses,Classrooms,AddC,ViewC,SearchC,DropC,AddedC,DroppedC,BackC,RequestR,ViewR,AvailableR,RequestedR,BackR1,BackR2,Logout,ChangePassword,Changed,LoginA,CheckAvailB;
	
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
	public void getrooms()
	{
		try
		{
			Class.forName("java.sql.DriverManager");
	        Connection con=(Connection) DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/project","root","30july1998");
	        Statement stmt=(Statement) con.createStatement();
	        String q = "Select RoomNo from rooms ;";
	        System.out.println(q);
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
			//System.out.println(ex.getMessage());
		}
	}
	@Override	
	public void initialize(URL location, ResourceBundle resources) {
		
		
		
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
			}
			
		});	
	
		Changed.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				String np1=TXTnewpwd1.getText();
				String np2=TXTnewpwd2.getText();
				if(np1.equals(np2))
				{
					student.changePassword(np1);
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
			}
			
		});	
	
		Timetable.setOnAction(new EventHandler<ActionEvent>() {
			
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
				 Default.setVisible(true);
				 ChangePasswordPane.setVisible(false);
				 TimetableTBL.setItems(null);
				 TimetableTBL.setEditable(true);
				 
				 
				
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
				
			}
			
		});	
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
				
			}
			
		});
		
		AddC.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				System.out.println("I m here");
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
				
			}
			
		});	
		
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
				 
				 try
				 {
					 ViewCoursesTable.setItems(null);
					 
					 Class.forName("java.sql.DriverManager");
				     Connection con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","30july1998");
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
							    Connection con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","30july1998");
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
				
				
			}
			
		});	
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
				
			}
			
		});	
      BackC.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				System.out.println("I m here");
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
				
			}
			
		});	
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
			}
			
		});	
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
				 
				 try
				 {
					 ReqStatusTable.setItems(null);
					 ReqStatusTable.setEditable(true);
				        
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
      AvailableR.setOnAction(new EventHandler<ActionEvent>() {
			
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
				RequestRoomPane.setVisible(false);
				 ViewStatusPane.setVisible(false);
				 AvaibilityPane.setVisible(true);
				 LogoutPane1.setVisible(false);
				 LogoutPane2.setVisible(false);
				 Default.setVisible(false);
				 ChangePasswordPane.setVisible(false);
				 getrooms();
			}
			
		});	
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
			}
			
		});	
      BackR2.setOnAction(new EventHandler<ActionEvent>() {
			
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
				
				
			}
			
		});	
      RequestedR.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				String purpose=TXTpurpose.getText();
				String room=TXTroom.getText();
				int cap=Integer.parseInt(TXTcapacity.getText());
				String date=TXTdate.getValue().toString();
				String stime=TXTtimeStart.getText();
				String etime=TXTtimeEnd.getText();
				
				student.MakeRequest(purpose, room, cap, date, stime, etime);
				System.out.println(date+" "+stime);
				
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
			}
			
		});	
      
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
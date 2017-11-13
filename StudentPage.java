package GUIComponents;

import application.Course;
import application.Request;
import application.Timetable;
import javafx.scene.Node;

import application.Student;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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
	TextField TXTnewpwd1,TXTnewpwd2,SearchBox,TXTpurpose,TXTroom,TXTcapacity,TXTtimeStart,TXTtimeEnd;
	@FXML
	DatePicker TXTdate;
	@FXML
	Label LblName, LblBatch, LblRno;
	@FXML
	CheckBox CHKcourse1,CHKcourse2,CHKcourse3,CHKcourse4,CHKcourse5,CHKselectAll;
	@FXML
	Button Timetable,Profile,Courses,Classrooms,AddC,ViewC,SearchC,DropC,AddedC,DroppedC,BackC,RequestR,ViewR,AvailableR,RequestedR,BackR1,BackR2,Logout,ChangePassword,Changed,LoginA;
	
	@FXML
	TableView<Course> ViewCoursesTable,AddCoursesTBL;
	@FXML
	TableColumn<Course, String> Code,CCodeCol;
	@FXML
	TableColumn<Course, String> Name,CNameCol;
	@FXML
	TableColumn<Course, String> Acronym;
	@FXML
	TableColumn<Course, String> Faculty,CFacultyCol;
	@FXML
	TableColumn<Course, Integer> Credits,CCreditsCol;
	@FXML
	TableColumn<Course, String> Type;
	
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
				TimetablePane.setVisible(true);
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
				 
				 TimetableTBL.setItems(null);
				 TimetableTBL.setEditable(true);
				 
				 
				 ArrayList<Timetable> t=student.getTimetable();
				 ObservableList<Timetable> l=FXCollections.observableArrayList(t);
				 
				 TimetableTBL.setItems(l);
				 
				 CourseNameCol.setCellValueFactory(new PropertyValueFactory<Timetable,String>("Course"));
				 DayCol.setCellValueFactory(new PropertyValueFactory<Timetable,String>("Day"));
				 STimeCol.setCellValueFactory(new PropertyValueFactory<Timetable,String>("Stime"));
				 ETimeCol.setCellValueFactory(new PropertyValueFactory<Timetable,String>("Etime"));
				 TTRoomCol.setCellValueFactory(new PropertyValueFactory<Timetable,String>("Room"));
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
				 ArrayList<String> c=new ArrayList<String> ();
				 try
				 {
					 c=student.getCourseList();
					 CHKcourse1.setText(c.get(0));
					 CHKcourse2.setText(c.get(1));
					 CHKcourse3.setText(c.get(2));
					 CHKcourse4.setText(c.get(3));
					 CHKcourse5.setText(c.get(4));
					 
					 
				 }
				 catch(IndexOutOfBoundsException e)
				 {
					 
				 }
				 finally
				 {
					 System.out.println(c.size());
					 if(c.size()==3)
					 {
						 System.out.println("I will disable checkboxes now");
						 CHKcourse4.setDisable(true);
						 CHKcourse5.setDisable(true);
					 }
					 else if(c.size()==4)
					 {
						 CHKcourse5.setDisable(true);
					 }
				 }
				
			}
			
		});	
		
		CHKselectAll.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				CHKcourse1.setSelected(true);
				CHKcourse2.setSelected(true);
				CHKcourse3.setSelected(true);
				CHKcourse4.setSelected(true);
				CHKcourse5.setSelected(true);
			}
		});
		
		AddedC.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
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
				
				if(CHKcourse1.isSelected())
				{
					String CourseName=CHKcourse1.getText().toLowerCase();
					student.DropCourse(CourseName);
				}
				if(CHKcourse2.isSelected())
				{
					String CourseName=CHKcourse2.getText().toLowerCase();
					student.DropCourse(CourseName);
				}
				if(CHKcourse3.isSelected())
				{
					String CourseName=CHKcourse3.getText().toLowerCase();
					student.DropCourse(CourseName);
				}
				if(CHKcourse4.isSelected())
				{
					String CourseName=CHKcourse4.getText().toLowerCase();
					student.DropCourse(CourseName);
				}
				if(CHKcourse5.isSelected())
				{
					String CourseName=CHKcourse5.getText().toLowerCase();
					student.DropCourse(CourseName);
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
					 
					 Class.forName("java.sql.DriverManager");
				     Connection con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","tapeied");
				     Statement stmt=(Statement) con.createStatement();
				        
					 ArrayList<Request> r=student.ViewRequests();
					 ObservableList<Request> l=FXCollections.observableArrayList(r);
					 
					 ReqStatusTable.setItems(l);
					 
					 RoomCol.setCellValueFactory(new PropertyValueFactory<Request,String>("room"));
					 PurposeCol.setCellValueFactory(new PropertyValueFactory<Request,String>("purpose"));
					 CapCol.setCellValueFactory(new PropertyValueFactory<Request,Integer>("capacity"));
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

package GUIComponents;

import application.Course;

import application.Student;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;

public class StudentPage  implements javafx.fxml.Initializable {
	static Student student;
	//Student student=new Student("pragya16067@iiitd.ac.in","a","Student");
	
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
	TextField TXTnewpwd1,TXTnewpwd2,SearchBox;
	@FXML
	Label LblName, LblBatch, LblRno;
	@FXML
	Button Timetable,Profile,Courses,Classrooms,AddC,ViewC,SearchC,DropC,AddedC,DroppedC,BackC,RequestR,ViewR,AvailableR,RequestedR,BackR1,BackR2,Logout,ChangePassword,Changed,LoginA;
	
	@FXML
	TableView<Course> ViewCoursesTable;
	@FXML
	TableColumn<Course, String> Code;
	@FXML
	TableColumn<Course, String> Name;
	@FXML
	TableColumn<Course, String> Acronym;
	@FXML
	TableColumn<Course, String> Faculty;
	@FXML
	TableColumn<Course, Integer> Credits;
	@FXML
	TableColumn<Course, String> Type;
	
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
				System.out.println(SearchBox.getText());
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
					// ViewCoursesTable.setItems(null);
					 
					 Class.forName("java.sql.DriverManager");
				     Connection con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","30july1998");
				     Statement stmt=(Statement) con.createStatement();
				        
					 ResultSet rs=student.ViewCourses();
					 
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
				                  
				        		System.out.println(l.get(i).getCourseCode()+" "+l.get(i).getCourseName());
				        	}	
				        		System.out.println(l.size());
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
				
				
			}
			
		});	
		AddedC.setOnAction(new EventHandler<ActionEvent>() {
			
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
      DroppedC.setOnAction(new EventHandler<ActionEvent>() {
			
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
				System.out.println("I m here");
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
    
	}	
	
	

}

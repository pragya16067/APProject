package GUIComponents;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class StudentPage  implements javafx.fxml.Initializable {
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
	Button Timetable,Profile,Courses,Classrooms,AddC,ViewC,DropC,AddedC,DroppedC,BackC,RequestR,ViewR,AvailableR,RequestedR,BackR1,BackR2,Logout,ChangePassword,Changed,LoginA;
	
	@Override	
	public void initialize(URL location, ResourceBundle resources) {
		ChangePassword.setOnAction(new EventHandler<ActionEvent>() {
			
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
				 AvaibilityPane.setVisible(false);
				 LogoutPane1.setVisible(false);
				 Default.setVisible(false);
				 ChangePasswordPane.setVisible(true);
			}
			
		});	
	
		Changed.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				System.out.println("I m here");
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
				 Default.setVisible(false);
				 ChangePasswordPane.setVisible(false);
			}
			
		});	
	
		Timetable.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				System.out.println("I m here");
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
				 Default.setVisible(false);
				
			}
			
		});	

		Profile.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				System.out.println("I m here");
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
				 Default.setVisible(false);
				
				
			}
			
		});	

		
		Courses.setOnAction(new EventHandler<ActionEvent>() {
			
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
				
				
			}
			
		});	
		Classrooms.setOnAction(new EventHandler<ActionEvent>() {
			
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
				 Default.setVisible(false);
				
				
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
				 
				
			}
			
		});	
		ViewC.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				System.out.println("I m here");
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
				
			}
			
		});	
		DropC.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				System.out.println("I m here");
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
				 Default.setVisible(false);
				
				
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
				 Default.setVisible(false);
				
				
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
				
			}
			
		});	
    
	}	
	
	

}

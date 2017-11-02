package GUIComponents;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class AdminPage  implements javafx.fxml.Initializable {
	@FXML
	Pane ShowPane;
	@FXML
	Pane ProfilePane;
	@FXML
	Pane BookPane;
	@FXML
	Pane CancelPane;
	@FXML
	Pane RequestPane;
	
	@FXML
	Pane ManagePane, Default,ChangePasswordPane;
	
	@FXML
	Button showB,profB,bookB,CancelB,RequestB,ManageB,ChangePassword,Changed;
	
	@Override	
	public void initialize(URL location, ResourceBundle resources) {
		
		showB.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				BookPane.setVisible(false);
				ShowPane.setVisible(true);
				ProfilePane.setVisible(false);
				CancelPane.setVisible(false);
				Default.setVisible(false);
				ChangePasswordPane.setVisible(false);
				RequestPane.setVisible(false);
				ManagePane.setVisible(false);
				
			}
			
		});	
		
		ManageB.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				BookPane.setVisible(false);
				ShowPane.setVisible(false);
				ProfilePane.setVisible(false);
				CancelPane.setVisible(false);
				Default.setVisible(false);
				ChangePasswordPane.setVisible(false);
				RequestPane.setVisible(false);
				ManagePane.setVisible(true);
				
			}
			
		});	

		profB.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				ProfilePane.setVisible(true);
				ShowPane.setVisible(false);
				BookPane.setVisible(false);
				CancelPane.setVisible(false);
				Default.setVisible(false);
				ChangePasswordPane.setVisible(false);
				RequestPane.setVisible(false);
				ManagePane.setVisible(false);

				
			}
			
		});
		
		bookB.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				ProfilePane.setVisible(false);
				ShowPane.setVisible(false);
				BookPane.setVisible(true);
				CancelPane.setVisible(false);
				Default.setVisible(false);
				ChangePasswordPane.setVisible(false);
				RequestPane.setVisible(false);
				ManagePane.setVisible(false);
			}
			
		});	
		
		CancelB.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				BookPane.setVisible(false);
				ShowPane.setVisible(false);
				ProfilePane.setVisible(false);
				CancelPane.setVisible(true);
				Default.setVisible(false);
				ChangePasswordPane.setVisible(false);
				RequestPane.setVisible(false);
				ManagePane.setVisible(false);
				
			}
		});
		
		RequestB.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				BookPane.setVisible(false);
				ShowPane.setVisible(false);
				ProfilePane.setVisible(false);
				CancelPane.setVisible(false);
				Default.setVisible(false);
				ChangePasswordPane.setVisible(false);
				RequestPane.setVisible(true);
				ManagePane.setVisible(false);
				
			}
		});
		
		ChangePassword.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				BookPane.setVisible(false);
				ShowPane.setVisible(false);
				ProfilePane.setVisible(false);
				CancelPane.setVisible(false);
				Default.setVisible(false);
				ChangePasswordPane.setVisible(true);
				RequestPane.setVisible(false);
				ManagePane.setVisible(false);
				
			}
		});
		
		
		Changed.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				BookPane.setVisible(false);
				ShowPane.setVisible(false);
				ProfilePane.setVisible(true);
				CancelPane.setVisible(false);
				Default.setVisible(false);
				ChangePasswordPane.setVisible(false);
				RequestPane.setVisible(false);
				ManagePane.setVisible(false);
				
			}
		});
		
		
	}	
	

}

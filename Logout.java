package GUIComponents;
import java.net.URL;

import java.util.ResourceBundle;

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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import application.Admin;
import application.Faculty;
import application.Student;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.Node;
/**
 * @author Tanya Raj
 * This classis the controller for logout.fxml and is used to login back again if u logged out
 *
 */
public class Logout implements javafx.fxml.Initializable {

	
	@FXML
	Button LoginA;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		LoginA.setOnAction(new EventHandler<ActionEvent>() {
			
			/* 
			 *Login button takes the user back to login option such that he can login again.
			 */
			@Override
			public void handle(ActionEvent event) {
				try
				{
				Parent page =  FXMLLoader.load(getClass().getResource("Login.fxml"));
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

package GUIComponents;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import application.Admin;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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
	Button showB,profB,bookB,CancelB,RequestB,ManageB,ChangePassword,Changed,LogoutBtn;
	@FXML
	TextField TXTnewpwd1,TXTnewpwd2, OldPwd;
	@FXML
	Label Lblemail, LblName;
	static Admin admin;
	public void setAdmin(Admin a)
	{
		admin = a;
	}
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
				LblName.setText("Hi ! "+admin.getName()+"!!");
				Lblemail.setText(admin.getemail());
				
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
				String np1=TXTnewpwd1.getText();
				String np2=TXTnewpwd2.getText();
				String op = OldPwd.getText();
				if(np1.equals(np2) && admin.AuthenticateUser(admin.getemail(), op))
				{
					admin.changePassword(np1);
					JOptionPane.showMessageDialog(null, "Password Changed Succesfully");
				}
				else
				if(!admin.AuthenticateUser(admin.getemail(), op))
				{
					JOptionPane.showMessageDialog(null, "Your Old Password is incorrect. Please Try Again!");
					OldPwd.setText("");
					TXTnewpwd1.setText("");
					TXTnewpwd2.setText("");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please make sure that your new passwords match");
					TXTnewpwd1.setText("");
					TXTnewpwd2.setText("");
				}
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
		LogoutBtn.setOnAction(new EventHandler<ActionEvent>() {
			
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
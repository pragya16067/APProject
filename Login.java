package GUIComponents;

import application.Student;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Login implements javafx.fxml.Initializable {
	@FXML
	TextField TXTemail;
	
	@FXML
	PasswordField TXTpwd;
	
	@FXML
	Button LoginBtn, SignUpBtn;
	
	@Override	
	public void initialize(URL location, ResourceBundle resources) {
		
		LoginBtn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				String email=TXTemail.getText();
				String password=TXTpwd.getText();
				Student u=new Student(email,password,"Student");
				if(u.AuthenticateUser(email, password))
				{
					JOptionPane.showMessageDialog(null, "You have succesfully logged in");
					//Move to student page
				}
				else
            	{
            		JOptionPane.showMessageDialog(null, "Email id or Password is incorrect");
            	}
				
				/*System.out.println(password);
		        try
		        {
		            Class.forName("java.sql.DriverManager");
		            Connection con=(Connection) DriverManager.getConnection(
		                    "jdbc:mysql://localhost:3306/project","root","tapeied");
		            Statement stmt=(Statement) con.createStatement();
		            String q="Insert into users values (2,'tanya108@iiitd.ac.in','tanya123','Student');";
		            stmt.executeUpdate(q);
				
		        }
		        catch(Exception e)
		        {
		        	System.out.println(e.getMessage());
		        }*/
			}
		});
	}
}

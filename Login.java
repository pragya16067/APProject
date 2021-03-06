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
 *
 */
public class Login  implements javafx.fxml.Initializable  {
	ObservableList<String> options = 
		    FXCollections.observableArrayList(
		        "Admin",
		        "Faculty",
		        "Student"
		    );
	    @FXML
		ComboBox UserType;
	    @FXML
	    Button SignedUpBtn,SignUpBtn,LoginBtn ;
	    @FXML
	    AnchorPane SignUp1;
	    @FXML
		TextField TXTemail,TXTNewUser;
	    @FXML
	    Label TXTWarning;
		
		@FXML
		PasswordField TXTpwd,TXTpwd1,TXTpwd2;
		
		
    
	/* 
	 * @see javafx.fxml.Initializable
	 * #initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
			UserType.setValue("User Type");
			UserType.setItems(options);
			SignUpBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				
				TXTpwd1.setText("");
				TXTpwd2.setText("");
				TXTNewUser.setText("");
				SignUp1.setVisible(true);
				
				
			}
			
			
		});	
			SignedUpBtn.setOnAction(new EventHandler<ActionEvent>() {
				 /**
				 * First time required registration for newusers
				 */
				@Override
				public void handle(ActionEvent event) {
					
					//SignUp1.setVisible(false);
					String email = TXTNewUser.getText();
					 try
				        {
				            Class.forName("java.sql.DriverManager");
				            Connection con=(Connection) DriverManager.getConnection(
				                    "jdbc:mysql://localhost:3306/project","root","tapeied");
				            Statement stmt=(Statement) con.createStatement();
				            String q="Select * from users where email='"+email+"';";
				            ResultSet rs=stmt.executeQuery(q);
				            boolean checkpwd = false;
				            System.out.println("email:"+email+"ii");
				            //String type = (String) UserType.getValue();
				            
				            if(!rs.next())
				            {  
				            	
				            	String password1 = TXTpwd1.getText();
				            	String password2 = TXTpwd2.getText();
				            	String type = (String) UserType.getValue();
				            	if(password2.equals(password1)== false  )
				            	{
				            		JOptionPane.showMessageDialog(null, "Re-entered Password is incorrect! Please try Again");
				            		TXTpwd1.setText("");
				    				TXTpwd2.setText("");
				    				UserType.getSelectionModel().clearSelection();
				    				TXTWarning.setVisible(true);
				            		SignUp1.setVisible(true);
				            		UserType.setItems(options);
				      
				            	}
				            	if(!email.contains("@iiitd.ac.in"))
				            	{
				            		JOptionPane.showMessageDialog(null, "Invalid email-Id! Please try Again");
				            	}
				            	else
				            	{   System.out.println(password1);
				            		String q1 = "Select max(uid) from users;";
				            		ResultSet rs1 = stmt.executeQuery(q1);
				            		System.out.println("tt");
				            		UserType.setItems(options);
				            		rs1.next();
				            		int uid = rs1.getInt("max(uid)")+1;
				            		if(!email.equals("") && !password1.equals("") && !type.equals("User Type"))
				            		{String q2 = "Insert into users values ("+uid+",'"+email+"','"+password1+"','"+type+"');" ;
				            		stmt.executeUpdate(q2);
				            		
				            		if(type.equals("Student"))
				            		{
				            			 q2 = "Insert into students values ('"+email+"','"+"CSE112;CSE201;CSE121;"+"','');" ;
				            		}
				            		stmt.executeUpdate(q2);
				            		JOptionPane.showMessageDialog(null, "Sucessfully Registered");
				            		SignUp1.setVisible(false);
				            		
				            		}
				            		else
				            		{
				            			JOptionPane.showMessageDialog(null, "Some fields are empty");
				            		}
				            		
				            	}
				            	
				            }
				            	
			            		
			            		
			            		
				            	
				            
				            
				            
				            else
				            {   
				            	JOptionPane.showMessageDialog(null, "Already Registered User");
				            	SignUp1.setVisible(false);
				            }
				           
				        }
				        catch(Exception exp)			        {
				        	System.out.println(exp.getMessage());
				        }
					
				}
				
				
			});	
			 /**
			 * Login Button actions (login) for already existing users
			 */
			LoginBtn.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					String email=TXTemail.getText();
					String password=TXTpwd.getText();
					Student u=new Student(email,password,"Student");
					if(u.AuthenticateUser(email, password))
					{
						JOptionPane.showMessageDialog(null, "You have succesfully logged in");
						try
				        {
				            Class.forName("java.sql.DriverManager");
				            Connection con=(Connection) DriverManager.getConnection(
				                    "jdbc:mysql://localhost:3306/project","root","tapeied");
				            Statement stmt=(Statement) con.createStatement();
				            String q="Select type from users where email='"+email+"';";
				            ResultSet rs=stmt.executeQuery(q);
				            rs.next();
				            String type = rs.getString("type");
						 
						//Move to student or Admin or Faculty page
						try {
							if(type.equals("Student"))
							{
							StudentPage sp=new StudentPage();
							sp.setStudent(u);
							System.out.println(sp.student.getName());
							}
							if(type.equals("Admin"))
							{
								AdminPage ap = new AdminPage();
								Admin a = new Admin(email,password,"Admin");
								ap.setAdmin(a);
								System.out.println(ap.admin.getName());
								
							}
							if(type.equals("Faculty"))
							{
								FacultyPage fp = new FacultyPage();
								Faculty f = new Faculty(email,password,"Faculty");
								fp.setFaculty(f);
								System.out.println(fp.faculty.getName());
								
							}
							
							
							Parent page =  FXMLLoader.load(getClass().getResource(type+"Page.fxml"));
							Scene scene = new Scene(page);
							Stage page_stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
							page_stage.setScene(scene);
							page_stage.show();
							
						} catch(Exception e) {
							e.printStackTrace();
						}
					}
						catch(Exception exp)
						{
							System.out.println(exp.getMessage());
						}
					}
					else
	            	{
	            		JOptionPane.showMessageDialog(null, "Email id or Password is incorrect");
	            		TXTpwd.setText("");
	            	}
					
					
				}
			});
		
		
	}

}
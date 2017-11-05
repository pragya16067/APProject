package GUIComponents;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import application.Admin;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
	Button showB,profB,bookB,CancelB,RequestB,ManageB,ChangePassword,Changed,LogoutBtn,BookedB;
	@FXML
	TextField TXTnewpwd1,TXTnewpwd2, OldPwd,TXTCode,TXTCapacity,TXTTime,TXTTime1,TXTDay;
	@FXML
	ComboBox TXTRoom;
	@FXML
	DatePicker TXTDate;
	@FXML
	Label Lblemail, LblName;
	
	static Admin admin;
	public void setAdmin(Admin a)
	{
		admin = a;
	}	
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
	        TXTRoom.setItems(options);
	        
		}
		catch(Exception ex)
		{
			//System.out.println(ex.getMessage());
		}
	}
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
				getrooms();
				
			}
			
			
		});	
		BookedB.setOnAction(new EventHandler<ActionEvent>() {
			
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
				String Code = TXTCode.getText();
				int Capacity = Integer.parseInt(TXTCapacity.getText());
				//System.out.println(Capacity);
				
				String Room = (String) TXTRoom.getValue();
				String day ="";
				LocalDate day1;
				System.out.println(TXTDate.getValue());
				if(TXTDate.getValue()==null)
				{
					day = TXTDay.getText();
				}
				String dayname ="";
				if(TXTDay.getText().equals("") && TXTDate.getValue()!=null)
				{
					day1 = TXTDate.getValue();
					 dayname = day1.getDayOfWeek().name();
					System.out.println(day1);
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
					day = day1.format(formatter);
					
				}
				System.out.println(dayname);
				if(TXTDate.getValue()==null && TXTDay.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Day and Date both are empty");
				}
				else
				{String STime = TXTTime.getText();
				String ETime = TXTTime1.getText();
				boolean book = admin.BookRoom(Code, Room, STime, ETime, day,Capacity,dayname);
				if(book ==true){
					JOptionPane.showMessageDialog(null, "Room Booked Sucessfully");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Selected Room Not Available! Please try Again");
					TXTCode.setText("");
					TXTDay.setText("");
					TXTCapacity.setText("");
					TXTTime.setText("");
					TXTTime1.setText("");
					TXTDate.setValue(null);
					
				}
				}
				BookPane.setVisible(false);
				Default.setVisible(true);
				
				
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
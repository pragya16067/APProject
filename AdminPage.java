package GUIComponents;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.sql.Date;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import application.Admin;
import application.Bookings;
import application.Classrooms;
import application.Request;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
	Button showB,profB,bookB,CancelB,RequestB,ManageB,ChangePassword,Changed,LogoutBtn,BookedB,CheckAvailB, AcceptBtn, RejectBtn,CancelBtn,ViewRoom,RRoom,AddNew;
	@FXML
	TextField TXTnewpwd1,TXTnewpwd2, OldPwd,TXTCode,TXTCapacity,TXTTime,TXTTime1,TXTDay,TXTBTime,TXTBDay,Room3,Room2,Roomget,Capacity1;
	@FXML
	Label Capacity2,Courses1;
	@FXML
	ComboBox TXTRoom,TXTBRoom;
	@FXML
	DatePicker TXTDate,TXTBDate;
	@FXML
	Label Lblemail, LblName;
	@FXML
	TableView Tblavail, TblRequest,TblCancel;
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
	TableColumn<Request,Integer> Id;
	@FXML
	TableColumn<Request,String> Purpose;
	@FXML
	TableColumn<Request,String> RoomN;
	@FXML
	TableColumn<Request,Integer> CapacityN;
	@FXML
	TableColumn<Request,String> DateN;
	@FXML
	TableColumn<Request,String> TimeN;
	@FXML
	TableColumn<Bookings,String> Purpose1;
	@FXML
	TableColumn<Bookings,String> RoomN1;
	@FXML
	TableColumn<Bookings,String> DateN1;
	@FXML
	TableColumn<Bookings,String> TimeN1;
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
	        TXTBRoom.setItems(options);
	        
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
				getrooms();
				
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
				ArrayList<Bookings> book = admin.GetBooking();
				ObservableList lists = FXCollections.observableArrayList(book);
				System.out.println(book.size());
				TblCancel.setItems(lists);
				Purpose1.setCellValueFactory(new PropertyValueFactory<Bookings,String>("Purpose1"));
				RoomN1.setCellValueFactory(new PropertyValueFactory<Bookings,String>("RoomN"));
				DateN1.setCellValueFactory(new PropertyValueFactory<Bookings,String>("DateN"));
				TimeN1.setCellValueFactory(new PropertyValueFactory<Bookings,String>("TimeN"));
				
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
				ArrayList<Request> list =  admin.GetRequests();
				ObservableList lists = FXCollections.observableArrayList(list);
				System.out.println(list.size());
				TblRequest.setItems(lists);
				Id.setCellValueFactory(new PropertyValueFactory<Request,Integer>("Rid"));
				Purpose.setCellValueFactory(new PropertyValueFactory<Request,String>("Purpose"));
				RoomN.setCellValueFactory(new PropertyValueFactory<Request,String>("RoomN"));
				CapacityN.setCellValueFactory(new PropertyValueFactory<Request,Integer>("CapacityN"));
				DateN.setCellValueFactory(new PropertyValueFactory<Request,String>("DateN"));
				TimeN.setCellValueFactory(new PropertyValueFactory<Request,String>("TimeN"));
				
				
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
		AcceptBtn.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Request rq = (Request) TblRequest.getSelectionModel().getSelectedItem();
				//System.out.println(rq.getpurpose());
				String s = admin.AcceptRequest(rq);
				if(s.equals(""))
				{ArrayList<Request> list =  admin.GetRequests();
				ObservableList lists = FXCollections.observableArrayList(list);
				System.out.println(list.size());
				TblRequest.setItems(lists);
				Id.setCellValueFactory(new PropertyValueFactory<Request,Integer>("Rid"));
				Purpose.setCellValueFactory(new PropertyValueFactory<Request,String>("Purpose"));
				RoomN.setCellValueFactory(new PropertyValueFactory<Request,String>("RoomN"));
				CapacityN.setCellValueFactory(new PropertyValueFactory<Request,Integer>("CapacityN"));
				DateN.setCellValueFactory(new PropertyValueFactory<Request,String>("DateN"));
				TimeN.setCellValueFactory(new PropertyValueFactory<Request,String>("TimeN"));
				}
				else
				{
					JOptionPane.showMessageDialog(null, s);
				}
				
			}
			
		});
		RejectBtn.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Request rq = (Request) TblRequest.getSelectionModel().getSelectedItem();
				//System.out.println(rq.getpurpose());
				admin.RejectRequest(rq);
				ArrayList<Request> list =  admin.GetRequests();
				ObservableList lists = FXCollections.observableArrayList(list);
				System.out.println(list.size());
				TblRequest.setItems(lists);
				Id.setCellValueFactory(new PropertyValueFactory<Request,Integer>("Rid"));
				Purpose.setCellValueFactory(new PropertyValueFactory<Request,String>("Purpose"));
				RoomN.setCellValueFactory(new PropertyValueFactory<Request,String>("RoomN"));
				CapacityN.setCellValueFactory(new PropertyValueFactory<Request,Integer>("CapacityN"));
				DateN.setCellValueFactory(new PropertyValueFactory<Request,String>("DateN"));
				TimeN.setCellValueFactory(new PropertyValueFactory<Request,String>("TimeN"));
				
				
			}
			
		});
		CancelBtn.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				
				Bookings rq = (Bookings) TblCancel.getSelectionModel().getSelectedItem();
				admin.CancelBooking(rq);
				ArrayList<Bookings> book = admin.GetBooking();
				ObservableList lists = FXCollections.observableArrayList(book);
				System.out.println(book.size());
				TblCancel.setItems(lists);
				Purpose1.setCellValueFactory(new PropertyValueFactory<Bookings,String>("Purpose1"));
				RoomN1.setCellValueFactory(new PropertyValueFactory<Bookings,String>("RoomN"));
				DateN1.setCellValueFactory(new PropertyValueFactory<Bookings,String>("DateN"));
				TimeN1.setCellValueFactory(new PropertyValueFactory<Bookings,String>("TimeN"));
				
				
			}});
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
				list = admin.accroom(Room);
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
				list = admin.accroom(Room,Date,Day);
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
				list = admin.accroom(Date,Day);
				}
				if(TXTBDate.getValue()==null && TXTBDay.getText().equals("") && !TXTBTime.getText().equals("") && Room!=null )
				{
					System.out.println("1d");
				String Times = TXTBTime.getText();
				list = admin.accroomt(Room,Times);
				}
				if(TXTBDate.getValue()==null&& TXTBDay.getText().equals("") && !TXTBTime.getText().equals("") && Room==null)
				{
					System.out.println("1e");
				String Times = TXTBTime.getText();
				list = admin.accroomt(Times);
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
				list = admin.accroom(Room,Date,Day,Times);
				}
				if(TXTBDate.getValue()==null && TXTBTime.getText().equals("") && Room==null && TXTBDay.getText().equals(""))
				{System.out.println("1h");
					JOptionPane.showMessageDialog(null, "Showing All Available records");
					list = admin.accroom();
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
		ViewRoom.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				String room = Room3.getText();
				try
				{
					Class.forName("java.sql.DriverManager");
			        Connection con=(Connection) DriverManager.getConnection(
			                "jdbc:mysql://localhost:3306/project","root","30july1998");
			        Statement stmt=(Statement) con.createStatement();
			        String q = "Select Capacity from rooms where RoomNo ='"+room+ "';";
			        System.out.println(q);
			        ResultSet rs = stmt.executeQuery(q);
			        if(rs.next())
			        Capacity2.setText(rs.getInt("Capacity")+"");
			        else
			        {
			        	JOptionPane.showMessageDialog(null, "Room Doesn't Exist!");
			        }
			      }
				catch(Exception ex)
				{
					
				}
				Room3.setText("");
		}});
		AddNew.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				String room = Room2.getText();
				String Capacity = Capacity1.getText();
				try
				{
					Class.forName("java.sql.DriverManager");
			        Connection con=(Connection) DriverManager.getConnection(
			                "jdbc:mysql://localhost:3306/project","root","30july1998");
			        Statement stmt=(Statement) con.createStatement();
			        String q = "Select Capacity from rooms where RoomNo ='"+room+ "';";
			        System.out.println(q);
			        ResultSet rs = stmt.executeQuery(q);
			        if(!rs.next())
			         {q = "Insert into rooms values ('"+room+"',"+Integer.parseInt(Capacity)+");";
			        System.out.println(q);
			         stmt.executeUpdate(q);
			         }
			        else
			        {
			        	JOptionPane.showMessageDialog(null, "Room Already Exist!");
			        }
			        
			        
			      }
				catch(Exception ex)
				{
					
				}
				Room2.setText("");
				Capacity1.setText("");
		}});
		RRoom.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				String room = Roomget.getText();
				try
				{System.out.println(room);
					Class.forName("java.sql.DriverManager");
			        Connection con=(Connection) DriverManager.getConnection(
			                "jdbc:mysql://localhost:3306/project","root","30july1998");
			        Statement stmt=(Statement) con.createStatement();
			        String q = "Delete from rooms where RoomNo ='"+room+ "';";
			         int k =stmt.executeUpdate(q);
			         System.out.println(q);
			         System.out.println("If it exists "+k);
			         if(k==0)
			         {
			        	 JOptionPane.showMessageDialog(null, "Room Doesnot Exist!");
			         }
			       
			      }
				catch(Exception ex)
				{
					
				}
				Roomget.setText("");
		}});
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
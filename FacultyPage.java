package GUIComponents;



import java.net.URL;
import java.sql.Time;
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
import application.Bookings;
import application.Classrooms;
import application.Faculty;
import application.Project;
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

public class FacultyPage  implements javafx.fxml.Initializable {
	@FXML
	Pane ShowPane;
	@FXML
	Pane ProfilePane;
	@FXML
	Pane BookPane, ProjectsPane;
	@FXML
	Pane CancelPane,Default,ChangePasswordPane;
	
	@FXML
	Button showB,profB,bookB,CancelB,projectB,ChangePassword,Changed,LogoutBtn,BookedB,CheckAvailB,CancelBtn, ViewProjectB, SearchProjectB, AcceptProjectB;
	@FXML
	TextField TXTnewpwd1,TXTnewpwd2, OldPwd,TXTCode,TXTCapacity,TXTTime,TXTTime1,TXTDay,TXTBTime,TXTBDay,TXTtypeProject,TXTfieldProject;
	@FXML
	TextField TXTdayOfweek, TXTtimeslot, TXTprereqC;
	@FXML
	Label Lblemail, LblName,LblCourse;
	@FXML
	ComboBox TXTRoom,TXTBRoom;
	@FXML
	DatePicker TXTDate, TXTBDate;
	@FXML
	TableView Tblavail,TblCancel;
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
	TableColumn<Bookings,String> Purpose1;
	@FXML
	TableColumn<Bookings,String> RoomN1;
	@FXML
	TableColumn<Bookings,String> DateN1;
	@FXML
	TableColumn<Bookings,String> TimeN1;
	
	
	@FXML
	TableView<Project> TblViewProjects, TblSearchProject;
	@FXML
	TableColumn<Project, String> Topic, Topic1;
	@FXML
	TableColumn<Project, Integer> Student, Student1;
	@FXML
	TableColumn<Project, String> Type, Type1;
	@FXML
	TableColumn<Project, String> Field, Field1;
	@FXML
	TableColumn<Project, String> Status;

	/*
	 * Implementing Singleton design pattern 
	 */
	static Faculty faculty;
	/**
	 * To set the static faculty object of this page
	 * 
	 * @param s - faculty object 
	 */
	public void setFaculty(Faculty f)
	{
		faculty = f;
	}
	/**
	 * To get all the room nos. in the institute from the rooms table in database
	 */
	public void getrooms()
	{
		try
		{
			Class.forName("java.sql.DriverManager");
	        Connection con=(Connection) DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/project","root","tapeied");
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
	
	@Override	
	public void initialize(URL location, ResourceBundle resources) {
		/* 
		 * To see bookings pane/See Room Avaibility
		 */
		showB.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				BookPane.setVisible(false);
				ShowPane.setVisible(true);
				ProfilePane.setVisible(false);
				CancelPane.setVisible(false);
				Default.setVisible(false);
				ProjectsPane.setVisible(false);
				getrooms();
			}
			
		});	
		
		CheckAvailB.setOnAction(new EventHandler<ActionEvent>() {
			/* 
			 * To set Check Avaibility of room  pane to visible and call the required details from
			 * the faculty.java
			 */
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
				list = faculty.accroom(Room);
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
				list = faculty.accroom(Room,Date,Day);
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
				list = faculty.accroom(Date,Day);
				}
				if(TXTBDate.getValue()==null && TXTBDay.getText().equals("") && !TXTBTime.getText().equals("") && Room!=null )
				{
					System.out.println("1d");
				String Times = TXTBTime.getText();
				list = faculty.accroomt(Room,Times);
				}
				if(TXTBDate.getValue()==null&& TXTBDay.getText().equals("") && !TXTBTime.getText().equals("") && Room==null)
				{
					System.out.println("1e");
				String Times = TXTBTime.getText();
				list = faculty.accroomt(Times);
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
				list = faculty.accroom(Room,Date,Day,Times);
				}
				if(TXTBDate.getValue()==null && TXTBTime.getText().equals("") && Room==null && TXTBDay.getText().equals(""))
				{System.out.println("1h");
					JOptionPane.showMessageDialog(null, "Showing All Available records");
					list = faculty.accroom();
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

		/* 
		 * To set profile of faculty pane to visible and call the required details from
		 * the faculty.java
		 */
		profB.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				LblName.setText("Hi ! "+faculty.getName()+"!!");
				Lblemail.setText(faculty.getemail());
				System.out.println(faculty.Courses()+"mmmm");
				LblCourse.setText(faculty.Courses());
				ProfilePane.setVisible(true);
				ShowPane.setVisible(false);
				BookPane.setVisible(false);
				CancelPane.setVisible(false);
				Default.setVisible(false);
				ProjectsPane.setVisible(false);

			}
			
		});
		
		bookB.setOnAction(new EventHandler<ActionEvent>() {
			/* 
			 * To set booking  pane to visible and call the required details from
			 * the faculty.java
			 */
			@Override
			public void handle(ActionEvent event) {
				ProfilePane.setVisible(false);
				ShowPane.setVisible(false);
				BookPane.setVisible(true);
				CancelPane.setVisible(false);
				Default.setVisible(false);
				ProjectsPane.setVisible(false);
				getrooms();
			}
			
		});	
		
		CancelB.setOnAction(new EventHandler<ActionEvent>() {
			/* 
			 * To cancel the room bookings made by faculty or student and call the required details from
			 * the faculty.java
			 */
			@Override
			public void handle(ActionEvent event) {
				BookPane.setVisible(false);
				ShowPane.setVisible(false);
				ProfilePane.setVisible(false);
				CancelPane.setVisible(true);
				Default.setVisible(false);
				ProjectsPane.setVisible(false);
				
				ArrayList<Bookings> book = faculty.GetBooking();
				ObservableList lists = FXCollections.observableArrayList(book);
				System.out.println(book.size());
				TblCancel.setItems(lists);
				Purpose1.setCellValueFactory(new PropertyValueFactory<Bookings,String>("Purpose1"));
				RoomN1.setCellValueFactory(new PropertyValueFactory<Bookings,String>("RoomN"));
				DateN1.setCellValueFactory(new PropertyValueFactory<Bookings,String>("DateN"));
				TimeN1.setCellValueFactory(new PropertyValueFactory<Bookings,String>("TimeN"));
			
				
			}
		});
		ChangePassword.setOnAction(new EventHandler<ActionEvent>() {
			/* 
			 * To  Change Password pane to visible and call the required details from
			 * the faculty.java
			 */
			@Override
			public void handle(ActionEvent event) {
				BookPane.setVisible(false);
				ShowPane.setVisible(false);
				ProfilePane.setVisible(false);
				CancelPane.setVisible(false);
				Default.setVisible(false);
				ChangePasswordPane.setVisible(true);
				ProjectsPane.setVisible(false);
			
				
			}
		});
		Changed.setOnAction(new EventHandler<ActionEvent>() {
			/* 
			 * To change the password as per entered input and call the required details from
			 * thefaculty.java
			 */
			@Override
			public void handle(ActionEvent event) {
				String np1=TXTnewpwd1.getText();
				String np2=TXTnewpwd2.getText();
				String op = OldPwd.getText();
				if(np1.equals(np2) && faculty.AuthenticateUser(faculty.getemail(), op))
				{
					faculty.changePassword(np1);
					JOptionPane.showMessageDialog(null, "Password Changed Succesfully");
				}
				else
				if(!faculty.AuthenticateUser(faculty.getemail(), op))
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
				ProjectsPane.setVisible(false);
				Default.setVisible(false);
				ChangePasswordPane.setVisible(false);
			
				
			}
		});
		BookedB.setOnAction(new EventHandler<ActionEvent>() {
			/* 
			 * To Book the course after selection from the list of courses provided
			 * and call the required functions from faculty.java
			 */
			@Override
			public void handle(ActionEvent event) {
				ProfilePane.setVisible(false);
				ShowPane.setVisible(false);
				CancelPane.setVisible(false);
				ProjectsPane.setVisible(false);
				
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
				boolean book = faculty.BookRoom(Code, Room, STime, ETime, day,Capacity,dayname);
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
		CancelBtn.setOnAction(new EventHandler<ActionEvent>(){
			/* 
			 * To set Cancel booking  pane to visible and call the required details from
			 * the facullty.java and populate the table
			 */
			@Override
			public void handle(ActionEvent event) {
				try
				{Bookings rq = (Bookings) TblCancel.getSelectionModel().getSelectedItem();
				faculty.CancelBooking(rq);
				ArrayList<Bookings> book = faculty.GetBooking();
				ObservableList lists = FXCollections.observableArrayList(book);
				System.out.println(book.size());
				TblCancel.setItems(lists);
				Purpose1.setCellValueFactory(new PropertyValueFactory<Bookings,String>("Purpose1"));
				RoomN1.setCellValueFactory(new PropertyValueFactory<Bookings,String>("RoomN"));
				DateN1.setCellValueFactory(new PropertyValueFactory<Bookings,String>("DateN"));
				TimeN1.setCellValueFactory(new PropertyValueFactory<Bookings,String>("TimeN"));
				}
				catch(Exception ex)
				{
					
				}
				
				
			}});
		
		projectB.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				BookPane.setVisible(false);
				ShowPane.setVisible(false);
				ProfilePane.setVisible(false);
				CancelPane.setVisible(false);
				Default.setVisible(false);
				ProjectsPane.setVisible(true);
			}
			
		});	
		
		ViewProjectB.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				TblViewProjects.setItems(null);
				TblViewProjects.setEditable(true);
				ArrayList<Project> projects=new ArrayList<Project> ();
				try
				{
					
					ResultSet rs=faculty.getProjects();
					while(rs.next()) {
						Project p = new Project(rs.getString("Topic"), rs.getInt("FacultyID"), rs.getInt("StudentID"), rs.getString("Fields"), 
								 rs.getString("type"), rs.getString("status"));
						projects.add(p);
					}
					
				}
				catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
				
				
				ObservableList<Project> l=FXCollections.observableArrayList(projects);
				TblViewProjects.setItems(l);
        		
        		Topic.setCellValueFactory(new PropertyValueFactory<Project,String>("Name"));
        		Student.setCellValueFactory(new PropertyValueFactory<Project,Integer>("sid"));
        		Type.setCellValueFactory(new PropertyValueFactory<Project,String>("Type"));
        		Field.setCellValueFactory(new PropertyValueFactory<Project,String>("Field"));
        		Status.setCellValueFactory(new PropertyValueFactory<Project,String>("Status"));
        		
        		
				BookPane.setVisible(false);
				ShowPane.setVisible(false);
				ProfilePane.setVisible(false);
				CancelPane.setVisible(false);
				Default.setVisible(false);
				ProjectsPane.setVisible(true);
			}
			
		});	
		
		SearchProjectB.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				String type = TXTtypeProject.getText();
				String field = TXTfieldProject.getText();
				
				TblSearchProject.setItems(null);
				TblSearchProject.setEditable(true);
				ArrayList<Project> projects = new ArrayList<Project> ();
				
				try {
					Class.forName("java.sql.DriverManager");
			        Connection con=(Connection) DriverManager.getConnection(
			                "jdbc:mysql://localhost:3306/project","root","tapeied");
			        Statement stmt=(Statement) con.createStatement();
			        String q;
					if(type.equals("") && field.equals(""))
					{
						q = "Select * from Projects where FacultyID = "+faculty.getFacultyID(faculty.getemail())+" and status = 'Pending';";
					}
					else if(type.equals("") && !field.equals(""))
					{
						q = "Select * from Projects where FacultyID = "+faculty.getFacultyID(faculty.getemail())+" and status = 'Pending' and Fields = '"+field+"';";
					}
					else if(field.equals("") && !type.equals(""))
					{
						q = "Select * from Projects where FacultyID = "+faculty.getFacultyID(faculty.getemail())+" and status = 'Pending' and Type = '"+type+"';";
					}
					else
					{
						q = "Select * from Projects where FacultyID = "+faculty.getFacultyID(faculty.getemail())+" and status = 'Pending' and Type = '"+type+"' and Fields = '"+field+"';";
					}
					ResultSet rs = stmt.executeQuery(q);
					while(rs.next()) {
						Project p = new Project(rs.getString("Topic"), rs.getInt("FacultyID"), rs.getInt("StudentID"), rs.getString("Fields"), 
								 rs.getString("type"), rs.getString("status"));
						projects.add(p);
					}
			        
				}
				catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
				
				ObservableList<Project> l=FXCollections.observableArrayList(projects);
				TblSearchProject.setItems(l);
        		
        		Topic1.setCellValueFactory(new PropertyValueFactory<Project,String>("Name"));
        		Student1.setCellValueFactory(new PropertyValueFactory<Project,Integer>("sid"));
        		Type1.setCellValueFactory(new PropertyValueFactory<Project,String>("Type"));
        		Field1.setCellValueFactory(new PropertyValueFactory<Project,String>("Field"));
				
				BookPane.setVisible(false);
				ShowPane.setVisible(false);
				ProfilePane.setVisible(false);
				CancelPane.setVisible(false);
				Default.setVisible(false);
				ProjectsPane.setVisible(true);
			}
			
		});	

		AcceptProjectB.setOnAction(new EventHandler<ActionEvent>() {
		
		@Override
		public void handle(ActionEvent event) {
			String dayofweek = TXTdayOfweek.getText();
			int timeslot = Integer.parseInt(TXTtimeslot.getText());
			String prereqC = TXTprereqC.getText();
			
			if(dayofweek.equals("") || timeslot<1 || timeslot>24 || prereqC.equals(""))
			{
				JOptionPane.showMessageDialog(null, "Please fill in all the text fields with valid data!!");
			}
			else {
				
				Project p = TblSearchProject.getSelectionModel().getSelectedItem();
				if(p==null)
				{
					JOptionPane.showMessageDialog(null, "Please select a Project to accept!");
				}
				else
				{
				
					try {
						Class.forName("java.sql.DriverManager");
				        Connection con=(Connection) DriverManager.getConnection(
				                "jdbc:mysql://localhost:3306/project","root","tapeied");
				        Statement stmt=(Statement) con.createStatement();
				        String q;
				        
				        //check for pre requisite course
				        int sid = p.getSid();
				        q = "select CoursesTaken from students where email = (Select email from users where uid = "+sid+");";
				        System.out.println(q);
				        ResultSet rs = stmt.executeQuery(q);
				        String CourseList="";
				        if(rs.next())
				        {
				        	CourseList = rs.getString("CoursesTaken");
						}
				        System.out.println(CourseList);
				        if(!CourseList.equals("") && CourseList.contains(prereqC))
				        {
				        	//now check for clashes with timetable
				        	String[] cCodes = CourseList.split(";");
				        	boolean flag = true;
				        	for (String cCode : cCodes)
				        	{
				        		q = "select * from bookings where CourseCode = '"+cCode+"' and day = '"+dayofweek+"';";
				        		System.out.println(q);
				        		rs = stmt.executeQuery(q);
				        		while(rs.next())
				        		{
				        			System.out.println("I found a record");
				        			Time stTime = rs.getTime("Start");
				        			Time endTime = rs.getTime("End");
				        			if(timeslot>=stTime.getTime() && timeslot <= endTime.getTime())
				        			{
				        				JOptionPane.showMessageDialog(null, "This student is not available in this Time slot");
				        				flag = false;
				        				break;
				        			}
				        			
				        		}
				        		if(flag == false)
				        			break;
				        	}
				        	if(flag == true) {
				        		q = "Update Projects set Status = 'Accepted' where pid = "+ p.getPID() +";";
				        		stmt.executeUpdate(q);
				        		JOptionPane.showMessageDialog(null, "This project has been Accepted");
				        		SearchProjectB.fire();
				        		ViewProjectB.fire();
				        	}
				        }
			        	else
			        	{
			        		JOptionPane.showMessageDialog(null, "This student doesn't have this pre-requisite course");
			        	}
				        
				        
					}
					catch(Exception e)
					{
						System.out.println(e.getMessage());
					}
				}
				
				
		}
			
			BookPane.setVisible(false);
			ShowPane.setVisible(false);
			ProfilePane.setVisible(false);
			CancelPane.setVisible(false);
			Default.setVisible(false);
			ProjectsPane.setVisible(true);
			
			BookPane.setVisible(false);
			ShowPane.setVisible(false);
			ProfilePane.setVisible(false);
			CancelPane.setVisible(false);
			Default.setVisible(false);
			ProjectsPane.setVisible(true);
		}
		
	});	
		
		 /**
		 * Logout from the application and ask whether user wishes to login again or exit the application
		 */
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
	



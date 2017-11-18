package GUIComponents;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import application.Student;
import application.Timetable;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * @author Tanya Raj
 * This class opens the personalised timetable of the student with required courses
 *
 */
public class ViewTimetable implements javafx.fxml.Initializable {

	@FXML
	TableView<Timetable> TimetableTBL;
	@FXML
	TableColumn<Timetable,String> CC,M,T,W,Th,F,Tu,La;
	@FXML
	Button BackD;
	
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		TimetableTBL.setItems(null);
		 TimetableTBL.setEditable(true);
		 
		 
		 ArrayList<Timetable> t=StudentPage.student.getTimetable();
		 ObservableList<Timetable> l=FXCollections.observableArrayList(t);
		 
		 TimetableTBL.setItems(l);
		 TimetableTBL.scrollToColumn(W);
		 TimetableTBL.scrollToColumn(Th);
		 TimetableTBL.scrollToColumn(F);
		 TimetableTBL.scrollToColumn(Tu);
		 TimetableTBL.scrollToColumn(La);
		 CC.setCellValueFactory(new PropertyValueFactory<Timetable,String>("Course"));
		 M.setCellValueFactory(new PropertyValueFactory<Timetable,String>("M"));
		 T.setCellValueFactory(new PropertyValueFactory<Timetable,String>("T"));
		 W.setCellValueFactory(new PropertyValueFactory<Timetable,String>("W"));
		 Th.setCellValueFactory(new PropertyValueFactory<Timetable,String>("Th"));
		 F.setCellValueFactory(new PropertyValueFactory<Timetable,String>("F"));
		 Tu.setCellValueFactory(new PropertyValueFactory<Timetable,String>("Tu"));
		 La.setCellValueFactory(new PropertyValueFactory<Timetable,String>("La"));
		 BackD.setOnAction(new EventHandler<ActionEvent>() {
				
				/* 
				 * On clicking on Back button it will go back to the student's page and close the timetable
				 */
				@Override
				public void handle(ActionEvent event) {
					try
					{
					Parent page =  FXMLLoader.load(getClass().getResource("StudentPage.fxml"));
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

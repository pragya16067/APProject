package GUIComponents;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage Stage) {
		try {
			AnchorPane scene1 = (AnchorPane) FXMLLoader.load(Main.class.getResource("login.fxml"));
			Scene scene = new Scene(scene1);
			
			Stage.setScene(scene);
			Stage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

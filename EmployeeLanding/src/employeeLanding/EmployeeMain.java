package employeeLanding;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EmployeeMain extends Application {
	
	@Override
	public void start(Stage stage) throws Exception {
		Parent root = (Parent) FXMLLoader.load(getClass().getResource("elanding.fxml"));
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Hello!");
		stage.show();
		
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}

}

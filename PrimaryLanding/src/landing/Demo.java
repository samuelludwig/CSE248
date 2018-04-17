package landing;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Demo extends Application {
	
	@Override
	public void start(Stage stage) throws Exception {
		Parent root = (Parent) FXMLLoader.load(getClass().getResource("landing.fxml"));
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Welcome!");
		stage.show();
		
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}

}

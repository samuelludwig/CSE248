package editAccount;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class editMain extends Application {
	
	@Override
	public void start(Stage stage) throws Exception {
		Parent root = (Parent) FXMLLoader.load(getClass().getResource("EditAccount.fxml"));

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Edit Account-");
		stage.show();
		
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}

}

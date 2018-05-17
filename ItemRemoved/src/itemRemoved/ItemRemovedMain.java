package itemRemoved;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ItemRemovedMain extends Application {
	
	@Override
	public void start(Stage stage) throws Exception {
		Parent root = (Parent) FXMLLoader.load(getClass().getResource("ItemRemoved.fxml"));
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Item Removed!");
		stage.show();
		
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}

}

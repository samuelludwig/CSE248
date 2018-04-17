package customerProductSearch;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SearchMain extends Application {
	
	@Override
	public void start(Stage stage) throws Exception {
		Parent root = (Parent) FXMLLoader.load(getClass().getResource("CustomerProductSearch.fxml"));
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Shop For Products!");
		stage.show();
		
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}

}

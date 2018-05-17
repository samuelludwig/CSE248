package landing;

import java.net.URL;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class landingController implements Initializable{
	
	landingModel landModel = new landingModel();
	
	@FXML
	private Button loginButton;
	@FXML
	private Button createAccountButton;
	@FXML
	private Label dbStatus;
	
	
	@FXML
	public void login(ActionEvent event) throws Exception {
		try {
			Runtime.getRuntime().exec("java -jar \\H4HProject\\Runnables\\H4H_Login.jar");
			Stage stage = (Stage)this.loginButton.getScene().getWindow();
			stage.close();
		} catch(Exception localException) {
			localException.printStackTrace();
		}
	}
	
	
	
	public void createAccount(ActionEvent event) throws Exception {
		try {
			Runtime.getRuntime().exec("java -jar \\H4HProject\\Runnables\\CreateAccount.jar");
			Stage stage = (Stage)this.loginButton.getScene().getWindow();
			stage.close();
		} catch(Exception localException) {
			localException.printStackTrace();
		}
	}
	
	public void initialize(URL url, ResourceBundle rb) {
		
				
		if(this.landModel.isConnected()) {
			this.dbStatus.setText("Connected...");
		} else {
			this.dbStatus.setText("Connection failed...");
		}
	}
}

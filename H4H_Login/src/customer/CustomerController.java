package customer;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class CustomerController implements Initializable {
	
	@FXML
	private Label dbstatus;
	
	@FXML
	private Button editAccountButton;
	
	customerLandingModel customerModel = new customerLandingModel();
	
	public void initialize(URL url, ResourceBundle rb) {
		if(this.customerModel.isConnected()) {
			this.dbstatus.setText("Connected...");
		} else {
			this.dbstatus.setText("Connection failed...");
		}
	}
	
	public void editAccount(ActionEvent event) throws Exception {
		try {
			Runtime.getRuntime().exec("java -jar C:\\H4HProject\\EditAccount.jar");
			Stage stage = (Stage)this.editAccountButton.getScene().getWindow();
			stage.close();
		} catch(Exception localException) {
			localException.printStackTrace();
		}
	}
	
	
	
	
}

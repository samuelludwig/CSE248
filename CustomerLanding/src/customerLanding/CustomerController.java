package customerLanding;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class CustomerController implements Initializable {

	@FXML
	private Button searchForButton;
	@FXML
	private Button logOutButton;
	@FXML 
	private Button updateAccountButton;
	@FXML
	private Label dbStatus;
	
	
	public void search() throws IOException {
		Runtime.getRuntime().exec("java -jar \\H4HProject\\CustomerProductSearch.jar");
		Stage stage = (Stage)this.searchForButton.getScene().getWindow();
		stage.close();
	}
	
	public void logOut() throws IOException {
		Runtime.getRuntime().exec("java -jar \\H4HProject\\H4H_Login.jar");
		Stage stage = (Stage)this.updateAccountButton.getScene().getWindow();
		stage.close();
	}
	
	public void updateAccount() throws IOException {
		Runtime.getRuntime().exec("java -jar \\H4HProject\\EditAccount.jar");
		Stage stage = (Stage)this.logOutButton.getScene().getWindow();
		stage.close();
	}
	
	
	CustomerModel customerModel = new CustomerModel();
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		if(this.customerModel.isConnected()) {
			this.dbStatus.setText("Connected...");
		} else {
			this.dbStatus.setText("Connection failed...");
		}
	
	}

}

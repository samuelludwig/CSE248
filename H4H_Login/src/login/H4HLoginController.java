package login;

//import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

//import customer.CustomerController;
//import employee.EmployeeController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
//import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
//import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class H4HLoginController implements Initializable {
	
	H4HLoginModel loginModel = new H4HLoginModel();
	
	@FXML
	private Label dbstatus;
	@FXML
	private TextField username;
	@FXML
	private PasswordField password;
	@FXML
	private ComboBox<option> comboBox;
	@FXML
	private Button loginButton;
	@FXML
	private Label loginStatus;
	
	public void initialize(URL url, ResourceBundle rb) {
		
		/* Sets all users in database to not activeOnMachine, once they log in, they are marked as active,
		 * this gives us the ability to consistently track and reference the user via this one unique tag. 
		 */
		try {
			String sqlEdit = "UPDATE H4HUserTable SET activeOnMachine = " + "'False'";
			PreparedStatement prep = this.loginModel.connection.prepareStatement(sqlEdit);
			prep.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(this.loginModel.isConnected()) {
			this.dbstatus.setText("Connected...");
		} else {
			this.dbstatus.setText("Connection failed...");
		}
	
		this.comboBox.setItems(FXCollections.observableArrayList(option.values()));
	}
	
	@FXML
	public void login(ActionEvent event) throws Exception {
		try {
			
			if(this.loginModel.isLoggedIn(this.username.getText(), this.password.getText(), ((option)this.comboBox.getValue()).toString())) {
				Stage stage = (Stage)this.loginButton.getScene().getWindow();
				stage.close();
				switch (((option)this.comboBox.getValue()).toString()) {
					case "Employee":
						employeeLogin();
						break;
					case "Customer":
						customerLogin();
						break;
					default:
						this.loginStatus.setText("Invalid login, try again...");
						break;
				} 
				
				/* Adds activeOnMachine tracking tag to better reference user */
				String sqlEdit = "UPDATE H4HUserTable SET activeOnMachine = 'True' WHERE username = ?";
				PreparedStatement prep = this.loginModel.connection.prepareStatement(sqlEdit);
				prep.setString(1, this.username.getText());
				prep.execute();
				
			}
			else {
				this.loginStatus.setText("Invalid login, try again...");
			}
			
		} catch (Exception localException) {
			this.loginStatus.setText("Invalid login, try again...");
		}
	}
	
	public void customerLogin() {
		try {
//			Stage userStage = new Stage();
//			FXMLLoader loader = new FXMLLoader();
//			Pane root = (Pane)loader.load(getClass().getResource("/customer/Customer.fxml").openStream());
//			CustomerController customerController = (CustomerController)loader.getController();
//		
//			Scene scene = new Scene(root);
//			userStage.setScene(scene);
//			userStage.setTitle("Customer Dashboard");
//			userStage.setResizable(false);
//			userStage.show();
			/* Run Login program */
			Runtime.getRuntime().exec("java -jar \\H4HProject\\CustomerLanding.jar");
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void employeeLogin() {
		try {
//			Stage employeeStage = new Stage();
//			FXMLLoader employeeLoader = new FXMLLoader();
//			Pane employeeRoot = (Pane)employeeLoader.load(getClass().getResource("/employee/Employee.fxml").openStream());
//			EmployeeController employeeController = (EmployeeController)employeeLoader.getController();
//		
//			Scene scene = new Scene(employeeRoot);
//			employeeStage.setScene(scene);
//			employeeStage.setTitle("Employee Dashboard");
//			employeeStage.setResizable(false);
//			employeeStage.show();
			Runtime.getRuntime().exec("java -jar \\H4HProject\\EmployeeLanding.jar");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}
}

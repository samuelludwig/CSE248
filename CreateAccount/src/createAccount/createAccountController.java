package createAccount;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import dbUtil.dbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class createAccountController implements Initializable{
	
	createAccountModel createAccModel = new createAccountModel();
	
	@FXML
	private TextField fNameBox;
	@FXML
	private TextField lNameBox;
	@FXML
	private TextField usernameBox;
	@FXML
	private TextField phoneBox;
	@FXML
	private TextField emailBox;
	@FXML
	private TextField countryBox;
	@FXML
	private TextField stateBox;
	@FXML
	private TextField cityBox;
	@FXML
	private TextField zipBox;
	@FXML
	private TextField addressBox;
	@FXML
	private PasswordField passwordBox;
	@FXML
	private PasswordField passwordCheckBox;
	@FXML
	private Button createAccountButton;
	@FXML
	private Label passwordMismatch;
	@FXML
	private Label dbstatus;
	@FXML 
	private Label errorLabel;
	@FXML 
	private Label usernameTaken;
	
	@FXML
	private void userCreateAccount(ActionEvent event) throws Exception {
		String sqlInsert = "INSERT INTO H4HUserTable(firstName, lastname, username, password, phoneNumber, email, country, state, city, zipCode, address) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		try {
			if(!createAccModel.isUsernameTaken(this.usernameBox.getText())) {
				usernameTaken.setText("");
				
				if(this.passwordBox.getText().equals(this.passwordCheckBox.getText())) {
					passwordMismatch.setText("");
				
					if((this.fNameBox.getText() != null) && (!this.fNameBox.getText().isEmpty()) 
						&& (this.lNameBox.getText()!=null) && (!this.lNameBox.getText().isEmpty())  
						&& (this.usernameBox.getText()!=null) && (!this.usernameBox.getText().isEmpty()) 
						&& (this.passwordBox.getText()!=null) && (!this.passwordBox.getText().isEmpty()) 
						&& (this.phoneBox.getText()!=null) && (!this.phoneBox.getText().isEmpty()) 
						&& (this.emailBox.getText()!=null) && (!this.emailBox.getText().isEmpty())
						&& (this.passwordCheckBox.getText() != null) && (!this.passwordCheckBox.getText().isEmpty())) {
						/* All other values optional */
						errorLabel.setText("");
						Connection conn = dbConnection.getConnection();
						PreparedStatement stmt = conn.prepareStatement(sqlInsert);
						stmt.setString(1, this.fNameBox.getText());
						stmt.setString(2, this.lNameBox.getText());
						stmt.setString(3, this.usernameBox.getText());
						stmt.setString(4, this.passwordBox.getText());
						stmt.setString(5, this.phoneBox.getText());
						stmt.setString(6, this.emailBox.getText());
						stmt.setString(7, this.countryBox.getText());
						stmt.setString(8, this.stateBox.getText());
						stmt.setString(9, this.cityBox.getText());
						stmt.setString(10, this.zipBox.getText());
						stmt.setString(11, this.addressBox.getText());
				
						stmt.execute();
						conn.close();
					
						/* Run Login program */
						Runtime.getRuntime().exec("java -jar \\H4HProject\\H4H_Login.jar");
						Stage stage = (Stage)this.createAccountButton.getScene().getWindow();
						stage.close();
						
					} else {
						errorLabel.setText("ERROR: missing fields-");
					}
				
				} else {
				passwordMismatch.setText("Error: Passwords do not match-");
				}
			
			} else {
				usernameTaken.setText("Username already taken-");
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
		
	createAccountModel newModel = new createAccountModel();
	
	public void initialize(URL url, ResourceBundle rb) {
		if(this.newModel.isConnected()) {
			this.dbstatus.setText("Connected...");
		} else {
			this.dbstatus.setText("Connection failed...");
		}
	}
	
}



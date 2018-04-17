package editAccount;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

public class EditAccountController implements Initializable {
	
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
	private Button updateAccountButton;
	@FXML
	private Button addPaymentButton;
	@FXML
	private Label passwordMismatch;
	@FXML
	private Label dbstatus;
	@FXML 
	private Label errorLabel;
	@FXML 
	private Label usernameTaken;
	
	
	EditAccountModel editModel = new EditAccountModel();
	
	@FXML
	private void editAccount(ActionEvent event) throws Exception {
		
		String sqlEdit = "UPDATE H4HUserTable SET"
				+ " firstName = ?,"
				+ " lastName = ?,"
				+ " username = ?,"
				+ " password = ?,"
				+ " phoneNumber = ?,"
				+ " email = ?,"
				+ " country = ?,"
				+ " state = ?,"
				+ " city = ?,"
				+ " zipCode = ?,"
				+ " address = ?"
				+ " WHERE activeOnMachine = 'True'";
		
		String sqlGetUsername = "SELECT username FROM H4HUserTable WHERE activeOnMachine = 'True'";
		
		try {
			
			/******************************************************************************************************************/
			Connection conn = dbConnection.getConnection();
			PreparedStatement getUser = conn.prepareStatement(sqlGetUsername);
			ResultSet rs = getUser.executeQuery();
			
			if(!editModel.isUsernameTaken(this.usernameBox.getText()) || this.usernameBox.getText().equals(rs.getString(1))) {
				usernameTaken.setText("");
			/*Above block is to make sure that the user is not forced to change their username whenever they edit account info*/
				
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
						PreparedStatement stmt = conn.prepareStatement(sqlEdit);
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
						Stage stage = (Stage)this.updateAccountButton.getScene().getWindow();
						stage.close();
						
					} else {
						errorLabel.setText("Error: missing fields-");
					}
				
				} else {
					passwordMismatch.setText("Error: Passwords do not match-");
				}
			
			} else {
				usernameTaken.setText("Error: Username already taken-");
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addPaymentInfo(ActionEvent event) throws Exception {
		
		String sqlEdit = "UPDATE H4HUserTable SET"
				+ " firstName = ?,"
				+ " lastName = ?,"
				+ " username = ?,"
				+ " password = ?,"
				+ " phoneNumber = ?,"
				+ " email = ?,"
				+ " country = ?,"
				+ " state = ?,"
				+ " city = ?,"
				+ " zipCode = ?,"
				+ " address = ?"
				+ " WHERE activeOnMachine = 'True'";
		
		String sqlGetUsername = "SELECT username FROM H4HUserTable WHERE activeOnMachine = 'True'";
		
		try {
			
			/******************************************************************************************************************/
			Connection conn = dbConnection.getConnection();
			PreparedStatement getUser = conn.prepareStatement(sqlGetUsername);
			ResultSet rs = getUser.executeQuery();
			
			if(!editModel.isUsernameTaken(this.usernameBox.getText()) || this.usernameBox.getText().equals(rs.getString(1))) {
				usernameTaken.setText("");
			/*Above block is to make sure that the user is not forced to change their username whenever they edit account info*/
				
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
						PreparedStatement stmt = conn.prepareStatement(sqlEdit);
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
					
						/* Run AddPaymentInfo program */
						Runtime.getRuntime().exec("java -jar \\H4HProject\\AddPaymentInfo.jar");
						Stage stage = (Stage)this.addPaymentButton.getScene().getWindow();
						stage.close();
						
					} else {
						errorLabel.setText("Error: missing fields-");
					}
				
				} else {
					passwordMismatch.setText("Error: Passwords do not match-");
				}
			
			} else {
				usernameTaken.setText("Error: Username already taken-");
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		if(this.editModel.isConnected()) {
			this.dbstatus.setText("Connected...");
		} else {
			this.dbstatus.setText("Connection failed...");
		}
	}
	
		
}

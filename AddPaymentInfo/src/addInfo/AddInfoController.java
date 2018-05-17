package addInfo;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import dbUtil.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddInfoController implements Initializable{
	
	AddInfoModel addModel = new AddInfoModel();
	
	@FXML
	private TextField cardHolderBox;
	@FXML
	private TextField cardNumberBox;
	@FXML
	private TextField cardExpDateBox;
	@FXML
	private TextField secCodeBox;
	@FXML
	private TextField cardTypeBox;
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
	private Button addInfoButton;
	@FXML
	private Label dbStatus;
	@FXML 
	private Label allRequiredLabel;
	
	@FXML
	private void addPaymentInfo(ActionEvent event) throws Exception {
		String sqlInsert = "UPDATE H4HUserTable SET"
				+ " cardHolderName = ?,"
				+ " cardNumber = ?,"
				+ " cardExpDate = ?,"
				+ " securityCode = ?,"
				+ " cardType = ?,"
				+ " country = ?,"
				+ " state = ?,"
				+ " city = ?,"
				+ " zipCode = ?,"
				+ " address = ?"
				+ " WHERE activeOnMachine = 'True'";
		
		try {
					if((this.cardHolderBox.getText() != null) && (!this.cardHolderBox.getText().isEmpty()) 
						&& (this.cardNumberBox.getText()!=null) && (!this.cardNumberBox.getText().isEmpty())  
						&& (this.cardExpDateBox.getText()!=null) && (!this.cardExpDateBox.getText().isEmpty()) 
						&& (this.countryBox.getText()!=null) && (!this.countryBox.getText().isEmpty()) 
						&& (this.stateBox.getText()!=null) && (!this.stateBox.getText().isEmpty())
						&& (this.cardTypeBox.getText()!=null) && (!this.cardTypeBox.getText().isEmpty())  
						&& (this.secCodeBox.getText()!=null) && (!this.secCodeBox.getText().isEmpty()) 
						&& (this.cityBox.getText() != null) && (!this.cityBox.getText().isEmpty())
						&& (this.zipBox.getText() != null) && (!this.zipBox.getText().isEmpty())
						&& (this.addressBox.getText() != null) && (!this.addressBox.getText().isEmpty())) {
						
						allRequiredLabel.setText("");
						Connection conn = DBConnection.getConnection();
						PreparedStatement stmt = conn.prepareStatement(sqlInsert);
						stmt.setString(1, this.cardHolderBox.getText());
						stmt.setString(2, this.cardNumberBox.getText());
						stmt.setString(3, this.cardExpDateBox.getText());
						stmt.setString(4, this.secCodeBox.getText());
						stmt.setString(5, this.cardTypeBox.getText());
						stmt.setString(6, this.countryBox.getText());
						stmt.setString(7, this.stateBox.getText());
						stmt.setString(8, this.cityBox.getText());
						stmt.setString(9, this.zipBox.getText());
						stmt.setString(10, this.addressBox.getText());
				
						stmt.execute();
						conn.close();
					
						/* Go to appropriate landing */
						String sqlDivision = "SELECT division FROM H4HUserTable WHERE activeOnMachine = 'True'";
						PreparedStatement p = conn.prepareStatement(sqlDivision);
						ResultSet rs = p.executeQuery();
						
						if(rs.getString(1).equals("Customer")) {
							Runtime.getRuntime().exec("java -jar \\H4HProject\\Runnables\\CustomerLanding.jar");
							Stage stage = (Stage)this.addInfoButton.getScene().getWindow();
							stage.close();
						} 
						else if(rs.getString(1).equals("Employee")) {
							Runtime.getRuntime().exec("java -jar \\H4HProject\\Runnables\\EmployeeLanding.jar");
							Stage stage = (Stage)this.addInfoButton.getScene().getWindow();
							stage.close();
						}
						
						Stage stage = (Stage)this.addInfoButton.getScene().getWindow();
						stage.close();
						
					} else {
						allRequiredLabel.setText("ERROR: missing fields-");
					}
				
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
		
	AddInfoModel newModel = new AddInfoModel();
	
	public void initialize(URL url, ResourceBundle rb) {
		if(this.newModel.isConnected()) {
			this.dbStatus.setText("Connected...");
		} else {
			this.dbStatus.setText("Connection failed...");
		}
	}
	
}

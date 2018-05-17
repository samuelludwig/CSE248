package addProductToList;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import dbUtil.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddProductController implements Initializable {
	
	
	@FXML
	private Button addButton;
	@FXML 
	private Button goBackButton;
	@FXML
	private TextField nameField;
	@FXML
	private TextArea descField;
	@FXML
	private TextField priceField;
	@FXML
	private TextField lengthField;
	@FXML
	private TextField widthField;
	@FXML
	private TextField heightField;
	@FXML
	private TextField imageField;
	@FXML
	private CheckBox furnitureCheck;
	@FXML
	private CheckBox applianceCheck;
	@FXML
	private CheckBox materialCheck;
	@FXML
	private CheckBox toolCheck;
	@FXML
	private Label dbStatus;
	@FXML
	private Label addErrorLabel;
	@FXML
	private Label imageAcceptedLabel;
	
	AddProductModel addPModel = new AddProductModel();

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		if(this.addPModel.isConnected()) {
			this.dbStatus.setText("Connected...");
		} else {
			this.dbStatus.setText("Connection failed...");
		}
	}
	
	@FXML
	private void addProduct(ActionEvent event) throws Exception {
		String sqlInsert = "INSERT INTO H4HProductTable (name, description, price, length, width, height, "
				+ "dateAdded, isFurniture, isAppliance, isBuildingMaterial, isTool, image) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		try {

			if((this.nameField.getText() != null) && (!this.nameField.getText().isEmpty()) 
					&& (this.descField.getText() != null) && (!this.descField.getText().isEmpty())  
					&& (this.priceField.getText() != null) && (!this.priceField.getText().isEmpty()) 
					&& (this.lengthField.getText() != null) && (!this.lengthField.getText().isEmpty()) 
					&& (this.widthField.getText() != null) && (!this.widthField.getText().isEmpty()) 
					&& (this.heightField.getText() != null) && (!this.heightField.getText().isEmpty())
					&& (this.imageField != null)) {
				/* All other values optional */
				addErrorLabel.setText("Item Added!");
				Connection conn = DBConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sqlInsert);
				stmt.setString(1, this.nameField.getText());
				stmt.setString(2, this.descField.getText());
				stmt.setString(3, this.priceField.getText());
				stmt.setString(4, this.lengthField.getText());
				stmt.setString(5, this.widthField.getText());
				stmt.setString(6, this.heightField.getText());
				
				// Date setup //
				SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");  
				Date date = new Date();
				stmt.setString(7, formatter.format(date));

				stmt.setBoolean(8, this.furnitureCheck.isSelected());
				stmt.setBoolean(9, this.applianceCheck.isSelected());
				stmt.setBoolean(10, this.materialCheck.isSelected());
				stmt.setBoolean(11, this.toolCheck.isSelected());
				
				// Upload image to table //
				byte[] bytesFromFile = null;
				try {
					String fileName = imageField.getText();
					File f = new File(fileName);
					bytesFromFile = Files.readAllBytes(f.toPath());
				} catch (Exception e) {
					e.printStackTrace();
				}

				stmt.setBytes (12,  bytesFromFile);

				stmt.execute();
				conn.close();
				
				nameField.setText("");
				descField.setText("");
				priceField.setText("");
				lengthField.setText("");
				widthField.setText("");
				heightField.setText("");
				imageField.setText("");

			} else {
				addErrorLabel.setText("ERROR: missing fields-");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void goBack() throws Exception {
		Runtime.getRuntime().exec("java -jar \\H4HProject\\Runnables\\EmployeeLanding.jar");
		Stage stage = (Stage)this.goBackButton.getScene().getWindow();
		stage.close();

	}
}

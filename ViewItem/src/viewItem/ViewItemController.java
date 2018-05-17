package viewItem;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ViewItemController implements Initializable {

	@FXML
	private Label dbStatus;
	@FXML
	private Button goBack;
	@FXML
	private Button reserveItem;
	@FXML
	private Text prodName;
	@FXML
	private Text prodDescription;
	@FXML
	private Text prodPrice;
	@FXML
	private Text prodLength;
	@FXML
	private Text prodWidth;
	@FXML
	private Text prodHeight;
	@FXML
	private Text prodDateAdded;
	@FXML
	private Text prodID;
	@FXML
	private ImageView prodImage;
	
	ViewItemModel newModel = new ViewItemModel();
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		if(this.newModel.isConnected()) {
			this.dbStatus.setText("Connected...");
		} else {
			this.dbStatus.setText("Connection failed...");
		}
		getChosenInfo();
	}

	public void getChosenInfo() {
		String getSelectedEntry = "SELECT * FROM H4HProductTable WHERE selected = 1";
		try {
			PreparedStatement stmt = this.newModel.connection.prepareStatement(getSelectedEntry);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			
			prodName.setText(rs.getString("name"));
			prodID.setText(rs.getString("id"));
			prodPrice.setText(rs.getString("price"));
			prodDateAdded.setText(rs.getString("dateAdded"));
			prodDescription.setText(rs.getString("description"));
			prodLength.setText(rs.getString("length"));
			prodWidth.setText(rs.getString("width"));
			prodHeight.setText(rs.getString("height"));
			
			Image img = new Image(rs.getBinaryStream("image"));
			prodImage.setImage(img);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	public void goBack(ActionEvent event) {
		try {
			Runtime.getRuntime().exec("java -jar \\H4HProject\\Runnables\\CustomerProductSearch.jar");
			Stage stage = (Stage)this.goBack.getScene().getWindow();
			stage.close();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	int id = 0;	// <-- user id for next 3 methods
	public void createReservedTable() {
		String getUserID = "SELECT id FROM H4HUserTable WHERE activeOnMachine = 'True'";
		id = 0;
		try {
			PreparedStatement stmt = this.newModel.connection.prepareStatement(getUserID);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			id = rs.getInt("id");
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String createReservedTable = "CREATE TABLE IF NOT EXISTS reservedTable" + id + "(id INTEGER PRIMARY KEY AUTOINCREMENT "
				+ "NOT NULL UNIQUE DEFAULT 0, name TEXT NOT NULL, description TEXT NOT NULL, price CURRENCY "
				+ "NOT NULL, length DOUBLE, width DOUBLE, height DOUBLE, dateAdded DATE "
				+ "NOT NULL, isFurniture BOOLEAN DEFAULT 0, isAppliance BOOLEAN DEFAULT 0, isBuildingMaterial "
				+ "BOOLEAN DEFAULT 0, isTool BOOLEAN DEFAULT 0, selected BOOLEAN DEFAULT 0, image BLOB NOT NULL DEFAULT 0, "
				+ "reservedBy VARCHAR DEFAULT H4HSTORE)";
		
		try {
			PreparedStatement stmt = this.newModel.connection.prepareStatement(createReservedTable);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void moveToReservedTable() {
		String insert = "INSERT INTO reservedTable" + id + " SELECT * FROM H4HProductTable WHERE selected = 1";
		try {
			PreparedStatement stmt = this.newModel.connection.prepareStatement(insert);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String username = "";
		String getUserName = "SELECT username FROM H4HUserTable WHERE activeOnMachine = 'True'";
		try {
			PreparedStatement stmt = this.newModel.connection.prepareStatement(getUserName);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			username = rs.getString("username");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String byMarkLocal = "UPDATE reservedTable" + id + " SET reservedBy = ? WHERE selected = 1";
		try {
			PreparedStatement stmt = this.newModel.connection.prepareStatement(byMarkLocal);
			stmt.setString(1, username);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String genTableInsert = "INSERT INTO reservedTable SELECT * FROM H4HProductTable WHERE selected = 1";
		try {
			PreparedStatement stmt = this.newModel.connection.prepareStatement(genTableInsert);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String byMark = "UPDATE reservedtable SET reservedBy = ? WHERE selected = 1";
		try {
			PreparedStatement stmt = this.newModel.connection.prepareStatement(byMark);
			stmt.setString(1, username);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String remove = "DELETE FROM H4HProductTable WHERE selected = 1";
		try {
			PreparedStatement stmt = this.newModel.connection.prepareStatement(remove);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void clearSelected() {
		String clear = "UPDATE reservedTable SET selected = 0 WHERE selected = 1";
		try {
			PreparedStatement stmt = this.newModel.connection.prepareStatement(clear);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String clearLocal = "UPDATE reservedTable" + id + " SET selected = 0 WHERE selected = 1";
		try {
			PreparedStatement stmt = this.newModel.connection.prepareStatement(clearLocal);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void reserveItem(ActionEvent event) {
		createReservedTable();
		moveToReservedTable();
		clearSelected();
		
		try {
			Runtime.getRuntime().exec("java -jar \\H4HProject\\Runnables\\ItemReserved.jar");
			Stage stage = (Stage)this.reserveItem.getScene().getWindow();
			stage.close();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}

package customerProductSearch;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import dbUtil.DBConnection;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SearchController implements Initializable {
	
	@FXML
	private Label dbStatus;
	@FXML
	private TextField nameSearch;
	@FXML
	private TextField idSearch;
	@FXML
	private TextField priceSearch;
	@FXML
	private TextField lengthSearch;
	@FXML
	private TextField widthSearch;
	@FXML
	private TextField heightSearch;
	@FXML
	private Button searchButton;
	@FXML
	private Button previousPageButton;
	@FXML
	private Button nextPageButton;
	@FXML
	private Button logOutButton;
	@FXML
	private Button editAccountButton;
	@FXML
	private Button goBack;
	@FXML
	private ComboBox<Categories> categorySelect;
	@FXML 
	private TextField id1;
	@FXML 
	private TextField id2;
	@FXML 
	private TextField id3;
	@FXML 
	private TextField id4;
	@FXML 
	private TextField id5;
	@FXML 
	private TextField id6;
	@FXML 
	private TextArea name1;
	@FXML 
	private TextArea name2;
	@FXML 
	private TextArea name3;
	@FXML 
	private TextArea name4;
	@FXML 
	private TextArea name5;
	@FXML 
	private TextArea name6;
	@FXML 
	private TextArea description1;
	@FXML 
	private TextArea description2;
	@FXML 
	private TextArea description3;
	@FXML 
	private TextArea description4;
	@FXML 
	private TextArea description5;
	@FXML 
	private TextArea description6;
	@FXML 
	private TextField price1;
	@FXML 
	private TextField price2;
	@FXML 
	private TextField price3;
	@FXML 
	private TextField price4;
	@FXML 
	private TextField price5;
	@FXML 
	private TextField price6;
	@FXML 
	private TextArea dateAdded1;
	@FXML 
	private TextArea dateAdded2;
	@FXML 
	private TextArea dateAdded3;
	@FXML 
	private TextArea dateAdded4;
	@FXML 
	private TextArea dateAdded5;
	@FXML 
	private TextArea dateAdded6;
	@FXML 
	private TextField length1;
	@FXML 
	private TextField length2;
	@FXML 
	private TextField length3;
	@FXML 
	private TextField length4;
	@FXML 
	private TextField length5;
	@FXML 
	private TextField length6;
	@FXML 
	private TextField width1;
	@FXML 
	private TextField width2;
	@FXML 
	private TextField width3;
	@FXML 
	private TextField width4;
	@FXML 
	private TextField width5;
	@FXML 
	private TextField width6;
	@FXML 
	private TextField height1;
	@FXML 
	private TextField height2;
	@FXML 
	private TextField height3;
	@FXML 
	private TextField height4;
	@FXML 
	private TextField height5;
	@FXML 
	private TextField height6;
	
	public final int ROWSIZE = 6;	// Amount of rows per page
	public int pageNum = 0;
	
	SearchModel newSearchModel = new SearchModel();

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		if(this.newSearchModel.isConnected()) {
			this.dbStatus.setText("Connected...");
		} else {
			this.dbStatus.setText("Connection failed...");
		}
		
		String clearSelected = "UPDATE H4HProductTable SET selected = 0";
		try {
			PreparedStatement stmt = this.newSearchModel.connection.prepareStatement(clearSelected);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		this.categorySelect.setItems(FXCollections.observableArrayList(Categories.values()));
	}
	
	// puts all possible results into one virtual table to be sorted through //
	public void getResults() {
		
		String sqlGetData = "INSERT INTO VirtualProductTable SELECT * FROM H4HProductTable WHERE";
		
		switch (((Categories)this.categorySelect.getValue()).toString()) {
		case "Furniture":
			sqlGetData = sqlGetData.concat(" isFurniture = 1");
			break;
		case "Appliances":
			sqlGetData = sqlGetData.concat(" isAppliance = 1");
			break;
		case "Building_Materials":
			sqlGetData = sqlGetData.concat(" isBuildingMaterial = 1");
			break;
		case "Tools":
			sqlGetData = sqlGetData.concat(" isTool = 1");
			break;
		default:
			break;
		}
		
		// corrects for empty/unimportant fields //
		try {
			Connection conn = DBConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sqlGetData);
			
			int i = 1;
			if(!this.idSearch.getText().equals(null) && !this.idSearch.getText().isEmpty()) {
				sqlGetData = sqlGetData.concat(" AND id = ?");
				stmt = conn.prepareStatement(sqlGetData);
				stmt.setString(i, this.idSearch.getText());
				i++;
			}
			
			if(!this.priceSearch.getText().equals(null) && !this.priceSearch.getText().isEmpty()) {
				sqlGetData = sqlGetData.concat(" AND price <= ?");
				stmt = conn.prepareStatement(sqlGetData);
				stmt.setString(i, this.priceSearch.getText());
				i++;
			}
			
			if(!this.lengthSearch.getText().equals(null) && !this.lengthSearch.getText().isEmpty()) {
				sqlGetData = sqlGetData.concat(" AND length <= ?");
				stmt = conn.prepareStatement(sqlGetData);
				stmt.setString(i, this.lengthSearch.getText());
				i++;
			}
			
			if(!this.widthSearch.getText().equals(null) && !this.widthSearch.getText().isEmpty()) {
				sqlGetData = sqlGetData.concat(" AND width <= ?");
				stmt = conn.prepareStatement(sqlGetData);
				stmt.setString(i, this.widthSearch.getText());
				i++;
			}
			
			if(!this.heightSearch.getText().equals(null) && !this.heightSearch.getText().isEmpty()) {
				sqlGetData = sqlGetData.concat(" AND height <= ?");
				stmt = conn.prepareStatement(sqlGetData);
				stmt.setString(i, this.heightSearch.getText());
				i++;
			}
			
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void clearFields() {
		
		id1.setText("");
		name1.setText("");
		name1.setWrapText(true);
		description1.setText("");
		description1.setWrapText(true);		// Clears out all of the result fields to reset consecutive searches //
		dateAdded1.setText("");
		price1.setText("");
		length1.setText("");
		width1.setText("");
		height1.setText("");
				
		id2.setText("");
		name2.setText("");
		name2.setWrapText(true);
		description2.setText("");
		description2.setWrapText(true);
		dateAdded2.setText("");
		price2.setText("");
		length2.setText("");
		width2.setText("");
		height2.setText("");
				
		id3.setText("");
		name3.setText("");
		name3.setWrapText(true);
		description3.setText("");
		description3.setWrapText(true);
		dateAdded3.setText("");
		price3.setText("");
		length3.setText("");
		width3.setText("");
		height3.setText("");
				
		id4.setText("");
		name4.setText("");
		name4.setWrapText(true);
		description4.setText("");
		description4.setWrapText(true);
		dateAdded4.setText("");
		price4.setText("");
		length4.setText("");
		width4.setText("");
		height4.setText("");
		
		id5.setText("");
		name5.setText("");
		name5.setWrapText(true);
		description5.setText("");
		description5.setWrapText(true);
		dateAdded5.setText("");
		price5.setText("");
		length5.setText("");
		width5.setText("");
		height5.setText("");

		id6.setText("");
		name6.setText("");
		name6.setWrapText(true);
		description6.setText("");
		description6.setWrapText(true);
		dateAdded6.setText("");
		price6.setText("");
		length6.setText("");
		width6.setText("");
		height6.setText("");
	}
	
	public void searchProduct (ActionEvent event) throws Exception {
		// makes sure we are not just piling row after row every consecutive search //

		String drop = "DELETE FROM VirtualProductTable";
		PreparedStatement stmt = this.newSearchModel.connection.prepareStatement(drop);
		stmt.execute();
		stmt.close();
		
		pageNum = 0;
		
		// 1. Creates a virtual table and inserts all entries of the category we're looking for, that are also within our other criteria 
		// 2. Sort that table into a list and print out the fields we need row by row 
		getResults();
		showFirstSix();
	}
	
	public void getProductFromTable() {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sqlTakeFields = "SELECT * FROM VirtualProductTable WHERE VirtualProductTable MATCH ? ORDER BY rank DESC";
		try {
			stmt = this.newSearchModel.connection.prepareStatement(sqlTakeFields);
			stmt.setString(1, nameSearch.getText());
			rs = stmt.executeQuery();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		// gets a sorted list of matching products, next we just need to print them out entry by entry //
		// we'll traverse every row in the result set, and print all needed values before moving to the next row //
		
		try {
			
			for (int i = 0; i < pageNum*ROWSIZE; i++) {
				rs.next();
			}
			System.out.println(pageNum*ROWSIZE);
			
			if(! rs.next()) {
				return;
			}
			
			// first entry
			String id = rs.getString("id");
			String name = rs.getString("name");
			String description = rs.getString("description");
			String price = rs.getString("price");
			String length = rs.getString("length");
			String width = rs.getString("width");
			String height = rs.getString("height");
			String dateAdded = rs.getString("dateAdded");
			
			id1.setText(id);
			name1.setText(name);
			name1.setWrapText(true);
			description1.setText(description);
			description1.setWrapText(true);
			dateAdded1.setText("Added on: \n" + dateAdded);
			price1.setText(price);
			length1.setText(length);
			width1.setText(width);
			height1.setText(height);
			
			if(! rs.next()) {
				return;
			}
			// second entry
			
			id = rs.getString("id");
			name = rs.getString("name");
			description = rs.getString("description");
			price = rs.getString("price");
			length = rs.getString("length");
			width = rs.getString("width");
			height = rs.getString("height");
			dateAdded = rs.getString("dateAdded");
			
			id2.setText(id);
			name2.setText(name);
			name2.setWrapText(true);
			description2.setText(description);
			description2.setWrapText(true);
			dateAdded2.setText("Added on: \n" + dateAdded);
			price2.setText(price);
			length2.setText(length);
			width2.setText(width);
			height2.setText(height);
			
			if(! rs.next()) {
				return;
			}
			// third entry
			
			id = rs.getString("id");
			name = rs.getString("name");
			description = rs.getString("description");
			price = rs.getString("price");
			length = rs.getString("length");
			width = rs.getString("width");
			height = rs.getString("height");
			dateAdded = rs.getString("dateAdded");
			
			id3.setText(id);
			name3.setText(name);
			name3.setWrapText(true);
			description3.setText(description);
			description3.setWrapText(true);
			dateAdded3.setText("Added on: \n" + dateAdded);
			price3.setText(price);
			length3.setText(length);
			width3.setText(width);
			height3.setText(height);
			
			if(! rs.next()) {
				return;
			}
			// fourth entry
			
			id = rs.getString("id");
			name = rs.getString("name");
			description = rs.getString("description");
			price = rs.getString("price");
			length = rs.getString("length");
			width = rs.getString("width");
			height = rs.getString("height");
			dateAdded = rs.getString("dateAdded");
			
			id4.setText(id);
			name4.setText(name);
			name4.setWrapText(true);
			description4.setText(description);
			description4.setWrapText(true);
			dateAdded4.setText("Added on: \n" + dateAdded);
			price4.setText(price);
			length4.setText(length);
			width4.setText(width);
			height4.setText(height);
			
			if(! rs.next()) {
				return;
			}
			// fifth entry
			
			id = rs.getString("id");
			name = rs.getString("name");
			description = rs.getString("description");
			price = rs.getString("price");
			length = rs.getString("length");
			width = rs.getString("width");
			height = rs.getString("height");
			dateAdded = rs.getString("dateAdded");
			
			id5.setText(id);
			name5.setText(name);
			name5.setWrapText(true);
			description5.setText(description);
			description5.setWrapText(true);
			dateAdded5.setText("Added on: \n" + dateAdded);
			price5.setText(price);
			length5.setText(length);
			width5.setText(width);
			height5.setText(height);
			
			if(! rs.next()) {
				return;
			}
			// sixth entry
			
			id = rs.getString("id");
			name = rs.getString("name");
			description = rs.getString("description");
			price = rs.getString("price");
			length = rs.getString("length");
			width = rs.getString("width");
			height = rs.getString("height");
			dateAdded = rs.getString("dateAdded");
			
			id6.setText(id);
			name6.setText(name);
			name6.setWrapText(true);
			description6.setText(description);
			description6.setWrapText(true);
			dateAdded6.setText("Added on: \n" + dateAdded);
			price6.setText(price);
			length6.setText(length);
			width6.setText(width);
			height6.setText(height);
			
			stmt.close();
			rs.close();
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		} 
	}
		
	public void showFirstSix() {
		
		clearFields();
		getProductFromTable();

	}
	
	public void gotoPreviousPage (ActionEvent event) throws Exception {
		if (pageNum>0) {
			pageNum--;
		}
		
		clearFields();	
		getProductFromTable();
	}
	
	public void gotoNextPage (ActionEvent event) throws Exception {
		String countTable = "SELECT COUNT(*) FROM VirtualProductTable";
		PreparedStatement countStmt = this.newSearchModel.connection.prepareStatement(countTable);
		ResultSet countRS = countStmt.executeQuery();
		countRS.next();
		if((countRS.getInt(1) / (pageNum+1.0 * ROWSIZE)) > 1.0) {
			pageNum++;
		}
		countStmt.close();
		countRS.close();
		
		clearFields();
		getProductFromTable();
	}

	public void item1Chosen(MouseEvent event) throws Exception {
		String id = id1.getText();
		gotoItemListing(id);
	}

	public void item2Chosen(MouseEvent event) throws Exception {
		String id = id2.getText();
		gotoItemListing(id);
	}

	public void item3Chosen(MouseEvent event) throws Exception {
		String id = id3.getText();
		gotoItemListing(id);
	}

	public void item4Chosen(MouseEvent event) throws Exception {
		String id = id4.getText();
		gotoItemListing(id);
	}

	public void item5Chosen(MouseEvent event) throws Exception {
		String id = id5.getText();
		gotoItemListing(id);
	}

	public void item6Chosen(MouseEvent event)  throws Exception {
		String id = id6.getText();
		gotoItemListing(id);
	}
	
	public void gotoItemListing(String id) throws Exception {

		String sqlSelected = "UPDATE H4HProductTable SET selected = 1 WHERE id = ?";
		PreparedStatement stmt = this.newSearchModel.connection.prepareStatement(sqlSelected);
		stmt.setString(1, id);
		stmt.execute();

		String division = "";
		String getDiv = "SELECT division FROM H4HUserTable WHERE activeOnMachine = 'True'";
		PreparedStatement divStmt = this.newSearchModel.connection.prepareStatement(getDiv);
		ResultSet rs = divStmt.executeQuery();
		rs.next();
		division = rs.getString("division");
		
		if(division.equals("Employee")) {
			try {
				Runtime.getRuntime().exec("java -jar \\H4HProject\\Runnables\\EmployeeViewItem.jar");
				Stage stage = (Stage)this.searchButton.getScene().getWindow();
				stage.close();
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		} else {
			try {
				Runtime.getRuntime().exec("java -jar \\H4HProject\\Runnables\\ViewItem.jar");
				Stage stage = (Stage)this.searchButton.getScene().getWindow();
				stage.close();
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		
		stmt.close();
		divStmt.close();
		rs.close();
	}
	
	public void logOut (ActionEvent event) throws Exception {
		try {
			Runtime.getRuntime().exec("java -jar \\H4HProject\\Runnables\\H4H_Login.jar");
			Stage stage = (Stage)this.logOutButton.getScene().getWindow();
			stage.close();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public void editAccount (ActionEvent event) throws Exception {
		try {
			Runtime.getRuntime().exec("java -jar \\H4HProject\\Runnables\\EditAccount.jar");
			Stage stage = (Stage)this.editAccountButton.getScene().getWindow();
			stage.close();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void goBack (ActionEvent event) throws Exception {
		
		String division = "";
		String getDiv = "SELECT division FROM H4HUserTable WHERE activeOnMachine = 'True'";
		PreparedStatement divStmt = this.newSearchModel.connection.prepareStatement(getDiv);
		ResultSet rs = divStmt.executeQuery();
		rs.next();
		division = rs.getString("division");
		
		if(division.equals("Employee")) {
			try {
				Runtime.getRuntime().exec("java -jar \\H4HProject\\Runnables\\EmployeeLanding.jar");
				Stage stage = (Stage)this.goBack.getScene().getWindow();
				stage.close();
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		} else {
			try {
				Runtime.getRuntime().exec("java -jar \\H4HProject\\Runnables\\CustomerLanding.jar");
				Stage stage = (Stage)this.goBack.getScene().getWindow();
				stage.close();
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		divStmt.close();
		rs.close();
	}
	
}

package customerProductSearch;

import java.net.URL;
//import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
//import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
	private Button viewOrdersButton;
	@FXML
	private Button editAccountButton;
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
	@FXML
	private ImageView image1;
	@FXML
	private ImageView image2;
	@FXML
	private ImageView image3;
	@FXML
	private ImageView image4;
	@FXML
	private ImageView image5;
	@FXML
	private ImageView image6;
	
	public int rowSize = 6;
	
	SearchModel newSearchModel = new SearchModel();

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		if(this.newSearchModel.isConnected()) {
			this.dbStatus.setText("Connected...");
		} else {
			this.dbStatus.setText("Connection failed...");
		}
		this.categorySelect.setItems(FXCollections.observableArrayList(Categories.values()));
	}
	
	// puts all possible results into one virtual table to be sorted through //
	public void getResults() {
//		String makeVirtualTable = "CREATE VIRTUAL TABLE VirtualProductTable USING fts5 "
//				+ "(id, name, description, price, length, width, height, "
//				+ "dateAdded, isFurniture, isAppliance, isBuildingMaterial, isTool, selected, image)";
//		try {
//			PreparedStatement stmt = this.newSearchModel.connection.prepareStatement(makeVirtualTable);
//			stmt.execute();
//			System.out.println("executed: " + makeVirtualTable);
//			stmt.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		
		String sqlGetData = "INSERT INTO VirtualProductTable SELECT * FROM H4HProductTable WHERE id = ? AND price <= ?" + 
			 " AND length <= ? AND width <= ? AND height <= ?";
		
		switch (((Categories)this.categorySelect.getValue()).toString()) {
		case "Furniture":
			sqlGetData.concat(" AND isFurniture = 1");
			break;
		case "Appliances":
			sqlGetData.concat(" AND isAppliance = 1");
			break;
		case "Building_Materials":
			sqlGetData.concat(" AND isBuildingMaterial = 1");
			System.out.println("Materials case hit");
			break;
		case "Tools":
			sqlGetData.concat(" AND isTool = 1");
			break;
		default:
			break;
		}
		
		// corrects for empty/unimportant fields //
		try {
			PreparedStatement stmt = this.newSearchModel.connection.prepareStatement(sqlGetData);
			if(this.idSearch.getText().equals(null) || this.idSearch.getText().equals("")) {
				stmt.setString(1, "id");
			} else {
				stmt.setString(1, this.idSearch.getText());
			}
			
			if(this.priceSearch.getText().equals(null) || this.priceSearch.getText().equals("")) {
				stmt.setString(2, "price");
			} else {
				stmt.setString(2, this.priceSearch.getText());
			}
			
			if(this.lengthSearch.getText().equals(null) || this.lengthSearch.getText().equals("")) {
				stmt.setString(3, "length");
			} else {
				stmt.setString(3, this.lengthSearch.getText());
			}
			
			if(this.widthSearch.getText().equals(null) || this.widthSearch.getText().equals("")) {
				stmt.setString(4, "width");
			} else {
				stmt.setString(4, this.widthSearch.getText());
			}
			
			if(this.heightSearch.getText().equals(null) || this.heightSearch.getText().equals("")) {
				stmt.setString(5, "height");
			} else {
				stmt.setString(5, this.heightSearch.getText());
			}
			
			stmt.execute();
			System.out.println("executed: " + sqlGetData);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void showFirstSix() {
		PreparedStatement takeFields = null;
		ResultSet rs = null;

		String sqlTakeFields = "SELECT * FROM VirtualProductTable WHERE VirtualProductTable MATCH ? ORDER BY rank DESC";
		try {
			takeFields = this.newSearchModel.connection.prepareStatement(sqlTakeFields);
			takeFields.setString(1, nameSearch.getText());
			rs = takeFields.executeQuery();
//			rs.next();
			if(! rs.next()) {
				System.out.println("no table loaded");
				return;
			}
			// first entry
			String id = rs.getString(1);
			String name = rs.getString(2);
			String description = rs.getString(3);
			String price = rs.getString(4);
			String length = rs.getString(5);
			String width = rs.getString(6);
			String height = rs.getString(7);
			String dateAdded = rs.getString(8);
//			Blob image = rs.getBlob(14);
			
			id1.setText(id);
			name1.setText(name);
			description1.setText(description);
			dateAdded1.setText(dateAdded);
			price1.setText(price);
			length1.setText(length);
			width1.setText(width);
			height1.setText(height);
//			image1.setImage((Image) image);
			
			if(! rs.next()) {
				return;
			}
			// second entry
//			rs.next();
			
			id = rs.getString(1);
			name = rs.getString(2);
			description = rs.getString(3);
			price = rs.getString(4);
			length = rs.getString(5);
			width = rs.getString(6);
			height = rs.getString(7);
			dateAdded = rs.getString(8);
//			image = rs.getBlob(14);
			
			id2.setText(id);
			name2.setText(name);
			description2.setText(description);
			dateAdded2.setText(dateAdded);
			price2.setText(price);
			length2.setText(length);
			width2.setText(width);
			height2.setText(height);
//			image2.setImage((Image) image);
			
			if(! rs.next()) {
				return;
			}
			// third entry
//			rs.next();
			
			id = rs.getString(1);
			name = rs.getString(2);
			description = rs.getString(3);
			price = rs.getString(4);
			length = rs.getString(5);
			width = rs.getString(6);
			height = rs.getString(7);
			dateAdded = rs.getString(8);
//			image = rs.getBlob(14);
			
			id3.setText(id);
			name3.setText(name);
			description3.setText(description);
			dateAdded3.setText(dateAdded);
			price3.setText(price);
			length3.setText(length);
			width3.setText(width);
			height3.setText(height);
//			image3.setImage((Image) image);
			
			if(! rs.next()) {
				return;
			}
			// fourth entry
//			rs.next();
			
			id = rs.getString(1);
			name = rs.getString(2);
			description = rs.getString(3);
			price = rs.getString(4);
			length = rs.getString(5);
			width = rs.getString(6);
			height = rs.getString(7);
			dateAdded = rs.getString(8);
//			image = rs.getBlob(14);
			
			id4.setText(id);
			name4.setText(name);
			description4.setText(description);
			dateAdded4.setText(dateAdded);
			price4.setText(price);
			length4.setText(length);
			width4.setText(width);
			height4.setText(height);
//			image4.setImage((Image) image);
			
			if(! rs.next()) {
				return;
			}
			// fifth entry
//			rs.next();
			
			id = rs.getString(1);
			name = rs.getString(2);
			description = rs.getString(3);
			price = rs.getString(4);
			length = rs.getString(5);
			width = rs.getString(6);
			height = rs.getString(7);
			dateAdded = rs.getString(8);
//			image = rs.getBlob(14);
			
			id5.setText(id);
			name5.setText(name);
			description5.setText(description);
			dateAdded5.setText(dateAdded);
			price5.setText(price);
			length5.setText(length);
			width5.setText(width);
			height5.setText(height);
//			image5.setImage((Image) image);
			
			if(! rs.next()) {
				return;
			}
			// sixth entry
//			rs.next();
			
			id = rs.getString(1);
			name = rs.getString(2);
			description = rs.getString(3);
			price = rs.getString(4);
			length = rs.getString(5);
			width = rs.getString(6);
			height = rs.getString(7);
			dateAdded = rs.getString(8);
//			image = rs.getBlob(14);
			
			id6.setText(id);
			name6.setText(name);
			description6.setText(description);
			dateAdded6.setText(dateAdded);
			price6.setText(price);
			length6.setText(length);
			width6.setText(width);
			height6.setText(height);
//			image6.setImage((Image) image);
			takeFields.close();
			rs.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		// gets a sorted list of matching products, next we just need to print them out entry by entry //
		// we'll traverse every row in the result set, and print all needed values before moving to the next row //
		
		/* 
		 *  **********************************************
		 *  TODO: What if there are less than six entries? 
		 *  **********************************************
		 */
		
	}
	
	public void searchProduct (ActionEvent event) throws Exception {
		// makes sure we are not just piling table after table every consecutive search //

		String drop = "TRUNCATE TABLE VirtualProductTable";
		PreparedStatement stmt = this.newSearchModel.connection.prepareStatement(drop);
		stmt.execute();
		stmt.close();
		
		
		// 1. Creates a virtual table and inserts all entries of the category we're looking for, that are also within our other criteria 
		// 2. Sort that table into a list and print out the fields we need row by row 
		getResults();
		showFirstSix();
	}
		
	public void gotoPreviousPage (ActionEvent event) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sqlTakeFields = "SELECT * FROM VirtualProductTable WHERE VirtualProductTable MATCH 'name:?' ORDER BY rank DESC";
		try {
			stmt = this.newSearchModel.connection.prepareStatement(sqlTakeFields);
			rs = stmt.executeQuery();
//			stmt.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		// gets a sorted list of matching products, next we just need to print them out entry by entry //
		// we'll traverse every row in the result set, and print all needed values before moving to the next row //
		
		/* 
		 *  **********************************************
		 *  TODO: What if there are less than six entries? 
		 *  **********************************************
		 */
		
		try {
			
			rs.moveToCurrentRow();
			for (int i = 0; i < rowSize*2; i++) {
				rs.previous();
			}
			// first entry
			String id = rs.getString(2);
			String name = rs.getString(3);
			String description = rs.getString(4);
			String price = rs.getString(5);
			String length = rs.getString(6);
			String width = rs.getString(7);
			String height = rs.getString(8);
			String dateAdded = rs.getString(9);
//			Blob image = rs.getBlob(14);
			
			id1.setText(id);
			name1.setText(name);
			description1.setText(description);
			dateAdded1.setText(dateAdded);
			price1.setText(price);
			length1.setText(length);
			width1.setText(width);
			height1.setText(height);
//			image1.setImage((Image) image);
			
			// second entry
			rs.next();
			
			id = rs.getString(2);
			name = rs.getString(3);
			description = rs.getString(4);
			price = rs.getString(5);
			length = rs.getString(6);
			width = rs.getString(7);
			height = rs.getString(8);
			dateAdded = rs.getString(9);
//			image = rs.getBlob(14);
			
			id2.setText(id);
			name2.setText(name);
			description2.setText(description);
			dateAdded2.setText(dateAdded);
			price2.setText(price);
			length2.setText(length);
			width2.setText(width);
			height2.setText(height);
//			image2.setImage((Image) image);
			
			// third entry
			rs.next();
			
			id = rs.getString(2);
			name = rs.getString(3);
			description = rs.getString(4);
			price = rs.getString(5);
			length = rs.getString(6);
			width = rs.getString(7);
			height = rs.getString(8);
			dateAdded = rs.getString(9);
//			image = rs.getBlob(14);
			
			id3.setText(id);
			name3.setText(name);
			description3.setText(description);
			dateAdded3.setText(dateAdded);
			price3.setText(price);
			length3.setText(length);
			width3.setText(width);
			height3.setText(height);
//			image3.setImage((Image) image);
			
			// fourth entry
			rs.next();
			
			id = rs.getString(2);
			name = rs.getString(3);
			description = rs.getString(4);
			price = rs.getString(5);
			length = rs.getString(6);
			width = rs.getString(7);
			height = rs.getString(8);
			dateAdded = rs.getString(9);
//			image = rs.getBlob(14);
			
			id4.setText(id);
			name4.setText(name);
			description4.setText(description);
			dateAdded4.setText(dateAdded);
			price4.setText(price);
			length4.setText(length);
			width4.setText(width);
			height4.setText(height);
//			image4.setImage((Image) image);
			
			// fifth entry
			rs.next();
			
			id = rs.getString(2);
			name = rs.getString(3);
			description = rs.getString(4);
			price = rs.getString(5);
			length = rs.getString(6);
			width = rs.getString(7);
			height = rs.getString(8);
			dateAdded = rs.getString(9);
//			image = rs.getBlob(14);
			
			id5.setText(id);
			name5.setText(name);
			description5.setText(description);
			dateAdded5.setText(dateAdded);
			price5.setText(price);
			length5.setText(length);
			width5.setText(width);
			height5.setText(height);
//			image5.setImage((Image) image);
			
			// sixth entry
			rs.next();
			
			id = rs.getString(2);
			name = rs.getString(3);
			description = rs.getString(4);
			price = rs.getString(5);
			length = rs.getString(6);
			width = rs.getString(7);
			height = rs.getString(8);
			dateAdded = rs.getString(9);
//			image = rs.getBlob(14);
			
			id6.setText(id);
			name6.setText(name);
			description6.setText(description);
			dateAdded6.setText(dateAdded);
			price6.setText(price);
			length6.setText(length);
			width6.setText(width);
			height6.setText(height);
//			image6.setImage((Image) image);
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		} 
	}
	
	public void gotoNextPage (ActionEvent event) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sqlTakeFields = "SELECT * FROM VirtualProductTable WHERE VirtualProductTable MATCH 'name:?' ORDER BY rank DESC";
		try {
			stmt = this.newSearchModel.connection.prepareStatement(sqlTakeFields);
			rs = stmt.executeQuery();
			stmt.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		// gets a sorted list of matching products, next we just need to print them out entry by entry //
		// we'll traverse every row in the result set, and print all needed values before moving to the next row //
		
		/* 
		 *  **********************************************
		 *  TODO: What if there are less than six entries? 
		 *  **********************************************
		 */
		
		try {
			
			rs.moveToCurrentRow();
			// first entry
			String id = rs.getString(2);
			String name = rs.getString(3);
			String description = rs.getString(4);
			String price = rs.getString(5);
			String length = rs.getString(6);
			String width = rs.getString(7);
			String height = rs.getString(8);
			String dateAdded = rs.getString(9);
//			Blob image = rs.getBlob(14);
			
			id1.setText(id);
			name1.setText(name);
			description1.setText(description);
			dateAdded1.setText(dateAdded);
			price1.setText(price);
			length1.setText(length);
			width1.setText(width);
			height1.setText(height);
//			image1.setImage((Image) image);
			
			// second entry
			rs.next();
			
			id = rs.getString(2);
			name = rs.getString(3);
			description = rs.getString(4);
			price = rs.getString(5);
			length = rs.getString(6);
			width = rs.getString(7);
			height = rs.getString(8);
			dateAdded = rs.getString(9);
//			image = rs.getBlob(14);
			
			id2.setText(id);
			name2.setText(name);
			description2.setText(description);
			dateAdded2.setText(dateAdded);
			price2.setText(price);
			length2.setText(length);
			width2.setText(width);
			height2.setText(height);
//			image2.setImage((Image) image);
			
			// third entry
			rs.next();
			
			id = rs.getString(2);
			name = rs.getString(3);
			description = rs.getString(4);
			price = rs.getString(5);
			length = rs.getString(6);
			width = rs.getString(7);
			height = rs.getString(8);
			dateAdded = rs.getString(9);
//			image = rs.getBlob(14);
			
			id3.setText(id);
			name3.setText(name);
			description3.setText(description);
			dateAdded3.setText(dateAdded);
			price3.setText(price);
			length3.setText(length);
			width3.setText(width);
			height3.setText(height);
//			image3.setImage((Image) image);
			
			// fourth entry
			rs.next();
			
			id = rs.getString(2);
			name = rs.getString(3);
			description = rs.getString(4);
			price = rs.getString(5);
			length = rs.getString(6);
			width = rs.getString(7);
			height = rs.getString(8);
			dateAdded = rs.getString(9);
//			image = rs.getBlob(14);
			
			id4.setText(id);
			name4.setText(name);
			description4.setText(description);
			dateAdded4.setText(dateAdded);
			price4.setText(price);
			length4.setText(length);
			width4.setText(width);
			height4.setText(height);
//			image4.setImage((Image) image);
			
			// fifth entry
			rs.next();
			
			id = rs.getString(2);
			name = rs.getString(3);
			description = rs.getString(4);
			price = rs.getString(5);
			length = rs.getString(6);
			width = rs.getString(7);
			height = rs.getString(8);
			dateAdded = rs.getString(9);
//			image = rs.getBlob(14);
			
			id5.setText(id);
			name5.setText(name);
			description5.setText(description);
			dateAdded5.setText(dateAdded);
			price5.setText(price);
			length5.setText(length);
			width5.setText(width);
			height5.setText(height);
//			image5.setImage((Image) image);
			
			// sixth entry
			rs.next();
			
			id = rs.getString(2);
			name = rs.getString(3);
			description = rs.getString(4);
			price = rs.getString(5);
			length = rs.getString(6);
			width = rs.getString(7);
			height = rs.getString(8);
			dateAdded = rs.getString(9);
//			image = rs.getBlob(14);
			
			id6.setText(id);
			name6.setText(name);
			description6.setText(description);
			dateAdded6.setText(dateAdded);
			price6.setText(price);
			length6.setText(length);
			width6.setText(width);
			height6.setText(height);
//			image6.setImage((Image) image);
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		} 
	}

	public void logOut (ActionEvent event) throws Exception {
		try {
			Runtime.getRuntime().exec("java -jar \\H4HProject\\H4H_Login.jar");
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public void viewOrders (ActionEvent event) throws Exception {
		try {
			Runtime.getRuntime().exec("java -jar \\H4HProject\\ViewOrders.jar");
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public void editAccount (ActionEvent event) throws Exception {
		try {
			Runtime.getRuntime().exec("java -jar \\H4HProject\\EditAccount.jar");
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
}

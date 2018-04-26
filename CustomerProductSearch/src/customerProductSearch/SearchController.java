package customerProductSearch;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
	
	
	
	
	SearchModel newSearchModel = new SearchModel();

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		if(this.newSearchModel.isConnected()) {
			this.dbStatus.setText("Connected...");
		} else {
			this.dbStatus.setText("Connection failed...");
		}
	}
	
	private void getFields() {
		
		String sqlTakeFields = "SELECT * FROM H4HProductTable WHERE name = ? AND id = ? AND price => ?"
				+ " AND length => ? AND width => ? AND height => ?";
		try {
			PreparedStatement stmt = this.newSearchModel.connection.prepareStatement(sqlTakeFields);
			stmt.setString(1, this.nameSearch.getText());
			stmt.setString(2, this.idSearch.getText());
			stmt.setString(3, this.priceSearch.getText());
			stmt.setString(4, this.lengthSearch.getText());
			stmt.setString(5, this.widthSearch.getText());
			stmt.setString(6, this.heightSearch.getText());
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void searchProduct (ActionEvent event) throws Exception {
		
	}
	
	public void gotoPreviousPage (ActionEvent event) throws Exception {
		
	}
	
	public void gotoNextPage (ActionEvent event) throws Exception {
		
	}

	public void logOut (ActionEvent event) throws Exception {
	
	}

	public void viewOrders (ActionEvent event) throws Exception {
	
	}

	public void editAccount (ActionEvent event) throws Exception {
	
	}
	
//		String sqlSearchName = "name = ?";
//		String sqlSearchID = "id = ?";
//		String sqlSearchPrice = "price => ?";
//		String sqlSearchLength = "length => ?";
//		String sqlSearchWidth = "width => ?";
//		String sqlSearchHeight = "height => ?";
//		
//		if (nameSearch.getText() != null && !nameSearch.getText().isEmpty()) {
//			sqlTakeFields.concat(" AND ");
//			
//			sqlTakeFields.concat(sqlSearchName);
//		}
//		
//		if (idSearch.getText() != null && !idSearch.getText().isEmpty()) {
//			sqlTakeFields.concat(" AND ");
//			
//			sqlTakeFields.concat(sqlSearchID);
//		}
//		
//		if (priceSearch.getText() != null && !priceSearch.getText().isEmpty()) {
//			sqlTakeFields.concat(" AND ");
//			
//			sqlTakeFields.concat(sqlSearchPrice);
//		}
//		
//		if (lengthSearch.getText() != null && !lengthSearch.getText().isEmpty()) {
//			sqlTakeFields.concat(" AND ");
//			
//			sqlTakeFields.concat(sqlSearchLength);
//		}
//		
//		if (widthSearch.getText() != null && !widthSearch.getText().isEmpty()) {
//			sqlTakeFields.concat(" AND ");
//			
//			sqlTakeFields.concat(sqlSearchWidth);
//		}
//		
//		if (heightSearch.getText() != null && !heightSearch.getText().isEmpty()) {
//			sqlTakeFields.concat(" AND ");
//			
//			sqlTakeFields.concat(sqlSearchHeight);
//		}
//		
//		
//		return sqlTakeFields;
//	}
}

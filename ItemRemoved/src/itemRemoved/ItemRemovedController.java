package itemRemoved;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ItemRemovedController {
	
	@FXML
	private Button searchButton;
	@FXML
	private Button landingButton;
	
	ItemRemovedModel newModel = new ItemRemovedModel();
	
	public void gotoSearch(ActionEvent event) {
		try {
			Runtime.getRuntime().exec("java -jar \\H4HProject\\Runnables\\CustomerProductSearch.jar");
			Stage stage = (Stage)this.searchButton.getScene().getWindow();
			stage.close();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void gotoLanding(ActionEvent event) {
		String userOrEmp = "SELECT division FROM H4HUserTable WHERE activeOnMachine = 'True'";
		try {
			PreparedStatement stmt = this.newModel.connection.prepareStatement(userOrEmp);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			String division = rs.getString("division");
			
			if(division.equals("Employee")) { // if not employee, -> customer //
				Runtime.getRuntime().exec("java -jar \\H4HProject\\Runnables\\EmployeeLanding.jar");
				Stage stage = (Stage)this.landingButton.getScene().getWindow();
				stage.close();
			}
			else {	
				Runtime.getRuntime().exec("java -jar \\H4HProject\\Runnables\\CustomerLanding.jar");
				Stage stage = (Stage)this.landingButton.getScene().getWindow();
				stage.close();
			}
			
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		
	}
}

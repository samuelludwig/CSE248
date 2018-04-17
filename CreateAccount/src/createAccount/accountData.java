package createAccount;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class accountData {
	
	private final StringProperty firstName;
	private final StringProperty lastName;
	private final StringProperty email;
	private final StringProperty phoneNumber;
	private final StringProperty country;
	private final StringProperty city;
	private final StringProperty address;
	private final StringProperty zipCode;
	private final StringProperty state;
	private final StringProperty username;
	private final StringProperty password;
	private final StringProperty passwordCheck;
	
	public accountData(String firstName, String lastName, String email, String phoneNumber, String country, 
			String city, String address, String zipCode, String state, String username, String password, String passwordCheck) {
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		this.email = new SimpleStringProperty(email);
		this.phoneNumber = new SimpleStringProperty(phoneNumber);
		this.country = new SimpleStringProperty(country);
		this.city = new SimpleStringProperty(city);
		this.address = new SimpleStringProperty(address);
		this.zipCode = new SimpleStringProperty(zipCode);
		this.state = new SimpleStringProperty(state);
		this.username = new SimpleStringProperty(username);
		this.password = new SimpleStringProperty(password);
		this.passwordCheck = new SimpleStringProperty(passwordCheck);
	}

	public StringProperty getFirstName() {
		return firstName;
	}

	public StringProperty getLastName() {
		return lastName;
	}

	public StringProperty getEmail() {
		return email;
	}

	public StringProperty getPhoneNumber() {
		return phoneNumber;
	}

	public StringProperty getCountry() {
		return country;
	}

	public StringProperty getCity() {
		return city;
	}

	public StringProperty getAddress() {
		return address;
	}

	public StringProperty getZipCode() {
		return zipCode;
	}

	public StringProperty getState() {
		return state;
	}

	public StringProperty getUsername() {
		return username;
	}

	public StringProperty getPassword() {
		return password;
	}

	public StringProperty getPasswordCheck() {
		return passwordCheck;
	}
	
	public StringProperty firstNameProperty() {
		return this.firstName;
	}
	
	public StringProperty lastNameProperty() {
		return this.lastName;
	}
	
	public StringProperty emailProperty() {
		return this.email;
	}
	
	public StringProperty phoneNumberProperty() {
		return this.phoneNumber;
	}
	
	public StringProperty countryProperty() {
		return this.country;
	}
	
	public StringProperty cityProperty() {
		return this.city;
	}
	
	public StringProperty stateProperty() {
		return this.state;
	}
	
	public StringProperty addressProperty() {
		return this.address;
	}
	
	public StringProperty zipCodeProperty() {
		return this.zipCode;
	}
	
	public StringProperty usernameProperty() {
		return this.username;
	}
	
	public StringProperty passwordProperty() {
		return this.password;
	}
	
	public StringProperty passwordCheckProperty() {
		return this.passwordCheck;
	}

}

package login;

public enum option {
	Employee, Customer;
	
	private option() {}
	
	public String value() {
		return name();
	}
	
	public static option fromValue(String v) {
		return valueOf(v);
	}
	
	
}

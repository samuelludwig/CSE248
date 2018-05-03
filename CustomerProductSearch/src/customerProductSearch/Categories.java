package customerProductSearch;

public enum Categories {
	Furniture, Appliances, Building_Materials, Tools;
	
	private Categories() {}
	
	public String value() {
		return name();
	}
	
	public static Categories fromValue(String v) {
		return valueOf(v);
	}
	
	
}

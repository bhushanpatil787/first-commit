package resources;
//Enum class has collection of constants and methods

public enum APIResources {
	AddPlaceAPI("/maps/api/place/add/json"),
	getPlaceAPI("/maps/api/place/get/json"),
	deletePlaceAPI("/maps/api/place/delete/json");
	
	String resource;

	APIResources(String resource) {
		// constructor stub and return type is string
		this.resource = resource;
	}
	
	public String getResource() {
		return resource;
	}

}

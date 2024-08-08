package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hook {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException{
		StepDefinitions m = new StepDefinitions();
		
		if (StepDefinitions.place_id ==null) { //Here place_id is static variable, so it is called by it's class name instead of object name i.e 'm'
		
		m.add_place_payload_with("SydneyWarehouse", "Kent Street", "English");
		m.user_calls_using_http_request("AddPlaceAPI", "POST");
		m.verify_place_id_created_maps_to_using("SydneyWarehouse", "getPlaceAPI");
		
		}
	}

}

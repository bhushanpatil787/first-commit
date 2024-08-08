package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import java.io.IOException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinitions extends Utils{
	
	ResponseSpecification resspec;
	RequestSpecification res;
	Response response;
	JsonPath js;
	static String place_id;
	
	TestDataBuild data = new TestDataBuild();
	
	@Given("Add place payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String address, String language) throws IOException {

		resspec= new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		res= given().spec(requestSpecification()).body(data.addPlacePayload(name, address,language));
	}
	
	@When("User calls {string} using {string} http request")
	public void user_calls_using_http_request(String resource, String method) {
		APIResources resourceAPI= APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());
		
		if (method.equalsIgnoreCase("POST"))
		response= res.when().post(resourceAPI.getResource());
		
		else if (method.equalsIgnoreCase("GET"))
		response = res.when().get(resourceAPI.getResource());
	}
	

	@Then("Add API is success with status code {int}")
	public void add_api_is_success_with_status_code(Integer int1) {
	    assertEquals(response.getStatusCode(), 200);
	    
	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String string2) {
	    
	    assertEquals(getJsonpath(response, keyValue), string2);    
	}

	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		place_id = getJsonpath(response, "place_id");
		res= given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_using_http_request(resource,"GET");
		String actualName = getJsonpath(response, "name");
		assertEquals(expectedName, actualName);
	}
	
	@Given("Delete place payload")
	public void delete_place_payload() throws IOException {
	    res = given().spec(requestSpecification()).body(data.deleteplace_paylod(place_id));
	}

}

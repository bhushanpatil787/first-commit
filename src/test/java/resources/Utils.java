package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	public static RequestSpecification req; //declaring it as a public static will use same variable for all the TCs from same run instance. It will not set to it run for each test data
	public RequestSpecification requestSpecification() throws IOException {
		
		if(req==null) { //adding this null check will make sure that log file is create once at the 1st Test execution and it will not be overwritten during next test execution
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		 req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseURL")).addQueryParam("key", "qaclick123")
				 .addFilter(RequestLoggingFilter.logRequestTo(log))
				 .addFilter(ResponseLoggingFilter.logResponseTo(log))
					.setContentType(ContentType.JSON).build();
		 return req;
	}
		else
		{
			return req;
		}
	}

	public static String getGlobalValue(String key) throws IOException
	{
		Properties prop= new Properties();
		FileInputStream fis = new FileInputStream("/Users/appy/eclipse-workspace/APIFramework/src/test/java/resources/global.properties");
		prop.load(fis);
		return prop.getProperty(key);
		
	}
	
	public String getJsonpath(Response response, String key) {
		String resp = response.asString();
		
		JsonPath js = new JsonPath(resp);
		return js.get(key).toString();
		
	}
}

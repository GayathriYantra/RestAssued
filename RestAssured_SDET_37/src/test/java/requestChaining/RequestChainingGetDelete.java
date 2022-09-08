package requestChaining;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.port;

import java.util.concurrent.TimeUnit;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import io.restassured.response.Response;

public class RequestChainingGetDelete 
{
@Test
public void requestChainingGet$Delete()
{
	baseURI="http://localhost";
	port=8084;
	
	Response res = when().get("projects/TY_PROJ_1445");
			
	// capture the project ID
	String proID = res.jsonPath().get("projectId");
	System.out.println(proID);
	res.then().log().all();
	
//Create a get reuest and pass proID as path parameter
	
	given()
	.pathParam("pid", proID)
	
	.when()
	.delete("/projects/{pid}")
	
	.then()
	.assertThat().statusCode(204).time(Matchers.lessThan(2L), TimeUnit.SECONDS)
	.log().all();
		
}	
}


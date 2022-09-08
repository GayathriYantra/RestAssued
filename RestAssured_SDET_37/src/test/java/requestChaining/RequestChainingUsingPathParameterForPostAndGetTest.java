package requestChaining;

import org.testng.annotations.Test;

import GenericUtility.JavaUtility;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojaClass.Project;

import static io.restassured.RestAssured.*;

public class RequestChainingUsingPathParameterForPostAndGetTest
{
@Test
public void requestChainingUsingPathParam()
{
	JavaUtility jLib = new JavaUtility();
	Project proj = new Project("Rithu", "Clinton24"+jLib.getRandomNum(), "Ongoing", 15);

	baseURI="http://localhost";
	port=8084;
	
	Response resp = given()
	.body(proj)
	.contentType(ContentType.JSON)
	.when()
	.post("/addProject");
	
	
			
	// capture the project ID
	String proID = resp.jsonPath().get("projectId");
	System.out.println(proID);
	resp.then().log().all();
	

	given()
	.pathParam("pid", proID)
	
	.when()
	.get("/projects/{pid}")
	
	.then()
	.assertThat().statusCode(200).log().all();
		
}
}

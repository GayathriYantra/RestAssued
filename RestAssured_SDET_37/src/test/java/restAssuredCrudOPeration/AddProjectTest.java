package restAssuredCrudOPeration;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.port;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import GenericUtility.BaseAPIClass;
import GenericUtility.EndPointsLibrary;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class AddProjectTest extends BaseAPIClass
{
	@Test
	public void addProject()
	{
		File file =new File("./src/test/resources/JsonFile.json");
		
		 Response reponse = given()
		.body(file)
		.contentType(ContentType.JSON)

		.when()
		.post(EndPointsLibrary.createProject);

		 reponse.then()
		.assertThat().contentType(ContentType.JSON).statusCode(201)
		.time(Matchers.lessThan(2L), TimeUnit.SECONDS)
		.log().all();	
		 
		
	}

}

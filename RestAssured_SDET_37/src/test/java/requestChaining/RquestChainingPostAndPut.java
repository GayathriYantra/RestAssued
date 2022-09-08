package requestChaining;

import static io.restassured.RestAssured.*;

import java.util.concurrent.TimeUnit;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import GenericUtility.JavaUtility;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojaClass.Project;

public class RquestChainingPostAndPut
{
	@Test
	public void requestChainPostAndPatch()
	{
		//Post the request
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

		//Create a put request and pass proID as path parameter

		Project project = new Project("RithuAkka", "Clinton24"+jLib.getRandomNum(), "Ongoing", 15);

		baseURI="http://localhost";
		port=8084;

		given()
		.pathParam("pid", proID)
		.body(project)
		.contentType(ContentType.JSON)
		.when()
		.put("/projects/{pid}")

		.then()
		.assertThat().statusCode(200)
		.time(Matchers.lessThan(2L), TimeUnit.SECONDS)
		.log().all();


	}
}

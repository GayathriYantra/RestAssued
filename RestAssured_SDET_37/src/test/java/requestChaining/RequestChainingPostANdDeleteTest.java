package requestChaining;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.port;
import static io.restassured.RestAssured.when;

import java.util.concurrent.TimeUnit;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import GenericUtility.JavaUtility;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import pojaClass.Project;

public class RequestChainingPostANdDeleteTest
{
	@Test
	public void requestChainPostAndPut()
	{
		JavaUtility jLib = new JavaUtility();
		Project proj = new Project("RithuGuru", "Clinton23"+jLib.getRandomNum(), "Ongoing", 15);
		baseURI="http://localhost";
		port=8084;

		Response res = given()
				.body(proj).contentType(ContentType.JSON)

				.when()
				.post("/addProject");

		// capture the project ID
		String proID = res.jsonPath().get("projectId");
		System.out.println(proID);
		res.then().log().all();

		//Create a get request and pass proID as path parameter

		given()
		.pathParam("pid", proID)

		.when()
		.delete("/projects/{pid}")

		.then()
		.assertThat().statusCode(200)
		.time(Matchers.lessThan(2L), TimeUnit.SECONDS)
		.log().all();

	}
}
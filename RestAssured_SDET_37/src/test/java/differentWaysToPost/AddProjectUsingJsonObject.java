package differentWaysToPost;

import static io.restassured.RestAssured.given;

import java.util.concurrent.TimeUnit;

import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class AddProjectUsingJsonObject 
{
	@Test
	public void createProjectUsingJsonObject()
	{

		JSONObject jobj=new JSONObject();
		jobj.put("createdBy", "KalaAmmaa");
		jobj.put("projectName", "LambhoghiniProject");
		jobj.put("status", "Ongoing");
		jobj.put("teamSize", 20);

		given()
		.body(jobj)
		.contentType(ContentType.JSON)
		.when()
		.post("http://localhost:8084/addProject")
		.then()
		.assertThat().contentType(ContentType.JSON).statusCode(201).time(Matchers.lessThan(2L), TimeUnit.SECONDS)
		.log().all();
	}
}

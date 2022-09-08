package crudOprationwithBdd;

import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

import java.util.concurrent.TimeUnit;

public class UpdateTheEntireProjectTest 
{
	@Test
	public void updateEntireProject()
	{
		JSONObject jobj=new JSONObject();
		jobj.put("createdBy", "KalaDayalan");
		jobj.put("projectName", "MeghnasTen");
		jobj.put("status", "Completed_Succssfully");
		jobj.put("teamSize", 20);

		given()
		.body(jobj)
		.contentType(ContentType.JSON)
		.when()
		.put("http://localhost:8084/projects/TY_PROJ_808")
		.then()
		.assertThat().contentType(ContentType.JSON).statusCode(200)
		.time(Matchers.lessThan(2L), TimeUnit.SECONDS)
		.log().all();

	}
}

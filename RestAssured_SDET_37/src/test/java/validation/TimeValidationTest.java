package validation;


import java.util.concurrent.TimeUnit;

import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;


public class TimeValidationTest 
{
	@Test
	public void timeValidationt()
	{
		JSONObject jobj=new JSONObject();
		jobj.put("createdBy", "KalaAmmaa");
		jobj.put("projectName", "LambhoghiniProject1");
		jobj.put("status", "Ongoing");
		jobj.put("teamSize", 20);
		
		given()
		.body(jobj)
		.contentType(ContentType.JSON)
		.when()
		.post("http://localhost:8084/addProject")
		.then()
		.assertThat().contentType(ContentType.JSON).statusCode(201).time(Matchers.lessThan(2000L), TimeUnit.MILLISECONDS)
		.log().all();
	}
}

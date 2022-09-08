package crudOprationwithBdd;

import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

import java.util.concurrent.TimeUnit;

public class AddProjectTest 
{
@Test
public void createProject()
{
	JSONObject jobj=new JSONObject();
	jobj.put("createdBy", "Dayalan");
	jobj.put("projectName", "MeghnasBSA1");
	jobj.put("status", "Completed");
	jobj.put("teamSize", 23);
	
	given()
	.body(jobj)
	.contentType(ContentType.JSON)
	.when()
	.post("http://localhost:8084/addProject")
	.then()
	.assertThat().contentType(ContentType.JSON).statusCode(201)
	.time(Matchers.lessThan(2L), TimeUnit.SECONDS)
	.log().all();
}
}

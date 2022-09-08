package differentWaysToPost;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.port;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class AddProjectUsingJsonFile
{
@Test
public void addProjectUsingJsonFile()
{
	
	File file =new File("./src/main/resources/JsonFile.json");
	
	baseURI="http://localhost";
	port=8084;
		
	given()
	.body(file)
	.contentType(ContentType.JSON)
	
	.when()
	.post("/addProject")
	
	.then()
	.assertThat().contentType(ContentType.JSON).statusCode(201)
	.time(Matchers.lessThan(2L), TimeUnit.SECONDS)
	.log().all();
	
}
}

package differentWaysToPost;

import java.util.concurrent.TimeUnit;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import pojaClass.Project;

public class AddProjectUsingPojoClassTest 
{
@Test
public void addProjectUsingPojaClass()
{
	Project project = new Project("Thanji1","TempoTravel","ongoing",23);
	
	given()
	.body(project).contentType(ContentType.JSON)
	.when()
	.post("http://localhost:8084/addProject")
	.then()
	.assertThat().contentType(ContentType.JSON).statusCode(201).time(Matchers.lessThan(2L), TimeUnit.SECONDS)
	.log().all();
}
}

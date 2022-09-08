package crudOprationwithBdd;


import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;

import java.util.concurrent.TimeUnit;

public class DeleteProjectTest 
{
@Test
public void deleteProject()
{
	when()  //actions to be performed here
	.delete("http://localhost:8084/projects/TY_PROJ_806")
	
	.then()  //validation 
	.assertThat().contentType(ContentType.JSON).statusCode(204)
	.time(Matchers.lessThan(2L), TimeUnit.SECONDS)
	.log().all();
}
}

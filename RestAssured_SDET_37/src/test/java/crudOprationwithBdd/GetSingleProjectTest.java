package crudOprationwithBdd;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;

import java.util.concurrent.TimeUnit;
public class GetSingleProjectTest
{
	@Test
	public void getSingleProject()
	{
		when()
		.get("http://localhost:8084/projects/TY_PROJ_1802")
		.then()
		.assertThat().contentType(ContentType.JSON).statusCode(200)
		.time(Matchers.lessThan(2L), TimeUnit.SECONDS)
		.log().all();
	}
}

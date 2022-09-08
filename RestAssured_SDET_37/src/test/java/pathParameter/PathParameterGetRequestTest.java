package pathParameter;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

import java.util.concurrent.TimeUnit;

public class PathParameterGetRequestTest
{
@Test
public void pathParameterGetRequest()
{
	baseURI="http://localhost";
	port=8084;
	
	given()
	.pathParam("pID", "TY_PROJ_2010")
	.when()
	.get("/projects/{pID}")
	.then()
	.contentType(ContentType.JSON).statusCode(200).time(Matchers.lessThan(2000L), TimeUnit.MILLISECONDS)
	.log().all();
	
}
}

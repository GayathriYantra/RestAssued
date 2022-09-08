package restAssuredCrudOPeration;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import java.util.concurrent.TimeUnit;

import GenericUtility.BaseAPIClass;
import GenericUtility.EndPointsLibrary;
import io.restassured.http.ContentType;

public class GetAllProjectTest extends BaseAPIClass
{
@Test
public void getSingleProject()
{
	
	when()
	.get(EndPointsLibrary.getAllProject)
	
	.then()
	.assertThat().statusCode(200).contentType(ContentType.JSON)
	.time(Matchers.lessThan(2L), TimeUnit.SECONDS)
	.log().all();
}
}

package restAssuredCrudOPeration;

import static io.restassured.RestAssured.*;

import java.util.concurrent.TimeUnit;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import GenericUtility.BaseAPIClass;
import GenericUtility.EndPointsLibrary;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojaClass.Project;

public class GetSingleProjectTest extends BaseAPIClass 
{
	@Test
	public void getSingleProject()
	{
		Project project = new Project("raayan","Rocks1"+jLib.getRandomNum(),"Completed",15);
		Response response = given()
				.body(project).contentType(ContentType.JSON)
				.pathParam("pID", "TY_PROJ_2002")
				.when()
				.get(EndPointsLibrary.getSingleProject+"{pID}");
		
		response.then()
		.assertThat().statusCode(200).contentType(ContentType.JSON).log().all();
	

		

	}
}

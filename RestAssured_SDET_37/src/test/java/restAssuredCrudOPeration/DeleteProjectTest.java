package restAssuredCrudOPeration;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import GenericUtility.BaseAPIClass;
import GenericUtility.EndPointsLibrary;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojaClass.Project;

public class DeleteProjectTest extends BaseAPIClass 
{
@Test
public void deleteProject()
{
	
			given()
			.pathParam("pID", "TY_PROJ_2010")
			
			.when()
			.delete(EndPointsLibrary.deleteProject+"{pID}")
	
			.then()
			.assertThat().statusCode(204).contentType(ContentType.JSON).log().all();


}
}

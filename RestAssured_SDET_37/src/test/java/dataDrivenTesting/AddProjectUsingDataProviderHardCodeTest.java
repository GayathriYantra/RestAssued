package dataDrivenTesting;

import static io.restassured.RestAssured.given;

import java.util.concurrent.TimeUnit;

import org.hamcrest.Matchers;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import pojaClass.Project;

public class AddProjectUsingDataProviderHardCodeTest
{
	@Test(dataProvider="postRequest")
	public void addProjectTest(String created,String projectName, String status, int teamSize)
	{
		Project project = new Project(created,projectName, status, teamSize);

		given()
		.body(project).contentType(ContentType.JSON)

		.when()
		.post("http://localhost:8084/addProject")

		.then()
		.assertThat().contentType(ContentType.JSON).statusCode(201).time(Matchers.lessThan(2L), TimeUnit.SECONDS)
		.log().all();
	}
		//dataProvider
		@DataProvider
		public Object[][] postRequest()
		{
			Object[][] objArr=new Object[4][4];

			objArr[0][0]="Nishi1";
			objArr[0][1]="micromax11";
			objArr[0][2]="Ongoing";
		    objArr[0][3]=20;
		    
		    objArr[1][0]="Roja2";
			objArr[1][1]="Nykaa11";
			objArr[1][2]="completed";
		    objArr[1][3]=20;
		    
		    objArr[2][0]="Pooja1";
			objArr[2][1]="Frrari11";
			objArr[2][2]="Ongoing";
		    objArr[2][3]=20;
		    
		    objArr[3][0]="Yazhi1";
			objArr[3][1]="Lambhoghini11";
			objArr[3][2]="Completed";
		    objArr[3][3]=30;
		    
		    return objArr;

		}
	}


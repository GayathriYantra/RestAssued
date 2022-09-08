package dataDrivenTesting;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.hamcrest.Matchers;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import GenericUtility.ExcelUtility;
import io.restassured.http.ContentType;
import pojaClass.Project;

public class AddProjectUsingDataProviderSoftcodeTest
{
	@Test(dataProvider="postRequest")
	public void addProjectTest(String createdBy,String projectName, String status, int teamSize)
	{
		Project project = new Project(createdBy,projectName, status, teamSize);

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
		public Object[][] postRequest() throws EncryptedDocumentException, FileNotFoundException, IOException
		{
			Object[][] objArr=new Object[4][4];
			ExcelUtility eLib = new ExcelUtility();
			objArr[0][0]=eLib.getDataFromExcel("Sheet1", 1, 0);
			objArr[0][1]=eLib.getDataFromExcel("Sheet1", 1, 1);
			objArr[0][2]=eLib.getDataFromExcel("Sheet1", 1, 2);
		    objArr[0][3]=eLib.getDataFromExcel("Sheet1", 1, 3);
		    
		    objArr[1][0]=eLib.getDataFromExcel("Sheet1", 2, 0);
			objArr[1][1]=eLib.getDataFromExcel("Sheet1", 2, 1);
			objArr[1][2]=eLib.getDataFromExcel("Sheet1", 2, 2);
		    objArr[1][3]=eLib.getDataFromExcel("Sheet1", 2, 3);
		    
		    objArr[2][0]=eLib.getDataFromExcel("Sheet1", 3, 0);
			objArr[2][1]=eLib.getDataFromExcel("Sheet1", 3, 1);
			objArr[2][2]=eLib.getDataFromExcel("Sheet1", 3, 2);
		    objArr[2][3]=eLib.getDataFromExcel("Sheet1", 3, 3);
		    
		    objArr[3][0]=eLib.getDataFromExcel("Sheet1", 4, 0);
			objArr[3][1]=eLib.getDataFromExcel("Sheet1", 4, 1);
			objArr[3][2]=eLib.getDataFromExcel("Sheet1", 4, 2);
		    objArr[3][3]=eLib.getDataFromExcel("Sheet1", 4, 3);
		    
		    return objArr;

		}
}

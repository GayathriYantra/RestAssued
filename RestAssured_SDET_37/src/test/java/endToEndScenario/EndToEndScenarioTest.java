package endToEndScenario;

import org.hamcrest.Matchers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import GenericUtility.BaseAPIClass;
import GenericUtility.BaseClass;
import GenericUtility.EndPointsLibrary;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojaClass.Project;
import static io.restassured.RestAssured.*;

import java.util.List;

public class EndToEndScenarioTest extends BaseAPIClass
{
	@Test
	public void end2EndTesting() throws Throwable {
		// creating body
		Project proj = new Project("Gayathri", "End2EndScnario" + jLib.getRandomNum(), "Created", 30);

		// add project in api
		Response response = given().body(proj).contentType(ContentType.JSON).when()
				.post(EndPointsLibrary.createProject);

		// getting projectId from response
		String projectIdFromResponse = raLib.getJsonData(response, "projectId");

		// getting response and verifying
		response.then().assertThat().statusCode(201).contentType(ContentType.JSON).time(Matchers.lessThan(2000L)).log()
				.all();
		Reporter.log("Project is CREATED in API");

		// Validating in GUI

		// launching the browser
		// entering url
		// entering valid details in field
		BaseClass base = new BaseClass();
		base.launchTheBrowser();

		// clicking on projects link
		base.driver.findElement(By.xpath("//a[text()='Projects']")).click();

		// selcting projectId for verifying and storing it in one variable
		List<WebElement> projectIdElement = base.driver.findElements(By.xpath("//td[1]"));

		// verification
		boolean temp = false;
		int count = 0;
		for (WebElement projectId : projectIdElement) {
			if (projectId.getText().equals(projectIdFromResponse)) {
				temp = true;
			}
			count++;
		}

		if (temp == true) {
			Assert.assertTrue(true);
			Reporter.log("Project is present in GUI", true);
		} else {
			Reporter.log("Project is not presented in GUI", true);
			Assert.assertTrue(false);
		}

		Reporter.log("Total no of projects checked = " + count, true);

		base.closeTheBrowser();

		// checking in database
		Reporter.log("Database verification starts", true);
		dLib.executeQuery("select * from project", 1, projectIdFromResponse);

		// deleting data in API
		when().delete(EndPointsLibrary.deleteProject + projectIdFromResponse).then().assertThat().statusCode(204)
				.time(Matchers.lessThan(2000L)).log().all();
		Reporter.log("Data deleted from API", true);

		// check data is deleted in gui or not
		// opening the browser
		// entering the url
		// giving valid details and login
		base.launchTheBrowser();

		// clicking on projects link
		base.driver.findElement(By.xpath("//a[text()='Projects']")).click();
		
		// selcting projectId for verifying and storing it in one variable
		List<WebElement> projectIdElement1 = base.driver.findElements(By.xpath("//td[1]"));

		// verification
		boolean flag = false;
		int countProject = 0;
		for (WebElement projectId1 : projectIdElement1) {
			if (projectId1.getText().equals(projectIdFromResponse)) {
				temp = true;
			}
			countProject++;
		}

		if (flag == false) {
			Assert.assertTrue(true);
			Reporter.log("Project is deleted, Not Present in GUI", true);
		} else {
			Reporter.log("Project is not deleted, It is Present in GUI", true);
			Assert.assertTrue(false);
		}

		Reporter.log("Total no of projects checked = " + count, true);

		base.closeTheBrowser();
		
		//checking data is deleted in database or not
		Reporter.log("Database verification starts",true);
		dLib.executeQuery("select * from project", 1, projectIdFromResponse);
		
		
	}
}

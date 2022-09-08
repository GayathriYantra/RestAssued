package requestChaining;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.port;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.hamcrest.Matchers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.mysql.cj.jdbc.Driver;

import GenericUtility.JavaUtility;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojaClass.Project;

public class AllInOne {
	
	@Test
	
	public void allInOne() throws Throwable
	{
		baseURI="http://localhost";
		port=8084;
		
		JavaUtility jlib = new JavaUtility();
		Project proj = new Project("Gayathri", "GD Project"+jlib.getRandomNum(), "Created", 40);
		
		//Post
		Response resp = given()
		.body(proj)
		.contentType(ContentType.JSON)
		.when()
		.post("/addProject");
		
		//capturing project id
		String projId = resp.jsonPath().get("projectId");
		System.out.println("Project CREATED sucessfully");
		System.out.println(projId);
		resp.then().assertThat().statusCode(201).contentType(ContentType.JSON).time(Matchers.lessThan(2000L)).log().all();
		
		//Get
		given()
		.pathParam("pId", projId)
		.when()
		.get("/projects/{pId}")
		.then()
		.assertThat().statusCode(200).contentType(ContentType.JSON).time(Matchers.lessThan(2000L)).log().all();
		System.out.println("Project FETCHED sucessfully");
		
		//Put
		Project projPut = new Project("ArunK", "AK "+jlib.getRandomNum(), "Created", 39);
		given()
		.pathParam("pId", projId)
		.body(projPut)
		.contentType(ContentType.JSON)
		.when()
		.put("/projects/{pId}")
		.then()
		.assertThat().statusCode(200).contentType(ContentType.JSON).time(Matchers.lessThan(2000L)).log().all();
		System.out.println("<======Project UPDATED sucessfully======>");
		
		//Delete
		given()
		.pathParam("pId", projId)
		.when()
		.delete("projects/{pId}")
		.then()
		.assertThat().statusCode(204).time(Matchers.lessThan(2000L)).log().all();
		System.out.println("<======Project DELETED sucessfully======>");
		
		//Check in dataBase
		System.out.println("<=========Databse verification starts========>");
		Driver driverRef = new Driver();
		DriverManager.registerDriver(driverRef);
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects","root","root");
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery("select * from project");
		int count=0;
		while(result.next())
		{
			if(result.getString(1).contains(projId))
			{
				count++;
				break;
			}
		}
		if(count==0)
		{
			System.out.println("Project is DELETED from Database");
		}else
		{
			System.out.println("Project is not deleted from database");
		}
		
		connection.close();
		System.out.println("Database connection closed");
		
		//GUI verification
		System.out.println("GUI verification starts");
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://localhost:8084/");
		//entering details and loggingin
		driver.findElement(By.id("usernmae")).sendKeys("rmgyantra");
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[text()='Sign in']")).click();
		
		//waiting
		Thread.sleep(5000);
		
		driver.findElement(By.xpath("//a[text()='Projects']")).click();
		
		List<WebElement> projectIdElement = driver.findElements(By.xpath("//td[1]"));
		
		
		boolean temp=true;
		int projectCount=0;
		for(WebElement projectId : projectIdElement)
		{
			String project = projectId.getText();
			if(project.equalsIgnoreCase(projId))
			{
				temp=false;
				break;
			}
			projectCount++;
		}
		if(temp==false)
		{
			System.out.println("project is not deleted from GUI");
		}else
		{
			System.out.println("Project is deleted from GUI");
		}
		System.out.println(projectCount);
		
		driver.close();
		
	}

}

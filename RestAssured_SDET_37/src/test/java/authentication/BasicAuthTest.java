package authentication;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

import java.util.concurrent.TimeUnit;

public class BasicAuthTest 
{	
	@Test
	
	public void basicAuth()
	{
		baseURI="http://localhost";
		port=8084;
		given()
		.auth().basic("rmgyantra", "rmgy@9999")
		
		.when()
		.get("/login")
		
		.then()
		.assertThat().statusCode(202)
		.contentType(ContentType.JSON)
		.time(Matchers.lessThan(2000L),TimeUnit.MILLISECONDS)
		.log().all();
	}
	
	@Test
	public void preemptiveauth()
	{
		baseURI="http://localhost";
		port=8084;
		
		given()
		.auth().preemptive().basic("rmgyantra", "rmgy@9999")
		
		.when()
		.get("/login")
		
		.then()
		.assertThat().statusCode(202)
		.contentType(ContentType.JSON)
		.time(Matchers.lessThan(2000L),TimeUnit.MILLISECONDS);
		
	}
	
	@Test
	public void digestiveAuth()
	{
		baseURI="http://localhost";
		port=8084;
		
		given()
		.auth().digest("rmgyantra", "rmgy@9999")
		
		.when()
		.get("/login")
		
		.then()
		.assertThat().statusCode(202)
		.contentType(ContentType.JSON)
		.time(Matchers.lessThan(2000L),TimeUnit.MILLISECONDS);
	}


}

package differentWaysToPost;

import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class AddProjectUsingHashMap
{
@Test
public void addProjectUsingHashMap()
{
	Random random = new Random();
	HashMap map = new HashMap();
	map.put("createdBy", "Pooja");
	map.put("projectName", "TNPS"+random.nextInt(200));
	map.put("status","Completed");
	map.put("teamSize", 20);
	
	baseURI="http://localhost";
	port=8084;
		
	
	given()
	.body(map)
	.contentType(ContentType.JSON)
	
	.when()
	.post("/addProject")
	
	.then()
	.assertThat().contentType(ContentType.JSON).statusCode(201)
	.time(Matchers.lessThan(2L), TimeUnit.SECONDS)
	.log().all();
}
}

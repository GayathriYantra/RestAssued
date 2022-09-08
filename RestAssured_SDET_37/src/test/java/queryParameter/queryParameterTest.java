package queryParameter;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class queryParameterTest
{
@Test
public void queryParam()
{
	baseURI="https://reqres.in/";
	given()
	.queryParam("Page", 2)
	
	.when()
	.get("api/users")
	.then()
	.log().all();
	
}
}

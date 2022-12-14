package authentication;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class Oath2_Barn_Unlock_Test 
{
@Test
public void barnUnlock()
{
	Response response = given()
			.formParam("client_id","RestAssuredGayathri")
			.formParam("client_secret", "6dc2320a1f9003a471faf2041bbbe70e")
			.formParam("grant_type", "client_credentials")
			.formParam("redirect_url", "https://restapi.com")
			.formParam("code", "3749")

			.when()
			.post("http://coop.apps.symfonycasts.com/token ");

	String token = response.jsonPath().get("access_token");
	System.out.println(token);
	given()
	.auth().oauth2(token)
	.pathParam("USER_ID",3749)

	.when()
	.post("http://coop.apps.symfonycasts.com/api/{USER_ID}/barn-unlock")

	.then().log().all();

}
}

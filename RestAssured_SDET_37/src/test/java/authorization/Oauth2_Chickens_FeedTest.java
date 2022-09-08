package authorization;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class Oauth2_Chickens_FeedTest
{
	@Test
	public void oauth2ChickensFeed()
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
		.post("http://coop.apps.symfonycasts.com/api/{USER_ID}/chickens-feed")

		.then().log().all();
	}
}

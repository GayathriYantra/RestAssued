package authorization;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;



public class BearerTokenAuthTest 
{
@Test
public void bearerTokenAuth()
{
given()
.auth().oauth2("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")

.when()
.post("https://api.github.com/user/repos")

.then()
.log().all();

}
}

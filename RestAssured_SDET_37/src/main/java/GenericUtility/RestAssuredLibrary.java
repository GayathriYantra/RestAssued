package GenericUtility;

import static io.restassured.RestAssured.*;

import io.restassured.response.Response;
/**
 * 
 * this class contains rest assured specific reusable methods
 * @author admin
 *
 */

public class RestAssuredLibrary 
{
/**
 * this method will get json data through json path from response body
 * @param response
 * @param path
 * @return
 */
public String getJsonData(Response response,String path)
{
String jsonData=response.jsonPath().get(path);
return jsonData;
}
}

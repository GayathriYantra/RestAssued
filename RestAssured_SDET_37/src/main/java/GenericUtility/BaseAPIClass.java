package GenericUtility;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.port;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
/**
 * 
 * @author admin
 *
 */
public class BaseAPIClass 
{
public DataBaseLibrary dLib=new DataBaseLibrary();
public JavaUtility jLib=new JavaUtility();
public RestAssuredLibrary raLib=new RestAssuredLibrary();
/**
 * this method is used to open the database connection
 */
@BeforeSuite
public void bsConfig() 
{
	dLib.connectToDatabase("projects");
	baseURI="http://localhost";
	port=8084;
}

/**
 * this method is used to close the database connection
 */
@AfterSuite
public void asConfig()
{
dLib.closeBatabase();
}
}

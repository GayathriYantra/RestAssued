package GenericUtility;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;


import io.github.bonigarcia.wdm.WebDriverManager;
/**
 * 
 * @author SanjayBabu
 *
 */
public class BaseClass {

	public static WebDriver sdriver;
	public WebDriver driver;
	public DataBaseLibrary dLib=new DataBaseLibrary();
	public ExcelUtility eLib=new ExcelUtility();
	public FileUtility fLib=new FileUtility();
	public JavaUtility jLib=new JavaUtility();
	public WebDriverUtility wLib=new WebDriverUtility();
	
	/**
	 * connecting to DB
	 */
 /*	@BeforeSuite(groups = {"smokeTest","regressionTest"})
	public void dbConfig()
	{
		dLib.connectToDatabase("projects");
	}
	
	/**
	 * launching browser
	 * @throws IOException
	 */
	//@Parameters("BROWSER")
	@BeforeClass
	public void launchTheBrowser() throws IOException
	{
		String BROWSER = fLib.getPropertyKeyValue("browser");
		String URL = fLib.getPropertyKeyValue("url");
		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			driver = WebDriverManager.chromedriver().create();
		}else if(BROWSER.equalsIgnoreCase("firefox"))
		{
			driver=WebDriverManager.firefoxdriver().create();
		}else {
			WebDriverManager.chromedriver().create();
		}
		System.out.println("browser is launched");
		//enter the url
		sdriver=driver;
		driver.get(URL);
		//maximize the window
		wLib.maximizeTheWindow(driver);
		//implicit wait
		wLib.waitTillPageGetsLoad(driver);
		
		//Entering the login details
		driver.findElement(By.id("username")).sendKeys("rmgyantra");
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[text()='Sign in']"));
	}
	
	/**
	 * logging into Application
	 * @throws IOException
	 */
	/*@BeforeMethod(groups = {"smokeTest","regressionTest"})
	public void loginToApp() throws IOException
	{
		String USERNAME = fLib.getPropertyKeyValue("username");
		String PASSWORD = fLib.getPropertyKeyValue("password");
		
//		LoginPage loginPage = new LoginPage(driver);
//		loginPage.loginToApp(USERNAME, PASSWORD);
		
		System.out.println("successfully logged into application");
	}
	
	/**
	 * logout from application
	 */
	/*@AfterMethod(groups = {"smokeTest","regressionTest"})
	public void logoutFromApp()
	{
//		HomePage homepage = new HomePage(driver);
//		homepage.Logout(driver);
		
		System.out.println("logged out from application");
	}
	
	/**
	 * closeing browser
	 */
	@AfterClass
	public void closeTheBrowser()
	{
		driver.quit();
		System.out.println("Browser is closed");
	}
	
	/**
	 * closeing DB
	 */
	@AfterSuite(groups = {"smokeTest","regressionTest"})
	public void closeBatabaseConnection()
	{
		dLib.closeBatabase();
	}
}

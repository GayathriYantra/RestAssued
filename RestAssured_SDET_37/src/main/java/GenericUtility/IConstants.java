package GenericUtility;

public interface IConstants 
{
	String filePath="./src/test/resources/commondata.properties.txt";
	String excelPath="./src/test/resources/Sdet37TestData.xlsx";
	String chromeKey="webdriver.chrome.driver";
	String chromeValue="./src/main/resources/chromedriver.exe";
	String DbUrl="jdbc:mysql://localhost:3306/";
	String DBUsername="root";
	String DBPassword="root";
	
	String appUserName="rmgyantra";
	String appPassword="rmgy@9999";
	
	int implicitlyWaitDuration=10;
	int explicitWaitDuration=10;
}
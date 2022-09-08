package GenericUtility;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListnerImplTn implements ITestListener{
	ExtentReports report;
	ExtentTest test;
	
	public void onTestStart(ITestResult result) {
		test=report.createTest(result.getName());
	}

	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, result.getMethod().getMethodName());
		test.log(Status.PASS, result.getThrowable());
	}

	public void onTestFailure(ITestResult result) {
	
		test.log(Status.FAIL, result.getMethod().getMethodName());
		test.log(Status.FAIL, result.getThrowable());
		
		try {
			String screenShot = WebDriverUtility.takeScreenShot(BaseClass.sdriver, result.getMethod().getMethodName());
			test.addScreenCaptureFromPath(screenShot);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		String testName = result.getMethod().getMethodName();
//		System.out.println("<--------I am Listening----------->");
//		
//		TakesScreenshot screenShot = (TakesScreenshot)BaseClass.sdriver;
//		File src = screenShot.getScreenshotAs(OutputType.FILE);
//		LocalDateTime localDateTime = LocalDateTime.now();
//		String dateTime = localDateTime.toString().replace(" ", "_").replace(":", "_");
//		File dst = new File("screenShots/"+"_"+testName+"_"+dateTime+".PNG");
//		try {
//			FileUtils.copyFile(src, dst);
//		} catch (IOException e) {
//			e.printStackTrace();
//			System.out.println("problem in saving screenshot"+e.getMessage());
//		}		
	}

	public void onTestSkipped(ITestResult result) {
		test.log(Status.SKIP, result.getMethod().getMethodName());
		test.log(Status.SKIP, result.getThrowable());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		
	}

	public void onStart(ITestContext context) {
		ExtentSparkReporter spark = new ExtentSparkReporter("./ExtentReport/report1.html");
		spark.config().setTheme(Theme.DARK);
		spark.config().setReportName("sanjay");
		spark.config().setDocumentTitle("extent report 1");
		
		report=new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("platformName", "windows11");
		report.setSystemInfo("document Created By", "sanjay");
		report.setSystemInfo("report Verified By", "deepak");
	}

	public void onFinish(ITestContext context) {
	report.flush();
	}

}

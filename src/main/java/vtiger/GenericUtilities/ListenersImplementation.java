package vtiger.GenericUtilities;

import java.io.File;
import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
/**
 * This class provides implementation for the ITestListeners Interface of TestNG
 * @author Asus
 *
 */

public class ListenersImplementation implements ITestListener
{
	ExtentReports report;
	ExtentTest test;
	
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		String methodName = result.getMethod().getMethodName();
		test = report.createTest(methodName);
		test.log(Status.INFO, "==== test script execution STARTED =====");
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		String methodName = result.getMethod().getMethodName();		
		test.log(Status.PASS, methodName+"==== PASS ====");
		
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		String methodName = result.getMethod().getMethodName();
		test.log(Status.FAIL, methodName+"===== FAIL =====");
		test.log(Status.INFO, result.getThrowable());
		
		WebDriverUtility wUtil=new WebDriverUtility();

		String screenShotName = methodName+"-"+new JavaUtility().getSystemDateInFormat();
		
		try {
			String path = wUtil.takesScreenShot(BaseClass.sDriver, screenShotName);
			test.addScreenCaptureFromPath(path); // go to the screenshot location and it will be attach to the report
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		String methodName = result.getMethod().getMethodName();
		test.log(Status.SKIP, methodName+"====== SKIPPED ======");
		test.log(Status.INFO, result.getThrowable()); //print the exception in report
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		System.out.println("====execution started=====");
		
		//Extent Report Configuration 
		                                                                          //Report-05 April 2023-01-52-60.html
		ExtentSparkReporter htmlreporter=new ExtentSparkReporter(".\\ExtentReports\\Report-"+new JavaUtility().getSystemDateInFormat()+".html");
		htmlreporter.config().setDocumentTitle("Vtiger Execution Report");
		htmlreporter.config().setTheme(Theme.DARK);
		htmlreporter.config().setReportName("Automation Execution Report");
		
		report=new ExtentReports();
		report.attachReporter(htmlreporter);
		report.setSystemInfo("Base URL", "http://localhost:8888");
		report.setSystemInfo("Base Browser", "Firefox");
		report.setSystemInfo("Base Platform", "Windows");
		report.setSystemInfo("Reporter-name", "Roshan");

	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
		System.out.println("====execution finished=====");
		
		report.flush(); //Generates the report
	}
	
}

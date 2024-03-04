/**
 * 
 */
package Utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

/**
 * @Author Akash Naykude
 * 03-Mar-2024
 */
public class MyListenersClass extends BaseClass 
implements ITestListener
{
	public MyListenersClass() throws Exception {}

	public ExtentReports reports;
	public ExtentTest eTest;
	
	@Override
	public void onStart(ITestContext context) {
		reports=reportConfig();
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		String methodName=result.getMethod().getMethodName();
		eTest=reports.createTest(methodName);
		eTest
		.assignAuthor("Akash Naykude")
		.assignCategory("Smoke Test")
		.assignDevice("Win11 Chrome");
		eTest.log(Status.INFO, methodName+" execution started");

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String methodName=result.getMethod().getMethodName();
		eTest.log(Status.PASS, methodName+" is Passed");
		
		String updatedPath = null;
		try {
			 updatedPath=screenshotsConfig(methodName);
			 eTest.addScreenCaptureFromPath(updatedPath, methodName);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String methodName=result.getMethod().getMethodName();
		eTest.log(Status.FAIL, methodName+" is Failed");
		
		String updatedPath = null;
		try {
			 updatedPath=screenshotsConfig(methodName);
			 eTest.addScreenCaptureFromPath(updatedPath, methodName);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	


	@Override
	public void onTestSkipped(ITestResult result) {
		String methodName=result.getMethod().getMethodName();
		eTest.log(Status.SKIP, methodName+" is Skipped");
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		String methodName=result.getMethod().getMethodName();
		eTest.log(Status.FAIL, methodName+" is Failed With Timeout");
		
		String updatedPath = null;
		try {
			 updatedPath=screenshotsConfig(methodName);
			 eTest.addScreenCaptureFromPath(updatedPath, methodName);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void onFinish(ITestContext context) {
		reports.flush();
		try {
			Desktop.getDesktop().browse(new File(System.getProperty("user.dir")+"/Reports/").toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	
	

}

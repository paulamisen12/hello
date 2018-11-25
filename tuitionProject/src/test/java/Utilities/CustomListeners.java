package Utilities;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.LogStatus;

import Base.TestBase;


public class CustomListeners extends TestBase implements ITestListener,ISuiteListener {

	public 	String messageBody;
	public void onFinish(ITestContext arg0) {
		log.debug("Suite ended");
		
	}

	public void onStart(ITestContext arg0) {
		log.debug("Suite Start");
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailure(ITestResult arg0) {
		log.debug(arg0.getName().toUpperCase() + " FAIL");
		//test.log(LogStatus.PASS, arg0.getName().toUpperCase());
		try{
		test.log(LogStatus.FAIL, test.addScreenCapture("C://Users//dipra//Desktop//jaguar-wide.jpg"));	
		}
		catch(Exception e){
			test.log(LogStatus.FAIL, "Failed");
			System.out.println(e.getStackTrace());
		}
		rep.endTest(test);
		rep.flush();
		
	}

	public void onTestSkipped(ITestResult arg0) {
		test.log(LogStatus.SKIP, arg0.getName().toUpperCase()+" Skipped the test");
		rep.endTest(test);
		rep.flush();
		
	}


	public void onTestStart(ITestResult arg0) {
		log.debug(arg0.getName().toUpperCase() + " Started");
		test = rep.startTest(arg0.getName().toUpperCase());
	
	}

	public void onTestSuccess(ITestResult arg0) {
		log.debug(arg0.getName().toUpperCase() + " PASS");
		try{
			test.log(LogStatus.PASS, test.addScreenCapture("C://Users//dipra//Desktop//jaguar-wide.jpg"));	
			}
			catch(Exception e){
				test.log(LogStatus.PASS, "Failed");
				System.out.println(e.getStackTrace());
			}

		rep.endTest(test);
		rep.flush();		
	}

	public void onFinish(ISuite arg0) {		
	}

	public void onStart(ISuite arg0) {
		// TODO Auto-generated method stub
		
	}

}

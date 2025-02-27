package com.comcast.CRM.listenerutility;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.CRM.basetest.BaseClass;
import com.comcast.CRM.generic.webdriverutility.UtilityClassObject;
import com.google.common.io.Files;

public class ListenerImpClass  implements ITestListener, ISuiteListener{
	
	public static ExtentReports report;
	public static ExtentTest test;

	@Override
	public void onStart(ISuite suite) {
		System.out.println("Report configuration");
		
		//Extent Report
		String curtime = new Date().toString().replace(" ","_").replace(":", "_");
		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvancedReport/report"+curtime+".html");
		spark.config().setDocumentTitle("CRM Report create Test");
		spark.config().setReportName("CRM TEST");
		spark.config().setTheme(Theme.STANDARD);
				
		report = new ExtentReports();
		report.attachReporter(spark);
		
		report.setSystemInfo("OS", "mac");
		
	}

	@Override
	public void onFinish(ISuite suite) {
		System.out.println("Report Backup");
		report.flush();
		
	}

	@Override
	public void onTestStart(ITestResult result) {
		String methodname = result.getMethod().getMethodName();
		System.out.println("===========>"+methodname+" Start<===========");
		report.setSystemInfo("BROWSER", UtilityClassObject.getBrowser());
		test = report.createTest(methodname);
		UtilityClassObject.setTest(test);
		test.log(Status.INFO,methodname+"====>  STARTED  <====");
		
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		String methodname = result.getMethod().getMethodName();
		System.out.println("===========>"+methodname+" END<===========");
		test.log(Status.INFO,methodname+"====>  COMPLETED  <====");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		String methodname = result.getMethod().getMethodName();
		TakesScreenshot t = (TakesScreenshot)BaseClass.sdriver;
		File src = t.getScreenshotAs(OutputType.FILE);
		File dest = new File("./ScreenShot/"+methodname+".png");
		try {
			Files.copy(src, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	    String curtime = new Date().toString().replace(" ","_").replace(":", "_");
//		TakesScreenshot ts = (TakesScreenshot)BaseClass.sdriver;
//		File srcfile = ts.getScreenshotAs(OutputType.FILE);
//		File destfile  = new File("./TimeScreenshot/"+methodname+"+"+curtime+".png");
//		
//		try {
//			Files.copy(srcfile, destfile);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		TakesScreenshot edriver = (TakesScreenshot)BaseClass.sdriver;
		String filepath = edriver.getScreenshotAs(OutputType.BASE64);
		test.addScreenCaptureFromBase64String(filepath, methodname+"+"+curtime);
		test.log(Status.FAIL,methodname+"====>  FAILED  <====");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
	
		String methodname = result.getMethod().getMethodName();
		test.log(Status.SKIP,methodname+"====>  SKIPPED  <====");
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}

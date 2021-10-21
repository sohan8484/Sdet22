package com.crm.vtiger.genericUtils;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ListenersClass implements ITestListener{
ExtentReports report;
ExtentTest test;
	public void onTestFailure(ITestResult result){
		String testName=result.getMethod().getMethodName();
		System.out.println(testName);
		Date date = new Date();
		String currentDate=date.toString().replace(" ", "_").replace(":", "_");
		EventFiringWebDriver takescreenshot = new EventFiringWebDriver(BaseClass.StaticDriver);
		File source = takescreenshot.getScreenshotAs(OutputType.FILE);
		String screenshotPath = System.getProperty("user.dir")+"//screenshot//"+result.getMethod().getMethodName()+"_"+currentDate+".PNG";
		try {
			FileUtils.copyFile(source, new File(screenshotPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//step 5: log for failure methods
		test.log(Status.FAIL, result.getMethod().getMethodName()+" is failed");
		test.log(Status.FAIL, result.getThrowable());
		test.addScreenCaptureFromPath(screenshotPath);
	}
	
	public void onFinish(ITestContext context) {
		//step 7: flush the report
		report.flush();
	}

	public void onStart(ITestContext context) {
		//step1:configuration
		Date date = new Date();
		String currentDate=date.toString().replace(" ", "_").replace(":", "_");
		ExtentSparkReporter htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/extentreport "+currentDate+".html");
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setDocumentTitle("Vtiger Automation Report");
		htmlReporter.config().setReportName("Execution Report");
		//step 2: attaching report and system info
		report = new ExtentReports();
		report.attachReporter(htmlReporter);
		report.setSystemInfo("OS", "Windows");
		report.setSystemInfo("URL", "http://localhost:8888/");
		report.setSystemInfo("Platform", "Windows10");
		report.setSystemInfo("Reporter Name", "Khan");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	public void onTestFailedWithTimeout(ITestResult result) {
	}

	public void onTestSkipped(ITestResult result) {
		//step6: log for skip methods
		test.generateLog(Status.SKIP, result.getMethod().getMethodName()+"is Skipped");
		test.log(Status.SKIP, result.getThrowable());
	}

	public void onTestStart(ITestResult result) {
		//step3: create test method
		test=report.createTest(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		//step4: log for pass methods
		test.log(Status.PASS, result.getMethod().getMethodName()+" is passed");
	}

}

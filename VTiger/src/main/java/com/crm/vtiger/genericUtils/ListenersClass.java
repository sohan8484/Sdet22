package com.crm.vtiger.genericUtils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class ListenersClass implements ITestListener{

	public void onTestFailure(ITestResult result){
		String testName=result.getMethod().getMethodName();
		System.out.println(testName);
		EventFiringWebDriver takescreenshot = new EventFiringWebDriver(BaseClass.StaticDriver);
		File source = takescreenshot.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(source, new File("./screenshot/"+testName+".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void onFinish(ITestContext context) {

	}

	public void onStart(ITestContext context) {
	
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	
	}

	public void onTestFailedWithTimeout(ITestResult result) {
	}




	public void onTestSkipped(ITestResult result) {
	}

	public void onTestStart(ITestResult result) {
	}

	public void onTestSuccess(ITestResult result) {
	}

}

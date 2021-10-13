package com.crm.vtiger.genericUtils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.google.protobuf.Method;

public class SampleTest extends BaseClass{
public void varifyHomePage(Method mtd) throws IOException {
	String currentTestName = mtd.getName();
	EventFiringWebDriver edriver  = new EventFiringWebDriver(driver);
	File srcFile = edriver.getScreenshotAs(OutputType.FILE);
	File destFile= new File("./ScreenShot/"+currentTestName+".png");
	FileUtils.copyFile(srcFile, destFile);
}
}

package com.crm.vtiger.genericUtils;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.vtiger.POMrepository.Home;
import com.vtiger.POMrepository.LoginToVtiger;

public class BaseClass {
	public static WebDriver StaticDriver;
	public WebDriver driver=null;
	public ExcelUtility eLib = new ExcelUtility();
	public FileUtility fLib = new FileUtility();
	public JavaUtility jLib = new JavaUtility();
	public WebDriverUtility wLib = new WebDriverUtility();
	
	@BeforeSuite(groups = {"smoketest"})
	public void connectDB() throws Throwable
	{
		System.out.println("db suceeesss");
	}
	
	@Parameters(value= {"browser"})
	@BeforeClass(groups = {"smoketest"})
	public void launchBrowser(@Optional("chrome") String BROWSER) throws Throwable {
		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			driver=new ChromeDriver();
		}
		else if(BROWSER.equalsIgnoreCase("firefox")) {
			driver=new FirefoxDriver();
		}
		else {
			driver=new ChromeDriver();
		}
		StaticDriver=driver;
		wLib.waitUntilPageLoad(driver);
		String URL=fLib.getPropertyKeyValue("url");
		driver.get(URL);
	}
	
	@BeforeMethod(groups = {"smoketest"})
	public void login() throws Throwable {
		LoginToVtiger login=new LoginToVtiger(driver);
		String USERNAME=fLib.getPropertyKeyValue("username");
		String PASSWORD=fLib.getPropertyKeyValue("password");
		login.loginToApp(USERNAME, PASSWORD);
		wLib.waitUntilPageLoad(driver);
	}
	
	@AfterMethod(groups = {"smoketest"})
	public void logout() {
		Home homePage = new Home(driver);
		homePage.logout();
	}
	
	@AfterClass(groups = {"smoketest"})
	public void closeBrowser() {
		driver.close();
	}
	@AfterSuite(groups = {"smoketest"})
	public void closeDB() {
		System.out.println("closed success");
	}
}
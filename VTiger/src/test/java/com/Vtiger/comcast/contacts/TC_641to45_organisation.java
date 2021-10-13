package com.Vtiger.comcast.contacts;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.crm.vtiger.genericUtils.ExcelUtility;
import com.crm.vtiger.genericUtils.FileUtility;
import com.crm.vtiger.genericUtils.JavaUtility;
import com.crm.vtiger.genericUtils.WebDriverUtility;

public class TC_641to45_organisation {
	
	WebDriver driver;
	@BeforeMethod
	public void login() throws IOException {
		driver= new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		FileUtility f=new FileUtility();
		String url = f.getPropertyKeyValue("url");
		System.out.println(url);
		String un = f.getPropertyKeyValue("username");
		String pwd = f.getPropertyKeyValue("password");
		driver.get(url);
		driver.findElement(By.name("user_name")).sendKeys(un);
		driver.findElement(By.name("user_password")).sendKeys(pwd+Keys.ENTER);
	}
	@Test
	public void searchOrg() {
		driver.findElement(By.name("query_string")).sendKeys("ACC1"+Keys.ENTER);
	}
	@Test(priority=1)
	public void createOrg() throws IOException, Throwable {
		WebDriverUtility wdu=new WebDriverUtility();
		wdu.waitUntilPageLoad(driver);
		ExcelUtility ex= new ExcelUtility();
		String orgname = ex.getExcelData("sheet1", 1, 1);
		driver.findElement(By.xpath("(//a[text()='Organizations'])[1]")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(orgname+JavaUtility.getRandomData());
		driver.findElement(By.xpath("(//input[@name='assigntype'])[2]")).click();
		WebElement lb1 = driver.findElement(By.xpath("//select[@name='assigned_group_id']"));
		wdu.SelectOptionText(lb1, "4");
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
		Thread.sleep(2000);
		String etitle = "Administrator - Organizations - vtiger CRM 5 - Commercial Open Source CRM";
		String aTitle = driver.getTitle();
		SoftAssert s= new SoftAssert();
		s.assertEquals(aTitle, etitle);
		s.assertAll();
		
		
	}
	@Test(priority=2)
	public void createOrg2() throws IOException, Throwable {
		WebDriverUtility wdu=new WebDriverUtility();
		wdu.waitUntilPageLoad(driver);
		ExcelUtility ex= new ExcelUtility();
		String orgname = ex.getExcelData("sheet1", 1, 1);
		driver.findElement(By.xpath("(//a[text()='Organizations'])[1]")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(orgname+JavaUtility.getRandomData());
		driver.findElement(By.xpath("(//input[@name='assigntype'])[2]")).click();
		WebElement lb1 = driver.findElement(By.xpath("//select[@name='assigned_group_id']"));
		wdu.SelectOptionText(lb1, "2");
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
		Thread.sleep(2000);
		String etitle = "Administrator - Organizations - vtiger CRM 5 - Commercial Open Source CRM";
		String aTitle = driver.getTitle();
		SoftAssert s= new SoftAssert();
		s.assertEquals(aTitle, etitle);
		s.assertAll();
	}
	@Test(priority=3)
	public void createOrg3() throws IOException, Throwable {
		WebDriverUtility wdu=new WebDriverUtility();
		wdu.waitUntilPageLoad(driver);
		ExcelUtility ex= new ExcelUtility();
		String orgname = ex.getExcelData("sheet1", 1, 1);
		driver.findElement(By.xpath("(//a[text()='Organizations'])[1]")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(orgname+JavaUtility.getRandomData());
		boolean checkBox = driver.findElement(By.name("emailoptout")).isSelected();
	    if (checkBox==false)
	    {
	    	driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
	    }
	    else
	    {
	    	driver.findElement(By.name("emailoptout")).click();
	    }
	    Thread.sleep(2000);
		String etitle = "Administrator - Organizations - vtiger CRM 5 - Commercial Open Source CRM";
		String aTitle = driver.getTitle();
		SoftAssert s= new SoftAssert();
		s.assertEquals(aTitle, etitle);
		s.assertAll();
	}
	@Test(priority=4)
	public void createOrgWithoutNotify() throws IOException, Throwable {
		WebDriverUtility wdu=new WebDriverUtility();
		wdu.waitUntilPageLoad(driver);
		ExcelUtility ex= new ExcelUtility();
		String orgname = ex.getExcelData("sheet1", 1, 1);
		driver.findElement(By.xpath("(//a[text()='Organizations'])[1]")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(orgname+JavaUtility.getRandomData());
		boolean cbox = driver.findElement(By.name("notify_owner")).isSelected();
		if (cbox==false)
		{
			driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
		}
		else
		{
			driver.findElement(By.name("notify_owner")).click();
		}
		Thread.sleep(2000);
		String etitle = "Administrator - Organizations - vtiger CRM 5 - Commercial Open Source CRM";
		String aTitle = driver.getTitle();
		SoftAssert s= new SoftAssert();
		s.assertEquals(aTitle, etitle);
		s.assertAll();
	}
	@AfterMethod
	public void logout() throws InterruptedException {
		WebDriverUtility wdu=new WebDriverUtility();
		wdu.waitUntilPageLoad(driver);
		Thread.sleep(3000);
		WebElement target = driver.findElement(By.xpath("(//img[@style='padding: 0px;padding-left:5px'])[1]"));
		wdu.mousehover(driver, target);
		driver.findElement(By.linkText("Sign Out")).click();
		driver.close();
	}
	

}
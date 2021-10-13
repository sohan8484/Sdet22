package com.Vtiger.comcast.contacts;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.crm.vtiger.genericUtils.FileUtility;
import com.crm.vtiger.genericUtils.WebDriverUtility;
import com.vtiger.POMrepository.Contacts;
import com.vtiger.POMrepository.CreateNewContact;
import com.vtiger.POMrepository.Home;
import com.vtiger.POMrepository.LoginToVtiger;

public class TC005CreateContact {
	@Test
	/*
	 * author @ sohan
	 */
	/**
	 * automate the vtiger application on creating organization and on creating new contact
	 * @throws InterruptedException
	 * @throws AWTException
	 */
	public void executingTestCase() throws Throwable {
		/*create objects*/
		WebDriver driver = new ChromeDriver();
		FileUtility file = new FileUtility();
		WebDriverUtility wdu= new WebDriverUtility();
		LoginToVtiger login=new LoginToVtiger(driver);

		String URL=file.getPropertyKeyValue("url");
		String USERNAME=file.getPropertyKeyValue("username");
		String PASSWORD=file.getPropertyKeyValue("password");
		/*login to application*/
		
		driver.get(URL);
		login.loginToApp(USERNAME, PASSWORD);
		
		wdu.waitUntilPageLoad(driver);

		/*navigate to contact module*/
		Home homePage = new Home(driver);
		homePage.getContactLinks().click();
		
		/*navigate to create contact page*/
		Contacts contPage = new Contacts(driver);
		contPage.getCreateContImg().click();
		
		/*create contact*/
		CreateNewContact cnc = new CreateNewContact(driver);
		String contName="comcast_1";

		/*add contact name*/
		cnc.createContct(contName);				
		driver.findElement(By.xpath("//img[@title='Import Contacts']")).click();
		
		File f= new File("./Data/Contacts1.csv");
		String path = f.getAbsolutePath();
		driver.findElement(By.id("import_file")).sendKeys(path);
		driver.findElement(By.name("next")).click();
		driver.findElement(By.name("import")).click();
		driver.findElement(By.name("cancel")).click();

		driver.findElement(By.className("txtBox")).sendKeys("CON23");
		driver.findElement(By.name("submit")).click();
		
		boolean name = driver.findElement(By.xpath("//a[contains(text(),'knn1212')]")).isDisplayed();
		if(name==true) {
			System.out.println("contact upload success");
			
		}
		else
			System.out.println("failed");
		
		String actual = "Administrator - Contacts - vtiger CRM 5 - Commercial Open Source CRM";
		String expected = driver.getTitle();
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actual, expected);
		sa.assertAll();
		
		/*log out*/
		homePage.logout();

		/*close the browser*/
		driver.close();	
	}
}
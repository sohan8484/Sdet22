package com.Vtiger.comcast.contacts;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.crm.vtiger.genericUtils.ExcelUtility;
import com.crm.vtiger.genericUtils.FileUtility;
import com.crm.vtiger.genericUtils.JavaUtility;
import com.crm.vtiger.genericUtils.WebDriverUtility;
import com.vtiger.POMrepository.Contacts;
import com.vtiger.POMrepository.CreateNewContact;
import com.vtiger.POMrepository.Home;
import com.vtiger.POMrepository.LoginToVtiger;

public class TC002CreateContact {
	@Test
	/*
	 * author @ sohan
	 */
	/**
	 * automate the vtiger application on creating organization and on creating new contact
	 * @throws Throwable
	 */
	public void executingTestCase() throws Throwable {

		/*create objects*/
		WebDriver driver = new ChromeDriver();
		FileUtility file = new FileUtility();
		WebDriverUtility wdu= new WebDriverUtility();
		LoginToVtiger login=new LoginToVtiger(driver);
		CreateNewContact cnc = new CreateNewContact(driver);
		ExcelUtility elib= new ExcelUtility();
		
		
		String URL=file.getPropertyKeyValue("url");
		String USERNAME=file.getPropertyKeyValue("username");
		String PASSWORD=file.getPropertyKeyValue("password");
		
		/*login to application*/
		
		driver.get(URL);
		login.loginToApp(USERNAME, PASSWORD);
		
		wdu.waitUntilPageLoad(driver);
		
	
		wdu.waitUntilPageLoad(driver);

		/*navigate to contact module*/
		Home homePage = new Home(driver);
		homePage.getContactLinks().click();

		/*navigate to create contact page*/
		Contacts contPage = new Contacts(driver);
		contPage.getCreateContImg().click();

		/*create contact*/
		String orgname=elib.getExcelData("org", 1, 1);

		/*add contact name*/
		cnc.createContct(orgname+"_"+JavaUtility.getRandomData());

		/*add organization to the contact page*/

		homePage.getContNameLookUpIcon().click();
		
		
		String pw = driver.getWindowHandle();
		Set<String> cw = driver.getWindowHandles();
		for(String pdw:cw) {
			driver.switchTo().window(pdw);

		}
		driver.findElement(By.xpath("//a[text()='spid053']")).click();
		driver.switchTo().window(pw);
		homePage.getSave();
		Thread.sleep(3000);
	
		boolean cont = driver.findElement(By.xpath("//span[contains(text(),//span[.='[ CON12 ] q  -  Contact Information'])]")).isDisplayed();
		
		if(cont==true) {
			System.out.println("contact created successfully");
		}
		else
			System.out.println("contact not created");

		/*log out*/
		homePage.logout();

		/*close the browser*/
		driver.close();


	}
}

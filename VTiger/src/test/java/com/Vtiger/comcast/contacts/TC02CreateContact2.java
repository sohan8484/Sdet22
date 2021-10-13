package com.Vtiger.comcast.contacts;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.crm.vtiger.genericUtils.ExcelUtility;
import com.crm.vtiger.genericUtils.FileUtility;
import com.crm.vtiger.genericUtils.JavaUtility;
import com.crm.vtiger.genericUtils.WebDriverUtility;
import com.vtiger.POMrepository.ContactInfo;
import com.vtiger.POMrepository.Contacts;
import com.vtiger.POMrepository.CreateNewContact;
import com.vtiger.POMrepository.Home;
import com.vtiger.POMrepository.LoginToVtiger;

public class TC02CreateContact2 {
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
		
		/*enter login details*/
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
		String orgName=elib.getExcelData("org", 1, 1);

		/*add contact name*/
		cnc.createContct(orgName+"_"+JavaUtility.getRandomData());

		/*add organization to the contact page*/

		homePage.getContNameLookUpIcon().click();
		cnc.switchToWindow(driver,"Contacts&action");
		
		/*select orgName from child window*/
		driver.findElement(By.xpath("//a[text()='spid053']")).click();
		cnc.switchToWindow(driver, "Accounts&action");
		
		homePage.getSave().click();
		
		/*to check weather the contact is displayed or not*/
		String contName="Administrator";
		ContactInfo contInfoPage = new ContactInfo(driver);
		String actSuccessMsg= contInfoPage.getHeaderName().getText();
		if(actSuccessMsg.contains(contName)) {
			System.out.println(contName +"_"+ "success");
		}
		else
			System.out.println("unsuccess");

		/*log out*/
		homePage.logout();

		/*close the browser*/
		driver.close();

	}
}

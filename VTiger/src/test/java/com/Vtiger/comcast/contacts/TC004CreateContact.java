package com.Vtiger.comcast.contacts;

import java.awt.AWTException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.crm.vtiger.genericUtils.FileUtility;
import com.crm.vtiger.genericUtils.WebDriverUtility;
import com.vtiger.POMrepository.Contacts;
import com.vtiger.POMrepository.CreateNewContact;
import com.vtiger.POMrepository.Home;
import com.vtiger.POMrepository.LoginToVtiger;
import com.vtiger.POMrepository.SearchContacts;

public class TC004CreateContact {
	@Test
	/*
	 * author @ sohan
	 */
	/**
	 * automate the vtiger application on creating organization and on creating new contact
	 * @throws InterruptedException
	 * @throws AWTException
	 */
	public void executingTestCase() throws InterruptedException, AWTException, Throwable {
		/*create objects*/
		WebDriver driver = new ChromeDriver();
		FileUtility file = new FileUtility();
		WebDriverUtility wdu= new WebDriverUtility();
		LoginToVtiger login=new LoginToVtiger(driver);
		WebDriverUtility wLib = new WebDriverUtility();
		/*login to application*/
		String url = file.getPropertyKeyValue("url");
		driver.get(url);
		login.loginToApp("admin", "admin");
		
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
		cnc.getAccNameLookUpIcon().click();
		wLib.switchToWindow(driver, "Contacts&action");
		
		SearchContacts sc=new SearchContacts(driver);
		sc.selectContactFromList(contName);
		
		wLib.switchToWindow(driver, "Creating New Contact");
		cnc.getSaveBtn().click();
		boolean check = driver.findElement(By.className("dvHeaderText")).isDisplayed();
		if (check==true) {
			System.out.println("is displayed");
		}
		else
			System.out.println("not displayed");
//		
//		
		/*log out*/
		homePage.logout();

		/*close the browser*/
		driver.close();	
	}
}

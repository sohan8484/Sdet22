package com.Vtiger.comContacts.xml;


import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.crm.vtiger.genericUtils.BaseClass;
import com.vtiger.POMrepository.Contacts;
import com.vtiger.POMrepository.CreateNewContact;
import com.vtiger.POMrepository.Home;
import com.vtiger.POMrepository.SearchContacts;

public class TC_04CreateContact extends BaseClass {
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
		
	}
}

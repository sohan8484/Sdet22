package com.Vtiger.comContacts.xml;


import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.crm.vtiger.genericUtils.BaseClass;
import com.crm.vtiger.genericUtils.ExcelUtility;
import com.crm.vtiger.genericUtils.JavaUtility;
import com.vtiger.POMrepository.ContactInfo;
import com.vtiger.POMrepository.Contacts;
import com.vtiger.POMrepository.CreateNewContact;
import com.vtiger.POMrepository.Home;

public class TC_02createContact extends BaseClass{
	@Test
	/*
	 * author @ sohan
	 */
	/**
	 * automate the vtiger application on creating organization and on creating new contact
	 * @throws Throwable
	 */
	public void executingTestCase() throws Throwable {

		
		/*navigate to contact module*/
		Home homePage = new Home(driver);
		homePage.getContactLinks().click();

		/*navigate to create contact page*/
		Contacts contPage = new Contacts(driver);
		contPage.getCreateContImg().click();

		/*create contact*/
		ExcelUtility elib = new ExcelUtility();
		String orgName=elib.getExcelData("org", 1, 1);

		/*add contact name*/
		CreateNewContact cnc = new CreateNewContact(driver);
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


	}
}

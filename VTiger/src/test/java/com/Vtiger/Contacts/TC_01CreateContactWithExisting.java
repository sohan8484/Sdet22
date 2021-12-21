package com.Vtiger.Contacts;

import org.testng.annotations.Test;

import com.crm.vtiger.genericUtils.BaseClass;
import com.crm.vtiger.genericUtils.ExcelUtility;
import com.vtiger.POMrepository.Contacts;
import com.vtiger.POMrepository.CreateNewContact;
import com.vtiger.POMrepository.Home;

public class TC_01CreateContactWithExisting extends BaseClass{
	@Test
	/*
	 * author @Sohan m s
	 */
	/**
	 * automate the vtiger and navigate to create new contact module
	 * @throws Throwable
	 */
	public void CreateContact() throws Throwable {
		Home homePage=new Home(driver);
		Contacts contact=new Contacts(driver);
		ExcelUtility elib= new ExcelUtility();
		CreateNewContact createContact=new CreateNewContact(driver);

		//	Open contacts module
		homePage.getContactLinks().click();	
		contact.getCreateContImg().click();

		//	read from excel data	
		String orgName=elib.getExcelData("org", 1, 1);

		//	open create contact page	
		createContact.createContct(orgName);
		createContact.getContNameLookUpIcon().click();
		createContact.createContWithOrg(orgName, driver);

		//	select leadSource
		createContact.leadSrc(driver);

		//	select email check box
		createContact.getEmailCheckBox().click();
		}	
}

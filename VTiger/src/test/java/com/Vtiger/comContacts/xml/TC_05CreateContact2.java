package com.Vtiger.comContacts.xml;


import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.crm.vtiger.genericUtils.BaseClass;
import com.crm.vtiger.genericUtils.JavaUtility;
import com.vtiger.POMrepository.Contacts;
import com.vtiger.POMrepository.CreateNewContact;
import com.vtiger.POMrepository.Home;
import com.vtiger.POMrepository.ImportContacts;
import com.vtiger.POMrepository.SearchAndVarifyInContacts;

public class TC_05CreateContact2 extends BaseClass{
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
		String orgName=eLib.getExcelData("org", 1, 1);

		CreateNewContact cnc = new CreateNewContact(driver);
		cnc.createContct(orgName+"_"+JavaUtility.getRandomData());

		/*add contact name*/
		cnc.createContct(orgName+"_"+JavaUtility.getRandomData());			
		cnc.getImportContact().click();
		wLib.importContacts(driver);
		
		ImportContacts ic=new ImportContacts(driver);
		ic.emport();

		SearchAndVarifyInContacts vc=new SearchAndVarifyInContacts(driver);
		vc.varifyTheText(orgName);
		String actual = "Administrator - Contacts - vtiger CRM 5 - Commercial Open Source CRM";
		String expected = driver.getTitle();
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actual, expected);
		sa.assertAll();
	}
}
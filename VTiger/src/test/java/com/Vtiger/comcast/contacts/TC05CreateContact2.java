package com.Vtiger.comcast.contacts;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.crm.vtiger.genericUtils.ExcelUtility;
import com.crm.vtiger.genericUtils.FileUtility;
import com.crm.vtiger.genericUtils.JavaUtility;
import com.crm.vtiger.genericUtils.WebDriverUtility;
import com.vtiger.POMrepository.Contacts;
import com.vtiger.POMrepository.CreateNewContact;
import com.vtiger.POMrepository.Home;
import com.vtiger.POMrepository.ImportContacts;
import com.vtiger.POMrepository.LoginToVtiger;
import com.vtiger.POMrepository.SearchAndVarifyInContacts;

public class TC05CreateContact2 {
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
		ExcelUtility elib= new ExcelUtility();

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

		CreateNewContact cnc = new CreateNewContact(driver);
		cnc.createContct(orgName+"_"+JavaUtility.getRandomData());

		/*add contact name*/
		cnc.createContct(orgName+"_"+JavaUtility.getRandomData());			
		cnc.getImportContact().click();
		wdu.importContacts(driver);
		
		ImportContacts ic=new ImportContacts(driver);
		ic.emport();

		SearchAndVarifyInContacts vc=new SearchAndVarifyInContacts(driver);
		vc.varifyTheText(orgName);
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
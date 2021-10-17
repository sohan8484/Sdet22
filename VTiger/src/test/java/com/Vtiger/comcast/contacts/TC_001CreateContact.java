package com.Vtiger.comcast.contacts;


import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.vtiger.genericUtils.FileUtility;
import com.crm.vtiger.genericUtils.WebDriverUtility;
import com.vtiger.POMrepository.ContactInfo;
import com.vtiger.POMrepository.Contacts;
import com.vtiger.POMrepository.CreateNewContact;
import com.vtiger.POMrepository.Home;
import com.vtiger.POMrepository.LoginToVtiger;
@Listeners(com.crm.vtiger.genericUtils.ListenersClass.class)
public class TC_001CreateContact {
	@Test
	/*
	 * author @ sohan
	 */
	/**
	 * automate the vtiger application on creating organization and on creating new contact
	 * @throws InterruptedException
	 * @throws AWTException
	 */
	public void executTestCase() throws Throwable {
		/*create objects*/
		WebDriver driver = new ChromeDriver();
		FileUtility file = new FileUtility();
		WebDriverUtility wbd= new WebDriverUtility();
		LoginToVtiger login=new LoginToVtiger(driver);

		
		String URL=file.getPropertyKeyValue("url");
		String USERNAME=file.getPropertyKeyValue("username");
		String PASSWORD=file.getPropertyKeyValue("password");
		
		/*login to application*/
		
		driver.get(URL);
		login.loginToApp(USERNAME, PASSWORD);
		
		wbd.waitUntilPageLoad(driver);
		
		/*navigate to contact module*/
		Home homePage = new Home(driver);
		homePage.getContactLinks().click();
		
		/*navigate to create contact page*/
		Contacts contPage = new Contacts(driver);
		contPage.getCreateContImg().click();
		
		/*create contact*/
		CreateNewContact cnc = new CreateNewContact(driver);
		String contName="comcast_1";
		cnc.createCont(contName);

		/*varify*/
		ContactInfo info = new ContactInfo(driver);
		wbd.waitForElementVisiblity(driver, info.getSuccessfullMsg());
	
		String actualMsg=info.getSuccessfullMsg().getText();
		if(actualMsg.contains(contName)) {
			System.out.println("contact is created successfully ");
		
		}
		else
			System.out.println("contact is not created ");
		
		assertEquals("a", "b");
		
		/*log out*/
		homePage.logout();

		/*close the browser*/
		driver.close();
		


}

}

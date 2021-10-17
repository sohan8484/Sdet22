package com.Vtiger.comContacts.xml;


//import static org.testng.Assert.assertEquals;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.vtiger.genericUtils.BaseClass;
import com.vtiger.POMrepository.ContactInfo;
import com.vtiger.POMrepository.Contacts;
import com.vtiger.POMrepository.CreateNewContact;
import com.vtiger.POMrepository.Home;
@Listeners(com.crm.vtiger.genericUtils.ListenersClass.class)
public class TC_01createContact extends BaseClass{
	
	@Test(groups = {"smoketest"})
	public void createContact() {
	/*navigate to contact module*/
		Home homePage=new Home(driver);
	homePage.getContactLinks().click();
	
	/*navigate to create contact page*/
	Contacts contPage = new Contacts(driver);
	contPage.getCreateContImg().click();
	/*create contact*/
	CreateNewContact cnc = new CreateNewContact(driver);
	String contName="comcast_1";
	cnc.createCont(contName);
	
//	assertEquals("l", "p");
	/*varify*/
	ContactInfo info = new ContactInfo(driver);
	wLib.waitForElementVisiblity(driver, info.getSuccessfullMsg());
	
	
	
	String actualMsg=info.getSuccessfullMsg().getText();
	if(actualMsg.contains(contName)) {
		System.out.println("contact is created successfully ");
	
	}
	else
		System.out.println("contact is not created ");
	}
}

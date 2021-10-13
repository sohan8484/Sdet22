package com.Vtiger.comcast.contacts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.crm.vtiger.genericUtils.WebDriverUtility;
import com.vtiger.POMrepository.ContactInfo;
import com.vtiger.POMrepository.Contacts;
import com.vtiger.POMrepository.CreateNewContact;
import com.vtiger.POMrepository.Home;
import com.vtiger.POMrepository.LoginToVtiger;

public class CreateContacts {
	public static void main(String[] args) {
		
	String contName="comcast_1";
	WebDriver driver = new ChromeDriver();
	WebDriverUtility util=new WebDriverUtility();
	util.waitUntilPageLoad(driver);
	driver.get("http://localhost:8888/");
	LoginToVtiger login = new LoginToVtiger(driver);
	login.loginToApp("admin", "admin");
	
	Home homePage = new Home(driver);
	homePage.getContactLinks().click();
	
	Contacts contPage = new Contacts(driver);
	contPage.getCreateContImg().click();
	
	CreateNewContact cnc = new CreateNewContact(driver);
	cnc.createCont(contName);
	
	ContactInfo contInfoPage = new ContactInfo(driver);
	String actSuccessMsg= contInfoPage.getSuccessfullMsg().getText();
	if(actSuccessMsg.contains(contName)) {
		System.out.println(contName + "success");
	}
	else
		System.out.println("unsuccess");
	homePage.logout();
	}
}

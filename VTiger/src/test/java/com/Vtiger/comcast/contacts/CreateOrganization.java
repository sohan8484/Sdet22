package com.Vtiger.comcast.contacts;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.crm.vtiger.genericUtils.ExcelUtility;
import com.crm.vtiger.genericUtils.FileUtility;
import com.crm.vtiger.genericUtils.JavaUtility;
import com.crm.vtiger.genericUtils.WebDriverUtility;
import com.vtiger.POMrepository.Home;
import com.vtiger.POMrepository.LoginToVtiger;
import com.vtiger.POMrepository.Organizations;

public class CreateOrganization{
	@Test
	public void createNewOrganization() throws Throwable {
		/*create objects*/
		WebDriver driver = new ChromeDriver();
		FileUtility file = new FileUtility();
		WebDriverUtility wbd= new WebDriverUtility();
		LoginToVtiger login=new LoginToVtiger(driver);
		ExcelUtility elib = new ExcelUtility();
		/*login to application*/
		String url = file.getPropertyKeyValue("url");
		driver.get(url);
		login.loginToApp("admin", "admin");

		wbd.waitUntilPageLoad(driver);

		/*navigate to contact module*/
		Home homePage = new Home(driver);
		homePage.getOrganizationLinks().click();
		Organizations org = new Organizations(driver);
		org.getCreateOrgImg().click();
		/*create contact*/
		String orgName=elib.getExcelData("org", 1, 1);

		/*add contact name*/

		/*log out*/
		homePage.logout();

		/*close the browser*/
		driver.close();

	}

}

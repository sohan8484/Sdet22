package com.Vtiger.comContacts.xml;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.crm.vtiger.genericUtils.BaseClass;
import com.crm.vtiger.genericUtils.ExcelUtility;
import com.crm.vtiger.genericUtils.FileUtility;
import com.crm.vtiger.genericUtils.JavaUtility;
import com.crm.vtiger.genericUtils.WebDriverUtility;
import com.vtiger.POMrepository.ContactInfo;
import com.vtiger.POMrepository.Contacts;
import com.vtiger.POMrepository.CreateNewContact;
import com.vtiger.POMrepository.Home;
import com.vtiger.POMrepository.ImportContacts;
import com.vtiger.POMrepository.LoginToVtiger;
import com.vtiger.POMrepository.SearchAndVarifyInContacts;

public class TC1to5CreateContact extends BaseClass{
		@Test(groups = {"smoketest"})
		public void executingTestCase01() {
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
		@Test(groups = {"smoketest"})
		public void executingTestCase02() throws Throwable {


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
		@Test(groups = {"smoketest"})
		public void executingTestCase03() throws Throwable {


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
			
			//uploading photo
			File f= new File("./data/uploadPhoto.jpg");
			String path = f.getAbsolutePath();
			driver.findElement(By.name("imagename")).sendKeys(path);
			
			driver.findElement(By.xpath("//input[@type='submit']")).click();
			Thread.sleep(3000);
			boolean img = driver.findElement(By.xpath("//td/img[@alt='Contact Image']")).isDisplayed();
			if(img==true) {
				System.out.println("image uploaded successfully");
			}
			else
				System.out.println("not uploaded");
			boolean cont = driver.findElement(By.xpath("//span[contains(text(),//span[.='[ CON12 ] q  -  Contact Information'])]")).isDisplayed();
			if(cont==true) {
				System.out.println("contact created successfully");
			}
			else
				System.out.println("contact not created");		

		}
		@Test(groups = {"smoketest"})
		public void executingTestCase04() throws Throwable {
			
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
			driver.findElement(By.xpath("(//td/img[@title='Select'])[2]")).click();
			
			wLib.switchToWindow(driver, "Contacts");	

		}
		@Test(groups= {"smoketest"})
		public void executingTestCase05() throws Throwable {
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
		}
	}

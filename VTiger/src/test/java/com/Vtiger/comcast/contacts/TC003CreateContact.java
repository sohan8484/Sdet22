package com.Vtiger.comcast.contacts;

import java.awt.AWTException;
import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.crm.vtiger.genericUtils.FileUtility;
import com.crm.vtiger.genericUtils.WebDriverUtility;
import com.vtiger.POMrepository.Contacts;
import com.vtiger.POMrepository.CreateNewContact;
import com.vtiger.POMrepository.Home;
import com.vtiger.POMrepository.LoginToVtiger;

public class TC003CreateContact {
	@Test
	/*
	 * author @ sohan
	 */
	/**
	 * automate the vtiger application on creating organization and on creating new contact
	 * @throws InterruptedException
	 * @throws AWTException
	 */
	public void executingTestCase() throws InterruptedException, AWTException, Throwable {

		/*create objects*/
		WebDriver driver = new ChromeDriver();
		FileUtility file = new FileUtility();
		WebDriverUtility wdu= new WebDriverUtility();
		LoginToVtiger login=new LoginToVtiger(driver);

		/*login to application*/
		String url = file.getPropertyKeyValue("url");
		driver.get(url);
		login.loginToApp("admin", "admin");
		
		wdu.waitUntilPageLoad(driver);

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

		
		/*log out*/
		homePage.logout();

		/*close the browser*/
		driver.close();	
	}
}

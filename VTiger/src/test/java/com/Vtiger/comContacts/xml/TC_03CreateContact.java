package com.Vtiger.comContacts.xml;

import java.io.File;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.crm.vtiger.genericUtils.BaseClass;
import com.vtiger.POMrepository.Contacts;
import com.vtiger.POMrepository.CreateNewContact;
import com.vtiger.POMrepository.Home;

public class TC_03CreateContact extends BaseClass{
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
}

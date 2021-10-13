package com.Vtiger.comContacts.xml;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.crm.vtiger.genericUtils.FileUtility;
import com.crm.vtiger.genericUtils.JavaUtility;
import com.crm.vtiger.genericUtils.WebDriverUtility;

public class TC_4CreateContact{
	@Test
	public void createContact() throws Throwable{
	WebDriver driver = new ChromeDriver();
	FileUtility file = new FileUtility();
	WebDriverUtility wdu= new WebDriverUtility();
	
	//read data from property file
	String username = file.getPropertyKeyValue("username");
	String password = file.getPropertyKeyValue("password");
	String url = file.getPropertyKeyValue("url");
	wdu.waitUntilPageLoad(driver);
	driver.get(url);

	driver.findElement(By.name("user_name")).sendKeys(username);
	driver.findElement(By.name("user_password")).sendKeys(password);
	driver.findElement(By.id("submitButton")).click();

	driver.findElement(By.xpath("//a[.='Contacts']")).click();
	driver.findElement(By.xpath("//a/img[@alt='Create Contact...']")).click();
	driver.findElement(By.cssSelector("input[name='lastname']")).sendKeys("Q/J"+JavaUtility.getRandomData());
	
	driver.findElement(By.xpath("(//td/img[@title='Select'])[2]")).click();
	
	wdu.switchToWindow(driver, "Contacts&action");
	
	
	driver.findElement(By.id("search_txt")).sendKeys("qj");
	driver.findElement(By.name("search")).click();
	driver.findElement(By.xpath("//td[@class='lvtCol']/../../tr[2]/td/a")).click();

	wdu.switchToWindow(driver, "Creating New Contact");
	driver.findElement(By.name("button")).click();
	boolean check = driver.findElement(By.className("dvHeaderText")).isDisplayed();
	if (check==true) {
		System.out.println("is displayed");
	}
	else
		System.out.println("not displayed");
	
	//logging out

	WebElement logout = driver.findElement(By.xpath("(//td[@valign='bottom'])[2]"));
	wdu.mousehover(driver, logout);
	driver.findElement(By.xpath("//a[.='Sign Out']")).click();
	driver.close();

}
}

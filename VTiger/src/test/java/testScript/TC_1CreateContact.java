package testScript;

import java.awt.AWTException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.crm.vtiger.genericUtils.FileUtility;
import com.crm.vtiger.genericUtils.WebDriverUtility;

public class TC_1CreateContact {
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
		WebDriver driver = new FirefoxDriver();
		FileUtility file = new FileUtility();
		WebDriverUtility wbd= new WebDriverUtility();
//		
//		LoginToVtiger login=new LoginToVtiger(driver);
//		login.setUsernameEdt("admin");
//		login.setUserPwdEdt("admin");
//		login.setLoginBtn(loginBtn);
		
		//read data from property file
		String username = file.getPropertyKeyValue("username");
		String password = file.getPropertyKeyValue("password");
		String url = file.getPropertyKeyValue("url");
		wbd.waitUntilPageLoad(driver);
		driver.get(url);

		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();

		driver.findElement(By.xpath("//a[.='Contacts']")).click();
		driver.findElement(By.xpath("//a/img[@alt='Create Contact...']")).click();

		driver.findElement(By.xpath("//input[@type='submit']")).click();//for save
		wbd.acceptAlert(driver);
		
		//logging out

		WebElement logout = driver.findElement(By.xpath("(//td[@valign='bottom'])[2]"));
		wbd.mousehover(driver, logout);
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();
		driver.close();


}

}

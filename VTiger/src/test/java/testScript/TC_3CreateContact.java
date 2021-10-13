package testScript;

import java.awt.AWTException;
import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.crm.vtiger.genericUtils.FileUtility;
import com.crm.vtiger.genericUtils.JavaUtility;
import com.crm.vtiger.genericUtils.WebDriverUtility;

public class TC_3CreateContact {
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

		
		//logging out

		WebElement logout = driver.findElement(By.xpath("(//td[@valign='bottom'])[2]"));
		wdu.mousehover(driver, logout);
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();
		driver.close();
	}
}

package testScript;

import java.awt.AWTException;
import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.crm.vtiger.genericUtils.FileUtility;
import com.crm.vtiger.genericUtils.JavaUtility;
import com.crm.vtiger.genericUtils.WebDriverUtility;

public class TC_5CreateContact {
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
		
		driver.findElement(By.xpath("//img[@title='Import Contacts']")).click();
		
		File f= new File("./Data/Contacts1.csv");
		String path = f.getAbsolutePath();
		driver.findElement(By.id("import_file")).sendKeys(path);
		driver.findElement(By.name("next")).click();
		driver.findElement(By.name("import")).click();
		driver.findElement(By.name("cancel")).click();

		driver.findElement(By.className("txtBox")).sendKeys("CON23");
		driver.findElement(By.name("submit")).click();
		
		boolean name = driver.findElement(By.xpath("//a[contains(text(),'knn1212')]")).isDisplayed();
		if(name==true) {
			System.out.println("contact upload success");
			
		}
		else
			System.out.println("failed");
		
		String actual = "Administrator - Contacts - vtiger CRM 5 - Commercial Open Source CRM";
		String expected = driver.getTitle();
		System.out.println(expected);
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actual, expected);
		sa.assertAll();
		
		//logging out
		WebElement logout = driver.findElement(By.xpath("(//td[@valign='bottom'])[2]"));
		wdu.mousehover(driver, logout);
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();
		driver.close();
	}
}
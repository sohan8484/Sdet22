package testScript;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.vtiger.genericUtils.FileUtility;
import com.crm.vtiger.genericUtils.WebDriverUtility;

public class TC06 {
	WebDriver driver;
	WebDriverUtility wdu;
	@BeforeMethod
	public void login() throws IOException {
		driver= new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		FileUtility f=new FileUtility();
		String url = f.getPropertyKeyValue("url");
		System.out.println(url);
		String un = f.getPropertyKeyValue("username");
		String pwd = f.getPropertyKeyValue("password");
		driver.get(url);
		driver.findElement(By.name("user_name")).sendKeys(un);
		driver.findElement(By.name("user_password")).sendKeys(pwd+Keys.ENTER);
	}
	@Test
	public void searchOrg() {
		driver.findElement(By.xpath("//a[.='Contacts']")).click();
	}
	@AfterMethod
	public void logout(){
		new WebDriverUtility();
		WebElement logout = driver.findElement(By.xpath("(//td[@valign='bottom'])[2]"));
		wdu.mousehover(driver, logout);
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();
		driver.close();
	}
	@Test(priority=1)
	public void createContact() {
		
	}
}
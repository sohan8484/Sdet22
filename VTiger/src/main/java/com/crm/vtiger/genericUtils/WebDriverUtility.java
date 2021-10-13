package com.crm.vtiger.genericUtils;//webdriver actions used for all testscripts

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

public class WebDriverUtility {
	/*
	 * author @sohan
	 * page loading
	 * element to be visible
	 * element to be clicked
	 * handle dropdown using index
	 * handle dropdown using visible text
	 * perform mouse over action
	 * right click operation
	 * switch from one window to another
	 * accept alert
	 * dismiss alert
	 * scrolling action in webpage
	 * pressEnterKey
	 * importContacts
	 * 
	 */
	/**
	 * this mehtod wait for 20 sec for page loading
	 * @param driver
	 */
	public void waitUntilPageLoad(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	/**
	 * this mehod wait for the element to be visible
	 * @param driver
	 * @param element
	 */
	public void waitForElementVisiblity(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	/**
	 * this mehtod wait for the element to be clicked
	 * @param element
	 * @throws InterruptedException
	 */
	public void waitAndClick(WebElement element) throws InterruptedException {
		int count=0;
		while(count<40) {
			try {
				element.click();
			} catch (Throwable e) {
				Thread.sleep(2000);
				count++;
			}
		}
	}
	/**
	 * this methods enables user to handle dropdown using index
	 * @param element
	 * @param index
	 */
	public void SelectOption(WebElement element, int index) {
		Select s = new Select(element);
		s.selectByIndex(index);
	}

	/**
	 * this mehods enables user to handle dropdown using visible text
	 * @param element
	 * @param option
	 */
	public void SelectOptionText(WebElement element, String option) {
		Select s = new Select(element);
		s.selectByVisibleText(option);
	}
	
	public void SelectOptionValue(WebElement element, String option) {
		Select s = new Select(element);
		s.selectByValue(option);
	}

	/**
	 * this method will perform mouse over action
	 * @param driver
	 * @param element
	 */
	public void mousehover(WebDriver driver, WebElement element) {
		Actions act = new Actions(driver);
		act.moveToElement(element).perform();
	}

	/**
	 * this method performs right click operation
	 * @param driver
	 * @param element
	 */
	public void rightClick(WebDriver driver, WebElement element) {
		Actions act = new Actions(driver);
		act.contextClick(element).perform();
	}

	/**
	 * this method helps to switch from one window to another
	 * @param driver
	 * @param partialWinTitle
	 */

	public void switchToWindow(WebDriver driver,String partialWinTitle) {
		Set<String> window = driver.getWindowHandles();
		Iterator<String> it = window.iterator();
		while (it.hasNext()) {
			String winId = it.next();
			String title = driver.switchTo().window(winId).getTitle();
			if(title.contains(partialWinTitle)) {
				break;
			}

		}
	}
	
	/**
	 * accept alert
	 * @param driver
	 */
	public void acceptAlert(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	
	/**
	 * dismiss alert
	 * @param driver
	 */
	public void cancelAlert(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}
	
	/**
	 * this method used for scrolling action in webpage
	 * @param driver
	 * @param element
	 */
	public void moveToElement(WebDriver driver, WebElement element) {
		JavascriptExecutor js= (JavascriptExecutor)driver;
		int y = element.getLocation().getY();
		js.executeScript("window.scrollBy(0,"+y+")", element);
	}
	
	public void switchFrame(WebDriver driver,int index) {
		driver.switchTo().frame(index);
	}
	
	public void switchFrame(WebDriver driver, WebElement element) {
		driver.switchTo().frame(element);
	}
	
	public void switchFrame(WebDriver driver, String idOrName) {
		driver.switchTo().frame(idOrName);
	}
	
	public String takeScreenshot(WebDriver driver, String screenshotName) throws Throwable {
		String screenshotPath="./screenshot/"+screenshotName+JavaUtility.getCurrentDate()+".PNG";
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest= new File(screenshotPath);
		Files.copy(src, dest);
		return screenshotPath;
	}
	
	public void pressEnterKey() throws Throwable {
		Robot rc= new Robot();
		rc.keyPress(KeyEvent.VK_ENTER);
		rc.keyRelease(KeyEvent.VK_ENTER);
	}
	
	public void importContacts(WebDriver driver) {
		File f = new File(IPathConstants.FILEPATH);
		String Path= f.getAbsolutePath();
		driver.findElement(By.id("import_file")).sendKeys(Path);;
	}
}

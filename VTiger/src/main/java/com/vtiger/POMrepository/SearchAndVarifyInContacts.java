package com.vtiger.POMrepository;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.vtiger.genericUtils.ExcelUtility;
import com.crm.vtiger.genericUtils.WebDriverUtility;

public class SearchAndVarifyInContacts extends WebDriverUtility{
	WebDriverUtility wdu;
	WebDriver driver;
	public SearchAndVarifyInContacts(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(className = "txtBox")
	private WebElement textBox;


	public WebElement getTextBox() {
		return textBox;
	}

	public void varifyTheText(String orgName) throws Throwable {
		ExcelUtility elib= new ExcelUtility();
		String orgName1=elib.getExcelData("org", 1, 1);
		driver.findElement(By.className("txtBox")).sendKeys(orgName1);
		WebElement select = driver.findElement(By.id("bas_searchfield"));
		SelectOptionValue(select, "lastname");
		getTextBox().sendKeys(orgName1);
		driver.findElement(By.name("submit")).click();

		boolean name = driver.findElement(By.xpath("//table[@class='lvt small']/tbody/tr[3]/td[2]")).isDisplayed();
		if(name==true) {
			System.out.println("contact upload success");

		}
		else
			System.out.println("failed");
	}
}

//driver.findElement(By.className("txtBox")).sendKeys(orgName);
//WebElement select = driver.findElement(By.id("bas_searchfield"));
//wdu.SelectOptionValue(select, "lastname");
//driver.findElement(By.name("submit")).click();
//
//boolean name = driver.findElement(By.xpath("//a[.='Last Name']/../../../../../../../td/div/table/tbody/tr[3]")).isDisplayed();
//if(name==true) {
//	System.out.println("contact upload success");
//	
//}
//else
//	System.out.println("failed");
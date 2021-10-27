package com.vtiger.POMrepository;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.vtiger.genericUtils.WebDriverUtility;

public class CreateNewContact extends WebDriverUtility{
	WebDriver driver;
	public CreateNewContact(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	@FindBy(name="lastname")
	private WebElement contNameEdt;
	
	@FindBy(xpath="//input[@name='account_name']/following-sibling::img")
	private WebElement contNameLookUpIcon;

	@FindBy(xpath="//input[@name='contact_name']/following-sibling::img")
	private WebElement accNameLookUpIcon;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(xpath = "//img[@title='Import Contacts']")
	private WebElement importContact;
	
	public WebDriver getDriver() {
		return driver;
	}


	public WebElement getContNameEdt() {
		return contNameEdt;
	}


	public WebElement getContNameLookUpIcon() {
		return contNameLookUpIcon;
	}

	public WebElement getAccNameLookUpIcon() {
		return accNameLookUpIcon;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}


	public WebElement getImportContact() {
		return importContact;
	}


	public void createContactWithOrg(String contactName, String orgName) {
		contNameEdt.sendKeys(contactName);
		contNameLookUpIcon.click();
		
		switchToWindow(driver,"Accounts&action");
		
		Organizations op=new Organizations(driver);
		op.getSearchEdt().sendKeys(orgName);
		op.getSearchBtn().click();
		driver.findElement(By.xpath("//a[text()='" +orgName+ "']")).click();
		switchToWindow(driver,"Contacts&action");
		saveBtn.click();
	}

	public void createCont(String contName) {
		contNameEdt.sendKeys(contName);
		saveBtn.click();
	}
	public void createContct(String contName) {
		contNameEdt.sendKeys(contName);
	}
	
	

	
}

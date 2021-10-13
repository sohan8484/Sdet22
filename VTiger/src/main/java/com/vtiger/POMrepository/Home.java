package com.vtiger.POMrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.vtiger.genericUtils.WebDriverUtility;

public class Home extends WebDriverUtility{
	 WebDriver driver;
	 WebDriverUtility wdu;
	 
	public Home(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText="Contacts")
	private WebElement contactLinks;
	
	@FindBy(linkText = "Organizations")
	private WebElement organizationLinks;

	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
	private WebElement administratorImg;
	
	@FindBy(xpath="//input[@type='submit']")
	private WebElement save;
	
	@FindBy(xpath="//input[@name='account_name']/following-sibling::img")
	private WebElement contNameLookUpIcon;

	@FindBy(linkText = "Sign Out")
	private WebElement signOutLink;
	

	/*get methods*/
	public WebElement getContactLinks() {
		return contactLinks;
	}

	public WebDriverUtility getWdu() {
		return wdu;
	}

	public WebElement getSave() {
		return save;
	}
	
	public WebElement getContNameLookUpIcon() {
		return contNameLookUpIcon;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getAdministratorImg() {
		return administratorImg;
	}

	public WebElement getSignOutLink() {
		return signOutLink;
	}
	public WebElement getOrganizationLinks() {
		return organizationLinks;
	}

	public void logout() {
		Actions act = new Actions(driver);
		act.moveToElement(administratorImg).perform();
		signOutLink.click();
	}
}

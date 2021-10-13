package com.vtiger.POMrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateOrganization{
	public CreateOrganization(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}	
	@FindBy(linkText = "Organizations")
	private WebElement createOrg;

	public WebElement getCreateOrg() {
		return createOrg;
	}
	
	
}

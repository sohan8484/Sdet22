package com.vtiger.POMrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInfo {
	public ContactInfo(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement successfullMsg;
	
	@FindBy(xpath="//span[contains(text(),//span[.='[ CON12 ] q  -  Contact Information'])]")
	private WebElement headerName;
	
	public WebElement getHeaderName() {
		return headerName;
	}

	public WebElement getSuccessfullMsg() {
		return successfullMsg;
	}
}

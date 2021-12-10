package com.vtiger.POMrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Organizations {
	public Organizations(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	@FindBy(name = "accountname")
	private WebElement orgName;
	@FindBy(name="search")
	private WebElement searchBtn;

	
	public WebElement getOrgName() {
		return orgName;
	}
	@FindBy(xpath="//img[@src='themes/softed/images/btnL3Add.gif']")
	private WebElement createOrgImg;

	@FindBy(className = "txtBox")
	private WebElement searchEdt;

	@FindBy(name = "submit")
	private WebElement SearchBtn;



	public WebElement getSearchEdt() {
		return searchEdt;
	}

	public WebElement getSearchBtn() {
		return SearchBtn;
	}

	public WebElement getCreateOrgImg() {
		return createOrgImg;
	}


}

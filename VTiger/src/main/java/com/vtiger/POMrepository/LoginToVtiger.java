package com.vtiger.POMrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginToVtiger {
	public LoginToVtiger(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	@FindBy(name="user_name")
	private WebElement usernameEdt;

	@FindBy(name="user_password")
	private WebElement userPwdEdt;
	
	@FindAll({@FindBy(id="submitButton"),@FindBy(xpath = "//input[@id='submitButton']")})
	private WebElement loginBtn;

	public WebElement getUsernameEdt() {
		return usernameEdt;
	}


	public WebElement getUserPwdEdt() {
		return userPwdEdt;
	}


	public WebElement getLoginBtn() {
		return loginBtn;
	}


	public void loginToApp(String username, String password) {
		usernameEdt.sendKeys(username);
		userPwdEdt.sendKeys(password);
		loginBtn.click();
	}
}

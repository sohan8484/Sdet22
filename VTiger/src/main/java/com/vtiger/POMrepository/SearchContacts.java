package com.vtiger.POMrepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class SearchContacts {
WebDriver driver;
public SearchContacts(WebDriver driver) {
	this.driver=driver;
	PageFactory.initElements(driver, this);
}
public void selectContactFromList(String orgName) {
	driver.findElement(By.id("search_txt")).sendKeys(orgName);
	driver.findElement(By.name("search")).click();
	driver.findElement(By.xpath("//td[@class='lvtCol']/../../tr[2]/td/a")).click();
}
}

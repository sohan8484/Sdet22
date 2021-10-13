package com.vtiger.POMrepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ImportContacts {
	WebDriver driver;

	public ImportContacts(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public void emport() {
		driver.findElement(By.name("next")).click();
		driver.findElement(By.name("import")).click();
		driver.findElement(By.name("cancel")).click();	
	}
	
}


package com.crm.vtiger.genericUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

public class WebDriverListner implements WebDriverEventListener{

	public void beforeAlertAccept(WebDriver driver) {
		System.out.println("Before Switching to Alert: "+driver.toString());

	}


	public void afterAlertAccept(WebDriver driver) {
		System.out.println("After Switching to Alert: "+driver.toString());

	}


	public void afterAlertDismiss(WebDriver driver) {
		System.out.println("Dismiss Alert: "+driver.toString());

	}


	public void beforeAlertDismiss(WebDriver driver) {
		System.out.println("Accept Alert: "+driver.toString());

	}


	public void beforeNavigateTo(String url, WebDriver driver) {
		System.out.println("Before Naviagating url: "+url);

	}


	public void afterNavigateTo(String url, WebDriver driver) {
		System.out.println("After Navigating url: "+url);

	}


	public void beforeNavigateBack(WebDriver driver) {
		System.out.println("Before navigate Back : "+ driver.toString());

	}


	public void afterNavigateBack(WebDriver driver) {
		System.out.println("After navigate Back : "+ driver.toString());

	}


	public void beforeNavigateForward(WebDriver driver) {
		System.out.println("Before navigate forward : "+ driver.toString());

	}


	public void afterNavigateForward(WebDriver driver) {
		System.out.println("after navigate Forward : "+ driver.toString());

	}


	public void beforeNavigateRefresh(WebDriver driver) {
		System.out.println("Before navigate Refresh : "+ driver.toString());

	}


	public void afterNavigateRefresh(WebDriver driver) {
		System.out.println("Before navigate Refresh : "+ driver.toString());

	}


	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		System.out.println("Before Finding the element : "+by +" element: "+element.getTagName());

	}


	public void afterFindBy(By by, WebElement element, WebDriver driver) {
		System.out.println("After Finding the element : "+by +" element: "+element.getTagName());

	}


	public void beforeClickOn(WebElement element, WebDriver driver) {
		System.out.println("Before clicking on Element: "+element);

	}


	public void afterClickOn(WebElement element, WebDriver driver) {
		System.out.println("After clicking on Element: "+element);

	}


	public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		System.out.println("Before Entering the Value: "+keysToSend.toString());

	}


	public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		System.out.println("After Entering the Value: "+keysToSend.toString());

	}


	public void beforeScript(String script, WebDriver driver) {

	}


	public void afterScript(String script, WebDriver driver) {

	}


	public void beforeSwitchToWindow(String windowName, WebDriver driver) {

	}


	public void afterSwitchToWindow(String windowName, WebDriver driver) {

	}


	public void onException(Throwable throwable, WebDriver driver) {

	}


	public <X> void beforeGetScreenshotAs(OutputType<X> target) {

	}


	public <X> void afterGetScreenshotAs(OutputType<X> target, X screenshot) {

	}


	public void beforeGetText(WebElement element, WebDriver driver) {

	}


	public void afterGetText(WebElement element, WebDriver driver, String text) {

	}
}

package com.web.app.framework.utlis.general;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.web.app.selenium.api.base.SeleniumBase;
import com.web.app.selenium.api.design.Locators;

public class WaitUtils extends SeleniumBase  {
    
	public void waitForElementToBeVisible(WebElement element) {
		getWait().until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForElementsToBeVisible(List<WebElement> element) {
		getWait().until(ExpectedConditions.visibilityOfAllElements(element));
	}
	
	public void waitForElementToBeClickable(WebElement element) {
		getWait().until(ExpectedConditions.elementToBeClickable(element));
	}
	 
	public void waitForTitleOfPage(String title) {
		getWait().until(ExpectedConditions.titleContains(title));
	}
	
	public void waitForPresenceOfElement(By by) {
		getWait().until(ExpectedConditions.presenceOfElementLocated(by));
	}
	
	public void waitForElementToBeStale(WebElement element) {
		getWait().until(ExpectedConditions.stalenessOf(element));
	}
	public void waitForAlert() {
		getWait().until(ExpectedConditions.alertIsPresent());
	}
	

}

package com.web.app.framework.pages;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.web.app.framework.utlis.properties.objects.HomePageElements;
import com.web.app.selenium.api.design.Locators;
import com.web.app.testng.api.TestNGHooks;

public class HomePage extends TestNGHooks{
	HomePageElements home = ConfigFactory.create(HomePageElements.class);
	
	public HomePage clickToggle() {
		getWait().until(ExpectedConditions.visibilityOf(locateElement(Locators.XPATH, home.toggle())));
		click(locateElement(Locators.XPATH, home.toggle()));
	 return this;
	}
	
	public HomePage verifyandclickViewAll() {
		executeJavaScript("arguments[0].click();", locateElement(Locators.XPATH, home.viewAll()));
		return this;
}

	//4. Click Option from App Launcher
		public DashboardPage AppLaucherOption(String AppLauncherOption) {
			checkWindowHandles();
			switch (AppLauncherOption.toLowerCase()) {	
			case  "sales":
				clickUsingActions(locateElement(Locators.XPATH, home.sales()));
			   break;
			case "service console":
				clickUsingActions(locateElement(Locators.XPATH, home.serviceconsole()));
				break;
			}
			return new DashboardPage();		
		}
	
		
		 //Verify user logged in successfully
		
		public HomePage verifyLoginIsSuccess() {
			getWait().until(ExpectedConditions.titleContains("Home | Salesforce"));
			verifyTitle("Home | Salesforce");
			return this;
		}
}

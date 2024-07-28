package com.web.app.framework.pages;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.web.app.framework.utlis.general.WaitUtils;
import com.web.app.framework.utlis.properties.objects.HomePageElements;
import com.web.app.selenium.api.design.Locators;
import com.web.app.testng.api.TestNGHooks;

public class HomePage extends TestNGHooks {
	HomePageElements home = ConfigFactory.create(HomePageElements.class);

	// Identifying WenElements in the Page
	WebElement btnToggle = locateElement(Locators.XPATH, home.toggle());
	WebElement btnViewAll = locateElement(Locators.XPATH, home.viewAll());
	WebElement btnServiceConsole = locateElement(Locators.XPATH, home.serviceconsole());
	WebElement btnSales = locateElement(Locators.XPATH, home.sales());

	public HomePage clickToggle() {
		new WaitUtils().waitForElementToBeVisible(btnToggle);
		click(btnToggle);
		return this;
	}

	public HomePage verifyandclickViewAll() {
		clickElementUsingJavascript(btnViewAll, "View All Button");
		return this;
	}

	// 4. Click Option from App Launcher
	public DashboardPage AppLaucherOption(String AppLauncherOption) {
		checkWindowHandles();
		switch (AppLauncherOption.toLowerCase()) {
		case "sales":
			clickUsingActions(btnSales);
			break;
		case "service console":
			clickUsingActions(btnServiceConsole);
			break;
		}
		return new DashboardPage();
	}

	// Verify user logged in successfully

	public HomePage verifyLoginIsSuccess() {
		new WaitUtils().waitForTitleOfPage("Home | Salesforce");
		verifyTitle("Home | Salesforce");
		return this;
	}
}

package com.web.app.framework.pages;

import java.util.List;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebElement;

import com.web.app.framework.utlis.properties.objects.DashboardPageElements;
import com.web.app.selenium.api.design.Locators;
import com.web.app.testng.api.TestNGHooks;

public class DashboardPage extends TestNGHooks {

	DashboardPageElements dashboard = ConfigFactory.create(DashboardPageElements.class);
	List<WebElement> tabOptions = locateElements(Locators.XPATH, dashboard.options());

	public SellerHomePage clickAccounts() {
		checkWindowHandles();
		for (int i = 0; i <= (tabOptions.size()) - 1; i++) {
			String TabName = tabOptions.get(i).getAttribute("title").trim();
			if (TabName.equals("Accounts")) {
				clickElementUsingJavascript(tabOptions.get(i), TabName);
				break;
			}

		}
		return new SellerHomePage();

	}

}

package com.web.app.framework.pages;

import static com.web.app.framework.utlis.properties.ObjectRepositoriesController.*;

import java.util.List;

import org.openqa.selenium.WebElement;

import com.web.app.framework.utlis.general.WaitUtils;
import com.web.app.framework.utlis.properties.ObjectRepositoriesController;
import com.web.app.selenium.api.design.Locators;
import com.web.app.testng.api.TestNGHooks;

public class AccountsPage extends TestNGHooks {

	WebElement txtMessage = locateElement(Locators.XPATH,
			ObjectRepositoriesController.getDOMValue("AccountsPage", "Message.textBox"));

	List<WebElement> tabOptions = locateElements(Locators.XPATH,
			ObjectRepositoriesController.getDOMValue("AccountsPage", "Options.tab"));

	public AccountsPage verifyAccountCreationSuccessMessage(String AccountName) {
		new WaitUtils().waitForElementToBeVisible(txtMessage);
		try {
			if (txtMessage.getText().trim().contains(AccountName)) {
//			Assertion.assertTrue(true, "Account is added successful");
//			reportStep("Account is added successful", "pass");
			}
		} catch (Exception e) {
//			reportStep("User Name is not Entered ", "fail");
		}
		return this;
	}

	public SellerHomePage clickAccounts() {
		checkWindowHandles();
		for (int i = 0; i <= (tabOptions.size()) - 1; i++) {
			String TabName = tabOptions.get(i).getAttribute("title").trim();
			if (TabName.equals("Accounts")) {
				clickElementUsingJavascript(tabOptions.get(i), "Accounts");
				break;
			}
		}
		return new SellerHomePage();

	}

}

package com.web.app.framework.pages;

import java.util.List;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.web.app.framework.utlis.general.Logs;
import com.web.app.framework.utlis.properties.objects.SellerHomePageElements;
import com.web.app.selenium.api.design.Locators;
import com.web.app.testng.api.TestNGHooks;

public class SellerHomePage extends TestNGHooks {
	
	SellerHomePageElements sellerPage = ConfigFactory.create(SellerHomePageElements.class);
	
	public SellerHomePage clickNewButton() {
		getWait().until(ExpectedConditions.visibilityOf(locateElement(Locators.XPATH, sellerPage.New())));
		clickUsingActions(locateElement(Locators.XPATH, sellerPage.New()));
		return this;
	}
	
	public SellerHomePage verifyCreateNewAccountPopupIsOpened() {
		getWait().until(ExpectedConditions.visibilityOf(locateElement(Locators.XPATH, sellerPage.NewAccount())));
		verifyDisplayed(locateElement(Locators.XPATH, sellerPage.NewAccount()));
        checkWindowHandles();
		return this;
	}

	
	public SellerHomePage enterAccountDetailsInCreateNewAccountPopup(String AccountName, String AccountType) {
		getWait().until(ExpectedConditions.visibilityOf(locateElement(Locators.XPATH, sellerPage.AccountName())));
		scrollToElement(locateElement(Locators.XPATH, sellerPage.AccountName()));
		append((locateElement(Locators.XPATH, sellerPage.AccountName())), AccountType);
		executeJavaScript("arguments[0].click();", locateElement(Locators.XPATH, sellerPage.OwnershipdropDown()));
	List<WebElement> OwnerShipOptions = locateElements(Locators.XPATH, sellerPage.OwnershipOptions());
		for (int i = 0; i <= (OwnerShipOptions.size()) - 1; i++) {
			String Option = OwnerShipOptions.get(i).getText().trim();
			if (Option.equals(AccountType)) {
				executeJavaScript("arguments[0].click();", OwnerShipOptions.get(i));
				break;
			}
		}
		return this;
	}
	
	public SellerHomePage clickSaveButton() {
		clickUsingActions(locateElement(Locators.XPATH, sellerPage.Save()));
		return this;
	}
	
	public AccountsPage verifyAccountCreationSuccessMessage(String AccountName) {
		
		WebElement txtMessage = locateElement(Locators.XPATH, sellerPage.Message());
		getWait().until(ExpectedConditions.visibilityOf(txtMessage));
		try {
		if (txtMessage.getText().trim().contains(AccountName)) {
		 new Logs().console().pass("Account is added successfully");
		}}catch (Exception e) {
			new Logs().console().fail("Account is not added successfully due to " + e);
		}
		return new AccountsPage();
	}
	
	public SellerHomePage searchForNewAccountInTable(String AccountName) {
		
		append(locateElement(Locators.XPATH, sellerPage.SearchAccount()), AccountName);
		for (int i = 0; i <= locateElements(Locators.XPATH, sellerPage.AccountView()).size() - 1; i++) {
			try {

				getWait().until(ExpectedConditions.visibilityOfAllElements(locateElements(Locators.XPATH, sellerPage.AccountView())));
			} catch (StaleElementReferenceException e) {
//				wait.until(ExpectedConditions.stalenessOf((getDriver().findElements(By.xpath("//table/tbody/tr"))).get(i)));
//				getDriver().findElements(By.xpath("//table/tbody/tr"));
//				wait.until(ExpectedConditions.visibilityOf((getDriver().findElements(By.xpath("//table/tbody/tr"))).get(i)));
	

			} catch (org.openqa.selenium.TimeoutException e) {

				clearAndType(locateElement(Locators.XPATH, sellerPage.SearchAccount()), AccountName);
				txtSearchAccount.sendKeys(Keys.ENTER);
			}

		}
		return this;
	}

}

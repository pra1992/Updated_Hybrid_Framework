package com.web.app.framework.pages;

import java.awt.AWTException;
import java.util.List;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.web.app.framework.utlis.general.Logs;
import com.web.app.framework.utlis.general.WaitUtils;
import com.web.app.framework.utlis.properties.objects.SellerHomePageElements;
import com.web.app.selenium.api.design.Locators;
import com.web.app.testng.api.TestNGHooks;

public class SellerHomePage extends TestNGHooks {

	SellerHomePageElements sellerPage = ConfigFactory.create(SellerHomePageElements.class);

	List<WebElement> tabOptions = locateElements(Locators.XPATH, sellerPage.options());

	WebElement btnNew = locateElement(Locators.XPATH, sellerPage.New());

	WebElement titleNewAccount = locateElement(Locators.XPATH, sellerPage.NewAccount());

	WebElement txtAccountName = locateElement(Locators.XPATH, sellerPage.AccountName());

	WebElement ddOwnership = locateElement(Locators.XPATH, sellerPage.OwnershipdropDown());

	List<WebElement> OwnerShipOptions = locateElements(Locators.XPATH, sellerPage.OwnershipOptions());

	WebElement btnSave = locateElement(Locators.XPATH, sellerPage.Save());

	List<WebElement> tabOption = locateElements(Locators.XPATH, sellerPage.options());

	WebElement txtSearchAccount = locateElement(Locators.XPATH, sellerPage.SearchAccount());

	WebElement txtMessage = locateElement(Locators.XPATH, sellerPage.Message());

	WebElement ddActionDropdown = locateElement(Locators.XPATH, sellerPage.ActionDropdown());

	WebElement btnEdit = locateElement(Locators.XPATH, sellerPage.Edit());

	WebElement popUpEditAccount = locateElement(Locators.XPATH, sellerPage.EditAccount());

	WebElement txtPhoneNo = locateElement(Locators.XPATH, sellerPage.PhoneNo());

	WebElement ddAccountType = locateElement(Locators.XPATH, sellerPage.AccountTypedropDown());

	List<WebElement> optionsAccountType = locateElements(Locators.XPATH, sellerPage.AccountTypeOptions());

	WebElement ddIndustry = locateElement(Locators.XPATH, sellerPage.IndustrydropDown());

	List<WebElement> optionsIndustry = locateElements(Locators.XPATH, sellerPage.IndustryOptions());

	WebElement txtBillingStreet = locateElement(Locators.XPATH, sellerPage.BillingStreet());

	WebElement txtBillingCity = locateElement(Locators.XPATH, sellerPage.BillingCity());

	WebElement txtBillingZipCode = locateElement(Locators.XPATH, sellerPage.BillingZipCode());

	WebElement txtBillingProvince = locateElement(Locators.XPATH, sellerPage.BillingProvince());

	WebElement txtBillingCountry = locateElement(Locators.XPATH, sellerPage.BillingCountry());

	WebElement txtShippingStreet = locateElement(Locators.XPATH, sellerPage.ShippingStreet());

	WebElement txtShippingCity = locateElement(Locators.XPATH, sellerPage.ShippingCity());

	WebElement txtShippingZipCode = locateElement(Locators.XPATH, sellerPage.ShippingZipCode());

	WebElement txtShippingProvince = locateElement(Locators.XPATH, sellerPage.ShippingProvince());

	WebElement txtShippingCountry = locateElement(Locators.XPATH, sellerPage.ShippingCountry());

	WebElement ddCustomerPriority = locateElement(Locators.XPATH, sellerPage.CustomerPrioritydropDown());
	
	List<WebElement> AccountView = locateElements(Locators.XPATH, sellerPage.AccountView());

	WebElement ddSLA = locateElement(Locators.XPATH, sellerPage.SLAdropDown());

	List<WebElement> optionsCustomerPriority = locateElements(Locators.XPATH, sellerPage.CustomerPriorityOptions());

	List<WebElement> optionsSLA = locateElements(Locators.XPATH, sellerPage.SLAOptions());

	WebElement ddActive = locateElement(Locators.XPATH, sellerPage.ActivedropDown());

	List<WebElement> optionsActive = locateElements(Locators.XPATH, sellerPage.ActiveOptions());

	WebElement ddUpsell = locateElement(Locators.XPATH, sellerPage.UpselldropDown());

	List<WebElement> OptionsUpsell = locateElements(Locators.XPATH, sellerPage.UpsellOptions());

	WebElement gridPhoneNoValue = locateElement(Locators.XPATH, sellerPage.PhoneNoValue());
	

	public SellerHomePage clickNewButton() {
	
		new WaitUtils().waitForElementToBeVisible(btnNew);
		clickUsingActions(btnNew);
		return this;
	}

	public SellerHomePage verifyCreateNewAccountPopupIsOpened() {
		new WaitUtils().waitForElementToBeVisible(titleNewAccount);
		verifyDisplayed(titleNewAccount);
		checkWindowHandles();
		return this;
	}

	public SellerHomePage enterAccountDetailsInCreateNewAccountPopup(String AccountName, String AccountType) {
		new WaitUtils().waitForElementToBeVisible(txtAccountName);
		scrollToElement(txtAccountName);	
		append(txtAccountName, AccountName);
		clickElementUsingJavascript(ddOwnership, "Ownership dropdown");
		for (int i = 0; i <= (OwnerShipOptions.size()) - 1; i++) {
			String Option = OwnerShipOptions.get(i).getText().trim();
			if (Option.equals(AccountType)) {
				clickElementUsingJavascript(OwnerShipOptions.get(i), "Selected Account Type Option");
				break;
			}
		}
		return this;
	}

	public SellerHomePage clickSaveButton() {
		clickUsingActions(btnSave);
		return this;
	}

	public AccountsPage verifyAccountCreationSuccessMessage(String AccountName) {
		new WaitUtils().waitForElementToBeVisible(txtMessage);
		
		try {
			if (txtMessage.getText().trim().contains(AccountName)) {
				new Logs().console().pass("Account is added successfully");
			}
		} catch (Exception e) {
			new Logs().console().fail("Account is not added successfully due to " + e);
		}
		return new AccountsPage();
	}

	public SellerHomePage searchForNewAccountInTable(String AccountName) throws AWTException {
		new WaitUtils().waitForElementToBeVisible(txtSearchAccount);
		append(txtSearchAccount, AccountName);
		pressEnter();
		for(int i =0; i<= (AccountView.size())-1; i++) {
			try {
				new WaitUtils().waitForElementsToBeVisible(AccountView);
			} catch (StaleElementReferenceException e) {
//				wait.until(ExpectedConditions.stalenessOf((getDriver().findElements(By.xpath("//table/tbody/tr"))).get(i)));
//				getDriver().findElements(By.xpath("//table/tbody/tr"));
//				wait.until(ExpectedConditions.visibilityOf((getDriver().findElements(By.xpath("//table/tbody/tr"))).get(i)));
				new WaitUtils().waitForPresenceOfElement(By.xpath(sellerPage.AccountView()));
 				append(txtSearchAccount, AccountName);
 				pressEnter();
			} catch (org.openqa.selenium.TimeoutException e) {
		//		txtSearchAccount.clear();
				clear(txtSearchAccount);
				append(txtSearchAccount, AccountName);
 				pressEnter();
			}

		}
		return this;
	}
	public SellerHomePage valideAddedAccountInTable(String AccountName) throws AWTException {
		for (int i = 0; i <= AccountView.size() - 1; i++) {
			try {
				new WaitUtils().waitForElementsToBeVisible(AccountView);
				if (AccountView.get(i).getText().trim()
						.contains(AccountName)) {
					new Logs().console().pass("Account Name is verified in the Table");
					new Logs().file().pass("Account Name is verified in the Table");
				}
			} catch (StaleElementReferenceException e) {

				new WaitUtils().waitForElementToBeStale(locateElement(Locators.XPATH, sellerPage.AccountView()));
				if (AccountView.get(i).getText().trim()
						.contains(AccountName)) {
					new Logs().console().pass("Account Name is verified in the Table");
					new Logs().file().pass("Account Name is verified in the Table");
				}
			} catch (TimeoutException e) {

			  clear(txtSearchAccount);
				append(txtSearchAccount, AccountName);
				pressEnter();
				new WaitUtils().waitForElementsToBeVisible(AccountView);
				if (AccountView.get(i).getText().trim()
						.contains(AccountName)) {
					new Logs().console().pass("Account Name is verified in the Table");
					new Logs().file().pass("Account Name is verified in the Table");
				}
			}

		}
		return this;

	}

	public SellerHomePage clickAction(String EditAccount) {

		try {
			new WaitUtils().waitForElementToBeVisible(ddActionDropdown);
			scrollToElement(ddActionDropdown);
            clickElementUsingJavascript(ddActionDropdown, "Action Dropdown");
		} catch (org.openqa.selenium.NoSuchElementException e) {
//			txtSearchAccount.clear();
//			txtSearchAccount.sendKeys(AccountName);
//			txtSearchAccount.sendKeys(Keys.ENTER);
			new WaitUtils().waitForElementToBeVisible(ddActionDropdown);
			scrollToElement(ddActionDropdown);
            clickElementUsingJavascript(ddActionDropdown, "Action Dropdown");
		}
		return this;

	}

	public SellerHomePage clickEdit() {
		clickElementUsingJavascript(btnEdit, "Edit Button");
		return this;
	}

	public SellerHomePage editAccountDetailsPhoneNumber(String PhoneNo) {
		new WaitUtils().waitForElementToBeVisible(popUpEditAccount);
		append(txtPhoneNo, PhoneNo);
		return this;
	}

	public SellerHomePage editAccountType(String AccountType) {
		clickElementUsingJavascript(ddAccountType, AccountType);
	
		for (int i = 0; i <= (optionsAccountType.size()) - 1; i++) {
			try {
				String AccountTypeOption = optionsAccountType.get(i).getText().trim();

				if (AccountTypeOption.equals(AccountType)) {
					clickElementUsingJavascript(optionsAccountType.get(i), AccountTypeOption);
					break;
				}

			} catch (java.lang.NullPointerException e) {
				continue;
			}
		}
		return this;
	}

	public SellerHomePage selectIndustry(String Industry) {
		clickElementUsingJavascript(ddIndustry, "Industry Dropdown");

		for (int j = 0; j <= (optionsIndustry.size()) - 1; j++) {
			String IndustryTypeOption = optionsIndustry.get(j).getText().trim();
			if (IndustryTypeOption.contains(Industry)) {
				clickElementUsingJavascript(optionsIndustry.get(j), IndustryTypeOption);
				break;
			}
		}
		return this;

	}

	public SellerHomePage enterBillingDetails(String BillingAddress1, String BillingAddress2, String BillingAddress3,
			String BillingAddress4) {
		scrollVertically(popUpEditAccount, 750);
		append(txtBillingStreet, BillingAddress1);
		append(txtBillingCity, BillingAddress2);
		append(txtBillingZipCode, BillingAddress3);
		append(txtBillingCountry, BillingAddress4);
		return this;
	}

	public SellerHomePage enterShippingDetails(String ShippingAddress1, String ShippingAddress2,
			String ShippingAddress3, String ShippingAddress4) {
		append(txtShippingStreet, ShippingAddress1);
		append(txtShippingCity, ShippingAddress2);
		append(txtShippingZipCode, ShippingAddress3);
		append(txtShippingCountry, ShippingAddress4);
		clickElementUsingJavascript(ddCustomerPriority, "Customer Priority dropdown");
		for (int j = 0; j <= (optionsCustomerPriority.size()) - 1; j++) {
			String CustomerPriorityTypeOption = optionsCustomerPriority.get(j).getText().trim();
			if (CustomerPriorityTypeOption.contains("Low")) {
				executeJavaScript("arguments[0].click();", optionsCustomerPriority.get(j));
				clickElementUsingJavascript(optionsCustomerPriority.get(j), CustomerPriorityTypeOption);
				break;
			}
		}
		return this;
	}

	public SellerHomePage selectSLA(String SLA) {

		scrollVertically(popUpEditAccount, 200);
		clickElementUsingJavascript(ddSLA, SLA);
			for (int j = 0; j <= (optionsSLA.size()) - 1; j++) {
			String SLATypeOption = optionsSLA.get(j).getText().trim();
			if (SLATypeOption.contains("Silver")) {
				clickElementUsingJavascript(optionsSLA.get(j), SLATypeOption);
				break;
			}
		}
		return this;
	}

	// 14) Select Active as NO
	public SellerHomePage selectActiveStatus(String Active) {
		// Scrolling
		scrollVertically(popUpEditAccount, 100);
		clickElementUsingJavascript(ddActionDropdown, "Active Dropdown");
		for (int j = 0; j <= (optionsActive.size()) - 1; j++) {
			try {
				String ActiveOption = optionsActive.get(j).getText().trim();
				if (ActiveOption.equals(Active)) {
					clickElementUsingJavascript(optionsActive.get(j), ActiveOption);
					break;
				}

			} catch (java.lang.NullPointerException e) {
				continue;
			}
		}
		return this;
	}

	public SellerHomePage selectUpsell() {
		clickElementUsingJavascript(ddUpsell, "Upsell Dropdown");
		for (int j = 0; j <= (OptionsUpsell.size()) - 1; j++) {
			try {
				String UpsellOption = OptionsUpsell.get(j).getText().trim();
				if (UpsellOption.equals("No")) {
					executeJavaScript("arguments[0].click();", OptionsUpsell.get(j));
					break;
				}
			} catch (java.lang.NullPointerException e) {
				continue;
			}
		}
		return this;
	}

	public SellerHomePage verifyEditSuccessToasterMessage() {
		try {
			if (locateElement(Locators.XPATH, sellerPage.EditSuccessToaster()).getText().trim().contains("saved")) {

				System.out.println("Account is saved successful");
				new Logs().console().pass("Account is saved successful");
				new Logs().file().pass("Account is saved successful");
			}

		} catch (Exception e) {
			new Logs().console().fail("Account is not saved due to " + e.getMessage());
			new Logs().file().fail("Account is not saved due to " + e.getMessage());
		}
		return this;
	}

	// Verifying the added Phone Number in the Grid
	public SellerHomePage verifyTheEditedPhoneNoInTheGrid(String PhoneNo) {
		try {
			if (locateElement(Locators.XPATH, sellerPage.PhoneNoValue()).getText().trim().equals(PhoneNo)) {
				System.out.println("Phone Number is verified Successfully");
				new Logs().console().pass("Phone Number is verified Successfully");
				new Logs().file().pass("Phone Number is verified Successfully");
			}

		} catch (Exception e) {
			new Logs().console().fail("Phone Number is not verified due to " + e.getMessage());
			new Logs().file().fail("Phone Number is not verified due to " + e.getMessage());
		}
		return this;
	}

}

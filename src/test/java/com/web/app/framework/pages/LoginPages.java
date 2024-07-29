package com.web.app.framework.pages;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebElement;

import static com.web.app.framework.utlis.properties.ObjectRepositoriesController.*;
import com.web.app.framework.utlis.properties.objects.LoginPageElements;
import com.web.app.selenium.api.design.Locators;
import com.web.app.testng.api.TestNGHooks;

public class LoginPages extends TestNGHooks {

	LoginPageElements loginpage = ConfigFactory.create(LoginPageElements.class);

	WebElement txtUserName = locateElement(Locators.XPATH, getDOMValue("LoginPage", "username.field"));

	WebElement txtPassword = locateElement(Locators.XPATH, getDOMValue("LoginPage", "password.field"));

	WebElement btnLogin = locateElement(Locators.XPATH, getDOMValue("LoginPage", "password.field"));

	public LoginPages enterUserName(String uname) {
		append(txtUserName, uname);
		return this;
	}

	public LoginPages enterPassword(String pword) {
		append(txtPassword, pword);
		return this;
	}

	public HomePage clickLoginButton() {
		click(btnLogin);
		return new HomePage();
	}

}
package com.web.app.framework.pages;

import org.aeonbits.owner.ConfigFactory;

import com.web.app.framework.utlis.properties.objects.LoginPageElements;
import com.web.app.selenium.api.design.Locators;
import com.web.app.testng.api.TestNGHooks;

public class LoginPages extends TestNGHooks {
	
	LoginPageElements loginpage = ConfigFactory.create(LoginPageElements.class);
	
	public LoginPages enterUserName(String  uname) {
		append(locateElement(Locators.XPATH, loginpage.username()), uname);
		return this;
	}
	
	public LoginPages enterPassword(String pword) {
		append(locateElement(Locators.XPATH, loginpage.username()), pword);
		return this;
	}
	
	public HomePage clickLoginButton() {
		click(locateElement(Locators.XPATH, loginpage.loginButton()));
		return new HomePage();
	}

}
package com.web.app.selenium.api.base;

import java.time.Duration;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.web.app.framework.utlis.general.Logs;
import com.web.app.framework.utlis.properties.ConfigPropertiesHandler;

public class DriverInstance {
	
	private static final ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<RemoteWebDriver>();
	private static final ThreadLocal<WebDriverWait> wait = new  ThreadLocal<WebDriverWait>();
	private static final ConfigPropertiesHandler config = ConfigFactory.create(ConfigPropertiesHandler.class);
	
	public void setWait() {
		wait.set(new WebDriverWait(getDriver(), Duration.ofSeconds(config.getExplicitWaitTime())));
	}

	public WebDriverWait getWait() {
		return wait.get();
	}

	public void setDriver(String browser, boolean headless) {		
		switch (browser) {
		case "chrome":
			ChromeOptions options = new ChromeOptions();
			if(headless) { options.addArguments("--headless=new"); }
			options.addArguments("--start-maximized"); 
			options.addArguments("--disable-notifications"); 
			options.addArguments("--incognito");
			driver.set(new ChromeDriver(options));
			getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(config.getImplicitWaitTime()));
			new Logs().console().pass("Successfully launched CHROME browser in the local machine.");
			new Logs().file().pass("Successfully launched CHROME browser in the local machine.");
			break;
		case "firefox":			
			break;
		case "edge":
		default:
			new Logs().console().warning("Currently web app framework supports CHROME, FIREFOX and EDGE browsers.");
			new Logs().file().warning("Currently web app framework supports CHROME, FIREFOX and EDGE browsers.");
			break;
		}
	}
	
	public RemoteWebDriver getDriver() {
		return driver.get();
	}

}
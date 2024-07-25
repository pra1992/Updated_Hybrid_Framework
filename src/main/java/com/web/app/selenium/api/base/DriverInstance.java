package com.web.app.selenium.api.base;

import java.time.Duration;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.web.app.framework.utlis.general.Logs;
import com.web.app.framework.utlis.properties.ConfigPropertiesHandler;

import org.openqa.selenium.remote.NoSuchDriverException;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.safari.ConnectionClosedException;
import org.openqa.selenium.*;

public class DriverInstance {
	
	private static final ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<RemoteWebDriver>();
	private static final ThreadLocal<WebDriverWait> wait = new  ThreadLocal<WebDriverWait>();
	private static final ConfigPropertiesHandler config = ConfigFactory.create(ConfigPropertiesHandler.class);
	

	public void setWait() {
		new WebDriverWait(getDriver(), Duration.ofSeconds(config.getExplicitWaitTime()));
	}
	
	public WebDriverWait getWait() {
		return wait.get();
		
	}


	public void setDriver(String browser, boolean headless) throws SessionNotCreatedException, ConnectionClosedException, UnreachableBrowserException, NoSuchDriverException, Exception{		
		switch (browser) {
		case "chrome":
			ChromeOptions chromeOptions = new ChromeOptions();
			if(headless) { chromeOptions.addArguments("--headless=new"); }
			chromeOptions.addArguments(config.getChromiumCliSwitches());
			driver.set(new ChromeDriver(chromeOptions));//setting the RemoteWebDriber to ThreadLocal, mandatory for ThreadLocal Implementation since it needs to be Private
			getDriver().manage().window().maximize();
			new Logs().console().pass("Successfully launched CHROME browser in the local machine.");
			new Logs().file().pass("Successfully launched CHROME browser in the local machine.");
			break;
		case "firefox":
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			if(headless) { firefoxOptions.addArguments("-headless"); }
			firefoxOptions.addArguments(config.getFriefoxCliOptions());
			firefoxOptions.addPreference("dom.webnotifications.enabled", false);
			driver.set(new FirefoxDriver(firefoxOptions));
			getDriver().manage().window().maximize();
			new Logs().console().pass("Successfully launched FIREFOX browser in the local machine.");
			new Logs().file().pass("Successfully launched FIREFOX browser in the local machine.");
			break;
		case "edge":
			EdgeOptions edgeOptions = new EdgeOptions();
			if(headless) { edgeOptions.addArguments("--headless=new"); }
			edgeOptions.addArguments(config.getChromiumCliSwitches());
			driver.set(new EdgeDriver(edgeOptions));
			new Logs().console().pass("Successfully launched EDGE browser in the local machine.");
			new Logs().file().pass("Successfully launched EDGE browser in the local machine.");
			break;
		default:
			new Logs().console().warning("Currently web app framework supports CHROME, FIREFOX and EDGE browsers.");
			new Logs().file().warning("Currently web app framework supports CHROME, FIREFOX and EDGE browsers.");
			break;
		}
	}
	
	//setDriver Method for memory clearing
	public void setDriver(RemoteWebDriver driver) {
		if(driver == null) {
			DriverInstance.driver.remove();// Removing the Threadlocal local variable
		}
		else {
			DriverInstance.driver.set(driver);
		}
	}
	
	
	public RemoteWebDriver getDriver() {
		return driver.get();// getting the converted RemoteWebDriver to ThreadLocal after setting Up
		
	}

	}

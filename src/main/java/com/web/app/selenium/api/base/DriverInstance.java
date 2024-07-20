package com.web.app.selenium.api.base;

import java.time.Duration;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.web.app.framework.utlis.Logs;

public class DriverInstance {
	
	private static final ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<RemoteWebDriver>();
	private static final ThreadLocal<WebDriverWait> wait = new  ThreadLocal<WebDriverWait>();
	
	public void setWait() {
		wait.set(new WebDriverWait(getDriver(), Duration.ofSeconds(30)));
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
			new Logs().console().pass("Successfully launched CHROME browser in the local machine.");
			break;
		case "firefox":			
			break;
		case "edge":
		default:
			new Logs().console().waring("Currently web app framework supports CHROME, FIREFOX and EDGE browsers.");
			break;
		}
	}
	
	public RemoteWebDriver getDriver() {
		return driver.get();
	}

}
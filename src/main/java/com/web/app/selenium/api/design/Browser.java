package com.web.app.selenium.api.design;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebElement;

public interface Browser {
	
	/**
	 * This method will launch the Chrome browser as default
	 * Headless - Options is flase as default 
	 * maximise the browser and set the wait seconds in the waf-config.properties file 
	 * and load the url
	 * @param url - This will load the specified url  `
	 * @author TestLeaf Team
	 * @throws SessionNotCreatedException, ConnectionClosedException, UnreachableBrowserException, NoSuchDriverException, Exception 
	 */	
	public void startApp(String url, String image);
	
	/**
	 * This method will launch the browser which is mentioned in waf-config.properties file  
	 * maximise the browser and set the wait seconds in the waf-config.properties file 
	 * and load the url
	 * @param url - This will load the specified url  `
	 * @author TestLeaf Team
	 * @throws SessionNotCreatedException, ConnectionClosedException, UnreachableBrowserException, NoSuchDriverException, Exception 
	 */	
	public void startApp(String url, boolean headless);
	
	/**
	 * This method will launch the Any browser and 
	 * maximise the browser and set the wait seconds in the waf-config.properties file 
	 * and load the url
	 * @param browser - This will load the specified browser
	 * @param url - This will load the specified url  
	 * @author TestLeaf Team
	 * @throws SessionNotCreatedException, ConnectionClosedException, UnreachableBrowserException, NoSuchDriverException, Exception 
	 */
	public void startApp(String browser, boolean headless, String url);
	/**
	 * This method will locate the element using any given locator
	 * @param locatorType  - The locator by which the element to be found
	 * @param locValue - The locator value by which the element to be found
	 * @author TestLeaf Team
	 * @throws NoSuchElementException
	 * @return The first matching element on the current context.
	 */
	public WebElement locateElement(Locators locatorType, String value);	
	
	/**
	 * This method will locate the element using id
	 * @param locValue - The locator value by which the element to be found
	 * @author TestLeaf Team
	 * @throws NoSuchElementException
	 * @return The first matching element on the current context.
	 */
	public List<WebElement> locateElements(Locators locatorType, String value);	
	
	/**
	 * This method will switch to the Alert
	 * @author TestLeaf Team
	 * @return NoAlertPresentException
	 */
	public Alert switchToAlert();
	/**
	 * This method will accept the alert opened
	 * @author TestLeaf Team
	 * @throws NoAlertPresentException
	 */
	public void acceptAlert();
	
	/**
	 * This method will dismiss the alert opened
	 * @author TestLeaf Team
	 * @throws NoAlertPresentException
	 */
	public void dismissAlert();
	
	/**
	 * This method will return the text of the alert
	 * @author TestLeaf Team
	 * @throws NoAlertPresentException
	 */
	public String getAlertText();

	/**
	 * This method will enter the value in the alert
	 * @author TestLeaf Team
	 * @param data- the data to be entered in alert
	 * @throws NoAlertPresentException
	*/
	public void typeAlert(String data);
	
	/**
	 * This method will switch to the Window of interest
	 * @param index The window index to be switched to. 0 -> first window 
	 * @author TestLeaf Team
	 * @throws NoSuchWindowException
	 */
	public void switchToWindow(int index);
	
	/**
	 * This method will switch to the Window of interest using its title
	 * @param title The window title to be switched to first window 
	 * @author TestLeaf Team
	 * @return 
	 * @throws NoSuchWindowException
	 */
	public boolean switchToWindow(String title);
	
	/**
	 * This method will switch to the specific frame using index
	 * @param index   - The int (frame) to be switched
	 * @author TestLeaf Team
	 * @throws NoSuchFrameException 
	 */
	public void switchToFrame(int index);	
	
	/**
	 * This method will switch to the specific frame
	 * @param ele   - The Webelement (frame) to be switched
	 * @author TestLeaf Team
	 * @throws NoSuchFrameException, StaleElementReferenceException 
	 */
	public void switchToFrame(WebElement ele);

	/**
	 * This method will switch to the specific frame using Id (or) Name
	 * @param idOrName   - The String (frame) to be switched
	 * @author TestLeaf Team
	 * @throws NoSuchFrameException 
	 */
	public void switchToFrame(String idOrName);
	
	/**
	 * This method will switch to the first frame on the page
	 * @author TestLeaf Team
	 * @return This driver focused on the top window/first frame.
	 */
	public void defaultContent();
	
	/**
	 * This method will verify browser actual url with expected
	 * @param url   - The url to be checked
	 * @author TestLeaf Team
	@Override
	 * @return true if the given object represents a String equivalent to this url, false otherwise
	 */
	public boolean verifyUrl(String url);
	
	/**
	 * This method will verify browser actual title with expected
	 * @param title - The expected title of the browser
	 * @author TestLeaf Team
	 * @return true if the given object represents a String equivalent to this title, false otherwise
	 */
	public boolean verifyTitle(String title);
	
	/**
	 * This method will take snapshot of the browser
	 * @author TestLeaf Team
	 * @return Object in which is stored information about the screenshot.
	 * @throws WebDriverException
	 */
	
	/**
	 * This method will close the active browser
	 * @author TestLeaf Team
	 */
	public void close();
	
	/**
	 * This method will close all the browsers
	 * @author TestLeaf Team
	 */
	public void quit();

	String getTypedText(WebElement ele, String attributeValue);

}
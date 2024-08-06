package com.web.app.selenium.api.design;

import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

public interface Element {
	
	/**
	 * This method is used to run the JavaScript
	 * @param js - JavaScript Code to run 
	 * @author TestLeaf Team
	 * @param ele - Run the given JavaScript on the Webelement (button/link/element)
	 */
	void executeJavaScript(String js, WebElement ele);
	
	/**
	 * This method will click the element and take snap
	 * @param ele   - The Webelement (button/link/element) to be clicked
	 * @see locateElement method in Browser Class
	 * @author TestLeaf Team
	 * @throws StaleElementReferenceException
	 */
	void click(WebElement ele, String image);
	
	
	/**
	 * This method will enter the value in the given text field 
	 * @param ele   - The Webelement (text field) in which the data to be entered
	 * @param data  - The data to be sent to the webelement
	 * @see locateElement method in Browser Class
	 * @author TestLeaf Team
	 * @throws ElementNotInteractable,IllegalArgumentException(throws if keysToSend is null)	
	 */
	 void append(WebElement ele, String data, String image);
	
	/**
	 * This method will clear the value in the given text field 
	 * @param ele   - The Webelement (text field) in which the data to be entered
	 * @see locateElement method in Browser Class
	 * @author TestLeaf Team
	 * @throws InvalidElementStateException	(throws if not user-editable element)	 
	 */
	 void clear(WebElement ele, String image);
	
	/**
	 * This method will clear and type the value in the given text field 
	 * @param ele   - The Webelement (text field) in which the data to be entered
	 * @param data  - The data to be sent to the webelement
	 * @see locateElement method in Browser Class
	 * @author TestLeaf Team
	 * @throws ElementNotInteractable,IllegalArgumentException(throws if keysToSend is null)		 
	 */
	 void clearAndType(WebElement ele,String data, String image);
	
	/**
	 * This method will get the visible text of the element
	 * @param ele   - The Webelement (button/link/element) in which text to be retrieved
	 * @author TestLeaf Team
	 * @see locateElement method in Browser Class
	 */
	 String getElementText(WebElement ele, String image);	
	
	/**
	 * This method will get the Color values of the element
	 * @param ele   - The Webelement (button/link/element) in which text to be retrieved
	 * @see locateElement method in Browser Class
	 * @author TestLeaf Team
	 * @return The visible text of this element.
	 */
	 String getBackgroundColor(WebElement ele, String image);
	
	/**
	 * This method will get the text of the element textbox
	 * @param ele   - The Webelement (button/link/element) in which text to be retrieved
	 * @see locateElement method in Browser Class
	 * @author TestLeaf Team
	 * @return The attribute/property's current value (or) null if the value is not set.
	 */
	 String getTypedText(WebElement ele , String attribute, String image);
	

	/**
	 * This method will select the drop down visible text
	 * @param ele   - The Webelement (dropdown) to be selected
	 * @param value The value to be selected (visibletext) from the dropdown
	 * @see locateElement method in Browser Class 
	 * @author TestLeaf Team
	 * @throws NoSuchElementException
	 */
	 void selectDropDownUsingText(WebElement ele, String value, String image) ;
	
	/**
	 * This method will select the drop down using index
	 * @param ele   - The Webelement (dropdown) to be selected
	 * @param index The index to be selected from the dropdown
	 * @see locateElement method in Browser Class
	 * @author Babu - TestLeaf
	 * @throws NoSuchElementException
	 */
	 void selectDropDownUsingIndex(WebElement ele, int index, String image) ;
	
	/**
	 * This method will select the drop down using index
	 * @param ele   - The Webelement (dropdown) to be selected
	 * @param value - The value to be selected (value) from the dropdown 
	 * @see locateElement method in Browser Class
	 * @author TestLeaf Team
	 * @throws NoSuchElementException
	 */
	 void selectDropDownUsingValue(WebElement ele, String value, String image) ;
	
	/**
	 * This method will verify exact given text with actual text on the given element
	 * @param ele   - The Webelement in which the text to be need to be verified
	 * @param expectedText  - The expected text to be verified
	 * @author TestLeaf Team
	 * @see locateElement method in Browser Class
	 * @return true if the given object represents a String equivalent to this string, false otherwise
	 */
	 boolean verifyExactText(WebElement ele, String expectedText, String image);
	
	/**
	 * This method will verify given text contains actual text on the given element
	 * @param ele   - The Webelement in which the text to be need to be verified
	 * @param expectedText  - The expected text to be verified
	 * @author TestLeaf Team
	 * @see locateElement method in Browser Class
	 * @return true if this String represents the same sequence of characters as the specified string, false otherwise
	 */
	 boolean verifyPartialText(WebElement ele, String expectedText, String Value, String image);

	/**
	 * This method will verify exact given attribute's value with actual value on the given element
	 * @param ele   - The Webelement in which the attribute value to be need to be verified
	 * @param attribute  - The attribute to be checked (like value, href etc)
	 * @param value  - The value of the attribute
	 * @author TestLeaf Team
	 * @see locateElement method in Browser Class
	 * @return true if this String represents the same sequence of characters as the specified value, false otherwise
	 */
	 boolean verifyExactAttribute(WebElement ele, String attribute, String value, String image);
	
	/**
	 * This method will verify partial given attribute's value with actual value on the given element
	 * @param ele   - The Webelement in which the attribute value to be need to be verified
	 * @param attribute  - The attribute to be checked (like value, href etc)
	 * @param value  - The value of the attribute
	 * @author TestLeaf Team
	 * @see locateElement method in Browser Class
	 * @return true if this String represents the same sequence of characters as the specified value, false otherwise
	 * 
	 */
	 boolean verifyPartialAttribute(WebElement ele, String attribute, String value, String image);
	
	/**
	 * This method will verify if the element is visible in the DOM
	 * @param ele   - The Webelement to be checked
	 * @author TestLeaf Team
	 * @see locateElement method in Browser Class
	 * @return true if the element is displayed or false otherwise
	 */
	 boolean verifyDisplayed(WebElement ele, String image);
	
	/**
	 * This method will checking the element to be invisible
	 * @param ele   - The Webelement to be checked
	 * @author TestLeaf Team
	 * @see locateElement method in Browser Class
	 */
	 boolean verifyDisappeared(WebElement ele, String image);	
	
	/**
	 * This method will verify if the input element is Enabled
	 * @param ele   - The Webelement (Radio button, Checkbox) to be verified
	 * @return true - if the element is enabled else false
	 * @author TestLeaf Team
	 * @see locateElement method in Browser Class
	 * @return True if the element is enabled, false otherwise.
	 */
	 boolean verifyEnabled(WebElement ele, String image);	
	
	/**
	 * This method will verify if the element (Radio button, Checkbox) is selected
	 * @param ele   - The Webelement (Radio button, Checkbox) to be verified
	 * @author TestLeaf Team
	 * @see locateElement method in Browser Class
	 * @return True if the element is currently selected or checked, false otherwise.
	 */
	 boolean verifySelected(WebElement ele, String image);

}
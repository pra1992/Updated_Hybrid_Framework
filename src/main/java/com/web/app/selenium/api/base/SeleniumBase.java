package com.web.app.selenium.api.base;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.NoSuchDriverException;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.safari.ConnectionClosedException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.web.app.framework.utlis.general.Logs;
import com.web.app.framework.utlis.properties.ConfigPropertiesHandler;
import com.web.app.selenium.api.design.Browser;
import com.web.app.selenium.api.design.Element;
import com.web.app.selenium.api.design.Locators;

public class SeleniumBase extends DriverInstance implements Browser, Element {

	private static final ConfigPropertiesHandler config = ConfigFactory.create(ConfigPropertiesHandler.class);

	@Override
	public void executeJavaScript(String js, WebElement ele) {
		getDriver().executeScript(js, ele);
	}
	
	public void executeJavaScript(String js, WebElement ele, int value) {
		getDriver().executeScript(js, ele, value);
	}


	@Override
	public void click(WebElement ele) {
		try {
			ele.click();
		} catch (ElementClickInterceptedException e) {
			new Logs().console().info(
					"The exception is usually thrown when an attempt to click on an element on a web page is intercepted or blocked by another element. "
							+ e.toString());
			new Logs().file().info(
					"The exception is usually thrown when an attempt to click on an element on a web page is intercepted or blocked by another element. "
							+ e.toString());
			executeJavaScript("arguments[0].scrollIntoView();", ele);
			ele.click();
		} catch (Exception e) {
			new Logs().console()
					.fail("Unable to click the given " + ele.toString() + " webelement. Due to --> " + e.toString());
			new Logs().file()
					.fail("Unable to click the given " + ele.toString() + " webelement. Due to --> " + e.toString());
			throw new RuntimeException(
					"Unable to click the given " + ele.toString() + " webelement. Due to --> " + e.toString());
		}
	}

	public void clickUsingActions(WebElement ele) {
		try {
			new Actions(getDriver()).moveToElement(ele).click().build().perform();
		} catch (ElementClickInterceptedException e) {
			new Logs().console().info(
					"The exception is usually thrown when an attempt to click on an element on a web page is intercepted or blocked by another element. "
							+ e.toString());
			new Logs().file().info(
					"The exception is usually thrown when an attempt to click on an element on a web page is intercepted or blocked by another element. "
							+ e.toString());
			executeJavaScript("arguments[0].scrollIntoView();", ele);
			ele.click();
		} catch (Exception e) {
			new Logs().console()
					.fail("Unable to click the given " + ele.toString() + " webelement. Due to --> " + e.toString());
			new Logs().file()
					.fail("Unable to click the given " + ele.toString() + " webelement. Due to --> " + e.toString());
			throw new RuntimeException(
					"Unable to click the given " + ele.toString() + " webelement. Due to --> " + e.toString());
		}
	}

	public void scrollToElement(WebElement ele) {
		try {
			new Actions(getDriver()).moveToElement(ele).build().perform();

		} catch (Exception e) {
			new Logs().console()
					.fail("Unable to move to the given " + ele.toString() + " webelement. Due to --> " + e.toString());
			new Logs().file()
					.fail("Unable to move to the given " + ele.toString() + " webelement. Due to --> " + e.toString());
			throw new RuntimeException(
					"Unable to click the move to " + ele.toString() + " webelement. Due to --> " + e.toString());
		}
	}

	@Override
	public void append(WebElement ele, String data) {
		getWait().until(ExpectedConditions.visibilityOf(ele));
		try {
			ele.sendKeys(data);
			new Logs().console().pass("Able to append the text to the " + ele.toString());
		} catch (Exception e) {
			new Logs().console().fail("Unable to append the text due to " + e.getMessage());
			new Logs().file().fail("Unable to append the text due to " + e.getMessage());
			throw new RuntimeException("Unable to append the text due to " + e.getMessage());
		}
	}

	public void append(WebElement ele, Keys key) {
		getWait().until(ExpectedConditions.visibilityOf(ele));
		try {
			ele.sendKeys(key);
			new Logs().console().pass("Able to press the Key into the " + ele.toString());
		} catch (Exception e) {
			new Logs().console().fail("Unable to press the Key into the " + e.getMessage());
			new Logs().file().fail("Unable to press the Key into the" + e.getMessage());
			throw new RuntimeException("Unable to press the Key into the " + e.getMessage());
		}
	}

	@Override
	public void clear(WebElement ele) {
		getWait().until(ExpectedConditions.visibilityOf(ele));
		try {
			ele.clear();
			new Logs().console().pass("Able to clear the text");
		} catch (Exception e) {
			new Logs().console().fail("Unable to clear the text due to " + e.getMessage());
			new Logs().file().fail("Unable to clear the text due to " + e.getMessage());
			throw new RuntimeException("Unable to clear the text due to " + e.getMessage());
		}
	}

	@Override
	public void clearAndType(WebElement ele, String data) {
		getWait().until(ExpectedConditions.visibilityOf(ele));
		try {
			ele.clear();
			ele.sendKeys(data);
			new Logs().console().pass("Able to clear and type the text");
		} catch (Exception e) {
			new Logs().console().fail("Unable to clear and type the text due to " + e.getMessage());
			new Logs().file().fail("Unable to clear and type the text due to " + e.getMessage());
			throw new RuntimeException("Unable to clear and type the text due to " + e.getMessage());
		}

	}

	@Override
	public String getElementText(WebElement ele) {
		String text = null;
		getWait().until(ExpectedConditions.visibilityOf(ele));
		try {
			ele.getText().trim();
			new Logs().console().pass("Able to extract the text from" + ele.toString());
		} catch (Exception e) {
			new Logs().console().fail("Unable to extract the text due to " + e.getMessage());
			new Logs().file().fail("Unable to extract the text due to " + e.getMessage());
			throw new RuntimeException("Unable to extract the text due to " + e.getMessage());
		}
		return text;

	}

	@Override
	public String getBackgroundColor(WebElement ele) {
		getWait().until(ExpectedConditions.visibilityOf(ele));
		try {
			String rgba = ele.getCssValue("background-color");
			if (rgba.startsWith("rgba")) {
				String[] A = rgba.replace("rgba(", "").replace(")", "").split(",");
				int[] B = new int[A.length];
				for (int i = 0; i < A.length; i++) {
					B[i] = Integer.parseInt(A[i].trim());
				}
				String hex = String.format("#%02x%02x%02x", B[0], B[1], B[2]);
				return hex;
			} else {

				return rgba;
			}
		} catch (Exception e) {
			new Logs().console().fail("Unable to extract the background colour due to " + e.getMessage());
			new Logs().file().fail("Unable to extract the background colour due to " + e.getMessage());
			throw new RuntimeException("Unable to extract the background colour due to " + e.getMessage());

		}

	}

	@Override
	public String getTypedText(WebElement ele, String attributeValue) {
		String text = null;
		getWait().until(ExpectedConditions.visibilityOf(ele));
		try {
			text = ele.getAttribute(attributeValue);
			new Logs().console().pass("Able to extract the text from" + ele.toString());
		} catch (Exception e) {
			new Logs().console().fail("Unable to extract the attribute value due to " + e.getMessage());
			new Logs().file().fail("Unable to extract the attribute value colour due to " + e.getMessage());
			throw new RuntimeException("Unable to extract the attribute value  due to " + e.getMessage());
		}
		return text;
	}

	@Override
	public void selectDropDownUsingText(WebElement ele, String value) {
		getWait().until(ExpectedConditions.visibilityOf(ele));
		getWait().until(ExpectedConditions.elementToBeClickable(ele));
		try {
			ele.click();
			new Select(ele).selectByVisibleText(value);
			new Logs().console().pass("Able to select the " + value + "from" + ele.toString());
		} catch (Exception e) {
			new Logs().console().fail("Unable to select the dropdown value due to " + e.getMessage());
			new Logs().file().fail("Unable to select the dropdown value due to " + e.getMessage());
			throw new RuntimeException("Unable to select the dropdown value  due to " + e.getMessage());
		}
	}

	@Override
	public void selectDropDownUsingIndex(WebElement ele, int index) {
		getWait().until(ExpectedConditions.visibilityOf(ele));
		getWait().until(ExpectedConditions.elementToBeClickable(ele));
		try {
			ele.click();
			new Select(ele).selectByIndex(index);
			new Logs().console().pass("Able to select the value from " + index + "from" + ele.toString());
		} catch (Exception e) {
			new Logs().console().fail("Unable to select the dropdown value due to " + e.getMessage());
			new Logs().file().fail("Unable to select the dropdown value due to " + e.getMessage());
			throw new RuntimeException("Unable to select the dropdown value  due to " + e.getMessage());
		}
	}

	@Override
	public void selectDropDownUsingValue(WebElement ele, String value) {
		getWait().until(ExpectedConditions.visibilityOf(ele));
		getWait().until(ExpectedConditions.elementToBeClickable(ele));
		try {
			ele.click();
			new Select(ele).selectByValue(value);
			new Logs().console().pass("Able to select the value from " + value + "from" + ele.toString());
		} catch (Exception e) {
			new Logs().console().fail("Unable to select the dropdown value due to " + e.getMessage());
			new Logs().file().fail("Unable to select the dropdown value due to " + e.getMessage());
			throw new RuntimeException("Unable to select the dropdown value  due to " + e.getMessage());
		}
	}

	@Override
	public boolean verifyExactText(WebElement ele, String expectedText) {
		getWait().until(ExpectedConditions.visibilityOf(ele));
		boolean flag = false;
		try {
			if (ele.getText().trim().equalsIgnoreCase(expectedText)) {
				flag = true;
				new Logs().console().pass("The text on the " + ele.toString() + "meets the " + expectedText);
			}
		} catch (Exception e) {
			new Logs().console().fail("The text on the " + ele.toString() + " does not meets the " + expectedText
					+ "due to" + e.getMessage());
			new Logs().file().fail("The text on the " + ele.toString() + " does not meets the " + expectedText
					+ "due to" + e.getMessage());
			throw new RuntimeException("The text on the " + ele.toString() + " does not meets the " + expectedText
					+ "due to" + e.getMessage());
		}
		return flag;
	}

	@Override
	public boolean verifyPartialText(WebElement ele, String expectedText, String Value) {
		getWait().until(ExpectedConditions.visibilityOf(ele));
		boolean flag = false;
		try {
			if (ele.getText().trim().contains(expectedText)) {
				flag = true;
				new Logs().console().pass("The text on the " + Value + ele.toString() + "meets the " + expectedText);
			}
		} catch (Exception e) {
			new Logs().console().fail("The text on the " + Value + ele.toString() + " does not meets the partial "
					+ expectedText + "due to" + e.getMessage());
			new Logs().file().fail("The text on the " + Value + ele.toString() + " does not meets the partial "
					+ expectedText + "due to" + e.getMessage());
			throw new RuntimeException("The text on the " + Value + ele.toString() + " does not meets the partial "
					+ expectedText + "due to" + e.getMessage());
		}
		return flag;
	}

	@Override
	public boolean verifyExactAttribute(WebElement ele, String attribute, String value) {
		getWait().until(ExpectedConditions.visibilityOf(ele));
		boolean flag = false;
		try {
			if (ele.getAttribute(attribute).trim().equals(value)) {
				flag = true;
				new Logs().console().pass("The text on the " + attribute + ele.toString() + "meets the " + value);
			}
		} catch (Exception e) {
			new Logs().console().fail("The text on the " + attribute + ele.toString() + " does not meets the " + value
					+ "due to" + e.getMessage());
			new Logs().file().fail("The text on the " + attribute + ele.toString() + " does not meets the " + value
					+ "due to" + e.getMessage());
			throw new RuntimeException("The text on the " + attribute + ele.toString() + " does not meets the " + value
					+ "due to" + e.getMessage());
		}
		return flag;
	}

	@Override
	public boolean verifyPartialAttribute(WebElement ele, String attribute, String value) {
		getWait().until(ExpectedConditions.visibilityOf(ele));
		boolean flag = false;
		try {
			if (ele.getAttribute(attribute).trim().contains(value)) {
				flag = true;
				new Logs().console().pass("The text on the " + attribute + ele.toString() + "meets the " + value);
			}
		} catch (Exception e) {
			new Logs().console().fail("The text on the " + attribute + ele.toString() + " does not meets the partial "
					+ value + "due to" + e.getMessage());
			new Logs().file().fail("The text on the " + attribute + ele.toString() + " does not meets the partial "
					+ value + "due to" + e.getMessage());
			throw new RuntimeException("The text on the " + attribute + ele.toString() + " does not meets the partial "
					+ value + "due to" + e.getMessage());
		}
		return flag;

	}

	@Override
	public boolean verifyDisplayed(WebElement ele) {
		boolean flag = false;
		getWait().until(ExpectedConditions.visibilityOf(ele));
		try {
			if (ele.isDisplayed()) {
				flag = true;
				new Logs().console().pass(ele.toString() + "is displayed");
			}
		} catch (Exception e) {
			new Logs().console().fail(ele.toString() + "is not displayed due to " + e.toString());
			new Logs().file().fail(ele.toString() + "is not displayed due to " + e.toString());
			throw new RuntimeException(ele.toString() + "is not displayed due to " + e.toString());
		}
		return flag;

	}

	@Override
	public boolean verifyDisappeared(WebElement ele) {
		boolean flag = false;
		getWait().until(ExpectedConditions.invisibilityOf(ele));
		try {
			if (!ele.isDisplayed()) {
				flag = true;
				new Logs().console().pass(ele.toString() + "is not displayed");
			}
		} catch (Exception e) {
			new Logs().console().fail(ele.toString() + "unable to verify due to " + e.toString());
			new Logs().file().fail(ele.toString() + "unable to verify due to " + e.toString());
			throw new RuntimeException(ele.toString() + "unable to verify due to " + e.toString());
		}
		return flag;

	}

	@Override
	public boolean verifyEnabled(WebElement ele) {
		boolean flag = false;
		getWait().until(ExpectedConditions.visibilityOf(ele));
		try {
			if (ele.isEnabled()) {
				flag = true;
				new Logs().console().pass(ele.toString() + "is enabled");
			}
		} catch (Exception e) {
			new Logs().console().fail(ele.toString() + "is not enabled due to " + e.toString());
			new Logs().file().fail(ele.toString() + "is not enabled due to " + e.toString());
			throw new RuntimeException(ele.toString() + "is not enabled due to " + e.toString());
		}
		return flag;
	}

	@Override
	public boolean verifySelected(WebElement ele) {
		boolean flag = false;
		getWait().until(ExpectedConditions.visibilityOf(ele));
		try {
			if (ele.isSelected()) {
				flag = true;
				new Logs().console().pass(ele.toString() + "is selected");
			}
		} catch (Exception e) {
			new Logs().console().fail(ele.toString() + "is not selected due to " + e.toString());
			new Logs().file().fail(ele.toString() + "is not selected due to " + e.toString());
			throw new RuntimeException(ele.toString() + "is not selected due to " + e.toString());
		}
		return flag;
	}

	@Override
	public void startApp(String url) {
		try {
			setDriver("chrome", false);
			getDriver().get(url);
			new Logs().console().pass("Successfully opened " + url + " application under test in the chrome browser.");
			new Logs().file().pass("Successfully opened " + url + " application under test in the chrome browser.");
			getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(config.getImplicitWaitTime()));
			new Logs().console().pass("Implicit wait time set to " + config.getImplicitWaitTime() + " seconds.");
			new Logs().file().pass("Implicit wait time set to " + config.getImplicitWaitTime() + " seconds.");
		} catch (SessionNotCreatedException sne) {
			new Logs().console().fail("A new session could not be successfully created. Due to --> " + sne.toString());
			new Logs().file().fail("A new session could not be successfully created. Due to --> " + sne.toString());
		} catch (ConnectionClosedException cce) {
			new Logs().console().fail("The Driver Connection got lost. Due to --> " + cce.toString());
			new Logs().file().fail("The Driver Connection got lost. Due to --> " + cce.toString());
			throw new RuntimeException("The Driver Connection got lost. Due to --> " + cce.toString());
		} catch (UnreachableBrowserException ube) {
			new Logs().console()
					.fail("The chrome browser is unable to be opened or has crashed because of " + ube.toString());
			new Logs().file()
					.fail("The chrome browser is unable to be opened or has crashed because of " + ube.toString());
			throw new RuntimeException(
					"The chrome browser is unable to be opened or has crashed because of " + ube.toString());
		} catch (NoSuchDriverException nde) {
			new Logs().console().fail(
					"Unable to find chrome driver in the local machine and, Now trying to set driver by webdriver manager class. "
							+ nde.toString());
			new Logs().file().fail(
					"Unable to find chrome driver in the local machine and, Now trying to set driver by webdriver manager class. "
							+ nde.toString());
			System.out.println(
					"Unable to find chrome driver in the local machine and, Now trying to set driver by webdriver manager class. "
							+ nde.toString());
		} catch (Exception e) {
			new Logs().console().fail("Unable to launch given chrome browser. Due to --> " + e.toString());
			new Logs().file().fail("Unable to launch given chrome browser. Due to --> " + e.toString());
			throw new RuntimeException("Unable to launch given chrome browser. Due to --> " + e.toString());
		}

	}

	@Override
	public void startApp(String url, boolean headless) {
		try {
			setDriver(config.getDefaultBrowserName(), config.isBrowserHeadless());
			getDriver().get(url);
			getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(config.getImplicitWaitTime()));
		} catch (SessionNotCreatedException sne) {
			new Logs().console().fail("A new session could not be successfully created. Due to --> " + sne.toString());
			new Logs().file().fail("A new session could not be successfully created. Due to --> " + sne.toString());
		} catch (ConnectionClosedException cce) {
			new Logs().console().fail("The Driver Connection got lost. Due to --> " + cce.toString());
			new Logs().file().fail("The Driver Connection got lost. Due to --> " + cce.toString());
			throw new RuntimeException("The Driver Connection got lost. Due to --> " + cce.toString());
		} catch (UnreachableBrowserException ube) {
			new Logs().console().fail("The " + config.getDefaultBrowserName()
					+ " browser is unable to be opened or has crashed because of " + ube.toString());
			new Logs().file().fail("The " + config.getDefaultBrowserName()
					+ " browser is unable to be opened or has crashed because of " + ube.toString());
			throw new RuntimeException("The " + config.getDefaultBrowserName()
					+ " browser is unable to be opened or has crashed because of " + ube.toString());
		} catch (NoSuchDriverException nde) {
			new Logs().console()
					.fail("Unable to find " + config.getDefaultBrowserName()
							+ " driver in the local machine and, Now trying to set driver by webdriver manager class. "
							+ nde.toString());
			new Logs().file()
					.fail("Unable to find " + config.getDefaultBrowserName()
							+ " driver in the local machine and, Now trying to set driver by webdriver manager class. "
							+ nde.toString());
			System.out.println("Unable to find " + config.getDefaultBrowserName()
					+ " driver in the local machine and, Now trying to set driver by webdriver manager class. "
					+ nde.toString());
		} catch (Exception e) {
			new Logs().console().fail("Unable to launch given " + config.getDefaultBrowserName()
					+ " browser. Due to --> " + e.toString());
			new Logs().file().fail("Unable to launch given " + config.getDefaultBrowserName() + " browser. Due to --> "
					+ e.toString());
			throw new RuntimeException("Unable to launch given " + config.getDefaultBrowserName()
					+ " browser. Due to --> " + e.toString());
		}
	}

	@Override
	public void startApp(String browser, boolean headless, String url) {
		try {
			setDriver(browser, config.isBrowserHeadless());
			getDriver().get(url);
			getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(config.getImplicitWaitTime()));
		} catch (SessionNotCreatedException sne) {
			new Logs().console().fail("A new session could not be successfully created. Due to --> " + sne.toString());
			new Logs().file().fail("A new session could not be successfully created. Due to --> " + sne.toString());
		} catch (ConnectionClosedException cce) {
			new Logs().console().fail("The Driver Connection got lost. Due to --> " + cce.toString());
			new Logs().file().fail("The Driver Connection got lost. Due to --> " + cce.toString());
			throw new RuntimeException("The Driver Connection got lost. Due to --> " + cce.toString());
		} catch (UnreachableBrowserException ube) {
			new Logs().console().fail(
					"The " + browser + " browser is unable to be opened or has crashed because of " + ube.toString());
			new Logs().file().fail(
					"The " + browser + " browser is unable to be opened or has crashed because of " + ube.toString());
			throw new RuntimeException(
					"The " + browser + " browser is unable to be opened or has crashed because of " + ube.toString());
		} catch (NoSuchDriverException nde) {
			new Logs().console()
					.fail("Unable to find " + browser
							+ " driver in the local machine and, Now trying to set driver by webdriver manager class. "
							+ nde.toString());
			new Logs().file()
					.fail("Unable to find " + browser
							+ " driver in the local machine and, Now trying to set driver by webdriver manager class. "
							+ nde.toString());
			System.out.println("Unable to find " + browser
					+ " driver in the local machine and, Now trying to set driver by webdriver manager class. "
					+ nde.toString());
		} catch (Exception e) {
			new Logs().console().fail("Unable to launch given " + browser + " browser. Due to --> " + e.toString());
			new Logs().file().fail("Unable to launch given " + browser + " browser. Due to --> " + e.toString());
			throw new RuntimeException("Unable to launch given " + browser + " browser. Due to --> " + e.toString());
		}
	}

	@Override
	public WebElement locateElement(Locators locatorType, String value) {
		WebElement element = null;
		try {
			switch (locatorType) {
			case ID:
				element = getDriver().findElement(By.id(value));
				new Logs().console().pass("Found the given weblement " + value + " using id locator type.");
				break;
			case NAME:
				element = getDriver().findElement(By.name(value));
				break;
			case CLASS_NAME:
				element = getDriver().findElement(By.className(value));
			case XPATH:
				element = getDriver().findElement(By.xpath(value));
				break;
			default:
				break;
			}
		} catch (NoSuchElementException e) {
			new Logs().console().fail("Unable to found given web element " + value + " using" + locatorType.toString()
					+ " type. " + e.toString());
			throw new RuntimeException("Unable to found given web element " + value + " using" + locatorType.toString()
					+ " type." + e.toString());
		} catch (StaleElementReferenceException e) {
			switch (locatorType) {
			case ID:
				getDriver().navigate().refresh();
				element = getDriver().findElement(By.id(value));
				new Logs().console().pass("Found the given weblement " + value + " using id locator type.");
				break;
			case NAME:
				getDriver().navigate().refresh();
				element = getDriver().findElement(By.name(value));
				break;
			case CLASS_NAME:
				getDriver().navigate().refresh();
				element = getDriver().findElement(By.className(value));
				break;
			default:
				break;

			}
		} catch (Exception e) {
			new Logs().console().fail("Unable to find given web element " + value + " using" + locatorType.toString()
					+ " type. " + e.toString());
			throw new RuntimeException("Unable to find given web element " + value + " using" + locatorType.toString()
					+ " type. " + e.toString());
		}
		return element;
	}

	@Override
	public List<WebElement> locateElements(Locators locatorType, String value) {
		List<WebElement> element = null;
		try {
			switch (locatorType) {
			case ID:
				element = getDriver().findElements(By.id(value));
				new Logs().console().pass("Found the given weblement " + value + " using id locator type.");
				break;
			case NAME:
				element = getDriver().findElements(By.name(value));
				break;
			case CLASS_NAME:
				element = getDriver().findElements(By.className(value));
			case XPATH:
				element = getDriver().findElements(By.xpath(value));
				break;
			default:
				break;
			}
		} catch (NoSuchElementException e) {
			new Logs().console().fail("Unable to found given web element " + value + " using" + locatorType.toString()
					+ " type. " + e.toString());
			throw new RuntimeException("Unable to found given web element " + value + " using" + locatorType.toString()
					+ " type." + e.toString());
		} catch (StaleElementReferenceException e) {
			switch (locatorType) {
			case ID:
				getDriver().navigate().refresh();
				element = getDriver().findElements(By.id(value));
				new Logs().console().pass("Found the given weblement " + value + " using id locator type.");
				break;
			case NAME:
				getDriver().navigate().refresh();
				element = getDriver().findElements(By.name(value));
				break;
			case CLASS_NAME:
				getDriver().navigate().refresh();
				element = getDriver().findElements(By.className(value));
				break;
			default:
				break;

			}
		} catch (Exception e) {
			new Logs().console().fail("Unable to find given web element " + value + " using" + locatorType.toString()
					+ " type. " + e.toString());
			throw new RuntimeException("Unable to find given web element " + value + " using" + locatorType.toString()
					+ " type. " + e.toString());
		}
		return element;
	}

	@Override
	public Alert switchToAlert() {
		try {
			return getDriver().switchTo().alert();
		} catch (NoAlertPresentException e) {
			new Logs().console().fail("Unable to switch to the Alert due to " + e.getMessage());
			return null;
		}

	}

	@Override
	public void acceptAlert() {
		try {
			switchToAlert().accept();
			new Logs().console().pass("Able to accept the alert");
		} catch (Exception e) {
			new Logs().console().fail("Unable to accept the alert due to " + e.getMessage());
		}

	}

	@Override
	public void dismissAlert() {
		try {
			switchToAlert().dismiss();
			new Logs().console().pass("Able to dismiss the alert");
		} catch (Exception e) {
			new Logs().console().fail("Unable to dismiss the alert due to " + e.getMessage());
		}

	}

	@Override
	public String getAlertText() {
		String AlertText = null;
		try {
			switchToAlert().getText().trim();
			new Logs().console().pass("Able to get the text on the alert");
		} catch (Exception e) {
			new Logs().console().fail("Unable to get the text on the alert due to " + e.getMessage());
		}
		return AlertText;
	}

	@Override
	public void typeAlert(String data) {
		try {
			switchToAlert().sendKeys(data);
			new Logs().console().pass("Able to type " + data + " in the alert text box");
		} catch (Exception e) {
			new Logs().console().fail("Unable to type the text on the alert due to " + e.getMessage());
		}

	}

	@Override
	public void switchToWindow(int index) {
		List<String> OpenedWindows = new ArrayList<String>(getDriver().getWindowHandles());
		try {
			for (index = 0; index < OpenedWindows.size(); index++) {
				getDriver().switchTo().window(OpenedWindows.get(index));
			}
		} catch (NoSuchWindowException e) {
			new Logs().console().fail("Unable to switch to the window due to " + e.getMessage());
		} catch (Exception e) {
			new Logs().console().fail("Unable to switch to the window due to " + e.getMessage());
		}
	}

	@Override
	public boolean switchToWindow(String title) {
		boolean found = false;
		try {
			String ParentWindow = getDriver().getWindowHandle();
			for (String window : getDriver().getWindowHandles()) {
				if (!window.equals(ParentWindow)) {
					getDriver().switchTo().window(window);
					if (getDriver().getTitle().equals(title)) {
						found = true;
						break;
					}
				}
			}

			if (!found) {
				throw new RuntimeException("Unable to find window with " + title);
			}
		} catch (NoSuchWindowException e) {
			new Logs().console().fail("Unable to switch to the window due to " + e.getMessage());
		} catch (Exception e) {
			new Logs().console().fail("Unable to switch to the window due to " + e.getMessage());
		}
		return found;

	}

	public void checkWindowHandles() {
		String ParentWindow = getDriver().getWindowHandle();
		try {
			for (String OpenWindow : getDriver().getWindowHandles()) {
				if (!OpenWindow.equals(ParentWindow)) {
					getDriver().switchTo().window(OpenWindow);
				}
			}
		} catch (NoSuchWindowException e) {
			new Logs().console().fail("Unable to switch to the window due to " + e.getMessage());
		} catch (Exception e) {
			new Logs().console().fail("Unable to switch to the window due to " + e.getMessage());
		}
	}

	@Override
	public void switchToFrame(int index) {
		try {
			getDriver().switchTo().frame(index);
			new Logs().console().pass("Able to switch the frame with" + index);
		} catch (NoSuchFrameException e) {
			new Logs().console().fail("Unable to switch to the frame due to " + e.getMessage());
		} catch (Exception e) {
			new Logs().console().fail("Unable to switch to the frame due to " + e.getMessage());
		}

	}

	@Override
	public void switchToFrame(WebElement ele) {
		try {
			getDriver().switchTo().frame(ele);
		} catch (NoSuchFrameException e) {
			new Logs().console().fail("Unable to switch to the frame due to " + e.getMessage());
		} catch (Exception e) {
			new Logs().console().fail("Unable to switch to the frame due to " + e.getMessage());
		}

	}

	@Override
	public void switchToFrame(String idOrName) {
		try {
			getDriver().switchTo().frame(idOrName);
		} catch (NoSuchFrameException e) {
			new Logs().console().fail("Unable to switch to the frame due to " + e.getMessage());
		} catch (Exception e) {
			new Logs().console().fail("Unable to switch to the frame due to " + e.getMessage());
		}

	}

	@Override
	public void defaultContent() {
		try {
			getDriver().switchTo().defaultContent();
		} catch (Exception e) {
			new Logs().console().fail("Unable to switch to the default content due to " + e.getMessage());
		}

	}

	@Override
	public boolean verifyUrl(String url) {
		boolean flag = false;
		try {
			if (getDriver().getCurrentUrl().equals(url)) {
				flag = true;
			}
		} catch (Exception e) {
			new Logs().console().fail("Unable to verify the url due to " + e.getMessage());
		}
		return flag;
	}

	@Override
	public boolean verifyTitle(String title) {
		boolean flag = false;
		try {
			if (getDriver().getTitle().equals(title)) {
				flag = true;
			}
		} catch (Exception e) {
			new Logs().console().fail("Unable to verify the title of the current page due to " + e.getMessage());
		}
		return flag;
	}

	@Override
	public void close() {
		try {
			if (getDriver() != null) {
				getDriver().close();
				new Logs().console().pass("Successfully closed the current window");
			}
		} catch (Exception e) {
			new Logs().console().fail("Unable to close the current browser session due to  " + e.getMessage());
		} finally {
			if (getDriver() != null) {
				getDriver().manage().deleteAllCookies();
				getDriver().executeScript("window.localStorage.clear();");
				setDriver(null);
			}
		}
	}

	@Override
	public void quit() {
		try {
			if (getDriver() != null) {
				getDriver().quit();
				new Logs().console().pass("Successfully closed all the browser sessions");
			}
		} catch (Exception e) {
			new Logs().console().fail("Unable to close all the browser sessions due to  " + e.getMessage());
		} finally {
			if (getDriver() != null) {
				setDriver(null);
			}
		}

	}

	// Key Board Events //

	/**
	 * @param>> Accepts Uppercase character as Input
	 * @throws>> AWTException
	 * @return>> KeyEvent
	 */
	public int getKeyEventUpperCase(Character character) throws AWTException {
		int key = 0;
		try {
			switch (character) {
			case 'A':
				key = KeyEvent.VK_A;
				break;
			case 'B':
				key = KeyEvent.VK_B;
				break;
			case 'C':
				key = KeyEvent.VK_C;
				break;
			case 'D':
				key = KeyEvent.VK_D;
				break;
			case 'E':
				key = KeyEvent.VK_E;
				break;
			case 'F':
				key = KeyEvent.VK_F;
				break;
			case 'G':
				key = KeyEvent.VK_G;
				break;
			case 'H':
				key = KeyEvent.VK_H;
				break;
			case 'I':
				key = KeyEvent.VK_I;
				break;
			case 'J':
				key = KeyEvent.VK_J;
				break;
			case 'K':
				key = KeyEvent.VK_K;
				break;
			case 'L':
				key = KeyEvent.VK_L;
				break;
			case 'M':
				key = KeyEvent.VK_M;
				break;
			case 'N':
				key = KeyEvent.VK_N;
				break;
			case 'O':
				key = KeyEvent.VK_O;
				break;
			case 'P':
				key = KeyEvent.VK_P;
				break;
			case 'Q':
				key = KeyEvent.VK_Q;
				break;
			case 'R':
				key = KeyEvent.VK_R;
				break;
			case 'S':
				key = KeyEvent.VK_S;
				break;
			case 'T':
				key = KeyEvent.VK_T;
				break;
			case 'U':
				key = KeyEvent.VK_U;
				break;
			case 'V':
				key = KeyEvent.VK_V;
				break;
			case 'W':
				key = KeyEvent.VK_W;
				break;
			case 'X':
				key = KeyEvent.VK_X;
				break;
			case 'Y':
				key = KeyEvent.VK_Y;
				break;
			case 'Z':
				key = KeyEvent.VK_Z;
				break;
			}
		} catch (Exception e) {
			new Logs().console().fail("Unable to fect the KeyEvent due to " + e.getMessage());
			new Logs().file().fail("Unable to fect the KeyEvent due to " + e.getMessage());
		}
		return key;
	}

	/**
	 * @param>> Accepts Uppercase character as Input
	 * @throws>> AWTException
	 * @return>> KeyEvent
	 */
	public int getKeyEventLowerCase(Character character) throws AWTException {
		int key = 0;
		try {
			switch (character) {
			case 'a':
				key = KeyEvent.VK_A;
				break;
			case 'b':
				key = KeyEvent.VK_B;
				break;
			case 'c':
				key = KeyEvent.VK_C;
				break;
			case 'd':
				key = KeyEvent.VK_D;
				break;
			case 'e':
				key = KeyEvent.VK_E;
				break;
			case 'f':
				key = KeyEvent.VK_F;
				break;
			case 'g':
				key = KeyEvent.VK_G;
				break;
			case 'h':
				key = KeyEvent.VK_H;
				break;
			case 'i':
				key = KeyEvent.VK_I;
				break;
			case 'j':
				key = KeyEvent.VK_J;
				break;
			case 'k':
				key = KeyEvent.VK_K;
				break;
			case 'l':
				key = KeyEvent.VK_L;
				break;
			case 'm':
				key = KeyEvent.VK_M;
				break;
			case 'n':
				key = KeyEvent.VK_N;
				break;
			case 'o':
				key = KeyEvent.VK_O;
				break;
			case 'p':
				key = KeyEvent.VK_P;
				break;
			case 'q':
				key = KeyEvent.VK_Q;
				break;
			case 'r':
				key = KeyEvent.VK_R;
				break;
			case 's':
				key = KeyEvent.VK_S;
				break;
			case 't':
				key = KeyEvent.VK_T;
				break;
			case 'u':
				key = KeyEvent.VK_U;
				break;
			case 'v':
				key = KeyEvent.VK_V;
				break;
			case 'w':
				key = KeyEvent.VK_W;
				break;
			case 'x':
				key = KeyEvent.VK_X;
				break;
			case 'y':
				key = KeyEvent.VK_Y;
				break;
			case 'z':
				key = KeyEvent.VK_Z;
				break;
			}
		} catch (Exception e) {
			new Logs().console().fail("Unable to fect the KeyEvent due to " + e.getMessage());
			new Logs().file().fail("Unable to fect the KeyEvent due to " + e.getMessage());
		}
		return key;
	}

	/**
	 * @param character
	 * @return key
	 * @throws AWTException
	 */
	public int getKeyEventDigit(Character character) {
		int key = 0;
		try {
			switch (character) {
			case '0':
				key = KeyEvent.VK_A;
				break;
			case '1':
				key = KeyEvent.VK_B;
				break;
			case '2':
				key = KeyEvent.VK_C;
				break;
			case '3':
				key = KeyEvent.VK_D;
				break;
			case '4':
				key = KeyEvent.VK_E;
				break;
			case '5':
				key = KeyEvent.VK_F;
				break;
			case '6':
				key = KeyEvent.VK_G;
				break;
			case '7':
				key = KeyEvent.VK_H;
				break;
			case '8':
				key = KeyEvent.VK_I;
				break;
			case '9':
				key = KeyEvent.VK_J;
				break;
			}
		} catch (Exception e) {
			new Logs().console().fail("Unable to fect the KeyEvent due to " + e.getMessage());
			new Logs().file().fail("Unable to fect the KeyEvent due to " + e.getMessage());
		}
		return key;

	}

	/**
	 * @throws
	 * @param input
	 */
	public void typeKeys(String input) throws AWTException {
		try {
			for (int i = 0; i < input.length(); i++) {
				if (Character.isUpperCase(input.charAt(i))) {
					new Robot().keyPress(input.charAt(i));
					new Robot().keyRelease(input.charAt(i));
				}
				if (Character.isLowerCase(input.charAt(i))) {
					new Robot().keyPress(KeyEvent.VK_SHIFT);
					new Robot().keyPress(getKeyEventLowerCase(input.charAt(i)));
					new Robot().keyRelease(getKeyEventLowerCase(input.charAt(i)));
					new Robot().keyRelease(KeyEvent.VK_SHIFT);
				}
				if (Character.isDigit(input.charAt(i))) {
					new Robot().keyPress(getKeyEventDigit(input.charAt(i)));
					new Robot().keyRelease(getKeyEventDigit(input.charAt(i)));
				}

			}
		} catch (Exception e) {
			new Logs().console().fail("Unable to type the Key due to " + e.getMessage());
			new Logs().file().fail("Unable to type the Key due to " + e.getMessage());
		}
	}
    
	/**
	 * presses ENTER
	 * 
	 */
	public void pressEnter() throws AWTException {
		try {
			new Robot().keyPress(KeyEvent.VK_ENTER);
			new Robot().keyRelease(KeyEvent.VK_ENTER);
		} catch (Exception e) {
			new Logs().console().fail("Unable to type the ENTER due to " + e.getMessage());
			new Logs().file().fail("Unable to type the ENTER due to " + e.getMessage());
		}
	}
	
	/**
	 * presses ESCAPE
	 * 
	 */
	public void pressEscape() throws AWTException {
		try {
			new Robot().keyPress(KeyEvent.VK_ESCAPE);
			new Robot().keyRelease(KeyEvent.VK_ESCAPE);
		} catch (Exception e) {
			new Logs().console().fail("Unable to type the ESCAPE due to " + e.getMessage());
			new Logs().file().fail("Unable to type the ESCAPE due to " + e.getMessage());
		}
	}
	
	public void clickElementUsingJavascript(WebElement webelement, String element) {

		try {
			webelement.isDisplayed();
        executeJavaScript("arguments[0].click();", webelement);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			new Logs().console().fail(element + " is not identified due to " + e.getMessage());
			new Logs().file().fail(element + " is not identified due to " + e.getMessage());
		} catch (Exception e) {
			new Logs().console().fail("Unable to click the" +  element  + " due to " + e.getMessage());
			new Logs().file().fail("Unable to click the" +  element  + " due to " + e.getMessage());
		}

	}
	
	//Scrolling within a WebElement Vertically

		public void scrollVertically(WebElement element, int ScrollBy) {
			executeJavaScript("arguments[0].scrollBy(0, arguments[1])", element, ScrollBy);
		}

	//Scrolling within a WebElement Horizontally

		public void scrollHorizontally(WebElement element, int ScrollBy) {

			executeJavaScript("arguments[0].scrollBy(arguments[1], 0)", element, ScrollBy);
		}

}
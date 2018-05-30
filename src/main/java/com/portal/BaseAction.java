package com.portal;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.portal.config.Initializing;

public class BaseAction extends Initializing{
	 static final Logger LOGGER = Logger.getLogger(BaseAction.class);
	 static WebDriver driver;
	 /**
		 * @return the driver
		 */
		public WebDriver getDriver() {
			return driver;
		}
	 
	/**
	 * @param xpath of button
	 */
	public void click(final String xpath) {
		WebElement clickElement = this.getDriver().findElement(By.xpath(xpath));
		if (clickElement != null) {
			clickElement.click();
		}
	}
	
	public void clearInputs() {
		
	}
	
	public void navigate(final String url) {
		this.getDriver().get(url);
	}
	
	/**
	 * @param xpath of input
	 * @param text to enter in field
	 */
	public void enterText(final String xpath,final String text) {
		WebElement clickElement = this.getDriver().findElement(By.xpath(xpath));
		if (clickElement != null) {
			clickElement.clear();
			clickElement.sendKeys(text);
		}
	}
	
	public By locatorValue(String locatorTpye, String value) {
		By by;
		switch (locatorTpye) {
		case "id":
			by = By.id(value);
			break;
		case "name":
			by = By.name(value);
			break;
		case "tagName":
			by = By.tagName(value);
			break;
		case "className":
			by = By.className(value);
			break;
		case "xpath":
			by = By.xpath(value);
			break;
		case "css":
			by = By.cssSelector(value);
			break;
		case "linkText":
			by = By.linkText(value);
			break;
		case "partialLinkText":
			by = By.partialLinkText(value);
			break;
		default:
			by = By.id(value);
			break;
		}
		return by;
	}
	
	/**
	 * @param locatorType
	 * @param value
	 * @param text
	 */
	public void enter_Text(String locatorType, String value, String text) {
		try {
			By locator;
			locator = locatorValue(locatorType, value);
			WebElement element = this.getDriver().findElement(locator);
			element.sendKeys(text);
		} catch (NoSuchElementException e) {
			LOGGER.warn("No Element Found to enter text : " + value);
		}
	}

	/**
	 * @param locatorType
	 * @param value
	 */
	public void click_On_Link(String locatorType, String value) {
		try {
			By locator;
			locator = locatorValue(locatorType, value);
			WebElement element = this.getDriver().findElement(locator);
			element.click();
		} catch (NoSuchElementException e) {
			LOGGER.warn("No Element Found to click on link : " + value);
		}
	}

	/**
	 * Click on particular button.
	 * @param locatorType search by type id,css,tag,xpath,text,name
	 * @param value unique key to identify
	 */
	public void click_On_Button(String locatorType, String value) {
		try {
			By locator;
			locator = locatorValue(locatorType, value);
			WebElement element = this.getDriver().findElement(locator);
			element.click();
		} catch (NoSuchElementException e) {
			LOGGER.warn("No Element Found to perform click : " + value);
		}
	}
	
	public void open_Browser(String browserName) {
		try {
			if (browserName.equalsIgnoreCase("Firefox")) {
				driver = new FirefoxDriver();
			} else if (browserName.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						"C:\\\\development\\\\automation\\\\chromedriver.exe");
				 driver = new ChromeDriver();
			} else if (browserName.equalsIgnoreCase("IE")) {
				System.setProperty("webdriver.ie.driver",
						"D:/Jars/IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			}
		} catch (WebDriverException e) {
			LOGGER.error("Unable to open browser: " + browserName,e);
		}
	}
	
	public static void wait_time(Long fWait) throws Exception{
		Thread.sleep(fWait);
	}
	

}

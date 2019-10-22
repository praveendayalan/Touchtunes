package com.touchtunes.Utility;

import java.sql.DriverAction;
import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.touchtunes.common.BasePage;
import com.touchtunes.Utility.ExtentLogger;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class Helper extends BasePage {
	public static final Properties readInput = LoadProperties.readPropertyFile("\\config.properties");
	public static String appName = readInput.getProperty("appName");
    public static String packageName = readInput.getProperty("packageName");
	public Helper(AndroidDriver<AndroidElement> driver) {
		super(driver);

	}

	public boolean isAppLaunched() {

		return driver.isAppInstalled(packageName);

	}

	public boolean isDisplayed(AndroidElement element, int waitTime) {
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.visibilityOf(element));
		return element.isDisplayed();
	}

	public void assertString(String actual, String expected) {
		Assert.assertEquals(actual, expected);
	}

	public String getText(AndroidElement element) {
		return element.getText();
	}

	public boolean isEnabled(AndroidElement element, int waitTime) {
		Boolean isEnabled = false;
		if (element.isEnabled()) {
			isEnabled = true;
		} else {
			isEnabled = false;
		}
		return isEnabled;
	}

	public void clickElement(AndroidElement element, int waitTime) {
		element.click();
	}

	public void tapElement(AndroidElement element, int waitTime) {
		TouchActions action = new TouchActions(driver);
		action.singleTap(element);
	}

	/*
	 * This method is used to scroll based on a specific text I have used
	 * scrollintoview method of uiScrollable class to perform the scroll operation
	 * untill the text is visible
	 */
	public void Scroll(String inputValue) {
		driver.findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + inputValue + "\"));");

	}

	/*
	 * This method is used to wait for an element which must be clickable and
	 * returns true
	 */
	public boolean waitForElementClickable(AndroidElement element, int waitTime) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e) {
			ExtentLogger.logInfo("Exception is clicking element within 10 seconds ");
		}
		return true;
	}
	
	public boolean adListner(AndroidElement element,int WaitTime) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, WaitTime);
			wait.until(ExpectedConditions.visibilityOf(element));
			this.ClickBack();
		}catch(Exception e) {
			ExtentLogger.logInfo("NO ad Pop-up"+e);
			return false;
		}
		
		return true;
		
	}
	
	public void ClickBack() {
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
	}

	
}

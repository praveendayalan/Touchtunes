package com.touchtunes.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.touchtunes.Utility.ExtentLogger;
import com.touchtunes.Utility.Helper;
import com.touchtunes.common.BasePage;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LandingPage extends BasePage {
	public static String landingPageExpectedTxt = Helper.readInput.getProperty("landingPageExpectedText");
	public static String assameseLanguage = Helper.readInput.getProperty("language");
	public static String numberofLanguageSelected = Helper.readInput.getProperty("selectionNumber");
	Helper helper;

	public LandingPage(AndroidDriver<AndroidElement> driver) {
		super(driver);
		helper = new Helper(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

	}

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='What kinds of music do you like?']")
	private AndroidElement landingPageActualtxt;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Hindi']")
	private AndroidElement hindiWidget;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='0 Selected']")
	private AndroidElement noSelection;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Assamese']")
	private AndroidElement assameseLanguagebtn;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='1 Selected']")
	private AndroidElement selectionNumber;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Done']")
	private AndroidElement doneBtn;

	/*
	 * This method is used to check whether the app is launched or not and if the
	 * app is launched it validates the landing page by asserting a string displayed
	 * in the landing page
	 */
	public void landingPage_Validation() {
		if (helper.isAppLaunched() & helper.isDisplayed(landingPageActualtxt, 20)) {
			
				helper.assertString(helper.getText(landingPageActualtxt), landingPageExpectedTxt);
				ExtentLogger.logInfo(helper.getText(landingPageActualtxt)+ " is displayed in the landing page");
		} else {
			ExtentLogger.logInfo("Exception in launching the app");
		}
	}

	/* This method is used to select or unselect a widget */
	public void checkBox_Validation() {

		helper.clickElement(hindiWidget, 20);

	}

	/*
	 * This method is used to scroll to a particular element and click on the
	 * element
	 */
	public void ScrollAndClick_Validation() {
		helper.Scroll(assameseLanguage);
		if (helper.waitForElementClickable(assameseLanguagebtn, 20))
			helper.clickElement(assameseLanguagebtn, 20);
	}

	/*
	 * This method is used to retrieved the number of selected languages and then
	 * compares with the expected selected language
	 */
	public void numberOfLanguageSelection_Validation() {
		helper.assertString(helper.getText(selectionNumber), numberofLanguageSelected);
		ExtentLogger.logInfo("Number of language selected is "+ helper.getText(selectionNumber));
	}

	/* This method is used to click on the done button */
	public void doneBtn_Validation() {
		if (helper.waitForElementClickable(doneBtn, 10)) {
			helper.clickElement(doneBtn, 10);
		}
		
  

	}

}

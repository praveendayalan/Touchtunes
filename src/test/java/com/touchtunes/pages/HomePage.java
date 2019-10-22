package com.touchtunes.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;

import com.touchtunes.Utility.ExtentLogger;
import com.touchtunes.Utility.Helper;
import com.touchtunes.common.BasePage;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AndroidFindBys;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class HomePage extends BasePage {
	public static String browsePageTxt = Helper.readInput.getProperty("browsePageText");
	boolean adClick;
	Helper helper;

	public HomePage(AndroidDriver<AndroidElement> driver) {
		super(driver);
		helper = new Helper(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//android.view.View[@content-desc='NO THANKS']")
	private AndroidElement adView;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.saavn.android:id/']")
	private AndroidElement HomeScreenTxt;

	@AndroidFindBy(className = "android.widget.LinearLayout[0]")
	private AndroidElement bottomMenuIcon;

	/*
	 * This method extracts the homepage text and compares with a static text which
	 * should be displayed in other pages of the application and also this method
	 * checks for any ad shows up or not, if the ad shows up back button will be pressed and the current page will be displayed
	 */
	public void homePage_validation() {
		do {
			try {
				if (!helper.getText(HomeScreenTxt).equalsIgnoreCase(browsePageTxt)) {
					ExtentLogger.logInfo("The application is navigated to the homepage and "
							+ helper.getText(HomeScreenTxt) + " is displayed");
				}

				adClick = false;
			} catch (NoSuchElementException ne) {
				adClick = helper.adListner(adView, 10);
				if (!adClick)
					throw ne;
			}
		} while (adClick);
	}

}

package com.touchtunes.common;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class BasePage {
	protected AndroidDriver<AndroidElement> driver;

	public BasePage(AndroidDriver<AndroidElement> driver) {

		this.driver = driver;

	}

}

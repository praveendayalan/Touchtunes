package com.touchtunes.common;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.touchtunes.Utility.LoadProperties;
import com.touchtunes.Utility.ExtentLogger;
import com.touchtunes.Utility.Helper;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class BaseTest {
	private static AndroidDriver<AndroidElement> driver;
	protected static AppiumDriverLocalService service;
	public static String emulatorName = Helper.readInput.getProperty("emulatorName");
	public String testName;
	public String testDescription = null;
	

	@BeforeSuite
	public void loadDriver() {
		//service =startServer();
		driver = capabilities();
		driver.resetApp();
		ExtentLogger.startReport();
	}

	/**
	 * @return the service
	 */
//	public static AppiumDriverLocalService getService() {
//		return service;
//	}

	
	@BeforeMethod
	public void startTest(Method method) {
		testName = method.getName();
		Test test = method.getAnnotation(Test.class);
		testDescription = test.description();
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println(testName + " : " + testDescription);
		ExtentLogger.startTest(testName, testDescription);
	}

	@AfterMethod
	public void endTestAndGetResult(ITestResult result) {
		try {
			ExtentLogger.getResults(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterSuite
	public void tearDownDriver() {
		//service.stop();
		ExtentLogger.flushReport();
		ExtentLogger.endTesting("Saavn Execution");
	}

	public AndroidDriver<AndroidElement> capabilities() {
		try {
			File appDir = new File("src");
			File app = new File(appDir, Helper.appName);

			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, emulatorName);
			capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
			capabilities.setCapability("autoDismissAlerts", true);
			capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
			driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

		} catch (Exception e) {
			System.out.print("Exception in the capabilities method" + e);
		}
		return driver;
	}

	public static AndroidDriver<AndroidElement> getDriver() {
		return driver;
	}

//	public static AppiumDriverLocalService startServer() {
//		
//		boolean flag = checkIfServerIsRunnning(4723);
//		if (!flag) {
//
//			service = AppiumDriverLocalService.buildDefaultService();
//			service.start();
//		}
//		return service;
//
//	}

//	public static boolean checkIfServerIsRunnning(int port) {
//
//		boolean isServerRunning = false;
//		ServerSocket serverSocket;
//		try {
//			serverSocket = new ServerSocket(port);
//
//			serverSocket.close();
//		} catch (IOException e) {
//			// If control comes here, then it means that the port is in use
//			isServerRunning = true;
//		} finally {
//			serverSocket = null;
//		}
//		return isServerRunning;
//	}

}

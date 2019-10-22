package com.touchtunes.test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.touchtunes.common.BaseTest;
import com.touchtunes.pages.HomePage;

public class HomePageTest extends BaseTest{
	
	HomePage homePage;
	
	@BeforeClass
	public void initialize() {
		homePage = new HomePage(BaseTest.getDriver());
	}
	
	@Test(description="TC06 Verify whether the application lands on the homepage",groups="HomePageTest",dependsOnGroups="LandingPageTest")
	public void TC_06_homePage_Validation() {
		homePage.homePage_validation();
	}

}

package com.touchtunes.test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.touchtunes.common.BaseTest;
import com.touchtunes.pages.LandingPage;

public class LandingPageTest extends BaseTest{
	LandingPage landingPage;
	@BeforeClass
	public void initialize() {
		
		landingPage = new LandingPage(BaseTest.getDriver());
	}
	
	@Test(description="TC01: Verify whether the app is launched and the landing page is validated",groups="LandingPageTest")
	public void TC_01_landingPage_Validation() {
		
		landingPage.landingPage_Validation();
	}
	
	@Test(description ="TC02: Verify whether the user can unselect the hindi language in the landing screen",groups="LandingPageTest",dependsOnMethods="TC_01_landingPage_Validation")
	public void TC_02_chechBoxValidation() {
		landingPage.checkBox_Validation();
	}
	
	@Test(description="TC03: Verify whether the user can scroll down to the bottom and click the assamese language",groups="LandingPageTest",dependsOnMethods="TC_02_chechBoxValidation")
	public void TC_03_ScrollandClick_Validation() {
		landingPage.ScrollAndClick_Validation();
	}
	
	@Test(description="TC04: Verify whether the number of language selected satifsies the expected selection",groups="LandingPageTest",dependsOnMethods="TC_03_ScrollandClick_Validation")
	public void TC_04_languageSelection_Validation() {
		landingPage.numberOfLanguageSelection_Validation();
	}
	
	@Test(description="TC05: Verify whether the done button is clickable",groups="LandingPageTest",dependsOnMethods="TC_04_languageSelection_Validation")
	public void TC_05_doneBtn_Validation() {
		landingPage.doneBtn_Validation();
	}

}

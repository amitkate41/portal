package com.portal;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CheckIsPortalUpTest extends BaseAction{
	
	@AfterTest
	public void afterTest() {
		this.getDriver().quit();			
	}
	
	@Parameters({ "suite-param" })
	@Test(groups= {"Regression","log-in"},dependsOnMethods = { "navigateToPortal" })
	public void verifyLogin() {
			final String testCasePath = this.getTestCasFilePath("Regression");
			final String testCaseSheetName = Thread.currentThread().getStackTrace()[1].getMethodName();
			executeTestCase(testCasePath, testCaseSheetName);
	}
	
	@Parameters({ "suite-param" })
	@Test(groups= {"Regression","log-in"})
	public void navigateToPortal() {
			final String testCasePath = this.getTestCasFilePath("Regression");
			final String testCaseSheetName = Thread.currentThread().getStackTrace()[1].getMethodName();
			executeTestCase(testCasePath, testCaseSheetName);
	}
	
}

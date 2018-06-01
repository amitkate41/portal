package com.portal;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.portal.config.ConfigFileReader;
import com.portal.config.Initializing;

import executionEngine.KeyWordExecution;

public class CheckIsPortalUpTest extends BaseAction{
	
	@AfterTest
	public void afterTest() {
		this.getDriver().quit();			
	}
	@BeforeSuite
	public void initializeSetup() {
		this.propertyReader = ConfigFileReader.getInstace();
		Initializing.exeKeyDriven = KeyWordExecution.getInstace();

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
		String testCasePath = "navigateToPortal";
		try {
			  testCasePath = this.getTestCasFilePath("Regression");
		}catch(NullPointerException e) {
			LOGGER.error("Exeception :>>>>>>>>>>>>>>>>>>>>>>>>>>>>",e);
		}
			final String testCaseSheetName = Thread.currentThread().getStackTrace()[1].getMethodName();
			executeTestCase(testCasePath, testCaseSheetName);
	}
	
}

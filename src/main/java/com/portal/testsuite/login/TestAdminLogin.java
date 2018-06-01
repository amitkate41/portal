package com.portal.testsuite.login;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.portal.BaseAction;

public class TestAdminLogin extends BaseAction {
	@Test
	public void navigateToAdmin() {
		final String testCasePath = this.getTestCasFilePath("Regression");
		final String testCaseSheetName = Thread.currentThread().getStackTrace()[1].getMethodName();
		executeTestCase(testCasePath, testCaseSheetName);
	}
	@Parameters({ "suite-param" })
	@Test(groups= {"Regression","log-in"},dependsOnMethods = { "navigateToAdmin" })
	public void verifyAdminLogin() {
			final String testCasePath = this.getTestCasFilePath("Regression");
			final String testCaseSheetName = Thread.currentThread().getStackTrace()[1].getMethodName();
			executeTestCase(testCasePath, testCaseSheetName);
			
	}
}
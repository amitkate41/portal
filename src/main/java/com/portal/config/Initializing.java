package com.portal.config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeSuite;

import com.portal.reader.ReadExcel;

import executionEngine.KeyWordExecution;

public class Initializing {
	
	public ConfigFileReader propertyReader;
	public static KeyWordExecution exeKeyDriven;
	

	/**
	 * @return the exeKeyDriven
	 */
	public static KeyWordExecution getExeKeyDriven() {
		return exeKeyDriven;
	}

	/**
	 * @return the propertyReader
	 */
	public ConfigFileReader getPropertyReader() {
		if(propertyReader ==null) {
			initializeSetup();
		}
		return propertyReader;
	}

	@BeforeSuite
	public void initializeSetup() {
		this.propertyReader = ConfigFileReader.getInstace();
		Initializing.exeKeyDriven = KeyWordExecution.getInstace();

	}

	public void executeTestCase(final String testCaseFilePath,final String testCaseName) {
		ReadExcel excelSheet = new ReadExcel();
		excelSheet.openSheet(testCaseFilePath,testCaseName);
		for (int row = 1; row < excelSheet.getRowCount(); row++) {
			List<Object> myParamList = new ArrayList<Object>();
			String methodName = excelSheet.getValueFromCell(3, row);
			for (int col = 4; col < excelSheet.getColumnCount(); col++) {
				if (!excelSheet.getValueFromCell(col, row).isEmpty()
						& !excelSheet.getValueFromCell(col, row).equals("null")) {
					myParamList.add(excelSheet.getValueFromCell(col, row));
				}
			}

			Object[] paramListObject = new String[myParamList.size()];
			paramListObject = myParamList.toArray(paramListObject);

			Initializing.exeKeyDriven.runReflectionMethod("com.portal.BaseAction", methodName, paramListObject);
		}

	}
	
	public String getTestCasFilePath(final String testSuitePath) {

		final String baseFolder = this.getPropertyReader().getTestSuiteBaseDir();
		StringBuilder testCasePath = new StringBuilder(baseFolder);
		testCasePath.append(File.separator);
		testCasePath.append(testSuitePath);
		testCasePath.append(Constants.FILE_EXTENSION);
		return testCasePath.toString();
	}
}

package com.portal.reader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.log4j.Logger;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ReadExcel {
	static final Logger LOGGER = Logger.getLogger(ReadExcel.class);
	Workbook wbWorkbook;
	Sheet shSheet;

	public void openSheet(final String filePath, final String sheetName) {
		FileInputStream fs;
		try {
			fs = new FileInputStream(filePath);
			this.wbWorkbook = Workbook.getWorkbook(fs);
			this.shSheet = wbWorkbook.getSheet(sheetName);

		} catch (FileNotFoundException e) {
			LOGGER.error("File not found on location : " + filePath, e);
		} catch (BiffException | IOException ex) {
			LOGGER.error("unable to read file from location : " + filePath, ex);
		}
	}

	public String getValueFromCell(int iColNumber, int iRowNumber) {
		return shSheet.getCell(iColNumber, iRowNumber).getContents();
	}

	public int getRowCount() {
		return shSheet.getRows();
	}

	public int getColumnCount() {
		return shSheet.getColumns();
	}
}
package com.qa.opencart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ExcelUtil {
	
	public static final String TEST_DATA_SHEET_PATH="";
	
	public void getTestData(String sheetName) {
		System.out.println("Reading the data from sheet :"+ sheetName);
		Object data[][]=null;
		try {
			FileInputStream ip=new FileInputStream(TEST_DATA_SHEET_PATH);
			//WorkbookFactory.create(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	

}

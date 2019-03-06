package com.automation.interfaces;


/**
 * @author Kumar Rohan
 *
 */
public interface FromExcel {
	
	public String[][] getEntireDataFromSheet(String excelName, String sheetName);

	public String getCellValueFromSheet(String excelName, String sheetName, String columnName, int rowNum);

	public String getDataForKnownVal(String excelName, String sheetName, String coulumValue, String knownValue);

	public String getFormData(String dataSet, String column);

}

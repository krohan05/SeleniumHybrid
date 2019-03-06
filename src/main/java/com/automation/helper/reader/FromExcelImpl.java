package com.automation.helper.reader;

import com.automation.interfaces.FromExcel;


/**
 * @author Kumar Rohan
 *
 */
public class FromExcelImpl implements FromExcel {

	ExcelReader excel;

	@Override
	public String[][] getEntireDataFromSheet(String excelName, String sheetName) {
		String path = System.getProperty("user.dir") + "/src/test/resources/data/exceldata/" + excelName;
		excel = new ExcelReader(path);
		String data[][] = excel.getDataFromSheet(sheetName);
		return data;
	}

	@Override
	public String getCellValueFromSheet(String excelName, String sheetName, String columnName, int rowNum) {
		String path = System.getProperty("user.dir") + "/src/test/resources/data/exceldata/" + excelName;
		excel = new ExcelReader(path);
		String data = excel.getCellData(sheetName, columnName, rowNum);
		return data;
	}

	@Override
	public String getDataForKnownVal(String excelName, String sheetName, String coulumValue, String knownValue) {
		String path = System.getProperty("user.dir") + "/src/test/resources/data/exceldata/" + excelName;
		excel = new ExcelReader(path);
		String data = excel.getcolValForOtherKnownVal(sheetName, coulumValue, knownValue);
		return data;
	}

	@Override
	public String getFormData(String dataSet, String column) {
		String path = AppConfig.getConfig("DataSource.excelData");
		String refData[] = dataSet.split("_");
		String refSheet = refData[0];
		String sheetName;

		switch (refSheet) {
		case "Risk":
			sheetName = "RiskFormData";
			break;

		case "Control":
			sheetName = "ControlFormData";
			break;

		case "IUC":
			sheetName = "IUCFormData";
			break;

		case "Asset":
			sheetName = "AssetFormData";
			break;

		case "Vendor":
			sheetName = "VendorFormData";
			break;

		default:
			sheetName = "";
			break;

		}
		String knownValue = refData[1];
		String coulumValue = column;
		excel = new ExcelReader(path);
		String value = excel.getcolValForOtherKnownVal(sheetName, coulumValue, knownValue);
		return value;
	}
}

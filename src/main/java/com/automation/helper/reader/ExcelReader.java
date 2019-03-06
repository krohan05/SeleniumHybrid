package com.automation.helper.reader;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


/**
 * @author Kumar Rohan
 *
 */
public class ExcelReader {
	
	public static final Logger log = Logger.getLogger(ExcelReader.class.getName());

	public FileOutputStream fos = null;
	public FileInputStream fis;
	public Workbook wb;
	public Sheet sheet;
	public Row row;
	public Cell cell;
	public String path;

	public ExcelReader(String path) {
		this.path = path;
		try {
			fis = new FileInputStream(path);
			wb = WorkbookFactory.create(fis);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * This Method will return 2 Dimensional object Data for each record in
	 * excel sheet.
	 * 
	 * @param SheetName
	 * @return
	 * @author t607741
	 */
	@SuppressWarnings("deprecation")
	public String[][] getDataFromSheet(String sheetName) {
		String dataSet[][] = null;

		try {

			sheet = wb.getSheet(sheetName);
			// count number of active rows
			int totalRow = sheet.getLastRowNum();
			// count number of active columns in the row
			int totalColumn = sheet.getRow(0).getLastCellNum();
			// Create array of rows and column
			dataSet = new String[totalRow][totalColumn];
			// Run "for loop" and store data in 2D array
			// This "for loop" will run on "rows"
			for (int i = 1; i <= totalRow; i++) {
				row = sheet.getRow(i);
				// This "for loop" will run on "columns" of that row
				for (int j = 0; j < totalColumn; j++) {
					cell = row.getCell(j);

					if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
						dataSet[i - 1][j] = cell.getStringCellValue();
					} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
						dataSet[i - 1][j] = String.valueOf(cell.getNumericCellValue());
					} else {
						dataSet[i - 1][j] = String.valueOf(cell.getBooleanCellValue());
					}
				}
			}
			return dataSet;

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception in reading excel file: " + e.getMessage());
		}

		return dataSet;
	}

	/**
	 * This will return the data in particular cell of the excel sheet
	 * 
	 * @param sheetName
	 * @param columnName
	 * @param rowNum
	 * @return
	 * @author t607741
	 */

	@SuppressWarnings("deprecation")
	public String getCellData(String sheetName, String columnName, int rowNum) {

		try {
			int colNum = 0;
			sheet = wb.getSheet(sheetName);
			row = sheet.getRow(0);

			for (int i = 0; i < row.getLastCellNum(); i++) {
				if (row.getCell(i).getStringCellValue().equalsIgnoreCase(columnName)) {
					colNum = i;
					break;
				}
			}

			row = sheet.getRow(rowNum - 1);
			cell = row.getCell(colNum);

			if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
				return cell.getStringCellValue();
			}

			else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
				return String.valueOf(cell.getNumericCellValue());
			} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
				return "";
			}
		} catch (Exception e) {

			e.printStackTrace();
			log.info("Exception in reading excel column: " + e.getMessage());
		}

		return null;
	}

	/**
	 * This will give the columnValue for a corresponding knowValue from excel
	 * 
	 * @param sheetName
	 * @param coulumValue
	 * @param knownValue
	 * @return
	 * @author t607741
	 */
	@SuppressWarnings("deprecation")
	public String getcolValForOtherKnownVal(String sheetName, String coulumValue, String knownValue) {

		try {
			int colNum = 0;
			int rowNum = 0;
			sheet = wb.getSheet(sheetName);

			for (int i = 0; i < sheet.getRow(0).getLastCellNum(); i++) {
				if (sheet.getRow(0).getCell(i).getStringCellValue().equalsIgnoreCase(coulumValue)) {
					colNum = i;
					break;
				}
			}

			for (int i = 0; i <= sheet.getLastRowNum(); i++) {
				// for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
				if (sheet.getRow(i).getCell(0).getStringCellValue().equalsIgnoreCase(knownValue)) {
					rowNum = i;
					break;
				}
				// }

			}

			row = sheet.getRow(rowNum);
			cell = row.getCell(colNum);

			if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
				return cell.getStringCellValue();
			}

			else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
				return String.valueOf(cell.getNumericCellValue());
			} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
				return "";
			}
		} catch (Exception e) {

			e.printStackTrace();
			log.info("Exception in reading excel column: " + e.getMessage());
		}

		return null;
	}

}

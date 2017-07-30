/**
 * 
 */
package com.framework.utility;

import java.util.LinkedHashMap;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import com.framework.commans.Commans.DataUtilCommans;
import com.framework.commans.Commans.FactoryHelperCommans;
import com.framework.constants.Constants.DataUtilConstants;

/**
 * @author nikhil
 *
 */
public class DataUtil {
	private XSSFWorkbook workbook;
	/*
	 * Data provider class for reading test data and verify method should run or
	 * not.
	 * 
	 */

	@DataProvider(name = "getData")
	public Object[][] getData() {
		Object[][] data = null;
		try {
			ExcelReader excelread = new ExcelReader();
			String sheetName = FactoryHelperCommans.sheetsName.get(DataUtilCommans.count);
			DataUtilCommans.count++;

			// get number of rows.
			int rows = excelread.getLastRowNumber(sheetName);

			// get number of columns.
			int cols = excelread.getLastColNum(sheetName, 1);

			// get count for Runmode=yes.
			int count = runModeCount(excelread, sheetName, rows);

			// Set Base URL
			DataUtilCommans.BASEURL = excelread.getCellData(sheetName, 1, 0);

			data = new Object[count][1];
			LinkedHashMap<String, String> table = null;
			int value = 0;
			for (int i = 0; i < rows; i++) {
				table = new LinkedHashMap<String, String>();
				if (excelread.getCellData(sheetName, 1, i).toLowerCase().trim()
						.equalsIgnoreCase(DataUtilConstants.RUNMODE.toString())) {
					table = getTestCaseMap(excelread, sheetName, i, cols, table);
					data[value][0] = table;
					value++;
					while (excelread.getCellData(sheetName, 1, i + 1).toLowerCase().trim().equals("")) {
						table = new LinkedHashMap<String, String>();
						if (i + 1 < rows) {
							i++;
							table = getTestCaseMap(excelread, sheetName, i, cols, table);
							data[value][0] = table;
							value++;
						} else {
							break;
						}
					}
				}
			}

			if (data == null) {
				return new Object[0][0];
			}

		} catch (Exception e) {
			System.out.println("Exception" + e);
		}
		return data;
	}

	/*
	 * getTestCaseMap return map of testcase.
	 */
	public LinkedHashMap<String, String> getTestCaseMap(ExcelReader excelread, String sheetName, int row, int cols,
			LinkedHashMap<String, String> table) {

		try {
			for (int j = 0; j < cols; j++) {
				StringBuffer key = new StringBuffer(excelread.getCellData(sheetName, j, 1).toLowerCase().trim());
				StringBuffer value = new StringBuffer(excelread.getCellData(sheetName, j, row).trim());
				table.put(key.toString().trim(), value.toString().trim());
			}
		} catch (Exception e) {
			System.out.println("Exception" + e);
		}
		return table;
	}

	/*
	 * runModeCount return count for runmode=yes.
	 */
	public int runModeCount(ExcelReader excelread, String sheetName, int rows) {
		int count = 0;
		for (int i = 1; i < rows; i++) {
			if (excelread.getCellData(sheetName, 1, i).toLowerCase().trim()
					.equalsIgnoreCase(DataUtilConstants.RUNMODE.toString())) {
				System.out.println("----" + excelread.getCellData(sheetName, 1, i).toLowerCase().trim());
				count++;
				while (excelread.getCellData(sheetName, 1, i + 1).toLowerCase().trim().equals("")) {
					if (i + 1 < rows) {
						System.out.println("----+1" + excelread.getCellData(sheetName, 1, i + 1).toLowerCase().trim());
						i++;
						count++;
					} else {
						break;
					}
				}
			}

		}
		return count;
	}

}
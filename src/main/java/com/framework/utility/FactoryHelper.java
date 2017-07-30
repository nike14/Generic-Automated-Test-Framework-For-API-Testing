/**
 * 
 */
package com.framework.utility;

import com.framework.commans.Commans.FactoryHelperCommans;
import com.framework.constants.Constants.DataUtilConstants;
import com.framework.constants.Constants.FactoryHelperConstants;
import com.framework.epochdate.EpochDate;
import com.framework.inmemorydatabase.InMemoryDatabaseHelper;

/**
 * @author nikhil
 *
 */
public class FactoryHelper {

	public void downloadFile() {
		try {
			DownloadExcelFileHelper.initialMethod();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void createTable() {
		try {
			// TODO Auto-generated method stub
			InMemoryDatabaseHelper inMemoryDatabasehelperObj = new InMemoryDatabaseHelper();
			inMemoryDatabasehelperObj.createTable();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void addEpochDates(String sheetName) {
		try {
			EpochDate epochDateObj = new EpochDate(sheetName);
			epochDateObj.insertStartToday();
			epochDateObj.insertEndToday();
			epochDateObj.insertStartYesterday();
			epochDateObj.insertEndYesterday();
			epochDateObj.insertStartThisWeek();
			epochDateObj.insertStartSevenDays();
			epochDateObj.insertStartThisMonth();
			epochDateObj.insertStartLastMonth();
			epochDateObj.insertEndLastMonth();
			epochDateObj.insertStartCustomRange();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getRunnableSheets() {
		try {
			ExcelReader excelread = new ExcelReader();
			String sheetName = FactoryHelperConstants.SHEETNAME.toString();
			int rows = excelread.getLastRowNumber(sheetName);
			for (int i = 1; i < rows; i++) {
				if (excelread.getCellData(sheetName, 1, i).equalsIgnoreCase(DataUtilConstants.RUNMODE.toString())) {
					FactoryHelperCommans.sheetsName.add(excelread.getCellData(sheetName, 0, i));
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

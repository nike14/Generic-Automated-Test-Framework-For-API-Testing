/**
 * 
 */
package com.framework.testcasesbase.testsuite;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.framework.epochdate.EpochDate;
import com.framework.inmemorydatabase.InMemoryDatabaseHelper;
import com.framework.utility.DownloadExcelFileHelper;
import com.framework.utility.ExcelReader;

/**
 * @author nikhil
 *
 */
public class DownloadFileFromDriveAndSetBaseUrl {

	/*
	 * Download file from google drive.
	 */

	@Test(priority = 1)
	public void downloadFile() {
		try {
			DownloadExcelFileHelper.initialMethod();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test(priority = 2)
	public void createTable() {
		// TODO Auto-generated method stub
		InMemoryDatabaseHelper inMemoryDatabasehelperObj = new InMemoryDatabaseHelper();
		inMemoryDatabasehelperObj.createTable();

	}

	@Test(priority = 30)
	public void addEpochDates() {
		EpochDate epochDateObj = new EpochDate();
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
	}

	@AfterMethod
	public void aftermethod() {
	}

}

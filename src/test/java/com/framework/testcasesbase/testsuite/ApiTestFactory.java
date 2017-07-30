/**
 * 
 */
package com.framework.testcasesbase.testsuite;

import org.apache.http.util.TextUtils;
import org.testng.annotations.Factory;

import com.framework.commans.Commans.FactoryHelperCommans;
import com.framework.constants.Constants.ApiTestFactoryConstants;
import com.framework.constants.Constants.DownloadExcelFileHelperConstants;
import com.framework.testsuite.TestCases;
import com.framework.utility.FactoryHelper;

/**
 * @author nikhil
 *
 */
public class ApiTestFactory {
	FactoryHelper factoryHelperObj = new FactoryHelper();

	@Factory
	public Object[] runSheet() {
		Object[] res = null;
		try {
			// download file from google drive.
			if (!TextUtils.isEmpty(DownloadExcelFileHelperConstants.EXCELFILEPATH.toString()))
				factoryHelperObj.downloadFile();

			// create table.
			factoryHelperObj.createTable();

			// Add epoch dates to database.
			factoryHelperObj.addEpochDates(ApiTestFactoryConstants.SHEETNAME.toString());

			// Get Runnable sheets from excel.
			factoryHelperObj.getRunnableSheets();

			res = new Object[FactoryHelperCommans.sheetsName.size()];
			for (int i = 0; i < FactoryHelperCommans.sheetsName.size(); i++) {
				res[i] = new TestCases();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

}

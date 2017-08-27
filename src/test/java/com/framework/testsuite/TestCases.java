/**
 * 
 */
package com.framework.testsuite;

import java.util.LinkedHashMap;

import org.apache.http.util.TextUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.framework.commans.Commans.DataUtilCommans;
import com.framework.commans.Commans.FactoryHelperCommans;
import com.framework.constants.Constants.ExcelColumnNameConstant;
import com.framework.utility.DataUtil;
import com.framework.utility.ExtentManager;
import com.framework.utility.GetDynamicData;
import com.framework.utility.RestAssuredHelper;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/**
 * @author nikhil
 *
 */
public class TestCases {
	ExtentManager extentReportObj = new ExtentManager();
	GetDynamicData getDynamicData = new GetDynamicData();
	RestAssuredHelper restAssuredHelperObj = new RestAssuredHelper();
	public ExtentReports extentReport;
	public ExtentTest extentTest;

	@Test(priority = 1, dataProvider = "getData", dataProviderClass = DataUtil.class)
	public void testMethod(LinkedHashMap<String, String> data) {
		String sheetName = FactoryHelperCommans.sheetsName.get(DataUtilCommans.count - 1).toLowerCase();
		extentReport = extentReportObj.getInstance(sheetName);
		try {
			if (!TextUtils.isEmpty(data.get(ExcelColumnNameConstant.TESTFLOWNAME.toString()))) {
				extentTest = extentReport.startTest(data.get(ExcelColumnNameConstant.TESTFLOWNAME.toString()));
			}
			extentTest.log(LogStatus.INFO,
					"Test Case Name:->>" + data.get(ExcelColumnNameConstant.TESTCASENAME.toString()));
			getDynamicData.getDynamicValues(data, extentTest, sheetName);
			getDynamicData.assertSingleDynamicValue(data, extentTest);
		} catch (AssertionError e) {
			extentTest.log(LogStatus.FAIL,
					"Fail " + data.get(ExcelColumnNameConstant.TESTCASENAME.toString()) + e.getMessage());
			org.testng.Assert
					.fail("Fail for" + data.get(ExcelColumnNameConstant.TESTCASENAME.toString()) + e.getMessage());
		} catch (Exception e) {
			extentTest.log(LogStatus.FAIL,
					"Fail " + data.get(ExcelColumnNameConstant.TESTCASENAME.toString()) + e.getMessage());
			org.testng.Assert
					.fail("Fail for" + data.get(ExcelColumnNameConstant.TESTCASENAME.toString()) + e.getMessage());
		}
	}

	@AfterMethod
	public void afterMethod() {
		if (extentReport != null) {
			extentReport.endTest(extentTest);
			extentReport.flush();
		}
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		extentReport.close();
	}

}
/**
 * 
 */
package com.framework.testsuite;

import java.util.LinkedHashMap;

import org.apache.http.util.TextUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
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
	public ExtentReports extentReport = extentReportObj.getInstance("API Report");
	public ExtentTest extentTest;
	GetDynamicData getDynamicData = new GetDynamicData();
	RestAssuredHelper restAssuredHelperObj = new RestAssuredHelper();
	/*
	 * 
	 * Get access token for ops user.
	 */

	@Test(dataProvider = "getData", dataProviderClass = DataUtil.class, priority = 1)
	public void testMethod(LinkedHashMap<String, String> data) {
		try {
			if (!TextUtils.isEmpty(data.get("tfnameanddesc"))) {
				extentTest = extentReport.startTest(data.get("tfnameanddesc"));
			}
			extentTest.log(LogStatus.INFO, "Test Case Name:->>" + data.get("tcname"));
			getDynamicData.getDynamicValues(data, extentTest);
			getDynamicData.assertSingleDynamicValue(data, extentTest);
		} catch (AssertionError e) {
			extentTest.log(LogStatus.FAIL, "Fail " + data.get("tcname") + e.getMessage());
			org.testng.Assert.fail("Fail for" + data.get("tcname") + e.getMessage());
		} catch (Exception e) {
			extentTest.log(LogStatus.FAIL, "Fail " + data.get("tcname") + e.getMessage());
			org.testng.Assert.fail("Fail for" + data.get("tcname") + e.getMessage());
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

/**
 * 
 */
package com.framework.restassured;

import java.util.LinkedHashMap;

import com.framework.commans.Commans.DataUtilCommans;
import com.framework.constants.Constants.ExcelColumnNameConstant;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/**
 * @author nikhil
 *
 */

public class APIExecutorHelper {

	/*
	 * setHeaders methods returns the header map for all test cases.
	 */
	public LinkedHashMap<String, String> setHeaders(LinkedHashMap<String, String> data) {

		LinkedHashMap<String, String> headersmap = new LinkedHashMap<String, String>();
		String[] headers = data.get(ExcelColumnNameConstant.TESTHEADERS.toString()).split(";");
		int headersSize = headers.length;
		System.out.println("Total Size" + headersSize);
		for (int i = 0; i < headersSize; i++) {
			String header = headers[i];
			System.out.println("" + header);
			String[] headerdata = header.split(":");
			StringBuffer key = new StringBuffer(headerdata[0].trim());
			StringBuffer value = new StringBuffer(headerdata[1].trim());
			headersmap.put(key.toString(), value.toString());
			System.out.println("key-->" + key + "value-->" + value);
		}
		System.out.println(headersmap);
		return headersmap;

	}

	/*
	 * printTestExecutionTime logs the execution time for all test cases.
	 */
	public static void printTestExecutionTime(long executionTimeInMillis, ExtentTest extentTest) {
		long executionTimeInSeconds = executionTimeInMillis / 1000;
		long executionTimeInMinutes = executionTimeInMillis / 60000;
		long executionTimeInHours = executionTimeInMillis / 3600000;
		if (executionTimeInMillis > 0 && executionTimeInMillis < 1000)
			extentTest.log(LogStatus.INFO, "Test execution time: " + executionTimeInMillis + " ms");
		if (executionTimeInMillis > 1000 && executionTimeInMillis < 60000) {
			if (executionTimeInSeconds == 1)
				extentTest.log(LogStatus.INFO, "Test execution time: " + executionTimeInSeconds + " second");
			else
				extentTest.log(LogStatus.INFO, "Test execution time: " + executionTimeInSeconds + " seconds");
		}
		if (executionTimeInMillis > 60000 && executionTimeInMillis < 3600000) {
			if (executionTimeInMinutes == 1)
				extentTest.log(LogStatus.INFO, "Test execution time: " + executionTimeInMinutes + " minute");
			else
				extentTest.log(LogStatus.INFO, "Test execution time: " + executionTimeInMinutes + " minutes");
		}
		if (executionTimeInMillis > 3600000) {
			if (executionTimeInHours == 1)
				extentTest.log(LogStatus.INFO, "Test execution time: " + executionTimeInHours + " hour");
			else
				extentTest.log(LogStatus.INFO, "Test execution time: " + executionTimeInHours + " hours");
		}
	}

	/*
	 * Create API URL as per selected Environment.
	 * 
	 */
	public String getUrl(LinkedHashMap<String, String> data) {
		String url = DataUtilCommans.BASEURL + data.get(ExcelColumnNameConstant.TESTURL.toString())
				+ data.get(ExcelColumnNameConstant.TESTPARAMETERS.toString());
		return url;
	}

}
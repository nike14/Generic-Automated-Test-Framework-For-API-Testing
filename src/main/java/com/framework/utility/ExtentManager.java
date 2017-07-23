/**
 * 
 */
package com.framework.utility;

import java.io.File;
import java.util.Date;

import com.framework.constants.Constants;
import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

/**
 * @author nikhil
 *
 */
public class ExtentManager {
	private ExtentReports extent;

	/*
	 * Create Extent report instance and report path.
	 * 
	 */

	public ExtentReports getInstance(String testName){
		if (extent == null) {
			Date date = new Date();
			String fileName = date.toString().replace(":", "_").replace(" ", "_") +"_"+testName+"_"+".html";
			String reportPath = Constants.REPORTS_PATH + fileName;

			extent = new ExtentReports(reportPath, true, DisplayOrder.NEWEST_FIRST);

			extent.loadConfig(new File(System.getProperty("user.dir") + "//ReportsConfig.xml"));
		}
		return extent;
	}

}

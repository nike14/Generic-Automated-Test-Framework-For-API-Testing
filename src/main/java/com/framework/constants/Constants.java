/**
 * 
 */
package com.framework.constants;

import java.util.LinkedHashMap;

/**
 * @author nikhil
 *
 */
public class Constants {
	public static final String PROJECT_PATH = System.getProperty("user.dir");
	public static final String PROJECT_OS = System.getProperty("os.name");

	public static final String EXCEL_PATH = PROJECT_PATH + java.io.File.separatorChar + "src"
			+ java.io.File.separatorChar + "test" + java.io.File.separatorChar + "resources"
			+ java.io.File.separatorChar + "ExcelData" + java.io.File.separatorChar + "FrameworkSheet.xlsx";

	public static final String SCHEMA_PATH = PROJECT_PATH + java.io.File.separatorChar + "target"
			+ java.io.File.separatorChar + "test-classes" + java.io.File.separatorChar + "schemas"
			+ java.io.File.separatorChar;

	public static final String LOG4J_PATH = PROJECT_PATH + java.io.File.separatorChar + "log4j.properties";
	public static final StringBuffer TESTSUITE = new StringBuffer("TestSuite");
	public static final StringBuffer TESTFLOW = new StringBuffer("TestFlows");
	public static final StringBuffer TESTDATA = new StringBuffer("TestData");
	public static final StringBuffer TSNAME = new StringBuffer("TSName");
	public static final StringBuffer TFNAME = new StringBuffer("TFName");
	public static final StringBuffer RUNMODE = new StringBuffer("RunMode");
	public static LinkedHashMap<String, String> testDataMap = new LinkedHashMap<String, String>();
	public static final StringBuffer RUNMODEVALUE = new StringBuffer("Yes");
	public static final String REPORTS_PATH = PROJECT_PATH + java.io.File.separatorChar + "Reports"
			+ java.io.File.separatorChar;

	public static class DataUtilConstants {
		public static final StringBuffer RUNMODE = new StringBuffer("Yes");
	}

	public static class DownloadExcelFileHelperConstants {
		public static final StringBuffer EXCELFILEPATH = new StringBuffer(
				"1sWxJZdDyPDDvER8guj-ILtI4gaRcgIOQm45V7kCr6Y4");
		public static final StringBuffer CLIENTSECRETJSON = new StringBuffer("/client_secret.json");
		public static final StringBuffer FILETYPE = new StringBuffer(
				"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		public static final StringBuffer APPLICATIONNAME = new StringBuffer("Drive API Quickstart");
		public static final StringBuffer LOCATIONOFCREDENTIAL = new StringBuffer(
				".credentials/loaction/drive-java-quickstart.json");
	}

	public static class ApiExecutorConstants {

		public static final StringBuffer CONTENTTYPE = new StringBuffer("application/json");
		public static final StringBuffer JSONOBJCTRESPONCEDATA = new StringBuffer("responseData");
		public static final StringBuffer ACCESSTOKEN = new StringBuffer("X-Authorization-Token");
		public static final StringBuffer USERNAME = new StringBuffer("username");
		public static final String INVALIDTOKEN = "invalidtoken";
		public static final String EXTENDTOKENLENGTH = "extendtokenlength";
		public static final String EXPIRETOKEN = "expiretoken";
		public static final String EXPIRETOKENVALUE = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZGVudGl0eSI6IjBhN2U2YjQ1LTNjMzctNDBkZS04YjVjLTU4MzQ2OTQxY2EyMyIsImlhdCI6MTQ2MzQxMTkyNiwibmJmIjoxNDYzNDExOTI2LCJleHAiOjE0NjM0NDA3MjZ9.6MttQU5mKBeCOVuFddtSTQWVEb7BfM37IIezskXzajM";
	}

	public static class RestAssuredHelperConstant {
		public static final String POST = "post";
		public static final String GET = "get";
		public static final String PUT = "put";
		public static final String DELETE = "delete";
		public static final String SETACCESSTOKEN = "setaccesstoken";
	}

	public static class GetAccessTokenConstant {
		public static final StringBuffer URL = new StringBuffer("url");
		public static final StringBuffer INPUTJSON = new StringBuffer("input json");
		public static final StringBuffer HEADERS = new StringBuffer("headers");
		public static final StringBuffer PARAMETERS = new StringBuffer("parameters");
		public static final StringBuffer ASSERTRESPONSE = new StringBuffer("assert response");
	}

	public static class InMemoryDatabaseHelperConstant {
		public static final StringBuffer HSQLDBJDBCDRIVER = new StringBuffer("org.hsqldb.jdbc.JDBCDriver");
		public static final StringBuffer CREATINGCONNECTIONFORHSQLDB = new StringBuffer("jdbc:hsqldb:mem:.");
		public static final StringBuffer TABLENAME = new StringBuffer("testcase_response");
		public static final StringBuffer TESTCASEID = new StringBuffer("tcid");
		public static final StringBuffer PATHVALUE = new StringBuffer("pathvalue");
		public static final StringBuffer RESPONSEVALUE = new StringBuffer("responsevalue");

	}

	public static class RestUtilConstant {
		public static final StringBuffer RESTUTILCLASSNAME = new StringBuffer(
				"com.framework.utility.GetJsonValuesFromResponse");
	}

	public static class EpochDateHelperConstant {
		public static final String TESTCASEID = "0";
		public static final String STARTTODAY = "startdatetoday";
		public static final String ENDTODAY = "enddatetoday";
		public static final String STARTYESTERDAY = "startdateyesterday";
		public static final String ENDYESTERDAY = "enddateyesterday";
		public static final String STARTTHISWEEK = "startdatethisweek";
		public static final String STARTLASTSEVENDAYS = "startdatelastsevendays";
		public static final String STARTTHISMONTH = "startdatethismonth";
		public static final String STARTLASTMONTH = "startdatelastmonth";
		public static final String ENDLASTMONTH = "enddatelastmonth";
		public static final String STARTCUSTOMRANGE = "startdatecustomrange";
	}
}

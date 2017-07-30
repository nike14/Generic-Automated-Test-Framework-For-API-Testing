/**
 * 
 */
package com.framework.constants;

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

	public static final String REPORTS_PATH = PROJECT_PATH + java.io.File.separatorChar + "Reports"
			+ java.io.File.separatorChar;

	public static class DataUtilConstants {
		public static final StringBuffer RUNMODE = new StringBuffer("Yes");
	}

	public static class FactoryHelperConstants {
		public static final StringBuffer SHEETNAME = new StringBuffer("testsuite");
	}
	
	public static class ApiTestFactoryConstants{
		public static final StringBuffer SHEETNAME = new StringBuffer("epoch");
	}

	public static class DownloadExcelFileHelperConstants {
		public static final StringBuffer EXCELFILEPATH = new StringBuffer(
				"");
		public static final StringBuffer CLIENTSECRETJSON = new StringBuffer("/client_secret.json");
		public static final StringBuffer FILETYPE = new StringBuffer(
				"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		public static final StringBuffer APPLICATIONNAME = new StringBuffer("Drive API Quickstart");
		public static final StringBuffer LOCATIONOFCREDENTIAL = new StringBuffer(
				".credentials/loaction/drive-java-quickstart.json");
	}

	public static class ApiExecutorConstants {
		public static final StringBuffer CONTENTTYPE = new StringBuffer("application/json");
	}

	public static class RestAssuredHelperConstant {
		public static final String POST = "post";
		public static final String GET = "get";
		public static final String PUT = "put";
		public static final String DELETE = "delete";
		public static final String PATCH = "patch";
	}

	public static class ExcelColumnNameConstant {
		public static final StringBuffer TESTID = new StringBuffer("test id");
		public static final StringBuffer TESTMODE = new StringBuffer("test mode");
		public static final StringBuffer TESTFLOWNAME = new StringBuffer("test flow name");
		public static final StringBuffer TESTCASENAME = new StringBuffer("test case name");
		public static final StringBuffer TESTAPITYPE = new StringBuffer("test api type");
		public static final StringBuffer TESTURL = new StringBuffer("test url");
		public static final StringBuffer TESTINPUTJSON = new StringBuffer("test input json");
		public static final StringBuffer TESTHEADERS = new StringBuffer("test headers");
		public static final StringBuffer TESTSCHEMANAME = new StringBuffer("test schema name");
		public static final StringBuffer TESTEXPECTEDSTATUSCODE = new StringBuffer("test expected status code");
		public static final StringBuffer TESTPARAMETERS = new StringBuffer("test parameters");
		public static final StringBuffer TESTMETHODANDJSONPATH = new StringBuffer("test method and json path");
		public static final StringBuffer TESTASSERTRESPONSE = new StringBuffer("test assert response");
	}

	public static class InMemoryDatabaseHelperConstant {
		public static final StringBuffer HSQLDBJDBCDRIVER = new StringBuffer("org.hsqldb.jdbc.JDBCDriver");
		public static final StringBuffer CREATINGCONNECTIONFORHSQLDB = new StringBuffer("jdbc:hsqldb:mem:.");
		public static final StringBuffer TABLENAME = new StringBuffer("testcase_response");
		public static final StringBuffer TESTCASEID = new StringBuffer("tcid");
		public static final StringBuffer SHEETNAME = new StringBuffer("sheetname");
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

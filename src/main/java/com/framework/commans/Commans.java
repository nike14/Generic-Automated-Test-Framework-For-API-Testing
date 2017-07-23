/**
 * 
 */
package com.framework.commans;

import java.sql.Connection;
import java.util.LinkedHashMap;

/**
 * @author nikhil
 *
 */
public class Commans {

	public static class DataUtilCommans {
		public static String BASEURL = "";
	}

	public static class ApiExecutorCommans {
		public static LinkedHashMap<String, String> ACCESSTOKENUSERNAMEANDACCESSTOKENVALUE = new LinkedHashMap<String, String>();
	}

	public static class InMemoryDatabaseHelperCommans {
		public static Connection connectionObj = null;
	}
}

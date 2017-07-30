/**
 * 
 */
package com.framework.commans;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * @author nikhil
 *
 */
public class Commans {

	public static class DataUtilCommans {
		public static String BASEURL = "";
		public static int count = 0;

	}

	public static class InMemoryDatabaseHelperCommans {
		public static Connection connectionObj = null;
	}

	public static class FactoryHelperCommans {
		public static List<String> sheetsName = new ArrayList<String>();
	}

}

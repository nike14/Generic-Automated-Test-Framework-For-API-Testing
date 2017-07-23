/**
 * 
 */
package com.framework.inmemorydatabase;


import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.framework.commans.Commans.InMemoryDatabaseHelperCommans;
import com.framework.constants.Constants.InMemoryDatabaseHelperConstant;

/**
 * @author nikhil
 *
 */
public class InMemoryDatabaseHelper {

	Statement stmt;
	int result = 0;

	/*
	 * create connection to hsqldb database.
	 */
	public void createConnection() {
		try {
			// Registering the HSQLDB JDBC driver
			Class.forName(InMemoryDatabaseHelperConstant.HSQLDBJDBCDRIVER.toString());
			// Creating the connection with HSQLDB
			InMemoryDatabaseHelperCommans.connectionObj = DriverManager
					.getConnection(InMemoryDatabaseHelperConstant.CREATINGCONNECTIONFORHSQLDB.toString());
		} catch (Exception e) {
			System.out.println("Exception in Database Connection" + e);
		}
	}

	/*
	 * Create table for storing data to database.
	 */
	public void createTable() {
		try {
			createConnection();
			stmt = InMemoryDatabaseHelperCommans.connectionObj.createStatement();
			result = stmt.executeUpdate("CREATE TABLE " + InMemoryDatabaseHelperConstant.TABLENAME.toString() + "("
					+ InMemoryDatabaseHelperConstant.TESTCASEID.toString() + " int,"
					+ InMemoryDatabaseHelperConstant.PATHVALUE.toString() + " VARCHAR(50),"
					+ InMemoryDatabaseHelperConstant.RESPONSEVALUE.toString() + " VARCHAR(1000) NOT NULL);");

		} catch (Exception e) {
			System.out.println("Exception in create Table for Database:" + e);
		}

	}

	/*
	 * Create data to created table.
	 */
	public void createData(String testCaseName, String path, String responseValue, String tableName) {
		try {
			stmt = InMemoryDatabaseHelperCommans.connectionObj.createStatement();
			result = stmt.executeUpdate("INSERT INTO " + tableName + " VALUES ('" + testCaseName + "','" + path + "','"
					+ responseValue + "')");
		} catch (Exception e) {
			System.out.println("Exception in create data to Database:" + e);
		}
	}

	/*
	 * Get data from database.
	 */
	public String getDataFromDataBase(String testCaseid, String pathValue) throws SQLException {
		ResultSet resultFromTable = null;
		try {
			stmt = InMemoryDatabaseHelperCommans.connectionObj.createStatement();
			resultFromTable = stmt.executeQuery("select " + InMemoryDatabaseHelperConstant.TESTCASEID.toString() + ","
					+ InMemoryDatabaseHelperConstant.PATHVALUE.toString() + ","
					+ InMemoryDatabaseHelperConstant.RESPONSEVALUE.toString() + " from "
					+ InMemoryDatabaseHelperConstant.TABLENAME.toString() + " where "
					+ InMemoryDatabaseHelperConstant.TESTCASEID.toString() + "='" + testCaseid + "'  AND "
					+ InMemoryDatabaseHelperConstant.PATHVALUE.toString() + "='" + pathValue + "'");

			while (resultFromTable.next()) {
				System.out.println(resultFromTable.getString(InMemoryDatabaseHelperConstant.TESTCASEID.toString())
						+ " |" + resultFromTable.getString(InMemoryDatabaseHelperConstant.PATHVALUE.toString()) + "|"
						+ resultFromTable.getString(InMemoryDatabaseHelperConstant.RESPONSEVALUE.toString()));
				return resultFromTable.getString(InMemoryDatabaseHelperConstant.RESPONSEVALUE.toString());
			}

		} catch (Exception e) {
			System.out.println("Exception in get data from Database:" + e);
		}
		return resultFromTable.getString(InMemoryDatabaseHelperConstant.RESPONSEVALUE.toString());
	}

}

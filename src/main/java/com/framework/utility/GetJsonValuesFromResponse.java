/**
 * 
 */
package com.framework.utility;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import com.framework.constants.Constants.InMemoryDatabaseHelperConstant;
import com.framework.inmemorydatabase.InMemoryDatabaseHelper;
import com.google.common.base.Joiner;
import com.jayway.jsonpath.JsonPath;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.response.Response;

/**
 * @author nikhil
 *
 */
public class GetJsonValuesFromResponse {
	InMemoryDatabaseHelper inMemoryDatabasehelperObj = new InMemoryDatabaseHelper();

	/*
	 * Get path name for storing in database.
	 */
	public String getJsonPath(String jsonPath, ExtentTest extentTest) {
		String result = "";
		try {
			result = jsonPath.substring(jsonPath.lastIndexOf('.') + 1).trim().toLowerCase();
		} catch (Exception e) {
			extentTest.log(LogStatus.ERROR, "Exception for getJsonPath:" + e);
		}
		return result;
	}

	/*
	 * Return List of String from response. Use for reading response
	 * dynamically.
	 * 
	 */

	public void extractStringList(Response response, String jsonPath, LinkedHashMap<String, String> data,
			ExtentTest extentTest) {
		try {
			String path = getJsonPath(jsonPath, extentTest);
			List<String> listValues = JsonPath.read(response.getBody().asString(), jsonPath);
			String values = String.join(",", listValues);
			inMemoryDatabasehelperObj.createData(data.get("tcid"), path, values,
					InMemoryDatabaseHelperConstant.TABLENAME.toString());
			System.out.println(values);
		} catch (Exception e) {
			extentTest.log(LogStatus.ERROR, "Exception for extractStringList:" + e);
		}
	}

	/*
	 * Return Number List from response. Use for reading response dynamically.
	 * 
	 */

	public void extractNumberList(Response response, String jsonPath, LinkedHashMap<String, String> data,
			ExtentTest extentTest) {
		try {
			String path = getJsonPath(jsonPath, extentTest);
			List<Number> numberlist = JsonPath.read(response.getBody().asString(), jsonPath);
			String values = Joiner.on(',').join(numberlist);
			inMemoryDatabasehelperObj.createData(data.get("tcid"), path, values,
					InMemoryDatabaseHelperConstant.TABLENAME.toString());
			System.out.println(values);
		} catch (Exception e) {
			extentTest.log(LogStatus.ERROR, "Exception for extractNumberList:" + e);
		}
	}

	/*
	 * Return Long List from response. Use for reading response dynamically.
	 * 
	 */

	public void extractLongList(Response response, String jsonPath, LinkedHashMap<String, String> data,
			ExtentTest extentTest) {
		try {
			String path = getJsonPath(jsonPath, extentTest);
			List<Long> longList = JsonPath.read(response.getBody().asString(), jsonPath);
			String values = Joiner.on(',').join(longList);
			inMemoryDatabasehelperObj.createData(data.get("tcid"), path, values,
					InMemoryDatabaseHelperConstant.TABLENAME.toString());
			System.out.println(values);
		} catch (Exception e) {
			extentTest.log(LogStatus.ERROR, "Exception for extractLongList:" + e);
		}
	}

	/*
	 * Return Number from response. Use for reading response dynamically.
	 * 
	 */
	public void extractNumber(Response response, String jsonPath, LinkedHashMap<String, String> data,
			ExtentTest extentTest) {
		try {
			String path = getJsonPath(jsonPath, extentTest);
			Number numberValue = JsonPath.read(response.getBody().asString(), jsonPath);
			String number = numberValue.toString();
			inMemoryDatabasehelperObj.createData(data.get("tcid"), path, number,
					InMemoryDatabaseHelperConstant.TABLENAME.toString());
			System.out.println(number);
		} catch (Exception e) {
			extentTest.log(LogStatus.ERROR, "Exception for extractNumber:" + e);
		}
	}

	/*
	 * Return String from response. Use for reading response dynamically.
	 * 
	 */

	public void extractString(Response response, String jsonPath, LinkedHashMap<String, String> data,
			ExtentTest extentTest) {
		try {
			String path = getJsonPath(jsonPath, extentTest);
			String value = JsonPath.read(response.getBody().asString(), jsonPath);
			inMemoryDatabasehelperObj.createData(data.get("tcid"), path, value,
					InMemoryDatabaseHelperConstant.TABLENAME.toString());
			System.out.println(value);
		} catch (Exception e) {
			extentTest.log(LogStatus.ERROR, "Exception for extractString:" + e);
		}
	}

	/*
	 * Return boolean value from response. Use for reading response dynamically.
	 * 
	 */
	public void extractBoolean(Response response, String jsonPath, LinkedHashMap<String, String> data,
			ExtentTest extentTest) {
		try {
			String path = getJsonPath(jsonPath, extentTest);
			Boolean booleanValue = JsonPath.read(response.getBody().asString(), jsonPath);
			String value = String.valueOf(booleanValue);
			inMemoryDatabasehelperObj.createData(data.get("tcid"), path, value,
					InMemoryDatabaseHelperConstant.TABLENAME.toString());
			System.out.println(value);
		} catch (Exception e) {
			extentTest.log(LogStatus.ERROR, "Exception for extractBooleanValue:" + e);
		}
	}

	/*
	 * Return Long value from response. Use for reading response dynamically.
	 */
	public void extractLong(Response response, String jsonPath, LinkedHashMap<String, String> data,
			ExtentTest extentTest) {
		try {
			String path = getJsonPath(jsonPath, extentTest);
			Long longvValue = JsonPath.read(response.getBody().asString(), jsonPath);
			String value = Long.toString(longvValue);
			inMemoryDatabasehelperObj.createData(data.get("tcid"), path, value,
					InMemoryDatabaseHelperConstant.TABLENAME.toString());
			System.out.println(value);
		} catch (Exception e) {
			extentTest.log(LogStatus.ERROR, "Exception for extractLong:" + e);
		}
	}

	/*
	 * Return List of array list from response. Use for reading response
	 * dynamically.
	 * 
	 */

	public void extractListOfLists(Response response, String jsonPath, LinkedHashMap<String, String> data,
			ExtentTest extentTest) {
		try {
			String path = getJsonPath(jsonPath, extentTest);
			List<ArrayList<String>> arrayList = JsonPath.read(response.getBody().asString(), jsonPath);
			String values = Joiner.on(',').join(arrayList);
			inMemoryDatabasehelperObj.createData(data.get("tcid"), path, values,
					InMemoryDatabaseHelperConstant.TABLENAME.toString());
			System.out.println(values);
		} catch (Exception e) {
			extentTest.log(LogStatus.ERROR, "Exception for extractListWithJsonPathForListOfLists:" + e);
		}

	}

}

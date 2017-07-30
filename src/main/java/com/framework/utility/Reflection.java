/**
 * 
 */
package com.framework.utility;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import com.framework.constants.Constants.ExcelColumnNameConstant;
import com.framework.constants.Constants.RestUtilConstant;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.response.Response;

/**
 * @author nikhil
 *
 */
public class Reflection {

	String restUtilClassName;
	Class<?> restutilClass = null;
	Object restUtil = null;

	public Reflection() {
		restUtilClassName = RestUtilConstant.RESTUTILCLASSNAME.toString();
		try {
			// convert string classname to class
			restutilClass = Class.forName(restUtilClassName);
			// invoke empty constructor
			restUtil = restutilClass.newInstance();
		} catch (Exception e) {
			System.out.println("Exception for Reflection---->" + e);
		}

	}

	/*
	 * separateMethodAndJsonPath method seprate the method and json path.
	 */
	public LinkedHashMap<String, String> separateMethodAndJsonPath(LinkedHashMap<String, String> data,
			ExtentTest extentTest) {
		LinkedHashMap<String, String> methodAndJsonPathMap = new LinkedHashMap<String, String>();
		try {
			String[] methodsAndJsonPaths = data.get(ExcelColumnNameConstant.TESTMETHODANDJSONPATH.toString())
					.split(";");
			int methodsAndJsonPathsSize = methodsAndJsonPaths.length;
			System.out.println("Total Size" + methodsAndJsonPathsSize);
			for (int i = 0; i < methodsAndJsonPathsSize; i++) {
				String[] methodAndJsonPath = methodsAndJsonPaths[i].split(":");
				String method = methodAndJsonPath[0];
				String jsonPath = methodAndJsonPath[1];
				methodAndJsonPathMap.put(jsonPath, method);
			}
		} catch (Exception e) {
			extentTest.log(LogStatus.ERROR, "Exception:" + e);
		}
		return methodAndJsonPathMap;
	}

	/*
	 * invokeReflection call the helper and separate multiple method calls.
	 */
	public void invokeReflection(LinkedHashMap<String, String> data, LinkedHashMap<String, String> methodAndJsonPathMap,
			Response resp, ExtentTest extentTest,String sheetName) {
		try {
			for (Entry<String, String> entry : methodAndJsonPathMap.entrySet()) {
				String jsonPath = entry.getKey();
				String methodName = entry.getValue();
				reflectionHelper(data, methodName, jsonPath, resp, extentTest,sheetName);
			}
		} catch (Exception e) {
			extentTest.log(LogStatus.ERROR, "Exception:" + e);
		}
	}

	/*
	 * Reflection helps to call method runtime.
	 */
	public void reflectionHelper(LinkedHashMap<String, String> data, String methodName, String jsonPath, Response resp,
			ExtentTest extentTest,String sheetName) {
		try {
			Method setNameMethod = restUtil.getClass().getMethod(methodName, Response.class, String.class,
					LinkedHashMap.class, ExtentTest.class,String.class);
			setNameMethod.invoke(restUtil, resp, jsonPath, data, extentTest,sheetName);
		} catch (Exception e) {
			extentTest.log(LogStatus.ERROR, "Exception for reflection:" + e);
		}
	}

}
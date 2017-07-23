/**
 * 
 */
package com.framework.utility;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.http.util.TextUtils;
import org.testng.Assert;

import com.framework.constants.Constants.RestAssuredHelperConstant;
import com.framework.restassured.ApiExecutor;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.response.Response;

/**
 * @author nikhil
 *
 */
public class RestAssuredHelper {

	ApiExecutor apiObj = new ApiExecutor();
	Reflection reflectionObj = new Reflection();

	public void apiExecutorHelper(LinkedHashMap<String, String> data, ExtentTest extentTest) {

		Response resp;
		String apiType = data.get("api type");
		int expectedResponseCode = Integer.parseInt(data.get("expected status code"));
		switch (apiType.toLowerCase()) {

		case RestAssuredHelperConstant.GET:
			resp = apiObj.apiGet(data, extentTest);
			Assert.assertEquals(resp.getStatusCode(), expectedResponseCode);
			if (!TextUtils.isEmpty(data.get("method and json path"))) {
				LinkedHashMap<String, String> methodAndJsonPathMap = reflectionObj.separateMethodAndJsonPath(data,
						extentTest);
				reflectionObj.invokeReflection(data, methodAndJsonPathMap, resp, extentTest);
			}
			break;

		case RestAssuredHelperConstant.POST:
			resp = apiObj.apiPost(data, extentTest);
			Assert.assertEquals(resp.getStatusCode(), expectedResponseCode);
			if (!TextUtils.isEmpty(data.get("method and json path"))) {
				LinkedHashMap<String, String> methodAndJsonPathMap = reflectionObj.separateMethodAndJsonPath(data,
						extentTest);
				reflectionObj.invokeReflection(data, methodAndJsonPathMap, resp, extentTest);
			}
			break;
		case RestAssuredHelperConstant.PUT:
			resp = apiObj.apiPut(data, extentTest);
			Assert.assertEquals(resp.getStatusCode(), expectedResponseCode);
			if (!TextUtils.isEmpty(data.get("method and json path"))) {
				LinkedHashMap<String, String> methodAndJsonPathMap = reflectionObj.separateMethodAndJsonPath(data,
						extentTest);
				reflectionObj.invokeReflection(data, methodAndJsonPathMap, resp, extentTest);
			}
			break;

		case RestAssuredHelperConstant.DELETE:
			resp = apiObj.apiDelete(data, extentTest);
			Assert.assertEquals(resp.getStatusCode(), expectedResponseCode);
			if (!TextUtils.isEmpty(data.get("method and json path"))) {
				LinkedHashMap<String, String> methodAndJsonPathMap = reflectionObj.separateMethodAndJsonPath(data,
						extentTest);
				reflectionObj.invokeReflection(data, methodAndJsonPathMap, resp, extentTest);
			}
			break;

		default:
			extentTest.log(LogStatus.ERROR, "No Case Matched For API Type:");
			break;
		}
	}

}

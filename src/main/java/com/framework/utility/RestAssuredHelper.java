/**
 * 
 */
package com.framework.utility;

import java.util.LinkedHashMap;
import org.apache.http.util.TextUtils;
import org.testng.Assert;
import com.framework.constants.Constants.ExcelColumnNameConstant;
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

	public void apiExecutorHelper(LinkedHashMap<String, String> data, ExtentTest extentTest, String sheetName) {

		Response resp;
		String apiType = data.get(ExcelColumnNameConstant.TESTAPITYPE.toString());
		int expectedResponseCode = Integer
				.parseInt(data.get(ExcelColumnNameConstant.TESTEXPECTEDSTATUSCODE.toString()));
		switch (apiType.toLowerCase()) {

		case RestAssuredHelperConstant.GET:
			resp = apiObj.apiGet(data, extentTest);
			if (resp != null) {
				Assert.assertEquals(resp.getStatusCode(), expectedResponseCode);
				if (!TextUtils.isEmpty(data.get(ExcelColumnNameConstant.TESTMETHODANDJSONPATH.toString()))) {
					LinkedHashMap<String, String> methodAndJsonPathMap = reflectionObj.separateMethodAndJsonPath(data,
							extentTest);
					reflectionObj.invokeReflection(data, methodAndJsonPathMap, resp, extentTest, sheetName);
				}
			}
			break;

		case RestAssuredHelperConstant.POST:
			resp = apiObj.apiPost(data, extentTest);
			if (resp != null) {
				Assert.assertEquals(resp.getStatusCode(), expectedResponseCode);
				if (!TextUtils.isEmpty(data.get(ExcelColumnNameConstant.TESTMETHODANDJSONPATH.toString()))) {
					LinkedHashMap<String, String> methodAndJsonPathMap = reflectionObj.separateMethodAndJsonPath(data,
							extentTest);
					reflectionObj.invokeReflection(data, methodAndJsonPathMap, resp, extentTest, sheetName);
				}
			}
			break;
		case RestAssuredHelperConstant.PUT:
			resp = apiObj.apiPut(data, extentTest);
			if (resp != null) {
				Assert.assertEquals(resp.getStatusCode(), expectedResponseCode);
				if (!TextUtils.isEmpty(data.get(ExcelColumnNameConstant.TESTMETHODANDJSONPATH.toString()))) {
					LinkedHashMap<String, String> methodAndJsonPathMap = reflectionObj.separateMethodAndJsonPath(data,
							extentTest);
					reflectionObj.invokeReflection(data, methodAndJsonPathMap, resp, extentTest, sheetName);
				}
			}
			break;

		case RestAssuredHelperConstant.DELETE:
			resp = apiObj.apiDelete(data, extentTest);
			if (resp != null) {
				Assert.assertEquals(resp.getStatusCode(), expectedResponseCode);
				if (!TextUtils.isEmpty(data.get(ExcelColumnNameConstant.TESTMETHODANDJSONPATH.toString()))) {
					LinkedHashMap<String, String> methodAndJsonPathMap = reflectionObj.separateMethodAndJsonPath(data,
							extentTest);
					reflectionObj.invokeReflection(data, methodAndJsonPathMap, resp, extentTest, sheetName);
				}
			}
			break;

		case RestAssuredHelperConstant.PATCH:
			resp = apiObj.apiPatch(data, extentTest);
			if (resp != null) {
				Assert.assertEquals(resp.getStatusCode(), expectedResponseCode);
				if (!TextUtils.isEmpty(data.get(ExcelColumnNameConstant.TESTMETHODANDJSONPATH.toString()))) {
					LinkedHashMap<String, String> methodAndJsonPathMap = reflectionObj.separateMethodAndJsonPath(data,
							extentTest);
					reflectionObj.invokeReflection(data, methodAndJsonPathMap, resp, extentTest, sheetName);
				}
			}
			break;

		default:
			extentTest.log(LogStatus.ERROR, "No Case Matched For API Type:");
			break;
		}
	}

}
/**
 * 
 */
package com.framework.restassured;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.LinkedHashMap;
import org.apache.http.util.TextUtils;
import org.testng.SkipException;

import com.framework.constants.Constants.ApiExecutorConstants;
import com.framework.constants.Constants.ExcelColumnNameConstant;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * @author nikhil
 *
 */

public class ApiExecutor {
	RestAssuredConfig config = RestAssured.config()
			.httpClient(HttpClientConfig.httpClientConfig().setParam("CONNECTION_MANAGER_TIMEOUT", 3000));

	/*
	 * Rest-Assured is using given/when/then notation. In below section we
	 * declare things like content type or request body. below method is use for
	 * getapi.
	 */
	public Response apiGet(LinkedHashMap<String, String> data, ExtentTest extentTest) {
		String url = APIExecutorHelper.getUrl(data);
		extentTest.log(LogStatus.INFO, "URL-->" + url);
		extentTest.log(LogStatus.INFO, "Input json" + data.get(ExcelColumnNameConstant.TESTINPUTJSON.toString()));
		Response resp = null;
		try {
			RequestSpecification rs = given().contentType(ApiExecutorConstants.CONTENTTYPE.toString())
					.body(data.get(ExcelColumnNameConstant.TESTINPUTJSON.toString()));

			if (!TextUtils.isEmpty(data.get(ExcelColumnNameConstant.TESTHEADERS.toString()))) {
				if (APIExecutorHelper.isDynamicValuePresent(url, data)) {
					extentTest.log(LogStatus.SKIP,
							"URL,Input Json or Headers dynamic values not replaces with actual value.");
					throw new SkipException("Skipping this exception");
				} else {
					extentTest.log(LogStatus.INFO, "Headers-->" + APIExecutorHelper.setHeaders(data));
					rs = rs.given().headers(APIExecutorHelper.setHeaders(data));
				}
			}

			resp = rs.when().get(url).then().extract().response();
			extentTest.log(LogStatus.INFO, "Response" + resp.asString());
			long executionTimeInMillis = resp.getTime();
			APIExecutorHelper.printTestExecutionTime(executionTimeInMillis, extentTest);

			if (!TextUtils.isEmpty(data.get(ExcelColumnNameConstant.TESTSCHEMANAME.toString()))) {
				assertThat(resp.asString(),
						matchesJsonSchemaInClasspath(data.get(ExcelColumnNameConstant.TESTSCHEMANAME.toString())));
			}
		} catch (SkipException e) {
			extentTest.log(LogStatus.SKIP, "Skip " + e.getMessage());
		} catch (Exception e) {
			extentTest.log(LogStatus.FAIL, "Fail " + e.getMessage());
			org.testng.Assert.fail("*******************************apiGet: " + e.getMessage());
		}
		return resp;

	}

	/*
	 * Rest-Assured is using given/when/then notation. In below section we
	 * declare things like content type or request body. below method is use for
	 * postapi.
	 */

	public Response apiPost(LinkedHashMap<String, String> data, ExtentTest extentTest) {
		String url = APIExecutorHelper.getUrl(data);
		Response resp = null;
		try {
			extentTest.log(LogStatus.INFO, "URL-->" + url);
			extentTest.log(LogStatus.INFO, "Input json" + data.get(ExcelColumnNameConstant.TESTINPUTJSON.toString()));
			RequestSpecification rs = given().contentType(ApiExecutorConstants.CONTENTTYPE.toString())
					.body(data.get(ExcelColumnNameConstant.TESTINPUTJSON.toString()));
			if (!TextUtils.isEmpty(data.get(ExcelColumnNameConstant.TESTHEADERS.toString()))) {
				if (APIExecutorHelper.isDynamicValuePresent(url, data)) {
					extentTest.log(LogStatus.SKIP,
							"URL,Input Json or Headers dynamic values not replaces with actual value.");
					throw new SkipException("Skipping this exception");
				} else {
					extentTest.log(LogStatus.INFO, "Headers-->" + APIExecutorHelper.setHeaders(data));
					rs = rs.given().headers(APIExecutorHelper.setHeaders(data));
				}
			}

			resp = rs.when().post(url).then().extract().response();
			extentTest.log(LogStatus.INFO, "Response" + resp.asString());
			long executionTimeInMillis = resp.getTime();
			APIExecutorHelper.printTestExecutionTime(executionTimeInMillis, extentTest);

			if (!TextUtils.isEmpty(data.get(ExcelColumnNameConstant.TESTSCHEMANAME.toString()))) {
				assertThat(resp.asString(),
						matchesJsonSchemaInClasspath(data.get(ExcelColumnNameConstant.TESTSCHEMANAME.toString())));
			}

		} catch (SkipException e) {
			extentTest.log(LogStatus.SKIP, "Skip " + e.getMessage());
		} catch (Exception e) {
			extentTest.log(LogStatus.FAIL, "Fail " + e.getMessage());
			org.testng.Assert.fail("*******************************apiPost: " + e.getMessage());
		}

		return resp;
	}

	/*
	 * Rest-Assured is using given/when/then notation. In below section we
	 * declare things like content type or request body. below method is use for
	 * putapi.
	 */

	public Response apiPut(LinkedHashMap<String, String> data, ExtentTest extentTest) {
		String url = APIExecutorHelper.getUrl(data);
		extentTest.log(LogStatus.INFO, "URL-->" + url);
		Response resp = null;
		try {
			extentTest.log(LogStatus.INFO, "Input json" + data.get(ExcelColumnNameConstant.TESTINPUTJSON.toString()));
			RequestSpecification rs = given().contentType(ApiExecutorConstants.CONTENTTYPE.toString())
					.body(data.get(ExcelColumnNameConstant.TESTINPUTJSON.toString()));
			if (!TextUtils.isEmpty(data.get(ExcelColumnNameConstant.TESTHEADERS.toString()))) {
				if (APIExecutorHelper.isDynamicValuePresent(url, data)) {
					extentTest.log(LogStatus.SKIP,
							"URL,Input Json or Headers dynamic values not replaces with actual value.");
					throw new SkipException("Skipping this exception");
				} else {
					extentTest.log(LogStatus.INFO, "Headers-->" + APIExecutorHelper.setHeaders(data));
					rs = rs.given().headers(APIExecutorHelper.setHeaders(data));
				}
			}
			resp = rs.when().put(url).then().extract().response();
			extentTest.log(LogStatus.INFO, "Response" + resp.asString());
			long executionTimeInMillis = resp.getTime();
			APIExecutorHelper.printTestExecutionTime(executionTimeInMillis, extentTest);

			if (!TextUtils.isEmpty(data.get(ExcelColumnNameConstant.TESTSCHEMANAME.toString()))) {
				assertThat(resp.asString(),
						matchesJsonSchemaInClasspath(data.get(ExcelColumnNameConstant.TESTSCHEMANAME.toString())));
			}

		} catch (SkipException e) {
			extentTest.log(LogStatus.SKIP, "Skip " + e.getMessage());
		} catch (Exception e) {
			extentTest.log(LogStatus.FAIL, "Fail " + e.getMessage());
			org.testng.Assert.fail("*******************************apiPut: " + e.getMessage());
		}
		return resp;
	}

	/*
	 * Rest-Assured is using given/when/then notation. In below section we
	 * declare things like content type or request body. below method is use for
	 * deleteapi.
	 */

	public Response apiDelete(LinkedHashMap<String, String> data, ExtentTest extentTest) {
		String url = APIExecutorHelper.getUrl(data);
		extentTest.log(LogStatus.INFO, "URL-->" + url);
		Response resp = null;
		try {
			extentTest.log(LogStatus.INFO, "Input json" + data.get(ExcelColumnNameConstant.TESTINPUTJSON.toString()));
			RequestSpecification rs = given().contentType(ApiExecutorConstants.CONTENTTYPE.toString())
					.body(data.get(ExcelColumnNameConstant.TESTINPUTJSON.toString()));
			if (!TextUtils.isEmpty(data.get(ExcelColumnNameConstant.TESTHEADERS.toString()))) {
				if (APIExecutorHelper.isDynamicValuePresent(url, data)) {
					extentTest.log(LogStatus.SKIP,
							"URL,Input Json or Headers dynamic values not replaces with actual value.");
					throw new SkipException("Skipping this exception");
				} else {
					extentTest.log(LogStatus.INFO, "Headers-->" + APIExecutorHelper.setHeaders(data));
					rs = rs.given().headers(APIExecutorHelper.setHeaders(data));
				}
			}
			resp = rs.when().delete(url).then().extract().response();
			extentTest.log(LogStatus.INFO, "Response" + resp.asString());
			long executionTimeInMillis = resp.getTime();
			APIExecutorHelper.printTestExecutionTime(executionTimeInMillis, extentTest);

			if (!TextUtils.isEmpty(data.get(ExcelColumnNameConstant.TESTSCHEMANAME.toString()))) {
				assertThat(resp.asString(),
						matchesJsonSchemaInClasspath(data.get(ExcelColumnNameConstant.TESTSCHEMANAME.toString())));
			}

		} catch (SkipException e) {
			extentTest.log(LogStatus.SKIP, "Skip " + e.getMessage());
		} catch (Exception e) {
			extentTest.log(LogStatus.FAIL, "Fail " + e.getMessage());
			org.testng.Assert.fail("*******************************apiDelete: " + e.getMessage());
		}
		return resp;
	}

	public Response apiPatch(LinkedHashMap<String, String> data, ExtentTest extentTest) {
		String url = APIExecutorHelper.getUrl(data);
		extentTest.log(LogStatus.INFO, "URL-->" + url);
		Response resp = null;
		try {
			extentTest.log(LogStatus.INFO, "Input json" + data.get(ExcelColumnNameConstant.TESTINPUTJSON.toString()));
			RequestSpecification rs = given().contentType(ApiExecutorConstants.CONTENTTYPE.toString())
					.body(data.get(ExcelColumnNameConstant.TESTINPUTJSON.toString()));
			if (!TextUtils.isEmpty(data.get(ExcelColumnNameConstant.TESTHEADERS.toString()))) {
				if (APIExecutorHelper.isDynamicValuePresent(url, data)) {
					extentTest.log(LogStatus.SKIP,
							"URL,Input Json or Headers dynamic values not replaces with actual value.");
					throw new SkipException("Skipping this exception");
				} else {
					extentTest.log(LogStatus.INFO, "Headers-->" + APIExecutorHelper.setHeaders(data));
					rs = rs.given().headers(APIExecutorHelper.setHeaders(data));
				}
			}
			resp = rs.when().patch(url).then().extract().response();
			extentTest.log(LogStatus.INFO, "Response" + resp.asString());
			long executionTimeInMillis = resp.getTime();
			APIExecutorHelper.printTestExecutionTime(executionTimeInMillis, extentTest);

			if (!TextUtils.isEmpty(data.get(ExcelColumnNameConstant.TESTSCHEMANAME.toString()))) {
				assertThat(resp.asString(),
						matchesJsonSchemaInClasspath(data.get(ExcelColumnNameConstant.TESTSCHEMANAME.toString())));
			}

		} catch (SkipException e) {
			extentTest.log(LogStatus.SKIP, "Skip " + e.getMessage());
		} catch (Exception e) {
			extentTest.log(LogStatus.FAIL, "Fail " + e.getMessage());
			org.testng.Assert.fail("*******************************apiPatch: " + e.getMessage());
		}
		return resp;
	}
}
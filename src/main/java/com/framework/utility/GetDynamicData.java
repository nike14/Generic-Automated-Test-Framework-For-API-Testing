/**
 * 
 */
package com.framework.utility;

import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.http.util.TextUtils;
import org.testng.Assert;
import com.framework.constants.Constants.ExcelColumnNameConstant;
import com.framework.inmemorydatabase.InMemoryDatabaseHelper;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/**
 * @author nikhil
 *
 */
public class GetDynamicData {

	InMemoryDatabaseHelper inMemoryDatabasehelperObj = new InMemoryDatabaseHelper();
	RestAssuredHelper restAssuredHelperObj = new RestAssuredHelper();

	/*
	 * getDyanmicsValues call the helper method for replacing dynamic values.
	 */
	public void getDynamicValues(LinkedHashMap<String, String> data, ExtentTest extentTest,String sheetName) {
		getSingleDynamicValue(ExcelColumnNameConstant.TESTURL.toString(), extentTest, data);
		getSingleDynamicValue(ExcelColumnNameConstant.TESTINPUTJSON.toString(), extentTest, data);
		getSingleDynamicValue(ExcelColumnNameConstant.TESTHEADERS.toString(), extentTest, data);
		getSingleDynamicValue(ExcelColumnNameConstant.TESTPARAMETERS.toString(), extentTest, data);
		getSingleDynamicValue(ExcelColumnNameConstant.TESTMETHODANDJSONPATH.toString(), extentTest, data);
		restAssuredHelperObj.apiExecutorHelper(data, extentTest,sheetName);
	}

	/*
	 * getSingleDynamicValue check whether string contains dynamic parameter i.e
	 * #
	 */
	public void getSingleDynamicValue(String value, ExtentTest extentTest, LinkedHashMap<String, String> data) {
		try {
			if (data.get(value).contains("#")) {
				extractSingleValue(value, extentTest, data);
			}
		} catch (Exception e) {
			extentTest.log(LogStatus.ERROR, "Exception for getSingleDynamicValue" + e);
		}
	}

	/*
	 * Replace for only assert response.
	 */
	public void getListDynamicValue(String value, ExtentTest extentTest, LinkedHashMap<String, String> data) {
		try {
			if (data.get(value).contains("@")) {
				extractListValue(value, extentTest, data);
				removeListValues(value, data, extentTest);

			}

		} catch (Exception e) {
			extentTest.log(LogStatus.ERROR, "Exception for getSingleDynamicValue" + e);
		}

	}

	/*
	 * assertSingleDynamicValue replace first dynamic values and assert static
	 * data.
	 */
	public void assertSingleDynamicValue(LinkedHashMap<String, String> data, ExtentTest extentTest) {
		try {
			if (!TextUtils.isEmpty(data.get(ExcelColumnNameConstant.TESTASSERTRESPONSE.toString()))) {

				getSingleDynamicValue(ExcelColumnNameConstant.TESTASSERTRESPONSE.toString(), extentTest, data);
				getListDynamicValue(ExcelColumnNameConstant.TESTASSERTRESPONSE.toString(), extentTest, data);
				assertSingleValueStaticData(data, extentTest);

			}
		} catch (Exception e) {
			extentTest.log(LogStatus.ERROR, "Exception for extractSingleValue" + e);
		}
	}

	/*
	 * extractSingleValue Replace single dynamic value from data base.
	 */
	public void extractSingleValue(String value, ExtentTest extentTest, LinkedHashMap<String, String> data) {
		try {
			String regex = "\\#[a-z|A-Z|0-9|\\.|\\-|\\_]+\\#";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(data.get(value));

			while (matcher.find()) {
				String dynamicValue = matcher.group();
				String replaceValueFromDynamicValue = matcher.group().replace("#", "");
				String[] splitDynamicValue = replaceValueFromDynamicValue.split("\\.");
				String sheetName=splitDynamicValue[0].toLowerCase();
				String testCaseid = splitDynamicValue[1];
				String pathValue = splitDynamicValue[2].toLowerCase();
				System.out.println("sheetName"+sheetName+"id--" + testCaseid + "  value--" + pathValue);
				String dataBaseValue = inMemoryDatabasehelperObj.getDataFromDataBase(sheetName,testCaseid, pathValue);
				data.put(value, data.get(value).replace(dynamicValue, dataBaseValue));
			}

		} catch (Exception e) {
			extentTest.log(LogStatus.ERROR, "Exception for extractSingleValue" + e);
		}
	}

	/*
	 * extractListValue Replace dynamic values from data base.
	 */
	public void extractListValue(String value, ExtentTest extentTest, LinkedHashMap<String, String> data) {
		try {
			String regex = "\\@[a-z|A-Z|0-9|\\.|\\-]+\\@";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(data.get(value));
			int count = 0;
			while (matcher.find()) {
				String dynamicValue = matcher.group();
				String replaceValueFromDynamicValue = matcher.group().replace("@", "");
				String[] splitDynamicValue = replaceValueFromDynamicValue.split("\\.");
				String sheetName=splitDynamicValue[0].toLowerCase();
				String testCaseid = splitDynamicValue[1];
				String pathValue = splitDynamicValue[2].toLowerCase();
				System.out.println("sheetName--"+sheetName+"id--" + testCaseid + "  value--" + pathValue);
				String dataBaseValue = inMemoryDatabasehelperObj.getDataFromDataBase(sheetName,testCaseid, pathValue);
				if (!TextUtils.isEmpty(dataBaseValue)) {
					String[] splitDataBaseValue = dataBaseValue.split("\\,");
					System.out.println(splitDataBaseValue.length);
					for (int i = 0; i < splitDataBaseValue.length; i++) {
						String assertText = data.get(ExcelColumnNameConstant.TESTASSERTRESPONSE.toString());
						assertText = assertText.replace(dynamicValue, splitDataBaseValue[i]);
						if (assertText.contains(";")) {
							String[] splitValueForAssert = assertText.split("\\;");
							assertText = splitValueForAssert[count];
							System.out.println(assertText);
							assertMethod(assertText, extentTest);
						} else {
							System.out.println(assertText);
							assertMethod(assertText, extentTest);
						}
					}
				}
				count++;
			}

		} catch (Exception e) {
			extentTest.log(LogStatus.ERROR, "Exception for extractListValue" + e);
		}
	}

	/*
	 * assertStaticData assert the values.
	 */
	public void assertSingleValueStaticData(LinkedHashMap<String, String> data, ExtentTest extentTest) {
		try {
			String assertText = data.get(ExcelColumnNameConstant.TESTASSERTRESPONSE.toString());
			assertMethod(assertText, extentTest);
		} catch (Exception e) {
			extentTest.log(LogStatus.ERROR, "Exception for assertmethod" + e);
		}
	}

	public void assertMethod(String assertText, ExtentTest extentTest) {
		try {
			String regex = "[a-z|A-Z|0-9|\\-|\\,|\\_|\\.]+";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(assertText);

			while (matcher.find()) {
				String values = matcher.group();
				String[] splitValues = values.split("\\,");
				if (splitValues.length < 3) {
					String actual = splitValues[0].toLowerCase();
					if (!TextUtils.isEmpty(actual)) {
						String expected = splitValues[1].toLowerCase();
						Assert.assertEquals(actual, expected);
						extentTest.log(LogStatus.INFO,
								"Values are correct->" + "actual:" + actual + " expected:" + expected);
					}
				} else {
					long firstValue = Long.valueOf(splitValues[0]);
					long secondValue = Long.valueOf(splitValues[1]);
					long thirdValue = Long.valueOf(splitValues[2]);
					if (secondValue <= firstValue && thirdValue >= firstValue) {
						extentTest.log(LogStatus.PASS, "Values are correct->" + " Response Value:" + firstValue
								+ " Epoch Start:" + secondValue + " Epoch end:" + thirdValue);
					} else {
						extentTest.log(LogStatus.FAIL, "Error in response->" + " Response Value:" + firstValue
								+ " Epoch Start:" + secondValue + " Epoch end:" + thirdValue);
					}
				}
			}

		} catch (Exception e) {
			extentTest.log(LogStatus.ERROR, "Exception for assertmethod" + e);
		}
	}

	public void removeListValues(String value, LinkedHashMap<String, String> data, ExtentTest extentTest) {
		String regex = "\\@[a-z|A-Z|0-9|\\.|\\@|\\,|\\_]+";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(data.get(value));
		while (matcher.find()) {
			String values = matcher.group();
			data.put(value, data.get(value).replace(values, ""));
			System.out.println(data.get(value));
		}
	}

}
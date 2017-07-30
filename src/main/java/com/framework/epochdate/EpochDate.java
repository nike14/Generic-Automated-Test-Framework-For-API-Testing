/**
 * 
 */
package com.framework.epochdate;

import java.util.Date;

import com.framework.constants.Constants.EpochDateHelperConstant;
import com.framework.constants.Constants.InMemoryDatabaseHelperConstant;
import com.framework.inmemorydatabase.InMemoryDatabaseHelper;

/**
 * @author nikhil
 *
 */
public class EpochDate {

	InMemoryDatabaseHelper inMemoryDatabaseHelperObj = new InMemoryDatabaseHelper();
	String sheetName;

	public EpochDate(String sheetName) {
		this.sheetName = sheetName;
	}

	public void insertStartToday() {
		Date date = EpochHelper.getDateToday();
		String epochValue = String.valueOf(EpochHelper.getStartOfDay(date).getTime());
		System.out.println("insertStartToday" + epochValue);
		inMemoryDatabaseHelperObj.createData(EpochDateHelperConstant.TESTCASEID.toString(), sheetName,
				EpochDateHelperConstant.STARTTODAY.toString(), epochValue,
				InMemoryDatabaseHelperConstant.TABLENAME.toString());

	}

	public void insertEndToday() {
		Date date = EpochHelper.getDateToday();
		String epochValue = String.valueOf(EpochHelper.getEndOfDay(date).getTime());
		System.out.println("insertEndToday" + epochValue);
		inMemoryDatabaseHelperObj.createData(EpochDateHelperConstant.TESTCASEID.toString(),sheetName,
				EpochDateHelperConstant.ENDTODAY.toString(), epochValue,
				InMemoryDatabaseHelperConstant.TABLENAME.toString());
	}

	public void insertStartYesterday() {
		Date date = EpochHelper.getDateYesterday();
		String epochValue = String.valueOf(EpochHelper.getStartOfDay(date).getTime());
		System.out.println("insertStartYesterday" + epochValue);
		inMemoryDatabaseHelperObj.createData(EpochDateHelperConstant.TESTCASEID.toString(),sheetName,
				EpochDateHelperConstant.STARTYESTERDAY.toString(), epochValue,
				InMemoryDatabaseHelperConstant.TABLENAME.toString());
	}

	public void insertEndYesterday() {
		Date date = EpochHelper.getDateYesterday();
		String epochValue = String.valueOf(EpochHelper.getEndOfDay(date).getTime());
		System.out.println("insertEndYesterday" + epochValue);
		inMemoryDatabaseHelperObj.createData(EpochDateHelperConstant.TESTCASEID.toString(),sheetName,
				EpochDateHelperConstant.ENDYESTERDAY.toString(), epochValue,
				InMemoryDatabaseHelperConstant.TABLENAME.toString());
	}

	public void insertStartThisWeek() {
		Date date = EpochHelper.getDateThisWeek();
		String epochValue = String.valueOf(EpochHelper.getStartOfDay(date).getTime());
		System.out.println("insertStartThisWeek" + epochValue);
		inMemoryDatabaseHelperObj.createData(EpochDateHelperConstant.TESTCASEID.toString(),sheetName,
				EpochDateHelperConstant.STARTTHISWEEK.toString(), epochValue,
				InMemoryDatabaseHelperConstant.TABLENAME.toString());
	}

	public void insertStartSevenDays() {
		Date date = EpochHelper.getSevenDaysBackDate();
		String epochValue = String.valueOf(EpochHelper.getStartOfDay(date).getTime());
		System.out.println("insertStartSevenDays" + epochValue);
		inMemoryDatabaseHelperObj.createData(EpochDateHelperConstant.TESTCASEID.toString(),sheetName,
				EpochDateHelperConstant.STARTLASTSEVENDAYS.toString(), epochValue,
				InMemoryDatabaseHelperConstant.TABLENAME.toString());
	}

	public void insertStartThisMonth() {
		Date date = EpochHelper.getStartDateOfMonth();
		String epochValue = String.valueOf(EpochHelper.getStartOfDay(date).getTime());
		System.out.println("insertStartThisMonth" + epochValue);
		inMemoryDatabaseHelperObj.createData(EpochDateHelperConstant.TESTCASEID.toString(),sheetName,
				EpochDateHelperConstant.STARTTHISMONTH.toString(), epochValue,
				InMemoryDatabaseHelperConstant.TABLENAME.toString());
	}

	public void insertStartLastMonth() {
		Date date = EpochHelper.getStartDateOfPreviousMonth();
		String epochValue = String.valueOf(EpochHelper.getStartOfDay(date).getTime());
		System.out.println("insertStartLastMonth" + epochValue);
		inMemoryDatabaseHelperObj.createData(EpochDateHelperConstant.TESTCASEID.toString(),sheetName,
				EpochDateHelperConstant.STARTLASTMONTH.toString(), epochValue,
				InMemoryDatabaseHelperConstant.TABLENAME.toString());
	}

	public void insertEndLastMonth() {
		Date date = EpochHelper.getEndDateOfPreviousMonth();
		String epochValue = String.valueOf(EpochHelper.getEndOfDay(date).getTime());
		System.out.println("insertEndLastMonth" + epochValue);
		inMemoryDatabaseHelperObj.createData(EpochDateHelperConstant.TESTCASEID.toString(),sheetName,
				EpochDateHelperConstant.ENDLASTMONTH.toString(), epochValue,
				InMemoryDatabaseHelperConstant.TABLENAME.toString());
	}

	public void insertStartCustomRange() {
		Date date = EpochHelper.getStartDateOfThreeMonthBefore();
		String epochValue = String.valueOf(EpochHelper.getStartOfDay(date).getTime());
		System.out.println("insertStartCustomRange" + epochValue);
		inMemoryDatabaseHelperObj.createData(EpochDateHelperConstant.TESTCASEID.toString(),sheetName,
				EpochDateHelperConstant.STARTCUSTOMRANGE.toString(), epochValue,
				InMemoryDatabaseHelperConstant.TABLENAME.toString());
	}

}
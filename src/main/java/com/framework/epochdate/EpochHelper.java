/**
 * 
 */
package com.framework.epochdate;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/**
 * @author nikhil
 *
 */
public class EpochHelper {

	/*
	 * getEndOfDay give epoch time of end of day.
	 */
	public static Date getEndOfDay(Date date) {
		LocalDateTime localDateTime = dateToLocalDateTime(date);
		LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
		return localDateTimeToDate(endOfDay);
	}

	/*
	 * getStartOfDay give epoch time of start of day.
	 */
	public static Date getStartOfDay(Date date) {
		LocalDateTime localDateTime = dateToLocalDateTime(date);
		LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
		return localDateTimeToDate(startOfDay);
	}

	public static Date localDateTimeToDate(LocalDateTime startOfDay) {
		return Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
	}

	public static LocalDateTime dateToLocalDateTime(Date date) {
		return LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
	}

	/*
	 * getDateToday give today date.
	 */
	public static Date getDateToday() {
		Date today = new Date();
		return today;

	}
	
	/*
	 * getDateYesterday give yesterday date.
	 */
	public static Date getDateYesterday() {
		Calendar calendar = Calendar.getInstance();

		// Move calendar to yesterday
		calendar.add(Calendar.DATE, -1);

		// Get current date of calendar which point to the yesterday now
		Date yesterday = calendar.getTime();

		return yesterday;
	}
	
	/*
	 * getDateThisWeek give start date of this week.
	 */
	public static Date getDateThisWeek() {
		Calendar calendar = Calendar.getInstance();

		// Move calendar to monday for this week
		calendar.set(Calendar.DAY_OF_WEEK, calendar.MONDAY);
		Date thisWeek = calendar.getTime();
		return thisWeek;
	}
	
	/*
	 * getSevenDaysBackDate give seven days back date.
	 */
	public static Date getSevenDaysBackDate() {
		Calendar calendar = Calendar.getInstance();
		// Move calendar to yesterday
		calendar.add(Calendar.DATE, -6);

		// Get current date of calendar which point to the yesterday now
		Date sevenDaysBackDate = calendar.getTime();

		return sevenDaysBackDate;
	}
	
	/*
	 * getStartDateOfMonth give start date of this month.
	 */
	public static Date getStartDateOfMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		Date startDateOfMonth = calendar.getTime();
		return startDateOfMonth;
	}
	
	/*
	 * getStartDateOfPreviousMonth give start date of previous month.
	 */
	public static Date getStartDateOfPreviousMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.MONTH, -1);
		Date startDateOfPreviousMonth = calendar.getTime();
		return startDateOfPreviousMonth;
	}
	
	/*
	 * getEndDateOfPreviousMonth giveEnd date of previous month.
	 */
	public static Date getEndDateOfPreviousMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.DATE, -1);
		Date endDateOfPreviousMonth = calendar.getTime();
		return endDateOfPreviousMonth;
	}
	
	/*
	 * getStartDateOfThreeMonthBefore give 3 months before Date.
	 */
	public static Date getStartDateOfThreeMonthBefore() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.MONTH, -3);
		Date startDateOfThreeMonthBefore = calendar.getTime();
		return startDateOfThreeMonthBefore;
	}

}

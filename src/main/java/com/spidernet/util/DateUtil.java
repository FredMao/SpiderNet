package com.spidernet.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	public static final String PATTERN_DATE = "yyyy-MM-dd";
	public static final String PATTERN_DATETIME = "yyyy-MM-dd HH:mm:ss";
	public static final String PATTERN_DATETIME2 = "yyyyMMddHHmmss";
	
	/**
	 * @see To Parse String date to Date Object
	 * @param datestr
	 * @return Date Obj
	 * @throws ParseException
	 */
	public static Date parseDate(String datestr) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_DATETIME);
		return sdf.parse(datestr);
	}
	
	/**
	 * @see Parse String data to Date Object with pattern format
	 * @param datestr
	 * @param pattern
	 * @return Date Obj
	 * @throws ParseException
	 */
	public static Date parseDate(String datestr, String pattern) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.parse(datestr);
	}
	
	/**
	 * @see Parse Date Object to String with yyyy-MM-dd HH:mm:ss Format
	 * @param dt
	 * @return Date String
	 */
	public static String formatDate(Date dt) {
		SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_DATETIME);
		return sdf.format(dt);
	}
	
	/**
	 * @see To Parse Date Object to String with user input dateFormat
	 * @param dt
	 * @param dateFormat
	 * @return
	 */
	public static String formatDate(Date dt, String dateFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(dt);
	}
	
	/**
	 * @see <p>Get current date String with "yyyy-MM-dd" dataFormat.
	 * @return
	 */
	public static String currentDateString() {

		SimpleDateFormat dateFormat = new SimpleDateFormat(PATTERN_DATE);
		String mDateTime = dateFormat.format(currentTime());
		return mDateTime;
	}
	
	/**
	 * @see <p>Get current  data String with yyyy-MM-dd HH:mm:ss dataFormat.
	 * @return
	 */
	public static String currentDateTimeString() {

		SimpleDateFormat dateTimeFormat = new SimpleDateFormat(PATTERN_DATETIME);
		String mDateTime = dateTimeFormat.format(currentTime());
		return mDateTime;
	}
	
	public static String currentDateTimeString2() {

		SimpleDateFormat dateTimeFormat = new SimpleDateFormat(PATTERN_DATETIME2);
		String mDateTime = dateTimeFormat.format(currentTime());
		return mDateTime;
	}
	
	public static Date currentTime() {

		return Calendar.getInstance().getTime();
	}
	
	public static String getDateByResetMonth(String datestr, int months) throws ParseException {
		Calendar cal = Calendar.getInstance();
		cal.setTime(parseDate(datestr));
		cal.add(Calendar.MONTH, months);
		SimpleDateFormat dateFormat = new SimpleDateFormat(PATTERN_DATE);
		String redate = dateFormat.format(cal.getTime());
		return redate;
	}

	public static String getDateByResetDay(String datestr, int days) throws ParseException {
		Calendar cal = Calendar.getInstance();
		cal.setTime(parseDate(datestr));
		cal.add(Calendar.DATE, days);
		SimpleDateFormat dateFormat = new SimpleDateFormat(PATTERN_DATE);
		String redate = dateFormat.format(cal.getTime());
		return redate;
	}

	public static String addDayNumsDate(String sDate, long DayNums) {
		String mDateTime = "";
		if (sDate == null)
			sDate = currentDateString();
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(PATTERN_DATE);
			Date StartDate = dateFormat.parse(sDate);
			long lTime = (StartDate.getTime() / 1000) + 60 * 60 * 24 * DayNums;
			StartDate.setTime(lTime * 1000);
			mDateTime = dateFormat.format(StartDate);
		} catch (Exception ex) {
			System.err.print(ex.getMessage());
		}

		return mDateTime;
	}

	public static long dateDayInteval(String sDate, String eDate) {
		long day = 0;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(PATTERN_DATE);
			Date StartDate = dateFormat.parse(sDate);
			Date EndDate = dateFormat.parse(eDate);
			day = (EndDate.getTime() - StartDate.getTime()) / (24 * 60 * 60 * 1000);
		} catch (Exception ex) {
			System.err.print(ex.getMessage());
		}
		return day;
	}

//	public static String getWeekDay(String date) throws ParseException {
//		String[] weekDays = { "����", "��һ", "�ܶ�", "����", "����", "����", "����" };
//		Calendar cal = Calendar.getInstance();
//		Date d = parseDate(date, PATTERN_DATE);
//		cal.setTime(d);
//		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
//		if (w < 0)
//			w = 0;
//		return weekDays[w];
//	}

	public static String getTimeDateStr(long millSecond){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(millSecond*1000l);
		return date;
	}
	
	public static int getTimeDateStr(String dstr){
		
		int stime = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(dstr.length()>0){
			try {
				stime = (int) (sdf.parse(dstr).getTime()/1000);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else{
			stime = 0;
		}
		return stime;
	}
	
    public static long getTimeDateStrToLong(String dstr){
		
		long stime = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(dstr.length()>0){
			try {
				stime = sdf.parse(dstr).getTime();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else{
			stime = 0;
		}
		return stime;
	}

	public static String getDateByResetMinute(String datestr, int minutes) throws ParseException {
		Calendar cal = Calendar.getInstance();
		cal.setTime(parseDate(datestr));
		cal.add(Calendar.MINUTE, minutes);
		SimpleDateFormat dateFormat = new SimpleDateFormat(PATTERN_DATETIME);
		String redate = dateFormat.format(cal.getTime());
		return redate;
	}
	
	public static void main(String[] args) throws ParseException {
		System.out.println(DateUtil.addDayNumsDate(DateUtil.currentDateTimeString(),-1)+" 00:00:00");
	}
}

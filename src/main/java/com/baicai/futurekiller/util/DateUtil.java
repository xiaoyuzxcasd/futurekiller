package com.baicai.futurekiller.util;

import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtil {
	private final static long DATE_MSEC = 24 * 60 * 60 * 1000;
	
	private final static long SECOND = 1000;
	private final static long MIN = 60 * SECOND;
	private final static long HOUR = 60 * MIN;
	private final static long DAY = 24 * HOUR;
	
	private final static long TIME_ZONE_DELAY_MSEC;
	
	static {
		Date date;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.parse("1970-01-01 00:00:00");
		} catch (ParseException e) {
			date = new Date(0);
			e.printStackTrace();
		}
		TIME_ZONE_DELAY_MSEC = date.getTime();
	}
	
	// SimpleDateFormat线程不安全, so ThreadLocal
	private static final ThreadLocal<DateFormats> dateFormats = new ThreadLocal<DateFormats>() {
		protected DateFormats initialValue() {
			return new DateFormats();
		}
	};

	public static String formatYMD(Date date) {
		return dateFormats.get().ymd.format(date);
	}

	public static String formatYMDHM(Date date) {
		return dateFormats.get().ymdhm.format(date);
	}

	public static String formatYMDHMChinese(Date date) {
		return dateFormats.get().ymdhmChinese.format(date);
	}

	public static String formatYMDHMS(Date date) {
		return dateFormats.get().ymdhms.format(date);
	}

	public static String formatYMDChinese(Date date) {
		return dateFormats.get().ymdChinese.format(date);
	}

	public static String formatYMDSlash(Date date) {
		return dateFormats.get().ymdSlash.format(date);
	}

	public static Date parseYMD(String dateStr) {
		return parse(dateFormats.get().ymd, dateStr);
	}

	public static Date parseYMDHMS(String dateStr) {
		return parse(dateFormats.get().ymdhms, dateStr);
	}

	public static Date parse(SimpleDateFormat format, String dateStr) {
		try {
			format.setLenient(false);
			Date d = format.parse(dateStr);
			// check range for mysql
			// mysql date range : '1000-01-01' to '9999-12-31'
			Calendar c = Calendar.getInstance();
			c.setTime(d);
			int year = c.get(Calendar.YEAR);
			if (year >= 1000 && year <=9999){
				return d;
			} else {
				return null;
			}
		} catch (ParseException ex) {
			return null;
		}
	}
	
	private static Date add(Date time, long offset) {
		return new Date(time.getTime() + offset);
	}

	public static Date addMinitue(Date time, int m) {
		return add(time, m * MIN);
	}
	
	public static Date addHour(Date time, int h) {
		return add(time, h * HOUR);
	}
	
	public static Date addDay(Date time, int d) {
		return add(time, d * DAY);
	}
	
	public static Date addSecond(Date time, int s) {
		return add(time, s * SECOND);
	}
	
	public static boolean after(Date d1, Date d2) {
		return (d1.getTime() - d2.getTime()) > 0;
	}
	
	public static boolean before(Date d1, Date d2) {
		return (d1.getTime() - d2.getTime()) < 0;
	}

	public static boolean isSameDay(Date date1, Date date2) {
		long d1 = (date1.getTime() - TIME_ZONE_DELAY_MSEC) / DATE_MSEC;
		long d2 = (date2.getTime() - TIME_ZONE_DELAY_MSEC) / DATE_MSEC;
		return d1 == d2;
	}
}

class DateFormats {
	public final SimpleDateFormat ymdhm = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm");
	public final SimpleDateFormat ymdhmChinese = new SimpleDateFormat(
			"yyyy年MM月dd日 HH:mm");
	public final SimpleDateFormat ymd = new SimpleDateFormat("yyyy-MM-dd");
	public final SimpleDateFormat ymdhms = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	public final SimpleDateFormat ymdChinese = new SimpleDateFormat(
			"yyyy年MM月dd");
	public final SimpleDateFormat ymdSlash = new SimpleDateFormat("yyyy/MM/dd");
}
package com.basics.support;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 */
public class DateUtils extends org.apache.commons.lang.time.DateUtils {

	private static String[] parsePatterns = { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyyMMddHHmmss" };

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd）
	 */
	public static String getDate() {
		return getDate("yyyy-MM-dd");
	}

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}

	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(Date date, Object... pattern) {
		if (null == date) {
			return null;
		}
		String formatDate = null;
		if (pattern != null && pattern.length > 0) {
			formatDate = DateFormatUtils.format(date, pattern[0].toString());
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		return formatDate;
	}

	/**
	 * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String formatDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String formatDateTime_short(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm");
	}

	/**
	 * 得到日期时间字符串，转换格式（yyyy-MM-dd）
	 */
	public static String formatDateTime_Day(Date date) {
		return formatDate(date, "yyyy-MM-dd");
	}

	public static Date parseDateTime(String value) {
		try {
			return parseDate(value, new String[] { "yyyy-MM-dd HH:mm:ss" });
		} catch (Throwable e) {
			LogUtils.performance.error("parseDateTime:{} error:{}", value, e);
			return null;
		}
	}

	public static Date parseDateTime_short(String value) {
		try {
			return parseDate(value, new String[] { "yyyy-MM-dd" });
		} catch (Throwable e) {
			LogUtils.performance.error("parseDateTime:{} error:{}", value, e);
			return null;
		}
	}

	/**
	 * 得到当前时间字符串 格式（HH:mm:ss）
	 */
	public static String getTime() {
		return formatDate(new Date(), "HH:mm:ss");
	}

	/**
	 * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getDateTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前年份字符串 格式（yyyy）
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	/**
	 * 得到当前月份字符串 格式（MM）
	 */
	public static String getMonth() {
		return formatDate(new Date(), "MM");
	}

	/**
	 * 得到当天字符串 格式（dd）
	 */
	public static String getDay() {
		return formatDate(new Date(), "dd");
	}

	/**
	 * 得到当前星期字符串 格式（E）星期几
	 */
	public static String getWeek() {
		return formatDate(new Date(), "E");
	}

	/**
	 * 日期型字符串转化为日期 格式 { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm" }
	 */
	public static Date parseDate(Object str) {
		if (str == null) {
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 获取过去的天数
	 * 
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {
		long t = new Date().getTime() - date.getTime();
		return t / (24 * 60 * 60 * 1000);
	}

	public static Date getDateStart(Date date) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date = sdf.parse(formatDate(date, "yyyy-MM-dd") + " 00:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static Date getDateEnd(Date date) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date = sdf.parse(formatDate(date, "yyyy-MM-dd") + " 23:59:59");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static long getHoursByDate(String start, String end) {
		long result = 0;
		long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
		long nh = 1000 * 60 * 60;// 一小时的毫秒数
		Date _start = parseDate(start);
		Date _end = parseDate(end);
		long temp = _end.getTime() - _start.getTime();
		if (temp > 0) {
			long day = temp / nd;// 计算差多少天
			result = temp % nd / nh + day * 24;// 计算差多少小时
		}
		return result;
	}

	/**
	 * 获取两个时间段之间的差，得到"×天×时×分"
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static String getDateTimeByDate(Date start, Date end) {
		if (null == start || null == end) {
			return "暂无";
		} else {
			long result = end.getTime() - start.getTime();
			long days = result / (1000 * 60 * 60 * 24);
			long hours = (result - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
			long minutes = (result - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
			return "" + days + "天" + hours + "小时" + minutes + "分";
		}

	}

	/**
	 * 获取两个时间段之间的差，得到"×天"
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static String getDaysByDate(Date start, Date end) {
		if (null == start || null == end) {
			return "暂无";
		} else {
			long result = end.getTime() - start.getTime();
			long days = result / (1000 * 60 * 60 * 24);
			return "" + days + "天";
		}

	}

	// 获取时间差毫秒数
	public static Long getTimeDif(Date begin, Date end) {
		long between = 0;
		try {
			// SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			// begin = dfs.parse("2009-07-10 10:22:21.214");
			// end = dfs.parse("2009-07-20 11:24:49.145");

			between = (begin.getTime() - end.getTime());// 得到两者的毫秒数
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return between;
	}

	public static long getTimeStemp(String time) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		long timeStemp = 0;
		try {
			Date date = simpleDateFormat.parse(time);
			timeStemp = date.getTime();
			return timeStemp;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return timeStemp;
		}

	}

	/**
	 * 解析身份认证接口返回的时间格式 "2015 - 09 -24T11:0 2:43.106+08:00"
	 * 
	 * @param time
	 * @return
	 */
	public static Timestamp parseAuthentication(String time) {
		time = StringUtils.substringBefore(time.replaceAll(" ", "").replaceAll("T", " "), "+");
		// 获取yyyy-MM-dd
		String second = " 00:00:00";
		if (time.length() <= 10) {
			return new Timestamp(getTimeStemp(time));
		} else {
			String first = time.substring(0, 10);
			second = " " + time.substring(10);
			time = first + second + ".000";
			return new Timestamp(getTimeStemp(time));
		}
	}

	/***
	 * 长整形日期转成Date类型日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date longToDate(long date) {
		Date date1 = new Date(date);
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return parseDateTime(sd.format(date1));
	}

	public static String[] createDateStringArray(Date startDate, Date endDate, String pattern) {
		Long days = 0L;
		if (null == startDate || null == endDate) {
			return null;
		} else {
			long result = endDate.getTime() - startDate.getTime();
			days = result / (1000 * 60 * 60 * 24);
		}
		String[] dateArr = new String[days.intValue()];
		Calendar startCalendar = Calendar.getInstance();
		Calendar endCalendar = Calendar.getInstance();
		startCalendar.setTime(startDate);
		endCalendar.setTime(endDate);
		int i = 0;
		while (true) {
			if (startCalendar.getTimeInMillis() < endCalendar.getTimeInMillis()) {
				dateArr[i] = DateFormatUtils.format(startCalendar, pattern);
				i++;
				startCalendar.add(Calendar.DAY_OF_MONTH, 1);
			} else {
				break;
			}
		}
		return dateArr;
	}

	// 获取当前时间前N天的时间
	public static String getBeforDataByNum(int num) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		calendar.add(Calendar.DATE, -num);
		return sdf.format(calendar.getTime());
	}

	// 获取所传时间前N天的时间
	public static String getDateBeforByNum(Date date, int num) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		calendar.add(Calendar.DATE, -num);
		return sdf.format(calendar.getTime());
	}

	// 获取所传时间前N天的时间
	public static Date getTimeBeforByNum(Date date, int num) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, -num);
		return calendar.getTime();
	}

	public static Date addDateMinut(Date date, int hour) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("front:" + format.format(date)); // 显示输入的日期
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR, hour);// 24小时制
		date = cal.getTime();
		System.out.println("after:" + format.format(date)); // 显示更新后的日期
		return cal.getTime();
	}

	// 往后多少分钟
	public static Date addDateMinutByMin(Date date, int min) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("front:" + format.format(date)); // 显示输入的日期
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, min);// 24小时制
		date = cal.getTime();
		System.out.println("after:" + format.format(date)); // 显示更新后的日期
		return cal.getTime();
	}

	public static void main(String[] args) {

		// 2016-5-3.12.14. 27. 104000000
		// System.out.println(DateUtils.getTimeStemp("2016-5-3.12.14. 27.
		// 104000000"));
		// System.out.println(new Timestamp(System.currentTimeMillis()) + "");
		// System.out.println(new Date().getTime() + "");
		// System.out.println(DateUtils.getTimeDif(new Date(), new Date()));
		// System.out.println(longToDate(1469519040000L));
		// System.out.println(getTimeStemp("2016-05-30 14:03:14.115"));
		// String[] dateArr = createDateStringArray(parseDate("2017-10-20"),
		// parseDate("2017-11-02"), "yyyy-MM-dd");
		// System.out.println(Json.toJson(dateArr));
		/*System.out.println(getBeforDataByNum(-1));*/
		System.out.println(getTimeBeforByNum(parseDate("2019-06-19"), 2));
		/*System.out.println(formatDateTime(addDateMinut(new Date(), 26)));
		System.out.println(formatDateTime(addDateMinutByMin(new Date(), 10)));*/
	}

	public static Long getDateTimesByHours(int hours) {
		Calendar time = Calendar.getInstance();
		time.set(Calendar.HOUR_OF_DAY, hours);
		time.set(Calendar.MINUTE, 0);
		time.set(Calendar.SECOND, 0);
		time.set(Calendar.MILLISECOND, 0);
		return time.getTimeInMillis();
	}
}

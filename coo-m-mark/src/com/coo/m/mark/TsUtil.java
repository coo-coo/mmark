package com.coo.m.mark;

import java.util.Date;

import com.kingstar.ngbf.ms.util.DateUtil;

/**
 * 时间戳工具类
 * 
 * @author boqing.shen
 * 
 */
public final class TsUtil {

	public TsUtil() {
		// TODO Auto-generated constructor stub
	}

	public final static long MIN_1 = 60 * 1000;
	public final static long HOUR_1 = MIN_1 * 60;
	public final static long DAY_1 = HOUR_1 * 24;
	public final static long DAY_2 = DAY_1 * 2;
	public final static long DAY_3 = DAY_1 * 3;

	/**
	 * 返回TS的时间表达式
	 */
	public static String dateEn(long ts) {
		return format(ts, "yyyy-MM-dd");
	}

	/**
	 * 返回TS的时间表达式
	 */
	public static String dateCn(long ts) {
		return format(ts, "yyyy年MM月dd日");
	}

	/**
	 * 返回TS的时间表达式
	 */
	public static String minEn(long ts) {
		return format(ts, "yyyy-MM-dd HH:mm");
	}

	/**
	 * 返回TS的时间表达式
	 */
	public static String secEn(long ts) {
		return format(ts, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 返回TS的时间表达式
	 */
	public static String format(long ts, String format) {
		return DateUtil.format(new Date(ts), format);
	}

	/**
	 * 根据时间戳和现在的时间戳进行比较,显示3天以内的标示,如果超过三天，则显示日期
	 * 
	 */
	public static String getDiffText(long ts) {
		long diff = System.currentTimeMillis() - ts;
		if (diff >= DAY_3) {
			return DateUtil.format(new Date(ts), "yyyy年MM月dd日");
		} else if (diff < DAY_3 && diff >= DAY_2) {
			return "两天前";
		} else if (diff < DAY_2 && diff >= DAY_1) {
			return "一天前";
		} else if (diff < DAY_1 && diff >= HOUR_1) {
			int hour = (int) (diff / HOUR_1);
			return hour + "小时前";
		} else if (diff < HOUR_1 && diff >= 0) {
			int min = (int) (diff / MIN_1);
			return min + "分钟前";
		} else {
			return "";
		}
	}

	/**
	 * 根据时间戳比较两个时间戳的相差日期
	 * 
	 */
	public static int getDiffDays(long start, long end) {
		long diff = end - start;
		return (int) (diff / DAY_1);
	}

}

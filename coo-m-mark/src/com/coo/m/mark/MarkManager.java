package com.coo.m.mark;

import java.util.Date;
import java.util.List;

import org.litepal.crud.DataSupport;

import android.graphics.Bitmap;
import android.os.Message;

import com.coo.ms.cloud.model.NetLink;
import com.kingstar.ngbf.ms.util.DateUtil;
import com.kingstar.ngbf.ms.util.android.res.ResourceFactory;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

/**
 * [框架]游戏管理器
 * 
 * @author boqing.shen
 * @since 1.0
 */
public final class MarkManager {

	 public static Class<?> MAIN_CLASS = MarkHisActivity.class;
//	public static Class<?> MAIN_CLASS = SysMainActivity.class;

	/**
	 * APP网站宣传地址,Baidu轻应用
	 */
	public static String APP_URL = "http://lightapp.baidu.com/?appid=1568236";
	/**
	 * APP名称
	 */
	public static String APP_NAME = "刻度";

	public static String APP_DESC = "刻度";
	
	
	public static int APP_ICON_RESID = R.drawable.mark_32;
	
	/**
	 * 图片加载参数,参见ImageLoader组件
	 * 
	 * @since 1.3
	 */
	public static DisplayImageOptions IMG_OPTIONS = new DisplayImageOptions.Builder()
			.showImageOnLoading(R.drawable.ic_stub)
			.showImageForEmptyUri(R.drawable.ic_empty)
			.showImageOnFail(R.drawable.ic_stub)
			.cacheInMemory(true).cacheOnDisk(true)
			.considerExifParams(true)
			.bitmapConfig(Bitmap.Config.RGB_565).build();

	/**
	 * 获得当前游戏玩家,即手机号
	 * 
	 * @return
	 */
	public static String getCurrentPlayer() {
		// TODO
		return "13X12345678";
	}

	/**
	 * 返回TS的时间表达式
	 */
	public static String getTsDateText(long ts) {
		return DateUtil.format(new Date(ts), "yyyy-MM-dd");
	}
	
	public static String getTsDateText2(long ts) {
		return DateUtil.format(new Date(ts), "yyyy年MM月dd日");
	}

	/**
	 * 返回TS的时间表达式
	 */
	public static String getTsMinText(long ts) {
		return DateUtil.format(new Date(ts), "yyyy-MM-dd HH:mm");
	}

	/**
	 * 返回TS的时间表达式
	 */
	public static String getTsText(long ts) {
		return DateUtil.format(new Date(ts), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 根据时间戳和现在的时间戳进行比较,显示3天以内的标示,如果超过三天，则显示日期
	 * 
	 * @param ts
	 * @return
	 */
	public static String getTsExpression(long ts) {
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

	public static int getDiffDays(long start, long end) {
		long diff = end - start;
		return (int) (diff / DAY_1);
	}

	private static long MIN_1 = 60 * 1000;
	private static long HOUR_1 = MIN_1 * 60;
	public static long DAY_1 = HOUR_1 * 24;
	private static long DAY_2 = DAY_1 * 2;
	private static long DAY_3 = DAY_1 * 3;

	/**
	 * 创建简单的消息
	 */
	public static Message createMessage(int what, Object obj) {
		Message msg = new Message();
		msg.what = what;
		msg.obj = obj;
		return msg;
	}

	/**
	 * 创建分享链接
	 */
	public static NetLink createNetLink(String description) {
		// 指定标题等基础信息
		Bitmap thumb = ResourceFactory.getBitmap(APP_ICON_RESID);
		NetLink nl = new NetLink("一起来玩\"消磨\"吧", APP_URL, thumb);
		nl.setDescription(description);
		return nl;
	}

	/**
	 * 根据状态获得Mark信息
	 */
	public static List<Mark> getMarks(String status) {
		// TODO 获得tso和当前tsc时间戳之间的消息
		return DataSupport.where("status = ?", status)
				.order("tsi desc").find(Mark.class);
	}

}

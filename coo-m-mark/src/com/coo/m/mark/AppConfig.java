package com.coo.m.mark;

import android.graphics.Bitmap;

import com.coo.m.sys.SysContactActivity;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

/**
 * 应用配置参数,级别高于Common级别
 * 
 * @author boqing.shen
 * 
 */
public final class AppConfig {

	/**
	 * 主应用
	 */
	public static Class<?> MAIN = SysContactActivity.class;
	// public static Class<?> MAIN = SysMainActivity.class;

	/**
	 * APP网站宣传地址,Baidu轻应用
	 */
	public static String URL = "http://lightapp.baidu.com/?appid=1568236";
	/**
	 * APP名称
	 */
	public static String NAME = "刻度";
	/**
	 * 描述
	 */
	public static String DESC = "刻度";
	/**
	 * ICON资源ID,用于Dialog的Icon
	 */
	public static int ICON_RESID = R.drawable.ic_logo_32;

	/**
	 * 图片加载参数,参见ImageLoader组件
	 */
	public static DisplayImageOptions IMG_OPTIONS = new DisplayImageOptions.Builder()
			.showImageOnLoading(R.drawable.ic_stub)
			.showImageForEmptyUri(R.drawable.ic_empty)
			.showImageOnFail(R.drawable.ic_stub)
			.cacheInMemory(true).cacheOnDisk(true)
			.considerExifParams(true)
			.bitmapConfig(Bitmap.Config.RGB_565).build();

	public AppConfig() {
		// TODO Auto-generated constructor stub
	}

}

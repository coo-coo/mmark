package com.coo.m.mark;

import org.litepal.LitePalApplication;

import android.util.Log;

import com.coo.ms.cloud.weixin.WeixinApi;
import com.kingstar.ngbf.ms.util.android.CommonConfig;
import com.kingstar.ngbf.ms.util.android.res.ResourceFactory;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * [框架]应用，APP入口
 * 
 * @author boqing.shen
 * @since 1.0
 * 
 */
public class MarkApplication extends LitePalApplication {

	private static final String TAG = MarkApplication.class.getSimpleName();

	@Override
	public void onCreate() {
		super.onCreate();
		Log.i(TAG, "App on created..");

		// 初始化Model
		initCommonModel();

		// 初始化ImageLoader
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				this).build();
		ImageLoader.getInstance().init(config);

		// 初始化资源Factory
		ResourceFactory.init(this);

		// // 初始化微信SDK
		WeixinApi.register(getApplicationContext());
	}

	private void initCommonModel() {
		CommonConfig.clearParams();
		CommonConfig.initParam(CommonConfig.KEY_CLASS_HOME_ACTIVITY,
				SysMainActivity.class);
		CommonConfig.initParam(CommonConfig.KEY_INT_DIALOG_VIEW_ID,
				R.layout.sys_common_dialog);
		CommonConfig.initParam(CommonConfig.KEY_INT_DIALOG_LAYOUT_ID,
				R.id.layout_dialog_common);
	}
}

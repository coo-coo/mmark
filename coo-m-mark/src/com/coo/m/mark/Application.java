package com.coo.m.mark;

import org.litepal.LitePalApplication;

<<<<<<< HEAD
import com.coo.m.sys.ContactManager;
import com.coo.m.sys.SysMainActivity;
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
public class Application extends LitePalApplication {

	@Override
	public void onCreate() {
		super.onCreate();

		// 初始化Model
		initCommonModel();

		// 初始化ImageLoader
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				this).build();
		ImageLoader.getInstance().init(config);

		// 初始化资源Factory
		ResourceFactory.init(this);

		// 初始化微信SDK
		WeixinApi.register(getApplicationContext());

		// Say Hello
		helloMark();
		
		// 同步本地通訊錄..
		ContactManager.syncLocalDevice(getApplicationContext());
=======
import com.coo.m.sys.SysMainActivity;
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
public class Application extends LitePalApplication {

	@Override
	public void onCreate() {
		super.onCreate();

		// 初始化Model
		initCommonModel();

		// 初始化ImageLoader
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				this).build();
		ImageLoader.getInstance().init(config);

		// 初始化资源Factory
		ResourceFactory.init(this);

		// 初始化微信SDK
		WeixinApi.register(getApplicationContext());

		// Say Hello
		helloMark();
>>>>>>> refs/remotes/origin/master
	}

	/**
	 * 初始化第一个Mark(当发现数据库没有时,认定是新装,创建)
	 */
	private void helloMark() {
		if (AppManager.getMarksAll().size() == 0) {
			MarkBean mark = new MarkBean();
			mark.setHost("13917081673");
			mark.setTarget("13917081673");
			String helloNote = "欢迎使用'"
					+ AppConfig.NAME
					+ "',在这里您可以写信给自己,给自己的好友,设定信打开的时间,不到时间是不能打开的哦~";
			mark.setNote(helloNote);
			mark.setTsi(System.currentTimeMillis());
			mark.setTso(System.currentTimeMillis());
			mark.save();
		}
	}

	/**
	 * 注册CommonModel参数
	 */
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

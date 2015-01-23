package com.coo.m.sys;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.coo.m.mark.AppManager;
import com.coo.m.mark.MarkAdapter;
import com.coo.m.mark.MarkBean;
import com.coo.m.mark.MarkCreateActivity;
import com.coo.m.mark.MarkHisActivity;
import com.coo.m.mark.R;
import com.coo.ms.cloud.weixin.WeixinHandler;
import com.kingstar.ngbf.ms.util.android.GenericActivity;

/**
 * [框架]主界面
 * 
 * @author boqing.shen
 * @since 1.0
 */
public class SysMainActivity extends GenericActivity {

	/**
	 * WeixinHandler
	 */
	@SuppressWarnings("unused")
	private WeixinHandler wxHandler = null;
	/**
	 * 适配器
	 */
	@SuppressWarnings("unused")
	private MarkAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		loadContent();
	}

	@Override
	public void loadContent() {
		setContentView(R.layout.sys_blank_activity);
		// 初始化WxHandler
		wxHandler = new WeixinHandler(this);

		// 代码构建ListView,不进行Find,需指定attr,参见styes.xml
		ListView listView = new ListView(this, null,
				R.attr.ref_common_lv);
		this.setContentView(listView);

		// 定义适配器
		adapter = new MarkAdapter(this,
				AppManager.getMarks(MarkBean.STATUS_CREATED),
				listView);
	}

	@Override
	public int getResMenuId() {
		return R.menu.main;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		// case R.id.item_main_about:
		// handleNext(this, SysAboutActivity.class);
		// break;
		// case R.id.item_main_share:
		// shareToWeixin();
		// break;
		case R.id.item_mark_create:
			onMarkCreate();
			break;
		case R.id.item_mark_his:
			handleNext(MarkHisActivity.class);
			break;
		case R.id.item_sys_version:
			handleNext(SysVersionActivity.class);
			break;
		case R.id.item_sys_quit:
			onSysQuit();
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * 系统退出
	 */
	private void onSysQuit(){
		AccountManager.onLogoff();
		// 跳转到登录界面
		handleNext(SysLoginActivity.class);
	}
	
	/**
	 * 创建刻度：判定是否登录
	 */
	private void onMarkCreate(){
		// 获得系统账号
		AccountBean account = AccountManager.get();
		if(account==null || account.getStatus()==AccountBean.STATUS_LOGOFF){
			// 没有账号,表明还没有登录或者没有注册,需要绑定账号
			// 如果有账号，但是登录状态为OFF,证明人工的退出,需要重新登录
			// 跳转到登录界面
			handleNext(SysLoginActivity.class);
		}
		else{
			// 已经有账号，且已经登录....
			handleNext(MarkCreateActivity.class);
		}
	}
}

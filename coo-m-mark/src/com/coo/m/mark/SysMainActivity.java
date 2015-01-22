package com.coo.m.mark;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.coo.ms.cloud.model.NetLink;
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
				MarkManager.getMarks(Mark.STATUS_CREATED),
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
		case R.id.item_main_version:
			handleNext(this, SysVersionActivity.class);
			break;
		case R.id.item_mark_create:
			handleNext(this, MarkCreateActivity.class);
			break;
		case R.id.item_mark_his:
			handleNext(this, MarkHisActivity.class);
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * 发送Link地址到微信
	 * 
	 * @since 1.3
	 */
	@SuppressWarnings("unused")
	private void shareToWeixin() {
		NetLink nl = MarkManager
				.createNetLink("百度,安智,安卓,91等市场都有的下哦~(暂时只支持安卓手机..)");
		wxHandler.share(nl);
	}

	/**
	 * 任务结束,跳转到下一个Activity,指定动画效果
	 */
	@SuppressWarnings("rawtypes")
	private void handleNext(Context context, Class cl) {
		Intent intent = new Intent();
		intent.setClass(context, cl);
		startActivity(intent);
	}
}

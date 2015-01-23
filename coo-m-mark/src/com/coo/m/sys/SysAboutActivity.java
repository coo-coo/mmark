package com.coo.m.sys;

import android.annotation.SuppressLint;
import android.webkit.WebView;

import com.coo.m.mark.AppConfig;
import com.coo.m.mark.R;
import com.kingstar.ngbf.ms.util.android.CommonBizActivity;
import com.kingstar.ngbf.ms.util.android.component.InnerWebViewClient;

/**
 * [框架]关于消磨,采用Baidu请应用形式实现,加载请应用网页地址
 * 
 * @author boqing.shen
 * @since 1.2
 * 
 */
public class SysAboutActivity extends CommonBizActivity {

	@Override
	public String getHeaderTitle() {
		return "关于" + AppConfig.NAME;
	}

	@Override
	public int getResViewLayoutId() {
		return R.layout.sys_about_activity;
	}

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	public void loadContent() {
		WebView webview = (WebView) findViewById(R.id.wv_about);

		webview.getSettings().setJavaScriptEnabled(true);
		// 触摸焦点起作用
		webview.requestFocus();
		webview.loadUrl(AppConfig.URL);
		// 页面中链接，如果希望点击链接继续在当前browser中响应
		webview.setWebViewClient(new InnerWebViewClient());
		// 取消滚动条
		// webview.setScrollBarStyle(SCROLLBARS_OUTSIDE_OVERLAY);
	}
}

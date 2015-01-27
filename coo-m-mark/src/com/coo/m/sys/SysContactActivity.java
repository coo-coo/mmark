package com.coo.m.sys;

import java.util.List;

import android.widget.ListView;

import com.coo.m.mark.R;
import com.kingstar.ngbf.ms.util.Reference;
import com.kingstar.ngbf.ms.util.android.CommonBizActivity;
import com.kingstar.ngbf.ms.util.android.CommonBizOptions;

/**
 * 【通讯录】,读取手机上用户的通讯录,用于识别手机账号是否已注册或者是进行(短信)邀请等操作
 * 
 * @since 1.0
 * @author boqing.shen
 * 
 */
public class SysContactActivity extends CommonBizActivity {

	@Override
	@Reference(override = CommonBizActivity.class)
	public CommonBizOptions getOptions() {
		return CommonBizOptions.blank().headerTitle("通讯录")
				.resViewLayoutId(R.layout.sys_blank_activity);
	}

	@Override
	public void loadContent() {
		// 从SQLLite中获取
		List<ContactBean> list = ContactManager.findAll();
		// 代码构建ListView,不进行Find,需指定attr,参见styes.xml
		ListView listView = new ListView(this, null,
				R.attr.ref_common_lv);
		this.setContentView(listView);

		// 定义适配器
		adapter = new SysContactAdapter(this, list, listView);
	}
}

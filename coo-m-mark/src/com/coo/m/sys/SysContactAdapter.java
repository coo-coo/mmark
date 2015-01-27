package com.coo.m.sys;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.coo.m.mark.R;
import com.coo.m.mark.TsUtil;
import com.kingstar.ngbf.ms.util.android.CommonAdapter;
import com.kingstar.ngbf.ms.util.android.CommonItemHolder;

/**
 * 通讯录适配器
 * 
 * @author boqing.shen
 * @since 1.0
 * 
 */
public class SysContactAdapter extends CommonAdapter<ContactBean> {

	private ContactItemHolder holder = new ContactItemHolder();
	@SuppressWarnings("unused")
	private Context context = null;

	public SysContactAdapter(Activity parent, List<ContactBean> items,
			ListView composite) {
		super(parent, items, composite);
		this.context = parent;
	}

	/**
	 * 返回控件布局
	 */
	public int getItemConvertViewId() {
		return R.layout.sys_contact_adapter;
	}

	@Override
	public CommonItemHolder initHolder(View convertView) {
		holder = new ContactItemHolder();
		holder.tv_mobile = (TextView) convertView
				.findViewById(R.id.tv_contact_mobile);
		holder.tv_name = (TextView) convertView
				.findViewById(R.id.tv_contact_name);
		holder.btn_invite = (Button) convertView
				.findViewById(R.id.btn_contact_invite);
		return holder;
	}

	@Override
	public void initHolderValue(CommonItemHolder ciHolder, ContactBean item) {
		holder = (ContactItemHolder) ciHolder;
		holder.tv_mobile.setText(item.getMobile());
		holder.tv_name.setText(item.getName());

		boolean invitable = isInvitable(item);
		holder.btn_invite.setEnabled(invitable);
		if (invitable) {
			holder.btn_invite.setText("邀请来玩");
			holder.btn_invite
					.setOnClickListener(new ContactItemListener(
							parent,
							item,
							this,
							ContactItemListener.ACTION_INVITE));
		} else {
			holder.btn_invite.setText("已邀请");
		}
	}

	/**
	 * 判断通讯录信息是否可以被邀请...
	 */
	private boolean isInvitable(ContactBean item) {
		boolean tof = true;
		if (item.getReged().equals(ContactBean.REG_YES)) {
			// 已注册,不能再被邀请
			// TODO 可以给TA写信
			tof = false;
		} else {
			// 未注册或不详
			long current = System.currentTimeMillis();
			if (TsUtil.getDiffDays(item.getTsu(), current) < 1) {
				// 一天内已经邀请
				tof = false;
			}
		}
		return tof;
	}
}

/**
 * 適配器Holder
 * 
 */
class ContactItemHolder extends CommonItemHolder {
	public TextView tv_mobile;
	public TextView tv_name;
	public Button btn_invite;
}

/**
 * 刻度条目监听器
 * 
 */
class ContactItemListener implements OnClickListener {

	private Activity context;
	private ContactBean item;
	private SysContactAdapter adapter;
	// 监听操作类型
	@SuppressWarnings("unused")
	private int action = ACTION_INVITE;

	public final static int ACTION_INVITE = 0;

	public ContactItemListener(Activity context, ContactBean item,
			SysContactAdapter adapter, int action) {
		this.context = context;
		this.item = item;
		this.adapter = adapter;
		this.action = action;
	}

	@Override
	public void onClick(View view) {
		// TODO 发送短信,进行邀请
		// 设置邀请时间
		item.setTsu(System.currentTimeMillis());
		item.update(item.getId());
		Toast.makeText(context, "TODO短信邀请:" + item.getMobile(),
				Toast.LENGTH_SHORT).show();
		adapter.notifyDataSetChanged();
	}
}

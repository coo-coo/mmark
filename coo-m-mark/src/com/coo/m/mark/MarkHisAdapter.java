package com.coo.m.mark;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.AbsListView;
import android.widget.TextView;

import com.kingstar.ngbf.ms.util.android.CommonAdapter;
import com.kingstar.ngbf.ms.util.android.CommonItemHolder;

/**
 * 刻度适配器
 * 
 * @author boqing.shen
 * @since 1.0
 * 
 */
public class MarkHisAdapter extends CommonAdapter<Mark> {

	MarkHisItemHolder holder = new MarkHisItemHolder();
	Context context = null;

	public MarkHisAdapter(Activity parent, List<Mark> items,
			AbsListView composite) {
		super(parent, items, composite);
		this.context = parent;
	}

	/**
	 * 返回控件布局
	 */
	public int getItemConvertViewId() {
		return R.layout.mark_his_adapter;
	}

	@Override
	public CommonItemHolder initHolder(View convertView) {
		holder = new MarkHisItemHolder();
		holder.tv_tsi = (TextView) convertView
				.findViewById(R.id.tv_mark_his_tsi);
		holder.tv_tso = (TextView) convertView
				.findViewById(R.id.tv_mark_his_tso);
		holder.tv_note = (TextView) convertView
				.findViewById(R.id.tv_mark_his_note);
		return holder;
	}

	@Override
	public void initHolderValue(CommonItemHolder ciHolder, Mark item) {
		holder = (MarkHisItemHolder) ciHolder;
		holder.tv_tsi.setText(MarkManager.getTsDateText2(item.getTsi()));
		holder.tv_tso.setText(MarkManager.getTsDateText2(item.getTso()));
		holder.tv_note.setText(item.getNote());
	}
}

/**
 * 適配器Holder
 * 
 * @author boqing.shen
 * 
 */
class MarkHisItemHolder extends CommonItemHolder {
	public TextView tv_tsi;
	public TextView tv_tso;
	public TextView tv_note;
}

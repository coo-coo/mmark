package com.coo.m.mark;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ListView;
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
public class MarkAdapter extends CommonAdapter<Mark> {

	GameScoreRowHolder holder = new GameScoreRowHolder();
	Context context = null;

	public MarkAdapter(Activity parent, List<Mark> items, ListView composite) {
		super(parent, items, composite);
		this.context = parent;
	}

	/**
	 * 返回控件布局
	 */
	public int getItemConvertViewId() {
		return R.layout.mark_adapter;
	}

	@Override
	public CommonItemHolder initHolder(View convertView) {
		holder = new GameScoreRowHolder();
		holder.tv_tsi = (TextView) convertView
				.findViewById(R.id.tv_mark_tsi);
		holder.tv_note = (TextView) convertView
				.findViewById(R.id.tv_mark_note);
		return holder;
	}

	@Override
	public void initHolderValue(CommonItemHolder ciHolder, Mark item) {
		holder = (GameScoreRowHolder) ciHolder;
		String ts = MarkManager.getTsExpression(item.getTsi());
		holder.tv_tsi.setText(ts);
		holder.tv_note.setText(item.getNote());
	}
}

class GameScoreRowHolder extends CommonItemHolder {
	public TextView tv_tsi;
	public TextView tv_note;
}

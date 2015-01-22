package com.coo.m.mark;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
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

	MarkItemHolder holder = new MarkItemHolder();
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
		holder = new MarkItemHolder();
		holder.tv_tsi = (TextView) convertView
				.findViewById(R.id.tv_mark_tsi);
		holder.btn_check = (Button) convertView
				.findViewById(R.id.btn_mark_check);
		holder.iv_help = (ImageView) convertView
				.findViewById(R.id.iv_mark_help);
		return holder;
	}

	@Override
	public void initHolderValue(CommonItemHolder ciHolder, Mark item) {
		holder = (MarkItemHolder) ciHolder;
		String ts = "(" + MarkManager.getTsDateText2(item.getTsi())
				+ ")你对自己说..." + item.getNote();
		holder.tv_tsi.setText(ts);

		// 判定是否可以查看,打开...
		boolean checkable = isCheckable(item);
		holder.btn_check.setEnabled(checkable);

		holder.btn_check.setOnClickListener(new MarkItemListener(
				parent, item, this,
				MarkItemListener.ACTION_CHECK));

		ImageView icon = holder.iv_help;
		icon.setAdjustViewBounds(true);
		// icon.setScaleType(ImageView.ScaleType.CENTER_CROP);
		icon.setPadding(0, 0, 1, 1);
		// TODO 根据Channel的状态进行设置图片资源, 0:未关注,1:已关注
		if (checkable) {
//			icon.setImageResource(R.drawable.ic_empty);
		} else {
			icon.setImageResource(R.drawable.balloon_blue);
			icon.setOnClickListener(new MarkItemListener(parent,
					item, this,
					MarkItemListener.ACTION_HELP));
		}
	}

	private boolean isCheckable(Mark item) {
		int diffDays = MarkManager.getDiffDays(
				System.currentTimeMillis(), item.getTso());
		if (Math.abs(diffDays) < 2) {
			// 小于15天,调试用
			return true;
		}
		return false;
	}

}

/**
 * 適配器Holder
 * 
 * @author boqing.shen
 * 
 */
class MarkItemHolder extends CommonItemHolder {
	public TextView tv_tsi;
	public Button btn_check;
	public ImageView iv_help;
}

/**
 * 刻度条目监听器
 * 
 * @author boqing.shen
 * 
 */
class MarkItemListener implements OnClickListener {

	private Activity context;
	private Mark item;
	private MarkAdapter adapter;
	// 监听操作类型
	private int action = ACTION_CHECK;

	public final static int ACTION_CHECK = 0;
	public final static int ACTION_HELP = 1;

	public MarkItemListener(Activity context, Mark item,
			MarkAdapter adapter, int action) {
		this.context = context;
		this.item = item;
		this.adapter = adapter;
		this.action = action;
	}

	@Override
	public void onClick(View view) {
		switch (action) {
		case ACTION_CHECK:
			// 显示刻度信息,弹出对话框
			new MarkCheckDialog(context, item, adapter).show();
			break;
		case ACTION_HELP:
			// 显示刻度信息,弹出对话框
			new MarkHelpDialog(context, item, adapter).show();
		}
	}
}

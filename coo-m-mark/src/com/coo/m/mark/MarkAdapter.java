package com.coo.m.mark;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageView;
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
public class MarkAdapter extends CommonAdapter<MarkBean> {

	private MarkItemHolder holder = new MarkItemHolder();
	@SuppressWarnings("unused")
	private Context context = null;

	public MarkAdapter(Activity parent, List<MarkBean> items,
			AbsListView composite) {
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
		holder.tv_tso = (TextView) convertView
				.findViewById(R.id.tv_mark_tso);
		holder.btn_check = (Button) convertView
				.findViewById(R.id.btn_mark_check);
		holder.iv_help = (ImageView) convertView
				.findViewById(R.id.iv_mark_help);
		return holder;
	}

	@Override
	public void initHolderValue(CommonItemHolder ciHolder, MarkBean item) {
		holder = (MarkItemHolder) ciHolder;
		// String tsi = "(" + TsUtil.dateCn(item.getTsi()) + ")你对自己说..."
		// + item.getNote();
		// holder.tv_tsi.setText(tsi);
		holder.tv_tsi.setText(TsUtil.dateEn(item.getTsi()) + "(发)");
		holder.tv_tso.setText(TsUtil.dateEn(item.getTso()) + "(收)");

		// 判定是否可以查看,打开...
		boolean checkable = isCheckable(item);

		ImageView icon = holder.iv_help;
		icon.setAdjustViewBounds(true);
		// icon.setScaleType(ImageView.ScaleType.CENTER_CROP);
		icon.setPadding(0, 0, 1, 1);
		// TODO 根据Channel的状态进行设置图片资源, 0:未关注,1:已关注
		if (checkable) {
			// icon.setImageResource(R.drawable.ic_empty);
			holder.btn_check.setEnabled(true);
			holder.btn_check.setOnClickListener(new MarkItemListener(
					parent, item, this,
					MarkItemListener.ACTION_CHECK));
			icon.setVisibility(View.INVISIBLE);
		} else {
			icon.setImageResource(R.drawable.balloon_blue);
			icon.setOnClickListener(new MarkItemListener(parent,
					item, this,
					MarkItemListener.ACTION_HELP));
			holder.btn_check.setVisibility(View.INVISIBLE);
		}
	}

	private boolean isCheckable(MarkBean item) {
		int diffDays = TsUtil.getDiffDays(System.currentTimeMillis(),
				item.getTso());
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
	public TextView tv_tso;
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
	private MarkBean item;
	private MarkAdapter adapter;
	// 监听操作类型
	private int action = ACTION_CHECK;

	public final static int ACTION_CHECK = 0;
	public final static int ACTION_HELP = 1;

	public MarkItemListener(Activity context, MarkBean item,
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

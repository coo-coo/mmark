package com.coo.m.mark;

import android.app.Activity;
import android.app.AlertDialog;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kingstar.ngbf.ms.util.android.CommonItemDialog;

/**
 * 刻度信息查看对话框
 * 
 * @since 1.0
 * @author boqing.shen
 * 
 */
public class MarkCheckDialog extends CommonItemDialog<MarkBean> {

	protected MarkAdapter adpater = null;

	/**
	 * 構造函數
	 * 
	 * @param parent
	 * @param item
	 */
	public MarkCheckDialog(Activity parent, MarkBean item, MarkAdapter adpater) {
		super(parent, item);
		this.adpater = adpater;
	}

	@Override
	public void initControls(LinearLayout layout) {
		TextView tvNote = new TextView(parent);
		tvNote.setText(item.getNote());
		// 添加控件
		layout.addView(tvNote);
		// 暂时是关闭处理,更改状态,通知ListView
		this.setButton(AlertDialog.BUTTON_POSITIVE, "看过", this);
	}

	@Override
	public void doOkAction() {
		// Toast.makeText(parent, item.getNote(),
		// Toast.LENGTH_SHORT).show();
		item.setStatus(MarkBean.STATUS_OPENED);
		item.update(item.getId());
		adpater.remove(item);
		adpater.notifyDataSetChanged();
	}

	@Override
	public int getBtnGroup() {
		return BTN_GROUP_NONE;
	}

	@Override
	public int getResIconId() {
		return AppConfig.ICON_RESID;
	}

	@Override
	public String getTitle() {
		String title = "刻度日期:" + TsUtil.dateCn(item.getTsi());
		return title;
	}

}

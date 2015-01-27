package com.coo.m.mark;

import android.app.Activity;
import android.app.AlertDialog;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 刻度帮助信息对话框,提示为何不能查看..
 * 
 * @since 1.0
 * @author boqing.shen
 * 
 */
public class MarkHelpDialog extends MarkCheckDialog {

	/**
	 * 構造函數
	 */
	public MarkHelpDialog(Activity parent, MarkBean item,
			MarkAdapter adpater) {
		super(parent, item, adpater);
	}

	@Override
	public void initControls(LinearLayout layout) {
		TextView tvNote = new TextView(parent, null,
				R.attr.ref_tv_mark_note);
		String text = "还没有到查看的时间(" + TsUtil.dateCn(item.getTso())
				+ ")哦,到时候再来看吧~";
		tvNote.setText(text);
		// 添加控件
		layout.addView(tvNote);
		// 暂时是关闭处理,更改状态,通知ListView
		this.setButton(AlertDialog.BUTTON_NEGATIVE, "知道了", this);
	}

}

package com.coo.m.mark;

import java.util.Calendar;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.kingstar.ngbf.ms.util.Reference;
import com.kingstar.ngbf.ms.util.StringUtil;
import com.kingstar.ngbf.ms.util.android.CommonBizActivity;
import com.kingstar.ngbf.ms.util.model.NgbfRuntimeException;

/**
 * 【创建话题】
 * 
 * @since 1.0
 * @author boqing.shen
 */
public class MarkCreateActivity extends CommonBizActivity implements
		DatePickerDialog.OnDateSetListener,
		DialogInterface.OnClickListener {

	protected static final String TAG = MarkCreateActivity.class.getName();
	/**
	 * Topic的文字主题输入框
	 */
	private EditText et_note;
	private TextView tv_tsi;
	private TextView tv_tso;
	private Button btn_create;

	// 当前日期
	private Calendar calendarTsi;
	// 打开日期
	private Calendar calendarTso;

	@Override
	public String getHeaderTitle() {
		return "写给自己";
	}

	@Override
	public int getResViewLayoutId() {
		return R.layout.mark_create_activity;
	}

	@Override
	public void loadContent() {
		// 设置
		tv_tsi = (TextView) findViewById(R.id.tv_mark_create_tsi);
		tv_tso = (TextView) findViewById(R.id.tv_mark_create_tso);
		et_note = (EditText) findViewById(R.id.et_mark_create_note);
		btn_create = (Button) findViewById(R.id.btn_mark_create);

		// 指定打开日期
		calendarTsi = Calendar.getInstance();
		// 创建日期
		tv_tsi.setText(MarkManager.getTsMinText(calendarTsi
				.getTimeInMillis()));

		// btn_create.setImageDrawable(getResources().getDrawable(
		// R.drawable.ic_stub));

		calendarTso = Calendar.getInstance();
		calendarTso.add(Calendar.DAY_OF_MONTH, 1);
		updateTsoText();

		tv_tso.setOnClickListener(this);
		btn_create.setOnClickListener(this);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_mark_create:
			doCreate();
			break;
		case R.id.tv_mark_create_tso:
			// 显示DatePickerDialog组件
			DatePickerDialog dpd = new DatePickerDialog(this, this,
					calendarTso.get(Calendar.YEAR),
					calendarTso.get(Calendar.MONTH),
					calendarTso.get(Calendar.DAY_OF_MONTH));
			dpd.show();
			break;
		}
	}

	/**
	 * 执行Mark创建操作,保存SQLite
	 */
	private void doCreate() {
		long start = calendarTsi.getTimeInMillis();
		long end = calendarTso.getTimeInMillis();
		int diffDays = MarkManager.getDiffDays(start, end);
		try {
			if (diffDays < 1) {
				throw new NgbfRuntimeException(
						"指定的接收日期在当前日期之前!请重新指定接收日期");
			}
			String note = et_note.getText().toString();
			if (StringUtil.isNullOrSpace(note)) {
				throw new NgbfRuntimeException("消息内容为空!");
			}
			if (note.length() > 70) {
				throw new NgbfRuntimeException("消息内容不能多于70个字符!");
			}
			Mark mark = new Mark();
			mark.setHost("13917081673");
			mark.setTarget("13917081673");
			mark.setNote(note);
			mark.setTsi(start);
			mark.setTso(end);
			boolean tof = mark.save();
			if (!tof) {
				throw new NgbfRuntimeException("保存失败!");
			} else {
				Intent intent = new Intent(this,
						SysMainActivity.class);
				startActivity(intent);
			}
		} catch (NgbfRuntimeException e) {
			notifyError(e.getMessage());
		}
	}

	@Override
	@Reference(override = DatePickerDialog.OnDateSetListener.class)
	public void onDateSet(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		calendarTso.set(Calendar.YEAR, year);
		calendarTso.set(Calendar.MONTH, monthOfYear);
		calendarTso.set(Calendar.DAY_OF_MONTH, dayOfMonth);
		updateTsoText();
	}

	private void updateTsoText() {
		// 计算打开时间和当前时间的相差天数
		long start = calendarTsi.getTimeInMillis();
		long end = calendarTso.getTimeInMillis();
		int diffDays = MarkManager.getDiffDays(start, end);
		tv_tso.setText(MarkManager.getTsDateText(end) + "(" + diffDays
				+ "天后)");
	}

	private void notifyError(String msg) {
		dialog = new AlertDialog.Builder(this).setCancelable(false)
				.setTitle("操作失败")
				.setIcon(MarkManager.APP_ICON_RESID)
				.setMessage(msg).setNegativeButton("知道了", this)
				.show();
	}

	public AlertDialog dialog = null;

	@Override
	@Reference(override = DialogInterface.OnClickListener.class)
	public void onClick(DialogInterface dialog, int which) {
		dialog.cancel();
	}
}

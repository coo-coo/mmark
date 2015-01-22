package com.coo.m.mark;

import java.util.List;

import android.widget.GridView;

import com.kingstar.ngbf.ms.util.Reference;
import com.kingstar.ngbf.ms.util.android.CommonBizActivity;
import com.kingstar.ngbf.ms.util.android.CommonBizOptions;

/**
 * 刻度历史信息
 * 
 * @author boqing.shen
 * @since 1.0
 */
public class MarkHisActivity extends CommonBizActivity {

	@Override
	@Reference(override = CommonBizActivity.class)
	public void loadContent() {
		// 代码构建ListView,不进行Find,需指定attr,参见styes.xml
		GridView gridView = (GridView)this.findViewById(R.id.gv_mark_his);
		
		List<Mark> items = MarkManager.getMarks(Mark.STATUS_OPENED);
		// 定义适配器
		@SuppressWarnings("unused")
		MarkHisAdapter adapter = new MarkHisAdapter(this, items,
				gridView);
	}

	@Override
	public CommonBizOptions getOptions() {
		return CommonBizOptions.blank().headerTitle("历史刻度")
				.resViewLayoutId(R.layout.mark_his_activity);
	}

}

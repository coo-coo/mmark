package com.coo.m.mark;

import java.util.List;

import android.app.Activity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.kingstar.ngbf.ms.util.android.CommonAdapter;
import com.kingstar.ngbf.ms.util.android.CommonItemHolder;
import com.kingstar.ngbf.ms.util.model.CommonItem;

/**
 * CommonItem对象的普通适配器
 * 
 * @since 1.3
 * @author boqing.shen
 */
public class ItemAdapter extends CommonAdapter<CommonItem> {

	/**
	 * 构造函数
	 */
	public ItemAdapter(Activity parent, List<CommonItem> items,
			ListView composite) {
		super(parent, items, composite);
	}

	@Override
	public int getItemConvertViewId() {
		return R.layout.sys_item_row;
	}

	@Override
	public CommonItemHolder initHolder(View convertView) {
		CommonItemRowHolder holder = new CommonItemRowHolder();
		holder.tv_label = (TextView) convertView
				.findViewById(R.id.common_item_row_label);
		holder.tv_value = (TextView) convertView
				.findViewById(R.id.common_item_row_value);
		return holder;
	}

	@Override
	public void initHolderValue(CommonItemHolder ciHolder, CommonItem item) {
		CommonItemRowHolder holder = (CommonItemRowHolder) ciHolder;
		holder.tv_label.setText(item.getLabel());
		holder.tv_value.setText("" + item.getValue());
	}
}

class CommonItemRowHolder extends CommonItemHolder {
	public TextView tv_label;
	public TextView tv_value;
}

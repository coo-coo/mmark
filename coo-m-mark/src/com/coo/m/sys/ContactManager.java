package com.coo.m.sys;

import java.util.ArrayList;
import java.util.List;

import org.litepal.crud.DataSupport;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import com.kingstar.ngbf.ms.util.RegexUtil;


/**
 * 通讯录管理
 * 
 * @author boqing.shen
 * 
 */
public final class ContactManager {

	public ContactManager() {
	}
	
	/**
	 * 同步本设备的通讯录信息
	 * TODO 考虑多次同步
	 * @param context
	 */
	public static void syncLocalDevice(Context context) {
		new Thread(new SyncLocalDeviceTask(context)).start();
	}
	
	/**
	 * 返回所有的通讯录信息
	 * 
	 * @return
	 */
	public static List<ContactBean> findAll() {
		return DataSupport.findAll(ContactBean.class);
	}

	/**
	 * 模拟
	 * 
	 * @return
	 */
	public static List<ContactBean> mockAll() {
		List<ContactBean> list = new ArrayList<ContactBean>();
		list.add(new ContactBean("13917081673", "沈大大"));
		list.add(new ContactBean("13816965673", "罗麻麻"));
		return list;
	}
	
	
	/**
	 * 同步本地电话薄信息
	 */
	public static List<ContactBean> findLocal(Context context) {
		List<ContactBean> list = new ArrayList<ContactBean>();
		Cursor cursor = context.getContentResolver().query(
				ContactsContract.Contacts.CONTENT_URI, null,
				null, null, null);
//		int count = 0;
		// 遍历查询结果，获取系统中所有联系人
		while (cursor.moveToNext()) {
			// 获取联系人ID
			String id = cursor
					.getString(cursor
							.getColumnIndex(ContactsContract.Contacts._ID));
			// 获取联系人的名字
			String name = cursor
					.getString(cursor
							.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

			

			// 使用ContentResolver查找联系人的电话号码
			Cursor phones = context
					.getContentResolver()
					.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
							null,
							ContactsContract.CommonDataKinds.Phone.CONTACT_ID
									+ " = "
									+ id,
							null, null);
			// 遍历查询结果，获取该联系人的多个电话号码
			while (phones.moveToNext()) {
				// 获取查询结果中电话号码列中数据。
				String mobile = phones
						.getString(phones
								.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
				// 是手机账号才获取
				if(RegexUtil.isMobile(mobile)){
					ContactBean cb = new ContactBean(mobile,name);
					list.add(cb);
				}
			}
			phones.close();
		}
		cursor.close();
		return list;
	}
}

class SyncLocalDeviceTask implements Runnable {
	
	private Context context;
	
	public SyncLocalDeviceTask(Context context){
		this.context = context;
	}
	
	public void run() {
		List<ContactBean> dbItems = ContactManager.findAll();
		if(dbItems.size()==0){
			// TODO 不为零也能同步?
			List<ContactBean> localItems = ContactManager.findLocal(context);
			for (ContactBean cb : localItems) {
				cb.setHost("HOST-TODO");
				cb.setTsi(System.currentTimeMillis());
				cb.save();
			}
		}
	}
}


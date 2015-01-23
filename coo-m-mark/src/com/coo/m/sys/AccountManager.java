package com.coo.m.sys;

import java.util.List;

import org.litepal.crud.DataSupport;

import com.kingstar.ngbf.s.ntp.NtpMessage;

/**
 * 账号管理,如果没有数据(可能是未注册,或有账号但重新安装了,跳转到登录界面) 退出(说明表格内已经有数据了[有且只有1条记录],设置状态)
 * 
 * @author boqing.shen
 * 
 */
public class AccountManager {

	public AccountManager() {
		// TODO Auto-generated constructor stub
	}

	public final static int RPC_ACCOUNT_ALL = 2010;
	public final static int RPC_ACCOUNT_UPDATE_STATUS = 2011;
	public final static int RPC_ACCOUNT_UPDATE_PARAM = 2012;
	public final static int RPC_ACCOUNT_INFO = 2013;
	public final static int RPC_ACCOUNT_LOGIN = 2014;
	public final static int RPC_ACCOUNT_SMS = 2015;
	public final static int RPC_ACCOUNT_REGISTER = 2016;

	/**
	 * 当前登录账号
	 */
	private static AccountBean current;

	/**
	 * 模拟登录,设置当前账号为数据库第一条
	 */
	public static void onMockLogon() {
		AccountBean account = get();
		if (account == null) {
			// 重新安装，第一次登录,插入数据
			account = new AccountBean();
			account.setTsi(System.currentTimeMillis());
			account.setMobile("13917081673");
			account.setNickname(""); // TODO 可能昵称已经有了
			account.setStatus(AccountBean.STATUS_LOGON);
			account.save();
		}
		// 设置当前登录账号
		current = account;
	}

	public static AccountBean getCurrent() {
		return current;
	}

	public static void setCurrent(AccountBean current) {
		AccountManager.current = current;
	}

	/**
	 * 获得当前账号，如果没有，需要注册或登录
	 * 
	 * @return
	 */
	public static AccountBean get() {
		List<AccountBean> list = DataSupport.findAll(AccountBean.class);
		AccountBean account = null;
		if (list.size() == 1) {
			account = list.get(0);
		}
		return account;
	}

	/**
	 * 判定是否有账号
	 */
	public static boolean hasAccount() {
		return get() == null ? false : true;
	}

	/**
	 * 当Rpc调用远端服务登录成功之后调用
	 * 
	 * @param mobile
	 */
	public static void onRpcLogon(NtpMessage resp, String mobile) {
		// 获得服务器端生成的id,作為uuid
		// String id = (String) resp.get("id");
		// String type = (String) resp.get("type");
		
		// 和密码无关,不存储密码...
		AccountBean account = get();
		if (account != null) {
			account.setStatus(AccountBean.STATUS_LOGON);
			account.setTsu(System.currentTimeMillis());
			account.update(account.getId());
		} else {
			// 重新安装，第一次登录,插入数据
			account = new AccountBean();
			account.setTsi(System.currentTimeMillis());
			account.setMobile(mobile);
			account.setNickname(""); // TODO 可能昵称已经有了
			account.setStatus(AccountBean.STATUS_LOGON);
			account.save();
		}
		// 设置当前登录账号
		current = account;
	}
	
	/**
	 * 登出,更改状态,不删除数据
	 */
	public static void onLogoff() {
		AccountBean account = current;
		if (account != null) {
			account.setStatus(AccountBean.STATUS_LOGOFF);
			account.update(account.getId());
		}
		current = null;
	}

}

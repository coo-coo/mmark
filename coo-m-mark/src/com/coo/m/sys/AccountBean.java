package com.coo.m.sys;

import org.litepal.crud.DataSupport;

/**
 * M端数据库,来记录当前账号信息(有且只有1条记录) 不记录密码,登出更改status标志位=STATUS_LOGOFF
 * 
 * @author boqing.shen
 * @since 1.0
 * 
 */
public class AccountBean extends DataSupport {

	private long id = 0l;
	/**
	 * 手机号:暂定为账号
	 */
	private String mobile = "";
	/**
	 * 昵称
	 */
	private String nickname = "";
	/**
	 * 消息类型:文本,音频,图片(+文本),视频,网页链接(+文本)等
	 */
	private String role = ROLE_COMMON;

	/**
	 * 首次登录时间戳
	 */
	private long tsi = 0l;

	/**
	 * 最近登录时间戳
	 */
	private long tsu = 0l;
	/**
	 * 登录状态
	 */
	private String status = STATUS_LOGOFF;

	// 状态:已登录
	public static String STATUS_LOGON = "1";
	// 状态:未登录
	public static String STATUS_LOGOFF = "0";

	// 角色类型:一般账户
	public static String ROLE_COMMON = "1";
	// 角色类型:管理员
	public static String ROLE_ADMIN = "9";

	/**
	 * 构造函数,创建一个Host给Target的Note
	 */
	public AccountBean() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public long getTsi() {
		return tsi;
	}

	public void setTsi(long tsi) {
		this.tsi = tsi;
	}

	public long getTsu() {
		return tsu;
	}

	public void setTsu(long tsu) {
		this.tsu = tsu;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}

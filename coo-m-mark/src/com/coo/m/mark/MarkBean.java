package com.coo.m.mark;

import org.litepal.crud.DataSupport;

/**
 * 游戏成绩
 * 
 * @author boqing.shen
 * @since 1.0
 * 
 */
public class MarkBean extends DataSupport {

	private long id = 0l;
	/**
	 * 信主:手机号(或其他TODO)
	 */
	private String host = "";
	/**
	 * 目标:手机号(或其他TODO)
	 */
	private String target = "";

	/**
	 * 备注内容(文本，TODO：图片、音频、视屏等，云存储):<70字符
	 */
	private String note = "";

	/**
	 * 消息类型:文本,音频,图片(+文本),视频,网页链接(+文本)等
	 */
	private String type = TYPE_TEXT;

	/**
	 * 创建(插入)时间戳，单位：毫秒 TimeStamp of Insert
	 */
	private long tsi = 0l;
	/**
	 * 定义打开的日期时间戳,指定日期的12:00
	 */
	private long tso = 0l;
	/**
	 * 实际打开时间戳:TimeStamp of Open Realtime
	 */
	private long tsor = 0l;
	/**
	 * 创建、打开状态 在一定时间范围内才能被打开,打开之后,进入历史...
	 */
	private String status = STATUS_CREATED;
	/**
	 * 云备份状态
	 */
	private String clouded = CLOUDED_NO;

	// 状态：已创建
	public static String STATUS_CREATED = "0";
	// 状态：已打开，已阅读
	public static String STATUS_OPENED = "9";

	// 云备份：已打开，已阅读
	public static String CLOUDED_NO = "0";
	public static String CLOUDED_YES = "1";

	// 类型:系统自动,文本,音频,图片(+文本),视频,网页链接(+文本)等
	public static String TYPE_TEXT = "1";
	public static String TYPE_LINK = "2";
	public static String TYPE_IMAGE = "3";
	public static String TYPE_AUDIO = "4";
	public static String TYPE_MEDIA = "5";
	public static String TYPE_SYS = "0";

	/**
	 * 构造函数,创建一个Host给Target的Note
	 */
	public MarkBean(String host, String target, String note, long tso) {
		this.host = host;
		this.target = target;
		this.note = note;
		this.tso = tso;
		this.tsi = System.currentTimeMillis();
		this.status = STATUS_CREATED;
		this.clouded = CLOUDED_NO;
	}

	public MarkBean() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public long getTsi() {
		return tsi;
	}

	public void setTsi(long tsi) {
		this.tsi = tsi;
	}

	public long getTso() {
		return tso;
	}

	public void setTso(long tso) {
		this.tso = tso;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getClouded() {
		return clouded;
	}

	public void setClouded(String clouded) {
		this.clouded = clouded;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getTsor() {
		return tsor;
	}

	public void setTsor(long tsor) {
		this.tsor = tsor;
	}
}

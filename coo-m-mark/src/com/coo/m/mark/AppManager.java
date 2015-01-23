package com.coo.m.mark;

import java.util.List;

import org.litepal.crud.DataSupport;

import android.graphics.Bitmap;
import android.os.Message;

import com.coo.ms.cloud.model.NetLink;
import com.kingstar.ngbf.ms.util.android.res.ResourceFactory;
import com.kingstar.ngbf.s.ntp.NtpHead;
import com.kingstar.ngbf.s.ntp.NtpMessage;

/**
 * [框架]游戏管理器
 * 
 * @author boqing.shen
 * @since 1.0
 */
public final class AppManager {

	
	/**
	 * 后台服务器地址
	 */
	public final static String HOST_REST = "http://10.253.45.103:8080/coo/rest";
//	public final static String HOST_SERVLET = "http://10.253.45.103:8080/vote/servlet";

	/**
	 * 判断返回信息是否OK
	 */
	public static boolean isRespOK(NtpMessage resp) {
		return resp.getHead().getRep_code().equals(NtpHead.REP_OK);
	}
	
	/**
	 * REST URI地址
	 */
	public static String rest(String relativeUrl) {
		return HOST_REST + relativeUrl;
	}
	
	/**
	 * 获得当前游戏玩家,即手机号
	 */
	public static String getCurrentPlayer() {
		// TODO
		return "13X12345678";
	}

	/**
	 * 创建简单的消息
	 */
	public static Message createMessage(int what, Object obj) {
		Message msg = new Message();
		msg.what = what;
		msg.obj = obj;
		return msg;
	}

	/**
	 * 创建分享链接
	 */
	public static NetLink createNetLink(String description) {
		// 指定标题等基础信息
		Bitmap thumb = ResourceFactory.getBitmap(AppConfig.ICON_RESID);
		NetLink nl = new NetLink("一起来玩\"消磨\"吧", AppConfig.URL, thumb);
		nl.setDescription(description);
		return nl;
	}

	/**
	 * 发送Link地址到微信
	 */
	@SuppressWarnings("unused")
	private void shareToWeixin() {
		NetLink nl = AppManager
				.createNetLink("百度,安智,安卓,91等市场都有的下哦~(暂时只支持安卓手机..)");
//		wxHandler.share(nl);
	}
	
	/**
	 * 根据状态获得Mark信息
	 */
	public static List<MarkBean> getMarks(String status) {
		// TODO 获得tso和当前tsc时间戳之间的消息
		return DataSupport.where("status = ?", status)
				.order("tsi desc").find(MarkBean.class);
	}

	/**
	 * 获取本机所有Mark信息
	 */
	public static List<MarkBean> getMarksAll() {
		return DataSupport.findAll(MarkBean.class);
	}

}

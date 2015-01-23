package com.coo.m.sys;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.coo.m.mark.AppConfig;
import com.coo.m.mark.AppManager;
import com.coo.m.mark.R;
import com.kingstar.ngbf.ms.util.Reference;
import com.kingstar.ngbf.ms.util.RegexUtil;
import com.kingstar.ngbf.ms.util.android.GenericActivity;
import com.kingstar.ngbf.ms.util.rpc2.IRpcCallback;
import com.kingstar.ngbf.ms.util.rpc2.RpcCallHandler;
import com.kingstar.ngbf.ms.util.rpc2.RpcCaller;
import com.kingstar.ngbf.s.ntp.NtpMessage;

/**
 * 【系统登录】
 * 
 * @since 1.0
 * @author boqing.shen
 */
public class SysLoginActivity extends GenericActivity implements
		OnClickListener, IRpcCallback {

	// 登陆按钮
	private Button btn_login;
	// 登录测试
	private Button btn_login_test;
	// 注册按钮
	private TextView tv_login_regist;
	// 用户名
	private EditText et_account;
	// 用户名密码
	private EditText et_pwd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.sys_login_activity);

		// 加载子内容
		loadContent();
	}

	@Override
	public void loadContent() {
		et_account = (EditText) findViewById(R.id.et_sys_login_account);
		et_pwd = (EditText) findViewById(R.id.et_sys_login_pwd);

		// TODO 测试用
		et_account.setText("13917081673");
		et_pwd.setText("222222");

		btn_login = (Button) findViewById(R.id.btn_sys_login_login);
		tv_login_regist = (TextView) findViewById(R.id.tv_sys_login_register);
		btn_login_test = (Button) findViewById(R.id.btn_sys_login_test);

		// 添加事件响应
		tv_login_regist.setOnClickListener(this);
		btn_login.setOnClickListener(this);
		btn_login_test.setOnClickListener(this);

		httpCaller = new RpcCaller(new RpcCallHandler(this));
		// FancyButton fb =
		// (FancyButton)findViewById(R.id.fb_sys_login_icon);
		// fb.setIconResource(R.drawable.ic_launcher);
		// fb.setRadius(30);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_sys_login_login:
			// 依据手机账号/密码登录,进行RPC请求
			onLogin();
			break;
		case R.id.tv_sys_login_register:
			// 跳转注册
			handleNext(SysRegisterActivity.class);
			break;
		case R.id.btn_sys_login_test:
			// 模拟登录
			AccountManager.onMockLogon();
			handleNext(AppConfig.MAIN);
			break;
		}
	}

	/**
	 * 登录实现
	 */
	private void onLogin() {
		mobile = et_account.getText().toString();
		password = et_pwd.getText().toString();

		boolean mobileValid = RegexUtil.isMobile(mobile);
		if (!mobileValid) {
			this.toast("登录账号不是手机号码!");
			return;
		}

		// RPC请求调用 参见AccountRestService.accountLogin
		String uri = "/account/login/mobile/" + mobile + "/password/"
				+ password;
		toast(uri);
		// 同步調用不可以,需要异步调用
		httpCaller.doGet(AccountManager.RPC_ACCOUNT_LOGIN,
				AppManager.rest(uri));
	}

	// 异步调用
	private RpcCaller httpCaller;
	// 手机号
	private String mobile = "";
	// 密码
	private String password = "";

	@Override
	@Reference(override = IRpcCallback.class)
	public void onHttpCallback(int what, NtpMessage resp) {
		// if (what == AccountManager.RPC_ACCOUNT_LOGIN) {}
		if (AppManager.isRespOK(resp)) {
			// 注册成功,即登录成功
			AccountManager.onRpcLogon(resp, mobile);
			// 跳转到主界面
			handleNext(AppConfig.MAIN);
		} else {
			toast("用户名或密码不正确!");
		}
	}

}

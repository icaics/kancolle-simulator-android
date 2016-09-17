package com.icaics.kancollesimulator.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.icaics.kancollesimulator.R;
import com.umeng.analytics.MobclickAgent;

public class AlmanacActivity extends Activity {
	
	private WebView webView;
	
	@Override
	@SuppressLint("SetJavaScriptEnabled")
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_almanac);
		// 初始化WebView
		webView = (WebView)findViewById(R.id.webViewAlmanac);
		// 支持JS
		WebSettings settings = webView.getSettings();
		settings.setJavaScriptEnabled(true);
		// 加载地址
		webView.loadUrl("http://sandbox.runjs.cn/show/x9ou86rn");
	}
	
	// 统计分析
	public void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
	}

	// 统计分析
	public void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
	}

}

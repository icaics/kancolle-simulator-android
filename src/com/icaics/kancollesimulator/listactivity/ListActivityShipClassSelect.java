package com.icaics.kancollesimulator.listactivity;

import com.icaics.kancollesimulator.utilty.ReadDatabase;
import com.umeng.analytics.MobclickAgent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListActivityShipClassSelect extends Activity {

	private ListView listView;
	ReadDatabase readDatabase;
	private static int clickedTextView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		readDatabase = new ReadDatabase();

		listView = new ListView(this);
		listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, readDatabase.getShipClass()));

		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO 调用舰船名称列表中的静态函数，启动舰船名称列表Activity
				ListActivityShipSelect.getShipName(ListActivityShipClassSelect.this, position + 1, clickedTextView);
			}
		});

		setContentView(listView);
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
	
	// 重写onActivityResult()方法用来接收从舰船名称列表返回的数据，并重定位到远征编成界面
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == 99) {
			// List list = data.getExtras().getStringArrayList("pathlist");
			this.setResult(99, data);
			// 仅接收到舰船名称列表回调时才销毁Activity，按返回键不销毁
			finish();
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	// 用户远征界面调用此Activity时使用
	public static void getShipClass(Context context, int clickedTextView) {
		Intent intent = new Intent(context, ListActivityShipClassSelect.class);
		ListActivityShipClassSelect.clickedTextView = clickedTextView;
		((Activity) context).startActivityForResult(intent, 1);
	}

}

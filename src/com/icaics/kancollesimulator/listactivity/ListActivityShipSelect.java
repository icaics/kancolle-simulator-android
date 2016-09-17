package com.icaics.kancollesimulator.listactivity;

import com.icaics.kancollesimulator.R;
import com.icaics.kancollesimulator.utilty.ReadDatabase;
import com.icaics.kancollesimulator.utilty.ToolFunction;
import com.umeng.analytics.MobclickAgent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ListActivityShipSelect extends Activity {

	private ListView listView;
	ReadDatabase readDatabase;
	ArrayAdapter<String> adapter;
	private static String strShipClass;
	private static int clickedTextView;

	// 标记显示全部舰娘还是改装舰娘
	private int kai = 0;
	// 保存显示类型
	private SharedPreferences sharedPreferences;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		readDatabase = new ReadDatabase();

		// 舰娘显示类型
		sharedPreferences = getApplicationContext().getSharedPreferences("config", Context.MODE_PRIVATE);
		kai = sharedPreferences.getInt("ShipSelectKAI", 0);
		System.out.println("显示舰娘类型 = " + kai);

		listView = new ListView(this);
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, readDatabase.getShipName(strShipClass, kai));
		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// 向远征编成界面传送数据
				Intent intent = new Intent();
				intent.putExtra("shipClass", strShipClass);
				intent.putExtra("kai", kai);
				intent.putExtra("position", position + 1);
				intent.putExtra("clickedTextView", clickedTextView);
				System.out.println(strShipClass);
				System.out.println(position + 1);
				System.out.println(clickedTextView);
				setResult(99, intent);
				finish();
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

	// 舰船类型列表跳转到此界面时调用
	public static void getShipName(Context context, int position, int clickedTextView) {
		Intent intent = new Intent(context, ListActivityShipSelect.class);
		intent.setAction("com.icaics.kancollesimulator.activity.ListActivityShipSelect");
		// 获取舰船类型
		strShipClass = ToolFunction.shipClassNumToString(position);
		ListActivityShipSelect.clickedTextView = clickedTextView;
		((Activity) context).startActivityForResult(intent, 2);
	}

	// 显示ActionBarMenu
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.shipselect, menu);
		// 按读取的配置设置菜单选中的选项
		switch (kai) {
		case 0:
			menu.findItem(R.id.menuShipSelectAll).setChecked(true);
			break;
		case 1:
			menu.findItem(R.id.menuShipSelectKai).setChecked(true);
			break;
		default:
			break;
		}
		return true;
	}

	// 实时修改选中的菜单项
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		super.onPrepareOptionsMenu(menu);
		switch (kai) {
		case 0:
			menu.findItem(R.id.menuShipSelectAll).setChecked(true);
			break;
		case 1:
			menu.findItem(R.id.menuShipSelectKai).setChecked(true);
			break;
		default:
			break;
		}
		return true;
	}

	// ActionBarMenu点击事件
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch (id) {
		case R.id.menuShipSelectAll:
			// 显示全部舰娘
			kai = 0;
			reloadShipSelectList(0);
			item.setChecked(true);
			break;
		case R.id.menuShipSelectKai:
			// 显示改装舰娘
			kai = 1;
			reloadShipSelectList(1);
			item.setChecked(true);
			break;
		default:
			break;
		}
		System.out.println("显示舰娘类型 = " + kai);
		// 保存显示类型
		sharedPreferences.edit().putInt("ShipSelectKAI", kai).commit();
		return true;
	}

	// 刷新舰娘列表
	private void reloadShipSelectList(int kai) {
		adapter.clear();
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, readDatabase.getShipName(strShipClass, kai));
		listView.setAdapter(adapter);
	}

}

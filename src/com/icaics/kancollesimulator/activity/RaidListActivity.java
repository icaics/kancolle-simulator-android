package com.icaics.kancollesimulator.activity;

import java.util.List;
import java.util.Map;

import com.icaics.kancollesimulator.R;
import com.icaics.kancollesimulator.adapter.AdapterRaidList;
import com.icaics.kancollesimulator.utilty.ReadDatabase;
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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class RaidListActivity extends Activity {

	private TextView textDataBaseVersion;
	private ListView listView;
	private AdapterRaidList listViewAdapter;
	private List<Map<String, Object>> listItems;

	ReadDatabase readDatabase;
	// 排序标记
	private int orderType = 1;
	// 保存排序
	private SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_raidlist);
		// 显示数据库版本
		readDatabase = new ReadDatabase();
		textDataBaseVersion = (TextView) findViewById(R.id.textDataBaseVersion);
		textDataBaseVersion.setText(readDatabase.getDatebaseVersion());
		// 数据排序读取
		sharedPreferences = getApplicationContext().getSharedPreferences("config", Context.MODE_PRIVATE);
		orderType = sharedPreferences.getInt("RaidListOrderType", 1);
		System.out.println("排序ID = " + orderType);
		// 加载列表及数据
		listView = (ListView) findViewById(R.id.listRaidList);
		listItems = readDatabase.getRaidListMap(orderType);
		listViewAdapter = new AdapterRaidList(this, listItems);
		listView.setAdapter(listViewAdapter);
		// 设置列表监听器
		listView.setOnItemClickListener(new ClickEvent());
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
	
	class ClickEvent implements OnItemClickListener {
		// ListItem监听器
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			// TODO Auto-generated method stub
			TextView textView = (TextView) view.findViewById(R.id.textRaidNum);
			int clickedID = Integer.parseInt(textView.getText().toString());
			Intent intent = new Intent(RaidListActivity.this, RaidFormationActivity.class);
			intent.putExtra("source", 1);
			intent.putExtra("raidNum", clickedID);
			startActivity(intent);
		}
	}

	// 显示ActionBarMenu
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.raidmap, menu);
		// 按读取的配置设置菜单选中的选项
		switch (orderType) {
		case 1:
			menu.findItem(R.id.menuRaidMapOrderByID).setChecked(true);
			break;
		case 2:
			menu.findItem(R.id.menuRaidMapOrderByIDDesc).setChecked(true);
			break;
		case 3:
			menu.findItem(R.id.menuRaidMapOrderByTime).setChecked(true);
			break;
		case 4:
			menu.findItem(R.id.menuRaidMapOrderByTimeDesc).setChecked(true);
			break;
		case 5:
			menu.findItem(R.id.menuRaidMapOrderByOilPerM).setChecked(true);
			break;
		case 6:
			menu.findItem(R.id.menuRaidMapOrderByAmmoPerM).setChecked(true);
			break;
		case 7:
			menu.findItem(R.id.menuRaidMapOrderBySteelPerM).setChecked(true);
			break;
		case 8:
			menu.findItem(R.id.menuRaidMapOrderByAlPerM).setChecked(true);
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
		switch (orderType) {
		case 1:
			menu.findItem(R.id.menuRaidMapOrderByID).setChecked(true);
			break;
		case 2:
			menu.findItem(R.id.menuRaidMapOrderByIDDesc).setChecked(true);
			break;
		case 3:
			menu.findItem(R.id.menuRaidMapOrderByTime).setChecked(true);
			break;
		case 4:
			menu.findItem(R.id.menuRaidMapOrderByTimeDesc).setChecked(true);
			break;
		case 5:
			menu.findItem(R.id.menuRaidMapOrderByOilPerM).setChecked(true);
			break;
		case 6:
			menu.findItem(R.id.menuRaidMapOrderByAmmoPerM).setChecked(true);
			break;
		case 7:
			menu.findItem(R.id.menuRaidMapOrderBySteelPerM).setChecked(true);
			break;
		case 8:
			menu.findItem(R.id.menuRaidMapOrderByAlPerM).setChecked(true);
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
		case R.id.menuRaidMapOrderByID:
			// 按ID排序
			orderType = 1;
			reloadRaidList(1);
			item.setChecked(true);
			break;
		case R.id.menuRaidMapOrderByIDDesc:
			// 按ID倒序
			orderType = 2;
			reloadRaidList(2);
			item.setChecked(true);
			break;
		case R.id.menuRaidMapOrderByTime:
			// 按时间排序
			orderType = 3;
			reloadRaidList(3);
			item.setChecked(true);
			break;
		case R.id.menuRaidMapOrderByTimeDesc:
			// 按时间倒序
			orderType = 4;
			reloadRaidList(4);
			item.setChecked(true);
			break;
		case R.id.menuRaidMapOrderByOilPerM:
			// 按燃料/分收益
			orderType = 5;
			reloadRaidList(5);
			item.setChecked(true);
			break;
		case R.id.menuRaidMapOrderByAmmoPerM:
			// 按弹药/分收益
			orderType = 6;
			reloadRaidList(6);
			item.setChecked(true);
			break;
		case R.id.menuRaidMapOrderBySteelPerM:
			// 按钢材/分收益
			orderType = 7;
			reloadRaidList(7);
			item.setChecked(true);
			break;
		case R.id.menuRaidMapOrderByAlPerM:
			// 按铝土/分收益
			orderType = 8;
			reloadRaidList(8);
			item.setChecked(true);
			break;
		default:
			break;
		}
		System.out.println("排序ID = " + orderType);
		// 保存排序
		sharedPreferences.edit().putInt("RaidListOrderType", orderType).commit();
		return true;
	}

	// 刷新远征列表
	private void reloadRaidList(int orderType) {
		// 不能直接listItems=集合，会导致无法刷新
		listItems.clear();
		listItems.addAll(readDatabase.getRaidListMap(orderType));
		listViewAdapter = new AdapterRaidList(this, listItems);
		listView.setAdapter(listViewAdapter);
	}

}
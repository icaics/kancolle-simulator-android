package com.icaics.kancollesimulator.activity;

import java.util.List;
import java.util.Map;

import com.icaics.kancollesimulator.R;
import com.icaics.kancollesimulator.adapter.AdapterEquip;
import com.icaics.kancollesimulator.utilty.ReadDatabase;
import com.umeng.analytics.MobclickAgent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class EquipSelectActivity extends Activity {

	ReadDatabase readDatabase;

	private int clickedTextView;
	private String shipClass;
	private int shipSpeed;

	private ListView listView;
	private AdapterEquip listViewAdapter;
	private List<Map<String, Object>> listItems;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_equip);

		readDatabase = new ReadDatabase();

		// 获得远征编成界面信息
		Intent intent = this.getIntent();
		shipClass = intent.getExtras().getString("shipClass");
		clickedTextView = intent.getExtras().getInt("clickedTextView");
		shipSpeed = intent.getExtras().getInt("speed");
		// 绑定ListAdapter
		listView = (ListView) findViewById(R.id.listEquip);
		listItems = readDatabase.getEquipListMap(shipClass, shipSpeed);
		listViewAdapter = new AdapterEquip(this, listItems);
		listView.setAdapter(listViewAdapter);
		// 绑定监听器
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

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			intent.putExtra("position", position + 1);
			setResult(clickedTextView, intent);
			finish();
		}
	}

}

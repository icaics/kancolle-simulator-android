package com.icaics.kancollesimulator.activity;

import java.util.List;
import java.util.Map;

import com.icaics.kancollesimulator.R;
import com.icaics.kancollesimulator.adapter.AdapterAttackList;
import com.icaics.kancollesimulator.utilty.ReadDatabase;
import com.umeng.analytics.MobclickAgent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class AttackListActivity extends Activity {

	ReadDatabase readDatabase;

	private ListView listView;
	private AdapterAttackList listViewAdapter;
	private List<Map<String, Object>> listItems;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_attacklist);

		readDatabase = new ReadDatabase();

		// 绑定ListAdapter
		listView = (ListView) findViewById(R.id.listAttackMap);
		listItems = readDatabase.getAttackMap();
		listViewAdapter = new AdapterAttackList(this, listItems);
		listView.setAdapter(listViewAdapter);

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
			if ((int) listItems.get(position).get("type") == 1) {
				Intent intent = new Intent(AttackListActivity.this, AttackFormationActivity.class);
				intent.putExtra("source", 1);
				intent.putExtra("attackMapNum", (int) listItems.get(position).get("id"));
				startActivity(intent);
			}
		}
	}

}

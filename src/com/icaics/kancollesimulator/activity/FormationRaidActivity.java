package com.icaics.kancollesimulator.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.icaics.kancollesimulator.R;
import com.icaics.kancollesimulator.adapter.AdapterFormationRaid;
import com.icaics.kancollesimulator.utilty.ReadDatabase;
import com.icaics.kancollesimulator.utilty.WriteDatabase;
import com.umeng.analytics.MobclickAgent;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class FormationRaidActivity extends Activity {

	private TextView textDataBaseVersion;
	private ListView listView;
	private AdapterFormationRaid listViewAdapter;
	private List<Map<String, Object>> listItems;
	private TextView textNoData;

	ReadDatabase readDatabase;
	WriteDatabase writeDatabase;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_formationraid);

		readDatabase = new ReadDatabase();
		writeDatabase = new WriteDatabase();
		// 获取控件
		textDataBaseVersion = (TextView) findViewById(R.id.textDataBaseVersion);
		textDataBaseVersion.setText(readDatabase.getDatebaseVersion());
		// 获取数据
		listView = (ListView) findViewById(R.id.listFormationRaid);
		listItems = new ArrayList<Map<String, Object>>();
		// 设置空白视图
		textNoData = (TextView) findViewById(R.id.textNoData);
		listView.setEmptyView(textNoData);
		// 绑定监听器
		listView.setOnItemClickListener(new ClickEvent());
		listView.setOnItemLongClickListener(new LongClickEvent());

	}

	// 统计分析
	public void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
	}

	@Override
	public void onResume() {
		// TODO 用于从已存列表进入编成列表以后重新修改保存了新的，返回这里时刷新
		super.onResume();
		// 放在这里首次进入和从编成界面回来时都能刷新（不能直接listItems=集合，会导致无法刷新）
		listItems.clear();
		listItems.addAll(readDatabase.getFormationRaidListMap());
		listViewAdapter = new AdapterFormationRaid(this, listItems);
		listView.setAdapter(listViewAdapter);
		// 统计分析
		MobclickAgent.onResume(this);
	}

	class ClickEvent implements OnItemClickListener {
		// ListItem监听器
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			// TODO Auto-generated method stub
			int savedID = (int) listItems.get(position).get("savedid");
			Intent intent = new Intent(FormationRaidActivity.this, RaidFormationActivity.class);
			intent.putExtra("source", 2);
			intent.putExtra("savedid", savedID);
			startActivity(intent);
		}
	}

	class LongClickEvent implements OnItemLongClickListener {
		// ListItem长按监听器
		@Override
		public boolean onItemLongClick(AdapterView<?> parent, final View view, final int position, long id) {
			// TODO Auto-generated method stub
			AlertDialog.Builder builder = new AlertDialog.Builder(FormationRaidActivity.this);
			builder.setTitle(R.string.textDeleteRaidFormation);
			// 确定按钮监听器
			builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// 删除此条记录
					int id = (int) listItems.get(position).get("savedid");
					if (writeDatabase.deleteFormation(id, 1)) {
						Toast.makeText(getApplicationContext(), R.string.deleteSucceed, Toast.LENGTH_SHORT).show();
					} else {
						Toast.makeText(getApplicationContext(), R.string.deleteFailed, Toast.LENGTH_SHORT).show();
					}
					// 刷新列表
					listItems.clear();
					listItems.addAll(readDatabase.getFormationRaidListMap());
					listViewAdapter.notifyDataSetChanged();
					dialog.dismiss();
				}
			});
			// 取消按钮监听器
			builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});
			builder.show();
			// return false;
			return true;
		}
	}

}

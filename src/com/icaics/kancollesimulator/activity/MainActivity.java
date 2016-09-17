package com.icaics.kancollesimulator.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.icaics.kancollesimulator.R;
import com.icaics.kancollesimulator.utilty.ReadDatabase;
import com.icaics.kancollesimulator.utilty.ToolFunction;
import com.umeng.analytics.MobclickAgent;
import com.umeng.update.UmengUpdateAgent;
import com.umeng.update.UmengUpdateListener;
import com.umeng.update.UpdateResponse;
import com.umeng.update.UpdateStatus;

public class MainActivity extends Activity {

	private ReadDatabase readDatabase;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 拷贝数据库
		copyDataBase();
		// 获取控件
		TextView textDataBaseVersion = (TextView) findViewById(R.id.textDataBaseVersion);
		Button btnAttackMap = (Button) findViewById(R.id.btnAttackMap);
		Button btnFormationAttack = (Button) findViewById(R.id.btnFormationAttack);
		Button btnRaidMap = (Button) findViewById(R.id.btnRaidMap);
		Button btnFormationRaid = (Button) findViewById(R.id.btnFormationRaid);
		Button btnAlmanac = (Button) findViewById(R.id.btnAlmanacActivity);
		Button btnModifyFactory = (Button) findViewById(R.id.btnModifyFactoryActivity);
		Button btnExpCalculator = (Button) findViewById(R.id.btnExpCalculator);
		// 绑定监听器
		btnAttackMap.setOnClickListener(new btnListenerAttackMap());
		btnFormationAttack.setOnClickListener(new btnListenerFormationAttack());
		btnRaidMap.setOnClickListener(new btnListenerRaidMap());
		btnFormationRaid.setOnClickListener(new btnListenerFormationRaid());
		btnAlmanac.setOnClickListener(new btnListenerAlmanac());
		btnModifyFactory.setOnClickListener(new btnListenerModifyFactory());
		btnExpCalculator.setOnClickListener(new btnListenerExpCalculator());
		// 显示数据库版本
		readDatabase = new ReadDatabase();
		textDataBaseVersion.setText(readDatabase.getDatebaseVersion());

		// 检查更新
		UmengUpdateAgent.update(this);
		// 统计分析发送策略
		MobclickAgent.updateOnlineConfig(this);

	}

	// 显示ActionBarMenu
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
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

	// ActionBarMenu点击事件
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch (id) {
		case R.id.menuAbout:
			// 关于界面
			Intent intent = new Intent(MainActivity.this, AboutActivity.class);
			startActivity(intent);
			break;
		case R.id.menuCheckUpdate:
			// 检查更新
			UmengUpdateAgent.setUpdateListener(new UmengUpdateListener() {
				@Override
				public void onUpdateReturned(int updateStatus, UpdateResponse updateInfo) {
					switch (updateStatus) {
					case UpdateStatus.Yes:
						UmengUpdateAgent.showUpdateDialog(getApplicationContext(), updateInfo);
						break;
					case UpdateStatus.No:
						Toast.makeText(getApplicationContext(), R.string.toastNoUpdate, Toast.LENGTH_SHORT).show();
						break;
					case UpdateStatus.Timeout:
						Toast.makeText(getApplicationContext(), R.string.toastUpdateTimeOut, Toast.LENGTH_SHORT).show();
						break;
					}
				}
			});
			UmengUpdateAgent.update(this);
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	class btnListenerAttackMap implements OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity.this, AttackListActivity.class);
			startActivity(intent);
		}
	}

	class btnListenerFormationAttack implements OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity.this, FormationAttackActivity.class);
			startActivity(intent);
		}
	}

	class btnListenerRaidMap implements OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity.this, RaidListActivity.class);
			startActivity(intent);
		}
	}

	class btnListenerFormationRaid implements OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity.this, FormationRaidActivity.class);
			startActivity(intent);
		}
	}

	class btnListenerAlmanac implements OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity.this, AlmanacActivity.class);
			startActivity(intent);
		}
	}

	class btnListenerModifyFactory implements OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity.this, ModifyFactoryActivity.class);
			startActivity(intent);
		}
	}

	class btnListenerExpCalculator implements OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity.this, ExpCalculatorActivity.class);
			startActivity(intent);
		}
	}

	// TODO 写入信息数据库
	public void copyDataBase() {
		String databaseFilenames = ToolFunction.DATABASE_PATH + ToolFunction.dbName;
		File dir = new File(ToolFunction.DATABASE_PATH);
		if (!dir.exists()) {
			dir.mkdir();
		}
		FileOutputStream os = null;
		try {
			os = new FileOutputStream(databaseFilenames);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		InputStream is = this.getResources().openRawResource(R.raw.data);
		byte[] buffer = new byte[1024];
		int count = 0;
		try {
			while ((count = is.read(buffer)) > 0) {
				os.write(buffer, 0, count);
				os.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			is.close();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

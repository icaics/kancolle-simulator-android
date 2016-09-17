package com.icaics.kancollesimulator.activity;

import java.util.HashMap;
import java.util.Map;

import com.icaics.kancollesimulator.R;
import com.icaics.kancollesimulator.listactivity.ListActivityShipClassSelect;
import com.icaics.kancollesimulator.utilty.ReadDatabase;
import com.icaics.kancollesimulator.utilty.ScreenShot;
import com.icaics.kancollesimulator.utilty.ToolFunction;
import com.icaics.kancollesimulator.utilty.WriteDatabase;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.sso.UMSsoHandler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class RaidFormationActivity extends Activity {

	private TextView textDataBaseVersion;
	private TextView textRaidFormationFlagLv, textRaidFormationFleetLv, textRaidFormationFleetLvDisplay, textRaidFormationShipRequire;
	private TextView textRaidFormation, textRaidFormationLv;
	// 舰队控件声明
	private ImageView imgEquip11, imgEquip12, imgEquip13, imgEquip14;
	private TextView textClear1, textShipName1, textEquip11, textEquip12, textEquip13, textEquip14;
	private EditText editShipLv1;
	private ImageView imgEquip21, imgEquip22, imgEquip23, imgEquip24;
	private TextView textClear2, textShipName2, textEquip21, textEquip22, textEquip23, textEquip24;
	private EditText editShipLv2;
	private ImageView imgEquip31, imgEquip32, imgEquip33, imgEquip34;
	private TextView textClear3, textShipName3, textEquip31, textEquip32, textEquip33, textEquip34;
	private EditText editShipLv3;
	private ImageView imgEquip41, imgEquip42, imgEquip43, imgEquip44;
	private TextView textClear4, textShipName4, textEquip41, textEquip42, textEquip43, textEquip44;
	private EditText editShipLv4;
	private ImageView imgEquip51, imgEquip52, imgEquip53, imgEquip54;
	private TextView textClear5, textShipName5, textEquip51, textEquip52, textEquip53, textEquip54;
	private EditText editShipLv5;
	private ImageView imgEquip61, imgEquip62, imgEquip63, imgEquip64;
	private TextView textClear6, textShipName6, textEquip61, textEquip62, textEquip63, textEquip64;
	private EditText editShipLv6;
	// 分享及保存按钮
	private Button btnShare, btnSaveFormation;
	// 远征详情
	private Map<String, Object> raidDetail = new HashMap<String, Object>();
	// 远征编成详情
	private Map<String, Object> raidFormationDetail = new HashMap<String, Object>();
	// 舰船及装备详情数据集合
	public static Map<String, Object> shipDetail1 = new HashMap<String, Object>();
	public static Map<String, Object> equipDetail11 = new HashMap<String, Object>();
	public static Map<String, Object> equipDetail12 = new HashMap<String, Object>();
	public static Map<String, Object> equipDetail13 = new HashMap<String, Object>();
	public static Map<String, Object> equipDetail14 = new HashMap<String, Object>();
	// 2
	public static Map<String, Object> shipDetail2 = new HashMap<String, Object>();
	public static Map<String, Object> equipDetail21 = new HashMap<String, Object>();
	public static Map<String, Object> equipDetail22 = new HashMap<String, Object>();
	public static Map<String, Object> equipDetail23 = new HashMap<String, Object>();
	public static Map<String, Object> equipDetail24 = new HashMap<String, Object>();
	// 3
	public static Map<String, Object> shipDetail3 = new HashMap<String, Object>();
	public static Map<String, Object> equipDetail31 = new HashMap<String, Object>();
	public static Map<String, Object> equipDetail32 = new HashMap<String, Object>();
	public static Map<String, Object> equipDetail33 = new HashMap<String, Object>();
	public static Map<String, Object> equipDetail34 = new HashMap<String, Object>();
	// 4
	public static Map<String, Object> shipDetail4 = new HashMap<String, Object>();
	public static Map<String, Object> equipDetail41 = new HashMap<String, Object>();
	public static Map<String, Object> equipDetail42 = new HashMap<String, Object>();
	public static Map<String, Object> equipDetail43 = new HashMap<String, Object>();
	public static Map<String, Object> equipDetail44 = new HashMap<String, Object>();
	// 5
	public static Map<String, Object> shipDetail5 = new HashMap<String, Object>();
	public static Map<String, Object> equipDetail51 = new HashMap<String, Object>();
	public static Map<String, Object> equipDetail52 = new HashMap<String, Object>();
	public static Map<String, Object> equipDetail53 = new HashMap<String, Object>();
	public static Map<String, Object> equipDetail54 = new HashMap<String, Object>();
	// 6
	public static Map<String, Object> shipDetail6 = new HashMap<String, Object>();
	public static Map<String, Object> equipDetail61 = new HashMap<String, Object>();
	public static Map<String, Object> equipDetail62 = new HashMap<String, Object>();
	public static Map<String, Object> equipDetail63 = new HashMap<String, Object>();
	public static Map<String, Object> equipDetail64 = new HashMap<String, Object>();
	// 舰船及装备详细数据变量
	private String ship1Class;
	private int ship1Slot, ship1Slot1, ship1Slot2, ship1Slot3, ship1Slot4;
	// private int ship1Speed;
	// 二号舰
	private String ship2Class;
	private int ship2Slot, ship2Slot1, ship2Slot2, ship2Slot3, ship2Slot4;
	// 三号舰
	private String ship3Class;
	private int ship3Slot, ship3Slot1, ship3Slot2, ship3Slot3, ship3Slot4;
	// 四号舰
	private String ship4Class;
	private int ship4Slot, ship4Slot1, ship4Slot2, ship4Slot3, ship4Slot4;
	// 五号舰
	private String ship5Class;
	private int ship5Slot, ship5Slot1, ship5Slot2, ship5Slot3, ship5Slot4;
	// 六号舰
	private String ship6Class;
	private int ship6Slot, ship6Slot1, ship6Slot2, ship6Slot3, ship6Slot4;
	// 舰队编成及等级计算
	private String strRaidFormation;
	private int intFleetLv = 0;
	private int intShipLv1 = 0, intShipLv2 = 0, intShipLv3 = 0, intShipLv4 = 0, intShipLv5 = 0, intShipLv6 = 0;
	private int intShipCount = 0;
	// 其他
	ReadDatabase readDatabase;
	WriteDatabase writeDatabase;
	private int raidNum;
	private String raidName;
	private int flagLvRequire, fleetLvRequire;
	// 来自已存远征编成界面所需变量
	private int savedID;
	private String savedName = "";
	// 分享
	final UMSocialService mController = UMServiceFactory.getUMSocialService("com.umeng.share");

	// TODO onCreate
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_raidformation);

		// 数据库初始化
		readDatabase = new ReadDatabase();
		writeDatabase = new WriteDatabase();

		// 控件初始化
		textDataBaseVersion = (TextView) findViewById(R.id.textDataBaseVersion);
		textRaidFormationFlagLv = (TextView) findViewById(R.id.textRaidFormationFlagLvDisplay);
		textRaidFormationFleetLv = (TextView) findViewById(R.id.textRaidFormationFleetLv);
		textRaidFormationFleetLvDisplay = (TextView) findViewById(R.id.textRaidFormationFleetLvDisplay);
		textRaidFormationShipRequire = (TextView) findViewById(R.id.textRaidShipRequire);
		textRaidFormation = (TextView) findViewById(R.id.textRaidFormation);
		textRaidFormationLv = (TextView) findViewById(R.id.textRaidFormationLv);
		btnShare = (Button) findViewById(R.id.btnShare);
		btnSaveFormation = (Button) findViewById(R.id.btnSaveFormation);
		// 舰队控件初始化
		shipTextViewInitialize();
		// 舰名及等级可用性初始化
		editShipLv1.setEnabled(false);
		ToolFunction.setShipNameAndLevelEnable(textShipName2, editShipLv2, false);
		ToolFunction.setShipNameAndLevelEnable(textShipName3, editShipLv3, false);
		ToolFunction.setShipNameAndLevelEnable(textShipName4, editShipLv4, false);
		ToolFunction.setShipNameAndLevelEnable(textShipName5, editShipLv5, false);
		ToolFunction.setShipNameAndLevelEnable(textShipName6, editShipLv6, false);
		// 装备部分可用性初始化
		ToolFunction.setEquipTextViewEnable(0, textEquip11, textEquip12, textEquip13, textEquip14);
		ToolFunction.setEquipTextViewEnable(0, textEquip21, textEquip22, textEquip23, textEquip24);
		ToolFunction.setEquipTextViewEnable(0, textEquip31, textEquip32, textEquip33, textEquip34);
		ToolFunction.setEquipTextViewEnable(0, textEquip41, textEquip42, textEquip43, textEquip44);
		ToolFunction.setEquipTextViewEnable(0, textEquip51, textEquip52, textEquip53, textEquip54);
		ToolFunction.setEquipTextViewEnable(0, textEquip61, textEquip62, textEquip63, textEquip64);
		// 显示数据库版本
		textDataBaseVersion.setText(readDatabase.getDatebaseVersion());

		// 清空舰队相关HashMap
		clearHashMap();

		// 接收处理Intent数据
		if (this.getIntent() != null) {
			Intent intent = this.getIntent();
			// 判断数据来源
			int source = intent.getExtras().getInt("source");
			System.out.println("Intent来自" + source);
			switch (source) {
			case 1:
				// 来自远征列表
				raidNum = intent.getExtras().getInt("raidNum");
				System.out.println("远征ID" + raidNum);
				break;
			case 2:
				// 来自已存远征编成列表
				savedID = intent.getExtras().getInt("savedid");
				System.out.println("远征编成保存的ID = " + savedID);
				// 只需提取已存编成对应的远征ID
				raidFormationDetail = readDatabase.getRaidFormationDetail(savedID);
				raidNum = (int) raidFormationDetail.get("id");
				System.out.println("远征的ID是" + raidNum);
				// 给本来在这个Activity中的raidDetail导入数据（下面还有一次，会刷两次屏）
				raidDetail = readDatabase.getRaidDetail(raidNum);
				// 读取已存远征编成给当前Activity相关变量写入数据
				setDataFromRaidDetail();
				// 计算舰船数量
				countShip();
				break;
			default:
				break;
			}
		}

		// 获取远征基本信息
		raidDetail = readDatabase.getRaidDetail(raidNum);
		raidName = raidDetail.get("name").toString();
		flagLvRequire = (int) raidDetail.get("flaglv");
		textRaidFormationFlagLv.setText("Lv" + flagLvRequire);
		fleetLvRequire = (int) raidDetail.get("fleetlv");
		textRaidFormationShipRequire.setText(raidDetail.get("require").toString());
		if (fleetLvRequire == 0) {
			textRaidFormationFleetLv.setVisibility(View.INVISIBLE);
			textRaidFormationFleetLvDisplay.setVisibility(View.INVISIBLE);
		} else {
			textRaidFormationFleetLv.setVisibility(View.VISIBLE);
			textRaidFormationFleetLvDisplay.setVisibility(View.VISIBLE);
			textRaidFormationFleetLvDisplay.setText("Lv" + fleetLvRequire);
		}

		// 设置Activity标题
		this.setTitle("远征 " + raidNum + "   " + raidName);

		// 设置舰队控件监听器
		setShip1ClickListener();
		setShip2ClickListener();
		setShip3ClickListener();
		setShip4ClickListener();
		setShip5ClickListener();
		setShip6ClickListener();
		// 设置实时等级计算监听器
		setEditTextChangeListener();

		// 刷新旗舰及舰队等级判断
		isFlagLvOK();
		isFleetLvOK();

		// 分享按钮监听器
		btnShare.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				if (intShipCount != 0) {
					// 准备字符
					String strShareString = getResources().getString(R.string.shareFormation1) + raidName + getResources().getString(R.string.shareFormation2);
					if (shipDetail1.size() != 0) {
						strShareString += shipDetail1.get("name") + getResources().getString(R.string.shareFormation3);
					}
					if (shipDetail2.size() != 0) {
						strShareString += shipDetail2.get("name") + getResources().getString(R.string.shareFormation3);
					}
					if (shipDetail3.size() != 0) {
						strShareString += shipDetail3.get("name") + getResources().getString(R.string.shareFormation3);
					}
					if (shipDetail4.size() != 0) {
						strShareString += shipDetail4.get("name") + getResources().getString(R.string.shareFormation3);
					}
					if (shipDetail5.size() != 0) {
						strShareString += shipDetail5.get("name") + getResources().getString(R.string.shareFormation3);
					}
					if (shipDetail6.size() != 0) {
						strShareString += shipDetail6.get("name");
					}
					// 屏幕截图 null参数为图片存储路径，编成界面路径在Shot类中指定
					ScreenShot.shoot(RaidFormationActivity.this, null, 2, intShipCount);
					// 分享
					ToolFunction.shareSNS(mController, RaidFormationActivity.this, strShareString, 2);
				} else {
					Toast.makeText(getApplicationContext(), R.string.toastOneShipLeast, Toast.LENGTH_SHORT).show();
				}
			}
		});

		// TODO 保存配置按钮监听器
		btnSaveFormation.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				if (intShipCount != 0) {
					AlertDialog.Builder builder = new AlertDialog.Builder(RaidFormationActivity.this);
					builder.setTitle(R.string.textEnterRaidFormationName);
					// builder.setIcon(android.R.drawable.ic_dialog_info);
					final EditText editText = new EditText(RaidFormationActivity.this);
					builder.setView(editText);
					// 加载已存配置名称
					if (savedName.length() != 0) {
						editText.setText(savedName);
					} else {
						editText.setText(R.string.textNewFormation);
					}
					editText.requestFocus();
					editText.selectAll();
					// 限制保存名称字数
					editText.setFilters(new InputFilter[] { new InputFilter.LengthFilter(10) });
					// 确定按钮监听器
					builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// 保存舰队配置
							boolean saveResult = writeDatabase.saveRaidFormation((int) raidDetail.get("id"), editText.getText().toString(), intShipLv1, intShipLv2, intShipLv3, intShipLv4, intShipLv5,
									intShipLv6);
							if (saveResult) {
								Toast.makeText(RaidFormationActivity.this, R.string.toastSaveSucceed, Toast.LENGTH_SHORT).show();
							} else {
								Toast.makeText(RaidFormationActivity.this, R.string.toastSaveFailed, Toast.LENGTH_SHORT).show();
							}
							dialog.dismiss();
							// RaidMapFormationActivity.this.finish();
						}
					});

					// 取消按钮监听器
					builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
							// RaidMapFormationActivity.this.finish();
						}
					});
					// builder.create();
					builder.show();
				} else {
					Toast.makeText(getApplicationContext(), R.string.toastOneShipLeast, Toast.LENGTH_SHORT).show();
				}
			}

		});

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
	
	// TODO 回调处理（舰船名 装备名显示等）
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		/** 使用SSO授权必须添加如下代码 **/
		UMSsoHandler ssoHandler = mController.getConfig().getSsoHandler(requestCode);
		if (ssoHandler != null) {
			ssoHandler.authorizeCallBack(requestCode, resultCode, data);
		}

		// 返回值为99 各舰船信息回调处理
		if (resultCode == 99) {
			// 提取Intent内容
			String shipClassTableName = data.getExtras().getString("shipClass");
			int kai = data.getExtras().getInt("kai");
			int shipPosition = data.getExtras().getInt("position");
			int clickedTextView = data.getExtras().getInt("clickedTextView");
			System.out.println(shipClassTableName);

			// 根据之前点击的不同TextView确定被操作舰船
			switch (clickedTextView) {
			case 1:
				// 获得舰船详细合集
				shipDetail1 = readDatabase.getShipDetail(shipClassTableName, shipPosition, kai);
				ship1Display();
				break;
			case 2:
				// 获得舰船详细合集
				shipDetail2 = readDatabase.getShipDetail(shipClassTableName, shipPosition, kai);
				ship2Display();
				break;
			case 3:
				// 获得舰船详细合集
				shipDetail3 = readDatabase.getShipDetail(shipClassTableName, shipPosition, kai);
				ship3Display();
				break;
			case 4:
				// 获得舰船详细合集
				shipDetail4 = readDatabase.getShipDetail(shipClassTableName, shipPosition, kai);
				ship4Display();
				break;
			case 5:
				// 获得舰船详细合集
				shipDetail5 = readDatabase.getShipDetail(shipClassTableName, shipPosition, kai);
				ship5Display();
				break;
			case 6:
				// 获得舰船详细合集
				shipDetail6 = readDatabase.getShipDetail(shipClassTableName, shipPosition, kai);
				ship6Display();
				break;
			default:
				break;
			}

			// 收到舰船回调后重新计算舰队编成
			listRaidFormation();
			// 计算舰船数量
			countShip();

		}

		// TODO 第一舰船第一装备槽回调处理
		if (resultCode == 11) {
			int equipPosition = data.getExtras().getInt("position");
			equipDetail11 = readDatabase.getEquipDetail(ship1Class, equipPosition, (int) shipDetail1.get("speed"));
			equip11Display();
		}
		// 第一舰船第二装备槽回调处理
		if (resultCode == 12) {
			int equipPosition = data.getExtras().getInt("position");
			equipDetail12 = readDatabase.getEquipDetail(ship1Class, equipPosition, (int) shipDetail1.get("speed"));
			equip12Display();
		}
		// 第一舰船第三装备槽回调处理
		if (resultCode == 13) {
			int equipPosition = data.getExtras().getInt("position");
			equipDetail13 = readDatabase.getEquipDetail(ship1Class, equipPosition, (int) shipDetail1.get("speed"));
			equip13Display();
		}
		// 第一舰船第四装备槽回调处理
		if (resultCode == 14) {
			int equipPosition = data.getExtras().getInt("position");
			equipDetail14 = readDatabase.getEquipDetail(ship1Class, equipPosition, (int) shipDetail1.get("speed"));
			equip14Display();
		}

		// TODO 第二舰船第一装备槽回调处理
		if (resultCode == 21) {
			int equipPosition = data.getExtras().getInt("position");
			equipDetail21 = readDatabase.getEquipDetail(ship2Class, equipPosition, (int) shipDetail2.get("speed"));
			equip21Display();
		}
		// 第二舰船第二装备槽回调处理
		if (resultCode == 22) {
			int equipPosition = data.getExtras().getInt("position");
			equipDetail22 = readDatabase.getEquipDetail(ship2Class, equipPosition, (int) shipDetail2.get("speed"));
			equip22Display();
		}
		// 第二舰船第三装备槽回调处理
		if (resultCode == 23) {
			int equipPosition = data.getExtras().getInt("position");
			equipDetail23 = readDatabase.getEquipDetail(ship2Class, equipPosition, (int) shipDetail2.get("speed"));
			equip23Display();
		}
		// 第二舰船第四装备槽回调处理
		if (resultCode == 24) {
			int equipPosition = data.getExtras().getInt("position");
			equipDetail24 = readDatabase.getEquipDetail(ship2Class, equipPosition, (int) shipDetail2.get("speed"));
			equip24Display();
		}

		// TODO 第三舰船第一装备槽回调处理
		if (resultCode == 31) {
			int equipPosition = data.getExtras().getInt("position");
			equipDetail31 = readDatabase.getEquipDetail(ship3Class, equipPosition, (int) shipDetail3.get("speed"));
			equip31Display();
		}
		// 第三舰船第二装备槽回调处理
		if (resultCode == 32) {
			int equipPosition = data.getExtras().getInt("position");
			equipDetail32 = readDatabase.getEquipDetail(ship3Class, equipPosition, (int) shipDetail3.get("speed"));
			equip32Display();
		}
		// 第三舰船第三装备槽回调处理
		if (resultCode == 33) {
			int equipPosition = data.getExtras().getInt("position");
			equipDetail33 = readDatabase.getEquipDetail(ship3Class, equipPosition, (int) shipDetail3.get("speed"));
			equip33Display();
		}
		// 第三舰船第四装备槽回调处理
		if (resultCode == 34) {
			int equipPosition = data.getExtras().getInt("position");
			equipDetail34 = readDatabase.getEquipDetail(ship3Class, equipPosition, (int) shipDetail3.get("speed"));
			equip34Display();
		}

		// TODO 第四舰船第一装备槽回调处理
		if (resultCode == 41) {
			int equipPosition = data.getExtras().getInt("position");
			equipDetail41 = readDatabase.getEquipDetail(ship4Class, equipPosition, (int) shipDetail4.get("speed"));
			equip41Display();
		}
		// 第四舰船第二装备槽回调处理
		if (resultCode == 42) {
			int equipPosition = data.getExtras().getInt("position");
			equipDetail42 = readDatabase.getEquipDetail(ship4Class, equipPosition, (int) shipDetail4.get("speed"));
			equip42Display();
		}
		// 第四舰船第三装备槽回调处理
		if (resultCode == 43) {
			int equipPosition = data.getExtras().getInt("position");
			equipDetail43 = readDatabase.getEquipDetail(ship4Class, equipPosition, (int) shipDetail4.get("speed"));
			equip43Display();
		}
		// 第四舰船第四装备槽回调处理
		if (resultCode == 44) {
			int equipPosition = data.getExtras().getInt("position");
			equipDetail44 = readDatabase.getEquipDetail(ship4Class, equipPosition, (int) shipDetail4.get("speed"));
			equip44Display();
		}

		// TODO 第五舰船第一装备槽回调处理
		if (resultCode == 51) {
			int equipPosition = data.getExtras().getInt("position");
			equipDetail51 = readDatabase.getEquipDetail(ship5Class, equipPosition, (int) shipDetail5.get("speed"));
			equip51Display();
		}
		// 第五舰船第二装备槽回调处理
		if (resultCode == 52) {
			int equipPosition = data.getExtras().getInt("position");
			equipDetail52 = readDatabase.getEquipDetail(ship5Class, equipPosition, (int) shipDetail5.get("speed"));
			equip52Display();
		}
		// 第五舰船第三装备槽回调处理
		if (resultCode == 53) {
			int equipPosition = data.getExtras().getInt("position");
			equipDetail53 = readDatabase.getEquipDetail(ship5Class, equipPosition, (int) shipDetail5.get("speed"));
			equip53Display();
		}
		// 第五舰船第四装备槽回调处理
		if (resultCode == 54) {
			int equipPosition = data.getExtras().getInt("position");
			equipDetail54 = readDatabase.getEquipDetail(ship5Class, equipPosition, (int) shipDetail5.get("speed"));
			equip54Display();
		}

		// TODO 第六舰船第一装备槽回调处理
		if (resultCode == 61) {
			int equipPosition = data.getExtras().getInt("position");
			equipDetail61 = readDatabase.getEquipDetail(ship6Class, equipPosition, (int) shipDetail6.get("speed"));
			equip61Display();
		}
		// 第六舰船第二装备槽回调处理
		if (resultCode == 62) {
			int equipPosition = data.getExtras().getInt("position");
			equipDetail62 = readDatabase.getEquipDetail(ship6Class, equipPosition, (int) shipDetail6.get("speed"));
			equip62Display();
		}
		// 第六舰船第三装备槽回调处理
		if (resultCode == 63) {
			int equipPosition = data.getExtras().getInt("position");
			equipDetail63 = readDatabase.getEquipDetail(ship6Class, equipPosition, (int) shipDetail6.get("speed"));
			equip63Display();
		}
		// 第六舰船第四装备槽回调处理
		if (resultCode == 64) {
			int equipPosition = data.getExtras().getInt("position");
			equipDetail64 = readDatabase.getEquipDetail(ship6Class, equipPosition, (int) shipDetail6.get("speed"));
			equip64Display();
		}

		// 清除文本框焦点防止屏幕乱跳
		editShipLv1.clearFocus();
		editShipLv2.clearFocus();
		editShipLv3.clearFocus();
		editShipLv4.clearFocus();
		editShipLv5.clearFocus();
		editShipLv6.clearFocus();
	}

	private void equip64Display() {
		if (ToolFunction.isAircraft((Integer) equipDetail64.get("type"))) {
			textEquip64.setText(String.valueOf(ship6Slot4) + "  " + (String) equipDetail64.get("name"));
		} else {
			textEquip64.setText((String) equipDetail64.get("name"));
		}
		// 显示装备图片
		ToolFunction.setEquipImg((Integer) equipDetail64.get("type"), imgEquip64);
	}

	private void equip63Display() {
		if (ToolFunction.isAircraft((Integer) equipDetail63.get("type"))) {
			textEquip63.setText(String.valueOf(ship6Slot3) + "  " + (String) equipDetail63.get("name"));
		} else {
			textEquip63.setText((String) equipDetail63.get("name"));
		}
		// 显示装备图片
		ToolFunction.setEquipImg((Integer) equipDetail63.get("type"), imgEquip63);
	}

	private void equip62Display() {
		if (ToolFunction.isAircraft((Integer) equipDetail62.get("type"))) {
			textEquip62.setText(String.valueOf(ship6Slot2) + "  " + (String) equipDetail62.get("name"));
		} else {
			textEquip62.setText((String) equipDetail62.get("name"));
		}
		// 显示装备图片
		ToolFunction.setEquipImg((Integer) equipDetail62.get("type"), imgEquip62);
	}

	private void equip61Display() {
		// 根据是否为舰载机判断是否显示搭载数
		if (ToolFunction.isAircraft((Integer) equipDetail61.get("type"))) {
			textEquip61.setText(String.valueOf(ship6Slot1) + "  " + (String) equipDetail61.get("name"));
		} else {
			textEquip61.setText((String) equipDetail61.get("name"));
		}
		// 显示装备图片
		ToolFunction.setEquipImg((Integer) equipDetail61.get("type"), imgEquip61);
	}

	private void equip54Display() {
		if (ToolFunction.isAircraft((Integer) equipDetail54.get("type"))) {
			textEquip54.setText(String.valueOf(ship5Slot4) + "  " + (String) equipDetail54.get("name"));
		} else {
			textEquip54.setText((String) equipDetail54.get("name"));
		}
		// 显示装备图片
		ToolFunction.setEquipImg((Integer) equipDetail54.get("type"), imgEquip54);
	}

	private void equip53Display() {
		if (ToolFunction.isAircraft((Integer) equipDetail53.get("type"))) {
			textEquip53.setText(String.valueOf(ship5Slot3) + "  " + (String) equipDetail53.get("name"));
		} else {
			textEquip53.setText((String) equipDetail53.get("name"));
		}
		// 显示装备图片
		ToolFunction.setEquipImg((Integer) equipDetail53.get("type"), imgEquip53);
	}

	private void equip52Display() {
		if (ToolFunction.isAircraft((Integer) equipDetail52.get("type"))) {
			textEquip52.setText(String.valueOf(ship5Slot2) + "  " + (String) equipDetail52.get("name"));
		} else {
			textEquip52.setText((String) equipDetail52.get("name"));
		}
		// 显示装备图片
		ToolFunction.setEquipImg((Integer) equipDetail52.get("type"), imgEquip52);
	}

	private void equip51Display() {
		// 根据是否为舰载机判断是否显示搭载数
		if (ToolFunction.isAircraft((Integer) equipDetail51.get("type"))) {
			textEquip51.setText(String.valueOf(ship5Slot1) + "  " + (String) equipDetail51.get("name"));
		} else {
			textEquip51.setText((String) equipDetail51.get("name"));
		}
		// 显示装备图片
		ToolFunction.setEquipImg((Integer) equipDetail51.get("type"), imgEquip51);
	}

	private void equip44Display() {
		if (ToolFunction.isAircraft((Integer) equipDetail44.get("type"))) {
			textEquip44.setText(String.valueOf(ship4Slot4) + "  " + (String) equipDetail44.get("name"));
		} else {
			textEquip44.setText((String) equipDetail44.get("name"));
		}
		// 显示装备图片
		ToolFunction.setEquipImg((Integer) equipDetail44.get("type"), imgEquip44);
	}

	private void equip43Display() {
		if (ToolFunction.isAircraft((Integer) equipDetail43.get("type"))) {
			textEquip43.setText(String.valueOf(ship4Slot3) + "  " + (String) equipDetail43.get("name"));
		} else {
			textEquip43.setText((String) equipDetail43.get("name"));
		}
		// 显示装备图片
		ToolFunction.setEquipImg((Integer) equipDetail43.get("type"), imgEquip43);
	}

	private void equip42Display() {
		if (ToolFunction.isAircraft((Integer) equipDetail42.get("type"))) {
			textEquip42.setText(String.valueOf(ship4Slot2) + "  " + (String) equipDetail42.get("name"));
		} else {
			textEquip42.setText((String) equipDetail42.get("name"));
		}
		// 显示装备图片
		ToolFunction.setEquipImg((Integer) equipDetail42.get("type"), imgEquip42);
	}

	private void equip41Display() {
		// 根据是否为舰载机判断是否显示搭载数
		if (ToolFunction.isAircraft((Integer) equipDetail41.get("type"))) {
			textEquip41.setText(String.valueOf(ship4Slot1) + "  " + (String) equipDetail41.get("name"));
		} else {
			textEquip41.setText((String) equipDetail41.get("name"));
		}
		// 显示装备图片
		ToolFunction.setEquipImg((Integer) equipDetail41.get("type"), imgEquip41);
	}

	private void equip34Display() {
		if (ToolFunction.isAircraft((Integer) equipDetail34.get("type"))) {
			textEquip34.setText(String.valueOf(ship3Slot4) + "  " + (String) equipDetail34.get("name"));
		} else {
			textEquip34.setText((String) equipDetail34.get("name"));
		}
		// 显示装备图片
		ToolFunction.setEquipImg((Integer) equipDetail34.get("type"), imgEquip34);
	}

	private void equip33Display() {
		if (ToolFunction.isAircraft((Integer) equipDetail33.get("type"))) {
			textEquip33.setText(String.valueOf(ship3Slot3) + "  " + (String) equipDetail33.get("name"));
		} else {
			textEquip33.setText((String) equipDetail33.get("name"));
		}
		// 显示装备图片
		ToolFunction.setEquipImg((Integer) equipDetail33.get("type"), imgEquip33);
	}

	private void equip32Display() {
		if (ToolFunction.isAircraft((Integer) equipDetail32.get("type"))) {
			textEquip32.setText(String.valueOf(ship3Slot2) + "  " + (String) equipDetail32.get("name"));
		} else {
			textEquip32.setText((String) equipDetail32.get("name"));
		}
		// 显示装备图片
		ToolFunction.setEquipImg((Integer) equipDetail32.get("type"), imgEquip32);
	}

	private void equip31Display() {
		// 根据是否为舰载机判断是否显示搭载数
		if (ToolFunction.isAircraft((Integer) equipDetail31.get("type"))) {
			textEquip31.setText(String.valueOf(ship3Slot1) + "  " + (String) equipDetail31.get("name"));
		} else {
			textEquip31.setText((String) equipDetail31.get("name"));
		}
		// 显示装备图片
		ToolFunction.setEquipImg((Integer) equipDetail31.get("type"), imgEquip31);
	}

	private void equip24Display() {
		if (ToolFunction.isAircraft((Integer) equipDetail24.get("type"))) {
			textEquip24.setText(String.valueOf(ship2Slot4) + "  " + (String) equipDetail24.get("name"));
		} else {
			textEquip24.setText((String) equipDetail24.get("name"));
		}
		// 显示装备图片
		ToolFunction.setEquipImg((Integer) equipDetail24.get("type"), imgEquip24);
	}

	private void equip23Display() {
		if (ToolFunction.isAircraft((Integer) equipDetail23.get("type"))) {
			textEquip23.setText(String.valueOf(ship2Slot3) + "  " + (String) equipDetail23.get("name"));
		} else {
			textEquip23.setText((String) equipDetail23.get("name"));
		}
		// 显示装备图片
		ToolFunction.setEquipImg((Integer) equipDetail23.get("type"), imgEquip23);
	}

	private void equip22Display() {
		if (ToolFunction.isAircraft((Integer) equipDetail22.get("type"))) {
			textEquip22.setText(String.valueOf(ship2Slot2) + "  " + (String) equipDetail22.get("name"));
		} else {
			textEquip22.setText((String) equipDetail22.get("name"));
		}
		// 显示装备图片
		ToolFunction.setEquipImg((Integer) equipDetail22.get("type"), imgEquip22);
	}

	private void equip21Display() {
		// 根据是否为舰载机判断是否显示搭载数
		if (ToolFunction.isAircraft((Integer) equipDetail21.get("type"))) {
			textEquip21.setText(String.valueOf(ship2Slot1) + "  " + (String) equipDetail21.get("name"));
		} else {
			textEquip21.setText((String) equipDetail21.get("name"));
		}
		// 显示装备图片
		ToolFunction.setEquipImg((Integer) equipDetail21.get("type"), imgEquip21);
	}

	private void equip14Display() {
		if (ToolFunction.isAircraft((Integer) equipDetail14.get("type"))) {
			textEquip14.setText(String.valueOf(ship1Slot4) + "  " + (String) equipDetail14.get("name"));
		} else {
			textEquip14.setText((String) equipDetail14.get("name"));
		}
		// 显示装备图片
		ToolFunction.setEquipImg((Integer) equipDetail14.get("type"), imgEquip14);
	}

	private void equip13Display() {
		if (ToolFunction.isAircraft((Integer) equipDetail13.get("type"))) {
			textEquip13.setText(String.valueOf(ship1Slot3) + "  " + (String) equipDetail13.get("name"));
		} else {
			textEquip13.setText((String) equipDetail13.get("name"));
		}
		// 显示装备图片
		ToolFunction.setEquipImg((Integer) equipDetail13.get("type"), imgEquip13);
	}

	private void equip12Display() {
		if (ToolFunction.isAircraft((Integer) equipDetail12.get("type"))) {
			textEquip12.setText(String.valueOf(ship1Slot2) + "  " + (String) equipDetail12.get("name"));
		} else {
			textEquip12.setText((String) equipDetail12.get("name"));
		}
		// 显示装备图片
		ToolFunction.setEquipImg((Integer) equipDetail12.get("type"), imgEquip12);
	}

	private void equip11Display() {
		// 根据是否为舰载机判断是否显示搭载数
		if (ToolFunction.isAircraft((Integer) equipDetail11.get("type"))) {
			textEquip11.setText(String.valueOf(ship1Slot1) + "  " + (String) equipDetail11.get("name"));
		} else {
			textEquip11.setText((String) equipDetail11.get("name"));
		}
		// 显示装备图片
		ToolFunction.setEquipImg((Integer) equipDetail11.get("type"), imgEquip11);
	}

	private void ship6Display() {
		// 从舰船集合中提取舰船详情
		ship6Class = (String) shipDetail6.get("class");
		ship6Slot = (Integer) shipDetail6.get("slot");
		ship6Slot1 = (Integer) shipDetail6.get("slot1");
		ship6Slot2 = (Integer) shipDetail6.get("slot2");
		ship6Slot3 = (Integer) shipDetail6.get("slot3");
		ship6Slot4 = (Integer) shipDetail6.get("slot4");
		// 根据舰船Slot判断下方装备槽是否可用
		textShipName6.setText((String) shipDetail6.get("name"));
		textEquip61.setText(String.valueOf(ship6Slot1));
		textEquip62.setText(String.valueOf(ship6Slot2));
		textEquip63.setText(String.valueOf(ship6Slot3));
		textEquip64.setText(String.valueOf(ship6Slot4));
		ToolFunction.clearEquipImage(imgEquip61, imgEquip62, imgEquip63, imgEquip64);
		ToolFunction.setEquipImageViewVisibility(ship6Slot, imgEquip61, imgEquip62, imgEquip63, imgEquip64);
		// 删除按钮处理
		textClear6.setVisibility(View.VISIBLE);
		textClear5.setVisibility(View.INVISIBLE);
		ToolFunction.setEquipTextViewVisibility(ship6Slot, textEquip61, textEquip62, textEquip63, textEquip64);
		// 设置解锁控件
		ToolFunction.setEquipTextViewEnable(ship6Slot, textEquip61, textEquip62, textEquip63, textEquip64);
		ToolFunction.setShipNameAndLevelEnable(textShipName6, editShipLv6, true);
		// 检查舰队编成是否符合要求
		isFormationMeetsRaidRequire();
	}

	private void ship5Display() {
		// 从舰船集合中提取舰船详情
		ship5Class = (String) shipDetail5.get("class");
		ship5Slot = (Integer) shipDetail5.get("slot");
		ship5Slot1 = (Integer) shipDetail5.get("slot1");
		ship5Slot2 = (Integer) shipDetail5.get("slot2");
		ship5Slot3 = (Integer) shipDetail5.get("slot3");
		ship5Slot4 = (Integer) shipDetail5.get("slot4");
		// 根据舰船Slot判断下方装备槽是否可用
		textShipName5.setText((String) shipDetail5.get("name"));
		textEquip51.setText(String.valueOf(ship5Slot1));
		textEquip52.setText(String.valueOf(ship5Slot2));
		textEquip53.setText(String.valueOf(ship5Slot3));
		textEquip54.setText(String.valueOf(ship5Slot4));
		// 设置装备图片
		ToolFunction.clearEquipImage(imgEquip51, imgEquip52, imgEquip53, imgEquip54);
		// 设置装备文字
		ToolFunction.setEquipImageViewVisibility(ship5Slot, imgEquip51, imgEquip52, imgEquip53, imgEquip54);
		ToolFunction.setEquipTextViewVisibility(ship5Slot, textEquip51, textEquip52, textEquip53, textEquip54);
		if (shipDetail6.size() == 0) {
			// 删除按钮处理
			textClear5.setVisibility(View.VISIBLE);
			textClear4.setVisibility(View.INVISIBLE);
			// 解锁名称及等级
			ToolFunction.setShipNameAndLevelEnable(textShipName6, editShipLv5, true);
			textShipName6.setText(R.string.textPleaseSelect);
		}
		// 设置解锁本舰装备控件
		ToolFunction.setEquipTextViewEnable(ship5Slot, textEquip51, textEquip52, textEquip53, textEquip54);
		// 检查舰队编成是否符合要求
		isFormationMeetsRaidRequire();
	}

	private void ship4Display() {
		// 从舰船集合中提取舰船详情
		ship4Class = (String) shipDetail4.get("class");
		ship4Slot = (Integer) shipDetail4.get("slot");
		ship4Slot1 = (Integer) shipDetail4.get("slot1");
		ship4Slot2 = (Integer) shipDetail4.get("slot2");
		ship4Slot3 = (Integer) shipDetail4.get("slot3");
		ship4Slot4 = (Integer) shipDetail4.get("slot4");
		// 根据舰船Slot判断下方装备槽是否可用
		textShipName4.setText((String) shipDetail4.get("name"));
		textEquip41.setText(String.valueOf(ship4Slot1));
		textEquip42.setText(String.valueOf(ship4Slot2));
		textEquip43.setText(String.valueOf(ship4Slot3));
		textEquip44.setText(String.valueOf(ship4Slot4));
		// 设置装备图片
		ToolFunction.clearEquipImage(imgEquip41, imgEquip42, imgEquip43, imgEquip44);
		ToolFunction.setEquipImageViewVisibility(ship4Slot, imgEquip41, imgEquip42, imgEquip43, imgEquip44);
		// 设置装备文字
		ToolFunction.setEquipTextViewVisibility(ship4Slot, textEquip41, textEquip42, textEquip43, textEquip44);
		if (shipDetail5.size() == 0) {
			// 删除按钮处理
			textClear4.setVisibility(View.VISIBLE);
			textClear3.setVisibility(View.INVISIBLE);
			// 解锁名称及等级
			ToolFunction.setShipNameAndLevelEnable(textShipName5, editShipLv4, true);
			textShipName5.setText(R.string.textPleaseSelect);
		}
		// 设置解锁本舰装备控件
		ToolFunction.setEquipTextViewEnable(ship4Slot, textEquip41, textEquip42, textEquip43, textEquip44);
		// 检查舰队编成是否符合要求
		isFormationMeetsRaidRequire();
	}

	private void ship3Display() {
		// 从舰船集合中提取舰船详情
		ship3Class = (String) shipDetail3.get("class");
		ship3Slot = (Integer) shipDetail3.get("slot");
		ship3Slot1 = (Integer) shipDetail3.get("slot1");
		ship3Slot2 = (Integer) shipDetail3.get("slot2");
		ship3Slot3 = (Integer) shipDetail3.get("slot3");
		ship3Slot4 = (Integer) shipDetail3.get("slot4");
		// 根据舰船Slot判断下方装备槽是否可用
		textShipName3.setText((String) shipDetail3.get("name"));
		textEquip31.setText(String.valueOf(ship3Slot1));
		textEquip32.setText(String.valueOf(ship3Slot2));
		textEquip33.setText(String.valueOf(ship3Slot3));
		textEquip34.setText(String.valueOf(ship3Slot4));
		// 设置装备图片
		ToolFunction.clearEquipImage(imgEquip31, imgEquip32, imgEquip33, imgEquip34);
		ToolFunction.setEquipImageViewVisibility(ship3Slot, imgEquip31, imgEquip32, imgEquip33, imgEquip34);
		// 设置装备文字
		ToolFunction.setEquipTextViewVisibility(ship3Slot, textEquip31, textEquip32, textEquip33, textEquip34);
		if (shipDetail4.size() == 0) {
			// 删除按钮处理
			textClear3.setVisibility(View.VISIBLE);
			textClear2.setVisibility(View.INVISIBLE);
			// 解锁名称及等级
			ToolFunction.setShipNameAndLevelEnable(textShipName4, editShipLv3, true);
			textShipName4.setText(R.string.textPleaseSelect);
		}
		// 设置解锁本舰装备控件
		ToolFunction.setEquipTextViewEnable(ship3Slot, textEquip31, textEquip32, textEquip33, textEquip34);
		// 检查舰队编成是否符合要求
		isFormationMeetsRaidRequire();
	}

	private void ship2Display() {
		// 从舰船集合中提取舰船详情
		ship2Class = (String) shipDetail2.get("class");
		ship2Slot = (Integer) shipDetail2.get("slot");
		ship2Slot1 = (Integer) shipDetail2.get("slot1");
		ship2Slot2 = (Integer) shipDetail2.get("slot2");
		ship2Slot3 = (Integer) shipDetail2.get("slot3");
		ship2Slot4 = (Integer) shipDetail2.get("slot4");
		// 根据舰船Slot判断下方装备槽是否可用
		textShipName2.setText((String) shipDetail2.get("name"));
		textEquip21.setText(String.valueOf(ship2Slot1));
		textEquip22.setText(String.valueOf(ship2Slot2));
		textEquip23.setText(String.valueOf(ship2Slot3));
		textEquip24.setText(String.valueOf(ship2Slot4));
		// 设置装备图片
		ToolFunction.clearEquipImage(imgEquip21, imgEquip22, imgEquip23, imgEquip24);
		ToolFunction.setEquipImageViewVisibility(ship2Slot, imgEquip21, imgEquip22, imgEquip23, imgEquip24);
		// 设置装备文字
		ToolFunction.setEquipTextViewVisibility(ship2Slot, textEquip21, textEquip22, textEquip23, textEquip24);
		if (shipDetail3.size() == 0) {
			// 删除按钮处理
			textClear2.setVisibility(View.VISIBLE);
			textClear1.setVisibility(View.INVISIBLE);
			// 解锁名称及等级
			ToolFunction.setShipNameAndLevelEnable(textShipName3, editShipLv2, true);
			textShipName3.setText(R.string.textPleaseSelect);
		}
		// 设置解锁本舰装备控件
		ToolFunction.setEquipTextViewEnable(ship2Slot, textEquip21, textEquip22, textEquip23, textEquip24);
		// 检查舰队编成是否符合要求
		isFormationMeetsRaidRequire();
	}

	private void ship1Display() {
		// 从舰船集合中提取舰船详情
		ship1Class = (String) shipDetail1.get("class");
		ship1Slot = (Integer) shipDetail1.get("slot");
		ship1Slot1 = (Integer) shipDetail1.get("slot1");
		ship1Slot2 = (Integer) shipDetail1.get("slot2");
		ship1Slot3 = (Integer) shipDetail1.get("slot3");
		ship1Slot4 = (Integer) shipDetail1.get("slot4");
		// 根据舰船Slot判断下方装备槽是否可用
		textShipName1.setText((String) shipDetail1.get("name"));
		textEquip11.setText(String.valueOf(ship1Slot1));
		textEquip12.setText(String.valueOf(ship1Slot2));
		textEquip13.setText(String.valueOf(ship1Slot3));
		textEquip14.setText(String.valueOf(ship1Slot4));
		// 设置装备图片
		ToolFunction.clearEquipImage(imgEquip11, imgEquip12, imgEquip13, imgEquip14);
		ToolFunction.setEquipImageViewVisibility(ship1Slot, imgEquip11, imgEquip12, imgEquip13, imgEquip14);
		// 设置装备文字
		ToolFunction.setEquipTextViewVisibility(ship1Slot, textEquip11, textEquip12, textEquip13, textEquip14);
		if (shipDetail2.size() == 0) {
			// 设置删除按钮
			textClear1.setVisibility(View.VISIBLE);
			// 解锁名称及等级
			ToolFunction.setShipNameAndLevelEnable(textShipName2, editShipLv1, true);
			textShipName2.setText(R.string.textPleaseSelect);
		}
		// 设置解锁本舰装备控件
		ToolFunction.setEquipTextViewEnable(ship1Slot, textEquip11, textEquip12, textEquip13, textEquip14);
		// 检查舰队编成是否符合要求
		isFormationMeetsRaidRequire();
	}

	// TODO 清除舰队相关HashMap
	private void clearHashMap() {
		shipDetail1.clear();
		equipDetail11.clear();
		equipDetail12.clear();
		equipDetail13.clear();
		equipDetail14.clear();
		shipDetail2.clear();
		equipDetail21.clear();
		equipDetail22.clear();
		equipDetail23.clear();
		equipDetail24.clear();
		shipDetail3.clear();
		equipDetail31.clear();
		equipDetail32.clear();
		equipDetail33.clear();
		equipDetail34.clear();
		shipDetail4.clear();
		equipDetail41.clear();
		equipDetail42.clear();
		equipDetail43.clear();
		equipDetail44.clear();
		shipDetail5.clear();
		equipDetail51.clear();
		equipDetail52.clear();
		equipDetail53.clear();
		equipDetail54.clear();
		shipDetail6.clear();
		equipDetail61.clear();
		equipDetail62.clear();
		equipDetail63.clear();
		equipDetail64.clear();
	}

	// TODO 舰队控件初始化
	private void shipTextViewInitialize() {
		// 一号舰控件
		textClear1 = (TextView) findViewById(R.id.textClear1);
		textShipName1 = (TextView) findViewById(R.id.textShipName1);
		editShipLv1 = (EditText) findViewById(R.id.editShipLv1);
		textEquip11 = (TextView) findViewById(R.id.textEquip11);
		textEquip12 = (TextView) findViewById(R.id.textEquip12);
		textEquip13 = (TextView) findViewById(R.id.textEquip13);
		textEquip14 = (TextView) findViewById(R.id.textEquip14);
		imgEquip11 = (ImageView) findViewById(R.id.imgEquip11);
		imgEquip12 = (ImageView) findViewById(R.id.imgEquip12);
		imgEquip13 = (ImageView) findViewById(R.id.imgEquip13);
		imgEquip14 = (ImageView) findViewById(R.id.imgEquip14);
		// 二号舰控件
		textClear2 = (TextView) findViewById(R.id.textClear2);
		textShipName2 = (TextView) findViewById(R.id.textShipName2);
		editShipLv2 = (EditText) findViewById(R.id.editShipLv2);
		textEquip21 = (TextView) findViewById(R.id.textEquip21);
		textEquip22 = (TextView) findViewById(R.id.textEquip22);
		textEquip23 = (TextView) findViewById(R.id.textEquip23);
		textEquip24 = (TextView) findViewById(R.id.textEquip24);
		imgEquip21 = (ImageView) findViewById(R.id.imgEquip21);
		imgEquip22 = (ImageView) findViewById(R.id.imgEquip22);
		imgEquip23 = (ImageView) findViewById(R.id.imgEquip23);
		imgEquip24 = (ImageView) findViewById(R.id.imgEquip24);
		// 三号舰控件
		textClear3 = (TextView) findViewById(R.id.textClear3);
		textShipName3 = (TextView) findViewById(R.id.textShipName3);
		editShipLv3 = (EditText) findViewById(R.id.editShipLv3);
		textEquip31 = (TextView) findViewById(R.id.textEquip31);
		textEquip32 = (TextView) findViewById(R.id.textEquip32);
		textEquip33 = (TextView) findViewById(R.id.textEquip33);
		textEquip34 = (TextView) findViewById(R.id.textEquip34);
		imgEquip31 = (ImageView) findViewById(R.id.imgEquip31);
		imgEquip32 = (ImageView) findViewById(R.id.imgEquip32);
		imgEquip33 = (ImageView) findViewById(R.id.imgEquip33);
		imgEquip34 = (ImageView) findViewById(R.id.imgEquip34);
		// 四号舰控件
		textClear4 = (TextView) findViewById(R.id.textClear4);
		textShipName4 = (TextView) findViewById(R.id.textShipName4);
		editShipLv4 = (EditText) findViewById(R.id.editShipLv4);
		textEquip41 = (TextView) findViewById(R.id.textEquip41);
		textEquip42 = (TextView) findViewById(R.id.textEquip42);
		textEquip43 = (TextView) findViewById(R.id.textEquip43);
		textEquip44 = (TextView) findViewById(R.id.textEquip44);
		imgEquip41 = (ImageView) findViewById(R.id.imgEquip41);
		imgEquip42 = (ImageView) findViewById(R.id.imgEquip42);
		imgEquip43 = (ImageView) findViewById(R.id.imgEquip43);
		imgEquip44 = (ImageView) findViewById(R.id.imgEquip44);
		// 五号舰控件
		textClear5 = (TextView) findViewById(R.id.textClear5);
		textShipName5 = (TextView) findViewById(R.id.textShipName5);
		editShipLv5 = (EditText) findViewById(R.id.editShipLv5);
		textEquip51 = (TextView) findViewById(R.id.textEquip51);
		textEquip52 = (TextView) findViewById(R.id.textEquip52);
		textEquip53 = (TextView) findViewById(R.id.textEquip53);
		textEquip54 = (TextView) findViewById(R.id.textEquip54);
		imgEquip51 = (ImageView) findViewById(R.id.imgEquip51);
		imgEquip52 = (ImageView) findViewById(R.id.imgEquip52);
		imgEquip53 = (ImageView) findViewById(R.id.imgEquip53);
		imgEquip54 = (ImageView) findViewById(R.id.imgEquip54);
		// 六号舰控件
		textClear6 = (TextView) findViewById(R.id.textClear6);
		textShipName6 = (TextView) findViewById(R.id.textShipName6);
		editShipLv6 = (EditText) findViewById(R.id.editShipLv6);
		textEquip61 = (TextView) findViewById(R.id.textEquip61);
		textEquip62 = (TextView) findViewById(R.id.textEquip62);
		textEquip63 = (TextView) findViewById(R.id.textEquip63);
		textEquip64 = (TextView) findViewById(R.id.textEquip64);
		imgEquip61 = (ImageView) findViewById(R.id.imgEquip61);
		imgEquip62 = (ImageView) findViewById(R.id.imgEquip62);
		imgEquip63 = (ImageView) findViewById(R.id.imgEquip63);
		imgEquip64 = (ImageView) findViewById(R.id.imgEquip64);
	}

	// TODO 设置一号舰控件监听器
	private void setShip1ClickListener() {
		// 设置一号舰舰名点击事件
		textShipName1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO 调用舰船类型列表中的静态函数，启动舰船类型列表Activity
				ListActivityShipClassSelect.getShipClass(RaidFormationActivity.this, 1);
			}
		});
		// 一号舰清空点击
		textClear1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				ToolFunction.clearShipWiget(textClear1, textShipName1, editShipLv1, imgEquip11, textEquip11, imgEquip12, textEquip12, imgEquip13, textEquip13, imgEquip14, textEquip14, shipDetail1,
						equipDetail11, equipDetail12, equipDetail13, equipDetail14);
				textShipName2.setEnabled(false);
				textShipName2.setText("");
				// 删除舰船后重新计算舰队编成
				listRaidFormation();
				// 检查舰队编成是否符合要求
				isFormationMeetsRaidRequire();
				// 计算舰船数量
				countShip();
			}
		});
		// 一号舰装备槽1点击
		textEquip11.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("shipClass", (String) shipDetail1.get("class"));
				intent.putExtra("speed", (int) shipDetail1.get("speed"));
				intent.putExtra("clickedTextView", 11);
				intent.setClass(RaidFormationActivity.this, EquipSelectActivity.class);
				startActivityForResult(intent, 11);
			}
		});
		// 一号舰装备槽2点击
		textEquip12.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("shipClass", (String) shipDetail1.get("class"));
				intent.putExtra("speed", (int) shipDetail1.get("speed"));
				intent.putExtra("clickedTextView", 12);
				intent.setClass(RaidFormationActivity.this, EquipSelectActivity.class);
				startActivityForResult(intent, 12);
			}
		});
		// 一号舰装备槽3点击
		textEquip13.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("shipClass", (String) shipDetail1.get("class"));
				intent.putExtra("speed", (int) shipDetail1.get("speed"));
				intent.putExtra("clickedTextView", 13);
				intent.setClass(RaidFormationActivity.this, EquipSelectActivity.class);
				startActivityForResult(intent, 13);
			}
		});
		// 一号舰装备槽4点击
		textEquip14.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("shipClass", (String) shipDetail1.get("class"));
				intent.putExtra("speed", (int) shipDetail1.get("speed"));
				intent.putExtra("clickedTextView", 14);
				intent.setClass(RaidFormationActivity.this, EquipSelectActivity.class);
				startActivityForResult(intent, 14);
			}
		});
	}

	// TODO 设置二号舰控件监听器
	private void setShip2ClickListener() {
		// 设置二号舰舰名点击事件
		textShipName2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO 调用舰船类型列表中的静态函数，启动舰船类型列表Activity
				ListActivityShipClassSelect.getShipClass(RaidFormationActivity.this, 2);
			}
		});
		// 二号舰清空点击
		textClear2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				ToolFunction.clearShipWiget(textClear2, textShipName2, editShipLv2, imgEquip21, textEquip21, imgEquip22, textEquip22, imgEquip23, textEquip23, imgEquip24, textEquip24, shipDetail2,
						equipDetail21, equipDetail22, equipDetail23, equipDetail24);
				textClear1.setVisibility(View.VISIBLE);
				textShipName3.setEnabled(false);
				textShipName3.setText("");
				// 删除舰船后重新计算舰队编成
				listRaidFormation();
				// 检查舰队编成是否符合要求
				isFormationMeetsRaidRequire();
				// 计算舰船数量
				countShip();
			}
		});
		// 二号舰装备槽1点击
		textEquip21.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("shipClass", (String) shipDetail2.get("class"));
				intent.putExtra("speed", (int) shipDetail2.get("speed"));
				intent.putExtra("clickedTextView", 21);
				intent.setClass(RaidFormationActivity.this, EquipSelectActivity.class);
				startActivityForResult(intent, 21);
			}
		});
		// 二号舰装备槽2点击
		textEquip22.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("shipClass", (String) shipDetail2.get("class"));
				intent.putExtra("speed", (int) shipDetail2.get("speed"));
				intent.putExtra("clickedTextView", 22);
				intent.setClass(RaidFormationActivity.this, EquipSelectActivity.class);
				startActivityForResult(intent, 22);
			}
		});
		// 二号舰装备槽3点击
		textEquip23.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("shipClass", (String) shipDetail2.get("class"));
				intent.putExtra("speed", (int) shipDetail2.get("speed"));
				intent.putExtra("clickedTextView", 23);
				intent.setClass(RaidFormationActivity.this, EquipSelectActivity.class);
				startActivityForResult(intent, 23);
			}
		});
		// 二号舰装备槽4点击
		textEquip24.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("shipClass", (String) shipDetail2.get("class"));
				intent.putExtra("speed", (int) shipDetail2.get("speed"));
				intent.putExtra("clickedTextView", 24);
				intent.setClass(RaidFormationActivity.this, EquipSelectActivity.class);
				startActivityForResult(intent, 24);
			}
		});
	}

	// TODO 设置三号舰控件监听器
	private void setShip3ClickListener() {
		// 设置三号舰舰名点击事件
		textShipName3.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO 调用舰船类型列表中的静态函数，启动舰船类型列表Activity
				ListActivityShipClassSelect.getShipClass(RaidFormationActivity.this, 3);
			}
		});
		// 三号舰清空点击
		textClear3.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				ToolFunction.clearShipWiget(textClear3, textShipName3, editShipLv3, imgEquip31, textEquip31, imgEquip32, textEquip32, imgEquip33, textEquip33, imgEquip34, textEquip34, shipDetail3,
						equipDetail31, equipDetail32, equipDetail33, equipDetail34);
				textClear2.setVisibility(View.VISIBLE);
				textShipName4.setEnabled(false);
				textShipName4.setText("");
				// 删除舰船后重新计算舰队编成
				listRaidFormation();
				// 检查舰队编成是否符合要求
				isFormationMeetsRaidRequire();
				// 计算舰船数量
				countShip();
			}
		});
		// 三号舰装备槽1点击
		textEquip31.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("shipClass", (String) shipDetail3.get("class"));
				intent.putExtra("speed", (int) shipDetail3.get("speed"));
				intent.putExtra("clickedTextView", 31);
				intent.setClass(RaidFormationActivity.this, EquipSelectActivity.class);
				startActivityForResult(intent, 31);
			}
		});
		// 三号舰装备槽2点击
		textEquip32.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("shipClass", (String) shipDetail3.get("class"));
				intent.putExtra("speed", (int) shipDetail3.get("speed"));
				intent.putExtra("clickedTextView", 32);
				intent.setClass(RaidFormationActivity.this, EquipSelectActivity.class);
				startActivityForResult(intent, 32);
			}
		});
		// 三号舰装备槽3点击
		textEquip33.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("shipClass", (String) shipDetail3.get("class"));
				intent.putExtra("speed", (int) shipDetail3.get("speed"));
				intent.putExtra("clickedTextView", 33);
				intent.setClass(RaidFormationActivity.this, EquipSelectActivity.class);
				startActivityForResult(intent, 33);
			}
		});
		// 三号舰装备槽4点击
		textEquip34.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("shipClass", (String) shipDetail3.get("class"));
				intent.putExtra("speed", (int) shipDetail3.get("speed"));
				intent.putExtra("clickedTextView", 34);
				intent.setClass(RaidFormationActivity.this, EquipSelectActivity.class);
				startActivityForResult(intent, 34);
			}
		});
	}

	// TODO 设置四号舰控件监听器
	private void setShip4ClickListener() {
		// 设置四号舰舰名点击事件
		textShipName4.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO 调用舰船类型列表中的静态函数，启动舰船类型列表Activity
				ListActivityShipClassSelect.getShipClass(RaidFormationActivity.this, 4);
			}
		});
		// 四号舰清空点击
		textClear4.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				ToolFunction.clearShipWiget(textClear4, textShipName4, editShipLv4, imgEquip41, textEquip41, imgEquip42, textEquip42, imgEquip43, textEquip43, imgEquip44, textEquip44, shipDetail4,
						equipDetail41, equipDetail42, equipDetail43, equipDetail44);
				textClear3.setVisibility(View.VISIBLE);
				textShipName5.setEnabled(false);
				textShipName5.setText("");
				// 删除舰船后重新计算舰队编成
				listRaidFormation();
				// 检查舰队编成是否符合要求
				isFormationMeetsRaidRequire();
				// 计算舰船数量
				countShip();
			}
		});
		// 四号舰装备槽1点击
		textEquip41.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("shipClass", (String) shipDetail4.get("class"));
				intent.putExtra("speed", (int) shipDetail4.get("speed"));
				intent.putExtra("clickedTextView", 41);
				intent.setClass(RaidFormationActivity.this, EquipSelectActivity.class);
				startActivityForResult(intent, 41);
			}
		});
		// 四号舰装备槽2点击
		textEquip42.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("shipClass", (String) shipDetail4.get("class"));
				intent.putExtra("speed", (int) shipDetail4.get("speed"));
				intent.putExtra("clickedTextView", 42);
				intent.setClass(RaidFormationActivity.this, EquipSelectActivity.class);
				startActivityForResult(intent, 42);
			}
		});
		// 四号舰装备槽3点击
		textEquip43.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("shipClass", (String) shipDetail4.get("class"));
				intent.putExtra("speed", (int) shipDetail4.get("speed"));
				intent.putExtra("clickedTextView", 43);
				intent.setClass(RaidFormationActivity.this, EquipSelectActivity.class);
				startActivityForResult(intent, 43);
			}
		});
		// 四号舰装备槽4点击
		textEquip44.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("shipClass", (String) shipDetail4.get("class"));
				intent.putExtra("speed", (int) shipDetail4.get("speed"));
				intent.putExtra("clickedTextView", 44);
				intent.setClass(RaidFormationActivity.this, EquipSelectActivity.class);
				startActivityForResult(intent, 44);
			}
		});
	}

	// TODO 设置五号舰控件监听器
	private void setShip5ClickListener() {
		// 设置五号舰舰名点击事件
		textShipName5.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO 调用舰船类型列表中的静态函数，启动舰船类型列表Activity
				ListActivityShipClassSelect.getShipClass(RaidFormationActivity.this, 5);
			}
		});
		// 五号舰清空点击
		textClear5.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				ToolFunction.clearShipWiget(textClear5, textShipName5, editShipLv5, imgEquip51, textEquip51, imgEquip52, textEquip52, imgEquip53, textEquip53, imgEquip54, textEquip54, shipDetail5,
						equipDetail51, equipDetail52, equipDetail53, equipDetail54);
				textClear4.setVisibility(View.VISIBLE);
				textShipName6.setEnabled(false);
				textShipName6.setText("");
				// 删除舰船后重新计算舰队编成
				listRaidFormation();
				// 检查舰队编成是否符合要求
				isFormationMeetsRaidRequire();
				// 计算舰船数量
				countShip();
			}
		});
		// 五号舰装备槽1点击
		textEquip51.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("shipClass", (String) shipDetail5.get("class"));
				intent.putExtra("speed", (int) shipDetail5.get("speed"));
				intent.putExtra("clickedTextView", 51);
				intent.setClass(RaidFormationActivity.this, EquipSelectActivity.class);
				startActivityForResult(intent, 51);
			}
		});
		// 五号舰装备槽2点击
		textEquip52.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("shipClass", (String) shipDetail5.get("class"));
				intent.putExtra("speed", (int) shipDetail5.get("speed"));
				intent.putExtra("clickedTextView", 52);
				intent.setClass(RaidFormationActivity.this, EquipSelectActivity.class);
				startActivityForResult(intent, 52);
			}
		});
		// 五号舰装备槽3点击
		textEquip53.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("shipClass", (String) shipDetail5.get("class"));
				intent.putExtra("speed", (int) shipDetail5.get("speed"));
				intent.putExtra("clickedTextView", 53);
				intent.setClass(RaidFormationActivity.this, EquipSelectActivity.class);
				startActivityForResult(intent, 53);
			}
		});
		// 五号舰装备槽4点击
		textEquip54.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("shipClass", (String) shipDetail5.get("class"));
				intent.putExtra("speed", (int) shipDetail5.get("speed"));
				intent.putExtra("clickedTextView", 54);
				intent.setClass(RaidFormationActivity.this, EquipSelectActivity.class);
				startActivityForResult(intent, 54);
			}
		});
	}

	// TODO 设置六号舰控件监听器
	private void setShip6ClickListener() {
		// 设置六号舰舰名点击事件
		textShipName6.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO 调用舰船类型列表中的静态函数，启动舰船类型列表Activity
				ListActivityShipClassSelect.getShipClass(RaidFormationActivity.this, 6);
			}
		});
		// 六号舰清空点击
		textClear6.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				ToolFunction.clearShipWiget(textClear6, textShipName6, editShipLv6, imgEquip61, textEquip61, imgEquip62, textEquip62, imgEquip63, textEquip63, imgEquip64, textEquip64, shipDetail6,
						equipDetail61, equipDetail62, equipDetail63, equipDetail64);
				textClear5.setVisibility(View.VISIBLE);
				// 删除舰船后重新计算舰队编成
				listRaidFormation();
				// 检查舰队编成是否符合要求
				isFormationMeetsRaidRequire();
				// 计算舰船数量
				countShip();
			}
		});
		// 六号舰装备槽1点击
		textEquip61.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("shipClass", (String) shipDetail6.get("class"));
				intent.putExtra("speed", (int) shipDetail6.get("speed"));
				intent.putExtra("clickedTextView", 61);
				intent.setClass(RaidFormationActivity.this, EquipSelectActivity.class);
				startActivityForResult(intent, 61);
			}
		});
		// 六号舰装备槽2点击
		textEquip62.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("shipClass", (String) shipDetail6.get("class"));
				intent.putExtra("speed", (int) shipDetail6.get("speed"));
				intent.putExtra("clickedTextView", 62);
				intent.setClass(RaidFormationActivity.this, EquipSelectActivity.class);
				startActivityForResult(intent, 62);
			}
		});
		// 六号舰装备槽3点击
		textEquip63.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("shipClass", (String) shipDetail6.get("class"));
				intent.putExtra("speed", (int) shipDetail6.get("speed"));
				intent.putExtra("clickedTextView", 63);
				intent.setClass(RaidFormationActivity.this, EquipSelectActivity.class);
				startActivityForResult(intent, 63);
			}
		});
		// 六号舰装备槽4点击
		textEquip64.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("shipClass", (String) shipDetail6.get("class"));
				intent.putExtra("speed", (int) shipDetail6.get("speed"));
				intent.putExtra("clickedTextView", 64);
				intent.setClass(RaidFormationActivity.this, EquipSelectActivity.class);
				startActivityForResult(intent, 64);
			}
		});
	}

	// TODO 实时等级监听器
	private void setEditTextChangeListener() {

		editShipLv1.addTextChangedListener(new TextWatcher() {
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
			}

			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub
			}

			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				if (!TextUtils.isEmpty(editShipLv1.getText())) {
					intShipLv1 = Integer.parseInt(editShipLv1.getText().toString());
				} else {
					intShipLv1 = 0;
				}
				intFleetLv = intShipLv1 + intShipLv2 + intShipLv3 + intShipLv4 + intShipLv5 + intShipLv6;
				textRaidFormationLv.setText(String.valueOf(intFleetLv));
				// 等级判断
				isFlagLvOK();
				isFleetLvOK();
			}
		});

		editShipLv2.addTextChangedListener(new TextWatcher() {
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
			}

			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub
			}

			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				if (!TextUtils.isEmpty(editShipLv2.getText())) {
					intShipLv2 = Integer.parseInt(editShipLv2.getText().toString());
				} else {
					intShipLv2 = 0;
				}
				intFleetLv = intShipLv1 + intShipLv2 + intShipLv3 + intShipLv4 + intShipLv5 + intShipLv6;
				textRaidFormationLv.setText(String.valueOf(intFleetLv));
				// 等级判断
				isFleetLvOK();
			}
		});

		editShipLv3.addTextChangedListener(new TextWatcher() {
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
			}

			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub
			}

			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				if (!TextUtils.isEmpty(editShipLv3.getText())) {
					intShipLv3 = Integer.parseInt(editShipLv3.getText().toString());
				} else {
					intShipLv3 = 0;
				}
				intFleetLv = intShipLv1 + intShipLv2 + intShipLv3 + intShipLv4 + intShipLv5 + intShipLv6;
				textRaidFormationLv.setText(String.valueOf(intFleetLv));
				// 等级判断
				isFleetLvOK();
			}
		});

		editShipLv4.addTextChangedListener(new TextWatcher() {
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
			}

			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub
			}

			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				if (!TextUtils.isEmpty(editShipLv4.getText())) {
					intShipLv4 = Integer.parseInt(editShipLv4.getText().toString());
				} else {
					intShipLv4 = 0;
				}
				intFleetLv = intShipLv1 + intShipLv2 + intShipLv3 + intShipLv4 + intShipLv5 + intShipLv6;
				textRaidFormationLv.setText(String.valueOf(intFleetLv));
				// 等级判断
				isFleetLvOK();
			}
		});

		editShipLv5.addTextChangedListener(new TextWatcher() {
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
			}

			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub
			}

			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				if (!TextUtils.isEmpty(editShipLv5.getText())) {
					intShipLv5 = Integer.parseInt(editShipLv5.getText().toString());
				} else {
					intShipLv5 = 0;
				}
				intFleetLv = intShipLv1 + intShipLv2 + intShipLv3 + intShipLv4 + intShipLv5 + intShipLv6;
				textRaidFormationLv.setText(String.valueOf(intFleetLv));
				// 等级判断
				isFleetLvOK();
			}
		});

		editShipLv6.addTextChangedListener(new TextWatcher() {
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
			}

			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub
			}

			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				if (!TextUtils.isEmpty(editShipLv6.getText())) {
					intShipLv6 = Integer.parseInt(editShipLv6.getText().toString());
				} else {
					intShipLv6 = 0;
				}
				intFleetLv = intShipLv1 + intShipLv2 + intShipLv3 + intShipLv4 + intShipLv5 + intShipLv6;
				textRaidFormationLv.setText(String.valueOf(intFleetLv));
				// 等级判断
				isFleetLvOK();
			}
		});

	}

	// 收到舰船回调后重新计算舰队编成（移动到工具类中）
	private void listRaidFormation() {
		strRaidFormation = "";
		if (shipDetail1.get("class") != null) {
			strRaidFormation += ((String) shipDetail1.get("class") + " ");
		}
		if (shipDetail2.get("class") != null) {
			strRaidFormation += ((String) shipDetail2.get("class") + " ");
		}
		if (shipDetail3.get("class") != null) {
			strRaidFormation += ((String) shipDetail3.get("class") + " ");
		}
		if (shipDetail4.get("class") != null) {
			strRaidFormation += ((String) shipDetail4.get("class") + " ");
		}
		if (shipDetail5.get("class") != null) {
			strRaidFormation += ((String) shipDetail5.get("class") + " ");
		}
		if (shipDetail6.get("class") != null) {
			strRaidFormation += ((String) shipDetail6.get("class") + " ");
		}
		textRaidFormation.setText(strRaidFormation);
	}

	// 检测旗舰等级是否符合要求（移动到工具类中）
	private void isFlagLvOK() {
		if (!TextUtils.isEmpty(editShipLv1.getText())) {
			if (Integer.parseInt(editShipLv1.getText().toString()) >= flagLvRequire) {
				textRaidFormationFlagLv.setTextColor(Color.parseColor("#009900"));
			} else {
				textRaidFormationFlagLv.setTextColor(Color.parseColor("#CC0000"));
			}
		} else {
			textRaidFormationFlagLv.setTextColor(Color.parseColor("#CC0000"));
		}
	}

	// 检测舰队等级是否符合要求（移动到工具类中）
	private void isFleetLvOK() {
		if (fleetLvRequire != 0) {
			if (intFleetLv >= fleetLvRequire) {
				textRaidFormationFleetLvDisplay.setTextColor(Color.parseColor("#009900"));
			} else {
				textRaidFormationFleetLvDisplay.setTextColor(Color.parseColor("#CC0000"));
			}
		}
	}

	// 检测舰队编成是否符合要求（移动到工具类中）
	private void isFormationMeetsRaidRequire() {
		boolean isFormationMeetsRaidRequire;
		isFormationMeetsRaidRequire = ToolFunction.isFormationMeetsRaidRequire(raidDetail, shipDetail1, shipDetail2, shipDetail3, shipDetail4, shipDetail5, shipDetail6);
		if (isFormationMeetsRaidRequire) {
			textRaidFormationShipRequire.setTextColor(Color.parseColor("#009900"));
		} else {
			textRaidFormationShipRequire.setTextColor(Color.parseColor("#CC0000"));
		}
	}

	// TODO 读取已存远征编成给当前Activity相关变量写入数据
	private void setDataFromRaidDetail() {
		savedName = raidFormationDetail.get("savedname").toString();
		// 舰船部分
		float ship1 = (float) raidFormationDetail.get("ship1");
		float ship2 = (float) raidFormationDetail.get("ship2");
		float ship3 = (float) raidFormationDetail.get("ship3");
		float ship4 = (float) raidFormationDetail.get("ship4");
		float ship5 = (float) raidFormationDetail.get("ship5");
		float ship6 = (float) raidFormationDetail.get("ship6");
		// 舰船ID不为0时才查询舰船详细
		if (ship1 != 0f) {
			String shipClassString = raidFormationDetail.get("ship1class").toString();
			shipDetail1 = readDatabase.getShipDetail(shipClassString, readDatabase.getShipPositionInTable(shipClassString, ship1), 0);
			ship1Display();
			intShipLv1 = (int) raidFormationDetail.get("ship1lv");
			editShipLv1.setText(String.valueOf(intShipLv1));
			textShipName2.setEnabled(true);
		}
		if (ship2 != 0f) {
			String shipClassString = raidFormationDetail.get("ship2class").toString();
			shipDetail2 = readDatabase.getShipDetail(shipClassString, readDatabase.getShipPositionInTable(shipClassString, ship2), 0);
			ship2Display();
			intShipLv2 = (int) raidFormationDetail.get("ship2lv");
			editShipLv2.setText(String.valueOf(intShipLv2));
			textShipName3.setEnabled(true);
		}
		if (ship3 != 0f) {
			String shipClassString = raidFormationDetail.get("ship3class").toString();
			shipDetail3 = readDatabase.getShipDetail(shipClassString, readDatabase.getShipPositionInTable(shipClassString, ship3), 0);
			ship3Display();
			intShipLv3 = (int) raidFormationDetail.get("ship3lv");
			editShipLv3.setText(String.valueOf(intShipLv3));
			textShipName4.setEnabled(true);
		}
		if (ship4 != 0f) {
			String shipClassString = raidFormationDetail.get("ship4class").toString();
			shipDetail4 = readDatabase.getShipDetail(shipClassString, readDatabase.getShipPositionInTable(shipClassString, ship4), 0);
			ship4Display();
			intShipLv4 = (int) raidFormationDetail.get("ship4lv");
			editShipLv4.setText(String.valueOf(intShipLv4));
			textShipName5.setEnabled(true);
		}
		if (ship5 != 0f) {
			String shipClassString = raidFormationDetail.get("ship5class").toString();
			shipDetail5 = readDatabase.getShipDetail(shipClassString, readDatabase.getShipPositionInTable(shipClassString, ship5), 0);
			ship5Display();
			intShipLv5 = (int) raidFormationDetail.get("ship5lv");
			editShipLv5.setText(String.valueOf(intShipLv5));
			textShipName6.setEnabled(true);
		}
		if (ship6 != 0f) {
			String shipClassString = raidFormationDetail.get("ship6class").toString();
			shipDetail6 = readDatabase.getShipDetail(shipClassString, readDatabase.getShipPositionInTable(shipClassString, ship6), 0);
			ship6Display();
			intShipLv6 = (int) raidFormationDetail.get("ship6lv");
			editShipLv6.setText(String.valueOf(intShipLv6));
		}
		// 装备部分
		if ((int) raidFormationDetail.get("equip11") != 0) {
			equipDetail11 = readDatabase.getEquipDetail((int) raidFormationDetail.get("equip11"));
		}
		if ((int) raidFormationDetail.get("equip12") != 0) {
			equipDetail12 = readDatabase.getEquipDetail((int) raidFormationDetail.get("equip12"));
		}
		if ((int) raidFormationDetail.get("equip13") != 0) {
			equipDetail13 = readDatabase.getEquipDetail((int) raidFormationDetail.get("equip13"));
		}
		if ((int) raidFormationDetail.get("equip14") != 0) {
			equipDetail14 = readDatabase.getEquipDetail((int) raidFormationDetail.get("equip14"));
		}
		if ((int) raidFormationDetail.get("equip21") != 0) {
			equipDetail21 = readDatabase.getEquipDetail((int) raidFormationDetail.get("equip21"));
		}
		if ((int) raidFormationDetail.get("equip22") != 0) {
			equipDetail22 = readDatabase.getEquipDetail((int) raidFormationDetail.get("equip22"));
		}
		if ((int) raidFormationDetail.get("equip23") != 0) {
			equipDetail23 = readDatabase.getEquipDetail((int) raidFormationDetail.get("equip23"));
		}
		if ((int) raidFormationDetail.get("equip24") != 0) {
			equipDetail24 = readDatabase.getEquipDetail((int) raidFormationDetail.get("equip24"));
		}
		if ((int) raidFormationDetail.get("equip31") != 0) {
			equipDetail31 = readDatabase.getEquipDetail((int) raidFormationDetail.get("equip31"));
		}
		if ((int) raidFormationDetail.get("equip32") != 0) {
			equipDetail32 = readDatabase.getEquipDetail((int) raidFormationDetail.get("equip32"));
		}
		if ((int) raidFormationDetail.get("equip33") != 0) {
			equipDetail33 = readDatabase.getEquipDetail((int) raidFormationDetail.get("equip33"));
		}
		if ((int) raidFormationDetail.get("equip34") != 0) {
			equipDetail34 = readDatabase.getEquipDetail((int) raidFormationDetail.get("equip34"));
		}
		if ((int) raidFormationDetail.get("equip41") != 0) {
			equipDetail41 = readDatabase.getEquipDetail((int) raidFormationDetail.get("equip41"));
		}
		if ((int) raidFormationDetail.get("equip42") != 0) {
			equipDetail42 = readDatabase.getEquipDetail((int) raidFormationDetail.get("equip42"));
		}
		if ((int) raidFormationDetail.get("equip43") != 0) {
			equipDetail43 = readDatabase.getEquipDetail((int) raidFormationDetail.get("equip43"));
		}
		if ((int) raidFormationDetail.get("equip44") != 0) {
			equipDetail44 = readDatabase.getEquipDetail((int) raidFormationDetail.get("equip44"));
		}
		if ((int) raidFormationDetail.get("equip51") != 0) {
			equipDetail51 = readDatabase.getEquipDetail((int) raidFormationDetail.get("equip51"));
		}
		if ((int) raidFormationDetail.get("equip52") != 0) {
			equipDetail52 = readDatabase.getEquipDetail((int) raidFormationDetail.get("equip52"));
		}
		if ((int) raidFormationDetail.get("equip53") != 0) {
			equipDetail53 = readDatabase.getEquipDetail((int) raidFormationDetail.get("equip53"));
		}
		if ((int) raidFormationDetail.get("equip54") != 0) {
			equipDetail54 = readDatabase.getEquipDetail((int) raidFormationDetail.get("equip54"));
		}
		if ((int) raidFormationDetail.get("equip61") != 0) {
			equipDetail61 = readDatabase.getEquipDetail((int) raidFormationDetail.get("equip61"));
		}
		if ((int) raidFormationDetail.get("equip62") != 0) {
			equipDetail62 = readDatabase.getEquipDetail((int) raidFormationDetail.get("equip62"));
		}
		if ((int) raidFormationDetail.get("equip63") != 0) {
			equipDetail63 = readDatabase.getEquipDetail((int) raidFormationDetail.get("equip63"));
		}
		if ((int) raidFormationDetail.get("equip64") != 0) {
			equipDetail64 = readDatabase.getEquipDetail((int) raidFormationDetail.get("equip64"));
		}
		// 1
		if (equipDetail11.size() != 0) {
			equip11Display();
		}
		if (equipDetail12.size() != 0) {
			equip12Display();
		}
		if (equipDetail13.size() != 0) {
			equip13Display();
		}
		if (equipDetail14.size() != 0) {
			equip14Display();
		}
		// 2
		if (equipDetail21.size() != 0) {
			equip21Display();
		}
		if (equipDetail22.size() != 0) {
			equip22Display();
		}
		if (equipDetail23.size() != 0) {
			equip23Display();
		}
		if (equipDetail24.size() != 0) {
			equip24Display();
		}
		// 3
		if (equipDetail31.size() != 0) {
			equip31Display();
		}
		if (equipDetail32.size() != 0) {
			equip32Display();
		}
		if (equipDetail33.size() != 0) {
			equip33Display();
		}
		if (equipDetail34.size() != 0) {
			equip34Display();
		}
		// 4
		if (equipDetail41.size() != 0) {
			equip41Display();
		}
		if (equipDetail42.size() != 0) {
			equip42Display();
		}
		if (equipDetail43.size() != 0) {
			equip43Display();
		}
		if (equipDetail44.size() != 0) {
			equip44Display();
		}
		// 5
		if (equipDetail51.size() != 0) {
			equip51Display();
		}
		if (equipDetail52.size() != 0) {
			equip52Display();
		}
		if (equipDetail53.size() != 0) {
			equip53Display();
		}
		if (equipDetail54.size() != 0) {
			equip54Display();
		}
		// 6
		if (equipDetail61.size() != 0) {
			equip61Display();
		}
		if (equipDetail62.size() != 0) {
			equip62Display();
		}
		if (equipDetail63.size() != 0) {
			equip63Display();
		}
		if (equipDetail64.size() != 0) {
			equip64Display();
		}
		// 从已存编成界面进入时重新计算舰队编成
		listRaidFormation();
		// 检测旗舰与舰队等级是否符合要求
		intFleetLv = intShipLv1 + intShipLv2 + intShipLv3 + intShipLv4 + intShipLv5 + intShipLv6;
		textRaidFormationLv.setText(String.valueOf(intFleetLv));
		fleetLvRequire = (int) raidDetail.get("fleetlv");
		isFlagLvOK();
		isFleetLvOK();
		// 清除文本框焦点防止屏幕乱跳
		editShipLv2.clearFocus();
		editShipLv3.clearFocus();
		editShipLv4.clearFocus();
		editShipLv5.clearFocus();
		editShipLv6.clearFocus();
		// 保持在最上
		editShipLv1.requestFocus();
		editShipLv1.clearFocus();
	}

	// TODO 计算舰船数量
	private void countShip() {
		intShipCount = 0;
		if (shipDetail1.size() != 0) {
			intShipCount += 1;
		}
		if (shipDetail2.size() != 0) {
			intShipCount += 1;
		}
		if (shipDetail3.size() != 0) {
			intShipCount += 1;
		}
		if (shipDetail4.size() != 0) {
			intShipCount += 1;
		}
		if (shipDetail5.size() != 0) {
			intShipCount += 1;
		}
		if (shipDetail6.size() != 0) {
			intShipCount += 1;
		}
	}

}

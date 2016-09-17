package com.icaics.kancollesimulator.utilty;

import com.icaics.kancollesimulator.activity.AttackFormationActivity;
import com.icaics.kancollesimulator.activity.RaidFormationActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class WriteDatabase {

	private SQLiteDatabase dataForTableCheck, dataForRecordCheck, data;
	private static String FORMATION = "data/data/com.icaics.kancollesimulator/databases/formation.db";

	int raidID;
	int ship1Name, ship2Name, ship3Name, ship4Name, ship5Name, ship6Name;
	float equip11ID, equip12ID, equip13ID, equip14ID;
	float equip21ID, equip22ID, equip23ID, equip24ID;
	float equip31ID, equip32ID, equip33ID, equip34ID;
	float equip41ID, equip42ID, equip43ID, equip44ID;
	float equip51ID, equip52ID, equip53ID, equip54ID;
	float equip61ID, equip62ID, equip63ID, equip64ID;

	private Cursor cursorForTableCheck;
	private Cursor cursorForRecordCheck;

	// ===========检查各个函数关闭Cursor和数据库（写入时不用关闭）===========

	// 检查表是否存在的函数
	public boolean isRaidTableExist(String tableName) {
		boolean isExist = false;
		try {
			dataForTableCheck = SQLiteDatabase.openOrCreateDatabase(FORMATION, null);
			String sql = "SELECT count(*) FROM sqlite_master WHERE type = 'table' AND name = '" + tableName + "'";
			System.out.println(sql);
			cursorForTableCheck = dataForTableCheck.rawQuery(sql, null);
			cursorForTableCheck.moveToNext();
			if (cursorForTableCheck.getInt(0) > 0) {
				isExist = true;
				System.out.println("Formation.db 中存在表 " + tableName);
			} else {
				isExist = false;
				System.out.println("Formation.db 中不存在表 " + tableName);
			}
		} catch (Exception e) {
			//
		} finally {
			cursorForTableCheck.close();
			dataForTableCheck.close();
		}
		return isExist;
	}

	// 检查表中是否存在对应记录
	private int isRecordExist(String tableName, String savedName, int type) {
		int recordID = -1;
		switch (type) {
		case 1:
			// 检查远征记录
			try {
				dataForRecordCheck = SQLiteDatabase.openOrCreateDatabase(FORMATION, null);
				String sql = "SELECT * FROM raid WHERE savedname = '" + savedName + "'";
				System.out.println(sql);
				cursorForRecordCheck = dataForRecordCheck.rawQuery(sql, null);
				cursorForRecordCheck.moveToNext();
				if (cursorForRecordCheck.getCount() > 0) {
					// 这里查出的是数量，不是具体的记录
					recordID = cursorForRecordCheck.getInt(cursorForRecordCheck.getColumnIndex("id"));
					System.out.println("远征记录存在，ID = " + recordID);
				} else {
					recordID = -1;
					System.out.println("名为 " + savedName + " 的远征记录不存在");
				}
			} catch (Exception e) {
				//
			} finally {
				cursorForRecordCheck.close();
				dataForRecordCheck.close();
			}
			return recordID;
		case 2:
			// 检查出击记录
			try {
				dataForRecordCheck = SQLiteDatabase.openOrCreateDatabase(FORMATION, null);
				String sql = "SELECT * FROM attack WHERE savedname = '" + savedName + "'";
				System.out.println(sql);
				cursorForRecordCheck = dataForRecordCheck.rawQuery(sql, null);
				cursorForRecordCheck.moveToNext();
				if (cursorForRecordCheck.getCount() > 0) {
					// 这里查出的是数量，不是具体的记录
					recordID = cursorForRecordCheck.getInt(cursorForRecordCheck.getColumnIndex("id"));
					System.out.println("出击记录存在，ID = " + recordID);
				} else {
					recordID = -1;
					System.out.println("名为 " + savedName + " 的出击记录不存在");
				}
			} catch (Exception e) {
				//
			} finally {
				cursorForRecordCheck.close();
				dataForRecordCheck.close();
			}
			return recordID;
		default:
			return -1;
		}

	}

	// TODO 保存远征配置
	public boolean saveRaidFormation(int raidID, String raidSaveName, int ship1lv, int ship2lv, int ship3lv, int ship4lv, int ship5lv, int ship6lv) {
		data = SQLiteDatabase.openOrCreateDatabase(FORMATION, null);
		// 检查是否存在远征表并创建
		if (!isRaidTableExist("raid")) {
			createTable(data, "raid", 1);
		}
		// 准备数据
		ContentValues cv = new ContentValues();
		cv.put("savedname", raidSaveName);
		cv.put("raidid", raidID);
		// 应该是没有的话就是0
		cv.put("ship1lv", ship1lv);
		cv.put("ship2lv", ship2lv);
		cv.put("ship3lv", ship3lv);
		cv.put("ship4lv", ship4lv);
		cv.put("ship5lv", ship5lv);
		cv.put("ship6lv", ship6lv);
		// 1
		if (RaidFormationActivity.shipDetail1.size() != 0) {
			cv.put("ship1", Float.valueOf(RaidFormationActivity.shipDetail1.get("id").toString()));
			cv.put("ship1class", ToolFunction.shipClassToTableName(RaidFormationActivity.shipDetail1.get("class").toString()));
		} else {
			cv.put("ship1", 0f);
			cv.put("ship1class", "null");
		}
		if (RaidFormationActivity.equipDetail11.size() != 0) {
			cv.put("equip11", Integer.valueOf(RaidFormationActivity.equipDetail11.get("id").toString()));
		} else {
			cv.put("equip11", 0);
		}
		if (RaidFormationActivity.equipDetail12.size() != 0) {
			cv.put("equip12", Integer.valueOf(RaidFormationActivity.equipDetail12.get("id").toString()));
		} else {
			cv.put("equip12", 0);
		}
		if (RaidFormationActivity.equipDetail13.size() != 0) {
			cv.put("equip13", Integer.valueOf(RaidFormationActivity.equipDetail13.get("id").toString()));
		} else {
			cv.put("equip13", 0);
		}
		if (RaidFormationActivity.equipDetail14.size() != 0) {
			cv.put("equip14", Integer.valueOf(RaidFormationActivity.equipDetail14.get("id").toString()));
		} else {
			cv.put("equip14", 0);
		}
		// 2
		if (RaidFormationActivity.shipDetail2.size() != 0) {
			cv.put("ship2", Float.valueOf(RaidFormationActivity.shipDetail2.get("id").toString()));
			cv.put("ship2class", ToolFunction.shipClassToTableName(RaidFormationActivity.shipDetail2.get("class").toString()));
		} else {
			cv.put("ship2", 0f);
			cv.put("ship2class", "null");
		}
		if (RaidFormationActivity.equipDetail21.size() != 0) {
			cv.put("equip21", Integer.valueOf(RaidFormationActivity.equipDetail21.get("id").toString()));
		} else {
			cv.put("equip21", 0);
		}
		if (RaidFormationActivity.equipDetail22.size() != 0) {
			cv.put("equip22", Integer.valueOf(RaidFormationActivity.equipDetail22.get("id").toString()));
		} else {
			cv.put("equip22", 0);
		}
		if (RaidFormationActivity.equipDetail23.size() != 0) {
			cv.put("equip23", Integer.valueOf(RaidFormationActivity.equipDetail23.get("id").toString()));
		} else {
			cv.put("equip23", 0);
		}
		if (RaidFormationActivity.equipDetail24.size() != 0) {
			cv.put("equip24", Integer.valueOf(RaidFormationActivity.equipDetail24.get("id").toString()));
		} else {
			cv.put("equip24", 0);
		}
		// 3
		if (RaidFormationActivity.shipDetail3.size() != 0) {
			cv.put("ship3", Float.valueOf(RaidFormationActivity.shipDetail3.get("id").toString()));
			cv.put("ship3class", ToolFunction.shipClassToTableName(RaidFormationActivity.shipDetail3.get("class").toString()));
		} else {
			cv.put("ship3", 0f);
			cv.put("ship3class", "null");
		}
		if (RaidFormationActivity.equipDetail31.size() != 0) {
			cv.put("equip31", Integer.valueOf(RaidFormationActivity.equipDetail31.get("id").toString()));
		} else {
			cv.put("equip31", 0);
		}
		if (RaidFormationActivity.equipDetail32.size() != 0) {
			cv.put("equip32", Integer.valueOf(RaidFormationActivity.equipDetail32.get("id").toString()));
		} else {
			cv.put("equip32", 0);
		}
		if (RaidFormationActivity.equipDetail33.size() != 0) {
			cv.put("equip33", Integer.valueOf(RaidFormationActivity.equipDetail33.get("id").toString()));
		} else {
			cv.put("equip33", 0);
		}
		if (RaidFormationActivity.equipDetail34.size() != 0) {
			cv.put("equip34", Integer.valueOf(RaidFormationActivity.equipDetail34.get("id").toString()));
		} else {
			cv.put("equip34", 0);
		}
		// 4
		if (RaidFormationActivity.shipDetail4.size() != 0) {
			cv.put("ship4", Float.valueOf(RaidFormationActivity.shipDetail4.get("id").toString()));
			cv.put("ship4class", ToolFunction.shipClassToTableName(RaidFormationActivity.shipDetail4.get("class").toString()));
		} else {
			cv.put("ship4", 0f);
			cv.put("ship4class", "null");
		}
		if (RaidFormationActivity.equipDetail41.size() != 0) {
			cv.put("equip41", Integer.valueOf(RaidFormationActivity.equipDetail41.get("id").toString()));
		} else {
			cv.put("equip41", 0);
		}
		if (RaidFormationActivity.equipDetail42.size() != 0) {
			cv.put("equip42", Integer.valueOf(RaidFormationActivity.equipDetail42.get("id").toString()));
		} else {
			cv.put("equip42", 0);
		}
		if (RaidFormationActivity.equipDetail43.size() != 0) {
			cv.put("equip43", Integer.valueOf(RaidFormationActivity.equipDetail43.get("id").toString()));
		} else {
			cv.put("equip43", 0);
		}
		if (RaidFormationActivity.equipDetail44.size() != 0) {
			cv.put("equip44", Integer.valueOf(RaidFormationActivity.equipDetail44.get("id").toString()));
		} else {
			cv.put("equip44", 0);
		}
		// 5
		if (RaidFormationActivity.shipDetail5.size() != 0) {
			cv.put("ship5", Float.valueOf(RaidFormationActivity.shipDetail5.get("id").toString()));
			cv.put("ship5class", ToolFunction.shipClassToTableName(RaidFormationActivity.shipDetail5.get("class").toString()));
		} else {
			cv.put("ship5", 0f);
			cv.put("ship5class", "null");
		}
		if (RaidFormationActivity.equipDetail51.size() != 0) {
			cv.put("equip51", Integer.valueOf(RaidFormationActivity.equipDetail51.get("id").toString()));
		} else {
			cv.put("equip51", 0);
		}
		if (RaidFormationActivity.equipDetail52.size() != 0) {
			cv.put("equip52", Integer.valueOf(RaidFormationActivity.equipDetail52.get("id").toString()));
		} else {
			cv.put("equip52", 0);
		}
		if (RaidFormationActivity.equipDetail53.size() != 0) {
			cv.put("equip53", Integer.valueOf(RaidFormationActivity.equipDetail53.get("id").toString()));
		} else {
			cv.put("equip53", 0);
		}
		if (RaidFormationActivity.equipDetail54.size() != 0) {
			cv.put("equip54", Integer.valueOf(RaidFormationActivity.equipDetail54.get("id").toString()));
		} else {
			cv.put("equip54", 0);
		}
		// 6
		if (RaidFormationActivity.shipDetail6.size() != 0) {
			cv.put("ship6", Float.valueOf(RaidFormationActivity.shipDetail6.get("id").toString()));
			cv.put("ship6class", ToolFunction.shipClassToTableName(RaidFormationActivity.shipDetail6.get("class").toString()));
		} else {
			cv.put("ship6", 0f);
			cv.put("ship6class", "null");
		}
		if (RaidFormationActivity.equipDetail61.size() != 0) {
			cv.put("equip61", Integer.valueOf(RaidFormationActivity.equipDetail61.get("id").toString()));
		} else {
			cv.put("equip61", 0);
		}
		if (RaidFormationActivity.equipDetail62.size() != 0) {
			cv.put("equip62", Integer.valueOf(RaidFormationActivity.equipDetail62.get("id").toString()));
		} else {
			cv.put("equip62", 0);
		}
		if (RaidFormationActivity.equipDetail63.size() != 0) {
			cv.put("equip63", Integer.valueOf(RaidFormationActivity.equipDetail63.get("id").toString()));
		} else {
			cv.put("equip63", 0);
		}
		if (RaidFormationActivity.equipDetail64.size() != 0) {
			cv.put("equip64", Integer.valueOf(RaidFormationActivity.equipDetail64.get("id").toString()));
		} else {
			cv.put("equip64", 0);
		}
		System.out.println(cv);

		// 检查是否存在同名记录
		int recordID = isRecordExist("raid", raidSaveName, 1);
		if (recordID == -1) {
			// 插入数据
			data.insert("raid", null, cv);
			data.close();
			return true;
		} else {
			// 更新数据
			data.update("raid", cv, "id = " + recordID, null);
			data.close();
			return true;
		}

	}

	// TODO 保存出击配置
	public boolean saveAttackFormation(int attackID, String attackSaveName, int ship1lv, int ship2lv, int ship3lv, int ship4lv, int ship5lv, int ship6lv) {
		data = SQLiteDatabase.openOrCreateDatabase(FORMATION, null);
		// 检查是否存在远征表并创建
		if (!isRaidTableExist("attack")) {
			createTable(data, "attack", 2);
		}
		// 准备数据
		ContentValues cv = new ContentValues();
		cv.put("savedname", attackSaveName);
		cv.put("attackid", attackID);
		// 应该是没有的话就是0
		cv.put("ship1lv", ship1lv);
		cv.put("ship2lv", ship2lv);
		cv.put("ship3lv", ship3lv);
		cv.put("ship4lv", ship4lv);
		cv.put("ship5lv", ship5lv);
		cv.put("ship6lv", ship6lv);
		// 1
		if (AttackFormationActivity.shipDetail1.size() != 0) {
			cv.put("ship1", Float.valueOf(AttackFormationActivity.shipDetail1.get("id").toString()));
			cv.put("ship1class", ToolFunction.shipClassToTableName(AttackFormationActivity.shipDetail1.get("class").toString()));
		} else {
			cv.put("ship1", 0f);
			cv.put("ship1class", "null");
		}
		if (AttackFormationActivity.equipDetail11.size() != 0) {
			cv.put("equip11", Integer.valueOf(AttackFormationActivity.equipDetail11.get("id").toString()));
		} else {
			cv.put("equip11", 0);
		}
		if (AttackFormationActivity.equipDetail12.size() != 0) {
			cv.put("equip12", Integer.valueOf(AttackFormationActivity.equipDetail12.get("id").toString()));
		} else {
			cv.put("equip12", 0);
		}
		if (AttackFormationActivity.equipDetail13.size() != 0) {
			cv.put("equip13", Integer.valueOf(AttackFormationActivity.equipDetail13.get("id").toString()));
		} else {
			cv.put("equip13", 0);
		}
		if (AttackFormationActivity.equipDetail14.size() != 0) {
			cv.put("equip14", Integer.valueOf(AttackFormationActivity.equipDetail14.get("id").toString()));
		} else {
			cv.put("equip14", 0);
		}
		// 2
		if (AttackFormationActivity.shipDetail2.size() != 0) {
			cv.put("ship2", Float.valueOf(AttackFormationActivity.shipDetail2.get("id").toString()));
			cv.put("ship2class", ToolFunction.shipClassToTableName(AttackFormationActivity.shipDetail2.get("class").toString()));
		} else {
			cv.put("ship2", 0f);
			cv.put("ship2class", "null");
		}
		if (AttackFormationActivity.equipDetail21.size() != 0) {
			cv.put("equip21", Integer.valueOf(AttackFormationActivity.equipDetail21.get("id").toString()));
		} else {
			cv.put("equip21", 0);
		}
		if (AttackFormationActivity.equipDetail22.size() != 0) {
			cv.put("equip22", Integer.valueOf(AttackFormationActivity.equipDetail22.get("id").toString()));
		} else {
			cv.put("equip22", 0);
		}
		if (AttackFormationActivity.equipDetail23.size() != 0) {
			cv.put("equip23", Integer.valueOf(AttackFormationActivity.equipDetail23.get("id").toString()));
		} else {
			cv.put("equip23", 0);
		}
		if (AttackFormationActivity.equipDetail24.size() != 0) {
			cv.put("equip24", Integer.valueOf(AttackFormationActivity.equipDetail24.get("id").toString()));
		} else {
			cv.put("equip24", 0);
		}
		// 3
		if (AttackFormationActivity.shipDetail3.size() != 0) {
			cv.put("ship3", Float.valueOf(AttackFormationActivity.shipDetail3.get("id").toString()));
			cv.put("ship3class", ToolFunction.shipClassToTableName(AttackFormationActivity.shipDetail3.get("class").toString()));
		} else {
			cv.put("ship3", 0f);
			cv.put("ship3class", "null");
		}
		if (AttackFormationActivity.equipDetail31.size() != 0) {
			cv.put("equip31", Integer.valueOf(AttackFormationActivity.equipDetail31.get("id").toString()));
		} else {
			cv.put("equip31", 0);
		}
		if (AttackFormationActivity.equipDetail32.size() != 0) {
			cv.put("equip32", Integer.valueOf(AttackFormationActivity.equipDetail32.get("id").toString()));
		} else {
			cv.put("equip32", 0);
		}
		if (AttackFormationActivity.equipDetail33.size() != 0) {
			cv.put("equip33", Integer.valueOf(AttackFormationActivity.equipDetail33.get("id").toString()));
		} else {
			cv.put("equip33", 0);
		}
		if (AttackFormationActivity.equipDetail34.size() != 0) {
			cv.put("equip34", Integer.valueOf(AttackFormationActivity.equipDetail34.get("id").toString()));
		} else {
			cv.put("equip34", 0);
		}
		// 4
		if (AttackFormationActivity.shipDetail4.size() != 0) {
			cv.put("ship4", Float.valueOf(AttackFormationActivity.shipDetail4.get("id").toString()));
			cv.put("ship4class", ToolFunction.shipClassToTableName(AttackFormationActivity.shipDetail4.get("class").toString()));
		} else {
			cv.put("ship4", 0f);
			cv.put("ship4class", "null");
		}
		if (AttackFormationActivity.equipDetail41.size() != 0) {
			cv.put("equip41", Integer.valueOf(AttackFormationActivity.equipDetail41.get("id").toString()));
		} else {
			cv.put("equip41", 0);
		}
		if (AttackFormationActivity.equipDetail42.size() != 0) {
			cv.put("equip42", Integer.valueOf(AttackFormationActivity.equipDetail42.get("id").toString()));
		} else {
			cv.put("equip42", 0);
		}
		if (AttackFormationActivity.equipDetail43.size() != 0) {
			cv.put("equip43", Integer.valueOf(AttackFormationActivity.equipDetail43.get("id").toString()));
		} else {
			cv.put("equip43", 0);
		}
		if (AttackFormationActivity.equipDetail44.size() != 0) {
			cv.put("equip44", Integer.valueOf(AttackFormationActivity.equipDetail44.get("id").toString()));
		} else {
			cv.put("equip44", 0);
		}
		// 5
		if (AttackFormationActivity.shipDetail5.size() != 0) {
			cv.put("ship5", Float.valueOf(AttackFormationActivity.shipDetail5.get("id").toString()));
			cv.put("ship5class", ToolFunction.shipClassToTableName(AttackFormationActivity.shipDetail5.get("class").toString()));
		} else {
			cv.put("ship5", 0f);
			cv.put("ship5class", "null");
		}
		if (AttackFormationActivity.equipDetail51.size() != 0) {
			cv.put("equip51", Integer.valueOf(AttackFormationActivity.equipDetail51.get("id").toString()));
		} else {
			cv.put("equip51", 0);
		}
		if (AttackFormationActivity.equipDetail52.size() != 0) {
			cv.put("equip52", Integer.valueOf(AttackFormationActivity.equipDetail52.get("id").toString()));
		} else {
			cv.put("equip52", 0);
		}
		if (AttackFormationActivity.equipDetail53.size() != 0) {
			cv.put("equip53", Integer.valueOf(AttackFormationActivity.equipDetail53.get("id").toString()));
		} else {
			cv.put("equip53", 0);
		}
		if (AttackFormationActivity.equipDetail54.size() != 0) {
			cv.put("equip54", Integer.valueOf(AttackFormationActivity.equipDetail54.get("id").toString()));
		} else {
			cv.put("equip54", 0);
		}
		// 6
		if (AttackFormationActivity.shipDetail6.size() != 0) {
			cv.put("ship6", Float.valueOf(AttackFormationActivity.shipDetail6.get("id").toString()));
			cv.put("ship6class", ToolFunction.shipClassToTableName(AttackFormationActivity.shipDetail6.get("class").toString()));
		} else {
			cv.put("ship6", 0f);
			cv.put("ship6class", "null");
		}
		if (AttackFormationActivity.equipDetail61.size() != 0) {
			cv.put("equip61", Integer.valueOf(AttackFormationActivity.equipDetail61.get("id").toString()));
		} else {
			cv.put("equip61", 0);
		}
		if (AttackFormationActivity.equipDetail62.size() != 0) {
			cv.put("equip62", Integer.valueOf(AttackFormationActivity.equipDetail62.get("id").toString()));
		} else {
			cv.put("equip62", 0);
		}
		if (AttackFormationActivity.equipDetail63.size() != 0) {
			cv.put("equip63", Integer.valueOf(AttackFormationActivity.equipDetail63.get("id").toString()));
		} else {
			cv.put("equip63", 0);
		}
		if (AttackFormationActivity.equipDetail64.size() != 0) {
			cv.put("equip64", Integer.valueOf(AttackFormationActivity.equipDetail64.get("id").toString()));
		} else {
			cv.put("equip64", 0);
		}
		System.out.println(cv);

		// 检查是否存在同名记录
		int recordID = isRecordExist("attack", attackSaveName, 2);
		if (recordID == -1) {
			// 插入数据
			data.insert("attack", null, cv);
			data.close();
			return true;
		} else {
			// 更新数据
			data.update("attack", cv, "id = " + recordID, null);
			data.close();
			return true;
		}

	}

	// TODO 创建数据库
	public void createTable(SQLiteDatabase data, String tableName, int type) {
		// 远征ID或出击地图ID
		String idType;
		if (type == 1) {
			// 远征表
			idType = "raidid";
		} else {
			// 出击表
			idType = "attackid";
		}
		String sql = "CREATE TABLE " + tableName + " (id INTEGER PRIMARY KEY, savedname, " + idType + ", " + "ship1, ship1class, ship1lv, equip11, equip12, equip13, equip14, "
				+ "ship2, ship2class, ship2lv, equip21, equip22, equip23, equip24, " + "ship3, ship3class, ship3lv, equip31, equip32, equip33, equip34, "
				+ "ship4, ship4class, ship4lv, equip41, equip42, equip43, equip44, " + "ship5, ship5class, ship5lv, equip51, equip52, equip53, equip54, "
				+ "ship6, ship6class, ship6lv, equip61, equip62, equip63, equip64)";
		System.out.println(sql);
		data.execSQL(sql);
	}

	// TODO 删除已存远征记录
	public boolean deleteFormation(int id, int type) {
		// 应该不用检查是否存在表格（已经在列表中了，肯定就有了）
		// 是否需要检查ID是否存在
		data = SQLiteDatabase.openOrCreateDatabase(FORMATION, null);
		switch (type) {
		case 1:
			// 远征记录
			data.execSQL("DELETE FROM raid WHERE id = " + id);
			data.close();
			return true;
		case 2:
			// 出击记录
			data.execSQL("DELETE FROM attack WHERE id = " + id);
			data.close();
			return true;
		default:
			data.close();
			return false;
		}
	}

}

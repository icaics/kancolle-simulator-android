package com.icaics.kancollesimulator.utilty;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ReadDatabase {
	// 舰船类型对应表格没有录入数据库（使用手动函数查询），今后需添加进数据库
	private SQLiteDatabase data;
	private WriteDatabase writeDatabase;
	private static String DATABASE = "data/data/com.icaics.kancollesimulator/databases/data.s3db";
	private static String FORMATION = "data/data/com.icaics.kancollesimulator/databases/formation.db";
	private Cursor cursorCell, cursorCellCodeEx, cursorDatabaseVersion, cursorExpSum;
	private Cursor cursorCloumn, cursorCloumnDesc;
	private Cursor cursorRaidMap;
	private Cursor cursorEquip;
	private Cursor cursorShipName;
	//private Cursor cursorDataCount;
	private Cursor cursorShipDetail;
	private Cursor cursorEquipName;
	// cursorEquipDetail用在了两个装备详情的函数中（函数名一样，参数不同）
	private Cursor cursorEquipDetail;
	private Cursor cursorRaidDetail;
	private Cursor cursorFormationRaid;
	private Cursor cursorFormationAttack;
	private Cursor cursorRaidFormationDetail;
	private Cursor cursorAttackFormationDetail;
	private Cursor cursorAttackMap;
	private Cursor cursorAttackMapDetail;
	private Cursor cursorMapExp;
	private Cursor cursorModifyFactory;
	private String strDatabaseVersion;
	private String string;
	private ArrayList<String> strArray = new ArrayList<String>();
	private int exp, stringint;
	//private int dataCount;

	// TODO 字符串编码转换（UTF8-GBK）
	private String codeExchangeString(Cursor cursor, String stringInput) throws UnsupportedEncodingException {
		byte[] exChange;
		String tmpString;
		String strExchanged;
		exChange = cursor.getBlob(cursor.getColumnIndex(stringInput));
		tmpString = new String(exChange, "GBK");
		strExchanged = tmpString.substring(0, tmpString.length() - 1);
		return strExchanged;
	}

	// TODO 读取数据库版本
	public String getDatebaseVersion() {
		try {
			strDatabaseVersion = "";
			data = SQLiteDatabase.openOrCreateDatabase(DATABASE, null);
			String sql = "SELECT date FROM version WHERE version = 20";
			System.out.println(sql);
			cursorDatabaseVersion = data.rawQuery(sql, null);
			cursorDatabaseVersion.moveToFirst();
			strDatabaseVersion = cursorDatabaseVersion.getString(cursorDatabaseVersion.getColumnIndex("date"));
			System.out.println(strDatabaseVersion);
		} catch (Exception e) {
			//
		} finally {
			cursorDatabaseVersion.close();
			data.close();
		}
		return strDatabaseVersion;
	}

	// TODO 返回数据库记录数量（用于舰船类型选择列表显示该项有多少，因筛选改装舰娘功能的加入暂时不用
	/*
	private int getDataCount(String table) {
		try {
			dataCount = 0;
			data = SQLiteDatabase.openOrCreateDatabase(DATABASE, null);
			String sql = "SELECT count(*) FROM " + table;
			System.out.println(sql);
			cursorDataCount = data.rawQuery(sql, null);
			cursorDataCount.moveToFirst();
			dataCount = Integer.parseInt(cursorDataCount.getString(cursorDataCount.getColumnIndex("count(*)")));
			System.out.println(dataCount);
		} catch (Exception e) {
			//
		} finally {
			cursorDataCount.close();
			data.close();
		}
		return dataCount;
	}
	*/

	// TODO 读取指定单元格内容（返回字符串 无转码）
	public String getDatabaseCell(String select, String from, String where, String target) {
		try {
			string = "";
			data = SQLiteDatabase.openOrCreateDatabase(DATABASE, null);
			String sql = "SELECT " + select + " FROM " + from + " WHERE " + where + " = " + target;
			System.out.println(sql);
			cursorCell = data.rawQuery(sql, null);
			cursorCell.moveToFirst();
			string = cursorCell.getString(cursorCell.getColumnIndex(select));
			System.out.println(string);
		} catch (Exception e) {
			//
		} finally {
			cursorCell.close();
			data.close();
		}
		return string;
	}

	// TODO 读取指定单元格内容（返回字符串 转码）
	public String getDatabaseCellCodeEx(String select, String from, String where, String target) {
		try {
			string = "";
			data = SQLiteDatabase.openOrCreateDatabase(DATABASE, null);
			String sql = "SELECT " + select + " FROM " + from + " WHERE " + where + " = " + target;
			System.out.println(sql);
			cursorCellCodeEx = data.rawQuery(sql, null);
			cursorCellCodeEx.moveToFirst();
			string = codeExchangeString(cursorCellCodeEx, select);
			System.out.println(string);
		} catch (Exception e) {
			//
		} finally {
			cursorCellCodeEx.close();
			data.close();
		}
		return string;
	}

	// TODO 读取指定单元格内容（返回整型）
	public int getDatabaseCellInt(String select, String from, String where, String target) {
		try {
			stringint = 0;
			data = SQLiteDatabase.openOrCreateDatabase(DATABASE, null);
			String sql = "SELECT " + select + " FROM " + from + " WHERE " + where + " = " + target;
			System.out.println(sql);
			cursorCell = data.rawQuery(sql, null);
			cursorCell.moveToFirst();
			stringint = cursorCell.getInt(cursorCell.getColumnIndex(select));
			System.out.println(stringint);
		} catch (Exception e) {
			//
		} finally {
			cursorCell.close();
			data.close();
		}
		return stringint;
	}

	// TODO 读取指定列内容（返回字符串数组 转码）
	public ArrayList<String> getDatebaseColumn(String select, String from, String where, String between, String and, String orderBy) {
		try {
			data = SQLiteDatabase.openOrCreateDatabase(DATABASE, null);
			String sql = "SELECT " + select + " FROM " + from + " WHERE " + where + " BETWEEN " + between + " AND " + and + " ORDER BY " + orderBy;
			System.out.println(sql);
			cursorCloumn = data.rawQuery(sql, null);
			// cursorForCell.moveToFirst();
			while (cursorCloumn.moveToNext()) {
				strArray.add(new String(string = codeExchangeString(cursorCloumn, select)));
			}
			System.out.println(strArray);
		} catch (Exception e) {
			//
		} finally {
			cursorCloumn.close();
			data.close();
		}
		return strArray;
	}

	// TODO 读取指定列内容（返回字符串数组 转码 倒序）
	public ArrayList<String> getDatebaseColumnDesc(String select, String from, String where, String between, String and, String orderBy) {
		try {
			data = SQLiteDatabase.openOrCreateDatabase(DATABASE, null);
			String sql = "SELECT " + select + " FROM " + from + " WHERE " + where + " BETWEEN " + between + " AND " + and + " ORDER BY " + orderBy + " DESC";
			System.out.println(sql);
			cursorCloumnDesc = data.rawQuery(sql, null);
			// cursorForCell.moveToFirst();
			while (cursorCloumnDesc.moveToNext()) {
				strArray.add(new String(string = codeExchangeString(cursorCloumnDesc, select)));
			}
			System.out.println(strArray);
		} catch (Exception e) {
			//
		} finally {
			cursorCloumnDesc.close();
			data.close();
		}
		return strArray;
	}

	// TODO 读取3-2A经验值计算
	public int getExpSum(int startLevel, int startExp, int endLevel) {
		try {
			data = SQLiteDatabase.openOrCreateDatabase(DATABASE, null);
			exp = 0;
			startLevel += 2;
			String sql = "SELECT exp FROM exp WHERE level BETWEEN " + startLevel + " AND " + endLevel;
			System.out.println(sql);
			cursorExpSum = data.rawQuery(sql, null);
			// cursor.moveToFirst();
			while (cursorExpSum.moveToNext()) {
				exp += cursorExpSum.getInt(cursorExpSum.getColumnIndex("exp"));
			}
			System.out.println(exp);
		} catch (Exception e) {
			//
		} finally {
			cursorExpSum.close();
			data.close();
		}
		return exp;
	}

	// TODO 读取远征列表元素
	public List<Map<String, Object>> getRaidListMap(int orderType) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			data = SQLiteDatabase.openOrCreateDatabase(DATABASE, null);
			String sql = "SELECT * FROM raid";
			switch (orderType) {
			case 1:
				sql = "SELECT * FROM raid ORDER BY id";
				break;
			case 2:
				sql = "SELECT * FROM raid ORDER BY id DESC";
				break;
			case 3:
				sql = "SELECT * FROM raid ORDER BY time";
				break;
			case 4:
				sql = "SELECT * FROM raid ORDER BY time DESC";
				break;
			case 5:
				sql = "SELECT * FROM raid ORDER BY oilperm DESC";
				break;
			case 6:
				sql = "SELECT * FROM raid ORDER BY ammoperm DESC";
				break;
			case 7:
				sql = "SELECT * FROM raid ORDER BY steelperm DESC";
				break;
			case 8:
				sql = "SELECT * FROM raid ORDER BY alperm DESC";
				break;
			default:
				break;
			}
			System.out.println(sql);
			cursorRaidMap = data.rawQuery(sql, null);
			// cursor.moveToFirst();
			while (cursorRaidMap.moveToNext()) {
				int time;
				String strReward = "";
				String strFleetLv;
				String strTime;

				// 生成时间字符串
				time = cursorRaidMap.getInt(cursorRaidMap.getColumnIndex("time"));
				strTime = String.valueOf(time / 60) + ":" + String.valueOf(time % 60) + ":0";
				// System.out.println(strTime);

				// 生成收益字符串
				if (cursorRaidMap.getInt(cursorRaidMap.getColumnIndex("oil")) != 0) {
					strReward += "燃料 " + (cursorRaidMap.getString(cursorRaidMap.getColumnIndex("oil")) + "   ");
				}
				if (cursorRaidMap.getInt(cursorRaidMap.getColumnIndex("ammo")) != 0) {
					strReward += "弹药 " + (cursorRaidMap.getString(cursorRaidMap.getColumnIndex("ammo")) + "   ");
				}
				if (cursorRaidMap.getInt(cursorRaidMap.getColumnIndex("steel")) != 0) {
					strReward += "钢材 " + (cursorRaidMap.getString(cursorRaidMap.getColumnIndex("steel")) + "   ");
				}
				if (cursorRaidMap.getInt(cursorRaidMap.getColumnIndex("al")) != 0) {
					strReward += "铝土 " + cursorRaidMap.getString(cursorRaidMap.getColumnIndex("al"));
				}
				// 处理无舰队等级要求情况
				if (cursorRaidMap.getInt(cursorRaidMap.getColumnIndex("fleetlv")) == 0) {
					strFleetLv = "0";
				} else {
					strFleetLv = cursorRaidMap.getString(cursorRaidMap.getColumnIndex("fleetlv"));
				}
				// 生成HashMap
				map = new HashMap<String, Object>();
				map.put("id", cursorRaidMap.getString(cursorRaidMap.getColumnIndex("id")));
				map.put("name", codeExchangeString(cursorRaidMap, "name"));
				map.put("flagLv", "Lv" + cursorRaidMap.getString(cursorRaidMap.getColumnIndex("flaglv")));
				map.put("fleetLv", "Lv" + strFleetLv);
				map.put("time", strTime);
				map.put("shipRequire", codeExchangeString(cursorRaidMap, "require"));
				map.put("reward", strReward);
				list.add(map);
			}
			System.out.println(list);
		} catch (Exception e) {
			//
		} finally {
			cursorRaidMap.close();
			data.close();
		}
		return list;

	}

	// TODO 返回远征详情
	public Map<String, Object> getRaidDetail(int raidNum) {
		Map<String, Object> map = new HashMap<String, Object>();
		int id, flagLvRequire, fleetLvRequire, shipNum;
		int bb, bbv, cv, cvl, av, ca, cav, cl, clt, dd, ss, ssv, other;
		int equipNum;
		String nameCodeEx, requireCodeEx;
		try {
			data = SQLiteDatabase.openOrCreateDatabase(DATABASE, null);
			String sql = "SELECT * FROM raid WHERE id = " + raidNum;
			System.out.println(sql);
			cursorRaidDetail = data.rawQuery(sql, null);
			cursorRaidDetail.moveToFirst();
			id = cursorRaidDetail.getInt(cursorRaidDetail.getColumnIndex("id"));
			// 名称及要求处理
			nameCodeEx = codeExchangeString(cursorRaidDetail, "name");
			requireCodeEx = codeExchangeString(cursorRaidDetail, "require");
			flagLvRequire = cursorRaidDetail.getInt(cursorRaidDetail.getColumnIndex("flaglv"));
			fleetLvRequire = cursorRaidDetail.getInt(cursorRaidDetail.getColumnIndex("fleetlv"));
			shipNum = cursorRaidDetail.getInt(cursorRaidDetail.getColumnIndex("shipnum"));
			// 所需舰船数量信息
			bb = cursorRaidDetail.getInt(cursorRaidDetail.getColumnIndex("bb"));
			bbv = cursorRaidDetail.getInt(cursorRaidDetail.getColumnIndex("bbv"));
			cv = cursorRaidDetail.getInt(cursorRaidDetail.getColumnIndex("cv"));
			cvl = cursorRaidDetail.getInt(cursorRaidDetail.getColumnIndex("cvl"));
			av = cursorRaidDetail.getInt(cursorRaidDetail.getColumnIndex("av"));
			ca = cursorRaidDetail.getInt(cursorRaidDetail.getColumnIndex("ca"));
			cav = cursorRaidDetail.getInt(cursorRaidDetail.getColumnIndex("cav"));
			cl = cursorRaidDetail.getInt(cursorRaidDetail.getColumnIndex("cl"));
			clt = cursorRaidDetail.getInt(cursorRaidDetail.getColumnIndex("clt"));
			dd = cursorRaidDetail.getInt(cursorRaidDetail.getColumnIndex("dd"));
			ss = cursorRaidDetail.getInt(cursorRaidDetail.getColumnIndex("ss"));
			ssv = cursorRaidDetail.getInt(cursorRaidDetail.getColumnIndex("ssv"));
			other = cursorRaidDetail.getInt(cursorRaidDetail.getColumnIndex("other"));
			equipNum = cursorRaidDetail.getInt(cursorRaidDetail.getColumnIndex("equip"));
			// 装入数据
			map.put("id", id);
			map.put("name", nameCodeEx);
			map.put("flaglv", flagLvRequire);
			map.put("fleetlv", fleetLvRequire);
			map.put("require", requireCodeEx);
			map.put("shipnum", shipNum);
			map.put("bb", bb);
			map.put("bbv", bbv);
			map.put("cv", cv);
			map.put("cvl", cvl);
			map.put("av", av);
			map.put("ca", ca);
			map.put("cav", cav);
			map.put("cl", cl);
			map.put("clt", clt);
			map.put("dd", dd);
			map.put("ss", ss);
			map.put("ssv", ssv);
			map.put("other", other);
			map.put("equipnum", equipNum);

			System.out.println(map);
		} catch (Exception e) {
			//
		} finally {
			cursorRaidDetail.close();
			data.close();
		}
		return map;
	}

	// TODO 返回舰船类型
	public List<String> getShipClass() {
		List<String> list = new ArrayList<String>();
		list.add("  01.戦艦 (BB)");
		list.add("  02.航空戦艦 (BBV)");
		list.add("  03.正規空母 (CV)");
		list.add("  04.軽空母 (CVL)");
		list.add("  05.水上機母艦 (AV)");
		list.add("  06.重巡洋艦 (CA)");
		list.add("  07.航空巡洋艦 (CAV)");
		list.add("  08.軽巡洋艦 (CL)");
		list.add("  09.重雷装巡洋艦 (CLT)");
		list.add("  10.駆逐艦 (DD)");
		list.add("  11.潜水艦 (SS)");
		list.add("  12.潜水空母 (SSV)");
		list.add("  13.其他 (OTHERS)");
		return list;
	}

	// TODO 返回列表视图中使用的舰船名称合集
	public List<String> getShipName(String shipClass, int kai) {
		List<String> list = new ArrayList<String>();
		try {
			data = SQLiteDatabase.openOrCreateDatabase(DATABASE, null);
			String sql = "SELECT * FROM " + shipClass;
			if (kai == 1) {
				sql =  "SELECT * FROM " + shipClass + " WHERE kai = " + kai;
			}
			System.out.println(sql);
			cursorShipName = data.rawQuery(sql, null);
			// cursor.moveToFirst();
			while (cursorShipName.moveToNext()) {
				String strShipName = "NULL";
				String nameCodeEx, speed;
				float num;
				int slots, slot1, slot2, slot3, slot4;

				// 舰名处理
				nameCodeEx = codeExchangeString(cursorShipName, "name");
				// 其他
				num = cursorShipName.getFloat(cursorShipName.getColumnIndex("id"));
				slots = cursorShipName.getInt(cursorShipName.getColumnIndex("slot"));
				slot1 = cursorShipName.getInt(cursorShipName.getColumnIndex("slot1"));
				slot2 = cursorShipName.getInt(cursorShipName.getColumnIndex("slot2"));
				slot3 = cursorShipName.getInt(cursorShipName.getColumnIndex("slot3"));
				slot4 = cursorShipName.getInt(cursorShipName.getColumnIndex("slot4"));
				// 判断速度
				if (cursorShipName.getInt(cursorShipName.getColumnIndex("speed")) == 2) {
					speed = "高速";
				} else {
					speed = "低速";
				}
				// 生成名称字符串
				strShipName = "  " + num + " " + nameCodeEx + " " + speed + "  " + slots + "槽(" + slot1 + "." + slot2 + "." + slot3 + "." + slot4 + ")";
				System.out.println(strShipName);
				// 添加数据
				list.add(strShipName);
			}
		} catch (Exception e) {
			//
		} finally {
			cursorShipName.close();
			data.close();
		}

		return list;
	}

	// TODO 远征编成界面根据回调获得舰船属性
	public Map<String, Object> getShipDetail(String shipClass, int shipPosition, int kai) {
		Map<String, Object> map = new HashMap<String, Object>();
		float id;
		int slot, slot1, slot2, slot3, slot4, speed;
		String nameCodeEx, className;
		try {
			data = SQLiteDatabase.openOrCreateDatabase(DATABASE, null);
			String sql = "SELECT * FROM " + shipClass;
			if (kai == 1) {
				sql =  "SELECT * FROM " + shipClass + " WHERE kai = " + kai;
			}
			System.out.println(sql);
			cursorShipDetail = data.rawQuery(sql, null);
			// cursor.moveToFirst();
			cursorShipDetail.move(shipPosition);
			id = cursorShipDetail.getFloat(cursorShipDetail.getColumnIndex("id"));
			className = cursorShipDetail.getString(cursorShipDetail.getColumnIndex("class"));
			slot = cursorShipDetail.getInt(cursorShipDetail.getColumnIndex("slot"));
			slot1 = cursorShipDetail.getInt(cursorShipDetail.getColumnIndex("slot1"));
			slot2 = cursorShipDetail.getInt(cursorShipDetail.getColumnIndex("slot2"));
			slot3 = cursorShipDetail.getInt(cursorShipDetail.getColumnIndex("slot3"));
			slot4 = cursorShipDetail.getInt(cursorShipDetail.getColumnIndex("slot4"));
			speed = cursorShipDetail.getInt(cursorShipDetail.getColumnIndex("speed"));
			// 舰名处理
			nameCodeEx = codeExchangeString(cursorShipDetail, "name");
			// 装入数据
			map.put("id", id);
			map.put("name", nameCodeEx);
			map.put("class", className);
			map.put("slot", slot);
			map.put("slot1", slot1);
			map.put("slot2", slot2);
			map.put("slot3", slot3);
			map.put("slot4", slot4);
			map.put("speed", speed);

			System.out.println(map);
		} catch (Exception e) {
			//
		} finally {
			cursorShipDetail.close();
			data.close();
		}
		return map;
	}

	// TODO 读取装备列表详情
	public List<Map<String, Object>> getEquipListMap(String shipClass, int shipSpeed) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			data = SQLiteDatabase.openOrCreateDatabase(DATABASE, null);
			String sql = "";
			if (shipClass.equals("BB") && shipSpeed == 1) {
				// 低速BB
				sql = "SELECT * FROM equip WHERE BB = 1 ORDER BY type";
			} else if (shipClass.equals("BB") && shipSpeed == 2) {
				// 高速BB
				sql = "SELECT * FROM equip WHERE BBHS = 1 ORDER BY type";
			} else {
				sql = "SELECT * FROM equip WHERE " + shipClass + " = 1 ORDER BY type";
			}
			System.out.println(sql);
			cursorEquip = data.rawQuery(sql, null);
			// cursor.moveToFirst();
			while (cursorEquip.moveToNext()) {
				String strEquipName = "NULL";
				String nameCodeEx;
				int num, type;

				// 装备名称处理
				nameCodeEx = codeExchangeString(cursorEquip, "name");
				num = cursorEquip.getInt(cursorEquip.getColumnIndex("id"));
				// 生成名称字符串
				strEquipName = "  " + num + "  -  " + nameCodeEx;
				// 装备类型（用于显示图标）
				type = cursorEquip.getInt(cursorEquip.getColumnIndex("type"));
				// 生成HashMap
				map = new HashMap<String, Object>();
				map.put("type", type);
				map.put("name", strEquipName);
				list.add(map);
			}
		} catch (Exception e) {
			//
		} finally {
			cursorEquip.close();
			data.close();
		}
		return list;

	}

	// TODO 返回列表视图中使用的装备名称合集（废弃）
	public List<String> getEquipName(String shipClass) {
		List<String> list = new ArrayList<String>();
		try {
			data = SQLiteDatabase.openOrCreateDatabase(DATABASE, null);
			String sql = "SELECT * FROM equip WHERE " + shipClass + " = 1 ORDER BY type";
			System.out.println(sql);
			cursorEquipName = data.rawQuery(sql, null);
			// cursor.moveToFirst();
			while (cursorEquipName.moveToNext()) {
				String strEquipName = "NULL";
				String nameCodeEx;
				int num;

				// 装备名称及类型处理
				nameCodeEx = codeExchangeString(cursorEquipName, "name");
				// 其他
				num = cursorEquipName.getInt(cursorEquipName.getColumnIndex("id"));
				// 生成名称字符串
				strEquipName = "  " + num + "  -  " + nameCodeEx;
				System.out.println(strEquipName);
				// 添加数据
				list.add(strEquipName);
			}
		} catch (Exception e) {
			//
		} finally {
			cursorEquipName.close();
			data.close();
		}
		return list;
	}

	// TODO 远征编成界面根据回调获得装备属性
	public Map<String, Object> getEquipDetail(String shipClass, int equipPosition, int shipSpeed) {
		Map<String, Object> map = new HashMap<String, Object>();
		int id;
		String nameCodeEx;
		int type, antiair, search;
		try {
			data = SQLiteDatabase.openOrCreateDatabase(DATABASE, null);
			String sql = "";
			if (shipClass.equals("BB") && shipSpeed == 1) {
				// 低速BB
				sql = "SELECT * FROM equip WHERE BB = 1 ORDER BY type";
			} else if (shipClass.equals("BB") && shipSpeed == 2) {
				// 高速BB
				sql = "SELECT * FROM equip WHERE BBHS = 1 ORDER BY type";
			} else {
				sql = "SELECT * FROM equip WHERE " + shipClass + " = 1 ORDER BY type";
			}
			System.out.println(sql);
			cursorEquipDetail = data.rawQuery(sql, null);
			// cursor.moveToFirst();
			cursorEquipDetail.move(equipPosition);
			id = cursorEquipDetail.getInt(cursorEquipDetail.getColumnIndex("id"));
			type = cursorEquipDetail.getInt(cursorEquipDetail.getColumnIndex("type"));
			antiair = cursorEquipDetail.getInt(cursorEquipDetail.getColumnIndex("antiair"));
			search = cursorEquipDetail.getInt(cursorEquipDetail.getColumnIndex("search"));
			// 装备名及类型处理
			nameCodeEx = codeExchangeString(cursorEquipDetail, "name");
			// 装入数据（可加入更多装备详情）
			map.put("id", id);
			map.put("name", nameCodeEx);
			map.put("type", type);
			map.put("antiair", antiair);
			map.put("search", search);

			System.out.println(map);

		} catch (Exception e) {
			//
		} finally {
			cursorEquipDetail.close();
			data.close();
		}
		return map;
	}

	// TODO 返回远征配置读取列表元素
	public List<Map<String, Object>> getFormationRaidListMap() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			data = SQLiteDatabase.openOrCreateDatabase(FORMATION, null);
			// 判断是否存在远征配置数据库
			writeDatabase = new WriteDatabase();
			// 检查是否存在远征表并创建
			if (!writeDatabase.isRaidTableExist("raid")) {
				writeDatabase.createTable(data, "raid", 1);
			}

			String sql = "SELECT * FROM raid";
			System.out.println(sql);
			cursorFormationRaid = data.rawQuery(sql, null);
			// cursor.moveToFirst();
			while (cursorFormationRaid.moveToNext()) {
				// 变量说明：id-对应的远征ID-字段名raidid；savedID-在表中的递增ID-字段名id
				int id, intRaidTime, savedID;
				String raidSavedName, raidIDName, strRaidTime;
				float ship1ID, ship2ID, ship3ID, ship4ID, ship5ID, ship6ID;
				String ship1Class, ship2Class, ship3Class, ship4Class, ship5Class, ship6Class;
				String ship1Name = null, ship2Name = null, ship3Name = null, ship4Name = null, ship5Name = null, ship6Name = null;

				// 获得保存的对应远征ID
				id = cursorFormationRaid.getInt(cursorFormationRaid.getColumnIndex("raidid"));
				// 获得远征ID与名称字符串
				raidIDName = "远征" + id + " " + getRaidName(id);
				// 获得对应远征时间字符串（若要做远征计时功能，这里和对应ListItem中都应该时分秒分开）
				intRaidTime = getRaidTime(id);
				strRaidTime = String.valueOf(intRaidTime / 60) + ":" + String.valueOf(intRaidTime % 60) + ":0";
				// 处理保存名称
				raidSavedName = cursorFormationRaid.getString(cursorFormationRaid.getColumnIndex("savedname"));
				System.out.println("远征配置保存名称为：" + raidSavedName);
				// 获得记录在表中的ID
				savedID = cursorFormationRaid.getInt(cursorFormationRaid.getColumnIndex("id"));
				// 获得对应的编成舰船ID
				ship1ID = cursorFormationRaid.getFloat(cursorFormationRaid.getColumnIndex("ship1"));
				ship2ID = cursorFormationRaid.getFloat(cursorFormationRaid.getColumnIndex("ship2"));
				ship3ID = cursorFormationRaid.getFloat(cursorFormationRaid.getColumnIndex("ship3"));
				ship4ID = cursorFormationRaid.getFloat(cursorFormationRaid.getColumnIndex("ship4"));
				ship5ID = cursorFormationRaid.getFloat(cursorFormationRaid.getColumnIndex("ship5"));
				ship6ID = cursorFormationRaid.getFloat(cursorFormationRaid.getColumnIndex("ship6"));
				// 获得对应的编成舰船名称
				if (ship1ID != 0f) {
					ship1Class = cursorFormationRaid.getString(cursorFormationRaid.getColumnIndex("ship1class"));
					ship1Name = getShipName(ship1Class, ship1ID);
				}
				if (ship2ID != 0f) {
					ship2Class = cursorFormationRaid.getString(cursorFormationRaid.getColumnIndex("ship2class"));
					ship2Name = getShipName(ship2Class, ship2ID);
				}
				if (ship3ID != 0f) {
					ship3Class = cursorFormationRaid.getString(cursorFormationRaid.getColumnIndex("ship3class"));
					ship3Name = getShipName(ship3Class, ship3ID);
				}
				if (ship4ID != 0f) {
					ship4Class = cursorFormationRaid.getString(cursorFormationRaid.getColumnIndex("ship4class"));
					ship4Name = getShipName(ship4Class, ship4ID);
				}
				if (ship5ID != 0f) {
					ship5Class = cursorFormationRaid.getString(cursorFormationRaid.getColumnIndex("ship5class"));
					ship5Name = getShipName(ship5Class, ship5ID);
				}
				if (ship6ID != 0f) {
					ship6Class = cursorFormationRaid.getString(cursorFormationRaid.getColumnIndex("ship6class"));
					ship6Name = getShipName(ship6Class, ship6ID);
				}

				// 生成HashMap
				map = new HashMap<String, Object>();
				map.put("idname", raidIDName);
				map.put("time", strRaidTime);
				map.put("savedid", savedID);
				map.put("savedname", raidSavedName);
				map.put("ship1", ship1Name);
				map.put("ship2", ship2Name);
				map.put("ship3", ship3Name);
				map.put("ship4", ship4Name);
				map.put("ship5", ship5Name);
				map.put("ship6", ship6Name);
				list.add(map);
			}

			System.out.println(list);

		} catch (Exception e) {
			//
		} finally {
			cursorFormationRaid.close();
			data.close();
		}

		return list;
	}

	// TODO 返回出击配置读取列表元素
	public List<Map<String, Object>> getFormationAttackListMap() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			data = SQLiteDatabase.openOrCreateDatabase(FORMATION, null);
			// 判断是否存在远征配置数据库
			writeDatabase = new WriteDatabase();
			// 检查是否存在远征表并创建
			if (!writeDatabase.isRaidTableExist("attack")) {
				writeDatabase.createTable(data, "attack", 2);
			}

			String sql = "SELECT * FROM attack";
			System.out.println(sql);
			cursorFormationAttack = data.rawQuery(sql, null);
			// cursor.moveToFirst();
			while (cursorFormationAttack.moveToNext()) {
				// 变量说明：id-对应的出击ID-字段名attackid；savedID-在表中的递增ID-字段名id
				int id, savedID;
				String attackSavedName, attackIDName;
				float ship1ID, ship2ID, ship3ID, ship4ID, ship5ID, ship6ID;
				String ship1Class, ship2Class, ship3Class, ship4Class, ship5Class, ship6Class;
				String ship1Name = null, ship2Name = null, ship3Name = null, ship4Name = null, ship5Name = null, ship6Name = null;

				// 获得保存的对应出击ID
				id = cursorFormationAttack.getInt(cursorFormationAttack.getColumnIndex("attackid"));
				// 获得出击ID与名称字符串
				attackIDName = getAttackName(id);
				// 处理保存名称
				attackSavedName = cursorFormationAttack.getString(cursorFormationAttack.getColumnIndex("savedname"));
				System.out.println("出击配置保存名称为：" + attackSavedName);
				// 获得记录在表中的ID
				savedID = cursorFormationAttack.getInt(cursorFormationAttack.getColumnIndex("id"));
				// 获得对应的编成舰船ID
				ship1ID = cursorFormationAttack.getFloat(cursorFormationAttack.getColumnIndex("ship1"));
				ship2ID = cursorFormationAttack.getFloat(cursorFormationAttack.getColumnIndex("ship2"));
				ship3ID = cursorFormationAttack.getFloat(cursorFormationAttack.getColumnIndex("ship3"));
				ship4ID = cursorFormationAttack.getFloat(cursorFormationAttack.getColumnIndex("ship4"));
				ship5ID = cursorFormationAttack.getFloat(cursorFormationAttack.getColumnIndex("ship5"));
				ship6ID = cursorFormationAttack.getFloat(cursorFormationAttack.getColumnIndex("ship6"));
				// 获得对应的编成舰船名称
				if (ship1ID != 0f) {
					ship1Class = cursorFormationAttack.getString(cursorFormationAttack.getColumnIndex("ship1class"));
					ship1Name = getShipName(ship1Class, ship1ID);
				}
				if (ship2ID != 0f) {
					ship2Class = cursorFormationAttack.getString(cursorFormationAttack.getColumnIndex("ship2class"));
					ship2Name = getShipName(ship2Class, ship2ID);
				}
				if (ship3ID != 0f) {
					ship3Class = cursorFormationAttack.getString(cursorFormationAttack.getColumnIndex("ship3class"));
					ship3Name = getShipName(ship3Class, ship3ID);
				}
				if (ship4ID != 0f) {
					ship4Class = cursorFormationAttack.getString(cursorFormationAttack.getColumnIndex("ship4class"));
					ship4Name = getShipName(ship4Class, ship4ID);
				}
				if (ship5ID != 0f) {
					ship5Class = cursorFormationAttack.getString(cursorFormationAttack.getColumnIndex("ship5class"));
					ship5Name = getShipName(ship5Class, ship5ID);
				}
				if (ship6ID != 0f) {
					ship6Class = cursorFormationAttack.getString(cursorFormationAttack.getColumnIndex("ship6class"));
					ship6Name = getShipName(ship6Class, ship6ID);
				}

				// 生成HashMap
				map = new HashMap<String, Object>();
				map.put("idname", attackIDName);
				map.put("savedid", savedID);
				map.put("savedname", attackSavedName);
				map.put("ship1", ship1Name);
				map.put("ship2", ship2Name);
				map.put("ship3", ship3Name);
				map.put("ship4", ship4Name);
				map.put("ship5", ship5Name);
				map.put("ship6", ship6Name);
				list.add(map);
			}

			System.out.println(list);

		} catch (Exception e) {
			//
		} finally {
			cursorFormationAttack.close();
			data.close();
		}

		return list;
	}

	// TODO 单独为读取远征配置读取列表 读取对应远征的时间
	private int getRaidTime(int raidID) {
		int time = 0;
		SQLiteDatabase data = null;
		Cursor cursor = null;
		try {
			data = SQLiteDatabase.openOrCreateDatabase(DATABASE, null);
			String sql = "SELECT * FROM raid WHERE id = " + raidID;
			System.out.println(sql);
			cursor = data.rawQuery(sql, null);
			cursor.moveToFirst();
			time = cursor.getInt(cursor.getColumnIndex("time"));
			System.out.println("远征时间（min）：" + time);
		} catch (Exception e) {
			//
		} finally {
			cursor.close();
			data.close();
		}
		return time;
	}

	// TODO 单独为读取远征配置读取列表 读取对应远征名称
	private String getRaidName(int raidID) {
		String nameCodeEx = "";
		SQLiteDatabase data = null;
		Cursor cursor = null;
		try {
			data = SQLiteDatabase.openOrCreateDatabase(DATABASE, null);
			String sql = "SELECT * FROM raid WHERE id = " + raidID;
			System.out.println(sql);
			cursor = data.rawQuery(sql, null);
			cursor.moveToFirst();
			nameCodeEx = codeExchangeString(cursor, "name");
			System.out.println(nameCodeEx);
		} catch (Exception e) {
			//
		} finally {
			cursor.close();
			data.close();
		}
		return nameCodeEx;
	}

	// TODO 单独为读取出击配置读取列表 读取对应出击名称
	private String getAttackName(int attackMapID) {
		String nameCodeEx = "";
		SQLiteDatabase data = null;
		Cursor cursor = null;
		try {
			data = SQLiteDatabase.openOrCreateDatabase(DATABASE, null);
			String sql = "SELECT * FROM map WHERE id = " + attackMapID;
			System.out.println(sql);
			cursor = data.rawQuery(sql, null);
			cursor.moveToFirst();
			nameCodeEx = codeExchangeString(cursor, "name");
			System.out.println(nameCodeEx);
		} catch (Exception e) {
			//
		} finally {
			cursor.close();
			data.close();
		}
		return nameCodeEx;
	}

	// TODO 单独为读取远征配置读取列表 读取对应编成舰船名称
	private String getShipName(String shipClass, float shipID) {
		String nameCodeEx = "";
		SQLiteDatabase data = null;
		Cursor cursor = null;
		try {
			data = SQLiteDatabase.openOrCreateDatabase(DATABASE, null);
			String sql = "SELECT * FROM " + shipClass + " WHERE id = " + shipID;
			System.out.println(sql);
			cursor = data.rawQuery(sql, null);
			cursor.moveToFirst();
			nameCodeEx = codeExchangeString(cursor, "name");
			System.out.println(nameCodeEx);
		} catch (Exception e) {
			//
		} finally {
			cursor.close();
			data.close();
		}
		return nameCodeEx;
	}

	// TODO 远征编成界面通过已存远征编成名称获得远征信息
	public Map<String, Object> getRaidFormationDetail(int savedID) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			int id;
			String savedName;
			float ship1, ship2, ship3, ship4, ship5, ship6;
			String ship1class, ship2class, ship3class, ship4class, ship5class, ship6class;
			int ship1lv, ship2lv, ship3lv, ship4lv, ship5lv, ship6lv;
			int equip11, equip12, equip13, equip14;
			int equip21, equip22, equip23, equip24;
			int equip31, equip32, equip33, equip34;
			int equip41, equip42, equip43, equip44;
			int equip51, equip52, equip53, equip54;
			int equip61, equip62, equip63, equip64;

			// 检查是否存在远征表并创建（此处不用检查，因为是从已存远征列表进来的，肯定是有这个表的）
			// if (!writeDatabase.isRaidTableExist("raid")) {
			// writeDatabase.createTable(data, "raid", 1);
			// }

			data = SQLiteDatabase.openOrCreateDatabase(FORMATION, null);
			String sql = "SELECT * FROM raid WHERE id = " + savedID;
			System.out.println(sql);
			cursorRaidFormationDetail = data.rawQuery(sql, null);
			cursorRaidFormationDetail.moveToFirst();
			// 获得已存远征详细对应的远征ID
			// 说明：远征ID在表中字段为raidid（id为保存ID），但是取出时定为“id”
			id = cursorRaidFormationDetail.getInt(cursorRaidFormationDetail.getColumnIndex("raidid"));
			savedName = cursorRaidFormationDetail.getString(cursorRaidFormationDetail.getColumnIndex("savedname"));
			// 获得舰船ID
			ship1 = cursorRaidFormationDetail.getFloat(cursorRaidFormationDetail.getColumnIndex("ship1"));
			ship2 = cursorRaidFormationDetail.getFloat(cursorRaidFormationDetail.getColumnIndex("ship2"));
			ship3 = cursorRaidFormationDetail.getFloat(cursorRaidFormationDetail.getColumnIndex("ship3"));
			ship4 = cursorRaidFormationDetail.getFloat(cursorRaidFormationDetail.getColumnIndex("ship4"));
			ship5 = cursorRaidFormationDetail.getFloat(cursorRaidFormationDetail.getColumnIndex("ship5"));
			ship6 = cursorRaidFormationDetail.getFloat(cursorRaidFormationDetail.getColumnIndex("ship6"));
			// 获得舰船Class
			ship1class = cursorRaidFormationDetail.getString(cursorRaidFormationDetail.getColumnIndex("ship1class"));
			ship2class = cursorRaidFormationDetail.getString(cursorRaidFormationDetail.getColumnIndex("ship2class"));
			ship3class = cursorRaidFormationDetail.getString(cursorRaidFormationDetail.getColumnIndex("ship3class"));
			ship4class = cursorRaidFormationDetail.getString(cursorRaidFormationDetail.getColumnIndex("ship4class"));
			ship5class = cursorRaidFormationDetail.getString(cursorRaidFormationDetail.getColumnIndex("ship5class"));
			ship6class = cursorRaidFormationDetail.getString(cursorRaidFormationDetail.getColumnIndex("ship6class"));
			// 获得舰船等级
			ship1lv = cursorRaidFormationDetail.getInt(cursorRaidFormationDetail.getColumnIndex("ship1lv"));
			ship2lv = cursorRaidFormationDetail.getInt(cursorRaidFormationDetail.getColumnIndex("ship2lv"));
			ship3lv = cursorRaidFormationDetail.getInt(cursorRaidFormationDetail.getColumnIndex("ship3lv"));
			ship4lv = cursorRaidFormationDetail.getInt(cursorRaidFormationDetail.getColumnIndex("ship4lv"));
			ship5lv = cursorRaidFormationDetail.getInt(cursorRaidFormationDetail.getColumnIndex("ship5lv"));
			ship6lv = cursorRaidFormationDetail.getInt(cursorRaidFormationDetail.getColumnIndex("ship6lv"));
			// 获得装备ID
			equip11 = cursorRaidFormationDetail.getInt(cursorRaidFormationDetail.getColumnIndex("equip11"));
			equip12 = cursorRaidFormationDetail.getInt(cursorRaidFormationDetail.getColumnIndex("equip12"));
			equip13 = cursorRaidFormationDetail.getInt(cursorRaidFormationDetail.getColumnIndex("equip13"));
			equip14 = cursorRaidFormationDetail.getInt(cursorRaidFormationDetail.getColumnIndex("equip14"));
			equip21 = cursorRaidFormationDetail.getInt(cursorRaidFormationDetail.getColumnIndex("equip21"));
			equip22 = cursorRaidFormationDetail.getInt(cursorRaidFormationDetail.getColumnIndex("equip22"));
			equip23 = cursorRaidFormationDetail.getInt(cursorRaidFormationDetail.getColumnIndex("equip23"));
			equip24 = cursorRaidFormationDetail.getInt(cursorRaidFormationDetail.getColumnIndex("equip24"));
			equip31 = cursorRaidFormationDetail.getInt(cursorRaidFormationDetail.getColumnIndex("equip31"));
			equip32 = cursorRaidFormationDetail.getInt(cursorRaidFormationDetail.getColumnIndex("equip32"));
			equip33 = cursorRaidFormationDetail.getInt(cursorRaidFormationDetail.getColumnIndex("equip33"));
			equip34 = cursorRaidFormationDetail.getInt(cursorRaidFormationDetail.getColumnIndex("equip34"));
			equip41 = cursorRaidFormationDetail.getInt(cursorRaidFormationDetail.getColumnIndex("equip41"));
			equip42 = cursorRaidFormationDetail.getInt(cursorRaidFormationDetail.getColumnIndex("equip42"));
			equip43 = cursorRaidFormationDetail.getInt(cursorRaidFormationDetail.getColumnIndex("equip43"));
			equip44 = cursorRaidFormationDetail.getInt(cursorRaidFormationDetail.getColumnIndex("equip44"));
			equip51 = cursorRaidFormationDetail.getInt(cursorRaidFormationDetail.getColumnIndex("equip51"));
			equip52 = cursorRaidFormationDetail.getInt(cursorRaidFormationDetail.getColumnIndex("equip52"));
			equip53 = cursorRaidFormationDetail.getInt(cursorRaidFormationDetail.getColumnIndex("equip53"));
			equip54 = cursorRaidFormationDetail.getInt(cursorRaidFormationDetail.getColumnIndex("equip54"));
			equip61 = cursorRaidFormationDetail.getInt(cursorRaidFormationDetail.getColumnIndex("equip61"));
			equip62 = cursorRaidFormationDetail.getInt(cursorRaidFormationDetail.getColumnIndex("equip62"));
			equip63 = cursorRaidFormationDetail.getInt(cursorRaidFormationDetail.getColumnIndex("equip63"));
			equip64 = cursorRaidFormationDetail.getInt(cursorRaidFormationDetail.getColumnIndex("equip64"));

			// 装入数据
			map.put("savedid", savedID);
			map.put("savedname", savedName);
			map.put("id", id);
			map.put("ship1", ship1);
			map.put("ship2", ship2);
			map.put("ship3", ship3);
			map.put("ship4", ship4);
			map.put("ship5", ship5);
			map.put("ship6", ship6);
			map.put("ship1class", ship1class);
			map.put("ship2class", ship2class);
			map.put("ship3class", ship3class);
			map.put("ship4class", ship4class);
			map.put("ship5class", ship5class);
			map.put("ship6class", ship6class);
			map.put("ship1lv", ship1lv);
			map.put("ship2lv", ship2lv);
			map.put("ship3lv", ship3lv);
			map.put("ship4lv", ship4lv);
			map.put("ship5lv", ship5lv);
			map.put("ship6lv", ship6lv);
			map.put("equip11", equip11);
			map.put("equip12", equip12);
			map.put("equip13", equip13);
			map.put("equip14", equip14);
			map.put("equip21", equip21);
			map.put("equip22", equip22);
			map.put("equip23", equip23);
			map.put("equip24", equip24);
			map.put("equip31", equip31);
			map.put("equip32", equip32);
			map.put("equip33", equip33);
			map.put("equip34", equip34);
			map.put("equip41", equip41);
			map.put("equip42", equip42);
			map.put("equip43", equip43);
			map.put("equip44", equip44);
			map.put("equip51", equip51);
			map.put("equip52", equip52);
			map.put("equip53", equip53);
			map.put("equip54", equip54);
			map.put("equip61", equip61);
			map.put("equip62", equip62);
			map.put("equip63", equip63);
			map.put("equip64", equip64);

		} catch (Exception e) {
			//
		} finally {
			cursorRaidFormationDetail.close();
			data.close();
		}
		return map;
	}

	// TODO 远征编成界面通过已存远征编成名称获得出击信息
	public Map<String, Object> getAttackFormationDetail(int savedID) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			int id;
			String savedName;
			float ship1, ship2, ship3, ship4, ship5, ship6;
			String ship1class, ship2class, ship3class, ship4class, ship5class, ship6class;
			int ship1lv, ship2lv, ship3lv, ship4lv, ship5lv, ship6lv;
			int equip11, equip12, equip13, equip14;
			int equip21, equip22, equip23, equip24;
			int equip31, equip32, equip33, equip34;
			int equip41, equip42, equip43, equip44;
			int equip51, equip52, equip53, equip54;
			int equip61, equip62, equip63, equip64;

			// 检查是否存在出击表并创建（此处不用检查，因为是从已存出击列表进来的，肯定是有这个表的）
			// if (!writeDatabase.isRaidTableExist("attack")) {
			// writeDatabase.createTable(data, "attack", 2);
			// }

			data = SQLiteDatabase.openOrCreateDatabase(FORMATION, null);
			String sql = "SELECT * FROM attack WHERE id = " + savedID;
			System.out.println(sql);
			cursorAttackFormationDetail = data.rawQuery(sql, null);
			cursorAttackFormationDetail.moveToFirst();
			// 获得已存出击详细对应的远征ID
			// 说明：出击地图ID在表中字段为attackid（id为保存ID），但是取出时定为“id”
			id = cursorAttackFormationDetail.getInt(cursorAttackFormationDetail.getColumnIndex("attackid"));
			savedName = cursorAttackFormationDetail.getString(cursorAttackFormationDetail.getColumnIndex("savedname"));
			// 获得舰船ID
			ship1 = cursorAttackFormationDetail.getFloat(cursorAttackFormationDetail.getColumnIndex("ship1"));
			ship2 = cursorAttackFormationDetail.getFloat(cursorAttackFormationDetail.getColumnIndex("ship2"));
			ship3 = cursorAttackFormationDetail.getFloat(cursorAttackFormationDetail.getColumnIndex("ship3"));
			ship4 = cursorAttackFormationDetail.getFloat(cursorAttackFormationDetail.getColumnIndex("ship4"));
			ship5 = cursorAttackFormationDetail.getFloat(cursorAttackFormationDetail.getColumnIndex("ship5"));
			ship6 = cursorAttackFormationDetail.getFloat(cursorAttackFormationDetail.getColumnIndex("ship6"));
			// 获得舰船Class
			ship1class = cursorAttackFormationDetail.getString(cursorAttackFormationDetail.getColumnIndex("ship1class"));
			ship2class = cursorAttackFormationDetail.getString(cursorAttackFormationDetail.getColumnIndex("ship2class"));
			ship3class = cursorAttackFormationDetail.getString(cursorAttackFormationDetail.getColumnIndex("ship3class"));
			ship4class = cursorAttackFormationDetail.getString(cursorAttackFormationDetail.getColumnIndex("ship4class"));
			ship5class = cursorAttackFormationDetail.getString(cursorAttackFormationDetail.getColumnIndex("ship5class"));
			ship6class = cursorAttackFormationDetail.getString(cursorAttackFormationDetail.getColumnIndex("ship6class"));
			// 获得舰船等级
			ship1lv = cursorAttackFormationDetail.getInt(cursorAttackFormationDetail.getColumnIndex("ship1lv"));
			ship2lv = cursorAttackFormationDetail.getInt(cursorAttackFormationDetail.getColumnIndex("ship2lv"));
			ship3lv = cursorAttackFormationDetail.getInt(cursorAttackFormationDetail.getColumnIndex("ship3lv"));
			ship4lv = cursorAttackFormationDetail.getInt(cursorAttackFormationDetail.getColumnIndex("ship4lv"));
			ship5lv = cursorAttackFormationDetail.getInt(cursorAttackFormationDetail.getColumnIndex("ship5lv"));
			ship6lv = cursorAttackFormationDetail.getInt(cursorAttackFormationDetail.getColumnIndex("ship6lv"));
			// 获得装备ID
			equip11 = cursorAttackFormationDetail.getInt(cursorAttackFormationDetail.getColumnIndex("equip11"));
			equip12 = cursorAttackFormationDetail.getInt(cursorAttackFormationDetail.getColumnIndex("equip12"));
			equip13 = cursorAttackFormationDetail.getInt(cursorAttackFormationDetail.getColumnIndex("equip13"));
			equip14 = cursorAttackFormationDetail.getInt(cursorAttackFormationDetail.getColumnIndex("equip14"));
			equip21 = cursorAttackFormationDetail.getInt(cursorAttackFormationDetail.getColumnIndex("equip21"));
			equip22 = cursorAttackFormationDetail.getInt(cursorAttackFormationDetail.getColumnIndex("equip22"));
			equip23 = cursorAttackFormationDetail.getInt(cursorAttackFormationDetail.getColumnIndex("equip23"));
			equip24 = cursorAttackFormationDetail.getInt(cursorAttackFormationDetail.getColumnIndex("equip24"));
			equip31 = cursorAttackFormationDetail.getInt(cursorAttackFormationDetail.getColumnIndex("equip31"));
			equip32 = cursorAttackFormationDetail.getInt(cursorAttackFormationDetail.getColumnIndex("equip32"));
			equip33 = cursorAttackFormationDetail.getInt(cursorAttackFormationDetail.getColumnIndex("equip33"));
			equip34 = cursorAttackFormationDetail.getInt(cursorAttackFormationDetail.getColumnIndex("equip34"));
			equip41 = cursorAttackFormationDetail.getInt(cursorAttackFormationDetail.getColumnIndex("equip41"));
			equip42 = cursorAttackFormationDetail.getInt(cursorAttackFormationDetail.getColumnIndex("equip42"));
			equip43 = cursorAttackFormationDetail.getInt(cursorAttackFormationDetail.getColumnIndex("equip43"));
			equip44 = cursorAttackFormationDetail.getInt(cursorAttackFormationDetail.getColumnIndex("equip44"));
			equip51 = cursorAttackFormationDetail.getInt(cursorAttackFormationDetail.getColumnIndex("equip51"));
			equip52 = cursorAttackFormationDetail.getInt(cursorAttackFormationDetail.getColumnIndex("equip52"));
			equip53 = cursorAttackFormationDetail.getInt(cursorAttackFormationDetail.getColumnIndex("equip53"));
			equip54 = cursorAttackFormationDetail.getInt(cursorAttackFormationDetail.getColumnIndex("equip54"));
			equip61 = cursorAttackFormationDetail.getInt(cursorAttackFormationDetail.getColumnIndex("equip61"));
			equip62 = cursorAttackFormationDetail.getInt(cursorAttackFormationDetail.getColumnIndex("equip62"));
			equip63 = cursorAttackFormationDetail.getInt(cursorAttackFormationDetail.getColumnIndex("equip63"));
			equip64 = cursorAttackFormationDetail.getInt(cursorAttackFormationDetail.getColumnIndex("equip64"));

			// 装入数据
			map.put("savedid", savedID);
			map.put("savedname", savedName);
			map.put("id", id);
			map.put("ship1", ship1);
			map.put("ship2", ship2);
			map.put("ship3", ship3);
			map.put("ship4", ship4);
			map.put("ship5", ship5);
			map.put("ship6", ship6);
			map.put("ship1class", ship1class);
			map.put("ship2class", ship2class);
			map.put("ship3class", ship3class);
			map.put("ship4class", ship4class);
			map.put("ship5class", ship5class);
			map.put("ship6class", ship6class);
			map.put("ship1lv", ship1lv);
			map.put("ship2lv", ship2lv);
			map.put("ship3lv", ship3lv);
			map.put("ship4lv", ship4lv);
			map.put("ship5lv", ship5lv);
			map.put("ship6lv", ship6lv);
			map.put("equip11", equip11);
			map.put("equip12", equip12);
			map.put("equip13", equip13);
			map.put("equip14", equip14);
			map.put("equip21", equip21);
			map.put("equip22", equip22);
			map.put("equip23", equip23);
			map.put("equip24", equip24);
			map.put("equip31", equip31);
			map.put("equip32", equip32);
			map.put("equip33", equip33);
			map.put("equip34", equip34);
			map.put("equip41", equip41);
			map.put("equip42", equip42);
			map.put("equip43", equip43);
			map.put("equip44", equip44);
			map.put("equip51", equip51);
			map.put("equip52", equip52);
			map.put("equip53", equip53);
			map.put("equip54", equip54);
			map.put("equip61", equip61);
			map.put("equip62", equip62);
			map.put("equip63", equip63);
			map.put("equip64", equip64);

		} catch (Exception e) {
			//
		} finally {
			cursorAttackFormationDetail.close();
			data.close();
		}
		return map;
	}

	// TODO 【作废，舰船表名称已经在已存远征配置的数据库表中有字段保存】
	/**
	 * public String getTableName(float shipID) { String tableName = "";
	 * SQLiteDatabase dataTableName = null; Cursor cursorTableName = null;
	 * String shipClass; try { dataTableName =
	 * SQLiteDatabase.openOrCreateDatabase(DATABASE, null); String sql =
	 * "SELECT * FROM shipbb WHERE id = " + shipID + " UNION " +
	 * "SELECT * FROM shipbbv WHERE id = " + shipID + " UNION " +
	 * "SELECT * FROM shipcv WHERE id = " + shipID + " UNION " +
	 * "SELECT * FROM shipcvl WHERE id = " + shipID + " UNION " +
	 * "SELECT * FROM shipav WHERE id = " + shipID + " UNION " +
	 * "SELECT * FROM shipca WHERE id = " + shipID + " UNION " +
	 * "SELECT * FROM shipcav WHERE id = " + shipID + " UNION " +
	 * "SELECT * FROM shipcl WHERE id = " + shipID + " UNION " +
	 * "SELECT * FROM shipclt WHERE id = " + shipID + " UNION " +
	 * "SELECT * FROM shipdd WHERE id = " + shipID + " UNION " +
	 * "SELECT * FROM shipss WHERE id = " + shipID + " UNION " +
	 * "SELECT * FROM shipssv WHERE id = " + shipID + " UNION " +
	 * "SELECT * FROM shipother WHERE id = " + shipID; //在
	 * getFormationRaidListMap() 中会查询6次，为避免刷屏，注释掉，换用一个固定提示
	 * //System.out.println(sql); System.out.println("联合查询舰船类型(class)6次");
	 * cursorTableName = dataTableName.rawQuery(sql, null);
	 * cursorTableName.moveToFirst(); shipClass =
	 * cursorTableName.getString(cursorTableName.getColumnIndex("class"));
	 * System.out.println("shipClass = " + shipClass); tableName =
	 * ToolFunction.shipClassToTableName(shipClass);
	 * System.out.println("tableName = " + tableName); } catch (Exception e) {
	 * // } finally { cursorTableName.close(); dataTableName.close(); } return
	 * tableName; }
	 **/

	// TODO 通过舰船ID获得在对应舰船类型表中的位置（为了getShipDetail(shipClassTableName, shipPosition)）
	public int getShipPositionInTable(String shipClass, float shipID) {
		int position = 0;
		SQLiteDatabase dataShipPositionInTable = null;
		Cursor cursorShipPositionInTable = null;
		try {
			// 查询数据
			dataShipPositionInTable = SQLiteDatabase.openOrCreateDatabase(DATABASE, null);
			String sql = "SELECT * FROM " + shipClass;
			System.out.println(sql);
			cursorShipPositionInTable = dataShipPositionInTable.rawQuery(sql, null);
			while (cursorShipPositionInTable.moveToNext()) {
				if (cursorShipPositionInTable.getFloat(cursorShipPositionInTable.getColumnIndex("id")) == shipID) {
					position = cursorShipPositionInTable.getPosition();
					System.out.println("舰船在" + shipClass + "中的位置是" + position);
					break;
				}
			}
		} catch (Exception e) {
			//
		} finally {
			cursorShipPositionInTable.close();
			dataShipPositionInTable.close();
		}
		// +1补正cursor从0开始
		return position + 1;
	}

	// TODO 编成界面根据已存信息获取装备详情
	public Map<String, Object> getEquipDetail(int id) {
		Map<String, Object> map = new HashMap<String, Object>();
		String nameCodeEx;
		int type, antiair, search;
		try {
			data = SQLiteDatabase.openOrCreateDatabase(DATABASE, null);
			String sql = "SELECT * FROM equip WHERE id = " + id;
			System.out.println(sql);
			cursorEquipDetail = data.rawQuery(sql, null);
			cursorEquipDetail.moveToFirst();
			type = cursorEquipDetail.getInt(cursorEquipDetail.getColumnIndex("type"));
			antiair = cursorEquipDetail.getInt(cursorEquipDetail.getColumnIndex("antiair"));
			search = cursorEquipDetail.getInt(cursorEquipDetail.getColumnIndex("search"));
			// 装备名及类型处理
			nameCodeEx = codeExchangeString(cursorEquipDetail, "name");
			// 装入数据（可加入更多装备详情）
			map.put("id", id);
			map.put("name", nameCodeEx);
			map.put("type", type);
			map.put("antiair", antiair);
			map.put("search", search);

			System.out.println(map);

		} catch (Exception e) {
			//
		} finally {
			cursorEquipDetail.close();
			data.close();
		}
		return map;
	}

	// TODO 获取出击地图列表元素
	public List<Map<String, Object>> getAttackMap() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			data = SQLiteDatabase.openOrCreateDatabase(DATABASE, null);
			String sql = "SELECT * FROM map ORDER BY id";
			System.out.println(sql);
			cursorAttackMap = data.rawQuery(sql, null);
			// cursor.moveToFirst();
			while (cursorAttackMap.moveToNext()) {
				String nameCodeEx;
				int num;
				// 标记分隔栏与数据
				int type = 0;
				// 获取ID
				num = cursorAttackMap.getInt(cursorAttackMap.getColumnIndex("id"));
				// 判断是分隔栏还是地图数据
				switch (num) {
				case 1:
					type = 0;
					nameCodeEx = "通用海域";
					break;
				case 3:
					type = 0;
					nameCodeEx = "鎮守府海域";
					break;
				case 10:
					type = 0;
					nameCodeEx = "南西諸島海域";
					break;
				case 17:
					type = 0;
					nameCodeEx = "北方海域";
					break;
				case 24:
					type = 0;
					nameCodeEx = "西方海域";
					break;
				case 31:
					type = 0;
					nameCodeEx = "南方海域";
					break;
				case 38:
					type = 0;
					nameCodeEx = "中部海域";
					break;
				default:
					type = 1;
					// 地图名称处理
					nameCodeEx = codeExchangeString(cursorAttackMap, "name");
					break;
				}
				System.out.println(nameCodeEx);
				// 生成HashMap
				map = new HashMap<String, Object>();
				map.put("id", num);
				map.put("type", type);
				map.put("name", nameCodeEx);

				list.add(map);
			}
		} catch (Exception e) {
			//
		} finally {
			cursorAttackMap.close();
			data.close();
		}

		return list;
	}

	// TODO 返回出击详情
	public Map<String, Object> getAttackMapDetail(int attackMapNum) {
		Map<String, Object> map = new HashMap<String, Object>();
		int id, airControlRequire;
		String nameCodeEx;
		try {
			data = SQLiteDatabase.openOrCreateDatabase(DATABASE, null);
			String sql = "SELECT * FROM map WHERE id = " + attackMapNum;
			System.out.println(sql);
			cursorAttackMapDetail = data.rawQuery(sql, null);
			cursorAttackMapDetail.moveToFirst();
			id = cursorAttackMapDetail.getInt(cursorAttackMapDetail.getColumnIndex("id"));
			// 名称及要求处理
			nameCodeEx = codeExchangeString(cursorAttackMapDetail, "name");
			airControlRequire = cursorAttackMapDetail.getInt(cursorAttackMapDetail.getColumnIndex("aircontrol"));
			// 装入数据
			map.put("id", id);
			map.put("name", nameCodeEx);
			map.put("aircontrol", airControlRequire);

			System.out.println(map);
		} catch (Exception e) {
			//
		} finally {
			cursorAttackMapDetail.close();
			data.close();
		}
		return map;
	}

	// TODO 读取各海域经验用于计算
	public int getMapExp(int position) {
		int mapexp = 0;
		try {
			data = SQLiteDatabase.openOrCreateDatabase(DATABASE, null);
			String sql = "SELECT mapexp FROM mapexp WHERE id = " + position;
			System.out.println(sql);
			cursorMapExp = data.rawQuery(sql, null);
			cursorMapExp.moveToFirst();
			mapexp = cursorMapExp.getInt(cursorMapExp.getColumnIndex("mapexp"));
		} catch (Exception e) {
			//
		} finally {
			cursorMapExp.close();
			data.close();
		}
		return mapexp;
	}

	// TODO 读取指定日期改修内容
	public List<Map<String, Object>> getModifyFactory(String weekday) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			data = SQLiteDatabase.openOrCreateDatabase(DATABASE, null);
			String sql = "SELECT * FROM modify_factory WHERE " + weekday + " <> 'null'";
			System.out.println(sql);
			cursorModifyFactory = data.rawQuery(sql, null);
			// 内容处理
			while (cursorModifyFactory.moveToNext()) {
				int type;
				String equip, content;
				type = cursorModifyFactory.getInt(cursorModifyFactory.getColumnIndex("type"));
				equip = codeExchangeString(cursorModifyFactory, "equip");
				content = "二号舰：" + codeExchangeString(cursorModifyFactory, weekday);
				// 生成HashMap
				map = new HashMap<String, Object>();
				// 装入数据
				map.put("type", type);
				map.put("equip", equip);
				map.put("content", content);
				list.add(map);
			}
			System.out.println(list);
		} catch (Exception e) {
			//
		} finally {
			cursorModifyFactory.close();
			data.close();
		}
		return list;
	}

}

package com.icaics.kancollesimulator.adapter;

import java.util.List;
import java.util.Map;

import com.icaics.kancollesimulator.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

@SuppressLint("InflateParams")
public class AdapterRaidList extends BaseAdapter {
	private List<Map<String, Object>> listItem;
	private LayoutInflater layoutInflater;

	public final class ListItemView {
		public TextView textRaidNum, textRaidName, textRaidTime;
		public TextView textRaidFlagLv, textRaidFleetLvDisplay;
		public TextView textRaidShipRequire, textRaidReward;
		public TextView textRaidFleetLv;
	}

	public AdapterRaidList(Context context, List<Map<String, Object>> listItem) {
		layoutInflater = LayoutInflater.from(context);
		this.listItem = listItem;
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return listItem.size();
	}

	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ListItemView listItemView = null;

		if (convertView == null) {
			listItemView = new ListItemView();
			convertView = layoutInflater.inflate(R.layout.listitem_raidlist, null);

			listItemView.textRaidNum = (TextView) convertView.findViewById(R.id.textRaidNum);
			listItemView.textRaidName = (TextView) convertView.findViewById(R.id.textRaidName);
			listItemView.textRaidTime = (TextView) convertView.findViewById(R.id.textRaidTime);
			listItemView.textRaidFlagLv = (TextView) convertView.findViewById(R.id.textRaidFlagLv);
			listItemView.textRaidFleetLv = (TextView) convertView.findViewById(R.id.textRaidFleetLv);
			listItemView.textRaidFleetLvDisplay = (TextView) convertView.findViewById(R.id.textRaidFleetLvDisplay);
			listItemView.textRaidShipRequire = (TextView) convertView.findViewById(R.id.textRaidShipRequire);
			listItemView.textRaidReward = (TextView) convertView.findViewById(R.id.textRaidReward);

			convertView.setTag(listItemView);
		} else {
			listItemView = (ListItemView) convertView.getTag();
		}

		listItemView.textRaidNum.setText((String) listItem.get(position).get("id"));
		listItemView.textRaidName.setText((String) listItem.get(position).get("name"));
		listItemView.textRaidFlagLv.setText((String) listItem.get(position).get("flagLv"));
		listItemView.textRaidFleetLvDisplay.setText((String) listItem.get(position).get("fleetLv"));
		listItemView.textRaidTime.setText((String) listItem.get(position).get("time"));
		listItemView.textRaidShipRequire.setText((String) listItem.get(position).get("shipRequire"));
		listItemView.textRaidReward.setText((String) listItem.get(position).get("reward"));

		// 处理无舰队等级需求时不显示文本
		if (listItem.get(position).get("fleetLv").equals("Lv0")) {
			listItemView.textRaidFleetLv.setVisibility(View.INVISIBLE);
			listItemView.textRaidFleetLvDisplay.setVisibility(View.INVISIBLE);
		} else {
			listItemView.textRaidFleetLv.setVisibility(View.VISIBLE);
			listItemView.textRaidFleetLvDisplay.setVisibility(View.VISIBLE);
		}

		return convertView;
	}

}

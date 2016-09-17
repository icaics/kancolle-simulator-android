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
public class AdapterFormationRaid extends BaseAdapter {
	private List<Map<String, Object>> listItem;
	private LayoutInflater layoutInflater;

	public final class ListItemView {
		public TextView textRaidIDName, textRaidTime, textRaidListID, textRaidSavedName;
		public TextView textRaidShip1, textRaidShip2, textRaidShip3, textRaidShip4, textRaidShip5, textRaidShip6;
	}

	public AdapterFormationRaid(Context context, List<Map<String, Object>> listItem) {
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
			convertView = layoutInflater.inflate(R.layout.listitem_formationraid, null);

			listItemView.textRaidIDName = (TextView) convertView.findViewById(R.id.textRaidIDName);
			listItemView.textRaidTime = (TextView) convertView.findViewById(R.id.textRaidTime);
			listItemView.textRaidListID = (TextView) convertView.findViewById(R.id.textRaidListID);
			listItemView.textRaidSavedName = (TextView) convertView.findViewById(R.id.textRaidSavedName);
			listItemView.textRaidShip1 = (TextView) convertView.findViewById(R.id.textRaidShip1);
			listItemView.textRaidShip2 = (TextView) convertView.findViewById(R.id.textRaidShip2);
			listItemView.textRaidShip3 = (TextView) convertView.findViewById(R.id.textRaidShip3);
			listItemView.textRaidShip4 = (TextView) convertView.findViewById(R.id.textRaidShip4);
			listItemView.textRaidShip5 = (TextView) convertView.findViewById(R.id.textRaidShip5);
			listItemView.textRaidShip6 = (TextView) convertView.findViewById(R.id.textRaidShip6);

			convertView.setTag(listItemView);
		} else {
			listItemView = (ListItemView) convertView.getTag();
		}

		listItemView.textRaidIDName.setText((String) listItem.get(position).get("idname"));
		listItemView.textRaidTime.setText((String) listItem.get(position).get("time"));
		listItemView.textRaidListID.setText(String.valueOf(position + 1));
		listItemView.textRaidSavedName.setText((String) listItem.get(position).get("savedname"));
		listItemView.textRaidShip1.setText((String) listItem.get(position).get("ship1"));
		listItemView.textRaidShip2.setText((String) listItem.get(position).get("ship2"));
		listItemView.textRaidShip3.setText((String) listItem.get(position).get("ship3"));
		listItemView.textRaidShip4.setText((String) listItem.get(position).get("ship4"));
		listItemView.textRaidShip5.setText((String) listItem.get(position).get("ship5"));
		listItemView.textRaidShip6.setText((String) listItem.get(position).get("ship6"));

		return convertView;
	}

}

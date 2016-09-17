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
public class AdapterFormationAttack extends BaseAdapter {
	private List<Map<String, Object>> listItem;
	private LayoutInflater layoutInflater;

	public final class ListItemView {
		public TextView textAttackIDName, textAttackListID, textAttackSavedName;
		public TextView textAttackShip1, textAttackShip2, textAttackShip3, textAttackShip4, textAttackShip5, textAttackShip6;
	}

	public AdapterFormationAttack(Context context, List<Map<String, Object>> listItem) {
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
			convertView = layoutInflater.inflate(R.layout.listitem_formationattack, null);

			listItemView.textAttackIDName = (TextView) convertView.findViewById(R.id.textAttackIDName);
			listItemView.textAttackListID = (TextView) convertView.findViewById(R.id.textAttackListID);
			listItemView.textAttackSavedName = (TextView) convertView.findViewById(R.id.textAttackSavedName);
			listItemView.textAttackShip1 = (TextView) convertView.findViewById(R.id.textAttackShip1);
			listItemView.textAttackShip2 = (TextView) convertView.findViewById(R.id.textAttackShip2);
			listItemView.textAttackShip3 = (TextView) convertView.findViewById(R.id.textAttackShip3);
			listItemView.textAttackShip4 = (TextView) convertView.findViewById(R.id.textAttackShip4);
			listItemView.textAttackShip5 = (TextView) convertView.findViewById(R.id.textAttackShip5);
			listItemView.textAttackShip6 = (TextView) convertView.findViewById(R.id.textAttackShip6);

			convertView.setTag(listItemView);
		} else {
			listItemView = (ListItemView) convertView.getTag();
		}

		listItemView.textAttackIDName.setText((String) listItem.get(position).get("idname"));
		listItemView.textAttackListID.setText(String.valueOf(position + 1));
		listItemView.textAttackSavedName.setText((String) listItem.get(position).get("savedname"));
		listItemView.textAttackShip1.setText((String) listItem.get(position).get("ship1"));
		listItemView.textAttackShip2.setText((String) listItem.get(position).get("ship2"));
		listItemView.textAttackShip3.setText((String) listItem.get(position).get("ship3"));
		listItemView.textAttackShip4.setText((String) listItem.get(position).get("ship4"));
		listItemView.textAttackShip5.setText((String) listItem.get(position).get("ship5"));
		listItemView.textAttackShip6.setText((String) listItem.get(position).get("ship6"));

		return convertView;
	}

}

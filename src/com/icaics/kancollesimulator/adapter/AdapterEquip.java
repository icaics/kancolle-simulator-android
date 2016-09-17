package com.icaics.kancollesimulator.adapter;

import java.util.List;
import java.util.Map;

import com.icaics.kancollesimulator.R;
import com.icaics.kancollesimulator.utilty.ToolFunction;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("InflateParams")
public class AdapterEquip extends BaseAdapter {
	private List<Map<String, Object>> listItem;
	private LayoutInflater layoutInflater;

	public final class ListItemView {
		public ImageView imgEquip;
		public TextView textEquip;
	}

	public AdapterEquip(Context context, List<Map<String, Object>> listItem) {
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
		int type;

		if (convertView == null) {
			listItemView = new ListItemView();
			convertView = layoutInflater.inflate(R.layout.listitem_equip, null);

			listItemView.imgEquip = (ImageView) convertView.findViewById(R.id.imgEquip);
			listItemView.textEquip = (TextView) convertView.findViewById(R.id.textEquip);

			convertView.setTag(listItemView);
		} else {
			listItemView = (ListItemView) convertView.getTag();
		}

		// œ‘ æÕº∆¨
		type = (int) listItem.get(position).get("type");
		ToolFunction.setEquipImg(type, listItemView.imgEquip);
		// œ‘ æ√˚≥∆
		listItemView.textEquip.setText((String) listItem.get(position).get("name"));

		return convertView;
	}

}

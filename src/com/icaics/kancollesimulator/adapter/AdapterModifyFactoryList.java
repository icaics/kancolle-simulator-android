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
public class AdapterModifyFactoryList extends BaseAdapter {
	private List<Map<String, Object>> listItem;
	private LayoutInflater layoutInflater;

	public final class ListItemView {
		public ImageView imageModifyFactoryType;
		public TextView textModifyFactoryEquip, textModifyFactoryContent;
	}

	public AdapterModifyFactoryList(Context context, List<Map<String, Object>> listItem) {
		layoutInflater = LayoutInflater.from(context);
		this.listItem = listItem;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listItem.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
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
			convertView = layoutInflater.inflate(R.layout.listitem_modifyfactory, null);

			listItemView.imageModifyFactoryType = (ImageView) convertView.findViewById(R.id.imageModifyFactoryType);
			listItemView.textModifyFactoryEquip = (TextView) convertView.findViewById(R.id.textModifyFactoryEquip);
			listItemView.textModifyFactoryContent = (TextView) convertView.findViewById(R.id.textModifyFactoryContent);

			convertView.setTag(listItemView);
		} else {
			listItemView = (ListItemView) convertView.getTag();
		}
		
		listItemView.imageModifyFactoryType.setImageResource(ToolFunction.getEquipImg((int) listItem.get(position).get("type")));
		listItemView.textModifyFactoryEquip.setText((String) listItem.get(position).get("equip"));
		listItemView.textModifyFactoryContent.setText((String) listItem.get(position).get("content"));

		return convertView;
	}

}

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
public class AdapterAttackList extends BaseAdapter {
	private List<Map<String, Object>> listItem;
	private LayoutInflater layoutInflater;

	public final class ListItemView {
		public TextView textAttackMapName;
		public TextView textAttackListMap;
	}

	public AdapterAttackList(Context context, List<Map<String, Object>> listItem) {
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
		listItemView = new ListItemView();

		// 根据分隔栏与数据加载不同的布局文件
		if ((int) listItem.get(position).get("type") == 0) {
			convertView = layoutInflater.inflate(R.layout.listitem_attacklistdivider, null);
			listItemView.textAttackMapName = (TextView) convertView.findViewById(R.id.textAttackMapName);
			listItemView.textAttackMapName.setText((String) listItem.get(position).get("name"));
		} else {
			convertView = layoutInflater.inflate(R.layout.listitem_attacklistmap, null);
			listItemView.textAttackListMap = (TextView) convertView.findViewById(R.id.textAttackListMap);
			listItemView.textAttackListMap.setText((String) listItem.get(position).get("name"));
		}
		convertView.setTag(listItemView);

		return convertView;
	}

}

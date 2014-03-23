package com.dy.publicchat;

import java.util.List;

import com.dy.domain.Chat;
import com.dy.domain.ChatRoom;
import com.dy.domain.HomeListItem;
import com.dy.domain.Keyword;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class HomeListAdapter extends ArrayAdapter<HomeListItem> {

	private LayoutInflater mInflater;
	
	public HomeListAdapter(Context context)
	{
		super(context, android.R.layout.simple_list_item_2);
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	public void setData(List<HomeListItem> data) {
        clear();
        if (data != null) {
            for (HomeListItem item : data) {
                add(item);
            }
        }
    }
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view;
		
		if ( convertView == null )
			view = mInflater.inflate(R.layout.list_item_chat_room, null);
		else
			view = convertView;
		
		HomeListItem item = getItem(position);
		item.setViewData(view);
		
		return view;
	}

}

package com.dy.publicchat;

import java.util.List;

import com.dy.domain.Chat;
import com.dy.domain.ChatRoom;
import com.dy.domain.HomeListItem;
import com.dy.domain.HomeListItemBtns;
import com.dy.domain.HomeListItemDesc1;
import com.dy.domain.HomeListItemDesc2;
import com.dy.domain.Keyword;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class HomeListAdapter extends ArrayAdapter<HomeListItem> {

	private LayoutInflater mInflater;
	
	public static final String CHATROOM = "chatroom";
	public static final String BTNS = "btns";
	public static final String DESC1 = "desc1";
	public static final String DESC2 = "desc2";
	
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
		View view = null;
		
		HomeListItem item = getItem(position);
		if ( item instanceof HomeListItemDesc1 )
		{
			if ( convertView == null || !HomeListAdapter.DESC1.equals(convertView.getTag()))
			{
				view = mInflater.inflate(R.layout.list_item_home_desc1, null);
				view.setTag( HomeListAdapter.DESC1 );
			}
			else view = convertView;
		}
		else if ( item instanceof HomeListItemDesc2 )
		{
			if ( convertView == null || !HomeListAdapter.DESC2.equals(convertView.getTag()))
			{
				view = mInflater.inflate(R.layout.list_item_home_desc2, null);
				view.setTag( HomeListAdapter.DESC2 );
			}
			else view = convertView;
		}
		else if ( item instanceof ChatRoom )
		{
			if ( convertView == null || !HomeListAdapter.CHATROOM.equals(convertView.getTag()))
			{
				view = mInflater.inflate(R.layout.list_item_chat_room, null);
				view.setTag( HomeListAdapter.CHATROOM );
			}
			else view = convertView;
		}
		else if ( item instanceof HomeListItemBtns )
		{
			if ( convertView == null || !HomeListAdapter.BTNS.equals(convertView.getTag()))
			{
				view = mInflater.inflate(R.layout.list_item_home_btns, null);
				view.setTag( HomeListAdapter.BTNS );
			}
			else view = convertView;
		}
		
		item.setViewData(view);
		
		return view;
	}

}

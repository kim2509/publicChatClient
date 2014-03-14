package com.dy.publicchat;

import java.util.List;

import com.dy.domain.Chat;
import com.dy.domain.ChatRoom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ChatRoomListAdapter extends ArrayAdapter<ChatRoom> {

	private LayoutInflater mInflater;
	
	public ChatRoomListAdapter(Context context)
	{
		super(context, android.R.layout.simple_list_item_2);
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	public void setData(List<ChatRoom> data) {
        clear();
        if (data != null) {
            for (ChatRoom room : data) {
                add(room);
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
		
		ChatRoom chatRoom = getItem(position);
		TextView txtTitle = (TextView) view.findViewById(R.id.txtTitle);
		txtTitle.setText( chatRoom.getTitle() );
		
		return view;
	}

}

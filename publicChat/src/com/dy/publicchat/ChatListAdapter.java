package com.dy.publicchat;

import java.util.List;

import com.dy.domain.Chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ChatListAdapter extends ArrayAdapter<Chat> {

	private LayoutInflater mInflater;
	
	public ChatListAdapter(Context context)
	{
		super(context, android.R.layout.simple_list_item_2);
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	public void setData(List<Chat> data) {
        clear();
        if (data != null) {
            for (Chat user : data) {
                add(user);
            }
        }
    }
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view;
		
		if ( convertView == null )
			view = mInflater.inflate(R.layout.list_item_chat, null);
		else
			view = convertView;
		
		Chat chat = getItem(position);
		TextView txtUserNo = (TextView) view.findViewById(R.id.txtUserNo);
		txtUserNo.setText( String.valueOf( chat.getUserNo() ) );
		TextView txtDate = (TextView) view.findViewById(R.id.txtDate);
		txtDate.setText( chat.getDateTime().substring(5, 16) );
		TextView txtMsg = (TextView) view.findViewById(R.id.txtMsg);
		txtMsg.setText( chat.getLastMessage() );
		
		return view;
	}

}

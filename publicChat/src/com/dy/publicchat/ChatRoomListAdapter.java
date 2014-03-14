package com.dy.publicchat;

import java.util.List;

import com.dy.domain.Chat;
import com.dy.domain.ChatRoom;
import com.dy.domain.Keyword;

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
		
		TextView txtNumOfUsers = (TextView) view.findViewById(R.id.txtNumOfUsers);
		txtNumOfUsers.setText( chatRoom.getNumOfUsers() + "/" + chatRoom.getMaxNumOfUsers() );
		
		TextView txtLocation = (TextView) view.findViewById(R.id.txtLocation);
		txtLocation.setText( chatRoom.getLocation() );
		
		TextView txtKeyword1 = (TextView) view.findViewById(R.id.txtKeyword1);
		TextView txtKeyword2 = (TextView) view.findViewById(R.id.txtKeyword2);
		TextView txtKeyword3 = (TextView) view.findViewById(R.id.txtKeyword3);
		
		txtKeyword1.setText("");
		txtKeyword2.setText("");
		txtKeyword3.setText("");
		
		if ( chatRoom.getKeywordList() != null )
		{
			for ( int i = 0; i < chatRoom.getKeywordList().size(); i++ )
			{
				Keyword keyword = chatRoom.getKeywordList().get(i);
				switch( i )
				{
				case 0:
					txtKeyword1.setText( keyword.getName() );
					break;
				case 1:
					txtKeyword2.setText( keyword.getName() );
					break;
				case 2:
					txtKeyword3.setText( keyword.getName() );
					break;
					default:
						break;
				}
				
				if ( i == 2 ) break;
			}
		}
		
		return view;
	}

}

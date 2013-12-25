package com.dy.publicchat;

import java.util.List;

import com.dy.domain.User;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class UserListAdapter extends ArrayAdapter<User> {

	private LayoutInflater mInflater;
	
	public UserListAdapter(Context context)
	{
		super(context, android.R.layout.simple_list_item_2);
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	public void setData(List<User> data) {
        clear();
        if (data != null) {
            for (User user : data) {
                add(user);
            }
        }
    }
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view;
		
		if ( convertView == null )
			view = mInflater.inflate(R.layout.list_item_user, null);
		else
			view = convertView;
		
		User user = getItem(position);
		TextView txtUserNo = (TextView) view.findViewById(R.id.txtUserNo);
		txtUserNo.setText( String.valueOf( user.getUserNo() ) );
		
		return view;
	}

}

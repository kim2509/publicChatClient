package com.dy.common;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dy.publicchat.R;

public class UserListAdapter extends BaseAdapter {
    
    private Activity activity;
    private JSONArray data;
    private LayoutInflater inflater=null;
    
    public UserListAdapter(Activity a, JSONArray d) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return data.length();
    }

    public Object getItem(int position) {
    	try
    	{
    		return data.getJSONObject(position);	
    	}
    	catch( Exception ex )
    	{
    		return null;
    	}
    }
    
    public JSONArray getData()
    {
    	return data;
    }

    public long getItemId(int position) {
        return position;
    }
    
    public View getView(int position, View convertView, ViewGroup parent) {
    	try
    	{
    		View vi=convertView;
    		
    		JSONObject obj = (JSONObject) getItem(position);
    		vi = inflater.inflate(R.layout.list_item_user, null);
    		TextView txtUserNo = (TextView)vi.findViewById(R.id.txtUserNo);
    		txtUserNo.setText( obj.getString("userNo") );
    		TextView txtDistance = (TextView)vi.findViewById(R.id.txtDistance);
    		txtDistance.setText( obj.getString("distance") + " km" );
    		
            return vi;	
    	}
    	catch( Exception ex )
    	{
    	}
    	
    	return null;
    }
}
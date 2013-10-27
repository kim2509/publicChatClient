package com.dy.publicchat;

import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;

import com.dy.common.Constants;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class RegisterAsRandomID extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try
		{
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_register_as_random_id);
			
			JSONObject requestObj = new JSONObject();
			requestObj.put("userNo", getMetaInfoString("userNo"));
			requestObj.put("latitude", String.valueOf( location.getLatitude() ) );
			requestObj.put("longitude", String.valueOf( location.getLongitude() ) );
			execTransReturningString("/registerAsRandomID.do", requestObj, Constants.REQUESTCODE_COMMON, false );
		}
		catch( Exception ex )
		{
			catchException(ex);
		}
	}
	
	@Override
	public void doPostTransaction(int requestCode, Object result) {
		// TODO Auto-generated method stub
		try
		{
			if ( !Constants.FAIL.equals( result.toString() ))
			{
				if ( requestCode == Constants.REQUESTCODE_COMMON)
				{
					JSONObject obj = new JSONObject( result.toString() );
					setMetaInfo("userNo", obj.getString("userNo"));
					showToastMessage(result.toString());
				}
				else if ( requestCode == Constants.REQUESTCODE_SAVE_SEX )
				{
					RelativeLayout layoutInputSex = (RelativeLayout) findViewById(R.id.layoutInputSex);
					RelativeLayout layoutInputBirthYear = (RelativeLayout) findViewById(R.id.layoutInputBirthYear);
					layoutInputSex.setVisibility(ViewGroup.GONE);
					layoutInputBirthYear.setVisibility(ViewGroup.VISIBLE);
					
					Date date = new Date();
					int year = date.getYear() + 1900;
					
					ListView listView = (ListView) findViewById(R.id.list);
					ArrayList<Integer> arYear = new ArrayList<Integer>();
					for ( int i = year - 100; i < year; i+= 3 )
						arYear.add( i );

					listView.setAdapter( new BirthYearItemAdapter(this, arYear));
					
					showToastMessage(result.toString());
				}
				else if ( requestCode == Constants.REQUESTCODE_SAVE_BIRTHYEAR )
				{
					RelativeLayout layoutInputBirthYear = (RelativeLayout) findViewById(R.id.layoutInputBirthYear);
					RelativeLayout layoutInputUserProfileKeywords = (RelativeLayout) findViewById(R.id.layoutInputUserProfileKeywords);
			    	layoutInputBirthYear.setVisibility(ViewGroup.GONE);
			    	layoutInputUserProfileKeywords.setVisibility(ViewGroup.VISIBLE);
			    	
			    	JSONArray array = new JSONArray( result.toString() );
			    	
			    	ListView listUserProfileKeywords = (ListView) findViewById(R.id.listUserProfileKeywords);
			    	listUserProfileKeywords.setAdapter( new UserProfileKeywordAdapter(this, array));
				}
			}
		}
		catch( Exception ex )
		{
			catchException(ex);
		}
		
		super.doPostTransaction(requestCode, result);
	}
	
	public void selectSex( View v )
	{
		try
		{
			Button btn = (Button) v;
			
			JSONObject requestObj = new JSONObject();
			requestObj.put("userNo", getMetaInfoString("userNo"));
			if ( getResources().getString(R.string.sex_male).equals( btn.getText() ) )
				requestObj.put("sex", 1);
			else
				requestObj.put("sex", 2);
			
			execTransReturningString("/saveUserSex.do", requestObj, Constants.REQUESTCODE_SAVE_SEX, false );
		}
		catch( Exception ex )
		{
			catchException(ex);
		}
	}
	
	public void birthYear_Clicked( View v )
    {
		try
		{
			Button btn = (Button) v;
	    	
	    	JSONObject requestObj = new JSONObject();
			requestObj.put("userNo", getMetaInfoString("userNo"));
			requestObj.put("birthYear", btn.getText().toString());
			
			execTransReturningString("/saveBirthYearAndGetUserProfileKeywords.do", requestObj, Constants.REQUESTCODE_SAVE_BIRTHYEAR, false );
		}
		catch( Exception ex )
		{
			catchException(ex);
		}
    }
	
	class BirthYearItemAdapter extends BaseAdapter {
        
        private Activity activity;
        private ArrayList<Integer> data;
        private LayoutInflater inflater=null;
        
        public BirthYearItemAdapter(Activity a, ArrayList<Integer> d) {
            activity = a;
            data=d;
            inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public int getCount() {
            return data.size();
        }

        public Object getItem(int position) {
            return data.get(position);
        }
        
        public ArrayList<Integer> getData()
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
        		
        		int year = data.get(position);

        		vi = inflater.inflate(R.layout.list_item_birthyear, null);
        		
        		Button btnYear1 = (Button)vi.findViewById(R.id.btnYear1);
        		Button btnYear2 = (Button)vi.findViewById(R.id.btnYear2);
        		Button btnYear3 = (Button)vi.findViewById(R.id.btnYear3);
                
        		btnYear1.setText( String.valueOf( year ) );
        		btnYear2.setText( String.valueOf( year + 1 ) );
        		btnYear3.setText( String.valueOf( year + 2 ) );
        		
        		if ( year == new Date().getYear() + 1900 )
        		{
        			btnYear2.setVisibility( ViewGroup.INVISIBLE );
        			btnYear3.setVisibility( ViewGroup.INVISIBLE );
        		}
        		else if ( year + 1 == new Date().getYear() + 1900 )
        			btnYear3.setVisibility( ViewGroup.INVISIBLE );
                
                return vi;	
        	}
        	catch( Exception ex )
        	{
        	}
        	
        	return null;
        }
    }
	
	class UserProfileKeywordAdapter extends BaseAdapter {
        
        private Activity activity;
        private JSONArray data;
        private LayoutInflater inflater=null;
        
        public UserProfileKeywordAdapter(Activity a, JSONArray d) {
            activity = a;
            data=d;
            inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public int getCount() {
        	if ( data.length() % 3 == 0)
        		return data.length() / 3;
        	else
        		return data.length() / 3 + 1;
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
        		
        		JSONObject keyword = data.getJSONObject( 3 * position );

        		vi = inflater.inflate(R.layout.list_item_user_profile, null);
        		
        		CheckBox chkKeywordLeft = (CheckBox)vi.findViewById(R.id.chkKeywordLeft);
        		CheckBox chkKeywordMiddle = (CheckBox)vi.findViewById(R.id.chkKeywordMiddle);
        		CheckBox chkKeywordRight = (CheckBox)vi.findViewById(R.id.chkKeywordRight);
                
        		chkKeywordLeft.setText( keyword.getString("keywordName") );
        		
        		if ( data.length() > 3 * position + 1 )
        		{
        			keyword = data.getJSONObject( 3 * position + 1 );
        			chkKeywordMiddle.setText( keyword.getString("keywordName") );	
        		}
        		else
        		{
        			chkKeywordMiddle.setVisibility(ViewGroup.INVISIBLE);
        			chkKeywordRight.setVisibility(ViewGroup.INVISIBLE);
        		}
        		
        		if ( data.length() > 3 * position + 2 )
        		{
        			keyword = data.getJSONObject( 3 * position + 2 );
        			chkKeywordRight.setText( keyword.getString("keywordName") );
        		}
        		else
        		{
        			chkKeywordRight.setVisibility(ViewGroup.INVISIBLE);
        		}
                
                return vi;	
        	}
        	catch( Exception ex )
        	{
        	}
        	
        	return null;
        }
    }
}

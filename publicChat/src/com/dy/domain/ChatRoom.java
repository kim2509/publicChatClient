package com.dy.domain;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.dy.publicchat.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ChatRoom extends HomeListItem{

	private int ROOM_ID = 0;

	private String title = "";

	private int maxNumOfUsers = 0;
	
	private List<User> userList = null;
	
	private List<Keyword> keywordList = null;
	
	private String location = "";
	
	public int getROOM_ID() {
		return ROOM_ID;
	}

	public void setROOM_ID(int rOOM_ID) {
		ROOM_ID = rOOM_ID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@JsonIgnore
	public int getNumOfUsers() {
		return (userList == null )? 0:userList.size();
	}

	public int getMaxNumOfUsers() {
		return maxNumOfUsers;
	}

	public void setMaxNumOfUsers(int maxNumOfUsers) {
		this.maxNumOfUsers = maxNumOfUsers;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public List<Keyword> getKeywordList() {
		return keywordList;
	}

	public void setKeywordList(List<Keyword> keywordList) {
		this.keywordList = keywordList;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	@Override
	@JsonIgnore
	public void setViewData( View view, ImageLoader imageLoader ) {
		// TODO Auto-generated method stub
		super.setViewData(view, imageLoader);
		
		TextView txtTitle = (TextView) view.findViewById(R.id.txtTitle);
		txtTitle.setText( getTitle() );
		
		TextView txtNumOfUsers = (TextView) view.findViewById(R.id.txtNumOfUsers);
		txtNumOfUsers.setText( getNumOfUsers() + "/" + getMaxNumOfUsers() );
		
		TextView txtLocation = (TextView) view.findViewById(R.id.txtLocation);
		txtLocation.setText( getLocation() );
		
		TextView txtKeyword1 = (TextView) view.findViewById(R.id.txtKeyword1);
		TextView txtKeyword2 = (TextView) view.findViewById(R.id.txtKeyword2);
		TextView txtKeyword3 = (TextView) view.findViewById(R.id.txtKeyword3);
		
		txtKeyword1.setText("");
		txtKeyword2.setText("");
		txtKeyword3.setText("");
		
		ImageView imgUser1 = (ImageView) view.findViewById(R.id.imgUser1);
		ImageView imgUser2 = (ImageView) view.findViewById(R.id.imgUser2);
		ImageView imgUser3 = (ImageView) view.findViewById(R.id.imgUser3);
		ImageView imgUser4 = (ImageView) view.findViewById(R.id.imgUser4);
		
		DisplayImageOptions options = new DisplayImageOptions.Builder()
        .displayer(new RoundedBitmapDisplayer(10))
        .imageScaleType(ImageScaleType.EXACTLY)
        .cacheInMemory(true) // default
        .build();
		
		if ( getNumOfUsers() > 0 )
		{
			imgUser1.setVisibility(ViewGroup.VISIBLE);
			imageLoader.displayImage( getUserList().get(0).getProfileImageUrl(), imgUser1, options);
		}
		else
		{
			imgUser1.setImageBitmap(null);
			imgUser1.setVisibility(ViewGroup.GONE);
		}

		if ( getNumOfUsers() > 1 )
		{
			imgUser2.setVisibility(ViewGroup.VISIBLE);
			imageLoader.displayImage( getUserList().get(1).getProfileImageUrl(), imgUser2, options);
		}
		else
		{
			imgUser2.setImageBitmap(null);
			imgUser2.setVisibility(ViewGroup.GONE);
		}

		if ( getNumOfUsers() > 2 )
		{
			imgUser3.setVisibility(ViewGroup.VISIBLE);
			imageLoader.displayImage( getUserList().get(2).getProfileImageUrl(), imgUser3, options);
		}
		else
		{
			imgUser3.setImageBitmap(null);
			imgUser3.setVisibility(ViewGroup.GONE);
		}
		
		if ( getNumOfUsers() > 3 )
		{
			imgUser4.setVisibility(ViewGroup.VISIBLE);
			imageLoader.displayImage( getUserList().get(3).getProfileImageUrl(), imgUser4, options);
		}
		else
		{
			imgUser4.setImageBitmap(null);
			imgUser4.setVisibility(ViewGroup.GONE);
		}
		
		if ( getKeywordList() != null )
		{
			for ( int i = 0; i < getKeywordList().size(); i++ )
			{
				Keyword keyword = getKeywordList().get(i);
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
	}
}

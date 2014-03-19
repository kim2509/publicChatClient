package com.dy.publicchat;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class UserProfileActivity extends BaseActivity{
	
	public String[] imageList = null;
	public String[] userNames = null;
	
	ImageLoaderConfiguration config = null;
	ImageLoader imageLoader = null;
	
	int index = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try
		{
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_user_profile);
			
			config = new ImageLoaderConfiguration.Builder(this).build();
			imageLoader = ImageLoader.getInstance();
			imageLoader.init(config);
			
			imageList = new String[5];
			imageList[0] = "http://cdn8.staztic.com/app/a/2840/2840891/comclara20130715-1-3-s-307x512.jpg";
			imageList[1] = "http://upload.enews24.net/News/Contents/20130324/83316877.jpg";
			imageList[2] = "http://www.ilbe.com/files/attach/new/20130722/1349949/1115636751/1631587486/50f89605e9aba3f89bb56d87f2be77cd.jpg";
			imageList[3] = "http://betanews.net/imagedb/orig/2010/0202/d63074da.jpg";
			imageList[4] = "http://spnimage.edaily.co.kr/images/photo/files/NP/S/2014/01/PS14012100156.jpg";
			
			userNames = new String[5];
			userNames[0] = "최고의 여배우 클라라";
			userNames[1] = "최여진, 여배우 벗고 돌직구 스타일";
			userNames[2] = "젊은 여배우축에서 다양한 장르에 출연";
			userNames[3] = "김남주 '승승장구' 초대 게스트";
			userNames[4] = "윤진서 화보, 헝클어진 머리";
			
			imageLoader.loadImage( imageList[index], new SimpleImageLoadingListener() {
			    @Override
			    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
			        // Do whatever you want with Bitmap
			    	RelativeLayout layoutIntro = (RelativeLayout) findViewById(R.id.layoutUserProfile);
			    	Drawable dr = new BitmapDrawable(loadedImage);
			    	layoutIntro.setBackgroundDrawable( dr );   	
			    	TextView txtUserName = (TextView) findViewById(R.id.txtUserName);
			    	txtUserName.setText( userNames[index++]);
			    }
			});
		}
		catch( Exception ex )
		{
		}

	}
	
	public void showNext( View v )
	{
		if ( index >= imageList.length )
			index = 0;
		
		imageLoader.loadImage( imageList[index], new SimpleImageLoadingListener() {
		    @Override
		    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
		        // Do whatever you want with Bitmap
		    	RelativeLayout layoutIntro = (RelativeLayout) findViewById(R.id.layoutUserProfile);
		    	Drawable dr = new BitmapDrawable(loadedImage);
		    	layoutIntro.setBackgroundDrawable( dr );
		    	TextView txtUserName = (TextView) findViewById(R.id.txtUserName);
		    	txtUserName.setText( userNames[index++]);
		    }
		});
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if (keyCode == KeyEvent.KEYCODE_BACK) {
	        // your code
	    	Intent mainIntent = new Intent(this, MainActivity.class);
			startActivity(mainIntent);
			finish();
			
	        return true;
	    }

	    return super.onKeyDown(keyCode, event);
	}
}

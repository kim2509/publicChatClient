package com.dy.common;

import java.util.ArrayList;
import java.util.List;

import com.dy.domain.OneComment;
import com.dy.publicchat.R;
import com.dy.publicchat.R.drawable;
import com.dy.publicchat.R.id;
import com.dy.publicchat.R.layout;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DiscussArrayAdapter extends ArrayAdapter<OneComment> {

	private TextView txtChatBubble;
	private List<OneComment> chatList = new ArrayList<OneComment>();
	private LinearLayout wrapper;

	@Override
	public void add(OneComment object) {
		chatList.add(object);
		super.add(object);
	}

	public DiscussArrayAdapter(Context context, int textViewResourceId) {
		super(context, textViewResourceId);
	}

	public int getCount() {
		return this.chatList.size();
	}

	public OneComment getItem(int index) {
		return this.chatList.get(index);
	}
	
	public void setChatList( ArrayList<OneComment> chatList )
	{
		this.chatList = chatList;
		notifyDataSetChanged();
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		if (row == null) {
			LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(R.layout.listitem_discuss, parent, false);
		}

		wrapper = (LinearLayout) row.findViewById(R.id.wrapper);
		OneComment coment = getItem(position);
		txtChatBubble = (TextView) row.findViewById(R.id.comment);
		txtChatBubble.setText(coment.getMsg());
		txtChatBubble.setBackgroundResource(coment.isLeft() ? R.drawable.bubble_yellow : R.drawable.bubble_green);
		wrapper.setGravity(coment.isLeft() ? Gravity.LEFT : Gravity.RIGHT);

		return row;
	}

	public Bitmap decodeToBitmap(byte[] decodedByte) {
		return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
	}

}
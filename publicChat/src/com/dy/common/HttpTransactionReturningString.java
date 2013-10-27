package com.dy.common;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.dy.publicchat.BaseActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

public class HttpTransactionReturningString extends AsyncTask<JSONObject, Integer, String> {

	private ProgressDialog dialog = null;
	private String url = "";
	private BaseActivity activity;
	private boolean bShowDlg = true;
	int requestCode = 0;

	public HttpTransactionReturningString( BaseActivity activity, String url, int requestCode, boolean bModal )
	{
		this.activity = activity;
		dialog = new ProgressDialog( activity );
		this.url = url;
		this.bShowDlg = bModal;
		this.requestCode = requestCode;
	}

	protected void onPreExecute() {

		if ( bShowDlg )
		{
			this.dialog.setMessage("·ÎµùÁß...");
			this.dialog.show();	
		}
	}

	protected String doInBackground( JSONObject... data ) {

		try
		{
			HttpClient client = new DefaultHttpClient();
			HttpConnectionParams.setConnectionTimeout(client.getParams(), 10000); //Timeout Limit
			HttpResponse response;
			JSONObject json = data[0];
			HttpPost post = new HttpPost( Constants.serverURL + url );
			StringEntity se = new StringEntity( json.toString(), "UTF-8");
			se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
			post.setEntity(se);
			response = client.execute(post);

			String responseString = EntityUtils.toString(response.getEntity());

//			Log.d("RESPONSE", responseString );

			return responseString;

		}
		catch(Exception e){
			e.printStackTrace();

			writeLog( e.getMessage() );

			return Constants.FAIL;
		}
	}

	protected void onProgressUpdate(Integer... progress) {

	}

	protected void onPostExecute(String result) {

		if (dialog.isShowing())
			dialog.dismiss();

		activity.doPostTransaction( requestCode, result );
	}

	public void writeLog( String log )
	{
		Log.i("KoreanSG", log );
	}
}

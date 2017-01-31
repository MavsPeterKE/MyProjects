package com.example.db;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.zip.GZIPInputStream;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.util.Log;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	EditText fname,lname,mname;
	Button save;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		fname=(EditText)findViewById(R.id.fname);
		mname=(EditText)findViewById(R.id.mname);
		lname=(EditText)findViewById(R.id.lname);
		save=(Button)findViewById(R.id.save);
		
		save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				   
					String a=fname.getText().toString();
					String b=mname.getText().toString();
					String c=lname.getText().toString();
					
					AsyncHttpClient network_trigger=new AsyncHttpClient();
					
					 try {
						 RequestParams alldata=new RequestParams();
						 JSONObject mydata= new JSONObject();
						 mydata.put("fname",a);
						 mydata.put("mname",b);
						 mydata.put("lname",c);
						 //Convert JSON Object to string 
						 alldata.put("mydata",mydata.toString());
						 Log.d("DATA", mydata.toString());
						 network_trigger.post("http://10.0.2.2/2015%20Projects/update.php",alldata,new AsyncHttpResponseHandler() {
							
							@Override
							public void onSuccess(int arg0, Header[] arg1, byte[] response) {
								// TODO Auto-generated method stub
								String result=new String(response);
								Log.d("DATA", result);
								if(result.equals("3")){
									Toast.makeText(getApplicationContext(),"All Details Required",Toast.LENGTH_LONG).show();
								}else if(result.equals("2")){
									Toast.makeText(getApplicationContext(),"Records Already in The Database",Toast.LENGTH_LONG).show();
								}else if(result.equals("4"))
								
								{Toast.makeText(getApplicationContext(),"Record Saved Successfully",Toast.LENGTH_LONG).show();}
								
								else if(result.equals("5"))
									
								{Toast.makeText(getApplicationContext(),"Problem Communicating with server",Toast.LENGTH_LONG).show();}
							
							}
							
							@Override
							public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
								// TODO Auto-generated method stub
								Log.e("DATA", "Failed to connect");
								 Toast.makeText(MainActivity.this,"Problem Connecting To server", Toast.LENGTH_SHORT).show();
							}
						});
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
			}});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

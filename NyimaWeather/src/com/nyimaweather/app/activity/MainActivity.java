package com.nyimaweather.app.activity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nyimaweather.app.R;
import com.nyimaweather.app.db.Index;
import com.nyimaweather.app.db.Results;
import com.nyimaweather.app.db.Root;
import com.nyimaweather.app.db.Weather_data;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nyimaweather.app.util.*;

public class MainActivity extends Activity implements OnClickListener {
	
	public static final int SHOW_RESPONSE=0;
	private Button sendRequest;
	LinearLayout container;//?
	private Bitmap today_day = null;
	private Bitmap today_night = null;
	ImageView img1=null;
	ImageView img2=null;
	private downloadImageTask task;
	private downloadImageTask task1;
	//LS添加
	private Button switchCity;
	private Button settings;
	
	
	//Activity初始化过程
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		sendRequest=(Button) findViewById(R.id.send_request);//创建按钮以及文本框等
		sendRequest.setOnClickListener(this);
		container = (LinearLayout) findViewById(R.id.container);
		task = new downloadImageTask();
		task1 = new downloadImageTask();
		img1=new ImageView(MainActivity.this);
		img2=new ImageView(MainActivity.this);
		
		switchCity=(Button) findViewById(R.id.switch_city);
		switchCity.setOnClickListener(this);
		
		settings=(Button)findViewById(R.id.settings);
		settings.setOnClickListener(this);
	}
	
	//响应点击函数
	public void onClick(View v){
		if(v.getId()==R.id.send_request){    //点击获取数据
			sendRequsetWithHttpClient();
		}
		else if(v.getId()==R.id.switch_city){   //点击切换城市
			Intent intent=new Intent();
			intent.setClass(MainActivity.this,Switch_city.class);
			startActivity(intent);
		}
		else if(v.getId()==R.id.settings){
			Intent intent2=new Intent();
			intent2.setClass(MainActivity.this,Settings.class);
			startActivity(intent2);
		}
	}
	
	
	//http请求
	private void sendRequsetWithHttpClient() {
		// TODO Auto-generated method stub
		new Thread (new Runnable(){
			@Override
			public void run(){
				try{
					HttpClient httpClient=new DefaultHttpClient();
					String city="哈尔滨";
					//HttpGet httpGet=new HttpGet("http://api.map.baidu.com/telematics/v3/weather?location="+ URLEncoder.encode(city, "UTF-8")+"&output=json&ak=nUAIT4hy05w6dxZ7UUQpLhBn&mcode=84:1A:43:1F:FC:36:2E:B4:F5:0C:CC:BD:5A:F6:27:2E:A3:89:45:3E;com.nyimaweather.app");				 
					HttpGet httpGet=new HttpGet("http://api.map.baidu.com/telematics/v3/weather?location="+ URLEncoder.encode(city, "UTF-8")+"&output=json&ak=ytyCsEX2nAmEPxCc1DbZfQrB");				 
					HttpResponse httpResponse=httpClient.execute(httpGet);
					if(httpResponse.getStatusLine().getStatusCode()==200){
						//请求和响应都成功了
						HttpEntity entity1=httpResponse.getEntity();
						String response =EntityUtils.toString(entity1,"utf-8");
						parseJSONWithGSON(response);
					}
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	//解析json
	private void parseJSONWithGSON(String jsonData) {
		Gson gson=new Gson();
		final Root root =gson.fromJson(jsonData,Root.class);
		final List<Results> r=root.getResults();
		final List<Weather_data> w=r.get(0).getWeather_data();//r是list，任何操作都先加一个get(0)
		final List<Index> l=r.get(0).getIndex();
		container.post(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				img1.setId(1);
				RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(
						ViewGroup.LayoutParams.MATCH_PARENT,
						ViewGroup.LayoutParams.WRAP_CONTENT);
				lp1.addRule(RelativeLayout.RIGHT_OF,1);
				
				TextView text1 = new TextView(MainActivity.this);
				TextView text2 = new TextView(MainActivity.this);
				TextView text3 = new TextView(MainActivity.this);
				TextView text4 = new TextView(MainActivity.this);
				TextView text5 = new TextView(MainActivity.this);
				TextView text6 = new TextView(MainActivity.this);
				
				text1.setText(r.get(0).showbase());
				text2.setText(w.get(0).showtoday());
				text3.setText(w.get(1).shownnnday());
				text4.setText(w.get(2).shownnnday());
				text5.setText(w.get(3).shownnnday());
				text6.setText(l.get(0).tieshi());
				task.execute(w.get(0).getDayPictureUrl());
				task1.execute(w.get(0).getNightPictureUrl());
				
				
				img1.setImageBitmap(today_day);
				img2.setImageBitmap(today_night);
				container.addView(text1);
				container.addView(text2);
				container.addView(img1,lp1);
				container.addView(img2,lp1);
				container.addView(text6);
				container.addView(text3);
				container.addView(text4);
				container.addView(text5);
			}
			
		
		});
	}
	
	
	
	
	
	
	class downloadImageTask extends AsyncTask<String, Integer, Boolean> {

		@Override
		protected Boolean doInBackground(String...params) {
			// TODO Auto-generated method stub
			System.out.println("[downloadImageTask->]doInBackground "
					+ params[0]);
			today_day = HttpUtils.getNetWorkBitmap(params[0]);
			return true;
		}
	

		// 更新进度回调
		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
		}
		
		// 下载完成回调
		@Override
		protected void onPostExecute(Boolean result) {
			// TODO Auto-generated method stub
			img1.setImageBitmap(today_day);
			System.out.println("result = " + result);
			super.onPostExecute(result);
		}


	}
	
	
	
	
}

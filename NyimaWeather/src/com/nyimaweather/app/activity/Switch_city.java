package com.nyimaweather.app.activity;

import com.nyimaweather.app.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ScrollView;

public class Switch_city extends Activity{
	
	AutoCompleteTextView enterCity=null;
	ScrollView cityList=null;
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_switch_city);
		
		//获取布局文件中两个空间对象
		enterCity=(AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
		cityList=(ScrollView)findViewById(R.id.scrollView1);
		
		//设置数据源，暂时的
		String[] autoStrings = new String[] {"北京","上海","哈尔滨","纽约","巴黎","广州"};
		
		//设置ArrayAdapter，第二个参数为下拉列表风格
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(Switch_city.this,android.R.layout.simple_dropdown_item_1line,autoStrings);
		
		//设置AutoCompleteTextView的adapter
		enterCity.setAdapter(adapter);
		enterCity.setThreshold(1);
		
		//设置监听
		enterCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
}

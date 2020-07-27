package introduction.android.widgetDemo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class SpinnerActivity extends Activity {
	 private List<String> list = new ArrayList<String>();
	    private TextView textview;
	    private Spinner spinnertext;
	    private ArrayAdapter<String> adapter;
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.spiner);
	        //第一步：定义下拉列表内容
	        list.add("沈阳");
	        list.add("天津");
	        list.add("北京");
	        list.add("上海");
	        list.add("深圳");
	        textview = (TextView)findViewById(R.id.textView1);
	        spinnertext = (Spinner)findViewById(R.id.spinner1);
	        //第二步：为下拉列表定义一个适配器
	        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, list);
	        //ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.city, android.R.layout.simple_spinner_item);
	        //第三步：设置下拉列表下拉时的菜单样式。
	        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	        //第四步：将适配器添加到下拉列表上
	        spinnertext.setAdapter(adapter);
	        //第五步：添加监听器，为下拉列表设置事件的响应
	        spinnertext.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
	            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
	                // TODO Auto-generated method stub
	                /* 将所选spinnertext的值带入myTextView 中*/
	        	textview.setText("我来自："+ adapter.getItem(arg2));
	                /* 将spinnertext显示*/
	                arg0.setVisibility(View.VISIBLE);
	            }
	            public void onNothingSelected(AdapterView<?> arg0) {
	                // TODO Auto-generated method stub
	        	textview.setText("NONE");
	                arg0.setVisibility(View.VISIBLE);
	            }
	        });
	        //将spinnertext添加到OnTouchListener 对内容选项触屏事件处理
	        spinnertext.setOnTouchListener(new Spinner.OnTouchListener(){
		    @Override
		    public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			// 将mySpinner 隐藏
                v.setVisibility(View.INVISIBLE);
                Log.i("spinner","Spinner Touch事件被触发!");
                return false;
		    }
	        });
	        //焦点改变事件处理
	        spinnertext.setOnFocusChangeListener(new Spinner.OnFocusChangeListener(){
	            public void onFocusChange(View v, boolean hasFocus) {
	            // TODO Auto-generated method stub
	                v.setVisibility(View.VISIBLE);
	                Log.i("spinner","Spinner FocusChange 事件被触发！");
	            }
	            });
	 
	 }
}

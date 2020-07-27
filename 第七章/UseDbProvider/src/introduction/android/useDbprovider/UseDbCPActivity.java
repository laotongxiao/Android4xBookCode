package introduction.android.useDbprovider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import introduction.android.mydbDemo.MyFriendsDB;

import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class UseDbCPActivity extends Activity {
    private List<Map<String, String>> data;
	private SimpleAdapter listAdapter;
	private ListView listview;
	private HashMap<String, String> item;
	private Button selBtn,addBtn,updBtn,delBtn;
	private EditText et_name;
	private EditText et_age;
	private EditText et_id;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        et_name=(EditText) findViewById(R.id.et_name);
		et_age=(EditText) findViewById(R.id.et_age);
		et_id=(EditText) findViewById(R.id.et_id);
        listview=(ListView)findViewById(R.id.listView);
        selBtn=(Button)findViewById(R.id.bt_query);
		addBtn=(Button)findViewById(R.id.bt_add);
		updBtn=(Button)findViewById(R.id.bt_modify);
		delBtn=(Button)findViewById(R.id.bt_del);
		selBtn.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if(et_id.getText().toString().equals(""))
					dbFindAll(MyFriendsDB.CONTENT_TYPE);
				else
					dbFindAll(MyFriendsDB.CONTENT_ITEM_TYPE);;
			}			
		});
		addBtn.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dbAdd(null);
				dbFindAll(MyFriendsDB.CONTENT_TYPE);
			}			
		});
		updBtn.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dbUpdate(null);
				dbFindAll(MyFriendsDB.CONTENT_TYPE);				
			}			
		});
		delBtn.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dbDel(-1);
				dbFindAll(MyFriendsDB.CONTENT_TYPE);
			}			
		});
        data = new ArrayList<Map<String,String>>();	       
        dbFindAll(MyFriendsDB.CONTENT_TYPE);
        
        listview.setOnItemClickListener(new OnItemClickListener() {
			private String selId;

			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				// TODO Auto-generated method stub
				
				Map<String,Object> listItem =(Map<String,Object>) listview.getItemAtPosition(position);
				et_name.setText((String)listItem.get("name"));
				et_age.setText((String)listItem.get("age"));				
				et_id.setText((String)listItem.get("_id"));
				Log.i("UseDB","id="+selId);
			}
		});	
    }

    private void showList() {
		// TODO Auto-generated method stub
		
		listAdapter=new SimpleAdapter(this,data,
				R.layout.listview,
				new String[]{"_id","name","age"},
				new int[]{R.id.tvID,R.id.tvName,R.id.tvAge});
		listview.setAdapter(listAdapter);
	}
    
	protected void dbDel(long iid) {
		// TODO Auto-generated method stub
		if(iid<0){
			String id=et_id.getText().toString().trim();
			if(id.equals("")){
				Log.e("UseDB","未指定更新数据。");
				return;
			}
			iid=Long.parseLong(id);
		}
		Uri uri=ContentUris.withAppendedId(MyFriendsDB.CONTENT_URI,iid);
		int i=this.getContentResolver().delete(uri, null, null);
		if(i>0){
			Log.i("UseDB","已删除数据id="+iid);
		}else{
			Log.i("UseDB","数据未删除。");
		}
	}

	protected void dbUpdate(ContentValues values) {
		// TODO Auto-generated method stub
		String id=et_id.getText().toString().trim();
		if(id.equals("")){
			Log.e("UseDB","未指定更新数据。");
			return;
		}
		Long selid=Long.parseLong(id);
		Uri uri=ContentUris.withAppendedId(MyFriendsDB.CONTENT_URI,selid);
		if(values==null){
			values=new ContentValues();
			values.put("name", et_name.getText().toString().trim());
			values.put("age", et_age.getText().toString().trim());
		}
		int i=this.getContentResolver().update(uri, values, null, null);
		if(i>0){
			Log.i("UseDB","已更新数据id="+selid);
		}else{
			Log.e("UseDB","数据更新失败！");
		}
	}

	protected void dbAdd(ContentValues values) {
		// TODO Auto-generated method stub
		if(values==null){
			values=new ContentValues();
			values.put("name", et_name.getText().toString().trim());
			values.put("age", et_age.getText().toString().trim());
		}
		Uri uri=this.getContentResolver().insert(MyFriendsDB.CONTENT_URI, values);
		if(uri==null){
			Log.e("UseDB","数据插入失败！");
		}
	}

	protected void dbFindAll(String type) {
		// TODO Auto-generated method stub
		data.clear();
		Cursor cursor;
		Uri uri;
		if(type==MyFriendsDB.CONTENT_TYPE){
			uri=MyFriendsDB.CONTENT_URI;
		}else{
			Long selid=Long.parseLong(et_id.getText().toString().trim());
			uri=ContentUris.withAppendedId(MyFriendsDB.CONTENT_URI,selid);		
			Log.d("UseDB",uri.toString());
		}
		cursor=this.getContentResolver().query(uri, null, null, null, null);
		cursor.moveToFirst();
        while (!cursor.isAfterLast() ) {
			String id=cursor.getString(0);
			String name=cursor.getString(1);
			String age=cursor.getString(2);			
			item=new HashMap<String,String>();
			item.put("_id", id);
			item.put("name", name);
			item.put("age", age);
			data.add(item);
			cursor.moveToNext();			
		}
        showList();
	}
}
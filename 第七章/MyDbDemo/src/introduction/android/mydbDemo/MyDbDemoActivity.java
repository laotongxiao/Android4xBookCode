package introduction.android.mydbDemo;


import introduction.android.mydbDemo.dbHelper;
import introduction.android.mydbDemo.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MyDbDemoActivity extends Activity {
	private static String DB_NAME = "mydb";
    private EditText et_name;
	private EditText et_age;
	private ArrayList<Map<String, Object>> data;
	private dbHelper dbHelper;
	private SQLiteDatabase db;
	private Cursor cursor;
	private SimpleAdapter listAdapter;
	private View view;
	private ListView listview;
	private Button selBtn,addBtn,updBtn,delBtn;
	private Map<String,Object> item;
	private String selId;
	private ContentValues selCV;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        et_name=(EditText) findViewById(R.id.et_name);
		et_age=(EditText) findViewById(R.id.et_age);
		listview = (ListView) findViewById(R.id.listView);
		selBtn=(Button)findViewById(R.id.bt_query);
		addBtn=(Button)findViewById(R.id.bt_add);
		updBtn=(Button)findViewById(R.id.bt_modify);
		delBtn=(Button)findViewById(R.id.bt_del);
		selBtn.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//dbFindAll();
				Uri uri=ContentUris.withAppendedId(MyFriendsDB.CONTENT_URI,7);
				Cursor cursor=getContentResolver().query(uri, null, null, null, null);
			}			
		});
		addBtn.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dbAdd();
				dbFindAll();
			}			
		});
		updBtn.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dbUpdate();
				dbFindAll();				
			}			
		});
		delBtn.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dbDel();
				dbFindAll();
			}			
		});
		dbHelper = new dbHelper(this, DB_NAME, null, 1);
		db = dbHelper.getWritableDatabase();// �����ݿ�		
		data = new ArrayList<Map<String,Object>>();	
		dbFindAll();
		
		listview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				// TODO Auto-generated method stub
				
				Map<String,Object> listItem =(Map<String,Object>) listview.getItemAtPosition(position);
				et_name.setText((String)listItem.get("name"));
				et_age.setText((String)listItem.get("age"));
				selId=(String)listItem.get("_id");
				Log.i("mydbDemo","id="+selId);
			}
		});		
    }

	protected void dbDel() {
		// TODO Auto-generated method stub
		String where="_id="+selId;
		int i=db.delete(dbHelper.TB_NAME, where, null);
		if(i>0)
			Log.i("myDbDemo","����ɾ���ɹ���");
		else
			Log.i("myDbDemo","����δɾ����");
	}

	private void showList() {
		// TODO Auto-generated method stub
			
		listAdapter=new SimpleAdapter(this,data,
				R.layout.listview,
				new String[]{"_id","name","age"},
				new int[]{R.id.tvID,R.id.tvName,R.id.tvAge});
		listview.setAdapter(listAdapter);
	}

	protected void dbUpdate() {
		// TODO Auto-generated method stub
		ContentValues values = new ContentValues();
		values.put("name", et_name.getText().toString().trim());
		values.put("age", et_age.getText().toString().trim());
		String where="_id="+selId;
		int i=db.update(dbHelper.TB_NAME, values, where, null);
		if(i>0)
			Log.i("myDbDemo","���ݸ��³ɹ���");
		else
			Log.i("myDbDemo","����δ���£�");
	}

	protected void dbAdd() {
		// TODO Auto-generated method stub
		ContentValues values = new ContentValues();
		values.put("name", et_name.getText().toString().trim());
		values.put("age", et_age.getText().toString().trim());
		long rowid=db.insert(dbHelper.TB_NAME, null, values);
		if(rowid==-1)
			Log.i("myDbDemo", "���ݲ���ʧ�ܣ�");
		else
			Log.i("myDbDemo", "���ݲ���ɹ���"+rowid);
	}

	protected void dbFindAll() {
		// TODO Auto-generated method stub		
		data.clear();
		cursor = db.query(dbHelper.TB_NAME, null, null, null, null, null,
				"_id ASC");
		cursor.moveToFirst();		
		while (!cursor.isAfterLast() ) {
			String id=cursor.getString(0);
			String name=cursor.getString(1);
			String age=cursor.getString(2);			
			item=new HashMap<String,Object>();
			item.put("_id", id);
			item.put("name", name);
			item.put("age", age);
			data.add(item);
			cursor.moveToNext();			
		}
		showList();
	}	
}
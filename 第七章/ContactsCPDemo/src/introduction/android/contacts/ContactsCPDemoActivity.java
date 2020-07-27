package introduction.android.contacts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class ContactsCPDemoActivity extends Activity {
    private SimpleAdapter listAdapter;
	private ListView listview;
	private ArrayList<Map<String, String>> data;
	private HashMap<String, String> item;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        listview=(ListView)this.findViewById(R.id.listView);
        data = new ArrayList<Map<String,String>>();	
        Cursor cursor=this.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
              
        while(cursor.moveToNext()){
        	int idFieldIndex=cursor.getColumnIndex(ContactsContract.Contacts._ID);
            int id=cursor.getInt(idFieldIndex);//��������ȡ�ø���ϵ�˵�id��
            int nameFieldIndex=cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
            String name=cursor.getString(nameFieldIndex);//��������ȡ�ø���ϵ�˵�name��
            int numCountFieldIndex=cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER);
            int numCount=cursor.getInt(numCountFieldIndex);//��ȡ��ϵ�˵ĵ绰�������
            String phoneNumber="";
            if(numCount>0){//��ϵ��������һ���绰����
            	//����ContactsContract.CommonDataKinds.Phone�и���id��ѯ��Ӧ��ϵ�˵����е绰��
            	Cursor phonecursor=getContentResolver().query(
            		ContactsContract.CommonDataKinds.Phone.CONTENT_URI, 
            		null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID+"=?", 
            		new String[]{Integer.toString(id)}, null);
            	if(phonecursor.moveToFirst()){//����ȡ��һ���绰����
            		int numFieldIndex=phonecursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            		phoneNumber=phonecursor.getString(numFieldIndex);            		          		
            	}
            }          
            item=new HashMap<String,String>();
            item.put("name", name);
            item.put("phoneNumber", phoneNumber);
            data.add(item);
        }
        listAdapter=new SimpleAdapter(this,data,
				android.R.layout.simple_list_item_2,
				new String[]{"name","phoneNumber"},
				new int[]{android.R.id.text1,android.R.id.text2});
		listview.setAdapter(listAdapter);
//        listAdapter=new SimpleCursorAdapter(this,
//				R.layout.listview,cursor,
//				new String[]{ContactsContract.Contacts.DISPLAY_NAME,phoneNumbers},
//				new int[]{R.id.tvID});
//		listview.setAdapter(listAdapter);
    }
}
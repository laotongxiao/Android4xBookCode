package introduction.android.SharedPreferencesDemo;

import javax.security.auth.PrivateCredentialPermission;

import introduction.android.SharedPreferencesDemo.R;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

public class SharedPreferencesDemo extends Activity {

    private EditText phoneText,cityText;
    private String phone,city;
    public static final String SET_INFO ="SET_Info";
    public static final String PHONE = "PHONE";
    public static final String CITY = "CITY";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        phoneText=(EditText)findViewById(R.id.phone_text);
        cityText=(EditText)findViewById(R.id.city_text);
        /*��ȡShared Preferences����*/
        SharedPreferences setinfo = getPreferences(Activity.MODE_PRIVATE);
        /*ȡ������ĵ绰����͵�ַ��Ϣ*/
        phone = setinfo.getString(PHONE,"");
        city = setinfo.getString(CITY, "");
        /*��ȡ������Ϣ�ֱ���ڶ�Ӧ��EditText��*/
        phoneText.setText(phone);
        cityText.setText(city);
    }
    
    @Override
    protected void onStop() {	
        SharedPreferences setinfo = getPreferences(Activity.MODE_PRIVATE);
        setinfo.edit()
        .putString(PHONE,phoneText.getText().toString())
        .putString(CITY,cityText.getText().toString())
        .commit();
        super.onStop();
    } 
}
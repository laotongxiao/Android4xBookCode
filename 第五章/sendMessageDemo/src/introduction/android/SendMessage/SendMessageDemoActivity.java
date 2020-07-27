package introduction.android.SendMessage;


import android.app.Activity;
import android.os.Bundle;

import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SendMessageDemoActivity extends Activity {
    /** Called when the activity is first created. */
	private Button button;
	private EditText edittext01,edittext02;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new buttonListener());//为发送按钮添加监听器
    }
    class buttonListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			edittext01 = (EditText)findViewById(R.id.edittext01);
			edittext02 = (EditText)findViewById(R.id.edittext02);
			
			String number = edittext01.getText().toString();//获取手机号码
			String message01 = edittext02.getText().toString();//获取短信内容
			if(number.equals("") || message01.equals(""))//判输入是否有空内容
			{
				Toast.makeText(SendMessageDemoActivity.this, "输入有误，请检查输入", Toast.LENGTH_LONG).show();
				}
			else{
		            SmsManager massage = SmsManager.getDefault();
		            massage.sendTextMessage(number, null, message01, null, null);
		  
           		//调用sendTextMessage方法来发送短信
		            Toast.makeText(SendMessageDemoActivity.this, "短信发送成功", Toast.LENGTH_LONG).show();
           	    }

				
			}
			
			
	}
    
}

    
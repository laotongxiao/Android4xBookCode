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
        button.setOnClickListener(new buttonListener());//Ϊ���Ͱ�ť��Ӽ�����
    }
    class buttonListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			edittext01 = (EditText)findViewById(R.id.edittext01);
			edittext02 = (EditText)findViewById(R.id.edittext02);
			
			String number = edittext01.getText().toString();//��ȡ�ֻ�����
			String message01 = edittext02.getText().toString();//��ȡ��������
			if(number.equals("") || message01.equals(""))//�������Ƿ��п�����
			{
				Toast.makeText(SendMessageDemoActivity.this, "����������������", Toast.LENGTH_LONG).show();
				}
			else{
		            SmsManager massage = SmsManager.getDefault();
		            massage.sendTextMessage(number, null, message01, null, null);
		  
           		//����sendTextMessage���������Ͷ���
		            Toast.makeText(SendMessageDemoActivity.this, "���ŷ��ͳɹ�", Toast.LENGTH_LONG).show();
           	    }

				
			}
			
			
	}
    
}

    
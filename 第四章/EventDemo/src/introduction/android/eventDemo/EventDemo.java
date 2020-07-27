package introduction.android.eventDemo;


import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.Toast;

public class EventDemo extends Activity implements OnFocusChangeListener {
    Button[] buttons = new Button[3];
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        buttons[0] =(Button)findViewById(R.id.button1);
        buttons[1] =(Button)findViewById(R.id.button2);
        buttons[2] =(Button)findViewById(R.id.button3);
        for(Button button :buttons){
            button.setOnFocusChangeListener(this);
        }
    }
    //�������´������¼�    
    public boolean onKeyDown(int keyCode,KeyEvent event) 
    { 
        switch(keyCode) 
        { 
            case KeyEvent.KEYCODE_DPAD_UP: 
                DisplayInformation("�����Ϸ������KEYCODE_DPAD_UP"); 
                break; 
            case KeyEvent.KEYCODE_DPAD_DOWN: 
                DisplayInformation("�����·������KEYCODE_DPAD_UP"); 
                break; 
        } 
        return false;      
    }    
    //�������𴥷����¼� 
    public boolean onKeyUp(int keyCode,KeyEvent event) 
    { 
        switch(keyCode) 
        { 
            case KeyEvent.KEYCODE_DPAD_UP: 
                DisplayInformation("�ɿ��Ϸ������KEYCODE_DPAD_UP"); 
                break; 
            case KeyEvent.KEYCODE_DPAD_DOWN: 
                DisplayInformation("�ɿ��·������KEYCODE_DPAD_UP"); 
                break; 
        } 
        return false; 
         
    } 
    
     
    //�ʴ��¼� 
    public boolean onTouchEvent(MotionEvent event) 
    { 
        switch (event.getAction()) {
	case MotionEvent.ACTION_DOWN:
	    DisplayInformation("��ָ��������Ļ�ϰ���");
	    break;
	case MotionEvent.ACTION_MOVE:
	    DisplayInformation("��ָ������Ļ���ƶ�");
	    break;
	case MotionEvent.ACTION_UP:
	    DisplayInformation("��ָ���ڴ���Ļ��̧��");
	    break;
	}
        return false; 
    } 
    
    //�����¼�
    @Override
    public void onFocusChange(View view, boolean arg1) {
	switch (view.getId()) {
	case R.id.button1:
	    DisplayInformation("��һ����ť����˽���");
	    break;
	case R.id.button2:
	    DisplayInformation("�ڶ�����ť����˽���");
	    break;
	case R.id.button3:
	    DisplayInformation("��������ť����˽���");
	    break;
	}
    }
    
    //��ʾToast 
    public void DisplayInformation(String string) 
    { 
        //Toast.makeText(EventDemo.this,string,Toast.LENGTH_SHORT).show();   
    	Log.i("enentDemo",string);
    }     
}
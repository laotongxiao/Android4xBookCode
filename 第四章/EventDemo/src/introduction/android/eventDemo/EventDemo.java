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
    //按键按下触发的事件    
    public boolean onKeyDown(int keyCode,KeyEvent event) 
    { 
        switch(keyCode) 
        { 
            case KeyEvent.KEYCODE_DPAD_UP: 
                DisplayInformation("按下上方向键，KEYCODE_DPAD_UP"); 
                break; 
            case KeyEvent.KEYCODE_DPAD_DOWN: 
                DisplayInformation("按下下方向键，KEYCODE_DPAD_UP"); 
                break; 
        } 
        return false;      
    }    
    //按键弹起触发的事件 
    public boolean onKeyUp(int keyCode,KeyEvent event) 
    { 
        switch(keyCode) 
        { 
            case KeyEvent.KEYCODE_DPAD_UP: 
                DisplayInformation("松开上方向键，KEYCODE_DPAD_UP"); 
                break; 
            case KeyEvent.KEYCODE_DPAD_DOWN: 
                DisplayInformation("松开下方向键，KEYCODE_DPAD_UP"); 
                break; 
        } 
        return false; 
         
    } 
    
     
    //笔触事件 
    public boolean onTouchEvent(MotionEvent event) 
    { 
        switch (event.getAction()) {
	case MotionEvent.ACTION_DOWN:
	    DisplayInformation("手指正在往屏幕上按下");
	    break;
	case MotionEvent.ACTION_MOVE:
	    DisplayInformation("手指正在屏幕上移动");
	    break;
	case MotionEvent.ACTION_UP:
	    DisplayInformation("手指正在从屏幕上抬起");
	    break;
	}
        return false; 
    } 
    
    //焦点事件
    @Override
    public void onFocusChange(View view, boolean arg1) {
	switch (view.getId()) {
	case R.id.button1:
	    DisplayInformation("第一个按钮获得了焦点");
	    break;
	case R.id.button2:
	    DisplayInformation("第二个按钮获得了焦点");
	    break;
	case R.id.button3:
	    DisplayInformation("第三个按钮获得了焦点");
	    break;
	}
    }
    
    //显示Toast 
    public void DisplayInformation(String string) 
    { 
        //Toast.makeText(EventDemo.this,string,Toast.LENGTH_SHORT).show();   
    	Log.i("enentDemo",string);
    }     
}
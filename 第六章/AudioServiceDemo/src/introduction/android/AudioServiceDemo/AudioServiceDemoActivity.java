package introduction.android.AudioServiceDemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AudioServiceDemoActivity extends Activity {
    /** Called when the activity is first created. */
	private Button btn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        btn=(Button)findViewById(R.id.button1);
        btn.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startService(new Intent("introduction.android.AudioServiceDemo.MY_AUDIO_SERVICE"));
				finish();
			}        	
        });
    }
}
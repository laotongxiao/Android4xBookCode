package introduction.android.dataTransDemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class DataTransDemoActivity extends Activity {
    /** Called when the activity is first created. */
	final int REQUEST_CODE=1;
	TextView tv;
	Button btn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        tv=(TextView) this.findViewById(R.id.textView1);
        btn=(Button)this.findViewById(R.id.button1);
        btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(DataTransDemoActivity.this,Activity2.class);
			       
		        intent.putExtra("author", "leebo");
		        Bundle myBundle=new Bundle();
		        myBundle.putString("bookname", "android");
		        intent.putExtras(myBundle);
				startActivityForResult(intent, REQUEST_CODE);
			}
        	
        });
        
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==this.REQUEST_CODE){
			switch(resultCode){
			case RESULT_OK:				
				String str=data.getStringExtra("Activity2");
				tv.setText(str);
				break;
			default:
				break;
			}
		}
	}
}
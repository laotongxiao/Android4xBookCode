package introduction.android.dataTransDemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Activity2 extends Activity {
	private TextView tv;
	Button btn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout2);
		tv=(TextView)this.findViewById(R.id.textView1);
		btn=(Button)this.findViewById(R.id.button1);
		Intent intent=Activity2.this.getIntent();
		Bundle myBundle=intent.getExtras();
		String bookname=(String) myBundle.get("bookname");
		String author=intent.getStringExtra("author");
		StringBuilder str=new StringBuilder(bookname);
		str.append(author);
		tv.setText(str);
		
	    btn.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					Intent intent=new Intent();
					intent.putExtra("Activity2", "activity2");
					Activity2.this.setResult(RESULT_OK, intent);
					finish();
				}
			});
	        
		
	}

}

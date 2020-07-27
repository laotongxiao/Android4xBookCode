package introduction.android.widgetDemo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class WidgetDemoActivity extends Activity {
    /** Called when the activity is first created. */
	private Button btn;	
	private TextView textview;
	private EditText editText;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        btn=(Button)this.findViewById(R.id.button1);  
        textview = (TextView)findViewById(R.id.textView1); 
        editText=(EditText)findViewById(R.id.editText1);
        editText.addTextChangedListener(new TextWatcher(){

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				String text=editText.getText().toString();
				textview.setText(text);
			}
        	
        });
        btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				//setTitle("button1 被用户点击了");
				Log.i("widgetDemo", "button1 被用户点击了。");
				
				textview.setText("设置TextView的字体");
				textview.setTextColor(Color.RED);
				textview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
				textview.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
			}        	
        });
        Button ckbtn=(Button)this.findViewById(R.id.button2);
        ckbtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(WidgetDemoActivity.this,CheckBoxActivity.class);
				startActivity(intent);
			}        	
        });
        Button radiotn=(Button)this.findViewById(R.id.button3);
        radiotn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(WidgetDemoActivity.this,RadioGroupActivity.class);
				startActivity(intent);
			}        	
        });
        Button spinnerbtn=(Button)this.findViewById(R.id.button4);
        spinnerbtn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(WidgetDemoActivity.this,SpinnerActivity.class);
				startActivity(intent);
			}        	
        });
        Button autobtn=(Button)this.findViewById(R.id.button5);
        autobtn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(WidgetDemoActivity.this,AutoCompleteTextViewActivity.class);
				startActivity(intent);
			}        	
        });
        Button timebtn=(Button)this.findViewById(R.id.button6);
        timebtn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(WidgetDemoActivity.this,TimeActivity.class);
				startActivity(intent);
			}        	
        });
        Button processbtn=(Button)this.findViewById(R.id.button7);
        processbtn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(WidgetDemoActivity.this,ProcessBarActivity.class);
				startActivity(intent);
			}        	
        });
        Button seekbarbtn=(Button)this.findViewById(R.id.button8);
        seekbarbtn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(WidgetDemoActivity.this,SeekBarActivity.class);
				startActivity(intent);
			}        	
        });
        Button ratingbarbtn=(Button)this.findViewById(R.id.button9);
        ratingbarbtn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(WidgetDemoActivity.this,RatingBarActivity.class);
				startActivity(intent);
			}        	
        });
        Button imgbtn=(Button)this.findViewById(R.id.button10);
        imgbtn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(WidgetDemoActivity.this,ImageButtonActivity.class);
				startActivity(intent);
			}        	
        });
        Button galleyBtn=(Button)this.findViewById(R.id.button11);
        galleyBtn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(WidgetDemoActivity.this,GalleryActivity.class);
				startActivity(intent);
			}        	
        });
        Button gridViewBtn=(Button)this.findViewById(R.id.button12);
        gridViewBtn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(WidgetDemoActivity.this,GridViewActivity.class);
				startActivity(intent);
			}        	
        });
        Button tabBtn=(Button)this.findViewById(R.id.button13);
        tabBtn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(WidgetDemoActivity.this,TabsActivity.class);
				startActivity(intent);
			}        	
        });
    }
}
package introduction.android.tvdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.ImageButton;
import android.widget.ImageView.ScaleType;

public class MainActivity extends Activity {
	ImageButton iv1,iv2,iv3;
	private String tag="TV";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		iv1=(ImageButton) this.findViewById(R.id.imageButton1);
		iv2=(ImageButton) this.findViewById(R.id.imageButton2);
		iv3=(ImageButton) this.findViewById(R.id.imageButton3);
		iv1.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				iv1.setScaleType(ScaleType.CENTER);
				if(hasFocus){
					iv1.setScaleX(1.3f);
					iv1.setScaleY(1.3f);
				}else{
					iv1.setScaleX(1.0f);
					iv1.setScaleY(1.0f);
				}
			}
		});
		iv2.setOnFocusChangeListener(new OnFocusChangeListener() {			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				iv2.setScaleType(ScaleType.CENTER);
				if(hasFocus){
					iv2.setScaleX(1.3f);
					iv2.setScaleY(1.3f);
				}else{
					iv2.setScaleX(1.0f);
					iv2.setScaleY(1.0f);
				}
			}
		});
		iv3.setOnFocusChangeListener(new OnFocusChangeListener() {			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				iv2.setScaleType(ScaleType.CENTER);
				if(hasFocus){
					iv3.setScaleX(1.3f);
					iv3.setScaleY(1.3f);
				}else{
					iv3.setScaleX(1.0f);
					iv3.setScaleY(1.0f);
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}

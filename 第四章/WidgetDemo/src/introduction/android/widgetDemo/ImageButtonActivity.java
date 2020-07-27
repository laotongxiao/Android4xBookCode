package introduction.android.widgetDemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageButton;
import android.widget.ImageView;


public class ImageButtonActivity extends Activity {

	private ImageButton imgbtn;
	private ImageView imgview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.imgbtn);
		imgbtn=(ImageButton)this.findViewById(R.id.imageButton1);
		imgview=(ImageView)this.findViewById(R.id.imageView1);
		imgbtn.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				LayoutParams params=imgview.getLayoutParams();
				params.height+=3;
				params.width+=3;
				imgview.setLayoutParams(params);
			}
			
		});
	}
	
}

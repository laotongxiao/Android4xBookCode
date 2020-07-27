package introduction.android.playvideo;

import introduction.android.playvideo.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class VideoPlayAcitvity extends Activity {
	/** Called when the activity is first created. */
	private Button button01;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		button01 = (Button) findViewById(R.id.button01);
		button01.setOnClickListener(new buttonListener());
	}

	class buttonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(VideoPlayAcitvity.this, PlayVideo.class);
			VideoPlayAcitvity.this.startActivity(intent);
		}
	}

}
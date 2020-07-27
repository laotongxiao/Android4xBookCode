package introduction.android.widgetDemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class RatingBarActivity extends Activity {
	private RatingBar chooseRatingBar;
	private TextView textView;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ratingbar);
		textView = (TextView) findViewById(R.id.textView1);		
		chooseRatingBar = (RatingBar) findViewById(R.id.ratingBar1);
		/* 创建RatingBar监听器 */
		chooseRatingBar
				.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
					@Override
					public void onRatingChanged(RatingBar ratingBar,float rating, boolean fromUser) {
						chooseRatingBar = (RatingBar) findViewById(R.id.ratingBar1);
						chooseRatingBar.setRating(rating);
						textView.setText("您选择了" + rating + "个星星");
					}
				});		
	}
}

package introduction.android.widgetDemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;

public class SeekBarActivity extends Activity {
	private TextView textView;
	private SeekBar seekBar;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.seekbar);
		textView = (TextView) findViewById(R.id.textView1);
		seekBar = (SeekBar) findViewById(R.id.seekBar1);
		/* 设置SeekBar监听setOnSeekBarChangeListener */
		seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			/* 拖动条停止拖动的时调用 */
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				Log.i("SeekBarActivity", "拖动停止");
			}
			/* 拖动条开始拖动的时调用 */
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				Log.i("SeekBarActivity", "开始拖动");
			}
			/* 拖动条进度改变的时调用 */
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				/* 拖动条进度改变的时调用 */
				textView.setText("当前进度为：" + progress + "%");
			}
		});
	}
}

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
		/* ����SeekBar����setOnSeekBarChangeListener */
		seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			/* �϶���ֹͣ�϶���ʱ���� */
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				Log.i("SeekBarActivity", "�϶�ֹͣ");
			}
			/* �϶�����ʼ�϶���ʱ���� */
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				Log.i("SeekBarActivity", "��ʼ�϶�");
			}
			/* �϶������ȸı��ʱ���� */
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				/* �϶������ȸı��ʱ���� */
				textView.setText("��ǰ����Ϊ��" + progress + "%");
			}
		});
	}
}

package introduction.android.batteryDemo;

import introduction.android.batteryDemo.R;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.ToggleButton;

public class BatteryDemoActivity extends Activity {
	/** Called when the activity is first created. */
	private ToggleButton button;
	private TextView text;
	BroadcastReceiver receiver = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		button = (ToggleButton) findViewById(R.id.button);
		text = (TextView) findViewById(R.id.text);

		final BroadcastReceiver receiver = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub
				String action = intent.getAction();
				if (Intent.ACTION_BATTERY_CHANGED.equals(action)) {
					int current = intent.getExtras().getInt("level");// ��ȡ��ǰ����
					int total = intent.getExtras().getInt("scale");// ��ȡ�ܵ���
					int value = current * 100 / total;
					text.setText("��ǰ������" + value + "%" + "");
				}
			}
		};
		button.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					IntentFilter filter = new IntentFilter(
							Intent.ACTION_BATTERY_CHANGED);
					registerReceiver(receiver, filter);
				} else {
					unregisterReceiver(receiver);
					text.setText("");
				}
			}
		});
	}
}
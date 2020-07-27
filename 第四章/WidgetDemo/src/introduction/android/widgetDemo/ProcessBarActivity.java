package introduction.android.widgetDemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

public class ProcessBarActivity extends Activity {
	ProgressBar progressBar;
	int i = 0;
	int progressBarMax = 0;
	/* ����Handler���� */
	Handler handler = new Handler();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.processbar);
		progressBar = (ProgressBar) findViewById(R.id.progressBar4);
		/* ��ȡ���ֵ */
		progressBarMax = progressBar.getMax();
		/* �����ڲ�������ʵ��Ч�����߳� */
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (i++ < progressBarMax) {
					// ���ù�������ǰ״ֵ̬
					progressBar.setProgress(i);
					try {
						Thread.sleep(15);

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
}

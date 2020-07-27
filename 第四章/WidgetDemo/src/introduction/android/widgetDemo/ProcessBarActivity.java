package introduction.android.widgetDemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

public class ProcessBarActivity extends Activity {
	ProgressBar progressBar;
	int i = 0;
	int progressBarMax = 0;
	/* 创建Handler对象 */
	Handler handler = new Handler();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.processbar);
		progressBar = (ProgressBar) findViewById(R.id.progressBar4);
		/* 获取最大值 */
		progressBarMax = progressBar.getMax();
		/* 匿名内部类启动实现效果的线程 */
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (i++ < progressBarMax) {
					// 设置滚动条当前状态值
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

package introduction.android.widgetDemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TabHost;

public class TabsActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab);
		// ����1�����TabHost�Ķ��󣬲����г�ʼ��setup()
		TabHost tabs = (TabHost) findViewById(R.id.tabhost);
		tabs.setup();
		// ����2��ͨ��TabHost.TabSpec����tab��һҳ��ͨ��setContent()�������ݣ�ͨ��setIndicator����ҳ�ı�ǩ
		/* ���ӵ�1��Tab */
		TabHost.TabSpec spec = tabs.newTabSpec("Tag1");
		// ���TabҪ��ʾ������
		spec.setContent(R.id.tab1);
		/* ��ʾTab3���� */
		spec.setIndicator("Tab1");
		tabs.addTab(spec);
		/* ���ӵ�2��Tab */
		spec = tabs.newTabSpec("Tag2");
		spec.setContent(R.id.tab2);// ���TabҪ��ʾ������
		/* ��ʾTab3���� */
		spec.setIndicator("Tab2");
		tabs.addTab(spec);
		/* ���ӵ�3��Tab */
		spec = tabs.newTabSpec("Tag3");
		spec.setContent(R.id.tab3);// ���TabҪ��ʾ������
		/* ��ʾTab3���� */
		spec.setIndicator("Tab3");
		tabs.addTab(spec);
		/* ����3����ͨ��setCurrentTab(index)ָ����ʾ��ҳ����0��ʼ���㡣 */
		tabs.setCurrentTab(0);
	}
}

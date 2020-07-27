package introduction.android.widgetDemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TabHost;

public class TabsActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab);
		// 步骤1：获得TabHost的对象，并进行初始化setup()
		TabHost tabs = (TabHost) findViewById(R.id.tabhost);
		tabs.setup();
		// 步骤2：通过TabHost.TabSpec增加tab的一页，通过setContent()增加内容，通过setIndicator增加页的标签
		/* 增加第1个Tab */
		TabHost.TabSpec spec = tabs.newTabSpec("Tag1");
		// 点击Tab要显示的内容
		spec.setContent(R.id.tab1);
		/* 显示Tab3内容 */
		spec.setIndicator("Tab1");
		tabs.addTab(spec);
		/* 增加第2个Tab */
		spec = tabs.newTabSpec("Tag2");
		spec.setContent(R.id.tab2);// 点击Tab要显示的内容
		/* 显示Tab3内容 */
		spec.setIndicator("Tab2");
		tabs.addTab(spec);
		/* 增加第3个Tab */
		spec = tabs.newTabSpec("Tag3");
		spec.setContent(R.id.tab3);// 点击Tab要显示的内容
		/* 显示Tab3内容 */
		spec.setIndicator("Tab3");
		tabs.addTab(spec);
		/* 步骤3：可通过setCurrentTab(index)指定显示的页，从0开始计算。 */
		tabs.setCurrentTab(0);
	}
}

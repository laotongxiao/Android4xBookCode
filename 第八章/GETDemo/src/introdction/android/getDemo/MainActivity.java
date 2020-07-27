package introdction.android.getDemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.android.activity.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
/**
 * 
 * @author wxq
 * 此代码用于初始化图形界面并设置对应监听器
 *
 */
public class MainActivity extends Activity {
    /** Called when the activity is first created. */
	private TextView textView_Get;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        textView_Get=(TextView) findViewById(R.id.TextView_Get);
        Button button_Get = (Button) findViewById(R.id.Button_Get);
		button_Get.setOnClickListener(new OnClickListener() {//给button_Get按钮设置监听器
			public void onClick(View v){				
				String httpUrl = " http://175.168.35.198:8080/android/getMessage.jsp?message=Helloworld";// 设置要访问的IP地址，读者测试时改成自己本机的ip地址，message=Helloworld是要传递的参数
				String resultData = "";// 定义一个resultData用于存储获得的数据
				URL url = null;// 定义一个URL对象		
				try {// 构造URL对象时需要使用异常处理
					url = new URL(httpUrl);//构造一个URL对象
				} catch (MalformedURLException e) {
					System.out.println(e.getMessage());//出现异常时，打印出异常信息
				}
				if (url != null) {//如果url不为空时
					try {

						HttpURLConnection urlConn = (HttpURLConnection) url
								.openConnection();// 使用HttpURLConnection打开连接
						InputStreamReader in = new InputStreamReader(urlConn
								.getInputStream());//得到读取的内容
						BufferedReader buffer = new BufferedReader(in);// 为输出创建BufferedReader
						String inputLine = null;//inputLine用于暂存读取到的数据
						while (((inputLine = buffer.readLine()) != null)) {//读取获得的数据
							resultData += inputLine + "\n";//加上"\n"实现换行
						}
						in.close();// 关闭InputStreamReader
						urlConn.disconnect();// 关闭HTTP连接
						if (resultData != null) {// 内容不为空时
							textView_Get.setText(resultData);//显示取得的内容

						} else {
							textView_Get.setText("Sorry,the content is null");//内容为空时显示信息

						}
					} catch (IOException e) {
						textView_Get.setText(e.getMessage());//出现异常时，打印出异常信息

					}
				} else {
					textView_Get.setText("url is null");// url为空时输出
				}
			}
		});
	}
}
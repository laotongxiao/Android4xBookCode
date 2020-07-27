package com.android.URLDemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.android.activity.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
	private TextView textView_HTTP;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        textView_HTTP=(TextView) findViewById(R.id.TextView_HTTP);
    	Button button_http = (Button) findViewById(R.id.Button_HTTP);		
		button_http.setOnClickListener(new OnClickListener() {//给button_http按钮设置监听器
			public void onClick(View v){//事件处理				
				String httpUrl = "http://119.118.188.105:8080/android/message.jsp";// 定义要访问的网络IP地址，读者测试时改成自己本机的ip地址
				String resultData = "";// 定义一个resultData用于存储获得的数据
				URL url = null;// 定义URL对象
				try {
					url = new URL(httpUrl); // 构造一个URL对象时需要使用异常处理
				} catch (MalformedURLException e) {
					System.out.println(e.getMessage());//打印出异常信息
				}
				if (url != null) {//如果URL不为空时
					try {//有关网络操作时，需要使用异常处理
						HttpURLConnection urlConn = (HttpURLConnection) url
								.openConnection();// 使用HttpURLConnection打开连接
						InputStreamReader in = new InputStreamReader(urlConn
								.getInputStream());// 得到读取的内容
						BufferedReader buffer = new BufferedReader(in);// 为输出创建BufferedReader
						String inputLine = null;
						while (((inputLine = buffer.readLine()) != null)){// 读取获得的数据				
							resultData += inputLine + "\n";// 加上"\n"实现换行
						}
						in.close();// 关闭InputStreamReader
						urlConn.disconnect();// 关闭HTTP连接
						if (resultData != null) {//如果获取到的数据不为空
							textView_HTTP.setText(resultData);//显示取得的内容

						} else {
							textView_HTTP.setText("Sorry,the content is null");//获取到的数据为空时，显示
						}
					} catch (IOException e) {
						textView_HTTP.setText(e.getMessage());//出现异常时，打印出异常信息
					}
				} else {
					textView_HTTP.setText("url is null");//当url为空时输出
				}
			}
		});
	}
}
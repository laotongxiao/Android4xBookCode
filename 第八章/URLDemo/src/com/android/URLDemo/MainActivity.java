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
		button_http.setOnClickListener(new OnClickListener() {//��button_http��ť���ü�����
			public void onClick(View v){//�¼�����				
				String httpUrl = "http://119.118.188.105:8080/android/message.jsp";// ����Ҫ���ʵ�����IP��ַ�����߲���ʱ�ĳ��Լ�������ip��ַ
				String resultData = "";// ����һ��resultData���ڴ洢��õ�����
				URL url = null;// ����URL����
				try {
					url = new URL(httpUrl); // ����һ��URL����ʱ��Ҫʹ���쳣����
				} catch (MalformedURLException e) {
					System.out.println(e.getMessage());//��ӡ���쳣��Ϣ
				}
				if (url != null) {//���URL��Ϊ��ʱ
					try {//�й��������ʱ����Ҫʹ���쳣����
						HttpURLConnection urlConn = (HttpURLConnection) url
								.openConnection();// ʹ��HttpURLConnection������
						InputStreamReader in = new InputStreamReader(urlConn
								.getInputStream());// �õ���ȡ������
						BufferedReader buffer = new BufferedReader(in);// Ϊ�������BufferedReader
						String inputLine = null;
						while (((inputLine = buffer.readLine()) != null)){// ��ȡ��õ�����				
							resultData += inputLine + "\n";// ����"\n"ʵ�ֻ���
						}
						in.close();// �ر�InputStreamReader
						urlConn.disconnect();// �ر�HTTP����
						if (resultData != null) {//�����ȡ�������ݲ�Ϊ��
							textView_HTTP.setText(resultData);//��ʾȡ�õ�����

						} else {
							textView_HTTP.setText("Sorry,the content is null");//��ȡ��������Ϊ��ʱ����ʾ
						}
					} catch (IOException e) {
						textView_HTTP.setText(e.getMessage());//�����쳣ʱ����ӡ���쳣��Ϣ
					}
				} else {
					textView_HTTP.setText("url is null");//��urlΪ��ʱ���
				}
			}
		});
	}
}
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
 * �˴������ڳ�ʼ��ͼ�ν��沢���ö�Ӧ������
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
		button_Get.setOnClickListener(new OnClickListener() {//��button_Get��ť���ü�����
			public void onClick(View v){				
				String httpUrl = " http://175.168.35.198:8080/android/getMessage.jsp?message=Helloworld";// ����Ҫ���ʵ�IP��ַ�����߲���ʱ�ĳ��Լ�������ip��ַ��message=Helloworld��Ҫ���ݵĲ���
				String resultData = "";// ����һ��resultData���ڴ洢��õ�����
				URL url = null;// ����һ��URL����		
				try {// ����URL����ʱ��Ҫʹ���쳣����
					url = new URL(httpUrl);//����һ��URL����
				} catch (MalformedURLException e) {
					System.out.println(e.getMessage());//�����쳣ʱ����ӡ���쳣��Ϣ
				}
				if (url != null) {//���url��Ϊ��ʱ
					try {

						HttpURLConnection urlConn = (HttpURLConnection) url
								.openConnection();// ʹ��HttpURLConnection������
						InputStreamReader in = new InputStreamReader(urlConn
								.getInputStream());//�õ���ȡ������
						BufferedReader buffer = new BufferedReader(in);// Ϊ�������BufferedReader
						String inputLine = null;//inputLine�����ݴ��ȡ��������
						while (((inputLine = buffer.readLine()) != null)) {//��ȡ��õ�����
							resultData += inputLine + "\n";//����"\n"ʵ�ֻ���
						}
						in.close();// �ر�InputStreamReader
						urlConn.disconnect();// �ر�HTTP����
						if (resultData != null) {// ���ݲ�Ϊ��ʱ
							textView_Get.setText(resultData);//��ʾȡ�õ�����

						} else {
							textView_Get.setText("Sorry,the content is null");//����Ϊ��ʱ��ʾ��Ϣ

						}
					} catch (IOException e) {
						textView_Get.setText(e.getMessage());//�����쳣ʱ����ӡ���쳣��Ϣ

					}
				} else {
					textView_Get.setText("url is null");// urlΪ��ʱ���
				}
			}
		});
	}
}
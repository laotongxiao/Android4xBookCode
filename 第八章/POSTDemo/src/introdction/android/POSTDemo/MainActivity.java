package introdction.android.POSTDemo;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

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
	private TextView textView_Post;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        textView_Post=(TextView) findViewById(R.id.TextView_Post);
        Button button_Post = (Button) findViewById(R.id.Button_Post);		
		button_Post.setOnClickListener(new OnClickListener() {//��button_Post��ť���ü�����
			public void onClick(View v){				
				String httpUrl = "http://175.168.35.198:8080/android/getMessage.jsp";//����һ��Ҫ���ʵ�����IP��ַ�����߲���ʱ�ĳ��Լ�������ip��ַ
				String resultData = "";//����һ��resultData���ڴ洢��õ�����
				URL url = null;//����һ��URL����
				try{//����URL����ʱ��Ҫ�쳣����			
					url = new URL(httpUrl);//����һ��URL���� 
				}
				catch (MalformedURLException e){
					System.out.println(e.getMessage());//�����쳣ʱ����ӡ���쳣��Ϣ
				}
				if (url != null){//���URL����Ϊ��ʱ
					try{//�ڽ����������ݲ���ʱ����Ҫ���쳣�������				
						HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();// ʹ��HttpURLConnection������				
						urlConn.setDoOutput(true);//post����,��Ҫ����Ϊtrue
						urlConn.setDoInput(true);		        
						urlConn.setRequestMethod("POST");//������POST��ʽ��ȡ����		        
						urlConn.setUseCaches(false);//����Post������ʹ�û���
						urlConn.setInstanceFollowRedirects(true);		        
						urlConn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");// ���ñ������ӵ�Content-type������Ϊapplication/x-www-form-urlencoded��
				        // ���ӣ���postUrl.openConnection()���˵����ñ���Ҫ��connect֮ǰ��ɣ�Ҫע�����connection.getOutputStream�������Ľ���connect��
						urlConn.connect();				
				        DataOutputStream out = new DataOutputStream(urlConn.getOutputStream());	//����DataOutputStream������	        
				        String content = "message=" + URLEncoder.encode("HelloWorld", "gb2312");//����Ҫ���ݵĲ��������뷽ʽ		        
				        out.writeBytes(content); //��Ҫ���ݵ���Ϣд������		        
				        out.flush();//ˢ�������
				        out.close();//�ر������	        
				        BufferedReader reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));//��ȡ����
						String inputLine = null;				
						while (((inputLine = reader.readLine()) != null)){//��ȡ��õ�����							
							resultData += inputLine + "\n";//��ÿ�е�ĩβ����"\n"��ʵ�ֻ���
						}		  
						reader.close();				
						urlConn.disconnect();//�ر�HTTP����				
						if ( resultData != null ){
							textView_Post.setText(resultData);//��ʾȡ�õ�����
						}
						else {
							textView_Post.setText("Sorry,the content is null");//����ȡ��������Ϊ��ʱ����ʾ��Ϣ
						}
					}
					catch (IOException e){
						textView_Post.setText(e.getMessage());//�����쳣ʱ������쳣��Ϣ
					}
				}
				else{
					textView_Post.setText("url is null");//��URL����Ϊ��ʱ����ӡ��Ϣ
				}		
			}
		});
	}
}
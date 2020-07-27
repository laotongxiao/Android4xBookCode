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
 * 此代码用于初始化图形界面并设置对应监听器
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
		button_Post.setOnClickListener(new OnClickListener() {//给button_Post按钮设置监听器
			public void onClick(View v){				
				String httpUrl = "http://175.168.35.198:8080/android/getMessage.jsp";//定义一个要访问的网络IP地址，读者测试时改成自己本机的ip地址
				String resultData = "";//定义一个resultData用于存储获得的数据
				URL url = null;//声明一个URL对象
				try{//构造URL对象时需要异常处理			
					url = new URL(httpUrl);//构造一个URL对象 
				}
				catch (MalformedURLException e){
					System.out.println(e.getMessage());//出现异常时，打印出异常信息
				}
				if (url != null){//如果URL对象不为空时
					try{//在进行网络数据操作时，需要有异常处理操作				
						HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();// 使用HttpURLConnection打开连接				
						urlConn.setDoOutput(true);//post请求,需要设置为true
						urlConn.setDoInput(true);		        
						urlConn.setRequestMethod("POST");//设置以POST方式获取数据		        
						urlConn.setUseCaches(false);//设置Post请求不能使用缓存
						urlConn.setInstanceFollowRedirects(true);		        
						urlConn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");// 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
				        // 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，要注意的是connection.getOutputStream会隐含的进行connect。
						urlConn.connect();				
				        DataOutputStream out = new DataOutputStream(urlConn.getOutputStream());	//定义DataOutputStream流对象	        
				        String content = "message=" + URLEncoder.encode("HelloWorld", "gb2312");//设置要传递的参数及编码方式		        
				        out.writeBytes(content); //将要传递的信息写入流中		        
				        out.flush();//刷新输出流
				        out.close();//关闭输出流	        
				        BufferedReader reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));//获取数据
						String inputLine = null;				
						while (((inputLine = reader.readLine()) != null)){//读取获得的数据							
							resultData += inputLine + "\n";//在每行的末尾加上"\n"来实现换行
						}		  
						reader.close();				
						urlConn.disconnect();//关闭HTTP连接				
						if ( resultData != null ){
							textView_Post.setText(resultData);//显示取得的内容
						}
						else {
							textView_Post.setText("Sorry,the content is null");//当获取到的数据为空时，显示信息
						}
					}
					catch (IOException e){
						textView_Post.setText(e.getMessage());//出现异常时，输出异常信息
					}
				}
				else{
					textView_Post.setText("url is null");//当URL对象为空时，打印信息
				}		
			}
		});
	}
}
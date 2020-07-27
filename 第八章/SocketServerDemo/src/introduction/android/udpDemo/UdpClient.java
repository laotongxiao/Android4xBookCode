package introduction.android.udpDemo;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class UdpClient extends Activity {
	private Button btn_listen;	
	private EditText et;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);		
		et=(EditText)findViewById(R.id.editText1);		
		btn_listen = (Button) findViewById(R.id.btn_listen);
		btn_listen.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				et.setText("开始发送数据...");
				new ServerThread().start();
			}
		});
	}

	class ServerThread extends Thread {
		public void run() {
			try {
				//首先创建一个DatagramSocket对象
				DatagramSocket socket = new DatagramSocket(12344);
				//创建一个InetAddree，自己测试的时候要设置成自己本机的IP地址
				//InetAddress serverAddress = InetAddress.getByName("192.168.1.101");
				//InetAddress serverAddress = InetAddress.getByName("175.168.46.96");
				InetAddress serverAddress = InetAddress.getByName("169.254.31.8");
				//InetAddress serverAddress = InetAddress.getByName("10.0.2.15");
				while(true){
					String str = "Hi, this is the string from the Android Client!";
					byte data [] = str.getBytes();
					//创建一个DatagramPacket对象，并指定要讲这个数据包发送到网络当中的哪个地址，以及端口号
					DatagramPacket packet = new DatagramPacket(data,data.length,serverAddress,12345);
					//调用socket对象的send方法，发送数据
					socket.send(packet);
					Log.d("server", "sending...");
					Thread.sleep(1000);
				}				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

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
				et.setText("��ʼ��������...");
				new ServerThread().start();
			}
		});
	}

	class ServerThread extends Thread {
		public void run() {
			try {
				//���ȴ���һ��DatagramSocket����
				DatagramSocket socket = new DatagramSocket(12344);
				//����һ��InetAddree���Լ����Ե�ʱ��Ҫ���ó��Լ�������IP��ַ
				//InetAddress serverAddress = InetAddress.getByName("192.168.1.101");
				//InetAddress serverAddress = InetAddress.getByName("175.168.46.96");
				InetAddress serverAddress = InetAddress.getByName("169.254.31.8");
				//InetAddress serverAddress = InetAddress.getByName("10.0.2.15");
				while(true){
					String str = "Hi, this is the string from the Android Client!";
					byte data [] = str.getBytes();
					//����һ��DatagramPacket���󣬲�ָ��Ҫ��������ݰ����͵����統�е��ĸ���ַ���Լ��˿ں�
					DatagramPacket packet = new DatagramPacket(data,data.length,serverAddress,12345);
					//����socket�����send��������������
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

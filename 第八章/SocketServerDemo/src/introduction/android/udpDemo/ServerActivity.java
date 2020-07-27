package introduction.android.udpDemo;

import introduction.android.udpDemo.R;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * �������˴��룬���ڼ����ͻ���
 * @author wxq
 * 
 */
public class ServerActivity extends Activity {
	/** Called when the activity is first created. */
	private Button btn_listen;	
	private EditText et;
	private Handler messageHandler;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);		
		et=(EditText)findViewById(R.id.editText1);
		messageHandler=new Handler(){

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				et.setText((String)msg.obj);
				super.handleMessage(msg);
			}
			
		};

		btn_listen = (Button) findViewById(R.id.btn_listen);
		btn_listen.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				et.setText("��ʼ����...");
				new ServerThread().start();
			}
		});
	}

	class ServerThread extends Thread {
		public void run() {
			try {
				

				// ����һ��DatagramSocket���󣬲�ָ�������Ķ˿ں�
				DatagramSocket socket = new DatagramSocket(12345);
				byte data[] = new byte[1024];
				// ����һ���յ�DatagramPacket����
				DatagramPacket packet = new DatagramPacket(data, data.length);
				// ʹ��receive�������տͻ��������͵�����
				while(true){
				socket.receive(packet);
				String result = new String(packet.getData(),
						packet.getOffset(), packet.getLength());
			//	System.out.println(result);
				Log.d("server", result);
				Message message=Message.obtain();
				message.obj=result;
				messageHandler.sendMessage(message);
				}
				//et.setText(result);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
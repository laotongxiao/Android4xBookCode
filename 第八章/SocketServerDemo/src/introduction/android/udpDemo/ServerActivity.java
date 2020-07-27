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
 * 服务器端代码，用于监听客户端
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
				et.setText("开始监听...");
				new ServerThread().start();
			}
		});
	}

	class ServerThread extends Thread {
		public void run() {
			try {
				

				// 创建一个DatagramSocket对象，并指定监听的端口号
				DatagramSocket socket = new DatagramSocket(12345);
				byte data[] = new byte[1024];
				// 创建一个空的DatagramPacket对象
				DatagramPacket packet = new DatagramPacket(data, data.length);
				// 使用receive方法接收客户端所发送的数据
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
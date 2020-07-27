package introduction.android.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Server {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 创建一个DatagramSocket对象，并指定监听的端口号
		DatagramSocket socket;		
		try {
			socket = new DatagramSocket(12345);	
			byte data[] = new byte[1024];
			// 创建一个空的DatagramPacket对象
			DatagramPacket packet = new DatagramPacket(data, data.length);
			// 使用receive方法接收客户端所发送的数据
			while(true){
			try {
				socket.receive(packet);
				String result = new String(packet.getData(),
				packet.getOffset(), packet.getLength());
				System.out.println(result);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			}
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}

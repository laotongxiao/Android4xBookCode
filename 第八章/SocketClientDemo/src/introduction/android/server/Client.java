package introduction.android.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
/**
 * �ͻ��˴��룬���ڷ�����Ϣ
 * @author wxq
 *
 */
public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			//���ȴ���һ��DatagramSocket����
			DatagramSocket socket = new DatagramSocket(12344);
			//����һ��InetAddree���Լ����Ե�ʱ��Ҫ���ó��Լ�������IP��ַ
			//InetAddress serverAddress = InetAddress.getByName("10.0.2.15");
			//InetAddress serverAddress = InetAddress.getByName("175.168.46.96");
			//InetAddress serverAddress = InetAddress.getByName("169.254.31.8");
			InetAddress serverAddress = InetAddress.getByName("127.0.0.1");
			while(true){
			String str = "Android";
			byte data [] = str.getBytes();
			//����һ��DatagramPacket���󣬲�ָ��Ҫ��������ݰ����͵����統�е��ĸ���ַ���Լ��˿ں�
			DatagramPacket packet = new DatagramPacket(data,data.length,serverAddress,12345);
			//����socket�����send��������������
			socket.send(packet);
			System.out.println("already send");
			Thread.sleep(1000);
			}
			//����һ��DatagramSocket���󣬲�ָ�������Ķ˿ں�
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//	Socket socket;
//	BufferedReader in;
//	PrintWriter out;
//	try {
//		socket=new Socket("192.168.0.99",1234);
//		in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
//		out=new PrintWriter(socket.getOutputStream(),true);
//	} catch (UnknownHostException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	} 
//	
//	ServerSocket server=null;	
//	try {
//		server=new ServerSocket(1234);
//		Socket socket=server.accept();
//		in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
//		out=new PrintWriter(socket.getOutputStream(),true);
//		//���������������......
//		in.close();
//		out.close();
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	} 
		
		

	
	}

}

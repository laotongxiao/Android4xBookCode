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
 * 客户端代码，用于发送信息
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
			//首先创建一个DatagramSocket对象
			DatagramSocket socket = new DatagramSocket(12344);
			//创建一个InetAddree，自己测试的时候要设置成自己本机的IP地址
			//InetAddress serverAddress = InetAddress.getByName("10.0.2.15");
			//InetAddress serverAddress = InetAddress.getByName("175.168.46.96");
			//InetAddress serverAddress = InetAddress.getByName("169.254.31.8");
			InetAddress serverAddress = InetAddress.getByName("127.0.0.1");
			while(true){
			String str = "Android";
			byte data [] = str.getBytes();
			//创建一个DatagramPacket对象，并指定要讲这个数据包发送到网络当中的哪个地址，以及端口号
			DatagramPacket packet = new DatagramPacket(data,data.length,serverAddress,12345);
			//调用socket对象的send方法，发送数据
			socket.send(packet);
			System.out.println("already send");
			Thread.sleep(1000);
			}
			//创建一个DatagramSocket对象，并指定监听的端口号
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
//		//进行输入输出操作......
//		in.close();
//		out.close();
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	} 
		
		

	
	}

}

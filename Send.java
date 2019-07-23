package CommuV3;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;

import javax.imageio.ImageIO;
import javax.swing.JTextField;

public class Send extends Thread implements config{
 
		@SuppressWarnings("resource")
		public void run() {
	    	SocketAddress localAddr = new InetSocketAddress(local_socket,local_null_duan); 
			DatagramSocket dSender = null;//2.�������͵�Socket����
			try {
				dSender = new DatagramSocket(localAddr);
			} catch (SocketException e) {
				e.printStackTrace();
			}  
			while(true){        
				byte buffer[] = null;
				if(Ui.image==null)continue;
				buffer = To_byte(Ui.image);
				SocketAddress destAdd = new InetSocketAddress(Send_socket,Receiver_duan); //�������ݵ�Ŀ���ַ�Ͷ˿�     			
				DatagramPacket dp = new DatagramPacket(buffer, buffer.length, destAdd); //����Ҫ���͵����ݰ�,ָ������,ָ��Ŀ���ַ 
				try {
					dSender.send(dp);
				} catch (IOException e) {
					e.printStackTrace();
				}         
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
	    }		
		public byte[] To_byte(BufferedImage i) {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			try {
				ImageIO.write(i, "jpg",bos);
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			byte[] bytes = bos.toByteArray();
			return bytes;
		}
	}


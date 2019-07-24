package CommuV3;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
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
		public JTextField field;
		public void run() {
	    	SocketAddress localAddr = new InetSocketAddress(local_socket,local_null_duan); 
			DatagramSocket dSender = null;//2.创建发送的Socket对象
			try {
				dSender = new DatagramSocket(localAddr);
			} catch (SocketException e) {
				e.printStackTrace();
			}  
			while(true){        
				byte buffer[] = new byte[40000];
				if(Ui.image==null)continue;
				if(Ui.flag==2) {
					ByteArrayOutputStream bos = new ByteArrayOutputStream();
					bos.write(2);
					bos.write(field.getText().getBytes());
					buffer = bos.toByteArray();
					Ui.flag=1;
				}
				else if(Ui.flag==3) {
					ByteArrayOutputStream bos = new ByteArrayOutputStream();
					bos.write(3);
				}
				else if(Ui.flag==4) {
					buffer[0]=4;
					buffer[1]=getrow();
					buffer[2]=getcolumn();
					Ui.flag=1;
				}
				else {
					buffer = To_byte(Ui.image);
				}								
     			SocketAddress destAdd = new InetSocketAddress(Send_socket,Receiver_duan); //发送数据的目标地址和端口     			
				DatagramPacket dp = new DatagramPacket(buffer, buffer.length, destAdd); //创建要发送的数据包,指定内容,指定目标地址 
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
			bos.write(1);
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


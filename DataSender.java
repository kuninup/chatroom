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

public class DataSender extends Thread implements Config{
 
		@SuppressWarnings("resource")
		public JTextField field;
		@SuppressWarnings("resource")
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
				if(MainUI.image==null)continue;
				if(MainUI.flag==2) {
					ByteArrayOutputStream bos = new ByteArrayOutputStream();
					bos.write(2);
					try {
						bos.write(field.getText().getBytes());
					} catch (IOException e) {
						e.printStackTrace();
					}
					buffer = bos.toByteArray();
					MainUI.flag=1;
				}
				else if(MainUI.flag==3) {
					ByteArrayOutputStream bos = new ByteArrayOutputStream();
					bos.write(3);
				}
				else if(MainUI.flag==4) {
					buffer[0]=4;
					//buffer[1]=getrow();
					//buffer[2]=getcolumn();
					MainUI.flag=1;
				}
				else {
					buffer = To_byte(MainUI.image);
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


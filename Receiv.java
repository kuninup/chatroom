package CommuV3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;

import org.omg.CORBA.portable.InputStream;
import org.omg.CORBA.portable.OutputStream;

import Chess.Chess;

public class Receiv extends Thread implements config{     
	    public Graphics g;
	    public JTextArea area;
	    BufferedImage buff=null;
	    
		public Receiv(Graphics g) {
			super();
			this.g = g;
		}
			@SuppressWarnings("resource")
		public void run() {	
		    SocketAddress localAddr = new InetSocketAddress(local_socket, local_receive_duan);//���նˣ��ڱ������յĻ�ַ�͵ȴ��Ķ˿ں� 
			DatagramSocket recvSocket = null;//���յķ�����UDP�˿�   
			try {
				recvSocket = new DatagramSocket(localAddr);
			} catch (SocketException e1) {
				e1.printStackTrace();
			} 
			while(true){   
				byte[] buffer  = new byte[40000];
				try {
					Thread.sleep(10);
				}
				catch(Exception e){
					e.printStackTrace();
				} 
				DatagramPacket packet = new DatagramPacket(buffer, buffer.length);//4.�����������ݰ�����    		
				try {
					recvSocket.receive(packet);//6.�ڴ˵ȴ����նԷ�����UDP��    
				} catch (IOException e) {
					e.printStackTrace();
				}    
				byte[] bytes =packet.getData();
				byte b0 = bytes[0];
				byte[] bytes2 = null;
				bytes2 = new byte[bytes.length-1];
				System.arraycopy(bytes, 1, bytes2, 0, bytes2.length);
				action(b0,bytes2);
				
				
				SocketAddress address = packet.getSocketAddress(); //�õ����ͷ���IP�Ͷ˿�
				//g.drawImage(bu, iamge_precious_width, 0, null);
				//String msg=new String(packet.getData()).trim();    ת�����յ�������Ϊ�ַ���         
				//System.out.println("recv is:"+msg+" from:"+address);  ���յ���,��ӡ���յ������ݳ���  
				}
			}
	   public  BufferedImage To_Image(byte[] buffer)  {
		   ByteArrayInputStream bis = new ByteArrayInputStream(buffer);
		   BufferedImage recvImg = null;
		   try {
			   recvImg = ImageIO.read(bis);
		   } catch (IOException e) {

			   e.printStackTrace();
		   }
		   return recvImg;
	   	}
		public  void action(byte b0,byte[] bytes2) {			
			if(b0 == 1) {	
				buff=To_Image(bytes2);
				g.drawImage(buff,image_fixed_x,400-iamge_precious_height, iamge_precious_width, iamge_precious_height,null);
			}
			if(b0 == 2) {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	    		area.append("OUTER  ");
	    		area.append(df.format(new Date()));
	    		area.append("  "+new String(bytes2));	
	    		area.append("\n");
			}
			if(b0 == 3) {	
				
			}
			if(b0 == 4) {
				int row=bytes2[0];
				int column=bytes2[1];
				int win=bytes2[2];
				
			}
		}
	  
	}

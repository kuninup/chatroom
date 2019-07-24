package CommuV3;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import Chess.Chess;


public class Ceshi  implements config{
public Graphics g;
public static void main(String [] args) {
	Ceshi c=new Ceshi();
	c.action();
}

public void action() {
	JFrame jf=new JFrame("V1");
	jf.setSize(frame_width, frame_height);
	jf.setDefaultCloseOperation(3);
	jf.setLayout(null);
	jf.setResizable(false);
	jf.setLocationRelativeTo(null);
	jf.setVisible(true);
	g=jf.getGraphics();
	new Thread() {
		public void run() {
			while(true) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Image bu = new ImageIcon("cuit1.jpg").getImage();
				BufferedImage buff=new BufferedImage(iamge_precious_width,iamge_precious_height,BufferedImage.TYPE_INT_RGB);
				Graphics buffg=buff.getGraphics();
				buffg.drawImage(bu, 0, 0, null);
				byte [] b = null;
				try {
					 b=To_byte(buff);
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					buff=To_Image(b);
				} catch (IOException e) {
					e.printStackTrace();
				}
				g.drawImage(buff, 0, 0, null);
			}
		}
	}.start();
}
public  BufferedImage To_Image(byte[] buffer) throws IOException {
	   ByteArrayInputStream bis = new ByteArrayInputStream(buffer);
	   BufferedImage recvImg = ImageIO.read(bis);
	   return recvImg;
}
public byte[] To_byte(BufferedImage i) throws IOException {
	ByteArrayOutputStream bos = new ByteArrayOutputStream();
	ImageIO.write(i, "jpg", bos);
	byte[] bytes = bos.toByteArray();
	return bytes;
}


}


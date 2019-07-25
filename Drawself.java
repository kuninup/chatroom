package CommuV3;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;

public class Drawself extends Thread implements Config{

	public Graphics g;

	public Drawself(Graphics g) {
		super();
		this.g = g;
	}

	public void run() {
		Webcam webcam=Webcam.getDefault();
	    webcam.setViewSize(WebcamResolution.VGA.getSize());
	    webcam.open();
		while(true) {
			BufferedImage image=webcam.getImage();
			MainUI.image=image;
			g.drawImage(image,image_fixed_x,frame_height-20-iamge_precious_height,iamge_precious_width,iamge_precious_height,null);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}	
	}
}

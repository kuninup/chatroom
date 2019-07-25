package CommuV3;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;

import Chess.Chess;

public class MainUI extends JFrame implements Config{
	public static BufferedImage image=new BufferedImage(frame_width,frame_width,BufferedImage.TYPE_INT_RGB);
	public static JTextField text=new JTextField();
	public static int flag=0;
	public static String path="";
	public static int chess_color=0;
	public static String face="楷体";
	public static int style=Font.PLAIN;
	public static int size=1;
	public static Webcam webcam;
    public static void main(String[] args) {
    	    
    	    MainUI mUI=new MainUI();
	        mUI.open();
	        mUI.init();
	}
	public void open() {
		
	}
    public void init() {
		JFrame jf=new JFrame("V1");
		jf.setSize(frame_width, frame_height);
		jf.setDefaultCloseOperation(3);
		jf.setLayout(null);
		jf.setResizable(false);
		jf.setLocationRelativeTo(null);
		
		//调节框
		JSlider js_local=new JSlider(0,100,0);
		js_local.setBounds(image_fixed_x, 0, 200, 100);
		jf.add(js_local);
		JSlider js_receive=new JSlider(0,100,0);
		js_receive.setBounds(image_fixed_x, 350, 200, 100);
		jf.add(js_receive);
		//文本输入区
		text.setBounds(20, 700,text_width,text_height);
		jf.add(text);
		//文本显示区
		JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(20,20,530,600);
        jf.add(scrollPane_1);                
        JTextArea textArea = new JTextArea();
        textArea.setBorder(null);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        scrollPane_1.setViewportView(textArea);

        
		//按钮
		JButton send_button=new JButton("Send");
		send_button.setBounds(450, 700, send_button_width, send_button_height);
		send_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				flag=1;
			}	          
		});
		jf.add(send_button);
		
		//文件
		JButton file_button=new JButton("FileSend");
		file_button.setBounds(220,660, 100, 40);
		file_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Filechoose file=new Filechoose();
				file.choose();
			}	          
		});
		jf.add(file_button);
		//游戏
		JButton game_button=new JButton("game");
		game_button.setBounds(320, 660, 100,40);
		game_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				Chess c=new Chess();
				c.fengUI();
			}	          
		});
		jf.add(game_button);
		//颜色
		JButton text_color_button=new JButton();
		text_color_button.setBackground(Color.black);
		text_color_button.setBounds(20,680, 20, 20);
		text_color_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
                JFrame color_choose_jf=new JFrame("Font-color");
                color_choose_jf.setDefaultCloseOperation(2);
                color_choose_jf.setSize(100,140);
                
                JButton jb=(JButton)e.getSource();
                Point p=jb.getLocationOnScreen();
                double x=p.getX();
                double y=p.getY();
                color_choose_jf.setLocation((int)(x),(int)(y)-140);
                color_choose_jf.setLayout(new FlowLayout(FlowLayout.CENTER));
                color_choose_jf.setResizable(false);
                Color[] colors=new Color[24];
                for(int i=0;i<=23;i++) {
                	colors[i]=new Color(1);
                }
                colors[0]=Color.black;
                colors[1]=Color.blue;
                colors[2]=Color.cyan;
                colors[3]=Color.DARK_GRAY;
                colors[4]=Color.gray;
                colors[5]=Color.green;
                colors[6]=Color.LIGHT_GRAY;
                colors[7]=Color.magenta;
                colors[8]=Color.orange;
                colors[9]=Color.pink;
                colors[10]=Color.red;
                colors[11]=Color.white;
                colors[12]=Color.yellow;
                JButton[] color_button=new JButton[24];
                for(int i=0;i<=23;i++) {
                	color_button[i]=new JButton();
                	color_button[i].addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
						     Color color=((JButton)e.getSource()).getBackground();
						     text.setForeground(color);
						     textArea.setIgnoreRepaint(true);
						     textArea.setForeground(color);
							 text_color_button.setBackground(color);
						}
                		
                	});
                	color_button[i].setBackground(colors[i]);
                	color_button[i].setPreferredSize(new Dimension(20,20));
                	color_choose_jf.add(color_button[i]);
                }
                color_choose_jf.setVisible(true);         
			}		
		});
		jf.add(text_color_button);
		jf.setVisible(true);
		
		Drawself mWeb=new Drawself(jf.getGraphics());
		mWeb.start();
		DataHandle r=new DataHandle(jf.getGraphics());
		r.start();
		DataSender s=new DataSender();
		s.start();
		TextShow record=new TextShow(textArea,text);
		record.start();
		
		
	}

}

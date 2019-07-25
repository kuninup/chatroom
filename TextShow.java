package CommuV3;

import java.util.Date;
import java.text.SimpleDateFormat;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import Chess.Chess_config;

public class TextShow extends Thread implements Config{
    public JTextArea area;
    public JTextField field;

	public TextShow(JTextArea area, JTextField field) {
		super();
		this.area = area;
		this.field = field;
	}

	public void run() {
	       while(true) {
	    	   try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	    	if(MainUI.flag==1) {	    		   
	    		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	    		area.append("MINE  ");
	    		area.append(df.format(new Date()));
	    		area.append("  "+field.getText());	
	    		area.append("\n");
	    		MainUI.flag=0;
	    		MainUI.text.setText("");
	    	}
	       }
	}

}

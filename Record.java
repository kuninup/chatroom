package CommuV3;

import java.util.Date;
import java.text.SimpleDateFormat;

import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Record extends Thread implements config{
    public JTextArea area;
    public JTextField field;

	public Record(JTextArea area, JTextField field) {
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
	    	if(Ui.flag==1) {	    		   
	    		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	    		area.append("MINE  ");
	    		area.append(df.format(new Date()));
	    		area.append("  "+field.getText());	
	    		area.append("\n");
	    		Ui.flag=0;
	    		Ui.text.setText("");
	    	}
	       }
	}

}

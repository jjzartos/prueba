package ti.snfco.NominaYCapitalHumano.service;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;

import com.jidesoft.utils.SwingWorker;

public class ProgressBar extends SwingWorker<Integer, String>{

	private JLabel lbl;
	private JProgressBar bar;
	private JTextArea area;	
	
	
	
	
	public ProgressBar(JLabel lbl, JProgressBar bar, JTextArea area) {
		super();
		this.lbl = lbl;
		this.bar = bar;
		this.area = area;
	}




	protected Integer doInBackground() throws Exception {
		getBar().setIndeterminate(true);
		
		
		getBar().setIndeterminate(false);
		return 0;
	}




	public JLabel getLbl() {
		return lbl;
	}




	public void setLbl(JLabel lbl) {
		this.lbl = lbl;
	}




	public JProgressBar getBar() {
		return bar;
	}




	public void setBar(JProgressBar bar) {
		this.bar = bar;
	}




	public JTextArea getArea() {
		return area;
	}




	public void setArea(JTextArea area) {
		this.area = area;
	}



}

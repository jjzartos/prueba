package ti.snfco.NominaYCapitalHumano.service;

import java.io.Serializable;

import javax.swing.JInternalFrame;

import org.slf4j.LoggerFactory;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JEditorPane;

public class InternalProcesos extends JInternalFrame implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(InternalProcesos.class);
	JEditorPane editorPane = new JEditorPane();

	

	public InternalProcesos() {
		setBounds(400, 150, 522, 325);
		getContentPane().setLayout(null);
		editorPane.setBounds(10, 11, 492, 279);
		
		getContentPane().add(editorPane);
		

	}
	
	

}

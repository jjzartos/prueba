package ti.snfco.NominaYCapitalHumano.service;

import java.awt.BorderLayout;
import java.awt.Label;
import java.awt.Window;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.AbstractButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;



public class Utilidades {
 
	final Float [] valores = new Float[] {
			-.3f,0f,.6f,1.0f
	};
	final Label[] rotulo = new Label[valores.length];
	final ProgressBar[] pbs = new ProgressBar[valores.length];
	
	//encriptar password MD5
	 public static String get_md5(String CadenaOriginal){
		 //System.out.println("cadenaOriginal:" + CadenaOriginal);
	        String md5="";
	        try {
	            if (!CadenaOriginal.equalsIgnoreCase("")) {
	                MessageDigest md = MessageDigest.getInstance("MD5");
	                md.reset();
	                md.update(CadenaOriginal.getBytes());
	                byte bytes[] = md.digest();
	                StringBuilder sb = new StringBuilder();
	                for (int i = 0; i < bytes.length; i++) {
	                    String hex = Integer.toHexString(0xff & bytes[i]);
	                    if (hex.length() == 1) {
	                        sb.append('0');
	                    }
	                    sb.append(hex);
	                }
	                md5 = sb.toString();
	                //System.out.println("md5:" + md5);
	            }
	        } catch (NoSuchAlgorithmException e) {
	            md5 = "Error inesperado";
	        }
	        return md5;
	    }
	 
	 
	 
	 //metodo para el tiempo de busqueda
	//Este es el evento que se ejecuta cuando un JFrame se carga
	  public static void windowOpened(ActionEvent e){
		  final long SLEEP_TIME = 1 * 1000;
	      SwingWorker<Void, Void> mySwingWorker = new SwingWorker<Void, Void>(){
	         protected Void doInBackground() throws Exception {

	            
	            Thread.sleep(SLEEP_TIME);
	            return null;
	         }
	      };
	      

	      Window win = SwingUtilities.getWindowAncestor((AbstractButton)e.getSource());
	      final JDialog dialog = new JDialog(win, "Buscando", ModalityType.APPLICATION_MODAL);

	      mySwingWorker.addPropertyChangeListener(new PropertyChangeListener() {

	         public void propertyChange(PropertyChangeEvent evt) {
	            if (evt.getPropertyName().equals("state")) {
	               if (evt.getNewValue() == SwingWorker.StateValue.DONE) {
	                  dialog.dispose();
	               }
	            }
	         }
	      });
	      mySwingWorker.execute();

	      JProgressBar progressBar = new JProgressBar();
	      progressBar.setIndeterminate(true);
	      JPanel panel = new JPanel(new BorderLayout());
	      panel.add(progressBar, BorderLayout.CENTER);
	      panel.add(new JLabel("Por favor espere..."), BorderLayout.PAGE_START);
	      dialog.getContentPane().add(panel);
	      dialog.pack();
	      dialog.setLocationRelativeTo(win);
	      dialog.setVisible(true);
		  
	  }
	 
		 //metodo para el tiempo de busqueda
		//Este es el evento que se ejecuta cuando un JFrame se carga
		  public static void windowOpenede(ActionEvent e){
			  final long SLEEP_TIME = 14 * 1000;
		      SwingWorker<Void, Void> mySwingWorker = new SwingWorker<Void, Void>(){
		         protected Void doInBackground() throws Exception {
		        	InternalFrameCatalogos catalogos = new InternalFrameCatalogos();	
		        	catalogos.updateSalario();
		        	catalogos.mostrarDatosPuestos("");
		            // mimic some long-running process here...
		            Thread.sleep(SLEEP_TIME);
		            return null;
		         }
		      };
		      

		      Window win = SwingUtilities.getWindowAncestor((AbstractButton)e.getSource());
		      final JDialog dialog = new JDialog(win, "Actualizando", ModalityType.APPLICATION_MODAL);

		      mySwingWorker.addPropertyChangeListener(new PropertyChangeListener() {

		         public void propertyChange(PropertyChangeEvent evt) {
		            if (evt.getPropertyName().equals("state")) {
		               if (evt.getNewValue() == SwingWorker.StateValue.DONE) {
		                  dialog.dispose();
		               }
		            }
		         }
		      });
		      mySwingWorker.execute();

		      JProgressBar progressBar = new JProgressBar();
		      progressBar.setIndeterminate(true);
		      JPanel panel = new JPanel(new BorderLayout());
		      panel.add(progressBar, BorderLayout.CENTER);
		      panel.add(new JLabel("Por favor espere..."), BorderLayout.PAGE_START);
		      dialog.getContentPane().add(panel);
		      dialog.pack();
		      dialog.setLocationRelativeTo(win);
		      dialog.setVisible(true);
			  
		  }
	
}
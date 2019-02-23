package ti.snfco.NominaYCapitalHumano.service;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.TextUI;

import org.slf4j.LoggerFactory;
import java.awt.SystemColor;
import javax.swing.SwingConstants;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(MainFrame.class);
	private JPanel contentPane;
	public static JPasswordField passwordField;
	public static TextField textFieldUser;

	
	public static void main(String[] args) {
		
		try {
//			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
//			UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
//			UIManager.setLookAndFeel("org.pushingpixels.substance.api.skin.SubstanceOfficeBlue2007LookAndFeel");
//			UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
			//UIManager.setLookAndFeel("com.jtattoo.plaf.luna.LunaLookAndFeel");
			//UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
			//UIManager.setLookAndFeel("com.jtattoo.plaf.bernstein.BernsteinLookAndFeel");
			//UIManager.setLookAndFeel("net.sourceforge.napkinlaf.NapkinLookAndFeel");
		
        }
        catch (Exception ex) {
            Logger.getLogger(TextUI.class.getName()).log(Level.SEVERE, null, ex);
        }
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("52x52px_sfr.png")));
		setTitle("Acceso a Usuarios");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 542, 242);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.controlHighlight);
		//ontentPane.setBackground(new Color(147,140,147));
		contentPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 128)));
		setContentPane(contentPane);
		contentPane.setLayout(null);


//		JButton btnTestConexion = new JButton("Test");
//		btnTestConexion.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//
//				PoolNYCH poolNYCH = new PoolNYCH();
//				java.sql.Connection con = null;
//				try {
//					con = poolNYCH.datasource.getConnection();
//					if(con != null) {
//						JOptionPane.showMessageDialog(null, "Conexion Exitosa");
//
//					}
//				}catch(SQLException ex) {
//					StringWriter errors = new StringWriter();
//					ex.printStackTrace(new PrintWriter(errors));
//					LOG.info("Excepcion: "+ errors );
//					JOptionPane.showMessageDialog(null, ex);
//				}finally {
//
//					try {
//						con.close();
//					}catch(SQLException s) {
//						StringWriter errors = new StringWriter();
//						s.printStackTrace(new PrintWriter(errors));
//						LOG.info("Excepcion: "+ errors );
//						JOptionPane.showMessageDialog(null, s);
//
//					}
//
//				}
//
//			}
//		});
//		
//		
//		btnTestConexion.setForeground(new Color(0, 0, 0));
//		btnTestConexion.setBackground(Color.WHITE);
//		btnTestConexion.setBounds(442, 11, 74, 25);
//		contentPane.add(btnTestConexion);

		JButton btnIniciarSesion = new JButton("Aceptar");
		btnIniciarSesion.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("accept.png"))));
		btnIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					windowOpened(e);
				} catch (HeadlessException e1) {
					StringWriter errors = new StringWriter();
					e1.printStackTrace(new PrintWriter(errors));
					LOG.info("Excepcion: "+ errors );
					e1.printStackTrace();
				}
			}
		});
		
		JButton lblEditBase = new JButton("");
		lblEditBase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConfigurarDataBase dataBase = new ConfigurarDataBase();
				//dataBase.mostrarParametrosConexion();
				dataBase.setVisible(true);
				dataBase.setLocationRelativeTo(null);
				
			}
		});
		
		JLabel lblConfiguarAcceso = new JLabel("Configuar Acceso");
		lblConfiguarAcceso.setHorizontalAlignment(SwingConstants.RIGHT);
		lblConfiguarAcceso.setBounds(364, 195, 127, 16);
		contentPane.add(lblConfiguarAcceso);
		lblEditBase.setToolTipText("Configurar Base de Datos");
		lblEditBase.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("database_edit.png"))));
		lblEditBase.setBounds(503, 175, 33, 36);
		contentPane.add(lblEditBase);
		btnIniciarSesion.setForeground(new Color(0, 0, 0));
		//btnIniciarSesion.setBackground(Color.WHITE);
		btnIniciarSesion.setBounds(284, 130, 106, 30);
		contentPane.add(btnIniciarSesion);

		textFieldUser = new TextField();
		textFieldUser.setForeground(new Color(0, 0, 0));
		textFieldUser.setBackground(Color.WHITE);
		textFieldUser.setBounds(295, 67, 153, 22);
		contentPane.add(textFieldUser);

		passwordField = new JPasswordField();
		passwordField.setToolTipText("");
		passwordField.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("static-access")
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					Login conexionDboGenerales = new Login();
					try {
						if(conexionDboGenerales.validarLogin()==1) {
							dispose();
							JOptionPane.showMessageDialog(null, "Bienvenido\n Has ingresado satisfactoriamente al sistema", "Mensaje de bienvenida",JOptionPane.INFORMATION_MESSAGE);
							FormularioPrincipal fr = new FormularioPrincipal();
							fr.btnEmpleadoIcono.setEnabled(false);
							fr.btnCatalogosIcono.setEnabled(false);
							fr.btnMovimientosIcono.setEnabled(false);
							fr.toolBar.setVisible(false);
							fr.lblCalculos.setVisible(false);
							fr.btnReportesIcon.setEnabled(false);
							fr.btnTimbradoIcon.setEnabled(false);
							fr.btnCalcularNominaTesoreria.setEnabled(false);
							fr.setVisible(true);
						}else
							JOptionPane.showMessageDialog(null, "Acceso denegado:\n Por favor ingrese un usuario y/o contraseña correctos", "Acceso denegado", JOptionPane.ERROR_MESSAGE);
					} catch (HeadlessException e1) {
						StringWriter errors = new StringWriter();
						e1.printStackTrace(new PrintWriter(errors));
						LOG.info("Excepcion: "+ errors );
						e1.printStackTrace();
					}	
				}
			}
		});
		passwordField.setForeground(new Color(0, 0, 0));
		passwordField.setBackground(Color.WHITE);
		passwordField.setBounds(292, 95, 158, 28);
		contentPane.add(passwordField);

		JLabel lblPassword = new JLabel("Contraseña");
		lblPassword.setForeground(new Color(0, 0, 0));
		lblPassword.setBounds(210, 98, 75, 14);
		contentPane.add(lblPassword);

		JLabel lblUsuario = new JLabel("Usuario.");
		lblUsuario.setForeground(new Color(0, 0, 0));
		lblUsuario.setBounds(210, 72, 46, 14);
		contentPane.add(lblUsuario);
		
		JLabel lblIconoAccess = new JLabel("");
		lblIconoAccess.setForeground(new Color(0, 0, 0));
		lblIconoAccess.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("access.png"))));
		lblIconoAccess.setBounds(24, 32, 174, 196);
		contentPane.add(lblIconoAccess);
		
		JLabel lblUsrPass = new JLabel("Proporcione su Usuario y Contraseña de Acceso al Sistema.");
		lblUsrPass.setForeground(new Color(0, 0, 0));
		lblUsrPass.setBounds(180, 6, 356, 39);
		contentPane.add(lblUsrPass);
		
		JButton btnCancelarSesion = new JButton("Cancelar");
		btnCancelarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelarSesion.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("delete.png"))));
		btnCancelarSesion.setForeground(new Color(0, 0, 0));
		btnCancelarSesion.setBounds(408, 130, 106, 30);
		//btnCancelarSesion.setBackground(Color.WHITE);
		contentPane.add(btnCancelarSesion);
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("prop.png"))));
		lblFondo.setBounds(0, 0, 536, 211);
		contentPane.add(lblFondo);

		JLabel lblIcono = new JLabel("");
		lblIcono.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("user.png"))));
		lblIcono.setBackground(Color.BLUE);
		lblIcono.setBounds(26, 11, 235, 256);
	}
	
	 public  void windowOpened(ActionEvent e){
		  final long SLEEP_TIME = 1 * 1000;
	      SwingWorker<Void, Void> mySwingWorker = new SwingWorker<Void, Void>(){
	         @SuppressWarnings("static-access")
			protected Void doInBackground() throws Exception {
	        	 Login conexionDboGenerales = new Login();
	        	 if(conexionDboGenerales.validarLogin()==1) {
						dispose();
						JOptionPane.showMessageDialog(null, "Bienvenido: "+ conexionDboGenerales.getUsuario() +  "\n Haz ingresado satisfactoriamente al sistema", "Mensaje de bienvenida",JOptionPane.INFORMATION_MESSAGE);
						//JOptionPane.showMessageDialog(null, "Bienvenido: \n Haz ingresado satisfactoriamente al sistema", "Mensaje de bienvenida",JOptionPane.INFORMATION_MESSAGE);
						FormularioPrincipal fr = new FormularioPrincipal();
						fr.btnEmpleadoIcono.setEnabled(false);
						fr.btnCatalogosIcono.setEnabled(false);
						fr.btnMovimientosIcono.setEnabled(false);
						fr.toolBar.setVisible(false);
						fr.lblCalculos.setVisible(false);
						fr.btnReportesIcon.setEnabled(false);
						fr.btnTimbradoIcon.setEnabled(false);
						fr.btnCalcularNominaTesoreria.setEnabled(false);
						fr.setVisible(true);
						
					}else
						JOptionPane.showMessageDialog(null, "Acceso denegado:\n Por favor ingrese un usuario y/o contraseña correctos", "Acceso denegado", JOptionPane.ERROR_MESSAGE);
	        	 
	            // mimic some long-running process here...
	          //  Thread.sleep(SLEEP_TIME);
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
}

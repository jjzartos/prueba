package ti.snfco.NominaYCapitalHumano.service;



import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;

import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.plaf.TextUI;

import org.pushingpixels.flamingo.api.common.icon.ImageWrapperResizableIcon;
import org.pushingpixels.flamingo.api.common.icon.ResizableIcon;
import org.pushingpixels.flamingo.api.ribbon.JRibbonFrame;
import org.pushingpixels.flamingo.api.ribbon.RibbonApplicationMenu;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainFrame1 extends JRibbonFrame {
	
	
	private static final long serialVersionUID = 1L;
	public static JTextField textFieldUser;
	public static JPasswordField passwordField;
	//public static JProgressBar progressBar = new JProgressBar();;
	
	public static Timer tiempo;
	public static int cont;
	public static final int TWO_SECOND=5;
	
	
	public MainFrame1() {
		getContentPane().setBackground(Color.GRAY);
		getRibbon().setBounds(0, 0, 692, 48);
		//getRibbon().setApplicationMenuKeyTip("Iniciar Sesión");
		getContentPane().setLayout(null);
		getRibbon().setApplicationMenu(new RibbonApplicationMenu());
		setApplicationIcon(getResizableIconFromResource("img/user.png"));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("img/user.png"))));
		lblNewLabel.setBounds(10, 59, 236, 256);
		getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Salir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton.setBounds(260, 63, 75, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Test");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PoolGenerales poolGenerales = new PoolGenerales();
				java.sql.Connection con = null;
				try {
					con = poolGenerales.datasource.getConnection();
					if(con != null) {
						JOptionPane.showMessageDialog(null, "Conexion Exitosa");

					}
				}catch(SQLException ex) {
					JOptionPane.showMessageDialog(null, ex);
				}finally {

					try {
						con.close();
					}catch(SQLException s) {
						JOptionPane.showMessageDialog(null, s);

					}

				}
				
			}
		});
		btnNewButton_1.setBounds(260, 97, 75, 23);
		getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("Usuario");
		lblNewLabel_1.setForeground(Color.BLUE);
		lblNewLabel_1.setBounds(10, 331, 46, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setForeground(Color.BLUE);
		lblNewLabel_2.setBounds(10, 362, 58, 14);
		getContentPane().add(lblNewLabel_2);
		
		textFieldUser = new JTextField();
		textFieldUser.setBounds(99, 326, 130, 20);
		getContentPane().add(textFieldUser);
		textFieldUser.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					Login conexionDboGenerales = new Login();
					try {
						if(conexionDboGenerales.validarLogin()==1) {
							dispose();
							JOptionPane.showMessageDialog(null, "Bienvenido\n Has ingresado satisfactoriamente al sistema", "Mensaje de bienvenida",JOptionPane.INFORMATION_MESSAGE);
							FormularioPrincipal1 fr = new FormularioPrincipal1();
							fr.setVisible(true);
							
						}else
							JOptionPane.showMessageDialog(null, "Acceso denegado:\n Por favor ingrese un usuario y/o contraseña correctos", "Acceso denegado", JOptionPane.ERROR_MESSAGE);
					} catch (HeadlessException e1) {
						e1.printStackTrace();
					}
					
				}
			}
		});
		passwordField.setBounds(99, 359, 130, 20);
		getContentPane().add(passwordField);
	
		
		JButton btnNewButton_2 = new JButton("Login");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Login conexionDboGenerales = new Login();
				try {
					if(conexionDboGenerales.validarLogin()==1) {
						dispose();
						JOptionPane.showMessageDialog(null, "Bienvenido\n Has ingresado satisfactoriamente al sistema", "Mensaje de bienvenida",JOptionPane.INFORMATION_MESSAGE);
						FormularioPrincipal1 fr = new FormularioPrincipal1();
						fr.setVisible(true);
						
					}else
						JOptionPane.showMessageDialog(null, "Acceso denegado:\n Por favor ingrese un usuario y/o contraseña correctos", "Acceso denegado", JOptionPane.ERROR_MESSAGE);
				} catch (HeadlessException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(199, 403, 89, 23);
		getContentPane().add(btnNewButton_2);
	}	
//		 final JProgressBar progressBar = new JProgressBar();
//		progressBar.setBounds(163, 404, 146, 22);
//		getContentPane().add(progressBar);
//		
		
//		 class TimerListener implements ActionListener{
//			public void actionPerformed(ActionEvent e) {
//				cont++;
//				progressBar.setValue(0);
//				if(cont == 100) {
//					tiempo.stop();
//					esconderProgressBar();
//					FormularioPrincipal1 fr = new FormularioPrincipal1();
//					fr.setVisible(true);
//					setVisible(false);
//				}//fin del if
//				
//			}//fin del actionPerfomed
//			 
//		 }//fin de la clase TimerListener
//		
//	}//fin del constructor main

	
//	//esconder la barra de porgreso
//	public void esconderProgressBar() {
//		setVisible(false);
//		
//	}
//	
//	//activar la barra de porgreso
//	public void activaProgressBar() {
//		tiempo.start();
//	}
//	

	public static ResizableIcon getResizableIconFromResource(String resource) {
		return ImageWrapperResizableIcon.getIcon(FormularioPrincipal1.class
				.getClassLoader().getResource(resource), new Dimension(48, 48));
	}
	
	
	

//	public static void main(String[] args) {
//		
//		try {
//		      UIManager.setLookAndFeel("org.pushingpixels.substance.api.skin.SubstanceOfficeBlue2007LookAndFeel");
//		   }
//		   catch (Exception ex) {
//		       Logger.getLogger(TextUI.class.getName()).log(Level.SEVERE, null, ex);
//		   }
//		
//		SwingUtilities.invokeLater(new Runnable() {
//			
//			@SuppressWarnings({ "unchecked", "rawtypes" })
//			public void run() {
//				
//				MainFrame1 frame = new MainFrame1();
//				frame.setVisible(true);
//				frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
//				frame.pack();
//				frame.setLocationRelativeTo(null);
//				frame.setIconImage(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("img/sfrpresidencia.png")));
//				frame.setTitle("Nomina Municipal y Capital Humano");
//				frame.setBounds(500, 100, 354, 473);
//			}//run
//		});//runnable
//	}//main
}

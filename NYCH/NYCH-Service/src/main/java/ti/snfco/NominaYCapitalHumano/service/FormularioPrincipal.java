package ti.snfco.NominaYCapitalHumano.service;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;	
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.ScrollPaneLayout;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.TextUI;
import org.slf4j.LoggerFactory;
//import com.jgoodies.forms.factories.CC;

public class FormularioPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(FormularioPrincipal.class);
	JPanel contentPane;
	InternalFramePeriodo internalFramePeriodo = new InternalFramePeriodo();
	InternalFrameImpulsoras internalFrameImpulsoras = new InternalFrameImpulsoras();
	//DJJCalendar djjCalendar =  new DJJCalendar();
	JLabel lblExplorer = new JLabel("");
	JLabel lblinternet = new JLabel("");
	JLabel lblCalc = new JLabel("");
	JButton lblMenu = new JButton("");
	JLabel lblPeriodoTexto = new JLabel("Ejercicios y Periodos");
	JLabel lblEmpleadoTexto = new JLabel("Empleados");
	JButton btnPeriodoIcono = new JButton("");
	static JButton btnEmpleadoIcono = new JButton("");
	//JButton btnReportes = new JButton("");
	JLabel lblBlock = new JLabel("");
	JLabel lblExcel = new JLabel("");
	JLabel lblRadio = new JLabel("");
	public static JDesktopPane desktopPane = new JDesktopPane();
//	public InputStream foto1=this.getClass().getResourceAsStream("/img/SFR-500.png");
	public InputStream foto1=this.getClass().getResourceAsStream("/img/sfcoescudo.jpg");
	private final JLabel lblUsuarioLogueado = new JLabel("");
	private final JLabel lblUsuario = new JLabel();
	static JButton btnCatalogosIcono = new JButton("");
	static JButton btnMovimientosIcono = new JButton("");
	private final JLabel lblCatalogoTexto = new JLabel("Catálogos");
	private final JLabel lblMoviNom = new JLabel("Movimientos en Nomina");
	JLabel lblDerechosReservados = new JLabel("©Desarrollado por Tecnologías de Información.");
	JButton btnPensionAl = new JButton("");
	JLabel lblPensionalim = new JLabel("Calcular Pension Alimenticia");
	static JToolBar toolBar = new JToolBar();
	Dimension dim;
	JLabel lblfondoiconos = new JLabel("");
	JLabel lblFond = new JLabel("");
	static JLabel lblCalculos = new JLabel("Procesos");
	static JLabel lblTipoNominaNombre = new JLabel("NominaNombre");
	static JLabel lblNewLabel = new JLabel("Tipo Nomina:");
	static JLabel lblNewLabel_1 = new JLabel("Periodo:");
	static JLabel lblPeriodoNumero = new JLabel("PeriodoNumero");
	static JButton btnReportesIcon = new JButton("");
	static JButton btnTimbradoIcon = new JButton("");
	static JLabel lblIdTipoNomina = new JLabel("IdTipoNomina");
	static JLabel lblIsNomC = new JLabel("IsNomC");
	static JLabel lblIsNomina = new JLabel("Nomina");
	static JLabel lblCatorcenal = new JLabel("Catorcenal:");
	static JLabel lblSemanal = new JLabel("Semanal:");
	static JLabel lblJubilados = new JLabel("Jubilados:");
	static JLabel lblIsNomS = new JLabel("IsNomS");
	static JLabel lblIsNomJ = new JLabel("IsNomJ");
	static JLabel lblImgDetalle = new JLabel("");
	static JLabel lblFechaPeriodo = new JLabel("Fecha Periodo");
	JButton btnNewButton = new JButton("Buscar");
	static JLabel lblFechaDel = new JLabel("Del");
	static JLabel lblFechaHasta = new JLabel("a");
	static JLabel lblFechaHastaPeriodo = new JLabel("Fecha Hasta periodo");
	static JButton btnCalcularNominaTesoreria = new JButton("");
	JLabel lblCalcularNominaTesoreria = new JLabel("Nomina Tesoreria");


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
//			UIManager.setLookAndFeel("net.sourceforge.napkinlaf.NapkinLookAndFeel");

//						LookAndFeelInfo info[] = UIManager.getInstalledLookAndFeels();
//						for(LookAndFeelInfo look: info)
////						    System.out.println(look.getClassName());
		}
		catch (Exception ex) {
			Logger.getLogger(TextUI.class.getName()).log(Level.SEVERE, null, ex);
		}
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					FormularioPrincipal frame = new FormularioPrincipal();
					frame.setVisible(true);
					frame.addWindowListener(new WindowAdapter() {
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("serial")
	public FormularioPrincipal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("52x52px_sfr.png")));
		setTitle("Nómina y Capital Humano");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 1930, 1040);
		setBounds(100, 100, 1550, 800);//53, 110, 1550, 730

//		Toolkit t = Toolkit.getDefaultToolkit();
//		Dimension screenSize = t.getDefaultToolkit().getScreenSize();
//		System.out.println("Tu resolución es de " + screenSize.width + "x" + screenSize.height);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setExtendedState(MAXIMIZED_BOTH);
		contentPane.setLayout(null);

		JPanel panelMenu = new JPanel();
		panelMenu.setBounds(0, 0, 53, 411);
		panelMenu.setBorder(new LineBorder(new Color(176, 196, 222), 3, true));
		panelMenu.setLayout(null);
		contentPane.add(panelMenu);

		lblMenu.setToolTipText("Utilidades");
		lblMenu.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseMoved(MouseEvent e) {
				lblMenu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

			}
		});
		lblMenu.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				AnimationClass internet = new AnimationClass();
				internet.jLabelXRight(-40, 5, 10, 5, lblinternet);

				AnimationClass Calculadora = new AnimationClass();
				Calculadora.jLabelXRight(-40, 5, 10, 5, lblCalc);

				AnimationClass explorer = new AnimationClass();
				explorer.jLabelXRight(-40, 5, 10, 5, lblExplorer);

				AnimationClass block = new AnimationClass();
				block.jLabelXRight(-40, 5, 10, 5, lblBlock);

				AnimationClass xlsx = new AnimationClass();
				xlsx.jLabelXRight(-40, 5, 10, 5, lblExcel);

				AnimationClass radio = new AnimationClass();
				radio.jLabelXRight(-40, 5, 10, 5, lblRadio);


				//regreso a posicion original
				AnimationClass internet1 = new AnimationClass();
				internet1.jLabelXLeft(5, -40, 10, 5, lblinternet);

				AnimationClass Calculadora1 = new AnimationClass();
				Calculadora1.jLabelXLeft(5, -40, 10, 5, lblCalc);

				AnimationClass explorer1 = new AnimationClass();
				explorer1.jLabelXLeft(5, -40, 10, 5, lblExplorer);

				AnimationClass block1 = new AnimationClass();
				block1.jLabelXLeft(5, -40, 10, 5, lblBlock);

				AnimationClass xlsx1 = new AnimationClass();
				xlsx1.jLabelXLeft(5, -40, 10, 5, lblExcel);

				AnimationClass radio1 = new AnimationClass();
				radio1.jLabelXLeft(5, -40, 10, 5, lblRadio);


			}
			public void mouseExited(MouseEvent e) {

				lblMenu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(173, 216, 230)));
			}
		});




		lblMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblMenu.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("icons8_Menu_32px.png"))));
		lblMenu.setBounds(1, 1, 51, 34);
		panelMenu.add(lblMenu);

		lblinternet.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseMoved(MouseEvent e) {
				lblinternet.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

			}
		});
		lblinternet.addMouseListener(new MouseAdapter() {

			public void mouseExited(MouseEvent e) {
				lblinternet.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(173, 216, 230)));
			}

		});
		lblinternet.setHorizontalAlignment(SwingConstants.CENTER);
		lblinternet.setToolTipText("Internet");
		lblinternet.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().browse(URI.create("http://sanfrancisco.gob.mx/index/"));
				}catch(Exception exc) {
					JOptionPane.showMessageDialog(null, "Error de conexion a internet");
				}

			}
		});

		lblinternet.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("icons8_Globe_32px.png"))));
		lblinternet.setBounds(-40, 111, 43, 31);
		panelMenu.add(lblinternet);

		lblCalc.setHorizontalAlignment(SwingConstants.CENTER);
		lblCalc.setToolTipText("Calculadora");
		lblCalc.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseMoved(MouseEvent e) {
				lblCalc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

			}
		});
		lblCalc.addMouseListener(new MouseAdapter() {
			public void mouseExited(MouseEvent e) {
				lblCalc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(173, 216, 230)));
			}

		});


		lblCalc.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Calculadora();
			}

			private void Calculadora(){
				try {
					Runtime rt = Runtime.getRuntime();
					Process p = rt.exec("calc");
					p.waitFor();
				} catch (Exception ex) {     
					JOptionPane.showMessageDialog(null,"Error al ejecutar la calculadora del sistema windows: ");
				}
			}
		});

		lblCalc.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("icons8_Calculator_32px.png"))));
		lblCalc.setBounds(-40, 168, 43, 31);
		panelMenu.add(lblCalc);

		lblExplorer.setHorizontalAlignment(SwingConstants.CENTER);
		lblExplorer.setToolTipText("Explorador de Windows");

		lblExplorer.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseMoved(MouseEvent e) {
				lblExplorer.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

			}
		});
		lblExplorer.addMouseListener(new MouseAdapter() {

			public void mouseExited(MouseEvent e) {
				lblExplorer.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(173, 216, 230)));
			}

		});


		lblExplorer.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				getexplorer();
			}

			private void getexplorer(){
				try {
					Runtime rt = Runtime.getRuntime();
					Process p = rt.exec("explorer");
					p.waitFor();
				} catch (Exception ex) {     
					JOptionPane.showMessageDialog(null,"Error al ejecutar el explorador de windows.");
				}
			} 


		});

		lblExplorer.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("carpetaa.png"))));
		lblExplorer.setBounds(-40, 225, 43, 31);
		panelMenu.add(lblExplorer);

		lblBlock.setHorizontalAlignment(SwingConstants.CENTER);
		lblBlock.setToolTipText("Block de Notas");
		lblBlock.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseMoved(MouseEvent e) {
				lblBlock.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

			}
		});
		lblBlock.addMouseListener(new MouseAdapter() {
			public void mouseExited(MouseEvent e) {
				lblBlock.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(173, 216, 230)));
			}

		});

		lblBlock.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				getBlock();
			}

			private void getBlock(){
				try {
					Runtime rt = Runtime.getRuntime();
					Process p = rt.exec("notepad");
					p.waitFor();
				} catch (Exception ex) {     
					System.out.println(ex);
					JOptionPane.showMessageDialog(null,"Error al ejecutar el block de notas.");
				}
			} 

		});


		lblBlock.setBounds(-40, 282, 43, 31);
		lblBlock.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("bloc.png"))));
		panelMenu.add(lblBlock);

//		lblExcel.addMouseListener(new MouseAdapter() {
//			public void mouseClicked(MouseEvent e) {
//				getExcel();
//			}
//
//			private void getExcel(){
//				try {
//					Runtime rt = Runtime.getRuntime();
//					Process p = rt.exec("excel.exe *32");
//					p.waitFor();
//				} catch (Exception ex ) {     
//					LOG.info("ex: " + ex);
//					JOptionPane.showMessageDialog(null,"Error al ejecutar excel.");
//				}
//			} 
//
//		});
//
//		lblExcel.addMouseMotionListener(new MouseMotionAdapter() {
//			public void mouseMoved(MouseEvent e) {
//				lblExcel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
//
//			}
//		});
//		lblExcel.addMouseListener(new MouseAdapter() {
//
//			public void mouseExited(MouseEvent e) {
//				lblExcel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(173, 216, 230)));
//			}
//
//		});
//
//
//		lblExcel.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("xlsx.png"))));
//		lblExcel.setHorizontalAlignment(SwingConstants.CENTER);
//		lblExcel.setToolTipText("Excel");
//		lblExcel.setBounds(-40, 339, 43, 31);
//		panelMenu.add(lblExcel);

		lblRadio.setHorizontalAlignment(SwingConstants.CENTER);

		lblRadio.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().browse(URI.create("http://www.emisoras.com.mx/amor/"));
				} catch (IOException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error de conexion a internet-Radio ");
				}

			}

		});

		lblRadio.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("icons8_Musical_Notes_32px.png"))));
		lblRadio.setToolTipText("Radio");
		lblRadio.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseMoved(MouseEvent e) {
				lblRadio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

			}
		});
		lblRadio.addMouseListener(new MouseAdapter() {

			public void mouseExited(MouseEvent e) {
				lblRadio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(173, 216, 230)));
			}

		});
//		lblRadio.setBounds(-40, 396, 43, 31);
		lblRadio.setBounds(-40, 339, 43, 31);
		panelMenu.add(lblRadio);

		lblFond.setBounds(3, 1, 46, 405);
		lblFond.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("ppp.png"))));
		panelMenu.add(lblFond);

		JPanel panelIconos = new JPanel();
		panelIconos.setBounds(53, 0, 1866, 108);
		panelIconos.setBorder(new LineBorder(new Color(176, 196, 222), 3));
		contentPane.add(panelIconos);
		btnPeriodoIcono.setBounds(4, 4, 147, 74);

		btnPeriodoIcono.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desktopPane.removeAll();
				desktopPane.repaint();
				
				btnEmpleadoIcono.setEnabled(false);
				btnCatalogosIcono.setEnabled(false);
				btnMovimientosIcono.setEnabled(false);
				btnTimbradoIcon.setEnabled(false);
				btnReportesIcon.setEnabled(false);
				toolBar.setVisible(false);
				lblCalculos.setVisible(false);
				lblTipoNominaNombre.setVisible(false);
				lblPeriodoNumero.setVisible(false);
				lblNewLabel.setVisible(false);
				lblNewLabel_1.setVisible(false);
				lblIsNomC.setVisible(false);
				lblIsNomina.setVisible(false);
				lblIdTipoNomina.setVisible(false);
				lblImgDetalle.setVisible(false);
				lblCatorcenal.setVisible(false);
				lblSemanal.setVisible(false);
				lblJubilados.setVisible(false);
				lblIsNomC.setVisible(false);
				lblIsNomS.setVisible(false);
				lblIsNomJ.setVisible(false);
				lblFechaPeriodo.setVisible(false);
				lblFechaDel.setVisible(false);
				lblFechaHasta.setVisible(false);
				lblFechaHastaPeriodo.setVisible(false);
				
				
				internalFramePeriodo.windowOpened(e);
				internalFramePeriodo.show();
				internalFramePeriodo.setIconifiable(true);
				internalFramePeriodo.setClosable(true);
				internalFramePeriodo.setResizable(true);
				internalFramePeriodo.setMaximizable(true);
				internalFramePeriodo.setFrameIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("calendario.png"))));
				internalFramePeriodo.tabbedPanePestañas.setEnabledAt(0, true);//1 periododos cartorcenal
				internalFramePeriodo.tabbedPanePestañas.setEnabledAt(1, false);//1 periododos cartorcenal
				internalFramePeriodo.tabbedPanePestañas.setEnabledAt(2, false);//2 periododos semanal
				desktopPane.add(internalFramePeriodo);
			}
		});
		panelIconos.setLayout(null);
		btnPeriodoIcono.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("calen.png"))));
		btnPeriodoIcono.setHorizontalAlignment(SwingConstants.CENTER);
		panelIconos.add(btnPeriodoIcono);
		btnEmpleadoIcono.setBounds(169, 4, 78, 74);

		btnEmpleadoIcono.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desktopPane.removeAll();
				desktopPane.repaint();
				
				InternalFrameEmpleado internalFrameEmpleado = new InternalFrameEmpleado();
				internalFrameEmpleado.lblTipoNominaOculta.setText(internalFramePeriodo.lblTipoNominaOculto.getText());
				internalFrameEmpleado.lblTipoNominaOcultaNombre.setText(internalFramePeriodo.lblTipoNominaOcultaNombre.getText());
//				internalFrameEmpleado.windowOpened(e);
				internalFrameEmpleado.setFrameIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("user.png"))));
				internalFrameEmpleado.setIconifiable(true);
				internalFrameEmpleado.setClosable(true);
				internalFrameEmpleado.setResizable(true);
				internalFrameEmpleado.setMaximizable(true);
				internalFrameEmpleado.show();
				desktopPane.add(internalFrameEmpleado);
			}
		});
		btnEmpleadoIcono.addMouseListener(new MouseAdapter() {
		});
		btnEmpleadoIcono.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("emplo.png"))));
		btnEmpleadoIcono.setHorizontalAlignment(SwingConstants.CENTER);
		panelIconos.add(btnEmpleadoIcono);
		lblPeriodoTexto.setBounds(25, 86, 132, 14);

		lblPeriodoTexto.setForeground(Color.BLACK);
		panelIconos.add(lblPeriodoTexto);
		lblEmpleadoTexto.setBounds(179, 86, 78, 14);

		lblEmpleadoTexto.setForeground(Color.BLACK);
		panelIconos.add(lblEmpleadoTexto);
		btnCatalogosIcono.setBounds(259, 4, 78, 74);

//				lblUsuarioLogueado.setForeground(SystemColor.controlShadow);
//				lblUsuarioLogueado.setBounds(1432, 59, 108, 41);
//				Login conexionDboGenerales = new Login();
//				lblUsuarioLogueado.setText(conexionDboGenerales.getUsuario());
//				panelIconos.add(lblUsuarioLogueado);
//				lblUsuario.setToolTipText("");
//				lblUsuario.setForeground(new Color(255, 127, 80));
//				lblUsuario.setBounds(1371, 59, 59, 41);
//				//lblUsuario.setText("Usuario:");
//				lblUsuario.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("userloged.png"))));
//				panelIconos.add(lblUsuario);

		btnCatalogosIcono.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desktopPane.removeAll();
				desktopPane.repaint();
				
				InternalFrameCatalogos internalFrameCatalogos = new InternalFrameCatalogos();
				internalFrameCatalogos.windowOpened(e);
				internalFrameCatalogos.btnREFRESH.setVisible(false);
				internalFrameCatalogos.show();
//				internalFrameCatalogos.setBounds(0, 0, 1650, 740);
				internalFrameCatalogos.setFrameIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("sesion-ayto.png"))));
				internalFrameCatalogos.setIconifiable(true);
				internalFrameCatalogos.setClosable(true);
				internalFrameCatalogos.setResizable(true);
				internalFrameCatalogos.setMaximizable(true);
				desktopPane.add(internalFrameCatalogos);		
			}
		});
		btnCatalogosIcono.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("catalog.png"))));
		panelIconos.add(btnCatalogosIcono);
		lblCatalogoTexto.setBounds(267, 81, 80, 24);

		lblCatalogoTexto.setForeground(Color.BLACK);
		panelIconos.add(lblCatalogoTexto);
		btnMovimientosIcono.setBounds(349, 4, 133, 74);

		btnMovimientosIcono.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				desktopPane.removeAll();
				desktopPane.repaint();
				InternalFrameMovimientos internalFrameMovimientos = new InternalFrameMovimientos();
//				internalFrameMovimientos.lblPeriodoOculto.setText(internalFramePeriodo.lblPeriodoOculto.getText());
				internalFrameMovimientos.lblTipoNominaOculta.setText(internalFramePeriodo.lblTipoNominaOculto.getText());
				internalFrameMovimientos.lblTipoNominaOcultaSemanal.setText(internalFramePeriodo.lblTipoNominaOcultaSemana.getText());
				internalFrameMovimientos.btnAgregarListaNomina.setEnabled(false);
				internalFrameMovimientos.btnBuscarSalario.setEnabled(false);
				internalFrameMovimientos.btnBuscarValor.setEnabled(false);
				internalFrameMovimientos.separatorNomrbeId.setVisible(false);
				internalFrameMovimientos.separator_3.setVisible(false);
				internalFrameMovimientos.separator_4.setVisible(false);
				internalFrameMovimientos.separator_5.setVisible(false);
				internalFrameMovimientos.lblClave.setVisible(false);
				internalFrameMovimientos.lblFechaDeFalta.setVisible(false);
				internalFrameMovimientos.lblAuxiliarSalario.setVisible(false);
				internalFrameMovimientos.lblSalarioAuxiliar.setVisible(false);
				internalFrameMovimientos.lblTituloChecador.setVisible(false);
				internalFrameMovimientos.lblEmpIdPerp.setVisible(false);
				internalFrameMovimientos.lblEmplNombrePerp.setVisible(false);
				internalFrameMovimientos.lblEmpApPatPerp.setVisible(false);
				internalFrameMovimientos.lblEmpApMatPerp.setVisible(false);
				internalFrameMovimientos.lblIdPuestoEmpMov.setVisible(false);
				internalFrameMovimientos.lblPuestoEmpMov.setVisible(false);
				internalFrameMovimientos.lblIdUREmpMov.setVisible(false);
				internalFrameMovimientos.lblEmpURMov.setVisible(false);
				internalFrameMovimientos.show();
				internalFrameMovimientos.setFrameIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("settings.png"))));
				internalFrameMovimientos.setIconifiable(true);
				internalFrameMovimientos.setClosable(true);
				internalFrameMovimientos.setResizable(true);
				internalFrameMovimientos.setMaximizable(true);
				desktopPane.add(internalFrameMovimientos);
			}
		});
		btnMovimientosIcono.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("operations.png"))));
		panelIconos.add(btnMovimientosIcono);
		lblMoviNom.setBounds(349, 86, 161, 14);

		lblMoviNom.setForeground(new Color(0, 0, 0));
		lblMoviNom.setBackground(new Color(0, 0, 0));
		panelIconos.add(lblMoviNom);
		lblCalculos.setBounds(505, 86, 65, 14);
		panelIconos.add(lblCalculos);
		toolBar.setBounds(491, 4, 102, 74);

		toolBar.setToolTipText("Seleccione el cálculo");
		toolBar.setFloatable(false);
		//toolBar.setBorder(BorderFactory.createLineBorder(Color.black));


		//Create the popup menu.
		final JPopupMenu popup = new JPopupMenu();
		popup.setBorderPainted(false);
		popup.add(new JMenuItem(new AbstractAction("Calcular Anticipo de Nómina") {
			public void actionPerformed(ActionEvent e) {
				//if(frameInternoPeriodoMsj(internalFrameNomina)) {
				desktopPane.removeAll();
				desktopPane.repaint();
				InternalFrameNomina internalFrameNomina = new InternalFrameNomina();
				internalFrameNomina.lbltiponominaOculta.setText(internalFramePeriodo.lblTipoNominaOculto.getText());
				//internalFrameNomina.windowOpened(e);
				internalFrameNomina.lblvaloAnticipoNom.setVisible(false);
				internalFrameNomina.textFieldAnticipoNom.setVisible(false);
				internalFrameNomina.separatorAnticipoNom.setVisible(false);
				internalFrameNomina.lblsimbolodinero.setVisible(false);
				internalFrameNomina.lblFechaInicio.setVisible(false);
				internalFrameNomina.lblFechaFinal.setVisible(false);
				internalFrameNomina.dateChooserFechaInicioAntNom.setVisible(false);
				internalFrameNomina.dateChooserFechaFinalAntNom.setVisible(false);
				internalFrameNomina.btnGuardarAnticpoNom.setVisible(false);
				internalFrameNomina.btnCancelarAntNom.setVisible(false);
				internalFrameNomina.lblSimboloDinero2.setVisible(false);
				internalFrameNomina.lblIdEmpleadoAnticipoNom.setVisible(false);
				internalFrameNomina.panelAntNom.setVisible(false);
				internalFrameNomina.lblDetalle.setVisible(false);
				internalFrameNomina.lblFlecha.setVisible(false);
				internalFrameNomina.show();
//				internalFrameNomina.setBounds(0, 0, 1780, 625);
				internalFrameNomina.setFrameIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("loan.png"))));
				internalFrameNomina.setIconifiable(true);
				internalFrameNomina.setClosable(true);
				internalFrameNomina.setResizable(true);
				internalFrameNomina.setMaximizable(true);
				internalFrameNomina.setVisible(true);
				internalFrameNomina.toFront();
				desktopPane.add(internalFrameNomina);

				//				}else
				//				{
				//					JOptionPane.showMessageDialog(null,"LA VENTANA CALCULO IMPULSORA YA ESTA ABIERTA");
				//				}
			}
		}));
		popup.add(new JMenuItem(new AbstractAction("Calcular Imuvi") {
			public void actionPerformed(ActionEvent e) {
				//if(frameInternoPeriodoMsj(internalFrameImuvi)) {
				desktopPane.removeAll();
				desktopPane.repaint();
				InternalFrameImuvi internalFrameImuvi = new InternalFrameImuvi();
				internalFrameImuvi.lblTipoNominaOculta.setText(internalFramePeriodo.lblTipoNominaOculto.getText());
				//internalFrameImuvi.windowOpened(e);
				internalFrameImuvi.btnCancelar.setVisible(false);
				internalFrameImuvi.btnguardar.setVisible(false);
				internalFrameImuvi.lblIdempleado.setVisible(false);
				internalFrameImuvi.lblnombreemp.setVisible(false);
				internalFrameImuvi.lblapPat.setVisible(false);
				internalFrameImuvi.lblAPMat.setVisible(false);
				internalFrameImuvi.lblIdPuesto.setVisible(false);
				internalFrameImuvi.lblSalario.setVisible(false);
				internalFrameImuvi.lblprecio.setVisible(false);
				internalFrameImuvi.lblprecio1.setVisible(false);
				internalFrameImuvi.labelMonto.setVisible(false);
				internalFrameImuvi.textFieldMontoImuvi.setVisible(false);
				internalFrameImuvi.lblPagos.setVisible(false);
				internalFrameImuvi.textFieldPagos.setVisible(false);
				internalFrameImuvi.separator.setVisible(false);
				internalFrameImuvi.panelImuu.setVisible(false);
				internalFrameImuvi.lblDetalle.setVisible(false);
				internalFrameImuvi.lblFlecha.setVisible(false);
				internalFrameImuvi.show();
//				internalFrameImuvi.setBounds(0, 0, 1180, 625);
				internalFrameImuvi.setFrameIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("casita.png"))));
				internalFrameImuvi.setIconifiable(true);
				internalFrameImuvi.setClosable(true);
				internalFrameImuvi.setResizable(true);
				internalFrameImuvi.setMaximizable(true);
				internalFrameImuvi.setVisible(true);
				internalFrameImuvi.toFront();
				desktopPane.add(internalFrameImuvi);

				//				}else
				//				{
				//					JOptionPane.showMessageDialog(null,"LA VENTANA CALCULO IMUVI YA ESTA ABIERTA");
				//				}

			}
		}));
		popup.add(new JMenuItem(new AbstractAction("Calcular Pensión Alimenticia") {
			public void actionPerformed(ActionEvent e) {
				desktopPane.removeAll();
				desktopPane.repaint();
				InternalFramePensionAlimenticia internalFramePensionAlimenticia = new InternalFramePensionAlimenticia();
				internalFramePensionAlimenticia.lblTipoNominaOculta.setText(internalFramePeriodo.lblTipoNominaOculto.getText());
//				internalFramePensionAlimenticia.windowOpened(e);
				internalFramePensionAlimenticia.btnGuardar.setVisible(false);
				internalFramePensionAlimenticia.btnCancelar.setVisible(false);
				internalFramePensionAlimenticia.lblIdEmp.setVisible(false);
				internalFramePensionAlimenticia.lblNombre.setVisible(false);
				internalFramePensionAlimenticia.lblApPat.setVisible(false);
				internalFramePensionAlimenticia.lblApMat.setVisible(false);
				internalFramePensionAlimenticia.lblIdPuesto.setVisible(false);
				internalFramePensionAlimenticia.lblPuesto.setVisible(false);
				internalFramePensionAlimenticia.lblPrecio.setVisible(false);
				internalFramePensionAlimenticia.lblSalario.setVisible(false);
				internalFramePensionAlimenticia.separator.setVisible(false);
				internalFramePensionAlimenticia.lblPago.setVisible(false);
				internalFramePensionAlimenticia.textFieldPagos.setVisible(false);
				internalFramePensionAlimenticia.panelpensionAl.setVisible(false);
				internalFramePensionAlimenticia.lblDetalle.setVisible(false);
				internalFramePensionAlimenticia.lblFlecha.setVisible(false);
				internalFramePensionAlimenticia.show();
//				internalFramePensionAlimenticia.setBounds(0, 0, 1180, 625);
				internalFramePensionAlimenticia.setFrameIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("judgee.png"))));
				internalFramePensionAlimenticia.setIconifiable(true);
				internalFramePensionAlimenticia.setClosable(true);
				internalFramePensionAlimenticia.setResizable(true);
				internalFramePensionAlimenticia.setMaximizable(true);
				internalFramePensionAlimenticia.setVisible(true);
				internalFramePensionAlimenticia.toFront();
				FormularioPrincipal.desktopPane.add(internalFramePensionAlimenticia);

			}

		}));
		popup.add(new JMenuItem(new AbstractAction("Calcular Prima Vacacional") {
			public void actionPerformed(ActionEvent e) {
				//if(frameInternoPeriodoMsj(internalFramePrimaVacacional)) {
				desktopPane.removeAll();
				desktopPane.repaint();
				InternalFramePrimaVacacional internalFramePrimaVacacional = new InternalFramePrimaVacacional();
				internalFramePrimaVacacional.lblTipoNominaOculta.setText(internalFramePeriodo.lblTipoNominaOculto.getText());
//				internalFramePrimaVacacional.windowOpened(e);
				internalFramePrimaVacacional.lblNombre.setVisible(false);
				internalFramePrimaVacacional.lblApPat.setVisible(false);
				internalFramePrimaVacacional.lblApMat.setVisible(false);
				internalFramePrimaVacacional.lblUR.setVisible(false);
				internalFramePrimaVacacional.lblPuesto.setVisible(false);
				internalFramePrimaVacacional.lblFechaIngreso.setVisible(false);
				internalFramePrimaVacacional.lblSalario.setVisible(false);
				internalFramePrimaVacacional.lblAyudaDesp.setVisible(false);
				internalFramePrimaVacacional.lblSal.setVisible(false);
				internalFramePrimaVacacional.lblAyudaADespensa.setVisible(false);
				internalFramePrimaVacacional.lblSalarioTotal.setVisible(false);
				internalFramePrimaVacacional.lblSalTotal.setVisible(false);
				internalFramePrimaVacacional.lblSalarioDiario.setVisible(false);
				internalFramePrimaVacacional.lblSalDiario.setVisible(false);
				internalFramePrimaVacacional.lblFaltas.setVisible(false);
				internalFramePrimaVacacional.lblDiasAPagar.setVisible(false);
				internalFramePrimaVacacional.lblProporcion.setVisible(false);
				internalFramePrimaVacacional.lblPropor.setVisible(false);
				internalFramePrimaVacacional.lblTotalAPagar.setVisible(false);
				internalFramePrimaVacacional.lblTotPagar.setVisible(false);
				internalFramePrimaVacacional.textFieldFaltas.setVisible(false);
				internalFramePrimaVacacional.lbldiasPP.setVisible(false);
				internalFramePrimaVacacional.btnCalcular.setVisible(false);
				internalFramePrimaVacacional.panelprVac.setVisible(false);
				internalFramePrimaVacacional.lblFlecha.setVisible(false);
				internalFramePrimaVacacional.lblDetalle.setVisible(false);
				internalFramePrimaVacacional.show();
//				internalFramePrimaVacacional.setBounds(0, 0, 1180, 625);
				internalFramePrimaVacacional.setFrameIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("sun-umbrella.png"))));
				internalFramePrimaVacacional.setIconifiable(true);
				internalFramePrimaVacacional.setClosable(true);
				internalFramePrimaVacacional.setResizable(true);
				internalFramePrimaVacacional.setMaximizable(true);
				internalFramePrimaVacacional.setVisible(true);
				internalFramePrimaVacacional.toFront();
				desktopPane.add(internalFramePrimaVacacional);

				//				}else
				//				{
				//					JOptionPane.showMessageDialog(null,"LA VENTANA CALCULO VACACIONES YA ESTA ABIERTA");
				//				}
			}
		}));
		popup.add(new JMenuItem(new AbstractAction("Calcular Aguinaldos") {
			public void actionPerformed(ActionEvent e) {
				//if(frameInternoPeriodoMsj(internalFramePrimaVacacional)) {
				desktopPane.removeAll();
				desktopPane.repaint();
				InternalFrameAguinaldo internalFrameAguinaldo = new InternalFrameAguinaldo();
				internalFrameAguinaldo.lblTipoNominaOculta.setText(internalFramePeriodo.lblTipoNominaOculto.getText());
//				internalFrameAguinaldo.windowOpened(e);
				internalFrameAguinaldo.show();
//				internalFrameAguinaldo.setBounds(0, 0, 1180, 625);
				internalFrameAguinaldo.setFrameIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("savings.png"))));
				internalFrameAguinaldo.setIconifiable(true);
				internalFrameAguinaldo.setClosable(true);
				internalFrameAguinaldo.setResizable(true);
				internalFrameAguinaldo.setMaximizable(true);
				internalFrameAguinaldo.setVisible(true);
				internalFrameAguinaldo.toFront();
				internalFrameAguinaldo.panelRedAgui.setVisible(false);
				internalFrameAguinaldo.lblDetalle.setVisible(false);
				internalFrameAguinaldo.lblflecha.setVisible(false);
				internalFrameAguinaldo.btnAjuste.setVisible(false);
				desktopPane.add(internalFrameAguinaldo);


				//				}else
				//				{
				//					JOptionPane.showMessageDialog(null,"LA VENTANA CALCULO VACACIONES YA ESTA ABIERTA");
				//				}
			}
		}));
		popup.add(new JMenuItem(new AbstractAction("Calcular Incapacidades") {
			public void actionPerformed(ActionEvent e) {
				desktopPane.removeAll();
				desktopPane.repaint();
				InternalFrameIncapacidad internalFrameIncapacidad = new InternalFrameIncapacidad();
				internalFrameIncapacidad.lblTipoNominaOculto.setText(internalFramePeriodo.lblTipoNominaOculto.getText());
				internalFrameIncapacidad.comboBoxTipoIncapacidad.removeAllItems();
				internalFrameIncapacidad.comboBoxPeriodo.removeAllItems();
//				internalFrameIncapacidad.limpiarTabla();
				internalFrameIncapacidad.btnVerEmpleados.setEnabled(true);
//				internalFrameIncapacidad.windowOpened(e);
				internalFrameIncapacidad.show();
				internalFrameIncapacidad.setFrameIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("savings.png"))));
				internalFrameIncapacidad.setIconifiable(true);
				internalFrameIncapacidad.setClosable(true);
				internalFrameIncapacidad.setResizable(true);
				internalFrameIncapacidad.setMaximizable(true);
				internalFrameIncapacidad.setVisible(true);
				internalFrameIncapacidad.toFront();
				internalFrameIncapacidad.panelDatosIncapacidad.setVisible(false);
				internalFrameIncapacidad.panelCantidadesAcumuladas.setVisible(false);
				internalFrameIncapacidad.panelAcum.setVisible(false);
				internalFrameIncapacidad.lblCantidadesAcumuladasDe.setVisible(false);
				desktopPane.add(internalFrameIncapacidad);
			}
		}));
		popup.add(new JMenuItem(new AbstractAction("Calcular Horas Extras") {
			public void actionPerformed(ActionEvent e) {
				desktopPane.removeAll();
				desktopPane.repaint();
				InternalFrameHorasExtras internalFrameHorasExtras = new InternalFrameHorasExtras();
				internalFrameHorasExtras.lblTipoNominaOculta.setText(internalFramePeriodo.lblTipoNominaOculto.getText());
//				internalFrameHorasExtras.windowOpened(e);
				internalFrameHorasExtras.show();
				internalFrameHorasExtras.setFrameIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("savings.png"))));
				internalFrameHorasExtras.setIconifiable(true);
				internalFrameHorasExtras.setClosable(true);
				internalFrameHorasExtras.setResizable(true);
				internalFrameHorasExtras.setMaximizable(true);
				internalFrameHorasExtras.setVisible(true);
				internalFrameHorasExtras.toFront();
				internalFrameHorasExtras.panelHE.setVisible(false);
				desktopPane.add(internalFrameHorasExtras);
			}
		}));
		popup.add(new JMenuItem(new AbstractAction("Ver Vacaciones") {
			public void actionPerformed(ActionEvent e) {
				//if(frameInternoPeriodoMsj(internalFrameVacaciones)) {
				desktopPane.removeAll();
				desktopPane.repaint();
				//				JScrollPane scrollPane = new JScrollPane();
				//				
				//				scrollPane.setLayout(new ScrollPaneLayout() {
				//
				//				    public void layoutContainer(Container parent) {
				//				        JScrollPane scrollPane = (JScrollPane) parent;
				//				        scrollPane.setComponentOrientation(
				//				          ComponentOrientation.LEFT_TO_RIGHT);
				//				        super.layoutContainer(parent);
				//				        scrollPane.setComponentOrientation(
				//				          ComponentOrientation.RIGHT_TO_LEFT);
				//				    }
				//				});

				InternalFrameVacaciones internalFrameVacaciones  = new InternalFrameVacaciones();
				internalFrameVacaciones.lblTipoNominaOculta.setText(internalFramePeriodo.lblTipoNominaOculto.getText());
//				internalFrameVacaciones.windowOpened(e);
				internalFrameVacaciones.show();
//				internalFrameVacaciones.setBounds(0,	 0, 1220, 550);
				internalFrameVacaciones.setFrameIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("sun-umbrella.png"))));
				internalFrameVacaciones.setIconifiable(true);
				internalFrameVacaciones.setClosable(true);
				internalFrameVacaciones.setResizable(true);
				internalFrameVacaciones.setMaximizable(true);
				internalFrameVacaciones.setVisible(true);
				internalFrameVacaciones.toFront();
				internalFrameVacaciones.lblDetalle.setVisible(false);
				internalFrameVacaciones.lblFlecha.setVisible(false);
				desktopPane.add(internalFrameVacaciones);
				//internalFrameVacaciones.setContentPane(scrollPane);

				//						ArrayList<String> listaDependencia = new ArrayList<String>();
				//						listaDependencia = internalFrameEmpleado.selectComboDependencia();
				//						internalFrameVacaciones.comboBoxVacaciones.addItem("Seleccione Una");
				//						for(int i = 0; i<listaDependencia.size();i++){
				//							internalFrameVacaciones.comboBoxVacaciones.addItem(listaDependencia.get(i));
				//							//System.out.println(listaDependencia.get(i));
				//						}

				//				}else
				//				{
				//					JOptionPane.showMessageDialog(null,"LA VENTANA CALCULO VACACIONES YA ESTA ABIERTA");
				//				}
			}
		}));
		popup.add(new JMenuItem(new AbstractAction("Calcular Finiquito") {
			public void actionPerformed(ActionEvent e) {
				desktopPane.removeAll();
				desktopPane.repaint();
				InternalFrameFiniquito internalFrameFiniquito =  new InternalFrameFiniquito();
				internalFrameFiniquito.lblTipoNominaOculta.setText(internalFramePeriodo.lblTipoNominaOculto.getText());
//				internalFrameFiniquito.windowOpened(e);
				internalFrameFiniquito.show();
				internalFrameFiniquito.setFrameIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("savings.png"))));
				internalFrameFiniquito.setIconifiable(true);
				internalFrameFiniquito.setClosable(true);
				internalFrameFiniquito.setResizable(true);
				internalFrameFiniquito.setMaximizable(true);
				internalFrameFiniquito.setVisible(true);
				internalFrameFiniquito.toFront();
				internalFrameFiniquito.btnReCalcular.setVisible(false);
				internalFrameFiniquito.lblFechaPago.setVisible(false);
				internalFrameFiniquito.dateChooserFechaPago.setVisible(false);
				internalFrameFiniquito.btnGuardaFiniquito.setVisible(false);
				internalFrameFiniquito.textAreaMotivos.setVisible(false);
				internalFrameFiniquito.lblMoti.setVisible(false);
				internalFrameFiniquito.btnArchivoFiniquito.setVisible(false);
				desktopPane.add(internalFrameFiniquito);
			}
		}));
		popup.add(new JMenuItem(new AbstractAction("Ingresas Faltas") {
			public void actionPerformed(ActionEvent e) {
				desktopPane.removeAll();
				desktopPane.repaint();
				InternalFrameFaltas internalFrameFaltas =  new InternalFrameFaltas();
				internalFrameFaltas.lblTipoNominaOculta.setText(internalFramePeriodo.lblTipoNominaOculto.getText());
//				internalFrameFiniquito.windowOpened(e);
				internalFrameFaltas.show();
				internalFrameFaltas.setFrameIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("savings.png"))));
				internalFrameFaltas.setIconifiable(true);
				internalFrameFaltas.setClosable(true);
				internalFrameFaltas.setResizable(true);
				internalFrameFaltas.setMaximizable(true);
				internalFrameFaltas.setVisible(true);
				internalFrameFaltas.toFront();
				internalFrameFaltas.lblTipoNominaOculta.setVisible(false);
				internalFrameFaltas.lblClave.setVisible(false);
				internalFrameFaltas.lblFecha.setVisible(false);
				internalFrameFaltas.lblEntraTurno.setVisible(false);
				internalFrameFaltas.lblEntrada.setVisible(false);
				internalFrameFaltas.lblSalidaTurno.setVisible(false);
				internalFrameFaltas.lblSalida.setVisible(false);
				internalFrameFaltas.lblIncidencia.setVisible(false);
				internalFrameFaltas.lblMotivo.setVisible(false);
				internalFrameFaltas.lblEstacion.setVisible(false);
				internalFrameFaltas.lblInhabil.setVisible(false);
				internalFrameFaltas.btnFaltas.setVisible(false);
				internalFrameFaltas.btnCalcularFalta.setVisible(false);
				internalFrameFaltas.lblIdEmp.setVisible(false);
				internalFrameFaltas.lblApPat.setVisible(false);
				internalFrameFaltas.lblApMat.setVisible(false);
				internalFrameFaltas.lblIdPuesto.setVisible(false);
				internalFrameFaltas.lblPuesto.setVisible(false);
				internalFrameFaltas.lblPrecio.setVisible(false);
				internalFrameFaltas.lblIdUREmpMov.setVisible(false);
				internalFrameFaltas.lblNombre.setVisible(false);
				internalFrameFaltas.scrollPaneMvtos.setVisible(false);
				internalFrameFaltas.btnSeleccionarMvtos.setVisible(false);
				internalFrameFaltas.lblFechaDe.setVisible(false);
				internalFrameFaltas.lblFechaHasta.setVisible(false);
				internalFrameFaltas.dateChooserFechaDe.setVisible(false);
				internalFrameFaltas.dateChooserFechaHasta.setVisible(false);
				desktopPane.add(internalFrameFaltas);
			}
		}));
//		popup.add(new JMenuItem(new AbstractAction("Prueba") {
//			public void actionPerformed(ActionEvent e) {
//				desktopPane.removeAll();
//				desktopPane.repaint();
////				internalPrueba.windowOpened(e);
//////				internalPrueba.mostrarEmpleadosPrueba();
////				internalPrueba.show();
////				internalPrueba.setIconifiable(true);
////				internalPrueba.setClosable(true);
////				internalPrueba.setResizable(true);
////				internalPrueba.setMaximizable(true);
////				internalPrueba.setVisible(true);
////				internalPrueba.toFront();
//				
////				frmPrincipal.show();
////				frmPrincipal.setIconifiable(true);
////				frmPrincipal.setClosable(true);
//				frmPrincipal.setResizable(true);
////				frmPrincipal.setMaximizable(true);
//				frmPrincipal.setVisible(true);
//				frmPrincipal.toFront();
//				
//				
//				
//				desktopPane.add(frmPrincipal);
//			}
//		}));

		final JButton button = new JButton("      ");
		button.setBackground(new Color(211, 211, 211));
		button.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("loan.png"))));
		button.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
		toolBar.add(button);
		panelIconos.add(toolBar);
		lblTipoNominaNombre.setBounds(1135, 4, 467, 16);
		lblTipoNominaNombre.setVisible(false);
		panelIconos.add(lblTipoNominaNombre);
		lblNewLabel.setBounds(1009, 4, 96, 16);

		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setVisible(false);
		panelIconos.add(lblNewLabel);
		lblNewLabel_1.setBounds(1027, 20, 78, 16);

		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setVisible(false);
		panelIconos.add(lblNewLabel_1);
		lblPeriodoNumero.setBounds(1117, 20, 30, 16);
		lblPeriodoNumero.setVisible(false);
		panelIconos.add(lblPeriodoNumero);

		btnTimbradoIcon.setBounds(605, 4, 78, 74);
		btnTimbradoIcon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				desktopPane.removeAll();
				desktopPane.repaint();
				InternalFrameTimbrado internalFrameTimbrado = new InternalFrameTimbrado();
				internalFrameTimbrado.show();
				internalFrameTimbrado.setIconifiable(true);
				internalFrameTimbrado.setClosable(true);
				internalFrameTimbrado.setResizable(true);
				internalFrameTimbrado.setMaximizable(true);
				internalFrameTimbrado.setVisible(true);
				internalFrameTimbrado.toFront();
				desktopPane.add(internalFrameTimbrado);

			}
		});
		btnTimbradoIcon.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("sync.png"))));
		panelIconos.add(btnTimbradoIcon);

		JLabel lblNewLabel_2 = new JLabel("Timbrado");
		lblNewLabel_2.setBounds(615, 85, 78, 16);
		panelIconos.add(lblNewLabel_2);



		btnReportesIcon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				desktopPane.removeAll();
				desktopPane.repaint();
				InternalFrameReportes internalFrameReportes = new InternalFrameReportes();
				internalFrameReportes.windowOpened(arg0);
				internalFrameReportes.show();
				internalFrameReportes.setIconifiable(true);
				internalFrameReportes.setClosable(true);
				internalFrameReportes.setResizable(true);
				internalFrameReportes.setMaximizable(true);
				internalFrameReportes.setVisible(true);
				internalFrameReportes.toFront();
				internalFrameReportes.btnGenerarReporte.setVisible(false);
				internalFrameReportes.lblSalarios.setVisible(false);
				internalFrameReportes.lblAyudaADespensa.setVisible(false);
				internalFrameReportes.lblIsr.setVisible(false);
				internalFrameReportes.lblImss.setVisible(false);
				internalFrameReportes.lblImuvi.setVisible(false);
				internalFrameReportes.lblAnticipoDeNomina.setVisible(false);
				internalFrameReportes.lblPensionAlimenticia.setVisible(false);
				internalFrameReportes.scrollPaneMov.setVisible(false);
				internalFrameReportes.scrollPaneAyuda.setVisible(false);
				internalFrameReportes.scrollPaneMovDeducciones.setVisible(false);
				internalFrameReportes.scrollPaneDedImss.setVisible(false);
				internalFrameReportes.scrollPaneImuvi.setVisible(false);
				internalFrameReportes.scrollPaneAnticipoNomina.setVisible(false);
				internalFrameReportes.scrollPanePensionAlimenticia.setVisible(false);
				desktopPane.add(internalFrameReportes);


			}
		});
		btnReportesIcon.setBounds(692, 4, 78, 74);
		btnReportesIcon.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("analyticss.png"))));
		panelIconos.add(btnReportesIcon);

		JLabel lblReportesIcono = new JLabel("Calcular");
		lblReportesIcono.setBounds(702, 85, 55, 16);
		panelIconos.add(lblReportesIcono);


		lblIdTipoNomina.setBounds(1117, 4, 42, 16);
		lblIdTipoNomina.setVisible(false);
		panelIconos.add(lblIdTipoNomina);

		lblIsNomina.setBounds(1117, 35, 55, 16);
		lblIsNomina.setVisible(false);
		panelIconos.add(lblIsNomina);
		lblCatorcenal.setHorizontalAlignment(SwingConstants.RIGHT);

		lblCatorcenal.setBounds(1017, 48, 88, 16);
		lblCatorcenal.setVisible(false);
		panelIconos.add(lblCatorcenal);
		lblSemanal.setHorizontalAlignment(SwingConstants.RIGHT);

		lblSemanal.setBounds(1017, 63, 88, 16);
		lblSemanal.setVisible(false);
		panelIconos.add(lblSemanal);
		lblJubilados.setHorizontalAlignment(SwingConstants.RIGHT);

		lblJubilados.setBounds(1017, 79, 88, 16);
		lblJubilados.setVisible(false);
		panelIconos.add(lblJubilados);

		lblIsNomC.setBounds(1117, 48, 55, 16);
		lblIsNomC.setVisible(false);
		panelIconos.add(lblIsNomC);

		lblIsNomS.setBounds(1117, 63, 55, 16);
		lblIsNomS.setVisible(false);
		panelIconos.add(lblIsNomS);

		lblIsNomJ.setBounds(1117, 79, 55, 16);
		lblIsNomJ.setVisible(false);
		panelIconos.add(lblIsNomJ);

		lblImgDetalle.setBounds(1164, 35, 55, 16);
		lblImgDetalle.setVisible(false);
		lblImgDetalle.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("download.png"))));
		panelIconos.add(lblImgDetalle);
		
		lblFechaPeriodo.setBounds(1187, 21, 80, 14);
		lblFechaPeriodo.setVisible(false);
		panelIconos.add(lblFechaPeriodo);
		
		lblFechaDel.setBounds(1157, 21, 30, 14);
		lblFechaDel.setVisible(false);
		panelIconos.add(lblFechaDel);

		lblFechaHasta.setBounds(1285, 21, 21, 14);
		lblFechaHasta.setVisible(false);
		panelIconos.add(lblFechaHasta);

		lblFechaHastaPeriodo.setBounds(1304, 21, 80, 14);
		lblFechaHastaPeriodo.setVisible(false);
		panelIconos.add(lblFechaHastaPeriodo);
		
		btnCalcularNominaTesoreria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desktopPane.removeAll();
				desktopPane.repaint();
				InternalFrameCalcularNominaTesoreria internalFrameCalcularNominaTesoreria = new InternalFrameCalcularNominaTesoreria();
				internalFrameCalcularNominaTesoreria.lblTipoNomina.setText(internalFramePeriodo.lblTipoNominaOculto.getText());
				internalFrameCalcularNominaTesoreria.show();
				internalFrameCalcularNominaTesoreria.setIconifiable(true);
				internalFrameCalcularNominaTesoreria.setClosable(true);
				internalFrameCalcularNominaTesoreria.setResizable(true);
				internalFrameCalcularNominaTesoreria.setMaximizable(true);
				internalFrameCalcularNominaTesoreria.setFrameIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("calculating (2).png"))));
				
//				internalFrameCalcularNominaTesoreria.textFieldIdPeriodo.setText(FormularioPrincipal.lblPeriodoNumero.getText());
//				internalFrameCalcularNominaTesoreria.textFieldPerdiodoFechas.setText("Del "+ FormularioPrincipal.lblFechaPeriodo.getText().substring(0, 9)+" Al "+FormularioPrincipal.lblFechaHastaPeriodo.getText().substring(0, 9));
				desktopPane.add(internalFrameCalcularNominaTesoreria);
				
				
			}
		});
		btnCalcularNominaTesoreria.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("calculating (2).png"))));
		btnCalcularNominaTesoreria.setBounds(778, 4, 119, 74);
		panelIconos.add(btnCalcularNominaTesoreria);
		
		lblCalcularNominaTesoreria.setBounds(788, 86, 109, 14);
		panelIconos.add(lblCalcularNominaTesoreria);
		
//		btnNewButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				desktopPane.removeAll();
//				desktopPane.repaint();
//				internalPrueba.windowOpened(e);
//				internalPrueba.show();
//				internalPrueba.setIconifiable(true);
//				internalPrueba.setClosable(true);
//				internalPrueba.setResizable(true);
//				internalPrueba.setMaximizable(true);
//				internalPrueba.setVisible(true);
//				internalPrueba.toFront();
//				//internalPrueba.mostrarEmpleadosPrueba();
//				desktopPane.add(internalPrueba);
//			}
//		});
//		btnNewButton.setBounds(1503, 16, 90, 28);
//		panelIconos.add(btnNewButton);

		lblfondoiconos.setBounds(4, 4, 1856, 101);
		lblfondoiconos.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("pro.png"))));
		panelIconos.add(lblfondoiconos);

//		desktopPane.setBorder(new LineBorder(Color.BLUE));
		desktopPane.setBackground(Color.WHITE);
		desktopPane.setBounds(53, 110, 1550, 730);
		//System.out.println("dimensiones desktop: "+desktopPane.getBounds());


		cargarImagen(desktopPane, foto1);

		//		lblTipoNominaOculto.setBounds(6, 6, 520, 16);
		//		desktopPane.add(lblTipoNominaOculto);

		//btnEmpleadoIcono.setEnabled(false);

		contentPane.add(desktopPane);
				lblDerechosReservados.setBounds(1254, 865, 292, 22);
				desktopPane.add(lblDerechosReservados);
		
				lblDerechosReservados.setForeground(Color.WHITE);
	}

	//cargar imagen a un JDesktopPane
	public void cargarImagen(javax.swing.JDesktopPane jDeskp, InputStream fileImagen){   
		try{   
			BufferedImage image = ImageIO.read(fileImagen);        
			jDeskp.setBorder(new Fondo(image)); 
		}catch (Exception e)

		{   System.out.println("Imagen no disponible");   }        
	}

	//revisa si existe jframe abierto
	public static boolean frameInternoPeriodoMsj(Object obj){
		JInternalFrame[] activos=desktopPane.getAllFrames();
		boolean cerrado=true;
		int i=0;
		while (i<activos.length && cerrado){
			if(activos[i]==obj){
				cerrado=false;
			}
			i++;
		}
		return cerrado;
	}



	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}

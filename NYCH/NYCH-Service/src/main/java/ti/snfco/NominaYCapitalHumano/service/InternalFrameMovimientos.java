package ti.snfco.NominaYCapitalHumano.service;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Dialog.ModalityType;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.print.PrinterJob;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import org.slf4j.LoggerFactory;
import java.awt.event.MouseAdapter;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JEditorPane;

public class InternalFrameMovimientos extends JInternalFrame{

	private static final long serialVersionUID = 1L;
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(InternalFrameMovimientos.class);
	InternalFrameReportes internalFrameReportes = new InternalFrameReportes();
	InternalFrameEmpleado internalFrameEmpleado = new InternalFrameEmpleado();
	InternalFrameFaltas internalFrameFaltas = new InternalFrameFaltas();
	InternalFramePeriodo internalFramePeriodo =  new InternalFramePeriodo();
	InternalFrameCatalogos internalFrameCatalogos = new InternalFrameCatalogos();
	InternalProcesos internalProcesos = new InternalProcesos();
	JTable tableMovEmpPerp = new JTable();
	JTable tableMovEmpNewPerp = new JTable();
	JTable tableMovEmpDed = new JTable();
	JTable tableMovEmpNewDed = new JTable();
	JTable tableMovPercepciones = new JTable();
	JTable tableMovDeducciones = new JTable();
	JTable tableNominaFinal = new JTable();
	String atributo = "id";
	//	MyButton btnPerp03 = new MyButton("");
	Color colorSilverLight=new Color(176, 196, 222);
	JLabel lblEmplNombrePerp = new JLabel("lblEmplNombrePerp");
	JLabel lblEmpApPatPerp = new JLabel("lblEmpApPatPerp");
	JLabel lblEmpApMatPerp = new JLabel("lblEmpApMatPerp");
	JLabel lblEmpIdPerp = new JLabel("lblEmpIdPerp");
	JLabel lblPuestoEmpMov = new JLabel("lblPuestoEmpMov");
	JLabel lblEmpURMov = new JLabel("lblEmpURMov");
	JLabel lblIdPuestoEmpMov = new JLabel("lblIdPuestoEmpMov");
	JLabel lblIdUREmpMov = new JLabel("lblIdUREmpMov");
	JSeparator separatorNomrbeId = new JSeparator();
	JSeparator separator_3 = new JSeparator();
	JSeparator separator_4 = new JSeparator();
	JSeparator separator_5 = new JSeparator();
	DefaultTableModel tmNomina = (DefaultTableModel) tableNominaFinal.getModel();
	JButton btnAgregarListaNomina = new JButton("AGREGAR A NOMINA");
	JButton btnDescartarCambios = new JButton("CANCELAR");
	JButton btnBuscarSalario = new JButton("Valor");
	JButton btnBuscarValor = new JButton("Buscar");
	JLabel lblAuxiliarSalario = new JLabel("");
	JLabel lblFechaMvto = new JLabel("");
	JLabel lblclavechecador = new JLabel("");
	JLabel lblClave = new JLabel("CLAVE EMPLEADO");
	JLabel lblFechaDeFalta = new JLabel("FECHA DE FALTA");
	JLabel lblSalarioAuxiliar = new JLabel("SALARIO CON DESCUENTO");
	JLabel lblTituloChecador = new JLabel("Datos del Reloj Checador");
	public static JDesktopPane desktopPane = new JDesktopPane();
	public InputStream foto1=this.getClass().getResourceAsStream("/img/SFR-500.png");
	JLabel lblFondo = new JLabel("");
	JButton btnEmpPerpAgr = new JButton("->");
	JButton btnEmpDedAgr = new JButton("->");
	static JLabel lblTipoNominaOculta = new JLabel("lblTipoNominaOculta");
	static JLabel lblTipoNominaOcultaSemanal = new JLabel("lblTipoNominaOcultaSemanal");
	private final JLabel label_1 = new JLabel("Deducciones");
	public static String faltas = "";
	public static String salarioxFalta = "";



	public InternalFrameMovimientos() {
		setBounds(0, 0, 1500, 701);
		setVisible(true);
		setTitle("Movimientos");
		getContentPane().setLayout(null);

		JPanel panelMov =new JPanel();
		panelMov.setBackground(SystemColor.controlHighlight);
		panelMov.setToolTipText("");
		panelMov.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		//panelMov.setBackground(new Color(147,140,147));
		panelMov.setBounds(0, 0, 1815, 830);
		getContentPane().add(panelMov);
		panelMov.setLayout(null);


		btnDescartarCambios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnDescartarCambios.setBounds(671, 581, 183, 28);
		btnDescartarCambios.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("delete.png"))));

		panelMov.add(btnDescartarCambios);

		JLabel lblPercepciones = new JLabel("Percepciones");
		lblPercepciones.setForeground(new Color(0, 0, 0));
		lblPercepciones.setBounds(851, 34, 82, 14);
		panelMov.add(lblPercepciones);

		JButton btnBuscarPerp = new JButton("Buscar Empleado");
		btnBuscarPerp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InternalFrameBuscarEmpl internalFrameBuscarEmpl = new InternalFrameBuscarEmpl();
				FormularioPrincipal.desktopPane.add(internalFrameBuscarEmpl);
				internalFrameBuscarEmpl.lblTipoNominaOculta.setText(InternalFrameMovimientos.lblTipoNominaOculta.getText());
				//internalFrameBuscarEmpl.lblTipoNominaOcultaSemanal.setText(InternalFrameMovimientos.lblTipoNominaOcultaSemanal.getText());
				//				internalFrameBuscarEmpl.windowOpened(e);
				internalFrameBuscarEmpl.show();
				//				internalFrameBuscarEmpl.setBounds(0, 0, 1571, 670);
				internalFrameBuscarEmpl.setFrameIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("user_add.png"))));
				internalFrameBuscarEmpl.setIconifiable(true);
				internalFrameBuscarEmpl.setClosable(true);
				internalFrameBuscarEmpl.setResizable(true);
				internalFrameBuscarEmpl.setMaximizable(true);
				internalFrameBuscarEmpl.setVisible(true);
				internalFrameBuscarEmpl.toFront();
				dispose();
			}
		});
		btnBuscarPerp.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("icons8_Search_32px_2.png"))));
		btnBuscarPerp.setBounds(28, 23, 183, 35);
		panelMov.add(btnBuscarPerp);

		JScrollPane scrollPaneDatosEmpPerp = new JScrollPane();
		scrollPaneDatosEmpPerp.setViewportBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		scrollPaneDatosEmpPerp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneDatosEmpPerp.setBounds(11, 185, 617, 174);

		//snippet-code para dar accion a los botones dentro de las tablas
		tableMovEmpPerp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int columna =  tableMovEmpPerp.getColumnModel().getColumnIndexAtX(arg0.getX());
				int fila =  arg0.getY()/tableMovEmpPerp.getRowHeight();

				if(fila<tableMovEmpPerp.getRowCount() && fila>=0 && columna<tableMovEmpPerp.getColumnCount() && columna>=0) {
					Object value = tableMovEmpPerp.getValueAt(fila,columna);
					if(value instanceof JButton) {
						((JButton)value).doClick();
						JButton boton = (JButton)value;

						if(boton.getName().equals("btnPercepcionSalario")) {
							System.out.println("boton btnPercepcionSalario: ");

						}

						if(boton.getName().equals("btnPercepcionAyuda")) {
							System.out.println("boton btnPercepcionAyuda: ");

						}

						if(boton.getName().equals("btnPrimaVacacional")) {
							System.out.println("boton btnPrimaVacacional: ");

						}

						if(boton.getName().equals("btnAguinaldo")) {
							System.out.println("boton btnAguinaldo: ");

						}

						if(boton.getName().equals("btnHrsExtras")) {
							System.out.println("boton btnHrsExtras: ");

						}



					}
				}
			}
		});
		tableMovEmpPerp.setSurrendersFocusOnKeystroke(true);
		scrollPaneDatosEmpPerp.setViewportView(tableMovEmpPerp);
		panelMov.add(scrollPaneDatosEmpPerp);

		JScrollPane scrollPaneDatosEmpNewPerp = new JScrollPane();
		scrollPaneDatosEmpNewPerp.setViewportBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		scrollPaneDatosEmpNewPerp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneDatosEmpNewPerp.setBounds(687, 185, 620, 174);
		scrollPaneDatosEmpNewPerp.setViewportView(tableMovEmpNewPerp);
		panelMov.add(scrollPaneDatosEmpNewPerp);



		btnEmpPerpAgr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DefaultTableModel tmD = (DefaultTableModel) tableMovEmpNewPerp.getModel();
				DefaultTableModel tmO = (DefaultTableModel) tableMovEmpPerp.getModel();
				int fila = tableMovEmpPerp.getSelectedRow();
				try {

					for(int i=0;i<tableMovEmpPerp.getRowCount();i++) {
						Object[] filaSeleccionada = {
								tmO.getValueAt(i, 0), 
								tmO.getValueAt(i, 1),
								tmO.getValueAt(i, 2),
								tmO.getValueAt(i, 3),


						};
						System.out.println("->>"+tmO.getRowCount());
						tmD.addRow(filaSeleccionada);
						System.out.println("->"+tmD.getRowCount());
						btnBuscarSalario.setEnabled(true);
						btnBuscarValor.setEnabled(true);
						btnAgregarListaNomina.setEnabled(true);
					}
				}catch(Exception exx) {
					exx.printStackTrace();
					LOG.info("Excepción: " + exx);
				}
				//Ocultar_Componte();

			}
		});
		btnEmpPerpAgr.setToolTipText("Agregar Percepción del Empleado");
		btnEmpPerpAgr.setBounds(638, 269, 45, 30);
		panelMov.add(btnEmpPerpAgr);


		btnEmpDedAgr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DefaultTableModel tmO = (DefaultTableModel) tableMovEmpDed.getModel();
				DefaultTableModel tmD = (DefaultTableModel) tableMovEmpNewDed.getModel();
				int fila = tableMovEmpDed.getSelectedRow();
				try {
					for(int i=0;i<tableMovEmpDed.getRowCount();i++) {
						Object[] filaSeleccionada = {
								tmO.getValueAt(i, 0), 
								tmO.getValueAt(i, 1),
								tmO.getValueAt(i, 2),
								tmO.getValueAt(i, 3),
								tmO.getValueAt(i, 4),

						};
						tmD.addRow(filaSeleccionada);
					}
				}catch(Exception exx) {
					exx.printStackTrace();
					LOG.info("Excepción: " + exx);
				}
			}
		});
		btnEmpDedAgr.setToolTipText("Agregar Deducción del Empleado");
		btnEmpDedAgr.setBounds(638, 449, 44, 30);
		panelMov.add(btnEmpDedAgr);


		JScrollPane scrollPaneDatosEmpDed = new JScrollPane();
		scrollPaneDatosEmpDed.setViewportBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		scrollPaneDatosEmpDed.setBounds(10, 384, 620, 174);
		scrollPaneDatosEmpDed.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		tableMovEmpDed.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("unused")
			@Override
			public void mouseClicked(MouseEvent e) {
				int columna =  tableMovEmpDed.getColumnModel().getColumnIndexAtX(e.getX());
				int fila =  e.getY()/tableMovEmpDed.getRowHeight();

				if(fila<tableMovEmpDed.getRowCount() && fila>=0 && columna<tableMovEmpDed.getColumnCount() && columna>=0) {
					Object value = tableMovEmpDed.getValueAt(fila,columna);
					if(value instanceof JButton) {
						((JButton)value).doClick();
						JButton boton = (JButton)value;

						if(boton.getName().equals("btnISR")) {
							System.out.println("boton btnISR: ");

							String nombre = lblEmplNombrePerp.getText();
							String ApPat = lblEmpApPatPerp.getText();
							String ApMat = lblEmpApMatPerp.getText();
							String nombreConcat = nombre +" "+ApPat+" "+ApMat;
							Date result = new Date();
							DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
							String convertido = formatter.format(result);
							String carpeta= "C:\\Informacion de Procesos del NYCH";
							String carpetaInterna= "\\ISR";
							String archivo = "\\ISR_"+convertido+"_"+nombreConcat+".txt";

							Timbrar leerTimbre = new Timbrar();
							String datosOut = leerTimbre.muestraContenido(carpeta+carpetaInterna+archivo);

							FormularioPrincipal.desktopPane.add(internalProcesos);
							internalProcesos.show();
							internalProcesos.setFrameIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("settings.png"))));
							internalProcesos.setIconifiable(true);
							internalProcesos.setClosable(true);
							internalProcesos.setResizable(true);
							internalProcesos.setMaximizable(true);
							internalProcesos.setVisible(true);
							internalProcesos.toFront();
							internalProcesos.editorPane.setContentType("text/html");
							internalProcesos.editorPane.setText(datosOut);



						}

						if(boton.getName().equals("btnIMSS")) {
							System.out.println("boton btnIMSS: ");


							String nombre = lblEmplNombrePerp.getText();
							String ApPat = lblEmpApPatPerp.getText();
							String ApMat = lblEmpApMatPerp.getText();
							String nombreConcat = nombre +" "+ApPat+" "+ApMat;
							Date result = new Date();
							DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
							String convertido = formatter.format(result);
							String carpeta= "C:\\Informacion de Procesos del NYCH";
							String carpetaInterna= "\\IMSS";
							String archivo = "\\IMSS_"+convertido+"_"+nombreConcat+".txt";

							Timbrar leerTimbre = new Timbrar();
							String datosOut = leerTimbre.muestraContenido(carpeta+carpetaInterna+archivo);

							FormularioPrincipal.desktopPane.add(internalProcesos);
							internalProcesos.show();
							internalProcesos.setFrameIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("settings.png"))));
							internalProcesos.setIconifiable(true);
							internalProcesos.setClosable(true);
							internalProcesos.setResizable(true);
							internalProcesos.setMaximizable(true);
							internalProcesos.setVisible(true);
							internalProcesos.toFront();
							internalProcesos.editorPane.setContentType("text/html");
							internalProcesos.editorPane.setText(datosOut);


						}

						if(boton.getName().equals("btnInfonavit")) {
							System.out.println("boton btnInfonavit: ");

							String nombre = lblEmplNombrePerp.getText();
							String ApPat = lblEmpApPatPerp.getText();
							String ApMat = lblEmpApMatPerp.getText();
							String nombreConcat = nombre +" "+ApPat+" "+ApMat;
							Date result = new Date();
							DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
							String convertido = formatter.format(result);
							String carpeta= "C:\\Informacion de Procesos del NYCH";
							String carpetaInterna= "\\INFONAVIT";
							String archivo = "\\INFONAVIT_"+convertido+"_"+nombreConcat+".txt";

							Timbrar leerTimbre = new Timbrar();
							String datosOut = leerTimbre.muestraContenido(carpeta+carpetaInterna+archivo);

							FormularioPrincipal.desktopPane.add(internalProcesos);
							internalProcesos.show();
							internalProcesos.setFrameIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("settings.png"))));
							internalProcesos.setIconifiable(true);
							internalProcesos.setClosable(true);
							internalProcesos.setResizable(true);
							internalProcesos.setMaximizable(true);
							internalProcesos.setVisible(true);
							internalProcesos.toFront();
							internalProcesos.editorPane.setContentType("text/html");
							internalProcesos.editorPane.setText(datosOut);

						}


						if(boton.getName().equals("btnImuvi")) {
							System.out.println("boton btnImuvi: ");

						}

						if(boton.getName().equals("btnAnticipo")) {
							System.out.println("boton btnAnticipo: ");

						}

						if(boton.getName().equals("btnPensionAlimenticia")) {
							System.out.println("boton btnPensionAlimenticia: ");

						}



					}
				}

			}
		});
		scrollPaneDatosEmpDed.setViewportView(tableMovEmpDed);
		panelMov.add(scrollPaneDatosEmpDed);

		JScrollPane scrollPaneDatosEmpNewDed = new JScrollPane();
		scrollPaneDatosEmpNewDed.setViewportBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		scrollPaneDatosEmpNewDed.setBounds(687, 384, 620, 174);
		scrollPaneDatosEmpNewDed.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneDatosEmpNewDed.setViewportView(tableMovEmpNewDed);
		panelMov.add(scrollPaneDatosEmpNewDed);


		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(new Color(211, 211, 211));
		separator.setBackground(new Color(255, 255, 255));
		separator.setBounds(836, 34, 5, 124);
		panelMov.add(separator);
		lblEmplNombrePerp.setForeground(new Color(0, 0, 0));

		lblEmplNombrePerp.setBounds(72, 66, 272, 14);
		panelMov.add(lblEmplNombrePerp);
		lblEmpApPatPerp.setForeground(new Color(0, 0, 0));


		lblEmpApPatPerp.setBounds(28, 83, 190, 14);
		panelMov.add(lblEmpApPatPerp);
		lblEmpApMatPerp.setForeground(new Color(0, 0, 0));

		lblEmpApMatPerp.setBounds(28, 100, 155, 14);
		panelMov.add(lblEmpApMatPerp);

		lblEmpIdPerp.setForeground(new Color(0, 0, 0));
		lblEmpIdPerp.setBounds(28, 66, 65, 14);
		panelMov.add(lblEmpIdPerp);


		separatorNomrbeId.setBackground(new Color(255, 255, 255));
		separatorNomrbeId.setForeground(new Color(211, 211, 211));
		separatorNomrbeId.setOrientation(SwingConstants.VERTICAL);
		separatorNomrbeId.setBounds(67, 66, 5, 14);
		panelMov.add(separatorNomrbeId);

		JLabel lblDeducciones = new JLabel("Deducciones");
		lblDeducciones.setForeground(new Color(0, 0, 0));
		lblDeducciones.setBounds(1159, 34, 82, 14);
		panelMov.add(lblDeducciones);

		JScrollPane scrollPanePercepciones = new JScrollPane();
		scrollPanePercepciones.setViewportBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		scrollPanePercepciones.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPanePercepciones.setBounds(851, 49, 281, 109);
		scrollPanePercepciones.setViewportView(tableMovPercepciones);
		panelMov.add(scrollPanePercepciones);

		JScrollPane scrollPaneDeducciones = new JScrollPane();
		scrollPaneDeducciones.setViewportBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		scrollPaneDeducciones.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneDeducciones.setViewportView(tableMovDeducciones);
		scrollPaneDeducciones.setBounds(1159, 49, 281, 109);

		panelMov.add(scrollPaneDeducciones);

		/*JButton btnEmpPerpOmi = new JButton("<---");
		btnEmpPerpOmi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				if(tableMovEmpNewPerp.getSelectedRow() != -1)
//				{
//					DefaultTableModel dlmAdd = (DefaultTableModel) tableMovEmpPerp.getModel();
//					int fsel = tableMovEmpNewPerp.getSelectedRow();
//					String filaElem [] = {tableMovEmpNewPerp.getValueAt(fsel, 0).toString(),tableMovEmpNewPerp.getValueAt(fsel, 1).toString()};
//					dlmAdd.addRow(filaElem);
//
//					DefaultTableModel dlmDel = (DefaultTableModel) tableMovEmpNewPerp.getModel();
//					dlmDel.removeRow(tableMovEmpNewPerp.getSelectedRow());
//				}else {
//					JOptionPane.showMessageDialog(null, "Debe seleccionar una deducción","Advertencia", JOptionPane.WARNING_MESSAGE );
//				}


				DefaultTableModel tmO = (DefaultTableModel) tableMovEmpNewPerp.getModel();
				//JOptionPane.showMessageDialog(null, "tableMovEmpPerp: "+ tableMovEmpPerp.getRowCount());
				DefaultTableModel tmD = (DefaultTableModel) tableMovEmpPerp.getModel();
				//JOptionPane.showMessageDialog(null, "tableMovEmpNewPerp: "+ tableMovEmpNewPerp.getRowCount());
				int fila = tableMovEmpNewPerp.getSelectedRow();
				//JOptionPane.showMessageDialog(null, "filaSeleccionada: "+ fila);
				try {
					if (fila != -1) {
						//int nColumns = tableMovAgr.getColumnCount();
						Object[] filaSeleccionada = {
								tmO.getValueAt(fila, 0), tmO.getValueAt(fila, 1),tmO.getValueAt(fila, 2)};
						//JOptionPane.showMessageDialog(null, "tmO: "+ tmO.getValueAt(fila, 0));
						//JOptionPane.showMessageDialog(null, "tmO: "+ tmO.getValueAt(fila, 1));
						//JOptionPane.showMessageDialog(null, "tmO: "+ tmO.getValueAt(fila, 2));
						for (int i = 0; i < filaSeleccionada.length; ++i)
							filaSeleccionada[i] = tmO.getValueAt(fila, i);
						tmD.addRow(filaSeleccionada);
					}else {
						JOptionPane.showMessageDialog(null, "Debe seleccionar una percepción","Advertencia", JOptionPane.WARNING_MESSAGE );
					}
				}catch(Exception exx) {
					exx.printStackTrace();
				}


			}
		});
		btnEmpPerpOmi.setToolTipText("Quitar Percepción del Empleado");
		btnEmpPerpOmi.setBounds(388, 224, 89, 23);
		panelMov.add(btnEmpPerpOmi);*/

		//		JButton btnEmpDedOmi = new JButton("<---");
		//		btnEmpDedOmi.setToolTipText("Quitar Percepción");
		//		btnEmpDedOmi.setBounds(1232, 224, 89, 23);
		//		panelMov.add(btnEmpDedOmi);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(new Color(211, 211, 211));
		separator_1.setBounds(851, 23, 272, 2);
		panelMov.add(separator_1);

		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(new Color(211, 211, 211));
		separator_2.setBounds(1159, 23, 281, 8);
		panelMov.add(separator_2);


		lblPuestoEmpMov.setForeground(new Color(0, 0, 0));
		lblPuestoEmpMov.setBounds(95, 119, 244, 14);
		panelMov.add(lblPuestoEmpMov);


		lblEmpURMov.setForeground(new Color(0, 0, 0));
		lblEmpURMov.setBounds(95, 141, 272, 14);
		panelMov.add(lblEmpURMov);


		btnAgregarListaNomina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

//				DefaultTableModel tmEmpNewPerp = (DefaultTableModel) tableMovEmpNewPerp.getModel();
//				DefaultTableModel tmEmNewDed = (DefaultTableModel) tableMovEmpNewDed.getModel();

//				int confirmado = JOptionPane.showConfirmDialog(null,"¿El Empleado(a) tiene Faltas Injustificadas?");

//				if (JOptionPane.OK_OPTION == confirmado) {
//					FormularioPrincipal.desktopPane.add(internalFrameFaltas);
//					internalFrameFaltas.btnCalcularFalta.setVisible(false);
//					internalFrameFaltas.scrollPaneMvtos.setVisible(false);
//					internalFrameFaltas.btnSeleccionarMvtos.setVisible(false);
//					internalFrameFaltas.lblClave.setVisible(false);
//					internalFrameFaltas.lblFecha.setVisible(false);
//					internalFrameFaltas.lblEntraTurno.setVisible(false);
//					internalFrameFaltas.lblEntrada.setVisible(false);
//					internalFrameFaltas.lblSalidaTurno.setVisible(false);
//					internalFrameFaltas.lblSalida.setVisible(false);
//					internalFrameFaltas.lblIncidencia.setVisible(false);
//					internalFrameFaltas.lblMotivo.setVisible(false);
//					internalFrameFaltas.lblEstacion.setVisible(false);
//					internalFrameFaltas.lblInhabil.setVisible(false);
//					internalFrameFaltas.btnFaltas.setVisible(false);
//
//					internalFrameFaltas.mostrarEmpleadosChecador();
//					internalFrameFaltas.mostrarEmpleados(lblEmpIdPerp.getText());
//					internalFrameFaltas.show();
//					internalFrameFaltas.setIconifiable(true);
//					internalFrameFaltas.setClosable(true);
//					internalFrameFaltas.setResizable(true);
//					internalFrameFaltas.setMaximizable(true);
//					internalFrameFaltas.setVisible(true);
//					internalFrameFaltas.toFront();
//				}else if(JOptionPane.NO_OPTION == confirmado){
//					if(tmEmpNewPerp.getRowCount()>0 || tmEmNewDed.getRowCount()>0) {

						insertarEmpleadoReporteNomina();

//					}else {
//						JOptionPane.showMessageDialog(null, "Debe agregar un movimiento","Advertencia", JOptionPane.WARNING_MESSAGE );
					}
//				}




//			}
		});
		btnAgregarListaNomina.setBounds(478, 581, 183, 28);
		btnAgregarListaNomina.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("accept.png"))));
		panelMov.add(btnAgregarListaNomina);


		lblIdPuestoEmpMov.setForeground(new Color(0, 0, 0));
		lblIdPuestoEmpMov.setBounds(28, 119, 55, 14);
		panelMov.add(lblIdPuestoEmpMov);

		lblIdUREmpMov.setForeground(new Color(0, 0, 0));
		lblIdUREmpMov.setBounds(28, 141, 55, 14);
		panelMov.add(lblIdUREmpMov);


		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setForeground(new Color(211, 211, 211));
		separator_3.setBackground(Color.WHITE);
		separator_3.setBounds(67, 119, 5, 14);
		panelMov.add(separator_3);


		separator_4.setOrientation(SwingConstants.VERTICAL);
		separator_4.setForeground(new Color(211, 211, 211));
		separator_4.setBackground(Color.WHITE);
		separator_4.setBounds(67, 143, 5, 14);
		panelMov.add(separator_4);

		separator_5.setOrientation(SwingConstants.VERTICAL);
		separator_5.setForeground(new Color(211, 211, 211));
		separator_5.setBackground(Color.WHITE);
		separator_5.setBounds(334, 80, 5, 78);
		panelMov.add(separator_5);
		lblAuxiliarSalario.setForeground(new Color(0, 0, 0));


		//		btnBuscarSalario.addActionListener(new ActionListener() {
		//			public void actionPerformed(ActionEvent e) {
		//				InternalFrameNomina nomina =  new InternalFrameNomina();
		//				Utilidades.windowOpened(e);
		//				FormularioPrincipal.desktopPane.add(nomina);
		//				nomina.mostrarPuestoSalarioEmpleado();
		//				nomina.show();
		//				nomina.setBounds(450,250, 750, 550);
		//				nomina.setFrameIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("img/user_add.png"))));
		//				nomina.setIconifiable(true);
		//				nomina.setClosable(true);
		//				nomina.setResizable(true);
		//				nomina.setMaximizable(true);
		//				nomina.setVisible(true);
		//				nomina.toFront();
		//			}
		//		});
		//		btnBuscarSalario.setBounds(805, 138, 56, 23);
		//		panelMov.add(btnBuscarSalario);
		lblAuxiliarSalario.setBounds(512, 215, 89, 14);

		panelMov.add(lblAuxiliarSalario);
		/*
		JButton btnNewButton = new JButton("Jasper Report");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Utilidades.windowOpened(e);
				PoolNYCH nych = new PoolNYCH();
				Connection con =null;
				try {
					con = nych.datasource.getConnection();

					//					ArrayList<String> listaIdEmple = new ArrayList<String>();
					//					listaIdEmple = getEmple();
					//					for(int i = 0; i<listaIdEmple.size();i++){
					//						listaIdEmple.get(i);
					//					}


					//					Map<String,Object> parametro = new HashMap<String,Object>();
					//					parametro.put("emp", lblEmpIdPerp.getText());
					//					System.out.println("parametro: "+ listaIdEmple.get(0));


//					JasperPrint jasper = null;
//					jasper = JasperFillManager.fillReport(new File(".").getAbsolutePath()+"/src/main/java/ti/snfco/NominaYCapitalHumano/reportes/Reporte.jasper",null,con);
//					JasperViewer jasperViewer = new JasperViewer(jasper);
//					jasperViewer.setTitle("Reporte Nomina");
//					jasperViewer.setVisible(true);
				}catch(Exception ep) {
					ep.printStackTrace();
					StringWriter errors = new StringWriter();
					ep.printStackTrace(new PrintWriter(errors));
					LOG.info("Excepcion: "+ errors );
				}
			}

		});
		btnNewButton.setBounds(1601, 23, 184, 66);
		panelMov.add(btnNewButton);
		lblclavechecador.setForeground(new Color(0, 0, 0));
		 */
		lblclavechecador.setBounds(512, 179, 61, 14);
		panelMov.add(lblclavechecador);
		lblFechaMvto.setForeground(new Color(0, 0, 0));


		lblFechaMvto.setBounds(512, 196, 201, 14);
		panelMov.add(lblFechaMvto);
		lblClave.setForeground(new Color(0, 0, 0));
		lblClave.setBounds(338, 83, 125, 14);

		panelMov.add(lblClave);
		lblFechaDeFalta.setForeground(new Color(0, 0, 0));
		lblFechaDeFalta.setBounds(338, 100, 125, 14);

		panelMov.add(lblFechaDeFalta);
		lblSalarioAuxiliar.setForeground(new Color(0, 0, 0));
		lblSalarioAuxiliar.setBounds(338, 119, 175, 14);

		panelMov.add(lblSalarioAuxiliar);
		lblTituloChecador.setForeground(new Color(0, 0, 0));
		lblTituloChecador.setBounds(338, 58, 227, 14);

		panelMov.add(lblTituloChecador);

		lblTipoNominaOculta.setBounds(221, 23, 207, 14);
		lblTipoNominaOculta.setVisible(false);
		panelMov.add(lblTipoNominaOculta);

		lblTipoNominaOcultaSemanal.setBounds(438, 23, 113, 14);
		lblTipoNominaOcultaSemanal.setVisible(false);
		panelMov.add(lblTipoNominaOcultaSemanal);

		JLabel label = new JLabel("Percepciones");
		label.setForeground(Color.BLACK);
		label.setBounds(11, 166, 82, 14);
		panelMov.add(label);
		label_1.setForeground(Color.BLACK);
		label_1.setBounds(10, 370, 82, 14);

		panelMov.add(label_1);

		//				JButton btnNewButton = new JButton("New button");
		//				btnNewButton.addActionListener(new ActionListener() {
		//					public void actionPerformed(ActionEvent e) {
		//						isrGlobal();
		//					}
		//				});
		//				btnNewButton.setBounds(20, 569, 89, 23);
		//				panelMov.add(btnNewButton);

		//
		//		textArea.setWrapStyleWord(true);
		//		textArea.setAutoscrolls(true);
		//		textArea.setLineWrap (true);
		//		//		textArea.setPreferredSize(new Dimension (10,100));
		//		textArea.setBounds(1495, 28, 314, 154);
		//		panelMov.add(textArea);


		//		editorPane.setBounds(673, 11, 786, 243);
		//		panelMov.add(editorPane);

		//		btnNewButton.addActionListener(new ActionListener() {
		//			public void actionPerformed(ActionEvent e) {
		//
		//
		//
		//			}
		//		});
		//		btnNewButton.setBounds(1537, 187, 89, 23);
		//		panelMov.add(btnNewButton);

		//		JInternalFrame internalFrameProceso = new JInternalFrame("New JInternalFrame");
		//		internalFrameProceso.setBounds(791, 23, 713, 230);
		//		internalFrameProceso.getContentPane().setLayout(null);
		//		internalFrameProceso.getContentPane().add(panelProceso);
		//		internalFrameProceso.setIconifiable(true);
		//		internalFrameProceso.setClosable(true);
		//		internalFrameProceso.setResizable(true);
		//		internalFrameProceso.setMaximizable(true);
		//		internalFrameProceso.setVisible(true);
		//		panelMov.add(internalFrameProceso);

		//		panelProceso.setBounds(0, 0, 697, 201);
		//		panelProceso.setLayout(null);
		//
		//		lblProceso.setBounds(0, 0, 695, 200);
		//		Font fuente = new Font("Arial", Font.BOLD, 14); 
		//		lblProceso.setFont(fuente);
		//		lblProceso.setAutoscrolls(true);
		//		lblProceso.setHorizontalAlignment(JLabel.CENTER);
		//		panelProceso.add(lblProceso);

		//						textFieldPeriodo = new JTextField();
		//						textFieldPeriodo.setBounds(554, 94, 293, 30);
		//						panelMov.add(textFieldPeriodo);
		//						textFieldPeriodo.setColumns(10);
		lblFondo.setToolTipText("Percepciones y Deducciones");
		lblFondo.setBounds(0, 11, 1487, 658);
		lblFondo.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("walll.png"))));
		panelMov.add(lblFondo);


		//		JButton btnNewButton = new JButton("isr");
		//		btnNewButton.addActionListener(new ActionListener() {
		//			public void actionPerformed(ActionEvent e) {
		//				calcularISR();
		//				System.exit(0);
		//			}
		//		});
		//		btnNewButton.setBounds(906, 11, 89, 23);
		//		panelMov.add(btnNewButton);

		//		JButton btnNewButton_1 = new JButton("imss");
		//		btnNewButton_1.addActionListener(new ActionListener() {
		//			public void actionPerformed(ActionEvent e) {
		//				calcularIMSS();
		//				System.exit(0);
		//			}
		//		});
		//		btnNewButton_1.setBounds(1005, 11, 89, 23);
		//		panelMov.add(btnNewButton_1);

		//		JButton btnNewButton_2 = new JButton("Infonavit");
		//		btnNewButton_2.addActionListener(new ActionListener() {
		//			public void actionPerformed(ActionEvent e) {
		//				calcularInfonavit();
		//				System.exit(0);
		//			}
		//		});
		//		btnNewButton_2.setBounds(1106, 13, 89, 23);
		//		panelMov.add(btnNewButton_2);


		//		btnBuscarValor.setBounds(805, 160, 56, 23);
		//		panelMov.add(btnBuscarValor);



	}

	public void limpiarEmpleadoReporteNomina() {
		DefaultTableModel tmOP = (DefaultTableModel) tableMovEmpPerp.getModel();
		DefaultTableModel tmOD = (DefaultTableModel) tableMovEmpDed.getModel();
		DefaultTableModel tmDP = (DefaultTableModel) tableMovEmpNewPerp.getModel();
		DefaultTableModel tmDD = (DefaultTableModel) tableMovEmpNewDed.getModel();

		tmOP.setRowCount(0);
		tmOD.setRowCount(0);
		tmDP.setRowCount(0);
		tmDD.setRowCount(0);
		lblEmpIdPerp.setText("");
		lblEmplNombrePerp.setText("");
		lblEmpApPatPerp.setText("");
		lblEmpApMatPerp.setText("");
		lblIdPuestoEmpMov.setText("");
		lblPuestoEmpMov.setText("");
		lblIdUREmpMov.setText("");
		lblEmpURMov.setText("");


	}


	//El metodo esta adapatado solo para 10 percepciones y 10 deducciones, si se requirieran mas, se tiene que modificar
	@SuppressWarnings("static-access")
	public int insertarEmpleadoReporteNomina() {
		int resultado = 0;
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date diaHoy = Calendar.getInstance().getTime();
		DefaultTableModel tmEmpNewPerp = (DefaultTableModel) tableMovEmpNewPerp.getModel();
		System.out.println("."+tmEmpNewPerp.getRowCount());
		DefaultTableModel tmEmNewDed = (DefaultTableModel) tableMovEmpNewDed.getModel();
		PoolNYCH nych = new PoolNYCH();
		Connection con1 =null,con2 =null,con3 =null,con4 =null,con5 =null,con6 =null,con7 =null,con8 =null,con9 =null,con10 =null,con11 =null,con12 =null,con13 =null,con14 =null,con15 =null,con16 =null,
				con17 =null,con18 =null,con19 =null,con20 =null,con21 =null;
		PreparedStatement pps1,pps2,pps3,pps4,pps5,pps6,pps7,pps8,pps9,pps10,pps11,pps12,pps13,pps14,pps15,pps16,pps17,pps18,pps19,pps20,pps21;
		String sqlInsert1 = "",sqlInsert2= "",sqlInsert3= "",sqlInsert4= "",sqlInsert5= "",sqlInsert6= "",sqlInsert7= "",sqlInsert8= "",sqlInsert9= "",sqlInsert10= "",sqlInsert11= "",
				sqlInsert12= "",sqlInsert13= "",sqlInsert14= "",sqlInsert15= "",sqlInsert16= "",sqlInsert17= "",sqlInsert18= "",sqlInsert19= "",sqlInsert20= "",sqlInsert21= "";
		try {

			System.out.println("PERCEPCIONES");
			if(tmEmpNewPerp.getRowCount() > 0){
				sqlInsert1="INSERT INTO DBO.REPORTE_NOMINA_PERCEPCIONES (ID_EMPLEADO,ID_PUESTO,ID_UNIDAD,ID_PERCEPCION,VALOR_PERCEPCION,FECHA_CALCULO,ID_TIPO_NOMINA,PERIODO) "
						+ "VALUES ('"+lblEmpIdPerp.getText() + "','"+ lblIdPuestoEmpMov.getText() +"','"+lblIdUREmpMov.getText() +"','"+ tmEmpNewPerp.getValueAt(0, 0) +"','"+tmEmpNewPerp.getValueAt(0, 3)+"',convert(datetime,'"+ formatter.format(diaHoy)+"',101),'"+ FormularioPrincipal.lblIdTipoNomina.getText() +"','"+FormularioPrincipal.lblPeriodoNumero.getText()+"')";
				System.out.println("sqlInsert1: " + sqlInsert1);
			}

			if(tmEmpNewPerp.getRowCount() > 1){
				sqlInsert2="INSERT INTO DBO.REPORTE_NOMINA_PERCEPCIONES (ID_EMPLEADO,ID_PUESTO,ID_UNIDAD,ID_PERCEPCION,VALOR_PERCEPCION,FECHA_CALCULO,ID_TIPO_NOMINA,PERIODO) "
						+ "VALUES ('"+lblEmpIdPerp.getText() + "','"+ lblIdPuestoEmpMov.getText() +"','"+lblIdUREmpMov.getText() +"','"+ tmEmpNewPerp.getValueAt(1, 0) +"','"+tmEmpNewPerp.getValueAt(1, 3)+"',convert(datetime,'"+ formatter.format(diaHoy)+"',101),'"+ FormularioPrincipal.lblIdTipoNomina.getText() +"','"+FormularioPrincipal.lblPeriodoNumero.getText()+"')";
				System.out.println("sqlInsert2: " + sqlInsert2);
			}

			if(tmEmpNewPerp.getRowCount() > 2){
				sqlInsert3="INSERT INTO DBO.REPORTE_NOMINA_PERCEPCIONES (ID_EMPLEADO,ID_PUESTO,ID_UNIDAD,ID_PERCEPCION,VALOR_PERCEPCION,FECHA_CALCULO,ID_TIPO_NOMINA,PERIODO) "
						+ "VALUES ('"+lblEmpIdPerp.getText() + "','"+ lblIdPuestoEmpMov.getText() +"','"+lblIdUREmpMov.getText() +"','"+ tmEmpNewPerp.getValueAt(2, 0) +"','"+tmEmpNewPerp.getValueAt(2, 3)+"',convert(datetime,'"+ formatter.format(diaHoy)+"',101),'"+ FormularioPrincipal.lblIdTipoNomina.getText() +"','"+FormularioPrincipal.lblPeriodoNumero.getText()+"')";
				System.out.println("sqlInsert3: " + sqlInsert3);
			}

			if(tmEmpNewPerp.getRowCount() > 3){
				sqlInsert4="INSERT INTO DBO.REPORTE_NOMINA_PERCEPCIONES (ID_EMPLEADO,ID_PUESTO,ID_UNIDAD,ID_PERCEPCION,VALOR_PERCEPCION,FECHA_CALCULO,ID_TIPO_NOMINA,PERIODO) "
						+ "VALUES ('"+lblEmpIdPerp.getText() + "','"+ lblIdPuestoEmpMov.getText() +"','"+lblIdUREmpMov.getText() +"','"+ tmEmpNewPerp.getValueAt(3, 0) +"','"+tmEmpNewPerp.getValueAt(3, 3)+"',convert(datetime,'"+ formatter.format(diaHoy)+"',101),'"+ FormularioPrincipal.lblIdTipoNomina.getText() +"','"+FormularioPrincipal.lblPeriodoNumero.getText()+"')";
				System.out.println("sqlInsert4: " + sqlInsert4);
			}

			if(tmEmpNewPerp.getRowCount() > 4){
				sqlInsert5="INSERT INTO DBO.REPORTE_NOMINA_PERCEPCIONES (ID_EMPLEADO,ID_PUESTO,ID_UNIDAD,ID_PERCEPCION,VALOR_PERCEPCION,FECHA_CALCULO,ID_TIPO_NOMINA,PERIODO) "
						+ "VALUES ('"+lblEmpIdPerp.getText() + "','"+ lblIdPuestoEmpMov.getText() +"','"+lblIdUREmpMov.getText() +"','"+ tmEmpNewPerp.getValueAt(4, 0) +"','"+tmEmpNewPerp.getValueAt(4, 3)+"',convert(datetime,'"+ formatter.format(diaHoy)+"',101),'"+ FormularioPrincipal.lblIdTipoNomina.getText() +"','"+FormularioPrincipal.lblPeriodoNumero.getText()+"')";
				System.out.println("sqlInsert5: " + sqlInsert5);
			}

			if(tmEmpNewPerp.getRowCount() > 5){
				sqlInsert6="INSERT INTO DBO.REPORTE_NOMINA_PERCEPCIONES (ID_EMPLEADO,ID_PUESTO,ID_UNIDAD,ID_PERCEPCION,VALOR_PERCEPCION,FECHA_CALCULO,ID_TIPO_NOMINA,PERIODO) "
						+ "VALUES ('"+lblEmpIdPerp.getText() + "','"+ lblIdPuestoEmpMov.getText() +"','"+lblIdUREmpMov.getText() +"','"+ tmEmpNewPerp.getValueAt(5, 0) +"','"+tmEmpNewPerp.getValueAt(5, 3)+"',convert(datetime,'"+ formatter.format(diaHoy)+"',101),'"+ FormularioPrincipal.lblIdTipoNomina.getText() +"','"+FormularioPrincipal.lblPeriodoNumero.getText()+"')";
				System.out.println("sqlInsert6: " + sqlInsert6);
			}

			if(tmEmpNewPerp.getRowCount() > 6){
				sqlInsert7="INSERT INTO DBO.REPORTE_NOMINA_PERCEPCIONES (ID_EMPLEADO,ID_PUESTO,ID_UNIDAD,ID_PERCEPCION,VALOR_PERCEPCION,FECHA_CALCULO,ID_TIPO_NOMINA,PERIODO) "
						+ "VALUES ('"+lblEmpIdPerp.getText() + "','"+ lblIdPuestoEmpMov.getText() +"','"+lblIdUREmpMov.getText() +"','"+ tmEmpNewPerp.getValueAt(6, 0) +"','"+tmEmpNewPerp.getValueAt(6, 3)+"',convert(datetime,'"+ formatter.format(diaHoy)+"',101),'"+ FormularioPrincipal.lblIdTipoNomina.getText() +"','"+FormularioPrincipal.lblPeriodoNumero.getText()+"')";
				System.out.println("sqlInsert7: " + sqlInsert7);
			}
			if(tmEmpNewPerp.getRowCount() > 7){
				sqlInsert8="INSERT INTO DBO.REPORTE_NOMINA_PERCEPCIONES (ID_EMPLEADO,ID_PUESTO,ID_UNIDAD,ID_PERCEPCION,VALOR_PERCEPCION,FECHA_CALCULO,ID_TIPO_NOMINA,PERIODO) "
						+ "VALUES ('"+lblEmpIdPerp.getText() + "','"+ lblIdPuestoEmpMov.getText() +"','"+lblIdUREmpMov.getText() +"','"+ tmEmpNewPerp.getValueAt(7, 0) +"','"+tmEmpNewPerp.getValueAt(7, 3)+"',convert(datetime,'"+ formatter.format(diaHoy)+"',101)','"+ FormularioPrincipal.lblIdTipoNomina.getText() +"','"+FormularioPrincipal.lblPeriodoNumero.getText()+"')";
				System.out.println("sqlInsert8: " + sqlInsert8);
			}
			if(tmEmpNewPerp.getRowCount() > 8){
				sqlInsert9="INSERT INTO DBO.REPORTE_NOMINA_PERCEPCIONES (ID_EMPLEADO,ID_PUESTO,ID_UNIDAD,ID_PERCEPCION,VALOR_PERCEPCION,FECHA_CALCULO,ID_TIPO_NOMINA,PERIODO) "
						+ "VALUES ('"+lblEmpIdPerp.getText() + "','"+ lblIdPuestoEmpMov.getText() +"','"+lblIdUREmpMov.getText() +"','"+ tmEmpNewPerp.getValueAt(8, 0) +"','"+tmEmpNewPerp.getValueAt(8, 3)+"',convert(datetime,'"+ formatter.format(diaHoy)+"',101),'"+ FormularioPrincipal.lblIdTipoNomina.getText() +"','"+FormularioPrincipal.lblPeriodoNumero.getText()+"')";
				System.out.println("sqlInsert9: " + sqlInsert9);
			}
			if(tmEmpNewPerp.getRowCount() > 9){
				sqlInsert10="INSERT INTO DBO.REPORTE_NOMINA_PERCEPCIONES (ID_EMPLEADO,ID_PUESTO,ID_UNIDAD,ID_PERCEPCION,VALOR_PERCEPCION,FECHA_CALCULO,ID_TIPO_NOMINA,PERIODO) "
						+ "VALUES ('"+lblEmpIdPerp.getText() + "','"+ lblIdPuestoEmpMov.getText() +"','"+lblIdUREmpMov.getText() +"','"+ tmEmpNewPerp.getValueAt(9, 0) +"','"+tmEmpNewPerp.getValueAt(9, 3)+"',convert(datetime,'"+ formatter.format(diaHoy)+"',101),'"+ FormularioPrincipal.lblIdTipoNomina.getText() +"','"+FormularioPrincipal.lblPeriodoNumero.getText()+"')";
				System.out.println("sqlInsert10: " + sqlInsert10);
			}
			if(tmEmpNewPerp.getRowCount() > 10){
				sqlInsert11="INSERT INTO DBO.REPORTE_NOMINA_PERCEPCIONES (ID_EMPLEADO,ID_PUESTO,ID_UNIDAD,ID_PERCEPCION,VALOR_PERCEPCION,FECHA_CALCULO,ID_TIPO_NOMINA,PERIODO) "
						+ "VALUES ('"+lblEmpIdPerp.getText() + "','"+ lblIdPuestoEmpMov.getText() +"','"+lblIdUREmpMov.getText() +"','"+ tmEmpNewPerp.getValueAt(10, 0) +"','"+tmEmpNewPerp.getValueAt(10, 3)+"',convert(datetime,'"+ formatter.format(diaHoy)+"',101),'"+ FormularioPrincipal.lblIdTipoNomina.getText() +"','"+FormularioPrincipal.lblPeriodoNumero.getText()+"')";
				System.out.println("sqlInsert11: " + sqlInsert12);
			}


			System.out.println("DEDUCCIONES");
			//deducciones
			if(tmEmNewDed.getRowCount() > 0){

				if(tmEmNewDed.getValueAt(0, 4)== null) {
					int parcilidad =0;
				}else{

				}

				sqlInsert12="INSERT INTO DBO.REPORTE_NOMINA_DEDUCCIONES (ID_EMPLEADO,ID_PUESTO,ID_UNIDAD,ID_DEDUCCION,VALOR_DEDUCCION,FECHA_CALCULO,ID_TIPO_NOMINA,PERIODO,PARCIALIDADES) "
						+ "VALUES ('"+lblEmpIdPerp.getText() + "','"+ lblIdPuestoEmpMov.getText() +"','"+lblIdUREmpMov.getText() +"','"+ tmEmNewDed.getValueAt(0, 0) +"','"+tmEmNewDed.getValueAt(0, 3) +"',convert(datetime,'"+ formatter.format(diaHoy)+"',101),'"+ FormularioPrincipal.lblIdTipoNomina.getText() +"','"+FormularioPrincipal.lblPeriodoNumero.getText()+"','"+tmEmNewDed.getValueAt(0, 4)+"')";
				System.out.println("sqlInsert12: " + sqlInsert12);
			}

			if(tmEmNewDed.getRowCount() > 1){
				sqlInsert13="INSERT INTO DBO.REPORTE_NOMINA_DEDUCCIONES (ID_EMPLEADO,ID_PUESTO,ID_UNIDAD,ID_DEDUCCION,VALOR_DEDUCCION,FECHA_CALCULO,ID_TIPO_NOMINA,PERIODO,PARCIALIDADES) "
						+ "VALUES ('"+lblEmpIdPerp.getText() + "','"+ lblIdPuestoEmpMov.getText() +"','"+lblIdUREmpMov.getText() +"','"+ tmEmNewDed.getValueAt(1, 0) +"','"+tmEmNewDed.getValueAt(1, 3) +"',convert(datetime,'"+ formatter.format(diaHoy)+"',101),'"+ FormularioPrincipal.lblIdTipoNomina.getText() +"','"+FormularioPrincipal.lblPeriodoNumero.getText()+"','"+tmEmNewDed.getValueAt(1, 4)+"')";
				System.out.println("sqlInsert13: " + sqlInsert13);
			}

			if(tmEmNewDed.getRowCount() > 2){
				sqlInsert14="INSERT INTO DBO.REPORTE_NOMINA_DEDUCCIONES (ID_EMPLEADO,ID_PUESTO,ID_UNIDAD,ID_DEDUCCION,VALOR_DEDUCCION,FECHA_CALCULO,ID_TIPO_NOMINA,PERIODO,PARCIALIDADES) "
						+ "VALUES ('"+lblEmpIdPerp.getText() + "','"+ lblIdPuestoEmpMov.getText() +"','"+lblIdUREmpMov.getText() +"','"+ tmEmNewDed.getValueAt(2, 0) +"','"+tmEmNewDed.getValueAt(2, 3) +"',convert(datetime,'"+ formatter.format(diaHoy)+"',101),'"+ FormularioPrincipal.lblIdTipoNomina.getText() +"','"+FormularioPrincipal.lblPeriodoNumero.getText()+"','"+tmEmNewDed.getValueAt(2, 4)+"')";
				System.out.println("sqlInsert14: " + sqlInsert14);
			}

			if(tmEmNewDed.getRowCount() > 3){
				sqlInsert15="INSERT INTO DBO.REPORTE_NOMINA_DEDUCCIONES (ID_EMPLEADO,ID_PUESTO,ID_UNIDAD,ID_DEDUCCION,VALOR_DEDUCCION,FECHA_CALCULO,ID_TIPO_NOMINA,PERIODO,PARCIALIDADES) "
						+ "VALUES ('"+lblEmpIdPerp.getText() + "','"+ lblIdPuestoEmpMov.getText() +"','"+lblIdUREmpMov.getText() +"','"+ tmEmNewDed.getValueAt(3, 0) +"','"+tmEmNewDed.getValueAt(3, 3) +"',convert(datetime,'"+ formatter.format(diaHoy)+"',101),'"+ FormularioPrincipal.lblIdTipoNomina.getText() +"','"+FormularioPrincipal.lblPeriodoNumero.getText()+"','"+tmEmNewDed.getValueAt(3, 4)+"')";
				System.out.println("sqlInsert15: " + sqlInsert15);
			}

			if(tmEmNewDed.getRowCount() > 4){
				sqlInsert16="INSERT INTO DBO.REPORTE_NOMINA_DEDUCCIONES (ID_EMPLEADO,ID_PUESTO,ID_UNIDAD,ID_DEDUCCION,VALOR_DEDUCCION,FECHA_CALCULO,ID_TIPO_NOMINA,PERIODO,PARCIALIDADES) "
						+ "VALUES ('"+lblEmpIdPerp.getText() + "','"+ lblIdPuestoEmpMov.getText() +"','"+lblIdUREmpMov.getText() +"','"+ tmEmNewDed.getValueAt(4, 0) +"','"+tmEmNewDed.getValueAt(4, 3) +"',convert(datetime,'"+ formatter.format(diaHoy)+"',101),'"+ FormularioPrincipal.lblIdTipoNomina.getText() +"','"+FormularioPrincipal.lblPeriodoNumero.getText()+"','"+tmEmNewDed.getValueAt(4, 4)+"')";
				System.out.println("sqlInsert16: " + sqlInsert16);
			}

			if(tmEmNewDed.getRowCount() > 5){
				sqlInsert17="INSERT INTO DBO.REPORTE_NOMINA_DEDUCCIONES (ID_EMPLEADO,ID_PUESTO,ID_UNIDAD,ID_DEDUCCION,VALOR_DEDUCCION,FECHA_CALCULO,ID_TIPO_NOMINA,PERIODO,PARCIALIDADES) "
						+ "VALUES ('"+lblEmpIdPerp.getText() + "','"+ lblIdPuestoEmpMov.getText() +"','"+lblIdUREmpMov.getText() +"','"+ tmEmNewDed.getValueAt(5, 0) +"','"+tmEmNewDed.getValueAt(5, 3) +"',convert(datetime,'"+ formatter.format(diaHoy)+"',101),'"+ FormularioPrincipal.lblIdTipoNomina.getText() +"','"+FormularioPrincipal.lblPeriodoNumero.getText()+"','"+tmEmNewDed.getValueAt(5, 4)+"')";
				System.out.println("sqlInsert17: " + sqlInsert17);
			}

			if(tmEmNewDed.getRowCount() > 6){
				sqlInsert18="INSERT INTO DBO.REPORTE_NOMINA_DEDUCCIONES (ID_EMPLEADO,ID_PUESTO,ID_UNIDAD,ID_DEDUCCION,VALOR_DEDUCCION,FECHA_CALCULO,ID_TIPO_NOMINA,PERIODO,PARCIALIDADES) "
						+ "VALUES ('"+lblEmpIdPerp.getText() + "','"+ lblIdPuestoEmpMov.getText() +"','"+lblIdUREmpMov.getText() +"','"+ tmEmNewDed.getValueAt(6, 0) +"','"+tmEmNewDed.getValueAt(6, 3) +"',convert(datetime,'"+ formatter.format(diaHoy)+"',101),'"+ FormularioPrincipal.lblIdTipoNomina.getText() +"','"+FormularioPrincipal.lblPeriodoNumero.getText()+"','"+tmEmNewDed.getValueAt(6, 4)+"')";
				System.out.println("sqlInsert18: " + sqlInsert18);
			}
			if(tmEmNewDed.getRowCount() > 7){
				sqlInsert19="INSERT INTO DBO.REPORTE_NOMINA_DEDUCCIONES (ID_EMPLEADO,ID_PUESTO,ID_UNIDAD,ID_DEDUCCION,VALOR_DEDUCCION,FECHA_CALCULO,ID_TIPO_NOMINA,PERIODO,PARCIALIDADES) "
						+ "VALUES ('"+lblEmpIdPerp.getText() + "','"+ lblIdPuestoEmpMov.getText() +"','"+lblIdUREmpMov.getText() +"','"+ tmEmNewDed.getValueAt(7, 0) +"','"+tmEmNewDed.getValueAt(7, 3) +"',convert(datetime,'"+ formatter.format(diaHoy)+"',101),'"+ FormularioPrincipal.lblIdTipoNomina.getText() +"','"+FormularioPrincipal.lblPeriodoNumero.getText()+"','"+tmEmNewDed.getValueAt(7, 4)+"')";
				System.out.println("sqlInsert8: " + sqlInsert8);
			}
			if(tmEmNewDed.getRowCount() > 8){
				sqlInsert9="INSERT INTO DBO.REPORTE_NOMINA_DEDUCCIONES (ID_EMPLEADO,ID_PUESTO,ID_UNIDAD,ID_DEDUCCION,VALOR_DEDUCCION,FECHA_CALCULO,ID_TIPO_NOMINA,PERIODO,PARCIALIDADES) "
						+ "VALUES ('"+lblEmpIdPerp.getText() + "','"+ lblIdPuestoEmpMov.getText() +"','"+lblIdUREmpMov.getText() +"','"+ tmEmNewDed.getValueAt(8, 0) +"','"+tmEmNewDed.getValueAt(8, 3) +"',convert(datetime,'"+ formatter.format(diaHoy)+"',101),'"+ FormularioPrincipal.lblIdTipoNomina.getText() +"','"+FormularioPrincipal.lblPeriodoNumero.getText()+"','"+tmEmNewDed.getValueAt(8, 4)+"')";
				System.out.println("sqlInsert19: " + sqlInsert19);
			}
			if(tmEmNewDed.getRowCount() > 9){
				sqlInsert20="INSERT INTO DBO.REPORTE_NOMINA_DEDUCCIONES (ID_EMPLEADO,ID_PUESTO,ID_UNIDAD,ID_DEDUCCION,VALOR_DEDUCCION,FECHA_CALCULO,ID_TIPO_NOMINA,PERIODO,PARCIALIDADES) "
						+ "VALUES ('"+lblEmpIdPerp.getText() + "','"+ lblIdPuestoEmpMov.getText() +"','"+lblIdUREmpMov.getText() +"','"+ tmEmNewDed.getValueAt(9, 0) +"','"+tmEmNewDed.getValueAt(9, 3) +"',convert(datetime,'"+ formatter.format(diaHoy)+"',101),'"+ FormularioPrincipal.lblIdTipoNomina.getText() +"','"+FormularioPrincipal.lblPeriodoNumero.getText()+"','"+tmEmNewDed.getValueAt(9, 4)+"')";
				System.out.println("sqlInsert20: " + sqlInsert20);
			}
			if(tmEmNewDed.getRowCount() > 10){
				sqlInsert21="INSERT INTO DBO.REPORTE_NOMINA_DEDUCCIONES (ID_EMPLEADO,ID_PUESTO,ID_UNIDAD,ID_DEDUCCION,VALOR_DEDUCCION,FECHA_CALCULO,ID_TIPO_NOMINA,PERIODO,PARCIALIDADES) "
						+ "VALUES ('"+lblEmpIdPerp.getText() + "','"+ lblIdPuestoEmpMov.getText() +"','"+lblIdUREmpMov.getText() +"','"+ tmEmNewDed.getValueAt(10, 0) +"','"+tmEmNewDed.getValueAt(10, 3) +"',convert(datetime,'"+ formatter.format(diaHoy)+"',101),'"+ FormularioPrincipal.lblIdTipoNomina.getText() +"','"+FormularioPrincipal.lblPeriodoNumero.getText()+"','"+tmEmNewDed.getValueAt(10, 4)+"')";
				System.out.println("sqlInsert21: " + sqlInsert21);
			}



			try {
				con1 = nych.datasource.getConnection();con2 = nych.datasource.getConnection();con3 = nych.datasource.getConnection();con4 = nych.datasource.getConnection();con5 = nych.datasource.getConnection();
				con6 = nych.datasource.getConnection();con7 = nych.datasource.getConnection();con8 = nych.datasource.getConnection();con9 = nych.datasource.getConnection();con10 = nych.datasource.getConnection();
				con11 = nych.datasource.getConnection();con12 = nych.datasource.getConnection();con13 = nych.datasource.getConnection();con14 = nych.datasource.getConnection();con15 = nych.datasource.getConnection();
				con16 = nych.datasource.getConnection();con17 = nych.datasource.getConnection();con18 = nych.datasource.getConnection();con19 = nych.datasource.getConnection();con20 = nych.datasource.getConnection();
				con21 = nych.datasource.getConnection();

				pps1 = con1.prepareStatement(sqlInsert1);pps2 = con2.prepareStatement(sqlInsert2);pps3 = con3.prepareStatement(sqlInsert3);pps4 = con4.prepareStatement(sqlInsert4);pps5 = con5.prepareStatement(sqlInsert5);
				pps6 = con6.prepareStatement(sqlInsert6);pps7 = con7.prepareStatement(sqlInsert7);pps8 = con8.prepareStatement(sqlInsert8);pps9 = con9.prepareStatement(sqlInsert9);pps10 = con10.prepareStatement(sqlInsert10);
				pps11 = con11.prepareStatement(sqlInsert11);pps12 = con12.prepareStatement(sqlInsert12);pps13 = con13.prepareStatement(sqlInsert13);pps14 = con14.prepareStatement(sqlInsert14);pps15 = con15.prepareStatement(sqlInsert15);
				pps16 = con16.prepareStatement(sqlInsert16);pps17 = con17.prepareStatement(sqlInsert17);pps18 = con18.prepareStatement(sqlInsert18);pps19 = con19.prepareStatement(sqlInsert19);pps20 = con20.prepareStatement(sqlInsert20);
				pps21 = con21.prepareStatement(sqlInsert21);

				pps1.executeUpdate();pps2.executeUpdate();pps3.executeUpdate();pps4.executeUpdate();
				pps5.executeUpdate();pps6.executeUpdate();pps7.executeUpdate();pps8.executeUpdate();pps9.executeUpdate();pps10.executeUpdate();
				pps11.executeUpdate();pps12.executeUpdate();pps13.executeUpdate();pps14.executeUpdate();pps15.executeUpdate();pps16.executeUpdate();pps17.executeUpdate();pps18.executeUpdate();pps19.executeUpdate();pps20.executeUpdate();
				pps21.executeUpdate();
				JOptionPane.showMessageDialog(null, "Datos Guardados");
			} catch (SQLException el) {
				el.printStackTrace();
				StringWriter errors = new StringWriter();
				el.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: "+ errors );
				JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
			}finally {
				try {
					con1.close();con2.close();con3.close();con4.close();con5.close();con6.close();con7.close();con8.close();con9.close();con10.close();con11.close();con12.close();con13.close();
					con14.close();con15.close();con16.close();con17.close();con18.close();con19.close();con20.close();con21.close();
				} catch (SQLException ep) {
					ep.printStackTrace();
					StringWriter errors = new StringWriter();
					ep.printStackTrace(new PrintWriter(errors));
					LOG.info("Excepcion: "+ errors );
					JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
				}
			}
		}catch(Exception insertarEmpleadoReporteNomina ) {
			insertarEmpleadoReporteNomina.printStackTrace();
			StringWriter errors = new StringWriter();
			insertarEmpleadoReporteNomina.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: "+ errors );
		}
		
		limpiarEmpleadoReporteNomina();
		//		}else {
		//			JOptionPane.showMessageDialog(null, "Seleccione el periodo");
		//		}
		return resultado;

	}

	public double isrGlobal() {
		double isr = 0;
		ArrayList<Double> listaDatosISR = new ArrayList<Double>();
		listaDatosISR = getListaDatosISR();
		System.out.println("*********");
		for(int i = 0; i<listaDatosISR.size();i++){
			System.out.println("indiceDatosISR: "+ i + " |valorDatosISR: " + listaDatosISR.get(i));
		}
		System.out.println("*********");

		ArrayList<Double> listaSubsidioISR = new ArrayList<Double>();
		listaSubsidioISR = getListaSubsidioISR();
		System.out.println("*********");
		for(int i = 0; i<listaSubsidioISR.size();i++){
			System.out.println("indiceSubsidio: "+ i + " |valorSubsidio: " + listaSubsidioISR.get(i));
		}
		System.out.println("*********");

		return isr;
	}

	public double calcularISR(){
		System.out.println("Inicia el proceso de calcularISR");
		double resta = 0;
		double tasa = 0;
		double cuota = 0;
		double isr = 0;
		PoolNYCH nych = new PoolNYCH();
		Connection conSalario = null;
		Statement stSalario;
		ResultSet resultSetSalario;

		ArrayList<Double> listaDatosISR = new ArrayList<Double>();
		listaDatosISR = getListaDatosISR();
		System.out.println("*********");
		for(int i = 0; i<listaDatosISR.size();i++){
			System.out.println("indiceDatosISR: "+ i + " |valorDatosISR: " + listaDatosISR.get(i));
		}
		System.out.println("*********");


		ArrayList<Double> listaSubsidioISR = new ArrayList<Double>();
		listaSubsidioISR = getListaSubsidioISR();
		System.out.println("*********");
		for(int i = 0; i<listaSubsidioISR.size();i++){
			//System.out.println("indiceSubsidio: "+ i + " |valorSubsidio: " + listaSubsidioISR.get(i));
		}
		//System.out.println("*********");

		double cantidadMaximaParaSubsidioPorAño =  listaSubsidioISR.get(20);


		String nombre = lblEmplNombrePerp.getText();
		String ApPat = lblEmpApPatPerp.getText();
		String ApMat = lblEmpApMatPerp.getText();
		String nombreConcat = nombre +" "+ApPat+" "+ApMat;

		Date result = new Date();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String convertido = formatter.format(result);
		String carpeta= "C:\\Informacion de Procesos del NYCH";
		String carpetaInterna= "\\ISR";
		String archivo = "\\ISR_"+convertido+"_"+nombreConcat+".txt";
		File crearCarpeta = new File(carpeta+carpetaInterna);
		File crearArchivo = new File(carpeta+carpetaInterna+archivo);
		if(crearArchivo.exists()) {
			//			System.out.println("el archivo ya existe,pero se sobreescribira");
			//			JOptionPane.showMessageDialog(null,"El archivo de los cálculos está en la carpeta: "+ crearCarpeta);
		}else {
			System.out.println("No existen pero se creara la ruta y el archivo");
			//JOptionPane.showMessageDialog(null,"El archivo de los cálculos está en la carpeta:"+ crearCarpeta);
			crearCarpeta.mkdirs();
			try {
				if(crearArchivo.createNewFile()) {
					System.out.println("archivo creado");
					JOptionPane.showMessageDialog(null,"El archivo de los cálculos está en la carpeta: " + crearCarpeta);
				}else{
					System.out.println("el archivo no fue creado");
					JOptionPane.showMessageDialog(null,"el archivo no fue creado");
				}
			}catch(Exception io) {
				io.printStackTrace();
			}
		}

		try {
			FileWriter fw;

			fw = new FileWriter(crearArchivo);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);


			pw.print("<!DOCTYPE html>");
			pw.println("<html>");
			pw.println("<head>");
			pw.println("PROCESO DEL CALCULO");
			pw.println("</head>");
			pw.println("<body>");
			pw.println("<p>");
			pw.println("Inicia el proceso de calcular ISR"+"<br>");
			System.out.println("----------");
			pw.println("----------"+"<br>");
			System.out.println("Cantidad maxima para subsidio por año en curso: " + cantidadMaximaParaSubsidioPorAño);
			pw.println("Cantidad maxima para subsidio por año en curso: " + cantidadMaximaParaSubsidioPorAño+"<br>");
			System.out.println("---------- ");
			pw.println("----------"+"<br>");
			String sqlSalario ="select salario from puestos where no_puesto = '"+lblIdPuestoEmpMov.getText()+"'";//'"+lblIdPuestoEmpMov.getText()+"'
			System.out.println("sqlSalario: " + sqlSalario);
			String datos[] = new String[1];

			conSalario = nych.datasource.getConnection();
			stSalario = conSalario.createStatement();
			resultSetSalario = stSalario.executeQuery(sqlSalario);

			while(resultSetSalario.next()){ 
				datos[0] = resultSetSalario.getString(1);

			}

			String sueldoAjustado = tableMovEmpPerp.getValueAt(0, 3).toString();
			System.out.println("Sueldo con faltas: " + sueldoAjustado);

			double salario =  Double.parseDouble(sueldoAjustado);
			pw.println("Salario:" + salario+"<br>");
			System.out.println("Salario: " + salario);

			if(salario > listaDatosISR.get(0)  && salario < listaDatosISR.get(3) ) {
				System.out.println("Limite Inferior:" + listaDatosISR.get(0)+" ");
				pw.println(" Limite Inferior: " + listaDatosISR.get(0)+"<br>");
				resta = salario - listaDatosISR.get(0);
				tasa = resta * listaDatosISR.get(2)/100;
				cuota = tasa + listaDatosISR.get(1);
				if(salario>cantidadMaximaParaSubsidioPorAño) {
					System.out.println("sin subsidio");
					pw.println(" sin subsidio "+"<br>");
					isr = cuota - listaSubsidioISR.get(21);
				}else {
					System.out.println("con subsidio");
					pw.println(" con subsidio "+"<br>");
					isr = cuota - listaSubsidioISR.get(21);
				}

				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				pw.println(" ----------- "+"<br>");

				System.out.println("resta: " + resta);
				pw.println(" \nresta: " + resta+"<br>");

				System.out.println("tasa: " + tasa);
				pw.println(" tasa: " + tasa+"<br>");

				System.out.println("cuota: " + cuota);
				pw.println(" cuota: " + cuota+"<br>");

				System.out.println("isr: " + isr);
				pw.println(" isr: " + isr+"<br>");

				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				pw.println(" ---------- "+"<br>");


			}

			if(salario > listaDatosISR.get(3) && salario < listaDatosISR.get(6) ) {
				System.out.println("Limite Inferior:" + listaDatosISR.get(3));
				pw.println("Limite Inferior:" + listaDatosISR.get(3)+"<br>");
				resta = salario - listaDatosISR.get(3);
				tasa = resta * listaDatosISR.get(5)/100;
				cuota = tasa + listaDatosISR.get(4);
				if(salario>cantidadMaximaParaSubsidioPorAño) {
					System.out.println("sin subsidio");
					pw.println("sin subsidio "+"<br>");
					isr = cuota - listaSubsidioISR.get(21);
				}else {
					System.out.println("con subsidio ");
					pw.println("con subsidio"+"<br>");
					isr = cuota - listaSubsidioISR.get(21);
				}

				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				pw.println("-----------"+"<br>");

				System.out.println("resta: " + resta);
				pw.println("resta: " + resta+"<br>");

				System.out.println("tasa: " + tasa);
				pw.println("tasa: " + tasa+"<br>");

				System.out.println("cuota: " + cuota);
				pw.println("cuota: " + cuota+"<br>");

				System.out.println("isr: " + isr);
				pw.println("isr: " + isr+"<br>");

				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				pw.println("-----------"+"<br>");

			}

			if(salario > listaDatosISR.get(6)  &&  salario < listaDatosISR.get(9) ) {
				System.out.println("Limite Inferior:" + listaDatosISR.get(6));
				pw.println("Limite Inferior:" + listaDatosISR.get(6)+"<br>");
				resta = salario - listaDatosISR.get(6);
				tasa = resta * listaDatosISR.get(8)/100;
				cuota = tasa + listaDatosISR.get(7);
				if(salario>cantidadMaximaParaSubsidioPorAño) {
					System.out.println("sin subsidio");
					pw.println("sin subsidio"+"<br>");
					isr = cuota - listaSubsidioISR.get(21);
				}else {
					System.out.println("con subsidio");
					pw.println("con subsidio"+"<br>");
					isr = cuota - listaSubsidioISR.get(21);
				}

				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				pw.println("-----------"+"<br>");

				System.out.println("resta: " + resta);
				pw.println("resta: " + resta+"<br>");

				System.out.println("tasa: " + tasa);
				pw.println("tasa: " + tasa+"<br>");

				System.out.println("cuota: " + cuota);
				pw.println("cuota: " + cuota+"<br>");

				System.out.println("isr: " + isr);
				pw.println("isr: " + isr+"<br>");

				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				pw.println("-----------"+"<br>");
			}

			if(salario > listaDatosISR.get(9)  && salario < listaDatosISR.get(12)) {
				System.out.println("Limite Inferior:" + listaDatosISR.get(9));
				pw.println("Limite Inferior:" + listaDatosISR.get(9)+"<br>");
				resta = salario - listaDatosISR.get(9);
				tasa = resta * listaDatosISR.get(11)/100;
				cuota = tasa + listaDatosISR.get(10);
				if(salario>cantidadMaximaParaSubsidioPorAño) {
					System.out.println("sin subsidio");
					pw.println("sin subsidio "+"<br>");
					isr = cuota - listaSubsidioISR.get(21);
				}else {
					System.out.println("con subsidio");
					pw.println("con subsidio "+"<br>");
					isr = cuota - listaSubsidioISR.get(21);
				}

				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				pw.println("-----------"+"<br>");

				System.out.println("resta: " + resta);
				pw.println("resta: " + resta+"<br>");

				System.out.println("tasa: " + tasa);
				pw.println("tasa: " + tasa+"<br>");

				System.out.println("cuota: " + cuota);
				pw.println("cuota: " + cuota+"<br>");

				System.out.println("isr: " + isr);
				pw.println("isr: " + isr+"<br>");

				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				pw.println("------------"+"<br>");
			}

			if(salario > listaDatosISR.get(12)  && salario < listaDatosISR.get(15)) {
				System.out.println("Limite Inferior:" + listaDatosISR.get(12));
				pw.println("Limite Inferior:" + listaDatosISR.get(12));
				resta = salario - listaDatosISR.get(12);
				tasa = resta * listaDatosISR.get(14)/100;
				cuota = tasa + listaDatosISR.get(13);
				if(salario>cantidadMaximaParaSubsidioPorAño) {
					System.out.println("sin subsidio");
					pw.println("sin subsidio"+"<br>");
					isr = cuota - listaSubsidioISR.get(21);
				}else {
					System.out.println("con subsidio");
					pw.println("con subsidio"+"<br>");
					isr = cuota - listaSubsidioISR.get(21);
				}

				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				pw.println("-----------"+"<br>");

				System.out.println("resta: " + resta);
				pw.println("resta: " + resta+"<br>");

				System.out.println("tasa: " + tasa);
				pw.println("tasa: " + tasa+"<br>");

				System.out.println("cuota: " + cuota);
				pw.println("cuota: " + cuota+"<br>");

				System.out.println("isr: " + isr);
				pw.println("isr: " + isr+"<br>");

				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				pw.println("-----------"+"<br>");

			}

			if(salario > listaDatosISR.get(15)  && salario < listaDatosISR.get(18)) {
				System.out.println("Limite Inferior:" + listaDatosISR.get(15));
				pw.println("Limite Inferior:" + listaDatosISR.get(15)+"<br>");
				resta = salario - listaDatosISR.get(15);
				tasa = resta * listaDatosISR.get(17)/100;
				cuota = tasa + listaDatosISR.get(16);
				if(salario>cantidadMaximaParaSubsidioPorAño) {
					System.out.println("sin subsidio");
					pw.println("sin subsidio"+"<br>");
					isr = cuota - listaSubsidioISR.get(21);
				}else {
					System.out.println("con subsidio");
					pw.println("con subsidio"+"<br>");
					isr = cuota - listaSubsidioISR.get(21);
				}

				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				pw.println("-----------"+"<br>");

				System.out.println("resta: " + resta);
				pw.println("resta: " + resta+"<br>");

				System.out.println("tasa: " + tasa);
				pw.println("tasa: " + tasa+"<br>");

				System.out.println("cuota: " + cuota);
				pw.println("cuota: " + cuota+"<br>");

				System.out.println("isr: " + isr);
				pw.println("isr: " + isr+"<br>");

				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				pw.println("-----------"+"<br>");

			}

			if(salario > listaDatosISR.get(18) &&  salario < listaDatosISR.get(21)) {
				System.out.println("Limite Inferior:" + listaDatosISR.get(18));
				pw.println("Limite Inferior:" + listaDatosISR.get(18)+"<br>");
				resta = salario - listaDatosISR.get(18);
				tasa = resta * listaDatosISR.get(20)/100;
				cuota = tasa + listaDatosISR.get(19);
				if(salario>cantidadMaximaParaSubsidioPorAño) {
					System.out.println("sin subsidio");
					pw.println("sin subsidio"+"<br>");
					isr = cuota - listaSubsidioISR.get(21);
				}else {
					System.out.println("con subsidio");
					pw.println("con subsidio"+"<br>");
					isr = cuota - listaSubsidioISR.get(21);
				}

				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				pw.println("-----------"+"<br>");

				System.out.println("resta: " + resta);
				pw.println("resta: " + resta+"<br>");

				System.out.println("tasa: " + tasa);
				pw.println("tasa: " + tasa+"<br>");

				System.out.println("cuota: " + cuota);
				pw.println("cuota: " + cuota+"<br>");

				System.out.println("isr: " + isr);
				pw.println("isr: " + isr+"<br>");

				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				pw.println("-----------"+"<br>");

			}

			if(salario > listaDatosISR.get(21)  && salario < listaDatosISR.get(24)) {
				System.out.println("Limite Inferior:" + listaDatosISR.get(21));
				pw.println("Limite Inferior:" + listaDatosISR.get(21)+"<br>");
				resta = salario - listaDatosISR.get(21);
				tasa = resta * listaDatosISR.get(23)/100;
				cuota = tasa + listaDatosISR.get(122);
				if(salario>cantidadMaximaParaSubsidioPorAño) {
					System.out.println("sin subsidio");
					pw.println("sin subsidio"+"<br>");
					isr = cuota - listaSubsidioISR.get(21);
				}else {
					System.out.println("con subsidio");
					pw.println("con subsidio"+"<br>");
					isr = cuota - listaSubsidioISR.get(21);
				}

				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				pw.println("-----------"+"<br>");

				System.out.println("resta: " + resta);
				pw.println("resta: " + resta+"<br>");

				System.out.println("tasa: " + tasa);
				pw.println("tasa: " + tasa+"<br>");

				System.out.println("cuota: " + cuota);
				pw.println("cuota: " + cuota+"<br>");

				System.out.println("isr: " + isr);
				pw.println("isr: " + isr+"<br>");

				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				pw.println("-----------"+"<br>");

			}

			if(salario > listaDatosISR.get(24)  && salario < listaDatosISR.get(27) ) {
				System.out.println("Limite Inferior:" + listaDatosISR.get(24));
				pw.println("Limite Inferior:" + listaDatosISR.get(24)+"<br>");
				resta = salario - listaDatosISR.get(24);
				tasa = resta * listaDatosISR.get(26)/100;
				cuota = tasa + listaDatosISR.get(25);
				if(salario>cantidadMaximaParaSubsidioPorAño) {
					System.out.println("sin subsidio");
					pw.println("sin subsidio"+"<br>");
					isr = cuota - listaSubsidioISR.get(21);
				}else {
					System.out.println("con subsidio");
					pw.println("con subsidio"+"<br>");
					isr = cuota - listaSubsidioISR.get(21);
				}

				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				pw.println("-----------"+"<br>");

				System.out.println("resta: " + resta);
				pw.println("resta: " + resta+"<br>");

				System.out.println("tasa: " + tasa);
				pw.println("tasa: " + tasa+"<br>");

				System.out.println("cuota: " + cuota);
				pw.println("cuota: " + cuota+"<br>");

				System.out.println("isr: " + isr);
				pw.println("isr: " + isr+"<br>");

				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				pw.println("-----------"+"<br>");

			}

			if(salario > listaDatosISR.get(27)  && salario < listaDatosISR.get(30) ) {
				System.out.println("Limite Inferior:" + listaDatosISR.get(27));
				pw.println("Limite Inferior:" + listaDatosISR.get(27)+"<br>");
				resta = salario - listaDatosISR.get(27);
				tasa = resta * listaDatosISR.get(29)/100;
				cuota = tasa + listaDatosISR.get(28);
				if(salario>cantidadMaximaParaSubsidioPorAño) {
					System.out.println("sin subsidio");
					pw.println("sin subsidio"+"<br>");
					isr = cuota - listaSubsidioISR.get(21);
				}else {
					System.out.println("con subsidio");
					pw.println("con subsidio"+"<br>");
					isr = cuota - listaSubsidioISR.get(21);
				}

				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				pw.println("-----------"+"<br>");

				System.out.println("resta: " + resta);
				pw.println("resta: " + resta+"<br>");

				System.out.println("tasa: " + tasa);
				pw.println("tasa: " + tasa+"<br>");

				System.out.println("cuota: " + cuota);
				pw.println("cuota: " + cuota+"<br>");

				System.out.println("isr: " + isr);
				pw.println("isr: " + isr+"<br>");

				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				pw.println("-----------"+"<br>");
			}

			if(salario > listaDatosISR.get(30) ) {
				System.out.println("Limite Inferior:" + listaDatosISR.get(30));
				pw.println("Limite Inferior:" + listaDatosISR.get(30)+"<br>");
				resta = salario - listaDatosISR.get(30);
				tasa = resta * listaDatosISR.get(32)/100;
				cuota = tasa + listaDatosISR.get(31);
				if(salario>cantidadMaximaParaSubsidioPorAño) {
					System.out.println("sin subsidio");
					pw.println("sin subsidio"+"<br>");
					isr = cuota - listaSubsidioISR.get(21);
				}else {
					System.out.println("con subsidio");
					pw.println("con subsidio"+"<br>");
					isr = cuota - listaSubsidioISR.get(21);
				}

				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				pw.println("-----------"+"<br>");

				System.out.println("resta: " + resta);
				pw.println("resta: " + resta+"<br>");

				System.out.println("tasa: " + tasa);
				pw.println("tasa: " + tasa+"<br>");

				System.out.println("cuota: " + cuota);
				pw.println("cuota: " + cuota+"<br>");

				System.out.println("isr: " + isr);
				pw.println("isr: " + isr+"<br>");

				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				pw.println("-----------"+"<br>");
			}

			pw.println("</p>");
			pw.println("</body>");
			pw.println("</html>");
			pw.flush();
			pw.close();
			bw.close();
			actualizaValorIsr(isr,pw);
		}catch(Exception es) {
			es.printStackTrace();
			LOG.info("Excepción: " + es);
			//JOptionPane.showMessageDialog(null, es, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
		}finally {
			try {
				conSalario.close();
			} catch (SQLException ep) {
				ep.printStackTrace();
				LOG.info("Excepción: " + ep);
				//JOptionPane.showMessageDialog(null, ep, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
			}
		}//FIN DEL FINALLY
		return isr;	
	}

	public static ArrayList<Double> getListaDatosISR() {
		PoolNYCH nych = new PoolNYCH();
		Connection connect = null;
		Statement st;
		ResultSet resultSet = null;
		ArrayList<Double> listaDatosISR = new ArrayList<Double>();
		String sqlDatosISR = "select * from datos_isr ";
		try {
			connect = nych.datasource.getConnection();
			st = connect.createStatement();
			resultSet = st.executeQuery(sqlDatosISR);
		}catch(Exception exp) {
			exp.printStackTrace();
			StringWriter errors = new StringWriter();
			exp.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: "+ errors );
			//JOptionPane.showMessageDialog(null, exp, "Error de conexion", JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
		}try {
			while(resultSet.next()){
				listaDatosISR.add(resultSet.getDouble(2));
				listaDatosISR.add(resultSet.getDouble(4));
				listaDatosISR.add(resultSet.getDouble(5));
				//				System.out.println("resultSet.getDouble(2): "+resultSet.getDouble(2));
				//				System.out.println("resultSet.getDouble(4): "+resultSet.getDouble(4));
				//				System.out.println("resultSet.getDouble(5): "+resultSet.getDouble(5));
			}
		}catch(Exception et) {
			et.printStackTrace();
			StringWriter errors = new StringWriter();
			et.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: "+ errors );
			//JOptionPane.showMessageDialog(null, et, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
		}finally {
			try {
				connect.close();
			} catch (SQLException ep) {
				ep.printStackTrace();
				StringWriter errors = new StringWriter();
				ep.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: "+ errors );
				//JOptionPane.showMessageDialog(null, ep, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
			}
		}
		return listaDatosISR;

	}

	public static ArrayList<Double> getListaSubsidioISR() {
		PoolNYCH nych = new PoolNYCH();
		Connection connect = null;
		Statement st;
		ResultSet resultSet = null;
		ArrayList<Double> listaSubsidioISR = new ArrayList<Double>();
		String sqlSubsidioISR = "select * from subsidio_isr ";
		try {
			connect = nych.datasource.getConnection();
			st = connect.createStatement();
			resultSet = st.executeQuery(sqlSubsidioISR);
		}catch(Exception exp) {
			exp.printStackTrace();
			StringWriter errors = new StringWriter();
			exp.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: "+ errors );
			//JOptionPane.showMessageDialog(null, exp, "Error de conexion", JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
		}try {
			while(resultSet.next()){
				listaSubsidioISR.add(resultSet.getDouble(2));
				listaSubsidioISR.add(resultSet.getDouble(4));
			}
		}catch(Exception et) {
			et.printStackTrace();
			StringWriter errors = new StringWriter();
			et.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: "+ errors );
			//JOptionPane.showMessageDialog(null, et, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
		}finally {
			try {
				connect.close();
			} catch (SQLException ep) {
				ep.printStackTrace();
				StringWriter errors = new StringWriter();
				ep.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: "+ errors );
				//JOptionPane.showMessageDialog(null, ep, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
			}
		}
		return listaSubsidioISR;

	}



	public static ArrayList<Double> getListaTablaMensualISR() {
		PoolNYCH nych = new PoolNYCH();
		Connection connect = null;
		Statement st;
		ResultSet resultSet = null;
		ArrayList<Double> listaDatosISR = new ArrayList<Double>();
		String sqlDatosISR = "select * from tabla_mensual_isr ";
		try {
			connect = nych.datasource.getConnection();
			st = connect.createStatement();
			resultSet = st.executeQuery(sqlDatosISR);
		}catch(Exception exp) {
			exp.printStackTrace();
			StringWriter errors = new StringWriter();
			exp.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: "+ errors );
			//JOptionPane.showMessageDialog(null, exp, "Error de conexion", JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
		}try {
			while(resultSet.next()){
				listaDatosISR.add(resultSet.getDouble(2));
				listaDatosISR.add(resultSet.getDouble(3));
				listaDatosISR.add(resultSet.getDouble(4));
				listaDatosISR.add(resultSet.getDouble(5));
				//				System.out.println("resultSet.getDouble(2): "+resultSet.getDouble(2));
				//				System.out.println("resultSet.getDouble(4): "+resultSet.getDouble(4));
				//				System.out.println("resultSet.getDouble(5): "+resultSet.getDouble(5));
			}
		}catch(Exception et) {
			et.printStackTrace();
			StringWriter errors = new StringWriter();
			et.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: "+ errors );
			//JOptionPane.showMessageDialog(null, et, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
		}finally {
			try {
				connect.close();
			} catch (SQLException ep) {
				ep.printStackTrace();
				StringWriter errors = new StringWriter();
				ep.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: "+ errors );
				//JOptionPane.showMessageDialog(null, ep, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
			}
		}
		return listaDatosISR;

	}


	public static ArrayList<Double> getListaTablaMensualSubsidioISR() {
		PoolNYCH nych = new PoolNYCH();
		Connection connect = null;
		Statement st;
		ResultSet resultSet = null;
		ArrayList<Double> listaSubsidioISR = new ArrayList<Double>();
		String sqlSubsidioISR = "select limite_inferior,limite_superior,cantidad from tabla_mensual_subsidio_isr ";
		try {
			connect = nych.datasource.getConnection();
			st = connect.createStatement();
			resultSet = st.executeQuery(sqlSubsidioISR);
		}catch(Exception exp) {
			exp.printStackTrace();
			StringWriter errors = new StringWriter();
			exp.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: "+ errors );
			//JOptionPane.showMessageDialog(null, exp, "Error de conexion", JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
		}try {
			while(resultSet.next()){
				listaSubsidioISR.add(resultSet.getDouble(1));
				listaSubsidioISR.add(resultSet.getDouble(2));
				listaSubsidioISR.add(resultSet.getDouble(3));
			}
		}catch(Exception et) {
			et.printStackTrace();
			StringWriter errors = new StringWriter();
			et.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: "+ errors );
			//JOptionPane.showMessageDialog(null, et, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
		}finally {
			try {
				connect.close();
			} catch (SQLException ep) {
				ep.printStackTrace();
				StringWriter errors = new StringWriter();
				ep.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: "+ errors );
				//JOptionPane.showMessageDialog(null, ep, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
			}
		}
		return listaSubsidioISR;

	}


	public void actualizaValorINFONAVIT(double infonavit) {
		Connection conUpdateINFONAVIT = null;
		PoolNYCH nych = new PoolNYCH();
		try {

			String sqlUpdateINFONAVIT ="UPDATE CALCULO_NOMINA SET VALOR_DEDUCCION='"+ infonavit +"' where id_empleado='"+lblEmpIdPerp.getText()+"' AND ID_DEDUCCION=10";
			System.out.println(sqlUpdateINFONAVIT);
			conUpdateINFONAVIT = nych.datasource.getConnection();
			PreparedStatement ppsUpdateINFONAVIT = conUpdateINFONAVIT.prepareStatement(sqlUpdateINFONAVIT);
			ppsUpdateINFONAVIT.executeUpdate();
			System.out.println("Datos Actualizados");
			System.out.println("----------");
		}catch(Exception ees) {
			ees.printStackTrace();
			StringWriter errors = new StringWriter();
			ees.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: "+ errors );
			//JOptionPane.showMessageDialog(null, ees, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
		}finally {
			try {
				conUpdateINFONAVIT.close();
			} catch (SQLException ep) {
				ep.printStackTrace();
				StringWriter errors = new StringWriter();
				ep.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: "+ errors );
				//JOptionPane.showMessageDialog(null, ep, "Error de desc onexion", JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
			}
		}//FIN DEL FINALLY	
	}


	@SuppressWarnings("static-access")
	public double calcularInfonavit() {
		System.out.println("Inicia proceso de calcularInfonavit");
		double infonavit = 0.0;
		String NO_CREDITO_INFONAVIT;
		PoolNYCH nych = new PoolNYCH();
		Connection conInfonavit = null;
		Statement stInfonavit;
		ResultSet resultInfonavit;
		Connection conCalInfonavit = null;
		Statement stCalInfonavit;
		ResultSet resultCalInfonavit;
		String datos[] = new String[8];
		String datosCalInfonavit[] = new String[2];

		String sqlCalInfonavit = "select count(*) from CALCULO_NOMINA where ID_EMPLEADO = "+ lblEmpIdPerp.getText() +" and ID_DEDUCCION = 10 and periodo = "+FormularioPrincipal.lblPeriodoNumero.getText()+" ";
		System.out.println("sqlCalInfonavit: "+sqlCalInfonavit);

		String sqlInfonavit ="select factor_infonavit,u_m_a,bimestre,dias_bimestres,impuesto_infonavit,catorcenas_bimestre,NO_CREDITO_INFONAVIT,semanas_bimestre from empleados "
				+ "where CLAVE = '"+lblEmpIdPerp.getText()+"'";//'"+lblIdPuestoEmpMov.getText()+"'
		System.out.println("sqlInfonavit: "+sqlInfonavit);
		try {

			conInfonavit = nych.datasource.getConnection();
			stInfonavit = conInfonavit.createStatement();
			resultInfonavit = stInfonavit.executeQuery(sqlInfonavit);

			conCalInfonavit = nych.datasource.getConnection();
			stCalInfonavit = conCalInfonavit.createStatement();
			resultCalInfonavit = stCalInfonavit.executeQuery(sqlCalInfonavit);

			while(resultCalInfonavit.next()) {
				datosCalInfonavit[0] = resultCalInfonavit.getString(1);
				
				while(resultInfonavit.next()){ 
					datos[0] = resultInfonavit.getString(1);
					datos[1] = resultInfonavit.getString(2);
					datos[2] = resultInfonavit.getString(3);
					datos[3] = resultInfonavit.getString(4);
					datos[4] = resultInfonavit.getString(5);
					datos[5] = resultInfonavit.getString(6);
					datos[6] = resultInfonavit.getString(7);
					datos[7] = resultInfonavit.getString(8);
				}
			}

			double registroInfonavit = Double.parseDouble(datosCalInfonavit[0]);
			System.out.println("datosCalInfonavit[0]: "+ datosCalInfonavit[0]);
			
			if(FormularioPrincipal.lblIsNomC.getText().equalsIgnoreCase("OK")) {

				NO_CREDITO_INFONAVIT = datos[6];
				System.out.println("NO_CREDITO_INFONAVIT: " + NO_CREDITO_INFONAVIT);

				if(NO_CREDITO_INFONAVIT != "0"){

					double factor_infonavit = Double.parseDouble(datos[0]);
					double u_m_a = Double.parseDouble(datos[1]);
					double bimestre = Double.parseDouble(datos[2]);
					double dias_bimestres = Double.parseDouble(datos[3]);
					double impuesto_infonavit = Double.parseDouble(datos[4]);
					double catorcenas_bimestre = Double.parseDouble(datos[5]);


					System.out.println("factor infonavit: " + factor_infonavit);

					System.out.println("UMA: " + u_m_a);

					System.out.println("Bimestre: " + bimestre);

					System.out.println("dias del bimestre: " + dias_bimestres);

					System.out.println("impuesto de infonavit: " + impuesto_infonavit);

					System.out.println("catorcenas por bimestre: " + catorcenas_bimestre);

					//FORMULA_INFONAVIT = ((((3.1620*75.49*2)/61)*61)+15)/4;

					infonavit = ((((factor_infonavit*u_m_a*bimestre)/dias_bimestres)*dias_bimestres)+impuesto_infonavit)/catorcenas_bimestre;
					System.out.println("infonavit: "+infonavit);
					
					
					if(registroInfonavit<1) {
						insertCalculoNominaInfonavit(infonavit);
					}

					actualizaValorINFONAVIT(infonavit);
				}
			}else if (FormularioPrincipal.lblIsNomS.getText().equalsIgnoreCase("OK")) {
				NO_CREDITO_INFONAVIT = (datos[6]);
				System.out.println("NO_CREDITO_INFONAVIT: " + NO_CREDITO_INFONAVIT);

				if(NO_CREDITO_INFONAVIT != "0"){

					double factor_infonavit = Double.parseDouble(datos[0]);
					double u_m_a = Double.parseDouble(datos[1]);
					double bimestre = Double.parseDouble(datos[2]);
					double dias_bimestres = Double.parseDouble(datos[3]);
					double impuesto_infonavit = Double.parseDouble(datos[4]);
					double semanas_bimestre = Double.parseDouble(datos[7]);


					System.out.println("factor infonavit: " + factor_infonavit);

					System.out.println("UMA: " + u_m_a);

					System.out.println("Bimestre: " + bimestre);

					System.out.println("dias del bimestre: " + dias_bimestres);

					System.out.println("impuesto de infonavit: " + impuesto_infonavit);

					System.out.println("semanas por bimestre: " + semanas_bimestre);

					//FORMULA_INFONAVIT = ((((3.1620*75.49*2)/61)*61)+15)/8;

					infonavit = ((((factor_infonavit*u_m_a*bimestre)/dias_bimestres)*dias_bimestres)+impuesto_infonavit)/semanas_bimestre;
					System.out.println("infonavit: "+infonavit);

					if(registroInfonavit<1) {
						insertCalculoNominaInfonavit(infonavit);
					}
					actualizaValorINFONAVIT(infonavit);
				}
			}

			//			else {
			//				JOptionPane.showMessageDialog(null,"El empleado no es aplicable para la deduccion de infonavit, no tiene un credito relacionado.");
			//			}
			//			
		}catch(Exception es) {
			es.printStackTrace();
			StringWriter errors = new StringWriter();
			es.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: "+ errors );
			//JOptionPane.showMessageDialog(null, es, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
		}finally {
			try {
				conInfonavit.close();
				conCalInfonavit.close();
			} catch (SQLException ep) {
				ep.printStackTrace();
				StringWriter errors = new StringWriter();
				ep.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: "+ errors );
				//JOptionPane.showMessageDialog(null, ep, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
			}
		}//FIN DEL FINALLY
		return infonavit;
	}

	public double calcularIMSS() {
		System.out.println("Inicia el proceso de calcularIMSS");
		double sumaPorcentaje = 0.0; 
		double formula = 0.0; 
		double diasCatorcena = 14;
		double diasSemana = 7;
		double diasJubilados = 30;
		ArrayList<Double> listaPorcentajeIMSS = new ArrayList<Double>();
		listaPorcentajeIMSS = getListaPorcentajeIMSS();
		System.out.println("*********");
		for(int i = 0; i<listaPorcentajeIMSS.size();i++){
			System.out.println("indice: "+ i + " |valor: " + listaPorcentajeIMSS.get(i));
		}
		System.out.println("*********");

		String nombreE = lblEmplNombrePerp.getText();
		String ApPat = lblEmpApPatPerp.getText();
		String ApMat = lblEmpApMatPerp.getText();
		String nombreConcat = nombreE +" "+ApPat+" "+ApMat;

		File attachments = null;
		Date result = new Date();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String convertido = formatter.format(result);
		String carpeta= "C:\\Informacion de Procesos del NYCH";
		String carpetaInterna= "\\IMSS";
		String archivo = "\\IMSS_"+convertido+"_"+nombreConcat+".txt";
		File crearCarpeta = new File(carpeta+carpetaInterna);
		File crearArchivo = new File(carpeta+carpetaInterna+archivo);
		if(crearArchivo.exists()) {
			//			System.out.println("el archivo ya existe,pero se sobreescribira");
			//			JOptionPane.showMessageDialog(null,"El archivo de los cálculos está en la carpeta: "+ crearCarpeta);
		}else {
			System.out.println("No existen pero se creara la ruta y el archivo");
			//JOptionPane.showMessageDialog(null,"El archivo de los cálculos está en la carpeta:"+ crearCarpeta);
			crearCarpeta.mkdirs();
			try {
				if(crearArchivo.createNewFile()) {
					System.out.println("archivo creado");
					JOptionPane.showMessageDialog(null,"El archivo de los cálculos está en la carpeta: " + crearCarpeta);
				}else{
					System.out.println("el archivo no fue creado");
					JOptionPane.showMessageDialog(null,"el archivo no fue creado");
				}
			}catch(Exception io) {
				io.printStackTrace();
			}
		}


		Connection conSDI = null;
		try {

			FileWriter fw;

			fw = new FileWriter(crearArchivo);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);


			pw.print("<!DOCTYPE html>");
			pw.println("<html>");
			pw.println("<head>");
			pw.println("PROCESO DEL CALCULO");
			pw.println("</head>");
			pw.println("<body>");
			pw.println("<p>");
			pw.println("Inicia el proceso de calcular IMSS"+"<br>");
			pw.println("----------"+"<br>");

			sumaPorcentaje = listaPorcentajeIMSS.get(0) + listaPorcentajeIMSS.get(6) + listaPorcentajeIMSS.get(9) + listaPorcentajeIMSS.get(12) + listaPorcentajeIMSS.get(15) + 
					listaPorcentajeIMSS.get(18) + listaPorcentajeIMSS.get(21) + listaPorcentajeIMSS.get(24);
			System.out.println("Suma Porcentaje: " + sumaPorcentaje);
			pw.println("Suma Porcentaje: " + sumaPorcentaje+"<br>");

			PoolNYCH nych = new PoolNYCH();
			Statement stSDI;
			ResultSet resultSetSDI;
			String sqlIMSS ="select sdi,nombre from empleados where clave = '"+lblEmpIdPerp.getText()+"'";//'"+lblEmpIdPerp.getText()+"'
			System.out.println("sqlIMSS: "+sqlIMSS);
			String datos[] = new String[2];
			conSDI = nych.datasource.getConnection();
			stSDI = conSDI.createStatement();
			resultSetSDI = stSDI.executeQuery(sqlIMSS);

			while(resultSetSDI.next()){ 
				datos[0] = resultSetSDI.getString(1);
				datos[1] = resultSetSDI.getString(2);
			}
			double sdi =  Double.parseDouble(datos[0]);
			String nombre = datos[1];
			System.out.println("SDI: " + sdi);
			pw.println("SDI: " + sdi+"<br>");

			if(FormularioPrincipal.lblIsNomC.getText().equalsIgnoreCase("OK")) {

				formula = sumaPorcentaje/100 * sdi * diasCatorcena;
				System.out.println("Salario Diario Integrado " + formula +" ,para el empleado: " + nombre +" en nomina: " + FormularioPrincipal.lblCatorcenal.getText() );
				pw.println("Salario Diario Integrado " + formula +" ,para el empleado: " + nombre +" en nomina: " + FormularioPrincipal.lblCatorcenal.getText()+"<br>" );

			}else if (FormularioPrincipal.lblIsNomS.getText().equalsIgnoreCase("OK")) {

				formula = sumaPorcentaje/100 * sdi * diasSemana;
				System.out.println("Salario Diario Integrado " + formula +" ,para el empleado: " + nombre +" en nomina: " + FormularioPrincipal.lblSemanal.getText() );
				pw.println("Salario Diario Integrado " + formula +" ,para el empleado: " + nombre +" en nomina: " + FormularioPrincipal.lblSemanal.getText() +"<br>");

			}

			//			else if(FormularioPrincipal.lblIsNomJ.getText().equalsIgnoreCase("OK")) {
			//
			//				formula = sumaPorcentaje/100 * sdi * diasJubilados;
			//				System.out.println("Salario Diario Integrado " + formula +" ,para el empleado: " + nombre +" en nomina: " + FormularioPrincipal.lblJubilados.getText() );
			//				pw.println("Salario Diario Integrado " + formula +" ,para el empleado: " + nombre +" en nomina: " + FormularioPrincipal.lblJubilados.getText() +"<br>");
			//			}

			pw.println("</p>");
			pw.println("</body>");
			pw.println("</html>");
			pw.flush();
			pw.close();
			bw.close();
			actualizaValorIMSS(formula);
		}catch(Exception ep) {
			ep.printStackTrace();
			StringWriter errors = new StringWriter();
			ep.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: "+ errors );
			JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
		}finally {
			try {
				conSDI.close();
			} catch (SQLException ep) {
				ep.printStackTrace();
				StringWriter errors = new StringWriter();
				ep.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: "+ errors );
				//JOptionPane.showMessageDialog(null, ep, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
			}
		}
		return formula;
	}

	public static ArrayList<Double> getListaPorcentajeIMSS() {
		PoolNYCH nych = new PoolNYCH();
		Connection connect = null;
		Statement st;
		ResultSet resultSet = null;
		ArrayList<Double> ListaPorcentajeIMSS = new ArrayList<Double>();
		String sqlPorcentajeIMSS = "select * from porcentaje_imss";
		try {
			connect = nych.datasource.getConnection();
			st = connect.createStatement();
			resultSet = st.executeQuery(sqlPorcentajeIMSS);
		}catch(Exception exp) {
			exp.printStackTrace();
			StringWriter errors = new StringWriter();
			exp.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: "+ errors );
			//JOptionPane.showMessageDialog(null, exp, "Error de conexion", JOptionPane.ERROR_MESSAGE);
		}try {
			while(resultSet.next()){
				ListaPorcentajeIMSS.add(resultSet.getDouble(3));
			}
		}catch(Exception et) {
			et.printStackTrace();
			StringWriter errors = new StringWriter();
			et.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: "+ errors );
			//JOptionPane.showMessageDialog(null, et, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
		}finally {
			try {
				connect.close();
			} catch (SQLException ep) {
				ep.printStackTrace();
				StringWriter errors = new StringWriter();
				ep.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: "+ errors );
				//JOptionPane.showMessageDialog(null, ep, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
			}
		}
		return ListaPorcentajeIMSS;
	}


	public void actualizaValorIMSS(double imss) {
		Connection conUpdateIMSS = null;
		PoolNYCH nych = new PoolNYCH();
		try {

			String sqlUpdateIMSS ="UPDATE CALCULO_NOMINA SET VALOR_DEDUCCION='"+ imss +"' where id_empleado='"+lblEmpIdPerp.getText()+"' and id_deduccion=1 and parcialidades=0";
			System.out.println("sqlUpdateIMSS: " + sqlUpdateIMSS);
			conUpdateIMSS = nych.datasource.getConnection();
			PreparedStatement ppsUpdateISR = conUpdateIMSS.prepareStatement(sqlUpdateIMSS);
			ppsUpdateISR.executeUpdate();
			System.out.println("Datos Actualizados");
			System.out.println("----------");
		}catch(Exception ees) {
			ees.printStackTrace();
			StringWriter errors = new StringWriter();
			ees.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: "+ errors );
			//JOptionPane.showMessageDialog(null, ees, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
		}finally {
			try {
				conUpdateIMSS.close();
			} catch (SQLException ep) {
				ep.printStackTrace();
				StringWriter errors = new StringWriter();
				ep.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: "+ errors );
				//JOptionPane.showMessageDialog(null, ep, "Error de desc onexion", JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
			}
		}//FIN DEL FINALLY	
	}


	public void actualizaValorIsr(double isr,PrintWriter pw) {
		Connection conUpdateISR = null;
		PoolNYCH nych = new PoolNYCH();
		try {

			String sqlUpdateIsr ="UPDATE CALCULO_NOMINA SET VALOR_DEDUCCION='"+ isr +"' where id_empleado='"+lblEmpIdPerp.getText()+"' and id_deduccion=2 and parcialidades=0";
			System.out.println("sqlUPDATEISR: " + sqlUpdateIsr);
			pw.write(sqlUpdateIsr);
			System.out.println("sqlUpdateIsr: "+sqlUpdateIsr);
			conUpdateISR = nych.datasource.getConnection();
			PreparedStatement ppsUpdateISR = conUpdateISR.prepareStatement(sqlUpdateIsr);
			ppsUpdateISR.executeUpdate();
			System.out.println("Datos Actualizados del ISR");
			pw.write("Datos Actualizados del ISR");
			System.out.println("----------");
			pw.write("----------");
		}catch(Exception ees) {
			ees.printStackTrace();
			StringWriter errors = new StringWriter();
			ees.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: "+ errors );
			//JOptionPane.showMessageDialog(null, ees, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
		}finally {
			try {
				conUpdateISR.close();
			} catch (SQLException ep) {
				ep.printStackTrace();
				StringWriter errors = new StringWriter();
				ep.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: "+ errors );
				//JOptionPane.showMessageDialog(null, ep, "Error de desc onexion", JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
			}
		}//FIN DEL FINALLY	
	}


	@SuppressWarnings("static-access")
	public void mostrarDatosPerpCalc(String valor) {
		//System.out.println("mostrarDatosPerpCalc");
		DefaultTableModel modelo = new DefaultTableModel()
		{
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				//return super.isCellEditable(row, column);
				if(column==6){
					return true;
				}else {
					return false;
				}
			}
		};

		JButton btnSalario = new JButton("Ver");
		btnSalario.setName("btnPercepcionSalario");

		JButton btnAyuda = new JButton("Ver");
		btnAyuda.setName("btnPercepcionAyuda");

		JButton btnPrimaVacacional = new JButton("Ver");
		btnPrimaVacacional.setName("btnPrimaVacacional");

		JButton btnAguinaldo = new JButton("Ver");
		btnAguinaldo.setName("btnAguinaldo");

		JButton btnHrsExtras = new JButton("Ver");
		btnHrsExtras.setName("btnHrsExtras");


		tableMovEmpPerp.setDefaultRenderer(Object.class, new TableRender());

		modelo.addColumn("ID");
		modelo.addColumn("CLAVE");
		modelo.addColumn("PERCEPCION");
		modelo.addColumn("VALOR");
		modelo.addColumn("PROCESO");

		final DefaultTableModel modeloO = new DefaultTableModel(){
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				//return super.isCellEditable(row, column);
				if(column==6){
					return true;
				}else {
					return false;
				}
			}

		};
		modeloO.addColumn("ID");
		modeloO.addColumn("CLAVE");
		modeloO.addColumn("PERCEPCION");
		modeloO.addColumn("VALOR");

		tableMovEmpPerp.setModel(modelo);
		tableMovEmpNewPerp.setModel(modeloO);
		tableMovEmpPerp.setBackground(Color.WHITE);
		tableMovEmpNewPerp.setBackground(Color.WHITE);

		JTableHeader th = new JTableHeader();
		JTableHeader th1 = new JTableHeader();
		Color colorSilverLight=new Color(176, 196, 222);
		Color colorNegro=new Color(0, 0, 0);
		th = tableMovEmpPerp.getTableHeader();
		th1 = tableMovEmpNewPerp.getTableHeader();
		Font fuente = new Font("Arial", Font.BOLD, 14); 
		th.setFont(fuente); 
		th1.setFont(fuente); 
		th.setBackground(colorSilverLight);
		th1.setBackground(colorSilverLight);
		th.setForeground(colorNegro);
		th1.setForeground(colorNegro);

		TableColumnModel columnModel = tableMovEmpPerp.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(50);
		columnModel.getColumn(1).setPreferredWidth(100);
		columnModel.getColumn(2).setPreferredWidth(280);
		columnModel.getColumn(3).setPreferredWidth(180);
		columnModel.getColumn(4).setPreferredWidth(150);

		TableColumnModel columnModel0 = tableMovEmpNewPerp.getColumnModel();
		columnModel0.getColumn(0).setPreferredWidth(50);
		columnModel0.getColumn(1).setPreferredWidth(150);
		columnModel0.getColumn(2).setPreferredWidth(300);
		columnModel0.getColumn(3).setPreferredWidth(200);

		PoolNYCH nych = new PoolNYCH();
		String sqlSelectFaltaXEmpleado  = "select count(*) from faltas_empleado where id_Puesto = "+lblIdPuestoEmpMov.getText() +" and periodo = "+ FormularioPrincipal.lblPeriodoNumero.getText()+" and tipo_nomina = "+FormularioPrincipal.lblIdTipoNomina.getText()+" ";
		System.out.println("faltas por empleado: " + sqlSelectFaltaXEmpleado);


		String sqlSelectEmplPerp="";
		sqlSelectEmplPerp = "SELECT DBO.ATRIBUTO_TIPO_PERCEPCION.ID_PERCEPCION,DBO.ATRIBUTO_TIPO_PERCEPCION.CLAVE,DBO.ATRIBUTO_TIPO_PERCEPCION.DESCRIPCION,"
				+ "DBO.CALCULO_NOMINA.VALOR_PERCEPCION "
				+ "FROM DBO.CALCULO_NOMINA "
				+ "LEFT JOIN DBO.ATRIBUTO_TIPO_PERCEPCION ON DBO.ATRIBUTO_TIPO_PERCEPCION.ID_PERCEPCION = DBO.CALCULO_NOMINA.ID_PERCEPCION "
				+ "WHERE DBO.CALCULO_NOMINA.ID_EMPLEADO='"+lblEmpIdPerp.getText()+"' and CALCULO_NOMINA.id_percepcion != 40 order by dbo.calculo_nomina.id_empleado";
		System.out.println("sqlSelectEmplPerp: " + sqlSelectEmplPerp);

		DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
		simbolos.setDecimalSeparator('.');
		DecimalFormat df = new DecimalFormat("####.##",simbolos);
		Object datos[] = new Object[5];
		Object datosFalta[] = new Object[1];
		Connection conEmpPerp =null;
		Statement stEmpPerp;
		ResultSet resultSetEmpPerp;
		Connection conFaltas =null;
		Statement stFaltas;
		ResultSet resultSetFaltas;
		try {
			conEmpPerp = nych.datasource.getConnection();
			conFaltas = nych.datasource.getConnection();
			stEmpPerp = conEmpPerp.createStatement();
			stFaltas = conFaltas.createStatement();
			resultSetEmpPerp = stEmpPerp.executeQuery(sqlSelectEmplPerp);
			resultSetFaltas = stFaltas.executeQuery(sqlSelectFaltaXEmpleado);

			while(resultSetFaltas.next()) {
				String faltas = resultSetFaltas.getString(1);

				while(resultSetEmpPerp.next()) {
					datos[0] = resultSetEmpPerp.getString(1);
					datos[1] = resultSetEmpPerp.getString(2);//clave en base de datos
					datos[2] = resultSetEmpPerp.getString(3);


					if(resultSetEmpPerp.getString(2).equalsIgnoreCase("16")) {//horas extras
						datos[3] = resultSetEmpPerp.getString(4);
					}

					if(resultSetEmpPerp.getString(2).equalsIgnoreCase("18")) {//prima vacacional
						datos[3] = resultSetEmpPerp.getString(4);
					}

					if(resultSetEmpPerp.getString(2).equalsIgnoreCase("2")) {//aguinaldo
						datos[3] = resultSetEmpPerp.getString(4);
					}

					if(resultSetEmpPerp.getString(2).equalsIgnoreCase("26")  && Integer.parseInt(faltas)<1 ) {//ayuda de despensa sin faltas
						datos[3] = resultSetEmpPerp.getString(4);
					}

					if(resultSetEmpPerp.getString(2).equalsIgnoreCase("1")  && Integer.parseInt(faltas)<1) {//salario sin faltas
						datos[3] = resultSetEmpPerp.getString(4);
					}




					if(resultSetEmpPerp.getString(2).equalsIgnoreCase("26") && Integer.parseInt(faltas)>0) {//ayuda de despensa con faltas
						if(FormularioPrincipal.lblIsNomC.getText().equalsIgnoreCase("OK")) {

							double faltasReales = 14 - Double.parseDouble(faltas);
							System.out.println("dias reales para ayuda catorcena: " + faltasReales);

							double cantidadAyudaDespensa = Double.parseDouble(internalFrameCatalogos.textFieldCantidadAyudaDespensa.getText());
							System.out.println("cantidadAyudaDespensa14: "+cantidadAyudaDespensa);
							double porcentajeAyudaDespensa = Double.parseDouble(internalFrameCatalogos.textFieldPorcentajeAyudaDespensa.getText());
							System.out.println("porcentajeAyudaDespensa14: "+porcentajeAyudaDespensa);

							double formulaAyudaDespensa = cantidadAyudaDespensa*porcentajeAyudaDespensa*faltasReales;
							System.out.println("formulaAyudaDespensa14: "+formulaAyudaDespensa);

							datos[3] = String.valueOf(df.format(formulaAyudaDespensa));
							System.out.println("-Ayu14-->"+datos[3]);
						}

						if(FormularioPrincipal.lblIsNomS.getText().equalsIgnoreCase("OK")) {
							double faltasReales = 7 - Double.parseDouble(faltas);
							System.out.println("dias reales para ayuda semanal: " + faltasReales);

							double cantidadAyudaDespensa = Double.parseDouble(internalFrameCatalogos.textFieldCantidadAyudaDespensa.getText());
							System.out.println("cantidadAyudaDespensa7: "+cantidadAyudaDespensa);
							double porcentajeAyudaDespensa = Double.parseDouble(internalFrameCatalogos.textFieldPorcentajeAyudaDespensa.getText());
							System.out.println("porcentajeAyudaDespensa7: "+porcentajeAyudaDespensa);

							double formulaAyudaDespensa = cantidadAyudaDespensa*porcentajeAyudaDespensa*faltasReales;
							System.out.println("formulaAyudaDespensa7: "+formulaAyudaDespensa);

							datos[3] = String.valueOf(df.format(formulaAyudaDespensa));
							System.out.println("-Ayu7-->"+datos[3]);
						}

					}
					//				else {
					//					datos[3] = resultSetEmpPerp.getString(4);
					//					System.out.println("-Ayu-->"+datos[3]);
					//				}


					if(resultSetEmpPerp.getString(2).equalsIgnoreCase("1")  && Integer.parseInt(faltas)>0) {//Salario con faltas
						datosFalta[0] = resultSetFaltas.getString(1);
						System.out.println("datos faltas: "+datosFalta[0]);
						System.out.println("datos dentro de las faltas: "+datos[3]);

						//					String faltas = resultSetFaltas.getString(1);
						String salTot = resultSetEmpPerp.getString(4);

						if(FormularioPrincipal.lblIsNomC.getText().equalsIgnoreCase("OK")) {
							double faltasReales = 14 - Double.parseDouble(faltas);
							System.out.println("dias reales14: " + faltasReales);
							double sDT= Double.parseDouble(salTot) / 14;
							System.out.println("sDt14: "+ sDT);

							double salTotXFalta = sDT * faltasReales;
							System.out.println("salTotXFalta14: "+ salTotXFalta);


							datos[3] = String.valueOf(df.format(salTotXFalta));
							System.out.println("-*-->14:"+datos[3]);
						}

						if(FormularioPrincipal.lblIsNomS.getText().equalsIgnoreCase("OK")) {
							double faltasReales = 7 - Double.parseDouble(faltas);
							System.out.println("dias reales7: " + faltasReales);
							double sDT= Double.parseDouble(salTot) / 7;
							System.out.println("sDt: "+ sDT);

							double salTotXFalta = sDT * faltasReales;
							System.out.println("salTotXFalta7: "+ salTotXFalta);

							datos[3] = String.valueOf(df.format(salTotXFalta));
							System.out.println("salTotXFalta7: "+ salTotXFalta);
						}

					}
					//				else{
					//					datos[3] = resultSetEmpPerp.getString(4);
					//					System.out.println("salTotXFalta: "+ datos[3]);
					//				}


					System.out.println("datos0: "+datos[0]);
					System.out.println("datos1: "+datos[1]);
					System.out.println("datos2: "+datos[2]);
					System.out.println("datos3: "+datos[3]);//salario

					if(resultSetEmpPerp.getString(1).matches("1")) {//datos[0].equals("1") 
						datos[4] = btnSalario;
					}
					else if(resultSetEmpPerp.getString(1).matches("26")) {//datos[0].equals("26")(
						datos[4] = btnAyuda;
					}
					else if(resultSetEmpPerp.getString(1).matches("18")){
						datos[4] = btnPrimaVacacional;
					}
					else if(resultSetEmpPerp.getString(1).matches("2")){
						datos[4] = btnAguinaldo;
					}
					else if(resultSetEmpPerp.getString(1).matches("16")){
						datos[4] = btnHrsExtras;
					}

					modelo.addRow(datos);

				}//fin del while resultSetEmpPerp
			}//FIN DEL WHILE resulsetFaltas
			tableMovEmpPerp.setModel(modelo);
			tableMovEmpPerp.setRowHeight(20);
			tableMovEmpNewPerp.setModel(modeloO);
		}catch (SQLException el) {
			el.printStackTrace();
			StringWriter errors = new StringWriter();
			el.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: "+ errors );
			//JOptionPane.showMessageDialog(null, el, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
		}finally {
			try {
				conEmpPerp.close();
			} catch (SQLException ep) {
				ep.printStackTrace();
				StringWriter errors = new StringWriter();
				ep.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: "+ errors );
				//JOptionPane.showMessageDialog(null, ep, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
			}
		}//FIN DEL FINALLY
	}//FIN DEL METODO


	public void ajustarSalarioXFalta(String valor) {
		System.out.println("ajustando salario x faltas");

	}


	public void mostrarDatosDedCalc(String valor) {
		//System.out.println("mostrarDatosDedCalc");
		final DefaultTableModel modelo = new DefaultTableModel()
		{
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				//return super.isCellEditable(row, column);
				if(column==6){
					return true;
				}else {
					return false;
				}
			}
		};

		JButton btnISR = new JButton("Ver");
		btnISR.setName("btnISR");

		JButton btnIMSS = new JButton("Ver");
		btnIMSS.setName("btnIMSS");

		JButton btnINFONAVIT = new JButton("Ver");
		btnINFONAVIT.setName("btnInfonavit");

		JButton btnIMUVI = new JButton("Ver");
		btnIMUVI.setName("btnImuvi");

		JButton btnANTICIPO = new JButton("Ver");
		btnANTICIPO.setName("btnAnticipo");

		JButton btnPENSIONALIMENTICIA = new JButton("Ver");
		btnPENSIONALIMENTICIA.setName("btnPensionAlimenticia");

		tableMovEmpDed.setDefaultRenderer(Object.class, new TableRender());


		modelo.addColumn("ID");
		modelo.addColumn("CLAVE");
		modelo.addColumn("DEDUCCION");
		modelo.addColumn("VALOR");
		modelo.addColumn("PARCIALIDADES");
		modelo.addColumn("PROCESO");

		DefaultTableModel modeloO = new DefaultTableModel()
		{
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				//return super.isCellEditable(row, column);
				if(column==6){
					return true;
				}else {
					return false;
				}
			}
		};
		modeloO.addColumn("ID");
		modeloO.addColumn("CLAVE");
		modeloO.addColumn("DEDUCCION");
		modeloO.addColumn("VALOR");
		modeloO.addColumn("PARCIALIDADES");

		tableMovEmpDed.setModel(modelo);
		tableMovEmpNewDed.setModel(modeloO);
		tableMovEmpDed.setBackground(Color.WHITE);
		tableMovEmpNewDed.setBackground(Color.WHITE);

		JTableHeader th = new JTableHeader();
		JTableHeader th1 = new JTableHeader();
		Color colorSilverLight=new Color(176, 196, 222);
		Color colorNegro=new Color(0, 0, 0);
		th = tableMovEmpDed.getTableHeader();
		th1 = tableMovEmpNewDed.getTableHeader();
		Font fuente = new Font("Arial", Font.BOLD, 14); 
		th.setFont(fuente); 
		th1.setFont(fuente); 
		th.setBackground(colorSilverLight);
		th1.setBackground(colorSilverLight);
		th.setForeground(colorNegro);
		th1.setForeground(colorNegro);

		TableColumnModel columnModel = tableMovEmpDed.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(50);
		columnModel.getColumn(1).setPreferredWidth(150);
		columnModel.getColumn(2).setPreferredWidth(280);
		columnModel.getColumn(3).setPreferredWidth(150);
		columnModel.getColumn(4).setPreferredWidth(200);
		columnModel.getColumn(5).setPreferredWidth(150);

		TableColumnModel columnModel0 = tableMovEmpNewDed.getColumnModel();
		columnModel0.getColumn(0).setPreferredWidth(50);
		columnModel0.getColumn(1).setPreferredWidth(150);
		columnModel0.getColumn(2).setPreferredWidth(300);
		columnModel0.getColumn(3).setPreferredWidth(200);
		columnModel0.getColumn(4).setPreferredWidth(200);


		String sqlSelectEmplDed = "SELECT DBO.ATRIBUTO_TIPO_DEDUCCION.ID_DEDUCCION,DBO.ATRIBUTO_TIPO_DEDUCCION.CLAVE,DBO.ATRIBUTO_TIPO_DEDUCCION.DESCRIPCION,DBO.CALCULO_NOMINA.VALOR_DEDUCCION,DBO.CALCULO_NOMINA.PARCIALIDADES "
				+ "FROM DBO.CALCULO_NOMINA "
				+ "LEFT JOIN DBO.ATRIBUTO_TIPO_DEDUCCION ON DBO.ATRIBUTO_TIPO_DEDUCCION.ID_DEDUCCION= DBO.CALCULO_NOMINA.ID_DEDUCCION "
				+ "WHERE DBO.CALCULO_NOMINA.ID_EMPLEADO= '"+lblEmpIdPerp.getText()+"' and dbo.calculo_nomina.id_deduccion != 22 order by dbo.calculo_nomina.id_empleado";
		System.out.println("sql Deducciones: "+sqlSelectEmplDed);
		Object datos[] = new Object[7];
		PoolNYCH nych = new PoolNYCH();
		Connection conEmpDed =null;
		Statement stEmpDed;
		ResultSet resultSetEmpDed;
		try {
			conEmpDed = nych.datasource.getConnection();
			stEmpDed = conEmpDed.createStatement();
			resultSetEmpDed = stEmpDed.executeQuery(sqlSelectEmplDed);
			while(resultSetEmpDed.next()) {
				datos[0] = resultSetEmpDed.getString(1);
				datos[1] = resultSetEmpDed.getString(2);
				datos[2] = resultSetEmpDed.getString(3);
				datos[3] = resultSetEmpDed.getString(4);
				datos[4] = resultSetEmpDed.getString(5);

				if(resultSetEmpDed.getString(1).matches("2")) {
					datos[5] = btnISR;
				}
				if(resultSetEmpDed.getString(1).matches("1")) {
					datos[5] = btnIMSS;
				}
				if(resultSetEmpDed.getString(1).matches("10")) {
					datos[5] = btnINFONAVIT;
				}

				if(resultSetEmpDed.getString(1).matches("23")) {
					datos[5] = btnIMUVI;
				}

				if(resultSetEmpDed.getString(1).matches("12")) {
					datos[5] = btnANTICIPO;
				}



				modelo.addRow(datos);
			}//FIN DEL WHILE


			//			modelo.addTableModelListener(new TableModelListener() {
			//				public void tableChanged(TableModelEvent e) {
			//					if(e.getType() == TableModelEvent.UPDATE) {
			//						JOptionPane.showMessageDialog(null, "Ha cambiado el valor, ¿Es correcto?");
			//						String colName =null;
			//						if(e.getColumn() >= 0 && e.getFirstRow() >=0 ) {//&& e.getFirstRow() == 1
			//							System.out.println("columna : " + e.getColumn());
			//							System.out.println("row: " + e.getFirstRow());
			////							String dato=String.valueOf(modelo.getValueAt(tableMovEmpDed.getSelectedColumn(),tableMovEmpDed.getSelectedRow()));
			////							System.out.println("3: " + dato);
			//							colName =  "VALOR_PERCEPCION";
			//						}
			//						System.out.println("Valor introducido: "+modelo.getValueAt(e.getFirstRow(), e.getColumn()));
			//						String sqlupdt ="INSERT DBO.REPORTE_NOMINA_DEDUCCIONES SET "+ colName +"= '"+ modelo.getValueAt(e.getFirstRow(), e.getColumn()) +"' where id_empleado= '"+ lblEmpIdPerp.getText()+"' and id_percepcion = 26";
			//						System.out.println(sqlupdt);
			//						PoolNYCH nych = new PoolNYCH();
			//						Connection con1 = null;
			//						try {
			//							con1 = nych.datasource.getConnection();
			//							PreparedStatement pps = con1.prepareStatement(sqlupdt);
			//							pps.executeUpdate();
			//							JOptionPane.showMessageDialog(null, "Datos Actualizados");
			//							//dispose();
			//						} catch (SQLException se) {
			//							se.printStackTrace();
			//							JOptionPane.showMessageDialog(null, "Datos No Actualizados");
			//						}finally {
			//							try {
			//								con1.close();
			//							} catch (SQLException ep) {
			//								ep.printStackTrace();
			//								JOptionPane.showMessageDialog(null, ep, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
			//							}
			//						}
			//					}//fin del if e.getype
			//
			//				}
			//			});


			tableMovEmpDed.setModel(modelo);
			tableMovEmpDed.setRowHeight(20);
			tableMovEmpNewDed.setModel(modeloO);
		}catch (SQLException el) {
			el.printStackTrace();
			StringWriter errors = new StringWriter();
			el.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: "+ errors );
			//JOptionPane.showMessageDialog(null, el, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
		}finally {
			try {
				//				con.close();
				conEmpDed.close();
				//conValorDed.close();
			} catch (SQLException ep) {
				ep.printStackTrace();
				StringWriter errors = new StringWriter();
				ep.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: "+ errors );
				//JOptionPane.showMessageDialog(null, ep, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
			}
		}//FIN DEL FINALLY
	}//FIN DEL METODO

	public void mostrarPercepciones() {
		//System.out.println("mostrarPercepciones");
		DefaultTableModel modelo = new DefaultTableModel()
		{

			public boolean isCellEditable(int row, int column) {
				//return super.isCellEditable(row, column);
				if(column==3){
					return true;
				}else {
					return false;
				}
			}
		};
		modelo.addColumn("ID");
		modelo.addColumn("CLAVE");
		modelo.addColumn("DESCRIPCION");

		tableMovPercepciones.setModel(modelo);

		JTableHeader th = new JTableHeader();
		Color colorSilverLight=new Color(176, 196, 222);
		Color colorNegro=new Color(0, 0, 0);
		th = tableMovPercepciones.getTableHeader();
		Font fuente = new Font("Arial", Font.BOLD, 14); 
		th.setFont(fuente); 
		th.setBackground(colorSilverLight);
		th.setForeground(colorNegro);

		TableColumnModel columnModel = tableMovPercepciones.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(50);
		columnModel.getColumn(1).setPreferredWidth(200);
		columnModel.getColumn(2).setPreferredWidth(600);

		String sqlSelect="";
		sqlSelect = "SELECT id_percepcion,clave,descripcion FROM dbo.ATRIBUTO_TIPO_PERCEPCION  where id_percepcion BETWEEN '3' AND '39'";
		String datos[] = new String[3];
		PoolNYCH nych = new PoolNYCH();
		Connection con =null;
		Statement st;
		ResultSet resultSet;
		try {
			con = nych.datasource.getConnection();
			st = con.createStatement();
			resultSet = st.executeQuery(sqlSelect);
			while(resultSet.next()) {
				datos[0] = resultSet.getString(1);
				datos[1] = resultSet.getString(2);
				datos[2] = resultSet.getString(3);
				modelo.addRow(datos);
				//modeloO.addRow(datos);
			}//FIN DEL WHILE
			tableMovPercepciones.setModel(modelo);
		}catch (SQLException el) {
			el.printStackTrace();
			StringWriter errors = new StringWriter();
			el.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: "+ errors );
			//JOptionPane.showMessageDialog(null, el, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
		}finally {
			try {
				con.close();
			} catch (SQLException ep) {
				ep.printStackTrace();
				StringWriter errors = new StringWriter();
				ep.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: "+ errors );
				//JOptionPane.showMessageDialog(null, ep, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
			}
		}//FIN DEL FINALLY
	}//FIN DEL METODO



	public void mostrarDeducciones() {
		//System.out.println("mostrarDeducciones");
		DefaultTableModel modelo = new DefaultTableModel()
		{

			public boolean isCellEditable(int row, int column) {
				//return super.isCellEditable(row, column);
				if(column==3){
					return true;
				}else {
					return false;
				}
			}
		};
		modelo.addColumn("ID");
		modelo.addColumn("CLAVE");
		modelo.addColumn("DESCRIPCION");

		tableMovDeducciones.setModel(modelo);

		JTableHeader th = new JTableHeader();
		Color colorSilverLight=new Color(176, 196, 222);
		Color colorNegro=new Color(0, 0, 0);
		th = tableMovDeducciones.getTableHeader();
		Font fuente = new Font("Arial", Font.BOLD, 14); 
		th.setFont(fuente); 
		th.setBackground(colorSilverLight);
		th.setForeground(colorNegro);

		TableColumnModel columnModel = tableMovDeducciones.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(50);
		columnModel.getColumn(1).setPreferredWidth(200);
		columnModel.getColumn(2).setPreferredWidth(600);

		String sqlSelect="";
		sqlSelect = "SELECT id_deduccion,clave,descripcion FROM dbo.ATRIBUTO_TIPO_DEDUCCION where id_deduccion BETWEEN '3' AND '22'";
		String datos[] = new String[3];
		PoolNYCH nych = new PoolNYCH();
		Connection con =null;
		Statement st;
		ResultSet resultSet;
		try {
			con = nych.datasource.getConnection();
			st = con.createStatement();
			resultSet = st.executeQuery(sqlSelect);
			while(resultSet.next()) {
				datos[0] = resultSet.getString(1);
				datos[1] = resultSet.getString(2);
				datos[2] = resultSet.getString(3);
				modelo.addRow(datos);
				//modeloO.addRow(datos);
			}//FIN DEL WHILE
			tableMovDeducciones.setModel(modelo);
		}catch (SQLException el) {
			el.printStackTrace();
			StringWriter errors = new StringWriter();
			el.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: "+ errors );
			//JOptionPane.showMessageDialog(null, el, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
		}finally {
			try {
				con.close();
			} catch (SQLException ep) {
				ep.printStackTrace();
				StringWriter errors = new StringWriter();
				ep.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: "+ errors );
				//JOptionPane.showMessageDialog(null, ep, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
			}
		}//FIN DEL FINALLY
	}//FIN DEL METODO



	public static ArrayList<String> getEmple() {
		PoolNYCH nych = new PoolNYCH();
		Connection connect = null;
		Statement st;
		ResultSet resultSet = null;
		ArrayList<String> lista = new ArrayList<String>();
		String sqlComboPuesto = "SELECT ID_EMPLEADO,ID_PUESTO from DBO.EMPLEADOS ORDER BY ID_PUESTO";
		try {
			connect = nych.datasource.getConnection();
			st = connect.createStatement();
			resultSet = st.executeQuery(sqlComboPuesto);
		}catch(Exception exp) {
			exp.printStackTrace();
			StringWriter errors = new StringWriter();
			exp.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: "+ errors );
			//JOptionPane.showMessageDialog(null, exp, "Error de conexion", JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
		}try {
			while(resultSet.next()){
				lista.add(resultSet.getString("ID_EMPLEADO"));
			}
		}catch(Exception et) {
			et.printStackTrace();
			StringWriter errors = new StringWriter();
			et.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: "+ errors );
			//JOptionPane.showMessageDialog(null, et, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
		}finally {
			try {
				connect.close();
			} catch (SQLException ep) {
				ep.printStackTrace();
				StringWriter errors = new StringWriter();
				ep.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: "+ errors );
				//JOptionPane.showMessageDialog(null, ep, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
			}
		}
		return lista;
	}

	class MyButton extends JButton implements MouseMotionListener{
		private static final long serialVersionUID = 1L;

		public MyButton(String text){

			super.setText(text);

			addMouseMotionListener(this);
		}

		public void mouseDragged(MouseEvent mme) {

			setLocation(

					this.getX() + mme.getX() - this.getWidth() / 2,

					this.getY() + mme.getY() - this.getHeight() / 2

					);

		}

		public void mouseMoved(MouseEvent mme) {

		}
	}


	/***FALTAS**///

	public double calcularIMSSConFaltas() {
		System.out.println("calcularIMSS");
		double sumaPorcentaje = 0.0; 
		double formula = 0.0; 
		ArrayList<Double> listaPorcentajeIMSS = new ArrayList<Double>();
		listaPorcentajeIMSS = getListaPorcentajeIMSS();
		System.out.println("*********");
		for(int i = 0; i<listaPorcentajeIMSS.size();i++){
			System.out.println("indice: "+ i + " |valor: " + listaPorcentajeIMSS.get(i));
		}
		System.out.println("*********");

		sumaPorcentaje = listaPorcentajeIMSS.get(0) + listaPorcentajeIMSS.get(6) + listaPorcentajeIMSS.get(9) + listaPorcentajeIMSS.get(12) + listaPorcentajeIMSS.get(15) + 
				listaPorcentajeIMSS.get(18) + listaPorcentajeIMSS.get(21) + listaPorcentajeIMSS.get(24);
		//sumaPorcentaje = listaPorcentajeIMSS.get(0) + listaPorcentajeIMSS.get(3) + listaPorcentajeIMSS.get(6) + listaPorcentajeIMSS.get(9) + listaPorcentajeIMSS.get(12) + listaPorcentajeIMSS.get(15) + 
		//listaPorcentajeIMSS.get(18) + listaPorcentajeIMSS.get(21) + listaPorcentajeIMSS.get(24);
		System.out.println("Suma Porcentaje: " + sumaPorcentaje);

		PoolNYCH nych = new PoolNYCH();
		Connection conSDI = null;
		Statement stSDI;
		ResultSet resultSetSDI;
		String sqlSalario ="select sdi,nombre from empleados where CLAVE = '"+lblEmpIdPerp.getText()+"'";//'"+lblEmpIdPerp.getText()+"'
		String datos[] = new String[2];
		try {
			conSDI = nych.datasource.getConnection();
			stSDI = conSDI.createStatement();
			resultSetSDI = stSDI.executeQuery(sqlSalario);

			while(resultSetSDI.next()){ 
				datos[0] = resultSetSDI.getString(1);
				datos[1] = resultSetSDI.getString(2);
			}
			double sdi =  Double.parseDouble(datos[0]);
			String nombre = datos[1];
			System.out.println("SDI: " + sdi);

			formula = sumaPorcentaje/100 * sdi * 13;
			System.out.println("Salario Diario Integrado " + formula +" ,para el empleado: " + nombre);
			actualizaValorIMSS(formula);
		}catch(Exception ep) {
			ep.printStackTrace();
			StringWriter errors = new StringWriter();
			ep.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: "+ errors );
			//JOptionPane.showMessageDialog(null, ep, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
		}finally {
			try {
				conSDI.close();
			} catch (SQLException ep) {
				ep.printStackTrace();
				StringWriter errors = new StringWriter();
				ep.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: "+ errors );
				//JOptionPane.showMessageDialog(null, ep, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
			}
		}


		return formula;
	}

	public double calcularISRConFaltas(){
		System.out.println("calcularISR");
		double resta = 0;
		double tasa = 0;
		double cuota = 0;
		double isr = 0;
		PoolNYCH nych = new PoolNYCH();
		Connection conSalario = null;
		Statement stSalario;
		ResultSet resultSetSalario;

		ArrayList<Double> listaDatosISR = new ArrayList<Double>();
		listaDatosISR = getListaDatosISR();
		System.out.println("*********");
		for(int i = 0; i<listaDatosISR.size();i++){
			System.out.println("indiceDatosISR: "+ i + " |valorDatosISR: " + listaDatosISR.get(i));
		}
		System.out.println("*********");


		ArrayList<Double> listaSubsidioISR = new ArrayList<Double>();
		listaSubsidioISR = getListaSubsidioISR();
		System.out.println("*********");
		for(int i = 0; i<listaSubsidioISR.size();i++){
			System.out.println("indiceSubsidio: "+ i + " |valorSubsidio: " + listaSubsidioISR.get(i));
		}
		System.out.println("*********");

		double cantidadMaximaParaSubsidioPorAño =  listaSubsidioISR.get(20);
		System.out.println("----------");
		System.out.println("Cantidad Maxima Para Subsidio Por Año En Curso: " + cantidadMaximaParaSubsidioPorAño);
		System.out.println("----------");
		String sqlSalario ="select salario from puestos where no_puesto = '"+lblIdPuestoEmpMov.getText()+"'";//'"+lblIdPuestoEmpMov.getText()+"'
		String datos[] = new String[1];
		try {
			conSalario = nych.datasource.getConnection();
			stSalario = conSalario.createStatement();
			resultSetSalario = stSalario.executeQuery(sqlSalario);

			while(resultSetSalario.next()){ 
				//datos[0] = resultSetSalario.getString(1);
				datos[0] = lblAuxiliarSalario.getText();
			}
			double salario =  Double.parseDouble(datos[0]);
			System.out.println("Salario: " + salario);

			if(salario > listaDatosISR.get(0)  && salario < listaDatosISR.get(3) ) {
				System.out.println("Limite Inferior:" + listaDatosISR.get(0));
				resta = salario - listaDatosISR.get(0);
				tasa = resta * listaDatosISR.get(2)/100;
				cuota = tasa + listaDatosISR.get(1);
				if(salario>cantidadMaximaParaSubsidioPorAño) {
					System.out.println("sin subsidio");
					isr = cuota - listaSubsidioISR.get(21);
				}else {
					System.out.println("con subsidio");
					isr = cuota - listaSubsidioISR.get(21);
				}

				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				System.out.println("resta: " + resta);
				System.out.println("tasa: " + tasa);
				System.out.println("cuota: " + cuota);
				System.out.println("isr: " + isr);
				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");


			}

			if(salario > listaDatosISR.get(3) && salario < listaDatosISR.get(6) ) {
				System.out.println("Limite Inferior:" + listaDatosISR.get(3));
				resta = salario - listaDatosISR.get(3);
				tasa = resta * listaDatosISR.get(5)/100;
				cuota = tasa + listaDatosISR.get(4);
				if(salario>cantidadMaximaParaSubsidioPorAño) {
					System.out.println("sin subsidio");
					isr = cuota - listaSubsidioISR.get(21);
				}else {
					System.out.println("con subsidio");
					isr = cuota - listaSubsidioISR.get(21);
				}

				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				System.out.println("resta: " + resta);
				System.out.println("tasa: " + tasa);
				System.out.println("cuota: " + cuota);
				System.out.println("isr: " + isr);
				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");

			}

			if(salario > listaDatosISR.get(6)  &&  salario < listaDatosISR.get(9) ) {
				System.out.println("Limite Inferior:" + listaDatosISR.get(6));
				resta = salario - listaDatosISR.get(6);
				tasa = resta * listaDatosISR.get(8)/100;
				cuota = tasa + listaDatosISR.get(7);
				if(salario>cantidadMaximaParaSubsidioPorAño) {
					System.out.println("sin subsidio");
					isr = cuota - listaSubsidioISR.get(21);
				}else {
					System.out.println("con subsidio");
					isr = cuota - listaSubsidioISR.get(21);
				}

				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				System.out.println("resta: " + resta);
				System.out.println("tasa: " + tasa);
				System.out.println("cuota: " + cuota);
				System.out.println("isr: " + isr);
				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
			}

			if(salario > listaDatosISR.get(9)  && salario < listaDatosISR.get(12)) {
				System.out.println("Limite Inferior:" + listaDatosISR.get(9));
				resta = salario - listaDatosISR.get(9);
				tasa = resta * listaDatosISR.get(11)/100;
				cuota = tasa + listaDatosISR.get(10);
				if(salario>cantidadMaximaParaSubsidioPorAño) {
					System.out.println("sin subsidio");
					isr = cuota - listaSubsidioISR.get(21);
				}else {
					System.out.println("con subsidio");
					isr = cuota - listaSubsidioISR.get(21);
				}

				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				System.out.println("resta: " + resta);
				System.out.println("tasa: " + tasa);
				System.out.println("cuota: " + cuota);
				System.out.println("isr: " + isr);
				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
			}

			if(salario > listaDatosISR.get(12)  && salario < listaDatosISR.get(15)) {
				System.out.println("Limite Inferior:" + listaDatosISR.get(12));
				resta = salario - listaDatosISR.get(12);
				tasa = resta * listaDatosISR.get(14)/100;
				cuota = tasa + listaDatosISR.get(13);
				if(salario>cantidadMaximaParaSubsidioPorAño) {
					System.out.println("sin subsidio");
					isr = cuota - listaSubsidioISR.get(21);
				}else {
					System.out.println("con subsidio");
					isr = cuota - listaSubsidioISR.get(21);
				}

				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				System.out.println("resta: " + resta);
				System.out.println("tasa: " + tasa);
				System.out.println("cuota: " + cuota);
				System.out.println("isr: " + isr);
				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");

			}

			if(salario > listaDatosISR.get(15)  && salario < listaDatosISR.get(18)) {
				System.out.println("Limite Inferior:" + listaDatosISR.get(15));
				resta = salario - listaDatosISR.get(15);
				tasa = resta * listaDatosISR.get(17)/100;
				cuota = tasa + listaDatosISR.get(16);
				if(salario>cantidadMaximaParaSubsidioPorAño) {
					System.out.println("sin subsidio");
					isr = cuota - listaSubsidioISR.get(21);
				}else {
					System.out.println("con subsidio");
					isr = cuota - listaSubsidioISR.get(21);
				}

				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				System.out.println("resta: " + resta);
				System.out.println("tasa: " + tasa);
				System.out.println("cuota: " + cuota);
				System.out.println("isr: " + isr);
				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");

			}

			if(salario > listaDatosISR.get(18) &&  salario < listaDatosISR.get(21)) {
				System.out.println("Limite Inferior:" + listaDatosISR.get(18));
				resta = salario - listaDatosISR.get(18);
				tasa = resta * listaDatosISR.get(20)/100;
				cuota = tasa + listaDatosISR.get(19);
				if(salario>cantidadMaximaParaSubsidioPorAño) {
					System.out.println("sin subsidio");
					isr = cuota - listaSubsidioISR.get(21);
				}else {
					System.out.println("con subsidio");
					isr = cuota - listaSubsidioISR.get(21);
				}

				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				System.out.println("resta: " + resta);
				System.out.println("tasa: " + tasa);
				System.out.println("cuota: " + cuota);
				System.out.println("isr: " + isr);
				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");

			}

			if(salario > listaDatosISR.get(21)  && salario < listaDatosISR.get(24)) {
				System.out.println("Limite Inferior:" + listaDatosISR.get(21));
				resta = salario - listaDatosISR.get(21);
				tasa = resta * listaDatosISR.get(23)/100;
				cuota = tasa + listaDatosISR.get(122);
				if(salario>cantidadMaximaParaSubsidioPorAño) {
					System.out.println("sin subsidio");
					isr = cuota - listaSubsidioISR.get(21);
				}else {
					System.out.println("con subsidio");
					isr = cuota - listaSubsidioISR.get(21);
				}

				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				System.out.println("resta: " + resta);
				System.out.println("tasa: " + tasa);
				System.out.println("cuota: " + cuota);
				System.out.println("isr: " + isr);
				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");

			}

			if(salario > listaDatosISR.get(24)  && salario < listaDatosISR.get(27) ) {
				System.out.println("Limite Inferior:" + listaDatosISR.get(24));
				resta = salario - listaDatosISR.get(24);
				tasa = resta * listaDatosISR.get(26)/100;
				cuota = tasa + listaDatosISR.get(25);
				if(salario>cantidadMaximaParaSubsidioPorAño) {
					System.out.println("sin subsidio");
					isr = cuota - listaSubsidioISR.get(21);
				}else {
					System.out.println("con subsidio");
					isr = cuota - listaSubsidioISR.get(21);
				}

				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				System.out.println("resta: " + resta);
				System.out.println("tasa: " + tasa);
				System.out.println("cuota: " + cuota);
				System.out.println("isr: " + isr);
				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");

			}

			if(salario > listaDatosISR.get(27)  && salario < listaDatosISR.get(30) ) {
				System.out.println("Limite Inferior:" + listaDatosISR.get(27));
				resta = salario - listaDatosISR.get(27);
				tasa = resta * listaDatosISR.get(29)/100;
				cuota = tasa + listaDatosISR.get(28);
				if(salario>cantidadMaximaParaSubsidioPorAño) {
					System.out.println("sin subsidio");
					isr = cuota - listaSubsidioISR.get(21);
				}else {
					System.out.println("con subsidio");
					isr = cuota - listaSubsidioISR.get(21);
				}

				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				System.out.println("resta: " + resta);
				System.out.println("tasa: " + tasa);
				System.out.println("cuota: " + cuota);
				System.out.println("isr: " + isr);
				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
			}

			if(salario > listaDatosISR.get(30) ) {
				System.out.println("Limite Inferior:" + listaDatosISR.get(30));
				resta = salario - listaDatosISR.get(30);
				tasa = resta * listaDatosISR.get(32)/100;
				cuota = tasa + listaDatosISR.get(31);
				if(salario>cantidadMaximaParaSubsidioPorAño) {
					System.out.println("sin subsidio");
					isr = cuota - listaSubsidioISR.get(21);
				}else {
					System.out.println("con subsidio");
					isr = cuota - listaSubsidioISR.get(21);
				}

				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				System.out.println("resta: " + resta);
				System.out.println("tasa: " + tasa);
				System.out.println("cuota: " + cuota);
				System.out.println("isr: " + isr);
				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
			}
			actualizaValorIsr(isr,null);
		}catch(Exception es) {
			es.printStackTrace();
			LOG.info("Excepción: " + es);
			//JOptionPane.showMessageDialog(null, es, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
		}finally {
			try {
				conSalario.close();
			} catch (SQLException ep) {
				ep.printStackTrace();
				LOG.info("Excepción: " + ep);
				//JOptionPane.showMessageDialog(null, ep, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
			}
		}//FIN DEL FINALLY
		return isr;	
	}


	public void mostrarDatosPerpCalcConFaltas(String valor) {
		System.out.println("mostrarDatosPerpCalcConFaltas");
		final DefaultTableModel modelo = new DefaultTableModel()
		{

			public boolean isCellEditable(int row, int column) {
				//return super.isCellEditable(row, column);
				if(column==4){
					return true;
				}else {
					return false;
				}
			}
		};
		modelo.addColumn("ID");
		modelo.addColumn("CLAVE");
		modelo.addColumn("PERCEPCION");
		modelo.addColumn("VALOR PRECEPCION");

		final DefaultTableModel modeloO = new DefaultTableModel(){

			public boolean isCellEditable(int row, int column) {
				//return super.isCellEditable(row, column);
				if(column==4){
					return true;
				}else {
					return false;
				}
			}
		};
		modeloO.addColumn("ID");
		modeloO.addColumn("CLAVE");
		modeloO.addColumn("PERCEPCION");
		modeloO.addColumn("VALOR PERCEPCION");

		tableMovEmpPerp.setModel(modelo);
		tableMovEmpNewPerp.setModel(modeloO);
		tableMovEmpPerp.setBackground(Color.WHITE);
		tableMovEmpNewPerp.setBackground(Color.WHITE);

		JTableHeader th = new JTableHeader();
		JTableHeader th1 = new JTableHeader();
		Color colorSilverLight=new Color(176, 196, 222);
		Color colorNegro=new Color(0, 0, 0);
		th = tableMovEmpPerp.getTableHeader();
		th1 = tableMovEmpNewPerp.getTableHeader();
		Font fuente = new Font("Cooper Black", Font.BOLD, 14); 
		th.setFont(fuente); 
		th1.setFont(fuente); 
		th.setBackground(colorSilverLight);
		th1.setBackground(colorSilverLight);
		th.setForeground(colorNegro);
		th1.setForeground(colorNegro);

		TableColumnModel columnModel = tableMovEmpPerp.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(50);
		columnModel.getColumn(1).setPreferredWidth(80);
		columnModel.getColumn(2).setPreferredWidth(300);
		columnModel.getColumn(3).setPreferredWidth(300);

		TableColumnModel columnModel0 = tableMovEmpNewPerp.getColumnModel();
		columnModel0.getColumn(0).setPreferredWidth(50);
		columnModel0.getColumn(1).setPreferredWidth(100);
		columnModel0.getColumn(2).setPreferredWidth(300);
		columnModel0.getColumn(3).setPreferredWidth(200);


		//		String sqlSelect="";
		String sqlSelectEmplPerp="";
		//		String sqlSelectValorPerp="";
		//		String sqlSelectValorPerpDespensa="";
		//		sqlSelect = "select dbo.empleados.nombre from dbo.CALCULO_NOMINA left join dbo.empleados on dbo.empleados.clave = dbo.CALCULO_NOMINA.ID_EMPLEADO WHERE DBO.CALCULO_NOMINA.ID_EMPLEADO='"+lblEmpIdPerp.getText()+"'";
		sqlSelectEmplPerp = "SELECT DBO.ATRIBUTO_TIPO_PERCEPCION.ID_PERCEPCION,DBO.ATRIBUTO_TIPO_PERCEPCION.CLAVE,DBO.ATRIBUTO_TIPO_PERCEPCION.DESCRIPCION,"
				+ "DBO.CALCULO_NOMINA.VALOR_PERCEPCION  "
				+ "FROM DBO.CALCULO_NOMINA "
				+ "LEFT JOIN DBO.ATRIBUTO_TIPO_PERCEPCION ON DBO.ATRIBUTO_TIPO_PERCEPCION.ID_PERCEPCION = DBO.CALCULO_NOMINA.ID_PERCEPCION "
				+ "WHERE DBO.CALCULO_NOMINA.ID_EMPLEADO='"+lblEmpIdPerp.getText()+"' and CALCULO_NOMINA.id_percepcion !=40 order by dbo.calculo_nomina.id_empleado";
		//		sqlSelectValorPerp ="select DBO.PUESTOS.SALARIO from dbo.PUESTOS RIGHT join dbo.CALCULO_NOMINA on dbo.CALCULO_NOMINA.ID_PUESTO = dbo.PUESTOS.NO_PUESTO WHERE DBO.CALCULO_NOMINA.ID_EMPLEADO='"+lblEmpIdPerp.getText()+"'";
		//		//			sqlSelectValorPerp ="select dbo.CALCULO_NOMINA.VALOR_PERCEPCION from dbo.CALCULO_NOMINA RIGHT join dbo.puestos on dbo.puestos.NO_PUESTO = dbo.CALCULO_NOMINA.ID_PUESTO WHERE DBO.CALCULO_NOMINA.ID_EMPLEADO=1535";
		//		sqlSelectValorPerpDespensa="select VALOR_PERCEPCION from dbo.CALCULO_NOMINA WHERE DBO.CALCULO_NOMINA.ID_EMPLEADO='"+lblEmpIdPerp.getText()+"'";


		String datos[] = new String[4];
		PoolNYCH nych = new PoolNYCH();
		Connection conEmpPerp =null;
		Statement stEmpPerp;
		ResultSet resultSetEmpPerp;
		try {
			conEmpPerp = nych.datasource.getConnection();
			stEmpPerp = conEmpPerp.createStatement();
			resultSetEmpPerp = stEmpPerp.executeQuery(sqlSelectEmplPerp);
			while(resultSetEmpPerp.next()) {//resultSet.next() && resultSetValorPer.next() && resultSetValorPerDes.next()
				datos[0] = resultSetEmpPerp.getString(1);
				System.out.println("datos0: " + datos[0]);
				datos[1] = resultSetEmpPerp.getString(2);
				System.out.println("datos1: " + datos[1]);
				datos[2] = resultSetEmpPerp.getString(3);
				System.out.println("datos2: " + datos[2]);
				datos[3] = resultSetEmpPerp.getString(4);
				System.out.println("datos3: " + datos[3]);
				//modelo.addRow(datos);

				int clave =  Integer.parseInt(datos[1]);
				System.out.println("clave: "+ clave);
				double res1 = 0;
				if(clave!=29) {
					double salario =  Double.parseDouble(datos[3]);
					System.out.println("salario: " + salario);
					double res = (salario/14);
					System.out.println("res: "+res);
					res1 = res*13;
					System.out.println("res1: "+res1);
					lblAuxiliarSalario.setText(String.valueOf(res1));

					//					formula = sumaPorcentaje/100 * sdi * 14;
					//					double SDI = res1/14;


					datos[0] = resultSetEmpPerp.getString(1);
					datos[1] = resultSetEmpPerp.getString(2);
					datos[2] = resultSetEmpPerp.getString(3);
					datos[3] = String.valueOf(res1);
				}

				if(clave==29) {
					double despensa = Double.parseDouble(datos[3]);
					System.out.println("despensa: "+ despensa);
					double res = (despensa/14);
					System.out.println("res: "+res);
					res1 = res*13;
					System.out.println("res1: "+res1);

					datos[0] = resultSetEmpPerp.getString(1);
					datos[1] = resultSetEmpPerp.getString(2);
					datos[2] = resultSetEmpPerp.getString(3);
					datos[3] = String.valueOf(res1);

				}

				modelo.addRow(datos);


			}//FIN DEL WHILE


			//			modeloO.addTableModelListener(new TableModelListener() {
			//				public void tableChanged(TableModelEvent e) {
			//					if(e.getType() == TableModelEvent.UPDATE) {
			//						JOptionPane.showMessageDialog(null, "Ha cambiado el valor, ¿Es correcto?");
			//						String colName =null;
			//						if(e.getColumn() == 3 && e.getFirstRow() == 1) {
			//							//										System.out.println("1: " + e.getColumn());
			//							//										System.out.println("2: " + e.getFirstRow());
			//							//										String dato=String.valueOf(modeloO.getValueAt(tableMovEmpNewPerp.getSelectedRow(),3));
			//							//										System.out.println("3: " + dato);
			//							colName =  "VALOR_PERCEPCION";
			//						}
			//						String sqlupdt ="UPDATE DBO.REPORTE_NOMINA_PERCEPCIONES SET "+ colName +"= '"+ modeloO.getValueAt(e.getFirstRow(), e.getColumn()) +"' where id_empleado= '"+ lblEmpIdPerp.getText()+"' and id_percepcion = 26";
			//						System.out.println(sqlupdt);
			//						try {
			//							con = nych.datasource.getConnection();
			//							PreparedStatement pps = con.prepareStatement(sqlupdt);
			//							pps.executeUpdate();
			//							JOptionPane.showMessageDialog(null, "Datos Actualizados");
			//							//dispose();
			//						} catch (SQLException se) {
			//							se.printStackTrace();
			//							JOptionPane.showMessageDialog(null, "Datos No Actualizados");
			//						}finally {
			//							try {
			//								con.close();
			//							} catch (SQLException ep) {
			//								ep.printStackTrace();
			//								JOptionPane.showMessageDialog(null, ep, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
			//							}
			//						}
			//					}//fin del if e.getype
			//
			//				}
			//			});
			tableMovEmpPerp.setModel(modelo);
			tableMovEmpNewPerp.setModel(modeloO);


		}catch (SQLException el) {
			el.printStackTrace();
			StringWriter errors = new StringWriter();
			el.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: "+ errors );
			//JOptionPane.showMessageDialog(null, el, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
		}finally {
			try {
				//				con.close();
				conEmpPerp.close();
				//				conValorPer.close();
				//				conValorPerDes.close();
			} catch (SQLException ep) {
				ep.printStackTrace();
				StringWriter errors = new StringWriter();
				ep.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: "+ errors );
				//JOptionPane.showMessageDialog(null, ep, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
			}
		}//FIN DEL FINALLY
	}//FIN DEL METODO

	//metodo para el tiempo de busqueda
	//Este es el evento que se ejecuta cuando un JFrame se carga
	public void windowOpened(ActionEvent e){
		final long SLEEP_TIME = 1 * 1000;
		SwingWorker<Void, Void> mySwingWorker = new SwingWorker<Void, Void>(){
			protected Void doInBackground() throws Exception {

				//mostrarDatosFechasPeriodo("");

				//				ArrayList<String> listaFechas = new ArrayList<String>();
				//				listaFechas = getComboBoxPeriodo();
				//				comboBoxNumeroPeriodo.addItem("Seleccione Uno");
				//				for (int i = 0; i < listaFechas.size(); i++) {
				//					comboBoxNumeroPeriodo.addItem(listaFechas.get(i));
				//				}

				//textFieldPeriodo.setText((String) comboBoxNumeroPeriodo.getSelectedItem());

				// mimic some long-running process here...
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


	//	void Ocultar_Componte(){
	//		btnEmpDedAgr.setName("Provedores");
	//		btnAgregarPerp.setName("Personal");
	//		btnAgregarDed.setName("Expendedores");
	//
	//		java.awt.Component[] d = this.getComponents();
	//		for( int index = 0 ; index < d.length ; index++ )
	//		{
	//			System.out.println(d[index].getName());
	//			if( ! (( d[index].getName() == "Provedores" ) || ( d[index].getName() == "Personal" ) || ( d[index].getName() == "Expendedores" )) )
	//			{
	//				this.remove( d[ index ] );
	//			}
	//		}
	//
	//	}



	public static String getPrimerDiaDeLaSemana() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		String fechaInicio = dateFormat.format(cal.getTime()); 
		//System.out.println("fecha Inicio De La Semana: "+fechaInicio);
		return fechaInicio;
	}

	public static String getSegundoDiaDelaSemana() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
		calendar.set(Calendar.AM_PM, Calendar.PM);
		calendar.set(Calendar.HOUR, 11);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		String fechaFinal = dateFormat.format(calendar.getTime()); 
		//System.out.println("fecha final De La Semana: "+fechaFinal);
		return fechaFinal;
	}

	public static String getTercerDiaDelaSemana() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
		calendar.set(Calendar.AM_PM, Calendar.PM);
		calendar.set(Calendar.HOUR, 11);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		String fechaFinal = dateFormat.format(calendar.getTime()); 
		//System.out.println("fecha final De La Semana: "+fechaFinal);
		return fechaFinal;
	}

	public static String getCuartoDiaDelaSemana() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
		calendar.set(Calendar.AM_PM, Calendar.PM);
		calendar.set(Calendar.HOUR, 11);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		String fechaFinal = dateFormat.format(calendar.getTime()); 
		//System.out.println("fecha final De La Semana: "+fechaFinal);
		return fechaFinal;
	}

	public static String getQuintoDiaDelaSemana() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
		calendar.set(Calendar.AM_PM, Calendar.PM);
		calendar.set(Calendar.HOUR, 11);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		String fechaFinal = dateFormat.format(calendar.getTime()); 
		//System.out.println("fecha final De La Semana: "+fechaFinal);
		return fechaFinal;
	}

	public static String getSextoDiaDelaSemana() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		calendar.set(Calendar.AM_PM, Calendar.PM);
		calendar.set(Calendar.HOUR, 11);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		String fechaFinal = dateFormat.format(calendar.getTime()); 
		//System.out.println("fecha final De La Semana: "+fechaFinal);
		return fechaFinal;
	}

	public static String getUltimoDiaDelaSemana() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		calendar.set(Calendar.AM_PM, Calendar.PM);
		calendar.set(Calendar.HOUR, 11);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		String fechaFinal = dateFormat.format(calendar.getTime()); 
		//System.out.println("fecha final De La Semana: "+fechaFinal);
		return fechaFinal;
	}

	public static String getUPrimerDiaDelMes() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
				cal.getActualMinimum(Calendar.DAY_OF_MONTH),
				cal.getMinimum(Calendar.HOUR_OF_DAY),
				cal.getMinimum(Calendar.MINUTE),
				cal.getMinimum(Calendar.SECOND));
		String fechaPrimerDiaDelMes = dateFormat.format(cal.getTime()); 
		System.out.println("fechaPrimerDiaDelMes: "+fechaPrimerDiaDelMes);
		return fechaPrimerDiaDelMes;
	}

	public static String getUltimoDiaDelMes() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
				cal.getActualMaximum(Calendar.DAY_OF_MONTH),
				cal.getMaximum(Calendar.HOUR_OF_DAY),
				cal.getMaximum(Calendar.MINUTE),
				cal.getMaximum(Calendar.SECOND));
		String fechafinalDelMes = dateFormat.format(cal.getTime()); 
		System.out.println("fechafinalDelMes: "+fechafinalDelMes);
		return fechafinalDelMes;
	}

	public double calcularISRNuevoEmpleado(String IdPuesto,String clave){
		System.out.println("Inicia el proceso de calcularISR Nuevo");
		double resta = 0;
		double tasa = 0;
		double cuota = 0;
		double isr = 0;
		PoolNYCH nych = new PoolNYCH();
		Connection conSalario = null;
		Statement stSalario;
		ResultSet resultSetSalario;

		ArrayList<Double> listaDatosISR = new ArrayList<Double>();
		listaDatosISR = getListaDatosISR();
		System.out.println("*********");
		for(int i = 0; i<listaDatosISR.size();i++){
			System.out.println("indiceDatosISR: "+ i + " |valorDatosISR: " + listaDatosISR.get(i));
		}
		System.out.println("*********");


		ArrayList<Double> listaSubsidioISR = new ArrayList<Double>();
		listaSubsidioISR = getListaSubsidioISR();
		System.out.println("*********");
		for(int i = 0; i<listaSubsidioISR.size();i++){
			//System.out.println("indiceSubsidio: "+ i + " |valorSubsidio: " + listaSubsidioISR.get(i));
		}
		//System.out.println("*********");

		double cantidadMaximaParaSubsidioPorAño =  listaSubsidioISR.get(20);


		String nombre = lblEmplNombrePerp.getText();
		String ApPat = lblEmpApPatPerp.getText();
		String ApMat = lblEmpApMatPerp.getText();
		String nombreConcat = nombre +" "+ApPat+" "+ApMat;

		Date result = new Date();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String convertido = formatter.format(result);
		String carpeta= "C:\\Informacion de Procesos del NYCH";
		String carpetaInterna= "\\ISR";
		String archivo = "\\ISR_"+convertido+"_"+nombreConcat+".txt";
		File crearCarpeta = new File(carpeta+carpetaInterna);
		File crearArchivo = new File(carpeta+carpetaInterna+archivo);
		if(crearArchivo.exists()) {
			//			System.out.println("el archivo ya existe,pero se sobreescribira");
			//			JOptionPane.showMessageDialog(null,"El archivo de los cálculos está en la carpeta: "+ crearCarpeta);
		}else {
			System.out.println("No existen pero se creara la ruta y el archivo");
			//JOptionPane.showMessageDialog(null,"El archivo de los cálculos está en la carpeta:"+ crearCarpeta);
			crearCarpeta.mkdirs();
			try {
				if(crearArchivo.createNewFile()) {
					System.out.println("archivo creado");
					JOptionPane.showMessageDialog(null,"El archivo de los cálculos está en la carpeta: " + crearCarpeta);
				}else{
					System.out.println("el archivo no fue creado");
					JOptionPane.showMessageDialog(null,"el archivo no fue creado");
				}
			}catch(Exception io) {
				io.printStackTrace();
			}
		}

		try {
			FileWriter fw;

			fw = new FileWriter(crearArchivo);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);


			pw.print("<!DOCTYPE html>");
			pw.println("<html>");
			pw.println("<head>");
			pw.println("PROCESO DEL CALCULO");
			pw.println("</head>");
			pw.println("<body>");
			pw.println("<p>");
			pw.println("Inicia el proceso de calcular ISR"+"<br>");
			System.out.println("----------");
			pw.println("----------"+"<br>");
			System.out.println("Cantidad maxima para subsidio por año en curso: " + cantidadMaximaParaSubsidioPorAño);
			pw.println("Cantidad maxima para subsidio por año en curso: " + cantidadMaximaParaSubsidioPorAño+"<br>");
			System.out.println("---------- ");
			pw.println("----------"+"<br>");
			String sqlSalario ="select salario from puestos where no_puesto = '"+IdPuesto+"'";//'"+lblIdPuestoEmpMov.getText()+"'
			System.out.println("sqlSalarioNuevoPuesto: " + sqlSalario);
			String datos[] = new String[1];


			conSalario = nych.datasource.getConnection();
			stSalario = conSalario.createStatement();
			resultSetSalario = stSalario.executeQuery(sqlSalario);

			while(resultSetSalario.next()){ 
				datos[0] = resultSetSalario.getString(1);
			}
			double salario =  Double.parseDouble(datos[0]);
			pw.println("Salario:" + salario+"<br>");
			System.out.println("Salario: " + salario);

			if(salario > listaDatosISR.get(0)  && salario < listaDatosISR.get(3) ) {
				System.out.println("Limite Inferior:" + listaDatosISR.get(0)+" ");
				pw.println(" Limite Inferior: " + listaDatosISR.get(0)+"<br>");
				resta = salario - listaDatosISR.get(0);
				tasa = resta * listaDatosISR.get(2)/100;
				cuota = tasa + listaDatosISR.get(1);
				if(salario>cantidadMaximaParaSubsidioPorAño) {
					System.out.println("sin subsidio");
					pw.println(" sin subsidio "+"<br>");
					isr = cuota - listaSubsidioISR.get(21);
				}else {
					System.out.println("con subsidio");
					pw.println(" con subsidio "+"<br>");
					isr = cuota - listaSubsidioISR.get(21);
				}

				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				pw.println(" ----------- "+"<br>");

				System.out.println("resta: " + resta);
				pw.println(" \nresta: " + resta+"<br>");

				System.out.println("tasa: " + tasa);
				pw.println(" tasa: " + tasa+"<br>");

				System.out.println("cuota: " + cuota);
				pw.println(" cuota: " + cuota+"<br>");

				System.out.println("isr: " + isr);
				pw.println(" isr: " + isr+"<br>");

				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				pw.println(" ---------- "+"<br>");


			}

			if(salario > listaDatosISR.get(3) && salario < listaDatosISR.get(6) ) {
				System.out.println("Limite Inferior:" + listaDatosISR.get(3));
				pw.println("Limite Inferior:" + listaDatosISR.get(3)+"<br>");
				resta = salario - listaDatosISR.get(3);
				tasa = resta * listaDatosISR.get(5)/100;
				cuota = tasa + listaDatosISR.get(4);
				if(salario>cantidadMaximaParaSubsidioPorAño) {
					System.out.println("sin subsidio");
					pw.println("sin subsidio "+"<br>");
					isr = cuota - listaSubsidioISR.get(21);
				}else {
					System.out.println("con subsidio ");
					pw.println("con subsidio"+"<br>");
					isr = cuota - listaSubsidioISR.get(21);
				}

				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				pw.println("-----------"+"<br>");

				System.out.println("resta: " + resta);
				pw.println("resta: " + resta+"<br>");

				System.out.println("tasa: " + tasa);
				pw.println("tasa: " + tasa+"<br>");

				System.out.println("cuota: " + cuota);
				pw.println("cuota: " + cuota+"<br>");

				System.out.println("isr: " + isr);
				pw.println("isr: " + isr+"<br>");

				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				pw.println("-----------"+"<br>");

			}

			if(salario > listaDatosISR.get(6)  &&  salario < listaDatosISR.get(9) ) {
				System.out.println("Limite Inferior:" + listaDatosISR.get(6));
				pw.println("Limite Inferior:" + listaDatosISR.get(6)+"<br>");
				resta = salario - listaDatosISR.get(6);
				tasa = resta * listaDatosISR.get(8)/100;
				cuota = tasa + listaDatosISR.get(7);
				if(salario>cantidadMaximaParaSubsidioPorAño) {
					System.out.println("sin subsidio");
					pw.println("sin subsidio"+"<br>");
					isr = cuota - listaSubsidioISR.get(21);
				}else {
					System.out.println("con subsidio");
					pw.println("con subsidio"+"<br>");
					isr = cuota - listaSubsidioISR.get(21);
				}

				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				pw.println("-----------"+"<br>");

				System.out.println("resta: " + resta);
				pw.println("resta: " + resta+"<br>");

				System.out.println("tasa: " + tasa);
				pw.println("tasa: " + tasa+"<br>");

				System.out.println("cuota: " + cuota);
				pw.println("cuota: " + cuota+"<br>");

				System.out.println("isr: " + isr);
				pw.println("isr: " + isr+"<br>");

				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				pw.println("-----------"+"<br>");
			}

			if(salario > listaDatosISR.get(9)  && salario < listaDatosISR.get(12)) {
				System.out.println("Limite Inferior:" + listaDatosISR.get(9));
				pw.println("Limite Inferior:" + listaDatosISR.get(9)+"<br>");
				resta = salario - listaDatosISR.get(9);
				tasa = resta * listaDatosISR.get(11)/100;
				cuota = tasa + listaDatosISR.get(10);
				if(salario>cantidadMaximaParaSubsidioPorAño) {
					System.out.println("sin subsidio");
					pw.println("sin subsidio "+"<br>");
					isr = cuota - listaSubsidioISR.get(21);
				}else {
					System.out.println("con subsidio");
					pw.println("con subsidio "+"<br>");
					isr = cuota - listaSubsidioISR.get(21);
				}

				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				pw.println("-----------"+"<br>");

				System.out.println("resta: " + resta);
				pw.println("resta: " + resta+"<br>");

				System.out.println("tasa: " + tasa);
				pw.println("tasa: " + tasa+"<br>");

				System.out.println("cuota: " + cuota);
				pw.println("cuota: " + cuota+"<br>");

				System.out.println("isr: " + isr);
				pw.println("isr: " + isr+"<br>");

				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				pw.println("------------"+"<br>");
			}

			if(salario > listaDatosISR.get(12)  && salario < listaDatosISR.get(15)) {
				System.out.println("Limite Inferior:" + listaDatosISR.get(12));
				pw.println("Limite Inferior:" + listaDatosISR.get(12));
				resta = salario - listaDatosISR.get(12);
				tasa = resta * listaDatosISR.get(14)/100;
				cuota = tasa + listaDatosISR.get(13);
				if(salario>cantidadMaximaParaSubsidioPorAño) {
					System.out.println("sin subsidio");
					pw.println("sin subsidio"+"<br>");
					isr = cuota - listaSubsidioISR.get(21);
				}else {
					System.out.println("con subsidio");
					pw.println("con subsidio"+"<br>");
					isr = cuota - listaSubsidioISR.get(21);
				}

				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				pw.println("-----------"+"<br>");

				System.out.println("resta: " + resta);
				pw.println("resta: " + resta+"<br>");

				System.out.println("tasa: " + tasa);
				pw.println("tasa: " + tasa+"<br>");

				System.out.println("cuota: " + cuota);
				pw.println("cuota: " + cuota+"<br>");

				System.out.println("isr: " + isr);
				pw.println("isr: " + isr+"<br>");

				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				pw.println("-----------"+"<br>");

			}

			if(salario > listaDatosISR.get(15)  && salario < listaDatosISR.get(18)) {
				System.out.println("Limite Inferior:" + listaDatosISR.get(15));
				pw.println("Limite Inferior:" + listaDatosISR.get(15)+"<br>");
				resta = salario - listaDatosISR.get(15);
				tasa = resta * listaDatosISR.get(17)/100;
				cuota = tasa + listaDatosISR.get(16);
				if(salario>cantidadMaximaParaSubsidioPorAño) {
					System.out.println("sin subsidio");
					pw.println("sin subsidio"+"<br>");
					isr = cuota - listaSubsidioISR.get(21);
				}else {
					System.out.println("con subsidio");
					pw.println("con subsidio"+"<br>");
					isr = cuota - listaSubsidioISR.get(21);
				}

				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				pw.println("-----------"+"<br>");

				System.out.println("resta: " + resta);
				pw.println("resta: " + resta+"<br>");

				System.out.println("tasa: " + tasa);
				pw.println("tasa: " + tasa+"<br>");

				System.out.println("cuota: " + cuota);
				pw.println("cuota: " + cuota+"<br>");

				System.out.println("isr: " + isr);
				pw.println("isr: " + isr+"<br>");

				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				pw.println("-----------"+"<br>");

			}

			if(salario > listaDatosISR.get(18) &&  salario < listaDatosISR.get(21)) {
				System.out.println("Limite Inferior:" + listaDatosISR.get(18));
				pw.println("Limite Inferior:" + listaDatosISR.get(18)+"<br>");
				resta = salario - listaDatosISR.get(18);
				tasa = resta * listaDatosISR.get(20)/100;
				cuota = tasa + listaDatosISR.get(19);
				if(salario>cantidadMaximaParaSubsidioPorAño) {
					System.out.println("sin subsidio");
					pw.println("sin subsidio"+"<br>");
					isr = cuota - listaSubsidioISR.get(21);
				}else {
					System.out.println("con subsidio");
					pw.println("con subsidio"+"<br>");
					isr = cuota - listaSubsidioISR.get(21);
				}

				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				pw.println("-----------"+"<br>");

				System.out.println("resta: " + resta);
				pw.println("resta: " + resta+"<br>");

				System.out.println("tasa: " + tasa);
				pw.println("tasa: " + tasa+"<br>");

				System.out.println("cuota: " + cuota);
				pw.println("cuota: " + cuota+"<br>");

				System.out.println("isr: " + isr);
				pw.println("isr: " + isr+"<br>");

				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				pw.println("-----------"+"<br>");

			}

			if(salario > listaDatosISR.get(21)  && salario < listaDatosISR.get(24)) {
				System.out.println("Limite Inferior:" + listaDatosISR.get(21));
				pw.println("Limite Inferior:" + listaDatosISR.get(21)+"<br>");
				resta = salario - listaDatosISR.get(21);
				tasa = resta * listaDatosISR.get(23)/100;
				cuota = tasa + listaDatosISR.get(122);
				if(salario>cantidadMaximaParaSubsidioPorAño) {
					System.out.println("sin subsidio");
					pw.println("sin subsidio"+"<br>");
					isr = cuota - listaSubsidioISR.get(21);
				}else {
					System.out.println("con subsidio");
					pw.println("con subsidio"+"<br>");
					isr = cuota - listaSubsidioISR.get(21);
				}

				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				pw.println("-----------"+"<br>");

				System.out.println("resta: " + resta);
				pw.println("resta: " + resta+"<br>");

				System.out.println("tasa: " + tasa);
				pw.println("tasa: " + tasa+"<br>");

				System.out.println("cuota: " + cuota);
				pw.println("cuota: " + cuota+"<br>");

				System.out.println("isr: " + isr);
				pw.println("isr: " + isr+"<br>");

				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				pw.println("-----------"+"<br>");

			}

			if(salario > listaDatosISR.get(24)  && salario < listaDatosISR.get(27) ) {
				System.out.println("Limite Inferior:" + listaDatosISR.get(24));
				pw.println("Limite Inferior:" + listaDatosISR.get(24)+"<br>");
				resta = salario - listaDatosISR.get(24);
				tasa = resta * listaDatosISR.get(26)/100;
				cuota = tasa + listaDatosISR.get(25);
				if(salario>cantidadMaximaParaSubsidioPorAño) {
					System.out.println("sin subsidio");
					pw.println("sin subsidio"+"<br>");
					isr = cuota - listaSubsidioISR.get(21);
				}else {
					System.out.println("con subsidio");
					pw.println("con subsidio"+"<br>");
					isr = cuota - listaSubsidioISR.get(21);
				}

				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				pw.println("-----------"+"<br>");

				System.out.println("resta: " + resta);
				pw.println("resta: " + resta+"<br>");

				System.out.println("tasa: " + tasa);
				pw.println("tasa: " + tasa+"<br>");

				System.out.println("cuota: " + cuota);
				pw.println("cuota: " + cuota+"<br>");

				System.out.println("isr: " + isr);
				pw.println("isr: " + isr+"<br>");

				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				pw.println("-----------"+"<br>");

			}

			if(salario > listaDatosISR.get(27)  && salario < listaDatosISR.get(30) ) {
				System.out.println("Limite Inferior:" + listaDatosISR.get(27));
				pw.println("Limite Inferior:" + listaDatosISR.get(27)+"<br>");
				resta = salario - listaDatosISR.get(27);
				tasa = resta * listaDatosISR.get(29)/100;
				cuota = tasa + listaDatosISR.get(28);
				if(salario>cantidadMaximaParaSubsidioPorAño) {
					System.out.println("sin subsidio");
					pw.println("sin subsidio"+"<br>");
					isr = cuota - listaSubsidioISR.get(21);
				}else {
					System.out.println("con subsidio");
					pw.println("con subsidio"+"<br>");
					isr = cuota - listaSubsidioISR.get(21);
				}

				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				pw.println("-----------"+"<br>");

				System.out.println("resta: " + resta);
				pw.println("resta: " + resta+"<br>");

				System.out.println("tasa: " + tasa);
				pw.println("tasa: " + tasa+"<br>");

				System.out.println("cuota: " + cuota);
				pw.println("cuota: " + cuota+"<br>");

				System.out.println("isr: " + isr);
				pw.println("isr: " + isr+"<br>");

				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				pw.println("-----------"+"<br>");
			}

			if(salario > listaDatosISR.get(30) ) {
				System.out.println("Limite Inferior:" + listaDatosISR.get(30));
				pw.println("Limite Inferior:" + listaDatosISR.get(30)+"<br>");
				resta = salario - listaDatosISR.get(30);
				tasa = resta * listaDatosISR.get(32)/100;
				cuota = tasa + listaDatosISR.get(31);
				if(salario>cantidadMaximaParaSubsidioPorAño) {
					System.out.println("sin subsidio");
					pw.println("sin subsidio"+"<br>");
					isr = cuota - listaSubsidioISR.get(21);
				}else {
					System.out.println("con subsidio");
					pw.println("con subsidio"+"<br>");
					isr = cuota - listaSubsidioISR.get(21);
				}

				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				pw.println("-----------"+"<br>");

				System.out.println("resta: " + resta);
				pw.println("resta: " + resta+"<br>");

				System.out.println("tasa: " + tasa);
				pw.println("tasa: " + tasa+"<br>");

				System.out.println("cuota: " + cuota);
				pw.println("cuota: " + cuota+"<br>");

				System.out.println("isr: " + isr);
				pw.println("isr: " + isr+"<br>");

				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				pw.println("-----------"+"<br>");
			}

			pw.println("</p>");
			pw.println("</body>");
			pw.println("</html>");
			pw.flush();
			pw.close();
			bw.close();
			actualizaValorIsrNuevoEmpleado(isr,pw,clave);
		}catch(Exception es) {
			es.printStackTrace();
			LOG.info("Excepción: " + es);
			//JOptionPane.showMessageDialog(null, es, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
		}finally {
			try {
				conSalario.close();
			} catch (SQLException ep) {
				ep.printStackTrace();
				LOG.info("Excepción: " + ep);
				//JOptionPane.showMessageDialog(null, ep, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
			}
		}//FIN DEL FINALLY
		return isr;	
	}//fin del metodo

	public double calcularIMSSNuevoEmpleado(String clave) {
		System.out.println("Inicia el proceso de calcularIMSS");
		double sumaPorcentaje = 0.0; 
		double formula = 0.0; 
		double diasCatorcena = 14;
		double diasSemana = 7;
		double diasJubilados = 30;
		ArrayList<Double> listaPorcentajeIMSS = new ArrayList<Double>();
		listaPorcentajeIMSS = getListaPorcentajeIMSS();
		System.out.println("*********");
		for(int i = 0; i<listaPorcentajeIMSS.size();i++){
			System.out.println("indice: "+ i + " |valor: " + listaPorcentajeIMSS.get(i));
		}
		System.out.println("*********");

		String nombreE = lblEmplNombrePerp.getText();
		String ApPat = lblEmpApPatPerp.getText();
		String ApMat = lblEmpApMatPerp.getText();
		String nombreConcat = nombreE +" "+ApPat+" "+ApMat;

		File attachments = null;
		Date result = new Date();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String convertido = formatter.format(result);
		String carpeta= "C:\\Informacion de Procesos del NYCH";
		String carpetaInterna= "\\IMSS";
		String archivo = "\\IMSS_"+convertido+"_"+nombreConcat+".txt";
		File crearCarpeta = new File(carpeta+carpetaInterna);
		File crearArchivo = new File(carpeta+carpetaInterna+archivo);
		if(crearArchivo.exists()) {
			//			System.out.println("el archivo ya existe,pero se sobreescribira");
			//			JOptionPane.showMessageDialog(null,"El archivo de los cálculos está en la carpeta: "+ crearCarpeta);
		}else {
			System.out.println("No existen pero se creara la ruta y el archivo");
			//JOptionPane.showMessageDialog(null,"El archivo de los cálculos está en la carpeta:"+ crearCarpeta);
			crearCarpeta.mkdirs();
			try {
				if(crearArchivo.createNewFile()) {
					System.out.println("archivo creado");
					JOptionPane.showMessageDialog(null,"El archivo de los cálculos está en la carpeta: " + crearCarpeta);
				}else{
					System.out.println("el archivo no fue creado");
					JOptionPane.showMessageDialog(null,"el archivo no fue creado");
				}
			}catch(Exception io) {
				io.printStackTrace();
			}
		}


		Connection conSDI = null;
		try {

			FileWriter fw;

			fw = new FileWriter(crearArchivo);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);


			pw.print("<!DOCTYPE html>");
			pw.println("<html>");
			pw.println("<head>");
			pw.println("PROCESO DEL CALCULO");
			pw.println("</head>");
			pw.println("<body>");
			pw.println("<p>");
			pw.println("Inicia el proceso de calcular IMSS"+"<br>");
			pw.println("----------"+"<br>");

			sumaPorcentaje = listaPorcentajeIMSS.get(0) + listaPorcentajeIMSS.get(6) + listaPorcentajeIMSS.get(9) + listaPorcentajeIMSS.get(12) + listaPorcentajeIMSS.get(15) + 
					listaPorcentajeIMSS.get(18) + listaPorcentajeIMSS.get(21) + listaPorcentajeIMSS.get(24);
			System.out.println("Suma Porcentaje: " + sumaPorcentaje);
			pw.println("Suma Porcentaje: " + sumaPorcentaje+"<br>");

			PoolNYCH nych = new PoolNYCH();
			Statement stSDI;
			ResultSet resultSetSDI;
			String sqlSalario ="select sdi,nombre from empleados where clave = '"+clave+"'";//'"+lblEmpIdPerp.getText()+"'
			System.out.println("sqlSalario: "+sqlSalario);
			String datos[] = new String[2];
			conSDI = nych.datasource.getConnection();
			stSDI = conSDI.createStatement();
			resultSetSDI = stSDI.executeQuery(sqlSalario);

			while(resultSetSDI.next()){ 
				datos[0] = resultSetSDI.getString(1);
				datos[1] = resultSetSDI.getString(2);
			}
			double sdi =  Double.parseDouble(datos[0]);
			String nombre = datos[1];
			System.out.println("SDI: " + sdi);
			pw.println("SDI: " + sdi+"<br>");

			if(FormularioPrincipal.lblIsNomC.getText().equalsIgnoreCase("OK")) {

				formula = sumaPorcentaje/100 * sdi * diasCatorcena;
				System.out.println("Salario Diario Integrado " + formula +" ,para el empleado: " + nombre +" en nomina: " + FormularioPrincipal.lblCatorcenal.getText() );
				pw.println("Salario Diario Integrado " + formula +" ,para el empleado: " + nombre +" en nomina: " + FormularioPrincipal.lblCatorcenal.getText()+"<br>" );

			}else if (FormularioPrincipal.lblIsNomS.getText().equalsIgnoreCase("OK")) {

				formula = sumaPorcentaje/100 * sdi * diasSemana;
				System.out.println("Salario Diario Integrado " + formula +" ,para el empleado: " + nombre +" en nomina: " + FormularioPrincipal.lblSemanal.getText() );
				pw.println("Salario Diario Integrado " + formula +" ,para el empleado: " + nombre +" en nomina: " + FormularioPrincipal.lblSemanal.getText() +"<br>");

			}else if(FormularioPrincipal.lblIsNomJ.getText().equalsIgnoreCase("OK")) {

				formula = sumaPorcentaje/100 * sdi * diasJubilados;
				System.out.println("Salario Diario Integrado " + formula +" ,para el empleado: " + nombre +" en nomina: " + FormularioPrincipal.lblJubilados.getText() );
				pw.println("Salario Diario Integrado " + formula +" ,para el empleado: " + nombre +" en nomina: " + FormularioPrincipal.lblJubilados.getText() +"<br>");
			}

			pw.println("</p>");
			pw.println("</body>");
			pw.println("</html>");
			pw.flush();
			pw.close();
			bw.close();
			actualizaValorIMSSNuevoEmpleado(formula,clave);
		}catch(Exception ep) {
			ep.printStackTrace();
			StringWriter errors = new StringWriter();
			ep.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: "+ errors );
			JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
		}finally {
			try {
				conSDI.close();
			} catch (SQLException ep) {
				ep.printStackTrace();
				StringWriter errors = new StringWriter();
				ep.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: "+ errors );
				//JOptionPane.showMessageDialog(null, ep, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
			}
		}
		return formula;
	}//fin del metodo


	public void actualizaValorIsrNuevoEmpleado(double isr,PrintWriter pw, String clave) {
		System.out.println("Actualizando calculo nomina isr nuevo empleado:" + clave);
		Connection conUpdateISR = null;
		PoolNYCH nych = new PoolNYCH();
		try {

			String sqlUpdateIsr ="UPDATE CALCULO_NOMINA SET parcialidades=0 where id_empleado='"+clave+"' and id_deduccion=2";
			//			String sqlUpdateIsr ="UPDATE CALCULO_NOMINA SET VALOR_DEDUCCION='"+ isr +"' where id_empleado='"+clave+"' and id_deduccion=2 and parcialidades=0";
			System.out.println("sqlUPDATEISR: " + sqlUpdateIsr);
			pw.write(sqlUpdateIsr);
			conUpdateISR = nych.datasource.getConnection();
			PreparedStatement ppsUpdateISR = conUpdateISR.prepareStatement(sqlUpdateIsr);
			ppsUpdateISR.executeUpdate();
			System.out.println("Datos Actualizados del ISR");
			pw.write("Datos Actualizados del ISR");
			System.out.println("----------");
			pw.write("----------");
		}catch(Exception ees) {
			ees.printStackTrace();
			StringWriter errors = new StringWriter();
			ees.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: "+ errors );
			//JOptionPane.showMessageDialog(null, ees, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
		}finally {
			try {
				conUpdateISR.close();
			} catch (SQLException ep) {
				ep.printStackTrace();
				StringWriter errors = new StringWriter();
				ep.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: "+ errors );
				//JOptionPane.showMessageDialog(null, ep, "Error de desc onexion", JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
			}
		}//FIN DEL FINALLY	
	}

	public void actualizaValorIMSSNuevoEmpleado(double imss,String clave) {
		System.out.println("Actualizando calculo nomina IMSS nuevo empleado: " + clave);
		Connection conUpdateIMSS = null;
		PoolNYCH nych = new PoolNYCH();
		try {

			String sqlUpdateIMSS ="UPDATE CALCULO_NOMINA SET parcialidades=0 where id_empleado='"+clave+"' and id_deduccion=1";
			System.out.println("sqlUpdateIMSS: " + sqlUpdateIMSS);
			conUpdateIMSS = nych.datasource.getConnection();
			PreparedStatement ppsUpdateISR = conUpdateIMSS.prepareStatement(sqlUpdateIMSS);
			ppsUpdateISR.executeUpdate();
			System.out.println("Datos Actualizados");
			System.out.println("----------");
		}catch(Exception ees) {
			ees.printStackTrace();
			StringWriter errors = new StringWriter();
			ees.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: "+ errors );
			//JOptionPane.showMessageDialog(null, ees, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
		}finally {
			try {
				conUpdateIMSS.close();
			} catch (SQLException ep) {
				ep.printStackTrace();
				StringWriter errors = new StringWriter();
				ep.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: "+ errors );
				//JOptionPane.showMessageDialog(null, ep, "Error de desc onexion", JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
			}
		}//FIN DEL FINALLY	
	}


	@SuppressWarnings("static-access")
	public int insertCalculoNominaInfonavit(double infonavit) {
		System.out.println("insertHE");
		int resultado = 0;
		int claveInternaDeduccion = 10;
		int clavePercepcionSistema = 40;
		double valorClaveInternaPercepcion = 0.0;
		PoolNYCH nych = new PoolNYCH();
		Connection conHE = null;
		String dependencia =  lblIdUREmpMov.getText();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date diaHoy = Calendar.getInstance().getTime();
		String sqlInsert = "";

		String id = lblEmpIdPerp.getText();
		String idPuesto = lblIdPuestoEmpMov.getText();
		sqlInsert = "INSERT INTO dbo.CALCULO_NOMINA (ID_EMPLEADO,ID_PERCEPCION,ID_DEDUCCION,VALOR_PERCEPCION,VALOR_DEDUCCION,ID_PUESTO,FECHA_CALCULO,ID_TIPO_NOMINA,ID_DEPENDENCIA,ESTATUS,PERIODO,PARCIALIDADES)"
				+ "" + "VALUES (" + id + "," + clavePercepcionSistema + "," + claveInternaDeduccion + "," + valorClaveInternaPercepcion + ","
				+ "" + infonavit + "," + idPuesto + ",'" +formatter.format(diaHoy) + "'," + lblTipoNominaOculta.getText() + ","+dependencia + ",'"+1+"','"+FormularioPrincipal.lblPeriodoNumero.getText()+"','"+0+"')";
		System.out.println("sql insert infonavit: "+sqlInsert);
//		sqlInsert="";

		try {
			conHE = nych.datasource.getConnection();
			PreparedStatement pps = conHE.prepareStatement(sqlInsert);
			pps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Datos Guardados");
		} catch (SQLException el) {
			el.printStackTrace();
			StringWriter errors = new StringWriter();
			el.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: " + errors);
			JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");
		} finally {
			try {
				conHE.close();
			} catch (SQLException ep) {
				ep.printStackTrace();
				StringWriter errors = new StringWriter();
				ep.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: " + errors);
				JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");
			}
		}
		return resultado;

	}
}

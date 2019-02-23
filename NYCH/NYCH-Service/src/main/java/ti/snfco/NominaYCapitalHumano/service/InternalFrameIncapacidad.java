package ti.snfco.NominaYCapitalHumano.service;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.border.MatteBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

import org.slf4j.LoggerFactory;

import com.toedter.calendar.JDateChooser;

public class InternalFrameIncapacidad extends JInternalFrame {
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(InternalFrameIncapacidad.class);
	private static final long serialVersionUID = 1L;
	JLabel lblFondo = new JLabel("");
	private JTextField textFieldBuscarEmpleado;
	TableRowSorter rowSorter;
	int IdBusquedaEmple = 1;
	JTable tableIncapacidad = new JTable();
	JTable tableCantidadAcumulada = new JTable();
	JTable tableAcum = new JTable();
	public FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo de imagen", "jpg");
	public String atributo = "";
	//JComboBox comboBoxTipoNominaEmpleados = new JComboBox();
	JComboBox comboBoxTipoIncapacidad = new JComboBox();
	JComboBox comboBoxPeriodo = new JComboBox();
	InternalFrameEmpleado empleado = new InternalFrameEmpleado();
	private JTextField textFieldFOLIO;
	private JTextField textFieldDIAS;
	private JTextField textFieldSDI;
	JPanel panelDatosIncapacidad = new JPanel();
	JPanel panelCantidadesAcumuladas = new JPanel();
	public ButtonGroup groupButton = new ButtonGroup();
	private JTextField textFieldCantidad;
	private JTextField textFieldPorcentaje;
	private JTextField textFieldProrratear;
	private JTextField textFieldAcumulado;
	private JTextField textFieldCantPro;
	JDateChooser dateChooserFechaInicial = new JDateChooser();
	JDateChooser dateChooserFechaFinal = new JDateChooser();
	JDateChooser dateChooserFechaDe = new JDateChooser();
	JDateChooser dateChooserFechaHasta = new JDateChooser();
	JRadioButton rdbtnInicial = new JRadioButton("INICIAL");
	JRadioButton rdbtnSubSecuente = new JRadioButton("SUBSECUENTE");
	JPanel panelAcum = new JPanel();
	JLabel lblCantidadesAcumuladasDe = new JLabel("Acumulado al ultimo periodo");
	private JTextField textFieldPorcentajeRT;
	JLabel lblTipoNominaOculto = new JLabel("New label");
	JButton btnVerEmpleados = new JButton("Ver Empleados");


	/**
	 * Create the frame.
	 */
	public InternalFrameIncapacidad() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
//		int ancho = (int) dim.getWidth()-150;
//		int alto = (int) dim.getHeight()-220;
		//setBounds(FormularioPrincipal.desktopPane.getX(), FormularioPrincipal.desktopPane.getY(), ancho, alto);
		//		System.out.println("bounds empleado: " + getBounds());

		setToolTipText("Incapacidades");
		setVisible(true);
		setTitle("Incapacidades");
		getContentPane().setLayout(null);

		JPanel panelIncapacidad = new JPanel();
		panelIncapacidad.setBackground(SystemColor.controlHighlight);
		panelIncapacidad.setBounds(0, 0, 1498, 699);

		setBounds(0, 0, 1501, 701);
		getContentPane().add(panelIncapacidad);
		panelIncapacidad.setLayout(null);

		JLabel lblicon = new JLabel();
		lblicon.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("icons8_Search_32px_2.png"))));
		lblicon.setBounds(24, 63, 46, 28);
		panelIncapacidad.add(lblicon);

		JLabel label_1 = new JLabel("Seleccione el Empleado(a):");
		label_1.setBounds(24, 31, 228, 21);
		panelIncapacidad.add(label_1);

		textFieldBuscarEmpleado = new JTextField();
		textFieldBuscarEmpleado.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				rowSorter.setRowFilter(RowFilter.regexFilter(textFieldBuscarEmpleado.getText().toUpperCase(), IdBusquedaEmple));
			}
		});
		textFieldBuscarEmpleado.setColumns(10);
		textFieldBuscarEmpleado.setBorder(null);
		textFieldBuscarEmpleado.setBackground(SystemColor.controlHighlight);
		textFieldBuscarEmpleado.setBounds(72, 63, 292, 28);
		panelIncapacidad.add(textFieldBuscarEmpleado);

		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(176, 196, 222));
		separator.setBackground(new Color(176, 196, 222));
		separator.setBounds(24, 92, 360, 2);
		panelIncapacidad.add(separator);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		scrollPane.setBounds(24, 139, 897, 285);
		scrollPane.setViewportView(tableIncapacidad);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
//		scrollPane.setVisible(false);
		panelIncapacidad.add(scrollPane);

		panelDatosIncapacidad.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		panelDatosIncapacidad.setBounds(934, 31, 532, 525);
		panelIncapacidad.add(panelDatosIncapacidad);
		panelDatosIncapacidad.setLayout(null);

		JLabel lblNewLabel = new JLabel("Folio de Incapacidad");
		lblNewLabel.setBounds(12, 11, 121, 14);
		panelDatosIncapacidad.add(lblNewLabel);

		textFieldFOLIO = new JTextField();
		textFieldFOLIO.setBounds(10, 31, 215, 30);
		panelDatosIncapacidad.add(textFieldFOLIO);
		textFieldFOLIO.setColumns(10);

		groupButton.add(rdbtnInicial);
		rdbtnInicial.setBounds(10, 68, 77, 23);
		panelDatosIncapacidad.add(rdbtnInicial);

		groupButton.add(rdbtnSubSecuente);
		rdbtnSubSecuente.setBounds(101, 68, 124, 23);
		panelDatosIncapacidad.add(rdbtnSubSecuente);

		comboBoxTipoIncapacidad.setBounds(10, 98, 215, 30);
		panelDatosIncapacidad.add(comboBoxTipoIncapacidad);

		JLabel lblDas = new JLabel("Días");
		lblDas.setBounds(12, 140, 49, 14);
		panelDatosIncapacidad.add(lblDas);

		textFieldDIAS = new JTextField();
		textFieldDIAS.setColumns(10);
		textFieldDIAS.setBounds(10, 160, 215, 30);
		panelDatosIncapacidad.add(textFieldDIAS);

		JLabel lblFechaInicial = new JLabel("Fecha Inicial");
		lblFechaInicial.setBounds(12, 201, 82, 14);
		panelDatosIncapacidad.add(lblFechaInicial);

		JLabel lblFechaFinal = new JLabel("Fecha Final");
		lblFechaFinal.setBounds(249, 201, 82, 14);
		panelDatosIncapacidad.add(lblFechaFinal);


		dateChooserFechaInicial.setBounds(10, 226, 215, 30);
		panelDatosIncapacidad.add(dateChooserFechaInicial);

		dateChooserFechaFinal.setBounds(249, 226, 215, 30);
		panelDatosIncapacidad.add(dateChooserFechaFinal);

		JLabel lblSalarioDiarioIntegrado = new JLabel("Salario diario integrado");
		lblSalarioDiarioIntegrado.setBounds(12, 267, 132, 14);
		panelDatosIncapacidad.add(lblSalarioDiarioIntegrado);

		textFieldSDI = new JTextField();
		textFieldSDI.setEnabled(false);
		textFieldSDI.setColumns(10);
		textFieldSDI.setBounds(10, 292, 215, 30);
		panelDatosIncapacidad.add(textFieldSDI);

		//		JButton btnNewButton_2 = new JButton("Calcular");
		//		btnNewButton_2.addActionListener(new ActionListener() {
		//			public void actionPerformed(ActionEvent e) {
		//				int valor = tableIncapacidad.getSelectedRow();
		//					String cantidad = tableIncapacidad.getValueAt(valor, 9).toString();
		//					double ca = Double.parseDouble(cantidad);
		//					System.out.println("cantidad: "+ cantidad );
		//					double cant = Double.parseDouble(textFieldDIAS.getText());
		//					System.out.println("cant: "+ cant );
		//					double porcentajeEnfermedad = (Double.parseDouble(textFieldPorcentaje.getText()));
		//					System.out.println("porcentaje: "+ porcentajeEnfermedad );
		//					double cantiIncap = ca*cant*porcentajeEnfermedad;
		//					System.out.println("cantidad de la incapacidad: "+ cantiIncap );
		//					textFieldCantidad.setText(String.valueOf(cantiIncap));
		//					
		//			}
		//		});
		//		btnNewButton_2.setBounds(290, 351, 89, 30);
		//		panelDatosIncapacidad.add(btnNewButton_2);

		JLabel lblCantidad = new JLabel("Cantidad total de la incapacidad");
		lblCantidad.setBounds(259, 274, 215, 14);
		panelDatosIncapacidad.add(lblCantidad);

		textFieldCantidad = new JTextField();
		textFieldCantidad.setEnabled(false);
		textFieldCantidad.setForeground(new Color(34, 139, 34));
		textFieldCantidad.setBounds(259, 292, 215, 30);
		panelDatosIncapacidad.add(textFieldCantidad);
		textFieldCantidad.setColumns(10);

		textFieldPorcentaje = new JTextField();
		textFieldPorcentaje.setText("60");
		textFieldPorcentaje.setBounds(435, 8, 58, 30);
		panelDatosIncapacidad.add(textFieldPorcentaje);
		textFieldPorcentaje.setColumns(10);

		JLabel lblPorcentajePara = new JLabel("Porcentaje para Enfermedad");
		lblPorcentajePara.setBounds(249, 16, 178, 14);
		panelDatosIncapacidad.add(lblPorcentajePara);

		JButton btnCalc = new JButton("Calcular");
		btnCalc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int fila = tableIncapacidad.getSelectedRow();
				if(fila>=0) {

					int confirmado = JOptionPane.showConfirmDialog(null,"¿Desea calcular una incapacidad?");
					if (JOptionPane.OK_OPTION == confirmado) {
						DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
						simbolos.setDecimalSeparator('.');
						DecimalFormat df = new DecimalFormat("####.##",simbolos);

						String cantidad = tableIncapacidad.getValueAt(fila, 8).toString();
						double ca = Double.parseDouble(cantidad);
						System.out.println("cantidad: "+ cantidad );

						double cant = Double.parseDouble(textFieldDIAS.getText());
						System.out.println("cant: "+ cant );

						double porcentajeEnfermedad = Double.parseDouble(textFieldPorcentaje.getText())/100;
						System.out.println("porcentajeEnfermedad: "+ porcentajeEnfermedad );

						double porcentajeRiesgoTotal =  Double.parseDouble(textFieldPorcentajeRT.getText())/100.00;
						System.out.println("porcentajeRiesgoTotal: "+ porcentajeRiesgoTotal);

						if(comboBoxTipoIncapacidad.getSelectedIndex()==1){
							double cantiIncap = ca*cant*porcentajeRiesgoTotal;
							System.out.println("cantidad de la incapacidad RT: "+ cantiIncap );
							textFieldCantidad.setText(String.valueOf(cantiIncap));
						}else {
							double cantiIncap = ca*cant*porcentajeEnfermedad;
							System.out.println("cantidad de la incapacidad ENF: "+ cantiIncap );
							textFieldCantidad.setText(String.valueOf(cantiIncap));

						}


						String valorsdi = tableIncapacidad.getValueAt(fila, 8).toString();
						textFieldCantPro.setText(valorsdi);

						double prrorr = Double.parseDouble(valorsdi);
						System.out.println("prrorra: "+ prrorr);

						String diasPro = textFieldProrratear.getText();
						System.out.println("diasPro: "+ diasPro);
						double x = Double.parseDouble(diasPro);

						double prorrateo = prrorr * x;
						System.out.println("prorrateo: "+ prorrateo);


						textFieldAcumulado.setText(String.valueOf(prorrateo));

						insertIncapacidad();
						insertIncapacidadEnCalculoNomina();

						textFieldFOLIO.setText(null);
						textFieldDIAS.setText(null);
						dateChooserFechaInicial.setDate(null);
						dateChooserFechaFinal.setDate(null);
						//						textFieldSDI.setText(null);
						textFieldCantidad.setText(null);
						textFieldAcumulado.setText(null);
						//						textFieldCantPro.setText(null);
						textFieldProrratear.setText(null);
						dateChooserFechaDe.setDate(null);
						dateChooserFechaHasta.setDate(null);

					}else if(JOptionPane.NO_OPTION == confirmado){
						panelCantidadesAcumuladas.setVisible(true);

					}else {
						JOptionPane.showMessageDialog(null, "No se ha guardo información","Advertencia", JOptionPane.WARNING_MESSAGE );
					}
				}
			}
		});
		btnCalc.setBounds(198, 479, 89, 30);
		panelDatosIncapacidad.add(btnCalc);

		JLabel lblAcumulado = new JLabel("Acumulado");
		lblAcumulado.setBounds(12, 333, 77, 14);
		panelDatosIncapacidad.add(lblAcumulado);

		textFieldAcumulado = new JTextField();
		textFieldAcumulado.setBounds(10, 351, 215, 30);
		panelDatosIncapacidad.add(textFieldAcumulado);
		textFieldAcumulado.setEnabled(false);
		textFieldAcumulado.setForeground(new Color(34, 139, 34));
		textFieldAcumulado.setColumns(10);

		JLabel lblCantidadProrrateada = new JLabel("Cantidad prorrateada");
		lblCantidadProrrateada.setBounds(10, 392, 121, 14);
		panelDatosIncapacidad.add(lblCantidadProrrateada);

		JLabel label_2 = new JLabel("Salario diario integrado");
		label_2.setBounds(12, 415, 132, 14);
		panelDatosIncapacidad.add(label_2);

		textFieldCantPro = new JTextField();
		textFieldCantPro.setBounds(10, 428, 132, 30);
		panelDatosIncapacidad.add(textFieldCantPro);
		textFieldCantPro.setEnabled(false);
		textFieldCantPro.setForeground(Color.BLACK);
		textFieldCantPro.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("x");
		lblNewLabel_1.setBounds(161, 436, 17, 14);
		panelDatosIncapacidad.add(lblNewLabel_1);

		JLabel label = new JLabel("Días");
		label.setBounds(190, 415, 37, 14);
		panelDatosIncapacidad.add(label);

		textFieldProrratear = new JTextField();
		textFieldProrratear.setBounds(188, 428, 37, 30);
		panelDatosIncapacidad.add(textFieldProrratear);
		textFieldProrratear.setForeground(Color.BLACK);
		textFieldProrratear.setColumns(10);

		//		JLabel lblPeriodo = new JLabel("Periodo");
		//		lblPeriodo.setBounds(249, 415, 121, 14);
		//		panelDatosIncapacidad.add(lblPeriodo);
		//		comboBoxPeriodo.setBounds(249, 428, 215, 30);
		//		panelDatosIncapacidad.add(comboBoxPeriodo);

		JLabel lblPorcentajeParaRiesgo = new JLabel("Porcentaje para Riesgo de Trabajo");
		lblPorcentajeParaRiesgo.setBounds(235, 57, 199, 14);
		panelDatosIncapacidad.add(lblPorcentajeParaRiesgo);

		textFieldPorcentajeRT = new JTextField();
		textFieldPorcentajeRT.setText("100");
		textFieldPorcentajeRT.setColumns(10);
		textFieldPorcentajeRT.setBounds(435, 49, 58, 30);
		panelDatosIncapacidad.add(textFieldPorcentajeRT);

		JLabel label_3 = new JLabel("%");
		label_3.setBounds(500, 16, 22, 14);
		panelDatosIncapacidad.add(label_3);

		JLabel label_4 = new JLabel("%");
		label_4.setBounds(500, 57, 22, 14);
		panelDatosIncapacidad.add(label_4);

		JButton btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int fila = tableIncapacidad.getSelectedRow();
				if(fila>=0) {


					//					if(FormularioPrincipal.lblIsNomC.getText().equalsIgnoreCase("OK")) {
					//						
					//						ArrayList<String> listaFechas = new ArrayList<String>();
					//						listaFechas = buscarFechas();
					//						comboBoxPeriodo.addItem("Seleccione Uno");
					//						for (int i = 0; i < listaFechas.size(); i++) {
					//							comboBoxPeriodo.addItem(listaFechas.get(i));
					//						}
					//					}
					//					
					//					 if(FormularioPrincipal.lblIsNomS.getText().equalsIgnoreCase("OK")) {
					//						
					//						ArrayList<String> listaFechasSemanal = new ArrayList<String>();
					//						listaFechasSemanal = buscarFechasSemanal();
					//						comboBoxPeriodo.addItem("Seleccione Uno");
					//						for (int i = 0; i < listaFechasSemanal.size(); i++) {
					//							comboBoxPeriodo.addItem(listaFechasSemanal.get(i));
					//						}
					//					}

					ArrayList<String> listaTipoIncapacidad = new ArrayList<String>();
					listaTipoIncapacidad = buscarTipoIncapacidad();
					comboBoxTipoIncapacidad.addItem("Seleccione Una");
					for (int i = 0; i < listaTipoIncapacidad.size(); i++) {
						comboBoxTipoIncapacidad.addItem(listaTipoIncapacidad.get(i));
					}


					DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
					simbolos.setDecimalSeparator('.');
					DecimalFormat df = new DecimalFormat("####.##",simbolos);

					panelDatosIncapacidad.setVisible(true);
					String valorSDI = tableIncapacidad.getValueAt(fila, 8).toString();
					textFieldSDI.setText(valorSDI);
					String valorsdi = tableIncapacidad.getValueAt(fila, 8).toString();
					textFieldCantPro.setText(valorsdi);


				}else{
					JOptionPane.showMessageDialog(null, "Seleccione un registro");
				}
			}
		});
		btnSeleccionar.setBounds(10, 435, 152, 30);
		panelIncapacidad.add(btnSeleccionar);


		panelCantidadesAcumuladas.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		panelCantidadesAcumuladas.setBounds(10, 476, 747, 171);
		panelIncapacidad.add(panelCantidadesAcumuladas);
		panelCantidadesAcumuladas.setLayout(null);

		JScrollPane scrollPaneCantidadesAcumuladas = new JScrollPane();
		scrollPaneCantidadesAcumuladas.setViewportBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		scrollPaneCantidadesAcumuladas.setBounds(10, 66, 727, 97);
		scrollPaneCantidadesAcumuladas.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneCantidadesAcumuladas.setViewportView(tableCantidadAcumulada);
		panelCantidadesAcumuladas.add(scrollPaneCantidadesAcumuladas);
		dateChooserFechaDe.setBounds(10, 25, 168, 30);
		panelCantidadesAcumuladas.add(dateChooserFechaDe);

		JLabel lblFechaDe = new JLabel("Fecha De");
		lblFechaDe.setBounds(10, 11, 77, 14);
		panelCantidadesAcumuladas.add(lblFechaDe);

		JLabel lblFechaHasta = new JLabel("Fecha Hasta");
		lblFechaHasta.setBounds(202, 11, 77, 14);
		panelCantidadesAcumuladas.add(lblFechaHasta);
		dateChooserFechaHasta.setBounds(202, 25, 168, 30);
		panelCantidadesAcumuladas.add(dateChooserFechaHasta);

		JButton btnNewButton_2 = new JButton("Calcular");
		btnNewButton_2.setBounds(380, 25, 97, 30);
		panelCantidadesAcumuladas.add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblCantidadesAcumuladasDe.setVisible(true);
				panelAcum.setVisible(true);
				mostrarCantidadAcumulada();
			}
		});
		lblCantidadesAcumuladasDe.setBounds(761, 460, 172, 14);
		panelIncapacidad.add(lblCantidadesAcumuladasDe);
		panelAcum.setBounds(761, 476, 172, 171);
		panelIncapacidad.add(panelAcum);

		panelAcum.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		panelAcum.setLayout(null);


		JScrollPane scrollPaneAcum = new JScrollPane();
		scrollPaneAcum.setBounds(10, 11, 152, 149);
		panelAcum.add(scrollPaneAcum);
		scrollPaneAcum.setViewportBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(176, 196, 222)));
		scrollPaneAcum.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneAcum.setViewportView(tableAcum);

		lblTipoNominaOculto.setBounds(387, 0, 435, 30);
		lblTipoNominaOculto.setVisible(false);
		panelIncapacidad.add(lblTipoNominaOculto);

		btnVerEmpleados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				scrollPane.setVisible(true);
				mostrarDatosEmpleadoIncapacidad(lblTipoNominaOculto.getText());
				btnVerEmpleados.setEnabled(false);
			}
		});
		btnVerEmpleados.setBounds(387, 58, 149, 30);
		panelIncapacidad.add(btnVerEmpleados);
		
//		JButton btnSalir = new JButton("Salir");
//		btnSalir.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				scrollPane.setVisible(false);
//				dispose();
//			}
//		});
//		btnSalir.setBounds(542, 58, 100, 30);
//		panelIncapacidad.add(btnSalir);
		
				lblFondo.setBounds(0,0,1488,830);
				lblFondo.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("walll.png"))));
				panelIncapacidad.add(lblFondo);

	}

	public void limpiarTabla() {
		DefaultTableModel table = (DefaultTableModel) tableIncapacidad.getModel();
		table.setRowCount(0);
		table.setColumnCount(0);
	}

	public int insertIncapacidadEnCalculoNomina(){

		int resultado = 0;
		int claveInternaPercepcion=40;
		int claveInternaDeduccion=22;
		int claveIncapacidad=6;
		double valorClaveInternaPercepcion=0.0;
		double valorClaveInternaDeduccion=0.0;
		PoolNYCH nych = new PoolNYCH();
		Connection conIncapacidad =null;
		int fila = tableIncapacidad.getSelectedRow();
		String dependencia =  tableIncapacidad.getValueAt(fila, 10).toString();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date diaHoy = Calendar.getInstance().getTime();
		String sqlInsert="";

		//		String text = textFieldTotPagar.getText();
		//		double DouVal = 0.0;
		//		try {
		//			DouVal = Double.valueOf(text).doubleValue();
		//			System.out.println("Double Valor: "+DouVal);
		//		} catch (NumberFormatException e) {
		//			//Log it if needed
		//			// intVal = //default fallback value;
		//		}


		DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
		simbolos.setDecimalSeparator('.');
		DecimalFormat formateador = new DecimalFormat("####.##",simbolos);

		boolean selectedInicial = rdbtnInicial.isSelected();
		boolean selectedSubSecuente = rdbtnSubSecuente.isSelected();
		String atributoPeriodo ="";
		if(selectedInicial) {
			atributoPeriodo = "INICIAL";
			//String atributoSegundoPeriodo = "SEGUNDO PERIODO";
		}

		if(selectedSubSecuente) {
			//String atributoPrimerPeriodo = "PRIMER PERIODO";
			atributoPeriodo = "SUBSECUENTE";
		}
		Date fechaInicial = dateChooserFechaInicial.getDate();
		Date fechaFinal = dateChooserFechaFinal.getDate();
		SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");



		String texto =textFieldAcumulado.getText();
		double valor = 0;
		try
		{
			// parse() lanza una ParseException en caso de fallo que hay que capturar.
			Number numero = formateador.parse(texto);
			valor = numero.doubleValue();
			System.out.println("Valor: "+valor);
		}
		catch (ParseException e)
		{
			e.printStackTrace();
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: "+ errors );
			//JOptionPane.showMessageDialog(null, el, "Error de conexion", JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null,"Error. El usuario ha escrito algo que no se puede convertir a número");
		}

		if(fila>=0) {
			String id= tableIncapacidad.getValueAt(fila, 0).toString();
			String idPuesto= tableIncapacidad.getValueAt(fila, 5).toString();
			String TipoNomina= tableIncapacidad.getValueAt(fila, 9).toString();
			sqlInsert="INSERT INTO dbo.CALCULO_NOMINA (ID_EMPLEADO,ID_PERCEPCION,ID_DEDUCCION,VALOR_PERCEPCION,VALOR_DEDUCCION,ID_PUESTO,FECHA_CALCULO,ID_DEPENDENCIA,ID_TIPO_NOMINA,ESTATUS,PERIODO)" 
					+ ""
					+ "VALUES ("+ id +","+ claveInternaPercepcion +","+ claveIncapacidad +","+  valorClaveInternaPercepcion +","
					+ ""+ valor +",'"+ idPuesto +"','"+formatter.format(diaHoy)+"','" + dependencia +"',"
					+ ""+ TipoNomina +",'"+1+"','"+FormularioPrincipal.lblPeriodoNumero.getText()+"')";
			System.out.println("INSERT INCAPACIDAD CALCULO NOMINA: "+sqlInsert);
		}

		try {
			conIncapacidad = nych.datasource.getConnection();
			PreparedStatement pps = conIncapacidad.prepareStatement(sqlInsert);
			pps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Datos Guardados");
		} catch (SQLException el) { 
			el.printStackTrace();
			StringWriter errors = new StringWriter();
			el.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: "+ errors );
			//JOptionPane.showMessageDialog(null, el, "Error de conexion", JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
		}finally {
			try {
				conIncapacidad.close();
			} catch (SQLException ep) {
				ep.printStackTrace();
				StringWriter errors = new StringWriter();
				ep.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: "+ errors );
				//JOptionPane.showMessageDialog(null, ep, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
			}
		}
		return resultado;

	}

	public int insertIncapacidad(){

		int resultado = 0;
		int claveInternaPercepcion=40;
		int claveInternaDeduccion=22;
		int claveIncapacidad=6;
		double valorClaveInternaPercepcion=0.0;
		double valorClaveInternaDeduccion=0.0;
		PoolNYCH nych = new PoolNYCH();
		Connection conIncapacidad =null;
		int fila = tableIncapacidad.getSelectedRow();
		String sqlInsert="";

		//		String text = textFieldTotPagar.getText();
		//		double DouVal = 0.0;
		//		try {
		//			DouVal = Double.valueOf(text).doubleValue();
		//			System.out.println("Double Valor: "+DouVal);
		//		} catch (NumberFormatException e) {
		//			//Log it if needed
		//			// intVal = //default fallback value;
		//		}


		//		DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
		//		simbolos.setDecimalSeparator('.');
		//		DecimalFormat formateador = new DecimalFormat("####.##",simbolos);

		boolean selectedInicial = rdbtnInicial.isSelected();
		boolean selectedSubSecuente = rdbtnSubSecuente.isSelected();
		String atributoincapacidad ="";
		if(selectedInicial) {
			atributoincapacidad = "INICIAL";
			//String atributoSegundoPeriodo = "SEGUNDO PERIODO";
		}

		if(selectedSubSecuente) {
			//String atributoPrimerPeriodo = "PRIMER PERIODO";
			atributoincapacidad = "SUBSECUENTE";
		}
		Date fechaInicial = dateChooserFechaInicial.getDate();
		Date fechaFinal = dateChooserFechaFinal.getDate();
		SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");



		//		 String texto ="";
		//		 double valor = 0;
		//		 try
		//		 {
		//		    // parse() lanza una ParseException en caso de fallo que hay que capturar.
		//		    Number numero = formateador.parse(texto);
		//		    valor = numero.doubleValue();
		//		    System.out.println("Valor: "+valor);
		//		 }
		//		 catch (ParseException e)
		//		 {
		//			 	e.printStackTrace();
		//				StringWriter errors = new StringWriter();
		//				e.printStackTrace(new PrintWriter(errors));
		//				LOG.info("Excepcion: "+ errors );
		//				//JOptionPane.showMessageDialog(null, el, "Error de conexion", JOptionPane.ERROR_MESSAGE);
		//				JOptionPane.showMessageDialog(null,"Error. El usuario ha escrito algo que no se puede convertir a número");
		//		 }

		if(fila>=0) {
			String id= tableIncapacidad.getValueAt(fila, 0).toString();
			String idPuesto= tableIncapacidad.getValueAt(fila, 5).toString();
			//			sqlInsert="INSERT INTO dbo.CALCULO_NOMINA (ID_EMPLEADO,ID_PERCEPCION,ID_DEDUCCION,VALOR_PERCEPCION,VALOR_DEDUCCION,ID_PUESTO,FECHA_CALCULO,DIAS,ID_TIPO_NOMINA)" 
			//					+ ""
			//					+ "VALUES ("+ id +","+ clavePercepcionAguinaldo +","+ claveInternaDeduccion +","+  valor +","
			//					+ ""+ valorClaveInternaDeduccion +","+ idPuesto +",'"+Calendar.getInstance().getTime()+"','" + 14 +"',"
			//					+ ""+TipoNomina+")";
			sqlInsert="INSERT INTO dbo.INCAPACIDADES (ID_TIPO_INCAPACIDAD,ID_EMPLEADO,FOLIO_INCAPACIDAD,PORCENTAJE_ENFERMEDAD,CLASIFICACION_INCAPACIDAD,DIAS,FECHA_INICIAL,FECHA_FINAL,ID_PUESTO,CANTIDAD_TOTAL,"
					+ "CANTIDAD_PRORRATEADA,ID_PERIODO,CANTIDAD_ACUMULADA)" 
					+ ""
					+ "VALUES ("+ comboBoxTipoIncapacidad.getSelectedIndex() +",'"+ id +"','"+ textFieldFOLIO.getText() +"',"+ textFieldPorcentaje.getText() +",'"+  atributoincapacidad +"',"
					+ ""+ textFieldDIAS.getText() +","+ "convert(datetime,'"+ formatoDeFecha.format(fechaInicial)+"',101)" +","+ "convert(datetime,'"+ formatoDeFecha.format(fechaFinal)+"',101)" +",'" + idPuesto +"',"
					+ ""+ textFieldCantidad.getText() +","+ textFieldCantPro.getText() +","+ comboBoxPeriodo.getSelectedIndex() +","+ textFieldAcumulado.getText() +")";
			System.out.println("INSERT INCAPACIDAD: "+sqlInsert);
		}

		try {
			conIncapacidad = nych.datasource.getConnection();
			PreparedStatement pps = conIncapacidad.prepareStatement(sqlInsert);
			pps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Datos Guardados");
		} catch (SQLException el) { 
			el.printStackTrace();
			StringWriter errors = new StringWriter();
			el.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: "+ errors );
			//JOptionPane.showMessageDialog(null, el, "Error de conexion", JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
		}finally {
			try {
				conIncapacidad.close();
			} catch (SQLException ep) {
				ep.printStackTrace();
				StringWriter errors = new StringWriter();
				ep.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: "+ errors );
				//JOptionPane.showMessageDialog(null, ep, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
			}
		}
		return resultado;

	}

	@SuppressWarnings({ "unlikely-arg-type", "resource" })
	public void mostrarDatosEmpleadoIncapacidad(String valor) {
		DefaultTableModel modelo = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				if (column == 11) {
					return true;
				} else {
					return false;
				}
			}
		};
		;
		modelo.addColumn("CLAVE");
		modelo.addColumn("NOMBRE");
		modelo.addColumn("APELLIDO PATERNO");
		modelo.addColumn("APELLIDO MATERNO");
		modelo.addColumn("PUESTO");
		modelo.addColumn("ID");
		modelo.addColumn("SALARIO");
		modelo.addColumn("UNIDAD RESPONSABLE");
		modelo.addColumn("SDI");
		modelo.addColumn("TIPO NOMINA");
		modelo.addColumn("ID DEPENDENCIA");

		tableIncapacidad.setModel(modelo);
		tableIncapacidad.setBackground(Color.WHITE);

		JTableHeader th = new JTableHeader();
		Color colorSilverLight = new Color(176, 196, 222);
		Color colorNegro = new Color(0, 0, 0);
		th = tableIncapacidad.getTableHeader();
		Font fuente = new Font("Arial", Font.BOLD, 14);
		th.setFont(fuente);
		th.setBackground(colorSilverLight);
		th.setForeground(colorNegro);

		TableColumnModel columnModel = tableIncapacidad.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(100);
		columnModel.getColumn(1).setPreferredWidth(200);
		columnModel.getColumn(2).setPreferredWidth(200);
		columnModel.getColumn(3).setPreferredWidth(200);
		columnModel.getColumn(4).setPreferredWidth(300);
		columnModel.getColumn(5).setPreferredWidth(80);
		columnModel.getColumn(6).setPreferredWidth(200);
		columnModel.getColumn(7).setPreferredWidth(350);
		columnModel.getColumn(8).setPreferredWidth(150);
		columnModel.getColumn(9).setPreferredWidth(70);
		columnModel.getColumn(10).setPreferredWidth(70);

		String sqlSelect = "";
		if (valor.equals("")) {
			//			System.out.println("aqui");
			sqlSelect = "select CLAVE,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,CURP,RFC,SDI from dbo.empleados WHERE ELIMINAR_LOGICO='" + 1 + "' and DBO.EMPLEADOS.ID_EJERCICIOS = '"+ valor +"'";// order by id_puesto
		} else {
			sqlSelect="SELECT DBO.EMPLEADOS.CLAVE,DBO.EMPLEADOS.NOMBRE,DBO.EMPLEADOS.APELLIDO_PATERNO,DBO.EMPLEADOS.APELLIDO_MATERNO,\r\n" +  
					"dbo.puestos.nombre_puesto,dbo.puestos.no_puesto,dbo.puestos.salario,DBO.DEPENDENCIAS.UNIDAD_RESPONSABLE,SDI, \r\n" + 
					"DBO.EJERCICIOS.ID_EJERCICIOS,DBO.DEPENDENCIAS.ID_UNIDAD\r\n" + 
					"from empleados\r\n" + 
					"left JOIN DBO.PUESTOS ON DBO.EMPLEADOS.ID_PUESTO = DBO.PUESTOS.no_puesto\r\n" + 
					"LEFT JOIN DBO.DEPENDENCIAS ON DBO.EMPLEADOS.ID_UNIDAD = DBO.DEPENDENCIAS.ID_UNIDAD\r\n" + 
					"LEFT JOIN DBO.EJERCICIOS ON DBO.EMPLEADOS.ID_EJERCICIOS = DBO.EJERCICIOS.ID_EJERCICIOS\r\n" +
					"WHERE DBO.EMPLEADOS.id_ejercicios = '"+valor+"' and ELIMINAR_LOGICO='1'\r\n" ;
			//			System.out.println("o aqui");
			//			System.out.println(sqlSelect);
			Object datos[] = new String[13];
			PoolNYCH nych = new PoolNYCH();
			Connection con = null;
			Statement st= null;
			ResultSet resultSet = null;
			try {
				con = nych.datasource.getConnection();
				System.out.println("se abre conexion: ");
				st = con.createStatement();
				resultSet = st.executeQuery(sqlSelect);
				while (resultSet.next()) {// && resPuesto.next() && resDependencia.next() && resEjercicio.next()
					datos[0] = resultSet.getString(1);
					datos[1] = resultSet.getString(2);
					datos[2] = resultSet.getString(3);
					datos[3] = resultSet.getString(4);
					datos[4] = resultSet.getString(5);
					datos[5] = resultSet.getString(6);
					datos[6] = resultSet.getString(7);
					datos[7] = resultSet.getString(8);
					datos[8] = resultSet.getString(9);
					datos[9] = resultSet.getString(10);
					datos[10] = resultSet.getString(11);
					modelo.addRow(datos);
				}

				try {
					rowSorter = new TableRowSorter(modelo);
					tableIncapacidad.setRowSorter(rowSorter);

				}catch(Exception em) {
					em.printStackTrace();
					StringWriter errors = new StringWriter();
					em.printStackTrace(new PrintWriter(errors));
					LOG.info("Excepcion: " + errors);
					JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");
				}
				tableIncapacidad.setModel(modelo);
			} catch (SQLException el) {
				el.printStackTrace();
				StringWriter errors = new StringWriter();
				el.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: " + errors);
				JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");
			} finally {
				try {
					con.close();
					System.out.println("se cierra conexion: ");
				} catch (SQLException ep) {
					ep.printStackTrace();
					StringWriter errors = new StringWriter();
					ep.printStackTrace(new PrintWriter(errors));
					LOG.info("Excepcion: " + errors);
					JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");
				}
			}
		}

	}


	@SuppressWarnings("unlikely-arg-type")
	public void mostrarCantidadAcumulada() {
		DefaultTableModel modelo = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				if (column == 11) {
					return true;
				} else {
					return false;
				}
			}
		};
		;
		modelo.addColumn("FOLIO");
		modelo.addColumn("PRORRATEADA");
		//modelo.addColumn("ACUMULADA");
		modelo.addColumn("CLASIFICACION");
		modelo.addColumn("PERIODO");
		modelo.addColumn("TIPO");


		DefaultTableModel modeloAcum = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				if (column == 11) {
					return true;
				} else {
					return false;
				}
			}
		};
		;
		modeloAcum.addColumn("ACUMULADO");
		//modeloAcum.addColumn("PERIODO");

		tableCantidadAcumulada.setModel(modelo);
		tableCantidadAcumulada.setBackground(Color.WHITE);

		tableAcum.setModel(modeloAcum);
		tableAcum.setBackground(Color.WHITE);

		JTableHeader th = new JTableHeader();
		Color colorSilverLight = new Color(176, 196, 222);
		Color colorNegro = new Color(0, 0, 0);
		th = tableCantidadAcumulada.getTableHeader();
		Font fuente = new Font("Arial", Font.BOLD, 14);
		th.setFont(fuente);
		th.setBackground(colorSilverLight);
		th.setForeground(colorNegro);

		TableColumnModel columnModel = tableCantidadAcumulada.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(150);
		columnModel.getColumn(1).setPreferredWidth(150);
		//columnModel.getColumn(2).setPreferredWidth(150);
		columnModel.getColumn(2).setPreferredWidth(220);
		columnModel.getColumn(3).setPreferredWidth(150);
		columnModel.getColumn(4).setPreferredWidth(260);

		JTableHeader thAcum = new JTableHeader();
		thAcum = tableAcum.getTableHeader();
		thAcum.setFont(fuente);
		thAcum.setBackground(colorSilverLight);
		thAcum.setForeground(colorNegro);

		TableColumnModel columnModelAcum = tableAcum.getColumnModel();
		columnModelAcum.getColumn(0).setPreferredWidth(140);
		//columnModelAcum.getColumn(1).setPreferredWidth(100);

		String sqlSelect = "";
		Date fechaDe = dateChooserFechaDe.getDate();
		Date fechaHasta = dateChooserFechaHasta.getDate();
		SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
		int fila = tableIncapacidad.getSelectedRow();
		String id= tableIncapacidad.getValueAt(fila, 0).toString();
		sqlSelect="SELECT DBO.INCAPACIDADES.FOLIO_INCAPACIDAD,DBO.INCAPACIDADES.CANTIDAD_ACUMULADA,DBO.INCAPACIDADES.CLASIFICACION_INCAPACIDAD,\r\n" +  
				"dbo.NOMINA_CATORCENAS.NUMERO_CATORCENA,DBO.TIPO_INCAPACIDAD.DESCRIPCION \r\n" + 
				"from INCAPACIDADES\r\n" + 
				"left JOIN DBO.NOMINA_CATORCENAS ON DBO.INCAPACIDADES.ID_PERIODO = DBO.NOMINA_CATORCENAS.ID_CATORCENA\r\n" +
				"left join DBO.TIPO_INCAPACIDAD ON DBO.INCAPACIDADES.ID_TIPO_INCAPACIDAD = DBO.TIPO_INCAPACIDAD.ID_INCAPACIDAD \r\n"+
				"WHERE DBO.INCAPACIDADES.id_empleado = '"+id+"' and dbo.INCAPACIDADES.fecha_inicial BETWEEN convert(datetime,'"+ formatoDeFecha.format(fechaDe)+"',101)  and convert(datetime,'"+ formatoDeFecha.format(fechaHasta)+"',101) \r\n" ;
		//		System.out.println(sqlSelect);
		Object datos[] = new String[6];
		PoolNYCH nych = new PoolNYCH();
		Connection con = null;
		Statement st;
		ResultSet resultSet;
		try {
			con = nych.datasource.getConnection();
			st = con.createStatement();
			resultSet = st.executeQuery(sqlSelect);
			while (resultSet.next()) {// && resPuesto.next() && resDependencia.next() && resEjercicio.next()
				datos[0] = resultSet.getString(1);
				datos[1] = resultSet.getString(2);
				datos[2] = resultSet.getString(3);
				datos[3] = resultSet.getString(4);
				datos[4] = resultSet.getString(5);
				//			if(datos[3].equals("INICIAL")) {
				//				datos[5]="";
				//			}else {
				datos[5] = sumaCantidadAcumuladaDeIncapacidadPorEmpleado(id);
				//			}
				System.out.println(datos[5] = sumaCantidadAcumuladaDeIncapacidadPorEmpleado(id));
				modelo.addRow(datos);
			}

			tableCantidadAcumulada.setModel(modelo);
		} catch (SQLException el) {
			el.printStackTrace();
			StringWriter errors = new StringWriter();
			el.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: " + errors);
			JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");
		} finally {
			try {
				con.close();
			} catch (SQLException ep) {
				ep.printStackTrace();
				StringWriter errors = new StringWriter();
				ep.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: " + errors);
				JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");
			}
		}

		System.out.println(sqlSelect);
		Object datosAcum[] = new String[2];
		try {
			datosAcum[0] = datos[5];
			//datosAcum[1] = datos[3];
			modeloAcum.addRow(datosAcum);
			tableAcum.setModel(modeloAcum);
		} catch (Exception el) {
			el.printStackTrace();
			StringWriter errors = new StringWriter();
			el.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: " + errors);
			JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");
		} finally {
			try {
				con.close();
			} catch (SQLException ep) {
				ep.printStackTrace();
				StringWriter errors = new StringWriter();
				ep.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: " + errors);
				JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");
			}
		}
	}


	public String getValorAcumulado() {
		int valor =  tableCantidadAcumulada.getSelectedRow();
		String valorProrr="";
		String valorAcum="";
		if(valor>0) {
			valorProrr = tableCantidadAcumulada.getValueAt(valor, 1).toString();
			valorAcum = tableCantidadAcumulada.getValueAt(valor, 2).toString();
		}

		return "";


	}

	public String sumaCantidadAcumuladaDeIncapacidadPorEmpleado(String id) {
		Date fechaDe = dateChooserFechaDe.getDate();
		Date fechaHasta = dateChooserFechaHasta.getDate();
		SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
		String sqlSelect = "";
		sqlSelect="select sum(CANTIDAD_ACUMULADA) from INCAPACIDADES where id_empleado = "+ id +" and dbo.INCAPACIDADES.fecha_inicial "
				+ "BETWEEN convert(datetime,'"+ formatoDeFecha.format(fechaDe)+"',101)  and convert(datetime,'"+ formatoDeFecha.format(fechaHasta)+"',101)\r\n";
		System.out.println(sqlSelect);
		Object datos[] = new String[6];
		PoolNYCH nych = new PoolNYCH();
		Connection con = null;
		Statement st;
		ResultSet resultSet;
		try {
			con = nych.datasource.getConnection();
			st = con.createStatement();
			resultSet = st.executeQuery(sqlSelect);
			while (resultSet.next()) {
				datos[0] = resultSet.getString(1);
			}

		} catch (SQLException el) {
			el.printStackTrace();
			StringWriter errors = new StringWriter();
			el.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: " + errors);
			JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");
		} finally {
			try {
				con.close();
			} catch (SQLException ep) {
				ep.printStackTrace();
				StringWriter errors = new StringWriter();
				ep.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: " + errors);
				JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");
			}
		}
		return (String) datos[0];
	}


	public void windowOpened(ActionEvent e) {
		final long SLEEP_TIME = 1 * 1000;
		SwingWorker<Void, Void> mySwingWorker = new SwingWorker<Void, Void>() {
			protected Void doInBackground() throws Exception {


				//				limpiarTabla();

				//				mostrarDatosEmpleadoIncapacidad(lblTipoNominaOculto.getText());

				//				ArrayList<String> listaTipoIncapacidad = new ArrayList<String>();
				//				listaTipoIncapacidad = buscarTipoIncapacidad();
				//				comboBoxTipoIncapacidad.addItem("Seleccione Una");
				//				for (int i = 0; i < listaTipoIncapacidad.size(); i++) {
				//					comboBoxTipoIncapacidad.addItem(listaTipoIncapacidad.get(i));
				//				}

				//				ArrayList<String> listaPeriodosCatorcena = new ArrayList<String>();
				//				listaPeriodosCatorcena = buscarPeriodosCatorcenal();
				//				comboBoxPeriodo.addItem("Seleccione Uno");
				//				for (int i = 0; i < listaPeriodosCatorcena.size(); i++) {
				//					comboBoxPeriodo.addItem(listaPeriodosCatorcena.get(i));
				//				}


				Thread.sleep(SLEEP_TIME);
				return null;
			}
		};

		Window win = SwingUtilities.getWindowAncestor((AbstractButton) e.getSource());
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


	//	public static ArrayList<String> buscarPeriodosSemana() {
	//		//System.out.println("buscarEjercicio");
	//		PoolNYCH nych = new PoolNYCH();
	//		Connection connect = null;
	//		ResultSet resultSet = null;
	//		String sqlSelectEjercicio = "SELECT * FROM DBO.NOMINA_SEMANAS";
	//		ArrayList<String> lista = new ArrayList<String>();
	//		try {
	//			connect = nych.datasource.getConnection();
	//			Statement st = connect.createStatement();
	//			resultSet = st.executeQuery(sqlSelectEjercicio);
	//		} catch (Exception exp) {
	//			exp.printStackTrace();
	//			LOG.info("Excepción: " + exp);
	//			// JOptionPane.showMessageDialog(null, exp, "Error de conexion",
	//			// JOptionPane.ERROR_MESSAGE);
	//			JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");
	//		}
	//		try {
	//			while (resultSet.next()) {
	//				lista.add(resultSet.getString("fecha_semana"));
	//			}
	//		} catch (Exception exc) {
	//			exc.printStackTrace();
	//			LOG.info("Excepción: " + exc);
	//			// JOptionPane.showMessageDialog(null, exc, "Error de conexion",
	//			// JOptionPane.ERROR_MESSAGE);
	//			JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");
	//		} finally {
	//			try {
	//				connect.close();
	//			} catch (SQLException ep) {
	//				ep.printStackTrace();
	//				LOG.info("Excepción: " + ep);
	//				// JOptionPane.showMessageDialog(null, ep, "Error de desconexion",
	//				// JOptionPane.ERROR_MESSAGE);
	//				JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");
	//			}
	//		}
	//		return lista;
	//	}


	public static ArrayList<String> buscarTipoIncapacidad() {
		//System.out.println("buscarEjercicio");
		PoolNYCH nych = new PoolNYCH();
		Connection connect = null;
		ResultSet resultSet = null;
		String sqlSelectEjercicio = "SELECT * FROM DBO.TIPO_INCAPACIDAD";
		ArrayList<String> lista = new ArrayList<String>();
		try {
			connect = nych.datasource.getConnection();
			Statement st = connect.createStatement();
			resultSet = st.executeQuery(sqlSelectEjercicio);
		} catch (Exception exp) {
			exp.printStackTrace();
			LOG.info("Excepción: " + exp);
			// JOptionPane.showMessageDialog(null, exp, "Error de conexion",
			// JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");
		}
		try {
			while (resultSet.next()) {
				lista.add(resultSet.getString("descripcion"));
			}
		} catch (Exception exc) {
			exc.printStackTrace();
			LOG.info("Excepción: " + exc);
			// JOptionPane.showMessageDialog(null, exc, "Error de conexion",
			// JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");
		} finally {
			try {
				connect.close();
			} catch (SQLException ep) {
				ep.printStackTrace();
				LOG.info("Excepción: " + ep);
				// JOptionPane.showMessageDialog(null, ep, "Error de desconexion",
				// JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");
			}
		}
		return lista;
	}

	//	public static ArrayList<String> buscarPeriodosCatorcenal() {
	//		PoolNYCH nych = new PoolNYCH();
	//		Connection connect = null;
	//		ResultSet resultSet = null;
	//		String sqlSelectEjercicio = "SELECT * FROM DBO.NOMINA_CATORCENAS";
	//		ArrayList<String> lista = new ArrayList<String>();
	//		try {
	//			connect = nych.datasource.getConnection();
	//			Statement st = connect.createStatement();
	//			resultSet = st.executeQuery(sqlSelectEjercicio);
	//		} catch (Exception exp) {
	//			exp.printStackTrace();
	//			LOG.info("Excepción: " + exp);
	//			// JOptionPane.showMessageDialog(null, exp, "Error de conexion",
	//			// JOptionPane.ERROR_MESSAGE);
	//			JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");
	//		}
	//		try {
	//			while (resultSet.next()) {
	//				lista.add(resultSet.getString("numero_catorcena"));
	//			}
	//		} catch (Exception exc) {
	//			exc.printStackTrace();
	//			LOG.info("Excepción: " + exc);
	//			// JOptionPane.showMessageDialog(null, exc, "Error de conexion",
	//			// JOptionPane.ERROR_MESSAGE);
	//			JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");
	//		} finally {
	//			try {
	//				connect.close();
	//			} catch (SQLException ep) {
	//				ep.printStackTrace();
	//				LOG.info("Excepción: " + ep);
	//				// JOptionPane.showMessageDialog(null, ep, "Error de desconexion",
	//				// JOptionPane.ERROR_MESSAGE);
	//				JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");
	//			}
	//		}
	//		return lista;
	//	}


	public static ArrayList<String> buscarFechas() {
		PoolNYCH nych = new PoolNYCH();
		Connection connect = null;
		ResultSet resultSet = null;
		String sqlSelectEjercicio = "SELECT * FROM DBO.NOMINA_CATORCENAS";
		ArrayList<String> lista = new ArrayList<String>();
		try {
			connect = nych.datasource.getConnection();
			Statement st = connect.createStatement();
			resultSet = st.executeQuery(sqlSelectEjercicio);
			while (resultSet.next()) {
				lista.add(resultSet.getString("numero_catorcena"));
				//				System.out.println("fecha inicial: " + resultSet.getString("fecha_inicial"));
			}
		} catch (Exception exp) {
			exp.printStackTrace();
			LOG.info("Excepción: " + exp);
			JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");

		} finally {
			try {
				connect.close();
			} catch (SQLException ep) {
				ep.printStackTrace();
				LOG.info("Excepción: " + ep);
				JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");
			}
		}
		return lista;
	}

	public static ArrayList<String> buscarFechasSemanal() {
		PoolNYCH nych = new PoolNYCH();
		Connection connect = null;
		ResultSet resultSet = null;
		String sqlSelectEjercicio = "SELECT * FROM DBO.NOMINA_SEMANAS";
		ArrayList<String> lista = new ArrayList<String>();
		try {
			connect = nych.datasource.getConnection();
			Statement st = connect.createStatement();
			resultSet = st.executeQuery(sqlSelectEjercicio);
			while (resultSet.next()) {
				lista.add(resultSet.getString("numero_semana"));
				//				System.out.println("fecha inicial: " + resultSet.getString("fecha_inicial"));
			}
		} catch (Exception exp) {
			exp.printStackTrace();
			LOG.info("Excepción: " + exp);
			JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");

		} finally {
			try {
				connect.close();
			} catch (SQLException ep) {
				ep.printStackTrace();
				LOG.info("Excepción: " + ep);
				JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");
			}
		}
		return lista;
	}
}

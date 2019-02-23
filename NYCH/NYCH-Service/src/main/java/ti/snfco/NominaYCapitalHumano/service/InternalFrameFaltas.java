package ti.snfco.NominaYCapitalHumano.service;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

//import org.apache.poi.hslf.record.OutlineTextRefAtom;
import org.slf4j.LoggerFactory;
import javax.swing.border.MatteBorder;
import java.awt.SystemColor;
import javax.swing.JSeparator;
import com.toedter.calendar.JDateChooser;

public class InternalFrameFaltas extends JInternalFrame {

	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(InternalFrameFaltas.class);
	private static final long serialVersionUID = 1L;

	JTextField textFieldEmpleado;
	JTable tableReporteVacaciones = new JTable();
	JTable tableReporteChecador =  new JTable();
	JTable tableReporteChecadorMvtos =  new JTable();
	JTable tableBuscEmple = new JTable(); 
	TableRowSorter rowSortere;
	TableRowSorter rowSorterer;
	int IdBusquedaEmplee = 1;
	JLabel lblIdEmp = new JLabel("ID");
	JLabel lblApPat = new JLabel("PATERNO");
	JLabel lblApMat = new JLabel("MATERNO");
	JLabel lblNombre = new JLabel("NOMBRE");
	JLabel lblPrecio = new JLabel("UNIDAD");
	JLabel lblPuesto = new JLabel("PUESTO");
	JLabel lblApMatChecador = new JLabel("");
	JLabel lblEmpleadoChecador = new JLabel("Nombre del Empleado(a):");
	JLabel lblClaveEmpChecador = new JLabel("");
	JLabel lblNombreChecador = new JLabel("");
	JLabel lbldptoChecador = new JLabel("");
	JLabel lblPuestoChecador = new JLabel("");
	JLabel lblTurnoChecador = new JLabel("");
	JLabel lblApPatChecador = new JLabel("");
	JLabel lblClave = new JLabel("Clave");
	JLabel lblFecha = new JLabel("Fecha");
	JLabel lblEntraTurno = new JLabel("Entrada Turno");
	JLabel lblEntrada = new JLabel("Entrada dia");
	JLabel lblSalida = new JLabel("Salida dia");
	JLabel lblSalidaTurno = new JLabel("Salida Turno");
	JTextField textFieldEmpleadoChecador;
	JLabel lblIncidencia = new JLabel("Incidencia");
	JLabel lblInhabil = new JLabel("Inhabil");
	JLabel lblMotivo = new JLabel("Motivo");
	JLabel lblEstacion = new JLabel("Estacion");
	JLabel lblValidacion = new JLabel("");
	JButton btnFaltas = new JButton("Verificar Usuario");
	JButton btnCalcularFalta = new JButton("Ver Faltas");
	JScrollPane scrollPaneMvtos = new JScrollPane();
	JButton btnSeleccionarMvtos = new JButton("Aplicar");
	public static JDesktopPane desktopPane = new JDesktopPane();
	JLabel lblTipoNominaOculta = new JLabel("idTipoNomina");
	JLabel lblIdPuesto = new JLabel("PUESTO");
	JLabel lblIdUREmpMov = new JLabel("UNIDAD");
	private final JSeparator separator = new JSeparator();
	private final JLabel lblicon = new JLabel();
	private JTextField textFieldBuscarEmpleado;
	JLabel lblFechaDe = new JLabel("Fecha De:");
	JDateChooser dateChooserFechaDe = new JDateChooser();
	JLabel lblFechaHasta = new JLabel("Fecha Hasta:");
	JDateChooser dateChooserFechaHasta = new JDateChooser();

	public InternalFrameFaltas() {
		setFrameIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("users_delete.png"))));
		setBounds(0, 0, 1501, 690);
		setVisible(true);
		setTitle("Faltas");
		getContentPane().setLayout(null);

		JPanel panelPension = new JPanel();
		panelPension.setBackground(SystemColor.controlHighlight);
		panelPension.setBorder(new TitledBorder(null, "Reporte Cálculo Faltas", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(176, 196, 222)));
		panelPension.setBounds(0, 0, 1569, 841);
		//panelPension.setBackground(new Color(147,140,147));
		getContentPane().add(panelPension);
		panelPension.setLayout(null);

		JLabel lblNombreEmp = new JLabel("Nombre del Empleado(a):");
		lblNombreEmp.setForeground(new Color(0, 0, 0));
		lblNombreEmp.setBounds(34, 56, 184, 14);
		panelPension.add(lblNombreEmp);

		//		textFieldEmpleado = new JTextField();
		//		textFieldEmpleado.addKeyListener(new KeyAdapter() {
		//			public void keyReleased(KeyEvent e) {
		//				//rowSortere.setRowFilter(RowFilter.regexFilter(textFieldEmpleado.getText().toUpperCase(), IdBusquedaEmplee));
		//			}
		//		});
		//		textFieldEmpleado.setColumns(10);
		//		textFieldEmpleado.setBounds(27, 51, 314, 25);
		//		panelPension.add(textFieldEmpleado);

		JScrollPane scrollPaneVacaciones = new JScrollPane();
		scrollPaneVacaciones.setBounds(34, 100, 669, 151);
		scrollPaneVacaciones.setViewportBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		scrollPaneVacaciones.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneVacaciones.setViewportView(tableReporteVacaciones);
		panelPension.add(scrollPaneVacaciones);

		JButton btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


				redireccionarEmpleado();
				lblIdEmp.setVisible(true);
				lblNombre.setVisible(true);
				lblApPat.setVisible(true);
				lblApMat.setVisible(true);
				lblPuesto.setVisible(true);
				lblPrecio.setVisible(true);
				lblIdPuesto.setVisible(true);
				lblIdUREmpMov.setVisible(true);


			}
		});
		btnSeleccionar.setBounds(294, 262, 128, 30);
		panelPension.add(btnSeleccionar);


		lblIdEmp.setForeground(new Color(0, 0, 0));
		lblIdEmp.setBounds(30, 328, 46, 14);
		panelPension.add(lblIdEmp);


		lblApPat.setForeground(new Color(0, 0, 0));
		lblApPat.setBounds(30, 351, 276, 14);
		panelPension.add(lblApPat);


		lblApMat.setForeground(new Color(0, 0, 0));
		lblApMat.setBounds(30, 376, 276, 14);
		panelPension.add(lblApMat);


		lblNombre.setForeground(new Color(0, 0, 0));
		lblNombre.setBounds(119, 328, 187, 14);
		panelPension.add(lblNombre);


		lblPrecio.setForeground(new Color(0, 0, 0));
		lblPrecio.setBounds(326, 351, 53, 14);
		panelPension.add(lblPrecio);


		lblPuesto.setForeground(new Color(0, 0, 0));
		lblPuesto.setBounds(406, 328, 294, 14);
		panelPension.add(lblPuesto);

		JScrollPane scrollPaneChecador = new JScrollPane();
		scrollPaneChecador.setBounds(773, 100,  693, 153);
		scrollPaneChecador.setViewportBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		scrollPaneChecador.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneChecador.setViewportView(tableReporteChecador);
		panelPension.add(scrollPaneChecador);


		lblEmpleadoChecador.setForeground(new Color(0, 0, 0));
		lblEmpleadoChecador.setBounds(773, 26, 184, 14);
		panelPension.add(lblEmpleadoChecador);

		textFieldEmpleadoChecador = new JTextField();
		textFieldEmpleadoChecador.setBorder(null);
		textFieldEmpleadoChecador.setBackground(SystemColor.controlHighlight);
		textFieldEmpleadoChecador.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				rowSortere.setRowFilter(RowFilter.regexFilter(textFieldEmpleadoChecador.getText().toUpperCase(), IdBusquedaEmplee));
			}
		});
		textFieldEmpleadoChecador.setColumns(10);
		textFieldEmpleadoChecador.setBounds(808, 51, 314, 25);
		panelPension.add(textFieldEmpleadoChecador);


		lblClaveEmpChecador.setForeground(new Color(0, 0, 0));
		lblClaveEmpChecador.setBounds(847, 303, 61, 14);
		panelPension.add(lblClaveEmpChecador);


		lblNombreChecador.setForeground(new Color(0, 0, 0));
		lblNombreChecador.setBounds(923, 303, 238, 14);
		panelPension.add(lblNombreChecador);


		lbldptoChecador.setForeground(new Color(0, 0, 0));
		lbldptoChecador.setBounds(1152, 303, 314, 14);
		panelPension.add(lbldptoChecador);


		lblPuestoChecador.setForeground(new Color(0, 0, 0));
		lblPuestoChecador.setBounds(1152, 349, 294, 14);
		panelPension.add(lblPuestoChecador);


		lblTurnoChecador.setForeground(new Color(0, 0, 0));
		lblTurnoChecador.setBounds(1152, 328, 298, 14);
		panelPension.add(lblTurnoChecador);


		lblApPatChecador.setForeground(new Color(0, 0, 0));
		lblApPatChecador.setBounds(847, 324, 276, 14);
		panelPension.add(lblApPatChecador);


		lblApMatChecador.setForeground(new Color(0, 0, 0));
		lblApMatChecador.setBounds(847, 349, 276, 14);
		panelPension.add(lblApMatChecador);

		JButton bntSeleccionarChecador = new JButton("Seleccionar");
		bntSeleccionarChecador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//redirecMovtos();
				redireccionarEmpleadoChecador();

			}
		});
		bntSeleccionarChecador.setBounds(1018, 262, 128, 30);
		panelPension.add(bntSeleccionarChecador);


		btnCalcularFalta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(dateChooserFechaDe.getDate()!=null || dateChooserFechaHasta.getDate()!=null) {
					verFaltas();
					lblClave.setVisible(false);
					lblFecha.setVisible(false);
					lblEntraTurno.setVisible(false);
					lblEntrada.setVisible(false);
					lblSalida.setVisible(false);
					lblSalidaTurno.setVisible(false);
					lblIncidencia.setVisible(false);
					lblInhabil.setVisible(false);
					lblMotivo.setVisible(false);
					lblEstacion.setVisible(false);
				}else {
					JOptionPane.showMessageDialog(null, "Seleccione las fechas de la búsqueda");
				}
			}
		});
		btnCalcularFalta.setBounds(956, 402, 131, 30);
		panelPension.add(btnCalcularFalta);


		lblClave.setForeground(new Color(0, 0, 0));
		lblClave.setBounds(1224, 634, 133, 14);
		panelPension.add(lblClave);


		lblFecha.setForeground(new Color(0, 0, 0));
		lblFecha.setBounds(1224, 609, 133, 14);
		panelPension.add(lblFecha);


		lblEntraTurno.setForeground(new Color(0, 0, 0));
		lblEntraTurno.setBounds(172, 609, 201, 14);
		panelPension.add(lblEntraTurno);


		lblEntrada.setForeground(new Color(0, 0, 0));
		lblEntrada.setBounds(395, 609, 281, 14);
		panelPension.add(lblEntrada);


		lblSalida.setForeground(new Color(0, 0, 0));
		lblSalida.setBounds(395, 634, 201, 14);
		panelPension.add(lblSalida);


		lblSalidaTurno.setForeground(new Color(0, 0, 0));
		lblSalidaTurno.setBounds(172, 634, 213, 14);
		panelPension.add(lblSalidaTurno);
		
		lblIncidencia.setForeground(new Color(0, 0, 0));
		lblIncidencia.setBounds(720, 634, 201, 14);
		panelPension.add(lblIncidencia);

		lblInhabil.setForeground(new Color(0, 0, 0));
		lblInhabil.setBounds(720, 609, 162, 14);
		panelPension.add(lblInhabil);

		lblMotivo.setForeground(new Color(0, 0, 0));
		lblMotivo.setBounds(994, 634, 201, 14);
		panelPension.add(lblMotivo);
		
		lblEstacion.setForeground(new Color(0, 0, 0));
		lblEstacion.setBounds(994, 609, 201, 14);
		panelPension.add(lblEstacion);


		scrollPaneMvtos.setBounds(34, 445, 1432, 153);
		scrollPaneMvtos.setViewportBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		scrollPaneMvtos.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneMvtos.setViewportView(tableReporteChecadorMvtos);
		panelPension.add(scrollPaneMvtos);


		btnSeleccionarMvtos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//redirecMovtos();
				//redireccionarRegistroEmpPerp();
				
//				int fila = tableReporteVacaciones.getSelectedRow();
//				String id = tableReporteVacaciones.getValueAt(fila, 0).toString();
//
//				ArrayList<String> listaFaltas = new ArrayList<String>();
//				listaFaltas = 	obtenerFaltasxEmpleado(id);
//				System.out.println("tamaño de la lista: " + listaFaltas.size());
//				System.out.println("valor de la faltas: " + listaFaltas.get(0));
//				for (int i = 0; i < listaFaltas.size(); i++) {
//					System.out.println("i: "+listaFaltas.get(i));
//					//textFieldFaltas.setText(String.valueOf((listaFaltas.get(i))));
//				}
//				
//			
//				
//				
//				int filaAplicarFaltas = tableReporteChecadorMvtos.getSelectedRow();
//				String fechaFalta =  tableReporteChecadorMvtos.getValueAt(filaAplicarFaltas, 1).toString();
//				DateFormat outputFormat = new SimpleDateFormat("yyyy/MM/dd");
//				DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
//				
//				String inputText = fechaFalta;
//				Date date = null;
//				try {
//					date = inputFormat.parse(inputText);
//				} catch (ParseException ex) {
//					ex.printStackTrace();
//				}
//				String outputText = outputFormat.format(date);
				
//				
				insertarFaltasPorEmpleado();
				//JOptionPane.showMessageDialog(null, "Se ha guardado la falta con exito");
				//dispose();
				
			}
		});
		btnSeleccionarMvtos.setBounds(34, 601, 128, 30);
		panelPension.add(btnSeleccionarMvtos);
		lblValidacion.setForeground(new Color(0, 128, 0));
		lblValidacion.setBounds(690, 346, 147, 14);

		panelPension.add(lblValidacion);

		btnFaltas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int filaEmpleados = tableReporteVacaciones.getSelectedRow();
				int filaChecador = tableReporteChecador.getSelectedRow();
				
				String nombre = tableReporteVacaciones.getValueAt(filaEmpleados, 1).toString().trim();
//				System.out.println("nombre: "+nombre +" longitud: "+nombre.length());
				String nombreChecadorr = tableReporteChecador.getValueAt(filaChecador, 1).toString().trim();
//				System.out.println("nombreChecadorr: "+nombreChecadorr +" longitud: "+nombreChecadorr.length());
				
				String apellidoPat = tableReporteVacaciones.getValueAt(filaEmpleados, 2).toString().trim();
//				System.out.println("apellidoPat: "+apellidoPat +" longitud: "+apellidoPat.length());
				String apellidoPatChecador = tableReporteChecador.getValueAt(filaChecador, 2).toString().trim();
//				System.out.println("apellidoPatChecador: "+apellidoPatChecador +" longitud: "+apellidoPatChecador.length());
				
				String apellidoMat = tableReporteVacaciones.getValueAt(filaEmpleados, 3).toString().trim();
//				System.out.println("apellidoMat: "+apellidoMat +" longitud: "+apellidoMat.length());
				String apellidoMatChecador = tableReporteChecador.getValueAt(filaChecador, 3).toString().trim();
//				System.out.println("apellidoMatChecador: "+apellidoMatChecador +" longitud: "+apellidoMatChecador.length());
				
				
//				System.out.println("nombreNYCH: "+lblNombre.getText().trim() +" lenght nombreNYCH: "+lblNombre.getText().length() + " apPatNych: " + lblApPat.getText().trim() + " lenght apPatNych: " + lblApPat.getText().length() + " ApMatNych: " + lblApMat.getText().trim() + " lenght ApMatNych: " + lblApMat.getText().length() );
//				System.out.println("nombreChecador: "+lblNombreChecador.getText().trim() + " lenght nombreChecador: "+lblNombreChecador.getText().length() + " apPatChecador: " + lblApPatChecador.getText().trim() + " lenght apPatChecador: " + lblApPatChecador.getText().length() + " ApMatChecador: " + lblApMatChecador.getText().trim() + " lenght ApMatChecador: " + lblApMatChecador.getText().length() );

//				String nombreNych=lblNombre.getText().trim();
//				String nombreChecador=lblNombreChecador.getText().trim();
//
//				String apPatNych=lblApPat.getText().trim();
//				String apPatChecador=lblApPatChecador.getText().trim();
//
//				String apMatNych=lblApMat.getText().trim();
//				String apMatChecador = lblApMatChecador.getText().trim();
//
//				int nomNych = nombreNych.length();
//				int nomChecador=nombreChecador.length();
//
//				int apPtNych = apPatNych.length();
//				int apPtChecador = apPatChecador.length();
//
//				int apMtNych = apMatNych.length();
//				int apMtChecador = apMatChecador.length();

//				System.out.println("Empleado: "+nombreNych+" "+apPatNych+" "+apMatNych);
//				System.out.println("longitudes: "+nomNych+" "+apPtNych+" "+apMtNych);
//				System.out.println("Empleado: "+nombreChecador+" "+apPatChecador+" "+apMatChecador);
//				System.out.println("longitudes: "+nomChecador+" "+apPtChecador+" "+apMtChecador);

//				if(nomNych==nomChecador && apPtNych==apPtChecador && apMtNych==apMtChecador) {// &&lblNombre.getText().trim()==lblNombreChecador.getText().trim() lblApPat==lblApPatChecador && lblApMat==lblApMatChecado
//					lblValidacion.setText("Es correcto");
//					System.out.println("correcto.");
//
//
//					btnCalcularFalta.setVisible(true);
//					scrollPaneMvtos.setVisible(true);
//					btnSeleccionarMvtos.setVisible(true);
//					lblClave.setVisible(false);
//					lblFecha.setVisible(false);
//					lblEntraTurno.setVisible(false);
//					lblEntrada.setVisible(false);
//					lblSalidaTurno.setVisible(false);
//					lblSalida.setVisible(false);
//					lblIncidencia.setVisible(false);
//					lblMotivo.setVisible(false);
//					lblEstacion.setVisible(false);
//					lblInhabil.setVisible(false);
//
//					if(lblValidacion.getText().trim()=="correcto") {
//						System.out.println("empleado: " + lblIdEmp.getText());
//					}
//
//				}
				if(nombre.matches(nombreChecadorr) && apellidoPat.matches(apellidoPatChecador) && apellidoMat.matches(apellidoMatChecador)) {
					
					lblValidacion.setText("Es correcto");
					System.out.println("correcto.");
					
					lblFechaDe.setVisible(true);
					lblFechaHasta.setVisible(true);
					dateChooserFechaDe.setVisible(true);
					dateChooserFechaHasta.setVisible(true);
					btnCalcularFalta.setVisible(true);
					scrollPaneMvtos.setVisible(true);
					btnSeleccionarMvtos.setVisible(true);
					lblClave.setVisible(false);
					lblFecha.setVisible(false);
					lblEntraTurno.setVisible(false);
					lblEntrada.setVisible(false);
					lblSalidaTurno.setVisible(false);
					lblSalida.setVisible(false);
					lblIncidencia.setVisible(false);
					lblMotivo.setVisible(false);
					lblEstacion.setVisible(false);
					lblInhabil.setVisible(false);
				}
				else {
					JOptionPane.showMessageDialog(null, "El empleado del checador no es el mismo que el de la nomina por favor, verficar.");
					lblValidacion.setText("Es Incorrecto");
					System.out.println("Incorrecto.");
				}

			}
		});
		btnFaltas.setBounds(684, 316, 133, 30);

		panelPension.add(btnFaltas);
		lblIdPuesto.setForeground(new Color(0, 0, 0));


		lblIdPuesto.setBounds(326, 328, 53, 14);
		panelPension.add(lblIdPuesto);
		lblIdUREmpMov.setForeground(new Color(0, 0, 0));
		lblIdUREmpMov.setBounds(406, 351, 281, 14);
		
		panelPension.add(lblIdUREmpMov);
		separator.setBackground(new Color(176, 196, 222));
		separator.setForeground(new Color(176, 196, 222));
		separator.setBounds(808, 77, 315, 3);
		
		panelPension.add(separator);
		lblicon.setBounds(769, 49, 46, 27);
		lblicon.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("icons8_Search_32px_2.png"))));
		panelPension.add(lblicon);
		
		JButton btnNewButton = new JButton("Ver Empleados");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarEmpleadosChecador();
				mostrarEmpleadosDesdeIconoCalcular(lblTipoNominaOculta.getText());
//				setVisible(false);
			}
		});
		btnNewButton.setBounds(585, 52, 118, 37);
		panelPension.add(btnNewButton);
		
		lblTipoNominaOculta.setBounds(585, 37, 70, 14);
		panelPension.add(lblTipoNominaOculta);
		
		textFieldBuscarEmpleado = new JTextField();
		textFieldBuscarEmpleado.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				rowSorterer.setRowFilter(RowFilter.regexFilter(textFieldBuscarEmpleado.getText().toUpperCase(), IdBusquedaEmplee));
			}
		});
		textFieldBuscarEmpleado.setColumns(10);
		textFieldBuscarEmpleado.setBorder(null);
		textFieldBuscarEmpleado.setBackground(SystemColor.controlHighlight);
		textFieldBuscarEmpleado.setBounds(260, 58, 314, 25);
		panelPension.add(textFieldBuscarEmpleado);
		
		JLabel lblIconEmpleados = new JLabel();
		lblIconEmpleados.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("icons8_Search_32px_2.png"))));
		lblIconEmpleados.setBounds(221, 58, 46, 27);
		panelPension.add(lblIconEmpleados);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(new Color(176, 196, 222));
		separator_1.setBackground(new Color(176, 196, 222));
		separator_1.setBounds(260, 86, 315, 3);
		panelPension.add(separator_1);
		
		lblFechaDe.setBounds(377, 415, 80, 14);
		panelPension.add(lblFechaDe);
		
		dateChooserFechaDe.setBounds(457, 412, 194, 20);
		panelPension.add(dateChooserFechaDe);
		
		lblFechaHasta.setBounds(671, 415, 80, 14);
		panelPension.add(lblFechaHasta);
		
		dateChooserFechaHasta.setBounds(752, 412, 194, 20);
		panelPension.add(dateChooserFechaHasta);
	}


	public void redirecMovtos() {
		int fila = tableReporteChecadorMvtos.getSelectedRow();
		if(fila>=0) {

			if(tableReporteChecadorMvtos.getValueAt(fila, 0)==null) {
				lblClave.setText("");
			}else {
				lblClave.setText(tableReporteChecadorMvtos.getValueAt(fila, 0).toString());
			}

			if(tableReporteChecadorMvtos.getValueAt(fila, 1)==null) {
				lblFecha.setText("");
			}else {
				lblFecha.setText(tableReporteChecadorMvtos.getValueAt(fila, 1).toString());
			}

			if(tableReporteChecadorMvtos.getValueAt(fila, 2)==null) {
				lblEntraTurno.setText("");
			}else {
				lblEntraTurno.setText(tableReporteChecadorMvtos.getValueAt(fila, 2).toString());
			}

			if(tableReporteChecadorMvtos.getValueAt(fila, 3)==null) {
				lblEntrada.setText("");
			}else {
				lblEntrada.setText(tableReporteChecadorMvtos.getValueAt(fila, 3).toString());
			}

			if(tableReporteChecadorMvtos.getValueAt(fila, 4)==null) {
				lblSalida.setText(Calendar.getInstance().getTime().toString());
			}else {
				lblSalida.setText(tableReporteChecadorMvtos.getValueAt(fila, 4).toString());
			}

			if(tableReporteChecadorMvtos.getValueAt(fila, 5)==null) {
				lblSalidaTurno.setText("");
			}else {
				lblSalidaTurno.setText(tableReporteChecadorMvtos.getValueAt(fila, 5).toString());
			}

			if(tableReporteChecadorMvtos.getValueAt(fila, 6)==null) {
				lblInhabil.setText("");
			}else {
				lblInhabil.setText(tableReporteChecadorMvtos.getValueAt(fila, 6).toString());
			}

			if(tableReporteChecadorMvtos.getValueAt(fila, 7)==null) {
				lblIncidencia.setText("");
			}else {
				lblIncidencia.setText(tableReporteChecadorMvtos.getValueAt(fila, 7).toString());
			}

			if(tableReporteChecadorMvtos.getValueAt(fila, 8)==null) {
				lblMotivo.setText("");
			}else {
				lblMotivo.setText(tableReporteChecadorMvtos.getValueAt(fila, 8).toString());
			}

			if(tableReporteChecadorMvtos.getValueAt(fila, 9)==null) {
				lblEstacion.setText("");
			}else {
				lblEstacion.setText(tableReporteChecadorMvtos.getValueAt(fila, 9).toString());
			}



			tableReporteChecadorMvtos.getModel().getRowCount();//saber cuantos registros hay en la tabla
			tableReporteChecadorMvtos.getRowCount();
			System.out.println("seleccionado: "+tableReporteChecadorMvtos.getModel().getRowCount());
			System.out.println("2: "+tableReporteChecadorMvtos.getRowSelectionAllowed());
			System.out.println("3: "+tableReporteChecadorMvtos.getSelectedRow());
			System.out.println("4: "+tableReporteChecadorMvtos.getRowCount());
			System.out.println("5: "+tableReporteChecadorMvtos.getComponentCount());
			System.out.println("6: "+tableReporteChecadorMvtos.getModel().getValueAt(fila, 0));
			System.out.println("7: "+tableReporteChecadorMvtos.getModel().getValueAt(fila, 1));
			System.out.println("8: "+tableReporteChecadorMvtos.getModel().getValueAt(fila, 2));
			System.out.println("9: "+tableReporteChecadorMvtos.getModel().getValueAt(fila, 3));
			System.out.println("10: "+tableReporteChecadorMvtos.getModel().getValueAt(fila, 4));
			System.out.println("11: "+tableReporteChecadorMvtos.getModel().getValueAt(fila, 5));
			System.out.println("12: "+tableReporteChecadorMvtos.getModel().getValueAt(fila, 6));
			System.out.println("13: "+tableReporteChecadorMvtos.getModel().getValueAt(fila, 7));
			System.out.println("14: "+tableReporteChecadorMvtos.getModel().getValueAt(fila, 8));
			System.out.println("15: "+tableReporteChecadorMvtos.getModel().getValueAt(fila, 9));




			//			if(fila>0) {
			//				//				int cont=0;
			//				//				cont++;
			//				//				tableReporteChecadorMvtos.getRowSelectionAllowed();
			//				//				tableReporteChecadorMvtos.getSelectedRow();
			//				//				tableReporteChecadorMvtos.getComponentCount();
			//				//				System.out.println("seleccionado: "+tableReporteChecadorMvtos.getModel().getRowCount());
			//				//				System.out.println("2: "+tableReporteChecadorMvtos.getRowSelectionAllowed());
			//				//				System.out.println("3: "+tableReporteChecadorMvtos.getSelectedRow());
			//				//				System.out.println("4: "+tableReporteChecadorMvtos.getRowCount());
			//				//				System.out.println("5: "+tableReporteChecadorMvtos.getComponentCount());
			//
			//				int [] filas = tableReporteChecadorMvtos.getSelectedRows();
			//				if(filas.length>=0) {
			//					
			//					System.out.println("?: "+tableReporteChecadorMvtos.getModel().getValueAt(filas.length, 0));
			//					System.out.println("?: "+tableReporteChecadorMvtos.getModel().getValueAt(filas.length, 1));
			//					System.out.println("?: "+tableReporteChecadorMvtos.getModel().getValueAt(filas.length, 2));
			//					System.out.println("?: "+tableReporteChecadorMvtos.getModel().getValueAt(filas.length, 3));
			//					System.out.println("?: "+tableReporteChecadorMvtos.getModel().getValueAt(filas.length, 4));
			//					System.out.println("?: "+tableReporteChecadorMvtos.getModel().getValueAt(filas.length, 5));
			//					System.out.println("?: "+tableReporteChecadorMvtos.getModel().getValueAt(filas.length, 6));
			//					System.out.println("?: "+tableReporteChecadorMvtos.getModel().getValueAt(filas.length, 7));
			//					System.out.println("?: "+tableReporteChecadorMvtos.getModel().getValueAt(filas.length, 8));
			//					System.out.println("?: "+tableReporteChecadorMvtos.getModel().getValueAt(filas.length, 9));
			//				}
			//				System.out.println("filas: "+filas.length);
			//				tableReporteChecadorMvtos.getSelectedColumns();
			//
			//			}
			//				tableReporteChecadorMvtos.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			//				tableReporteChecadorMvtos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			//				    public void valueChanged(ListSelectionEvent e) {
			//				        ListSelectionModel sm = (ListSelectionModel) e.getSource();
			//				        int sum = 0;
			//				        for (int i = sm.getMinSelectionIndex(); i <= sm.getMaxSelectionIndex(); i++) {
			//				            sum += sm.isSelectedIndex(i) ? 1 : 0;
			//				        }
			//				        System.out.println(sum);
			//				    }
			//				});





		}else {
			JOptionPane.showMessageDialog(null, "Registro no Seleccionado");
		}
	}
	
	
	public int insertarFaltasPorEmpleado() {
		
		int resultado = 0;
		PoolNYCH nych = new PoolNYCH();
		Connection conn =null;
		String sqlInsert="";

		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		int fila = tableReporteVacaciones.getSelectedRow();
		int filaAplicarFaltas = tableReporteChecadorMvtos.getSelectedRow();
		

		if(filaAplicarFaltas>0) {
			
			String fechaFalta =  tableReporteChecadorMvtos.getValueAt(filaAplicarFaltas, 1).toString();
			System.out.println("fecha a insertar: "+fechaFalta);
			
			
			DateFormat outputFormat = new SimpleDateFormat("yyyy/MM/dd");
			DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
			
			String inputText = fechaFalta;
			Date date = null;
			try {
				date = inputFormat.parse(inputText);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			String outputText = outputFormat.format(date);
		
		
			
			/**/
			
			String id = tableReporteVacaciones.getValueAt(fila, 0).toString();

			ArrayList<String> listaFaltas = new ArrayList<String>();
			listaFaltas = 	obtenerFaltasxEmpleado(id);
			boolean si = false;
			for (int i = 0; i < listaFaltas.size(); i++) {
				
				System.out.println("i: "+listaFaltas.get(i));
				System.out.println("fec: |"+fechaFalta.contains(listaFaltas.get(i)));
				if(fechaFalta.contains(listaFaltas.get(i))) {
					System.out.println("fec: |"+fechaFalta.contains(listaFaltas.get(i)));
					 si = true;
					 System.out.println("-: "+si);
				}
			}
			
			
			if(si==true) {
				JOptionPane.showMessageDialog(null,"La fecha ya está guardada como falta injustificada");
				return resultado;
			}else {
				si = false;
				System.out.println("x: "+si);
			}
			
			
			
			/**/


		if(fila>=0) {
			String dependencia =  tableReporteVacaciones.getValueAt(fila, 6).toString();
			String idPuesto= tableReporteVacaciones.getValueAt(fila, 7).toString();
			sqlInsert="INSERT INTO dbo.FALTAS_EMPLEADO (ID_EMPLEADO,FECHA_FALTA,PERIODO,TIPO_NOMINA,ID_DEPENDENCIA,ID_PUESTO)" 
					+ ""
					+ "VALUES ("+ id +","+"convert(datetime,'"+ outputText+"',101)"+","+FormularioPrincipal.lblPeriodoNumero.getText()+","
					+ ""+lblTipoNominaOculta.getText()+","+dependencia+","+idPuesto+")";
//			sqlInsert="";
			System.out.println("INSERT FALTA: "+sqlInsert);
		}
		try {
			conn = nych.datasource.getConnection();
			PreparedStatement pps = conn.prepareStatement(sqlInsert);
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
				conn.close();
			} catch (SQLException ep) {
				ep.printStackTrace();
				StringWriter errors = new StringWriter();
				ep.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: "+ errors );
				//JOptionPane.showMessageDialog(null, ep, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
			}
		}
		//dispose();
		}else {
			JOptionPane.showMessageDialog(null,"No ha seleccionado ninguna fecha para aplicar falta");
		}
		return resultado;

	}
	
	public static ArrayList<String> obtenerFaltasxEmpleado(String id){
		PoolNYCH nych = new PoolNYCH();
		Connection connect = null;
		ResultSet resultSet = null;
		String sqlSelectEjercicio = "select fecha_falta from FALTAS_EMPLEADO where ID_EMPLEADO = '"+id+"' and periodo = '"+FormularioPrincipal.lblPeriodoNumero.getText()+"' and TIPO_NOMINA='"+FormularioPrincipal.lblIdTipoNomina.getText()+"'";
//		System.out.println("sqlSelectEjercicio: " + sqlSelectEjercicio);
		ArrayList<String> lista = new ArrayList<String>();
		try {
			connect = nych.datasource.getConnection();
			Statement st = connect.createStatement();
			resultSet = st.executeQuery(sqlSelectEjercicio);
			while (resultSet.next()) {
				lista.add(resultSet.getString(1));
			}
		} catch (Exception exp) {
			exp.printStackTrace();
			StringWriter errors = new StringWriter();
			exp.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: "+ errors );
			JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
		} finally {
			try {
				connect.close();
			} catch (SQLException ep) {
				ep.printStackTrace();
				StringWriter errors = new StringWriter();
				ep.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: "+ errors );
				JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
			}
		}
		return lista;
	}
	
	
	
	public void  verFaltas(){
		DefaultTableModel modelo = new DefaultTableModel()
		{

			public boolean isCellEditable(int row, int column) {
				//return super.isCellEditable(row, column);
				if(column==7){
					return true;
				}else {
					return false;
				}
			}
		};
		modelo.addColumn("CLAVE");
		modelo.addColumn("FECHA MVTO");
		modelo.addColumn("ENTRADA TURNO");
		modelo.addColumn("ENTRADA");
		modelo.addColumn("SALIDA");
		modelo.addColumn("SALIDA TURNO");
		modelo.addColumn("INHABIL");
		modelo.addColumn("INCIDENCIA");
		modelo.addColumn("MOTIVO");
		modelo.addColumn("ESTACION");
		tableReporteChecadorMvtos.setModel(modelo);


		JTableHeader th = new JTableHeader();
		JTableHeader th1 = new JTableHeader();
		Color colorSilverLight=new Color(176, 196, 222);
		Color colorNegro=new Color(0, 0, 0);
		th = tableReporteChecadorMvtos.getTableHeader();
		th1 = tableReporteChecadorMvtos.getTableHeader();
		Font fuente = new Font("Arial", Font.BOLD, 14); 
		th.setFont(fuente); 
		th1.setFont(fuente); 
		th.setBackground(colorSilverLight);
		th1.setBackground(colorSilverLight);
		th.setForeground(colorNegro);
		th1.setForeground(colorNegro);

		TableColumnModel columnModel = tableReporteChecadorMvtos.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(60);
		columnModel.getColumn(1).setPreferredWidth(80);
		columnModel.getColumn(2).setPreferredWidth(100);
		columnModel.getColumn(3).setPreferredWidth(80);
		columnModel.getColumn(4).setPreferredWidth(80);
		columnModel.getColumn(5).setPreferredWidth(80);
		columnModel.getColumn(6).setPreferredWidth(80);
		columnModel.getColumn(7).setPreferredWidth(80);
		columnModel.getColumn(8).setPreferredWidth(80);
		columnModel.getColumn(9).setPreferredWidth(80);


		Connection con= null;
		PoolChecador checador = new PoolChecador();
		String datos[] = new String[10];
		
		Date fechaDe = dateChooserFechaDe.getDate();
		Date fechaHasta = dateChooserFechaHasta.getDate();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String sqlSelect="select clave,fechamovto,entradaturno,entrada,salida,salidaturno,inhabil,incidencia,motivo,estacion from movtos where clave ="+lblClaveEmpChecador.getText().trim()+ " and fechamovto between convert(datetime, '"+ formatter.format(fechaDe)+"', 101) and convert(datetime, '"+ formatter.format(fechaHasta)+"', 101) order by fechamovto";
		System.out.println("sql checador: "+ sqlSelect);
		try {
			con = checador.datasource.getConnection();
			Statement st = con.createStatement();
			ResultSet resultSet = st.executeQuery(sqlSelect);
			while(resultSet.next()) {
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
				modelo.addRow(datos);
				//System.out.println("1: "+datos[0]+" "+datos[1].trim()+" "+datos[2]+" "+datos[3]+" "+datos[4]+" "+datos[5]+" "+datos[6]+" "+datos[7]+" "+datos[8]+" "+datos[9]+" "+datos[10]);
			}//FIN DEL WHILE
			tableReporteChecadorMvtos.setModel(modelo);

			btnFaltas.setVisible(false);
			lblValidacion.setVisible(false);


		}catch(Exception e) {
			e.printStackTrace();
		}
	}



	public void redireccionarEmpleado() {
		int fila = tableReporteVacaciones.getSelectedRow();
		if(fila>=0) {
			lblIdEmp.setText(tableReporteVacaciones.getValueAt(fila, 0).toString());
			lblNombre.setText(tableReporteVacaciones.getValueAt(fila, 1).toString());
			lblApPat.setText(tableReporteVacaciones.getValueAt(fila, 2).toString());
			lblApMat.setText(tableReporteVacaciones.getValueAt(fila, 3).toString());
			lblPuesto.setText(tableReporteVacaciones.getValueAt(fila, 4).toString());
			lblIdUREmpMov.setText(tableReporteVacaciones.getValueAt(fila, 5).toString());
//			lblEmpURMov.setText(tableReporteVacaciones.getValueAt(fila, 6).toString());
//			lblIdPuesto.setText(tableReporteVacaciones.getValueAt(fila, 7).toString());
			
			//lblSalario.setText(tableReporteVacaciones.getValueAt(fila, 5).toString());

			//			if(tableReporteVacaciones.getValueAt(fila,7)==null) {
			//
			//				lblFechaIngreso.setText(null);
			//			}else {
			//
			//				lblFechaIngreso.setText(tableReporteVacaciones.getValueAt(fila, 7).toString());
			//
			//			}
			//			
		}else {
			JOptionPane.showMessageDialog(null, "Registro no Seleccionado");
		}
	}

	public void mostrarEmpleados(String valor) {
		DefaultTableModel modelo = new DefaultTableModel()
		{

			public boolean isCellEditable(int row, int column) {
				//return super.isCellEditable(row, column);
				if(column==7){
					return true;
				}else {
					return false;
				}
			}
		};
		modelo.addColumn("ID");
		modelo.addColumn("NOMBRE");
		modelo.addColumn("APELLIDO PATERNO");
		modelo.addColumn("APELLIDO MATERNO");
		modelo.addColumn("PUESTO");
		modelo.addColumn("UNIDAD_RESPONSABLE");
		modelo.addColumn("ID_UNIDAD_RESPONSABLE");
		modelo.addColumn("ID_PUESTO");
		
		tableReporteVacaciones.setModel(modelo);

		JTableHeader th = new JTableHeader();
		JTableHeader th1 = new JTableHeader();
		Color colorSilverLight=new Color(176, 196, 222);
		Color colorNegro=new Color(0, 0, 0);
		th = tableReporteVacaciones.getTableHeader();
		th1 = tableReporteVacaciones.getTableHeader();
		Font fuente = new Font("Arial", Font.BOLD, 14); 
		th.setFont(fuente); 
		th1.setFont(fuente); 
		th.setBackground(colorSilverLight);
		th1.setBackground(colorSilverLight);
		th.setForeground(colorNegro);
		th1.setForeground(colorNegro);

		TableColumnModel columnModel = tableReporteVacaciones.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(50);
		columnModel.getColumn(1).setPreferredWidth(200);
		columnModel.getColumn(2).setPreferredWidth(200);
		columnModel.getColumn(3).setPreferredWidth(200);
		columnModel.getColumn(4).setPreferredWidth(200);
		columnModel.getColumn(5).setPreferredWidth(200);
		columnModel.getColumn(6).setPreferredWidth(200);
		columnModel.getColumn(7).setPreferredWidth(200);


		String sqlSelect="";
		String sqlSelectUR="";
			sqlSelect = "select dbo.EMPLEADOS.clave,dbo.EMPLEADOS.NOMBRE,dbo.EMPLEADOS.APELLIDO_PATERNO,dbo.EMPLEADOS.APELLIDO_MATERNO,DBO.PUESTOS.NOMBRE_PUESTO,DBO.EMPLEADOS.ID_UNIDAD,DBO.PUESTOS.NO_PUESTO from dbo.EMPLEADOS left join dbo.PUESTOS on dbo.EMPLEADOS.ID_PUESTO = dbo.PUESTOS.NO_PUESTO  WHERE DBO.EMPLEADOS.ELIMINAR_LOGICO='"+1+"' and clave="+valor +"";
			sqlSelectUR = "SELECT DBO.DEPENDENCIAS.UNIDAD_RESPONSABLE FROM dbo.EMPLEADOS left JOIN DBO.DEPENDENCIAS ON DBO.EMPLEADOS.ID_UNIDAD = DBO.DEPENDENCIAS.ID_UNIDAD WHERE DBO.EMPLEADOS.ELIMINAR_LOGICO='"+1+"' and clave="+valor +"order by dbo.empleados.id_puesto";
		PoolNYCH nych = new PoolNYCH();
		Connection con =null;
		Statement st;
		ResultSet resultSet;
		
		Connection conUR =null;
		Statement stUR;
		ResultSet resultSetUR;
		Object datos[] = new String[8];
		try {
			con = nych.datasource.getConnection();
			conUR = nych.datasource.getConnection();
			st = con.createStatement();
			stUR = conUR.createStatement();
			resultSet = st.executeQuery(sqlSelect);
			resultSetUR = stUR.executeQuery(sqlSelectUR);
			while(resultSet.next() && resultSetUR.next()) {
				datos[0] = resultSet.getString(1);
				datos[1] = resultSet.getString(2);
				datos[2] = resultSet.getString(3);
				datos[3] = resultSet.getString(4);
				datos[4] = resultSet.getString(5);
				datos[5] = resultSetUR.getString(1);
				datos[6] = resultSet.getString(6);
				datos[7] = resultSet.getString(7);
				modelo.addRow(datos);
			}
		
//		String sqlSelect="";
//		sqlSelect = "Select dbo.empleados.ID_EMPLEADO,dbo.empleados.NOMBRE,dbo.empleados.APELLIDO_PATERNO,dbo.empleados.APELLIDO_MATERNO,dbo.puestos.NOMBRE_PUESTO,dbo.puestos.SALARIO,dbo.puestos.no_puesto from dbo.empleados left join dbo.puestos on dbo.empleados.ID_puesto = dbo.puestos.no_puesto WHERE DBO.EMPLEADOS.ELIMINAR_LOGICO='"+1+"' and id_empleado="+valor +" order by id_puesto ";
//		//System.out.println(sqlSelect);
//		Connection con= null;
//		PoolNYCH nych = new PoolNYCH();
//		String datos[] = new String[8];
//		try {
//			con = nych.datasource.getConnection();
//			Statement st = con.createStatement();
//			ResultSet resultSet = st.executeQuery(sqlSelect);
//			while(resultSet.next()) {
//				datos[0] = resultSet.getString(1);
//				datos[1] = resultSet.getString(2);
//				datos[2] = resultSet.getString(3);
//				datos[3] = resultSet.getString(4);
//				datos[4] = resultSet.getString(5);
//				datos[5] = resultSet.getString(6);
//				datos[6] = resultSet.getString(7);
//				modelo.addRow(datos);
//			}//FIN DEL WHILE
			//
//						rowSortere = new TableRowSorter(modelo);
//						tableReporteVacaciones.setRowSorter(rowSortere);

			tableReporteVacaciones.setModel(modelo);
		}catch (SQLException el) {
			el.printStackTrace();
			StringWriter errors = new StringWriter();
			el.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: "+ errors );
			//JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
			JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
		}finally {
			try {
				con.close();
			} catch (SQLException ep) {
				ep.printStackTrace();
				StringWriter errors = new StringWriter();
				ep.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: "+ errors );
				//JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
				JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
			}
		}//FIN DEL FINALLY
	}
	
	public void mostrarEmpleadosDesdeIconoCalcular(String valor) {
		DefaultTableModel modelo = new DefaultTableModel()
		{

			public boolean isCellEditable(int row, int column) {
				//return super.isCellEditable(row, column);
				if(column==7){
					return true;
				}else {
					return false;
				}
			}
		};
		modelo.addColumn("CLAVE");
		modelo.addColumn("NOMBRE");
		modelo.addColumn("APELLIDO PATERNO");
		modelo.addColumn("APELLIDO MATERNO");
		modelo.addColumn("PUESTO");
		modelo.addColumn("UNIDAD_RESPONSABLE");
		modelo.addColumn("ID PUESTO");
		modelo.addColumn("ID DEPENDENCIA");
//		modelo.addColumn("SALARIO");
//		modelo.addColumn("FECHA INGRESO");
//		modelo.addColumn("CURP");
//		modelo.addColumn("RFC");
		//modelo.addColumn("AYUDA A DESPENSA");


		tableReporteVacaciones.setModel(modelo);
		tableReporteVacaciones.setBackground(Color.WHITE);

		JTableHeader th = new JTableHeader();
		Color colorSilverLight=new Color(176, 196, 222);
		Color colorNegro=new Color(0, 0, 0);
		th = tableReporteVacaciones.getTableHeader();
		Font fuente = new Font("Arial", Font.BOLD, 14); 
		th.setFont(fuente); 
		th.setBackground(colorSilverLight);
		th.setForeground(colorNegro);


		TableColumnModel columnModel = tableReporteVacaciones.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(80);
		columnModel.getColumn(1).setPreferredWidth(200);
		columnModel.getColumn(2).setPreferredWidth(200);
		columnModel.getColumn(3).setPreferredWidth(200);
		columnModel.getColumn(4).setPreferredWidth(200);
		columnModel.getColumn(5).setPreferredWidth(300);
		columnModel.getColumn(6).setPreferredWidth(50);
		columnModel.getColumn(7).setPreferredWidth(50);
//		columnModel.getColumn(8).setPreferredWidth(200);
//		columnModel.getColumn(9).setPreferredWidth(100);
//		columnModel.getColumn(10).setPreferredWidth(100);
//		columnModel.getColumn(11).setPreferredWidth(200);


		String sqlSelect="";
		sqlSelect = "SELECT DBO.EMPLEADOS.CLAVE,DBO.EMPLEADOS.NOMBRE,DBO.EMPLEADOS.APELLIDO_PATERNO,DBO.EMPLEADOS.APELLIDO_MATERNO,\r\n"
				+ "dbo.puestos.nombre_puesto,DBO.DEPENDENCIAS.UNIDAD_RESPONSABLE,dbo.dependencias.id_unidad,dbo.puestos.no_puesto\r\n"
				+ "from empleados\r\n"
				+ "left JOIN DBO.PUESTOS ON DBO.EMPLEADOS.ID_PUESTO = DBO.PUESTOS.no_puesto\r\n"
				+ "LEFT JOIN DBO.DEPENDENCIAS ON DBO.EMPLEADOS.ID_UNIDAD = DBO.DEPENDENCIAS.ID_UNIDAD\r\n"
				+ "LEFT JOIN DBO.EJERCICIOS ON DBO.EMPLEADOS.ID_EJERCICIOS = DBO.EJERCICIOS.ID_EJERCICIOS\r\n"
				/*+ "LEFT JOIN DBO.CALCULO_NOMINA ON DBO.EMPLEADOS.clave = DBO.CALCULO_NOMINA.id_empleado\r\n"*/
				+ "WHERE DBO.EMPLEADOS.id_ejercicios = '" + valor + "' and ELIMINAR_LOGICO='1'  \r\n";//AND dbo.calculo_nomina.id_percepcion=26 falta nomina semanal en calculo nomina
//		System.out.println("sql faltas checador: " + sqlSelect);
		PoolNYCH nych = new PoolNYCH();				
		Connection con =null;
		String datos[] = new String[14];
		try {
			con = nych.datasource.getConnection();
			Statement st = con.createStatement();
			ResultSet resultSet = st.executeQuery(sqlSelect);
			while(resultSet.next()) {// && resultSetUR.next() && resultSetPu.next() && resultSetPer.next() && resultSetEjer.next()
				datos[0] = resultSet.getString(1);
				datos[1] = resultSet.getString(2);
				datos[2] = resultSet.getString(3);
				datos[3] = resultSet.getString(4);
				datos[4] = resultSet.getString(5);
				datos[5] = resultSet.getString(6);
				datos[6] = resultSet.getString(7);
				datos[7] = resultSet.getString(8);
//				datos[8] = resultSet.getString(9);
//				datos[9] = resultSet.getString(10);
//				datos[10] = resultSet.getString(11);
//				datos[11] = resultSet.getString(12);
				modelo.addRow(datos);
			}


			rowSorterer = new TableRowSorter(modelo);
			tableReporteVacaciones.setRowSorter(rowSorterer);
			
			tableReporteVacaciones.setModel(modelo);
		}catch (SQLException el) {
			el.printStackTrace();
			StringWriter errors = new StringWriter();
			el.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: "+ errors );
			//JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
			JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
		}finally {
			try {
				con.close();
			} catch (SQLException ep) {
				ep.printStackTrace();
				StringWriter errors = new StringWriter();
				ep.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: "+ errors );
				//JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
				JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
			}
		}//FIN DEL FINALLY
	}


	public void redireccionarEmpleadoChecador() {

		int fila = tableReporteChecador.getSelectedRow();
		if(fila>=0) {
			lblClaveEmpChecador.setText(tableReporteChecador.getValueAt(fila, 0).toString());
			lblNombreChecador.setText(tableReporteChecador.getValueAt(fila, 1).toString());
			lblApPatChecador.setText(tableReporteChecador.getValueAt(fila, 2).toString());
			lblApMatChecador.setText(tableReporteChecador.getValueAt(fila, 3).toString());
			lbldptoChecador.setText(tableReporteChecador.getValueAt(fila, 4).toString());
			lblTurnoChecador.setText(tableReporteChecador.getValueAt(fila, 5).toString());
			lblPuestoChecador.setText(tableReporteChecador.getValueAt(fila, 6).toString());
			

			//			if(tableReporteChecador.getValueAt(fila,7)==null) {
			//
			//				lblFechaIngreso.setText(null);
			//			}else {
			//
			//				lblFechaIngreso.setText(tableReporteChecador.getValueAt(fila, 7).toString());
			//
			//			}
			btnFaltas.setVisible(true);
		}else {
			JOptionPane.showMessageDialog(null, "Registro no Seleccionado");
		}
	}

	public void mostrarEmpleadosChecador() {
		DefaultTableModel modelo = new DefaultTableModel()
		{

			public boolean isCellEditable(int row, int column) {
				if(column==7){
					return true;
				}else {
					return false;
				}
			}
		};
		modelo.addColumn("CLAVE");
		modelo.addColumn("NOMBRE");
		modelo.addColumn("APELLIDO PATERNO");
		modelo.addColumn("APELLIDO MATERNO");
		modelo.addColumn("DEPARTAMENTO");
		modelo.addColumn("TURNO");
		modelo.addColumn("PUESTO");
		tableReporteChecador.setModel(modelo);

		JTableHeader th = new JTableHeader();
		JTableHeader th1 = new JTableHeader();
		Color colorSilverLight=new Color(176, 196, 222);
		Color colorNegro=new Color(0, 0, 0);
		th = tableReporteChecador.getTableHeader();
		th1 = tableReporteChecador.getTableHeader();
		Font fuente = new Font("Arial", Font.BOLD, 14); 
		th.setFont(fuente); 
		th1.setFont(fuente); 
		th.setBackground(colorSilverLight);
		th1.setBackground(colorSilverLight);
		th.setForeground(colorNegro);
		th1.setForeground(colorNegro);

		TableColumnModel columnModel = tableReporteChecador.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(50);
		columnModel.getColumn(1).setPreferredWidth(100);
		columnModel.getColumn(2).setPreferredWidth(100);
		columnModel.getColumn(3).setPreferredWidth(100);
		columnModel.getColumn(4).setPreferredWidth(150);
		columnModel.getColumn(5).setPreferredWidth(50);
		columnModel.getColumn(6).setPreferredWidth(100);


		String sqlSelect="";
		sqlSelect = "select empleados.clave,nombre,apellidopaterno,apellidomaterno,departamentos.descripcion,turnos.descripcion,puestos.descripcion from empleados \r\n" + 
				"inner join departamentos on empleados.departamento = departamentos.clave\r\n" + 
				"inner join puestos on empleados.puesto = puestos.clave\r\n" + 
				"inner join turnos on empleados.turno = turnos.clave";
		//System.out.println(sqlSelect);
		Connection con= null;
		PoolChecador checador = new PoolChecador();
		String datos[] = new String[7];
		try {
			con = checador.datasource.getConnection();
			Statement st = con.createStatement();
			ResultSet resultSet = st.executeQuery(sqlSelect);
			while(resultSet.next()) {
				datos[0] = resultSet.getString(1);
				datos[1] = resultSet.getString(2);
				datos[2] = resultSet.getString(3);
				datos[3] = resultSet.getString(4);
				datos[4] = resultSet.getString(5);
				datos[5] = resultSet.getString(6);
				datos[6] = resultSet.getString(7);
				modelo.addRow(datos);
			}//FIN DEL WHILE

			rowSortere = new TableRowSorter(modelo);
			tableReporteChecador.setRowSorter(rowSortere);

			tableReporteChecador.setModel(modelo);
		}catch (SQLException el) {
			el.printStackTrace();
			StringWriter errors = new StringWriter();
			el.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: "+ errors );
			//JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
			JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
		}finally {
			try {
				con.close();
			} catch (SQLException ep) {
				ep.printStackTrace();
				StringWriter errors = new StringWriter();
				ep.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: "+ errors );
				//JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
				JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
			}
		}//FIN DEL FINALLY
	}
	
	
	public void backMovimientos(String valor) {
		DefaultTableModel modelo = new DefaultTableModel()
		{

			public boolean isCellEditable(int row, int column) {
				//return super.isCellEditable(row, column);
				if(column==8){
					return true;
				}else {
					return false;
				}
			}
		};
		modelo.addColumn("ID");
		modelo.addColumn("NOMBRE");
		modelo.addColumn("APELLIDO PATERNO");
		modelo.addColumn("APELLIDO MATERNO");
		modelo.addColumn("PUESTO");
		modelo.addColumn("UNIDAD_RESPONSABLE");
		modelo.addColumn("ID_UNIDAD_RESPONSABLE");
		modelo.addColumn("ID_PUESTO");

		tableBuscEmple.setModel(modelo);
		tableBuscEmple.setBackground(Color.WHITE);

		JTableHeader th = new JTableHeader();
		Color colorSilverLight=new Color(176, 196, 222);
		Color colorNegro=new Color(0, 0, 0);
		th = tableBuscEmple.getTableHeader();
		Font fuente = new Font("Cooper Black", Font.BOLD, 14); 
		th.setFont(fuente); 
		th.setBackground(colorSilverLight);
		th.setForeground(colorNegro);

		
		TableColumnModel columnModel = tableBuscEmple.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(50);
		columnModel.getColumn(1).setPreferredWidth(200);
		columnModel.getColumn(2).setPreferredWidth(200);
		columnModel.getColumn(3).setPreferredWidth(200);
		columnModel.getColumn(4).setPreferredWidth(200);
		columnModel.getColumn(5).setPreferredWidth(200);
		columnModel.getColumn(6).setPreferredWidth(200);
		columnModel.getColumn(7).setPreferredWidth(200);


		String sqlSelect="";
		String sqlSelectUR="";
//		if(valor.equals("")) {
			//sqlSelect = "select * from dbo.empleados order by id_puesto";
			sqlSelect = "select dbo.EMPLEADOS.ID_EMPLEADO,dbo.EMPLEADOS.NOMBRE,dbo.EMPLEADOS.APELLIDO_PATERNO,dbo.EMPLEADOS.APELLIDO_MATERNO,DBO.PUESTOS.NOMBRE_PUESTO,DBO.EMPLEADOS.ID_UNIDAD,DBO.PUESTOS.NO_PUESTO from dbo.EMPLEADOS left join dbo.PUESTOS on dbo.EMPLEADOS.ID_PUESTO = dbo.PUESTOS.NO_PUESTO where dbo.empleados.id_empleado="+ valor +"order by dbo.EMPLEADOS.ID_PUESTO";
			sqlSelectUR = "SELECT DBO.DEPENDENCIAS.UNIDAD_RESPONSABLE FROM dbo.EMPLEADOS left JOIN DBO.DEPENDENCIAS ON DBO.EMPLEADOS.ID_UNIDAD = DBO.DEPENDENCIAS.ID_UNIDAD where dbo.empleados.id_empleado="+ valor +" order by dbo.empleados.id_puesto";
//		}else {
//			String atributo = null;
//			sqlSelect = "SELECT * FROM dbo.EMPLEADOS WHERE "+atributo+""+"='"+ valor +"' order by id_puesto"; 
//		}
		Connection con= null;
		Connection conUR= null;
		PoolNYCH nych = new PoolNYCH();
		Object datos[] = new String[8];
		try {
			con = nych.datasource.getConnection();
			conUR = nych.datasource.getConnection();
			Statement st = con.createStatement();
			Statement stUR = conUR.createStatement();
			ResultSet resultSet = st.executeQuery(sqlSelect);
			ResultSet resultSetUR = stUR.executeQuery(sqlSelectUR);
			while(resultSet.next() && resultSetUR.next()) {
				datos[0] = resultSet.getString(1);
				datos[1] = resultSet.getString(2);
				datos[2] = resultSet.getString(3);
				datos[3] = resultSet.getString(4);
				datos[4] = resultSet.getString(5);
				datos[5] = resultSetUR.getString(1);
				datos[6] = resultSet.getString(6);
				datos[7] = resultSet.getString(7);
				modelo.addRow(datos);
			}
//			rowSorter = new TableRowSorter(modelo);
//			tableBuscEmple.setRowSorter(rowSorter);
			
			tableBuscEmple.setModel(modelo);
		}catch (SQLException el) {
			el.printStackTrace();
			StringWriter errors = new StringWriter();
			el.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: "+ errors );
			//JOptionPane.showMessageDialog(null, el, "Error de conexion", JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
		}finally {
			try {
				con.close();
				conUR.close();
			} catch (SQLException ep) {
				ep.printStackTrace();
				StringWriter errors = new StringWriter();
				ep.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: "+ errors );
				//JOptionPane.showMessageDialog(null, ep, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
			}
		}
	}//fin del metodo
	
	
	
	public void redireccionarRegistroEmpPerp() {
		InternalFrameMovimientos internalFrameMovimientos = new InternalFrameMovimientos();
		int fila = tableReporteVacaciones.getSelectedRow();
		
		if(fila>=0) {
			
			
//			if() {
//				
//			}
//			internalFrameMovimientos.mostrarDatosFechasPeriodo("");
			
			internalFrameMovimientos.lblEmpIdPerp.setText(tableReporteVacaciones.getValueAt(fila, 0).toString());
			internalFrameMovimientos.lblEmplNombrePerp.setText(tableReporteVacaciones.getValueAt(fila, 1).toString());
			internalFrameMovimientos.lblEmpApPatPerp.setText(tableReporteVacaciones.getValueAt(fila, 2).toString());
			internalFrameMovimientos.lblEmpApMatPerp.setText(tableReporteVacaciones.getValueAt(fila, 3).toString());
			internalFrameMovimientos.lblPuestoEmpMov.setText(tableReporteVacaciones.getValueAt(fila, 4).toString());
			internalFrameMovimientos.lblEmpURMov.setText(tableReporteVacaciones.getValueAt(fila, 5).toString());		
			internalFrameMovimientos.lblIdUREmpMov.setText(tableReporteVacaciones.getValueAt(fila, 6).toString());
			internalFrameMovimientos.lblIdPuestoEmpMov.setText(tableReporteVacaciones.getValueAt(fila, 7).toString());
			
			int filaMvtos = tableReporteChecadorMvtos.getSelectedRow();
			if(filaMvtos>=0) {
				internalFrameMovimientos.lblclavechecador.setText(tableReporteChecadorMvtos.getValueAt(filaMvtos, 0).toString());
				internalFrameMovimientos.lblFechaMvto.setText(tableReporteChecadorMvtos.getValueAt(filaMvtos, 1).toString());
			}
			
			FormularioPrincipal.desktopPane.add(internalFrameMovimientos);
			internalFrameMovimientos.btnBuscarSalario.setEnabled(false);
			internalFrameMovimientos.btnBuscarValor.setEnabled(false);
			internalFrameMovimientos.btnAgregarListaNomina.setEnabled(false);
			internalFrameMovimientos.separatorNomrbeId.setVisible(true);
			internalFrameMovimientos.separator_3.setVisible(true);
			internalFrameMovimientos.separator_4.setVisible(true);
			internalFrameMovimientos.separator_5.setVisible(true);
			internalFrameMovimientos.lblClave.setVisible(true);
			internalFrameMovimientos.lblFechaDeFalta.setVisible(true);
			internalFrameMovimientos.lblAuxiliarSalario.setVisible(true);
			internalFrameMovimientos.lblSalarioAuxiliar.setVisible(true);
			internalFrameMovimientos.lblTituloChecador.setVisible(true);
			internalFrameMovimientos.show();
			internalFrameMovimientos.setBounds(0, 0, 1800, 870);
			internalFrameMovimientos.setFrameIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("edit.png"))));
			internalFrameMovimientos.setIconifiable(true);
			internalFrameMovimientos.setClosable(true);
			internalFrameMovimientos.setResizable(true);
			internalFrameMovimientos.setMaximizable(true);
			internalFrameMovimientos.setVisible(true);
			internalFrameMovimientos.toFront();

			internalFrameMovimientos.mostrarDatosPerpCalcConFaltas(lblIdEmp.getText());
			internalFrameMovimientos.calcularISRConFaltas();
			internalFrameMovimientos.calcularIMSSConFaltas();
			internalFrameMovimientos.calcularInfonavit();
			internalFrameMovimientos.mostrarDatosDedCalc(lblIdEmp.getText());
			internalFrameMovimientos.mostrarPercepciones();
			internalFrameMovimientos.mostrarDeducciones();
		}else {
			JOptionPane.showMessageDialog(null, "Registro no Seleccionado");
		}
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
}

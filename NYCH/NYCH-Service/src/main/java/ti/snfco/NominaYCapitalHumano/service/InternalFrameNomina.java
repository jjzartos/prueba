package ti.snfco.NominaYCapitalHumano.service;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

import org.slf4j.LoggerFactory;

import com.toedter.calendar.JDateChooser;
import javax.swing.border.MatteBorder;
import java.awt.SystemColor;

public class InternalFrameNomina extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(InternalFrameNomina.class);
	JTable tableReporteNomina = new JTable();
	JTable tableAnticipoNomina = new JTable();
	private JTextField textFieldBuscarSalario = new JTextField();
	TableRowSorter rowSortere;
	int IdBusquedaEmplee = 1;
	JLabel lblNombreAnticipoNomina = new JLabel("Nombre");
	JLabel lblApellidoPatAnticipoNom = new JLabel("Apellido Paterno");
	JLabel lblApellidoMatAnticipoNom = new JLabel("Apellido Materno");
	JLabel lblPuestoAnticipoNom = new JLabel("Puesto");
	JLabel lblSalarioAnticipoNom = new JLabel("Salario");
	JLabel lblvaloAnticipoNom = new JLabel("Introduce el Monto del Anticipo");
	JSeparator separatorAnticipoNom = new JSeparator();
	JTextField textFieldAnticipoNom = new JTextField();
	JLabel lblsimbolodinero = new JLabel("$");
	JLabel lblFechaInicio = new JLabel("Fecha Inicio");
	JLabel lblFechaFinal = new JLabel("Fecha Final");
	JButton btnCancelarAntNom = new JButton("Cancelar");;
	JButton btnGuardarAnticpoNom = new JButton("Guardar");
	JDateChooser dateChooserFechaInicioAntNom =  new JDateChooser();
	JDateChooser dateChooserFechaFinalAntNom =  new JDateChooser();
	JLabel lblSimboloDinero2 = new JLabel("$");
	JLabel lblIdEmpleadoAnticipoNom = new JLabel("Clave");
	JLabel lblIdPuestoAnticipoNom = new JLabel("IDPuesto");
	private final JSeparator separator = new JSeparator();
	private final JLabel lblicon = new JLabel();
	JPanel panelAntNom = new JPanel();
	JLabel lblDetalle = new JLabel("Detalle");
	JLabel lblFlecha = new JLabel("");
	private final JLabel lblFondo = new JLabel("");
	JLabel lbltiponominaOculta = new JLabel("New label");
	JProgressBar progressBar = new JProgressBar();
	private final JButton btnVerEmpleados = new JButton("Ver Empleados");

	public InternalFrameNomina() {
		setBounds(0, 0, 1501, 670);
		setVisible(true);
		setTitle("Anticipo de Nomina");
		getContentPane().setLayout(null);

		JPanel panelNomina = new JPanel();
		panelNomina.setBackground(SystemColor.controlHighlight);
		//panelNomina.setBackground(new Color(147,140,147));
		panelNomina.setBorder(new TitledBorder(null, "Reporte Anticipo Nomina", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(176, 196, 222)));
		panelNomina.setBounds(0, 0, 1971, 1051);
		panelNomina.setLayout(null);
		getContentPane().add(panelNomina);

//		JScrollPane scrollPane = new JScrollPane();
//		scrollPane.setBounds(23, 565, 1368, 131);
//		scrollPane.setViewportBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
//		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
//		scrollPane.setViewportView(tableAnticipoNomina);
//		panelNomina.add(scrollPane);

		JScrollPane scrollPaneSalario = new JScrollPane();
		scrollPaneSalario.setBounds(23, 106, 814, 365);
		scrollPaneSalario.setViewportBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		scrollPaneSalario.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneSalario.setViewportView(tableReporteNomina);
		panelNomina.add(scrollPaneSalario);

		JLabel lblNewLabel = new JLabel("Nombre Empleado(a):");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBounds(23, 22, 212, 14);
		panelNomina.add(lblNewLabel);
		textFieldBuscarSalario.setBackground(SystemColor.controlHighlight);
		textFieldBuscarSalario.setBorder(null);
		textFieldBuscarSalario.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				rowSortere.setRowFilter(RowFilter.regexFilter(textFieldBuscarSalario.getText().toUpperCase(), IdBusquedaEmplee));
			}
		});
		textFieldBuscarSalario.setBounds(60, 57, 308, 25);
		textFieldBuscarSalario.setColumns(10);
		panelNomina.add(textFieldBuscarSalario);

		JButton btnAceptarSalario = new JButton("Seleccionar");
		btnAceptarSalario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String datos[] = new String[13];
				ResultSet resulset = null;
				//getEmpleadoAnticipo(datos,resulset);
				redireccionarSalarioEmpleado(datos);

				lblvaloAnticipoNom.setVisible(true);
				textFieldAnticipoNom.setVisible(true);
				separatorAnticipoNom.setVisible(true);
				lblsimbolodinero.setVisible(true);
				lblFechaInicio.setVisible(true);
				lblFechaFinal.setVisible(true);
				dateChooserFechaInicioAntNom.setVisible(true);
				dateChooserFechaFinalAntNom.setVisible(true);
				btnGuardarAnticpoNom.setVisible(true);
				btnCancelarAntNom.setVisible(true);
				lblSimboloDinero2.setVisible(true);
				lblIdEmpleadoAnticipoNom.setVisible(true);
				panelAntNom.setVisible(true);
				lblDetalle.setVisible(true);
				lblFlecha.setVisible(true);
			}
		});
		btnAceptarSalario.setBounds(360, 479, 128, 30);
		btnAceptarSalario.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("accept.png"))));
		panelNomina.add(btnAceptarSalario);
		separator.setForeground(new Color(176, 196, 222));
		separator.setBackground(new Color(176, 196, 222));
		separator.setBounds(23, 83, 360, 2);

		panelNomina.add(separator);
		lblicon.setBounds(23, 57, 46, 28);
		lblicon.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("icons8_Search_32px_2.png"))));
		panelNomina.add(lblicon);
		panelAntNom.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));


		panelAntNom.setBounds(849, 106, 426, 396);
		panelNomina.add(panelAntNom);
		panelAntNom.setLayout(null);
		lblNombreAnticipoNomina.setBounds(60, 11, 247, 14);
		panelAntNom.add(lblNombreAnticipoNomina);
		lblNombreAnticipoNomina.setForeground(new Color(0, 0, 0));
		lblApellidoPatAnticipoNom.setBounds(10, 41, 282, 14);
		panelAntNom.add(lblApellidoPatAnticipoNom);
		lblApellidoPatAnticipoNom.setForeground(new Color(0, 0, 0));
		lblApellidoMatAnticipoNom.setBounds(10, 71, 297, 14);
		panelAntNom.add(lblApellidoMatAnticipoNom);
		lblApellidoMatAnticipoNom.setForeground(new Color(0, 0, 0));
		lblPuestoAnticipoNom.setBounds(58, 96, 292, 14);
		panelAntNom.add(lblPuestoAnticipoNom);
		lblPuestoAnticipoNom.setForeground(new Color(0, 0, 0));
		lblSalarioAnticipoNom.setBounds(19, 126, 297, 14);
		panelAntNom.add(lblSalarioAnticipoNom);
		lblSalarioAnticipoNom.setForeground(new Color(0, 0, 0));
		textFieldAnticipoNom.setBounds(20, 206, 194, 25);
		panelAntNom.add(textFieldAnticipoNom);
		textFieldAnticipoNom.setColumns(10);
		lblvaloAnticipoNom.setBounds(10, 181, 186, 14);
		panelAntNom.add(lblvaloAnticipoNom);
		lblvaloAnticipoNom.setForeground(new Color(0, 0, 0));
		separatorAnticipoNom.setBounds(10, 168, 282, 2);
		panelAntNom.add(separatorAnticipoNom);
		lblsimbolodinero.setBounds(10, 126, 46, 14);
		panelAntNom.add(lblsimbolodinero);
		lblsimbolodinero.setForeground(new Color(0, 0, 0));
		lblFechaInicio.setBounds(10, 242, 85, 14);
		panelAntNom.add(lblFechaInicio);
		lblFechaInicio.setForeground(new Color(0, 0, 0));
		lblFechaFinal.setBounds(163, 242, 85, 14);
		panelAntNom.add(lblFechaFinal);
		lblFechaFinal.setForeground(new Color(0, 0, 0));
		dateChooserFechaInicioAntNom.setBounds(10, 268, 143, 25);
		panelAntNom.add(dateChooserFechaInicioAntNom);
		dateChooserFechaFinalAntNom.setBounds(163, 268, 143, 25);
		panelAntNom.add(dateChooserFechaFinalAntNom);
		btnGuardarAnticpoNom.setBounds(85, 342, 128, 30);
		panelAntNom.add(btnGuardarAnticpoNom);

		btnGuardarAnticpoNom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					insertarAnticipoNomina();
					
					textFieldAnticipoNom.setText(null);
					dateChooserFechaInicioAntNom.setDate(null);
					dateChooserFechaFinalAntNom.setDate(null);
					
					
					
					dispose();
				}catch(Exception es){
					es.printStackTrace();
				}
			}
		});
		btnGuardarAnticpoNom.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("accept.png"))));
		btnCancelarAntNom.setBounds(222, 342, 128, 30);
		panelAntNom.add(btnCancelarAntNom);

		btnCancelarAntNom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});


		btnCancelarAntNom.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("delete.png"))));
		lblSimboloDinero2.setBounds(10, 211, 46, 14);
		panelAntNom.add(lblSimboloDinero2);
		lblSimboloDinero2.setForeground(new Color(0, 0, 0));
		lblIdEmpleadoAnticipoNom.setBounds(10, 11, 46, 14);
		panelAntNom.add(lblIdEmpleadoAnticipoNom);
		lblIdEmpleadoAnticipoNom.setForeground(new Color(0, 0, 0));
		lblIdPuestoAnticipoNom.setBounds(10, 96, 46, 14);
		panelAntNom.add(lblIdPuestoAnticipoNom);
		lblIdPuestoAnticipoNom.setForeground(new Color(0, 0, 0));
		lblDetalle.setBounds(849, 81, 46, 14);

		panelNomina.add(lblDetalle);
		lblFlecha.setBounds(886, 73, 46, 20);
		lblFlecha.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("download.png"))));
		panelNomina.add(lblFlecha);

		lbltiponominaOculta.setBounds(389, 22, 219, 14);
		lbltiponominaOculta.setVisible(false);
		panelNomina.add(lbltiponominaOculta);
		
		btnVerEmpleados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarPuestoSalarioEmpleado(lbltiponominaOculta.getText());
				btnVerEmpleados.setEnabled(false);
				//						mostrarEmpleadoSiTieneAnticipo(lbltiponominaOculta.getText());	
			}
		});
		btnVerEmpleados.setBounds(392, 58, 149, 30);

		panelNomina.add(btnVerEmpleados);

		//		JProgressBar progressBar = new JProgressBar();
		//		progressBar.setBounds(585, 47, 164, 25);
		//		panelNomina.add(progressBar);


		//		progressBar.setIndeterminate(true);
		//		panelNomina.add(progressBar, BorderLayout.CENTER);
		//		panelNomina.add(new JLabel("Por favor espere..."), BorderLayout.PAGE_START);
		//		progressBar.setBounds(585, 47, 164, 25);
		//		panelNomina.add(progressBar);



		lblFondo.setBounds(0, 0, 1906, 983);
		lblFondo.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("walll.png"))));
		panelNomina.add(lblFondo);

	}


	public void mostrarPuestoSalarioEmpleado(String valor) {
		System.out.println("mostrarPuestoSalarioEmpleado");
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
		modelo.addColumn("ID_PUESTO");
		modelo.addColumn("PUESTO");
		modelo.addColumn("SALARIO");
		modelo.addColumn("DEPENDENCIA");
		modelo.addColumn("ID DEPENDENCIA");
		tableReporteNomina.setModel(modelo);

		JTableHeader th = new JTableHeader();
		JTableHeader th1 = new JTableHeader();
		Color colorSilverLight=new Color(176, 196, 222);
		Color colorNegro=new Color(0, 0, 0);
		th = tableReporteNomina.getTableHeader();
		th1 = tableReporteNomina.getTableHeader();
		Font fuente = new Font("Arial", Font.BOLD, 14); 
		th.setFont(fuente); 
		th1.setFont(fuente); 
		th.setBackground(colorSilverLight);
		th1.setBackground(colorSilverLight);
		th.setForeground(colorNegro);
		th1.setForeground(colorNegro);

		TableColumnModel columnModel = tableReporteNomina.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(80);
		columnModel.getColumn(1).setPreferredWidth(80);
		columnModel.getColumn(2).setPreferredWidth(100);
		columnModel.getColumn(3).setPreferredWidth(80);
		columnModel.getColumn(4).setPreferredWidth(80);
		columnModel.getColumn(5).setPreferredWidth(80);
		columnModel.getColumn(6).setPreferredWidth(80);
		columnModel.getColumn(7).setPreferredWidth(100);
		columnModel.getColumn(8).setPreferredWidth(100);


		String sqlSelect="";
		sqlSelect = "SELECT DBO.EMPLEADOS.CLAVE,DBO.EMPLEADOS.NOMBRE,DBO.EMPLEADOS.APELLIDO_PATERNO,DBO.EMPLEADOS.APELLIDO_MATERNO,\r\n"
				+ "dbo.puestos.no_puesto,dbo.puestos.nombre_puesto,dbo.puestos.salario,DBO.DEPENDENCIAS.UNIDAD_RESPONSABLE,DBO.DEPENDENCIAS.ID_UNIDAD\r\n"
				+ "from empleados\r\n"
				+ "left JOIN DBO.PUESTOS ON DBO.EMPLEADOS.ID_PUESTO = DBO.PUESTOS.no_puesto\r\n"
				+ "LEFT JOIN DBO.DEPENDENCIAS ON DBO.EMPLEADOS.ID_UNIDAD = DBO.DEPENDENCIAS.ID_UNIDAD\r\n"
				+ "LEFT JOIN DBO.EJERCICIOS ON DBO.EMPLEADOS.ID_EJERCICIOS = DBO.EJERCICIOS.ID_EJERCICIOS\r\n"
				+ "WHERE DBO.EMPLEADOS.id_ejercicios = '" + valor + "' and ELIMINAR_LOGICO='1' \r\n";//and dbo.empleados.clave=124
				System.out.println(sqlSelect);
		Connection con= null;
		PoolNYCH nych = new PoolNYCH();
		String datos[] = new String[9];
		try {
			con = nych.datasource.getConnection();
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
				modelo.addRow(datos);
			}//FIN DEL WHILE

			rowSortere = new TableRowSorter(modelo);
			tableReporteNomina.setRowSorter(rowSortere);

			tableReporteNomina.setModel(modelo);
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
	}//FIN DEL METODO

	public void mostrarEmpleadoSiTieneAnticipo(String valor) {
		//		DefaultTableModel modelo = new DefaultTableModel()
		//		{
		//			public boolean isCellEditable(int row, int column) {
		//				if(column==7){
		//					return true;
		//				}else {
		//					return false;
		//				}
		//			}
		//		};
		//		modelo.addColumn("CLAVE");
		//		modelo.addColumn("NOMBRE");
		//		modelo.addColumn("APELLIDO PATERNO");
		//		modelo.addColumn("APELLIDO MATERNO");
		//		modelo.addColumn("ID_PUESTO");
		//		modelo.addColumn("PUESTO");
		//		modelo.addColumn("SALARIO");
		//		modelo.addColumn("DEPENDENCIA");
		//		modelo.addColumn("ID DEPENDENCIA");
		//		modelo.addColumn("PERIODO");
		//		modelo.addColumn("ID DEDUCCION");
		//		modelo.addColumn("VALOR DEDUCCION");
		//		tableAnticipoNomina.setModel(modelo);
		//
		//		JTableHeader th = new JTableHeader();
		//		JTableHeader th1 = new JTableHeader();
		//		Color colorSilverLight=new Color(176, 196, 222);
		//		Color colorNegro=new Color(0, 0, 0);
		//		th = tableAnticipoNomina.getTableHeader();
		//		th1 = tableAnticipoNomina.getTableHeader();
		//		Font fuente = new Font("Arial", Font.BOLD, 14); 
		//		th.setFont(fuente); 
		//		th1.setFont(fuente); 
		//		th.setBackground(colorSilverLight);
		//		th1.setBackground(colorSilverLight);
		//		th.setForeground(colorNegro);
		//		th1.setForeground(colorNegro);
		//
		//		TableColumnModel columnModel = tableAnticipoNomina.getColumnModel();
		//		columnModel.getColumn(0).setPreferredWidth(80);
		//		columnModel.getColumn(1).setPreferredWidth(80);
		//		columnModel.getColumn(2).setPreferredWidth(100);
		//		columnModel.getColumn(3).setPreferredWidth(80);
		//		columnModel.getColumn(4).setPreferredWidth(80);
		//		columnModel.getColumn(5).setPreferredWidth(80);
		//		columnModel.getColumn(6).setPreferredWidth(80);
		//		columnModel.getColumn(7).setPreferredWidth(100);
		//		columnModel.getColumn(8).setPreferredWidth(100);
		//		columnModel.getColumn(9).setPreferredWidth(100);
		//		columnModel.getColumn(10).setPreferredWidth(100);
		//		columnModel.getColumn(11).setPreferredWidth(100);


		String sqlSelect="";
		//		sqlSelect = "select id_empleado,periodo from CALCULO_NOMINA where  ID_DEDUCCION = 12 and periodo = '" + FormularioPrincipal.lblPeriodoNumero.getText() + "'";//and dbo.empleados.clave=124
		sqlSelect ="SELECT DBO.EMPLEADOS.CLAVE,DBO.EMPLEADOS.NOMBRE,DBO.EMPLEADOS.APELLIDO_PATERNO,DBO.EMPLEADOS.APELLIDO_MATERNO,\r\n" + 
				"dbo.puestos.no_puesto,dbo.puestos.nombre_puesto,dbo.puestos.salario,DBO.DEPENDENCIAS.UNIDAD_RESPONSABLE,DBO.DEPENDENCIAS.ID_UNIDAD,\r\n" + 
				"DBO.CALCULO_NOMINA.PERIODO,DBO.CALCULO_NOMINA.ID_DEDUCCION,DBO.CALCULO_NOMINA.VALOR_DEDUCCION\r\n" + 
				"from empleados\r\n" + 
				"left JOIN DBO.PUESTOS ON DBO.EMPLEADOS.ID_PUESTO = DBO.PUESTOS.no_puesto\r\n" + 
				"LEFT JOIN DBO.DEPENDENCIAS ON DBO.EMPLEADOS.ID_UNIDAD = DBO.DEPENDENCIAS.ID_UNIDAD\r\n" + 
				"LEFT JOIN DBO.EJERCICIOS ON DBO.EMPLEADOS.ID_EJERCICIOS = DBO.EJERCICIOS.ID_EJERCICIOS\r\n" + 
				"LEFT JOIN DBO.CALCULO_NOMINA ON DBO.EMPLEADOS.CLAVE = DBO.CALCULO_NOMINA.ID_EMPLEADO\r\n" + 
				"WHERE DBO.EMPLEADOS.id_ejercicios = '"+valor+"' and ELIMINAR_LOGICO='1'  AND DBO.CALCULO_NOMINA.PERIODO = '"+ FormularioPrincipal.lblPeriodoNumero.getText()+"' AND DBO.CALCULO_NOMINA.ID_DEDUCCION=12";
		//		System.out.println("sqlSelectGetAnticipo: "+sqlSelect);
		Connection con= null;
		PoolNYCH nych = new PoolNYCH();
		String datos[] = new String[12];
		try {
			con = nych.datasource.getConnection();
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
				datos[10] = resultSet.getString(11);
				datos[11] = resultSet.getString(12);

				System.out.println("0: "+datos[0]);
				System.out.println("1: "+datos[1]);
				System.out.println("2: "+datos[2]);
				System.out.println("3: "+datos[3]);
				System.out.println("4: "+datos[4]);
				System.out.println("5: "+datos[5]);
				System.out.println("6: "+datos[6]);
				System.out.println("7: "+datos[7]);
				System.out.println("8: "+datos[8]);
				System.out.println("9: "+datos[9]);
				System.out.println("10: "+datos[10]);
				System.out.println("11: "+datos[11]);
				//				System.out.println("12: "+datos[12]);

				//getEmpleadoAnticipo(datos,resultSet);

			}//FIN DEL WHILE


			//			rowSortere = new TableRowSorter(modelo);
			//			tableAnticipoNomina.setRowSorter(rowSortere);
			//			tableAnticipoNomina.setModel(modelo);
		}catch (SQLException el) {
			el.printStackTrace();
			StringWriter errors = new StringWriter();
			el.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: "+ errors );
			JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
		}finally {
			try {
				con.close();
			} catch (SQLException ep) {
				ep.printStackTrace();
				StringWriter errors = new StringWriter();
				ep.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: "+ errors );
				JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
			}
		}//FIN DEL FINALLY
	}//FIN DEL METODO


	public void getEmpleadoAnticipo(String[] datos,ResultSet resultSet) {

		try {
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
			datos[11] = resultSet.getString(12);


			System.out.println("0-: "+datos[1]);
			System.out.println("1-: "+datos[2]);
			System.out.println("2-: "+datos[3]);
			System.out.println("3-: "+datos[4]);
			System.out.println("4-: "+datos[5]);
			System.out.println("5-: "+datos[6]);
			System.out.println("6-: "+datos[7]);
			System.out.println("7-: "+datos[8]);
			System.out.println("8-: "+datos[9]);
			System.out.println("9-: "+datos[10]);
			System.out.println("10-: "+datos[11]);
			System.out.println("11-: "+datos[12]);




			//redireccionarSalarioEmpleado(datos);


		}catch(Exception e) {
			e.printStackTrace();
		}



	}

	public void redireccionarSalarioEmpleado(String datos[]) {
		System.out.println("redireccionarSalarioEmpleado");
		int fila = tableReporteNomina.getSelectedRow();
		if(fila>=0) {

			//			int filaGetAnticipo = tableAnticipoNomina.getSelectedRow();
			//			String idEmpleado = tableAnticipoNomina.getValueAt(filaGetAnticipo, 0).toString();
			//			String periodo = tableAnticipoNomina.getValueAt(filaGetAnticipo, 9).toString();
			//			System.out.println("id: "+idEmpleado);
			//			System.out.println("periodo: "+periodo);

			//			System.out.println("0: "+datos[0]);
			//			System.out.println("1: "+datos[1]);
			//			System.out.println("2: "+datos[2]);
			//			System.out.println("3: "+datos[3]);
			//			System.out.println("4: "+datos[4]);
			//			System.out.println("5: "+datos[5]);
			//			System.out.println("6: "+datos[6]);
			//			System.out.println("7: "+datos[7]);
			//			System.out.println("8: "+datos[8]);
			//			System.out.println("9: "+datos[9]);
			//			System.out.println("10: "+datos[10]);
			//			System.out.println("11: "+datos[11]);
			//			System.out.println("12: "+datos[12]); 
			//			
			//			if(datos[11].equalsIgnoreCase(FormularioPrincipal.lblPeriodoNumero.getText())) {
			//				System.out.println("Existe una deduccion de aniticpo de nomina");
			//			}


			lblIdEmpleadoAnticipoNom.setText(tableReporteNomina.getValueAt(fila, 0).toString());
			lblNombreAnticipoNomina.setText(tableReporteNomina.getValueAt(fila, 1).toString());
			lblApellidoPatAnticipoNom.setText(tableReporteNomina.getValueAt(fila, 2).toString());
			lblApellidoMatAnticipoNom.setText(tableReporteNomina.getValueAt(fila, 3).toString());
			lblIdPuestoAnticipoNom.setText(tableReporteNomina.getValueAt(fila, 4).toString());
			lblPuestoAnticipoNom.setText(tableReporteNomina.getValueAt(fila, 5).toString());
			lblSalarioAnticipoNom.setText(tableReporteNomina.getValueAt(fila, 6).toString());
		}else {
			JOptionPane.showMessageDialog(null, "Registro no Seleccionado");
		}
	}



	public static ArrayList<Object> getListaNominaCatorcena() {
		PoolNYCH nych = new PoolNYCH();
		Connection connect = null;
		ResultSet resultSet = null;
		Statement st=null;
		ArrayList<Object> listaNominaCatorcena = new ArrayList<Object>();
		String sqlDatosISR = "select * from NOMINA_CATORCENAS";
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
				listaNominaCatorcena.add(resultSet.getString(2));
				listaNominaCatorcena.add(resultSet.getInt(3));
				listaNominaCatorcena.add(resultSet.getInt(4));
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
		return listaNominaCatorcena;

	}

	public void pruebaButton() {


		ArrayList<Object> listaNominaCatorcena = new ArrayList<Object>();
		listaNominaCatorcena = getListaNominaCatorcena();
		System.out.println("*********");
		for(int i = 0; i<listaNominaCatorcena.size();i++){
			System.out.println("indice fecha catorcena: "+ i + " |valor Catorcena: " + listaNominaCatorcena.get(i));
		}
		System.out.println("*********");

		Object fechaUno = listaNominaCatorcena.get(0);
		System.out.println("FechaUno: " +fechaUno);
		Object fechaFinal =listaNominaCatorcena.get(75);
		System.out.println("FechaFinal: " +fechaFinal);

		System.out.println("*********");

		//		DefaultTableModel tmO = (DefaultTableModel) tableFechaInicio.getModel();
		//		//JOptionPane.showMessageDialog(null, "tableMovEmpPerp: "+ tableMovEmpPerp.getRowCount());
		//		//DefaultTableModel tmD = (DefaultTableModel) tableMovEmpNewDed.getModel();
		//		//JOptionPane.showMessageDialog(null, "tableMovEmpNewPerp: "+ tableMovEmpNewPerp.getRowCount());
		//		int fila = tableFechaInicio.getSelectedRow();
		//		//JOptionPane.showMessageDialog(null, "filaSeleccionada: "+ fila);
		//		try {
		//			if (fila != -1) {
		//				//int nColumns = tableMovAgr.getColumnCount();
		//				Object[] filaSeleccionada = {
		//						tmO.getValueAt(fila, 0), 
		//						tmO.getValueAt(fila, 1),
		//						tmO.getValueAt(fila, 2),
		//						//tmO.getValueAt(fila, 3),
		//				};
		//				System.out.println("fecha Catorcena: "+ tmO.getValueAt(fila, 0));
		//				System.out.println("Numero de catorcena: "+ tmO.getValueAt(fila, 1));
		//				System.out.println("año: "+ tmO.getValueAt(fila, 2));
		//				for (int i = 0; i < filaSeleccionada.length; ++i)
		//					filaSeleccionada[i] = tmO.getValueAt(fila, i);
		//			}else {
		//				JOptionPane.showMessageDialog(null, "Debe seleccionar una percepción","Advertencia", JOptionPane.WARNING_MESSAGE );
		//			}
		//		}catch(Exception exx) {
		//			exx.printStackTrace();
		//		}
		//
		//		System.out.println("-------");
		//		DefaultTableModel tmOFechaFinal = (DefaultTableModel) tableFechaFinal.getModel();
		//		//JOptionPane.showMessageDialog(null, "tableMovEmpPerp: "+ tableMovEmpPerp.getRowCount());
		//		//DefaultTableModel tmD = (DefaultTableModel) tableMovEmpNewDed.getModel();
		//		//JOptionPane.showMessageDialog(null, "tableMovEmpNewPerp: "+ tableMovEmpNewPerp.getRowCount());
		//		int filaFechaFinal = tableFechaFinal.getSelectedRow();
		//		//JOptionPane.showMessageDialog(null, "filaSeleccionada: "+ fila);
		//		try {
		//			if (fila != -1) {
		//				//int nColumns = tableMovAgr.getColumnCount();
		//				Object[] filaSeleccionadaFechaFinal = {
		//						tmOFechaFinal.getValueAt(filaFechaFinal, 0), 
		//						tmOFechaFinal.getValueAt(filaFechaFinal, 1),
		//						tmOFechaFinal.getValueAt(filaFechaFinal, 2),
		//						//tmO.getValueAt(fila, 3),
		//				};
		//				System.out.println("fecha Catorcena: "+ tmOFechaFinal.getValueAt(filaFechaFinal, 0));
		//				System.out.println("Numero de catorcena: "+ tmOFechaFinal.getValueAt(filaFechaFinal, 1));
		//				System.out.println("año: "+ tmOFechaFinal.getValueAt(filaFechaFinal, 2));
		//				for (int i = 0; i < filaSeleccionadaFechaFinal.length; ++i)
		//					filaSeleccionadaFechaFinal[i] = tmOFechaFinal.getValueAt(filaFechaFinal, i);
		//			}else {
		//				JOptionPane.showMessageDialog(null, "Debe seleccionar una percepción","Advertencia", JOptionPane.WARNING_MESSAGE );
		//			}
		//		}catch(Exception exx) {
		//			exx.printStackTrace();
		//		}

		System.out.println("-------------");
		//		if(fechaUno.equals(listaNominaCatorcena.get(0))) {
		//			Object numeroCatorcenaUno,numeroCatorcenaDos,numeroCatorcenaTres,numeroCatorcenaCuatro,numeroCatorcenaCinco,numeroCatorcenaSeis,numeroCatorcenasiete;
		//			numeroCatorcenaUno = listaNominaCatorcena.get(0);
		//			numeroCatorcenaDos = listaNominaCatorcena.get(3);
		//			numeroCatorcenaTres = listaNominaCatorcena.get(6);
		//			numeroCatorcenaUno = 1;numeroCatorcenaDos=2;numeroCatorcenaTres=3;
		//			System.out.println("numeroCatorcena1: " + numeroCatorcenaUno);
		//			System.out.println("numeroCatorcena2: " + numeroCatorcenaDos);
		//			System.out.println("numeroCatorcena3: " + numeroCatorcenaTres);
		//
		//
		//		}

		//		if(tmO.getValueAt(fila, 1).equals("25")) {
		//			System.out.println("aqui");
		//			Object uno = listaNominaCatorcena.get(1);
		//			System.out.println("uno:" + uno);
		//		}


		//		int idx = 0;
		//		for (Object o : listaNominaCatorcena) {
		//		   
		//		   idx++;
		//		}

		//		for(int i=1;i<;i++) {
		//			System.out.println("aqui: " + i);
		//		}


		Calendar cumple = Calendar.getInstance();
		int semana = cumple.get(Calendar.WEEK_OF_YEAR);
		System.out.println("semana: " + semana); 

		dateChooserFechaInicioAntNom.getDate();
		System.out.println("."+ dateChooserFechaInicioAntNom);
	}

	public void prueba1() {

		//		Date date = new Date();
		//		Calendar calendar = Calendar.getInstance();
		//		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		//		calendar.setMinimalDaysInFirstWeek(7);
		//		calendar.setTime(date);
		//		int numberWeekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);



		int numberWeekOfYear = dateChooserFechaInicioAntNom.getCalendar().get(Calendar.WEEK_OF_YEAR);
		System.out.println("fecha seleccionada: "+ dateChooserFechaInicioAntNom.getDate());
		int numberWeekOfYear1 = dateChooserFechaFinalAntNom.getCalendar().get(Calendar.WEEK_OF_YEAR);
		System.out.println("fecha seleccionada: "+ dateChooserFechaFinalAntNom.getDate());
		System.out.println("-------------");
		System.out.println("Es la " +numberWeekOfYear+ " semana of the year");
		System.out.println("Es la " +numberWeekOfYear/2+ " catorcena of the year");
		System.out.println("Es la " +numberWeekOfYear1+ " semana of the year");
		System.out.println("Es la " +numberWeekOfYear1/2+ " catorcena of the year");
		System.out.println("-------------");


		for(int i=numberWeekOfYear/2;i<numberWeekOfYear1/2;i++) {
			//System.out.println("catorcenas a pagar: " + i);
			System.out.println("parcialidades: " + i);
		}
	}


	public int insertarAnticipoNomina() {
		
		int claveInternaPercepcion=40;	
		int claveDeduccionAnticipoNomina=12;
		double valorClaveInternaPercepcion=0.0;
		PoolNYCH nych = new PoolNYCH();
		Connection conAnticipoNomina =null;
		int resultado = 0;
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date diaHoy = Calendar.getInstance().getTime();
		int fila = tableReporteNomina.getSelectedRow();
		String dependencia =  tableReporteNomina.getValueAt(fila, 8).toString();


		int numeroSemananaInicial = dateChooserFechaInicioAntNom.getCalendar().get(Calendar.WEEK_OF_YEAR);
		System.out.println("fecha seleccionada Inicial: "+ dateChooserFechaInicioAntNom.getDate());
		int numeroSemanaFinal = dateChooserFechaFinalAntNom.getCalendar().get(Calendar.WEEK_OF_YEAR);
		System.out.println("fecha seleccionada final: "+ dateChooserFechaFinalAntNom.getDate());
	
		
		System.out.println("-------------");
		System.out.println("Semana Inicial: " +numeroSemananaInicial+ " del año");
		System.out.println("Catorcena Inicial: " +numeroSemananaInicial/2+ " del año");
		System.out.println("Semana Final: " +numeroSemanaFinal+ " del año");
		System.out.println("Catorcena Final: " +numeroSemanaFinal/2+ " del año");
		System.out.println("-------------");

		int parcialidadesAPagar;
		System.out.println(parcialidadesAPagar=numeroSemananaInicial/2);
		System.out.println(parcialidadesAPagar<numeroSemanaFinal/2);
		System.out.println("catorcenas a pagar" );
		for(parcialidadesAPagar=numeroSemananaInicial/2;parcialidadesAPagar<numeroSemanaFinal/2;parcialidadesAPagar++) {
			System.out.println("parcialidades: " + parcialidadesAPagar);
			System.out.println("incremento: " + parcialidadesAPagar++);
		}
		System.out.println("-------------");
		System.out.println("parcialidades a pagar: " + parcialidadesAPagar);
		
		if(FormularioPrincipal.lblIsNomC.getText().equalsIgnoreCase("OK")) {
			System.out.println("-------------");
			System.out.println("Anticipo de nomina para tipo nomina catorcenal");
			
			int inicio = numeroSemananaInicial/2;
			int fin = numeroSemanaFinal/2;
			int res = (fin-inicio)+1;
			System.out.println("periodo inicio: " + inicio);
			System.out.println("periodo fin: " + fin);
			System.out.println("res(fin - inicio): " + res);
		

		String sqlInsert="INSERT INTO dbo.CALCULO_NOMINA (ID_EMPLEADO,ID_PERCEPCION,ID_DEDUCCION,VALOR_PERCEPCION,VALOR_DEDUCCION,ID_PUESTO,FECHA_CALCULO,ID_DEPENDENCIA,ID_TIPO_NOMINA,ESTATUS,PERIODO,PARCIALIDADES)" 
				+ ""
				+ "VALUES ("+ lblIdEmpleadoAnticipoNom.getText()+","+ claveInternaPercepcion +","+ claveDeduccionAnticipoNomina +","+ valorClaveInternaPercepcion +","
				+ ""+ Double.parseDouble(textFieldAnticipoNom.getText())/res +","+ lblIdPuestoAnticipoNom.getText() +",'"+formatter.format(diaHoy)+"','"+dependencia+"',"
				+ "'"+lbltiponominaOculta.getText()+"','"+1+"','"+FormularioPrincipal.lblPeriodoNumero.getText()+"','"+res+"')";
		System.out.println("anticipo por catorcena: "+Double.parseDouble(textFieldAnticipoNom.getText())/res);
//		String sqlInsert="";
		System.out.println("SQL INSERT ANTICIPO DE NOMINA: "+sqlInsert);
		
		try {
			conAnticipoNomina = nych.datasource.getConnection();
			PreparedStatement pps = conAnticipoNomina.prepareStatement(sqlInsert);
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
				conAnticipoNomina.close();
			} catch (SQLException ep) {
				ep.printStackTrace();
				StringWriter errors = new StringWriter();
				ep.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: "+ errors );
				//JOptionPane.showMessageDialog(null, ep, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
			}
		}
		}//fin del if
		
		if(FormularioPrincipal.lblIsNomS.getText().equalsIgnoreCase("OK")) {
			System.out.println("Anticipo de nomina para tipo nomina semanal");
			
			int inicio = numeroSemananaInicial;
			int fin = numeroSemanaFinal;
			int res = (fin-inicio)+1;
		

		String sqlInsert="INSERT INTO dbo.CALCULO_NOMINA (ID_EMPLEADO,ID_PERCEPCION,ID_DEDUCCION,VALOR_PERCEPCION,VALOR_DEDUCCION,ID_PUESTO,FECHA_CALCULO,ID_DEPENDENCIA,ID_TIPO_NOMINA,ESTATUS,PERIODO)" 
				+ ""
				+ "VALUES ("+ lblIdEmpleadoAnticipoNom.getText()+","+ claveInternaPercepcion +","+ claveDeduccionAnticipoNomina +","+ valorClaveInternaPercepcion +","
				+ ""+ Double.parseDouble(textFieldAnticipoNom.getText())/res +","+ lblIdPuestoAnticipoNom.getText() +",'"+formatter.format(diaHoy)+"','"+dependencia+"',"
				+ "'"+lbltiponominaOculta.getText()+"','"+1+"','"+FormularioPrincipal.lblPeriodoNumero.getText()+"')";
		System.out.println("SQL INSERT ANTICIPO DE NOMINA: "+sqlInsert);
		
		try {
			conAnticipoNomina = nych.datasource.getConnection();
			PreparedStatement pps = conAnticipoNomina.prepareStatement(sqlInsert);
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
				conAnticipoNomina.close();
			} catch (SQLException ep) {
				ep.printStackTrace();
				StringWriter errors = new StringWriter();
				ep.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: "+ errors );
				//JOptionPane.showMessageDialog(null, ep, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
			}
		}
		}
		return resultado;
	}

	public static ArrayList<String> buscarTipoNomina() {
		PoolNYCH nych = new PoolNYCH();
		Connection connect = null;
		ResultSet resultSet = null;
		String sqlSelectEjercicio = "SELECT DBO.TIPO_NOMINA.TIPO_NOMINA FROM DBO.TIPO_NOMINA";
		ArrayList<String> lista = new ArrayList<String>();
		try {
			connect = nych.datasource.getConnection();
			Statement st = connect.createStatement();
			resultSet = st.executeQuery(sqlSelectEjercicio);
		}catch(Exception exp) {
			exp.printStackTrace();
			StringWriter errors = new StringWriter();
			exp.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: "+ errors );
			//JOptionPane.showMessageDialog(null, exp, "Error de conexion", JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
		}try {
			while(resultSet.next()){
				lista.add(resultSet.getString("TIPO_NOMINA"));
			}
		}catch(Exception exc) {
			exc.printStackTrace();
			StringWriter errors = new StringWriter();
			exc.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: "+ errors );
			//JOptionPane.showMessageDialog(null, exc, "Error de conexion", JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
		}
		finally {
			try {
				connect.close();
			} catch (SQLException ep) {
				ep.printStackTrace();
				//JOptionPane.showMessageDialog(null, ep, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
			}
		}
		return lista;
	}

	//metodo para el tiempo de busqueda
	//Este es el evento que se ejecuta cuando un JFrame se carga
	public  void windowOpened(ActionEvent e){

		final long SLEEP_TIME = 1 * 1000;
		SwingWorker<Void, Void> mySwingWorker = new SwingWorker<Void, Void>(){
			protected Void doInBackground() throws Exception {


				mostrarPuestoSalarioEmpleado(lbltiponominaOculta.getText());
				mostrarEmpleadoSiTieneAnticipo(lbltiponominaOculta.getText());

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

}

package ti.snfco.NominaYCapitalHumano.service;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.Dialog.ModalityType;
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

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

import org.slf4j.LoggerFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.border.MatteBorder;
import java.awt.SystemColor;

public class InternalFramePensionAlimenticia extends JInternalFrame {

	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(InternalFramePensionAlimenticia.class);
	private static final long serialVersionUID = 1L;
	JTextField textFieldNombreEmp;
	JScrollPane scrollPaneEmp = new JScrollPane();
	JLabel lblNewLabel = new JLabel("Nombre del Empleado(a):");
	JTable tableReportePension = new JTable();
	TableRowSorter rowSortere;
	int IdBusquedaEmplee = 1;
	JLabel lblIdEmp = new JLabel("New label");
	JLabel lblApPat = new JLabel("New label");
	JLabel lblNombre = new JLabel("New label");
	JLabel lblApMat = new JLabel("New label");
	JLabel lblIdPuesto = new JLabel("New label");
	JLabel lblPuesto = new JLabel("New label");
	JLabel lblPrecio = new JLabel("$");
	JLabel lblSalario = new JLabel("New label");
	JButton btnGuardar = new JButton("Guardar");
	JButton btnCancelar = new JButton("Cancelar");
	JButton btnSeleccionar = new JButton("Seleccionar");
	InternalFrameNomina nomina = new InternalFrameNomina();
	JSeparator separator = new JSeparator();
	JLabel lblPago = new JLabel("¿Cuál es la cantidad a pagar de pensión alimenticia?");
	JFormattedTextField textFieldPagos = new JFormattedTextField();
	private final JSeparator separator_1 = new JSeparator();
	private final JLabel lblIcon = new JLabel();
	JPanel panelpensionAl = new JPanel();
	JLabel lblDetalle = new JLabel("Detalle");
	JLabel lblFlecha = new JLabel("");
	private final JLabel lblFondo = new JLabel("");
	JLabel lblTipoNominaOculta = new JLabel("New label");
	private final JButton btnVerEmpleados = new JButton("Ver Empleados");




	public InternalFramePensionAlimenticia() {
		setBounds(0, 0, 1501, 670);
		setVisible(true);
		setTitle("Pension Alimenticia");
		getContentPane().setLayout(null);

		JPanel panelPension = new JPanel();
		panelPension.setBackground(SystemColor.controlHighlight);
		panelPension.setBorder(new TitledBorder(null, "Reporte Pension Alimenticia", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(176, 196, 222)));
		panelPension.setBounds(0, 0, 1971, 1051);
		//panelPension.setBackground(new Color(147,140,147));
		getContentPane().add(panelPension);
		panelPension.setLayout(null);





		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBounds(34, 29, 184, 14);
		panelPension.add(lblNewLabel);

		textFieldNombreEmp = new JTextField();
		textFieldNombreEmp.setBorder(null);
		textFieldNombreEmp.setBackground(SystemColor.controlHighlight);
		textFieldNombreEmp.setBounds(75, 54, 314, 25);
		textFieldNombreEmp.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				rowSortere.setRowFilter(RowFilter.regexFilter(textFieldNombreEmp.getText().toUpperCase(), IdBusquedaEmplee));
			}
		});
		panelPension.add(textFieldNombreEmp);
		textFieldNombreEmp.setColumns(10);

		scrollPaneEmp.setBounds(34, 100, 887, 355);
		scrollPaneEmp.setViewportBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		scrollPaneEmp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneEmp.setViewportView(tableReportePension);
		panelPension.add(scrollPaneEmp);
		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//				ArrayList<String> listaEjercicio = new ArrayList<String>();
				//				listaEjercicio = nomina.buscarTipoNomina();
				//				comboBoxTipoNomina.addItem("Seleccione Una");
				//				for(int i = 0; i<listaEjercicio.size();i++){
				//					comboBoxTipoNomina.addItem(listaEjercicio.get(i));
				//				}

				redireccionarEmpleado();
				btnCancelar.setVisible(true);
				btnGuardar.setVisible(true);
				lblIdEmp.setVisible(true);
				lblNombre.setVisible(true);
				lblApPat.setVisible(true);
				lblApMat.setVisible(true);
				lblIdPuesto.setVisible(true);
				lblPuesto.setVisible(true);
				lblSalario.setVisible(true);
				lblPrecio.setVisible(true);
				separator.setVisible(true);
				lblPago.setVisible(true);
				//				lblTipoNomina.setVisible(true);
				//				comboBoxTipoNomina.setVisible(true);
				textFieldPagos.setVisible(true);
				panelpensionAl.setVisible(true);
				lblDetalle.setVisible(true);
				lblFlecha.setVisible(true);


			}
		});
		btnSeleccionar.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("accept.png"))));
		btnSeleccionar.setBounds(399, 466, 128, 30);
		panelPension.add(btnSeleccionar);
		separator_1.setBounds(34, 79, 334, 7);

		panelPension.add(separator_1);

		lblIcon.setBounds(34, 51, 46, 28);
		lblIcon.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("icons8_Search_32px_2.png"))));

		panelPension.add(lblIcon);
		panelpensionAl.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		panelpensionAl.setBounds(931, 100, 385, 355);

		panelPension.add(panelpensionAl);
		panelpensionAl.setLayout(null);
		lblIdEmp.setBounds(10, 11, 46, 14);
		panelpensionAl.add(lblIdEmp);

		lblIdEmp.setForeground(new Color(0, 0, 0));
		lblApPat.setBounds(10, 34, 276, 14);
		panelpensionAl.add(lblApPat);
		lblApPat.setForeground(new Color(0, 0, 0));
		lblNombre.setBounds(99, 11, 187, 14);
		panelpensionAl.add(lblNombre);
		lblNombre.setForeground(new Color(0, 0, 0));
		lblApMat.setBounds(10, 59, 276, 14);
		panelpensionAl.add(lblApMat);
		lblApMat.setForeground(new Color(0, 0, 0));
		lblIdPuesto.setBounds(10, 84, 46, 14);
		panelpensionAl.add(lblIdPuesto);
		lblIdPuesto.setForeground(new Color(0, 0, 0));
		lblPuesto.setBounds(66, 84, 294, 14);
		panelpensionAl.add(lblPuesto);
		lblPuesto.setForeground(new Color(0, 0, 0));
		lblPrecio.setBounds(10, 101, 46, 14);
		panelpensionAl.add(lblPrecio);
		lblPrecio.setForeground(new Color(0, 0, 0));
		lblSalario.setBounds(66, 101, 298, 14);
		panelpensionAl.add(lblSalario);
		lblSalario.setForeground(new Color(0, 0, 0));
		btnGuardar.setBounds(44, 297, 128, 30);
		panelpensionAl.add(btnGuardar);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertarPension();

				textFieldPagos.setText(null);

				dispose();
			}
		});
		btnGuardar.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("accept.png"))));
		btnCancelar.setBounds(182, 297, 128, 30);
		panelpensionAl.add(btnCancelar);

		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("delete.png"))));
		separator.setBounds(10, 145, 300, 2);
		panelpensionAl.add(separator);
		lblPago.setBounds(10, 171, 361, 14);
		panelpensionAl.add(lblPago);
		lblPago.setForeground(new Color(0, 0, 0));
		textFieldPagos.setBounds(10, 196, 170, 25);
		panelpensionAl.add(textFieldPagos);
		textFieldPagos.setColumns(10);
		textFieldPagos.setValue(new Double(0.0));

		JButton btnNewButton = new JButton("Guardar Archivo");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {




			}
		});
		btnNewButton.setBounds(182, 206, 128, 14);
		panelpensionAl.add(btnNewButton);
		lblDetalle.setBounds(938, 86, 46, 14);

		panelPension.add(lblDetalle);
		lblFlecha.setBounds(975, 78, 46, 20);
		lblFlecha.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("download.png"))));
		panelPension.add(lblFlecha);

		lblTipoNominaOculta.setBounds(380, 11, 318, 14);
		lblTipoNominaOculta.setVisible(false);
		panelPension.add(lblTipoNominaOculta);
		btnVerEmpleados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarEmpleados(lblTipoNominaOculta.getText());
				btnVerEmpleados.setEnabled(false);
			} 
		});
		btnVerEmpleados.setBounds(399, 51, 149, 30);

		panelPension.add(btnVerEmpleados);


		lblFondo.setBounds(0, 0, 1906, 983);
		lblFondo.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("walll.png"))));
		panelPension.add(lblFondo);

		//
		//		ArrayList<String> listaEjercicio = new ArrayList<String>();
		//		listaEjercicio = nomina.buscarTipoNomina();
		//		comboBoxTipoNomina.addItem("Seleccione Una");
		//		for(int i = 0; i<listaEjercicio.size();i++){
		//			comboBoxTipoNomina.addItem(listaEjercicio.get(i));
		//		}
	}


	public int insertarPension(){

		//		ArrayList<Object> listaNominaCatorcena = new ArrayList<Object>();
		//		listaNominaCatorcena = nomina.getListaNominaCatorcena();
		//		System.out.println("*********");
		//		for(int i = 0; i<listaNominaCatorcena.size();i++){
		//			System.out.println("indice fecha catorcena: "+ i + " |valor Catorcena: " + listaNominaCatorcena.get(i));
		//		}
		//		System.out.println("*********");


		//		int numberWeekOfYear = dateChooserfechaincial.getCalendar().get(Calendar.WEEK_OF_YEAR);
		//		System.out.println("fecha seleccionada: "+ dateChooserfechaincial.getDate());
		//		int numberWeekOfYear1 = dateChoosefechaFinal.getCalendar().get(Calendar.WEEK_OF_YEAR);
		//		System.out.println("fecha seleccionada: "+ dateChoosefechaFinal.getDate());
		//		System.out.println("-------------");
		//		System.out.println("today is " +numberWeekOfYear+ " semana of the year");
		//		System.out.println("today is " +numberWeekOfYear/2+ " quincena of the year");
		//		System.out.println("today is " +numberWeekOfYear1+ " semana of the year");
		//		System.out.println("today is " +numberWeekOfYear1/2+ " quincena of the year");
		//		System.out.println("-------------");
		//
		//		int parcialidadesAPagar;
		//		for(parcialidadesAPagar=numberWeekOfYear/2;parcialidadesAPagar<numberWeekOfYear1/2;parcialidadesAPagar++) {
		//			//System.out.println("catorcenas a pagar: " + );
		//			System.out.println("parcialidades: " + parcialidadesAPagar);
		//		}

		int claveInternaPercepcion=40;
		int claveDeduccionAnticipoNomina=25;
		double valorClaveInternaPercepcion=0.0;
		PoolNYCH nych = new PoolNYCH();
		Connection conPension =null;
		int resultado = 0;
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date diaHoy = Calendar.getInstance().getTime();
		Double pagos = (Double) textFieldPagos.getValue();
		int fila = tableReportePension.getSelectedRow();
		String dependencia =  tableReportePension.getValueAt(fila, 8).toString();


		String sqlInsert="INSERT INTO dbo.CALCULO_NOMINA (ID_EMPLEADO,ID_PERCEPCION,ID_DEDUCCION,VALOR_PERCEPCION,VALOR_DEDUCCION,ID_PUESTO,FECHA_CALCULO,ID_DEPENDENCIA,ID_TIPO_NOMINA,ESTATUS,PERIODO,PARCIALIDADES)" 
				+ ""
				+ "VALUES ("+ lblIdEmp.getText()+","+ claveInternaPercepcion +","+ claveDeduccionAnticipoNomina +","+ valorClaveInternaPercepcion +","
				+ ""+ pagos+","+ lblIdPuesto.getText() +",'"+formatter.format(diaHoy)+"','" + dependencia +"','"+lblTipoNominaOculta.getText() + "','"+1+"','"+FormularioPrincipal.lblPeriodoNumero.getText()+"',"+0+")";
		System.out.println("SQL INSERT PENSION ALIMENTICIA: "+sqlInsert);
		try {
			conPension = nych.datasource.getConnection();
			PreparedStatement pps = conPension.prepareStatement(sqlInsert);
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
				conPension.close();
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


	public void redireccionarEmpleado() {
		int fila = tableReportePension.getSelectedRow();
		if(fila>=0) {
			lblIdEmp.setText(tableReportePension.getValueAt(fila, 0).toString());
			lblNombre.setText(tableReportePension.getValueAt(fila, 1).toString());
			lblApPat.setText(tableReportePension.getValueAt(fila, 2).toString());
			lblApMat.setText(tableReportePension.getValueAt(fila, 3).toString());
			lblIdPuesto.setText(tableReportePension.getValueAt(fila, 4).toString());
			lblPuesto.setText(tableReportePension.getValueAt(fila, 5).toString());
			lblSalario.setText(tableReportePension.getValueAt(fila, 6).toString());
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
		modelo.addColumn("CLAVE");
		modelo.addColumn("NOMBRE");
		modelo.addColumn("APELLIDO PATERNO");
		modelo.addColumn("APELLIDO MATERNO");
		modelo.addColumn("ID_PUESTO");
		modelo.addColumn("PUESTO");
		modelo.addColumn("SALARIO");
		modelo.addColumn("DEPENDENCIA");
		modelo.addColumn("ID DEPENDENCIA");
		tableReportePension.setModel(modelo);

		JTableHeader th = new JTableHeader();
		JTableHeader th1 = new JTableHeader();
		Color colorSilverLight=new Color(176, 196, 222);
		Color colorNegro=new Color(0, 0, 0);
		th = tableReportePension.getTableHeader();
		th1 = tableReportePension.getTableHeader();
		Font fuente = new Font("Arial", Font.BOLD, 14); 
		th.setFont(fuente); 
		th1.setFont(fuente); 
		th.setBackground(colorSilverLight);
		th1.setBackground(colorSilverLight);
		th.setForeground(colorNegro);
		th1.setForeground(colorNegro);

		TableColumnModel columnModel = tableReportePension.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(80);
		columnModel.getColumn(1).setPreferredWidth(80);
		columnModel.getColumn(2).setPreferredWidth(100);
		columnModel.getColumn(3).setPreferredWidth(80);
		columnModel.getColumn(4).setPreferredWidth(80);
		columnModel.getColumn(5).setPreferredWidth(80);
		columnModel.getColumn(6).setPreferredWidth(80);
		columnModel.getColumn(7).setPreferredWidth(80);
		columnModel.getColumn(8).setPreferredWidth(80);


		//InternalFrameMovimientos movimientos = new InternalFrameMovimientos();
		String sqlSelect="";
		//System.out.println("id: " + movimientos.lblEmpIdPerp.getText());
		//sqlSelect = "select dbo.empleados.CLAVE,dbo.empleados.nombre,dbo.empleados.apellido_paterno,dbo.empleados.apellido_materno,dbo.PUESTOS.NO_PUESTO,dbo.PUESTOS.NOMBRE_PUESTO,dbo.PUESTOS.SALARIO from dbo.empleados left join dbo.puestos on dbo.empleados.ID_puesto = dbo.puestos.no_puesto WHERE DBO.EMPLEADOS.ELIMINAR_LOGICO='"+1+"'  order by id_puesto ";
		sqlSelect = "SELECT DBO.EMPLEADOS.CLAVE,DBO.EMPLEADOS.NOMBRE,DBO.EMPLEADOS.APELLIDO_PATERNO,DBO.EMPLEADOS.APELLIDO_MATERNO,\r\n"
				+ "dbo.puestos.no_puesto,dbo.puestos.nombre_puesto,dbo.puestos.salario,DBO.DEPENDENCIAS.UNIDAD_RESPONSABLE,dbo.dependencias.id_unidad\r\n"
				+ "from empleados\r\n"
				+ "left JOIN DBO.PUESTOS ON DBO.EMPLEADOS.ID_PUESTO = DBO.PUESTOS.no_puesto\r\n"
				+ "LEFT JOIN DBO.DEPENDENCIAS ON DBO.EMPLEADOS.ID_UNIDAD = DBO.DEPENDENCIAS.ID_UNIDAD\r\n"
				+ "LEFT JOIN DBO.EJERCICIOS ON DBO.EMPLEADOS.ID_EJERCICIOS = DBO.EJERCICIOS.ID_EJERCICIOS\r\n"
				+ "WHERE DBO.EMPLEADOS.id_ejercicios = '" + valor + "' and ELIMINAR_LOGICO='1' \r\n";//and dbo.empleados.clave=124
		System.out.println("sqlSelect Pesnion alimenticia: "+ sqlSelect);
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
			tableReportePension.setRowSorter(rowSortere);

			tableReportePension.setModel(modelo);
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

	//metodo para el tiempo de busqueda
	//Este es el evento que se ejecuta cuando un JFrame se carga
	public  void windowOpened(ActionEvent e){
		final long SLEEP_TIME = 1 * 1000;
		SwingWorker<Void, Void> mySwingWorker = new SwingWorker<Void, Void>(){
			protected Void doInBackground() throws Exception {


				//			        	 mostrarEmpleados(lblTipoNominaOculta.getText());

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

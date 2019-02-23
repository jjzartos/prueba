package ti.snfco.NominaYCapitalHumano.service;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.Dialog.ModalityType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

import org.slf4j.LoggerFactory;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JLabel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.awt.SystemColor;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.JDialog;

public class InternalFrameBuscarEmpl extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(InternalFrameBuscarEmpl.class);
	InternalFrameMovimientos internalFrameMovimientos = new InternalFrameMovimientos();
	InternalFrameEmpleado empleado = new InternalFrameEmpleado();
	InternalFramePeriodo internalFramePeriodo = new InternalFramePeriodo();
	//	InternalFrameBuscarEmpl busEmpl = new  InternalFrameBuscarEmpl();
	public JTable tableBuscEmple = new JTable();
	public JTable tableCalNom = new JTable();
	String atributo = "id";
	TableRowSorter rowSorter;
	int IdBusquedaEmple = 1;
	JTextField textFieldBuscarEmpleMov = new JTextField();
	JLabel lblFondo = new JLabel("");
	//	JComboBox comboBoxTipoNomina = new JComboBox();
	JLabel lblTipoNominaOculta = new JLabel("lblTipoNominaOculta");
	JLabel lblTipoNominaOcultaSemanal = new JLabel("lblTipoNominaOcultaSemanal");

	PoolNYCH nych = new PoolNYCH();
	Connection con =null;
	static Statement st;
	static ResultSet resultSet;

	Connection conUR =null;
	static Statement stUR;
	static ResultSet resultSetUR;



	public InternalFrameBuscarEmpl() {
		getContentPane().setBackground(SystemColor.controlHighlight);
		//getContentPane().setBackground(new Color(147,140,147));
		setBounds(0, 0, 1491, 701);
		setVisible(true);
		setTitle("Empleados");
		getContentPane().setLayout(null);

		JScrollPane scrollPaneEmple = new JScrollPane();
		scrollPaneEmple.setViewportBorder(null);
		scrollPaneEmple.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneEmple.setBounds(23, 95, 1273, 358);
		tableBuscEmple.setSurrendersFocusOnKeystroke(true);
		scrollPaneEmple.setViewportView(tableBuscEmple);
		getContentPane().add(scrollPaneEmple);

		JButton btnAceptarEmpPerp = new JButton("Aceptar");
		btnAceptarEmpPerp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				redireccionarRegistroEmpPerp();
				dispose();
			}
		});
		btnAceptarEmpPerp.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("accept.png"))));
		btnAceptarEmpPerp.setBounds(426, 465, 106, 30);
		getContentPane().add(btnAceptarEmpPerp);

		JButton btnCancelarEmpPerp = new JButton("Cancelar");
		btnCancelarEmpPerp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelarEmpPerp.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("delete.png"))));
		btnCancelarEmpPerp.setBounds(547, 465, 106, 30);
		getContentPane().add(btnCancelarEmpPerp);

		textFieldBuscarEmpleMov.setBackground(SystemColor.controlHighlight);
		textFieldBuscarEmpleMov.setBorder(null);
		textFieldBuscarEmpleMov.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				rowSorter.setRowFilter(RowFilter.regexFilter(textFieldBuscarEmpleMov.getText().toUpperCase(), IdBusquedaEmple));
			}
		});


		textFieldBuscarEmpleMov.setBounds(66, 39, 386, 28);
		textFieldBuscarEmpleMov.setColumns(10);
		getContentPane().add(textFieldBuscarEmpleMov);

		JLabel lblNewLabel = new JLabel("Nombre Empleado(a):");
		lblNewLabel.setBounds(22, 5, 277, 14);
		getContentPane().add(lblNewLabel);

		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(176, 196, 222));
		separator.setForeground(new Color(176, 196, 222));
		separator.setBounds(66, 71, 386, 3);
		getContentPane().add(separator);

		JLabel lblIcon = new JLabel();
		lblIcon.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("icons8_Search_32px_2.png"))));
		lblIcon.setBounds(23, 39, 46, 28);
		getContentPane().add(lblIcon);

		//		comboBoxTipoNomina.setBounds(771, 11, 435, 30);
		//		getContentPane().add(comboBoxTipoNomina);

		//		JButton btnBuscar = new JButton("Buscar");
		//		btnBuscar.addActionListener(new ActionListener() {
		//			public void actionPerformed(ActionEvent e) {
		////				if (comboBoxTipoNomina.getSelectedIndex() >= 0) {
		////					atributo = "ID_EJERCICIOS";
		////					mostrarDatosEmpleado(lblTipoNominaOculta.getText());
		////					mostrarDatosEmpleadoFiniquitoFalso(comboBoxTipoNomina.getSelectedIndex());
		////				}
		//				
		//			}
		//		});
		//		btnBuscar.setBounds(1117, 46, 89, 30);
		//		getContentPane().add(btnBuscar);

		lblTipoNominaOculta.setBounds(460, 12, 143, 14);
		lblTipoNominaOculta.setVisible(false);
		getContentPane().add(lblTipoNominaOculta);

		lblTipoNominaOcultaSemanal.setBounds(613, 11, 106, 14);
		lblTipoNominaOcultaSemanal.setVisible(false);
		getContentPane().add(lblTipoNominaOcultaSemanal);
		//
		//		JButton btnNewButton = new JButton("ISR GLOBAL");
		//		btnNewButton.addActionListener(new ActionListener() {
		//			public void actionPerformed(ActionEvent arg0) {
		//				calcularISRGlobal();
		//			}
		//		});
		//		btnNewButton.setBounds(23, 465, 129, 42);
		//		getContentPane().add(btnNewButton);
		//		
		//		JButton btnNewButton_1 = new JButton("IMSS GLOBAL");
		//		btnNewButton_1.addActionListener(new ActionListener() {
		//			public void actionPerformed(ActionEvent arg0) {
		//				calcularIMSSGlobal();
		//			}
		//		});
		//		btnNewButton_1.setBounds(23, 518, 129, 42);
		//		getContentPane().add(btnNewButton_1);

		final JButton btnVerEmpleados = new JButton("Ver Empleados");
		btnVerEmpleados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarDatosEmpleado(lblTipoNominaOculta.getText());
				btnVerEmpleados.setEnabled(false);
			}
		});
		btnVerEmpleados.setBounds(462, 42, 149, 30);
		getContentPane().add(btnVerEmpleados);


		lblFondo.setBounds(10,11,1474,661);
		lblFondo.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("walll.png"))));
		getContentPane().add(lblFondo);
	}


	@SuppressWarnings("unlikely-arg-type")
	public void mostrarDatosEmpleado(String valor) {
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

		modelo.addColumn("CLAVE");
		modelo.addColumn("NOMBRE");
		modelo.addColumn("APELLIDO PAT.");
		modelo.addColumn("APELLIDO MAT.");
		modelo.addColumn("PUESTO");
		modelo.addColumn("SALARIO");
		modelo.addColumn("UNIDAD RESPONSABLE");
		modelo.addColumn("ID_UNIDAD_RESPONSABLE");
		modelo.addColumn("ID_PUESTO");

		tableBuscEmple.setModel(modelo);
		tableBuscEmple.setBackground(Color.WHITE);

		JTableHeader th = new JTableHeader();
		Color colorSilverLight = new Color(176, 196, 222);
		Color colorNegro = new Color(0, 0, 0);
		th = tableBuscEmple.getTableHeader();
		Font fuente = new Font("Arial", Font.BOLD, 14);
		th.setFont(fuente);
		th.setBackground(colorSilverLight);
		th.setForeground(colorNegro);

		TableColumnModel columnModel = tableBuscEmple.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(100);
		columnModel.getColumn(1).setPreferredWidth(200);
		columnModel.getColumn(2).setPreferredWidth(200);
		columnModel.getColumn(3).setPreferredWidth(200);
		columnModel.getColumn(4).setPreferredWidth(300);
		columnModel.getColumn(5).setPreferredWidth(100);
		columnModel.getColumn(6).setPreferredWidth(300);
		columnModel.getColumn(7).setPreferredWidth(100);
		columnModel.getColumn(8).setPreferredWidth(100);

		String sqlSelect = "";

		sqlSelect = "SELECT DBO.EMPLEADOS.CLAVE,DBO.EMPLEADOS.NOMBRE,DBO.EMPLEADOS.APELLIDO_PATERNO,DBO.EMPLEADOS.APELLIDO_MATERNO,\r\n"
				+ "dbo.puestos.nombre_puesto,dbo.puestos.salario,DBO.DEPENDENCIAS.UNIDAD_RESPONSABLE,DBO.DEPENDENCIAS.id_unidad,dbo.puestos.no_puesto \r\n"
				+ "from empleados\r\n"
				+ "left JOIN DBO.PUESTOS ON DBO.EMPLEADOS.ID_PUESTO = DBO.PUESTOS.no_puesto\r\n"
				+ "LEFT JOIN DBO.DEPENDENCIAS ON DBO.EMPLEADOS.ID_UNIDAD = DBO.DEPENDENCIAS.ID_UNIDAD\r\n"
				+ "LEFT JOIN DBO.EJERCICIOS ON DBO.EMPLEADOS.ID_EJERCICIOS = DBO.EJERCICIOS.ID_EJERCICIOS\r\n"
				+ "WHERE DBO.EMPLEADOS.id_ejercicios = '" + valor + "' and ELIMINAR_LOGICO='1' \r\n";//and dbo.empleados.clave=124
		System.out.println(sqlSelect);
		Object datos[] = new String[14];
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
				datos[1] = resultSet.getString(2);
				datos[2] = resultSet.getString(3);
				datos[3] = resultSet.getString(4);
				datos[4] = resultSet.getString(5);
				datos[5] = resultSet.getString(6);
				datos[6] = resultSet.getString(7);
				datos[7] = resultSet.getString(8);
				datos[8] = resultSet.getString(9);
				modelo.addRow(datos);
			}

			rowSorter = new TableRowSorter(modelo);
			tableBuscEmple.setRowSorter(rowSorter);
			tableBuscEmple.setModel(modelo);
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
	}


	//	public void mostrarDatosEmpleadooooooo(Integer valor) {
	//		DefaultTableModel modelo = new DefaultTableModel()
	//		{
	//
	//			public boolean isCellEditable(int row, int column) {
	//				//return super.isCellEditable(row, column);
	//				if(column==8){
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
	//		modelo.addColumn("PUESTO");
	//		modelo.addColumn("UNIDAD_RESPONSABLE");
	//		modelo.addColumn("ID_UNIDAD_RESPONSABLE");
	//		modelo.addColumn("ID_PUESTO");
	//
	//		tableBuscEmple.setModel(modelo);
	//		tableBuscEmple.setBackground(Color.WHITE);
	//
	//		JTableHeader th = new JTableHeader();
	//		Color colorSilverLight=new Color(176, 196, 222);
	//		Color colorNegro=new Color(0, 0, 0);
	//		th = tableBuscEmple.getTableHeader();
	//		Font fuente = new Font("Cooper Black", Font.BOLD, 14); 
	//		th.setFont(fuente); 
	//		th.setBackground(colorSilverLight);
	//		th.setForeground(colorNegro);
	//
	//		
	//		TableColumnModel columnModel = tableBuscEmple.getColumnModel();
	//		columnModel.getColumn(0).setPreferredWidth(50);
	//		columnModel.getColumn(1).setPreferredWidth(200);
	//		columnModel.getColumn(2).setPreferredWidth(200);
	//		columnModel.getColumn(3).setPreferredWidth(200);
	//		columnModel.getColumn(4).setPreferredWidth(200);
	//		columnModel.getColumn(5).setPreferredWidth(200);
	//		columnModel.getColumn(6).setPreferredWidth(200);
	//		columnModel.getColumn(7).setPreferredWidth(200);
	//
	//
	//		String sqlSelect="";
	//		String sqlSelectUR="";
	//		if(valor.equals("")) {
	//			//sqlSelect = "select * from dbo.empleados order by id_puesto";
	//			sqlSelect = "select dbo.EMPLEADOS.CLAVE,dbo.EMPLEADOS.NOMBRE,dbo.EMPLEADOS.APELLIDO_PATERNO,dbo.EMPLEADOS.APELLIDO_MATERNO,DBO.PUESTOS.NOMBRE_PUESTO,DBO.EMPLEADOS.ID_UNIDAD,DBO.PUESTOS.NO_PUESTO from dbo.EMPLEADOS left join dbo.PUESTOS on dbo.EMPLEADOS.ID_PUESTO = dbo.PUESTOS.NO_PUESTO order by dbo.EMPLEADOS.ID_PUESTO";
	//			sqlSelectUR = "SELECT DBO.DEPENDENCIAS.UNIDAD_RESPONSABLE FROM dbo.EMPLEADOS left JOIN DBO.DEPENDENCIAS ON DBO.EMPLEADOS.ID_UNIDAD = DBO.DEPENDENCIAS.ID_UNIDAD order by dbo.empleados.id_puesto";
	//		}else {
	//			sqlSelect = "SELECT * FROM dbo.EMPLEADOS WHERE "+atributo+""+"='"+ valor +"' order by id_puesto"; 
	//		}
	//
	//		Object datos[] = new String[8];
	//		try {
	//			con = nych.datasource.getConnection();
	//			conUR = nych.datasource.getConnection();
	//			st = con.createStatement();
	//			stUR = conUR.createStatement();
	//			resultSet = st.executeQuery(sqlSelect);
	//			resultSetUR = stUR.executeQuery(sqlSelectUR);
	//			while(resultSet.next() && resultSetUR.next()) {
	//				datos[0] = resultSet.getString(1);
	//				datos[1] = resultSet.getString(2);
	//				datos[2] = resultSet.getString(3);
	//				datos[3] = resultSet.getString(4);
	//				datos[4] = resultSet.getString(5);
	//				datos[5] = resultSetUR.getString(1);
	//				datos[6] = resultSet.getString(6);
	//				datos[7] = resultSet.getString(7);
	//				modelo.addRow(datos);
	//			}
	//			rowSorter = new TableRowSorter(modelo);
	//			tableBuscEmple.setRowSorter(rowSorter);
	//			
	//			tableBuscEmple.setModel(modelo);
	//		}catch (SQLException el) {
	//			el.printStackTrace();
	//			StringWriter errors = new StringWriter();
	//			el.printStackTrace(new PrintWriter(errors));
	//			LOG.info("Excepcion: "+ errors );
	//			//JOptionPane.showMessageDialog(null, el, "Error de conexion", JOptionPane.ERROR_MESSAGE);
	//			JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
	//		}finally {
	//			try {
	//				con.close();
	//				conUR.close();
	//			} catch (SQLException ep) {
	//				ep.printStackTrace();
	//				StringWriter errors = new StringWriter();
	//				ep.printStackTrace(new PrintWriter(errors));
	//				LOG.info("Excepcion: "+ errors );
	//				//JOptionPane.showMessageDialog(null, ep, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
	//				JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
	//			}
	//		}
	//	}//fin del metodo

	public void mostrarCalcNom(String valor) {
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("ID");


		tableCalNom.setModel(modelo);
		tableCalNom.setBackground(Color.WHITE);

		JTableHeader th = new JTableHeader();
		Color colorSilverLight=new Color(176, 196, 222);
		Color colorNegro=new Color(0, 0, 0);
		th = tableCalNom.getTableHeader();
		Font fuente = new Font("Cooper Black", Font.BOLD, 14); 
		th.setFont(fuente); 
		th.setBackground(colorSilverLight);
		th.setForeground(colorNegro);


		TableColumnModel columnModel = tableCalNom.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(50);

		String sqlSelect="";
		if(valor.equals("")) {
			sqlSelect = "select id_calculo_nomina from dbo.calculo_nomina";
		}else {
			sqlSelect = "SELECT * FROM dbo.EMPLEADOS WHERE "+atributo+""+"='"+ valor +"' order by id_puesto"; 
		}

		Object datos[] = new String[1];
		try {
			con = nych.datasource.getConnection();
			st = con.createStatement();
			resultSet = st.executeQuery(sqlSelect);
			while(resultSet.next()) {
				datos[0] = resultSet.getString(1);

				modelo.addRow(datos);
			}
			rowSorter = new TableRowSorter(modelo);
			tableCalNom.setRowSorter(rowSorter);

			tableCalNom.setModel(modelo);
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


	public void calcularISRGlobal() {
		for(int i =0 ; i<tableBuscEmple.getRowCount();i++){
			internalFrameMovimientos.lblEmpIdPerp.setText(tableBuscEmple.getValueAt(i, 0).toString());
			//			System.out.println("Empleado:" + tableBuscEmple.getValueAt(i, 0));
			internalFrameMovimientos.lblIdPuestoEmpMov.setText(tableBuscEmple.getValueAt(i, 8).toString());
			String nombre = tableBuscEmple.getValueAt(i, 1).toString();
			String apPat = tableBuscEmple.getValueAt(i, 2).toString();
			String apMat = tableBuscEmple.getValueAt(i, 3).toString();
			String nombreCompleto = nombre + " " + apPat + " "+ apMat;
			System.out.println("***INICIA PROCESO CALCULO ISR POR EMPLEADO*****");
			System.out.println("id: " + tableBuscEmple.getValueAt(i, 0)+" Nombre: "+ nombreCompleto+ " puesto: " + tableBuscEmple.getValueAt(i, 8));
			String id = tableBuscEmple.getValueAt(i, 0).toString();
			String idPuesto = tableBuscEmple.getValueAt(i, 8).toString();


			calcularISR(id,idPuesto);
		}
		//System.out.println("empleados: " + tableBuscEmple.getRowCount());
	}

	public void calcularIMSSGlobal() {
		for(int i =0 ; i<tableBuscEmple.getRowCount();i++){
			String nombre = tableBuscEmple.getValueAt(i, 1).toString();
			String apPat = tableBuscEmple.getValueAt(i, 2).toString();
			String apMat = tableBuscEmple.getValueAt(i, 3).toString();
			String nombreCompleto = nombre + " " + apPat + " "+ apMat;
			System.out.println("***INICIA PROCESO CALCULO IMSS POR EMPLEADO*****");
			System.out.println("id: " + tableBuscEmple.getValueAt(i, 0)+" Nombre: "+ nombreCompleto);
			String id = tableBuscEmple.getValueAt(i, 0).toString();


			//calcularISR(id,idPuesto);
			calcularIMSS(id);
		}

	}

	public double calcularIMSS(String id) {
		//System.out.println("calcularIMSS");
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
		String sqlSalario ="select sdi,nombre from empleados where clave = '"+id+"'";//'"+lblEmpIdPerp.getText()+"'
		System.out.println("sqlSalario: "+sqlSalario);
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

			formula = sumaPorcentaje/100 * sdi * 14;
			System.out.println("Salario Diario Integrado " + formula +" ,para el empleado: " + nombre);
			actualizaValorIMSS(formula,id);
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


	public void actualizaValorIMSS(double imss,String id) {
		Connection conUpdateIMSS = null;
		PoolNYCH nych = new PoolNYCH();
		try {
			//String sqlUpdateIMSS="";
			String sqlUpdateIMSS ="UPDATE CALCULO_NOMINA SET VALOR_DEDUCCION='"+ imss +"' where id_empleado='"+id+"' and id_deduccion=1";
			System.out.println("sqlUpdateIMSS: "+sqlUpdateIMSS);
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

	public double calcularISR(String id,String idPuesto){
		//System.out.println("calcularISR");
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
		System.out.println("********");
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
		String sqlSalario ="select salario from puestos where no_puesto = '"+idPuesto+"'";//'"+lblIdPuestoEmpMov.getText()+"'
		System.out.println("sqlSalario: " + sqlSalario);
		String datos[] = new String[1];
		try {
			conSalario = nych.datasource.getConnection();
			stSalario = conSalario.createStatement();
			resultSetSalario = stSalario.executeQuery(sqlSalario);

			while(resultSetSalario.next()){ 
				datos[0] = resultSetSalario.getString(1);
			}
			double salario =  Double.parseDouble(datos[0]);
			System.out.println("Salario: " + salario);

			if(salario > listaDatosISR.get(0)  && salario < listaDatosISR.get(3) ) {
				System.out.println("opción 1");
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
				System.out.println("opción 2");
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
				System.out.println("opción 3");
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
				System.out.println("opción 4");
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
				System.out.println("opción 5");
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
				System.out.println("opción 6");
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
				System.out.println("opción 7");
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
				System.out.println("opción 8");
				System.out.println("Limite Inferior:" + listaDatosISR.get(21));
				resta = salario - listaDatosISR.get(21);
				tasa = resta * listaDatosISR.get(23)/100;
				cuota = tasa + listaDatosISR.get(22);
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
				System.out.println("opción 9");
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
				System.out.println("opción 10");
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
				System.out.println("opción 11");
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
			actualizaValorIsr(id, isr);
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
				//JOptionPane.showMessageDialog(null,w ep, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
			}
		}
		return listaSubsidioISR;

	}

	public void actualizaValorIsr(String id,double isr) {
		Connection conUpdateISR = null;
		PoolNYCH nych = new PoolNYCH();
		try {
			//String sqlUpdateIsr="";
			String sqlUpdateIsr ="UPDATE CALCULO_NOMINA SET VALOR_DEDUCCION='"+ isr +"' where id_empleado='"+id+"' and id_deduccion=2";
			System.out.println("sqlUpdateIsr: "+sqlUpdateIsr);
			conUpdateISR = nych.datasource.getConnection();
			PreparedStatement ppsUpdateISR = conUpdateISR.prepareStatement(sqlUpdateIsr);
			ppsUpdateISR.executeUpdate();
			System.out.println("Datos Actualizados del ISR");
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


	public void redireccionarRegistroEmpPerp() {

		int fila = tableBuscEmple.getSelectedRow();
		if(fila>=0) {

			//			internalFrameMovimientos.scrollPane.setVisible(false);
			//			internalFrameMovimientos.lblPeriodoOculto.setText(internalFramePeriodo.lblPeriodoOculto.getText());
			FormularioPrincipal.desktopPane.add(internalFrameMovimientos);
			
			internalFrameMovimientos.lblEmpIdPerp.setText(tableBuscEmple.getValueAt(fila, 0).toString());
			internalFrameMovimientos.lblEmplNombrePerp.setText(tableBuscEmple.getValueAt(fila, 1).toString());
			internalFrameMovimientos.lblEmpApPatPerp.setText(tableBuscEmple.getValueAt(fila, 2).toString());
			internalFrameMovimientos.lblEmpApMatPerp.setText(tableBuscEmple.getValueAt(fila, 3).toString());
			internalFrameMovimientos.lblPuestoEmpMov.setText(tableBuscEmple.getValueAt(fila, 4).toString());
			internalFrameMovimientos.lblEmpURMov.setText(tableBuscEmple.getValueAt(fila, 6).toString());		
			internalFrameMovimientos.lblIdUREmpMov.setText(tableBuscEmple.getValueAt(fila, 7).toString());
			internalFrameMovimientos.lblIdPuestoEmpMov.setText(tableBuscEmple.getValueAt(fila, 8).toString());

			
			internalFrameMovimientos.btnBuscarSalario.setEnabled(false);
			internalFrameMovimientos.btnBuscarValor.setEnabled(false);
			internalFrameMovimientos.btnAgregarListaNomina.setEnabled(false);
			internalFrameMovimientos.separatorNomrbeId.setVisible(true);
			internalFrameMovimientos.separator_3.setVisible(true);
			internalFrameMovimientos.separator_4.setVisible(true);
			internalFrameMovimientos.separator_5.setVisible(true);
			internalFrameMovimientos.lblClave.setVisible(false);
			internalFrameMovimientos.lblFechaDeFalta.setVisible(false);
			internalFrameMovimientos.lblAuxiliarSalario.setVisible(false);
			internalFrameMovimientos.lblSalarioAuxiliar.setVisible(false);
			internalFrameMovimientos.lblTituloChecador.setVisible(false);
			internalFrameMovimientos.show();
			internalFrameMovimientos.setBounds(0, 0, 1500, 701);//setBounds(0, 0, 1862, 870)
			internalFrameMovimientos.setFrameIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("edit.png"))));
			internalFrameMovimientos.setIconifiable(true);
			internalFrameMovimientos.setClosable(true);
			internalFrameMovimientos.setResizable(true);
			internalFrameMovimientos.setMaximizable(true);
			internalFrameMovimientos.setVisible(true);
			internalFrameMovimientos.toFront();

			internalFrameMovimientos.mostrarDatosPerpCalc("");
			internalFrameMovimientos.calcularISR();
			internalFrameMovimientos.calcularIMSS();
			internalFrameMovimientos.calcularInfonavit();
			internalFrameMovimientos.mostrarDatosDedCalc("");
			internalFrameMovimientos.mostrarPercepciones();
			internalFrameMovimientos.mostrarDeducciones();
		}else {
			JOptionPane.showMessageDialog(null, "Registro no Seleccionado");
		}
	}


	public void windowOpened(ActionEvent e){
		final long SLEEP_TIME = 1 * 1000;
		SwingWorker<Void, Void> mySwingWorker = new SwingWorker<Void, Void>(){
			protected Void doInBackground() throws Exception {

				//				ArrayList<String> listaEjercicio = new ArrayList<String>();
				//				listaEjercicio = empleado.buscarEjercicio();
				//				comboBoxTipoNomina.addItem("Seleccione Una");
				//				for (int i = 0; i < listaEjercicio.size(); i++) {
				//					comboBoxTipoNomina.addItem(listaEjercicio.get(i));
				//				}

				//				mostrarDatosEmpleado(lblTipoNominaOculta.getText());
				//mostrarDatosEmpleado(lblTipoNominaOcultaSemanal.getText());

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
}

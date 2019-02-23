package ti.snfco.NominaYCapitalHumano.service;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.MatteBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

import org.pushingpixels.substance.internal.utils.combo.ComboBoxBackgroundDelegate;
import org.slf4j.LoggerFactory;

import java.awt.event.ActionListener;
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
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.JComboBox;

public class InternalFrameCambioPuestos extends JInternalFrame {

	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(InternalFrameCambioPuestos.class);
	private static final long serialVersionUID = 1L;
	private JTextField textFieldBuscarEmpleado;
	public JTable tableEmpleadosCambioPuesto = new JTable();
	public JTable tablePuestos = new JTable();
	JButton btnBuscarEmpleado = new JButton("Ver Empleados");
	JLabel lblTipoNominaOculto = new JLabel("New label");

	TableRowSorter rowSorter;
	int IdBusquedaEmple = 1;

	TableRowSorter rowSorterPuesto;
	int IdBusquedaPuesto = 1;

	JLabel lblActualDependencia = new JLabel("Actual dependencia");
	JLabel lblActualPuesto = new JLabel("Actual puesto");
	JPanel panelInfoEmpleado = new JPanel();
	private JPanel panelNuevoPD = new JPanel();
	private JButton btnCambiarP = new JButton("Cambiar");
	private final JScrollPane scrollPanePuestos = new JScrollPane();
	private final JLabel lblIcon2 = new JLabel();
	private final JTextField textFieldBuscarPuesto = new JTextField();
	private final JSeparator separator_1 = new JSeparator();
	private final JButton btnCambiarD = new JButton("Cambiar");
	JLabel lblIdNuevoPuesto = new JLabel("IdNuevoPuesto");
	JLabel lblclave = new JLabel("clave");
	JLabel lblIdNuevoDependencia = new JLabel("idNuevaDependencia");
	private final JButton btnNewButton = new JButton("Cerrar");
	JLabel lblIdPuestoCam = new JLabel("IdPuestoCam");
	JLabel lblIdDependenciaCam = new JLabel("IdDependenciaCam");
	private final JButton btnVerHistorial = new JButton("Ver historial");


	public InternalFrameCambioPuestos() {

		setBounds(0, 45, 1500, 551);
		setVisible(true);
		setTitle("Cambio de Puesto");
		getContentPane().setBackground(SystemColor.controlHighlight);
		getContentPane().setLayout(null);

		JLabel lblIcon = new JLabel();
		lblIcon.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("icons8_Search_32px_2.png"))));
		lblIcon.setBounds(10, 40, 46, 28);
		getContentPane().add(lblIcon);

		textFieldBuscarEmpleado = new JTextField();
		textFieldBuscarEmpleado.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				rowSorter.setRowFilter(RowFilter.regexFilter(textFieldBuscarEmpleado.getText().toUpperCase(), IdBusquedaEmple));
			}
		});
		textFieldBuscarEmpleado.setColumns(10);
		textFieldBuscarEmpleado.setBorder(null);
		textFieldBuscarEmpleado.setBackground(SystemColor.controlHighlight);
		textFieldBuscarEmpleado.setBounds(53, 40, 386, 28);
		getContentPane().add(textFieldBuscarEmpleado);

		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(176, 196, 222));
		separator.setBackground(new Color(176, 196, 222));
		separator.setBounds(53, 72, 386, 3);
		getContentPane().add(separator);


		btnBuscarEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				getEmpleadosCambioPuesto(lblTipoNominaOculto.getText());
				btnBuscarEmpleado.setEnabled(false);

			}
		});
		btnBuscarEmpleado.setBounds(449, 43, 149, 30);
		getContentPane().add(btnBuscarEmpleado);

		JLabel lblNombreEmpleado = new JLabel("Nombre Empleado(a):");
		lblNombreEmpleado.setBounds(24, 15, 277, 14);
		getContentPane().add(lblNombreEmpleado);


		lblTipoNominaOculto.setBounds(449, 25, 46, 14);
		lblTipoNominaOculto.setVisible(false);
		getContentPane().add(lblTipoNominaOculto);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 103, 839, 362);
		scrollPane.setViewportBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setViewportView(tableEmpleadosCambioPuesto);
		getContentPane().add(scrollPane);

		JButton btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = tableEmpleadosCambioPuesto.getSelectedRow();
				if(fila>=0) {

					panelInfoEmpleado.setVisible(true);

					String clave =  tableEmpleadosCambioPuesto.getValueAt(fila, 0).toString();
					String puesto =  tableEmpleadosCambioPuesto.getValueAt(fila, 5).toString();
					String IDpuesto =  tableEmpleadosCambioPuesto.getValueAt(fila, 4).toString();
					String dependencia =  tableEmpleadosCambioPuesto.getValueAt(fila, 7).toString();
					String Iddependencia =  tableEmpleadosCambioPuesto.getValueAt(fila, 9).toString();

					//					lblIdNuevoPuesto.setVisible(false);
					//					lblIdNuevoDependencia.setVisible(false);
					//					lblIdPuestoCam.setVisible(false);
					//					lblIdDependenciaCam.setVisible(false);


					lblclave.setText(clave);
					lblActualPuesto.setText(puesto);
					lblActualDependencia.setText(dependencia);
					lblIdPuestoCam.setText(IDpuesto);
					lblIdDependenciaCam.setText(Iddependencia);

					getEmpleadosCambioPuesto(lblTipoNominaOculto.getText());


				}else {
					JOptionPane.showMessageDialog(null, "No ha seleccionado ninguno empleado");
				}

			}
		});
		btnSeleccionar.setBounds(371, 469, 124, 28);
		getContentPane().add(btnSeleccionar);

		panelInfoEmpleado.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		panelInfoEmpleado.setBounds(865, 72, 574, 160);
		getContentPane().add(panelInfoEmpleado);
		panelInfoEmpleado.setLayout(null);

		JLabel lblAntDependencia = new JLabel("EL empleado actualmente esta en la dependencia:");
		lblAntDependencia.setBounds(10, 85, 286, 19);
		panelInfoEmpleado.add(lblAntDependencia);

		JLabel lblPuestoActual = new JLabel("El puesto acual es:");
		lblPuestoActual.setBounds(10, 11, 286, 19);
		panelInfoEmpleado.add(lblPuestoActual);
		lblActualDependencia.setForeground(new Color(250, 128, 114));

		lblActualDependencia.setBounds(10, 109, 375, 14);
		panelInfoEmpleado.add(lblActualDependencia);
		lblActualPuesto.setForeground(new Color(250, 128, 114));

		lblActualPuesto.setBounds(10, 35, 286, 14);
		panelInfoEmpleado.add(lblActualPuesto);

		btnCambiarP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				InternalFrameActualizarPuesto internalFrameActualizarPuesto = new  InternalFrameActualizarPuesto();
				FormularioPrincipal.desktopPane.add(internalFrameActualizarPuesto);


				internalFrameActualizarPuesto.show();
				internalFrameActualizarPuesto.setFrameIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("user_add.png"))));
				internalFrameActualizarPuesto.setIconifiable(true);
				internalFrameActualizarPuesto.setClosable(true);
				internalFrameActualizarPuesto.setResizable(true);
				internalFrameActualizarPuesto.setMaximizable(true);
				internalFrameActualizarPuesto.setVisible(true);
				internalFrameActualizarPuesto.toFront();
				internalFrameActualizarPuesto.getPuestos();
				internalFrameActualizarPuesto.lblClave.setText(lblclave.getText());
				internalFrameActualizarPuesto.lblDependencia.setText(lblActualDependencia.getText());
				internalFrameActualizarPuesto.lblIdPuestoAct.setText(lblIdPuestoCam.getText());
				internalFrameActualizarPuesto.lblIdDependenciaAct.setText(lblIdDependenciaCam.getText());
				//				internalFrameActualizarPuesto.lblClave.setVisible(false);
				//				internalFrameActualizarPuesto.lblIdPuestoAct.setVisible(false);
				//				internalFrameActualizarPuesto.lblIdDependenciaAct.setVisible(false);
				//				internalFrameActualizarPuesto.lblDependencia.setVisible(false);
				//				panelInfoEmpleado.setVisible(false);
				dispose();

			}
		});
		btnCambiarP.setBounds(10, 55, 89, 22);
		panelInfoEmpleado.add(btnCambiarP);

		panelNuevoPD.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		panelNuevoPD.setBounds(10, 165, 554, 263);
		panelNuevoPD.setBackground(new Color(240, 240, 240));
		panelInfoEmpleado.add(panelNuevoPD);
		panelNuevoPD.setLayout(null);

		scrollPanePuestos.setViewportBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		scrollPanePuestos.setBounds(10, 59, 534, 160);
		scrollPanePuestos.setViewportBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		scrollPanePuestos.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPanePuestos.setViewportView(tablePuestos);
		panelNuevoPD.add(scrollPanePuestos);

		JButton btnGuardarNuevoPD = new JButton("Actualizar");
		btnGuardarNuevoPD.setBounds(455, 222, 89, 30);
		panelNuevoPD.add(btnGuardarNuevoPD);

		lblIcon2.setBounds(10, 13, 46, 28);
		lblIcon2.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("icons8_Search_32px_2.png"))));
		panelNuevoPD.add(lblIcon2);
		textFieldBuscarPuesto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				rowSorterPuesto.setRowFilter(RowFilter.regexFilter(textFieldBuscarPuesto.getText().toUpperCase(), IdBusquedaPuesto));

			}
		});

		textFieldBuscarPuesto.setColumns(10);
		textFieldBuscarPuesto.setBorder(null);
		textFieldBuscarPuesto.setBackground(new Color(240, 240, 240));
		textFieldBuscarPuesto.setBounds(53, 13, 229, 28);

		panelNuevoPD.add(textFieldBuscarPuesto);
		separator_1.setForeground(new Color(176, 196, 222));
		separator_1.setBackground(new Color(176, 196, 222));
		separator_1.setBounds(53, 45, 229, 3);

		panelNuevoPD.add(separator_1);
		btnCambiarD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				InternalFrameActualizarDependencia internalFrameActualizarDependencia = new  InternalFrameActualizarDependencia();
				FormularioPrincipal.desktopPane.add(internalFrameActualizarDependencia);
				internalFrameActualizarDependencia.show();
				internalFrameActualizarDependencia.setFrameIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("user_add.png"))));
				internalFrameActualizarDependencia.setIconifiable(true);
				internalFrameActualizarDependencia.setClosable(true);
				internalFrameActualizarDependencia.setResizable(true);
				internalFrameActualizarDependencia.setMaximizable(true);
				internalFrameActualizarDependencia.setVisible(true);
				internalFrameActualizarDependencia.toFront();
				internalFrameActualizarDependencia.getDependencias();
				internalFrameActualizarDependencia.lblClave.setText(lblclave.getText());
				internalFrameActualizarDependencia.lblIdPuesto.setText(lblIdPuestoCam.getText());
				internalFrameActualizarDependencia.lblPuesto.setText(lblActualPuesto.getText());
				internalFrameActualizarDependencia.lblIdDependencia.setText(lblIdDependenciaCam.getText());
				//				internalFrameActualizarDependencia.lblClave.setVisible(false);
				//				internalFrameActualizarDependencia.lblIdPuesto.setVisible(false);
				//				internalFrameActualizarDependencia.lblPuesto.setVisible(false);
				//				internalFrameActualizarDependencia.lblIdDependencia.setVisible(false);
				dispose();
			}
		});
		btnCambiarD.setBounds(10, 132, 89, 22);

		panelInfoEmpleado.add(btnCambiarD);
		lblIdNuevoPuesto.setBounds(409, 35, 74, 14);

		panelInfoEmpleado.add(lblIdNuevoPuesto);
		lblIdNuevoDependencia.setBounds(390, 109, 112, 14);

		panelInfoEmpleado.add(lblIdNuevoDependencia);
		btnGuardarNuevoPD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//actualizaPD();
				//getEmpleadosCambioPuesto(lblTipoNominaOculto.getText());


			}
		});
		lblclave.setBounds(865, 51, 46, 14);

		getContentPane().add(lblclave);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(1385, 472, 89, 23);

		getContentPane().add(btnNewButton);
		lblIdPuestoCam.setBounds(866, 35, 149, 14);

		getContentPane().add(lblIdPuestoCam);
		lblIdDependenciaCam.setBounds(865, 15, 117, 14);

		getContentPane().add(lblIdDependenciaCam);
		btnVerHistorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = tableEmpleadosCambioPuesto.getSelectedRow();
				if(fila>=0) {
					String clave = tableEmpleadosCambioPuesto.getValueAt(fila, 0).toString();
					InternalFrameHistorial internalFrameHistorial = new InternalFrameHistorial();
					FormularioPrincipal.desktopPane.add(internalFrameHistorial);
					internalFrameHistorial.show();
					internalFrameHistorial.setFrameIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("edit.png"))));
					internalFrameHistorial.setIconifiable(true);
					internalFrameHistorial.setClosable(true);
					internalFrameHistorial.setResizable(true);
					internalFrameHistorial.setMaximizable(true);
					internalFrameHistorial.setVisible(true);
					internalFrameHistorial.toFront();
					internalFrameHistorial.lblClave.setText(clave);
					internalFrameHistorial.getHistorial();

				}else {
					JOptionPane.showConfirmDialog(null, "No ha seleccionado ningun empleado para ver su historial");
				}

			}
		});
		btnVerHistorial.setBounds(608, 43, 149, 30);

		getContentPane().add(btnVerHistorial);

		JLabel lblFoto = new JLabel("");
		lblFoto.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("walll.png"))));
		lblFoto.setBounds(0, 0, 1535, 522);
		getContentPane().add(lblFoto);
	}

	public int insertarHistorialEmpleadoPuesto() {
		JOptionPane.showMessageDialog(null, "Se guardará el historial del empleado.");
		PoolNYCH nych = new PoolNYCH();
		Connection con =null;
		String sqlInsert="";
		int resultado = 0;
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date diaHoy = Calendar.getInstance().getTime();
		int fila = tableEmpleadosCambioPuesto.getSelectedRow();

		sqlInsert="INSERT INTO DBO.HISTORIAL_EMPLEADO (FECHA_MOVIMIENTO,DEPENDENCIA_ANTERIOR,DEPENDENCIA_ACTUAL,PUESTO_ANTERIOR,PUESTO_ACTUAL,CLAVE_EMPLEADO)"
				+ "VALUES ("+"convert(datetime,'"+ formatter.format(diaHoy)+"',101)"+","+lblIdDependenciaCam.getText()+",'"+lblIdDependenciaCam.getText()+"',"+lblIdPuestoCam.getText()+","+ lblIdNuevoPuesto.getText()+","+ lblclave.getText() +")";
		System.out.println("INSERT HISTORIAL: "+sqlInsert);
		//sqlInsert="";

		try {
			con = nych.datasource.getConnection();
			PreparedStatement pps = con.prepareStatement(sqlInsert);
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
		return resultado;

	}//fin del metodo

	public int insertarHistorialEmpleadoDependencia() {
		JOptionPane.showMessageDialog(null, "Se guardará el historial del empleado.");
		PoolNYCH nych = new PoolNYCH();
		Connection con =null;
		String sqlInsert="";
		int resultado = 0;
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date diaHoy = Calendar.getInstance().getTime();
		int fila = tableEmpleadosCambioPuesto.getSelectedRow();

		String idPuestoAct =  lblIdNuevoPuesto.getText();

		sqlInsert="INSERT INTO DBO.HISTORIAL_EMPLEADO (FECHA_MOVIMIENTO,DEPENDENCIA_ANTERIOR,DEPENDENCIA_ACTUAL,PUESTO_ANTERIOR,PUESTO_ACTUAL,CLAVE_EMPLEADO)"
				+ "VALUES ("+"convert(datetime,'"+ formatter.format(diaHoy)+"',101)"+","+lblIdDependenciaCam.getText()+",'"+lblIdNuevoDependencia.getText()+"',"+lblIdPuestoCam.getText()+","+idPuestoAct+","+ lblclave.getText() +")";
		System.out.println("INSERT HISTORIAL Dep: "+sqlInsert);
		//sqlInsert="";

		try {
			con = nych.datasource.getConnection();
			PreparedStatement pps = con.prepareStatement(sqlInsert);
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
		return resultado;

	}


	public int insertarHistorialEmpleadoPuestoInicial(String idDepAnt ,String idDepAct ,String idPuestoAnt ,String idPuestoAct,String clave) {
		JOptionPane.showMessageDialog(null, "Se guardará el historial del empleado.");
		PoolNYCH nych = new PoolNYCH();
		Connection con =null;
		String sqlInsert="";
		int resultado = 0;
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date diaHoy = Calendar.getInstance().getTime();
		int fila = tableEmpleadosCambioPuesto.getSelectedRow();

		//		String id =  tableEmpleadosCambioPuesto.getValueAt(fila, 0).toString();
		//		String idPuestoAnt =  tableEmpleadosCambioPuesto.getValueAt(fila, 4).toString();
		//		String idPuestoAct =  lblIdNuevoPuesto.getText();
		//		String idDependencia =  tableEmpleadosCambioPuesto.getValueAt(fila, 9).toString();

		sqlInsert="INSERT INTO DBO.HISTORIAL_EMPLEADO (FECHA_MOVIMIENTO,DEPENDENCIA_ANTERIOR,DEPENDENCIA_ACTUAL,PUESTO_ANTERIOR,PUESTO_ACTUAL,CLAVE_EMPLEADO)"
				+ "VALUES ("+"convert(datetime,'"+ formatter.format(diaHoy)+"',101)"+","+idDepAnt+",'"+idDepAnt+"',"+idPuestoAnt+","+idPuestoAnt+","+ clave +")";
		System.out.println("INSERT HISTORIAL: "+sqlInsert);
		//sqlInsert="";

		try {
			con = nych.datasource.getConnection();
			PreparedStatement pps = con.prepareStatement(sqlInsert);
			pps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Historial Guardado");
		} catch (SQLException el) { 
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
		return resultado;

	}

	public void getPuestos() {
		final DefaultTableModel modeloPuestos = new DefaultTableModel(){

			public boolean isCellEditable(int row, int column) {
				if(column==2){
					return true;
				}else {
					return false;
				}
			}
		};
		modeloPuestos.addColumn("NO_PUESTO");
		modeloPuestos.addColumn("NOMBRE DEL PUESTO");
		modeloPuestos.addColumn("SALARIO");
		tablePuestos.setModel(modeloPuestos);
		tablePuestos.setBackground(Color.WHITE);

		JTableHeader th = new JTableHeader();
		Color colorSilverLight=new Color(176, 196, 222);
		Color colorNegro=new Color(0, 0, 0);
		th = tablePuestos.getTableHeader();
		Font fuente = new Font("Arial", Font.BOLD, 14); 
		th.setFont(fuente); 
		th.setBackground(colorSilverLight);
		th.setForeground(colorNegro);

		TableColumnModel columnModel = tablePuestos.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(100);
		columnModel.getColumn(1).setPreferredWidth(400);
		columnModel.getColumn(2).setPreferredWidth(400);

		String sqlSelect="";
		String sql="";
		sqlSelect = "SELECT NO_PUESTO,NOMBRE_PUESTO,SALARIO FROM dbo.PUESTOS";
		String datos[] = new String[5];
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
				modeloPuestos.addRow(datos);
			}//FIN DEL WHILE

			rowSorterPuesto = new TableRowSorter(modeloPuestos);
			tablePuestos.setRowSorter(rowSorterPuesto);

			tablePuestos.setModel(modeloPuestos);
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


	public void actualizaP() {
		Connection conUpdate = null;
		PoolNYCH nych = new PoolNYCH();
		try {

			String sqlUpdateIsr ="update empleados set ID_PUESTO = '"+ lblIdNuevoPuesto.getText() +"' where clave = '"+ lblclave.getText() + "'";
			//			sqlUpdateIsr = "";
			//			System.out.println("sqlUpdateIsr: "+sqlUpdateIsr);
			conUpdate = nych.datasource.getConnection();
			PreparedStatement ppsUpdateISR = conUpdate.prepareStatement(sqlUpdateIsr);
			ppsUpdateISR.executeUpdate();
			System.out.println("Datos Actualizados");
			JOptionPane.showMessageDialog(null, "Datos Actualizados");
		}catch(Exception ees) {
			ees.printStackTrace();
			StringWriter errors = new StringWriter();
			ees.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: "+ errors );
			//JOptionPane.showMessageDialog(null, ees, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
		}finally {
			try {
				conUpdate.close();
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


	public void actualizaD() {
		Connection conUpdate = null;
		PoolNYCH nych = new PoolNYCH();
		try {

			String sqlUpdateIsr ="update empleados set ID_UNIDAD = '"+ lblIdNuevoDependencia.getText() +"' where clave = '"+ lblclave.getText() + "'";
			//			sqlUpdateIsr = "";
			System.out.println("sqlUpdateIsr: "+sqlUpdateIsr);
			conUpdate = nych.datasource.getConnection();
			PreparedStatement ppsUpdateISR = conUpdate.prepareStatement(sqlUpdateIsr);
			ppsUpdateISR.executeUpdate();
			System.out.println("Datos Actualizados");
			JOptionPane.showMessageDialog(null, "Datos Actualizados");
		}catch(Exception ees) {
			ees.printStackTrace();
			StringWriter errors = new StringWriter();
			ees.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: "+ errors );
			//JOptionPane.showMessageDialog(null, ees, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
		}finally {
			try {
				conUpdate.close();
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

	public void getEmpleadosCambioPuesto(String valor) {
		DefaultTableModel modelo = new DefaultTableModel()
		{

			public boolean isCellEditable(int row, int column) {
				if(column==8){
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
		modelo.addColumn("ID PUESTO");
		modelo.addColumn("PUESTO");
		modelo.addColumn("SALARIO");
		modelo.addColumn("UNIDAD_RESPONSABLE");
		modelo.addColumn("FECHA INGRESO");
		modelo.addColumn("ID DEPENDENCIA");


		tableEmpleadosCambioPuesto.setModel(modelo);
		tableEmpleadosCambioPuesto.setBackground(Color.WHITE);

		JTableHeader th = new JTableHeader();
		Color colorSilverLight=new Color(176, 196, 222);
		Color colorNegro=new Color(0, 0, 0);
		th = tableEmpleadosCambioPuesto.getTableHeader();
		Font fuente = new Font("Arial", Font.BOLD, 14); 
		th.setFont(fuente); 
		th.setBackground(colorSilverLight);
		th.setForeground(colorNegro);


		TableColumnModel columnModel = tableEmpleadosCambioPuesto.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(80);
		columnModel.getColumn(1).setPreferredWidth(200);
		columnModel.getColumn(2).setPreferredWidth(200);
		columnModel.getColumn(3).setPreferredWidth(200);
		columnModel.getColumn(4).setPreferredWidth(200);
		columnModel.getColumn(5).setPreferredWidth(300);
		columnModel.getColumn(6).setPreferredWidth(200);
		columnModel.getColumn(7).setPreferredWidth(200);
		columnModel.getColumn(8).setPreferredWidth(200);
		columnModel.getColumn(9).setPreferredWidth(200);


		String sqlSelect="";
		sqlSelect = "SELECT DBO.EMPLEADOS.CLAVE,DBO.EMPLEADOS.NOMBRE,DBO.EMPLEADOS.APELLIDO_PATERNO,DBO.EMPLEADOS.APELLIDO_MATERNO,\r\n"
				+ "dbo.puestos.no_puesto,dbo.puestos.nombre_puesto,dbo.puestos.salario,DBO.DEPENDENCIAS.UNIDAD_RESPONSABLE,dbo.empleados.fecha_ingreso,DBO.DEPENDENCIAS.ID_UNIDAD\r\n"//DBO.CALCULO_NOMINA.valor_percepcion
				+ "from empleados\r\n"
				+ "left JOIN DBO.PUESTOS ON DBO.EMPLEADOS.ID_PUESTO = DBO.PUESTOS.no_puesto\r\n"
				+ "LEFT JOIN DBO.DEPENDENCIAS ON DBO.EMPLEADOS.ID_UNIDAD = DBO.DEPENDENCIAS.ID_UNIDAD\r\n"
				+ "LEFT JOIN DBO.EJERCICIOS ON DBO.EMPLEADOS.ID_EJERCICIOS = DBO.EJERCICIOS.ID_EJERCICIOS\r\n"
				+ "WHERE DBO.EMPLEADOS.id_ejercicios = '" + valor + "' and ELIMINAR_LOGICO='1'  \r\n";
		//		System.out.println("sql aguinaldo: " + sqlSelect);
		PoolNYCH nych = new PoolNYCH();				
		Connection con =null;
		String datos[] = new String[14];
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
				modelo.addRow(datos);
			}
			rowSorter = new TableRowSorter(modelo);
			tableEmpleadosCambioPuesto.setRowSorter(rowSorter);

			tableEmpleadosCambioPuesto.setModel(modelo);
			//			System.out.println(""+tableEmpleadosCambioPuesto.getRowCount());
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


	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static ArrayList<String> selectComboPuesto() {
		PoolNYCH nych = new PoolNYCH();
		Connection connect = null;
		Statement st;
		ResultSet resultSet = null;
		//		DefaultComboBoxModel value = null;
		ArrayList<String> lista = new ArrayList<String>();
		String sqlComboPuesto = "SELECT DBO.PUESTOS.no_puesto as puesto,DBO.PUESTOS.NOMBRE_PUESTO from dbo.PUESTOS order by NO_PUESTO";
		try {
			connect = nych.datasource.getConnection();
			st = connect.createStatement();
			resultSet = st.executeQuery(sqlComboPuesto);
			//			value =new DefaultComboBoxModel();
			//			comboBoxPuesto.setModel(value);
		} catch (Exception exp) {
			exp.printStackTrace();
			StringWriter errors = new StringWriter();
			exp.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: " + errors);
			// JOptionPane.showMessageDialog(null, exp, "Error de conexion",
			// JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");
		}
		try {
			while (resultSet.next()) {
				lista.add(resultSet.getString("NOMBRE_PUESTO"));
				//				value.addElement(resultSet.getString("PUESTO"));
				//				value.addElement(resultSet.getString("NOMBRE_PUESTO"));
				//System.out.println("LISTA: "+ lista.size() );
			}
		} catch (Exception et) {
			et.printStackTrace();
			StringWriter errors = new StringWriter();
			et.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: " + errors);
			// JOptionPane.showMessageDialog(null, et, "Error de desconexion",
			// JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");
		} finally {
			try {
				connect.close();
			} catch (SQLException ep) {
				ep.printStackTrace();
				StringWriter errors = new StringWriter();
				ep.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: " + errors);
				// JOptionPane.showMessageDialog(null, ep, "Error de desconexion",
				// JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");
			}
		}
		return lista;
	}


	public static ArrayList<String> selectComboDependencia() {
		PoolNYCH nych = new PoolNYCH();
		Connection connect = null;
		Statement st;
		ResultSet resultSet = null;
		ArrayList<String> lista = new ArrayList<String>();
		// String sqlComboDependencia = "SELECT DBO.DEPENDENCIAS.UNIDAD_RESPONSABLE from
		// dbo.DEPENDENCIAS inner join dbo.empleados on dbo.DEPENDENCIAS.ID_UNIDAD =
		// dbo.EMPLEADOS.ID_UNIDAD";
		String sqlComboDependencia = "SELECT DBO.DEPENDENCIAS.UNIDAD_RESPONSABLE from dbo.DEPENDENCIAS order by UNIDAD_RESPONSABLE";
		try {
			connect = nych.datasource.getConnection();
			st = connect.createStatement();
			resultSet = st.executeQuery(sqlComboDependencia);
		} catch (Exception exp) {
			exp.printStackTrace();
			StringWriter errors = new StringWriter();
			exp.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: " + errors);
			// JOptionPane.showMessageDialog(null, exp, "Error de conexion",
			// JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");
		}
		try {
			while (resultSet.next()) {
				lista.add(resultSet.getString("UNIDAD_RESPONSABLE"));
			}
		} catch (Exception et) {
			et.printStackTrace();
			StringWriter errors = new StringWriter();
			et.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: " + errors);
			// JOptionPane.showMessageDialog(null, et, "Error de desconexion",
			// JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");
		} finally {
			try {
				connect.close();
			} catch (SQLException ep) {
				ep.printStackTrace();
				StringWriter errors = new StringWriter();
				ep.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: " + errors);
				// JOptionPane.showMessageDialog(null, ep, "Error de desconexion",
				// JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");
			}
		}
		return lista;
	}


	/////////////////////////setter
	public JPanel getPanelInfoEmpleado() {
		return panelInfoEmpleado;
	}


	public void setPanelInfoEmpleado(JPanel panelInfoEmpleado) {
		this.panelInfoEmpleado = panelInfoEmpleado;
	}
}

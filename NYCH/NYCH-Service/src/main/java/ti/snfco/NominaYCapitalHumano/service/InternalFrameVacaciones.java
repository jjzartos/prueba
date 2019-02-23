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
import java.util.GregorianCalendar;

import javax.print.attribute.standard.DateTimeAtCompleted;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.border.MatteBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

import org.slf4j.LoggerFactory;

import com.toedter.calendar.JDateChooser;
import java.awt.SystemColor;
import javax.swing.JSeparator;
import javax.swing.UIManager;

public class InternalFrameVacaciones extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(InternalFrameVacaciones.class);
	InternalFrameEmpleado internalFrameEmpleado = new InternalFrameEmpleado();
	JTable tableVacaciones = new JTable();
	JTable tableFechaVacaciones = new JTable();

	private JTextField textFieldVacaciones;
	TableRowSorter rowSorter;
	int IdBusquedaEmple = 1;
	JButton btnSeleccionar = new JButton("Seleccionar");
	JPanel panelDatosEmp = new JPanel();
	JLabel lblNombre = new JLabel("label");
	JLabel lblApPat = new JLabel("label");
	JLabel lblApMat = new JLabel("label");
	JLabel lblPuesto = new JLabel("label");
	JLabel lblIdEmp = new JLabel("label");
	JLabel lblFechaIngreso = new JLabel("label");
	JLabel lblFechaIn = new JLabel("Fecha Ingreso:");
	private final JLabel lblPuesto_1 = new JLabel("Puesto:");
	private final JPanel panelFecha = new JPanel();
	JLabel lblSetFecha = new JLabel("");
	private final JScrollPane scrollPaneFechas = new JScrollPane();
	private final JButton btnAd = new JButton("Consultar");
	private final JButton btnDel = new JButton("Eliminar");
	private final JLabel lblHaSeleccionadoLa = new JLabel("Ha Seleccionado la Fecha:");
	//JScrollPane scrollPanePrimerSemestre = new JScrollPane();
	final JDateChooser dateChooserVacaciones = new JDateChooser();
	private final JSeparator separator = new JSeparator();
	JLabel lblDetalle = new JLabel("Detalle");
	JLabel lblFlecha = new JLabel("");
	private final JLabel lblFondo = new JLabel("");
	JLabel lblTipoNominaOculta = new JLabel("New label");
	private final JButton btnVerEmpleados = new JButton("Ver Empleados");



	public InternalFrameVacaciones() {
		getContentPane().setBackground(SystemColor.controlHighlight);

		setBounds(0, 0, 1501, 670);
		setVisible(true);
		setTitle("Vacaciones");
		getContentPane().setLayout(null);


		JLabel lblNewLabel = new JLabel("Seleccione el Empleado(a):");
		lblNewLabel.setBounds(28, 11, 228, 21);
		getContentPane().add(lblNewLabel);


		JScrollPane scrollPaneVacaciones = new JScrollPane();
		scrollPaneVacaciones.setViewportBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		scrollPaneVacaciones.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneVacaciones.setBounds(28, 113, 833, 304);
		scrollPaneVacaciones.setViewportView(tableVacaciones);
		getContentPane().add(scrollPaneVacaciones);

		textFieldVacaciones = new JTextField();
		textFieldVacaciones.setBackground(SystemColor.controlHighlight);
		textFieldVacaciones.setBorder(null);
		textFieldVacaciones.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent arg0) {
				rowSorter.setRowFilter(RowFilter.regexFilter(textFieldVacaciones.getText().toUpperCase(), IdBusquedaEmple));
			}
		});
		textFieldVacaciones.setBounds(70, 53, 350, 28);
		getContentPane().add(textFieldVacaciones);
		textFieldVacaciones.setColumns(10);


		panelDatosEmp.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		panelDatosEmp.setBounds(871, 113, 604, 148);
		panelDatosEmp.setLayout(null);
		panelDatosEmp.setVisible(false);
		getContentPane().add(panelDatosEmp);


		lblNombre.setForeground(Color.BLACK);
		lblNombre.setBounds(66, 11, 170, 14);
		lblNombre.setVisible(false);
		panelDatosEmp.add(lblNombre);


		lblApPat.setForeground(Color.BLACK);
		lblApPat.setBounds(10, 41, 226, 14);
		lblApPat.setVisible(false);
		panelDatosEmp.add(lblApPat);


		lblApMat.setForeground(Color.BLACK);
		lblApMat.setBounds(10, 71, 226, 14);
		lblApMat.setVisible(false);
		panelDatosEmp.add(lblApMat);


		lblPuesto.setForeground(Color.BLACK);
		lblPuesto.setBounds(116, 93, 179, 14);
		lblPuesto.setVisible(false);
		panelDatosEmp.add(lblPuesto);


		lblIdEmp.setForeground(Color.BLACK);
		lblIdEmp.setBounds(10, 11, 46, 14);
		lblIdEmp.setVisible(false);
		panelDatosEmp.add(lblIdEmp);


		lblFechaIngreso.setForeground(Color.BLACK);
		lblFechaIngreso.setBounds(116, 123, 179, 14);
		lblFechaIngreso.setVisible(false);
		panelDatosEmp.add(lblFechaIngreso);

		lblFechaIn.setBounds(10, 123, 109, 14);
		lblFechaIn.setVisible(false);
		panelDatosEmp.add(lblFechaIn);

		lblPuesto_1.setBounds(10, 93, 109, 14);
		lblPuesto_1.setVisible(false);
		panelDatosEmp.add(lblPuesto_1);

		panelFecha.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		panelFecha.setBounds(871, 276, 350, 132);
		panelFecha.setLayout(null);
		panelFecha.setVisible(false);
		getContentPane().add(panelFecha);


		dateChooserVacaciones.setBounds(10, 28, 330, 30);
		dateChooserVacaciones.setVisible(false);
		panelFecha.add(dateChooserVacaciones);

		final JLabel lblSeleccione = new JLabel("Seleccione Fecha para Vacaciones:");
		lblSeleccione.setVisible(false);
		lblSeleccione.setBounds(10, 11, 286, 14);
		panelFecha.add(lblSeleccione);

		lblSetFecha.setForeground(new Color(255, 0, 0));
		lblSetFecha.setBackground(new Color(176, 196, 222));
		lblSetFecha.setBounds(174, 100, 131, 21);
		panelFecha.add(lblSetFecha);

		final JButton btnNewButton_1 = new JButton("AGREGAR");
		btnNewButton_1.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("accept.png"))));
		btnNewButton_1.setVisible(false);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//				int dia = dateChooserVacaciones.getCalendar().get(Calendar.DAY_OF_MONTH);
				//				lblSetFecha.setText(Integer.toString(dia));

				lblSetFecha.setText(dateChooserVacaciones.getDate().toString());

				insertarRegistro();
				getFechasVacacionesEmpleado("");
				
				
				
				dateChooserVacaciones.setDate(null);
				DefaultTableModel table = (DefaultTableModel) tableFechaVacaciones.getModel();
				table.setRowCount(0);


			}
		});
		btnNewButton_1.setBounds(10, 62, 154, 30);
		panelFecha.add(btnNewButton_1);

		//		scrollPanePrimerSemestre.setBounds(1044, 61, 692, 270);
		//		scrollPanePrimerSemestre.setVisible(false);
		//		getContentPane().add(scrollPanePrimerSemestre);


		scrollPaneFechas.setViewportBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		scrollPaneFechas.setVisible(false);
		scrollPaneFechas.setBounds(1231, 276, 244, 287);
		scrollPaneFechas.setViewportView(tableFechaVacaciones);
		getContentPane().add(scrollPaneFechas);

		btnAd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				getFechasVacacionesEmpleado("");


			}
		});

		btnAd.setToolTipText("Agregar Fecha");
		btnAd.setBounds(1087, 419, 134, 68);
		btnAd.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("add.png"))));
		btnAd.setVisible(false);
		getContentPane().add(btnAd);

		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				eliminarFechaVacacion();
				getFechasVacacionesEmpleado("");
			}
		});

		btnDel.setToolTipText("Eliminar Fecha");
		btnDel.setBounds(1087, 495, 134, 68);
		btnDel.setVisible(false);
		btnDel.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("delete.png"))));
		getContentPane().add(btnDel);

		lblHaSeleccionadoLa.setBounds(10, 100, 154, 21);
		lblHaSeleccionadoLa.setVisible(false);
		panelFecha.add(lblHaSeleccionadoLa);

		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				redireccionarEmpleado();
				getFechasVacacionesEmpleado("");
				//scrollPanePrimerSemestre.setVisible(true);
				panelDatosEmp.setVisible(true);
				lblIdEmp.setVisible(true);
				lblNombre.setVisible(true);
				lblApPat.setVisible(true);
				lblApMat.setVisible(true);
				lblPuesto.setVisible(true);
				lblFechaIngreso.setVisible(true);
				lblFechaIn.setVisible(true);
				lblPuesto_1.setVisible(true);
				panelFecha.setVisible(true);
				lblSeleccione.setVisible(true);
				dateChooserVacaciones.setVisible(true);
				btnNewButton_1.setVisible(true);
				scrollPaneFechas.setVisible(true);
				btnAd.setVisible(true);
				btnDel.setVisible(true);
				lblHaSeleccionadoLa.setVisible(true);
				lblDetalle.setVisible(true);
				lblFlecha.setVisible(true);
			}
		});
		btnSeleccionar.setBounds(373, 428, 121, 30);
		btnSeleccionar.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("accept.png"))));
		getContentPane().add(btnSeleccionar);		
		separator.setBounds(28, 81, 350, 1);
		
		getContentPane().add(separator);
		
		JLabel label = new JLabel();
		label.setBounds(28, 53, 46, 28);
		label.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("icons8_Search_32px_2.png"))));
		getContentPane().add(label);
		lblDetalle.setBounds(873, 95, 46, 14);
		
		getContentPane().add(lblDetalle);
		lblFlecha.setBounds(910, 92, 46, 20);
		lblFlecha.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("download.png"))));
		getContentPane().add(lblFlecha);

		//mostrarDatosEmpleado("");
		
		lblTipoNominaOculta.setBounds(431, 14, 228, 14);
		lblTipoNominaOculta.setVisible(false);
		getContentPane().add(lblTipoNominaOculta);
		btnVerEmpleados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarDatosEmpleado(lblTipoNominaOculta.getText());
				btnVerEmpleados.setEnabled(false);
			}
		});
		btnVerEmpleados.setBounds(429, 58, 149, 30);
		
		getContentPane().add(btnVerEmpleados);
		

		lblFondo.setBounds(0, 0, 1906, 983);
		lblFondo.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("walll.png"))));
		getContentPane().add(lblFondo);

	}


	public void eliminarFechaVacacion() {
		PoolNYCH nych = new PoolNYCH();
		Connection con =null;

		Date datetime;
		int fila = tableFechaVacaciones.getSelectedRow();
		if(fila>=0) {
			try {
				String valor= tableFechaVacaciones.getValueAt(fila, 0).toString();
				//DELETE FROM dbo.FECHAS_VACACIONES WHERE ID_EMPLEADO=1535 and FECHA_VACACIONES=convert(datetime, '2018-03-13 00:00:00.0', 101)
				String sqlDelete="DELETE FROM dbo.FECHAS_VACACIONES WHERE CLAVE="+ lblIdEmp.getText() +" and FECHA_VACACIONES="+ "convert(datetime,"+ "'"+valor+"'" +", 101)";
				System.out.println(sqlDelete);
				con = nych.datasource.getConnection();
				PreparedStatement pps = con.prepareStatement(sqlDelete);
				pps.executeUpdate();
				JOptionPane.showMessageDialog(null, "Se ha eliminado el registro");
			}catch(Exception e) {
				e.printStackTrace();
				LOG.info("Excepción: " + e);
				JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
			}finally {
				try {
					con.close();
				} catch (SQLException ep) {
					ep.printStackTrace();
					LOG.info("Excepción: " + ep);
					JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
				}
			}
		}else {
			JOptionPane.showMessageDialog(null, "Registro no seleccionado para eliminar");
		}
	}


	public int insertarRegistro() {
		
		DateFormat outputFormat = new SimpleDateFormat("yyyy/MM/dd");
		DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date inputText;
		String outputText;
		
		ArrayList<String> listaFechasInhabiles = new ArrayList<String>();
		listaFechasInhabiles = getListafechasVacaciones();
		System.out.println("*********");
		for(int i = 0; i<listaFechasInhabiles.size();i++){
			System.out.println("indiceFechasVacaciones: "+ i + " |valorFechasVacaciones: " + listaFechasInhabiles.get(i));
		}
		
		PoolNYCH nych = new PoolNYCH();
		Connection con =null;
		int resultado = 0;

		Date fecha = dateChooserVacaciones.getDate();
		System.out.println("fecha sin format: "+fecha);
		//SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat formatoDeFecha1 = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("fecha con fo: "+formatoDeFecha1.format(fecha));
		System.out.println("lista.get(0): "+listaFechasInhabiles.get(0).substring(0, 10));
		System.out.println("longitud de fecha con formato: "+ formatoDeFecha1.format(fecha).length() );
		System.out.println("longitud de lista.get(0): "+ listaFechasInhabiles.get(0).substring(0, 10).length() );
		
		
		
//		inputText = dateChooserVacaciones.getDate();
//		System.out.println("inputText: " + inputText);
//		String date = inputFormat.format(inputText);
//		System.out.println("date: " + date);
//		outputText = outputFormat.format(date);
//		System.out.println("outputText: " + outputText);

		if(formatoDeFecha1.format(fecha).equals(listaFechasInhabiles.get(0).substring(0, 10))) {
			JOptionPane.showMessageDialog(null, "La Fecha Seleccionada es fecha inhábil oficial, favor de verificar");
			
		}
		
		if(formatoDeFecha1.format(fecha).equals(listaFechasInhabiles.get(1).substring(0, 10))) {
			JOptionPane.showMessageDialog(null, "La Fecha Seleccionada es fecha inhábil oficial, favor de verificar");
			
		}
		
		if(formatoDeFecha1.format(fecha).equals(listaFechasInhabiles.get(2).substring(0, 10))) {
			JOptionPane.showMessageDialog(null, "La Fecha Seleccionada es fecha inhábil oficial, favor de verificar");
			
		}
		
		if(formatoDeFecha1.format(fecha).equals(listaFechasInhabiles.get(3).substring(0, 10))) {
			JOptionPane.showMessageDialog(null, "La Fecha Seleccionada es fecha inhábil oficial, favor de verificar");
			
		}
		
		if(formatoDeFecha1.format(fecha).equals(listaFechasInhabiles.get(4).substring(0, 10))) {
			JOptionPane.showMessageDialog(null, "La Fecha Seleccionada es fecha inhábil oficial, favor de verificar");
			
		}
		
		if(formatoDeFecha1.format(fecha).equals(listaFechasInhabiles.get(5).substring(0, 10))) {
			JOptionPane.showMessageDialog(null, "La Fecha Seleccionada es fecha inhábil oficial, favor de verificar");
			
		}
		
		if(formatoDeFecha1.format(fecha).equals(listaFechasInhabiles.get(6).substring(0, 10))) {
			JOptionPane.showMessageDialog(null, "La Fecha Seleccionada es fecha inhábil oficial, favor de verificar");
			
		}
		
		if(formatoDeFecha1.format(fecha).equals(listaFechasInhabiles.get(7).substring(0, 10))) {
			JOptionPane.showMessageDialog(null, "La Fecha Seleccionada es fecha inhábil oficial, favor de verificar");
			
		}
		
		if(formatoDeFecha1.format(fecha).equals(listaFechasInhabiles.get(8).substring(0, 10))) {
			JOptionPane.showMessageDialog(null, "La Fecha Seleccionada es fecha inhábil oficial, favor de verificar");
			
		}
		
		if(formatoDeFecha1.format(fecha).equals(listaFechasInhabiles.get(9).substring(0, 10))) {
			JOptionPane.showMessageDialog(null, "La Fecha Seleccionada es fecha inhábil oficial, favor de verificar");
			
		}
		
		
		String sqlInsert="insert into dbo.fechas_vacaciones (id_empleado,fecha_vacaciones) values('"+ lblIdEmp.getText() +"',"+"convert(datetime,'"+ formatoDeFecha1.format(fecha)+"',101))";
//		String sqlInsert="";
		System.out.println(sqlInsert);
		try {
			con = nych.datasource.getConnection();
			PreparedStatement pps = con.prepareStatement(sqlInsert);
			pps.executeUpdate();
			JOptionPane.showMessageDialog(null, "SE HA GUARDADO LA FECHA");
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
	
	
	public static ArrayList<String> getListafechasVacaciones() {
		PoolNYCH nych = new PoolNYCH();
		Connection connect = null;
		ResultSet resultSet = null;
		String sqlSelectEjercicio = "SELECT DBO.DIAS_INHABILES.DIA_INHABIL FROM DBO.DIAS_INHABILES";
		ArrayList<String> lista = new ArrayList<String>();
		try {
			connect = nych.datasource.getConnection();
			Statement st = connect.createStatement();
			resultSet = st.executeQuery(sqlSelectEjercicio);
			while(resultSet.next()){
				lista.add(resultSet.getString("DIA_INHABIL"));
			}
		}catch(Exception exp) {
			exp.printStackTrace();
			LOG.info("Excepción: " + exp);
			//JOptionPane.showMessageDialog(null, exp, "Error de conexion", JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
		}
		finally {
			try {
				connect.close();
			} catch (SQLException ep) {
				ep.printStackTrace();
				LOG.info("Excepción: " + ep);
				//JOptionPane.showMessageDialog(null, ep, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
			}
		}
		return lista;
	}

	

	public void redireccionarEmpleado() {
		InternalFrameMovimientos internalFrameMovimientos = new InternalFrameMovimientos();
		int fila = tableVacaciones.getSelectedRow();
		if(fila>=0) {
			lblIdEmp.setText(tableVacaciones.getValueAt(fila, 0).toString());
			lblNombre.setText(tableVacaciones.getValueAt(fila, 1).toString());
			lblApPat.setText(tableVacaciones.getValueAt(fila, 2).toString());
			lblApMat.setText(tableVacaciones.getValueAt(fila, 3).toString());
			lblPuesto.setText(tableVacaciones.getValueAt(fila, 5).toString());
			lblFechaIngreso.setText(tableVacaciones.getValueAt(fila, 8).toString());


		}else {
			JOptionPane.showMessageDialog(null, "Registro no Seleccionado");
		}
	}


	public void getFechasVacacionesEmpleado(String valor) {
		final DefaultTableModel modelo = new DefaultTableModel()
		{

			public boolean isCellEditable(int row, int column) {
				//return super.isCellEditable(row, column);
				if(column==1){
					return true;
				}else {
					return false;
				}
			}
		};
		modelo.addColumn("FECHA DE VACACIONES");
		//modelo.addColumn("COMENTARIO");

		tableFechaVacaciones.setModel(modelo);
		tableFechaVacaciones.setBackground(Color.WHITE);

		JTableHeader th = new JTableHeader();
		Color colorSilverLight=new Color(176, 196, 222);
		Color colorNegro=new Color(0, 0, 0);
		th = tableFechaVacaciones.getTableHeader();
		Font fuente = new Font("Arial", Font.BOLD, 14); 
		th.setFont(fuente); 
		th.setBackground(colorSilverLight);
		th.setForeground(colorNegro);


		TableColumnModel columnModel = tableFechaVacaciones.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(100);
		//columnModel.getColumn(1).setPreferredWidth(100);



		String sqlSelect="";
		sqlSelect = "select fecha_vacaciones from dbo.FECHAS_VACACIONES where id_empleado='"+lblIdEmp.getText()+"'";//,comentario
		System.out.println(sqlSelect);
		final PoolNYCH nych = new PoolNYCH();
		Connection con =null;
		Object datos[] = new String[1];
		try {
			con = nych.datasource.getConnection();
			Statement st = con.createStatement();
			ResultSet resultSet = st.executeQuery(sqlSelect);
			while(resultSet.next()) {
				datos[0] = resultSet.getString(1);
				//datos[1] = resultSet.getString(2);
				modelo.addRow(datos);
			}


//			modelo.addTableModelListener(new TableModelListener() {
//				public void tableChanged(TableModelEvent e) {
//					if(e.getType() == TableModelEvent.UPDATE) {
//						JOptionPane.showMessageDialog(null, "Ha cambiado el valor, ¿Es correcto?");
//						String colName = "comentario";
//						int fila = tableFechaVacaciones.getSelectedRow();
//						//int col = tableFechaVacaciones.getSelectedColumn();
//						String valor = "";
//						String valor1 = "";
//						if(fila>=0) {
//							valor= tableFechaVacaciones.getValueAt(fila, 0).toString();
//							//valor1=tableFechaVacaciones.getValueAt(col, 1).toString();
//							System.out.println("valor: "+valor);
//							//System.out.println("valor1: "+valor1);
//						}
//						System.out.println("Valor introducido: "+modelo.getValueAt(e.getFirstRow(), e.getColumn()));
//						String sqlupdt ="update DBO.FECHAS_VACACIONES SET "+ colName +"= '"+ modelo.getValueAt(e.getFirstRow(), e.getColumn()) +"' where id_empleado='"+lblIdEmp.getText()+"' and fecha_vacaciones= convert(datetime,"+ "'"+ valor +"'" +", 101)";
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
			
			
			
			tableFechaVacaciones.setModel(modelo);
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



	public void mostrarDatosEmpleado(String valor) {
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
		modelo.addColumn("CLAVE");
		modelo.addColumn("NOMBRE");
		modelo.addColumn("APELLIDO PATERNO");
		modelo.addColumn("APELLIDO MATERNO");
		modelo.addColumn("ID_PUESTO");
		modelo.addColumn("PUESTO");
		modelo.addColumn("UNIDAD_RESPONSABLE");
		modelo.addColumn("ID_UNIDAD");
		modelo.addColumn("FECHA INGRESO");

		tableVacaciones.setModel(modelo);
		tableVacaciones.setBackground(Color.WHITE);

		JTableHeader th = new JTableHeader();
		Color colorSilverLight=new Color(176, 196, 222);
		Color colorNegro=new Color(0, 0, 0);
		th = tableVacaciones.getTableHeader();
		Font fuente = new Font("Arial", Font.BOLD, 14); 
		th.setFont(fuente); 
		th.setBackground(colorSilverLight);
		th.setForeground(colorNegro);


		TableColumnModel columnModel = tableVacaciones.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(80);
		columnModel.getColumn(1).setPreferredWidth(200);
		columnModel.getColumn(2).setPreferredWidth(200);
		columnModel.getColumn(3).setPreferredWidth(200);
		columnModel.getColumn(4).setPreferredWidth(200);
		columnModel.getColumn(5).setPreferredWidth(300);
		columnModel.getColumn(6).setPreferredWidth(200);
		columnModel.getColumn(7).setPreferredWidth(100);
		columnModel.getColumn(8).setPreferredWidth(100);


		String sqlSelect="";
		String sqlSelectUR="";
//		sqlSelect = "select dbo.EMPLEADOS.clave,dbo.EMPLEADOS.NOMBRE,dbo.EMPLEADOS.APELLIDO_PATERNO,dbo.EMPLEADOS.APELLIDO_MATERNO,DBO.PUESTOS.NOMBRE_PUESTO,DBO.EMPLEADOS.FECHA_INGRESO,DBO.EMPLEADOS.ID_UNIDAD,DBO.PUESTOS.NO_PUESTO from dbo.EMPLEADOS left join dbo.PUESTOS on dbo.EMPLEADOS.ID_PUESTO = dbo.PUESTOS.NO_PUESTO order by dbo.EMPLEADOS.ID_PUESTO";
//		sqlSelectUR = "SELECT DBO.DEPENDENCIAS.UNIDAD_RESPONSABLE FROM dbo.EMPLEADOS left JOIN DBO.DEPENDENCIAS ON DBO.EMPLEADOS.ID_UNIDAD = DBO.DEPENDENCIAS.ID_UNIDAD order by dbo.empleados.id_puesto";
		sqlSelect = "SELECT DBO.EMPLEADOS.CLAVE,DBO.EMPLEADOS.NOMBRE,DBO.EMPLEADOS.APELLIDO_PATERNO,DBO.EMPLEADOS.APELLIDO_MATERNO,\r\n"
				+ "	dbo.puestos.no_puesto,dbo.puestos.nombre_puesto,DBO.DEPENDENCIAS.UNIDAD_RESPONSABLE,dbo.dependencias.id_unidad,dbo.empleados.fecha_ingreso\r\n"
				+ "from empleados\r\n"
				+ "left JOIN DBO.PUESTOS ON DBO.EMPLEADOS.ID_PUESTO = DBO.PUESTOS.no_puesto\r\n"
				+ "LEFT JOIN DBO.DEPENDENCIAS ON DBO.EMPLEADOS.ID_UNIDAD = DBO.DEPENDENCIAS.ID_UNIDAD\r\n"
				+ "LEFT JOIN DBO.EJERCICIOS ON DBO.EMPLEADOS.ID_EJERCICIOS = DBO.EJERCICIOS.ID_EJERCICIOS\r\n"
				+ "WHERE DBO.EMPLEADOS.id_ejercicios = '" + valor + "' and ELIMINAR_LOGICO='1'\r\n";

		PoolNYCH nych = new PoolNYCH();
		Connection con =null;
//		Connection conUR = null;
		Object datos[] = new String[9];
		try {
			con = nych.datasource.getConnection();
//			conUR = nych.datasource.getConnection();
			Statement st = con.createStatement();
//			Statement stUR = conUR.createStatement();
			ResultSet resultSet = st.executeQuery(sqlSelect);
//			ResultSet resultSetUR = stUR.executeQuery(sqlSelectUR);
			while(resultSet.next()) {// && resultSetUR.next()
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
			tableVacaciones.setRowSorter(rowSorter);

			tableVacaciones.setModel(modelo);
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
//				conUR.close();
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

	public JDateChooser getDateChooserVacaciones() {
		return dateChooserVacaciones;
	}
	
	public void windowOpened(ActionEvent e) {
		final long SLEEP_TIME = 1 * 1000;
		SwingWorker<Void, Void> mySwingWorker = new SwingWorker<Void, Void>() {
			protected Void doInBackground() throws Exception {
			
//				mostrarDatosEmpleado(lblTipoNominaOculta.getText());

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
}

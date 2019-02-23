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

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
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
import javax.swing.border.MatteBorder;
import java.awt.SystemColor;

public class InternalFrameImuvi extends JInternalFrame {
	
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(InternalFrameImuvi.class);
	private static final long serialVersionUID = 1L;
	JTextField textFieldImuvi;
	JTable tableReporteImuvi = new JTable();
	TableRowSorter rowSortere;
	int IdBusquedaEmplee = 1;
	JTextField textFieldMontoImuvi;
	JLabel lblIdPuesto = new JLabel("id puesto");
	JLabel lblnombreemp = new JLabel("Nombre");
	JLabel lblapPat = new JLabel("Pat");
	JLabel lblAPMat = new JLabel("Mat");
	JLabel lblPuesto = new JLabel("Puesto");
	JLabel lblSalario = new JLabel("salario");
	JLabel labelMonto = new JLabel("Introduce el Monto del Prestamo");
	JSeparator separator = new JSeparator();
	JLabel lblprecio = new JLabel("$");
	JButton btnguardar = new JButton("Guardar");
	JButton btnCancelar = new JButton("Cancelar");
	JLabel lblprecio1 = new JLabel("$");
	JLabel lblIdempleado = new JLabel("clave");
	InternalFrameNomina nomina = new InternalFrameNomina();
	JFormattedTextField textFieldPagos = new JFormattedTextField();
	JLabel lblPagos = new JLabel("¿Cantidad de Pagos?");
	private final JSeparator separator_1 = new JSeparator();
	private final JLabel lblicon = new JLabel();
	JPanel panelImuu = new JPanel();
	JLabel lblDetalle = new JLabel("Detalle");
	JLabel lblFlecha = new JLabel("");
	private final JLabel lblFondo = new JLabel("");
	JLabel lblTipoNominaOculta = new JLabel("New label");
	
	public InternalFrameImuvi() {
		setBounds(0, 0, 1501, 670);
		setVisible(true);
		setTitle("Imuvi");
		getContentPane().setLayout(null);
		
		JPanel panelImuvi = new JPanel();
		panelImuvi.setBackground(SystemColor.controlHighlight);
		panelImuvi.setBorder(new TitledBorder(null, "Reporte Imuvi", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(176, 196, 222)));
		panelImuvi.setBounds(0, 0, 1971, 1051);
		//panelImuvi.setBackground(new Color(147,140,147));
		panelImuvi.setLayout(null);
		getContentPane().add(panelImuvi);
		
		JLabel lblNewLabel = new JLabel("Nombre Empleado(a):");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBounds(26, 25, 171, 14);
		panelImuvi.add(lblNewLabel);
		
		textFieldImuvi = new JTextField(new Integer(3));
		textFieldImuvi.setBorder(null);
		textFieldImuvi.setBackground(SystemColor.controlHighlight);
		textFieldImuvi.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				rowSortere.setRowFilter(RowFilter.regexFilter(textFieldImuvi.getText().toUpperCase(), IdBusquedaEmplee));
				
			}
		});
		textFieldImuvi.setBounds(72, 61, 297, 28);
		panelImuvi.add(textFieldImuvi);
		textFieldImuvi.setColumns(10);
		
		JScrollPane scrollPaneImuvi = new JScrollPane();
		scrollPaneImuvi.setBounds(26, 105, 931, 396);
		scrollPaneImuvi.setViewportBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		scrollPaneImuvi.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneImuvi.setViewportView(tableReporteImuvi);
		panelImuvi.add(scrollPaneImuvi);
		
		JButton btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
//				ArrayList<String> listaEjercicio = new ArrayList<String>();
//				listaEjercicio = nomina.buscarTipoNomina();
//				comboBoxtiponomina.addItem("Seleccione Una");
//				for(int i = 0; i<listaEjercicio.size();i++){
//					comboBoxtiponomina.addItem(listaEjercicio.get(i));
//				}
				
				redireccionarEmpleado();
				btnCancelar.setVisible(true);
				btnguardar.setVisible(true);
				lblIdempleado.setVisible(true);
				lblnombreemp.setVisible(true);
				lblapPat.setVisible(true);
				lblAPMat.setVisible(true);
				lblIdPuesto.setVisible(true);
				lblSalario.setVisible(true);
				lblprecio.setVisible(true);
				lblprecio1.setVisible(true);
//				lbltipoNomina.setVisible(true);
				labelMonto.setVisible(true);
				textFieldMontoImuvi.setVisible(true);
//				lblFechainicio.setVisible(true);
//				lblFechaFinal.setVisible(true);
//				dateChoosefechaFinal.setVisible(true);
//				dateChooserfechaincial.setVisible(true);
				separator.setVisible(true);
//				comboBoxtiponomina.setVisible(true);
				lblPagos.setVisible(true);
				textFieldPagos.setVisible(true);
				panelImuu.setVisible(true);
				lblDetalle.setVisible(true);
				lblFlecha.setVisible(true);
				
				
				
				
				
			}
		});
		btnSeleccionar.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("accept.png"))));
		btnSeleccionar.setBounds(239, 515, 128, 30);
		panelImuvi.add(btnSeleccionar);
		separator_1.setForeground(new Color(176, 196, 222));
		separator_1.setBackground(new Color(176, 196, 222));
		separator_1.setBounds(26, 89, 360, 2);
		
		panelImuvi.add(separator_1);
		lblicon.setBounds(26, 61, 46, 28);
		lblicon.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("icons8_Search_32px_2.png"))));
		panelImuvi.add(lblicon);
		panelImuu.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		panelImuu.setBounds(991, 105, 439, 396);
		
		panelImuvi.add(panelImuu);
		panelImuu.setLayout(null);
		lblnombreemp.setBounds(60, 11, 247, 14);
		panelImuu.add(lblnombreemp);
		lblnombreemp.setForeground(new Color(0, 0, 0));
		lblapPat.setBounds(10, 41, 282, 14);
		panelImuu.add(lblapPat);
		lblapPat.setForeground(new Color(0, 0, 0));
		lblAPMat.setBounds(10, 71, 297, 14);
		panelImuu.add(lblAPMat);
		lblAPMat.setForeground(new Color(0, 0, 0));
		lblPuesto.setBounds(71, 96, 292, 14);
		panelImuu.add(lblPuesto);
		lblPuesto.setForeground(new Color(0, 0, 0));
		lblSalario.setBounds(19, 126, 344, 14);
		panelImuu.add(lblSalario);
		lblSalario.setForeground(new Color(0, 0, 0));
		
		textFieldMontoImuvi = new JTextField();
		textFieldMontoImuvi.setBounds(20, 180, 194, 25);
		panelImuu.add(textFieldMontoImuvi);
		textFieldMontoImuvi.setColumns(10);
		labelMonto.setBounds(10, 155, 271, 14);
		panelImuu.add(labelMonto);
		labelMonto.setForeground(new Color(0, 0, 0));
		separator.setBounds(10, 147, 300, 2);
		panelImuu.add(separator);
		lblprecio.setBounds(10, 126, 46, 14);
		panelImuu.add(lblprecio);
		lblprecio.setForeground(new Color(0, 0, 0));
		btnguardar.setBounds(118, 326, 128, 30);
		panelImuu.add(btnguardar);
		
		
		btnguardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertarImuvi();
				
				textFieldMontoImuvi.setText(null);
				textFieldPagos.setText(null);
				
				dispose();
			}
		});
		btnguardar.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("accept.png"))));
		btnCancelar.setBounds(252, 326, 128, 30);
		panelImuu.add(btnCancelar);
		
		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("delete.png"))));
		lblprecio1.setBounds(10, 185, 46, 14);
		panelImuu.add(lblprecio1);
		lblprecio1.setForeground(new Color(0, 0, 0));
		lblIdempleado.setBounds(10, 11, 46, 14);
		panelImuu.add(lblIdempleado);
		lblIdempleado.setForeground(new Color(0, 0, 0));
		lblIdPuesto.setBounds(10, 96, 46, 14);
		panelImuu.add(lblIdPuesto);
		lblIdPuesto.setForeground(new Color(0, 0, 0));
		textFieldPagos.setBounds(10, 241, 86, 25);
		panelImuu.add(textFieldPagos);
		
		textFieldPagos.setValue(new Integer(0));
		textFieldPagos.setColumns(10);
		lblPagos.setBounds(10, 216, 282, 14);
		panelImuu.add(lblPagos);
		lblPagos.setForeground(new Color(0, 0, 0));
		lblDetalle.setBounds(991, 83, 46, 14);
		
		panelImuvi.add(lblDetalle);
		lblFlecha.setBounds(1028, 75, 46, 20);
		lblFlecha.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("download.png"))));
		panelImuvi.add(lblFlecha);
		
		lblTipoNominaOculta.setBounds(397, 25, 155, 14);
		lblTipoNominaOculta.setVisible(false);
		panelImuvi.add(lblTipoNominaOculta);
		
		final JButton btnVerEmpleados = new JButton("Ver Empleados");
		btnVerEmpleados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mostrarEmpleadosParaImuvi(lblTipoNominaOculta.getText());
				btnVerEmpleados.setEnabled(false);
			}
		});
		btnVerEmpleados.setBounds(395, 58, 149, 30);
		panelImuvi.add(btnVerEmpleados);
		
		lblFondo.setBounds(0, 0, 1906, 983);
		lblFondo.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("walll.png"))));
		panelImuvi.add(lblFondo);
		
//		ArrayList<String> listaEjercicio = new ArrayList<String>();
//		listaEjercicio = nomina.buscarTipoNomina();
//		comboBoxtiponomina.addItem("Seleccione Una");
//		for(int i = 0; i<listaEjercicio.size();i++){
//			comboBoxtiponomina.addItem(listaEjercicio.get(i));
//		}

	}
	
	

	public void redireccionarEmpleado() {
		InternalFrameMovimientos internalFrameMovimientos = new InternalFrameMovimientos();
		int fila = tableReporteImuvi.getSelectedRow();
		if(fila>=0) {
			lblIdempleado.setText(tableReporteImuvi.getValueAt(fila, 0).toString());
			lblnombreemp.setText(tableReporteImuvi.getValueAt(fila, 1).toString());
			lblapPat.setText(tableReporteImuvi.getValueAt(fila, 2).toString());
			lblAPMat.setText(tableReporteImuvi.getValueAt(fila, 3).toString());
			lblIdPuesto.setText(tableReporteImuvi.getValueAt(fila, 4).toString());
			lblPuesto.setText(tableReporteImuvi.getValueAt(fila, 5).toString());
			lblSalario.setText(tableReporteImuvi.getValueAt(fila, 6).toString());
		}else {
			JOptionPane.showMessageDialog(null, "Registro no Seleccionado");
		}
	}
	
	public int insertarImuvi(){
		
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
		int claveDeduccionImuvi=23;
		double valorClaveInternaPercepcion=0.0;
		PoolNYCH nych = new PoolNYCH();
		Connection conImuvi =null;
		int resultado = 0;
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date diaHoy = Calendar.getInstance().getTime();
		int fila = tableReporteImuvi.getSelectedRow();
		String dependencia =  tableReporteImuvi.getValueAt(fila, 8).toString();
		
		Integer pagos = (Integer) textFieldPagos.getValue();
		String sqlInsert="INSERT INTO dbo.CALCULO_NOMINA (ID_EMPLEADO,ID_PERCEPCION,ID_DEDUCCION,VALOR_PERCEPCION,VALOR_DEDUCCION,ID_PUESTO,FECHA_CALCULO,ID_DEPENDENCIA,ID_TIPO_NOMINA,ESTATUS,PERIODO,PARCIALIDADES)" 
				+ ""
				+ "VALUES ("+ lblIdempleado.getText()+","+ claveInternaPercepcion +","+ claveDeduccionImuvi +","+ valorClaveInternaPercepcion +","
				+ ""+ Double.parseDouble(textFieldMontoImuvi.getText())/pagos+","+ lblIdPuesto.getText() +",'"+formatter.format(diaHoy)+"','" + dependencia +"',"
						+ "'"+lblTipoNominaOculta.getText()+"','"+1+"','"+FormularioPrincipal.lblPeriodoNumero.getText()+"','"+pagos+"')";
		System.out.println("SQL INSERT IMUVI:"+sqlInsert);
		try {
			conImuvi = nych.datasource.getConnection();
			PreparedStatement pps = conImuvi.prepareStatement(sqlInsert);
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
				conImuvi.close();
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
	
	
	public void mostrarEmpleadosParaImuvi(String valor) {
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
		tableReporteImuvi.setModel(modelo);

		JTableHeader th = new JTableHeader();
		JTableHeader th1 = new JTableHeader();
		Color colorSilverLight=new Color(176, 196, 222);
		Color colorNegro=new Color(0, 0, 0);
		th = tableReporteImuvi.getTableHeader();
		th1 = tableReporteImuvi.getTableHeader();
		Font fuente = new Font("Arial", Font.BOLD, 14); 
		th.setFont(fuente); 
		th1.setFont(fuente); 
		th.setBackground(colorSilverLight);
		th1.setBackground(colorSilverLight);
		th.setForeground(colorNegro);
		th1.setForeground(colorNegro);

		TableColumnModel columnModel = tableReporteImuvi.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(80);
		columnModel.getColumn(1).setPreferredWidth(80);
		columnModel.getColumn(2).setPreferredWidth(100);
		columnModel.getColumn(3).setPreferredWidth(80);
		columnModel.getColumn(4).setPreferredWidth(80);
		columnModel.getColumn(5).setPreferredWidth(80);
		columnModel.getColumn(6).setPreferredWidth(80);
		columnModel.getColumn(7).setPreferredWidth(80);


		String sqlSelect="";
		//sqlSelect = "select dbo.empleados.clave,dbo.empleados.nombre,dbo.empleados.apellido_paterno,dbo.empleados.apellido_materno,dbo.PUESTOS.NO_PUESTO,dbo.PUESTOS.NOMBRE_PUESTO,dbo.PUESTOS.SALARIO from dbo.empleados left join dbo.puestos on dbo.empleados.ID_puesto = dbo.puestos.no_puesto WHERE DBO.EMPLEADOS.ELIMINAR_LOGICO='"+1+"'  order by id_puesto ";
		sqlSelect = "SELECT DBO.EMPLEADOS.CLAVE,DBO.EMPLEADOS.NOMBRE,DBO.EMPLEADOS.APELLIDO_PATERNO,DBO.EMPLEADOS.APELLIDO_MATERNO,\r\n"
				+ "dbo.puestos.no_puesto,dbo.puestos.nombre_puesto,dbo.puestos.salario,DBO.DEPENDENCIAS.UNIDAD_RESPONSABLE,DBO.DEPENDENCIAS.ID_UNIDAD \r\n"
				+ "from empleados\r\n"
				+ "left JOIN DBO.PUESTOS ON DBO.EMPLEADOS.ID_PUESTO = DBO.PUESTOS.no_puesto\r\n"
				+ "LEFT JOIN DBO.DEPENDENCIAS ON DBO.EMPLEADOS.ID_UNIDAD = DBO.DEPENDENCIAS.ID_UNIDAD\r\n"
				+ "LEFT JOIN DBO.EJERCICIOS ON DBO.EMPLEADOS.ID_EJERCICIOS = DBO.EJERCICIOS.ID_EJERCICIOS\r\n"
				+ "WHERE DBO.EMPLEADOS.id_ejercicios = '" + valor + "' and ELIMINAR_LOGICO='1' \r\n";//and dbo.empleados.clave=124
		//System.out.println(sqlSelect);
		Connection con= null;
		PoolNYCH nych = new PoolNYCH();
		String datos[] = new String[10];
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
			tableReporteImuvi.setRowSorter(rowSortere);

			tableReporteImuvi.setModel(modelo);
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

	        	// mostrarEmpleadosParaImuvi(lblTipoNominaOculta.getText());
	            
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

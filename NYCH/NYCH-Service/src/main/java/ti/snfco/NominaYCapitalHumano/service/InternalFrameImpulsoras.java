package ti.snfco.NominaYCapitalHumano.service;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

import org.slf4j.LoggerFactory;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InternalFrameImpulsoras extends JInternalFrame {

	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(InternalFrameImpulsoras.class);
	private static final long serialVersionUID = 1L;
	JTable tableReporteImpul = new JTable();
	TableRowSorter rowSortere;
	int IdBusquedaEmplee = 1;
	JTextField textFieldImpul;
	JTextField textFieldMonto = new JTextField();
	JLabel lblPagos = new JLabel("¿Cantidad de Pagos?");
	JLabel lblNombre = new JLabel("");
	JLabel lblApPat = new JLabel("");
	JLabel lblApMat = new JLabel("");
	JLabel lblPuesto = new JLabel("");
	JLabel lblSalario = new JLabel("");
	JLabel lblMonto = new JLabel("Introduce el Monto del Prestamo");
	JLabel lblPrecio = new JLabel("$");
	JSeparator separator = new JSeparator();
	JComboBox comboBoxTipoNomina = new JComboBox();
	JLabel lbltiponomina = new JLabel("Introduce el tipo de Nomina del Empleado");
	JButton btnGuardar = new JButton("Guardar");
	JButton bntCancelar = new JButton("Cancelar");
	JLabel lblPreciio = new JLabel("$");
	JLabel lblIdEmp = new JLabel("");
	JLabel lblIdPuesto = new JLabel("");
	JFormattedTextField formattedTextFieldPagos = new JFormattedTextField();
	InternalFrameNomina nomina = new InternalFrameNomina();

	/**
	 * Create the frame.
	 */
	public InternalFrameImpulsoras() {
		setBounds(100, 100, 1971, 1051);
		setVisible(true);
		setTitle("Impulsoras");
		getContentPane().setLayout(null);
		
		JPanel panelImpul = new JPanel();
		panelImpul.setBorder(new TitledBorder(null, "Reporte Impulsoras", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 248, 220)));
		//panelImpul.setBorder(new TitledBorder(null, "Reporte Impulsoras", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(176, 196, 222)));
		panelImpul.setBounds(0, 0, 1971, 1051);
		panelImpul.setBackground(new Color(147,140,147));
		panelImpul.setLayout(null);
		getContentPane().add(panelImpul);
		
		JLabel lblNewLabel = new JLabel("Nombre Empleado(a):");
		lblNewLabel.setForeground(new Color(255, 250, 205));
		lblNewLabel.setBounds(26, 25, 171, 14);
		panelImpul.add(lblNewLabel);
		
		textFieldImpul = new JTextField(new Integer(3));
		textFieldImpul.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				rowSortere.setRowFilter(RowFilter.regexFilter(textFieldImpul.getText().toUpperCase(), IdBusquedaEmplee));
				
			}
		});
		textFieldImpul.setBounds(26, 49, 297, 25);
		panelImpul.add(textFieldImpul);
		textFieldImpul.setColumns(10);
		
		JScrollPane scrollPaneImpul = new JScrollPane();
		scrollPaneImpul.setBounds(23, 81, 669, 396);
		scrollPaneImpul.setViewportBorder(null);
		scrollPaneImpul.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneImpul.setViewportView(tableReporteImpul);
		panelImpul.add(scrollPaneImpul);
		
		JButton btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ArrayList<String> listaEjercicio = new ArrayList<String>();
				listaEjercicio = nomina.buscarTipoNomina();
				comboBoxTipoNomina.addItem("Seleccione Una");
				for(int i = 0; i<listaEjercicio.size();i++){
					comboBoxTipoNomina.addItem(listaEjercicio.get(i));
				}
				
				redireccionarEmpleado();
				bntCancelar.setVisible(true);
				btnGuardar.setVisible(true);
				lblIdEmp.setVisible(true);
				lblNombre.setVisible(true);
				lblApPat.setVisible(true);
				lblApMat.setVisible(true);
				lblIdPuesto.setVisible(true);
				lblSalario.setVisible(true);
				lblPrecio.setVisible(true);
				lblPreciio.setVisible(true);
				lbltiponomina.setVisible(true);
				lblMonto.setVisible(true);
				textFieldMonto.setVisible(true);
//				lblFechainicio.setVisible(true);
//				lblFechaFinal.setVisible(true);
//				dateChoosefechaFinal.setVisible(true);
//				dateChooserfechaincial.setVisible(true);
				separator.setVisible(true);
				comboBoxTipoNomina.setVisible(true);
				lblPagos.setVisible(true);
				formattedTextFieldPagos.setVisible(true);
				
				
				
			}
		});
		btnSeleccionar.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("accept.png"))));
		btnSeleccionar.setBounds(312, 495, 128, 30);
		panelImpul.add(btnSeleccionar);
		
		
		textFieldMonto.setColumns(10);
		textFieldMonto.setBounds(921, 219, 194, 25);
		panelImpul.add(textFieldMonto);
		
		
		lblMonto.setForeground(new Color(255, 250, 205));
		lblMonto.setBounds(911, 194, 271, 14);
		panelImpul.add(lblMonto);
		
		
		separator.setBounds(911, 186, 300, 2);
		panelImpul.add(separator);
		
		
		lblPrecio.setForeground(new Color(255, 250, 205));
		lblPrecio.setBounds(1209, 131, 46, 14);
		panelImpul.add(lblPrecio);
		
		
		comboBoxTipoNomina.setBounds(921, 280, 430, 25);
		panelImpul.add(comboBoxTipoNomina);
		
	
		lbltiponomina.setForeground(new Color(255, 250, 205));
		lbltiponomina.setBounds(911, 255, 297, 14);
		panelImpul.add(lbltiponomina);
		
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertarImpul();
			}
		});
		
		
		btnGuardar.setBounds(1009, 417, 128, 30);
		panelImpul.add(btnGuardar);
		bntCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		
		bntCancelar.setBounds(1146, 417, 128, 30);
		panelImpul.add(bntCancelar);
		
		
		lblPreciio.setForeground(new Color(255, 250, 205));
		lblPreciio.setBounds(911, 224, 46, 14);
		panelImpul.add(lblPreciio);
		
		
		lblNombre.setForeground(new Color(255, 250, 205));
		lblNombre.setBounds(961, 101, 247, 14);
		panelImpul.add(lblNombre);
		
		
		lblApPat.setForeground(new Color(255, 250, 205));
		lblApPat.setBounds(911, 131, 282, 14);
		panelImpul.add(lblApPat);
		
		
		lblApMat.setForeground(new Color(255, 250, 205));
		lblApMat.setBounds(911, 161, 297, 14);
		panelImpul.add(lblApMat);
		
		
		lblPuesto.setForeground(new Color(255, 250, 205));
		lblPuesto.setBounds(1270, 101, 292, 14);
		panelImpul.add(lblPuesto);
		
		
		lblSalario.setForeground(new Color(255, 250, 205));
		lblSalario.setBounds(1218, 131, 344, 14);
		panelImpul.add(lblSalario);
		
	
		lblIdEmp.setForeground(new Color(255, 250, 205));
		lblIdEmp.setBounds(911, 101, 46, 14);
		panelImpul.add(lblIdEmp);
		
		
		lblIdPuesto.setForeground(new Color(255, 250, 205));
		lblIdPuesto.setBounds(1209, 101, 46, 14);
		panelImpul.add(lblIdPuesto);
		
		formattedTextFieldPagos.setValue(new Integer(0));
		formattedTextFieldPagos.setColumns(10);
		formattedTextFieldPagos.setBounds(911, 346, 86, 25);
		panelImpul.add(formattedTextFieldPagos);
		
		
		lblPagos.setForeground(new Color(255, 250, 205));
		lblPagos.setBounds(911, 321, 282, 14);
		panelImpul.add(lblPagos);
		

	}
	
	
public int insertarImpul(){
		
		ArrayList<Object> listaNominaCatorcena = new ArrayList<Object>();
		listaNominaCatorcena = nomina.getListaNominaCatorcena();
		System.out.println("*********");
		for(int i = 0; i<listaNominaCatorcena.size();i++){
			System.out.println("indice fecha catorcena: "+ i + " |valor Catorcena: " + listaNominaCatorcena.get(i));
		}
		System.out.println("*********");

		
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
		int claveDeduccionAnticipoNomina=24;
		double valorClaveInternaPercepcion=0.0;
		PoolNYCH nych = new PoolNYCH();
		Connection conImpul =null;
		int resultado = 0;
		Integer pagos = (Integer) formattedTextFieldPagos.getValue();
		String sqlInsert="INSERT INTO dbo.CALCULO_NOMINA (ID_EMPLEADO,ID_PERCEPCION,ID_DEDUCCION,VALOR_PERCEPCION,VALOR_DEDUCCION,ID_PUESTO,FECHA_CALCULO,DIAS,ID_TIPO_NOMINA)" 
				+ ""
				+ "VALUES ("+ lblIdEmp.getText()+","+ claveInternaPercepcion +","+ claveDeduccionAnticipoNomina +","+ valorClaveInternaPercepcion +","
				//+ ""+ Double.parseDouble(textFieldMontoImuvi.getText())/parcialidadesAPagar +","+ lblIdPuesto.getText() +",'"+Calendar.getInstance().getTime()+"','" + 14 +"','"+ dateChooserfechaincial.getDate()+"','"+  dateChoosefechaFinal.getDate()+"',"
				+ ""+ Double.parseDouble(textFieldMonto.getText())/pagos+","+ lblIdPuesto.getText() +",'"+Calendar.getInstance().getTime()+"','" + 14 +"',"
						+ "'"+comboBoxTipoNomina.getSelectedIndex()+"')";
		System.out.println(sqlInsert);
		try {
			conImpul = nych.datasource.getConnection();
			PreparedStatement pps = conImpul.prepareStatement(sqlInsert);
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
				conImpul.close();
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
		InternalFrameMovimientos internalFrameMovimientos = new InternalFrameMovimientos();
		int fila = tableReporteImpul.getSelectedRow();
		if(fila>=0) {
			lblIdEmp.setText(tableReporteImpul.getValueAt(fila, 0).toString());
			lblNombre.setText(tableReporteImpul.getValueAt(fila, 1).toString());
			lblApPat.setText(tableReporteImpul.getValueAt(fila, 2).toString());
			lblApMat.setText(tableReporteImpul.getValueAt(fila, 3).toString());
			lblIdPuesto.setText(tableReporteImpul.getValueAt(fila, 4).toString());
			lblPuesto.setText(tableReporteImpul.getValueAt(fila, 5).toString());
			lblSalario.setText(tableReporteImpul.getValueAt(fila, 6).toString());
		}else {
			JOptionPane.showMessageDialog(null, "Registro no Seleccionado");
		}
	}
	
	public void mostrarEmpleadosParaImpul() {
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
		tableReporteImpul.setModel(modelo);

		JTableHeader th = new JTableHeader();
		JTableHeader th1 = new JTableHeader();
		Color colorSilverLight=new Color(176, 196, 222);
		Color colorNegro=new Color(0, 0, 0);
		th = tableReporteImpul.getTableHeader();
		th1 = tableReporteImpul.getTableHeader();
		Font fuente = new Font("Arial", Font.BOLD, 14); 
		th.setFont(fuente); 
		th1.setFont(fuente); 
		th.setBackground(colorSilverLight);
		th1.setBackground(colorSilverLight);
		th.setForeground(colorNegro);
		th1.setForeground(colorNegro);

		TableColumnModel columnModel = tableReporteImpul.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(80);
		columnModel.getColumn(1).setPreferredWidth(80);
		columnModel.getColumn(2).setPreferredWidth(100);
		columnModel.getColumn(3).setPreferredWidth(80);
		columnModel.getColumn(4).setPreferredWidth(80);
		columnModel.getColumn(5).setPreferredWidth(80);
		columnModel.getColumn(6).setPreferredWidth(80);


		//InternalFrameMovimientos movimientos = new InternalFrameMovimientos();
		String sqlSelect="";
		//System.out.println("id: " + movimientos.lblEmpIdPerp.getText());
		sqlSelect = "select dbo.empleados.CLAVE,dbo.empleados.nombre,dbo.empleados.apellido_paterno,dbo.empleados.apellido_materno,dbo.PUESTOS.NO_PUESTO,dbo.PUESTOS.NOMBRE_PUESTO,dbo.PUESTOS.SALARIO from dbo.empleados left join dbo.puestos on dbo.empleados.ID_puesto = dbo.puestos.no_puesto WHERE DBO.EMPLEADOS.ELIMINAR_LOGICO='"+1+"'  order by id_puesto ";
		//System.out.println(sqlSelect);
		Connection con= null;
		PoolNYCH nych = new PoolNYCH();
		String datos[] = new String[7];
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
				modelo.addRow(datos);
			}//FIN DEL WHILE

			//rowSortere = new TableRowSorter(modelo);
			tableReporteImpul.setRowSorter(rowSortere);

			tableReporteImpul.setModel(modelo);
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
}

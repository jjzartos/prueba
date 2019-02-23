package ti.snfco.NominaYCapitalHumano.service;

import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;

import org.slf4j.LoggerFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.JTable;

import java.awt.SystemColor;
import javax.swing.JScrollPane;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InternalFrameCalcularNominaTesoreria extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(InternalFrameCalcularNominaTesoreria.class);
	private JTextField textField;
//	JTextField textFieldIdPeriodo;
//	JTextField textFieldPerdiodoFechas;
	JTable tableEmpleTeso = new JTable();
	TableRowSorter rowSortere;
	int IdBusquedaEmplee = 1;
	JLabel lblTipoNomina = new JLabel("IDTipoNomina");

	public InternalFrameCalcularNominaTesoreria() {
		getContentPane().setBackground(SystemColor.controlHighlight);
		setBounds(0, 0, 1477, 633);
		setTitle("Calcular Nómina");
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Buscar Empleado:");
		lblNewLabel.setBounds(10, 11, 145, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblIcon = new JLabel("");
		lblIcon.setBounds(10, 56, 46, 37);
		lblIcon.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("icons8_Search_32px_2.png"))));
		getContentPane().add(lblIcon);
		
		
		JSeparator separator = new JSeparator();
		separator.setBounds(66, 90, 259, 9);
		getContentPane().add(separator);
		
		lblTipoNomina.setBounds(165, 11, 80, 14);
		getContentPane().add(lblTipoNomina);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				rowSortere.setRowFilter(RowFilter.regexFilter(textField.getText().toUpperCase(), IdBusquedaEmplee));
			}
		});
		textField.setBorder(null);
		textField.setBackground(SystemColor.controlHighlight);
		textField.setBounds(66, 59, 259, 30);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JScrollPane scrollPaneEmpleadosTeso = new JScrollPane();
		scrollPaneEmpleadosTeso.setViewportBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(176, 196, 222)));
		scrollPaneEmpleadosTeso.setBounds(10, 104, 637, 184);
		scrollPaneEmpleadosTeso.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneEmpleadosTeso.setViewportView(tableEmpleTeso);
		getContentPane().add(scrollPaneEmpleadosTeso);
		
		final JButton btnVerEmpleTeso = new JButton("Ver Empleados");
		btnVerEmpleTeso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				mostrarEmpleados(lblTipoNomina.getText());
				btnVerEmpleTeso.setEnabled(false);
				
			}
		});
		btnVerEmpleTeso.setBounds(335, 63, 145, 30);
		getContentPane().add(btnVerEmpleTeso);
		
//		JLabel lblNewLabel = new JLabel("Periodo");
//		lblNewLabel.setBounds(10, 35, 66, 30);
//		getContentPane().add(lblNewLabel);
		
//		textFieldIdPeriodo = new JTextField();
//		textFieldIdPeriodo.setBounds(79, 32, 42, 30);
//		getContentPane().add(textFieldIdPeriodo);
//		textFieldIdPeriodo.setColumns(10);
//		
//		textFieldPerdiodoFechas = new JTextField();
//		textFieldPerdiodoFechas.setBounds(128, 32, 286, 30);
//		getContentPane().add(textFieldPerdiodoFechas);
//		textFieldPerdiodoFechas.setColumns(10);
		
//		JButton btnNewButton = new JButton("Calcular");
//		btnNewButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				
//			}
//		});
//		btnNewButton.setBounds(79, 105, 89, 30);
//		getContentPane().add(btnNewButton);
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setBackground(SystemColor.controlHighlight);
		lblFondo.setBounds(0, 0, 1461, 604);
		lblFondo.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("walll.png"))));
		getContentPane().add(lblFondo);

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
		modelo.addColumn("PUESTO");
		modelo.addColumn("SALARIO");
		modelo.addColumn("DEPENDENCIA");
		tableEmpleTeso.setModel(modelo);

		JTableHeader th = new JTableHeader();
		JTableHeader th1 = new JTableHeader();
		Color colorSilverLight=new Color(176, 196, 222);
		Color colorNegro=new Color(0, 0, 0);
		th = tableEmpleTeso.getTableHeader();
		th1 = tableEmpleTeso.getTableHeader();
		Font fuente = new Font("Arial", Font.BOLD, 14); 
		th.setFont(fuente); 
		th1.setFont(fuente); 
		th.setBackground(colorSilverLight);
		th1.setBackground(colorSilverLight);
		th.setForeground(colorNegro);
		th1.setForeground(colorNegro);

		TableColumnModel columnModel = tableEmpleTeso.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(80);
		columnModel.getColumn(1).setPreferredWidth(80);
		columnModel.getColumn(2).setPreferredWidth(100);
		columnModel.getColumn(3).setPreferredWidth(80);
		columnModel.getColumn(4).setPreferredWidth(80);
		columnModel.getColumn(5).setPreferredWidth(80);
		columnModel.getColumn(6).setPreferredWidth(80);


		String sqlSelect="";
		sqlSelect = "SELECT DBO.EMPLEADOS.CLAVE,DBO.EMPLEADOS.NOMBRE,DBO.EMPLEADOS.APELLIDO_PATERNO,DBO.EMPLEADOS.APELLIDO_MATERNO,\r\n"
				+ "dbo.puestos.nombre_puesto,dbo.puestos.salario,DBO.DEPENDENCIAS.UNIDAD_RESPONSABLE \r\n"
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
				modelo.addRow(datos);
			}//FIN DEL WHILE

			rowSortere = new TableRowSorter(modelo);
			tableEmpleTeso.setRowSorter(rowSortere);

			tableEmpleTeso.setModel(modelo);
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

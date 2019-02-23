package ti.snfco.NominaYCapitalHumano.service;

import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.slf4j.LoggerFactory;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.ScrollPaneConstants;
import javax.swing.JSeparator;
import javax.swing.JTable;

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

import javax.swing.JScrollPane;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InternalFrameHistorial extends JInternalFrame {

	
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(InternalFrameHistorial.class);
	private static final long serialVersionUID = 1L;
	TableRowSorter rowSorter;
	int IdBusqueda = 2;
	public JTable tableHistorialEmpelado = new JTable();
	JLabel lblClave = new JLabel("Clave");

	public InternalFrameHistorial() {
		setBounds(0, 45, 1500, 551);
		setVisible(true);
		setTitle("Historial del Empleado");
		getContentPane().setBackground(SystemColor.controlHighlight);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		scrollPane.setBounds(10, 99, 1412, 316);
		scrollPane.setViewportBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setViewportView(tableHistorialEmpelado);
		getContentPane().add(scrollPane);
		
		lblClave.setBounds(10, 0, 46, 14);
		getContentPane().add(lblClave);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCerrar.setBounds(1333, 426, 89, 30);
		getContentPane().add(btnCerrar);
		
		JLabel lblFoto = new JLabel("");
		lblFoto.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("walll.png"))));
		lblFoto.setBounds(0, 0, 1484, 522);
		getContentPane().add(lblFoto);

	}
	
	public void getHistorial() {
		final DefaultTableModel modeloDependencias = new DefaultTableModel(){

			public boolean isCellEditable(int row, int column) {
				if(column==2){
					return true;
				}else {
					return false;
				}
			}
		};
		
		modeloDependencias.addColumn("CLAVE");
		modeloDependencias.addColumn("NOMBRE");
		modeloDependencias.addColumn("APELLIDO PATERNO");
		modeloDependencias.addColumn("APELLIDO MATERNO");
		modeloDependencias.addColumn("FECHA_MOVIMIENTO");
		modeloDependencias.addColumn("DEPENDENCIA ANTERIOR");
		modeloDependencias.addColumn("DEPENDENCIA ACTUAL");
		modeloDependencias.addColumn("PUESTO ANTERIOR");
		modeloDependencias.addColumn("PUESTO ACTUAL");
		tableHistorialEmpelado.setModel(modeloDependencias);
		tableHistorialEmpelado.setBackground(Color.WHITE);

		JTableHeader th = new JTableHeader();
		Color colorSilverLight=new Color(176, 196, 222);
		Color colorNegro=new Color(0, 0, 0);
		th = tableHistorialEmpelado.getTableHeader();
		Font fuente = new Font("Arial", Font.BOLD, 14); 
		th.setFont(fuente); 
		th.setBackground(colorSilverLight);
		th.setForeground(colorNegro);

		TableColumnModel columnModel = tableHistorialEmpelado.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(100);
		columnModel.getColumn(1).setPreferredWidth(400);
		columnModel.getColumn(2).setPreferredWidth(400);
		columnModel.getColumn(3).setPreferredWidth(400);
		columnModel.getColumn(4).setPreferredWidth(400);
		columnModel.getColumn(5).setPreferredWidth(400);
		columnModel.getColumn(6).setPreferredWidth(400);
		columnModel.getColumn(7).setPreferredWidth(400);
		columnModel.getColumn(8).setPreferredWidth(400);

		String sqlSelect="";
		String sql="";
		sqlSelect = "select E.clave,E.nombre,E.APELLIDO_PATERNO,E.APELLIDO_MATERNO,H.FECHA_MOVIMIENTO,D.UNIDAD_RESPONSABLE,DA.UNIDAD_RESPONSABLE,\r\n" + 
				"P.NOMBRE_PUESTO,PA.NOMBRE_PUESTO\r\n" + 
				"from HISTORIAL_EMPLEADO AS H\r\n" + 
				"inner join empleados AS E on H.CLAVE_EMPLEADO =  E.CLAVE\r\n" + 
				"inner join dependencias AS D on H.DEPENDENCIA_ANTERIOR = D.ID_UNIDAD\r\n" + 
				"inner join dependencias AS DA on H.DEPENDENCIA_ACTUAL = DA.ID_UNIDAD\r\n" + 
				"inner join puestos AS P on H.PUESTO_ANTERIOR = P.NO_PUESTO\r\n" + 
				"inner join puestos AS PA on H.PUESTO_ACTUAL = PA.NO_PUESTO \r\n" + 
				"where H.CLAVE_EMPLEADO = '"+lblClave.getText()+"'\r\n" + 
				"";
		System.out.println("SQL: " + sqlSelect);
		String datos[] = new String[11];
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
				datos[3] = resultSet.getString(4);
				datos[4] = resultSet.getString(5);
				datos[5] = resultSet.getString(6);
				datos[6] = resultSet.getString(7);
				datos[7] = resultSet.getString(8);
				datos[8] = resultSet.getString(9);
				modeloDependencias.addRow(datos);
			}//FIN DEL WHILE

			rowSorter = new TableRowSorter(modeloDependencias);
			tableHistorialEmpelado.setRowSorter(rowSorter);
			
			tableHistorialEmpelado.setModel(modeloDependencias);
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

	
}

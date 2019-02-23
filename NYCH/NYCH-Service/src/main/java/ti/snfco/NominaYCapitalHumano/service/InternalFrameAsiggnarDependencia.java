package ti.snfco.NominaYCapitalHumano.service;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

import org.slf4j.LoggerFactory;

public class InternalFrameAsiggnarDependencia extends JInternalFrame {

	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(InternalFrameAsiggnarDependencia.class);
	private static final long serialVersionUID = 1L;
	private JTextField textFieldBuscarDependencia;
	TableRowSorter rowSorterDependencia;
	int IdBusquedaDependencia = 2;
	public JTable tabledependencias = new JTable();
	JLabel lblClave = new JLabel("clave");
	JLabel lblDependencia = new JLabel("dependencia");
	JLabel lblIdDependenciaAct = new JLabel("IdDepen");
	JLabel lblIdPuestoAct = new JLabel("IdPuesto");
	
	public InternalFrameAsiggnarDependencia() {
		setBounds(865, 350, 515, 334);
		getContentPane().setLayout(null);
		setTitle("Asignar Dependencia");
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		panel.setBounds(0, 0, 499, 305);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		scrollPane.setBounds(10, 54, 465, 206);
		scrollPane.setViewportBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setViewportView(tabledependencias);
		panel.add(scrollPane);
		
		textFieldBuscarDependencia = new JTextField();
		textFieldBuscarDependencia.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				rowSorterDependencia.setRowFilter(RowFilter.regexFilter(textFieldBuscarDependencia.getText().toUpperCase(), IdBusquedaDependencia));
			}
		});
		textFieldBuscarDependencia.setColumns(10);
		textFieldBuscarDependencia.setBorder(null);
		textFieldBuscarDependencia.setBackground(SystemColor.menu);
		textFieldBuscarDependencia.setBounds(53, 11, 229, 28);
		panel.add(textFieldBuscarDependencia);
		
		JLabel lblIcon = new JLabel();
		lblIcon.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("icons8_Search_32px_2.png"))));
		lblIcon.setBounds(10, 11, 46, 28);
		panel.add(lblIcon);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(176, 196, 222));
		separator.setBackground(new Color(176, 196, 222));
		separator.setBounds(53, 43, 229, 3);
		panel.add(separator);
		
		JButton btnActualizar = new JButton("Asignar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				InternalFrameNuevoEmpleado internalFrameNuevoEmpleado = new InternalFrameNuevoEmpleado();
				int fila = tabledependencias.getSelectedRow();
				if(fila>=0) {
					String noDepen = tabledependencias.getValueAt(fila, 0).toString();
					String depend = tabledependencias.getValueAt(fila, 2).toString();
					
//					FormularioPrincipal.desktopPane.add(internalFrameNuevoEmpleado);
//
//					internalFrameNuevoEmpleado.show();
//					internalFrameNuevoEmpleado.setFrameIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("user_add.png"))));
//					internalFrameNuevoEmpleado.setIconifiable(true);
//					internalFrameNuevoEmpleado.setClosable(true);
//					internalFrameNuevoEmpleado.setResizable(true);
//					internalFrameNuevoEmpleado.setMaximizable(true);
//					internalFrameNuevoEmpleado.setVisible(true);
//					internalFrameNuevoEmpleado.toFront();
					InternalFrameNuevoEmpleado.textFieldIdDepen.setText(noDepen);
					//InternalFrameNuevoEmpleado.textFieldAsignarDepen.setText(depend.substring(0, 27));
					InternalFrameNuevoEmpleado.textFieldAsignarDepen.setText(depend);
					InternalFrameNuevoEmpleado.lblIdDependencia.setText(noDepen);
					
					dispose();
					
					
				}else {
					JOptionPane.showConfirmDialog(null, "No ha seleccionado ningun puesto");
				}
				
				
			}
		});
		btnActualizar.setBounds(386, 271, 89, 23);
		panel.add(btnActualizar);
		
		lblClave.setBounds(292, 29, 46, 14);
		panel.add(lblClave);
		
		lblDependencia.setBounds(323, 29, 89, 14);
		panel.add(lblDependencia);
		
		lblIdPuestoAct.setBounds(292, 11, 46, 14);
		panel.add(lblIdPuestoAct);
		
		lblIdDependenciaAct.setBounds(342, 11, 46, 14);
		panel.add(lblIdDependenciaAct);
		
		JButton btnNewButton = new JButton("Cerrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(10, 271, 89, 23);
		panel.add(btnNewButton);
		
//		lblpuesto.setBounds(329, 29, 106, 14);
//		panel.add(lblpuesto);

	}
	
	public void getDependencias() {
		final DefaultTableModel modeloDependencias = new DefaultTableModel(){

			public boolean isCellEditable(int row, int column) {
				if(column==2){
					return true;
				}else {
					return false;
				}
			}
		};
		modeloDependencias.addColumn("ID_UNIDAD");
		modeloDependencias.addColumn("UR");
		modeloDependencias.addColumn("UNIDAD_RESPONSABLE");
		tabledependencias.setModel(modeloDependencias);
		tabledependencias.setBackground(Color.WHITE);

		JTableHeader th = new JTableHeader();
		Color colorSilverLight=new Color(176, 196, 222);
		Color colorNegro=new Color(0, 0, 0);
		th = tabledependencias.getTableHeader();
		Font fuente = new Font("Arial", Font.BOLD, 14); 
		th.setFont(fuente); 
		th.setBackground(colorSilverLight);
		th.setForeground(colorNegro);

		TableColumnModel columnModel = tabledependencias.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(100);
		columnModel.getColumn(1).setPreferredWidth(400);
		columnModel.getColumn(2).setPreferredWidth(400);

		String sqlSelect="";
		String sql="";
		sqlSelect = "SELECT ID_UNIDAD,UR,UNIDAD_RESPONSABLE FROM dbo.DEPENDENCIAS";
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
				modeloDependencias.addRow(datos);
			}//FIN DEL WHILE

			rowSorterDependencia = new TableRowSorter(modeloDependencias);
			tabledependencias.setRowSorter(rowSorterDependencia);
			
			tabledependencias.setModel(modeloDependencias);
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

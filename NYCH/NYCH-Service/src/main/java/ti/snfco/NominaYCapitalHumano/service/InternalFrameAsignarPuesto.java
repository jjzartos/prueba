package ti.snfco.NominaYCapitalHumano.service;

import java.awt.Color;
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

public class InternalFrameAsignarPuesto extends JInternalFrame {
	
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(InternalFrameAsignarPuesto.class);
	private static final long serialVersionUID = 1L;
	private JTextField textFieldBuscarPuesto;
	TableRowSorter rowSorterPuesto;
	int IdBusquedaPuesto = 1;
	public JTable tablePuestos = new JTable();
	JLabel lblClave = new JLabel("clave");
	JLabel lblDependencia = new JLabel("dependencia");
//	JLabel lblpuesto = new JLabel("puesto");
	
	JLabel lblIdDependenciaAct = new JLabel("IdDepen");
	JLabel lblIdPuestoAct = new JLabel("IdPuesto");

	public InternalFrameAsignarPuesto() {
		setBounds(865, 350, 515, 334);
		getContentPane().setLayout(null);
		setTitle("Asignar Puesto");
		
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
		scrollPane.setViewportView(tablePuestos);
		panel.add(scrollPane);
		
		textFieldBuscarPuesto = new JTextField();
		textFieldBuscarPuesto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				rowSorterPuesto.setRowFilter(RowFilter.regexFilter(textFieldBuscarPuesto.getText().toUpperCase(), IdBusquedaPuesto));
			}
		});
		textFieldBuscarPuesto.setColumns(10);
		textFieldBuscarPuesto.setBorder(null);
		textFieldBuscarPuesto.setBackground(SystemColor.menu);
		textFieldBuscarPuesto.setBounds(53, 11, 229, 28);
		panel.add(textFieldBuscarPuesto);
		
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
				int fila = tablePuestos.getSelectedRow();
				if(fila>=0) {
					String noPuesto = tablePuestos.getValueAt(fila, 0).toString();
					String puesto = tablePuestos.getValueAt(fila, 1).toString();
					String salario = tablePuestos.getValueAt(fila, 2).toString();
					
					System.out.println("no de puesto: "+ noPuesto);
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
					InternalFrameNuevoEmpleado.textFieldNoPuesto.setText(noPuesto);
					InternalFrameNuevoEmpleado.textFieldAsignarPuesto.setText(puesto);
					InternalFrameNuevoEmpleado.lblSalario.setText(salario);
					InternalFrameNuevoEmpleado.lblIdPuesto.setText(noPuesto);
					
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
	
	
	
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
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
}

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

public class InternalFrameActualizarDependencia extends JInternalFrame {

	
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(InternalFrameActualizarDependencia.class);
	private static final long serialVersionUID = 1L;
	TableRowSorter rowSorter;
	int IdBusqueda = 2;
	public JTable tableDependencias = new JTable();
	private JTextField textFieldBuscarDependencia;
	JLabel lblClave = new JLabel("clave");
	JLabel lblPuesto = new JLabel("Puesto");
	JLabel lblIdPuesto = new JLabel("IdPuesto");
	JLabel lblIdDependencia = new JLabel("IdDependencia");

	public InternalFrameActualizarDependencia() {
		setBounds(865, 350, 515, 334);
		getContentPane().setLayout(null);
		setTitle("Actualizar Dependencia");
		
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
		scrollPane.setViewportView(tableDependencias);
		panel.add(scrollPane);
		
		textFieldBuscarDependencia = new JTextField();
		textFieldBuscarDependencia.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				rowSorter.setRowFilter(RowFilter.regexFilter(textFieldBuscarDependencia.getText().toUpperCase(), IdBusqueda));
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
		
		JButton btnNewButton = new JButton("Actualizar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int fila = tableDependencias.getSelectedRow();
				if(fila>=0) {
					String noDepen = tableDependencias.getValueAt(fila, 0).toString();
					String depen = tableDependencias.getValueAt(fila, 2).toString();
					
					InternalFrameCambioPuestos internalFrameCambioPuestos =  new InternalFrameCambioPuestos();
					FormularioPrincipal.desktopPane.add(internalFrameCambioPuestos);
					internalFrameCambioPuestos.show();
					internalFrameCambioPuestos.setBounds(0, 45, 1551, 551);
					internalFrameCambioPuestos.setFrameIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("edit.png"))));
					internalFrameCambioPuestos.setIconifiable(true);
					internalFrameCambioPuestos.setClosable(true);
					internalFrameCambioPuestos.setResizable(true);
					internalFrameCambioPuestos.setMaximizable(true);
					internalFrameCambioPuestos.setVisible(true);
					internalFrameCambioPuestos.toFront();
					internalFrameCambioPuestos.lblclave.setText(lblClave.getText());
					internalFrameCambioPuestos.lblIdNuevoDependencia.setText(noDepen);
					internalFrameCambioPuestos.actualizaD();
					internalFrameCambioPuestos.getEmpleadosCambioPuesto(FormularioPrincipal.lblIdTipoNomina.getText());
//					internalFrameCambioPuestos.lblclave.setVisible(false);
//					internalFrameCambioPuestos.lblIdNuevoPuesto.setVisible(false);
//					internalFrameCambioPuestos.lblIdNuevoDependencia.setVisible(false);
					
					internalFrameCambioPuestos.lblActualDependencia.setText(depen);
					internalFrameCambioPuestos.lblActualPuesto.setText(lblPuesto.getText());
					internalFrameCambioPuestos.lblIdPuestoCam.setText(lblIdPuesto.getText());
					internalFrameCambioPuestos.lblIdNuevoPuesto.setText(lblIdPuesto.getText());
					internalFrameCambioPuestos.lblIdDependenciaCam.setText(lblIdDependencia.getText());
					
					internalFrameCambioPuestos.insertarHistorialEmpleadoDependencia();
					internalFrameCambioPuestos.getEmpleadosCambioPuesto(FormularioPrincipal.lblIdTipoNomina.getText());
					dispose();
//					internalFrameCambioPuestos.dispose();
					
					
				}else {
					JOptionPane.showConfirmDialog(null, "No ha seleccionado ningun puesto");
				}
				
				
			}
		});
		btnNewButton.setBounds(386, 271, 89, 23);
		panel.add(btnNewButton);
		
		lblClave.setBounds(292, 29, 46, 14);
		panel.add(lblClave);
		
		lblPuesto.setBounds(366, 29, 89, 14);
		panel.add(lblPuesto);
		
		lblIdPuesto.setBounds(292, 11, 46, 14);
		panel.add(lblIdPuesto);
		
		lblIdDependencia.setBounds(366, 11, 109, 14);
		panel.add(lblIdDependencia);
		
		JButton btnNewButton_1 = new JButton("Cerrar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(10, 271, 89, 23);
		panel.add(btnNewButton_1);
		

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
		tableDependencias.setModel(modeloDependencias);
		tableDependencias.setBackground(Color.WHITE);

		JTableHeader th = new JTableHeader();
		Color colorSilverLight=new Color(176, 196, 222);
		Color colorNegro=new Color(0, 0, 0);
		th = tableDependencias.getTableHeader();
		Font fuente = new Font("Arial", Font.BOLD, 14); 
		th.setFont(fuente); 
		th.setBackground(colorSilverLight);
		th.setForeground(colorNegro);

		TableColumnModel columnModel = tableDependencias.getColumnModel();
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

			rowSorter = new TableRowSorter(modeloDependencias);
			tableDependencias.setRowSorter(rowSorter);
			
			tableDependencias.setModel(modeloDependencias);
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

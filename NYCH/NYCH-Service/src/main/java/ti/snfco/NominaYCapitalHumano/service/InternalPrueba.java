package ti.snfco.NominaYCapitalHumano.service;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;

import java.awt.SystemColor;
import java.awt.Window;
import java.awt.Dialog.ModalityType;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

import org.slf4j.LoggerFactory;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JLabel;

public class InternalPrueba extends JInternalFrame {
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(InternalPrueba.class);
	private static final long serialVersionUID = 1L;
	private JTextField textFieldPrueba;
	JTable tablePrueba =  new JTable();
	TableRowSorter rowSorter;
	int IdBusquedaEmple = 1;
	JLabel lblNombre = new JLabel("New label");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InternalPrueba frame = new InternalPrueba();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InternalPrueba() {
		setBounds(0, 0, 1052, 481);
		getContentPane().setLayout(null);
		
		textFieldPrueba = new JTextField();
		textFieldPrueba.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				
				 rowSorter.setRowFilter(RowFilter.regexFilter(textFieldPrueba.getText().toUpperCase(), IdBusquedaEmple));
				
			}
		});
		textFieldPrueba.setColumns(10);
		textFieldPrueba.setBorder(null);
		textFieldPrueba.setBackground(SystemColor.controlHighlight);
		textFieldPrueba.setBounds(73, 36, 292, 28);
		getContentPane().add(textFieldPrueba);
		
		JScrollPane scrollPanePrueba = new JScrollPane();
		scrollPanePrueba.setViewportBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		scrollPanePrueba.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPanePrueba.setBounds(25, 112, 581, 214);
		scrollPanePrueba.setViewportView(tablePrueba);
		getContentPane().add(scrollPanePrueba);
		
		JButton btnNewButton = new JButton("buscar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//mostrarEmpleadosPrueba();
			}
		});
		btnNewButton.setBounds(517, 84, 89, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Seleccionar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object selection = null;
				if(tablePrueba.getSelectedRow() != -1) {
		            selection = tablePrueba.getValueAt(tablePrueba.getSelectedRow(), 1);
		            lblNombre.setText(""+selection);
				System.out.println("selection: " + selection);
				}else {
					System.out.println("no selecciono ningun empleado");
				}
				
			}
		});
		btnNewButton_1.setBounds(245, 337, 89, 23);
		getContentPane().add(btnNewButton_1);
		
		JPanel panel = new JPanel();
		panel.setBounds(634, 36, 344, 324);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		
		lblNombre.setBounds(10, 25, 324, 14);
		panel.add(lblNombre);
		
		JButton btnNewButton_2 = new JButton("Find");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int fila =  tablePrueba.getSelectedRow();
				
				String empleado = textFieldPrueba.getText();
				String sql = "";
			}
		});
		btnNewButton_2.setBounds(139, 78, 89, 23);
		getContentPane().add(btnNewButton_2);
		
		//mostrarEmpleadosPrueba();

	}
	
	public void mostrarEmpleadosPrueba() {
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
		;
		modelo.addColumn("CLAVE");
		modelo.addColumn("NOMBRE");
		modelo.addColumn("APELLIDO PATERNO");
		modelo.addColumn("APELLIDO MATERNO");

		tablePrueba.setModel(modelo);
		tablePrueba.setBackground(Color.WHITE);

		JTableHeader th = new JTableHeader();
		Color colorSilverLight = new Color(176, 196, 222);
		Color colorNegro = new Color(0, 0, 0);
		th = tablePrueba.getTableHeader();
		Font fuente = new Font("Arial", Font.BOLD, 14);
		th.setFont(fuente);
		th.setBackground(colorSilverLight);
		th.setForeground(colorNegro);

		TableColumnModel columnModel = tablePrueba.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(100);
		columnModel.getColumn(1).setPreferredWidth(200);
		columnModel.getColumn(2).setPreferredWidth(200);
		columnModel.getColumn(3).setPreferredWidth(200);

		String sqlSelect = "";
			sqlSelect="SELECT DBO.EMPLEADOS.CLAVE,DBO.EMPLEADOS.NOMBRE,DBO.EMPLEADOS.APELLIDO_PATERNO,DBO.EMPLEADOS.APELLIDO_MATERNO\r\n" +  
					"from empleados\r\n" + 
					"WHERE DBO.EMPLEADOS.id_ejercicios = '"+1+"' and ELIMINAR_LOGICO='1'\r\n" ;
//			System.out.println("Sql prueba: "+ sqlSelect);
			Object datos[] = new String[4];
			PoolNYCH nych = new PoolNYCH();
			Connection con = null;
			Statement st;
			ResultSet resultSet;
			try {
				con = nych.datasource.getConnection();
				st = con.createStatement();
				resultSet = st.executeQuery(sqlSelect);
				while (resultSet.next()) {// && resPuesto.next() && resDependencia.next() && resEjercicio.next()
					datos[0] = resultSet.getString(1);
					datos[1] = resultSet.getString(2);
					datos[2] = resultSet.getString(3);
					datos[3] = resultSet.getString(4);
					modelo.addRow(datos);
				}
				try {
					rowSorter = new TableRowSorter(modelo);
					tablePrueba.setRowSorter(rowSorter);
				}catch(Exception em) {
					em.printStackTrace();
					StringWriter errors = new StringWriter();
					em.printStackTrace(new PrintWriter(errors));
					LOG.info("Excepcion: " + errors);
					JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");
				}
				tablePrueba.setModel(modelo);
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
	
	
	public void windowOpened(ActionEvent e) {
		final long SLEEP_TIME = 1 * 1000;
//		SwingWorker<Void, Void> mySwingWorker = new SwingWorker<Void, Void>() {
//			protected Void doInBackground() throws Exception {
//				   System.out.println("doInBackground() esta en el hilo "
//			                + Thread.currentThread().getName());
//				mostrarEmpleadosPrueba();
//
//				Thread.sleep(SLEEP_TIME);
//				return null;
//			}
//		};
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					 System.out.println("doInBackground() esta en el hilo "
				                + Thread.currentThread().getName());
					mostrarEmpleadosPrueba();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		Window win = SwingUtilities.getWindowAncestor((AbstractButton) e.getSource());
		final JDialog dialog = new JDialog(win, "Buscando", ModalityType.APPLICATION_MODAL);
		
//		mySwingWorker.addPropertyChangeListener(new PropertyChangeListener() {
//
//			public void propertyChange(PropertyChangeEvent evt) {
//				if (evt.getPropertyName().equals("state")) {
//					if (evt.getNewValue() == SwingWorker.StateValue.DONE) {
//						dialog.dispose();
//					}
//				}
//			}
//		});
//		mySwingWorker.execute();
		
		
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

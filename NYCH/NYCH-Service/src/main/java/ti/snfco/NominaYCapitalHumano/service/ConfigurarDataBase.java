package ti.snfco.NominaYCapitalHumano.service;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import org.slf4j.LoggerFactory;

public class ConfigurarDataBase extends JFrame implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ConfigurarDataBase.class);
	private JPanel contentPane;
	private JTextField textFieldServidor;
	private JTextField textFieldDB;
	private JTextField textFieldUsuario;
	private JPasswordField passwordFieldDB;
	JTable tableParametros = new JTable();

	public ConfigurarDataBase() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("process.png")));
		setBounds(100, 100, 538, 265);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setTitle("Configuracion");
		contentPane.setLayout(null);
		
		JPanel panelDB = new JPanel();
		panelDB.setBorder(new TitledBorder(null, "Par\u00E1metros", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(176, 196, 222)));
		panelDB.setBounds(0, 0, 524, 231);
		contentPane.add(panelDB);
		panelDB.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Servidor:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(21, 34, 132, 14);
		panelDB.add(lblNewLabel);
		
		JLabel lblUsuario = new JLabel("Base de Datos:");
		lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsuario.setBounds(21, 70, 132, 14);
		panelDB.add(lblUsuario);
		
		JLabel lblPasswordDatabase = new JLabel("Password DataBase:");
		lblPasswordDatabase.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPasswordDatabase.setBounds(21, 149, 132, 14);
		panelDB.add(lblPasswordDatabase);
		
		//textFieldServidor = new JTextField("TOSHIBA-ELI");
		
		//desarrollo
//		textFieldServidor = new JTextField("192.168.235.224\\SNFCOSQLSERVER");
		
		
		//produccion
		textFieldServidor = new JTextField("192.168.112.5\\SQL_SANFRANCISCO");
		
		
		
		//textFieldServidor = new JTextField();
		textFieldServidor.setBounds(163, 26, 261, 30);
		textFieldServidor.setColumns(10);
		panelDB.add(textFieldServidor);
		
		//textFieldDB = new JTextField("NOMINA");
		textFieldDB = new JTextField("NYCH");
		//textFieldDB = new JTextField();
		textFieldDB.setColumns(10);
		textFieldDB.setBounds(163, 62, 261, 30);
		panelDB.add(textFieldDB);
		
		JButton btnNewButton = new JButton("Guardar");
		btnNewButton.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("accept.png"))));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//textFieldServidor.setText(textFieldServidor.getText());
			}
		});
		btnNewButton.setBounds(173, 182, 112, 30);
		panelDB.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("delete.png"))));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(288, 182, 112, 30);
		panelDB.add(btnNewButton_1);
		
		//desarrollo
//		textFieldUsuario = new JTextField("usrSanFco");
		
		
		//produccion
		textFieldUsuario = new JTextField("sa");
		
		//textFieldUsuario = new JTextField();
		textFieldUsuario.setColumns(10);
		textFieldUsuario.setBounds(163, 103, 261, 30);
		panelDB.add(textFieldUsuario);
		
		JLabel lblUsuarioDatabase = new JLabel("Usuario DataBase:");
		lblUsuarioDatabase.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsuarioDatabase.setBounds(21, 111, 132, 14); 
		panelDB.add(lblUsuarioDatabase);
		
		//passwordFieldDB = new JPasswordField("12345");
		
		//desarrollo
//		passwordFieldDB = new JPasswordField("MuyD1f1c1l");
		
		
		//produccion
		passwordFieldDB = new JPasswordField("Mun1c|p105@nF|2@nc15c0");
		
		//passwordFieldDB = new JPasswordField();
		passwordFieldDB.setBounds(163, 141, 261, 30);
		panelDB.add(passwordFieldDB);
			
		JButton btnTest = new JButton("Test");
		btnTest.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent arg0) {
				PoolNYCH poolNYCH = new PoolNYCH();
				java.sql.Connection con = null;
				try {
					con = poolNYCH.datasource.getConnection();
					if(con != null) {
						JOptionPane.showMessageDialog(null, "Conexion Exitosa");
					}
				}catch(SQLException ex) {
					StringWriter errors = new StringWriter();
					ex.printStackTrace(new PrintWriter(errors));
					LOG.info("Excepcion: "+ errors );
					JOptionPane.showMessageDialog(null, ex);
				}finally {

					try {
						con.close();
					}catch(SQLException s) {
						StringWriter errors = new StringWriter();
						s.printStackTrace(new PrintWriter(errors));
						LOG.info("Excepcion: "+ errors );
						JOptionPane.showMessageDialog(null, s);
					}
				}
			}
		});
		btnTest.setBounds(458, 197, 53, 23);
		panelDB.add(btnTest);
		
		
	}

	public JTextField getTextFieldServidor() {
		return textFieldServidor;
	}

	public void setTextFieldServidor(JTextField textFieldServidor) {
		this.textFieldServidor = textFieldServidor;
	}

	public JTextField getTextFieldDB() {
		return textFieldDB;
	}

	public void setTextFieldDB(JTextField textFieldDB) {
		this.textFieldDB = textFieldDB;
	}

	public JTextField getTextFieldUsuario() {
		return textFieldUsuario;
	}

	public void setTextFieldUsuario(JTextField textFieldUsuario) {
		this.textFieldUsuario = textFieldUsuario;
	}

	public JPasswordField getPasswordFieldDB() {
		return passwordFieldDB;
	}

	public void setPasswordFieldDB(JPasswordField passwordFieldDB) {
		this.passwordFieldDB = passwordFieldDB;
	}
	
	
//	public ArrayList<String> mostrarParametrosConexion() {
//		ArrayList<String> lista = new ArrayList<String>();
//		DefaultTableModel modelo = new DefaultTableModel();
//		modelo.addColumn("SERVIDOR");
//		modelo.addColumn("BASE DE DATOS");
//		modelo.addColumn("PUERTO");
//		modelo.addColumn("USUARIO");
//		modelo.addColumn("PASSWORD");
//
//
//		tableParametros.setModel(modelo);
//		tableParametros.setBackground(Color.WHITE);
//
//		JTableHeader th = new JTableHeader();
//		Color colorSilverLight=new Color(176, 196, 222);
//		Color colorNegro=new Color(0, 0, 0);
//		th = tableParametros.getTableHeader();
//		Font fuente = new Font("Cooper Black", Font.BOLD, 14); 
//		th.setFont(fuente); 
//		th.setBackground(colorSilverLight);
//		th.setForeground(colorNegro);
//
//
//		TableColumnModel columnModel = tableParametros.getColumnModel();
//		columnModel.getColumn(0).setPreferredWidth(320);
//		columnModel.getColumn(1).setPreferredWidth(170);
//		columnModel.getColumn(2).setPreferredWidth(100);
//		columnModel.getColumn(3).setPreferredWidth(100);
//		columnModel.getColumn(4).setPreferredWidth(140);
//
//		String sqlSelect = "select SERVIDOR,BASE_DE_DATOS,PUERTO,USUARIO,PASSWORD from dbo.PARAMETROS_CONEXION";
//
//		PoolNYCH nych = new PoolNYCH();				
//		Connection con =null;
//		String datos[] = new String[5];
//		try {
//			con = nych.datasource.getConnection();
//			Statement st = con.createStatement();
//			ResultSet resultSet = st.executeQuery(sqlSelect);
//			while(resultSet.next()) {
//				lista.add(resultSet.getString(1));
//				lista.add(resultSet.getString(2));
//				lista.add(resultSet.getString(3));
//				lista.add(resultSet.getString(4));
//				lista.add(resultSet.getString(5));
//				
//				datos[0] = resultSet.getString(1);
//				datos[1] = resultSet.getString(2);
//				datos[2] = resultSet.getString(3);
//				datos[3] = resultSet.getString(4);
//				datos[4] = resultSet.getString(5);
//				
//				modelo.addRow(datos);
//			}
//
//			
//			
//			
//			tableParametros.setModel(modelo);
//		}catch (SQLException el) {
//			el.printStackTrace();
//			StringWriter errors = new StringWriter();
//			el.printStackTrace(new PrintWriter(errors));
//			LOG.info("Excepcion: "+ errors );
//			//JOptionPane.showMessageDialog(null, el, "Error de conexion", JOptionPane.ERROR_MESSAGE);
//			JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
//		}finally {
//			try {
//				con.close();
//			} catch (SQLException ep) {
//				ep.printStackTrace();
//				StringWriter errors = new StringWriter();
//				ep.printStackTrace(new PrintWriter(errors));
//				LOG.info("Excepcion: "+ errors );
//				//JOptionPane.showMessageDialog(null, ep, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
//				JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
//			}
//		}
//		return lista;
//	}//fin del metodo
	
	
	
}

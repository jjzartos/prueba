package ti.snfco.NominaYCapitalHumano.service;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

import org.slf4j.LoggerFactory;

import com.toedter.calendar.JDateChooser;


public class InternalFrameNuevoEmpleado extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(InternalFrameNuevoEmpleado.class);
	InternalFrameEmpleado internalFrameEmpleado = new InternalFrameEmpleado();
	InternalFrameCambioPuestos internalFrameCambioPuestos = new InternalFrameCambioPuestos();
	private JTextField textFieldNvoNombre;
	private JTextField textFieldNvoApPat;
	private JTextField textFieldNvoApMat;
	private JTextField textFieldNvoCurp;
	private JTextField textFieldNvoRFC;
	private JTextField textFieldNvoRegImss;
	private JTextField textFieldNvoSalario;
	private JTextField textFieldNvoTel;
	private JTextField textFieldNvoCodPost;
	private JTextField textFieldNvoCorreo;
	JDateChooser dateChooserNvoFechaNac = new JDateChooser();
	JDateChooser dateChooserNvoFechaAltaImss = new JDateChooser();
	JDateChooser dateChooserFechaBajaImss = new JDateChooser();
	JDateChooser dateChooserFechaAltaIngreso = new JDateChooser();
	JDateChooser dateChooserFechaBajaIngreso = new JDateChooser();
	JButton btnGuardar = new JButton("Guardar");
	JButton btnCancelar = new JButton("Cancelar");
	private JTextField textFieldDireccion;
	private JTextField textFieldNvoCiudad;
	private JTextField textFieldNvoEstado;
	private JTextField textFieldNvoContacto;
	JComboBox comboBoxGenero = new JComboBox();
	JComboBox comboBoxEdoCivil = new JComboBox();
	//	JComboBox comboBoxUnidadResp = new JComboBox();
	JComboBox comboBoxLocalidad = new JComboBox();
	//	JComboBox comboBoxEjercicio = new JComboBox();
	//	JComboBox comboBoxPuesto = new JComboBox();
	private JTextField textFieldContactoEmail;
	private JTextField textFieldContactoCelular;
	private JTextField textFieldContactoDireccion;
	//	JCheckBox chckbxCatorcenal = new JCheckBox("Catorcenal",false);
	//	JCheckBox chckbxSemanal = new JCheckBox("Semanal",false);
	//	JCheckBox chckbxJubilados = new JCheckBox("Jubilados",false);
	String atributoCatorcenal;
	String atributoSemanal;
	String atributoJubilado;
	JLabel lblrutaPhoto = new JLabel("");
	JLabel lblNewFoto = new JLabel("");
	private JTextField textFieldSDINuevo;
	private JTextField textFieldNCI;
	private JTextField textFieldFI;
	private JTextField textFieldUMA;
	JLabel lblClasificacin = new JLabel("Clasificación Dispersion:");
	JLabel lblRgimen = new JLabel("Régimen:");
	JLabel lblTipoEmpleado = new JLabel("Tipo Empleado:");
	JLabel lblClasificacionEmpleado = new JLabel("Clasificacion Empleado:");
	JLabel lblTipoJornada = new JLabel("Tipo Jornada:");
	JLabel lblRiesgo = new JLabel("Riesgo:");
	JLabel lblTipoContrato = new JLabel("Tipo Contrato:");
	JComboBox comboBoxClasificacionDispersion = new JComboBox();
	JComboBox comboBoxRegimen = new JComboBox();
	JComboBox comboBoxTipoEmpleado = new JComboBox();
	JComboBox comboBoxTipoJornada = new JComboBox();
	JComboBox comboBoxRiesgo = new JComboBox();
	JComboBox comboBoxTipoContrato = new JComboBox();
	JComboBox comboBoxCalsificacionEmpleado = new JComboBox();
	JComboBox comboBoxBanco = new JComboBox();
	JTextField textFieldCtaBancaria;
	JTextField textFieldMonedero;
	JLabel lblCuentaBancaria = new JLabel("Cuenta Bancaria:");
	JLabel lblMonedero = new JLabel("Monedero:");
	JLabel lblBanco = new JLabel("Banco:");
	JTextField textFieldTipoNominaOculta;
	JLabel lblIdTipoNominaOculta = new JLabel("New label");
	static JTextField textFieldAsignarPuesto;
	//	JScrollPane scrollPaneBack = new JScrollPane();
	JScrollPane scrollPanePuestos = new JScrollPane();
	TableRowSorter rowSorterPuesto;
	int IdBusquedaPuesto = 1;
	TableRowSorter rowSorterDependencia;
	int IdBusquedaDependencia = 1;
	public JTable tablePuestos = new JTable();
	JTextField textFieldBuscarPuesto;
	private JTextField textFieldBuscarDependencia;
	//	JPanel panelPuestos = new JPanel();
	//	JPanel panelDependencias = new JPanel();
	static JTextField textFieldNoPuesto;
	static JTextField textFieldIdDepen;
	static JTextField textFieldAsignarDepen;
	static JLabel lblSalario = new JLabel("Salario");
	JTextField textFieldClave;
	JLabel lblClaveDelEmpleado = new JLabel("Clave del Empleado:");
	static JLabel lblIdPuesto = new JLabel("IdPuesto");
	static JLabel lblIdDependencia = new JLabel("IdDependencia");
	static JLabel lblClave = new JLabel("clave");

	/**
	 * Launch the application.
	 */
	//	public static void main(String[] args) {
	//		try {
	//			UIManager.setLookAndFeel("org.pushingpixels.substance.api.skin.SubstanceOfficeBlue2007LookAndFeel");
	//			//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	//		}
	//		catch (Exception ex) {
	//			Logger.getLogger(TextUI.class.getName()).log(Level.SEVERE, null, ex);
	//		}
	//		EventQueue.invokeLater(new Runnable() {
	//			public void run() {
	//				try {
	//					InternalFrameNuevoEmpleado frame = new InternalFrameNuevoEmpleado();
	//					frame.setVisible(true);
	//				} catch (Exception e) {
	//					e.printStackTrace();
	//				}
	//			}
	//		});
	//	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("unchecked")
	public InternalFrameNuevoEmpleado() {
		//setBounds(100, 100, 1971, 1051);
		setBounds(9, 15, 1520, 688);
		setTitle("Nuevo Empleado");
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.controlHighlight);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Nuevo Empleado", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(176, 196, 222)));
		panel.setBounds(0, 0, 1518, 656);
		getContentPane().add(panel);
		panel.setLayout(null);

		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = internalFrameEmpleado.tableDatosEmpleados.getSelectedRow();

				try {
				
				insertarRegistro();

				//CODIGO PARA SABER QUE CLAVE SIGUE PARA INSERT
				ArrayList<String> listaGetClaves = new ArrayList<String>();
				listaGetClaves = getClaves();
				for (int i = 0; i < listaGetClaves.size(); i++) {
//					System.out.println("claves: "+ listaGetClaves.get(i));
					textFieldClave.setVisible(true);
					lblClaveDelEmpleado.setVisible(true);
					textFieldClave.setText(listaGetClaves.get(i));
					lblClave.setText(listaGetClaves.get(i));
				}
				internalFrameCambioPuestos.insertarHistorialEmpleadoPuestoInicial(textFieldIdDepen.getText(), null, textFieldNoPuesto.getText(), null, textFieldClave.getText());
				System.out.println("textFieldNoPuesto.getText(): "+textFieldNoPuesto.getText());
				internalFrameEmpleado.busquedaEmpleadoReal(lblIdTipoNominaOculta.getText());


				double isr = insertarCalculoNominaSalarioIsr(textFieldNoPuesto.getText(),textFieldIdDepen.getText(),lblClave.getText());
				System.out.println("isr: "+isr);
				double imss = insertarCalculoNominaAyudaImss(textFieldClave.getText(),lblIdPuesto.getText(),lblIdDependencia.getText());
				System.out.println("imss: "+imss);

				int idPercepcionSal = 1;
				int idPercepcionAyuda = 26;
//				System.out.println("clave: "+ Integer.parseInt(textFieldClave.getText()));
//				System.out.println("id puesto: "+ Integer.parseInt(lblIdPuesto.getText()));
//				System.out.println("id dependencia: "+ Integer.parseInt(lblIdDependencia.getText()));
//				System.out.println("nuevo salario: "+ lblSalario.getText());
				insertarReportePercepcionSalario(Integer.parseInt(textFieldClave.getText()),Integer.parseInt(lblIdPuesto.getText()),Integer.parseInt(lblIdDependencia.getText()),
						idPercepcionSal,lblSalario.getText());
				
				if(FormularioPrincipal.lblIsNomC.getText().equalsIgnoreCase("OK")) {
					insertarReportePercepcionAyuda(Integer.parseInt(textFieldClave.getText()),Integer.parseInt(lblIdPuesto.getText()),Integer.parseInt(lblIdDependencia.getText()),
							idPercepcionAyuda,Double.parseDouble(InternalFrameCatalogos.textFieldCantidadAyudaDespensa.getText())*.40*14);	
				}else if(FormularioPrincipal.lblIsNomC.getText().equalsIgnoreCase("OK")) {
					insertarReportePercepcionAyuda(Integer.parseInt(textFieldClave.getText()),Integer.parseInt(lblIdPuesto.getText()),Integer.parseInt(lblIdDependencia.getText()),
							idPercepcionAyuda,Double.parseDouble(InternalFrameCatalogos.textFieldCantidadAyudaDespensa.getText())*.40*7);
				}
				
				int idDeduccionISR = 2;
				int idDeduccionIMSS = 1;
				insertarReporteDeduccionISR(Integer.parseInt(textFieldClave.getText()),Integer.parseInt(lblIdPuesto.getText()),Integer.parseInt(lblIdDependencia.getText()),
						idDeduccionISR,isr);
				insertarReporteDeduccionISR(Integer.parseInt(textFieldClave.getText()),Integer.parseInt(lblIdPuesto.getText()),Integer.parseInt(lblIdDependencia.getText()),
						idDeduccionIMSS,imss);
				dispose();
				limpiarFormularioNuevoEmpleado();
				}catch(Exception es) {
					es.printStackTrace();
					LOG.info("Excepción: " + es);
					JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
				}finally {
					try {
						
					} catch (Exception ep) {
						ep.printStackTrace();
						LOG.info("Excepción: " + ep);
						JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
					}
				}//FIN DEL FINALLY
			}
		});

		btnGuardar.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("accept.png"))));
		btnGuardar.setBounds(545, 615, 118, 30);
		btnGuardar.setForeground(Color.BLACK);
		panel.add(btnGuardar);


		btnCancelar.setForeground(Color.BLACK);
		btnCancelar.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("delete.png"))));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(673, 615, 118, 30);
		panel.add(btnCancelar);

		JPanel panelDatosPersonales = new JPanel();
		panelDatosPersonales.setBackground(SystemColor.inactiveCaption);
		panelDatosPersonales.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos de la persona", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(249,246,212)));
		panelDatosPersonales.setBounds(10, 14, 435, 393);
		panel.add(panelDatosPersonales);
		panelDatosPersonales.setLayout(null);

		JLabel lblNvoNombre = new JLabel("Nombre(s):");
		lblNvoNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNvoNombre.setBounds(53, 24, 75, 14);
		panelDatosPersonales.add(lblNvoNombre);
		lblNvoNombre.setForeground(new Color(0, 0, 0));

		textFieldNvoNombre = new JTextField();
		textFieldNvoNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				char c = arg0.getKeyChar();
				if(Character.isLowerCase(c)) {
					String cadena = (""+c).toUpperCase();
					c=cadena.charAt(0);
					arg0.setKeyChar(c);
				}


				if(!Character.isLetter(c) && c!=KeyEvent.VK_SPACE && c!=KeyEvent.VK_BACK_SPACE) {//()
					textFieldNvoNombre.setBackground(new Color(255,255,000));//rojo
					JOptionPane.showMessageDialog(null, "Solo se aceptan letras");
					arg0.consume();
					textFieldNvoNombre.setBackground(new Color(255,255,255));//blanco
				}

			}
		});
		textFieldNvoNombre.setBackground(new Color(255, 255, 255));
		textFieldNvoNombre.setBounds(138, 16, 289, 30);
		panelDatosPersonales.add(textFieldNvoNombre);
		textFieldNvoNombre.setForeground(Color.BLACK);
		textFieldNvoNombre.setColumns(10);

		JLabel lblNvoApPat = new JLabel("Apellido Paterno:");
		lblNvoApPat.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNvoApPat.setBounds(29, 58, 99, 14);
		panelDatosPersonales.add(lblNvoApPat);
		lblNvoApPat.setForeground(new Color(0, 0, 0));

		textFieldNvoApPat = new JTextField();
		textFieldNvoApPat.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				char c = arg0.getKeyChar();
				if(Character.isLowerCase(c)) {
					String cadena = (""+c).toUpperCase();
					c=cadena.charAt(0);
					arg0.setKeyChar(c);
				}

				if(!Character.isLetter(c) && c!=KeyEvent.VK_SPACE && c!=KeyEvent.VK_BACK_SPACE) {//()
					textFieldNvoApPat.setBackground(new Color(255,255,000));//rojo
					JOptionPane.showMessageDialog(null, "Solo se aceptan letras");
					arg0.consume();
					textFieldNvoApPat.setBackground(new Color(255,255,255));//blanco
				}
			}
		});
		textFieldNvoApPat.setBackground(new Color(255, 255, 255));
		textFieldNvoApPat.setBounds(138, 50, 289, 30);
		panelDatosPersonales.add(textFieldNvoApPat);
		textFieldNvoApPat.setForeground(Color.BLACK);
		textFieldNvoApPat.setColumns(10);

		textFieldNvoApMat = new JTextField();
		textFieldNvoApMat.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				char c = arg0.getKeyChar();
				if(Character.isLowerCase(c)) {
					String cadena = (""+c).toUpperCase();
					c=cadena.charAt(0);
					arg0.setKeyChar(c);
				}

				if(!Character.isLetter(c) && c!=KeyEvent.VK_SPACE && c!=KeyEvent.VK_BACK_SPACE) {//()
					textFieldNvoApMat.setBackground(new Color(255,255,000));//rojo
					JOptionPane.showMessageDialog(null, "Solo se aceptan letras");
					arg0.consume();
					textFieldNvoApMat.setBackground(new Color(255,255,255));//blanco
				}
			}
		});
		textFieldNvoApMat.setBounds(138, 84, 289, 30);
		panelDatosPersonales.add(textFieldNvoApMat);
		textFieldNvoApMat.setBackground(new Color(255, 255, 255));
		textFieldNvoApMat.setForeground(Color.BLACK);
		textFieldNvoApMat.setColumns(10);
		dateChooserNvoFechaNac.setForeground(Color.BLACK);
		dateChooserNvoFechaNac.setBounds(138, 118, 289, 30);
		panelDatosPersonales.add(dateChooserNvoFechaNac);

		textFieldNvoCurp = new JTextField();
		textFieldNvoCurp.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(Character.isLowerCase(c)) {
					String cadena = (""+c).toUpperCase();
					c=cadena.charAt(0);
					e.setKeyChar(c);
				}
			}
		});
		textFieldNvoCurp.setBounds(138, 152, 289, 30);
		panelDatosPersonales.add(textFieldNvoCurp);

		textFieldNvoCurp.setBackground(new Color(255, 255, 255));
		textFieldNvoCurp.setForeground(Color.BLACK);
		textFieldNvoCurp.setColumns(10);

		textFieldNvoRFC = new JTextField();
		textFieldNvoRFC.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(Character.isLowerCase(c)) {
					String cadena = (""+c).toUpperCase();
					c=cadena.charAt(0);
					e.setKeyChar(c);
				}
			}
		});
		textFieldNvoRFC.setBounds(138, 186, 289, 30);
		panelDatosPersonales.add(textFieldNvoRFC);

		textFieldNvoRFC.setBackground(new Color(255, 255, 255));
		textFieldNvoRFC.setForeground(Color.BLACK);
		textFieldNvoRFC.setColumns(10);

		textFieldNvoTel = new JTextField();
		textFieldNvoTel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(Character.isLowerCase(c)) {
					String cadena = (""+c).toUpperCase();
					c=cadena.charAt(0);
					e.setKeyChar(c);
				}

				if(!Character.isDigit(c) && c!=KeyEvent.VK_SPACE && c!=KeyEvent.VK_BACK_SPACE) {//c<'0' || c>'9'
					textFieldNvoTel.setBackground(new Color(255,255,000));
					JOptionPane.showMessageDialog(null, "Solo se aceptan números");
					e.consume();
					textFieldNvoTel.setBackground(new Color(255,255,255));
				}
			}
		});
		textFieldNvoTel.setBounds(138, 220, 289, 30);
		panelDatosPersonales.add(textFieldNvoTel);

		textFieldNvoTel.setBackground(new Color(255, 255, 255));
		textFieldNvoTel.setForeground(Color.BLACK);
		textFieldNvoTel.setColumns(10);

		comboBoxGenero.setBounds(138, 254, 156, 30);
		comboBoxGenero.setForeground(Color.BLACK);
		panelDatosPersonales.add(comboBoxGenero);

		comboBoxEdoCivil.setBounds(138, 288, 156, 30);
		comboBoxEdoCivil.setForeground(Color.BLACK);
		panelDatosPersonales.add(comboBoxEdoCivil);

		textFieldNvoCorreo = new JTextField();
		textFieldNvoCorreo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(Character.isLowerCase(c)) {
					String cadena = (""+c).toUpperCase();
					c=cadena.charAt(0);
					e.setKeyChar(c);
				}
			}
		});
		textFieldNvoCorreo.setBounds(138, 322, 289, 30);
		panelDatosPersonales.add(textFieldNvoCorreo);
		textFieldNvoCorreo.setForeground(Color.BLACK);
		textFieldNvoCorreo.setColumns(10);

		JLabel lblNvoApMat = new JLabel("Apellido Materno:");
		lblNvoApMat.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNvoApMat.setBounds(29, 92, 99, 14);
		panelDatosPersonales.add(lblNvoApMat);
		lblNvoApMat.setForeground(new Color(0, 0, 0));

		JLabel lblNvoFechaNac = new JLabel("Fecha de Nacimiento:");
		lblNvoFechaNac.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNvoFechaNac.setBounds(0, 126, 128, 14);
		panelDatosPersonales.add(lblNvoFechaNac);
		lblNvoFechaNac.setForeground(new Color(0, 0, 0));

		JLabel lblNvoCurp = new JLabel("Curp:");
		lblNvoCurp.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNvoCurp.setBounds(82, 160, 46, 14);
		panelDatosPersonales.add(lblNvoCurp);
		lblNvoCurp.setForeground(new Color(0, 0, 0));

		JLabel lblNvoRfc = new JLabel("RFC:");
		lblNvoRfc.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNvoRfc.setBounds(29, 194, 99, 14);
		panelDatosPersonales.add(lblNvoRfc);
		lblNvoRfc.setForeground(new Color(0, 0, 0));

		JLabel lblNvoTel = new JLabel("Telefono:");
		lblNvoTel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNvoTel.setBounds(29, 228, 99, 14);
		panelDatosPersonales.add(lblNvoTel);
		lblNvoTel.setForeground(new Color(0, 0, 0));

		JLabel lblNvoTurno = new JLabel("Genero:");
		lblNvoTurno.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNvoTurno.setBounds(10, 262, 118, 14);
		panelDatosPersonales.add(lblNvoTurno);
		lblNvoTurno.setForeground(new Color(0, 0, 0));

		JLabel lblNvoEdoCivil = new JLabel("Estado Civil:");
		lblNvoEdoCivil.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNvoEdoCivil.setBounds(29, 296, 99, 14);
		panelDatosPersonales.add(lblNvoEdoCivil);
		lblNvoEdoCivil.setForeground(new Color(0, 0, 0));

		JLabel lblNvoEmail = new JLabel("Email:");
		lblNvoEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNvoEmail.setBounds(10, 327, 118, 20);
		panelDatosPersonales.add(lblNvoEmail);
		lblNvoEmail.setForeground(new Color(0, 0, 0));

		textFieldClave = new JTextField();
		textFieldClave.setBounds(138, 356, 86, 30);
		panelDatosPersonales.add(textFieldClave);
		textFieldClave.setColumns(10);

		lblClaveDelEmpleado.setHorizontalAlignment(SwingConstants.RIGHT);
		lblClaveDelEmpleado.setForeground(Color.BLACK);
		lblClaveDelEmpleado.setBounds(0, 364, 128, 14);
		panelDatosPersonales.add(lblClaveDelEmpleado);


		lblClave.setBounds(280, 363, 46, 14);
		panelDatosPersonales.add(lblClave);

		JPanel panelDomicilio = new JPanel();
		panelDomicilio.setBackground(SystemColor.inactiveCaption);
		panelDomicilio.setBorder(new TitledBorder(null, "Domicilio", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(249,246,212)));
		panelDomicilio.setBounds(10, 412, 435, 194);
		panel.add(panelDomicilio);
		panelDomicilio.setLayout(null);

		JLabel lblDireccion = new JLabel("Direccion:");
		lblDireccion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDireccion.setBounds(29, 25, 99, 14);
		panelDomicilio.add(lblDireccion);
		lblDireccion.setForeground(new Color(0, 0, 0));

		textFieldDireccion = new JTextField();
		textFieldDireccion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(Character.isLowerCase(c)) {
					String cadena = (""+c).toUpperCase();
					c=cadena.charAt(0);
					e.setKeyChar(c);
				}

				//				if(!Character.isLetter(c) && c!=KeyEvent.VK_SPACE && c!=KeyEvent.VK_BACK_SPACE ) {//()
				//					textFieldNvoNombre.setBackground(new Color(255,255,000));//rojo
				//					JOptionPane.showMessageDialog(null, "Solo se aceptan letras");
				//					e.consume();
				//					textFieldNvoNombre.setBackground(new Color(255,255,255));//blanco
				//				}
			}
		});
		textFieldDireccion.setBackground(new Color(255, 255, 255));
		textFieldDireccion.setBounds(138, 17, 289, 30);
		panelDomicilio.add(textFieldDireccion);
		textFieldDireccion.setForeground(Color.BLACK);
		textFieldDireccion.setColumns(10);

		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEstado.setBounds(29, 161, 99, 14);
		panelDomicilio.add(lblEstado);
		lblEstado.setForeground(new Color(0, 0, 0));

		textFieldNvoEstado = new JTextField();
		textFieldNvoEstado.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(Character.isLowerCase(c)) {
					String cadena = (""+c).toUpperCase();
					c=cadena.charAt(0);
					e.setKeyChar(c);
				}

				if(!Character.isLetter(c) && c!=KeyEvent.VK_SPACE && c!=KeyEvent.VK_BACK_SPACE) {//()
					textFieldNvoEstado.setBackground(new Color(255,255,000));//rojo
					JOptionPane.showMessageDialog(null, "Solo se aceptan letras");
					e.consume();
					textFieldNvoEstado.setBackground(new Color(255,255,255));//blanco
				}
			}
		});
		textFieldNvoEstado.setBackground(new Color(255, 255, 255));
		textFieldNvoEstado.setBounds(138, 153, 289, 30);
		panelDomicilio.add(textFieldNvoEstado);
		textFieldNvoEstado.setForeground(Color.BLACK);
		textFieldNvoEstado.setColumns(10);

		JLabel lblNvoCodPost = new JLabel("Codigo Postal:");
		lblNvoCodPost.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNvoCodPost.setBounds(10, 124, 118, 20);
		panelDomicilio.add(lblNvoCodPost);
		lblNvoCodPost.setForeground(new Color(0, 0, 0));

		textFieldNvoCodPost = new JTextField();
		textFieldNvoCodPost.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(Character.isLowerCase(c)) {
					String cadena = (""+c).toUpperCase();
					c=cadena.charAt(0);
					e.setKeyChar(c);
				}
				if(!Character.isDigit(c) && c!=KeyEvent.VK_SPACE && c!=KeyEvent.VK_BACK_SPACE) {//c<'0' || c>'9'
					textFieldNvoCodPost.setBackground(new Color(255,255,000));
					JOptionPane.showMessageDialog(null, "Solo se aceptan números");
					e.consume();
					textFieldNvoCodPost.setBackground(new Color(255,255,255));
				}
			}
		});
		textFieldNvoCodPost.setBackground(new Color(255, 255, 255));
		textFieldNvoCodPost.setBounds(138, 119, 289, 30);
		panelDomicilio.add(textFieldNvoCodPost);
		textFieldNvoCodPost.setForeground(Color.BLACK);
		textFieldNvoCodPost.setColumns(10);

		JLabel lblColoniaOComunidad = new JLabel("Colonia o Comunidad:");
		lblColoniaOComunidad.setHorizontalAlignment(SwingConstants.RIGHT);
		lblColoniaOComunidad.setForeground(new Color(0, 0, 0));
		lblColoniaOComunidad.setBounds(0, 57, 131, 20);
		panelDomicilio.add(lblColoniaOComunidad);
		comboBoxLocalidad.setForeground(Color.BLACK);


		comboBoxLocalidad.setBounds(138, 52, 289, 30);
		panelDomicilio.add(comboBoxLocalidad);

		JLabel lblCiudad = new JLabel("Ciudad:");
		lblCiudad.setBounds(29, 91, 99, 14);
		panelDomicilio.add(lblCiudad);
		lblCiudad.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCiudad.setForeground(new Color(0, 0, 0));

		textFieldNvoCiudad = new JTextField();
		textFieldNvoCiudad.setBounds(138, 86, 289, 30);
		panelDomicilio.add(textFieldNvoCiudad);
		textFieldNvoCiudad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(Character.isLowerCase(c)) {
					String cadena = (""+c).toUpperCase();
					c=cadena.charAt(0);
					e.setKeyChar(c);
				}

				if(!Character.isLetter(c) && c!=KeyEvent.VK_SPACE && c!=KeyEvent.VK_BACK_SPACE) {//()
					textFieldNvoCiudad.setBackground(new Color(255,255,000));//rojo
					JOptionPane.showMessageDialog(null, "Solo se aceptan letras");
					e.consume();
					textFieldNvoCiudad.setBackground(new Color(255,255,255));//blanco
				}
			}
		});
		textFieldNvoCiudad.setBackground(new Color(255, 255, 255));
		textFieldNvoCiudad.setForeground(Color.BLACK);
		textFieldNvoCiudad.setColumns(10);

		JPanel panelLocalizacion = new JPanel();
		panelLocalizacion.setBackground(SystemColor.inactiveCaption);
		panelLocalizacion.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos de Localizaci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(249, 246, 212)));
		panelLocalizacion.setBounds(455, 455, 526, 151);
		panel.add(panelLocalizacion);
		panelLocalizacion.setLayout(null);

		JLabel lblContacto = new JLabel("Contacto:");
		lblContacto.setHorizontalAlignment(SwingConstants.RIGHT);
		lblContacto.setBounds(62, 19, 99, 14);
		panelLocalizacion.add(lblContacto);
		lblContacto.setForeground(new Color(0, 0, 0));

		textFieldNvoContacto = new JTextField();
		textFieldNvoContacto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(Character.isLowerCase(c)) {
					String cadena = (""+c).toUpperCase();
					c=cadena.charAt(0);
					e.setKeyChar(c);
				}
				if(!Character.isLetter(c) && c!=KeyEvent.VK_SPACE && c!=KeyEvent.VK_BACK_SPACE) {//()
					textFieldNvoContacto.setBackground(new Color(255,255,000));//rojo
					JOptionPane.showMessageDialog(null, "Solo se aceptan letras");
					e.consume();
					textFieldNvoContacto.setBackground(new Color(255,255,255));//blanco
				}
			}
		});
		textFieldNvoContacto.setBackground(new Color(255, 255, 255));
		textFieldNvoContacto.setBounds(171, 11, 337, 30);
		panelLocalizacion.add(textFieldNvoContacto);
		textFieldNvoContacto.setForeground(Color.BLACK);
		textFieldNvoContacto.setColumns(10);

		JLabel lblContactoEmail = new JLabel("Email contacto:");
		lblContactoEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblContactoEmail.setForeground(new Color(0, 0, 0));
		lblContactoEmail.setBounds(62, 53, 99, 20);
		panelLocalizacion.add(lblContactoEmail);

		JLabel lblCelular = new JLabel("Celular contacto:");
		lblCelular.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCelular.setForeground(new Color(0, 0, 0));
		lblCelular.setBounds(62, 84, 99, 20);
		panelLocalizacion.add(lblCelular);

		textFieldContactoEmail = new JTextField();
		textFieldContactoEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(Character.isLowerCase(c)) {
					String cadena = (""+c).toUpperCase();
					c=cadena.charAt(0);
					e.setKeyChar(c);
				}

			}
		});
		textFieldContactoEmail.setBackground(new Color(255, 255, 255));
		textFieldContactoEmail.setForeground(Color.BLACK);
		textFieldContactoEmail.setColumns(10);
		textFieldContactoEmail.setBounds(171, 45, 337, 30);
		panelLocalizacion.add(textFieldContactoEmail);

		textFieldContactoCelular = new JTextField();
		textFieldContactoCelular.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(Character.isLowerCase(c)) {
					String cadena = (""+c).toUpperCase();
					c=cadena.charAt(0);
					e.setKeyChar(c);
				}

				if(!Character.isDigit(c) && c!=KeyEvent.VK_SPACE && c!=KeyEvent.VK_BACK_SPACE) {//c<'0' || c>'9'
					textFieldContactoCelular.setBackground(new Color(255,255,000));
					JOptionPane.showMessageDialog(null, "Solo se aceptan números");
					e.consume();
					textFieldContactoCelular.setBackground(new Color(255,255,255));
				}
			}
		});
		textFieldContactoCelular.setBackground(new Color(255, 255, 255));
		textFieldContactoCelular.setForeground(Color.BLACK);
		textFieldContactoCelular.setColumns(10);
		textFieldContactoCelular.setBounds(171, 79, 337, 30);
		panelLocalizacion.add(textFieldContactoCelular);

		JLabel lblDireccin = new JLabel("Dirección contacto:");
		lblDireccin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDireccin.setForeground(new Color(0, 0, 0));
		lblDireccin.setBounds(10, 115, 151, 20);
		panelLocalizacion.add(lblDireccin);

		textFieldContactoDireccion = new JTextField();
		textFieldContactoDireccion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(Character.isLowerCase(c)) {
					String cadena = (""+c).toUpperCase();
					c=cadena.charAt(0);
					e.setKeyChar(c);
				}
				//				if(!Character.isLetter(c) && c!=KeyEvent.VK_SPACE && c!=KeyEvent.VK_BACK_SPACE) {//()
				//					textFieldNvoNombre.setBackground(new Color(255,255,000));//rojo
				//					JOptionPane.showMessageDialog(null, "Solo se aceptan letras");
				//					e.consume();
				//					textFieldNvoNombre.setBackground(new Color(255,255,255));//blanco
				//				}
			}
		});
		textFieldContactoDireccion.setBackground(new Color(255, 255, 255));
		textFieldContactoDireccion.setForeground(Color.BLACK);
		textFieldContactoDireccion.setColumns(10);
		textFieldContactoDireccion.setBounds(171, 113, 337, 30);
		panelLocalizacion.add(textFieldContactoDireccion);

		JPanel panelImss = new JPanel();
		panelImss.setBackground(SystemColor.inactiveCaption);
		panelImss.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos del Imss e Infonavit", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(249, 246, 212)));
		panelImss.setBounds(991, 351, 503, 254);
		panel.add(panelImss);
		panelImss.setLayout(null);

		JLabel lblNvoRegImss = new JLabel("Resgistro Imss:");
		lblNvoRegImss.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNvoRegImss.setBounds(62, 19, 99, 14);
		panelImss.add(lblNvoRegImss);
		lblNvoRegImss.setForeground(new Color(0, 0, 0));

		textFieldNvoRegImss = new JTextField();
		textFieldNvoRegImss.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(Character.isLowerCase(c)) {
					String cadena = (""+c).toUpperCase();
					c=cadena.charAt(0);
					e.setKeyChar(c);
				}
			}
		});
		textFieldNvoRegImss.setBackground(new Color(255, 255, 255));
		textFieldNvoRegImss.setBounds(169, 11, 324, 30);
		panelImss.add(textFieldNvoRegImss);
		textFieldNvoRegImss.setForeground(Color.BLACK);
		textFieldNvoRegImss.setColumns(10);

		JLabel lblNvaFechaAltaImss = new JLabel("Fecha Alta Imss:");
		lblNvaFechaAltaImss.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNvaFechaAltaImss.setBounds(62, 56, 99, 14);
		panelImss.add(lblNvaFechaAltaImss);
		lblNvaFechaAltaImss.setForeground(new Color(0, 0, 0));
		dateChooserNvoFechaAltaImss.setForeground(Color.BLACK);
		dateChooserNvoFechaAltaImss.setBackground(new Color(255, 255, 255));
		dateChooserNvoFechaAltaImss.setBounds(169, 45, 324, 30);
		panelImss.add(dateChooserNvoFechaAltaImss);

		JLabel lblFechaBajaImss = new JLabel("Fecha Baja Imss:");
		lblFechaBajaImss.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFechaBajaImss.setBounds(62, 92, 99, 14);
		panelImss.add(lblFechaBajaImss);
		lblFechaBajaImss.setForeground(new Color(0, 0, 0));
		dateChooserFechaBajaImss.setForeground(Color.BLACK);
		dateChooserFechaBajaImss.setBackground(new Color(255, 255, 255));
		dateChooserFechaBajaImss.setBounds(169, 79, 324, 30);
		panelImss.add(dateChooserFechaBajaImss);

		JLabel lblNewLabel = new JLabel("Salario Diario Integrado:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(-8, 121, 169, 14);
		panelImss.add(lblNewLabel);

		textFieldSDINuevo = new JTextField();
		textFieldSDINuevo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(Character.isLowerCase(c)) {
					String cadena = (""+c).toUpperCase();
					c=cadena.charAt(0);
					e.setKeyChar(c);
				}

				//				if(!Character.isDigit(c) && c!=KeyEvent.VK_SPACE && c!=KeyEvent.VK_BACK_SPACE) {//c<'0' || c>'9'
				//					textFieldSDINuevo.setBackground(new Color(255,255,000));
				//					JOptionPane.showMessageDialog(null, "Solo se aceptan números");
				//					e.consume();
				//					textFieldSDINuevo.setBackground(new Color(255,255,255));
				//				}
			}
		});
		textFieldSDINuevo.setText("0.0");
		textFieldSDINuevo.setForeground(Color.BLACK);
		textFieldSDINuevo.setColumns(10);
		textFieldSDINuevo.setBackground(Color.WHITE);
		textFieldSDINuevo.setBounds(171, 113, 322, 30);
		panelImss.add(textFieldSDINuevo);

		JLabel lblNumeroCrditoInfonavit = new JLabel("Numero Crédito Infonavit:");
		lblNumeroCrditoInfonavit.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNumeroCrditoInfonavit.setForeground(Color.BLACK);
		lblNumeroCrditoInfonavit.setBounds(20, 155, 140, 14);
		panelImss.add(lblNumeroCrditoInfonavit);

		JLabel lblFactorInfonavit = new JLabel("Factor Infonavit:");
		lblFactorInfonavit.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFactorInfonavit.setForeground(Color.BLACK);
		lblFactorInfonavit.setBounds(62, 189, 99, 14);
		panelImss.add(lblFactorInfonavit);

		JLabel lblUma = new JLabel("U.M.A.:");
		lblUma.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUma.setForeground(Color.BLACK);
		lblUma.setBounds(62, 223, 99, 14);
		panelImss.add(lblUma);

		textFieldNCI = new JTextField();
		textFieldNCI.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(Character.isLowerCase(c)) {
					String cadena = (""+c).toUpperCase();
					c=cadena.charAt(0);
					e.setKeyChar(c);
				}
			}
		});
		textFieldNCI.setForeground(Color.BLACK);
		textFieldNCI.setColumns(10);
		textFieldNCI.setBackground(Color.WHITE);
		textFieldNCI.setBounds(171, 147, 322, 30);
		panelImss.add(textFieldNCI);

		textFieldFI = new JTextField();
		textFieldFI.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(Character.isLowerCase(c)) {
					String cadena = (""+c).toUpperCase();
					c=cadena.charAt(0);
					e.setKeyChar(c);
				}

				//				if(!Character.isDigit(c) && c!=KeyEvent.VK_SPACE && c!=KeyEvent.VK_BACK_SPACE) {//c<'0' || c>'9'
				//					textFieldFI.setBackground(new Color(255,255,000));
				//					JOptionPane.showMessageDialog(null, "Solo se aceptan números");
				//					e.consume();
				//					textFieldFI.setBackground(new Color(255,255,255));
				//				}
			}
		});
		textFieldFI.setText("0.0");
		textFieldFI.setForeground(Color.BLACK);
		textFieldFI.setColumns(10);
		textFieldFI.setBackground(Color.WHITE);
		textFieldFI.setBounds(171, 181, 322, 30);
		panelImss.add(textFieldFI);

		textFieldUMA = new JTextField();
		textFieldUMA.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(Character.isLowerCase(c)) {
					String cadena = (""+c).toUpperCase();
					c=cadena.charAt(0);
					e.setKeyChar(c);
				}

				//				if(!Character.isDigit(c) && c!=KeyEvent.VK_SPACE && c!=KeyEvent.VK_BACK_SPACE) {//c<'0' || c>'9'
				//					textFieldUMA.setBackground(new Color(255,255,000));
				//					JOptionPane.showMessageDialog(null, "Solo se aceptan números");
				//					e.consume();
				//					textFieldUMA.setBackground(new Color(255,255,255));
				//				}
			}
		});
		textFieldUMA.setText("0.0");
		textFieldUMA.setForeground(Color.BLACK);
		textFieldUMA.setColumns(10);
		textFieldUMA.setBackground(Color.WHITE);
		textFieldUMA.setBounds(171, 215, 322, 30);
		panelImss.add(textFieldUMA);

		JPanel panelDatosEmpleado = new JPanel();
		panelDatosEmpleado.setBackground(SystemColor.inactiveCaption);
		panelDatosEmpleado.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos del Empleo", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(249,246,212)));
		panelDatosEmpleado.setBounds(455, 14, 526, 430);
		panel.add(panelDatosEmpleado);
		panelDatosEmpleado.setLayout(null);

		JLabel lblNvoPuesto = new JLabel("Puesto:");
		lblNvoPuesto.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNvoPuesto.setBounds(43, 156, 118, 14);
		panelDatosEmpleado.add(lblNvoPuesto);
		lblNvoPuesto.setForeground(new Color(0, 0, 0));

		//		JLabel lblNvoSalario = new JLabel("Salario:");
		//		lblNvoSalario.setBounds(10, 57, 99, 14);
		//		panelDatosEmpleado.add(lblNvoSalario);
		//		lblNvoSalario.setForeground(new Color(0, 0, 0));

		JLabel lblFechaAltaIngreso = new JLabel("Fecha Alta Ingreso:");
		lblFechaAltaIngreso.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFechaAltaIngreso.setBounds(43, 52, 118, 17);
		panelDatosEmpleado.add(lblFechaAltaIngreso);
		lblFechaAltaIngreso.setForeground(new Color(0, 0, 0));

		JLabel lblFechaBajaIngreso = new JLabel("Fecha Baja Ingreso:");
		lblFechaBajaIngreso.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFechaBajaIngreso.setBounds(43, 87, 118, 17);
		panelDatosEmpleado.add(lblFechaBajaIngreso);
		lblFechaBajaIngreso.setForeground(new Color(0, 0, 0));
		dateChooserFechaBajaIngreso.setForeground(Color.BLACK);
		dateChooserFechaBajaIngreso.setBackground(new Color(255, 255, 255));
		dateChooserFechaBajaIngreso.setBounds(175, 79, 337, 30);
		panelDatosEmpleado.add(dateChooserFechaBajaIngreso);
		dateChooserFechaAltaIngreso.setForeground(Color.BLACK);
		dateChooserFechaAltaIngreso.setBackground(new Color(255, 255, 255));
		dateChooserFechaAltaIngreso.setBounds(175, 43, 337, 30);
		panelDatosEmpleado.add(dateChooserFechaAltaIngreso);

		//		textFieldNvoSalario = new JTextField();
		//		textFieldNvoSalario.setBackground(new Color(255, 255, 255));
		//		textFieldNvoSalario.setBounds(131, 47, 381, 25);
		//		panelDatosEmpleado.add(textFieldNvoSalario);
		//		textFieldNvoSalario.setForeground(new Color(255, 127, 80));
		//		textFieldNvoSalario.setColumns(10);

		JLabel lblDependencia = new JLabel("U. Responsable:");
		lblDependencia.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDependencia.setForeground(new Color(0, 0, 0));
		lblDependencia.setBounds(43, 120, 118, 17);
		panelDatosEmpleado.add(lblDependencia);

		//		comboBoxUnidadResp.setForeground(Color.BLACK);
		//		comboBoxUnidadResp.setBounds(175, 113, 337, 30);
		//		panelDatosEmpleado.add(comboBoxUnidadResp);

		//		comboBoxPuesto.setForeground(Color.BLACK);
		//		comboBoxPuesto.setBounds(171, 148, 341, 30);
		//		panelDatosEmpleado.add(comboBoxPuesto);

		lblClasificacin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblClasificacin.setForeground(Color.BLACK);
		lblClasificacin.setBounds(10, 194, 151, 17);
		panelDatosEmpleado.add(lblClasificacin);

		lblRgimen.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRgimen.setForeground(Color.BLACK);
		lblRgimen.setBounds(10, 228, 151, 17);
		panelDatosEmpleado.add(lblRgimen);

		lblTipoEmpleado.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTipoEmpleado.setForeground(Color.BLACK);
		lblTipoEmpleado.setBounds(10, 260, 151, 17);
		panelDatosEmpleado.add(lblTipoEmpleado);

		lblClasificacionEmpleado.setHorizontalAlignment(SwingConstants.RIGHT);
		lblClasificacionEmpleado.setForeground(Color.BLACK);
		lblClasificacionEmpleado.setBounds(10, 398, 151, 17);
		panelDatosEmpleado.add(lblClasificacionEmpleado);

		lblTipoJornada.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTipoJornada.setForeground(Color.BLACK);
		lblTipoJornada.setBounds(10, 296, 151, 17);
		panelDatosEmpleado.add(lblTipoJornada);

		lblRiesgo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRiesgo.setForeground(Color.BLACK);
		lblRiesgo.setBounds(10, 330, 151, 17);
		panelDatosEmpleado.add(lblRiesgo);

		lblTipoContrato.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTipoContrato.setForeground(Color.BLACK);
		lblTipoContrato.setBounds(10, 364, 151, 17);
		panelDatosEmpleado.add(lblTipoContrato);

		comboBoxClasificacionDispersion.setForeground(Color.BLACK);
		comboBoxClasificacionDispersion.setBounds(171, 181, 341, 30);
		panelDatosEmpleado.add(comboBoxClasificacionDispersion);

		comboBoxRegimen.setForeground(Color.BLACK);
		comboBoxRegimen.setBounds(171, 215, 341, 30);
		panelDatosEmpleado.add(comboBoxRegimen);

		comboBoxTipoEmpleado.setForeground(Color.BLACK);
		comboBoxTipoEmpleado.setBounds(171, 249, 341, 30);
		panelDatosEmpleado.add(comboBoxTipoEmpleado);

		comboBoxTipoJornada.setForeground(Color.BLACK);
		comboBoxTipoJornada.setBounds(171, 283, 341, 30);
		panelDatosEmpleado.add(comboBoxTipoJornada);

		comboBoxRiesgo.setForeground(Color.BLACK);
		comboBoxRiesgo.setBounds(171, 317, 341, 30);
		panelDatosEmpleado.add(comboBoxRiesgo);

		comboBoxTipoContrato.setForeground(Color.BLACK);
		comboBoxTipoContrato.setBounds(171, 351, 341, 30);
		panelDatosEmpleado.add(comboBoxTipoContrato);

		comboBoxCalsificacionEmpleado.setForeground(Color.BLACK);
		comboBoxCalsificacionEmpleado.setBounds(171, 385, 341, 30);
		panelDatosEmpleado.add(comboBoxCalsificacionEmpleado);

		JLabel lblTipoDeNomina = new JLabel("Tipo nomina:");
		lblTipoDeNomina.setBounds(10, 18, 85, 17);
		panelDatosEmpleado.add(lblTipoDeNomina);
		lblTipoDeNomina.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTipoDeNomina.setForeground(Color.BLACK);

		textFieldTipoNominaOculta = new JTextField();
		textFieldTipoNominaOculta.setForeground(Color.BLACK);
		textFieldTipoNominaOculta.setColumns(10);
		textFieldTipoNominaOculta.setBackground(Color.WHITE);
		textFieldTipoNominaOculta.setBounds(105, 11, 407, 30);
		panelDatosEmpleado.add(textFieldTipoNominaOculta);

		JButton btnSeleccionarPuesto = new JButton("Seleccionar");
		btnSeleccionarPuesto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//				panelPuestos.setVisible(true);

				InternalFrameAsignarPuesto internalFrameASIGNARPuesto = new  InternalFrameAsignarPuesto();
				FormularioPrincipal.desktopPane.add(internalFrameASIGNARPuesto);
				internalFrameASIGNARPuesto.show();
				internalFrameASIGNARPuesto.setFrameIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("user_add.png"))));
				internalFrameASIGNARPuesto.setIconifiable(true);
				internalFrameASIGNARPuesto.setClosable(true);
				internalFrameASIGNARPuesto.setResizable(true);
				internalFrameASIGNARPuesto.setMaximizable(true);
				internalFrameASIGNARPuesto.setVisible(true);
				internalFrameASIGNARPuesto.toFront();
				internalFrameASIGNARPuesto.getPuestos();
				internalFrameASIGNARPuesto.lblIdPuestoAct.setVisible(false);
				internalFrameASIGNARPuesto.lblIdDependenciaAct.setVisible(false);
				internalFrameASIGNARPuesto.lblClave.setVisible(false);
				internalFrameASIGNARPuesto.lblDependencia.setVisible(false);
				getPuestos();



			}
		});
		btnSeleccionarPuesto.setBounds(171, 147, 89, 30);
		panelDatosEmpleado.add(btnSeleccionarPuesto);

		textFieldAsignarPuesto = new JTextField();
		textFieldAsignarPuesto.setBounds(313, 147, 199, 30);
		panelDatosEmpleado.add(textFieldAsignarPuesto);
		textFieldAsignarPuesto.setColumns(10);

		textFieldNoPuesto = new JTextField();
		textFieldNoPuesto.setBounds(262, 147, 47, 30);
		panelDatosEmpleado.add(textFieldNoPuesto);
		textFieldNoPuesto.setColumns(10);

		JButton button = new JButton("Seleccionar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InternalFrameAsiggnarDependencia internalFrameAsignarDependencia = new  InternalFrameAsiggnarDependencia();
				FormularioPrincipal.desktopPane.add(internalFrameAsignarDependencia);
				internalFrameAsignarDependencia.show();
				internalFrameAsignarDependencia.setFrameIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("user_add.png"))));
				internalFrameAsignarDependencia.setIconifiable(true);
				internalFrameAsignarDependencia.setClosable(true);
				internalFrameAsignarDependencia.setResizable(true);
				internalFrameAsignarDependencia.setMaximizable(true);
				internalFrameAsignarDependencia.setVisible(true);
				internalFrameAsignarDependencia.toFront();
				internalFrameAsignarDependencia.getDependencias();;
				internalFrameAsignarDependencia.lblIdPuestoAct.setVisible(false);
				internalFrameAsignarDependencia.lblIdDependenciaAct.setVisible(false);
				internalFrameAsignarDependencia.lblClave.setVisible(false);
				internalFrameAsignarDependencia.lblDependencia.setVisible(false);
				getPuestos();
			}
		});
		button.setBounds(171, 112, 89, 30);
		panelDatosEmpleado.add(button);

		textFieldIdDepen = new JTextField();
		textFieldIdDepen.setColumns(10);
		textFieldIdDepen.setBounds(262, 112, 47, 30);
		panelDatosEmpleado.add(textFieldIdDepen);

		textFieldAsignarDepen = new JTextField();
		textFieldAsignarDepen.setColumns(10);
		textFieldAsignarDepen.setBounds(313, 112, 199, 30);
		panelDatosEmpleado.add(textFieldAsignarDepen);

		//		comboBoxEjercicio.setBounds(105, 11, 407, 30);
		//		panelDatosEmpleado.add(comboBoxEjercicio);
		//		comboBoxEjercicio.setForeground(Color.BLACK);


		//		chckbxCatorcenal.setBackground(new Color(211, 211, 211));
		//		chckbxCatorcenal.setBounds(138, 169, 97, 23);
		//		panelDatosEmpleado.add(chckbxCatorcenal);
		//
		//
		//		chckbxSemanal.setBackground(new Color(211, 211, 211));
		//		chckbxSemanal.setBounds(254, 169, 97, 23);
		//		panelDatosEmpleado.add(chckbxSemanal);
		//
		//
		//		chckbxJubilados.setBackground(new Color(211, 211, 211));
		//		chckbxJubilados.setBounds(358, 169, 97, 23);
		//		panelDatosEmpleado.add(chckbxJubilados);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Empleado(a)", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(249,246,212)));
		panel_1.setBackground(SystemColor.inactiveCaption);
		panel_1.setBounds(1300, 14, 194, 228);
		panel.add(panel_1);
		panel_1.setLayout(null);


		lblNewFoto.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("businessman(2).png"))));
		lblNewFoto.setBounds(10, 25, 174, 189);
		panel_1.add(lblNewFoto);

		JButton btnNewCargarFoto = new JButton("Cargar Foto");
		btnNewCargarFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {



				JFileChooser file = new JFileChooser();
				File fichero = null;
				file.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int seleccion = file.showOpenDialog(file);
				FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.jpg", "jpg");
				file.setFileFilter(filtro);
				//				//Si el usuario, pincha en aceptar
				if (seleccion == JFileChooser.APPROVE_OPTION) {
					//Seleccionamos el fichero
					fichero = file.getSelectedFile();
					//Ecribe la ruta del fichero seleccionado en el campo de texto
					lblrutaPhoto.setText(fichero.getAbsolutePath());
					ImageIcon icon = new ImageIcon(fichero.toString());
					System.out.println("ruta: " + icon);
					System.out.println("Nombre de archivo: " + fichero.getName());
					Icon icono = new ImageIcon(icon.getImage().getScaledInstance(lblNewFoto.getWidth(), lblNewFoto.getHeight(), Image.SCALE_DEFAULT));
					lblNewFoto.setText(null);
					lblNewFoto.setIcon(icono);





					//										try {
					//											fis = new FileInputStream(file.getSelectedFile());
					//											longitubBytes = (int) file.getSelectedFile().length();
					//											Image icono = ImageIO.read(file.getSelectedFile()).getScaledInstance(lblNewFoto.getWidth(), lblNewFoto.getHeight(), Image.SCALE_DEFAULT);
					//											lblNewFoto.setIcon(new ImageIcon(icono));
					//											lblNewFoto.updateUI(); 
					//										} catch (FileNotFoundException e1) {
					//											e1.printStackTrace();
					//											JOptionPane.showMessageDialog(null, "El archivo no se pudo abrir");
					//										}catch (IOException ex){
					//											ex.printStackTrace();
					//											JOptionPane.showMessageDialog(null, "El archivo no se pudo abrir");
					//										}
					//				}

					//Date result = new Date();
					//DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					//String convertido = formatter.format(result);
					//					String file = archivo.getSelectedFile().getPath(); // obtiene ruta seleccionada en el diálogo
					//		            String NombreFoto=archivo.getSelectedFile().getName(); // nombre del fichero dado 
					String carpeta= "C:\\Users\\DeveloperTI\\Documents\\Fotos de Empleados";
					String archivo="\\" + file.getSelectedFile().getName() + "";
					System.out.println("archivo: " + archivo);
					File crearCarpeta = new File(carpeta);
					File crearArchivo = new File(carpeta + archivo);
					if(crearArchivo.exists()){
						System.out.println("El Archivo ya existe");
					}else {
						System.out.println("El Archivo no existe, se creará");
						crearCarpeta.mkdir();
						try {
							if(crearArchivo.createNewFile()) {
								System.out.println("Archivo creado");
							}else {
								System.out.println("Archivo no creado");
							}
						} catch (IOException e1) {
							e1.printStackTrace();
							LOG.info("Excepción: " + e1);
						}
					}

					//						BufferedImage imagen;
					//			            File f = new File("img/hombre-de-negocios.png");
					//			            try {
					//							imagen = ImageIO.read(f);
					//							ImageIO.write(imagen, "png", new File("imagen2.png"));
					//						} catch (IOException e1) {
					//							e1.printStackTrace();
					//						}


				}//fin del if seleccion
				JOptionPane.showMessageDialog(null, "Foto Guardada");
			}//actionperformed
		});//actionlistener
		btnNewCargarFoto.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("photos.png"))));
		btnNewCargarFoto.setBounds(1335, 243, 138, 39);
		panel.add(btnNewCargarFoto);


		lblrutaPhoto.setBounds(1307, 293, 187, 14);
		panel.add(lblrutaPhoto);

		JButton btnNewButton = new JButton("Guardar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JLabel limg;
				//CargarPhoto cp = new CargarPhoto();
				if (!lblrutaPhoto.getText().equals("")) {
					try {
						//						BufferedImage img = ImageIO.read(new File(fichero.toString()));
						//						String image_string = cp.encodeToString(img);
						//						cp.guardar_imagen(image_string, fichero.getName());


					} catch (Exception ex) {
						Logger.getLogger(CargarPhoto.class.getName()).log(Level.SEVERE, null, ex);
					}

				}
			}
		});
		btnNewButton.setBounds(1368, 318, 89, 23);
		panel.add(btnNewButton);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Datos Bancarios", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.info));
		panel_2.setBackground(SystemColor.inactiveCaption);
		panel_2.setBounds(991, 203, 306, 137);
		panel.add(panel_2);
		panel_2.setLayout(null);

		lblCuentaBancaria.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCuentaBancaria.setForeground(Color.BLACK);
		lblCuentaBancaria.setBounds(10, 16, 101, 17);
		panel_2.add(lblCuentaBancaria);

		lblMonedero.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMonedero.setForeground(Color.BLACK);
		lblMonedero.setBounds(10, 50, 92, 17);
		panel_2.add(lblMonedero);

		lblBanco.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBanco.setForeground(Color.BLACK);
		lblBanco.setBounds(29, 86, 73, 17);
		panel_2.add(lblBanco);

		textFieldCtaBancaria = new JTextField();
		textFieldCtaBancaria.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(Character.isLowerCase(c)) {
					String cadena = (""+c).toUpperCase();
					c=cadena.charAt(0);
					e.setKeyChar(c);
				}

				if(!Character.isDigit(c) && c!=KeyEvent.VK_SPACE && c!=KeyEvent.VK_BACK_SPACE) {//c<'0' || c>'9'
					textFieldCtaBancaria.setBackground(new Color(255,255,000));
					JOptionPane.showMessageDialog(null, "Solo se aceptan números");
					e.consume();
					textFieldCtaBancaria.setBackground(new Color(255,255,255));
				}
			}
		});
		textFieldCtaBancaria.setForeground(Color.BLACK);
		textFieldCtaBancaria.setColumns(10);
		textFieldCtaBancaria.setBackground(Color.WHITE);
		textFieldCtaBancaria.setBounds(113, 11, 183, 30);
		panel_2.add(textFieldCtaBancaria);

		textFieldMonedero = new JTextField();
		textFieldMonedero.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(Character.isLowerCase(c)) {
					String cadena = (""+c).toUpperCase();
					c=cadena.charAt(0);
					e.setKeyChar(c);
				}

				if(!Character.isDigit(c) && c!=KeyEvent.VK_SPACE && c!=KeyEvent.VK_BACK_SPACE) {//c<'0' || c>'9'
					textFieldMonedero.setBackground(new Color(255,255,000));
					JOptionPane.showMessageDialog(null, "Solo se aceptan números");
					e.consume();
					textFieldMonedero.setBackground(new Color(255,255,255));
				}

			}
		});
		textFieldMonedero.setForeground(Color.BLACK);
		textFieldMonedero.setColumns(10);
		textFieldMonedero.setBackground(Color.WHITE);
		textFieldMonedero.setBounds(113, 45, 183, 30);
		panel_2.add(textFieldMonedero);

		comboBoxBanco.setForeground(Color.BLACK);
		comboBoxBanco.setBounds(112, 79, 184, 30);
		panel_2.add(comboBoxBanco);

		lblIdTipoNominaOculta.setVisible(false);
		lblIdTipoNominaOculta.setBounds(991, 188, 229, 14);
		panel.add(lblIdTipoNominaOculta);

		lblSalario.setBounds(984, 163, 94, 14);
		panel.add(lblSalario);

		lblIdPuesto.setBounds(984, 145, 76, 14);
		panel.add(lblIdPuesto);

		lblIdDependencia.setBounds(984, 130, 72, 14);
		panel.add(lblIdDependencia);

		//		panelPuestos.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		//		panelPuestos.setBounds(996, 455, 499, 190);
		//		panel.add(panelPuestos);
		//		panelPuestos.setLayout(null);
		//		scrollPanePuestos.setBounds(10, 49, 479, 130);
		//		panelPuestos.add(scrollPanePuestos);

		//		scrollPanePuestos.setViewportBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		//		scrollPanePuestos.setViewportBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		//		scrollPanePuestos.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		//		scrollPanePuestos.setViewportView(tablePuestos);

		//		JLabel lblIconPuesto = new JLabel();
		//		lblIconPuesto.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("icons8_Search_32px_2.png"))));
		//		lblIconPuesto.setBounds(10, 11, 46, 28);
		//		panelPuestos.add(lblIconPuesto);

		//		textFieldBuscarPuesto = new JTextField();
		//		textFieldBuscarPuesto.addKeyListener(new KeyAdapter() {
		//			@Override
		//			public void keyReleased(KeyEvent e) {
		//				rowSorterPuesto.setRowFilter(RowFilter.regexFilter(textFieldBuscarPuesto.getText().toUpperCase(), IdBusquedaPuesto));
		//			}
		//		});
		//		textFieldBuscarPuesto.setColumns(10);
		//		textFieldBuscarPuesto.setBorder(null);
		//		textFieldBuscarPuesto.setBackground(SystemColor.menu);
		//		textFieldBuscarPuesto.setBounds(53, 11, 229, 28);
		//		panelPuestos.add(textFieldBuscarPuesto);
		//		
		//		JSeparator separator = new JSeparator();
		//		separator.setForeground(new Color(176, 196, 222));
		//		separator.setBackground(new Color(176, 196, 222));
		//		separator.setBounds(53, 43, 229, 3);
		//		panelPuestos.add(separator);
		//		
		//		JButton btnCerrarPuesto = new JButton("Cerrar");
		//		btnCerrarPuesto.addActionListener(new ActionListener() {
		//			public void actionPerformed(ActionEvent e) {
		//				dispose();
		//			}
		//		});
		//		btnCerrarPuesto.setBounds(400, 15, 89, 23);
		//		panelPuestos.add(btnCerrarPuesto);

		//		JButton btnSeleccionarPuesto = new JButton("Seleccionar");
		//		btnSeleccionarPuesto.addActionListener(new ActionListener() {
		//			public void actionPerformed(ActionEvent e) {
		//				int fila = tablePuestos.getSelectedRow();
		//				if(fila>=0) {
		//					String nopuesto = tablePuestos.getValueAt(fila, 0).toString();
		//					String puesto = tablePuestos.getValueAt(fila, 1).toString();
		//					textFieldAsignarPuesto.setText(puesto);
		//					textFieldNoPuesto.setText(nopuesto);
		//				}else {
		//					JOptionPane.showMessageDialog(null, "No ha seleccionado ningun puesto");
		//				}
		//			}
		//		});
		//		btnSeleccionarPuesto.setBounds(302, 15, 89, 23);
		//		panelPuestos.add(btnSeleccionarPuesto);
		//		
		//		panelDependencias.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		//		panelDependencias.setBounds(1024, 427, 475, 194);
		//		panel.add(panelDependencias);
		//		panelDependencias.setLayout(null);
		//		
		//		JLabel lblIconDepen = new JLabel();
		//		lblIconDepen.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("icons8_Search_32px_2.png"))));
		//		lblIconDepen.setBounds(10, 14, 46, 28);
		//		panelDependencias.add(lblIconDepen);
		//		
		//		textFieldBuscarDependencia = new JTextField();
		//		textFieldBuscarDependencia.addKeyListener(new KeyAdapter() {
		//			@Override
		//			public void keyReleased(KeyEvent e) {
		//				rowSorterDependencia.setRowFilter(RowFilter.regexFilter(textFieldBuscarDependencia.getText().toUpperCase(), IdBusquedaDependencia));
		//			}
		//		});
		//		textFieldBuscarDependencia.setColumns(10);
		//		textFieldBuscarDependencia.setBorder(null);
		//		textFieldBuscarDependencia.setBackground(SystemColor.menu);
		//		textFieldBuscarDependencia.setBounds(58, 14, 229, 28);
		//		panelDependencias.add(textFieldBuscarDependencia);

		//		JSeparator separator_1 = new JSeparator();
		//		separator_1.setForeground(new Color(176, 196, 222));
		//		separator_1.setBackground(new Color(176, 196, 222));
		//		separator_1.setBounds(58, 45, 229, 3);
		//		panelDependencias.add(separator_1);
		//		
		//		JScrollPane scrollPaneDepedencias = new JScrollPane();
		//		scrollPaneDepedencias.setViewportBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		//		scrollPaneDepedencias.setBounds(10, 59, 455, 124);
		//		panelDependencias.add(scrollPaneDepedencias);
		//		

	}


	public double insertarCalculoNominaSalarioIsr(String IdPuesto,String IdDependencia,String clave) {
		System.out.println("INICIA EL PROCESO DE INGRESAR EL ISR PARA NUEVO EMPLEADO");
		InternalFrameMovimientos internalFrameMovimientos = new InternalFrameMovimientos();
		int resultado = 0;
		int claveInternaPercepcionSalario=1;	
		int claveDeduccionIsr=2;
		double valorClaveInternaDeduccionIsr=internalFrameMovimientos.calcularISRNuevoEmpleado(IdPuesto,clave);
		System.out.println("valorClaveInternaDeduccionIsr: "+ valorClaveInternaDeduccionIsr);
		PoolNYCH nych = new PoolNYCH();
		Connection conn =null;
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date diaHoy = Calendar.getInstance().getTime();

		//if(FormularioPrincipal.lblIsNomC.getText().equalsIgnoreCase("OK")) {
		String sqlInsert="INSERT INTO dbo.CALCULO_NOMINA (ID_EMPLEADO,ID_PERCEPCION,ID_DEDUCCION,VALOR_PERCEPCION,VALOR_DEDUCCION,ID_PUESTO,FECHA_CALCULO,ID_DEPENDENCIA,ID_TIPO_NOMINA,ESTATUS,PARCIALIDADES)"
				+ "VALUES ("+ textFieldClave.getText()+","+ claveInternaPercepcionSalario +","+ claveDeduccionIsr +","+ lblSalario.getText() +","
				+ ""+valorClaveInternaDeduccionIsr +","+ IdPuesto +",'"+formatter.format(diaHoy)+"','"+IdDependencia+"',"
				+ "'"+FormularioPrincipal.lblIdTipoNomina.getText()+"','"+1+"',"+0+")";
		System.out.println("SQL INSERT ANTICIPO DE NOMINA: "+sqlInsert);

		try {
			conn = nych.datasource.getConnection();
			PreparedStatement pps = conn.prepareStatement(sqlInsert);
			pps.executeUpdate();
			//JOptionPane.showMessageDialog(null, "Datos Guardados");
			System.out.println("Datos Guardados");
		} catch (SQLException el) { 
			el.printStackTrace();
			StringWriter errors = new StringWriter();
			el.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: "+ errors );
			//JOptionPane.showMessageDialog(null, el, "Error de conexion", JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
		}finally {
			try {
				conn.close();
			} catch (SQLException ep) {
				ep.printStackTrace();
				StringWriter errors = new StringWriter();
				ep.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: "+ errors );
				//JOptionPane.showMessageDialog(null, ep, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
			}
		}//fin del finally
		//}//fin del if
		return valorClaveInternaDeduccionIsr;
	}//fin del metodo

	public double insertarCalculoNominaAyudaImss(String clave, String idPuesto, String IdDependencia) {
		InternalFrameMovimientos internalFrameMovimientos =  new InternalFrameMovimientos();
		int resultado = 0;
		int claveInternaPercepcionAyuda=26;	
		int claveDeduccionImss=1;
		double valorClaveInternaPercepcionAyuda=494.82;
		double valorClaveInternaDeduccionImss=internalFrameMovimientos.calcularIMSSNuevoEmpleado(clave);
		System.out.println("valorClaveInternaDeduccionImss: "+valorClaveInternaDeduccionImss);
		PoolNYCH nych = new PoolNYCH();
		Connection conn =null;
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date diaHoy = Calendar.getInstance().getTime();

		//if(FormularioPrincipal.lblIsNomC.getText().equalsIgnoreCase("OK")) {
		String sqlInsert="INSERT INTO dbo.CALCULO_NOMINA (ID_EMPLEADO,ID_PERCEPCION,ID_DEDUCCION,VALOR_PERCEPCION,VALOR_DEDUCCION,ID_PUESTO,FECHA_CALCULO,ID_DEPENDENCIA,ID_TIPO_NOMINA,ESTATUS,PARCIALIDADES)" 
				+ "VALUES ("+ textFieldClave.getText()+","+ claveInternaPercepcionAyuda +","+ claveDeduccionImss +","+ valorClaveInternaPercepcionAyuda +","
				+ ""+valorClaveInternaDeduccionImss +","+ idPuesto +",'"+formatter.format(diaHoy)+"','"+IdDependencia+"',"
				+ "'"+FormularioPrincipal.lblIdTipoNomina.getText()+"','"+1+"',"+0+")";
		System.out.println("SQL INSERT imss nuevo ingreso: "+sqlInsert);

		try {
			conn = nych.datasource.getConnection();
			PreparedStatement pps = conn.prepareStatement(sqlInsert);
			pps.executeUpdate();
			//OptionPane.showMessageDialog(null, "Datos Guardados");
			System.out.println("Datos Guardados");
		} catch (SQLException el) { 
			el.printStackTrace();
			StringWriter errors = new StringWriter();
			el.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: "+ errors );
			//JOptionPane.showMessageDialog(null, el, "Error de conexion", JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
		}finally {
			try {
				conn.close();
			} catch (SQLException ep) {
				ep.printStackTrace();
				StringWriter errors = new StringWriter();
				ep.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: "+ errors );
				//JOptionPane.showMessageDialog(null, ep, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
			}
		}//fin del finally
		//}//fin del if
		return valorClaveInternaDeduccionImss;
	}//fin del metodo

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

	public static ArrayList<String> getClaves() {
		PoolNYCH nych = new PoolNYCH();
		Connection connect = null;
		ResultSet resultSet = null;
		String sqlSelectEjercicio = "SELECT * FROM DBO.EMPLEADOS";
		ArrayList<String> lista = new ArrayList<String>();
		try {
			connect = nych.datasource.getConnection();
			Statement st = connect.createStatement();
			resultSet = st.executeQuery(sqlSelectEjercicio);
			while (resultSet.next()) {
				lista.add(resultSet.getString("CLAVE"));
			}
		} catch (Exception exp) {
			exp.printStackTrace();
			StringWriter errors = new StringWriter();
			exp.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: " + errors);
			JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");
		} finally {
			try {
				connect.close();
			} catch (SQLException ep) {
				ep.printStackTrace();
				StringWriter errors = new StringWriter();
				ep.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: " + errors);
				JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");
			}
		}//fin del finally
		return lista;
	}//fin del metodo


	@SuppressWarnings({ "static-access", "unchecked" })
	public int insertarRegistro() {
		PoolNYCH nych = new PoolNYCH();
		Connection con =null;
		int resultado = 0;
		Date fechaNacimiento = null;
		Date fechaAltaImss =null;
		Date fechaBajaImss = null;
		Date fechaAltaIngreso = null;
		Date fechaBajaIngreso = null;
		Date fechaMovimiento = Calendar.getInstance().getTime();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String target = "1900-01-01";
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date result = null;
		String localidad =null;
		int genero = 0;
		int edoCivil = 0;
		String respGenero  = null;
		String respEdocivil  = null;
		String respClasDispersion  = null;
		String respRegimen  = null;
		String respTipoEmpleado  = null;
		String respTipoJornada  = null;
		String respRiesgo  = null;
		String respTipoContrato  = null;
		String respClasEmpleado  = null;
		String respBanco  = null;
		String nci = "0";


		if(textFieldNCI.getText()==null){
			try {
				 nci = "0"; 
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			nci = textFieldNCI.getText();
		}


		if(dateChooserNvoFechaNac.getDate()==null){
			try {
				result = df.parse(target);
			} catch (ParseException e) {
				e.printStackTrace();
			}  
			fechaNacimiento = result;
		}else {
			fechaNacimiento = dateChooserNvoFechaNac.getDate();
		}

		if(dateChooserNvoFechaAltaImss.getDate()==null){
			try {
				result = df.parse(target);
			} catch (ParseException e) {
				e.printStackTrace();
			}  
			fechaAltaImss = result;
		}else {
			fechaAltaImss = dateChooserNvoFechaAltaImss.getDate();
		}

		if(dateChooserFechaBajaImss.getDate() == null){
			try {
				result = df.parse(target);
			} catch (ParseException e) {
				e.printStackTrace();
			}  
			fechaBajaImss = result;
		}else {
			fechaBajaImss = dateChooserFechaBajaImss.getDate();
		}

		if(dateChooserFechaAltaIngreso.getDate()==null){
			try {
				result = df.parse(target);
			} catch (ParseException e) {
				e.printStackTrace();
			}  
			fechaAltaIngreso = result;
		}else {
			fechaAltaIngreso = dateChooserFechaAltaIngreso.getDate();
		}

		if(dateChooserFechaBajaIngreso.getDate()==null){
			try {
				result = df.parse(target);
			} catch (ParseException e) {
				e.printStackTrace();
			}  
			fechaBajaIngreso = result;
		}else {
			fechaBajaIngreso = dateChooserFechaBajaIngreso.getDate();
		}

		if(comboBoxGenero.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(null, "Seleccione el genero de la persona");

			String[] gen = {
					"Masculino",
					"Femenino",
			};
			MyIcon icon = new MyIcon();
			respGenero = (String) JOptionPane.showInputDialog(null, "Seleccione el genero de la persona", "Información", JOptionPane.DEFAULT_OPTION, icon, gen, gen[0]);
			System.out.println("respp: " +respGenero);			
			if(respGenero == "Masculino") {
				respGenero= String.valueOf(1);
				System.out.println("resp1: " +respGenero);
			}

			if(respGenero == "Femenino") {
				respGenero= String.valueOf(2);
				System.out.println("resp2: " +respGenero);

			}
		}else {
			respGenero = String.valueOf(comboBoxGenero.getSelectedIndex());
			System.out.println("respuesta3: " +respGenero);
		}

		if(comboBoxEdoCivil.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(null, "Seleccione el estado civil de la persona");

			String[] genEdo = {
					"Soltero(a)",
					"Casado(a)",
					"Divorciado(a)",
					"Viudo(a)",
					"Union Libre",
			};
			MyIcon icon = new MyIcon();
			respEdocivil = (String) JOptionPane.showInputDialog(null, "Seleccione el estado civil de la persona", "Información", JOptionPane.DEFAULT_OPTION, icon, genEdo, genEdo[0]);
			System.out.println("respp: " +respEdocivil);			
			if(respEdocivil == "Soltero(a)") {
				respEdocivil= String.valueOf(1);
				System.out.println("resp1: " +respEdocivil);
			}

			if(respEdocivil == "Casado(a)") {
				respEdocivil= String.valueOf(2);
				System.out.println(respEdocivil);

			}

			if(respEdocivil == "Divorciado(a)") {
				respEdocivil= String.valueOf(3);
				System.out.println("resp3: " +respEdocivil);

			}

			if(respEdocivil == "Viudo(a)") {
				respEdocivil= String.valueOf(4);
				System.out.println("resp4: " +respEdocivil);

			}

			if(respEdocivil == "Union Libre") {
				respEdocivil= String.valueOf(5);
				System.out.println("resp5: " +respEdocivil);

			}

		}else {
			respEdocivil = String.valueOf(comboBoxEdoCivil.getSelectedIndex());
			System.out.println("respuesta6: " +respEdocivil);
		}

		if(comboBoxLocalidad.getSelectedItem() ==  "Seleccione Una"){
			try {

				JOptionPane.showMessageDialog(null, "Seleccione la localidad de la persona");

				InternalFrameEmpleado internalFrameEmpleado = InternalFrameEmpleado();
				ArrayList<String> listaLocalidad = new ArrayList<String>();
				listaLocalidad = internalFrameEmpleado.buscarLocalidad();
				comboBoxLocalidad.addItem("Seleccione Una");
				for (int i = 0; i < listaLocalidad.size(); i++) {
					comboBoxLocalidad.addItem(listaLocalidad.get(i));
				}

				MyIcon icon = new MyIcon();
				localidad = (String) JOptionPane.showInputDialog(null, "Seleccione la localidad de la persona", "Información", JOptionPane.DEFAULT_OPTION, 
						icon, listaLocalidad.toArray(), listaLocalidad.size());
				System.out.println("localidad: " +localidad);

			} catch (Exception e) {
				e.printStackTrace();
			}  

		}else {
			localidad = 
					comboBoxLocalidad.getSelectedItem().toString();
		}

		if(comboBoxClasificacionDispersion.getSelectedIndex() == 0 ) {

			JOptionPane.showMessageDialog(null, "Seleccione la clasificacion de dispersión de la persona");

			ArrayList<String> listaClasificacionDispersion = new ArrayList<String>();
			listaClasificacionDispersion = internalFrameEmpleado.buscarClasificacionDispersion();
			comboBoxClasificacionDispersion.addItem("Seleccione Una");
			for (int i = 0; i < listaClasificacionDispersion.size(); i++) {
				comboBoxClasificacionDispersion.addItem(listaClasificacionDispersion.get(i));
			}

			MyIcon icon = new MyIcon();
			respClasDispersion = (String) JOptionPane.showInputDialog(null, "Seleccione la clasificacion de dispersión de la persona", "Información", JOptionPane.DEFAULT_OPTION, 
					icon, listaClasificacionDispersion.toArray(),listaClasificacionDispersion.get(0));
			System.out.println("respClasDispersion: " +respClasDispersion);


			if(respClasDispersion.equalsIgnoreCase("N2J")) {
				respClasDispersion =  String.valueOf(1);
				System.out.println("respClasDispersionnn: " +respClasDispersion);
			}

			if(respClasDispersion.equalsIgnoreCase("N2D")) {
				respClasDispersion =  String.valueOf(2);
				System.out.println("respClasDispersionnn: " +respClasDispersion);
			}

			if(respClasDispersion.equalsIgnoreCase("N3J")) {
				respClasDispersion =  String.valueOf(3);
				System.out.println("respClasDispersionnn: " +respClasDispersion);
			}

			if(respClasDispersion.equalsIgnoreCase("R")) {
				respClasDispersion =  String.valueOf(4);
				System.out.println("respClasDispersionnn: " +respClasDispersion);
			}

			if(respClasDispersion.equalsIgnoreCase("N/A")) {
				respClasDispersion =  String.valueOf(5);
				System.out.println("respClasDispersionnn: " +respClasDispersion);
			}

		}else {
			respClasDispersion = String.valueOf(comboBoxClasificacionDispersion.getSelectedIndex());
			System.out.println("respClasDispersion7: " +respClasDispersion);
		}


		if(comboBoxRegimen.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(null, "Seleccione el regimen de trabajo de la persona");


			ArrayList<String> listaRegimen = new ArrayList<String>();
			listaRegimen = internalFrameEmpleado.buscarRegimen();
			comboBoxRegimen.addItem("Seleccione Uno");
			for (int i = 0; i < listaRegimen.size(); i++) {
				comboBoxRegimen.addItem(listaRegimen.get(i));
			}

			respRegimen = (String) JOptionPane.showInputDialog(null, "Seleccione el regimen de trabajo de la persona", "Información", JOptionPane.DEFAULT_OPTION, 
					null, listaRegimen.toArray(),listaRegimen.get(0));
			System.out.println("resp regimen seleecionado: " +respRegimen);

			if(respRegimen.equalsIgnoreCase("SUELDOS Y SALARIOS")) {
				respRegimen =  String.valueOf(1);
				System.out.println("resp regimen selecc: " +respRegimen);
			}

			if(respRegimen.equalsIgnoreCase("JUBILADOS")) {
				respRegimen =  String.valueOf(2);
				System.out.println("resp regimen selecc: " +respRegimen);
			}

			if(respRegimen.equalsIgnoreCase("PENSIONADOS")) {
				respRegimen =  String.valueOf(3);
				System.out.println("resp regimen selecc: " +respRegimen);
			}

			if(respRegimen.equalsIgnoreCase("ASIMILADOS A SALARIOS,MIEMBROS DE LAS SOCIEDADES COOPERATIVAS DE PRODUCCION")) {
				respRegimen =  String.valueOf(4);
				System.out.println("resp regimen selecc: " +respRegimen);
			}

			if(respRegimen.equalsIgnoreCase("ASIMILADOS A SALARIOS,INTEGRANTES DE SOCIEDADES Y ASOCIACIONES CIVILES")) {
				respRegimen =  String.valueOf(5);
				System.out.println("resp regimen selecc: " +respRegimen);
			}

			if(respRegimen.equalsIgnoreCase("ASIMILADOS A SALARIOS,MIEMBROS DE CONSEJOS DIRECTIVOS,DE VIGILANCIA,CONSULTIVOS,HONORARIOS A ADMINISTRADORES, COMISARIOS Y GERENTES GENERALES")) {
				respRegimen =  String.valueOf(6);
				System.out.println("resp regimen selecc: " +respRegimen);
			}

			if(respRegimen.equalsIgnoreCase("ASIMILADOS A SALARIOS,ACTIVIDAD EMPRESARIAL(COMISIONISTAS)")) {
				respRegimen =  String.valueOf(7);
				System.out.println("resp regimen selecc: " +respRegimen);
			}

			if(respRegimen.equalsIgnoreCase("ASIMILADOS A SALARIOS,HONORARIOS ASMILIADOS A SALARIOS)")) {
				respRegimen =  String.valueOf(8);
				System.out.println("resp regimen selecc: " +respRegimen);
			}

			if(respRegimen.equalsIgnoreCase("ASIMILADOS A SALARIOS,INGRESOS ACCIONES O TITULOS VALOR)")) {
				respRegimen =  String.valueOf(9);
				System.out.println("resp regimen selecc: " +respRegimen);
			}
		}else {
			respRegimen = String.valueOf(comboBoxRegimen.getSelectedIndex());
			System.out.println("respuesta regimen seleccionada: " +respRegimen);
		}


		if(comboBoxTipoEmpleado.getSelectedIndex() == 0){
			JOptionPane.showMessageDialog(null, "Seleccione el tipo de empleado");

			ArrayList<String> listaTipoEmpleado = new ArrayList<String>();
			listaTipoEmpleado = internalFrameEmpleado.buscarTipoEmpleado();
			comboBoxTipoEmpleado.addItem("Seleccione Uno");
			for (int i = 0; i < listaTipoEmpleado.size(); i++) {
				comboBoxTipoEmpleado.addItem(listaTipoEmpleado.get(i));
			}

			respTipoEmpleado = (String) JOptionPane.showInputDialog(null, "Seleccione el tipo de empleado", "Información", JOptionPane.DEFAULT_OPTION, 
					null, listaTipoEmpleado.toArray(),listaTipoEmpleado.get(0));
			System.out.println("resp tipo empleado seleecionado: " +respTipoEmpleado);

			if(respTipoEmpleado.equalsIgnoreCase("BASE")) {
				respTipoEmpleado =  String.valueOf(1);
				System.out.println("resp tipo empleado selecc: " +respTipoEmpleado);
			}

			if(respTipoEmpleado.equalsIgnoreCase("CONFIANZA")) {
				respTipoEmpleado =  String.valueOf(2);
				System.out.println("resp tipo empleado selecc: " +respTipoEmpleado);
			}

			if(respTipoEmpleado.equalsIgnoreCase("HONORARIOS")) {
				respTipoEmpleado =  String.valueOf(3);
				System.out.println("resp tipo empleado selecc: " +respTipoEmpleado);
			}

			if(respTipoEmpleado.equalsIgnoreCase("TEMPORAL")) {
				respTipoEmpleado =  String.valueOf(4);
				System.out.println("resp tipo empleado selecc: " +respTipoEmpleado);
			}

			if(respTipoEmpleado.equalsIgnoreCase("INTERINO")) {
				respTipoEmpleado =  String.valueOf(5);
				System.out.println("resp tipo empleado selecc: " +respTipoEmpleado);
			}

		}else {
			respTipoEmpleado = String.valueOf(comboBoxTipoEmpleado.getSelectedIndex());
			System.out.println("respuesta tipo empleado seleccionada: " +respTipoEmpleado);
		}

		if(comboBoxTipoJornada.getSelectedIndex() == 0){
			JOptionPane.showMessageDialog(null, "Seleccione el tipo de jornada");

			ArrayList<String> listaTipoJornada = new ArrayList<String>();
			listaTipoJornada = internalFrameEmpleado.buscarTipoJornada();
			comboBoxTipoJornada.addItem("Seleccione Una");
			for (int i = 0; i < listaTipoJornada.size(); i++) {
				comboBoxTipoJornada.addItem(listaTipoJornada.get(i));
			}

			respTipoJornada = (String) JOptionPane.showInputDialog(null, "Seleccione el tipo de jornada", "Información", JOptionPane.DEFAULT_OPTION, 
					null, listaTipoJornada.toArray(),listaTipoJornada.get(0));
			System.out.println("resp tipo de jornada seleecionado: " +respTipoJornada);

			if(respTipoJornada.equalsIgnoreCase("DIURNA")) {
				respTipoJornada =  String.valueOf(1);
				System.out.println("resp tipo de jornada selecc: " +respTipoJornada);
			}

			if(respTipoJornada.equalsIgnoreCase("NOCTURNA")) {
				respTipoJornada =  String.valueOf(2);
				System.out.println("resp tipo de jornada selecc: " +respTipoJornada);
			}

			if(respTipoJornada.equalsIgnoreCase("MIXTA")) {
				respTipoJornada =  String.valueOf(3);
				System.out.println("resp tipo de jornada selecc: " +respTipoJornada);
			}

			if(respTipoJornada.equalsIgnoreCase("POR HORA")) {
				respTipoJornada =  String.valueOf(4);
				System.out.println("resp tipo de jornada selecc: " +respTipoJornada);
			}

			if(respTipoJornada.equalsIgnoreCase("REDUCIDA")) {
				respTipoJornada =  String.valueOf(5);
				System.out.println("resp tipo de jornada selecc: " +respTipoJornada);
			}

		}else {
			respTipoJornada = String.valueOf(comboBoxTipoJornada.getSelectedIndex());
			System.out.println("respuesta tipo de jornada seleccionada: " +respTipoJornada);
		}


		if(comboBoxRiesgo.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(null, "Seleccione el tipo de riesgo del trabajador");

			ArrayList<String> listaRiesgo = new ArrayList<String>();
			listaRiesgo = internalFrameEmpleado.buscarRiesgo();
			comboBoxRiesgo.addItem("Seleccione Uno");
			for (int i = 0; i < listaRiesgo.size(); i++) {
				comboBoxRiesgo.addItem(listaRiesgo.get(i));
			}

			respRiesgo = (String) JOptionPane.showInputDialog(null, "Seleccione el tipo de empleado", "Información", JOptionPane.DEFAULT_OPTION, 
					null, listaRiesgo.toArray(),listaRiesgo.get(0));
			System.out.println("resp riesgo seleecionado: " +respRiesgo);

			if(respRiesgo.equalsIgnoreCase("CLASE I")) {
				respRiesgo =  String.valueOf(1);
				System.out.println("resp riesgo selecc: " +respRiesgo);
			}

			if(respRiesgo.equalsIgnoreCase("CLASE II")) {
				respRiesgo =  String.valueOf(2);
				System.out.println("resp riesgo selecc: " +respRiesgo);
			}

			if(respRiesgo.equalsIgnoreCase("CLASE III")) {
				respRiesgo =  String.valueOf(3);
				System.out.println("resp riesgo selecc: " +respRiesgo);
			}

			if(respRiesgo.equalsIgnoreCase("CLASE IV")) {
				respRiesgo =  String.valueOf(4);
				System.out.println("resp riesgo selecc: " +respRiesgo);
			}

			if(respRiesgo.equalsIgnoreCase("CLASE V")) {
				respRiesgo =  String.valueOf(5);
				System.out.println("resp riesgo selecc: " +respRiesgo);
			}


		}else {
			respRiesgo = String.valueOf(comboBoxRiesgo.getSelectedIndex());
			System.out.println("respuesta tipo de riesgo seleccionada: " +respRiesgo);
		}


		if(comboBoxTipoContrato.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(null, "Seleccione el tipo de contrato del trabajador");

			ArrayList<String> listaTipoContrato = new ArrayList<String>();
			listaTipoContrato = internalFrameEmpleado.buscarTipoContrato();
			comboBoxTipoContrato.addItem("Seleccione Uno");
			for (int i = 0; i < listaTipoContrato.size(); i++) {
				comboBoxTipoContrato.addItem(listaTipoContrato.get(i));
			}

			respTipoContrato = (String) JOptionPane.showInputDialog(null, "Seleccione el tipo de contrato del trabajador", "Información", JOptionPane.DEFAULT_OPTION, 
					null, listaTipoContrato.toArray(),listaTipoContrato.get(0));
			System.out.println("resp tipo contrato seleecionado: " +respTipoContrato);

			if(respTipoContrato.equalsIgnoreCase("CONTRATO DE TRABAJO POR TIEMPO INDETERMINADO")) {
				respTipoContrato =  String.valueOf(1);
				System.out.println("resp tipo de contrato selecc: " +respTipoContrato);
			}


			if(respTipoContrato.equalsIgnoreCase("CONTRATO DE TRABAJO POR OBRA DETERMINADA")) {
				respTipoContrato =  String.valueOf(2);
				System.out.println("resp tipo de contrato selecc: " +respTipoContrato);
			}


			if(respTipoContrato.equalsIgnoreCase("CONTRATO DE TRABAJO POR TIEMPO DETERMINADO")) {
				respTipoContrato =  String.valueOf(3);
				System.out.println("resp tipo de contrato selecc: " +respTipoContrato);
			}


			if(respTipoContrato.equalsIgnoreCase("CONTRATO DE TRABAJO POR TEMPORADA")) {
				respTipoContrato =  String.valueOf(4);
				System.out.println("resp tipo de contrato selecc: " +respTipoContrato);
			}


			if(respTipoContrato.equalsIgnoreCase("CONTRATO DE TRABAJO SUJETO A PRUEBA")) {
				respTipoContrato =  String.valueOf(5);
				System.out.println("resp tipo de contrato selecc: " +respTipoContrato);
			}


			if(respTipoContrato.equalsIgnoreCase("CONTRATO DE TRABAJO CON CAPACITACION INICAL")) {
				respTipoContrato =  String.valueOf(6);
				System.out.println("resp tipo de contrato selecc: " +respTipoContrato);
			}


			if(respTipoContrato.equalsIgnoreCase("CONTRATO DE TRABAJO POR PAGO DE HORA LABORADA")) {
				respTipoContrato =  String.valueOf(7);
				System.out.println("resp tipo de contrato selecc: " +respTipoContrato);
			}


			if(respTipoContrato.equalsIgnoreCase("CONTRATO DE TRABAJO POR COMISION LABORAL")) {
				respTipoContrato =  String.valueOf(8);
				System.out.println("resp tipo de contrato selecc: " +respTipoContrato);
			}


			if(respTipoContrato.equalsIgnoreCase("CONTRATO DE TRABAJO JUBILACION,RETIRO,PENSION")) {
				respTipoContrato =  String.valueOf(9);
				System.out.println("resp tipo de contrato selecc: " +respTipoContrato);
			}


			if(respTipoContrato.equalsIgnoreCase("OTRO TIPO DE CONTRATO DE TRABAJO")) {
				respTipoContrato =  String.valueOf(10);
				System.out.println("resp tipo de contrato selecc: " +respTipoContrato);
			}
		}else {
			respTipoContrato = String.valueOf(comboBoxTipoContrato.getSelectedIndex());
			System.out.println("respuesta tipo de contrato seleccionada: " +respTipoContrato);
		}

		if(comboBoxCalsificacionEmpleado.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(null, "Seleccione la clasificacion del trabajador");

			ArrayList<String> listaClasifEmpleado = new ArrayList<String>();
			listaClasifEmpleado = internalFrameEmpleado.buscarClasifEmpleado();
			comboBoxCalsificacionEmpleado.addItem("Seleccione Uno");
			for (int i = 0; i < listaClasifEmpleado.size(); i++) {
				comboBoxCalsificacionEmpleado.addItem(listaClasifEmpleado.get(i));
			}

			respClasEmpleado = (String) JOptionPane.showInputDialog(null, "Seleccione la clasificacion del trabajador", "Información", JOptionPane.DEFAULT_OPTION, 
					null, listaClasifEmpleado.toArray(),listaClasifEmpleado.get(0));
			System.out.println("resp clasificacion empleado seleecionado: " +respTipoContrato);

			if(respClasEmpleado.equalsIgnoreCase("ADMINISTRATIVO")) {
				respClasEmpleado =  String.valueOf(1);
				System.out.println("resp clasificacion empleado selecc: " +respClasEmpleado);
			}

			if(respClasEmpleado.equalsIgnoreCase("DIRECTOR")) {
				respClasEmpleado =  String.valueOf(2);
				System.out.println("resp clasificacion empleado selecc: " +respClasEmpleado);
			}

			if(respClasEmpleado.equalsIgnoreCase("REGIDOR")){
				respClasEmpleado =  String.valueOf(3);
				System.out.println("resp clasificacion empleado selecc: " +respClasEmpleado);
			}

			if(respClasEmpleado.equalsIgnoreCase("SINDICO")) {
				respClasEmpleado =  String.valueOf(4);
				System.out.println("resp clasificacion empleado selecc: " +respClasEmpleado);
			}

			if(respClasEmpleado.equalsIgnoreCase("OPERATIVO")) { 
				respClasEmpleado =  String.valueOf(5);
				System.out.println("resp clasificacion empleado selecc: " +respClasEmpleado);
			}
		}else {
			respClasEmpleado = String.valueOf(comboBoxCalsificacionEmpleado.getSelectedIndex());
			System.out.println("respuesta clasificacion empleado seleccionada: " +respClasEmpleado);
		}


		//		if(comboBoxBanco.getSelectedIndex() == 0) {
		//			JOptionPane.showMessageDialog(null, "Seleccione el banco para dispersión del trabajador");
		//			
		//			
		//			
		//		}else {
		//			respBanco = String.valueOf(comboBoxBanco.getSelectedIndex());
		//			System.out.println("respuesta banco dispersion seleccionada: " +respBanco);
		//		}

		String sqlInsert = "INSERT INTO dbo.EMPLEADOS (NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,FECHA_NACIMIENTO,CURP,SDI,RFC,REGISTRO_IMSS,FECHA_ALTA_IMSS,FECHA_BAJA_IMSS,ID_PUESTO,FECHA_INGRESO,FECHA_BAJA,"
				+ "DIRECCION,TELEFONO,CIUDAD,CODIGO_POSTAL,ID_GENERO,ESTADO_CIVIL,EMAIL,LOCALIDAD,CUENTA_BANCARIA,MONEDERO,ID_BANCO,ID_UNIDAD,ID_EJERCICIOS,ID_FOTO,EMAIL_CONTACTO,CELULAR_CONTACTO,"
				+ "DIRECCION_CONTACTO,CONTACTO_FAMILIAR,ESTADO,NO_CREDITO_INFONAVIT,FACTOR_INFONAVIT,U_M_A,BIMESTRE,DIAS_BIMESTRES,IMPUESTO_INFONAVIT,CATORCENAS_BIMESTRE,SEMANAS_BIMESTRE,ELIMINAR_LOGICO,"
				+ "ID_CLASIFICACION_DISPERSION,ID_REGIMEN,ID_TIPO_EMPLEADO,ID_TIPO_JORNADA,ID_RIESGO,ID_CONTRATO,ID_CLASIFICACION_EMPLEADO,FECHA_MOVIMIENTO)"

				+ "VALUES ('"+ textFieldNvoNombre.getText().toUpperCase() +"','"+textFieldNvoApPat.getText().toUpperCase() +"','"+ textFieldNvoApMat.getText().toUpperCase()+"',convert(datetime,'"+ formatter.format(fechaNacimiento)+"',101),"
				+ "'"+textFieldNvoCurp.getText() +"',"+textFieldSDINuevo.getText()+",'"+textFieldNvoRFC.getText() +"','"+  textFieldNvoRegImss.getText()+"',"
				+ "convert(datetime,'"+ formatter.format(fechaAltaImss)+"',101),convert(datetime,'"+ formatter.format(fechaBajaImss)+"',101),"
				+ "'"+textFieldNoPuesto.getText()+"',convert(datetime,'"+ formatter.format(fechaAltaIngreso)+"',101),"
				+ "convert(datetime,'"+ formatter.format(fechaBajaIngreso)+"',101),'"+textFieldDireccion.getText().toUpperCase()+"','"
				+ textFieldNvoTel.getText()+"','"+textFieldNvoCiudad.getText().toUpperCase() +"','"+textFieldNvoCodPost.getText() +"',"+respGenero+",'"+respEdocivil+"','"
				+ textFieldNvoCorreo.getText()+"','"+localidad+"','"+textFieldCtaBancaria.getText() +"','"+textFieldMonedero.getText()+"','"
				+ comboBoxBanco.getSelectedIndex()+ "','" +textFieldIdDepen.getText()+"','"+lblIdTipoNominaOculta.getText() +"','"/*'"+comboBoxEjercicio.getSelectedIndex() +"'*/
				+ lblNewFoto.getText().toString()+"','"+ textFieldContactoEmail.getText() +"','"+ textFieldContactoCelular.getText() +"','"+textFieldContactoDireccion.getText().toUpperCase()+"',"
				+ "'"+textFieldNvoContacto.getText().toUpperCase()+"','"+textFieldNvoEstado.getText().toUpperCase()+"','"+ nci +"',"+ textFieldFI.getText() +","+textFieldUMA.getText()+","+2/*bimestre*/+","
				+ ""+ 61/*dias bimestre*/+","+15/*impuesto_infonavit*/+","+4/*catorcenas bimestre*/+","+8/*semanas bimestre*/+","+1+","+respClasDispersion+","
				+ ""+respRegimen+","+respTipoEmpleado+","+ respTipoJornada+","+ respRiesgo +","
				+ ""+respTipoContrato+","+ respClasEmpleado+","+formatter.format(fechaMovimiento) +")";
		System.out.println(sqlInsert);
		//		sqlInsert="";
		//		System.out.println("SQLINSERT VACIO: "+sqlInsert);
		try {
			con = nych.datasource.getConnection();
			PreparedStatement pps = con.prepareStatement(sqlInsert);
			pps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Empleado guardado");
		} catch (SQLException el) {
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
		}
		return resultado;
	}



	private InternalFrameEmpleado InternalFrameEmpleado() {
		return null;
	}


	@SuppressWarnings("static-access")
	public int insertarReportePercepcionSalario(int clave,int idPuesto, int idDependencia, int idPercepcion,String valor) {
		System.out.println("insert reporte percepcion salario");
		int resultado = 0;
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date diaHoy = Calendar.getInstance().getTime();
		PoolNYCH nych = new PoolNYCH();
		Connection con1 =null;
		PreparedStatement pps1;
		String sqlInsert1 = "";
		try {
			sqlInsert1="INSERT INTO DBO.REPORTE_NOMINA_PERCEPCIONES (ID_EMPLEADO,ID_PUESTO,ID_UNIDAD,ID_PERCEPCION,VALOR_PERCEPCION,FECHA_CALCULO,ID_TIPO_NOMINA,PERIODO,PARCIALIDADES) "
					+ "VALUES ('"+ clave + "','"+ idPuesto +"','"+ idDependencia +"','"+idPercepcion +"','"+ Double.parseDouble(valor) +"',convert(datetime,'"+ formatter.format(diaHoy)+"',101),'"+ FormularioPrincipal.lblIdTipoNomina.getText() +"',"
							+ ""+null+","+null+")";
			System.out.println("sql insert reporte percepcion salario: " + sqlInsert1);
			try {
				con1 = nych.datasource.getConnection();
				pps1 = con1.prepareStatement(sqlInsert1);
				pps1.executeUpdate();
//				JOptionPane.showMessageDialog(null, "Datos Guardados");
				System.out.println("Datos Guardados en insertarReportePercepcionSalario");
			} catch (SQLException el) {
				el.printStackTrace();
				StringWriter errors = new StringWriter();
				el.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: "+ errors );
				JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
			}finally {
				try {
					con1.close();
				} catch (SQLException ep) {
					ep.printStackTrace();
					StringWriter errors = new StringWriter();
					ep.printStackTrace(new PrintWriter(errors));
					LOG.info("Excepcion: "+ errors );
					JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
				}
			}
		}catch(Exception insertarEmpleadoReporteNomina ) {
			insertarEmpleadoReporteNomina.printStackTrace();
			StringWriter errors = new StringWriter();
			insertarEmpleadoReporteNomina.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: "+ errors );
		}

		return resultado;

	}
	
	@SuppressWarnings("static-access")
	public int insertarReportePercepcionAyuda(int clave,int idPuesto, int idDependencia, int idPercepcion,double valor) {
		System.out.println("insert reporte percepcion ayuda");
		int resultado = 0;
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date diaHoy = Calendar.getInstance().getTime();
		PoolNYCH nych = new PoolNYCH();
		Connection con1 =null;
		PreparedStatement pps1;
		String sqlInsert1 = "";
		try {
			sqlInsert1="INSERT INTO DBO.REPORTE_NOMINA_PERCEPCIONES (ID_EMPLEADO,ID_PUESTO,ID_UNIDAD,ID_PERCEPCION,VALOR_PERCEPCION,FECHA_CALCULO,ID_TIPO_NOMINA,PERIODO,PARCIALIDADES) "
					+ "VALUES ('"+ clave + "','"+ idPuesto +"','"+ idDependencia +"','"+idPercepcion +"','"+valor +"',convert(datetime,'"+ formatter.format(diaHoy)+"',101),'"+ FormularioPrincipal.lblIdTipoNomina.getText() +"',"
							+ ""+null+","+null+")";
			System.out.println("insert reporte percepcion ayuda: " + sqlInsert1);
			try {
				con1 = nych.datasource.getConnection();
				pps1 = con1.prepareStatement(sqlInsert1);
				pps1.executeUpdate();
//				JOptionPane.showMessageDialog(null, "Datos Guardados");
				System.out.println("Datos Guardados en insertarReportePercepcionAyuda");
			} catch (SQLException el) {
				el.printStackTrace();
				StringWriter errors = new StringWriter();
				el.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: "+ errors );
				JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
			}finally {
				try {
					con1.close();
				} catch (SQLException ep) {
					ep.printStackTrace();
					StringWriter errors = new StringWriter();
					ep.printStackTrace(new PrintWriter(errors));
					LOG.info("Excepcion: "+ errors );
					JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
				}
			}
		}catch(Exception insertarEmpleadoReporteNomina ) {
			insertarEmpleadoReporteNomina.printStackTrace();
			StringWriter errors = new StringWriter();
			insertarEmpleadoReporteNomina.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: "+ errors );
		}

		return resultado;

	}


	@SuppressWarnings("static-access")
	public int insertarReporteDeduccionISR(int clave,int idPuesto, int idDependencia, int idDeduccion ,double valor) {
		System.out.println("insert reporte deduccion isr");
		int resultado = 0;
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date diaHoy = Calendar.getInstance().getTime();
		PoolNYCH nych = new PoolNYCH();
		Connection con1 =null;
		PreparedStatement pps1;
		String sqlInsert1 = "";
		try {
			sqlInsert1="INSERT INTO DBO.REPORTE_NOMINA_DEDUCCIONES (ID_EMPLEADO,ID_PUESTO,ID_UNIDAD,ID_DEDUCCION,VALOR_DEDUCCION,FECHA_CALCULO,ID_TIPO_NOMINA,PERIODO,PARCIALIDADES) "
					+ "VALUES ('"+ clave + "','"+ idPuesto +"','"+ idDependencia +"','"+idDeduccion +"','"+valor +"',convert(datetime,'"+ formatter.format(diaHoy)+"',101),'"+ FormularioPrincipal.lblIdTipoNomina.getText() +"',"
							+ ""+null+","+null+")";
			System.out.println("insert reporte deduccion isr: " + sqlInsert1);
			try {
				con1 = nych.datasource.getConnection();
				pps1 = con1.prepareStatement(sqlInsert1);
				pps1.executeUpdate();
//				JOptionPane.showMessageDialog(null, "Datos Guardados");
				System.out.println("Datos Guardados en insertarReporteDeduccionISR");
			} catch (SQLException el) {
				el.printStackTrace();
				StringWriter errors = new StringWriter();
				el.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: "+ errors );
				JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
			}finally {
				try {
					con1.close();
				} catch (SQLException ep) {
					ep.printStackTrace();
					StringWriter errors = new StringWriter();
					ep.printStackTrace(new PrintWriter(errors));
					LOG.info("Excepcion: "+ errors );
					JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
				}
			}
		}catch(Exception insertarEmpleadoReporteNomina ) {
			insertarEmpleadoReporteNomina.printStackTrace();
			StringWriter errors = new StringWriter();
			insertarEmpleadoReporteNomina.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: "+ errors );
		}
		return resultado;
	}
	
	@SuppressWarnings("static-access")
	public int insertarReporteDeduccionIMSS(int clave,int idPuesto, int idDependencia, int idDeduccion ,double valor) {
		System.out.println("insert reporte deduccion imss");
		int resultado = 0;
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date diaHoy = Calendar.getInstance().getTime();
		PoolNYCH nych = new PoolNYCH();
		Connection con1 =null;
		PreparedStatement pps1;
		String sqlInsert1 = "";
		try {
			sqlInsert1="INSERT INTO DBO.REPORTE_NOMINA_DEDUCCIONES (ID_EMPLEADO,ID_PUESTO,ID_UNIDAD,ID_PERCEPCION,VALOR_DEDUCCION,FECHA_CALCULO,ID_TIPO_NOMINA,PERIODO,PARCIALIDADES) "
					+ "VALUES ('"+ clave + "','"+ idPuesto +"','"+ idDependencia +"','"+idDeduccion +"','"+valor +"',convert(datetime,'"+ formatter.format(diaHoy)+"',101),'"+ FormularioPrincipal.lblIdTipoNomina.getText() +"',"
							+ ""+null+","+null+")";
			System.out.println("insert reporte deduccion imss: " + sqlInsert1);
			try {
				con1 = nych.datasource.getConnection();
				pps1 = con1.prepareStatement(sqlInsert1);
				pps1.executeUpdate();
//				JOptionPane.showMessageDialog(null, "Datos Guardados");
				System.out.println("Datos Guardados en insertarReporteDeduccionIMSS");
			} catch (SQLException el) {
				el.printStackTrace();
				StringWriter errors = new StringWriter();
				el.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: "+ errors );
				JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
			}finally {
				try {
					con1.close();
				} catch (SQLException ep) {
					ep.printStackTrace();
					StringWriter errors = new StringWriter();
					ep.printStackTrace(new PrintWriter(errors));
					LOG.info("Excepcion: "+ errors );
					JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
				}
			}
		}catch(Exception insertarEmpleadoReporteNomina ) {
			insertarEmpleadoReporteNomina.printStackTrace();
			StringWriter errors = new StringWriter();
			insertarEmpleadoReporteNomina.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: "+ errors );
		}
		return resultado;
	}
	
	public void limpiarFormularioNuevoEmpleado() {
		textFieldNvoNombre.setText(null);
		textFieldNvoApPat.setText(null);
		textFieldNvoApMat.setText(null);
		textFieldNvoCurp.setText(null);
		textFieldNvoRFC.setText(null);
		textFieldNvoTel.setText(null);
		textFieldNvoCorreo.setText(null);
		textFieldClave.setText(null);
		textFieldDireccion.setText(null);
		textFieldNvoCiudad.setText(null);
		textFieldNvoCodPost.setText(null);
		textFieldNvoEstado.setText(null);
		textFieldIdDepen.setText("");
		textFieldNoPuesto.setText("");
		textFieldAsignarDepen.setText("");
		textFieldAsignarPuesto.setText("");
		textFieldNvoContacto.setText(null);
		textFieldContactoEmail.setText(null);
		textFieldContactoCelular.setText(null);
		textFieldContactoDireccion.setText(null);
		textFieldCtaBancaria.setText(null);
		textFieldMonedero.setText(null);
		textFieldNvoRegImss.setText(null);
		textFieldSDINuevo.setText(null);
		textFieldNCI.setText(null);
		textFieldFI.setText(null);
		textFieldUMA.setText(null);
		comboBoxGenero.removeAllItems();
		comboBoxEdoCivil.removeAllItems();
		comboBoxLocalidad.removeAllItems();
		comboBoxClasificacionDispersion.removeAllItems();
		comboBoxRegimen.removeAllItems();
		comboBoxTipoEmpleado.removeAllItems();
		comboBoxTipoJornada.removeAllItems();
		comboBoxRiesgo.removeAllItems();
		comboBoxTipoContrato.removeAllItems();
		comboBoxCalsificacionEmpleado.removeAllItems();
		comboBoxBanco.removeAllItems();
		dateChooserNvoFechaNac.setDate(null);
		dateChooserFechaAltaIngreso.setDate(null);
		dateChooserFechaBajaIngreso.setDate(null);
		dateChooserNvoFechaAltaImss.setDate(null);
		dateChooserFechaBajaImss.setDate(null);
		


	}

}

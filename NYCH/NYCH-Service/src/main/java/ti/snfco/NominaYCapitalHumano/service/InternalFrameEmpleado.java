package ti.snfco.NominaYCapitalHumano.service;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Window;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

import org.slf4j.LoggerFactory;

import com.toedter.calendar.JDateChooser;

public class InternalFrameEmpleado extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(InternalFrameEmpleado.class);
	static InternalFrameNuevoEmpleado ifne = new InternalFrameNuevoEmpleado();
	static InternalFrameActualizar internalFrameActualizar = new InternalFrameActualizar();
	static InternalFramePeriodo internalFramePeriodo = new InternalFramePeriodo();

	public JButton btnNuevo = new JButton("Nuevo Empleado");
	public JButton btnActualizar = new JButton("Actualizar");
	public JButton btnBorrar = new JButton("Dar de Baja");
	final JButton btnCargarFoto = new JButton("Ver Foto");
	public JTable tableDatosEmpleados = new JTable();
	public JTable tableDatosEmpleadosFalsa = new JTable();
	public JTable tableDatosEmpleadosInactivos = new JTable();
	public JTable tableDatosEmpleadosExp = new JTable();
	public JTextField textFieldBuscarEmpExpediente;
	public JTextField textFieldBuscar;
	public JTextField textFieldApPatExpediente = new JTextField();
	public JTextField textFieldApMatExpdiente = new JTextField();
	public JTextField textFieldNombreExpediente;
	public ButtonGroup groupButton = new ButtonGroup();
	public JLabel lblApellidoPaterno = new JLabel("Apellido Paterno: ");
	public JLabel lblApellidoMaterno = new JLabel("Apellido Materno: ");
	public JLabel lblEmpleadoExpediente = new JLabel("Empleado(a):");
	public JLabel lblfoto = new JLabel("");
	final JLabel lblRutaFoto = new JLabel("");
	public FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo de imagen", "jpg");
	public String atributo = "id";
	public String rutaImagen;
	private Dimension dim;
	private final JSeparator separator = new JSeparator();
	private final JSeparator separator_1 = new JSeparator();
	private final JLabel lblicon = new JLabel();
	private JTextField textFieldEmpInac;
	private final JSeparator separator_2 = new JSeparator();
	private final JButton btnConsultarInac = new JButton("Consultar");
	private final JButton btnActivar = new JButton("Activar");
	@SuppressWarnings("rawtypes")
	TableRowSorter rowSortere;
	int IdBusquedaEmplee = 1;

	@SuppressWarnings("rawtypes")
	TableRowSorter rowSortereBuscarEmple;
	int columnaNombreRowSorter = 1;

	private final JLabel lblFondo = new JLabel("");
	private final JLabel lblFondoExpediente = new JLabel("");
	private final JLabel lblFondoInactivo = new JLabel("");
	private JTextField textFieldBuscarEmInac;
	JDateChooser dateChooserFechaDe = new JDateChooser();
	JDateChooser dateChooserFechaHasta = new JDateChooser();
	private JTextField textFieldCurp;
	private JTextField textFieldRFC;
	private JTextField textFieldClave;
	private JTextField textFieldDepen;
	private JTextField textFieldPuesto;
	private JTextField textFieldSalario;
	private JTextField textFieldClasif;
	private JTextField textFieldFechaIngreso;
	private JTextField textFieldFechaBaja;
	private JTextField textFieldRegImss;
	private JTextField textFieldSDI;
	private JTextField textFieldFechaAltaImss;
	private JTextField textFieldEdoCivil;
	private JTextField textFieldRegimen;
	private JTextField textFieldTipoEmpleado;
	private JTextField textFieldJornada;
	private JTextField textFieldRiesgo;
	private JTextField textFieldContrato;
	private JTextField textFieldBanco;
	private JTextField textFieldCtaBanco;
	private JTextField textFieldCtaMonedero;
	private JTextField textFieldCP;
	private JTextField textFieldEstado;
	private JTextField textFieldTel;
	private JTextField textFieldLocalidad;
	private JTextField textFieldCiudad;
	private JTextField textFieldDireccion;
	private JTextField textFieldGenero;
	private JTextField textFieldEmail;
	private JTextField textFieldContactoFamiliar;
	private JTextField textFieldDireccionContacto;
	private JTextField textFieldCelularContacto;
	private JTextField textFieldEmailContacto;
	private JTextField textFieldFechaNAC;
	JPanel panelPestañaExpediente = new JPanel();
	JPanel panelInactivo = new JPanel();
	JLabel lblTipoNominaOculta = new JLabel("Tipo de Nomina Id");
	JLabel lblTipoNominaOcultaNombre = new JLabel("Nomina");
	private JTextField textFieldBuscarEmpleado;
	private JTextField textFieldBuscarEmple;



	public InternalFrameEmpleado() {
		setToolTipText("Empleado Inactivo");
		//setBounds(100, 100, 1971, 1051);
		setVisible(true);
		setTitle("Empleado");
		getContentPane().setLayout(null);

//		Toolkit tk = Toolkit.getDefaultToolkit();
//		Dimension dim = tk.getScreenSize();
//		int ancho = (int) dim.getWidth()-50;
//		int alto = (int) dim.getHeight()-220;
//		setBounds(FormularioPrincipal.desktopPane.getX(), FormularioPrincipal.desktopPane.getY(), ancho, alto);
		setBounds(0, 0, 1500, 701);

		JPanel panelPestañas = new JPanel();
		panelPestañas.setToolTipText("Empleado");
		panelPestañas.setBounds(0, 0, 1555, 751);
		getContentPane().add(panelPestañas);
		panelPestañas.setLayout(null);

		JTabbedPane tabbedPanePestañas = new JTabbedPane(JTabbedPane.TOP);
		tabbedPanePestañas.setBounds(0, 0,1555, 751);
		panelPestañas.add(tabbedPanePestañas);


		JPanel panelPestañaEmpleado = new JPanel();
		panelPestañaEmpleado.setBackground(SystemColor.controlHighlight);
		// panelPestañaEmpleado.setBackground(new Color(240, 248, 255));
		// panelPestaña1.setBackground(Color.WHITE);
		panelPestañaEmpleado.setToolTipText("Empleado");
		panelPestañaEmpleado.setForeground(new Color(255, 127, 80));
		panelPestañaEmpleado.setName("Empleado");
		panelPestañaEmpleado.setBounds(FormularioPrincipal.desktopPane.getX(), FormularioPrincipal.desktopPane.getY(), 1555, 751);

		tabbedPanePestañas.addTab("Empleado", null, panelPestañaEmpleado, null);
		tabbedPanePestañas.setForegroundAt(0, Color.BLACK);

		btnNuevo.setBounds(20, 22, 173, 40);
		btnNuevo.setToolTipText("Nuevo Empleado");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				windowOpenedNuevoEmpleado(e);
				/*
				 * setear campos*
				 */

				if(FormularioPrincipal.frameInternoPeriodoMsj(ifne)) {
					FormularioPrincipal.desktopPane.add(ifne);
					ifne.textFieldClave.setVisible(false);
					ifne.lblClaveDelEmpleado.setVisible(false);
//					ifne.panelDependencias.setVisible(false);
//					ifne.panelPuestos.setVisible(false);
					ifne.textFieldTipoNominaOculta.setText(lblTipoNominaOcultaNombre.getText());
					ifne.lblIdTipoNominaOculta.setText(lblTipoNominaOculta.getText());
					ifne.show();
					ifne.setFrameIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("user_add.png"))));
					ifne.setIconifiable(true);
					ifne.setClosable(true);
					ifne.setResizable(true);
					ifne.setMaximizable(true);
					ifne.setVisible(true);
					ifne.toFront();
				}else
				{
					JOptionPane.showMessageDialog(null,"LA VENTANA NUEVO EMPLEADO YA ESTA ABIERTA");
				}

			}
		});

		btnNuevo.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("user_add.png"))));
		panelPestañaEmpleado.add(btnNuevo);


		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				windowOpenedActualizar(e);
				int fila = tableDatosEmpleados.getSelectedRow();
				System.out.println("fila: " + fila);
//				int filaFalsa =  tableDatosEmpleadosFalsa.getSelectedRow();
//				System.out.println("filaFalsa: " + filaFalsa);
				if (fila >= 0 ) {
					internalFrameActualizar.textFieldIdEmpleado.setText(tableDatosEmpleados.getValueAt(fila, 0).toString());//real - clave
					internalFrameActualizar.textFieldActualizarNombre.setText(tableDatosEmpleados.getValueAt(fila, 1).toString());//real - nombre
					internalFrameActualizar.textFieldActualizarPat.setText(tableDatosEmpleados.getValueAt(fila, 2).toString());//real - paterno
					internalFrameActualizar.textFieldActualizarMat.setText(tableDatosEmpleados.getValueAt(fila, 3).toString());//real - materno
					try {
						Date fechaNac =new SimpleDateFormat("yyyy-MM-dd").parse(tableDatosEmpleadosFalsa.getValueAt(fila, 4).toString());//falsa - fecha de nacimiento
//						System.out.println("fechaNac: "+fechaNac);
						internalFrameActualizar.dateChooserFechaNacimiento.setDate(fechaNac);
//						System.out.println("fechaNac: "+fechaNac);

					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					internalFrameActualizar.textFieldActualizarCurp.setText(tableDatosEmpleados.getValueAt(fila, 4).toString());// real - curp
					internalFrameActualizar.textFieldActualizarRfc.setText(tableDatosEmpleados.getValueAt(fila, 5).toString());//real - rfc
					internalFrameActualizar.textFieldActualizarTel.setText(tableDatosEmpleadosFalsa.getValueAt(fila, 8).toString());//falsa - telefono
					internalFrameActualizar.comboBoxActualizarGenero.setSelectedItem(tableDatosEmpleadosFalsa.getValueAt(fila, 41).toString());//falsa - genero
					internalFrameActualizar.comboBoxActualizarEdoCivil.setSelectedItem(tableDatosEmpleadosFalsa.getValueAt(fila, 22).toString());//falsa  - estado civil
					internalFrameActualizar.textFieldActualizarEmail.setText(tableDatosEmpleadosFalsa.getValueAt(fila, 9).toString());//falsa - email
					internalFrameActualizar.textFieldActualizarDireccion.setText(tableDatosEmpleadosFalsa.getValueAt(fila, 10).toString());//falsa - direccion
					internalFrameActualizar.textFieldActualizarCiudad.setText(tableDatosEmpleadosFalsa.getValueAt(fila, 11).toString());//falsa - ciudad
					internalFrameActualizar.textFieldActualizarEstado.setText(tableDatosEmpleadosFalsa.getValueAt(fila, 12).toString());//falsa - estado
					internalFrameActualizar.textFieldActualizarCodigoPostal.setText(tableDatosEmpleadosFalsa.getValueAt(fila, 13).toString());//falsa - codigo postal
					internalFrameActualizar.comboBoxActTipoNomina.setSelectedItem(tableDatosEmpleadosFalsa.getValueAt(fila, 29).toString());//falsa - tipo nomina
					try {
						Date fechaAltaIngreso =new SimpleDateFormat("yyyy-MM-dd").parse(tableDatosEmpleadosFalsa.getValueAt(fila, 30).toString());//falsa - fecha alta ingreso
						//System.out.println("fechaAltaIngreso:  "+fechaAltaIngreso);
						internalFrameActualizar.dateChooserFechaAltaIngreso.setDate(fechaAltaIngreso);
						//System.out.println("fechaAltaIngreso:  "+fechaAltaIngreso);

					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					try {
						Date fechaBajaIngreso =new SimpleDateFormat("yyyy-MM-dd").parse(tableDatosEmpleadosFalsa.getValueAt(fila, 31).toString());//falsa - fecha baja ingreso
						//System.out.println("fechaBajaIngreso:  "+fechaBajaIngreso);
						internalFrameActualizar.dateChooserFechaBajaIngreso.setDate(fechaBajaIngreso);
						//System.out.println("fechaBajaIngreso:  "+fechaBajaIngreso);

					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					internalFrameActualizar.comboBoxActualizarDependencia.setSelectedItem(tableDatosEmpleados.getValueAt(fila,9).toString());//real - dependencia
					internalFrameActualizar.comboBoxPuesto.setSelectedItem(tableDatosEmpleados.getValueAt(fila, 7).toString());//real - puesto
					internalFrameActualizar.comboBoxClasificacion.setSelectedItem(tableDatosEmpleadosFalsa.getValueAt(fila, 34).toString());//falsa - clasificacion dispersion
					internalFrameActualizar.comboBoxRegimen.setSelectedItem(tableDatosEmpleadosFalsa.getValueAt(fila, 35).toString());//falsa - regimen 
					internalFrameActualizar.comboBoxTipoEmpleado.setSelectedItem(tableDatosEmpleadosFalsa.getValueAt(fila, 36).toString());//falsa - tipo empleado
					internalFrameActualizar.comboBoxTipoJornada.setSelectedItem(tableDatosEmpleadosFalsa.getValueAt(fila, 37).toString());//falsa - tipo jornada
					internalFrameActualizar.comboBoxRiesgo.setSelectedItem(tableDatosEmpleadosFalsa.getValueAt(fila, 38).toString());//falsa - riesgo trabajo
					internalFrameActualizar.comboBoxTipoContrato.setSelectedItem(tableDatosEmpleadosFalsa.getValueAt(fila, 39).toString());//falsa - tipo contrato
					internalFrameActualizar.comboBoxClasificacionEmpleado.setSelectedItem(tableDatosEmpleadosFalsa.getValueAt(fila, 40).toString());//falsa - clasificacion empleado
					internalFrameActualizar.textFieldActualizarContacto.setText(tableDatosEmpleadosFalsa.getValueAt(fila, 14).toString());//falsa - contacto familiar
					internalFrameActualizar.textFieldActualizarEmailContacto.setText(tableDatosEmpleadosFalsa.getValueAt(fila, 15).toString());//falsa - email contacto
					internalFrameActualizar.textFieldActualizarCelular.setText(tableDatosEmpleadosFalsa.getValueAt(fila, 16).toString());//fake - celular contacto
					internalFrameActualizar.textFieldActualizarDireccionContacto.setText(tableDatosEmpleadosFalsa.getValueAt(fila, 17).toString());// falsa - direccion contacto
					internalFrameActualizar.textFieldCtaBancaria.setText(tableDatosEmpleadosFalsa.getValueAt(fila, 26).toString());//falsa - cuenta bancaria
					internalFrameActualizar.textFieldMonedero.setText(tableDatosEmpleadosFalsa.getValueAt(fila, 27).toString());// falsa - monedero
					internalFrameActualizar.textFieldSDIActualizar.setText(tableDatosEmpleadosFalsa.getValueAt(fila, 18).toString());//falsa - salario diario integrado
					internalFrameActualizar.textFieldUMAActualizr.setText(tableDatosEmpleadosFalsa.getValueAt(fila, 21).toString());//falsa - UMA
					internalFrameActualizar.comboBoxBanco.setSelectedItem(tableDatosEmpleadosFalsa.getValueAt(fila, 28).toString());//falsa - banco
					internalFrameActualizar.textFieldActualizarRegistroImss.setText(tableDatosEmpleados.getValueAt(fila, 6).toString());//real registro imss

					try {
						Date fechaAltaImss =new SimpleDateFormat("yyyy-MM-dd").parse(tableDatosEmpleadosFalsa.getValueAt(fila, 24).toString());//falsa - fecha alta imss
						internalFrameActualizar.dateChooserFechaAltaImss.setDate(fechaAltaImss);

					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					try {
						Date fechaBajaImss =new SimpleDateFormat("yyyy-MM-dd").parse(tableDatosEmpleadosFalsa.getValueAt(fila, 25).toString());// falsa - fecha baja imss
						internalFrameActualizar.dateChooserFechaBajaImss.setDate(fechaBajaImss);

					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					if (tableDatosEmpleadosFalsa.getValueAt(fila, 19) == null) {

						internalFrameActualizar.textFieldNCIActualizar.setText(null);

					} else {

						internalFrameActualizar.textFieldNCIActualizar
						.setText(tableDatosEmpleadosFalsa.getValueAt(fila, 19).toString());//falsa - numero credito infonavit

					}
					//
					if (tableDatosEmpleadosFalsa.getValueAt(fila, 20) == null) {

						internalFrameActualizar.textFieldFIActualizar.setText(null);

					} else {

						internalFrameActualizar.textFieldFIActualizar
						.setText(tableDatosEmpleadosFalsa.getValueAt(fila, 20).toString());//falsa - factor infonavit

					}

				} else {
					JOptionPane.showMessageDialog(null, "Registro no Seleccionado para Actualizar");
				}

				FormularioPrincipal.desktopPane.add(internalFrameActualizar);
				internalFrameActualizar.show();
//				internalFrameActualizar.setBounds(100, 70, 1600, 700);
				internalFrameActualizar.setFrameIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("edit.png"))));
				internalFrameActualizar.setIconifiable(true);
				internalFrameActualizar.setClosable(true);
				internalFrameActualizar.setResizable(true);
				internalFrameActualizar.setMaximizable(true);
				internalFrameActualizar.setVisible(true);
				internalFrameActualizar.toFront();

			}
		});

		btnActualizar.setBounds(203, 22, 116, 40);
		btnActualizar.setToolTipText("Actualizar");

		btnActualizar.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("edit.png"))));
		panelPestañaEmpleado.add(btnActualizar);


		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarRegistro();
				busquedaEmpleadoReal(lblTipoNominaOculta.getText());
			}
		});
		btnBorrar.setBounds(329, 22, 138, 40);
		btnBorrar.setToolTipText("");
		btnBorrar.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("delete.png"))));
		panelPestañaEmpleado.add(btnBorrar);
		panelPestañaEmpleado.setLayout(null);

		//		btnBuscar.addMouseListener(new MouseAdapter() {
		//			public void mouseClicked(MouseEvent e) {
		////				groupButton.add(rdbtnNombre);
		////				groupButton.add(rdbtnApPat);
		////				groupButton.add(rdbtnRegistroImss);
		////				if (rdbtnNombre.isSelected()) {
		////					atributo = "NOMBRE";
		////					busquedaPorCampos(textFieldBuscar.getText());
		////				} else if (rdbtnApPat.isSelected()) {
		////					atributo = "APELLIDO_PATERNO";
		////					busquedaPorCampos(textFieldBuscar.getText());
		////				} else if (rdbtnRegistroImss.isSelected()) {
		////					atributo = "REGISTRO_IMSS";
		////					busquedaPorCampos(textFieldBuscar.getText());
		////				}
		//				
		////				else 
		////					if(comboBoxTipoNominaEmpleados.getSelectedIndex()>=0){
		////					atributo = "ID_EJERCICIOS";
		////					busquedaPorCamposCombo(comboBoxTipoNominaEmpleados.getSelectedIndex());
		//					mostrarDatosEmpleadoFalso(lblTipoNominaOculta.getText());
		////				}
		////
		////				else {
		////					JOptionPane.showMessageDialog(null, "No se ha seleccionado ningun tipo de busqueda");
		////				}
		//			}
		//		});
		//		//btnBuscar.setBounds(1170, 87, 119, 32);
		//		double porcentajeAncho = 10/100.f;
		//		double porcentajeAlto = 84/100.f;
		////		System.out.println("porcentaje ancho: "+porcentajeAncho+" %");
		////		System.out.println("porcentaje alto: "+porcentajeAlto +" %");
		//		double porcAncho = porcentajeAncho * ancho;
		//		double porcAlto = porcentajeAlto *alto;
		////		System.out.println("px ancho: "+porcAncho+" px");
		////		System.out.println("px alto: "+porcAlto+" px");
		//		btnBuscar.setBounds(1676,99, 119, 32);//146 //461 //x=1170,y=87,width=119,height=32
		////		System.out.println("boton: " + btnBuscar.getBounds());
		//		btnBuscar.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("icons8_Search_32px_2.png"))));
		//		btnBuscar.setForeground(new Color(0, 0, 0));
		//		panelPestañaEmpleado.add(btnBuscar);
		//		panelPestañaEmpleado.add(btnNuevo);
		//		panelPestañaEmpleado.add(btnActualizar);
		//		panelPestañaEmpleado.add(btnBorrar);

		JScrollPane scrollPaneDatosEmpleados = new JScrollPane();
		scrollPaneDatosEmpleados.setViewportBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		scrollPaneDatosEmpleados.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneDatosEmpleados.setBounds(20, 142, 1453, 464);
		scrollPaneDatosEmpleados.setViewportView(tableDatosEmpleados);
		scrollPaneDatosEmpleados.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		panelPestañaEmpleado.add(scrollPaneDatosEmpleados);

		//		groupButton.add(rdbtnNombre);
		//		rdbtnNombre.setBackground(SystemColor.controlHighlight);

		//		rdbtnNombre.setBounds(1340, 62, 85, 23);
		//		panelPestañaEmpleado.add(rdbtnNombre);
		//		groupButton.add(rdbtnApPat);
		//		rdbtnApPat.setBackground(SystemColor.controlHighlight);

		//		rdbtnApPat.setBounds(1441, 62, 135, 23);
		//		panelPestañaEmpleado.add(rdbtnApPat);
		//		groupButton.add(rdbtnRegistroImss);
		//		rdbtnRegistroImss.setBackground(SystemColor.controlHighlight);

		//		rdbtnRegistroImss.setBounds(1573, 62, 109, 23);
		//		panelPestañaEmpleado.add(rdbtnRegistroImss);

		//		textFieldBuscar = new JTextField();
		//		textFieldBuscar.setBorder(null);
		//		textFieldBuscar.setBackground(SystemColor.controlHighlight);
		//		textFieldBuscar.setBounds(1340, 87, 326, 30);
		//		textFieldBuscar.setColumns(10);
		//		panelPestañaEmpleado.add(textFieldBuscar);

		//		separator.setBounds(1340, 117, 326, 2);
		//		panelPestañaEmpleado.add(separator);

		// rdbtnNomCator.setBackground(new Color(147,140,147));
		// rdbtnNomCator.setBounds(1340, 19, 142, 23);
		// panelPestañaEmpleado.add(rdbtnNomCator);

		// rdbtnNomSemanal.setBounds(1498, 19, 157, 23);
		// rdbtnNomSemanal.setBackground(new Color(147,140,147));
		// panelPestañaEmpleado.add(rdbtnNomSemanal);
		//
		// rdbtnNomJubi.setBounds(1657, 19, 150, 23);
		// rdbtnNomJubi.setBackground(new Color(147,140,147));
		// panelPestañaEmpleado.add(rdbtnNomJubi);

		// JLabel lblNewLabel = new JLabel("Tipo Nomina:");
		// lblNewLabel.setBounds(1340, 9, 158, 14);
		// panelPestañaEmpleado.add(lblNewLabel);

		//		comboBoxTipoNominaEmpleados.setBounds(1340, 32, 466, 25);
		//		panelPestañaEmpleado.add(comboBoxTipoNominaEmpleados);

		lblTipoNominaOculta.setBounds(606, 35, 157, 14);
		lblTipoNominaOculta.setVisible(false);
		panelPestañaEmpleado.add(lblTipoNominaOculta);


		lblTipoNominaOcultaNombre.setBounds(606, 7, 531, 14);
		lblTipoNominaOcultaNombre.setVisible(false);
		panelPestañaEmpleado.add(lblTipoNominaOcultaNombre);

		JButton btnVerEmplee = new JButton("Ver Empleados");
		btnVerEmplee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				windowOpened(e);
				busquedaEmpleadoReal(lblTipoNominaOculta.getText());
				mostrarDatosEmpleadoFalso(lblTipoNominaOculta.getText());
			}
		});
		btnVerEmplee.setBounds(1343, 108, 129, 23);
		panelPestañaEmpleado.add(btnVerEmplee);

		JSeparator separator_3 = new JSeparator();
		separator_3.setForeground(new Color(176, 196, 222));
		separator_3.setBackground(new Color(176, 196, 222));
		separator_3.setBounds(973, 129, 360, 2);
		panelPestañaEmpleado.add(separator_3);

		textFieldBuscarEmple = new JTextField();
		textFieldBuscarEmple.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("unchecked")
			public void keyReleased(KeyEvent e) {
				rowSortereBuscarEmple.setRowFilter(RowFilter.regexFilter(textFieldBuscarEmple.getText().toUpperCase(), columnaNombreRowSorter));	
			}
		});
		textFieldBuscarEmple.setColumns(10);
		textFieldBuscarEmple.setBorder(null);
		textFieldBuscarEmple.setBackground(SystemColor.controlHighlight);
		textFieldBuscarEmple.setBounds(1021, 100, 312, 28);
		panelPestañaEmpleado.add(textFieldBuscarEmple);

		JLabel lblIconBuscar = new JLabel();
		lblIconBuscar.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("icons8_Search_32px_2.png"))));
		lblIconBuscar.setBounds(973, 100, 46, 28);
		panelPestañaEmpleado.add(lblIconBuscar);
		
		JLabel lblNewLabel = new JLabel("Buscar Empleado:");
		lblNewLabel.setBounds(973, 68, 164, 14);
		panelPestañaEmpleado.add(lblNewLabel);
		
				//		JLabel lblIconBuscar = new JLabel();
				//		lblIconBuscar.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("icons8_Search_32px_2.png"))));
				//		lblIconBuscar.setBounds(20, 102, 46, 28);
				//		panelPestañaEmpleado.add(lblIconBuscar);
		
				//		textFieldBuscarEmpleado = new JTextField();
				//		textFieldBuscarEmpleado.addKeyListener(new KeyAdapter() {
				//			public void keyReleased(KeyEvent arg0) {
				//				rowSortere.setRowFilter(RowFilter.regexFilter(textFieldBuscarEmpleado.getText().toUpperCase(), IdBusquedaEmplee));
				//			}
				//		});
				//		textFieldBuscarEmpleado.setColumns(10);
				//		textFieldBuscarEmpleado.setBorder(null);
				//		textFieldBuscarEmpleado.setBackground(SystemColor.controlHighlight);
				//		textFieldBuscarEmpleado.setBounds(68, 102, 292, 28);
				//		panelPestañaEmpleado.add(textFieldBuscarEmpleado);
		
				//		JSeparator separatorBuscar = new JSeparator();
				//		separatorBuscar.setForeground(new Color(176, 196, 222));
				//		separatorBuscar.setBackground(new Color(176, 196, 222));
				//		separatorBuscar.setBounds(20, 131, 360, 2);
				//		panelPestañaEmpleado.add(separatorBuscar);
		
				lblFondo.setBounds(0, 0, 1555, 751);
				lblFondo.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("walll.png"))));
				panelPestañaEmpleado.add(lblFondo);

		panelPestañaExpediente.setToolTipText("Empleado");
		panelPestañaExpediente.setBackground(SystemColor.controlHighlight);
		tabbedPanePestañas.addTab("Expediente", null, panelPestañaExpediente, null);
		tabbedPanePestañas.setForegroundAt(1, Color.BLACK);
		panelPestañaExpediente.setLayout(null);
		//		btnBuscarEmpleado.addActionListener(new ActionListener() {
		//			public void actionPerformed(ActionEvent e) {
		//				mostrasRegistroExpediente();
		//			}
		//		});
		//
		//		btnBuscarEmpleado.setBounds(163, 672, 142, 40);
		//		//		btnBuscarEmpleado.setIcon(new ImageIcon(
		//		//				Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("icons8_Search_32px_2.png"))));
		//		panelPestañaExpediente.add(btnBuscarEmpleado);

		textFieldBuscarEmpExpediente = new JTextField();
		textFieldBuscarEmpExpediente.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("unchecked")
			@Override
			public void keyReleased(KeyEvent e) {
				rowSortere.setRowFilter(RowFilter.regexFilter(textFieldBuscarEmpExpediente.getText().toUpperCase(), IdBusquedaEmplee));
				//				rowSortereFalso.setRowFilter(
				//						RowFilter.regexFilter(textFieldBuscarEmpExpediente.getText().toUpperCase(), IdBusquedaEmpleeFalso));
			}
		});
		textFieldBuscarEmpExpediente.setBorder(null);
		textFieldBuscarEmpExpediente.setBackground(SystemColor.controlHighlight);
		textFieldBuscarEmpExpediente.setBounds(141, 11, 374, 28);
		panelPestañaExpediente.add(textFieldBuscarEmpExpediente);
		textFieldBuscarEmpExpediente.setColumns(10);

		JPanel panel_Foto = new JPanel();
		panel_Foto.setBorder(new LineBorder(Color.BLUE));
		panel_Foto.setBounds(10, 83, 180, 194);
		panelPestañaExpediente.add(panel_Foto);
		panel_Foto.setLayout(null);

		lblfoto.setBounds(10, 11, 160, 170);
		lblfoto.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("businessman(2).png"))));
		panel_Foto.add(lblfoto);

		textFieldNombreExpediente = new JTextField();
		textFieldNombreExpediente.setBounds(315, 117, 200, 30);
		panelPestañaExpediente.add(textFieldNombreExpediente);
		textFieldNombreExpediente.setColumns(10);

		JLabel lblNombreExpediente = new JLabel("Nombre: ");
		lblNombreExpediente.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombreExpediente.setBounds(217, 127, 88, 14);
		panelPestañaExpediente.add(lblNombreExpediente);
		lblApellidoPaterno.setHorizontalAlignment(SwingConstants.RIGHT);

		lblApellidoPaterno.setBounds(200, 161, 105, 14);
		panelPestañaExpediente.add(lblApellidoPaterno);
		textFieldApPatExpediente.setColumns(10);
		textFieldApPatExpediente.setBounds(315, 151, 200, 30);

		panelPestañaExpediente.add(textFieldApPatExpediente);
		textFieldApMatExpdiente.setColumns(10);
		textFieldApMatExpdiente.setBounds(315, 185, 200, 30);

		panelPestañaExpediente.add(textFieldApMatExpdiente);
		lblApellidoMaterno.setHorizontalAlignment(SwingConstants.RIGHT);
		lblApellidoMaterno.setBounds(200, 195, 105, 14);

		panelPestañaExpediente.add(lblApellidoMaterno);
		lblEmpleadoExpediente.setBounds(10, 19, 84, 14);

		panelPestañaExpediente.add(lblEmpleadoExpediente);

		btnCargarFoto.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("photos.png"))));
		btnCargarFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// //creamos objeto FileChooser
				// JFileChooser jfc = new JFileChooser();
				// //llamamos el metodo setFilter
				// jfc.setFileFilter(filter);
				// //abrimos la ventana de dialogo para escoger imagenes
				// int opcion = jfc.showOpenDialog(jfc);
				// if(opcion == JFileChooser.APPROVE_OPTION) {
				// //obtenemos el nombre del archivo que hemos seleccionado
				// String fil = jfc.getSelectedFile().getPath();
				// //obtener la direccion donde se guardara la imagen
				// String file = jfc.getSelectedFile().toString();
				// lblfoto.setIcon(new ImageIcon(fil));
				// //modificamos la imagen
				// ImageIcon icon = new ImageIcon(fil);
				// //extraer la imagen del icono
				// Image image = icon.getImage();
				// //cambiando el tamaño de la imagen
				// Image newImg = image.getScaledInstance(155, 175,
				// java.awt.Image.SCALE_SMOOTH);
				// //se genera el IMAGEICON con al nueva imagen
				// ImageIcon newIcono = new ImageIcon(newImg);
				// lblfoto.setIcon(newIcono);
				// lblfoto.setSize(155,175);
				// lblRutaFoto.setText(fil);
				// rutaImagen="";
				// }

				ImageIcon foto = CargarPhoto.getFoto(1);
				if (foto != null) {
					lblfoto.setIcon(foto);
				} else {
					lblfoto.setIcon(new ImageIcon(getClass().getResource("defaultlarge.gif")));
				}
				lblfoto.updateUI();

			}
		});
		btnCargarFoto.setBounds(33, 288, 126, 40);
		panelPestañaExpediente.add(btnCargarFoto);

		lblRutaFoto.setBounds(20, 60, 492, 14);
		panelPestañaExpediente.add(lblRutaFoto);
		separator_1.setBounds(141, 42, 374, 2);

		panelPestañaExpediente.add(separator_1);
		lblicon.setBounds(104, 11, 46, 28);
		lblicon.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("icons8_Search_32px_2.png"))));
		panelPestañaExpediente.add(lblicon);

		textFieldCurp = new JTextField();
		textFieldCurp.setColumns(10);
		textFieldCurp.setBounds(315, 219, 200, 30);
		panelPestañaExpediente.add(textFieldCurp);

		textFieldRFC = new JTextField();
		textFieldRFC.setColumns(10);
		textFieldRFC.setBounds(315, 253, 200, 30);
		panelPestañaExpediente.add(textFieldRFC);

		textFieldClave = new JTextField();
		textFieldClave.setColumns(10);
		textFieldClave.setBounds(315, 83, 200, 30);
		panelPestañaExpediente.add(textFieldClave);

		JLabel lblCurp = new JLabel("Curp: ");
		lblCurp.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCurp.setBounds(200, 229, 105, 14);
		panelPestañaExpediente.add(lblCurp);

		JLabel lblRfc = new JLabel("RFC: ");
		lblRfc.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRfc.setBounds(200, 263, 105, 14);
		panelPestañaExpediente.add(lblRfc);

		JLabel lblClave = new JLabel("Clave: ");
		lblClave.setHorizontalAlignment(SwingConstants.RIGHT);
		lblClave.setBounds(200, 93, 105, 14);
		panelPestañaExpediente.add(lblClave);

		JLabel lblDependencia = new JLabel("Dependencia: ");
		lblDependencia.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDependencia.setBounds(525, 93, 105, 14);
		panelPestañaExpediente.add(lblDependencia);

		textFieldDepen = new JTextField();
		textFieldDepen.setColumns(10);
		textFieldDepen.setBounds(640, 83, 200, 30);
		panelPestañaExpediente.add(textFieldDepen);

		JLabel lblPuesto = new JLabel("Puesto: ");
		lblPuesto.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPuesto.setBounds(542, 127, 88, 14);
		panelPestañaExpediente.add(lblPuesto);

		textFieldPuesto = new JTextField();
		textFieldPuesto.setColumns(10);
		textFieldPuesto.setBounds(640, 117, 200, 30);
		panelPestañaExpediente.add(textFieldPuesto);

		JLabel lblFechaIngreso = new JLabel("Salario: ");
		lblFechaIngreso.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFechaIngreso.setBounds(525, 161, 105, 14);
		panelPestañaExpediente.add(lblFechaIngreso);

		textFieldSalario = new JTextField();
		textFieldSalario.setColumns(10);
		textFieldSalario.setBounds(640, 151, 200, 30);
		panelPestañaExpediente.add(textFieldSalario);

		JLabel lblFecha = new JLabel("Clasificación:");
		lblFecha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFecha.setBounds(525, 195, 105, 14);
		panelPestañaExpediente.add(lblFecha);

		textFieldClasif = new JTextField();
		textFieldClasif.setColumns(10);
		textFieldClasif.setBounds(640, 185, 200, 30);
		panelPestañaExpediente.add(textFieldClasif);

		JLabel lblFechaIngreso_1 = new JLabel("Fecha Ingreso: ");
		lblFechaIngreso_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFechaIngreso_1.setBounds(525, 229, 105, 14);
		panelPestañaExpediente.add(lblFechaIngreso_1);

		textFieldFechaIngreso = new JTextField();
		textFieldFechaIngreso.setColumns(10);
		textFieldFechaIngreso.setBounds(640, 219, 200, 30);
		panelPestañaExpediente.add(textFieldFechaIngreso);

		JLabel lblFechaBaja = new JLabel("Fecha Baja: ");
		lblFechaBaja.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFechaBaja.setBounds(525, 263, 105, 14);
		panelPestañaExpediente.add(lblFechaBaja);

		textFieldFechaBaja = new JTextField();
		textFieldFechaBaja.setColumns(10);
		textFieldFechaBaja.setBounds(640, 253, 200, 30);
		panelPestañaExpediente.add(textFieldFechaBaja);

		JLabel lblRegImss = new JLabel("Reg. Imss: ");
		lblRegImss.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRegImss.setBounds(894, 93, 105, 14);
		panelPestañaExpediente.add(lblRegImss);

		textFieldRegImss = new JTextField();
		textFieldRegImss.setColumns(10);
		textFieldRegImss.setBounds(1009, 83, 150, 30);
		panelPestañaExpediente.add(textFieldRegImss);

		JLabel lblSdi = new JLabel("S.D.I.: ");
		lblSdi.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSdi.setBounds(911, 125, 88, 14);
		panelPestañaExpediente.add(lblSdi);

		textFieldSDI = new JTextField();
		textFieldSDI.setColumns(10);
		textFieldSDI.setBounds(1009, 117, 150, 30);
		panelPestañaExpediente.add(textFieldSDI);

		JLabel lblFechaAltaImss = new JLabel("Fecha Alta Imss: ");
		lblFechaAltaImss.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFechaAltaImss.setBounds(894, 161, 105, 14);
		panelPestañaExpediente.add(lblFechaAltaImss);

		textFieldFechaAltaImss = new JTextField();
		textFieldFechaAltaImss.setColumns(10);
		textFieldFechaAltaImss.setBounds(1009, 151, 150, 30);
		panelPestañaExpediente.add(textFieldFechaAltaImss);

		JLabel label_4 = new JLabel("");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setBounds(894, 193, 105, 14);
		panelPestañaExpediente.add(label_4);

		textFieldEdoCivil = new JTextField();
		textFieldEdoCivil.setColumns(10);
		textFieldEdoCivil.setBounds(1009, 380, 150, 30);
		panelPestañaExpediente.add(textFieldEdoCivil);

		JLabel lblRgimenDeContratacin = new JLabel("Régimen de contratación: ");
		lblRgimenDeContratacin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRgimenDeContratacin.setBounds(850, 227, 149, 14);
		panelPestañaExpediente.add(lblRgimenDeContratacin);

		textFieldRegimen = new JTextField();
		textFieldRegimen.setColumns(10);
		textFieldRegimen.setBounds(1009, 219, 150, 30);
		panelPestañaExpediente.add(textFieldRegimen);

		JLabel lblTipoDeEmpleado = new JLabel("Tipo de Empleado: ");
		lblTipoDeEmpleado.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTipoDeEmpleado.setBounds(882, 263, 117, 14);
		panelPestañaExpediente.add(lblTipoDeEmpleado);

		textFieldTipoEmpleado = new JTextField();
		textFieldTipoEmpleado.setColumns(10);
		textFieldTipoEmpleado.setBounds(1009, 253, 150, 30);
		panelPestañaExpediente.add(textFieldTipoEmpleado);

		JLabel lblJornada = new JLabel("Jornada:");
		lblJornada.setHorizontalAlignment(SwingConstants.RIGHT);
		lblJornada.setBounds(894, 24, 105, 14);
		panelPestañaExpediente.add(lblJornada);

		textFieldJornada = new JTextField();
		textFieldJornada.setColumns(10);
		textFieldJornada.setBounds(1009, 15, 150, 30);
		panelPestañaExpediente.add(textFieldJornada);

		JLabel lblRiesgo = new JLabel("Riesgo: ");
		lblRiesgo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRiesgo.setBounds(911, 60, 88, 14);
		panelPestañaExpediente.add(lblRiesgo);

		textFieldRiesgo = new JTextField();
		textFieldRiesgo.setColumns(10);
		textFieldRiesgo.setBounds(1009, 49, 150, 30);
		panelPestañaExpediente.add(textFieldRiesgo);

		JLabel lblContrato = new JLabel("Contrato: ");
		lblContrato.setHorizontalAlignment(SwingConstants.RIGHT);
		lblContrato.setBounds(894, 191, 105, 14);
		panelPestañaExpediente.add(lblContrato);

		textFieldContrato = new JTextField();
		textFieldContrato.setColumns(10);
		textFieldContrato.setBounds(1009, 185, 150, 30);
		panelPestañaExpediente.add(textFieldContrato);

		JLabel lblBanco = new JLabel("Banco:");
		lblBanco.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBanco.setBounds(1229, 20, 84, 14);
		panelPestañaExpediente.add(lblBanco);

		textFieldBanco = new JTextField();
		textFieldBanco.setColumns(10);
		textFieldBanco.setBounds(1328, 15, 150, 30);
		panelPestañaExpediente.add(textFieldBanco);

		JLabel lblCtaC = new JLabel("Cta Banco: ");
		lblCtaC.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCtaC.setBounds(1229, 49, 84, 14);
		panelPestañaExpediente.add(lblCtaC);

		textFieldCtaBanco = new JTextField();
		textFieldCtaBanco.setColumns(10);
		textFieldCtaBanco.setBounds(1328, 49, 150, 30);
		panelPestañaExpediente.add(textFieldCtaBanco);

		JLabel lblCtaMonedero = new JLabel("Cta Monedero: ");
		lblCtaMonedero.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCtaMonedero.setBounds(1222, 87, 96, 14);
		panelPestañaExpediente.add(lblCtaMonedero);

		textFieldCtaMonedero = new JTextField();
		textFieldCtaMonedero.setColumns(10);
		textFieldCtaMonedero.setBounds(1328, 83, 150, 30);
		panelPestañaExpediente.add(textFieldCtaMonedero);

		textFieldCP = new JTextField();
		textFieldCP.setColumns(10);
		textFieldCP.setBounds(1328, 253, 150, 30);
		panelPestañaExpediente.add(textFieldCP);

		JLabel lblCodigoPostal = new JLabel("Codigo Postal:");
		lblCodigoPostal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCodigoPostal.setBounds(1229, 257, 84, 14);
		panelPestañaExpediente.add(lblCodigoPostal);

		textFieldEstado = new JTextField();
		textFieldEstado.setColumns(10);
		textFieldEstado.setBounds(1328, 219, 150, 30);
		panelPestañaExpediente.add(textFieldEstado);

		JLabel lblGenero = new JLabel("Localidad: ");
		lblGenero.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGenero.setBounds(1236, 189, 77, 14);
		panelPestañaExpediente.add(lblGenero);

		JLabel lblEstadoCivil = new JLabel("Estado Civil: ");
		lblEstadoCivil.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEstadoCivil.setBounds(910, 390, 88, 14);
		panelPestañaExpediente.add(lblEstadoCivil);

		textFieldTel = new JTextField();
		textFieldTel.setColumns(10);
		textFieldTel.setBounds(1009, 343, 150, 30);
		panelPestañaExpediente.add(textFieldTel);

		textFieldLocalidad = new JTextField();
		textFieldLocalidad.setColumns(10);
		textFieldLocalidad.setBounds(1328, 185, 150, 30);
		panelPestañaExpediente.add(textFieldLocalidad);

		JLabel lblCiudad = new JLabel("Ciudad: ");
		lblCiudad.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCiudad.setBounds(1236, 157, 77, 14);
		panelPestañaExpediente.add(lblCiudad);

		JLabel lblTelefono = new JLabel("Telefono: ");
		lblTelefono.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTelefono.setBounds(910, 354, 88, 14);
		panelPestañaExpediente.add(lblTelefono);

		textFieldCiudad = new JTextField();
		textFieldCiudad.setColumns(10);
		textFieldCiudad.setBounds(1328, 151, 150, 30);
		panelPestañaExpediente.add(textFieldCiudad);

		textFieldDireccion = new JTextField();
		textFieldDireccion.setColumns(10);
		textFieldDireccion.setBounds(1328, 117, 150, 30);
		panelPestañaExpediente.add(textFieldDireccion);

		JLabel lblDireccin = new JLabel("Dirección:");
		lblDireccin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDireccin.setBounds(1229, 123, 84, 14);
		panelPestañaExpediente.add(lblDireccin);

		JLabel lblEmail = new JLabel("Email: ");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setBounds(911, 466, 88, 14);
		panelPestañaExpediente.add(lblEmail);

		JLabel lblEmailContacto = new JLabel("Genero: ");
		lblEmailContacto.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmailContacto.setBounds(894, 430, 105, 14);
		panelPestañaExpediente.add(lblEmailContacto);

		textFieldGenero = new JTextField();
		textFieldGenero.setColumns(10);
		textFieldGenero.setBounds(1009, 421, 150, 30);
		panelPestañaExpediente.add(textFieldGenero);

		JLabel lblGenero_1 = new JLabel("Contacto Familiar:");
		lblGenero_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGenero_1.setBounds(1213, 347, 105, 14);
		panelPestañaExpediente.add(lblGenero_1);

		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(1009, 462, 150, 30);
		panelPestañaExpediente.add(textFieldEmail);

		JLabel lblDireccinContacto = new JLabel("Dirección Contacto: ");
		lblDireccinContacto.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDireccinContacto.setBounds(1201, 470, 117, 14);
		panelPestañaExpediente.add(lblDireccinContacto);

		textFieldContactoFamiliar = new JTextField();
		textFieldContactoFamiliar.setColumns(10);
		textFieldContactoFamiliar.setBounds(1328, 343, 150, 30);
		panelPestañaExpediente.add(textFieldContactoFamiliar);

		JLabel lblCelularContacto = new JLabel("Celular Contacto: ");
		lblCelularContacto.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCelularContacto.setBounds(1206, 388, 112, 14);
		panelPestañaExpediente.add(lblCelularContacto);

		textFieldDireccionContacto = new JTextField();
		textFieldDireccionContacto.setColumns(10);
		textFieldDireccionContacto.setBounds(1328, 462, 150, 30);
		panelPestañaExpediente.add(textFieldDireccionContacto);

		JLabel lblEmailContacto_1 = new JLabel("Email Contacto:");
		lblEmailContacto_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmailContacto_1.setBounds(1213, 429, 105, 14);
		panelPestañaExpediente.add(lblEmailContacto_1);

		textFieldCelularContacto = new JTextField();
		textFieldCelularContacto.setColumns(10);
		textFieldCelularContacto.setBounds(1328, 380, 150, 30);
		panelPestañaExpediente.add(textFieldCelularContacto);

		JLabel lblEstado = new JLabel("Estado: ");
		lblEstado.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEstado.setBounds(1229, 227, 77, 14);
		panelPestañaExpediente.add(lblEstado);

		textFieldEmailContacto = new JTextField();
		textFieldEmailContacto.setColumns(10);
		textFieldEmailContacto.setBounds(1328, 421, 150, 30);
		panelPestañaExpediente.add(textFieldEmailContacto);

		JLabel lblFechaDeNacimiento = new JLabel("Fecha de Nacimiento: ");
		lblFechaDeNacimiento.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFechaDeNacimiento.setBounds(513, 60, 126, 14);
		panelPestañaExpediente.add(lblFechaDeNacimiento);

		textFieldFechaNAC = new JTextField();
		textFieldFechaNAC.setColumns(10);
		textFieldFechaNAC.setBounds(640, 49, 200, 30);
		panelPestañaExpediente.add(textFieldFechaNAC);

		JScrollPane scrollPaneEmpExp = new JScrollPane();
		scrollPaneEmpExp.setViewportBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		scrollPaneEmpExp.setBounds(10, 339, 874, 291);
		scrollPaneEmpExp.setViewportView(tableDatosEmpleadosExp);
		panelPestañaExpediente.add(scrollPaneEmpExp);

		JButton btnVerEmpleados = new JButton("Ver Empleados");
		btnVerEmpleados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getEmpleadoExp(lblTipoNominaOculta.getText());
			}
		});
		btnVerEmpleados.setBounds(524, 21, 186, 23);
		panelPestañaExpediente.add(btnVerEmpleados);

		lblFondoExpediente.setBounds(0, 0, 1855, 807);
		lblFondoExpediente.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("walll.png"))));
		panelPestañaExpediente.add(lblFondoExpediente);

		panelInactivo.setToolTipText("Empleado Inactivo");
		panelInactivo.setBackground(SystemColor.controlHighlight);
		tabbedPanePestañas.addTab("Inactivos", null, panelInactivo, null);
		panelInactivo.setLayout(null);

		JLabel lblEmplInac = new JLabel("Seleccione el Empleado(a):");
		lblEmplInac.setBounds(22, 40, 228, 21);
		panelInactivo.add(lblEmplInac);

		textFieldEmpInac = new JTextField();
		textFieldEmpInac.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				rowSortere.setRowFilter(
						RowFilter.regexFilter(textFieldEmpInac.getText().toUpperCase(), IdBusquedaEmplee));
			}
		});
		textFieldEmpInac.setColumns(10);
		textFieldEmpInac.setBorder(null);
		textFieldEmpInac.setBackground(SystemColor.controlHighlight);
		textFieldEmpInac.setBounds(85, 102, 350, 28);
		panelInactivo.add(textFieldEmpInac);

		JLabel lblIcon = new JLabel();
		lblIcon.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("icons8_Search_32px_2.png"))));
		lblIcon.setBounds(25, 102, 46, 28);
		panelInactivo.add(lblIcon);

		JScrollPane scrollPaneInac = new JScrollPane();
		scrollPaneInac.setViewportBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		scrollPaneInac.setBounds(22, 142, 1450, 399);
		scrollPaneInac.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneInac.setViewportView(tableDatosEmpleadosInactivos);
		scrollPaneInac.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		panelInactivo.add(scrollPaneInac);
		separator_2.setBounds(70, 133, 365, 10);

		panelInactivo.add(separator_2);
		btnConsultarInac.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarDatosEmpleadoInactivos(lblTipoNominaOculta.getText());
			}
		});
		btnConsultarInac.setBounds(462, 101, 89, 30);

		panelInactivo.add(btnConsultarInac);
		btnActivar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = tableDatosEmpleadosInactivos.getSelectedRow();
				if (fila >= 0) {
					String empleado = tableDatosEmpleadosInactivos.getValueAt(fila, 1).toString();
					System.out.println("Empleado: " + empleado);

				} else {
					JOptionPane.showMessageDialog(null, "Registro no seleccionado para activar");

				}
			}
		});
		btnActivar.setBounds(559, 101, 89, 30);

		panelInactivo.add(btnActivar);

		JLabel lblNewLabel_1 = new JLabel("Fecha De:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(668, 101, 89, 30);
		panelInactivo.add(lblNewLabel_1);

		JLabel lblFechaHasta = new JLabel("Fecha Hasta:");
		lblFechaHasta.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFechaHasta.setBounds(990, 101, 89, 30);
		panelInactivo.add(lblFechaHasta);

		JButton btnBuscarFecha = new JButton("Buscar Fecha");
		btnBuscarFecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				getEmpleadoInactivo(dateChooserFechaDe.getDate(), dateChooserFechaHasta.getDate());

			}
		});
		btnBuscarFecha.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("icons8_Search_32px_2.png"))));
		btnBuscarFecha.setBounds(1312, 101, 160, 30);
		panelInactivo.add(btnBuscarFecha);


		dateChooserFechaDe.setBounds(767, 102, 213, 30);
		panelInactivo.add(dateChooserFechaDe);


		dateChooserFechaHasta.setBounds(1089, 100, 213, 30);
		panelInactivo.add(dateChooserFechaHasta);

		lblFondoInactivo.setBounds(0, 0, 1855, 807);
		lblFondoInactivo.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("walll.png"))));
		panelInactivo.add(lblFondoInactivo);

		//		JLabel lblTipoNomina = new JLabel("Tipo de Nomina");
		//		lblTipoNomina.setBounds(1350, 20, 157, 14);
		//		panel.add(lblTipoNomina);
		//
		//		JComboBox comboBoxTipoNomina = new JComboBox();
		//		comboBoxTipoNomina.setBounds(1351, 45, 466, 25);
		//		panel.add(comboBoxTipoNomina);
		//
		//		JRadioButton radioButtonNombre = new JRadioButton("Nombre");
		//		radioButtonNombre.setBackground(SystemColor.controlHighlight);
		//		radioButtonNombre.setBounds(1351, 75, 85, 23);
		//		panel.add(radioButtonNombre);
		//
		//		JRadioButton radioButtonApPat = new JRadioButton("Apellido Paterno");
		//		radioButtonApPat.setBackground(SystemColor.controlHighlight);
		//		radioButtonApPat.setBounds(1452, 75, 135, 23);
		//		panel.add(radioButtonApPat);
		//
		//		JRadioButton radioButtonRegImss = new JRadioButton("Registro Imss");
		//		radioButtonRegImss.setBackground(SystemColor.controlHighlight);
		//		radioButtonRegImss.setBounds(1584, 75, 109, 23);
		//		panel.add(radioButtonRegImss);
		//
		//		JButton btnBuscarInac = new JButton("Buscar");
		//		btnBuscarInac.setForeground(Color.BLACK);
		//		btnBuscarInac.setBounds(1699, 80, 119, 32);
		//		panel.add(btnBuscarInac);
		//
		//		textFieldBuscarEmInac = new JTextField();
		//		textFieldBuscarEmInac.setColumns(10);
		//		textFieldBuscarEmInac.setBorder(null);
		//		textFieldBuscarEmInac.setBackground(SystemColor.controlHighlight);
		//		textFieldBuscarEmInac.setBounds(1351, 100, 326, 30);
		//		panel.add(textFieldBuscarEmInac);
		//
		//		JSeparator separator_3 = new JSeparator();
		//		separator_3.setBounds(1350, 132, 327, 2);
		//		panel.add(separator_3);


		//		ArrayList<String> listaEjercicio = new ArrayList<String>();
		//		listaEjercicio = buscarEjercicio();
		//		comboBoxTipoNominaEmpleados.addItem("Seleccione Una");
		//		for (int i = 0; i < listaEjercicio.size(); i++) {
		//			comboBoxTipoNominaEmpleados.addItem(listaEjercicio.get(i));
		//		}


		//		mostrarDatosEmpleadoInactivos("");
		//		getEmpleadoExp();

		//System.out.println("Cargando...");
	}


	public void getEmpleadoExp(String valor) {
		//System.out.println("getEmpleadoExp");
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
		modelo.addColumn("CLAVE");
		modelo.addColumn("NOMBRE");
		modelo.addColumn("APELLIDO PATERNO");
		modelo.addColumn("APELLIDO MATERNO");
		modelo.addColumn("PUESTO");
		modelo.addColumn("SALARIO");
		modelo.addColumn("DEPENDENCIA");

		tableDatosEmpleadosExp.setModel(modelo);
		tableDatosEmpleadosExp.setBackground(Color.WHITE);

		JTableHeader th = new JTableHeader();
		Color colorSilverLight = new Color(176, 196, 222);
		Color colorNegro = new Color(0, 0, 0);
		th = tableDatosEmpleadosExp.getTableHeader();
		Font fuente = new Font("Amazone", Font.BOLD, 14);
		th.setFont(fuente);
		th.setBackground(colorSilverLight);
		th.setForeground(colorNegro);

		TableColumnModel columnModel = tableDatosEmpleadosExp.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(100);
		columnModel.getColumn(1).setPreferredWidth(200);
		columnModel.getColumn(2).setPreferredWidth(200);
		columnModel.getColumn(3).setPreferredWidth(200);
		columnModel.getColumn(4).setPreferredWidth(200);
		columnModel.getColumn(5).setPreferredWidth(200);
		columnModel.getColumn(6).setPreferredWidth(200);


		//String sqlSelect = "select CLAVE,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO from dbo.empleados WHERE eliminar_logico = '"+1+"'  order by dbo.empleados.id_puesto  ";
		String sqlSelect = "SELECT DBO.EMPLEADOS.CLAVE,DBO.EMPLEADOS.NOMBRE,DBO.EMPLEADOS.APELLIDO_PATERNO,DBO.EMPLEADOS.APELLIDO_MATERNO,\r\n"
				+ "dbo.puestos.nombre_puesto,dbo.puestos.salario,DBO.DEPENDENCIAS.UNIDAD_RESPONSABLE \r\n"
				+ "from empleados\r\n"
				+ "left JOIN DBO.PUESTOS ON DBO.EMPLEADOS.ID_PUESTO = DBO.PUESTOS.no_puesto\r\n"
				+ "LEFT JOIN DBO.DEPENDENCIAS ON DBO.EMPLEADOS.ID_UNIDAD = DBO.DEPENDENCIAS.ID_UNIDAD\r\n"
				+ "WHERE DBO.EMPLEADOS.id_ejercicios = '" + valor + "' and ELIMINAR_LOGICO='1' \r\n";
		//System.out.println("sqlSelect: " + sqlSelect);


		Object datos[] = new String[7];
		PoolNYCH nych = new PoolNYCH();
		Connection con = null;
		Statement st;
		ResultSet resultSet;
		try {
			con = nych.datasource.getConnection();
			st = con.createStatement();
			resultSet = st.executeQuery(sqlSelect);
			while (resultSet.next()) {
				datos[0] = resultSet.getString(1);
				datos[1] = resultSet.getString(2);
				datos[2] = resultSet.getString(3);
				datos[3] = resultSet.getString(4);
				datos[4] = resultSet.getString(5);
				datos[5] = resultSet.getString(6);
				datos[6] = resultSet.getString(7);
				modelo.addRow(datos);
			}

			tableDatosEmpleadosExp.addKeyListener( new KeyAdapter() {
				public void keyReleased( KeyEvent e ) {
					if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
						mostrasRegistroExpediente();
					}
				}
			});

			//				tableDatosEmpleadosExp.addMouseListener(new MouseListener() {
			//					
			//					@Override
			//					public void mouseReleased(MouseEvent e) {
			//						
			//					}
			//					
			//					@Override
			//					public void mousePressed(MouseEvent e) {
			//						
			//					}
			//					
			//					@Override
			//					public void mouseExited(MouseEvent e) {
			//						
			//					}
			//					
			//					@Override
			//					public void mouseEntered(MouseEvent e) {
			//						
			//					}
			//					
			//					@Override
			//					public void mouseClicked(MouseEvent e) {
			//						mostrasRegistroExpediente();
			//						
			//					}
			//				});


			rowSortere = new TableRowSorter(modelo);
			tableDatosEmpleadosExp.setRowSorter(rowSortere);
			tableDatosEmpleadosExp.setModel(modelo);
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
				StringWriter errors = new StringWriter();
				ep.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: " + errors);
			}
		}
	}

	// metodo para los inactivos a partir de una fecha
	public void getEmpleadoInactivo(Date fechaDe, Date FechaHasta) {
		DefaultTableModel modelo = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				// return super.isCellEditable(row, column);
				if (column == 11) {
					return true;
				} else {
					return false;
				}
			}
		};
		modelo.addColumn("CLAVE");
		modelo.addColumn("NOMBRE");
		modelo.addColumn("APELLIDO PATERNO");
		modelo.addColumn("APELLIDO MATERNO");
		modelo.addColumn("CURP");
		modelo.addColumn("RFC");
		modelo.addColumn("REGISTRO IMSS");
		modelo.addColumn("PUESTO");
		modelo.addColumn("SALARIO");
		modelo.addColumn("UNIDAD RESPONSABLE");
		modelo.addColumn("TIPO NOMINA");

		tableDatosEmpleadosInactivos.setModel(modelo);
		tableDatosEmpleadosInactivos.setBackground(Color.WHITE);

		JTableHeader th = new JTableHeader();
		Color colorSilverLight = new Color(176, 196, 222);
		Color colorNegro = new Color(0, 0, 0);
		th = tableDatosEmpleadosInactivos.getTableHeader();
		Font fuente = new Font("Amazone", Font.BOLD, 14);
		th.setFont(fuente);
		th.setBackground(colorSilverLight);
		th.setForeground(colorNegro);

		TableColumnModel columnModel = tableDatosEmpleadosInactivos.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(100);
		columnModel.getColumn(1).setPreferredWidth(200);
		columnModel.getColumn(2).setPreferredWidth(200);
		columnModel.getColumn(3).setPreferredWidth(200);
		columnModel.getColumn(4).setPreferredWidth(300);
		columnModel.getColumn(5).setPreferredWidth(200);
		columnModel.getColumn(6).setPreferredWidth(300);
		columnModel.getColumn(7).setPreferredWidth(200);
		columnModel.getColumn(8).setPreferredWidth(200);
		columnModel.getColumn(9).setPreferredWidth(350);
		columnModel.getColumn(10).setPreferredWidth(700);
		// columnModel.getColumn(11).setPreferredWidth(200);

		String sqlSelect = "";
		String sqlpuesto = "";
		String sqlDependencia = "";
		String sqlEjercicio = "";
		fechaDe = dateChooserFechaDe.getDate();
		FechaHasta = dateChooserFechaHasta.getDate();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		sqlSelect = "select CLAVE,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,CURP,RFC,REGISTRO_IMSS from dbo.empleados WHERE eliminar_logico = '"+0+"' and fecha_baja between "+" convert(datetime,'"+ formatter.format(fechaDe)+"',101)" +" and "+ " convert(datetime,'"+ formatter.format(fechaDe)+"',101)" +" ";
		sqlpuesto = "SELECT dbo.puestos.nombre_puesto,dbo.puestos.salario FROM dbo.EMPLEADOS left JOIN DBO.PUESTOS ON DBO.EMPLEADOS.ID_PUESTO = DBO.PUESTOS.no_puesto ";
		sqlDependencia = "SELECT DBO.DEPENDENCIAS.UNIDAD_RESPONSABLE FROM dbo.EMPLEADOS left JOIN DBO.DEPENDENCIAS ON DBO.EMPLEADOS.ID_UNIDAD = DBO.DEPENDENCIAS.ID_UNIDAD ";
		sqlEjercicio = "SELECT DBO.EJERCICIOS.NOMBRE_EJERCICIO FROM dbo.EMPLEADOS left JOIN DBO.EJERCICIOS ON DBO.EMPLEADOS.ID_EJERCICIOS = DBO.EJERCICIOS.ID_EJERCICIOS ";
		System.out.println("sqlSelect: " + sqlSelect);


		Object datos[] = new String[12];
		PoolNYCH nych = new PoolNYCH();
		Connection con = null;
		Statement st;
		ResultSet resultSet;
		Connection conPuesto = null;
		Connection conDepen = null;
		Connection conEjercicio = null;
		Statement stPuesto;
		Statement stDepen;
		Statement stEjercicio;
		ResultSet resPuesto;
		ResultSet resDependencia;
		ResultSet resEjercicio;
		try {
			con = nych.datasource.getConnection();
			conPuesto = nych.datasource.getConnection();
			conDepen = nych.datasource.getConnection();
			conEjercicio = nych.datasource.getConnection();
			st = con.createStatement();
			stPuesto = conPuesto.createStatement();
			stDepen = conDepen.createStatement();
			stEjercicio = conEjercicio.createStatement();
			resultSet = st.executeQuery(sqlSelect);
			resPuesto = stPuesto.executeQuery(sqlpuesto);
			resDependencia = stDepen.executeQuery(sqlDependencia);
			resEjercicio = stEjercicio.executeQuery(sqlEjercicio);
			while (resultSet.next() && resPuesto.next() && resDependencia.next() && resEjercicio.next()) {
				datos[0] = resultSet.getString(1);
				datos[1] = resultSet.getString(2);
				datos[2] = resultSet.getString(3);
				datos[3] = resultSet.getString(4);
				datos[4] = resultSet.getString(5);
				datos[5] = resultSet.getString(6);
				datos[6] = resultSet.getString(7);
				datos[7] = resPuesto.getString(1);
				datos[8] = resPuesto.getString(2);
				datos[9] = resDependencia.getString(1);
				datos[10] = resEjercicio.getString(1);
				modelo.addRow(datos);
			}
			rowSortere = new TableRowSorter(modelo);
			tableDatosEmpleadosInactivos.setRowSorter(rowSortere);

			tableDatosEmpleadosInactivos.setModel(modelo);
		} catch (SQLException el) {
			el.printStackTrace();
			StringWriter errors = new StringWriter();
			el.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: " + errors);
			// JOptionPane.showMessageDialog(null, el, "Error de conexion",
			// JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");

		} finally {
			try {
				con.close();
			} catch (SQLException ep) {
				StringWriter errors = new StringWriter();
				ep.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: " + errors);
				// JOptionPane.showMessageDialog(null, el, "Error de conexion",
				// JOptionPane.ERROR_M
			}
		}
	}


	public void busquedaPorTipoNomina(String valor) {

		DefaultTableModel modelo = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				// return super.isCellEditable(row, column);
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
		modelo.addColumn("CURP");
		modelo.addColumn("RFC");
		modelo.addColumn("REGISTRO IMSS");
		modelo.addColumn("PUESTO");
		modelo.addColumn("SALARIO");
		modelo.addColumn("UNIDAD RESPONSABLE");
		modelo.addColumn("TIPO NOMINA");

		tableDatosEmpleados.setModel(modelo);
		tableDatosEmpleados.setBackground(Color.WHITE);

		JTableHeader th = new JTableHeader();
		Color colorSilverLight = new Color(176, 196, 222);
		Color colorNegro = new Color(0, 0, 0);
		th = tableDatosEmpleados.getTableHeader();
		Font fuente = new Font("Cooper Black", Font.BOLD, 14);
		th.setFont(fuente);
		th.setBackground(colorSilverLight);
		th.setForeground(colorNegro);


		TableColumnModel columnModel = tableDatosEmpleados.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(50);
		columnModel.getColumn(1).setPreferredWidth(200);
		columnModel.getColumn(2).setPreferredWidth(200);
		columnModel.getColumn(3).setPreferredWidth(200);
		columnModel.getColumn(4).setPreferredWidth(300);
		columnModel.getColumn(5).setPreferredWidth(200);
		columnModel.getColumn(6).setPreferredWidth(300);
		columnModel.getColumn(7).setPreferredWidth(200);
		columnModel.getColumn(8).setPreferredWidth(200);
		columnModel.getColumn(9).setPreferredWidth(350);
		columnModel.getColumn(10).setPreferredWidth(700);

		String sqlSelect = "";
		String sqlpuesto = "";
		String sqlDependencia = "";
		String sqlEjercicio = "";
		if (valor.equals("")) {
			sqlSelect = "select * from dbo.empleados WHERE ELIMINAR_LOGICO='" + 1 + "' order by id_puesto";
		} else {
			sqlSelect = "select * from dbo.empleados WHERE ELIMINAR_LOGICO='" + 1 + "' order by id_puesto";
			sqlpuesto = "SELECT dbo.puestos.nombre_puesto,dbo.puestos.salario FROM dbo.EMPLEADOS left JOIN DBO.PUESTOS ON DBO.EMPLEADOS.ID_PUESTO = DBO.PUESTOS.no_puesto order by dbo.PUESTOS.no_puesto";
			sqlDependencia = "SELECT DBO.DEPENDENCIAS.UNIDAD_RESPONSABLE FROM dbo.EMPLEADOS left JOIN DBO.DEPENDENCIAS ON DBO.EMPLEADOS.ID_UNIDAD = DBO.DEPENDENCIAS.ID_UNIDAD order by dbo.empleados.id_puesto";
			sqlEjercicio = "SELECT DBO.EJERCICIOS.NOMBRE_EJERCICIO FROM dbo.EMPLEADOS left JOIN DBO.EJERCICIOS ON DBO.EMPLEADOS.ID_EJERCICIOS = DBO.EJERCICIOS.ID_EJERCICIOS WHERE "
					+ atributo + " = '" + valor + "' and ELIMINAR_LOGICO='" + 1 + "' order by dbo.empleados.id_puesto";
			System.out.println(sqlSelect);
		}

		Object datos[] = new String[11];
		PoolNYCH nych = new PoolNYCH();
		Connection con = null;
		Statement st;
		ResultSet resultSet;
		Connection conPuesto = null;
		Connection conDepen = null;
		Connection conEjercicio = null;
		Statement stPuesto;
		Statement stDepen;
		Statement stEjercicio;
		ResultSet resPuesto;
		ResultSet resDependencia;
		ResultSet resEjercicio;
		try {
			con = nych.datasource.getConnection();
			conPuesto = nych.datasource.getConnection();
			conDepen = nych.datasource.getConnection();
			conEjercicio = nych.datasource.getConnection();
			st = con.createStatement();
			stPuesto = conPuesto.createStatement();
			stDepen = conDepen.createStatement();
			stEjercicio = conEjercicio.createStatement();
			resultSet = st.executeQuery(sqlSelect);
			resPuesto = stPuesto.executeQuery(sqlpuesto);
			resDependencia = stDepen.executeQuery(sqlDependencia);
			resEjercicio = stEjercicio.executeQuery(sqlEjercicio);
			while (resultSet.next() && resPuesto.next() && resDependencia.next() && resEjercicio.next()) {
				datos[0] = resultSet.getString(1);
				datos[1] = resultSet.getString(2);
				datos[2] = resultSet.getString(3);
				datos[3] = resultSet.getString(4);
				datos[4] = resultSet.getString(6);
				datos[5] = resultSet.getString(8);
				datos[6] = resultSet.getString(9);
				datos[7] = resPuesto.getString(1);
				datos[8] = resPuesto.getString(2);
				datos[9] = resDependencia.getString(1);
				datos[10] = resEjercicio.getString(1);
				modelo.addRow(datos);
			}


			rowSortere = new TableRowSorter(modelo);
			tableDatosEmpleados.setRowSorter(rowSortere);
			tableDatosEmpleados.setModel(modelo);
		} catch (SQLException el) {
			el.printStackTrace();
			// JOptionPane.showMessageDialog(null, el, "Error de conexion",
			// JOptionPane.ERROR_MESSAGE);
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
				// JOptionPane.showMessageDialog(null, ep, "Error de des-conexion",
				// JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");
			}
		}
	}

	//metodo principal de empleados
	public void busquedaEmpleadoReal(String valor) {
//		System.out.println("busquedaPorCampos");
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
		modelo.addColumn("CURP");
		modelo.addColumn("RFC");
		modelo.addColumn("REGISTRO IMSS");
		modelo.addColumn("PUESTO");
		modelo.addColumn("SALARIO");
		modelo.addColumn("UNIDAD RESPONSABLE");
//		modelo.addColumn("TIPO NOMINA");


		tableDatosEmpleados.setModel(modelo);
		tableDatosEmpleados.setBackground(Color.WHITE);

		JTableHeader th = new JTableHeader();
		Color colorSilverLight = new Color(176, 196, 222);
		Color colorNegro = new Color(0, 0, 0);
		th = tableDatosEmpleados.getTableHeader();
		Font fuente = new Font("Arial", Font.BOLD, 14);
		th.setFont(fuente);
		th.setBackground(colorSilverLight);
		th.setForeground(colorNegro);

		TableColumnModel columnModel = tableDatosEmpleados.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(100);
		columnModel.getColumn(1).setPreferredWidth(200);
		columnModel.getColumn(2).setPreferredWidth(200);
		columnModel.getColumn(3).setPreferredWidth(200);
		columnModel.getColumn(4).setPreferredWidth(300);
		columnModel.getColumn(5).setPreferredWidth(200);
		columnModel.getColumn(6).setPreferredWidth(300);
		columnModel.getColumn(7).setPreferredWidth(200);
		columnModel.getColumn(8).setPreferredWidth(200);
		columnModel.getColumn(9).setPreferredWidth(350);
//		columnModel.getColumn(10).setPreferredWidth(700);

		String sqlSelect = "";
		String atributo = "id_ejercicios";
		sqlSelect = "SELECT DBO.EMPLEADOS.CLAVE,DBO.EMPLEADOS.NOMBRE,DBO.EMPLEADOS.APELLIDO_PATERNO,DBO.EMPLEADOS.APELLIDO_MATERNO,DBO.EMPLEADOS.CURP,DBO.EMPLEADOS.RFC,\r\n"
				+" DBO.EMPLEADOS.REGISTRO_IMSS,\r\n"
				+ "dbo.puestos.nombre_puesto,dbo.puestos.salario,DBO.DEPENDENCIAS.UNIDAD_RESPONSABLE \r\n"
				+ "from empleados\r\n"
				+ "inner JOIN DBO.PUESTOS ON DBO.EMPLEADOS.ID_PUESTO = DBO.PUESTOS.no_puesto\r\n"
				+ "inner JOIN DBO.DEPENDENCIAS ON DBO.EMPLEADOS.ID_UNIDAD = DBO.DEPENDENCIAS.ID_UNIDAD\r\n"
				+ "inner JOIN DBO.EJERCICIOS ON DBO.EMPLEADOS.ID_EJERCICIOS = DBO.EJERCICIOS.ID_EJERCICIOS\r\n"
				+ "WHERE DBO.EMPLEADOS." + atributo + " = '" + valor + "' and ELIMINAR_LOGICO='1' \r\n";
		System.out.println("sql empleados real: "+sqlSelect);


		Object datos[] = new String[11];
		PoolNYCH nych = new PoolNYCH();
		Connection con = null;
		Statement st;
		ResultSet resultSet;
		try {
			con = nych.datasource.getConnection();
			st = con.createStatement();
			resultSet = st.executeQuery(sqlSelect);
			while (resultSet.next()) {
				datos[0] = resultSet.getString(1);
				datos[1] = resultSet.getString(2);
				datos[2] = resultSet.getString(3);
				datos[3] = resultSet.getString(4);
				datos[4] = resultSet.getString(5);
				datos[5] = resultSet.getString(6);
				datos[6] = resultSet.getString(7);
				datos[7] = resultSet.getString(8);
				datos[8] = resultSet.getString(9);
				datos[9] = resultSet.getString(10);
//				datos[10] = resultSet.getString(11);
				modelo.addRow(datos);
				
//				System.out.println("real clave: "+datos[0] +" fecha nac: "+datos[4]);
				
			}
			rowSortereBuscarEmple = new TableRowSorter(modelo);
			tableDatosEmpleados.setRowSorter(rowSortereBuscarEmple);

			tableDatosEmpleados.setModel(modelo);
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


	@SuppressWarnings("unlikely-arg-type")
	public void busquedaPorCamposCombo(Integer valor) {
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
		modelo.addColumn("CURP");
		modelo.addColumn("RFC");
		modelo.addColumn("REGISTRO IMSS");
		modelo.addColumn("PUESTO");
		modelo.addColumn("SALARIO");
		modelo.addColumn("UNIDAD RESPONSABLE");
		modelo.addColumn("TIPO NOMINA");

		tableDatosEmpleados.setModel(modelo);
		tableDatosEmpleados.setBackground(Color.WHITE);

		JTableHeader th = new JTableHeader();
		Color colorSilverLight = new Color(176, 196, 222);
		Color colorNegro = new Color(0, 0, 0);
		th = tableDatosEmpleados.getTableHeader();
		Font fuente = new Font("Cooper Black", Font.BOLD, 14);
		th.setFont(fuente);
		th.setBackground(colorSilverLight);
		th.setForeground(colorNegro);

		// TableRowSorter sorter = new TableRowSorter(modelo);
		// tableDatosEmpleados.setRowSorter(sorter);

		TableColumnModel columnModel = tableDatosEmpleados.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(100);
		columnModel.getColumn(1).setPreferredWidth(200);
		columnModel.getColumn(2).setPreferredWidth(200);
		columnModel.getColumn(3).setPreferredWidth(200);
		columnModel.getColumn(4).setPreferredWidth(300);
		columnModel.getColumn(5).setPreferredWidth(200);
		columnModel.getColumn(6).setPreferredWidth(200);
		columnModel.getColumn(7).setPreferredWidth(300);
		columnModel.getColumn(8).setPreferredWidth(150);
		columnModel.getColumn(9).setPreferredWidth(450);
		columnModel.getColumn(10).setPreferredWidth(700);

		String sqlSelect = "";
		if (valor.equals("")) {
			sqlSelect = "select CLAVE,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,CURP,RFC,REGISTRO_IMSS from dbo.empleados WHERE ELIMINAR_LOGICO='" + 1 + "' and DBO.EMPLEADOS.ID_EJERCICIOS = '"+ valor +"'";// order by id_puesto
		} else {
			sqlSelect="SELECT DBO.EMPLEADOS.CLAVE,DBO.EMPLEADOS.NOMBRE,DBO.EMPLEADOS.APELLIDO_PATERNO,DBO.EMPLEADOS.APELLIDO_MATERNO,DBO.EMPLEADOS.CURP,DBO.EMPLEADOS.RFC,DBO.EMPLEADOS.REGISTRO_IMSS,\r\n" +  
					"					dbo.puestos.nombre_puesto,dbo.puestos.salario,DBO.DEPENDENCIAS.UNIDAD_RESPONSABLE, \r\n" + 
					"					DBO.EJERCICIOS.NOMBRE_EJERCICIO\r\n" + 
					"from empleados\r\n" + 
					"left JOIN DBO.PUESTOS ON DBO.EMPLEADOS.ID_PUESTO = DBO.PUESTOS.no_puesto\r\n" + 
					"LEFT JOIN DBO.DEPENDENCIAS ON DBO.EMPLEADOS.ID_UNIDAD = DBO.DEPENDENCIAS.ID_UNIDAD\r\n" + 
					"LEFT JOIN DBO.EJERCICIOS ON DBO.EMPLEADOS.ID_EJERCICIOS = DBO.EJERCICIOS.ID_EJERCICIOS\r\n" +
					"WHERE DBO.EMPLEADOS."+atributo+" = '"+valor+"' and ELIMINAR_LOGICO='1'\r\n" ;
		}
		Object datos[] = new String[11];
		PoolNYCH nych = new PoolNYCH();
		Connection con = null;
		Statement st;
		ResultSet resultSet;
		try {
			con = nych.datasource.getConnection();
			st = con.createStatement();
			resultSet = st.executeQuery(sqlSelect);
			while (resultSet.next()) {
				datos[0] = resultSet.getString(1);
				datos[1] = resultSet.getString(2);
				datos[2] = resultSet.getString(3);
				datos[3] = resultSet.getString(4);
				datos[4] = resultSet.getString(5);
				datos[5] = resultSet.getString(6);
				datos[6] = resultSet.getString(7);
				datos[7] = resultSet.getString(8);
				datos[8] = resultSet.getString(9);
				datos[9] = resultSet.getString(10);
				datos[10] = resultSet.getString(11);
				modelo.addRow(datos);
			}
			tableDatosEmpleados.setModel(modelo);
		} catch (SQLException el) {
			el.printStackTrace();
			StringWriter errors = new StringWriter();
			el.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: " + errors);
			// JOptionPane.showMessageDialog(null, el, "Error de conexion",
			// JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");
		} finally {
			try {
				con.close();
			} catch (SQLException ep) {
				ep.printStackTrace();
				StringWriter errors = new StringWriter();
				ep.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: " + errors);
				// JOptionPane.showMessageDialog(null, ep, "Error de des-conexion",
				// JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");
			}
		}

	}


	public void mostrarDatosEmpleadoFalso(String valor) {
		DefaultTableModel modeloFalso = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				if (column == 11) {
					return true;
				} else {
					return false;
				}
			}
		};
		modeloFalso.addColumn("CLAVE");
		modeloFalso.addColumn("NOMBRE");
		modeloFalso.addColumn("APELLIDO PATERNO");
		modeloFalso.addColumn("APELLIDO MATERNO");
		modeloFalso.addColumn("FECHA NACIMIENTO");
		modeloFalso.addColumn("CURP");
		modeloFalso.addColumn("RFC");
		modeloFalso.addColumn("REGISTRO IMSS");
		modeloFalso.addColumn("TELEFONO");
		modeloFalso.addColumn("EMAIL");
		modeloFalso.addColumn("DIRECCION");
		modeloFalso.addColumn("CIUDAD");
		modeloFalso.addColumn("ESTADO");
		modeloFalso.addColumn("CODIGO POSTAL");
		modeloFalso.addColumn("CONTACTO FAMILIAR");
		modeloFalso.addColumn("EMAIL CONTACTO");
		modeloFalso.addColumn("CELULAR CONTACTO");
		modeloFalso.addColumn("DIRECCION CONTACTO");
		modeloFalso.addColumn("SDI");
		modeloFalso.addColumn("NUMERO CREDITO INFONAVIT");
		modeloFalso.addColumn("FACTOR INFONAVIT");
		modeloFalso.addColumn("U.M.A.");
		modeloFalso.addColumn("ESTADO CIVIL");
		modeloFalso.addColumn("LOCALIDAD");
		modeloFalso.addColumn("FECHA ALTA IMSS");
		modeloFalso.addColumn("FECHA BAJA IMSS");
		modeloFalso.addColumn("CUENTA BANCARIA");
		modeloFalso.addColumn("MONEDERO");
		modeloFalso.addColumn("BANCO");
		modeloFalso.addColumn("EJERICIO");
		modeloFalso.addColumn("FECHA INGRESO");
		modeloFalso.addColumn("FECHA BAJA");
		modeloFalso.addColumn("UNIDAD");
		modeloFalso.addColumn("PUESTO");
		modeloFalso.addColumn("CALISIFICACION DISPERSION");
		modeloFalso.addColumn("REGIMEN");
		modeloFalso.addColumn("TIPO EMPLEADO");
		modeloFalso.addColumn("TIPO JORNADA");
		modeloFalso.addColumn("RIESGO");
		modeloFalso.addColumn("CONTRATO");
		modeloFalso.addColumn("CLASIFICACION EMPLEADO");
		modeloFalso.addColumn("GENERO");

		tableDatosEmpleadosFalsa.setModel(modeloFalso);
		tableDatosEmpleadosFalsa.setBackground(Color.WHITE);

		JTableHeader thFalso = new JTableHeader();
		Color colorSilverLightFalso = new Color(176, 196, 222);
		Color colorNegroFalso = new Color(0, 0, 0);
		thFalso = tableDatosEmpleadosFalsa.getTableHeader();
		Font fuenteFalso = new Font("Cooper Black", Font.BOLD, 14);
		thFalso.setFont(fuenteFalso);
		thFalso.setBackground(colorSilverLightFalso);
		thFalso.setForeground(colorNegroFalso);

		String sqlSelectFalso = "";
		sqlSelectFalso="SELECT DBO.EMPLEADOS.CLAVE,DBO.EMPLEADOS.NOMBRE,DBO.EMPLEADOS.APELLIDO_PATERNO,DBO.EMPLEADOS.APELLIDO_MATERNO,DBO.EMPLEADOS.FECHA_NACIMIENTO,DBO.EMPLEADOS.CURP,DBO.EMPLEADOS.RFC,DBO.EMPLEADOS.REGISTRO_IMSS,DBO.EMPLEADOS.TELEFONO,DBO.EMPLEADOS.EMAIL ,\r\n" + 
				"					DBO.EMPLEADOS.DIRECCION,DBO.EMPLEADOS.CIUDAD,DBO.EMPLEADOS.ESTADO,DBO.EMPLEADOS.CODIGO_POSTAL,DBO.EMPLEADOS.CONTACTO_FAMILIAR,DBO.EMPLEADOS.EMAIL_CONTACTO,DBO.EMPLEADOS.CELULAR_CONTACTO,DBO.EMPLEADOS.DIRECCION_CONTACTO,DBO.EMPLEADOS.SDI ,\r\n" + 
				"					DBO.EMPLEADOS.NO_CREDITO_INFONAVIT,DBO.EMPLEADOS.FACTOR_INFONAVIT,DBO.EMPLEADOS.U_M_A,DBO.ESTADO_CIVIL.EDO_CIVIL,DBO.EMPLEADOS.LOCALIDAD,DBO.EMPLEADOS.FECHA_ALTA_IMSS,DBO.EMPLEADOS.FECHA_BAJA_IMSS,DBO.EMPLEADOS.CUENTA_BANCARIA,\r\n" + 
				"					DBO.EMPLEADOS.MONEDERO,DBO.CATALOGOS_BANCOS.NOMBRE_CORTO,DBO.EJERCICIOS.NOMBRE_EJERCICIO,DBO.EMPLEADOS.FECHA_INGRESO,DBO.EMPLEADOS.FECHA_BAJA,DBO.DEPENDENCIAS.UNIDAD_RESPONSABLE,DBO.puestos.NOMBRE_PUESTO,DBO.CLASIFICACION_DISPERSION.CLAVE,DBO.REGIMEN_CONTRATACION.DESCRIPCION, \r\n" + 
				"					DBO.TIPO_EMPLEADO.tipo_empleado,DBO.TIPO_JORNADA.TIPO_JORNADA,DBO.RIESGO_PUESTO.DESCRIPCION,DBO.TIPO_CONTRATO.CONTRATO,DBO.TIPO_CLASIFICACION_EMPLEADO.clasificacion_empleado,DBO.GENERO.GENERO \r\n" +
				"from empleados\r\n" + 
				"left JOIN DBO.PUESTOS ON DBO.EMPLEADOS.ID_PUESTO = DBO.PUESTOS.no_puesto\r\n" + 
				"left JOIN DBO.DEPENDENCIAS ON DBO.EMPLEADOS.ID_UNIDAD = DBO.DEPENDENCIAS.ID_UNIDAD\r\n" + 
				"left JOIN DBO.EJERCICIOS ON DBO.EMPLEADOS.ID_EJERCICIOS = DBO.EJERCICIOS.ID_EJERCICIOS\r\n" +
				"left JOIN DBO.GENERO ON DBO.EMPLEADOS.ID_GENERO = DBO.GENERO.ID_GENERO\r\n" +
				"left JOIN DBO.ESTADO_CIVIL ON DBO.EMPLEADOS.ESTADO_CIVIL = DBO.ESTADO_CIVIL.ID_ESTADO_CIVIL\r\n"+
				"left JOIN DBO.CLASIFICACION_DISPERSION ON DBO.EMPLEADOS.ID_CLASIFICACION_DISPERSION = CLASIFICACION_DISPERSION.ID_CLASIFICACION_DISPERSION\r\n" + 
				"left JOIN DBO.TIPO_CLASIFICACION_EMPLEADO ON DBO.EMPLEADOS.ID_TIPO_EMPLEADO = TIPO_CLASIFICACION_EMPLEADO.id_clasificacion_empleado\r\n"+
				"left JOIN DBO.REGIMEN_CONTRATACION ON DBO.EMPLEADOS.ID_REGIMEN = DBO.REGIMEN_CONTRATACION.ID_REGIMEN\r\n" + 
				"left JOIN DBO.TIPO_EMPLEADO ON DBO.EMPLEADOS.ID_TIPO_EMPLEADO = DBO.TIPO_EMPLEADO.id_tipo_empleado\r\n" + 
				"left JOIN DBO.TIPO_JORNADA ON DBO.EMPLEADOS.ID_TIPO_JORNADA = DBO.TIPO_JORNADA.ID_TIPO_JORNADA\r\n" + 
				"left JOIN DBO.RIESGO_PUESTO ON DBO.EMPLEADOS.ID_RIESGO = DBO.RIESGO_PUESTO.ID_RIESGO\r\n" + 
				"left JOIN DBO.TIPO_CONTRATO ON DBO.EMPLEADOS.ID_CONTRATO = DBO.TIPO_CONTRATO.ID_CONTRATO\r\n"+
				"left JOIN DBO.CATALOGOS_BANCOS ON DBO.EMPLEADOS.ID_BANCO = DBO.CATALOGOS_BANCOS.ID_BANCO\r\n"+
				"WHERE DBO.EMPLEADOS.ID_EJERCICIOS = '"+valor+"' and ELIMINAR_LOGICO='1'\r\n" ; 
		//"order by dbo.EMPLEADOS.id_puesto";
//		System.out.println("falso: " + sqlSelectFalso);
		Connection con = null;
		Statement st;
		ResultSet resultSet;
		Object datosFalso[] = new String[42];
		PoolNYCH nych = new PoolNYCH();
		try {
			con = nych.datasource.getConnection();
			st = con.createStatement();
			resultSet = st.executeQuery(sqlSelectFalso);
			while (resultSet.next()) {// && resGenero.next() && resPuesto.next()
				datosFalso[0] = resultSet.getString(1);
				datosFalso[1] = resultSet.getString(2);
				datosFalso[2] = resultSet.getString(3);
				datosFalso[3] = resultSet.getString(4);
				datosFalso[4] = resultSet.getString(5);
				datosFalso[5] = resultSet.getString(6);
				datosFalso[6] = resultSet.getString(7);
				datosFalso[7] = resultSet.getString(8);
				datosFalso[8] = resultSet.getString(9);
				datosFalso[9] = resultSet.getString(10);
				datosFalso[10] = resultSet.getString(11);
				datosFalso[11] = resultSet.getString(12);
				datosFalso[12] = resultSet.getString(13);
				datosFalso[13] = resultSet.getString(14);
				datosFalso[14] = resultSet.getString(15);
				datosFalso[15] = resultSet.getString(16);
				datosFalso[16] = resultSet.getString(17);
				datosFalso[17] = resultSet.getString(18);
				datosFalso[18] = resultSet.getString(19);
				datosFalso[19] = resultSet.getString(20);
				datosFalso[20] = resultSet.getString(21);
				datosFalso[21] = resultSet.getString(22);
				datosFalso[22] = resultSet.getString(23);
				datosFalso[23] = resultSet.getString(24);
				datosFalso[24] = resultSet.getString(25);
				datosFalso[25] = resultSet.getString(26);
				datosFalso[26] = resultSet.getString(27);
				datosFalso[27] = resultSet.getString(28);
				datosFalso[28] = resultSet.getString(29);
				datosFalso[29] = resultSet.getString(30);
				datosFalso[30] = resultSet.getString(31);
				datosFalso[31] = resultSet.getString(32);
				datosFalso[32] = resultSet.getString(33);
				datosFalso[33] = resultSet.getString(34);
				datosFalso[34] = resultSet.getString(35);
				datosFalso[35] = resultSet.getString(36);
				datosFalso[36] = resultSet.getString(37);
				datosFalso[37] = resultSet.getString(38);
				datosFalso[38] = resultSet.getString(39);
				datosFalso[39] = resultSet.getString(40);
				datosFalso[40] = resultSet.getString(41);
				datosFalso[41] = resultSet.getString(42);
				modeloFalso.addRow(datosFalso);
				
//				System.out.println("Falso clave: "+datosFalso[0] +" fecha nac: "+datosFalso[4]);
			}

			//			rowSortereFalso = new TableRowSorter(modeloFalso);
			//			tableDatosEmpleadosFalsa.setRowSorter(rowSortereFalso);
			tableDatosEmpleadosFalsa.setModel(modeloFalso);
		} catch (SQLException el) {
			el.printStackTrace();
			LOG.info("Excepción: " + el);
			JOptionPane.showMessageDialog(null, el, "Error de conexion", JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");
		} finally {
			try {
				con.close();
			} catch (SQLException ep) {
				ep.printStackTrace();
				LOG.info("Excepción: " + ep);
				JOptionPane.showMessageDialog(null, ep, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void redireccionarActualizarRegistro() {


		internalFrameActualizar.comboBoxActualizarGenero.removeAllItems();
		internalFrameActualizar.comboBoxActualizarEdoCivil.removeAllItems();
		internalFrameActualizar.comboBoxActLocal.removeAllItems();
		internalFrameActualizar.comboBoxActTipoNomina.removeAllItems();
		internalFrameActualizar.comboBoxActualizarDependencia.removeAllItems();
		internalFrameActualizar.comboBoxPuesto.removeAllItems();
		internalFrameActualizar.comboBoxClasificacion.removeAllItems();
		internalFrameActualizar.comboBoxRegimen.removeAllItems();
		internalFrameActualizar.comboBoxTipoEmpleado.removeAllItems();
		internalFrameActualizar.comboBoxTipoJornada.removeAllItems();
		internalFrameActualizar.comboBoxRiesgo.removeAllItems();
		internalFrameActualizar.comboBoxTipoContrato.removeAllItems();
		internalFrameActualizar.comboBoxClasificacionEmpleado.removeAllItems();
		internalFrameActualizar.comboBoxBanco.removeAllItems();


		ArrayList<String> listaGenero = new ArrayList<String>();
		listaGenero = internalFrameActualizar.selectComboGenero();
		internalFrameActualizar.comboBoxActualizarGenero.addItem("Seleccione Uno");
		for (int i = 0; i < listaGenero.size(); i++) {
			internalFrameActualizar.comboBoxActualizarGenero.addItem(listaGenero.get(i));
		}

		ArrayList<String> listaEdoCivil = new ArrayList<String>();
		listaEdoCivil = selectComboEdoCivil();
		internalFrameActualizar.comboBoxActualizarEdoCivil.addItem("Seleccione Uno");
		for (int i = 0; i < listaEdoCivil.size(); i++) {
			internalFrameActualizar.comboBoxActualizarEdoCivil.addItem(listaEdoCivil.get(i));
		}
		//
		ArrayList<String> listaLocal = new ArrayList<String>();
		listaLocal = buscarLocalidad();
		internalFrameActualizar.comboBoxActLocal.addItem("Seleccione Una");
		for (int i = 0; i < listaLocal.size(); i++) {
			internalFrameActualizar.comboBoxActLocal.addItem(listaLocal.get(i));
		}

		ArrayList<String> listaEjercicio = new ArrayList<String>();
		listaEjercicio = buscarEjercicio();
		internalFrameActualizar.comboBoxActTipoNomina.addItem("Seleccione Uno");
		for (int i = 0; i < listaEjercicio.size(); i++) {
			internalFrameActualizar.comboBoxActTipoNomina.addItem(listaEjercicio.get(i));
		}

		ArrayList<String> listaDependencia = new ArrayList<String>();
		listaDependencia = selectComboDependencia();
		internalFrameActualizar.comboBoxActualizarDependencia.addItem("Seleccione Una");
		for (int i = 0; i < listaDependencia.size(); i++) {
			internalFrameActualizar.comboBoxActualizarDependencia.addItem(listaDependencia.get(i));
		}


		ArrayList<String> listaPuestos = new ArrayList<String>();
		listaPuestos = selectComboPuesto();
		internalFrameActualizar.comboBoxPuesto.addItem("Seleccione Uno");
		for (int i = 0; i < listaPuestos.size(); i++) {
			internalFrameActualizar.comboBoxPuesto.addItem(listaPuestos.get(i));
		}


		ArrayList<String> listaClasificacionDispersion = new ArrayList<String>();
		listaClasificacionDispersion = buscarClasificacionDispersion();
		internalFrameActualizar.comboBoxClasificacion.addItem("Seleccione Una");
		for (int i = 0; i < listaClasificacionDispersion.size(); i++) {
			internalFrameActualizar.comboBoxClasificacion.addItem(listaClasificacionDispersion.get(i));
		}

		ArrayList<String> listaRegimen = new ArrayList<String>();
		listaRegimen = buscarRegimen();
		internalFrameActualizar.comboBoxRegimen.addItem("Seleccione Uno");
		for (int i = 0; i < listaRegimen.size(); i++) {
			internalFrameActualizar.comboBoxRegimen.addItem(listaRegimen.get(i));
		}

		ArrayList<String> listaTipoEmpleado = new ArrayList<String>();
		listaTipoEmpleado = buscarTipoEmpleado();
		internalFrameActualizar.comboBoxTipoEmpleado.addItem("Seleccione Uno");
		for (int i = 0; i < listaTipoEmpleado.size(); i++) {
			internalFrameActualizar.comboBoxTipoEmpleado.addItem(listaTipoEmpleado.get(i));
		}

		ArrayList<String> listaTipoJornada = new ArrayList<String>();
		listaTipoJornada = buscarTipoJornada();
		internalFrameActualizar.comboBoxTipoJornada.addItem("Seleccione Una");
		for (int i = 0; i < listaTipoJornada.size(); i++) {
			internalFrameActualizar.comboBoxTipoJornada.addItem(listaTipoJornada.get(i));
		}

		ArrayList<String> listaRiesgo = new ArrayList<String>();
		listaRiesgo = buscarRiesgo();
		internalFrameActualizar.comboBoxRiesgo.addItem("Seleccione Uno");
		for (int i = 0; i < listaRiesgo.size(); i++) {
			internalFrameActualizar.comboBoxRiesgo.addItem(listaRiesgo.get(i));
		}

		ArrayList<String> listaTipoContrato = new ArrayList<String>();
		listaTipoContrato = buscarTipoContrato();
		internalFrameActualizar.comboBoxTipoContrato.addItem("Seleccione Uno");
		for (int i = 0; i < listaTipoContrato.size(); i++) {
			internalFrameActualizar.comboBoxTipoContrato.addItem(listaTipoContrato.get(i));
		}

		ArrayList<String> listaClasifEmpleado = new ArrayList<String>();
		listaClasifEmpleado = buscarClasifEmpleado();
		internalFrameActualizar.comboBoxClasificacionEmpleado.addItem("Seleccione Uno");
		for (int i = 0; i < listaClasifEmpleado.size(); i++) {
			internalFrameActualizar.comboBoxClasificacionEmpleado.addItem(listaClasifEmpleado.get(i));
		}

		ArrayList<String> listaBancos = new ArrayList<String>();
		listaBancos = buscarBancos();
		internalFrameActualizar.comboBoxBanco.addItem("Seleccione Uno");
		for (int i = 0; i < listaBancos.size(); i++) {
			internalFrameActualizar.comboBoxBanco.addItem(listaBancos.get(i));
		}

	}

	// metodo verdadero
	/*public void mostrarDatosEmpleado(String valor) {
		DefaultTableModel modelo = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				// return super.isCellEditable(row, column);
				if (column == 11) {
					return true;
				} else {
					return false;
				}
			}
		};
		modelo.addColumn("CLAVE");
		modelo.addColumn("NOMBRE");
		modelo.addColumn("APELLIDO PATERNO");
		modelo.addColumn("APELLIDO MATERNO");
		modelo.addColumn("CURP");
		modelo.addColumn("RFC");
		modelo.addColumn("REGISTRO IMSS");
		modelo.addColumn("PUESTO");
		modelo.addColumn("SALARIO");
		modelo.addColumn("UNIDAD RESPONSABLE");
		modelo.addColumn("TIPO NOMINA");

		tableDatosEmpleados.setModel(modelo);
		tableDatosEmpleados.setBackground(Color.WHITE);

		JTableHeader th = new JTableHeader();
		Color colorSilverLight = new Color(176, 196, 222);
		Color colorNegro = new Color(0, 0, 0);
		th = tableDatosEmpleados.getTableHeader();
		Font fuente = new Font("Amazone", Font.BOLD, 14);
		th.setFont(fuente);
		th.setBackground(colorSilverLight);
		th.setForeground(colorNegro);

		// TableRowSorter sorter = new TableRowSorter(modelo);
		// tableDatosEmpleados.setRowSorter(sorter);

		TableColumnModel columnModel = tableDatosEmpleados.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(100);
		columnModel.getColumn(1).setPreferredWidth(200);
		columnModel.getColumn(2).setPreferredWidth(200);
		columnModel.getColumn(3).setPreferredWidth(200);
		columnModel.getColumn(4).setPreferredWidth(300);
		columnModel.getColumn(5).setPreferredWidth(200);
		columnModel.getColumn(6).setPreferredWidth(300);
		columnModel.getColumn(7).setPreferredWidth(200);
		columnModel.getColumn(8).setPreferredWidth(200);
		columnModel.getColumn(9).setPreferredWidth(350);
		columnModel.getColumn(10).setPreferredWidth(700);

		String sqlSelect = "";
		String sqlpuesto = "";
		String sqlDependencia = "";
		String sqlEjercicio = "";
		sqlSelect = "select CLAVE,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,CURP,RFC,REGISTRO_IMSS from dbo.empleados WHERE ELIMINAR_LOGICO='"+ 1 + "' ";//and id_ejercicios = "+ 4 +"
		sqlpuesto = "SELECT dbo.puestos.nombre_puesto,dbo.puestos.salario FROM dbo.EMPLEADOS left JOIN DBO.PUESTOS ON DBO.EMPLEADOS.ID_PUESTO = DBO.PUESTOS.no_puesto ";
		sqlDependencia = "SELECT DBO.DEPENDENCIAS.UNIDAD_RESPONSABLE FROM dbo.EMPLEADOS left JOIN DBO.DEPENDENCIAS ON DBO.EMPLEADOS.ID_UNIDAD = DBO.DEPENDENCIAS.ID_UNIDAD ";
		sqlEjercicio = "SELECT DBO.EJERCICIOS.NOMBRE_EJERCICIO FROM dbo.EMPLEADOS left JOIN DBO.EJERCICIOS ON DBO.EMPLEADOS.ID_EJERCICIOS = DBO.EJERCICIOS.ID_EJERCICIOS ";
		//System.out.println("sql: " + sqlSelect);
		//System.out.println("sql: " + sqlpuesto);

		Object datos[] = new String[11];
		PoolNYCH nych = new PoolNYCH();
		Connection con = null;
		Statement st;
		ResultSet resultSet;
		Connection conPuesto = null;
		Connection conDepen = null;
		Connection conEjercicio = null;
		Statement stPuesto;
		Statement stDepen;
		Statement stEjercicio;
		ResultSet resPuesto;
		ResultSet resDependencia;
		ResultSet resEjercicio;
		try {
			con = nych.datasource.getConnection();
			conPuesto = nych.datasource.getConnection();
			conDepen = nych.datasource.getConnection();
			conEjercicio = nych.datasource.getConnection();
			st = con.createStatement();
			stPuesto = conPuesto.createStatement();
			stDepen = conDepen.createStatement();
			stEjercicio = conEjercicio.createStatement();
			resultSet = st.executeQuery(sqlSelect);
			resPuesto = stPuesto.executeQuery(sqlpuesto);
			resDependencia = stDepen.executeQuery(sqlDependencia);
			resEjercicio = stEjercicio.executeQuery(sqlEjercicio);
			while (resultSet.next() && resPuesto.next() && resDependencia.next() && resEjercicio.next()) {
				datos[0] = resultSet.getString(1);
				datos[1] = resultSet.getString(2);
				datos[2] = resultSet.getString(3);
				datos[3] = resultSet.getString(4);
				datos[4] = resultSet.getString(5);
				datos[5] = resultSet.getString(6);
				datos[6] = resultSet.getString(7);
				datos[7] = resPuesto.getString(1);
				datos[8] = resPuesto.getString(2);
				datos[9] = resDependencia.getString(1);
				datos[10] = resEjercicio.getString(1);
				modelo.addRow(datos);
			}
			tableDatosEmpleados.setModel(modelo);
			// rowSortere = new TableRowSorter(modelo);
			// tableDatosEmpleados.setRowSorter(rowSortere);
		} catch (SQLException el) {
			// el.printStackTrace();
			StringWriter errors = new StringWriter();
			el.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: " + errors);
			// JOptionPane.showMessageDialog(null, el, "Error de conexion",
			// JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");

		} finally {
			try {
				con.close();
			} catch (SQLException ep) {
				// if (LOG.isTraceEnabled())
				// {
				// LOG.info("mensaje de trace.");
				// System.out.print("Ex: "+ep);
				// System.out.print("Exxx: "+ep.getStackTrace());
				// }
				// LOG.info("Excepción: " + ep.getCause());
				// JOptionPane.showMessageDialog(null, ep, "Error de desconexion",
				// JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");
			}
		}
	}*/

	public static ArrayList<String> selectComboEdoCivil() {
		PoolNYCH poolNYCH = new PoolNYCH();
		Connection connect = null;
		ArrayList<String> lista = new ArrayList<String>();
		String sqlComboEdoCivil = "SELECT * FROM DBO.ESTADO_CIVIL";
		Statement st;
		ResultSet resultSet = null;
		try {
			connect = poolNYCH.datasource.getConnection();
			st = connect.createStatement();
			resultSet = st.executeQuery(sqlComboEdoCivil);
		} catch (Exception exp) {
			exp.printStackTrace();
			StringWriter errors = new StringWriter();
			exp.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: " + errors);
			// JOptionPane.showMessageDialog(null, exp, "Error de conexion",
			// JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");
		}
		try {
			while (resultSet.next()) {
				lista.add(resultSet.getString("EDO_CIVIL"));
			}
		} catch (Exception et) {
			et.printStackTrace();
			StringWriter errors = new StringWriter();
			et.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: " + errors);
			// JOptionPane.showMessageDialog(null, et, "Error de conexion",
			// JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");
		} finally {
			try {
				connect.close();
			} catch (SQLException ep) {
				ep.printStackTrace();
				StringWriter errors = new StringWriter();
				ep.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: " + errors);
				// JOptionPane.showMessageDialog(null, ep, "Error de desconexion",
				// JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");
			}
		}
		return lista;
	}

	public static ArrayList<String> selectComboDependencia() {
		PoolNYCH nych = new PoolNYCH();
		Connection connect = null;
		Statement st;
		ResultSet resultSet = null;
		ArrayList<String> lista = new ArrayList<String>();
		// String sqlComboDependencia = "SELECT DBO.DEPENDENCIAS.UNIDAD_RESPONSABLE from
		// dbo.DEPENDENCIAS inner join dbo.empleados on dbo.DEPENDENCIAS.ID_UNIDAD =
		// dbo.EMPLEADOS.ID_UNIDAD";
		String sqlComboDependencia = "SELECT DBO.DEPENDENCIAS.UNIDAD_RESPONSABLE from dbo.DEPENDENCIAS";
		try {
			connect = nych.datasource.getConnection();
			st = connect.createStatement();
			resultSet = st.executeQuery(sqlComboDependencia);
		} catch (Exception exp) {
			exp.printStackTrace();
			StringWriter errors = new StringWriter();
			exp.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: " + errors);
			// JOptionPane.showMessageDialog(null, exp, "Error de conexion",
			// JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");
		}
		try {
			while (resultSet.next()) {
				lista.add(resultSet.getString("UNIDAD_RESPONSABLE"));
			}
		} catch (Exception et) {
			et.printStackTrace();
			StringWriter errors = new StringWriter();
			et.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: " + errors);
			// JOptionPane.showMessageDialog(null, et, "Error de desconexion",
			// JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");
		} finally {
			try {
				connect.close();
			} catch (SQLException ep) {
				ep.printStackTrace();
				StringWriter errors = new StringWriter();
				ep.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: " + errors);
				// JOptionPane.showMessageDialog(null, ep, "Error de desconexion",
				// JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");
			}
		}
		return lista;
	}

	public static ArrayList<String> selectComboPuesto() {
		PoolNYCH nych = new PoolNYCH();
		Connection connect = null;
		Statement st;
		ResultSet resultSet = null;
		ArrayList<String> lista = new ArrayList<String>();
		String sqlComboPuesto = "SELECT DBO.PUESTOS.NOMBRE_PUESTO from dbo.PUESTOS";
		try {
			connect = nych.datasource.getConnection();
			st = connect.createStatement();
			resultSet = st.executeQuery(sqlComboPuesto);
		} catch (Exception exp) {
			exp.printStackTrace();
			StringWriter errors = new StringWriter();
			exp.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: " + errors);
			// JOptionPane.showMessageDialog(null, exp, "Error de conexion",
			// JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");
		}
		try {
			while (resultSet.next()) {
				lista.add(resultSet.getString("NOMBRE_PUESTO"));
				//System.out.println("LISTA: "+ lista.size() );
			}
		} catch (Exception et) {
			et.printStackTrace();
			StringWriter errors = new StringWriter();
			et.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: " + errors);
			// JOptionPane.showMessageDialog(null, et, "Error de desconexion",
			// JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");
		} finally {
			try {
				connect.close();
			} catch (SQLException ep) {
				ep.printStackTrace();
				StringWriter errors = new StringWriter();
				ep.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: " + errors);
				// JOptionPane.showMessageDialog(null, ep, "Error de desconexion",
				// JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");
			}
		}
		return lista;
	}

	public static ArrayList<String> selectComboPuesto2() {
		PoolNYCH nych = new PoolNYCH();
		Connection connect = null;
		Statement st;
		ResultSet resultSet = null;
		ArrayList<String> lista = new ArrayList<String>();
		String sqlComboPuesto = "SELECT dbo.empleados.id_empleado,dbo.empleados.nombre,dbo.empleados.apellido_paterno,dbo.empleados.apellido_materno,dbo.empleados.registro_imss,dbo.puestos.nombre_puesto,dbo.puestos.salario,dbo.empleados.curp,dbo.empleados.rfc FROM dbo.EMPLEADOS left JOIN DBO.PUESTOS ON DBO.EMPLEADOS.ID_PUESTO = DBO.PUESTOS.no_puesto where dbo.empleados.id_empleado=1535 order by dbo.PUESTOS.no_puesto";
		try {
			connect = nych.datasource.getConnection();
			st = connect.createStatement();
			resultSet = st.executeQuery(sqlComboPuesto);
		} catch (Exception exp) {
			exp.printStackTrace();
			StringWriter errors = new StringWriter();
			exp.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: " + errors);
			// JOptionPane.showMessageDialog(null, exp, "Error de conexion",
			// JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");
		}
		try {
			while (resultSet.next()) {
				lista.add(resultSet.getString("id_empleado"));
				lista.add(resultSet.getString("nombre"));
				lista.add(resultSet.getString("apellido_paterno"));
				lista.add(resultSet.getString("apellido_materno"));
				lista.add(resultSet.getString("registro_imss"));
				lista.add(resultSet.getString("NOMBRE_PUESTO"));
				lista.add(resultSet.getString("salario"));
				lista.add(resultSet.getString("curp"));
				lista.add(resultSet.getString("rfc"));
			}
		} catch (Exception et) {
			et.printStackTrace();
			StringWriter errors = new StringWriter();
			et.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: " + errors);
			// JOptionPane.showMessageDialog(null, et, "Error de desconexion",
			// JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");
		} finally {
			try {
				connect.close();
			} catch (SQLException ep) {
				ep.printStackTrace();
				StringWriter errors = new StringWriter();
				ep.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: " + errors);
				// JOptionPane.showMessageDialog(null, ep, "Error de desconexion",
				// JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");
			}
		}
		return lista;
	}

	public static ArrayList<String> selectComboPuesto3() {
		PoolNYCH nych = new PoolNYCH();
		Connection connect = null;
		Statement st;
		ResultSet resultSet = null;
		ArrayList<String> lista = new ArrayList<String>();
		String sqlComboPuesto = "SELECT dbo.empleados.id_empleado,dbo.empleados.nombre,dbo.empleados.apellido_paterno,dbo.empleados.apellido_materno,dbo.empleados.registro_imss,dbo.puestos.nombre_puesto,dbo.puestos.salario,dbo.empleados.curp,dbo.empleados.rfc FROM dbo.EMPLEADOS left JOIN DBO.PUESTOS ON DBO.EMPLEADOS.ID_PUESTO = DBO.PUESTOS.no_puesto order by dbo.PUESTOS.no_puesto";
		try {
			connect = nych.datasource.getConnection();
			st = connect.createStatement();
			resultSet = st.executeQuery(sqlComboPuesto);
		} catch (Exception exp) {
			exp.printStackTrace();
			StringWriter errors = new StringWriter();
			exp.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: " + errors);
			// JOptionPane.showMessageDialog(null, exp, "Error de conexion",
			// JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");
		}
		try {
			while (resultSet.next()) {
				// lista.add(resultSet.getString("id_empleado"));
				lista.add(resultSet.getString("nombre"));
				// lista.add(resultSet.getString("apellido_paterno"));
				// lista.add(resultSet.getString("apellido_materno"));
				// lista.add(resultSet.getString("registro_imss"));
				// lista.add(resultSet.getString("NOMBRE_PUESTO"));
				// lista.add(resultSet.getString("salario"));
				// lista.add(resultSet.getString("curp"));
				// lista.add(resultSet.getString("rfc"));
			}
		} catch (Exception et) {
			et.printStackTrace();
			StringWriter errors = new StringWriter();
			et.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: " + errors);
			// JOptionPane.showMessageDialog(null, et, "Error de desconexion",
			// JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");
		} finally {
			try {
				connect.close();
			} catch (SQLException ep) {
				ep.printStackTrace();
				StringWriter errors = new StringWriter();
				ep.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: " + errors);
				// JOptionPane.showMessageDialog(null, ep, "Error de desconexion",
				// JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");
			}
		}
		return lista;
	}

	public static ArrayList<String> buscarLocalidad() {
		PoolGenerales poolG = new PoolGenerales();
		Connection connect = null;
		ResultSet resultSet = null;
		String sqlSelectLocalidad = "SELECT * FROM DBO.LOCALIDADES";
		//System.out.println(sqlSelectLocalidad);
		ArrayList<String> lista = new ArrayList<String>();
		try {
			connect = poolG.datasource.getConnection();
			Statement st = connect.createStatement();
			resultSet = st.executeQuery(sqlSelectLocalidad);
		} catch (Exception exp) {
			exp.printStackTrace();
			StringWriter errors = new StringWriter();
			exp.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: " + errors);
			// JOptionPane.showMessageDialog(null, exp, "Error de conexion",
			// JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");
		}
		try {
			while (resultSet.next()) {
				lista.add(resultSet.getString("Localidad"));
			}
		} catch (Exception exc) {
			exc.printStackTrace();
			StringWriter errors = new StringWriter();
			exc.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: " + errors);
			// JOptionPane.showMessageDialog(null, exc, "Error de conexion",
			// JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");
		} finally {
			try {
				connect.close();
			} catch (SQLException ep) {
				ep.printStackTrace();
				StringWriter errors = new StringWriter();
				ep.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: " + errors);
				// JOptionPane.showMessageDialog(null, ep, "Error de desconexion",
				// JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");
			}
		}
		return lista;
	}

	public static ArrayList<String> buscarEjercicio() {
		//System.out.println("buscarEjercicio");
		PoolNYCH nych = new PoolNYCH();
		Connection connect = null;
		ResultSet resultSet = null;
		String sqlSelectEjercicio = "SELECT DBO.EJERCICIOS.NOMBRE_EJERCICIO FROM DBO.EJERCICIOS";
		ArrayList<String> lista = new ArrayList<String>();
		try {
			connect = nych.datasource.getConnection();
			Statement st = connect.createStatement();
			resultSet = st.executeQuery(sqlSelectEjercicio);
		} catch (Exception exp) {
			exp.printStackTrace();
			LOG.info("Excepción: " + exp);
			// JOptionPane.showMessageDialog(null, exp, "Error de conexion",
			// JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");
		}
		try {
			while (resultSet.next()) {
				lista.add(resultSet.getString("NOMBRE_EJERCICIO"));
			}
		} catch (Exception exc) {
			exc.printStackTrace();
			LOG.info("Excepción: " + exc);
			// JOptionPane.showMessageDialog(null, exc, "Error de conexion",
			// JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");
		} finally {
			try {
				connect.close();
			} catch (SQLException ep) {
				ep.printStackTrace();
				LOG.info("Excepción: " + ep);
				// JOptionPane.showMessageDialog(null, ep, "Error de desconexion",
				// JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");
			}
		}
		return lista;
	}

	public void eliminarRegistro() {
		PoolNYCH nych = new PoolNYCH();
		Connection con = null;
		int fila = tableDatosEmpleados.getSelectedRow();
		if (fila >= 0) {
			try {
				String valor = tableDatosEmpleados.getValueAt(fila, 0).toString();
				// String sqlDelete="DELETE FROM DBO.EMPLEADOS WHERE ID_EMPLEADO='"+ valor +"'";
				String sqlDelete = "UPDATE dbo.EMPLEADOS set eliminar_logico='" + 0 + "' WHERE clave='" + valor
						+ "'";
				System.out.println(sqlDelete);
				con = nych.datasource.getConnection();
				PreparedStatement pps = con.prepareStatement(sqlDelete);
				pps.executeUpdate();
				JOptionPane.showMessageDialog(null, "Se ha eliminado el registro");

				updateFechaBaja(Integer.parseInt(valor));

			} catch (Exception e) {
				StringWriter errors = new StringWriter();
				e.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: "+ errors );
				//JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
				JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
			} finally {
				try {
					con.close();
				} catch (SQLException ep) {
					StringWriter errors = new StringWriter();
					ep.printStackTrace(new PrintWriter(errors));
					LOG.info("Excepcion: "+ errors );
					//JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
					JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
				}
			}


		} else {
			JOptionPane.showMessageDialog(null, "Registro no seleccionado para eliminar");
		}
	}

	@SuppressWarnings("static-access")
	public void updateFechaBaja(int clave) {
		PoolNYCH nych = new PoolNYCH();
		Connection con = null;
		Date fechaBaja = Calendar.getInstance().getTime();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			String sqlUpdate = "UPDATE dbo.EMPLEADOS set fecha_baja= convert(datetime,'"+ formatter.format(fechaBaja)+"',101)" + " WHERE clave='" + clave + "'";
			System.out.println("update: " + sqlUpdate);
			con = nych.datasource.getConnection();
			PreparedStatement pps = con.prepareStatement(sqlUpdate);
			pps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Se ha eliminado el registro");
		} catch (Exception e) {
			e.printStackTrace();
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: "+ errors );
			//JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
			JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
		} finally {
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
		}
	}

	public void mostrasRegistroExpediente() {
		PoolNYCH nych = new PoolNYCH();
		Connection con = null;
		Statement st;
		ResultSet resultSet = null;
		String sqlSelect = "";
		int fila = tableDatosEmpleadosExp.getSelectedRow();
		if(fila>=0) {
			String valor = tableDatosEmpleadosExp.getValueAt(fila, 0).toString();
			//System.out.println("valor: "  + valor);
			sqlSelect = "SELECT CLAVE,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,CURP,RFC,ID_CLASIFICACION_DISPERSION,FECHA_INGRESO,FECHA_BAJA,REGISTRO_IMSS,SDI,FECHA_ALTA_IMSS,CUENTA_BANCARIA,MONEDERO,"
					+ "DIRECCION,CIUDAD,LOCALIDAD,CODIGO_POSTAL,ESTADO,TELEFONO,ESTADO_CIVIL,EMAIL,CONTACTO_FAMILIAR,DIRECCION_CONTACTO,CELULAR_CONTACTO,EMAIL_CONTACTO,FECHA_NACIMIENTO "
					+ "FROM DBO.EMPLEADOS WHERE clave='"	+ valor + "' order by id_puesto ";
			//System.out.println(sqlSelect);
			try {
				con = nych.datasource.getConnection();
				st = con.createStatement();
				resultSet = st.executeQuery(sqlSelect);
				while (resultSet.next()) {
					// lblfoto.setText(resultSet.getString(1));
					textFieldClave.setText(resultSet.getString(1));
					textFieldNombreExpediente.setText(resultSet.getString(2));
					textFieldApPatExpediente.setText(resultSet.getString(3));
					textFieldApMatExpdiente.setText(resultSet.getString(4));

					if(resultSet.getString(5).equalsIgnoreCase("")) {
						textFieldCurp.setForeground(new Color(255,0,0));
						textFieldCurp.setText("NO HAY INFORMACION");
					}else {
						textFieldCurp.setForeground(new Color(0,0,0));
						textFieldCurp.setText(resultSet.getString(5));
					}

					if(resultSet.getString(6).equalsIgnoreCase("")) {
						textFieldRFC.setForeground(new Color(255,0,0));
						textFieldRFC.setText("NO HAY INFORMACION");
					}else {
						textFieldRFC.setForeground(new Color(0,0,0));
						textFieldRFC.setText(resultSet.getString(6));
					}

					if(resultSet.getString(7).equalsIgnoreCase("")) {
						textFieldClasif.setForeground(new Color(255,0,0));
						textFieldClasif.setText("NO HAY INFORMACION");
					}else {
						textFieldClasif.setForeground(new Color(0,0,0));
						textFieldClasif.setText(resultSet.getString(7));
					}

					textFieldFechaIngreso.setText(resultSet.getString(8));
					textFieldFechaBaja.setText(resultSet.getString(9));

					if(resultSet.getString(10).equalsIgnoreCase("")) {
						textFieldRegImss.setForeground(new Color(255,0,0));
						textFieldRegImss.setText("NO HAY INFORMACION");
					}else {
						textFieldRegImss.setForeground(new Color(0,0,0));
						textFieldRegImss.setText(resultSet.getString(10));
					}

					if(resultSet.getString(11).equalsIgnoreCase("")) {
						textFieldSDI.setForeground(new Color(255,0,0));
						textFieldSDI.setText("NO HAY INFORMACION");
					}else {
						textFieldSDI.setForeground(new Color(0,0,0));
						textFieldSDI.setText(resultSet.getString(11));
					}

					textFieldFechaAltaImss.setText(resultSet.getString(12));

					if(resultSet.getString(13).equalsIgnoreCase("")) {
						textFieldCtaBanco.setForeground(new Color(255,0,0));
						textFieldCtaBanco.setText("NO HAY INFORMACION");
					}else {
						textFieldCtaBanco.setForeground(new Color(0,0,0));
						textFieldCtaBanco.setText(resultSet.getString(13));
					}

					if(resultSet.getString(14).equalsIgnoreCase("")) {
						textFieldCtaMonedero.setForeground(new Color(255,0,0));
						textFieldCtaMonedero.setText("NO HAY INFORMACION");
					}else {
						textFieldCtaMonedero.setForeground(new Color(0,0,0));
						textFieldCtaMonedero.setText(resultSet.getString(14));
					}

					if(resultSet.getString(15).equalsIgnoreCase("")) {
						textFieldDireccion.setForeground(new Color(255,0,0));
						textFieldDireccion.setText("NO HAY INFORMACION");
					}else {
						textFieldDireccion.setForeground(new Color(0,0,0));
						textFieldDireccion.setText(resultSet.getString(15));
					}

					if(resultSet.getString(16).equalsIgnoreCase("")) {
						textFieldCiudad.setForeground(new Color(255,0,0));
						textFieldCiudad.setText("NO HAY INFORMACION");
					}else {
						textFieldCiudad.setForeground(new Color(0,0,0));
						textFieldCiudad.setText(resultSet.getString(16));
					}

					textFieldLocalidad.setText(resultSet.getString(17));

					if(resultSet.getString(18).equalsIgnoreCase("")) {
						textFieldCP.setForeground(new Color(255,0,0));
						textFieldCP.setText("NO HAY INFORMACION");
					}else {
						textFieldCP.setForeground(new Color(0,0,0));
						textFieldCP.setText(resultSet.getString(18));
					}

					if(resultSet.getString(19).equalsIgnoreCase("")) {
						textFieldEstado.setForeground(new Color(255,0,0));
						textFieldEstado.setText("NO HAY INFORMACION");
					}else {
						textFieldEstado.setForeground(new Color(0,0,0));
						textFieldEstado.setText(resultSet.getString(19));
					}

					if(resultSet.getString(20).equalsIgnoreCase("")) {
						textFieldTel.setForeground(new Color(255,0,0));
						textFieldTel.setText("NO HAY INFORMACION");
					}else {
						textFieldTel.setForeground(new Color(0,0,0));
						textFieldTel.setText(resultSet.getString(20));
					}

					if(resultSet.getString(21).equalsIgnoreCase("")) {
						textFieldEdoCivil.setForeground(new Color(255,0,0));
						textFieldEdoCivil.setText("NO HAY INFORMACION");
					}else {
						textFieldEdoCivil.setForeground(new Color(0,0,0));
						textFieldEdoCivil.setText(resultSet.getString(21));
					}


					if(resultSet.getString(22).equalsIgnoreCase("")) {
						textFieldEmail.setForeground(new Color(255,0,0));
						textFieldEmail.setText("NO HAY INFORMACION");
						System.out.println("emailvacio: "+resultSet.getString(22));
					}else {
						textFieldEmail.setForeground(new Color(0,0,0));
						textFieldEmail.setText(resultSet.getString(22));
						System.out.println("email: "+resultSet.getString(22));
					}

					if(resultSet.getString(23).equalsIgnoreCase("")) {
						textFieldContactoFamiliar.setForeground(new Color(255,0,0));
						textFieldContactoFamiliar.setText("NO HAY INFORMACION");
					}else {
						textFieldContactoFamiliar.setForeground(new Color(0,0,0));
						textFieldContactoFamiliar.setText(resultSet.getString(23));
					}

					if(resultSet.getString(24).equalsIgnoreCase("")) {
						textFieldDireccionContacto.setForeground(new Color(255,0,0));
						textFieldDireccionContacto.setText("NO HAY INFORMACION");
					}else {
						textFieldDireccionContacto.setForeground(new Color(0,0,0));
						textFieldDireccionContacto.setText(resultSet.getString(24));
					}

					if(resultSet.getString(25).equalsIgnoreCase("")) {
						textFieldCelularContacto.setForeground(new Color(255,0,0));
						textFieldCelularContacto.setText("NO HAY INFORMACION");
					}else {
						textFieldCelularContacto.setForeground(new Color(0,0,0));
						textFieldCelularContacto.setText(resultSet.getString(25));
					}

					if(resultSet.getString(26).equalsIgnoreCase("")) {
						textFieldEmailContacto.setForeground(new Color(255,0,0));
						textFieldEmailContacto.setText("NO HAY INFORMACION");
					}else {
						textFieldEmailContacto.setForeground(new Color(0,0,0));
						textFieldEmailContacto.setText(resultSet.getString(26));
					}

					textFieldFechaNAC.setText(resultSet.getString(27));
				}
				// JOptionPane.showMessageDialog(null, "Registro Encontrado");
			} catch (Exception sl) {
				sl.printStackTrace();
				StringWriter errors = new StringWriter();
				sl.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: "+ errors );
				//JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
				JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
			} finally {
				try {
					con.close();
				} catch (Exception ep) {
					ep.printStackTrace();
					StringWriter errors = new StringWriter();
					ep.printStackTrace(new PrintWriter(errors));
					LOG.info("Excepcion: "+ errors );
					//JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
					JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
				}
			}
		}else {
			JOptionPane.showMessageDialog(null, "Registro no Seleccionado");
		}


	}

	public void windowOpened(ActionEvent e) {
		final long SLEEP_TIME = 15 * 1000;
		SwingWorker<Void, Void> mySwingWorker = new SwingWorker<Void, Void>() {
			protected Void doInBackground() throws Exception {

				Thread.sleep(SLEEP_TIME);
				return null;
			}
		};

		Window win = SwingUtilities.getWindowAncestor((AbstractButton) e.getSource());
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

	public void windowOpenedNuevoEmpleado(ActionEvent e) {
		final long SLEEP_TIME = 1 * 1000;
		SwingWorker<Void, Void> mySwingWorker = new SwingWorker<Void, Void>() {
			protected Void doInBackground() throws Exception {


				ifne.comboBoxGenero.removeAllItems();
//				ifne.comboBoxPuesto.removeAllItems();
				ifne.comboBoxEdoCivil.removeAllItems();
//				ifne.comboBoxUnidadResp.removeAllItems();
				ifne.comboBoxLocalidad.removeAllItems();
				//ifne.comboBoxEjercicio.removeAllItems();
				ifne.comboBoxClasificacionDispersion.removeAllItems();
				ifne.comboBoxRegimen.removeAllItems();
				ifne.comboBoxTipoEmpleado.removeAllItems();
				ifne.comboBoxRiesgo.removeAllItems();
				ifne.comboBoxTipoContrato.removeAllItems();
				ifne.comboBoxCalsificacionEmpleado.removeAllItems();
				ifne.comboBoxBanco.removeAllItems();


				ArrayList<String> listaGenero = new ArrayList<String>();
				listaGenero = selectComboGenero();
				ifne.comboBoxGenero.addItem("Seleccione Uno");
				for (int i = 0; i < listaGenero.size(); i++) {
					ifne.comboBoxGenero.addItem(listaGenero.get(i));
				}

//				ArrayList<String> listaPuestos = new ArrayList<String>();
//				listaPuestos = selectComboPuesto();
//				ifne.comboBoxPuesto.addItem("Seleccione Uno");
//				for (int i = 0; i < listaPuestos.size(); i++) {
//					ifne.comboBoxPuesto.addItem(listaPuestos.get(i));
//				}

				ArrayList<String> listaEdoCivil = new ArrayList<String>();
				listaEdoCivil = selectComboEdoCivil();
				ifne.comboBoxEdoCivil.addItem("Seleccione Uno");
				for (int i = 0; i < listaEdoCivil.size(); i++) {
					ifne.comboBoxEdoCivil.addItem(listaEdoCivil.get(i));
				}

//				ArrayList<String> listaDependencia = new ArrayList<String>();
//				listaDependencia = selectComboDependencia();
//				ifne.comboBoxUnidadResp.addItem("Seleccione Una");
//				for (int i = 0; i < listaDependencia.size(); i++) {
//					ifne.comboBoxUnidadResp.addItem(listaDependencia.get(i));
//				}

				ArrayList<String> listaLocalidad = new ArrayList<String>();
				listaLocalidad = buscarLocalidad();
				ifne.comboBoxLocalidad.addItem("Seleccione Una");
				for (int i = 0; i < listaLocalidad.size(); i++) {
					ifne.comboBoxLocalidad.addItem(listaLocalidad.get(i));
				}

				//				ArrayList<String> listaEjercicio = new ArrayList<String>();
				//				listaEjercicio = buscarEjercicio();
				//				ifne.comboBoxEjercicio.addItem("Seleccione Una");
				//				for (int i = 0; i < listaEjercicio.size(); i++) {
				//					ifne.comboBoxEjercicio.addItem(listaEjercicio.get(i));
				//				}

				ArrayList<String> listaClasificacionDispersion = new ArrayList<String>();
				listaClasificacionDispersion = buscarClasificacionDispersion();
				ifne.comboBoxClasificacionDispersion.addItem("Seleccione Una");
				for (int i = 0; i < listaClasificacionDispersion.size(); i++) {
					ifne.comboBoxClasificacionDispersion.addItem(listaClasificacionDispersion.get(i));
				}

				ArrayList<String> listaRegimen = new ArrayList<String>();
				listaRegimen = buscarRegimen();
				ifne.comboBoxRegimen.addItem("Seleccione Una");
				for (int i = 0; i < listaRegimen.size(); i++) {
					ifne.comboBoxRegimen.addItem(listaRegimen.get(i));
				}

				ArrayList<String> listaTipoEmpleado = new ArrayList<String>();
				listaTipoEmpleado = buscarTipoEmpleado();
				ifne.comboBoxTipoEmpleado.addItem("Seleccione Una");
				for (int i = 0; i < listaTipoEmpleado.size(); i++) {
					ifne.comboBoxTipoEmpleado.addItem(listaTipoEmpleado.get(i));
				}

				ArrayList<String> listaTipoJornada = new ArrayList<String>();
				listaTipoJornada = buscarTipoJornada();
				ifne.comboBoxTipoJornada.addItem("Seleccione Una");
				for (int i = 0; i < listaTipoJornada.size(); i++) {
					ifne.comboBoxTipoJornada.addItem(listaTipoJornada.get(i));
				}

				ArrayList<String> listaRiesgo = new ArrayList<String>();
				listaRiesgo = buscarRiesgo();
				ifne.comboBoxRiesgo.addItem("Seleccione Una");
				for (int i = 0; i < listaRiesgo.size(); i++) {
					ifne.comboBoxRiesgo.addItem(listaRiesgo.get(i));
				}

				ArrayList<String> listaTipoContrato = new ArrayList<String>();
				listaTipoContrato = buscarTipoContrato();
				ifne.comboBoxTipoContrato.addItem("Seleccione Una");
				for (int i = 0; i < listaTipoContrato.size(); i++) {
					ifne.comboBoxTipoContrato.addItem(listaTipoContrato.get(i));
				}

				ArrayList<String> listaClasifEmpleado = new ArrayList<String>();
				listaClasifEmpleado = buscarClasifEmpleado();
				ifne.comboBoxCalsificacionEmpleado.addItem("Seleccione Una");
				for (int i = 0; i < listaClasifEmpleado.size(); i++) {
					ifne.comboBoxCalsificacionEmpleado.addItem(listaClasifEmpleado.get(i));
				}

				ArrayList<String> listaBancos = new ArrayList<String>();
				listaBancos = buscarBancos();
				ifne.comboBoxBanco.addItem("Seleccione Una");
				for (int i = 0; i < listaBancos.size(); i++) {
					ifne.comboBoxBanco.addItem(listaBancos.get(i));
				}

				Thread.sleep(SLEEP_TIME);
				return null;
			}
		};

		Window win = SwingUtilities.getWindowAncestor((AbstractButton) e.getSource());
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



	// metodo verdadero
	public void mostrarDatosEmpleadoInactivos(String valor) {
		//System.out.println("mostrarDatosEmpleadoInactivos");
		DefaultTableModel modelo = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				// return super.isCellEditable(row, column);
				if (column == 11) {
					return true;
				} else {
					return false;
				}
			}
		};
		modelo.addColumn("CLAVE");
		modelo.addColumn("NOMBRE");
		modelo.addColumn("APELLIDO PATERNO");
		modelo.addColumn("APELLIDO MATERNO");
		modelo.addColumn("CURP");
		modelo.addColumn("RFC");
		modelo.addColumn("REGISTRO IMSS");
		modelo.addColumn("PUESTO");
		modelo.addColumn("SALARIO");
		modelo.addColumn("UNIDAD RESPONSABLE");
		modelo.addColumn("TIPO NOMINA");

		tableDatosEmpleadosInactivos.setModel(modelo);
		tableDatosEmpleadosInactivos.setBackground(Color.WHITE);

		JTableHeader th = new JTableHeader();
		Color colorSilverLight = new Color(176, 196, 222);
		Color colorNegro = new Color(0, 0, 0);
		th = tableDatosEmpleadosInactivos.getTableHeader();
		Font fuente = new Font("Amazone", Font.BOLD, 14);
		th.setFont(fuente);
		th.setBackground(colorSilverLight);
		th.setForeground(colorNegro);

		TableColumnModel columnModel = tableDatosEmpleadosInactivos.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(100);
		columnModel.getColumn(1).setPreferredWidth(200);
		columnModel.getColumn(2).setPreferredWidth(200);
		columnModel.getColumn(3).setPreferredWidth(200);
		columnModel.getColumn(4).setPreferredWidth(300);
		columnModel.getColumn(5).setPreferredWidth(200);
		columnModel.getColumn(6).setPreferredWidth(300);
		columnModel.getColumn(7).setPreferredWidth(200);
		columnModel.getColumn(8).setPreferredWidth(200);
		columnModel.getColumn(9).setPreferredWidth(350);
		columnModel.getColumn(10).setPreferredWidth(700);
		// columnModel.getColumn(11).setPreferredWidth(200);

		String sqlSelect = "";
		String atributo = "id_ejercicios";
		//		String sqlDependencia = "";
		//		String sqlEjercicio = "";

		//sqlSelect = "select CLAVE,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,CURP,RFC,REGISTRO_IMSS from dbo.empleados WHERE ELIMINAR_LOGICO='"+ 0 + "' order by id_puesto";
		//		sqlpuesto = "SELECT dbo.puestos.nombre_puesto,dbo.puestos.salario FROM dbo.EMPLEADOS left JOIN DBO.PUESTOS ON DBO.EMPLEADOS.ID_PUESTO = DBO.PUESTOS.no_puesto order by dbo.PUESTOS.no_puesto";
		//		sqlDependencia = "SELECT DBO.DEPENDENCIAS.UNIDAD_RESPONSABLE FROM dbo.EMPLEADOS left JOIN DBO.DEPENDENCIAS ON DBO.EMPLEADOS.ID_UNIDAD = DBO.DEPENDENCIAS.ID_UNIDAD order by dbo.empleados.id_puesto";
		//		sqlEjercicio = "SELECT DBO.EJERCICIOS.NOMBRE_EJERCICIO FROM dbo.EMPLEADOS left JOIN DBO.EJERCICIOS ON DBO.EMPLEADOS.ID_EJERCICIOS = DBO.EJERCICIOS.ID_EJERCICIOS order by dbo.empleados.id_puesto";
		sqlSelect = "SELECT DBO.EMPLEADOS.CLAVE,DBO.EMPLEADOS.NOMBRE,DBO.EMPLEADOS.APELLIDO_PATERNO,DBO.EMPLEADOS.APELLIDO_MATERNO,DBO.EMPLEADOS.CURP,DBO.EMPLEADOS.RFC,\r\n"
				+" DBO.EMPLEADOS.REGISTRO_IMSS,\r\n"
				+ "dbo.puestos.nombre_puesto,dbo.puestos.salario,DBO.DEPENDENCIAS.UNIDAD_RESPONSABLE,DBO.EJERCICIOS.NOMBRE_EJERCICIO \r\n"
				+ "from empleados\r\n"
				+ "left JOIN DBO.PUESTOS ON DBO.EMPLEADOS.ID_PUESTO = DBO.PUESTOS.no_puesto\r\n"
				+ "LEFT JOIN DBO.DEPENDENCIAS ON DBO.EMPLEADOS.ID_UNIDAD = DBO.DEPENDENCIAS.ID_UNIDAD\r\n"
				+ "LEFT JOIN DBO.EJERCICIOS ON DBO.EMPLEADOS.ID_EJERCICIOS = DBO.EJERCICIOS.ID_EJERCICIOS\r\n"
				+ "WHERE DBO.EMPLEADOS." + atributo + " = '" + valor + "' and ELIMINAR_LOGICO='0' \r\n";
		//System.out.println("sqlSelect: "+sqlSelect);
		Object datos[] = new String[12];
		PoolNYCH nych = new PoolNYCH();
		Connection con = null;
		Statement st;
		ResultSet resultSet;
		//		Connection conPuesto = null;
		//		Connection conDepen = null;
		//		Connection conEjercicio = null;
		//		Statement stPuesto;
		//		Statement stDepen;
		//		Statement stEjercicio;
		//		ResultSet resPuesto;
		//		ResultSet resDependencia;
		//		ResultSet resEjercicio;
		try {
			con = nych.datasource.getConnection();
			//			conPuesto = nych.datasource.getConnection();
			//			conDepen = nych.datasource.getConnection();
			//			conEjercicio = nych.datasource.getConnection();
			st = con.createStatement();
			//			stPuesto = conPuesto.createStatement();
			//			stDepen = conDepen.createStatement();
			//			stEjercicio = conEjercicio.createStatement();
			resultSet = st.executeQuery(sqlSelect);
			//			resPuesto = stPuesto.executeQuery(sqlpuesto);
			//			resDependencia = stDepen.executeQuery(sqlDependencia);
			//			resEjercicio = stEjercicio.executeQuery(sqlEjercicio);
			while (resultSet.next()) {//&& resPuesto.next() && resDependencia.next() && resEjercicio.next()
				datos[0] = resultSet.getString(1);
				datos[1] = resultSet.getString(2);
				datos[2] = resultSet.getString(3);
				datos[3] = resultSet.getString(4);
				datos[4] = resultSet.getString(5);
				datos[5] = resultSet.getString(6);
				datos[6] = resultSet.getString(7);
				datos[7] = resultSet.getString(8);
				datos[8] = resultSet.getString(9);
				datos[9] = resultSet.getString(10);
				datos[10] = resultSet.getString(11);
				modelo.addRow(datos);
			}
			rowSortere = new TableRowSorter(modelo);
			tableDatosEmpleadosInactivos.setRowSorter(rowSortere);
			tableDatosEmpleadosInactivos.setModel(modelo);
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
				StringWriter errors = new StringWriter();
				ep.printStackTrace(new PrintWriter(errors));
				// JOptionPane.showMessageDialog(null, el, "Error de conexion",
				// JOptionPane.ERROR_M
			}
		}
	}//fin del metodo


	public static ArrayList<String> buscarClasificacionDispersion() {
		PoolNYCH nych = new PoolNYCH();
		Connection connect = null;
		ResultSet resultSet = null;
		String sqlSelectEjercicio = "SELECT * FROM DBO.CLASIFICACION_DISPERSION";
		ArrayList<String> lista = new ArrayList<String>();
		try {
			connect = nych.datasource.getConnection();
			Statement st = connect.createStatement();
			resultSet = st.executeQuery(sqlSelectEjercicio);
			while (resultSet.next()) {
				lista.add(resultSet.getString("clave"));
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

	public static ArrayList<String> buscarRegimen() {
		PoolNYCH nych = new PoolNYCH();
		Connection connect = null;
		ResultSet resultSet = null;
		String sqlSelectEjercicio = "SELECT * FROM DBO.REGIMEN_CONTRATACION";
		ArrayList<String> lista = new ArrayList<String>();
		try {
			connect = nych.datasource.getConnection();
			Statement st = connect.createStatement();
			resultSet = st.executeQuery(sqlSelectEjercicio);
			while (resultSet.next()) {
				lista.add(resultSet.getString("DESCRIPCION"));
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

	public static ArrayList<String> buscarTipoEmpleado() {
		PoolNYCH nych = new PoolNYCH();
		Connection connect = null;
		ResultSet resultSet = null;
		String sqlSelectEjercicio = "SELECT * FROM DBO.TIPO_EMPLEADO";
		ArrayList<String> lista = new ArrayList<String>();
		try {
			connect = nych.datasource.getConnection();
			Statement st = connect.createStatement();
			resultSet = st.executeQuery(sqlSelectEjercicio);
			while (resultSet.next()) {
				lista.add(resultSet.getString("TIPO_EMPLEADO"));
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

	public static ArrayList<String> buscarTipoJornada() {
		PoolNYCH nych = new PoolNYCH();
		Connection connect = null;
		ResultSet resultSet = null;
		String sqlSelectEjercicio = "SELECT * FROM DBO.TIPO_JORNADA";
		ArrayList<String> lista = new ArrayList<String>();
		try {
			connect = nych.datasource.getConnection();
			Statement st = connect.createStatement();
			resultSet = st.executeQuery(sqlSelectEjercicio);
			while (resultSet.next()) {
				lista.add(resultSet.getString("TIPO_JORNADA"));
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

	public static ArrayList<String> buscarRiesgo() {
		PoolNYCH nych = new PoolNYCH();
		Connection connect = null;
		ResultSet resultSet = null;
		String sqlSelectEjercicio = "SELECT * FROM DBO.RIESGO_PUESTO";
		ArrayList<String> lista = new ArrayList<String>();
		try {
			connect = nych.datasource.getConnection();
			Statement st = connect.createStatement();
			resultSet = st.executeQuery(sqlSelectEjercicio);
			while (resultSet.next()) {
				lista.add(resultSet.getString("DESCRIPCION"));
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

	public static ArrayList<String> buscarTipoContrato() {
		PoolNYCH nych = new PoolNYCH();
		Connection connect = null;
		ResultSet resultSet = null;
		String sqlSelectEjercicio = "SELECT * FROM DBO.TIPO_CONTRATO";
		ArrayList<String> lista = new ArrayList<String>();
		try {
			connect = nych.datasource.getConnection();
			Statement st = connect.createStatement();
			resultSet = st.executeQuery(sqlSelectEjercicio);
			while (resultSet.next()) {
				lista.add(resultSet.getString("CONTRATO"));
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

	public static ArrayList<String> buscarClasifEmpleado() {
		PoolNYCH nych = new PoolNYCH();
		Connection connect = null;
		ResultSet resultSet = null;
		String sqlSelectEjercicio = "SELECT * FROM DBO.TIPO_CLASIFICACION_EMPLEADO";
		ArrayList<String> lista = new ArrayList<String>();
		try {
			connect = nych.datasource.getConnection();
			Statement st = connect.createStatement();
			resultSet = st.executeQuery(sqlSelectEjercicio);
			while (resultSet.next()) {
				lista.add(resultSet.getString("CLASIFICACION_EMPLEADO"));
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

	public static ArrayList<String> buscarBancos() {
		PoolNYCH nych = new PoolNYCH();
		Connection connect = null;
		ResultSet resultSet = null;
		String sqlSelectEjercicio = "SELECT * FROM DBO.CATALOGOS_BANCOS";
		ArrayList<String> lista = new ArrayList<String>();
		try {
			connect = nych.datasource.getConnection();
			Statement st = connect.createStatement();
			resultSet = st.executeQuery(sqlSelectEjercicio);
			while (resultSet.next()) {
				lista.add(resultSet.getString("NOMBRE_CORTO"));
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

	public static ArrayList<String> selectComboGenero() {
		PoolNYCH nych = new PoolNYCH();
		Connection connect = null;
		ResultSet resultSet = null;
		String sqlSelectEjercicio = "SELECT * FROM DBO.GENERO";
		ArrayList<String> lista = new ArrayList<String>();
		try {
			connect = nych.datasource.getConnection();
			Statement st = connect.createStatement();
			resultSet = st.executeQuery(sqlSelectEjercicio);
			while (resultSet.next()) {
				lista.add(resultSet.getString("genero"));
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


	//metodo para el tiempo de busqueda
	//Este es el evento que se ejecuta cuando un JFrame se carga
	public  void windowOpenedActualizar(ActionEvent e){
		final long SLEEP_TIME = 1 * 1000;
		SwingWorker<Void, Void> mySwingWorker = new SwingWorker<Void, Void>(){
			protected Void doInBackground() throws Exception {

				redireccionarActualizarRegistro();


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



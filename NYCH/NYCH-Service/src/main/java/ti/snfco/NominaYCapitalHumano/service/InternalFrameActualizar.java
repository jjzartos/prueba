package ti.snfco.NominaYCapitalHumano.service;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import org.slf4j.LoggerFactory;

import com.toedter.calendar.JDateChooser;
import javax.swing.SwingConstants;
import java.awt.SystemColor;

public class InternalFrameActualizar extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(InternalFrameActualizar.class);
	InternalFrameEmpleado internalFrameEmpleado = new InternalFrameEmpleado();


	public JTextField textFieldIdEmpleado = new JTextField();
	public JTextField textFieldActualizarNombre;
	public JTextField textFieldActualizarPat;
	public JTextField textFieldActualizarMat;
	public JTextField textFieldActualizarCurp;
	public JTextField textFieldActualizarRfc;
	public JTextField textFieldActualizarTel;
	public JTextField textFieldActualizarEmail;
	public JTextField textFieldActualizarContacto = new JTextField();
	public JTextField textFieldActualizarEmailContacto = new JTextField();
	public JTextField textFieldActualizarCelular = new JTextField();
	public JTextField textFieldActualizarDireccionContacto = new JTextField();
	public JTextField textFieldActualizarRegistroImss = new JTextField();
	public JTextField textFieldActualizarDireccion = new JTextField();
	public JTextField textFieldActualizarCiudad = new JTextField();
	public JTextField textFieldActualizarEstado = new JTextField();
	public JTextField textFieldActualizarCodigoPostal = new JTextField();
	public JDateChooser ActualizarFechaAltaImss = new JDateChooser();
	public JPanel panelDomicilioActulizar = new JPanel();
	public JPanel panelActualizarDatosEmpleado = new JPanel();
	public JPanel panelActualizarDatosBancarios = new JPanel();
	public JLabel lblActualizarNombre = new JLabel("Nombre(s):");
	public JLabel lblActualizarPat = new JLabel("Apellido Paterno:");
	public JLabel labelActualizarMat = new JLabel("Apellido Materno:");
	public JLabel lblActualizarFechaNac = new JLabel("Fecha de Nacimiento:");
	public JLabel lblActualizarCurp = new JLabel("Curp:");
	public JLabel lblActualizarRfc = new JLabel("RFC:");
	public JLabel lblActualizarTel = new JLabel("Telefono:");
	public JLabel lblActualizarGenero = new JLabel("Genero:");
	public JLabel lblActualizarEdoCivil = new JLabel("Estado Civil:");
	public JLabel lblActualizarEmail = new JLabel("Email:");
	public JLabel lblPuesto = new JLabel("Puesto:");
	public JLabel lblFechaAltaIngreso = new JLabel("Fecha Alta Ingreso:");
	public JLabel lblFechaBajaIngreso = new JLabel("Fecha Baja Ingreso:");
	public JLabel lblDependencia = new JLabel("U.Responsable:");
	public JLabel lblRegistroImss = new JLabel("Registro Imss:");
	public JLabel lblFechaAltaImss = new JLabel("Fecha Alta Imss:");
	public JLabel lblFechaBajaImss = new JLabel("Fecha Baja Imss:");
	public JPanel panelActualizarLocalizacion = new JPanel();
	public JLabel lblContacto = new JLabel("Contacto:");
	public JLabel lblEmail = new JLabel("Email:");
	public JLabel lblCelular = new JLabel("Celular:");
	public JLabel lblDireccin = new JLabel("Dirección:");
	public JLabel lblIdEmpleado = new JLabel("Clave:");
	public JButton btnActualizar = new JButton("Aceptar");
	public JButton btnActualizarCancelar = new JButton("Cancelar");
	public String atributo = "id";
	public JTextField textFieldSDIActualizar = new JTextField();
	public JTextField textFieldNCIActualizar = new JTextField();
	public JTextField textFieldFIActualizar = new JTextField();
	public JTextField textFieldUMAActualizr = new JTextField();
	public JLabel lblNewLabel = new JLabel("Salario Diario Integrado:");
	public JLabel lblNewLabel_1 = new JLabel("Numero Credito Infonavit:");
	public JLabel lblNewLabel_2 = new JLabel("Factor Infonavit:");
	public JLabel lblNewLabel_3 = new JLabel("U.M.A.:");
	public JComboBox comboBoxActualizarGenero = new JComboBox();
	public JComboBox comboBoxActualizarEdoCivil = new JComboBox();
	public JComboBox comboBoxActualizarDependencia = new JComboBox();
	public JComboBox comboBoxActLocal = new JComboBox();
	Color colorSilverLight=new Color(176, 196, 222);
	JComboBox comboBoxActTipoNomina = new JComboBox();
	JComboBox comboBoxPuesto = new JComboBox();
	JComboBox comboBoxClasificacion = new JComboBox();
	JComboBox comboBoxRegimen = new JComboBox();
	JComboBox comboBoxTipoEmpleado = new JComboBox();
	JComboBox comboBoxTipoJornada = new JComboBox();
	JComboBox comboBoxRiesgo = new JComboBox();
	JComboBox comboBoxTipoContrato = new JComboBox();
	JComboBox comboBoxClasificacionEmpleado = new JComboBox();
	JLabel lblCuentaBancaria = new JLabel("Cuenta Bancaria:");
	JTextField textFieldCtaBancaria = new JTextField();
	JLabel lblMonedero = new JLabel("Monedero:");
	JTextField textFieldMonedero = new JTextField();
	JLabel lblBanco = new JLabel("Banco:");
	JComboBox comboBoxBanco = new JComboBox();
	JDateChooser dateChooserFechaNacimiento = new JDateChooser();
	JDateChooser dateChooserFechaAltaIngreso = new JDateChooser();
	JDateChooser dateChooserFechaBajaIngreso = new JDateChooser();
	JDateChooser dateChooserFechaAltaImss = new JDateChooser();
	JDateChooser dateChooserFechaBajaImss = new JDateChooser();



	/**
	 * Create the frame.
	 */
	public InternalFrameActualizar() {
		textFieldActualizarDireccion.setForeground(Color.BLACK);
		textFieldActualizarDireccion.setBackground(Color.WHITE);
		textFieldActualizarDireccion.setBounds(138, 11, 293, 25);
		textFieldActualizarDireccion.setColumns(10);
//		setBounds(100, 100, 1971, 1051);
		setBounds(9, 15, 1520, 688);
		setTitle("Actualizar Empleado");
		getContentPane().setLayout(null);

		JPanel panelActualizar = new JPanel();
		panelActualizar.setBackground(SystemColor.controlHighlight);
		panelActualizar.setBorder(new TitledBorder(null, "Actualizar Registro", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(147,140,147)));
		panelActualizar.setBounds(0, 0, 1554, 711);
		getContentPane().add(panelActualizar);
		panelActualizar.setLayout(null);

		JPanel panelActualizarDatosPersonales = new JPanel();
		panelActualizarDatosPersonales.setLayout(null);
		panelActualizarDatosPersonales.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos de la persona", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(249,246,212)));
		panelActualizarDatosPersonales.setBackground(SystemColor.inactiveCaption);
		panelActualizarDatosPersonales.setBounds(10, 15, 438, 344);
		panelActualizar.add(panelActualizarDatosPersonales);
		lblActualizarNombre.setHorizontalAlignment(SwingConstants.RIGHT);

		lblActualizarNombre.setForeground(Color.BLACK);
		lblActualizarNombre.setBounds(53, 43, 75, 14);
		panelActualizarDatosPersonales.add(lblActualizarNombre);

		textFieldActualizarNombre = new JTextField();
		textFieldActualizarNombre.setForeground(Color.BLACK);
		textFieldActualizarNombre.setColumns(10);
		textFieldActualizarNombre.setBackground(Color.WHITE);
		textFieldActualizarNombre.setBounds(138, 38, 293, 25);
		panelActualizarDatosPersonales.add(textFieldActualizarNombre);
		lblActualizarPat.setHorizontalAlignment(SwingConstants.RIGHT);


		lblActualizarPat.setForeground(Color.BLACK);
		lblActualizarPat.setBounds(29, 74, 99, 14);
		panelActualizarDatosPersonales.add(lblActualizarPat);

		textFieldActualizarPat = new JTextField();
		textFieldActualizarPat.setForeground(Color.BLACK);
		textFieldActualizarPat.setColumns(10);
		textFieldActualizarPat.setBackground(Color.WHITE);
		textFieldActualizarPat.setBounds(138, 68, 293, 25);
		panelActualizarDatosPersonales.add(textFieldActualizarPat);

		textFieldActualizarMat = new JTextField();
		textFieldActualizarMat.setForeground(Color.BLACK);
		textFieldActualizarMat.setColumns(10);
		textFieldActualizarMat.setBackground(Color.WHITE);
		textFieldActualizarMat.setBounds(138, 98, 293, 25);
		panelActualizarDatosPersonales.add(textFieldActualizarMat);

		textFieldActualizarCurp = new JTextField();
		textFieldActualizarCurp.setForeground(Color.BLACK);
		textFieldActualizarCurp.setColumns(10);
		textFieldActualizarCurp.setBackground(Color.WHITE);
		textFieldActualizarCurp.setBounds(138, 158, 293, 25);
		panelActualizarDatosPersonales.add(textFieldActualizarCurp);

		textFieldActualizarRfc = new JTextField();
		textFieldActualizarRfc.setForeground(Color.BLACK);
		textFieldActualizarRfc.setColumns(10);
		textFieldActualizarRfc.setBackground(Color.WHITE);
		textFieldActualizarRfc.setBounds(138, 188, 293, 25);
		panelActualizarDatosPersonales.add(textFieldActualizarRfc);

		textFieldActualizarTel = new JTextField();
		textFieldActualizarTel.setForeground(Color.BLACK);
		textFieldActualizarTel.setColumns(10);
		textFieldActualizarTel.setBackground(Color.WHITE);
		textFieldActualizarTel.setBounds(138, 218, 293, 25);
		panelActualizarDatosPersonales.add(textFieldActualizarTel);

		textFieldActualizarEmail = new JTextField();
		textFieldActualizarEmail.setForeground(Color.BLACK);
		textFieldActualizarEmail.setColumns(10);
		textFieldActualizarEmail.setBackground(Color.WHITE);
		textFieldActualizarEmail.setBounds(138, 308, 293, 30);
		panelActualizarDatosPersonales.add(textFieldActualizarEmail);
		labelActualizarMat.setHorizontalAlignment(SwingConstants.RIGHT);


		labelActualizarMat.setForeground(Color.BLACK);
		labelActualizarMat.setBounds(29, 103, 99, 14);
		panelActualizarDatosPersonales.add(labelActualizarMat);
		lblActualizarFechaNac.setHorizontalAlignment(SwingConstants.RIGHT);


		lblActualizarFechaNac.setForeground(Color.BLACK);
		lblActualizarFechaNac.setBounds(0, 135, 128, 14);
		panelActualizarDatosPersonales.add(lblActualizarFechaNac);
		lblActualizarCurp.setHorizontalAlignment(SwingConstants.RIGHT);


		lblActualizarCurp.setForeground(Color.BLACK);
		lblActualizarCurp.setBounds(82, 163, 46, 14);
		panelActualizarDatosPersonales.add(lblActualizarCurp);
		lblActualizarRfc.setHorizontalAlignment(SwingConstants.RIGHT);


		lblActualizarRfc.setForeground(Color.BLACK);
		lblActualizarRfc.setBounds(29, 193, 99, 14);
		panelActualizarDatosPersonales.add(lblActualizarRfc);
		lblActualizarTel.setHorizontalAlignment(SwingConstants.RIGHT);


		lblActualizarTel.setForeground(Color.BLACK);
		lblActualizarTel.setBounds(29, 223, 99, 14);
		panelActualizarDatosPersonales.add(lblActualizarTel);
		lblActualizarGenero.setHorizontalAlignment(SwingConstants.RIGHT);


		lblActualizarGenero.setForeground(Color.BLACK);
		lblActualizarGenero.setBounds(10, 254, 118, 14);
		panelActualizarDatosPersonales.add(lblActualizarGenero);
		lblActualizarEdoCivil.setHorizontalAlignment(SwingConstants.RIGHT);


		lblActualizarEdoCivil.setForeground(Color.BLACK);
		lblActualizarEdoCivil.setBounds(29, 283, 99, 14);
		panelActualizarDatosPersonales.add(lblActualizarEdoCivil);
		lblActualizarEmail.setHorizontalAlignment(SwingConstants.RIGHT);


		lblActualizarEmail.setForeground(Color.BLACK);
		lblActualizarEmail.setBounds(10, 310, 118, 20);
		panelActualizarDatosPersonales.add(lblActualizarEmail);
		lblIdEmpleado.setHorizontalAlignment(SwingConstants.RIGHT);

		lblIdEmpleado.setForeground(Color.BLACK);
		lblIdEmpleado.setBounds(53, 17, 75, 14);
		panelActualizarDatosPersonales.add(lblIdEmpleado);

		textFieldIdEmpleado.setForeground(Color.BLACK);
		textFieldIdEmpleado.setColumns(10);
		textFieldIdEmpleado.setBackground(colorSilverLight);
		textFieldIdEmpleado.setBounds(138, 12, 46, 25);
		textFieldIdEmpleado.setEnabled(false);
		panelActualizarDatosPersonales.add(textFieldIdEmpleado);
		comboBoxActualizarGenero.setForeground(Color.BLACK);


		comboBoxActualizarGenero.setBounds(138, 249, 293, 25);
		panelActualizarDatosPersonales.add(comboBoxActualizarGenero);
		comboBoxActualizarEdoCivil.setForeground(Color.BLACK);


		comboBoxActualizarEdoCivil.setBounds(138, 279, 293, 25);
		panelActualizarDatosPersonales.add(comboBoxActualizarEdoCivil);
		
		dateChooserFechaNacimiento.setBounds(138, 127, 293, 25);
		panelActualizarDatosPersonales.add(dateChooserFechaNacimiento);

		panelDomicilioActulizar.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Domicilio", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(249,246,212)));
		panelDomicilioActulizar.setBackground(SystemColor.inactiveCaption);
		panelDomicilioActulizar.setBounds(10, 372, 438, 186);

		panelActualizar.add(panelDomicilioActulizar);
		panelDomicilioActulizar.setLayout(null);

		JLabel lblActualizarDomicilio = new JLabel("Dirección:");
		lblActualizarDomicilio.setHorizontalAlignment(SwingConstants.RIGHT);
		lblActualizarDomicilio.setBackground(new Color(255, 160, 122));
		lblActualizarDomicilio.setForeground(Color.BLACK);
		lblActualizarDomicilio.setBounds(58, 16, 75, 14);
		panelDomicilioActulizar.add(lblActualizarDomicilio);

		JLabel lblCiudad = new JLabel("Ciudad:");
		lblCiudad.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCiudad.setForeground(Color.BLACK);
		lblCiudad.setBounds(58, 46, 75, 14);
		panelDomicilioActulizar.add(lblCiudad);

		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEstado.setForeground(Color.BLACK);
		lblEstado.setBounds(58, 81, 75, 14);
		panelDomicilioActulizar.add(lblEstado);

		JLabel lblCodigoPostal = new JLabel("Codigo Postal:");
		lblCodigoPostal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCodigoPostal.setForeground(Color.BLACK);
		lblCodigoPostal.setBounds(10, 106, 123, 14);
		panelDomicilioActulizar.add(lblCodigoPostal);

		JLabel lblColoniaOComunidad = new JLabel("Colonia o Comunidad:");
		lblColoniaOComunidad.setHorizontalAlignment(SwingConstants.RIGHT);
		lblColoniaOComunidad.setForeground(Color.BLACK);
		lblColoniaOComunidad.setBounds(0, 138, 133, 14);
		panelDomicilioActulizar.add(lblColoniaOComunidad);

		panelDomicilioActulizar.add(textFieldActualizarDireccion);
		textFieldActualizarCiudad.setForeground(Color.BLACK);
		textFieldActualizarCiudad.setColumns(10);
		textFieldActualizarCiudad.setBackground(Color.WHITE);
		textFieldActualizarCiudad.setBounds(138, 41, 293, 25);

		panelDomicilioActulizar.add(textFieldActualizarCiudad);
		textFieldActualizarEstado.setForeground(Color.BLACK);
		textFieldActualizarEstado.setColumns(10);
		textFieldActualizarEstado.setBackground(Color.WHITE);
		textFieldActualizarEstado.setBounds(138, 71, 293, 25);

		panelDomicilioActulizar.add(textFieldActualizarEstado);
		textFieldActualizarCodigoPostal.setForeground(Color.BLACK);
		textFieldActualizarCodigoPostal.setColumns(10);
		textFieldActualizarCodigoPostal.setBackground(Color.WHITE);
		textFieldActualizarCodigoPostal.setBounds(138, 101, 293, 25);
		panelDomicilioActulizar.add(textFieldActualizarCodigoPostal);
		comboBoxActLocal.setForeground(Color.BLACK);

		comboBoxActLocal.setBounds(138, 133, 293, 25);
		panelDomicilioActulizar.add(comboBoxActLocal);


		panelActualizarDatosEmpleado.setBackground(SystemColor.inactiveCaption);
		panelActualizarDatosEmpleado.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos del Empleo", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(249,246,212)));
		panelActualizarDatosEmpleado.setBounds(455, 15, 541, 382);
		panelActualizarDatosEmpleado.setLayout(null);
		panelActualizar.add(panelActualizarDatosEmpleado);

		lblPuesto.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPuesto.setForeground(Color.BLACK);
		lblPuesto.setBounds(97, 140, 75, 14);

		panelActualizarDatosEmpleado.add(lblPuesto);
		lblFechaAltaIngreso.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFechaAltaIngreso.setForeground(Color.BLACK);
		lblFechaAltaIngreso.setBounds(57, 47, 119, 14);

		panelActualizarDatosEmpleado.add(lblFechaAltaIngreso);
		lblFechaBajaIngreso.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFechaBajaIngreso.setForeground(Color.BLACK);
		lblFechaBajaIngreso.setBounds(57, 80, 119, 14);

		panelActualizarDatosEmpleado.add(lblFechaBajaIngreso);
		lblDependencia.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDependencia.setForeground(Color.BLACK);
		lblDependencia.setBounds(57, 110, 119, 14);

		panelActualizarDatosEmpleado.add(lblDependencia);

		JLabel lblActualizarEjercicio = new JLabel("Tipo de Nomina:");
		lblActualizarEjercicio.setHorizontalAlignment(SwingConstants.RIGHT);
		lblActualizarEjercicio.setForeground(Color.BLACK);
		lblActualizarEjercicio.setBounds(10, 16, 101, 20);
		panelActualizarDatosEmpleado.add(lblActualizarEjercicio);

		comboBoxActualizarDependencia.setForeground(Color.BLACK);
		comboBoxActualizarDependencia.setBounds(178, 105, 353, 25);
		panelActualizarDatosEmpleado.add(comboBoxActualizarDependencia);

		dateChooserFechaAltaIngreso.setBounds(178, 42, 353, 25);
		panelActualizarDatosEmpleado.add(dateChooserFechaAltaIngreso);

		dateChooserFechaBajaIngreso.setBounds(178, 72, 353, 25);
		panelActualizarDatosEmpleado.add(dateChooserFechaBajaIngreso);
		comboBoxActTipoNomina.setForeground(Color.BLACK);
		comboBoxActTipoNomina.setBounds(110, 11, 421, 30);

		panelActualizarDatosEmpleado.add(comboBoxActTipoNomina);
		comboBoxPuesto.setForeground(Color.BLACK);
		comboBoxPuesto.setBounds(178, 135, 353, 25);

		panelActualizarDatosEmpleado.add(comboBoxPuesto);
		comboBoxClasificacion.setForeground(Color.BLACK);
		comboBoxClasificacion.setBounds(178, 165, 353, 25);

		panelActualizarDatosEmpleado.add(comboBoxClasificacion);
		comboBoxRegimen.setForeground(Color.BLACK);
		comboBoxRegimen.setBounds(178, 194, 353, 25);

		panelActualizarDatosEmpleado.add(comboBoxRegimen);
		comboBoxTipoEmpleado.setForeground(Color.BLACK);
		comboBoxTipoEmpleado.setBounds(178, 224, 353, 25);

		panelActualizarDatosEmpleado.add(comboBoxTipoEmpleado);
		comboBoxTipoJornada.setForeground(Color.BLACK);
		comboBoxTipoJornada.setBounds(178, 257, 353, 25);

		panelActualizarDatosEmpleado.add(comboBoxTipoJornada);
		comboBoxRiesgo.setForeground(Color.BLACK);
		comboBoxRiesgo.setBounds(178, 287, 353, 25);

		panelActualizarDatosEmpleado.add(comboBoxRiesgo);
		comboBoxTipoContrato.setForeground(Color.BLACK);
		comboBoxTipoContrato.setBounds(178, 317, 353, 25);

		panelActualizarDatosEmpleado.add(comboBoxTipoContrato);
		comboBoxClasificacionEmpleado.setForeground(Color.BLACK);
		comboBoxClasificacionEmpleado.setBounds(178, 346, 353, 25);

		panelActualizarDatosEmpleado.add(comboBoxClasificacionEmpleado);

		JLabel lblClasificacin = new JLabel("Clasificación:");
		lblClasificacin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblClasificacin.setForeground(Color.BLACK);
		lblClasificacin.setBounds(97, 170, 75, 14);
		panelActualizarDatosEmpleado.add(lblClasificacin);

		JLabel lblRgimen = new JLabel("Régimen:");
		lblRgimen.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRgimen.setForeground(Color.BLACK);
		lblRgimen.setBounds(97, 199, 75, 14);
		panelActualizarDatosEmpleado.add(lblRgimen);

		JLabel lblTipoEmpleado = new JLabel("Tipo Empleado:");
		lblTipoEmpleado.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTipoEmpleado.setForeground(Color.BLACK);
		lblTipoEmpleado.setBounds(80, 230, 92, 14);
		panelActualizarDatosEmpleado.add(lblTipoEmpleado);

		JLabel lblTipoJornada = new JLabel("Tipo Jornada:");
		lblTipoJornada.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTipoJornada.setForeground(Color.BLACK);
		lblTipoJornada.setBounds(97, 260, 75, 14);
		panelActualizarDatosEmpleado.add(lblTipoJornada);

		JLabel lblRiesgo = new JLabel("Riesgo:");
		lblRiesgo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRiesgo.setForeground(Color.BLACK);
		lblRiesgo.setBounds(97, 289, 75, 14);
		panelActualizarDatosEmpleado.add(lblRiesgo);

		JLabel lblTipoContrato = new JLabel("Tipo Contrato:");
		lblTipoContrato.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTipoContrato.setForeground(Color.BLACK);
		lblTipoContrato.setBounds(80, 322, 92, 14);
		panelActualizarDatosEmpleado.add(lblTipoContrato);

		JLabel lblClasifiacinEmpleado = new JLabel("Clasifiación Empleado:");
		lblClasifiacinEmpleado.setHorizontalAlignment(SwingConstants.RIGHT);
		lblClasifiacinEmpleado.setForeground(Color.BLACK);
		lblClasifiacinEmpleado.setBounds(33, 351, 139, 14);
		panelActualizarDatosEmpleado.add(lblClasifiacinEmpleado);

		panelActualizarDatosBancarios.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos Bancarios", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(249, 246, 212)));
		panelActualizarDatosBancarios.setBackground(SystemColor.inactiveCaption);
		panelActualizarDatosBancarios.setBounds(1002, 224, 275, 106);

		panelActualizar.add(panelActualizarDatosBancarios);
		panelActualizarDatosBancarios.setLayout(null);
		lblCuentaBancaria.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCuentaBancaria.setBounds(10, 21, 114, 14);

		panelActualizarDatosBancarios.add(lblCuentaBancaria);
		textFieldCtaBancaria.setForeground(Color.BLACK);
		textFieldCtaBancaria.setColumns(10);
		textFieldCtaBancaria.setBounds(127, 11, 138, 25);

		panelActualizarDatosBancarios.add(textFieldCtaBancaria);
		lblMonedero.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMonedero.setBounds(10, 44, 114, 14);

		panelActualizarDatosBancarios.add(lblMonedero);
		textFieldMonedero.setForeground(Color.BLACK);
		textFieldMonedero.setColumns(10);
		textFieldMonedero.setBounds(127, 40, 138, 25);

		panelActualizarDatosBancarios.add(textFieldMonedero);
		lblBanco.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBanco.setBounds(10, 72, 116, 14);

		panelActualizarDatosBancarios.add(lblBanco);
		comboBoxBanco.setForeground(Color.BLACK);
		comboBoxBanco.setBounds(127, 69, 138, 25);

		panelActualizarDatosBancarios.add(comboBoxBanco);

		panelActualizarLocalizacion.setBackground(SystemColor.inactiveCaption);
		panelActualizarLocalizacion.setBorder(new TitledBorder(null, "Localizaci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(249,246,212)));
		panelActualizarLocalizacion.setBounds(455, 408, 541, 150);
		panelActualizarLocalizacion.setLayout(null);
		lblContacto.setHorizontalAlignment(SwingConstants.RIGHT);
		panelActualizarLocalizacion.add(lblContacto);
		lblCelular.setHorizontalAlignment(SwingConstants.RIGHT);
		panelActualizarLocalizacion.add(lblCelular);
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		panelActualizarLocalizacion.add(lblEmail);
		lblDireccin.setHorizontalAlignment(SwingConstants.RIGHT);
		panelActualizarLocalizacion.add(lblDireccin);
		panelActualizar.add(panelActualizarLocalizacion);

		lblContacto.setForeground(Color.BLACK);
		lblContacto.setBounds(64, 16, 104, 14);

		lblEmail.setForeground(Color.BLACK);
		lblEmail.setBounds(64, 46, 104, 14);

		lblCelular.setForeground(Color.BLACK);
		lblCelular.setBounds(64, 77, 104, 14);

		lblDireccin.setForeground(Color.BLACK);
		lblDireccin.setBounds(64, 106, 104, 14);

		textFieldActualizarContacto.setForeground(Color.BLACK);
		textFieldActualizarContacto.setColumns(10);
		textFieldActualizarContacto.setBackground(Color.WHITE);
		textFieldActualizarContacto.setBounds(178, 11, 297, 25);

		panelActualizarLocalizacion.add(textFieldActualizarContacto);
		textFieldActualizarEmailContacto.setForeground(Color.BLACK);
		textFieldActualizarEmailContacto.setColumns(10);
		textFieldActualizarEmailContacto.setBackground(Color.WHITE);
		textFieldActualizarEmailContacto.setBounds(178, 41, 297, 25);

		panelActualizarLocalizacion.add(textFieldActualizarEmailContacto);
		textFieldActualizarCelular.setForeground(Color.BLACK);
		textFieldActualizarCelular.setColumns(10);
		textFieldActualizarCelular.setBackground(Color.WHITE);
		textFieldActualizarCelular.setBounds(178, 72, 297, 25);

		panelActualizarLocalizacion.add(textFieldActualizarCelular);
		textFieldActualizarDireccionContacto.setForeground(Color.BLACK);
		textFieldActualizarDireccionContacto.setColumns(10);
		textFieldActualizarDireccionContacto.setBackground(Color.WHITE);
		textFieldActualizarDireccionContacto.setBounds(178, 101, 297, 25);

		
		panelActualizarLocalizacion.add(textFieldActualizarDireccionContacto);
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					actualizarRegistro();
					//internalFrameEmpleado.mostrarDatosEmpleado("");
			}
		});

		btnActualizar.setBounds(558, 569, 103, 35);
		btnActualizar.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("accept.png"))));
		panelActualizar.add(btnActualizar);

		btnActualizarCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		btnActualizarCancelar.setBounds(675, 569, 123, 35);
		btnActualizarCancelar.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("delete.png"))));
		panelActualizar.add(btnActualizarCancelar);

		JPanel panelActualizarDatosImssInfo = new JPanel();
		panelActualizarDatosImssInfo.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos del Imss e Infonavit", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(249, 246, 212)));
		panelActualizarDatosImssInfo.setBounds(1002, 341, 488, 217);
		panelActualizarDatosImssInfo.setBackground(SystemColor.inactiveCaption);
		panelActualizar.add(panelActualizarDatosImssInfo);
		panelActualizarDatosImssInfo.setLayout(null);
		lblRegistroImss.setBounds(58, 22, 114, 14);
		panelActualizarDatosImssInfo.add(lblRegistroImss);
		lblRegistroImss.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRegistroImss.setForeground(Color.BLACK);
		textFieldActualizarRegistroImss.setBounds(178, 11, 299, 25);
		panelActualizarDatosImssInfo.add(textFieldActualizarRegistroImss);

		textFieldActualizarRegistroImss.setForeground(Color.BLACK);
		textFieldActualizarRegistroImss.setColumns(10);
		textFieldActualizarRegistroImss.setBackground(Color.WHITE);
		lblFechaAltaImss.setBounds(58, 49, 114, 14);
		panelActualizarDatosImssInfo.add(lblFechaAltaImss);
		lblFechaAltaImss.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFechaAltaImss.setForeground(Color.BLACK);

		dateChooserFechaAltaImss.setBounds(178, 39, 299, 25);
		panelActualizarDatosImssInfo.add(dateChooserFechaAltaImss);
		lblFechaBajaImss.setBounds(58, 77, 114, 14);
		panelActualizarDatosImssInfo.add(lblFechaBajaImss);
		lblFechaBajaImss.setHorizontalAlignment(SwingConstants.RIGHT);	
		lblFechaBajaImss.setForeground(Color.BLACK);

		dateChooserFechaBajaImss.setBounds(178, 67, 299, 25);
		panelActualizarDatosImssInfo.add(dateChooserFechaBajaImss);
		textFieldSDIActualizar.setBounds(178, 97, 299, 25);
		panelActualizarDatosImssInfo.add(textFieldSDIActualizar);
		textFieldSDIActualizar.setColumns(10);
		textFieldSDIActualizar.setForeground(Color.BLACK);
		textFieldNCIActualizar.setBounds(178, 126, 299, 25);
		panelActualizarDatosImssInfo.add(textFieldNCIActualizar);
		textFieldNCIActualizar.setColumns(10);
		textFieldNCIActualizar.setForeground(Color.BLACK);
		textFieldFIActualizar.setBounds(178, 154, 299, 25);
		panelActualizarDatosImssInfo.add(textFieldFIActualizar);
		textFieldFIActualizar.setColumns(10);
		textFieldFIActualizar.setForeground(Color.BLACK);
		textFieldUMAActualizr.setBounds(178, 181, 299, 25);
		panelActualizarDatosImssInfo.add(textFieldUMAActualizr);
		textFieldUMAActualizr.setColumns(10);
		textFieldUMAActualizr.setForeground(Color.BLACK);
		lblNewLabel.setBounds(34, 105, 138, 14);
		panelActualizarDatosImssInfo.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(10, 131, 162, 14);
		panelActualizarDatosImssInfo.add(lblNewLabel_1);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(58, 159, 116, 14);
		panelActualizarDatosImssInfo.add(lblNewLabel_2);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setBounds(58, 186, 116, 14);
		panelActualizarDatosImssInfo.add(lblNewLabel_3);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);

		JPanel panelFoto = new JPanel();
		panelFoto.setLayout(null);
		panelFoto.setBorder(new TitledBorder(null, "Empleado(a)", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(249,246,212)));
		panelFoto.setBackground(SystemColor.inactiveCaption);
		panelFoto.setBounds(1287, 15, 203, 210);
		panelActualizar.add(panelFoto);

		JLabel lblFoto = new JLabel("");
		lblFoto.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("businessman(2).png"))));
		lblFoto.setBounds(10, 21, 183, 179);
		panelFoto.add(lblFoto);

		JButton btnCargarFoto = new JButton("Cargar Foto");
		btnCargarFoto.setBounds(1326, 224, 138, 39);
		panelActualizar.add(btnCargarFoto);

		JLabel labelRutaFoto = new JLabel("");
		labelRutaFoto.setBounds(1287, 274, 211, 14);
		panelActualizar.add(labelRutaFoto);

		JButton btnGuardarFoto = new JButton("Guardar");
		btnGuardarFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnGuardarFoto.setBounds(1359, 299, 89, 23);
		panelActualizar.add(btnGuardarFoto);

		
//		ArrayList<String> listaGenero = new ArrayList<String>();
//		listaGenero = selectComboGenero();
//		comboBoxActualizarGenero.addItem("Seleccione Uno");
//		for (int i = 0; i < listaGenero.size(); i++) {
//			comboBoxActualizarGenero.addItem(listaGenero.get(i));
//		}
//
//		ArrayList<String> listaEdoCivil = new ArrayList<String>();
//		listaEdoCivil = internalFrameEmpleado.selectComboEdoCivil();
//		comboBoxActualizarEdoCivil.addItem("Seleccione Uno");
//		for (int i = 0; i < listaEdoCivil.size(); i++) {
//			comboBoxActualizarEdoCivil.addItem(listaEdoCivil.get(i));
//		}
////
//		ArrayList<String> listaLocal = new ArrayList<String>();
//		listaLocal = internalFrameEmpleado.buscarLocalidad();
//		comboBoxActLocal.addItem("Seleccione Una");
//		for (int i = 0; i < listaLocal.size(); i++) {
//			comboBoxActLocal.addItem(listaLocal.get(i));
//		}
//
//		ArrayList<String> listaEjercicio = new ArrayList<String>();
//		listaEjercicio = internalFrameEmpleado.buscarEjercicio();
//		comboBoxActTipoNomina.addItem("Seleccione Una");
//		for (int i = 0; i < listaEjercicio.size(); i++) {
//			comboBoxActTipoNomina.addItem(listaEjercicio.get(i));
//		}
//
//		ArrayList<String> listaDependencia = new ArrayList<String>();
//		listaDependencia = internalFrameEmpleado.selectComboDependencia();
//		comboBoxActualizarDependencia.addItem("Seleccione Una");
//		for (int i = 0; i < listaDependencia.size(); i++) {
//			comboBoxActualizarDependencia.addItem(listaDependencia.get(i));
//		}
//
//		
//		ArrayList<String> listaPuestos = new ArrayList<String>();
//		listaPuestos = internalFrameEmpleado.selectComboPuesto();
//		comboBoxPuesto.addItem("Seleccione Uno");
//		for (int i = 0; i < listaPuestos.size(); i++) {
//			comboBoxPuesto.addItem(listaPuestos.get(i));
//		}
//
//
//		ArrayList<String> listaClasificacionDispersion = new ArrayList<String>();
//		listaClasificacionDispersion = internalFrameEmpleado.buscarClasificacionDispersion();
//		comboBoxClasificacion.addItem("Seleccione Una");
//		for (int i = 0; i < listaClasificacionDispersion.size(); i++) {
//			comboBoxClasificacion.addItem(listaClasificacionDispersion.get(i));
//		}
//		
//		ArrayList<String> listaRegimen = new ArrayList<String>();
//		listaRegimen = internalFrameEmpleado.buscarRegimen();
//		comboBoxRegimen.addItem("Seleccione Una");
//		for (int i = 0; i < listaRegimen.size(); i++) {
//			comboBoxRegimen.addItem(listaRegimen.get(i));
//		}
//
//		ArrayList<String> listaTipoEmpleado = new ArrayList<String>();
//		listaTipoEmpleado = internalFrameEmpleado.buscarTipoEmpleado();
//		comboBoxTipoEmpleado.addItem("Seleccione Una");
//		for (int i = 0; i < listaTipoEmpleado.size(); i++) {
//			comboBoxTipoEmpleado.addItem(listaTipoEmpleado.get(i));
//		}
//
//		ArrayList<String> listaTipoJornada = new ArrayList<String>();
//		listaTipoJornada = internalFrameEmpleado.buscarTipoJornada();
//		comboBoxTipoJornada.addItem("Seleccione Una");
//		for (int i = 0; i < listaTipoJornada.size(); i++) {
//			comboBoxTipoJornada.addItem(listaTipoJornada.get(i));
//		}
//
//		ArrayList<String> listaRiesgo = new ArrayList<String>();
//		listaRiesgo = internalFrameEmpleado.buscarRiesgo();
//		comboBoxRiesgo.addItem("Seleccione Una");
//		for (int i = 0; i < listaRiesgo.size(); i++) {
//			comboBoxRiesgo.addItem(listaRiesgo.get(i));
//		}
//
//		ArrayList<String> listaTipoContrato = new ArrayList<String>();
//		listaTipoContrato = internalFrameEmpleado.buscarTipoContrato();
//		comboBoxTipoContrato.addItem("Seleccione Una");
//		for (int i = 0; i < listaTipoContrato.size(); i++) {
//			comboBoxTipoContrato.addItem(listaTipoContrato.get(i));
//		}
//
//		ArrayList<String> listaClasifEmpleado = new ArrayList<String>();
//		listaClasifEmpleado = internalFrameEmpleado.buscarClasifEmpleado();
//		comboBoxClasificacionEmpleado.addItem("Seleccione Una");
//		for (int i = 0; i < listaClasifEmpleado.size(); i++) {
//			comboBoxClasificacionEmpleado.addItem(listaClasifEmpleado.get(i));
//		}
//
//		ArrayList<String> listaBancos = new ArrayList<String>();
//		listaBancos = internalFrameEmpleado.buscarBancos();
//		comboBoxBanco.addItem("Seleccione Una");
//		for (int i = 0; i < listaBancos.size(); i++) {
//			comboBoxBanco.addItem(listaBancos.get(i));
//		}
	}


	public void actualizarRegistro() {
		PoolNYCH nych = new PoolNYCH();
		Connection con =null;
//		int fila = internalFrameEmpleado.tableDatosEmpleados.getSelectedRow();
//		int fila2 = internalFrameEmpleado.tableDatosEmpleadosFalsa.getSelectedRow();
		String SqlUpdate="";
		Date fechaNacimiento = null;
		Date fechaAltaImss =null;
		Date fechaBajaImss = null;
		Date fechaAltaIngreso = null;
		Date fechaBajaIngreso = null;
		fechaNacimiento = dateChooserFechaNacimiento.getDate();
		fechaAltaImss = dateChooserFechaAltaImss.getDate();
		fechaBajaImss = dateChooserFechaBajaImss.getDate();
		fechaAltaIngreso =  dateChooserFechaAltaIngreso.getDate();
		fechaBajaIngreso =  dateChooserFechaBajaIngreso.getDate(); 
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
//		if(fila>1){
			SqlUpdate="UPDATE dbo.EMPLEADOS SET NOMBRE='"+textFieldActualizarNombre.getText()+"',"
					+ "APELLIDO_PATERNO='"+textFieldActualizarPat.getText()+"',"
					+ "APELLIDO_MATERNO='"+textFieldActualizarMat.getText()+"',"
					+ "FECHA_NACIMIENTO=convert(datetime,'"+ formatter.format(fechaNacimiento)+"',101),"
					+ "CURP='"+ textFieldActualizarCurp.getText() +"',"
					+ "RFC='"+ textFieldActualizarRfc.getText() + "',"
					+ "TELEFONO='"+ textFieldActualizarTel.getText() + "',"
					+ "ID_GENERO='"+comboBoxActualizarGenero.getSelectedIndex()+"',"
					+ "ESTADO_CIVIL='"+comboBoxActualizarEdoCivil.getSelectedIndex()+"',"
					+ "EMAIL='"+ textFieldActualizarEmail.getText() + "',"
					+ "DIRECCION='"+ textFieldActualizarDireccion.getText()+"',"
					+ "CIUDAD='"+ textFieldActualizarCiudad.getText()+"',"
					+ "ESTADO='"+ textFieldActualizarEstado.getText()+"',"
					+ "CODIGO_POSTAL='"+ textFieldActualizarCodigoPostal.getText()+"',"
					+ "LOCALIDAD='"+comboBoxActLocal.getSelectedIndex()+"',"
					+ "ID_EJERCICIOS='"+comboBoxActTipoNomina.getSelectedIndex()+"',"
					+ "FECHA_INGRESO=convert(datetime,'"+ formatter.format(fechaAltaIngreso)+"',101),"
					+ "FECHA_BAJA=convert(datetime,'"+ formatter.format(fechaBajaIngreso)+"',101),"
					+ "ID_UNIDAD='"+comboBoxActualizarDependencia.getSelectedIndex()+"',"
					+ "ID_PUESTO='"+ comboBoxPuesto.getSelectedIndex()+"',"
					+ "ID_CLASIFICACION_DISPERSION='"+comboBoxClasificacion.getSelectedIndex()+"',"
					+ "ID_REGIMEN='"+comboBoxRegimen.getSelectedIndex()+"',"
					+ "ID_TIPO_EMPLEADO='"+comboBoxTipoEmpleado.getSelectedIndex()+"',"
					+ "ID_TIPO_JORNADA='"+comboBoxTipoJornada.getSelectedIndex()+"',"
					+ "ID_RIESGO='"+comboBoxRiesgo.getSelectedIndex()+"',"
					+ "ID_CONTRATO='"+comboBoxTipoContrato.getSelectedIndex()+"',"
					+ "ID_CLASIFICACION_EMPLEADO='"+comboBoxClasificacionEmpleado.getSelectedIndex()+"',"
					+ "CONTACTO_FAMILIAR='"+ textFieldActualizarContacto.getText() +"',"
					+ "EMAIL_CONTACTO='"+ textFieldActualizarEmailContacto.getText()+ "',"
					+ "CELULAR_CONTACTO='"+ textFieldActualizarCelular.getText()+"',"
					+ "DIRECCION_CONTACTO='"+ textFieldActualizarDireccionContacto.getText()+"',"
					+ "CUENTA_BANCARIA='"+textFieldCtaBancaria.getText()+"',"
					+ "MONEDERO='"+textFieldMonedero.getText() +"',"
					+ "ID_BANCO='"+comboBoxBanco.getSelectedIndex()+"',"
					+ "REGISTRO_IMSS='"+ textFieldActualizarRegistroImss.getText()+"',"
					+ "FECHA_ALTA_IMSS=convert(datetime,'"+ formatter.format(fechaAltaImss)+"',101),"
					+ "FECHA_BAJA_IMSS=convert(datetime,'"+ formatter.format(fechaBajaImss)+"',101),"
					+ "SDI='"+textFieldSDIActualizar.getText()+"',"
					+ "NO_CREDITO_INFONAVIT='"+textFieldNCIActualizar.getText()+"',"
					+ "FACTOR_INFONAVIT='"+textFieldFIActualizar.getText()+"',"
					+ "U_M_A='"+textFieldUMAActualizr.getText()+ "'"
					+ "WHERE CLAVE='"+textFieldIdEmpleado.getText()+"'";
			System.out.println("update empleado: "+SqlUpdate);
//		}else if(fila2>1) {
//			JOptionPane.showMessageDialog(null, "Datos Actualizados...");
//		}
		try {
			con = nych.datasource.getConnection();
			PreparedStatement pps = con.prepareStatement(SqlUpdate);
			pps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Datos Actualizados***");
			dispose();
		} catch (SQLException e) {
			e.printStackTrace();
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: "+ errors );
			JOptionPane.showMessageDialog(null, "Datos No Actualizados");
		}finally {
			try {
				con.close();
			} catch (SQLException ep) {
				ep.printStackTrace();
				StringWriter errors = new StringWriter();
				ep.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: "+ errors );
				//JOptionPane.showMessageDialog(null, ep, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
			}
		}
	}


	public void listarRegistros(String valor) {
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("ID");
		modelo.addColumn("NOMBRE");
		modelo.addColumn("APELLIDO PATERNO");
		modelo.addColumn("APELLIDO MATERNO");
		modelo.addColumn("CURP");
		modelo.addColumn("RFC");
		modelo.addColumn("PUESTO");
		modelo.addColumn("SALARIO");
		modelo.addColumn("REGISTRO IMSS");
		modelo.addColumn("UNIDAD RESPONSABLE");
		modelo.addColumn("TIPO NOMINA");


		internalFrameEmpleado.tableDatosEmpleados.setModel(modelo);
		internalFrameEmpleado.tableDatosEmpleados.setBackground(Color.WHITE);

		JTableHeader th = new JTableHeader();
		Color colorSilverLight=new Color(176, 196, 222);
		Color colorNegro=new Color(0, 0, 0);
		th = internalFrameEmpleado.tableDatosEmpleados.getTableHeader();
		Font fuente = new Font("Cooper Black", Font.BOLD, 14); 
		th.setFont(fuente); 
		th.setBackground(colorSilverLight);
		th.setForeground(colorNegro);

		TableColumnModel columnModel = internalFrameEmpleado.tableDatosEmpleados.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(50);
		columnModel.getColumn(1).setPreferredWidth(200);
		columnModel.getColumn(2).setPreferredWidth(200);
		columnModel.getColumn(3).setPreferredWidth(200);
		columnModel.getColumn(4).setPreferredWidth(200);
		columnModel.getColumn(5).setPreferredWidth(200);
		columnModel.getColumn(6).setPreferredWidth(300);
		columnModel.getColumn(7).setPreferredWidth(200);
		columnModel.getColumn(8).setPreferredWidth(200);
		columnModel.getColumn(9).setPreferredWidth(350);
		columnModel.getColumn(10).setPreferredWidth(700);

		String sqlSelect="";
		String sqlpuesto="";
		String sqlDependencia="";
		String sqlEjercicio="";
		if(valor.equals("")) {
			sqlSelect = "select id_empleado,nombre,apellido_paterno,apellido_materno,curp,rfc,registro_imss from dbo.empleados where eliminar_logico='"+1+"' order by id_puesto";
			sqlpuesto ="SELECT dbo.puestos.nombre_puesto,dbo.puestos.salario FROM dbo.EMPLEADOS left JOIN DBO.PUESTOS ON DBO.EMPLEADOS.ID_PUESTO = DBO.PUESTOS.no_puesto order by dbo.PUESTOS.no_puesto";
			sqlDependencia="SELECT DBO.DEPENDENCIAS.UNIDAD_RESPONSABLE FROM dbo.EMPLEADOS left JOIN DBO.DEPENDENCIAS ON DBO.EMPLEADOS.ID_UNIDAD = DBO.DEPENDENCIAS.ID_UNIDAD order by dbo.empleados.id_puesto";
			sqlEjercicio = "SELECT DBO.EJERCICIOS.NOMBRE_EJERCICIO FROM dbo.EMPLEADOS left JOIN DBO.EJERCICIOS ON DBO.EMPLEADOS.ID_EJERCICIOS = DBO.EJERCICIOS.ID_EJERCICIOS order by dbo.empleados.id_puesto";
		}else {
			sqlSelect = "SELECT * FROM dbo.EMPLEADOS WHERE "+atributo+""+"='"+ valor +"' order by id_puesto"; 
		}

		Object datos[] = new String[11];
		PoolNYCH nych = new PoolNYCH();
		Connection con =null;
		Connection conPuesto =null;
		Connection conDepen =null;
		Connection conEjercicio =null;
		Statement st;
		Statement stPuesto;
		Statement stDepen;
		Statement stEjercicio;
		ResultSet resultSet;
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
			while(resultSet.next() && resPuesto.next() && resDependencia.next() && resEjercicio.next()) {
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
			internalFrameEmpleado.tableDatosEmpleados.setModel(modelo);
		}catch (SQLException el) {
			el.printStackTrace();
			StringWriter errors = new StringWriter();
			el.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: "+ errors );
			//JOptionPane.showMessageDialog(null, el, "Error de conexion", JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
		}finally {
			try {
				con.close();
			} catch (SQLException ep) {
				ep.printStackTrace();
				StringWriter errors = new StringWriter();
				ep.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: "+ errors );
				//JOptionPane.showMessageDialog(null, ep, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
			}
		}
	}//fin del metodo

	public  ArrayList<String> selectComboGenero() {
		PoolNYCH poolNych = new PoolNYCH();
		Connection connect = null;
		Statement st;
		ResultSet resultSet = null;
		ArrayList<String> lista = new ArrayList<String>();
		String sqlComboGenero = "SELECT * FROM DBO.genero";
		//String sqlComboGenero = "SELECT dbo.GENERO.GENERO FROM dbo.EMPLEADOS left JOIN DBO.GENERO ON DBO.EMPLEADOS.ID_GENERO = DBO.GENERO.ID_GENERO";
		//System.out.println(sqlComboGenero);
		try {
			connect = poolNych.datasource.getConnection();
			st = connect.createStatement();
			resultSet = st.executeQuery(sqlComboGenero);
		}catch(Exception exp) {
			exp.printStackTrace();
			StringWriter errors = new StringWriter();
			exp.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: "+ errors );
			//JOptionPane.showMessageDialog(null, exp, "Error de conexion", JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
		}
		try {
			while(resultSet.next()){
				lista.add(resultSet.getString("GENERO"));
			}
		}catch(Exception et) {
			et.printStackTrace();
			StringWriter errors = new StringWriter();
			et.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: "+ errors );
			//JOptionPane.showMessageDialog(null, et, "Error de conexion", JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
		}finally {
			try {
				connect.close();
			} catch (SQLException ep) {
				ep.printStackTrace();
				StringWriter errors = new StringWriter();
				ep.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: "+ errors );
				//JOptionPane.showMessageDialog(null, ep, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
			}
		}
		return lista;
	}
}

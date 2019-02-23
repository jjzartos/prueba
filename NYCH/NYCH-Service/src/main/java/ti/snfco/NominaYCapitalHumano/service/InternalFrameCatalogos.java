package ti.snfco.NominaYCapitalHumano.service;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

import org.slf4j.LoggerFactory;

import com.toedter.calendar.JDateChooser;
import java.awt.SystemColor;
import javax.swing.JSeparator;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.border.LineBorder;
import java.awt.FlowLayout;


public class InternalFrameCatalogos extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	InternalFrameFechaNuevaInhabil fechaNuevaInhabil = new InternalFrameFechaNuevaInhabil();
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(InternalFrameCatalogos.class);
	public static JTable tableDatosPercepciones = new JTable();
	public static JTable tableDatosDeducciones = new JTable();
	public static JTable tableDatosRegimen = new JTable();
	public static JTable tableDatosBanco = new JTable();
	public static JTable tableDatosRiesgos = new JTable();
	public static JTable tableDatosIncapacidad = new JTable();
	public static JTable tableDatosPuestos = new JTable();
	public static JTable tableDatosDatosISR = new JTable();
	public static JTable tableDatosSubsidioISR = new JTable();
	public static JTable tableDatosDatosImss = new JTable();
	public static JTable tableDatosDiasInhabiles = new JTable();
	public static JTable tableClasificacion = new JTable();
	public static JTable tableMensualISR = new JTable();
	public static JTable tableMensualSubisidioISR = new JTable();
	public static JTable tableTablaIsrAnual = new JTable();
	InternalFramePeriodo internalFramePeriodo = new InternalFramePeriodo();
	String atributo = "id";
	ButtonGroup groupButtonPer = new ButtonGroup();
	ButtonGroup groupButtonDed = new ButtonGroup();
	ButtonGroup groupButtonReg = new ButtonGroup();
	ButtonGroup groupButtonBanco = new ButtonGroup();
	ButtonGroup groupButtonRiesgos = new ButtonGroup();
	ButtonGroup groupButtonIncapacidad = new ButtonGroup();
	JRadioButton rdbtnClavePer = new JRadioButton("Clave");
	JRadioButton rdbtnClaveReg = new JRadioButton("Clave");
	JRadioButton rdbtnClaveDed = new JRadioButton("Clave");
	JRadioButton rdbtnClaveBanco = new JRadioButton("Clave");
	JRadioButton rdbtnClaveRiesgos = new JRadioButton("Clave");
	JRadioButton rdbtnClaveIncapacidad = new JRadioButton("Clave");
	JRadioButton rdbtnDescripcionRiesgos = new JRadioButton("Descripción");
	JRadioButton rdbtnDescripcionPer = new JRadioButton("Descripción");
	JRadioButton rdbtndescripcionDed = new JRadioButton("Descripción");
	JRadioButton rdbtnDescripcionReg = new JRadioButton("Descripción");
	JRadioButton rdbtnDescripcionIncap = new JRadioButton("Descripción");
	JRadioButton rdbtnNombreBanco = new JRadioButton("Nombre");
	JTextField textFieldBuscarPer = new JTextField();
	JTextField textFieldBuscarDed= new JTextField();
	JTextField textFieldBuscarReg = new JTextField();
	JTextField textFieldBuscarBanco = new JTextField();
	JTextField textFieldBuscarRiesgos;
	JTextField textFieldBuscarIncap = new JTextField();
	JButton btnNewButton = new JButton("Buscar");
	JDateChooser dateChooserVacaciones = new JDateChooser();
	JTextField textFieldBuscarPuesto;
	JButton btnREFRESH = new JButton("Actualizar");
	//JLabel lblFechaSeleccionada = new JLabel("New label");
	JPanel panelDiasInahabiles = new JPanel();
	JTextField textFieldIncremento;
	JTextField textFieldNombrePuesto;
	JTextField textFieldSalario;
	JLabel lblNombrePuesto = new JLabel("Nombre del Puesto");
	JLabel lblSalario = new JLabel("Salario");
	JButton btnAgregarPuesto = new JButton("Agregar");
	private JTextField textFieldNuevaDed;
	private JTextField textFieldComentario;
	JLabel lblNuevaDed = new JLabel("Ingresar nueva deduccion");
	JLabel lblComentario = new JLabel("Comentario");
	JButton btnAgregarDed = new JButton("Guardar");
	JTextField textFieldNuevaPerp;
	JTextField textFieldCoement;
	JLabel lblNuevaPerp = new JLabel("Ingresar nueva  percepción");
	JLabel labelComentt = new JLabel("Comentario");
	JButton btnNuevaPerpp = new JButton("Guardar");
	private JTextField textFieldEmpClasif;
	TableRowSorter rowSortere;
	int IdBusquedaEmplee = 1;
	JLabel lblCalsifi = new JLabel("Clasificación a actualizar");
	JButton btnActualizarCalsifi = new JButton("Actualizar");
	public ButtonGroup groupButton = new ButtonGroup();
	JLabel lblNumReg = new JLabel("Numero de registros");
	JLabel lblNumeroRegistro = new JLabel("0");
	JComboBox comboBoxClasif = new JComboBox();
	JComboBox comboBoxClasif2 = new JComboBox();
	JTextField textFieldNuevaClasifi;
	JLabel lblNuevaClasifi = new JLabel("Nueva clasificación");
	JButton btnGuardarNuevaclasif = new JButton("Guardar");
	JLabel lblEmpUno = new JLabel("Seleccione el Empleado(a):");
	JPanel panelCalsificacion = new JPanel();
	JPanel panelRegimen = new JPanel();
	JLabel lblFondoPercep = new JLabel("");
	JLabel lblFondo = new JLabel("");
	JLabel lblFondoRegimen = new JLabel("");
	JLabel lblFondoBancos = new JLabel("");
	JLabel lblFondoriesgos = new JLabel("");
	JLabel lblFondoTipoIncapacidad = new JLabel("");
	JLabel lblFondoPuestos = new JLabel("");
	JLabel lblFondoDatosISR = new JLabel("");
	JLabel lblFondoSubsidioISR = new JLabel("");
	JLabel lblFondoDatosIMSS = new JLabel("");
	JLabel lblFondoDiasInhabiles = new JLabel("");
	JLabel lblFondoClasificacion = new JLabel("");
	JLabel lblFondoTablaMensualISR = new JLabel("");
	JLabel lblFondoTablaMensualSubsidioISR = new JLabel("");
	JPanel panelDeducciones = new JPanel();
	JPanel panelPerpeciones = new JPanel();
	JPanel panelRiesgos = new JPanel();
	JPanel panelTipoIncapacidad = new JPanel();
	JPanel panelPuestos = new JPanel();
	JPanel panelSubsidioISR = new JPanel();
	JPanel panelDatosISR = new JPanel();
	JPanel panelDatosImss = new JPanel();
	JPanel panelTablaMensualISR = new JPanel();
	JPanel panelTablaMensualSubsidioISR = new JPanel();
	JPanel panelDatosIniciales = new JPanel();
	JLabel lblFondoIsrAnual = new JLabel("");
	public static JTextField textFieldCantidadAyudaDespensa;
	private final JLabel lblPorcentajeParaLa = new JLabel("Porcentaje para la ayuda de despensa");
	public static JTextField textFieldPorcentajeAyudaDespensa = new JTextField();
	private final JLabel label = new JLabel("%");

	public InternalFrameCatalogos() {
		setBounds(0, 0, 1500, 701);
		setVisible(true);
		setTitle("Catálogos");
		getContentPane().setLayout(null);

		JPanel panelCatalogos = new JPanel();
		panelCatalogos.setBounds(0, 0, 1484, 672);
		getContentPane().add(panelCatalogos);
		panelCatalogos.setLayout(null);

		JTabbedPane tabbedPaneCatalogoPestañas = new JTabbedPane(JTabbedPane.TOP);
		tabbedPaneCatalogoPestañas.setBounds(0, 0, 1483, 672);
		panelCatalogos.add(tabbedPaneCatalogoPestañas);


		panelPerpeciones.setBackground(SystemColor.controlHighlight);
		panelPerpeciones.setBorder(new TitledBorder(null, "Clave Percepciones", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(176, 196, 222)));
		//panelPerpeciones.setBackground(new Color(147,140,147));
		tabbedPaneCatalogoPestañas.addTab("Percepciones", null, panelPerpeciones, null);
		panelPerpeciones.setLayout(null);

		JScrollPane scrollPaneDatosPercepciones = new JScrollPane();
		scrollPaneDatosPercepciones.setViewportBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		scrollPaneDatosPercepciones.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneDatosPercepciones.setBounds(22, 122, 767, 470);
		tableDatosPercepciones.setCellSelectionEnabled(true);
		scrollPaneDatosPercepciones.setViewportView(tableDatosPercepciones);
		panelPerpeciones.add(scrollPaneDatosPercepciones);
		rdbtnClavePer.setBackground(SystemColor.controlHighlight);

		//rdbtnClavePer.setBackground(new Color(147,140,147));
		rdbtnClavePer.setBounds(22, 29, 109, 23);
		panelPerpeciones.add(rdbtnClavePer);
		rdbtnDescripcionPer.setBackground(SystemColor.controlHighlight);

		//rdbtnDescripcionPer.setBackground(new Color(147,140,147));
		rdbtnDescripcionPer.setBounds(153, 29, 109, 23);
		panelPerpeciones.add(rdbtnDescripcionPer);

		textFieldBuscarPer.setBackground(SystemColor.controlHighlight);
		textFieldBuscarPer.setBounds(62, 60, 305, 30);
		textFieldBuscarPer.setColumns(10);
		panelPerpeciones.add(textFieldBuscarPer);

		JButton btnBuscarPer = new JButton("Buscar");
		btnBuscarPer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNumReg.setVisible(true);
				lblNumeroRegistro.setVisible(true);
				//lblNumeroRegistro.setText(tableClasificacion.getRowCount());
				groupButtonPer.add(rdbtnClavePer);
				groupButtonPer.add(rdbtnDescripcionPer);
				if(rdbtnClavePer.isSelected()){
					atributo = "CLAVE";
					mostrarDatosPercepciones(textFieldBuscarPer.getText());
				}else if(rdbtnDescripcionPer.isSelected()){
					atributo = "DESCRIPCION";
					mostrarDatosPercepciones(textFieldBuscarPer.getText());
				}else {
					JOptionPane.showMessageDialog(null, "No se ha seleccionado ningun tipo de busqueda");
				}
			}

		});
		btnBuscarPer.setBounds(395, 59, 119, 32);
		btnBuscarPer.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("icons8_Search_32px_2.png"))));
		panelPerpeciones.add(btnBuscarPer);

		JLabel lbliconperc = new JLabel();
		lbliconperc.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("icons8_Search_32px_2.png"))));
		lbliconperc.setBounds(22, 59, 46, 32);
		panelPerpeciones.add(lbliconperc);

		JButton btnNuevaPercepcion = new JButton("Nueva Percepcion");
		btnNuevaPercepcion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				labelComentt.setVisible(true);
				lblNuevaPerp.setVisible(true);
				textFieldNuevaPerp.setVisible(true);
				textFieldCoement.setVisible(true);
				btnNuevaPerpp.setVisible(true);
			}
		});
		btnNuevaPercepcion.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("add.png"))));
		btnNuevaPercepcion.setBounds(524, 59, 179, 32);
		panelPerpeciones.add(btnNuevaPercepcion);

		lblNuevaPerp.setBounds(796, 122, 159, 14);
		panelPerpeciones.add(lblNuevaPerp);

		textFieldNuevaPerp = new JTextField();
		textFieldNuevaPerp.setColumns(10);
		textFieldNuevaPerp.setBounds(796, 147, 353, 30);
		panelPerpeciones.add(textFieldNuevaPerp);

		labelComentt.setBounds(796, 188, 159, 14);
		panelPerpeciones.add(labelComentt);

		textFieldCoement = new JTextField();
		textFieldCoement.setColumns(10);
		textFieldCoement.setBounds(796, 213, 353, 30);
		panelPerpeciones.add(textFieldCoement);
		btnNuevaPerpp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertNuevaPercepcion();
				mostrarDatosPercepciones("");
			}
		});
		btnNuevaPerpp.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("add.png"))));
		btnNuevaPerpp.setBounds(915, 254, 149, 30);
		panelPerpeciones.add(btnNuevaPerpp);

		lblFondoPercep.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("walll.png"))));
		lblFondoPercep.setBounds(0, 0, 1482, 645);
		panelPerpeciones.add(lblFondoPercep);

		panelDeducciones.setBackground(SystemColor.controlHighlight);
		panelDeducciones.setBorder(new TitledBorder(null, "Clave Deducciones", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(176, 196, 222)));
		//panelDeducciones.setBackground(new Color(147,140,147));
		tabbedPaneCatalogoPestañas.addTab("Deducciones", null, panelDeducciones, null);
		panelDeducciones.setLayout(null);
		rdbtnClaveDed.setBackground(SystemColor.controlHighlight);


		rdbtnClaveDed.setBounds(22, 29, 109, 23);
		panelDeducciones.add(rdbtnClaveDed);
		rdbtndescripcionDed.setBackground(SystemColor.controlHighlight);
		//rdbtndescripcionDed.setBackground(new Color(147,140,147));

		rdbtndescripcionDed.setBounds(153, 29, 109, 23);
		panelDeducciones.add(rdbtndescripcionDed);
		textFieldBuscarDed.setBackground(SystemColor.controlHighlight);

		textFieldBuscarDed.setBounds(66, 59, 305, 32);
		panelDeducciones.add(textFieldBuscarDed);
		textFieldBuscarDed.setColumns(10);

		JButton btnBuscarDed = new JButton("Buscar");
		btnBuscarDed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				groupButtonDed.add(rdbtnClaveDed);
				groupButtonDed.add(rdbtndescripcionDed);
				if(rdbtnClaveDed.isSelected()){
					atributo = "CLAVE";
					mostrarDatosDeducciones(textFieldBuscarDed.getText());
				}else if(rdbtndescripcionDed.isSelected()){
					atributo = "DESCRIPCION";
					mostrarDatosDeducciones(textFieldBuscarDed.getText());
				}else {
					JOptionPane.showMessageDialog(null, "No se ha seleccionado ningun tipo de busqueda");
				}
			}
		});
		btnBuscarDed.setBounds(381, 59, 119, 32);
		btnBuscarDed.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("icons8_Search_32px_2.png"))));
		panelDeducciones.add(btnBuscarDed);

		JScrollPane scrollPaneDatosDeducciones = new JScrollPane();
		scrollPaneDatosDeducciones.setViewportBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		scrollPaneDatosDeducciones.setBounds(22, 120, 824, 436);
		scrollPaneDatosDeducciones.setViewportView(tableDatosDeducciones);
		panelDeducciones.add(scrollPaneDatosDeducciones);

		JLabel lblicon = new JLabel();
		lblicon.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("icons8_Search_32px_2.png"))));
		lblicon.setBounds(22, 59, 46, 32);
		panelDeducciones.add(lblicon);

		JButton btnNuevaDeduccion = new JButton("Nueva Deduccion");
		btnNuevaDeduccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblComentario.setVisible(true);
				lblNuevaDed.setVisible(true);
				textFieldNuevaDed.setVisible(true);
				textFieldComentario.setVisible(true);
				btnAgregarDed.setVisible(true);
			}
		});
		btnNuevaDeduccion.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("add.png"))));
		btnNuevaDeduccion.setBounds(510, 59, 179, 32);
		panelDeducciones.add(btnNuevaDeduccion);


		lblNuevaDed.setBounds(856, 120, 159, 14);
		panelDeducciones.add(lblNuevaDed);

		textFieldNuevaDed = new JTextField();
		textFieldNuevaDed.setBounds(856, 145, 353, 30);
		panelDeducciones.add(textFieldNuevaDed);
		textFieldNuevaDed.setColumns(10);

		lblComentario.setBounds(856, 186, 159, 14);
		panelDeducciones.add(lblComentario);

		textFieldComentario = new JTextField();
		textFieldComentario.setColumns(10);
		textFieldComentario.setBounds(856, 211, 353, 30);
		panelDeducciones.add(textFieldComentario);
		btnAgregarDed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertarNuevaDeduccion();
				mostrarDatosDeducciones("");
				lblNuevaDed.setVisible(false);
				lblNuevaDed.setText("");
				lblComentario.setVisible(false);
				lblComentario.setText("");
				textFieldNuevaDed.setVisible(false);
				textFieldNuevaDed.setText("");
				textFieldComentario.setVisible(false);
				textFieldComentario.setText("");
				btnAgregarDed.setVisible(false);

			}
		});


		btnAgregarDed.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("add.png"))));
		btnAgregarDed.setBounds(975, 252, 149, 30);
		panelDeducciones.add(btnAgregarDed);

		lblFondo.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("walll.png"))));
		lblFondo.setBounds(0, 0, 1540, 702);
		panelDeducciones.add(lblFondo);


		panelRegimen.setBackground(SystemColor.controlHighlight);
		panelRegimen.setBorder(new TitledBorder(null, "Clave Regimen de Contrataci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(176, 196, 222)));
		//panelRegimen.setBackground(new Color(147,140,147));
		tabbedPaneCatalogoPestañas.addTab("Regimen de Contratación", null, panelRegimen, null);
		panelRegimen.setLayout(null);
		textFieldBuscarReg.setBackground(SystemColor.controlHighlight);

		textFieldBuscarReg.setBounds(67, 60, 305, 32);
		textFieldBuscarReg.setColumns(10);
		panelRegimen.add(textFieldBuscarReg);

		JButton btnBuscarReg = new JButton("Buscar");
		btnBuscarReg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				groupButtonReg.add(rdbtnClaveReg);
				groupButtonReg.add(rdbtnDescripcionReg);
				if(rdbtnClaveReg.isSelected()){
					atributo = "CLAVE";
					mostrarDatosRegimen(textFieldBuscarReg.getText());
				}else if(rdbtnDescripcionReg.isSelected()){
					atributo = "DESCRIPCION";
					mostrarDatosRegimen(textFieldBuscarReg.getText());
				}else {
					JOptionPane.showMessageDialog(null, "No se ha seleccionado ningun tipo de busqueda");
				}
			}
		});
		btnBuscarReg.setBounds(400, 59, 119, 32);
		btnBuscarReg.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("icons8_Search_32px_2.png"))));
		panelRegimen.add(btnBuscarReg);

		JScrollPane scrollPaneRegimen = new JScrollPane();
		scrollPaneRegimen.setViewportBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		scrollPaneRegimen.setBounds(22, 117, 965, 213);
		scrollPaneRegimen.setViewportView(tableDatosRegimen);
		panelRegimen.add(scrollPaneRegimen);
		rdbtnClaveReg.setBackground(SystemColor.controlHighlight);
		//rdbtnClaveReg.setBackground(new Color(147,140,147));

		rdbtnClaveReg.setBounds(22, 29, 109, 23);
		panelRegimen.add(rdbtnClaveReg);
		rdbtnDescripcionReg.setBackground(SystemColor.controlHighlight);
		//rdbtnDescripcionReg.setBackground(new Color(147,140,147));

		rdbtnDescripcionReg.setBounds(153, 29, 109, 23);
		panelRegimen.add(rdbtnDescripcionReg);

		JLabel lbliconreg = new JLabel();
		lbliconreg.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("icons8_Search_32px_2.png"))));
		lbliconreg.setBounds(22, 60, 46, 32);
		panelRegimen.add(lbliconreg);

		JPanel panelBancos = new JPanel();
		panelBancos.setBackground(SystemColor.controlHighlight);
		panelBancos.setBorder(new TitledBorder(null, "Clave de Banco", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(176, 196, 222)));
		//panelBancos.setBackground(new Color(147,140,147));
		tabbedPaneCatalogoPestañas.addTab("Catálogo de Bancos", null, panelBancos, null);
		panelBancos.setLayout(null);
		rdbtnClaveBanco.setBackground(SystemColor.controlHighlight);
		//rdbtnClaveBanco.setBackground(new Color(147,140,147));


		rdbtnClaveBanco.setBounds(22, 29, 109, 23);
		panelBancos.add(rdbtnClaveBanco);
		rdbtnNombreBanco.setBackground(SystemColor.controlHighlight);
		//rdbtnNombreBanco.setBackground(new Color(147,140,147));

		rdbtnNombreBanco.setBounds(153, 29, 109, 23);
		panelBancos.add(rdbtnNombreBanco);
		textFieldBuscarBanco.setBackground(SystemColor.controlHighlight);

		textFieldBuscarBanco.setBounds(67, 60, 305, 32);
		panelBancos.add(textFieldBuscarBanco);
		textFieldBuscarBanco.setColumns(10);

		JButton btnBuscarBanco = new JButton("Buscar");
		btnBuscarBanco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				groupButtonBanco.add(rdbtnClaveBanco);
				groupButtonBanco.add(rdbtnNombreBanco);
				if(rdbtnClaveBanco.isSelected()){
					atributo = "CLAVE";
					mostrarDatosBanco(textFieldBuscarBanco.getText());
				}else if(rdbtnNombreBanco.isSelected()){
					atributo = "NOMBRE_CORTO";
					mostrarDatosBanco(textFieldBuscarBanco.getText());
				}else {
					JOptionPane.showMessageDialog(null, "No se ha seleccionado ningun tipo de busqueda");
				}

			}
		});
		btnBuscarBanco.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("icons8_Search_32px_2.png"))));
		btnBuscarBanco.setBounds(400, 59, 119, 32);
		panelBancos.add(btnBuscarBanco);

		JScrollPane scrollPaneBancos = new JScrollPane();
		scrollPaneBancos.setViewportBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		scrollPaneBancos.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneBancos.setBounds(22, 114, 1181, 475);
		scrollPaneBancos.setViewportView(tableDatosBanco);
		panelBancos.add(scrollPaneBancos);

		JLabel lbliconBancos = new JLabel();
		lbliconBancos.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("icons8_Search_32px_2.png"))));
		lbliconBancos.setBounds(22, 59, 46, 32);
		panelBancos.add(lbliconBancos);

		panelRiesgos.setBackground(SystemColor.controlHighlight);
		panelRiesgos.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Clave Riesgos de Puestos", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(176, 196, 222)));
		//panelRiesgos.setBackground(new Color(147,140,147));
		tabbedPaneCatalogoPestañas.addTab("Riesgos de Puestos", null, panelRiesgos, null);
		panelRiesgos.setLayout(null);
		rdbtnClaveRiesgos.setBackground(SystemColor.controlHighlight);


		//rdbtnClaveRiesgos.setBackground(new Color(147,140,147));
		rdbtnClaveRiesgos.setBounds(22, 29, 109, 23);
		panelRiesgos.add(rdbtnClaveRiesgos);


		rdbtnDescripcionRiesgos.setBackground(SystemColor.controlHighlight);
		rdbtnDescripcionRiesgos.setBounds(153, 29, 109, 23);
		panelRiesgos.add(rdbtnDescripcionRiesgos);

		textFieldBuscarRiesgos = new JTextField();
		textFieldBuscarRiesgos.setBackground(SystemColor.controlHighlight);
		textFieldBuscarRiesgos.setBounds(68, 60, 305, 32);
		panelRiesgos.add(textFieldBuscarRiesgos);
		textFieldBuscarRiesgos.setColumns(10);

		JButton btnBuscarRiesgos = new JButton("Buscar");
		btnBuscarRiesgos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				groupButtonRiesgos.add(rdbtnClaveRiesgos);
				groupButtonRiesgos.add(rdbtnDescripcionRiesgos);
				if(rdbtnClaveRiesgos.isSelected()){
					atributo = "CLAVE";
					mostrarDatosRiesgos(textFieldBuscarRiesgos.getText());
				}else if(rdbtnDescripcionRiesgos.isSelected()){
					atributo = "NOMBRE_CORTO";
					mostrarDatosRiesgos(textFieldBuscarRiesgos.getText());
				}else {
					JOptionPane.showMessageDialog(null, "No se ha seleccionado ningun tipo de busqueda");
				}



			}
		});
		btnBuscarRiesgos.setBounds(401, 59, 119, 32);
		btnBuscarRiesgos.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("icons8_Search_32px_2.png"))));
		panelRiesgos.add(btnBuscarRiesgos);

		JScrollPane scrollPaneRiesgos = new JScrollPane();
		scrollPaneRiesgos.setViewportBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		scrollPaneRiesgos.setBounds(22, 121, 965, 155);
		scrollPaneRiesgos.setViewportView(tableDatosRiesgos);
		panelRiesgos.add(scrollPaneRiesgos);

		JLabel lbliconRiesgos = new JLabel();
		lbliconRiesgos.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("icons8_Search_32px_2.png"))));
		lbliconRiesgos.setBounds(22, 59, 46, 32);
		panelRiesgos.add(lbliconRiesgos);

		panelTipoIncapacidad.setBackground(SystemColor.controlHighlight);
		panelTipoIncapacidad.setBorder(new TitledBorder(null, "Clave de Incapacidad", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(176, 196, 222)));
		//panelTipoIncapacidad.setBackground(new Color(147,140,147));
		tabbedPaneCatalogoPestañas.addTab("Tipo de incapacidad", null, panelTipoIncapacidad, null);
		panelTipoIncapacidad.setLayout(null);
		rdbtnClaveIncapacidad.setBackground(SystemColor.controlHighlight);
		//rdbtnClaveIncapacidad.setBackground(new Color(147,140,147));
		rdbtnClaveIncapacidad.setBounds(22, 29, 109, 23);

		panelTipoIncapacidad.add(rdbtnClaveIncapacidad);
		rdbtnDescripcionIncap.setBackground(SystemColor.controlHighlight);
		//rdbtnDescripcionIncap.setBackground(new Color(147,140,147));
		rdbtnDescripcionIncap.setBounds(153, 29, 109, 23);
		panelTipoIncapacidad.add(rdbtnDescripcionIncap);
		textFieldBuscarIncap.setBackground(SystemColor.controlHighlight);

		textFieldBuscarIncap.setBounds(62, 59, 305, 32);
		textFieldBuscarIncap.setColumns(10);
		panelTipoIncapacidad.add(textFieldBuscarIncap);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				groupButtonIncapacidad.add(rdbtnClaveIncapacidad);
				groupButtonIncapacidad.add(rdbtnDescripcionIncap);
				if(rdbtnClaveIncapacidad.isSelected()){
					atributo = "CLAVE";
					mostrarDatosIncapacidad(textFieldBuscarIncap.getText());
				}else if(rdbtnDescripcionIncap.isSelected()){
					atributo = "DESCRIPCION";
					mostrarDatosIncapacidad(textFieldBuscarIncap.getText());
				}else {
					JOptionPane.showMessageDialog(null, "No se ha seleccionado ningun tipo de busqueda");
				}




			}
		});
		btnNewButton.setBounds(404, 59, 119, 32);
		btnNewButton.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("icons8_Search_32px_2.png"))));
		panelTipoIncapacidad.add(btnNewButton);

		JScrollPane scrollPaneIncapacidad = new JScrollPane();
		scrollPaneIncapacidad.setViewportBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		scrollPaneIncapacidad.setBounds(22, 121, 557, 95);
		scrollPaneIncapacidad.setViewportView(tableDatosIncapacidad);
		panelTipoIncapacidad.add(scrollPaneIncapacidad);

		JLabel lbliconincap = new JLabel();
		lbliconincap.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("icons8_Search_32px_2.png"))));
		lbliconincap.setBounds(22, 59, 46, 32);
		panelTipoIncapacidad.add(lbliconincap);


		panelPuestos.setBackground(SystemColor.controlHighlight);
		panelPuestos.setBorder(new TitledBorder(null, "Puestos", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(176, 196, 222)));
		//panelPuestos.setBackground(new Color(147,140,147));
		tabbedPaneCatalogoPestañas.addTab("Puestos", null, panelPuestos, null);
		tabbedPaneCatalogoPestañas.setBackgroundAt(6, Color.YELLOW);
		panelPuestos.setLayout(null);

		JRadioButton radioButtonClavePuesto = new JRadioButton("Clave");
		radioButtonClavePuesto.setBackground(SystemColor.controlHighlight);
		//radioButtonClavePuesto.setBackground(new Color(147,140,147));
		radioButtonClavePuesto.setBounds(20, 29, 109, 23);
		panelPuestos.add(radioButtonClavePuesto);

		JRadioButton radioButtonDescripcionPuesto = new JRadioButton("Descripción");
		radioButtonDescripcionPuesto.setBackground(SystemColor.controlHighlight);
		//radioButtonDescripcionPuesto.setBackground(new Color(147,140,147));
		radioButtonDescripcionPuesto.setBounds(153, 29, 109, 23);
		panelPuestos.add(radioButtonDescripcionPuesto);

		textFieldBuscarPuesto = new JTextField();
		textFieldBuscarPuesto.setBackground(SystemColor.controlHighlight);
		textFieldBuscarPuesto.setColumns(10);
		textFieldBuscarPuesto.setBounds(64, 60, 305, 32);
		panelPuestos.add(textFieldBuscarPuesto);

		JButton btnBuscarPuesto = new JButton("Buscar");
		//btnBuscarPuesto.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("icons8_Search_32px_2.png"))));
		btnBuscarPuesto.setBounds(399, 59, 119, 32);
		panelPuestos.add(btnBuscarPuesto);

		JScrollPane scrollPanePuestos = new JScrollPane();
		scrollPanePuestos.setViewportBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		scrollPanePuestos.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPanePuestos.setBounds(20, 119, 965, 442);
		scrollPanePuestos.setViewportView(tableDatosPuestos);
		panelPuestos.add(scrollPanePuestos);

		JButton btnNewButton_1 = new JButton("Incrementar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JPasswordField jpf = new JPasswordField();
				JLabel titulo = new JLabel ("Ingrese su Clave de Autorización");
				JOptionPane.showConfirmDialog (null, new Object[]{titulo, jpf}, "Información", JOptionPane.OK_CANCEL_OPTION);
				System.out.println("El usuario ha escrito: "+jpf.getText());
				if(jpf.getText().equalsIgnoreCase("NYCHSNFCO")) {
					windowOpenede(e);
					mostrarDatosPuestos("");
				}else if(jpf.getText()!="NYCHSNFCO"){
					JOptionPane.showMessageDialog(null, "La clave de autorización para incrementar el salario es incorrecta, favor de verificar con su superior");
				}else {
					JOptionPane.showMessageDialog(null, "La clave de autorización para incrementar el salario es incorrecta, favor de verificar con su superior");
				}
			}
		});
		btnNewButton_1.setBounds(995, 191, 163, 30);
		btnNewButton_1.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("add.png"))));
		panelPuestos.add(btnNewButton_1);

		textFieldIncremento = new JTextField();
		textFieldIncremento.setBounds(995, 150, 52, 30);
		panelPuestos.add(textFieldIncremento);
		textFieldIncremento.setColumns(10);

		JLabel lblNewLabel = new JLabel("Incremento de Salario");
		lblNewLabel.setBounds(995, 119, 163, 14);
		panelPuestos.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("%");
		lblNewLabel_1.setBounds(1051, 158, 46, 14);
		panelPuestos.add(lblNewLabel_1);

		JLabel lbliconpuestos = new JLabel();
		lbliconpuestos.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("icons8_Search_32px_2.png"))));
		lbliconpuestos.setBounds(20, 59, 46, 32);
		panelPuestos.add(lbliconpuestos);

		JButton btnNuevoPuesto = new JButton("Nuevo Puesto");
		btnNuevoPuesto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblNombrePuesto.setVisible(true);
				lblSalario.setVisible(true);
				textFieldNombrePuesto.setVisible(true);
				textFieldSalario.setVisible(true);
				btnAgregarPuesto.setVisible(true);
			}
		});
		btnNuevoPuesto.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("add.png"))));
		btnNuevoPuesto.setBounds(523, 59, 154, 33);
		panelPuestos.add(btnNuevoPuesto);


		lblNombrePuesto.setBounds(995, 278, 137, 14);
		panelPuestos.add(lblNombrePuesto);

		textFieldNombrePuesto = new JTextField();
		textFieldNombrePuesto.setBounds(995, 298, 260, 30);
		panelPuestos.add(textFieldNombrePuesto);
		textFieldNombrePuesto.setColumns(10);

		lblSalario.setBounds(995, 339, 137, 14);
		panelPuestos.add(lblSalario);

		textFieldSalario = new JTextField();
		textFieldSalario.setColumns(10);
		textFieldSalario.setBounds(995, 359, 260, 30);
		panelPuestos.add(textFieldSalario);
		btnAgregarPuesto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertarNuevoPuesto();
				mostrarDatosPuestos("");
				lblNombrePuesto.setVisible(false);
				lblNombrePuesto.setText("");
				lblSalario.setVisible(false);
				lblSalario.setText("");
				textFieldNombrePuesto.setVisible(false);
				textFieldNombrePuesto.setText("");
				textFieldSalario.setVisible(false);
				textFieldSalario.setText("");
				btnAgregarPuesto.setVisible(false);
			}
		});


		btnAgregarPuesto.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("add.png"))));
		btnAgregarPuesto.setBounds(1065, 400, 130, 30);
		//		tabbedPaneCatalogoPestañas.setBackgroundAt(7, new Color(255, 255, 204));
		panelPuestos.add(btnAgregarPuesto);


		panelDatosISR.setBackground(SystemColor.controlHighlight);
		panelDatosISR.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos ISR", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(176, 196, 222)));
		//panelDatosISR.setBackground(new Color(147,140,147));
		tabbedPaneCatalogoPestañas.addTab("Datos ISR", null, panelDatosISR, null);
		tabbedPaneCatalogoPestañas.setBackgroundAt(7, Color.YELLOW);
		panelDatosISR.setLayout(null);

		JScrollPane scrollPaneDatosISR = new JScrollPane();
		scrollPaneDatosISR.setBounds(24, 42, 1057, 300);
		scrollPaneDatosISR.setViewportBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		scrollPaneDatosISR.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneDatosISR.setViewportView(tableDatosDatosISR);
		panelDatosISR.add(scrollPaneDatosISR);


		panelSubsidioISR.setBackground(SystemColor.controlHighlight);
		panelSubsidioISR.setBorder(new TitledBorder(null, "Subsidio ISR", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(176, 196, 222)));
		//panelSubsidioISR.setBackground(new Color(147,140,147));
		tabbedPaneCatalogoPestañas.addTab("Subsidio ISR", null, panelSubsidioISR, null);
		tabbedPaneCatalogoPestañas.setBackgroundAt(8, Color.YELLOW);
		panelSubsidioISR.setLayout(null);

		JScrollPane scrollPaneSubsidioISR = new JScrollPane();
		scrollPaneSubsidioISR.setBounds(23, 32, 1057, 340);
		scrollPaneSubsidioISR.setViewportBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		scrollPaneSubsidioISR.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneSubsidioISR.setViewportView(tableDatosSubsidioISR);
		panelSubsidioISR.add(scrollPaneSubsidioISR);

		panelDatosImss.setBackground(SystemColor.controlHighlight);
		panelDatosImss.setBorder(new TitledBorder(null, "Datos IMSS", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(176, 196, 222)));
		//panelDatosImss.setBackground(new Color(147,140,147));
		tabbedPaneCatalogoPestañas.addTab("Datos IMSS", null, panelDatosImss, null);
		tabbedPaneCatalogoPestañas.setBackgroundAt(9, Color.YELLOW);
		panelDatosImss.setLayout(null);

		JScrollPane scrollPaneDatosImss = new JScrollPane();
		scrollPaneDatosImss.setViewportBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		scrollPaneDatosImss.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneDatosImss.setViewportView(tableDatosDatosImss);
		scrollPaneDatosImss.setBounds(22, 34, 1057, 393);
		panelDatosImss.add(scrollPaneDatosImss);
		panelDiasInahabiles.setBackground(SystemColor.controlHighlight);


		tabbedPaneCatalogoPestañas.addTab("Días Inhábiles", null, panelDiasInahabiles, null);
		tabbedPaneCatalogoPestañas.setBackgroundAt(10, Color.YELLOW);
		panelDiasInahabiles.setLayout(null);

		JScrollPane scrollPaneDiasInhabiles = new JScrollPane();
		scrollPaneDiasInhabiles.setViewportBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		scrollPaneDiasInhabiles.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneDiasInhabiles.setBounds(27, 43, 1057, 238);
		scrollPaneDiasInhabiles.setViewportView(tableDatosDiasInhabiles);
		panelDiasInahabiles.add(scrollPaneDiasInhabiles);

		JButton btnAgregar = new JButton("Nueva Fecha");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Color colorSilverLight=Color.RED;
				String frase ="recuerde que para ACTUALIZAR una fecha es dentro de la tabla.";
				int confirmado = JOptionPane.showConfirmDialog(null,"Desea agregar una fecha nueva?,"+ frase +" ");

				if (JOptionPane.OK_OPTION == confirmado) {
					//System.out.println("confirmado");
					FormularioPrincipal.desktopPane.add(fechaNuevaInhabil);
					fechaNuevaInhabil.show();
					fechaNuevaInhabil.setBounds(400, 200, 400, 300);
					fechaNuevaInhabil.setIconifiable(true);
					fechaNuevaInhabil.setClosable(true);
					fechaNuevaInhabil.setResizable(true);
					fechaNuevaInhabil.setMaximizable(true);
					fechaNuevaInhabil.setVisible(true);
					fechaNuevaInhabil.toFront();





				}else if(JOptionPane.NO_OPTION == confirmado){
					//System.out.println("NO_OPTION");

				}else {
					JOptionPane.showMessageDialog(null, "No se realizará ningun movimiento","Advertencia", JOptionPane.WARNING_MESSAGE );
				}


			}
		});
		btnAgregar.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("accept.png"))));
		btnAgregar.setBounds(48, 321, 135, 31);
		panelDiasInahabiles.add(btnAgregar);

		JButton btnEliminar = new JButton("Eliminar Fecha");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				eliminarLogicoDiaInhabil();
				mostrarDiasInhabiles("");

			}
		});
		btnEliminar.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("delete.png"))));
		btnEliminar.setBounds(206, 321, 158, 31);
		panelDiasInahabiles.add(btnEliminar);

		JButton btnREFRESH = new JButton("Refresh");
		btnREFRESH.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("accept.png"))));
		btnREFRESH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarDiasInhabiles("");
			}
		});
		btnREFRESH.setBounds(967, 11, 117, 30);
		panelDiasInahabiles.add(btnREFRESH);

		panelCalsificacion.setBackground(SystemColor.controlHighlight);
		tabbedPaneCatalogoPestañas.addTab("Clasificación de Dispersión", null, panelCalsificacion, null);
		tabbedPaneCatalogoPestañas.setBackgroundAt(11, Color.YELLOW);
		panelCalsificacion.setLayout(null);

		JButton btnNewButton_3 = new JButton("Actualizar");
		btnNewButton_3.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("accept.png"))));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblCalsifi.setVisible(true); 
				btnActualizarCalsifi.setVisible(true);
				comboBoxClasif2.setVisible(true);
			}
		});
		btnNewButton_3.setBounds(367, 55, 129, 35);
		panelCalsificacion.add(btnNewButton_3);


		comboBoxClasif2.setBounds(832, 123, 164, 30);
		panelCalsificacion.add(comboBoxClasif2);
		btnGuardarNuevaclasif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				insertNuevaClasificacion();
				comboBoxClasif.removeAllItems();
				comboBoxClasif2.removeAllItems();
				ArrayList<String> listaClasif = new ArrayList<String>();
				listaClasif = buscarClasificacion();
				comboBoxClasif.addItem("Seleccione Una");
				comboBoxClasif2.addItem("Seleccione Una");
				for (int i = 0; i < listaClasif.size(); i++) {
					comboBoxClasif.addItem(listaClasif.get(i));
					comboBoxClasif2.addItem(listaClasif.get(i));
				}


				lblNuevaClasifi.setVisible(false);
				textFieldNuevaClasifi.setVisible(false);
				textFieldNuevaClasifi.setText("");
				btnGuardarNuevaclasif.setVisible(false);
			}
		});
		btnGuardarNuevaclasif.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("add.png"))));
		btnGuardarNuevaclasif.setBounds(832, 279, 164, 30);
		panelCalsificacion.add(btnGuardarNuevaclasif);


		comboBoxClasif.setBounds(20, 55, 198, 35);
		panelCalsificacion.add(comboBoxClasif);

		JButton btnNewButton_4 = new JButton("Nueva");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				lblNuevaClasifi.setVisible(true);
				textFieldNuevaClasifi.setVisible(true);
				btnGuardarNuevaclasif.setVisible(true);

			}
		});
		btnNewButton_4.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("add.png"))));
		btnNewButton_4.setBounds(506, 55, 129, 35);
		panelCalsificacion.add(btnNewButton_4);


		lblNuevaClasifi.setBounds(832, 221, 164, 14);
		panelCalsificacion.add(lblNuevaClasifi);

		//		JLabel lbliicon = new JLabel();
		//		lbliicon.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("icons8_Search_32px_2.png"))));
		//		lbliicon.setBounds(20, 60, 46, 28);
		//		panel.add(lbliicon);

		//		textFieldEmpClasif = new JTextField();
		//		textFieldEmpClasif.addKeyListener(new KeyAdapter() {
		//			@Override
		//			public void keyReleased(KeyEvent arg0) {
		//				rowSortere.setRowFilter(RowFilter.regexFilter(textFieldEmpClasif.getText().toUpperCase(), IdBusquedaEmplee));
		//			}
		//		});
		//		textFieldEmpClasif.setColumns(10);
		//		textFieldEmpClasif.setBorder(null);
		//		textFieldEmpClasif.setBackground(SystemColor.controlHighlight);
		//		textFieldEmpClasif.setBounds(57, 60, 308, 25);
		//		panel.add(textFieldEmpClasif);

		//		JSeparator separator = new JSeparator();
		//		separator.setForeground(new Color(176, 196, 222));
		//		separator.setBackground(new Color(176, 196, 222));
		//		separator.setBounds(20, 86, 360, 2);
		//		panel.add(separator);

		JScrollPane scrollPaneClasifi = new JScrollPane();
		scrollPaneClasifi.setViewportBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		scrollPaneClasifi.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneClasifi.setBounds(20, 99, 802, 396);
		scrollPaneClasifi.setViewportView(tableClasificacion);
		panelCalsificacion.add(scrollPaneClasifi);

		lblCalsifi.setBounds(832, 105, 164, 14);
		panelCalsificacion.add(lblCalsifi);

		lblNumeroRegistro.setBounds(157, 506, 46, 14);
		panelCalsificacion.add(lblNumeroRegistro);

		//		JButton btnActualizar = new JButton("Actualizar Uno");
		//		btnActualizar.addActionListener(new ActionListener() {
		//			public void actionPerformed(ActionEvent e) {
		//				lblEmpUno.setVisible(true);
		//			}
		//		});
		//		btnActualizar.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("accept.png"))));
		//		btnActualizar.setBounds(645, 55, 177, 35);
		//		panelCalsificacion.add(btnActualizar);

		btnActualizarCalsifi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmado = JOptionPane.showConfirmDialog(null,"¿Seguro que desea cambiar la clasificación, esto cambia la dispersión en el Banco?");

				if (JOptionPane.OK_OPTION == confirmado) {
					//System.out.println("confirmado");

					updateEmpClasificacion();
					mostrarEmpleadosConClasificacion(comboBoxClasif.getSelectedIndex());

					lblCalsifi.setVisible(false);
					comboBoxClasif2.setVisible(false);
					btnActualizarCalsifi.setVisible(false);

				}else if(JOptionPane.NO_OPTION == confirmado){
					//System.out.println("NO_OPTION");

					lblCalsifi.setVisible(false); 
					comboBoxClasif2.setVisible(false);
					btnActualizarCalsifi.setVisible(false); 


				}else {
					JOptionPane.showMessageDialog(null, "No se ha realizado ningún movimiento","Advertencia", JOptionPane.WARNING_MESSAGE );
				}


			}
		});
		btnActualizarCalsifi.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("accept.png"))));
		btnActualizarCalsifi.setBounds(832, 163, 164, 30);
		panelCalsificacion.add(btnActualizarCalsifi);

		textFieldNuevaClasifi = new JTextField();
		textFieldNuevaClasifi.setBounds(832, 244, 164, 30);
		panelCalsificacion.add(textFieldNuevaClasifi);
		textFieldNuevaClasifi.setColumns(10);

		//		JRadioButton radioButtonN2J = new JRadioButton("N2J");
		//		groupButton.add(radioButtonN2J);
		//		radioButtonN2J.setBackground(SystemColor.controlHighlight);
		//		radioButtonN2J.setBounds(20, 65, 64, 23);
		//		panel.add(radioButtonN2J);

		//		JRadioButton radioButtonN2D = new JRadioButton("N2D");
		//		groupButton.add(radioButtonN2D);
		//		radioButtonN2D.setBackground(SystemColor.controlHighlight);
		//		radioButtonN2D.setBounds(86, 65, 64, 23);
		//		panel.add(radioButtonN2D);

		//		JRadioButton radioButtonN3J = new JRadioButton("N3J");
		//		groupButton.add(radioButtonN3J);
		//		radioButtonN3J.setBackground(SystemColor.controlHighlight);
		//		radioButtonN3J.setBounds(152, 65, 64, 23);
		//		panel.add(radioButtonN3J);

		//		JRadioButton radioButtonR = new JRadioButton("R");
		//		groupButton.add(radioButtonR);
		//		radioButtonR.setBackground(SystemColor.controlHighlight);
		//		radioButtonR.setBounds(218, 65, 54, 23);
		//		panel.add(radioButtonR);

		JButton btnNewButton_2 = new JButton("Buscar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(comboBoxClasif.getSelectedIndex()>=0){
					mostrarEmpleadosConClasificacion(comboBoxClasif.getSelectedIndex());
				}

				else {
					JOptionPane.showMessageDialog(null, "No se ha seleccionado ningun tipo de busqueda");
				}
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("icons8_Search_32px_2.png"))));
		btnNewButton_2.setBounds(228, 55, 129, 35);
		panelCalsificacion.add(btnNewButton_2);


		lblNumReg.setBounds(20, 506, 130, 14);
		panelCalsificacion.add(lblNumReg);

		JLabel lblClasificacin = new JLabel("Clasificación:");
		lblClasificacin.setForeground(Color.BLACK);
		lblClasificacin.setBounds(20, 36, 91, 14);
		panelCalsificacion.add(lblClasificacin);

		lblEmpUno.setBounds(1012, 36, 177, 21);
		panelCalsificacion.add(lblEmpUno);


		//		JButton btnNewButton_1 = new JButton("Mostrar Calendario");
		//		btnNewButton_1.addActionListener(new ActionListener() {
		//			public void actionPerformed(ActionEvent arg0) {
		//				
		//				InternalFrameCalendario calendario = new InternalFrameCalendario();
		//				calendario.main();
		//				
		//				
		//			}//fin del evento
		//		});
		//		btnNewButton_1.setBounds(10, 22, 144, 30);
		//		panelDiasInahabiles.add(btnNewButton_1);
		//		
		//		JCalendar calendar = new JCalendar();
		//		Color colorSilverLight=new Color(176, 196, 222);
		//calendar.setTodayButtonVisible(true);

		// Cambiar color de letra del numero de día 
		//calendar.setForeground(colorSilverLight);

		// Cambiar color de letra del dia domingo
		//calendar.setSundayForeground(Color.BLUE);

		// Cambiar color de letra de semana
		//calendar.setWeekdayForeground(Color.RED);

		//		calendar.setBounds(29, 80, 1027, 491);
		//		panelDiasInahabiles.add(calendar);
		//		

		//		lblFechaSeleccionada.setBounds(29, 607, 234, 30);
		//		panelDiasInahabiles.add(lblFechaSeleccionada);

		lblFondoRegimen.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("walll.png"))));
		lblFondoRegimen.setBounds(0, 0, 1905, 998);
		panelRegimen.add(lblFondoRegimen);

		lblFondoBancos.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("walll.png"))));
		lblFondoBancos.setBounds(0, 0, 1905, 998);
		panelBancos.add(lblFondoBancos);

		lblFondoriesgos.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("walll.png"))));
		lblFondoriesgos.setBounds(0, 0, 1905, 998);
		panelRiesgos.add(lblFondoriesgos);

		lblFondoTipoIncapacidad.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("walll.png"))));
		lblFondoTipoIncapacidad.setBounds(0, 0, 1905, 998);
		panelTipoIncapacidad.add(lblFondoTipoIncapacidad);

		JButton btnNewButton_5 = new JButton("Cambio de Puesto");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				InternalFrameCambioPuestos internalFrameCambioPuestos = new InternalFrameCambioPuestos();
				FormularioPrincipal.desktopPane.add(internalFrameCambioPuestos);
				internalFrameCambioPuestos.lblTipoNominaOculto.setText(FormularioPrincipal.lblIdTipoNomina.getText());
				internalFrameCambioPuestos.show();
				internalFrameCambioPuestos.setFrameIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("user_add.png"))));
				internalFrameCambioPuestos.setIconifiable(true);
				internalFrameCambioPuestos.setClosable(true);
				internalFrameCambioPuestos.setResizable(true);
				internalFrameCambioPuestos.setMaximizable(true);
				internalFrameCambioPuestos.setVisible(true);
				internalFrameCambioPuestos.toFront();
				internalFrameCambioPuestos.getPanelInfoEmpleado().setVisible(false);
				//				internalFrameCambioPuestos.lblclave.setVisible(false);
				//				internalFrameCambioPuestos.lblIdPuestoCam.setVisible(false);
				//				internalFrameCambioPuestos.lblIdDependenciaCam.setVisible(false);


			}
		});
		btnNewButton_5.setBounds(687, 60, 163, 32);
		panelPuestos.add(btnNewButton_5);

		lblFondoPuestos.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("walll.png"))));
		lblFondoPuestos.setBounds(0, 0, 1905, 998);
		panelPuestos.add(lblFondoPuestos);

		lblFondoDatosISR.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("walll.png"))));
		lblFondoDatosISR.setBounds(0, 0, 1905, 998);
		panelDatosISR.add(lblFondoDatosISR);

		lblFondoSubsidioISR.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("walll.png"))));
		lblFondoSubsidioISR.setBounds(0, 0, 1905, 998);
		panelSubsidioISR.add(lblFondoSubsidioISR);

		lblFondoDatosIMSS.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("walll.png"))));
		lblFondoDatosIMSS.setBounds(0, 0, 1905, 998);
		panelDatosImss.add(lblFondoDatosIMSS);

		lblFondoDiasInhabiles.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("walll.png"))));
		lblFondoDiasInhabiles.setBounds(0, 0, 1905, 998);
		panelDiasInahabiles.add(lblFondoDiasInhabiles);

		lblFondoClasificacion.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("walll.png"))));
		lblFondoClasificacion.setBounds(0, 0, 1905, 998);
		panelCalsificacion.add(lblFondoClasificacion);

		tabbedPaneCatalogoPestañas.addTab("Tabla Mensual ISR", null, panelTablaMensualISR, null);
		tabbedPaneCatalogoPestañas.setBackgroundAt(12, Color.YELLOW);
		panelTablaMensualISR.setLayout(null);

		JScrollPane scrollPaneTMensualISR = new JScrollPane();
		scrollPaneTMensualISR.setViewportBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		scrollPaneTMensualISR.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneTMensualISR.setBounds(23, 57, 1057, 230);
		scrollPaneTMensualISR.setViewportView(tableMensualISR);
		panelTablaMensualISR.add(scrollPaneTMensualISR);

		lblFondoTablaMensualISR.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("walll.png"))));
		lblFondoTablaMensualISR.setBounds(0, 0, 1905, 998);
		panelTablaMensualISR.add(lblFondoTablaMensualISR);

		tabbedPaneCatalogoPestañas.addTab("Tabla Mensual Subsidio ISR", null, panelTablaMensualSubsidioISR, null);
		tabbedPaneCatalogoPestañas.setBackgroundAt(13, Color.YELLOW);
		panelTablaMensualSubsidioISR.setLayout(null);

		JScrollPane scrollPaneTablaMensualSubsidioISR = new JScrollPane();
		scrollPaneTablaMensualSubsidioISR.setViewportBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		scrollPaneTablaMensualSubsidioISR.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneTablaMensualSubsidioISR.setBounds(32, 41, 1057, 304);
		scrollPaneTablaMensualSubsidioISR.setViewportView(tableMensualSubisidioISR);
		panelTablaMensualSubsidioISR.add(scrollPaneTablaMensualSubsidioISR);

		lblFondoTablaMensualSubsidioISR.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("walll.png"))));
		lblFondoTablaMensualSubsidioISR.setBounds(0, 0, 1905, 998);
		panelTablaMensualSubsidioISR.add(lblFondoTablaMensualSubsidioISR);


		tabbedPaneCatalogoPestañas.addTab("Datos Iniciales", null, panelDatosIniciales, null);
		panelDatosIniciales.setLayout(null);
		
		JLabel lblCantidadAyudaDespensa = new JLabel("Cantidad para Ayuda de Despensa");
		lblCantidadAyudaDespensa.setBounds(10, 21, 191, 14);
		panelDatosIniciales.add(lblCantidadAyudaDespensa);
		
		textFieldCantidadAyudaDespensa = new JTextField();
		textFieldCantidadAyudaDespensa.setText("102.68");
		textFieldCantidadAyudaDespensa.setBounds(211, 11, 55, 30);
		panelDatosIniciales.add(textFieldCantidadAyudaDespensa);
		textFieldCantidadAyudaDespensa.setColumns(10);
		lblPorcentajeParaLa.setBounds(10, 62, 202, 14);
		
		panelDatosIniciales.add(lblPorcentajeParaLa);
		textFieldPorcentajeAyudaDespensa.setText(".40");
		textFieldPorcentajeAyudaDespensa.setColumns(10);
		textFieldPorcentajeAyudaDespensa.setBounds(211, 52, 55, 30);
		
		panelDatosIniciales.add(textFieldPorcentajeAyudaDespensa);
		label.setBounds(276, 62, 21, 14);
		
		panelDatosIniciales.add(label);
		tabbedPaneCatalogoPestañas.setBackgroundAt(14, Color.YELLOW);

		JPanel panelTablaIsrAnual = new JPanel();
		tabbedPaneCatalogoPestañas.addTab("Tabla ISR Anual", null, panelTablaIsrAnual, null);
		panelTablaIsrAnual.setLayout(null);

		JScrollPane scrollPaneIsrAnual = new JScrollPane();
		scrollPaneIsrAnual.setViewportView(tableTablaIsrAnual);
		scrollPaneIsrAnual.setViewportBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		scrollPaneIsrAnual.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneIsrAnual.setBounds(10, 26, 1057, 300);
		panelTablaIsrAnual.add(scrollPaneIsrAnual);

		lblFondoIsrAnual.setBounds(0, 0, 1478, 644);
		lblFondoIsrAnual.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("walll.png"))));
		panelTablaIsrAnual.add(lblFondoIsrAnual);

	}

	//metodo para el tiempo de busqueda
	//Este es el evento que se ejecuta cuando un JFrame se carga
	public void windowOpenede(ActionEvent e){
		final long SLEEP_TIME = 1 * 1000;
		SwingWorker<Void, Void> mySwingWorker = new SwingWorker<Void, Void>(){
			protected Void doInBackground() throws Exception {
				updateSalario();
				return null;
			}
		};


		Window win = SwingUtilities.getWindowAncestor((AbstractButton)e.getSource());
		final JDialog dialog = new JDialog(win, "Actualizando", ModalityType.APPLICATION_MODAL);

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


	public int insertNuevaClasificacion() {
		PoolNYCH nych = new PoolNYCH();
		Connection con =null;
		int resultado = 0;

		String sqlInsert="INSERT INTO dbo.CLASIFICACION_DISPERSION (clave) VALUES ('"+textFieldNuevaClasifi.getText()+"')";
		System.out.println(sqlInsert);
		try {
			con = nych.datasource.getConnection();
			PreparedStatement pps = con.prepareStatement(sqlInsert);
			pps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Datos Guardados");
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
				//JOptionPane.showMessageDialog(null, ep, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
			}
		}
		return resultado;
	}


	public int insertarNuevoPuesto() {
		PoolNYCH nych = new PoolNYCH();
		Connection con =null;
		int resultado = 0;

		DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
		simbolos.setDecimalSeparator('.');
		DecimalFormat formateador = new DecimalFormat("####.##",simbolos);

		String texto = textFieldSalario.getText();
		double valor = 0;
		try
		{
			// parse() lanza una ParseException en caso de fallo que hay que capturar.
			Number numero = formateador.parse(texto);
			valor = numero.doubleValue();
			System.out.println("Valor: "+valor);
		}catch (ParseException e)
		{
			e.printStackTrace();
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: "+ errors );
			JOptionPane.showMessageDialog(null,"Error. El usuario ha escrito algo que no se puede convertir a número");
		}

		String sqlInsert="INSERT INTO dbo.puestos (nombre_puesto,salario) VALUES ('"+textFieldNombrePuesto.getText().toUpperCase()+"','"+ valor +"')";
		System.out.println(sqlInsert);
		try {
			con = nych.datasource.getConnection();
			PreparedStatement pps = con.prepareStatement(sqlInsert);
			pps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Datos Guardados");
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
				//JOptionPane.showMessageDialog(null, ep, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
			}
		}
		return resultado;
	}

	public int insertarNuevaDeduccion() {
		PoolNYCH nych = new PoolNYCH();
		Connection con =null;
		int resultado = 0;

		String sqlInsert="INSERT INTO dbo.ATRIBUTO_TIPO_DEDUCCION (descripcion,comentario) VALUES ('"+textFieldNuevaDed.getText()+"','"+ textFieldComentario.getText() +"')";
		System.out.println(sqlInsert);
		try {
			con = nych.datasource.getConnection();
			PreparedStatement pps = con.prepareStatement(sqlInsert);
			pps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Datos Guardados");
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
				//JOptionPane.showMessageDialog(null, ep, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
			}
		}
		return resultado;
	}

	public int insertNuevaPercepcion() {
		PoolNYCH nych = new PoolNYCH();
		Connection con =null;
		int resultado = 0;

		String sqlInsert="INSERT INTO dbo.ATRIBUTO_TIPO_PERCEPCION (descripcion) VALUES ('"+textFieldNuevaPerp.getText()+"')";
		System.out.println(sqlInsert);
		try {
			con = nych.datasource.getConnection();
			PreparedStatement pps = con.prepareStatement(sqlInsert);
			pps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Datos Guardados");
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
				//JOptionPane.showMessageDialog(null, ep, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
			}
		}
		return resultado;
	}

	public void incrementarSalario(double salar,int noPuesto) {
		PoolNYCH nych = new PoolNYCH();
		Connection con =null;
		String SqlUpdate="update puestos set salario="+salar+" where no_puesto="+ noPuesto+ "";
		System.out.println("update en incremento de salario: "+SqlUpdate);
		//		SqlUpdate="";
		try {
			con = nych.datasource.getConnection();
			PreparedStatement pps = con.prepareStatement(SqlUpdate);
			pps.executeUpdate();
			
			incrementarSalarioEnCalculoNomina(salar, noPuesto);
			
			
			if(FormularioPrincipal.lblIsNomC.getText().equalsIgnoreCase("OK")) {
				
				double ayudaDespensa = Double.parseDouble(textFieldCantidadAyudaDespensa.getText())*.04*14;
				System.out.println("ayuda de despensa: " + ayudaDespensa);
				incrementarSalarioEnReportePercepcionSalario(salar, noPuesto);
				incrementarSalarioEnReportePercepcionAyuda(ayudaDespensa, noPuesto);
				
			}else if(FormularioPrincipal.lblIsNomS.getText().equalsIgnoreCase("OK")) {
				
				double ayudaDespensa = Double.parseDouble(textFieldCantidadAyudaDespensa.getText())*.04*7;
				System.out.println("ayuda de despensa: " + ayudaDespensa);
				incrementarSalarioEnReportePercepcionSalario(salar, noPuesto);
				incrementarSalarioEnReportePercepcionAyuda(ayudaDespensa, noPuesto);
				
			}
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
				JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
			}
		}
	}

	@SuppressWarnings("static-access")
	public void incrementarSalarioEnCalculoNomina(double salar,int noPuesto) {
		System.out.println("Actualizando el salario en calculo nomina");
		PoolNYCH nych = new PoolNYCH();
		Connection con =null;
		String SqlUpdate="update C set C.VALOR_PERCEPCION = "+salar+" from CALCULO_NOMINA as C inner join dbo.puestos as p on p.NO_PUESTO = C.ID_PUESTO where C.ID_PERCEPCION = 1 and C.ID_PUESTO = "+noPuesto+" "; 
		System.out.println("update en calculo nomina: " +SqlUpdate);
		//		SqlUpdate="";
		try {
			con = nych.datasource.getConnection();
			PreparedStatement pps = con.prepareStatement(SqlUpdate);
			pps.executeUpdate();
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
				JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
			}
		}
	}
	
	@SuppressWarnings("static-access")
	public void incrementarSalarioEnCalculoNominaIndividual(Object salar,String noPuesto) {
		System.out.println("Actualizando el salario en calculo nomina");
		PoolNYCH nych = new PoolNYCH();
		Connection con =null;
		String SqlUpdate="update C set C.VALOR_PERCEPCION = "+salar+" from CALCULO_NOMINA as C inner join dbo.puestos as p on p.NO_PUESTO = C.ID_PUESTO where C.ID_PERCEPCION = 1 and C.ID_PUESTO = "+noPuesto+" "; 
		System.out.println("update en calculo nomina: " +SqlUpdate);
		//		SqlUpdate="";
		try {
			con = nych.datasource.getConnection();
			PreparedStatement pps = con.prepareStatement(SqlUpdate);
			pps.executeUpdate();
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
				JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
			}
		}
	}

	@SuppressWarnings("static-access")
	public void incrementarSalarioEnReportePercepcionSalario(double salar,int noPuesto) {
		System.out.println("Actualizando el salario en reporte percepcion salario");
		PoolNYCH nych = new PoolNYCH();
		Connection con =null;
		String SqlUpdate="update R\r\n" + 
				"set R.VALOR_PERCEPCION = "+salar+"\r\n" + 
				"from REPORTE_NOMINA_PERCEPCIONES as R\r\n" + 
				"inner join dbo.puestos as p on p.NO_PUESTO = R.ID_PUESTO \r\n" + 
				"where R.ID_PERCEPCION = 1 and R.ID_PUESTO = "+noPuesto+" and r.id_empleado = 843 and R.periodo = 0"; 
		System.out.println("update en reporte percepcion salario: " +SqlUpdate);
		//		SqlUpdate="";
		try {
			con = nych.datasource.getConnection();
			PreparedStatement pps = con.prepareStatement(SqlUpdate);
			pps.executeUpdate();
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
				JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
			}
		}
	}

	
	@SuppressWarnings("static-access")
	public void incrementarSalarioEnReportePercepcionAyuda(double ayuda,int noPuesto) {
		System.out.println("Actualizando el salario en reporte percepcion ayuda");
		PoolNYCH nych = new PoolNYCH();
		Connection con =null;
		String SqlUpdate="\r\n" + 
				"update R\r\n" + 
				"set R.VALOR_PERCEPCION = "+ayuda+" \r\n" + 
				"from REPORTE_NOMINA_PERCEPCIONES as R\r\n" + 
				"inner join dbo.puestos as p on p.NO_PUESTO = R.ID_PUESTO \r\n" + 
				"where R.ID_PERCEPCION = 26 and R.ID_PUESTO = "+noPuesto+" and r.id_empleado = 843 and R.periodo = 0"; 
		System.out.println("update en reporte percepcion ayuda: " +SqlUpdate);
		//		SqlUpdate="";
		try {
			con = nych.datasource.getConnection();
			PreparedStatement pps = con.prepareStatement(SqlUpdate);
			pps.executeUpdate();
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
				JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
			}
		}
	}


	@SuppressWarnings("static-access")
	public double updateSalario() {
		PoolNYCH nych = new PoolNYCH();
		Connection con =null;
		Statement st;
		ResultSet resultSet;
		String datos[] = new String[2];
		String Sql="select salario,no_puesto from puestos";
		System.out.println(Sql);
		double salario=0;
		double sal=0;
		double salFinal = 0;
		int noPuesto;
		try {
			con = nych.datasource.getConnection();
			st = con.createStatement();
			resultSet = st.executeQuery(Sql);
			while(resultSet.next()) {
				datos[0] = resultSet.getString(1);
				datos[1] = resultSet.getString(2);

				salario = Double.parseDouble(datos[0]);
				sal = Double.parseDouble(datos[0]) * Double.parseDouble(textFieldIncremento.getText()) / 100;
				salFinal = salario + sal;
				System.out.println("salarios: "+salario);
				System.out.println("sal: "+ sal);
				System.out.println("Salario final: "+ salFinal);


				noPuesto = Integer.parseInt(datos[1]);
				System.out.println("noPuesto: " + noPuesto + " ,Salario Aumentado: " + salFinal);

				incrementarSalario(salFinal,noPuesto);


			}//FIN DEL WHILE
			JOptionPane.showMessageDialog(null, "Datos Actualizados***");
			textFieldIncremento.setText("");
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
		return salFinal;
	}

	public static ArrayList<String> selectCalculosNomina() {
		PoolNYCH nych = new PoolNYCH();
		Connection connect = null;
		ResultSet resultSet = null;
		String sqlSelectEjercicio = "SELECT * FROM DBO.CALCULO_NOMINA";
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


	/*
	 * Metodo Para la busquedda del catalogo de percepciones
	 */
	public void mostrarDatosPercepciones(String valor) {
		DefaultTableModel modelo = new DefaultTableModel() {

			public boolean isCellEditable(int row, int column) {
				//return super.isCellEditable(row, column);
				if(column==2){
					return true;
				}else {
					return false;
				}
			}
		};
		modelo.addColumn("CLAVE");
		modelo.addColumn("DESCRIPCION");
		tableDatosPercepciones.setModel(modelo);
		tableDatosPercepciones.setBackground(Color.WHITE);

		JTableHeader th = new JTableHeader();
		Color colorSilverLight=new Color(176, 196, 222);
		Color colorNegro=new Color(0, 0, 0);
		th = tableDatosPercepciones.getTableHeader();
		Font fuente = new Font("Arial", Font.BOLD, 14); 
		th.setFont(fuente); 
		th.setBackground(colorSilverLight);
		th.setForeground(colorNegro);

		TableColumnModel columnModel = tableDatosPercepciones.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(50);
		columnModel.getColumn(1).setPreferredWidth(800);

		String sqlSelect="";
		if(valor.equals("")) {
			sqlSelect = "SELECT clave,descripcion FROM dbo.ATRIBUTO_TIPO_PERCEPCION";
		}else {
			sqlSelect = "SELECT clave,descripcion FROM dbo.ATRIBUTO_TIPO_PERCEPCION WHERE "+ atributo +" "+" = '"+ valor +"'"; 
			System.out.println(sqlSelect);
		}
		String datos[] = new String[2];
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
				modelo.addRow(datos);
			}//FIN DEL WHILE
			tableDatosPercepciones.setModel(modelo);
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

	/*
	 * Metodo Para la busquedda del catalogo de deducciones
	 */
	public void mostrarDatosDeducciones(String valor) {
		DefaultTableModel modeloDed = new DefaultTableModel(){

			public boolean isCellEditable(int row, int column) {
				//return super.isCellEditable(row, column);
				if(column==3){
					return true;
				}else {
					return false;
				}
			}
		};
		modeloDed.addColumn("CLAVE");
		modeloDed.addColumn("DESCRIPCION");
		modeloDed.addColumn("COMENTARIO");
		tableDatosDeducciones.setModel(modeloDed);
		tableDatosDeducciones.setBackground(Color.WHITE);

		JTableHeader th = new JTableHeader();
		Color colorSilverLight=new Color(176, 196, 222);
		Color colorNegro=new Color(0, 0, 0);
		th = tableDatosDeducciones.getTableHeader();
		Font fuente = new Font("Arial", Font.BOLD, 14); 
		th.setFont(fuente); 
		th.setBackground(colorSilverLight);
		th.setForeground(colorNegro);

		TableColumnModel columnModel = tableDatosDeducciones.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(100);
		columnModel.getColumn(1).setPreferredWidth(800);
		columnModel.getColumn(2).setPreferredWidth(700);

		String sqlSelectDed="";
		if(valor.equals("")) {
			sqlSelectDed = "SELECT clave,descripcion,comentario FROM dbo.ATRIBUTO_TIPO_DEDUCCION";
		}else {
			sqlSelectDed = "SELECT clave,descripcion,comentario FROM dbo.ATRIBUTO_TIPO_DEDUCCION WHERE "+atributo+""+"='"+ valor +"'"; 
		}
		String datosDed[] = new String[3];
		PoolNYCH nych = new PoolNYCH();
		Connection con =null;
		Statement st;
		ResultSet resultSet;
		try {
			con = nych.datasource.getConnection();
			st = con.createStatement();
			resultSet = st.executeQuery(sqlSelectDed);
			while(resultSet.next()) {
				datosDed[0] = resultSet.getString(1);
				datosDed[1] = resultSet.getString(2);
				datosDed[2] = resultSet.getString(3);
				modeloDed.addRow(datosDed);
			}//FIN DEL WHILE
			tableDatosDeducciones.setModel(modeloDed);
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
				LOG.info("Excepción: " + ep);
				JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
			}
		}//FIN DEL FINALLY
	}//FIN DEL METODO


	/*
	 * Metodo Para la busquedda del catalogo de Regimen
	 */
	public void mostrarDatosRegimen(String valor) {
		DefaultTableModel modeloReg = new DefaultTableModel(){

			public boolean isCellEditable(int row, int column) {
				//return super.isCellEditable(row, column);
				if(column==2){
					return true;
				}else {
					return false;
				}
			}
		};
		modeloReg.addColumn("CLAVE");
		modeloReg.addColumn("DESCRIPCION");
		tableDatosRegimen.setModel(modeloReg);
		tableDatosRegimen.setBackground(Color.WHITE);

		JTableHeader th = new JTableHeader();
		Color colorSilverLight=new Color(176, 196, 222);
		Color colorNegro=new Color(0, 0, 0);
		th = tableDatosRegimen.getTableHeader();
		Font fuente = new Font("Arial", Font.BOLD, 14); 
		th.setFont(fuente); 
		th.setBackground(colorSilverLight);
		th.setForeground(colorNegro);

		TableColumnModel columnModel = tableDatosRegimen.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(100);
		columnModel.getColumn(1).setPreferredWidth(1000);

		String sqlSelectReg="";
		if(valor.equals("")) {
			sqlSelectReg = "SELECT clave,descripcion FROM dbo.REGIMEN_CONTRATACION";
		}else {
			sqlSelectReg = "SELECT clave,descripcion FROM dbo.REGIMEN_CONTRATACION WHERE "+atributo+""+"='"+ valor +"'"; 
		}

		String datosReg[] = new String[2];
		PoolNYCH nych = new PoolNYCH();
		Connection con =null;
		Statement st;
		ResultSet resultSet;
		try {
			con = nych.datasource.getConnection();
			st = con.createStatement();
			resultSet = st.executeQuery(sqlSelectReg);
			while(resultSet.next()) {
				datosReg[0] = resultSet.getString(1);
				datosReg[1] = resultSet.getString(2);
				modeloReg.addRow(datosReg);
			}//FIN DEL WHILE
			tableDatosRegimen.setModel(modeloReg);
		}catch (SQLException el) {
			el.printStackTrace();
			LOG.info("Excepción: " + el);
			JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
		}finally {
			try {
				con.close();
			} catch (SQLException ep) {
				ep.printStackTrace();
				LOG.info("Excepción: " + ep);
				JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
			}
		}//FIN DEL FINALLY
	}//FIN DEL METODO


	/*
	 * Metodo Para la busquedda del catalogo de Banco
	 */
	public void mostrarDatosBanco(String valor) {
		DefaultTableModel modeloBanco = new DefaultTableModel(){

			public boolean isCellEditable(int row, int column) {
				//return super.isCellEditable(row, column);
				if(column==3){
					return true;
				}else {
					return false;
				}
			}
		};
		modeloBanco.addColumn("CLAVE");
		modeloBanco.addColumn("NOMBRE CORTO");
		modeloBanco.addColumn("RAZON SOCIAL");
		tableDatosBanco.setModel(modeloBanco);
		tableDatosBanco.setBackground(Color.WHITE);

		JTableHeader th = new JTableHeader();
		Color colorSilverLight=new Color(176, 196, 222);
		Color colorNegro=new Color(0, 0, 0);
		th = tableDatosBanco.getTableHeader();
		Font fuente = new Font("Arial", Font.BOLD, 14); 
		th.setFont(fuente); 
		th.setBackground(colorSilverLight);
		th.setForeground(colorNegro);

		TableColumnModel columnModel = tableDatosBanco.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(100);
		columnModel.getColumn(1).setPreferredWidth(1000);
		columnModel.getColumn(2).setPreferredWidth(1000);

		String sqlSelectReg="";
		if(valor.equals("")) {
			sqlSelectReg = "SELECT clave,nombre_corto,nombre_razon_social FROM dbo.CATALOGOS_BANCOS";
		}else {
			sqlSelectReg = "SELECT clave,nombre_corto,nombre_razon_social FROM dbo.CATALOGOS_BANCOS WHERE "+atributo+""+"='"+ valor +"'"; 
		}

		String datosBan[] = new String[3];
		PoolNYCH nych = new PoolNYCH();
		Connection con =null;
		Statement st;
		ResultSet resultSet;
		try {
			con = nych.datasource.getConnection();
			st = con.createStatement();
			resultSet = st.executeQuery(sqlSelectReg);
			while(resultSet.next()) {
				datosBan[0] = resultSet.getString(1);
				datosBan[1] = resultSet.getString(2);
				datosBan[2] = resultSet.getString(3);
				modeloBanco.addRow(datosBan);
			}//FIN DEL WHILE
			tableDatosBanco.setModel(modeloBanco);
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

	/*
	 * Metodo Para la busquedda del catalogo de Riesgos
	 */
	public void mostrarDatosRiesgos(String valor) {
		DefaultTableModel modeloRiesgos = new DefaultTableModel(){

			public boolean isCellEditable(int row, int column) {
				//return super.isCellEditable(row, column);
				if(column==2){
					return true;
				}else {
					return false;
				}
			}
		};
		modeloRiesgos.addColumn("CLAVE");
		modeloRiesgos.addColumn("DESCRIPCION");
		tableDatosRiesgos.setModel(modeloRiesgos);
		tableDatosRiesgos.setBackground(Color.WHITE);

		JTableHeader th = new JTableHeader();
		Color colorSilverLight=new Color(176, 196, 222);
		Color colorNegro=new Color(0, 0, 0);
		th = tableDatosRiesgos.getTableHeader();
		Font fuente = new Font("Arial", Font.BOLD, 14); 
		th.setFont(fuente); 
		th.setBackground(colorSilverLight);
		th.setForeground(colorNegro);

		TableColumnModel columnModel = tableDatosRiesgos.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(100);
		columnModel.getColumn(1).setPreferredWidth(1000);

		String sqlSelectReg="";
		if(valor.equals("")) {
			sqlSelectReg = "SELECT clave,descripcion FROM dbo.RIESGO_PUESTO";
		}else {
			sqlSelectReg = "SELECT clave,descripcion FROM dbo.RIESGO_PUESTO WHERE "+atributo+""+"='"+ valor +"'"; 
		}

		String datosRies[] = new String[2];
		PoolNYCH nych = new PoolNYCH();
		Connection con =null;
		Statement st;
		ResultSet resultSet;
		try {
			con = nych.datasource.getConnection();
			st = con.createStatement();
			resultSet = st.executeQuery(sqlSelectReg);
			while(resultSet.next()) {
				datosRies[0] = resultSet.getString(1);
				datosRies[1] = resultSet.getString(2);
				modeloRiesgos.addRow(datosRies);
			}//FIN DEL WHILE
			tableDatosRiesgos.setModel(modeloRiesgos);
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


	/*
	 * Metodo Para la busquedda del catalogo de Riesgos
	 */
	public void mostrarDatosIncapacidad(String valor) {
		DefaultTableModel modeloIncap = new DefaultTableModel()
		{

			public boolean isCellEditable(int row, int column) {
				//return super.isCellEditable(row, column);
				if(column==2){
					return true;
				}else {
					return false;
				}
			}
		};
		modeloIncap.addColumn("CLAVE");
		modeloIncap.addColumn("DESCRIPCION");
		tableDatosIncapacidad.setModel(modeloIncap);
		tableDatosIncapacidad.setBackground(Color.WHITE);

		JTableHeader th = new JTableHeader();
		Color colorSilverLight=new Color(176, 196, 222);
		Color colorNegro=new Color(0, 0, 0);
		th = tableDatosIncapacidad.getTableHeader();
		Font fuente = new Font("Arial", Font.BOLD, 14); 
		th.setFont(fuente); 
		th.setBackground(colorSilverLight);
		th.setForeground(colorNegro);

		TableColumnModel columnModel = tableDatosIncapacidad.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(100);
		columnModel.getColumn(1).setPreferredWidth(1000);

		String sqlSelectReg="";
		if(valor.equals("")) {
			sqlSelectReg = "SELECT clave,descripcion FROM dbo.TIPO_INCAPACIDAD";
		}else {
			sqlSelectReg = "SELECT clave,descripcion FROM dbo.TIPO_INCAPACIDAD WHERE "+atributo+""+"='"+ valor +"'"; 
		}

		String datosRies[] = new String[2];
		PoolNYCH nych = new PoolNYCH();
		Connection con =null;
		Statement st;
		ResultSet resultSet;
		try {
			con = nych.datasource.getConnection();
			st = con.createStatement();
			resultSet = st.executeQuery(sqlSelectReg);
			while(resultSet.next()) {
				datosRies[0] = resultSet.getString(1);
				datosRies[1] = resultSet.getString(2);
				modeloIncap.addRow(datosRies);
			}//FIN DEL WHILE
			tableDatosIncapacidad.setModel(modeloIncap);
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


	/*
	 * Metodo Para la busquedda del catalogo de percepciones
	 */
	public void mostrarDatosPuestos(String valor) {
		final DefaultTableModel modeloPuestos = new DefaultTableModel(){

			public boolean isCellEditable(int row, int column) {
				//return super.isCellEditable(row, column);
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
		//		modeloPuestos.addColumn("AYUDA A DESPENSA");
		//		modeloPuestos.addColumn("SUBTOTAL");
		tableDatosPuestos.setModel(modeloPuestos);
		tableDatosPuestos.setBackground(Color.WHITE);

		JTableHeader th = new JTableHeader();
		Color colorSilverLight=new Color(176, 196, 222);
		Color colorNegro=new Color(0, 0, 0);
		th = tableDatosPuestos.getTableHeader();
		Font fuente = new Font("Arial", Font.BOLD, 14); 
		th.setFont(fuente); 
		th.setBackground(colorSilverLight);
		th.setForeground(colorNegro);

		TableColumnModel columnModel = tableDatosPuestos.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(100);
		columnModel.getColumn(1).setPreferredWidth(400);
		columnModel.getColumn(2).setPreferredWidth(400);

		String sqlSelect="";
		String sql="";
		sqlSelect = "SELECT NO_PUESTO,NOMBRE_PUESTO,SALARIO FROM dbo.PUESTOS order by nombre_puesto";
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


			modeloPuestos.addTableModelListener(new TableModelListener() {
				public void tableChanged(TableModelEvent e) {
					if(e.getType() == TableModelEvent.UPDATE) {
						JOptionPane.showMessageDialog(null, "Ha cambiado el valor, ¿Es correcto?");
						String colName = "salario";
						int fila = tableDatosPuestos.getSelectedRow();
						String valor = "";
						if(fila>=0) {
							valor= tableDatosPuestos.getValueAt(fila, 0).toString();
						}
						System.out.println("Valor introducido: "+modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()));
						String sqlupdt ="update DBO.puestos SET "+ colName +"= '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where no_puesto = '"+ valor +"'";
						System.out.println(sqlupdt);
						PoolNYCH nych = new PoolNYCH();
						Connection con1 = null;
						try {
							con1 = nych.datasource.getConnection();
							PreparedStatement pps = con1.prepareStatement(sqlupdt);
							pps.executeUpdate();
							JOptionPane.showMessageDialog(null, "Datos Actualizados");
							
							System.out.println("salario nuevo: " + (modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn())));
							System.out.println("numero de puesto: "+valor);
							incrementarSalarioEnCalculoNominaIndividual(modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()),valor);
							
							
						} catch (SQLException se) {
							se.printStackTrace();
							JOptionPane.showMessageDialog(null, "Datos No Actualizados");
						}finally {
							try {
								con1.close();
							} catch (SQLException ep) {
								ep.printStackTrace();
								JOptionPane.showMessageDialog(null, ep, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
							}
						}
					}//fin del if e.getype

				}
			});


			tableDatosPuestos.setModel(modeloPuestos);
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


	/*
	 * Metodo Para la busquedda del catalogo de Datos ISR
	 */
	public void mostrarDatosISR() {
		final DefaultTableModel modeloPuestos = new DefaultTableModel();
		modeloPuestos.addColumn("ID");
		modeloPuestos.addColumn("LIMITE INFERIOR");
		modeloPuestos.addColumn("LIMITE SUPERIOR");
		modeloPuestos.addColumn("CUOTA FIJA");
		modeloPuestos.addColumn("TASA EXCEDENTE");
		modeloPuestos.addColumn("COMENTARIO");
		tableDatosDatosISR.setModel(modeloPuestos);
		tableDatosDatosISR.setBackground(Color.WHITE);

		JTableHeader th = new JTableHeader();
		Color colorSilverLight=new Color(176, 196, 222);
		Color colorNegro=new Color(0, 0, 0);
		th = tableDatosDatosISR.getTableHeader();
		Font fuente = new Font("Arial", Font.BOLD, 14); 
		th.setFont(fuente); 
		th.setBackground(colorSilverLight);
		th.setForeground(colorNegro);

		TableColumnModel columnModel = tableDatosDatosISR.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(100);
		columnModel.getColumn(1).setPreferredWidth(200);
		columnModel.getColumn(2).setPreferredWidth(200);
		columnModel.getColumn(3).setPreferredWidth(200);
		columnModel.getColumn(4).setPreferredWidth(200);
		columnModel.getColumn(5).setPreferredWidth(800);

		String sqlSelect="";
		sqlSelect = "SELECT id_datos_isr,limite_inferior,limite_superior,cuota_fija,tasa_excedente,comentario FROM dbo.datos_isr";
		String datos[] = new String[6];
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
				modeloPuestos.addRow(datos);
			}//FIN DEL WHILE

			modeloPuestos.addTableModelListener(new TableModelListener() {
				public void tableChanged(TableModelEvent e) {
					if(e.getType() == TableModelEvent.UPDATE) {
						JOptionPane.showMessageDialog(null, "Ha cambiado el valor, ¿Es correcto?");
						String colName_LIMITE_INFERIOR =  "LIMITE_INFERIOR";
						String colName_LIMITE_SUPERIOR =  "LIMITE_SUPERIOR";
						String colName_CUOTA_FIJA =  "CUOTA_FIJA";
						String colName_TASA_EXCEDENTE = "TASA_EXCEDENTE";

						InternalFrameMovimientos internalFrameMovimientos = new InternalFrameMovimientos();
						ArrayList<Double> listaDatosISR = new ArrayList<Double>();
						listaDatosISR = internalFrameMovimientos.getListaDatosISR();
						System.out.println("*********");
						for(int i = 0; i<listaDatosISR.size();i++){
							System.out.println("indiceDatos[isr]: "+ i + " |valorDatos[isr]: " + listaDatosISR.get(i));
						}

						String sqlupdt ="";
						if(e.getColumn()==1 && e.getFirstRow()==0) {
							sqlupdt="UPDATE DBO.datos_isr SET "+ colName_LIMITE_INFERIOR +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where limite_inferior= '"+listaDatosISR.get(0) +"'";
						}

						else if(e.getColumn()==1 && e.getFirstRow()==1) {
							sqlupdt="UPDATE DBO.datos_isr SET "+ colName_LIMITE_INFERIOR +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where limite_inferior= '"+listaDatosISR.get(3) +"'";
						}

						else if(e.getColumn()==1 && e.getFirstRow()==2) {

							sqlupdt="UPDATE DBO.datos_isr SET "+ colName_LIMITE_INFERIOR +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where limite_inferior= '"+listaDatosISR.get(6) +"'";
						}
						else if(e.getColumn()==1 && e.getFirstRow()==3) {

							sqlupdt="UPDATE DBO.datos_isr SET "+ colName_LIMITE_INFERIOR +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where limite_inferior= '"+listaDatosISR.get(9) +"'";
						}
						else if(e.getColumn()==1 && e.getFirstRow()==4) {

							sqlupdt="UPDATE DBO.datos_isr SET "+ colName_LIMITE_INFERIOR +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where limite_inferior= '"+listaDatosISR.get(12) +"'";
						}
						else if(e.getColumn()==1 && e.getFirstRow()==5) {

							sqlupdt="UPDATE DBO.datos_isr SET "+ colName_LIMITE_INFERIOR +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where limite_inferior= '"+listaDatosISR.get(15) +"'";
						}

						else if(e.getColumn()==1 && e.getFirstRow()==6) {

							sqlupdt="UPDATE DBO.datos_isr SET "+ colName_LIMITE_INFERIOR +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where limite_inferior= '"+listaDatosISR.get(18) +"'";
						}

						else if(e.getColumn()==1 && e.getFirstRow()==7) {

							sqlupdt="UPDATE DBO.datos_isr SET "+ colName_LIMITE_INFERIOR +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where limite_inferior= '"+listaDatosISR.get(21) +"'";
						}
						else if(e.getColumn()==1 && e.getFirstRow()==8) {

							sqlupdt="UPDATE DBO.datos_isr SET "+ colName_LIMITE_INFERIOR +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where limite_inferior= '"+listaDatosISR.get(24) +"'";
						}
						else if(e.getColumn()==1 && e.getFirstRow()==9) {

							sqlupdt="UPDATE DBO.datos_isr SET "+ colName_LIMITE_INFERIOR +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where limite_inferior= '"+listaDatosISR.get(27) +"'";
						}
						else if(e.getColumn()==1 && e.getFirstRow()==10) {

							sqlupdt="UPDATE DBO.datos_isr SET "+ colName_LIMITE_INFERIOR +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where limite_inferior= '"+listaDatosISR.get(30) +"'";
						}
						///////

						if(e.getColumn()==3 && e.getFirstRow()==0) {

							sqlupdt="UPDATE DBO.datos_isr SET "+ colName_CUOTA_FIJA +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where cuota_fija= '"+listaDatosISR.get(1) +"'";
						}

						else if(e.getColumn()==3 && e.getFirstRow()==1) {

							sqlupdt="UPDATE DBO.datos_isr SET "+ colName_CUOTA_FIJA +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where cuota_fija= '"+listaDatosISR.get(4) +"'";
						}

						else if(e.getColumn()==3 && e.getFirstRow()==2) {

							sqlupdt="UPDATE DBO.datos_isr SET "+ colName_CUOTA_FIJA +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where cuota_fija= '"+listaDatosISR.get(7) +"'";
						}

						else if(e.getColumn()==3 && e.getFirstRow()==3) {

							sqlupdt="UPDATE DBO.datos_isr SET "+ colName_CUOTA_FIJA +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where cuota_fija= '"+listaDatosISR.get(10) +"'";
						}
						else if(e.getColumn()==3 && e.getFirstRow()==4) {

							sqlupdt="UPDATE DBO.datos_isr SET "+ colName_CUOTA_FIJA +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where cuota_fija= '"+listaDatosISR.get(13) +"'";
						}
						else if(e.getColumn()==3 && e.getFirstRow()==5) {

							sqlupdt="UPDATE DBO.datos_isr SET "+ colName_CUOTA_FIJA +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where cuota_fija= '"+listaDatosISR.get(16) +"'";
						}
						else if(e.getColumn()==3 && e.getFirstRow()==6) {

							sqlupdt="UPDATE DBO.datos_isr SET "+ colName_CUOTA_FIJA +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where cuota_fija= '"+listaDatosISR.get(19) +"'";
						}
						else if(e.getColumn()==3 && e.getFirstRow()==7) {

							sqlupdt="UPDATE DBO.datos_isr SET "+ colName_CUOTA_FIJA +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where cuota_fija= '"+listaDatosISR.get(22) +"'";
						}
						else if(e.getColumn()==3 && e.getFirstRow()==8) {

							sqlupdt="UPDATE DBO.datos_isr SET "+ colName_CUOTA_FIJA +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where cuota_fija= '"+listaDatosISR.get(25) +"'";
						}
						else if(e.getColumn()==3 && e.getFirstRow()==9) {

							sqlupdt="UPDATE DBO.datos_isr SET "+ colName_CUOTA_FIJA +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where cuota_fija= '"+listaDatosISR.get(28) +"'";
						}
						else if(e.getColumn()==3 && e.getFirstRow()==10) {

							sqlupdt="UPDATE DBO.datos_isr SET "+ colName_CUOTA_FIJA +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where cuota_fija= '"+listaDatosISR.get(31) +"'";
						}
						//////
						if(e.getColumn()==4 && e.getFirstRow()==0) {

							sqlupdt="UPDATE DBO.datos_isr SET "+ colName_TASA_EXCEDENTE +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where TASA_EXCEDENTE= '"+listaDatosISR.get(2) +"'";
						}
						else if(e.getColumn()==4 && e.getFirstRow()==1) {

							sqlupdt="UPDATE DBO.datos_isr SET "+ colName_TASA_EXCEDENTE +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where TASA_EXCEDENTE= '"+listaDatosISR.get(5) +"'";
						}
						else if(e.getColumn()==4 && e.getFirstRow()==2) {

							sqlupdt="UPDATE DBO.datos_isr SET "+ colName_TASA_EXCEDENTE +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where TASA_EXCEDENTE= '"+listaDatosISR.get(8) +"'";
						}
						else if(e.getColumn()==4 && e.getFirstRow()==3) {

							sqlupdt="UPDATE DBO.datos_isr SET "+ colName_TASA_EXCEDENTE +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where TASA_EXCEDENTE= '"+listaDatosISR.get(11) +"'";
						}
						else if(e.getColumn()==4 && e.getFirstRow()==4) {

							sqlupdt="UPDATE DBO.datos_isr SET "+ colName_TASA_EXCEDENTE +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where TASA_EXCEDENTE= '"+listaDatosISR.get(14) +"'";
						}
						else if(e.getColumn()==4 && e.getFirstRow()==5) {

							sqlupdt="UPDATE DBO.datos_isr SET "+ colName_TASA_EXCEDENTE +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where TASA_EXCEDENTE= '"+listaDatosISR.get(17) +"'";
						}
						else if(e.getColumn()==4 && e.getFirstRow()==6) {

							sqlupdt="UPDATE DBO.datos_isr SET "+ colName_TASA_EXCEDENTE +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where TASA_EXCEDENTE= '"+listaDatosISR.get(20) +"'";
						}
						else if(e.getColumn()==4 && e.getFirstRow()==7) {

							sqlupdt="UPDATE DBO.datos_isr SET "+ colName_TASA_EXCEDENTE +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where TASA_EXCEDENTE= '"+listaDatosISR.get(23) +"'";
						}
						else if(e.getColumn()==4 && e.getFirstRow()==8) {

							sqlupdt="UPDATE DBO.datos_isr SET "+ colName_TASA_EXCEDENTE +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where TASA_EXCEDENTE= '"+listaDatosISR.get(26) +"'";
						}
						else if(e.getColumn()==4 && e.getFirstRow()==9) {

							sqlupdt="UPDATE DBO.datos_isr SET "+ colName_TASA_EXCEDENTE +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where TASA_EXCEDENTE= '"+listaDatosISR.get(29) +"'";
						}
						else if(e.getColumn()==4 && e.getFirstRow()==10) {

							sqlupdt="UPDATE DBO.datos_isr SET "+ colName_TASA_EXCEDENTE +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where TASA_EXCEDENTE= '"+listaDatosISR.get(32) +"'";
						}

						System.out.println(sqlupdt);
						PoolNYCH nych1 = new PoolNYCH();
						Connection con1 = null;
						try {
							con1 = nych1.datasource.getConnection();
							PreparedStatement pps = con1.prepareStatement(sqlupdt);
							pps.executeUpdate();
							JOptionPane.showMessageDialog(null, "Datos Actualizados");
							//dispose();
						} catch (SQLException se) {
							se.printStackTrace();
							StringWriter errors = new StringWriter();
							se.printStackTrace(new PrintWriter(errors));
							LOG.info("Excepcion: "+ errors );
							JOptionPane.showMessageDialog(null, "Datos No Actualizados");
						}finally {
							try {
								con1.close();
							} catch (SQLException ep) {
								ep.printStackTrace();
								StringWriter errors = new StringWriter();
								ep.printStackTrace(new PrintWriter(errors));
								LOG.info("Excepcion: "+ errors );
								JOptionPane.showMessageDialog(null, ep, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
							}
						}
					}//fin del if e.getype

				}
			});
			tableDatosDatosISR.setModel(modeloPuestos);
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

	/*
	 * Metodo Para la busquedda del catalogo de Subsidio ISR
	 */
	public void mostrarSubsidioISR() {
		final DefaultTableModel modeloSubsidioISR = new DefaultTableModel();
		modeloSubsidioISR.addColumn("ID");
		modeloSubsidioISR.addColumn("PARA INGRESOS DE");
		modeloSubsidioISR.addColumn("HASTA INGRESOS DE");
		modeloSubsidioISR.addColumn("SUBSIDIO");
		modeloSubsidioISR.addColumn("COMENTARIO");
		tableDatosSubsidioISR.setModel(modeloSubsidioISR);
		tableDatosSubsidioISR.setBackground(Color.WHITE);

		JTableHeader th = new JTableHeader();
		Color colorSilverLight=new Color(176, 196, 222);
		Color colorNegro=new Color(0, 0, 0);
		th = tableDatosSubsidioISR.getTableHeader();
		Font fuente = new Font("Arial", Font.BOLD, 14); 
		th.setFont(fuente); 
		th.setBackground(colorSilverLight);
		th.setForeground(colorNegro);

		TableColumnModel columnModel = tableDatosSubsidioISR.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(100);
		columnModel.getColumn(1).setPreferredWidth(200);
		columnModel.getColumn(2).setPreferredWidth(200);
		columnModel.getColumn(3).setPreferredWidth(200);
		columnModel.getColumn(4).setPreferredWidth(800);

		String sqlSelect="";
		sqlSelect = "SELECT id_subsidio_isr,para_ingresos_de,hasta_ingresos_de,subsidio,comentario FROM dbo.SUBSIDIO_isr";
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
				datos[3] = resultSet.getString(4);
				datos[4] = resultSet.getString(5);
				modeloSubsidioISR.addRow(datos);
			}//FIN DEL WHILE

			modeloSubsidioISR.addTableModelListener(new TableModelListener() {
				public void tableChanged(TableModelEvent e) {
					if(e.getType() == TableModelEvent.UPDATE) {
						JOptionPane.showMessageDialog(null, "Ha cambiado el valor");
						String colName_PARA_INGRESOS_DE = "PARA_INGRESOS_DE";
						String colName_SUBSIDIO = "SUBSIDIO";

						InternalFrameMovimientos internalFrameMovimientos = new InternalFrameMovimientos();
						ArrayList<Double> listaSubsidioISR = new ArrayList<Double>();
						listaSubsidioISR = internalFrameMovimientos.getListaSubsidioISR();
						System.out.println("*********");
						for(int i = 0; i<listaSubsidioISR.size();i++){
							System.out.println("indiceSubsidio[isr]: "+ i + " |valorSubsidio[isr]: " + listaSubsidioISR.get(i));
						}

						String sqlupdt ="";
						if(e.getColumn()==1 && e.getFirstRow()==0) {
							sqlupdt="UPDATE DBO.SUBSIDIO_ISR SET "+ colName_PARA_INGRESOS_DE +" = '"+ modeloSubsidioISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where PARA_INGRESOS_DE= '"+listaSubsidioISR.get(0) +"'";
						}

						else if(e.getColumn()==1 && e.getFirstRow()==1) {
							sqlupdt="UPDATE DBO.SUBSIDIO_ISR SET "+ colName_PARA_INGRESOS_DE +" = '"+ modeloSubsidioISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where PARA_INGRESOS_DE= '"+listaSubsidioISR.get(2) +"'";
						}

						else if(e.getColumn()==1 && e.getFirstRow()==2) {

							sqlupdt="UPDATE DBO.SUBSIDIO_ISR SET "+ colName_PARA_INGRESOS_DE +" = '"+ modeloSubsidioISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where PARA_INGRESOS_DE= '"+listaSubsidioISR.get(4) +"'";
						}
						else if(e.getColumn()==1 && e.getFirstRow()==3) {

							sqlupdt="UPDATE DBO.SUBSIDIO_ISR SET "+ colName_PARA_INGRESOS_DE +" = '"+ modeloSubsidioISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where PARA_INGRESOS_DE= '"+listaSubsidioISR.get(6) +"'";
						}
						else if(e.getColumn()==1 && e.getFirstRow()==4) {

							sqlupdt="UPDATE DBO.SUBSIDIO_ISR SET "+ colName_PARA_INGRESOS_DE +" = '"+ modeloSubsidioISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where PARA_INGRESOS_DE= '"+listaSubsidioISR.get(8) +"'";
						}
						else if(e.getColumn()==1 && e.getFirstRow()==5) {

							sqlupdt="UPDATE DBO.SUBSIDIO_ISR SET "+ colName_PARA_INGRESOS_DE +" = '"+ modeloSubsidioISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where PARA_INGRESOS_DE= '"+listaSubsidioISR.get(10) +"'";
						}

						else if(e.getColumn()==1 && e.getFirstRow()==6) {

							sqlupdt="UPDATE DBO.SUBSIDIO_ISR SET "+ colName_PARA_INGRESOS_DE +" = '"+ modeloSubsidioISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where PARA_INGRESOS_DE= '"+listaSubsidioISR.get(12) +"'";
						}

						else if(e.getColumn()==1 && e.getFirstRow()==7) {

							sqlupdt="UPDATE DBO.SUBSIDIO_ISR SET "+ colName_PARA_INGRESOS_DE +" = '"+ modeloSubsidioISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where PARA_INGRESOS_DE= '"+listaSubsidioISR.get(14) +"'";
						}
						else if(e.getColumn()==1 && e.getFirstRow()==8) {

							sqlupdt="UPDATE DBO.SUBSIDIO_ISR SET "+ colName_PARA_INGRESOS_DE +" = '"+ modeloSubsidioISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where PARA_INGRESOS_DE= '"+listaSubsidioISR.get(16) +"'";
						}
						else if(e.getColumn()==1 && e.getFirstRow()==9) {

							sqlupdt="UPDATE DBO.SUBSIDIO_ISR SET "+ colName_PARA_INGRESOS_DE +" = '"+ modeloSubsidioISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where PARA_INGRESOS_DE= '"+listaSubsidioISR.get(18) +"'";
						}
						else if(e.getColumn()==1 && e.getFirstRow()==10) {

							sqlupdt="UPDATE DBO.SUBSIDIO_ISR SET "+ colName_PARA_INGRESOS_DE +" = '"+ modeloSubsidioISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where PARA_INGRESOS_DE= '"+listaSubsidioISR.get(20) +"'";
						}
						///////
						if(e.getColumn()==3 && e.getFirstRow()==0) {

							sqlupdt="UPDATE DBO.SUBSIDIO_ISR SET "+ colName_SUBSIDIO +" = '"+ modeloSubsidioISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where SUBSIDIO= '"+listaSubsidioISR.get(1) +"'";
						}

						else if(e.getColumn()==3 && e.getFirstRow()==1) {

							sqlupdt="UPDATE DBO.SUBSIDIO_ISR SET "+ colName_SUBSIDIO +" = '"+ modeloSubsidioISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where SUBSIDIO= '"+listaSubsidioISR.get(3) +"'";
						}

						else if(e.getColumn()==3 && e.getFirstRow()==2) {

							sqlupdt="UPDATE DBO.SUBSIDIO_ISR SET "+ colName_SUBSIDIO +" = '"+ modeloSubsidioISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where SUBSIDIO= '"+listaSubsidioISR.get(5) +"'";
						}

						else if(e.getColumn()==3 && e.getFirstRow()==3) {

							sqlupdt="UPDATE DBO.SUBSIDIO_ISR SET "+ colName_SUBSIDIO +" = '"+ modeloSubsidioISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where SUBSIDIO= '"+listaSubsidioISR.get(7) +"'";
						}
						else if(e.getColumn()==3 && e.getFirstRow()==4) {

							sqlupdt="UPDATE DBO.SUBSIDIO_ISR SET "+ colName_SUBSIDIO +" = '"+ modeloSubsidioISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where SUBSIDIO= '"+listaSubsidioISR.get(9) +"'";
						}
						else if(e.getColumn()==3 && e.getFirstRow()==5) {

							sqlupdt="UPDATE DBO.SUBSIDIO_ISR SET "+ colName_SUBSIDIO +" = '"+ modeloSubsidioISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where SUBSIDIO= '"+listaSubsidioISR.get(11) +"'";
						}
						else if(e.getColumn()==3 && e.getFirstRow()==6) {

							sqlupdt="UPDATE DBO.SUBSIDIO_ISR SET "+ colName_SUBSIDIO +" = '"+ modeloSubsidioISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where SUBSIDIO= '"+listaSubsidioISR.get(13) +"'";
						}
						else if(e.getColumn()==3 && e.getFirstRow()==7) {

							sqlupdt="UPDATE DBO.SUBSIDIO_ISR SET "+ colName_SUBSIDIO +" = '"+ modeloSubsidioISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where SUBSIDIO= '"+listaSubsidioISR.get(15) +"'";
						}
						else if(e.getColumn()==3 && e.getFirstRow()==8) {

							sqlupdt="UPDATE DBO.SUBSIDIO_ISR SET "+ colName_SUBSIDIO +" = '"+ modeloSubsidioISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where SUBSIDIO= '"+listaSubsidioISR.get(17) +"'";
						}
						else if(e.getColumn()==3 && e.getFirstRow()==9) {

							sqlupdt="UPDATE DBO.SUBSIDIO_ISR SET "+ colName_SUBSIDIO +" = '"+ modeloSubsidioISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where SUBSIDIO= '"+listaSubsidioISR.get(19) +"'";
						}
						else if(e.getColumn()==3 && e.getFirstRow()==10) {

							sqlupdt="UPDATE DBO.SUBSIDIO_ISR SET "+ colName_SUBSIDIO +" = '"+ modeloSubsidioISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where SUBSIDIO= '"+listaSubsidioISR.get(21) +"'";
						}


						System.out.println(sqlupdt);
						PoolNYCH nych1 = new PoolNYCH();
						Connection con1 = null;
						try {
							con1 = nych1.datasource.getConnection();
							PreparedStatement pps = con1.prepareStatement(sqlupdt);
							pps.executeUpdate();
							JOptionPane.showMessageDialog(null, "Datos Actualizados");
							//dispose();
						} catch (SQLException se) {
							se.printStackTrace();
							StringWriter errors = new StringWriter();
							se.printStackTrace(new PrintWriter(errors));
							LOG.info("Excepcion: "+ errors );
							JOptionPane.showMessageDialog(null, "Datos No Actualizados");
						}finally {
							try {
								con1.close();
							} catch (SQLException ep) {
								ep.printStackTrace();
								StringWriter errors = new StringWriter();
								ep.printStackTrace(new PrintWriter(errors));
								LOG.info("Excepcion: "+ errors );
								JOptionPane.showMessageDialog(null, ep, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
							}
						}
					}//fin del if e.getype

				}
			});

			tableDatosSubsidioISR.setModel(modeloSubsidioISR);
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


	/*
	 * Metodo Para la busquedda del catalogo de porcentaje imss
	 */
	public void mostrarDatosImss() {
		final DefaultTableModel modeloDatosImss = new DefaultTableModel();
		modeloDatosImss.addColumn("ID");
		modeloDatosImss.addColumn("VARIABLE IMSS");
		modeloDatosImss.addColumn("VALOR");
		tableDatosDatosImss.setModel(modeloDatosImss);
		tableDatosDatosImss.setBackground(Color.WHITE);

		JTableHeader th = new JTableHeader();
		Color colorSilverLight=new Color(176, 196, 222);
		Color colorNegro=new Color(0, 0, 0);
		th = tableDatosDatosImss.getTableHeader();
		Font fuente = new Font("Arial", Font.BOLD, 14); 
		th.setFont(fuente); 
		th.setBackground(colorSilverLight);
		th.setForeground(colorNegro);

		TableColumnModel columnModel = tableDatosDatosImss.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(100);
		columnModel.getColumn(1).setPreferredWidth(800);
		columnModel.getColumn(2).setPreferredWidth(200);

		String sqlSelect="";
		sqlSelect = "SELECT id_porcentajes_imss,variable_imss,valor FROM dbo.PORCENTAJE_IMSS";
		String datos[] = new String[3];
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
				modeloDatosImss.addRow(datos);
			}//FIN DEL WHILE

			modeloDatosImss.addTableModelListener(new TableModelListener() {
				public void tableChanged(TableModelEvent e) {
					if(e.getType() == TableModelEvent.UPDATE) {
						JOptionPane.showMessageDialog(null, "Ha cambiado el valor, ¿Es correcto?");
						String colName_VALOR = "VALOR";

						InternalFrameMovimientos internalFrameMovimientos = new InternalFrameMovimientos();
						ArrayList<Double> listaPorcentajeImss = new ArrayList<Double>();
						listaPorcentajeImss = internalFrameMovimientos.getListaPorcentajeIMSS();
						System.out.println("*********");
						for(int i = 0; i<listaPorcentajeImss.size();i++){
							System.out.println("indicePorcentajeImss: "+ i + " |valorPorcentajeImss: " + listaPorcentajeImss.get(i));
						}

						String sqlupdt ="";
						if(e.getColumn()==2 && e.getFirstRow()==0) {
							sqlupdt="UPDATE DBO.porcentaje_imss SET "+ colName_VALOR +" = '"+ modeloDatosImss.getValueAt(e.getFirstRow(), e.getColumn()) +"' where VALOR= '"+listaPorcentajeImss.get(0) +"'";
						}

						else if(e.getColumn()==2 && e.getFirstRow()==1) {
							sqlupdt="UPDATE DBO.porcentaje_imss SET "+ colName_VALOR +" = '"+ modeloDatosImss.getValueAt(e.getFirstRow(), e.getColumn()) +"' where VALOR= '"+listaPorcentajeImss.get(1) +"'";
						}

						else if(e.getColumn()==2 && e.getFirstRow()==2) {

							sqlupdt="UPDATE DBO.porcentaje_imss SET "+ colName_VALOR +" = '"+ modeloDatosImss.getValueAt(e.getFirstRow(), e.getColumn()) +"' where VALOR= '"+listaPorcentajeImss.get(2) +"'";
						}
						else if(e.getColumn()==2 && e.getFirstRow()==3) {

							sqlupdt="UPDATE DBO.porcentaje_imss SET "+ colName_VALOR +" = '"+ modeloDatosImss.getValueAt(e.getFirstRow(), e.getColumn()) +"' where VALOR= '"+listaPorcentajeImss.get(3) +"'";
						}
						else if(e.getColumn()==2 && e.getFirstRow()==4) {

							sqlupdt="UPDATE DBO.porcentaje_imss SET "+ colName_VALOR +" = '"+ modeloDatosImss.getValueAt(e.getFirstRow(), e.getColumn()) +"' where VALOR= '"+listaPorcentajeImss.get(4) +"'";
						}
						else if(e.getColumn()==2 && e.getFirstRow()==5) {

							sqlupdt="UPDATE DBO.porcentaje_imss SET "+ colName_VALOR +" = '"+ modeloDatosImss.getValueAt(e.getFirstRow(), e.getColumn()) +"' where VALOR= '"+listaPorcentajeImss.get(5) +"'";
						}

						else if(e.getColumn()==2 && e.getFirstRow()==6) {

							sqlupdt="UPDATE DBO.porcentaje_imss SET "+ colName_VALOR +" = '"+ modeloDatosImss.getValueAt(e.getFirstRow(), e.getColumn()) +"' where VALOR= '"+listaPorcentajeImss.get(6) +"'";
						}

						else if(e.getColumn()==2 && e.getFirstRow()==7) {

							sqlupdt="UPDATE DBO.porcentaje_imss SET "+ colName_VALOR +" = '"+ modeloDatosImss.getValueAt(e.getFirstRow(), e.getColumn()) +"' where VALOR= '"+listaPorcentajeImss.get(7) +"'";
						}
						else if(e.getColumn()==2 && e.getFirstRow()==8) {

							sqlupdt="UPDATE DBO.porcentaje_imss SET "+ colName_VALOR +" = '"+ modeloDatosImss.getValueAt(e.getFirstRow(), e.getColumn()) +"' where VALOR= '"+listaPorcentajeImss.get(8) +"'";
						}
						else if(e.getColumn()==2 && e.getFirstRow()==9) {

							sqlupdt="UPDATE DBO.porcentaje_imss SET "+ colName_VALOR +" = '"+ modeloDatosImss.getValueAt(e.getFirstRow(), e.getColumn()) +"' where VALOR= '"+listaPorcentajeImss.get(9) +"'";
						}
						else if(e.getColumn()==2 && e.getFirstRow()==10) {

							sqlupdt="UPDATE DBO.porcentaje_imss SET "+ colName_VALOR +" = '"+ modeloDatosImss.getValueAt(e.getFirstRow(), e.getColumn()) +"' where VALOR= '"+listaPorcentajeImss.get(10) +"'";
						}

						else if(e.getColumn()==2 && e.getFirstRow()==11) {

							sqlupdt="UPDATE DBO.porcentaje_imss SET "+ colName_VALOR +" = '"+ modeloDatosImss.getValueAt(e.getFirstRow(), e.getColumn()) +"' where VALOR= '"+listaPorcentajeImss.get(11) +"'";
						}
						else if(e.getColumn()==2 && e.getFirstRow()==12) {

							sqlupdt="UPDATE DBO.porcentaje_imss SET "+ colName_VALOR +" = '"+ modeloDatosImss.getValueAt(e.getFirstRow(), e.getColumn()) +"' where VALOR= '"+listaPorcentajeImss.get(12) +"'";
						}
						else if(e.getColumn()==2 && e.getFirstRow()==13) {

							sqlupdt="UPDATE DBO.porcentaje_imss SET "+ colName_VALOR +" = '"+ modeloDatosImss.getValueAt(e.getFirstRow(), e.getColumn()) +"' where VALOR= '"+listaPorcentajeImss.get(13) +"'";
						}
						else if(e.getColumn()==2 && e.getFirstRow()==14) {

							sqlupdt="UPDATE DBO.porcentaje_imss SET "+ colName_VALOR +" = '"+ modeloDatosImss.getValueAt(e.getFirstRow(), e.getColumn()) +"' where VALOR= '"+listaPorcentajeImss.get(14) +"'";
						}
						else if(e.getColumn()==2 && e.getFirstRow()==15) {

							sqlupdt="UPDATE DBO.porcentaje_imss SET "+ colName_VALOR +" = '"+ modeloDatosImss.getValueAt(e.getFirstRow(), e.getColumn()) +"' where VALOR= '"+listaPorcentajeImss.get(15) +"'";
						}
						else if(e.getColumn()==2 && e.getFirstRow()==16) {

							sqlupdt="UPDATE DBO.porcentaje_imss SET "+ colName_VALOR +" = '"+ modeloDatosImss.getValueAt(e.getFirstRow(), e.getColumn()) +"' where VALOR= '"+listaPorcentajeImss.get(16) +"'";
						}
						else if(e.getColumn()==2 && e.getFirstRow()==17) {

							sqlupdt="UPDATE DBO.porcentaje_imss SET "+ colName_VALOR +" = '"+ modeloDatosImss.getValueAt(e.getFirstRow(), e.getColumn()) +"' where VALOR= '"+listaPorcentajeImss.get(17) +"'";
						}
						else if(e.getColumn()==2 && e.getFirstRow()==18) {

							sqlupdt="UPDATE DBO.porcentaje_imss SET "+ colName_VALOR +" = '"+ modeloDatosImss.getValueAt(e.getFirstRow(), e.getColumn()) +"' where VALOR= '"+listaPorcentajeImss.get(18) +"'";
						}
						else if(e.getColumn()==2 && e.getFirstRow()==19) {

							sqlupdt="UPDATE DBO.porcentaje_imss SET "+ colName_VALOR +" = '"+ modeloDatosImss.getValueAt(e.getFirstRow(), e.getColumn()) +"' where VALOR= '"+listaPorcentajeImss.get(19) +"'";
						}
						else if(e.getColumn()==2 && e.getFirstRow()==20) {

							sqlupdt="UPDATE DBO.porcentaje_imss SET "+ colName_VALOR +" = '"+ modeloDatosImss.getValueAt(e.getFirstRow(), e.getColumn()) +"' where VALOR= '"+listaPorcentajeImss.get(20) +"'";
						}
						else if(e.getColumn()==2 && e.getFirstRow()==21) {

							sqlupdt="UPDATE DBO.porcentaje_imss SET "+ colName_VALOR +" = '"+ modeloDatosImss.getValueAt(e.getFirstRow(), e.getColumn()) +"' where VALOR= '"+listaPorcentajeImss.get(21) +"'";
						}else if(e.getColumn()==2 && e.getFirstRow()==22) {

							sqlupdt="UPDATE DBO.porcentaje_imss SET "+ colName_VALOR +" = '"+ modeloDatosImss.getValueAt(e.getFirstRow(), e.getColumn()) +"' where VALOR= '"+listaPorcentajeImss.get(22) +"'";
						}
						else if(e.getColumn()==2 && e.getFirstRow()==23) {

							sqlupdt="UPDATE DBO.porcentaje_imss SET "+ colName_VALOR +" = '"+ modeloDatosImss.getValueAt(e.getFirstRow(), e.getColumn()) +"' where VALOR= '"+listaPorcentajeImss.get(23) +"'";
						}
						else if(e.getColumn()==2 && e.getFirstRow()==24) {

							sqlupdt="UPDATE DBO.porcentaje_imss SET "+ colName_VALOR +" = '"+ modeloDatosImss.getValueAt(e.getFirstRow(), e.getColumn()) +"' where VALOR= '"+listaPorcentajeImss.get(24) +"'";
						}
						else if(e.getColumn()==2 && e.getFirstRow()==25) {

							sqlupdt="UPDATE DBO.porcentaje_imss SET "+ colName_VALOR +" = '"+ modeloDatosImss.getValueAt(e.getFirstRow(), e.getColumn()) +"' where VALOR= '"+listaPorcentajeImss.get(25) +"'";
						}
						else if(e.getColumn()==2 && e.getFirstRow()==26) {

							sqlupdt="UPDATE DBO.porcentaje_imss SET "+ colName_VALOR +" = '"+ modeloDatosImss.getValueAt(e.getFirstRow(), e.getColumn()) +"' where VALOR= '"+listaPorcentajeImss.get(26) +"'";
						}
						else if(e.getColumn()==2 && e.getFirstRow()==27) {

							sqlupdt="UPDATE DBO.porcentaje_imss SET "+ colName_VALOR +" = '"+ modeloDatosImss.getValueAt(e.getFirstRow(), e.getColumn()) +"' where VALOR= '"+listaPorcentajeImss.get(27) +"'";
						}
						else if(e.getColumn()==2 && e.getFirstRow()==28) {

							sqlupdt="UPDATE DBO.porcentaje_imss SET "+ colName_VALOR +" = '"+ modeloDatosImss.getValueAt(e.getFirstRow(), e.getColumn()) +"' where VALOR= '"+listaPorcentajeImss.get(28) +"'";
						}
						System.out.println(sqlupdt);
						PoolNYCH nych1 = new PoolNYCH();
						Connection con1 = null;
						try {
							con1 = nych1.datasource.getConnection();
							PreparedStatement pps = con1.prepareStatement(sqlupdt);
							pps.executeUpdate();
							JOptionPane.showMessageDialog(null, "Datos Actualizados");
							//dispose();
						} catch (SQLException se) {
							se.printStackTrace();
							StringWriter errors = new StringWriter();
							se.printStackTrace(new PrintWriter(errors));
							LOG.info("Excepcion: "+ errors );
							JOptionPane.showMessageDialog(null, "Datos No Actualizados");
						}finally {
							try {
								con1.close();
							} catch (SQLException ep) {
								ep.printStackTrace();
								StringWriter errors = new StringWriter();
								ep.printStackTrace(new PrintWriter(errors));
								LOG.info("Excepcion: "+ errors );
								JOptionPane.showMessageDialog(null, ep, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
							}
						}
					}//fin del if e.getype

				}
			});


			tableDatosDatosImss.setModel(modeloDatosImss);
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


	/*
	 * Metodo Para la busquedda del catalogo de percepciones
	 */
	public void mostrarDiasInhabiles(String valor) {
		//DateFormat outputFormat = new SimpleDateFormat("EEEEEEEEE dd 'de' MMMMM 'de' yyyy");
		DateFormat outputFormat = new SimpleDateFormat("yyyy/MM/dd");
		DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
		final DefaultTableModel modelo = new DefaultTableModel() {

			public boolean isCellEditable(int row, int column) {
				//return super.isCellEditable(row, column);
				if(column==0){
					return true;
				}else {
					return false;
				}
			}
		};
		modelo.addColumn("DIA INHÁBIL (YYYY/MM/DD)");
		modelo.addColumn("PERIODO");
		tableDatosDiasInhabiles.setModel(modelo);
		tableDatosDiasInhabiles.setBackground(Color.WHITE);

		JTableHeader th = new JTableHeader();
		Color colorSilverLight=new Color(176, 196, 222);
		Color colorNegro=new Color(0, 0, 0);
		th = tableDatosDiasInhabiles.getTableHeader();
		Font fuente = new Font("Arial", Font.BOLD, 14); 
		th.setFont(fuente); 
		th.setBackground(colorSilverLight);
		th.setForeground(colorNegro);

		TableColumnModel columnModel = tableDatosDiasInhabiles.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(100);
		columnModel.getColumn(1).setPreferredWidth(200);

		String sqlSelect="";
		sqlSelect = "SELECT DIA_INHABIL,PERIODO FROM dbo.DIAS_INHABILES where ELIMINAR_LOGICO='"+ 1 +"'";
		//System.out.println(sqlSelect);
		String datos[] = new String[2];
		PoolNYCH nych = new PoolNYCH();
		Connection con =null;
		Statement st;
		ResultSet resultSet;
		String inputText;
		String outputText;
		try {
			con = nych.datasource.getConnection();
			st = con.createStatement();
			resultSet = st.executeQuery(sqlSelect);
			while(resultSet.next()) {
				datos[0] = resultSet.getString(1);
				datos[1] = resultSet.getString(2);


				inputText = datos[0];
				Date date = inputFormat.parse(inputText);
				outputText = outputFormat.format(date);
				datos[0] = outputText;
				modelo.addRow(datos);


			}//FIN DEL WHILE
			String dato = "2018/11/20";
			//			dato=String.valueOf(modelo.getValueAt(0,0));
			//			System.out.println("dato: "+dato);

			modelo.addTableModelListener(new TableModelListener() {
				public void tableChanged(TableModelEvent e) {
					if(e.getType() == TableModelEvent.UPDATE) {
						JOptionPane.showMessageDialog(null, "Ha cambiado el valor, ¿Es correcto?");
						String colName_VALOR = "DIA_INHABIL";

						String sqlupdt ="";
						if(e.getColumn()==0 && e.getFirstRow()==0) {
							sqlupdt="UPDATE DBO.DIAS_INHABILES SET "+ colName_VALOR +" = convert(datetime,"+ "'"+ modelo.getValueAt(e.getFirstRow(), e.getColumn()) +"'" +", 101)  where clave= '"+ 1 +"'";
						}

						else if(e.getColumn()==0 && e.getFirstRow()==1) {
							sqlupdt="UPDATE DBO.DIAS_INHABILES SET "+ colName_VALOR +" = convert(datetime,"+ "'"+ modelo.getValueAt(e.getFirstRow(), e.getColumn()) +"'" +", 101)  where clave= '"+ 2 +"'";
						}

						else if(e.getColumn()==0 && e.getFirstRow()==2) {

							sqlupdt="UPDATE DBO.DIAS_INHABILES SET "+ colName_VALOR +" = convert(datetime,"+ "'"+ modelo.getValueAt(e.getFirstRow(), e.getColumn()) +"'" +", 101)  where clave= '"+ 3 +"'";
						}
						else if(e.getColumn()==0 && e.getFirstRow()==3) {

							sqlupdt="UPDATE DBO.DIAS_INHABILES SET "+ colName_VALOR +" = convert(datetime,"+ "'"+ modelo.getValueAt(e.getFirstRow(), e.getColumn()) +"'" +", 101)  where clave= '"+ 4 +"'";
						}
						else if(e.getColumn()==0 && e.getFirstRow()==4) {

							sqlupdt="UPDATE DBO.DIAS_INHABILES SET "+ colName_VALOR +" = convert(datetime,"+ "'"+ modelo.getValueAt(e.getFirstRow(), e.getColumn()) +"'" +", 101)  where clave= '"+ 5 +"'";
						}
						else if(e.getColumn()==0 && e.getFirstRow()==5) {

							sqlupdt="UPDATE DBO.DIAS_INHABILES SET "+ colName_VALOR +" = convert(datetime,"+ "'"+ modelo.getValueAt(e.getFirstRow(), e.getColumn()) +"'" +", 101)  where clave= '"+ 6 +"'";
						}

						else if(e.getColumn()==0 && e.getFirstRow()==6) {

							sqlupdt="UPDATE DBO.DIAS_INHABILES SET "+ colName_VALOR +" = convert(datetime,"+ "'"+ modelo.getValueAt(e.getFirstRow(), e.getColumn()) +"'" +", 101)  where clave= '"+ 7 +"'";
						}

						else if(e.getColumn()==0 && e.getFirstRow()==7) {

							sqlupdt="UPDATE DBO.DIAS_INHABILES SET "+ colName_VALOR +" = convert(datetime,"+ "'"+ modelo.getValueAt(e.getFirstRow(), e.getColumn()) +"'" +", 101)  where clave= '"+ 8 +"'";
						}
						else if(e.getColumn()==0 && e.getFirstRow()==8) {

							sqlupdt="UPDATE DBO.DIAS_INHABILES SET "+ colName_VALOR +" = convert(datetime,"+ "'"+ modelo.getValueAt(e.getFirstRow(), e.getColumn()) +"'" +", 101)  where clave= '"+ 9 +"'";
						}
						else if(e.getColumn()==0 && e.getFirstRow()==9) {

							sqlupdt="UPDATE DBO.DIAS_INHABILES SET "+ colName_VALOR +" = convert(datetime,"+ "'"+ modelo.getValueAt(e.getFirstRow(), e.getColumn()) +"'" +", 101)  where clave= '"+ 10 +"'";
						}


						///si agregan mas dias
						//						else if(e.getColumn()==0 && e.getFirstRow()==10) {
						//
						//							sqlupdt="UPDATE DBO.DIAS_INHABILES SET "+ colName_VALOR +" = convert(datetime,"+ "'"+ modelo.getValueAt(e.getFirstRow(), e.getColumn()) +"'" +", 101)  where clave= '"+ 11 +"'";
						//						}


						System.out.println(sqlupdt);
						PoolNYCH nych1 = new PoolNYCH();
						Connection con1 = null;
						try {
							con1 = nych1.datasource.getConnection();
							PreparedStatement pps = con1.prepareStatement(sqlupdt);
							pps.executeUpdate();
							JOptionPane.showMessageDialog(null, "Datos Actualizados");
							//dispose();
						} catch (SQLException se) {
							se.printStackTrace();
							StringWriter errors = new StringWriter();
							se.printStackTrace(new PrintWriter(errors));
							LOG.info("Excepcion: "+ errors );
							JOptionPane.showMessageDialog(null, "Datos No Actualizados");
						}finally {
							try {
								con1.close();
							} catch (SQLException ep) {
								ep.printStackTrace();
								StringWriter errors = new StringWriter();
								ep.printStackTrace(new PrintWriter(errors));
								LOG.info("Excepcion: "+ errors );
								JOptionPane.showMessageDialog(null, ep, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
							}
						}
					}//fin del if e.getype

				}
			});



			tableDatosDiasInhabiles.setModel(modelo);
		}catch (Exception el) {
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


	public void eliminarLogicoDiaInhabil() {
		PoolNYCH nych = new PoolNYCH();
		Connection con =null;

		Date datetime;
		int fila = tableDatosDiasInhabiles.getSelectedRow();
		if(fila>=0) {
			try {
				String valor= tableDatosDiasInhabiles.getValueAt(fila, 0).toString();
				System.out.println("valor:_ "+valor);
				String sqlDelete="UPDATE dbo.DIAS_INHABILES set eliminar_logico="+ 0 +" WHERE DIA_INHABIL="+ "convert(datetime,"+ "'"+valor+"'" +", 101)";
				System.out.println(sqlDelete);
				con = nych.datasource.getConnection();
				PreparedStatement pps = con.prepareStatement(sqlDelete);
				pps.executeUpdate();
				JOptionPane.showMessageDialog(null, "Se ha eliminado el registro");
			}catch(Exception e) {
				e.printStackTrace();
				LOG.info("Excepción: " + e);
				JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
			}finally {
				try {
					con.close();
				} catch (SQLException ep) {
					ep.printStackTrace();
					LOG.info("Excepción: " + ep);
					JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
				}
			}
		}else {
			JOptionPane.showMessageDialog(null, "Registro no seleccionado para eliminar");
		}
	}//fin del metodo

	//metodo para el tiempo de busqueda
	//Este es el evento que se ejecuta cuando un JFrame se carga
	public void windowOpened(ActionEvent e){
		final long SLEEP_TIME = 1 * 1000;
		SwingWorker<Void, Void> mySwingWorker = new SwingWorker<Void, Void>(){
			protected Void doInBackground() throws Exception {

				ArrayList<String> listaClasif = new ArrayList<String>();
				listaClasif = buscarClasificacion();
				comboBoxClasif.addItem("Seleccione Una");
				comboBoxClasif2.addItem("Seleccione Una");
				for (int i = 0; i < listaClasif.size(); i++) {
					comboBoxClasif.addItem(listaClasif.get(i));
					comboBoxClasif2.addItem(listaClasif.get(i));
				}

				mostrarDatosPercepciones("");
				mostrarDatosDeducciones ("");
				mostrarDatosRegimen("");
				mostrarDatosBanco("");
				mostrarDatosRiesgos("");
				mostrarDatosIncapacidad("");
				mostrarDatosPuestos("");
				mostrarDatosISR();
				mostrarSubsidioISR();
				mostrarDatosImss();
				mostrarDiasInhabiles("");
				mostrarTablaMensualISR();
				mostrarTablaMensualSubsidioISR();
				mostrarTablaIsrAnual();


				//mostrarEmpleadosConClasificacion();
				lblNombrePuesto.setVisible(false);
				lblSalario.setVisible(false);
				textFieldNombrePuesto.setVisible(false);
				textFieldSalario.setVisible(false);
				btnAgregarPuesto.setVisible(false);
				lblComentario.setVisible(false);
				lblNuevaDed.setVisible(false);
				textFieldNuevaDed.setVisible(false);
				textFieldComentario.setVisible(false);
				btnAgregarDed.setVisible(false);

				labelComentt.setVisible(false);
				lblNuevaPerp.setVisible(false);
				textFieldNuevaPerp.setVisible(false);
				textFieldCoement.setVisible(false);
				btnNuevaPerpp.setVisible(false);

				lblCalsifi.setVisible(false); 
				btnActualizarCalsifi.setVisible(false); 
				comboBoxClasif2.setVisible(false);

				lblNumReg.setVisible(false);
				lblNumeroRegistro.setVisible(false);

				lblNuevaClasifi.setVisible(false);
				textFieldNuevaClasifi.setVisible(false);
				btnGuardarNuevaclasif.setVisible(false);

				lblEmpUno.setVisible(false);
				// mimic some long-running process here...
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


	public void mostrarEmpleadosConClasificacion(int atributo) {
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
		modelo.addColumn("CLASIFICACION_DISPERSION");
		tableClasificacion.setModel(modelo);

		JTableHeader th = new JTableHeader();
		JTableHeader th1 = new JTableHeader();
		Color colorSilverLight=new Color(176, 196, 222);
		Color colorNegro=new Color(0, 0, 0);
		th = tableClasificacion.getTableHeader();
		th1 = tableClasificacion.getTableHeader();
		Font fuente = new Font("Arial", Font.BOLD, 14); 
		th.setFont(fuente); 
		th1.setFont(fuente); 
		th.setBackground(colorSilverLight);
		th1.setBackground(colorSilverLight);
		th.setForeground(colorNegro);
		th1.setForeground(colorNegro);

		TableColumnModel columnModel = tableClasificacion.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(50);
		columnModel.getColumn(1).setPreferredWidth(80);
		columnModel.getColumn(2).setPreferredWidth(100);
		columnModel.getColumn(3).setPreferredWidth(100);
		columnModel.getColumn(4).setPreferredWidth(200);
		columnModel.getColumn(5).setPreferredWidth(100);


		String sqlSelect="";
		sqlSelect="select dbo.empleados.clave,dbo.empleados.nombre,dbo.empleados.apellido_paterno,dbo.empleados.apellido_materno,dbo.puestos.NOMBRE_PUESTO,dbo.CLASIFICACION_DISPERSION.CLAVE "
				+ "from dbo.empleados "
				+ "left join dbo.puestos on dbo.empleados.ID_puesto = dbo.puestos.no_puesto "
				+ "left join dbo.CLASIFICACION_DISPERSION on dbo.empleados.ID_CLASIFICACION_DISPERSION = dbo.CLASIFICACION_DISPERSION.ID_CLASIFICACION_DISPERSION "
				+ "WHERE DBO.EMPLEADOS.ELIMINAR_LOGICO='1' and DBO.EMPLEADOS.ID_CLASIFICACION_DISPERSION ='"+atributo+"'  order by DBO.EMPLEADOS.clave";
		System.out.println(sqlSelect);
		Connection con= null;
		PoolNYCH nych = new PoolNYCH();
		String datos[] = new String[7];
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
				modelo.addRow(datos);
			}//FIN DEL WHILE


			tableClasificacion.setModel(modelo);
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

	public void updateEmpClasificacion() {
		PoolNYCH nych = new PoolNYCH();
		Connection con =null;
		String SqlUpdate="";
		DefaultTableModel tmO = (DefaultTableModel) tableClasificacion.getModel();
		if(tmO.getRowCount()>0 ) {
			for (int i = 0; i < tmO.getRowCount(); ++i) {
				String valor = tableClasificacion.getValueAt(i, 0).toString();
				SqlUpdate="update empleados set ID_CLASIFICACION_DISPERSION='"+ comboBoxClasif2.getSelectedIndex() +"' where CLAVE="+ valor + "";//ID_CLASIFICACION_DISPERSION
				System.out.println(SqlUpdate);
				//SqlUpdate="";
				try {
					con = nych.datasource.getConnection();
					PreparedStatement pps = con.prepareStatement(SqlUpdate);
					pps.executeUpdate();
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
						JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
					}
				}

			}//fin del for
			JOptionPane.showMessageDialog(null, "Datos Actualizados");
		}
	}


	public static ArrayList<String> buscarClasificacion() {
		PoolNYCH nych = new PoolNYCH();
		Connection connect = null;
		ResultSet resultSet = null;
		String sqlSelectEjercicio = "SELECT CLASIFICACION_DISPERSION.CLAVE FROM DBO.CLASIFICACION_DISPERSION";
		ArrayList<String> lista = new ArrayList<String>();
		try {
			connect = nych.datasource.getConnection();
			Statement st = connect.createStatement();
			resultSet = st.executeQuery(sqlSelectEjercicio);
			while (resultSet.next()) {
				lista.add(resultSet.getString(1));
			}
		} catch (Exception exp) {
			exp.printStackTrace();
			StringWriter errors = new StringWriter();
			exp.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: "+ errors );
			JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
		} finally {
			try {
				connect.close();
			} catch (SQLException ep) {
				ep.printStackTrace();
				StringWriter errors = new StringWriter();
				ep.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: "+ errors );
				JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
			}
		}
		return lista;
	}



	/*
	 * Metodo Para la busquedda del catalogo de Datos ISR
	 */
	public void mostrarTablaMensualISR() {
		final DefaultTableModel modeloTablaMensualISR = new DefaultTableModel();
		//		modeloPuestos.addColumn("ID");
		modeloTablaMensualISR.addColumn("LIMITE INFERIOR");
		modeloTablaMensualISR.addColumn("LIMITE SUPERIOR");
		modeloTablaMensualISR.addColumn("CUOTA FIJA");
		modeloTablaMensualISR.addColumn("PORCENTAJE");
		modeloTablaMensualISR.addColumn("COMENTARIO");
		tableMensualISR.setModel(modeloTablaMensualISR);
		tableMensualISR.setBackground(Color.WHITE);

		JTableHeader th = new JTableHeader();
		Color colorSilverLight=new Color(176, 196, 222);
		Color colorNegro=new Color(0, 0, 0);
		th = tableMensualISR.getTableHeader();
		Font fuente = new Font("Arial", Font.BOLD, 14); 
		th.setFont(fuente); 
		th.setBackground(colorSilverLight);
		th.setForeground(colorNegro);

		TableColumnModel columnModel = tableMensualISR.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(200);
		columnModel.getColumn(1).setPreferredWidth(200);
		columnModel.getColumn(2).setPreferredWidth(200);
		columnModel.getColumn(3).setPreferredWidth(200);
		columnModel.getColumn(4).setPreferredWidth(800);
		//		columnModel.getColumn(5).setPreferredWidth(800);

		String sqlSelect="";
		sqlSelect = "SELECT limite_inferior,limite_superior,cuota_fija,porcentaje,comentario FROM dbo.TABLA_MENSUAL_ISR";
		String datos[] = new String[6];
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
				//datos[5] = resultSet.getString(6);
				modeloTablaMensualISR.addRow(datos);
			}//FIN DEL WHILE

			modeloTablaMensualISR.addTableModelListener(new TableModelListener() {
				public void tableChanged(TableModelEvent e) {
					if(e.getType() == TableModelEvent.UPDATE) {
						JOptionPane.showMessageDialog(null, "Ha cambiado el valor");
						String colName_LIMITE_INFERIOR =  "LIMITE_INFERIOR";
						String colName_LIMITE_SUPERIOR =  "LIMITE_SUPERIOR";
						String colName_CUOTA_FIJA =  "CUOTA_FIJA";
						String colName_PORCENTAJE = "PORCENTAJE";

						InternalFrameMovimientos internalFrameMovimientos = new InternalFrameMovimientos();
						ArrayList<Double> listaMensualISR = new ArrayList<Double>();
						listaMensualISR = internalFrameMovimientos.getListaTablaMensualISR();
						System.out.println("*********");
						for(int i = 0; i<listaMensualISR.size();i++){
							System.out.println("indiceTablaMensual[isr]: "+ i + " |valorTablaMensual[isr]: " + listaMensualISR.get(i));
						}

						//						String sqlupdt="";
						String sqlupdt ="";
						if(e.getColumn()==0 && e.getFirstRow()==0) {
							sqlupdt="UPDATE DBO.TABLA_MENSUAL_ISR SET "+ colName_LIMITE_INFERIOR +" = '"+ modeloTablaMensualISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where limite_inferior= '"+listaMensualISR.get(0) +"'";
						}

						else if(e.getColumn()==0 && e.getFirstRow()==1) {
							sqlupdt="UPDATE DBO.TABLA_MENSUAL_ISR SET "+ colName_LIMITE_INFERIOR +" = '"+ modeloTablaMensualISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where limite_inferior= '"+listaMensualISR.get(4) +"'";
						}

						else if(e.getColumn()==0 && e.getFirstRow()==2) {

							sqlupdt="UPDATE DBO.TABLA_MENSUAL_ISR SET "+ colName_LIMITE_INFERIOR +" = '"+ modeloTablaMensualISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where limite_inferior= '"+listaMensualISR.get(8) +"'";
						}
						else if(e.getColumn()==0 && e.getFirstRow()==3) {

							sqlupdt="UPDATE DBO.TABLA_MENSUAL_ISR SET "+ colName_LIMITE_INFERIOR +" = '"+ modeloTablaMensualISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where limite_inferior= '"+listaMensualISR.get(12) +"'";
						}
						else if(e.getColumn()==0 && e.getFirstRow()==4) {

							sqlupdt="UPDATE DBO.TABLA_MENSUAL_ISR SET "+ colName_LIMITE_INFERIOR +" = '"+ modeloTablaMensualISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where limite_inferior= '"+listaMensualISR.get(16) +"'";
						}
						else if(e.getColumn()==0 && e.getFirstRow()==5) {

							sqlupdt="UPDATE DBO.TABLA_MENSUAL_ISR SET "+ colName_LIMITE_INFERIOR +" = '"+ modeloTablaMensualISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where limite_inferior= '"+listaMensualISR.get(20) +"'";
						}

						else if(e.getColumn()==0 && e.getFirstRow()==6) {

							sqlupdt="UPDATE DBO.TABLA_MENSUAL_ISR SET "+ colName_LIMITE_INFERIOR +" = '"+ modeloTablaMensualISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where limite_inferior= '"+listaMensualISR.get(24) +"'";
						}

						else if(e.getColumn()==0 && e.getFirstRow()==7) {

							sqlupdt="UPDATE DBO.TABLA_MENSUAL_ISR SET "+ colName_LIMITE_INFERIOR +" = '"+ modeloTablaMensualISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where limite_inferior= '"+listaMensualISR.get(28) +"'";
						}
						else if(e.getColumn()==0 && e.getFirstRow()==8) {

							sqlupdt="UPDATE DBO.TABLA_MENSUAL_ISR SET "+ colName_LIMITE_INFERIOR +" = '"+ modeloTablaMensualISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where limite_inferior= '"+listaMensualISR.get(32) +"'";
						}
						else if(e.getColumn()==0 && e.getFirstRow()==9) {

							sqlupdt="UPDATE DBO.TABLA_MENSUAL_ISR SET "+ colName_LIMITE_INFERIOR +" = '"+ modeloTablaMensualISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where limite_inferior= '"+listaMensualISR.get(36) +"'";
						}
						else if(e.getColumn()==0 && e.getFirstRow()==10) {

							sqlupdt="UPDATE DBO.TABLA_MENSUAL_ISR SET "+ colName_LIMITE_INFERIOR +" = '"+ modeloTablaMensualISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where limite_inferior= '"+listaMensualISR.get(40) +"'";
						}
						//////

						if(e.getColumn()==1 && e.getFirstRow()==0) {
							sqlupdt="UPDATE DBO.TABLA_MENSUAL_ISR SET "+ colName_LIMITE_SUPERIOR +" = '"+ modeloTablaMensualISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where limite_superior= '"+listaMensualISR.get(1) +"'";
						}

						else if(e.getColumn()==1 && e.getFirstRow()==1) {
							sqlupdt="UPDATE DBO.TABLA_MENSUAL_ISR SET "+ colName_LIMITE_SUPERIOR +" = '"+ modeloTablaMensualISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where limite_superior= '"+listaMensualISR.get(5) +"'";
						}

						else if(e.getColumn()==1 && e.getFirstRow()==2) {

							sqlupdt="UPDATE DBO.TABLA_MENSUAL_ISR SET "+ colName_LIMITE_SUPERIOR +" = '"+ modeloTablaMensualISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where limite_superior= '"+listaMensualISR.get(9) +"'";
						}
						else if(e.getColumn()==1 && e.getFirstRow()==3) {

							sqlupdt="UPDATE DBO.TABLA_MENSUAL_ISR SET "+ colName_LIMITE_SUPERIOR +" = '"+ modeloTablaMensualISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where limite_superior= '"+listaMensualISR.get(13) +"'";
						}
						else if(e.getColumn()==1 && e.getFirstRow()==4) {

							sqlupdt="UPDATE DBO.TABLA_MENSUAL_ISR SET "+ colName_LIMITE_SUPERIOR +" = '"+ modeloTablaMensualISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where limite_superior= '"+listaMensualISR.get(17) +"'";
						}
						else if(e.getColumn()==1 && e.getFirstRow()==5) {

							sqlupdt="UPDATE DBO.TABLA_MENSUAL_ISR SET "+ colName_LIMITE_SUPERIOR +" = '"+ modeloTablaMensualISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where limite_superior= '"+listaMensualISR.get(21) +"'";
						}

						else if(e.getColumn()==1 && e.getFirstRow()==6) {

							sqlupdt="UPDATE DBO.TABLA_MENSUAL_ISR SET "+ colName_LIMITE_SUPERIOR +" = '"+ modeloTablaMensualISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where limite_superior= '"+listaMensualISR.get(25) +"'";
						}

						else if(e.getColumn()==1 && e.getFirstRow()==7) {

							sqlupdt="UPDATE DBO.TABLA_MENSUAL_ISR SET "+ colName_LIMITE_SUPERIOR +" = '"+ modeloTablaMensualISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where limite_superior= '"+listaMensualISR.get(29) +"'";
						}
						else if(e.getColumn()==1 && e.getFirstRow()==8) {

							sqlupdt="UPDATE DBO.TABLA_MENSUAL_ISR SET "+ colName_LIMITE_SUPERIOR +" = '"+ modeloTablaMensualISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where limite_superior= '"+listaMensualISR.get(33) +"'";
						}
						else if(e.getColumn()==1 && e.getFirstRow()==9) {

							sqlupdt="UPDATE DBO.TABLA_MENSUAL_ISR SET "+ colName_LIMITE_SUPERIOR +" = '"+ modeloTablaMensualISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where limite_superior= '"+listaMensualISR.get(37) +"'";
						}

						///////
						if(e.getColumn()==2 && e.getFirstRow()==0) {

							sqlupdt="UPDATE DBO.TABLA_MENSUAL_ISR SET "+ colName_CUOTA_FIJA +" = '"+ modeloTablaMensualISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where cuota_fija= '"+listaMensualISR.get(2) +"'";
						}

						else if(e.getColumn()==2 && e.getFirstRow()==1) {

							sqlupdt="UPDATE DBO.TABLA_MENSUAL_ISR SET "+ colName_CUOTA_FIJA +" = '"+ modeloTablaMensualISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where cuota_fija= '"+listaMensualISR.get(6) +"'";
						}

						else if(e.getColumn()==2 && e.getFirstRow()==2) {

							sqlupdt="UPDATE DBO.TABLA_MENSUAL_ISR SET "+ colName_CUOTA_FIJA +" = '"+ modeloTablaMensualISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where cuota_fija= '"+listaMensualISR.get(10) +"'";
						}

						else if(e.getColumn()==2 && e.getFirstRow()==3) {

							sqlupdt="UPDATE DBO.TABLA_MENSUAL_ISR SET "+ colName_CUOTA_FIJA +" = '"+ modeloTablaMensualISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where cuota_fija= '"+listaMensualISR.get(14) +"'";
						}
						else if(e.getColumn()==2 && e.getFirstRow()==4) {

							sqlupdt="UPDATE DBO.TABLA_MENSUAL_ISR SET "+ colName_CUOTA_FIJA +" = '"+ modeloTablaMensualISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where cuota_fija= '"+listaMensualISR.get(18) +"'";
						}
						else if(e.getColumn()==2 && e.getFirstRow()==5) {

							sqlupdt="UPDATE DBO.TABLA_MENSUAL_ISR SET "+ colName_CUOTA_FIJA +" = '"+ modeloTablaMensualISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where cuota_fija= '"+listaMensualISR.get(22) +"'";
						}
						else if(e.getColumn()==2 && e.getFirstRow()==6) {

							sqlupdt="UPDATE DBO.TABLA_MENSUAL_ISR SET "+ colName_CUOTA_FIJA +" = '"+ modeloTablaMensualISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where cuota_fija= '"+listaMensualISR.get(26) +"'";
						}
						else if(e.getColumn()==2 && e.getFirstRow()==7) {

							sqlupdt="UPDATE DBO.TABLA_MENSUAL_ISR SET "+ colName_CUOTA_FIJA +" = '"+ modeloTablaMensualISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where cuota_fija= '"+listaMensualISR.get(30) +"'";
						}
						else if(e.getColumn()==2 && e.getFirstRow()==8) {

							sqlupdt="UPDATE DBO.TABLA_MENSUAL_ISR SET "+ colName_CUOTA_FIJA +" = '"+ modeloTablaMensualISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where cuota_fija= '"+listaMensualISR.get(34) +"'";
						}
						else if(e.getColumn()==2 && e.getFirstRow()==9) {

							sqlupdt="UPDATE DBO.TABLA_MENSUAL_ISR SET "+ colName_CUOTA_FIJA +" = '"+ modeloTablaMensualISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where cuota_fija= '"+listaMensualISR.get(38) +"'";
						}
						else if(e.getColumn()==2 && e.getFirstRow()==10) {

							sqlupdt="UPDATE DBO.TABLA_MENSUAL_ISR SET "+ colName_CUOTA_FIJA +" = '"+ modeloTablaMensualISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where cuota_fija= '"+listaMensualISR.get(42) +"'";
						}
						//////
						if(e.getColumn()==4 && e.getFirstRow()==0) {

							sqlupdt="UPDATE DBO.TABLA_MENSUAL_ISR SET "+ colName_PORCENTAJE +" = '"+ modeloTablaMensualISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where PORCENTAJE= '"+listaMensualISR.get(3) +"'";
						}
						else if(e.getColumn()==4 && e.getFirstRow()==1) {

							sqlupdt="UPDATE DBO.TABLA_MENSUAL_ISR SET "+ colName_PORCENTAJE +" = '"+ modeloTablaMensualISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where PORCENTAJE= '"+listaMensualISR.get(7) +"'";
						}
						else if(e.getColumn()==4 && e.getFirstRow()==2) {

							sqlupdt="UPDATE DBO.TABLA_MENSUAL_ISR SET "+ colName_PORCENTAJE +" = '"+ modeloTablaMensualISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where PORCENTAJE= '"+listaMensualISR.get(11) +"'";
						}
						else if(e.getColumn()==4 && e.getFirstRow()==3) {

							sqlupdt="UPDATE DBO.TABLA_MENSUAL_ISR SET "+ colName_PORCENTAJE +" = '"+ modeloTablaMensualISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where PORCENTAJE= '"+listaMensualISR.get(15) +"'";
						}
						else if(e.getColumn()==4 && e.getFirstRow()==4) {

							sqlupdt="UPDATE DBO.TABLA_MENSUAL_ISR SET "+ colName_PORCENTAJE +" = '"+ modeloTablaMensualISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where PORCENTAJE= '"+listaMensualISR.get(19) +"'";
						}
						else if(e.getColumn()==4 && e.getFirstRow()==5) {

							sqlupdt="UPDATE DBO.TABLA_MENSUAL_ISR SET "+ colName_PORCENTAJE +" = '"+ modeloTablaMensualISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where PORCENTAJE= '"+listaMensualISR.get(23) +"'";
						}
						else if(e.getColumn()==4 && e.getFirstRow()==6) {

							sqlupdt="UPDATE DBO.TABLA_MENSUAL_ISR SET "+ colName_PORCENTAJE +" = '"+ modeloTablaMensualISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where PORCENTAJE= '"+listaMensualISR.get(27) +"'";
						}
						else if(e.getColumn()==4 && e.getFirstRow()==7) {

							sqlupdt="UPDATE DBO.TABLA_MENSUAL_ISR SET "+ colName_PORCENTAJE +" = '"+ modeloTablaMensualISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where PORCENTAJE= '"+listaMensualISR.get(31) +"'";
						}
						else if(e.getColumn()==4 && e.getFirstRow()==8) {

							sqlupdt="UPDATE DBO.TABLA_MENSUAL_ISR SET "+ colName_PORCENTAJE +" = '"+ modeloTablaMensualISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where PORCENTAJE= '"+listaMensualISR.get(35) +"'";
						}
						else if(e.getColumn()==4 && e.getFirstRow()==9) {

							sqlupdt="UPDATE DBO.TABLA_MENSUAL_ISR SET "+ colName_PORCENTAJE +" = '"+ modeloTablaMensualISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where PORCENTAJE= '"+listaMensualISR.get(39) +"'";
						}
						else if(e.getColumn()==4 && e.getFirstRow()==10) {

							sqlupdt="UPDATE DBO.TABLA_MENSUAL_ISR SET "+ colName_PORCENTAJE +" = '"+ modeloTablaMensualISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where PORCENTAJE= '"+listaMensualISR.get(43) +"'";
						}

						System.out.println(sqlupdt);
						PoolNYCH nych1 = new PoolNYCH();
						Connection con1 = null;
						try {
							con1 = nych1.datasource.getConnection();
							PreparedStatement pps = con1.prepareStatement(sqlupdt);
							pps.executeUpdate();
							JOptionPane.showMessageDialog(null, "Datos Actualizados");
							//dispose();
						} catch (SQLException se) {
							se.printStackTrace();
							StringWriter errors = new StringWriter();
							se.printStackTrace(new PrintWriter(errors));
							LOG.info("Excepcion: "+ errors );
							JOptionPane.showMessageDialog(null, "Datos No Actualizados");
						}finally {
							try {
								con1.close();
							} catch (SQLException ep) {
								ep.printStackTrace();
								StringWriter errors = new StringWriter();
								ep.printStackTrace(new PrintWriter(errors));
								LOG.info("Excepcion: "+ errors );
								JOptionPane.showMessageDialog(null, ep, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
							}
						}
					}//fin del if e.getype

				}
			});
			tableMensualISR.setModel(modeloTablaMensualISR);
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

	/*
	 * Metodo Para la busquedda del catalogo mensual de Subsidio ISR
	 */
	public void mostrarTablaMensualSubsidioISR() {
		final DefaultTableModel modeloTablaMensualSubsidioISR = new DefaultTableModel();
		//		modeloSubsidioISR.addColumn("ID");
		modeloTablaMensualSubsidioISR.addColumn("LIMITE INFERIOR");
		modeloTablaMensualSubsidioISR.addColumn("LIMITE SUPERIOR");
		modeloTablaMensualSubsidioISR.addColumn("CANTIDAD");
		modeloTablaMensualSubsidioISR.addColumn("COMENTARIO");
		tableMensualSubisidioISR.setModel(modeloTablaMensualSubsidioISR);
		tableMensualSubisidioISR.setBackground(Color.WHITE);

		JTableHeader th = new JTableHeader();
		Color colorSilverLight=new Color(176, 196, 222);
		Color colorNegro=new Color(0, 0, 0);
		th = tableMensualSubisidioISR.getTableHeader();
		Font fuente = new Font("Arial", Font.BOLD, 14); 
		th.setFont(fuente); 
		th.setBackground(colorSilverLight);
		th.setForeground(colorNegro);

		TableColumnModel columnModel = tableMensualSubisidioISR.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(200);
		columnModel.getColumn(1).setPreferredWidth(200);
		columnModel.getColumn(2).setPreferredWidth(200);
		columnModel.getColumn(3).setPreferredWidth(800);
		//		columnModel.getColumn(4).setPreferredWidth(800);

		String sqlSelect="";
		sqlSelect = "SELECT LIMITE_INFERIOR,LIMITE_SUPERIOR,CANTIDAD,comentario FROM dbo.TABLA_MENSUAL_SUBSIDIO_ISR";
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
				datos[3] = resultSet.getString(4);
				//				datos[4] = resultSet.getString(5);
				modeloTablaMensualSubsidioISR.addRow(datos);
			}//FIN DEL WHILE

			modeloTablaMensualSubsidioISR.addTableModelListener(new TableModelListener() {
				public void tableChanged(TableModelEvent e) {
					if(e.getType() == TableModelEvent.UPDATE) {
						JOptionPane.showMessageDialog(null, "Ha cambiado el valor");
						String colName_LIMITE_INFERIOR = "LIMITE_INFERIOR";
						String colName_LIMITE_SUPERIOR = "LIMITE_SUPERIOR";
						String colName_CANTIDAD = "CANTIDAD";

						InternalFrameMovimientos internalFrameMovimientos = new InternalFrameMovimientos();
						ArrayList<Double> listaTablaMensualSubsidioISR = new ArrayList<Double>();
						listaTablaMensualSubsidioISR = internalFrameMovimientos.getListaTablaMensualSubsidioISR();
						System.out.println("*********");
						for(int i = 0; i<listaTablaMensualSubsidioISR.size();i++){
							System.out.println("indiceTablaMensualSubsidio[isr]: "+ i + " |valorTablaMensualSubsidio[isr]: " + listaTablaMensualSubsidioISR.get(i));
						}

						String sqlupdt ="";
						if(e.getColumn()==0 && e.getFirstRow()==0) {
							sqlupdt="UPDATE DBO.TABLA_MENSUAL_SUBSIDIO_ISR SET "+ colName_LIMITE_INFERIOR +" = '"+ modeloTablaMensualSubsidioISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where LIMITE_INFERIOR= '"+listaTablaMensualSubsidioISR.get(0) +"'";
						}

						else if(e.getColumn()==0 && e.getFirstRow()==1) {
							sqlupdt="UPDATE DBO.TABLA_MENSUAL_SUBSIDIO_ISR SET "+ colName_LIMITE_INFERIOR +" = '"+ modeloTablaMensualSubsidioISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where LIMITE_INFERIOR= '"+listaTablaMensualSubsidioISR.get(3) +"'";
						}

						else if(e.getColumn()==0 && e.getFirstRow()==2) {

							sqlupdt="UPDATE DBO.TABLA_MENSUAL_SUBSIDIO_ISR SET "+ colName_LIMITE_INFERIOR +" = '"+ modeloTablaMensualSubsidioISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where LIMITE_INFERIOR= '"+listaTablaMensualSubsidioISR.get(6) +"'";
						}
						else if(e.getColumn()==0 && e.getFirstRow()==3) {

							sqlupdt="UPDATE DBO.TABLA_MENSUAL_SUBSIDIO_ISR SET "+ colName_LIMITE_INFERIOR +" = '"+ modeloTablaMensualSubsidioISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where LIMITE_INFERIOR= '"+listaTablaMensualSubsidioISR.get(9) +"'";
						}
						else if(e.getColumn()==0 && e.getFirstRow()==4) {

							sqlupdt="UPDATE DBO.TABLA_MENSUAL_SUBSIDIO_ISR SET "+ colName_LIMITE_INFERIOR +" = '"+ modeloTablaMensualSubsidioISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where LIMITE_INFERIOR= '"+listaTablaMensualSubsidioISR.get(12) +"'";
						}
						else if(e.getColumn()==0 && e.getFirstRow()==5) {

							sqlupdt="UPDATE DBO.TABLA_MENSUAL_SUBSIDIO_ISR SET "+ colName_LIMITE_INFERIOR +" = '"+ modeloTablaMensualSubsidioISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where LIMITE_INFERIOR= '"+listaTablaMensualSubsidioISR.get(15) +"'";
						}

						else if(e.getColumn()==0 && e.getFirstRow()==6) {

							sqlupdt="UPDATE DBO.TABLA_MENSUAL_SUBSIDIO_ISR SET "+ colName_LIMITE_INFERIOR +" = '"+ modeloTablaMensualSubsidioISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where LIMITE_INFERIOR= '"+listaTablaMensualSubsidioISR.get(18) +"'";
						}

						else if(e.getColumn()==0 && e.getFirstRow()==7) {

							sqlupdt="UPDATE DBO.TABLA_MENSUAL_SUBSIDIO_ISR SET "+ colName_LIMITE_INFERIOR +" = '"+ modeloTablaMensualSubsidioISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where LIMITE_INFERIOR= '"+listaTablaMensualSubsidioISR.get(21) +"'";
						}
						else if(e.getColumn()==0 && e.getFirstRow()==8) {

							sqlupdt="UPDATE DBO.TABLA_MENSUAL_SUBSIDIO_ISR SET "+ colName_LIMITE_INFERIOR +" = '"+ modeloTablaMensualSubsidioISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where LIMITE_INFERIOR= '"+listaTablaMensualSubsidioISR.get(24) +"'";
						}
						else if(e.getColumn()==0 && e.getFirstRow()==9) {

							sqlupdt="UPDATE DBO.TABLA_MENSUAL_SUBSIDIO_ISR SET "+ colName_LIMITE_INFERIOR +" = '"+ modeloTablaMensualSubsidioISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where LIMITE_INFERIOR= '"+listaTablaMensualSubsidioISR.get(27) +"'";
						}
						else if(e.getColumn()==0 && e.getFirstRow()==10) {

							sqlupdt="UPDATE DBO.TABLA_MENSUAL_SUBSIDIO_ISR SET "+ colName_LIMITE_INFERIOR +" = '"+ modeloTablaMensualSubsidioISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where LIMITE_INFERIOR= '"+listaTablaMensualSubsidioISR.get(30) +"'";
						}
						///////


						if(e.getColumn()==1 && e.getFirstRow()==0) {
							sqlupdt="UPDATE DBO.TABLA_MENSUAL_SUBSIDIO_ISR SET "+ colName_LIMITE_SUPERIOR +" = '"+ modeloTablaMensualSubsidioISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where LIMITE_SUPERIOR= '"+listaTablaMensualSubsidioISR.get(0) +"'";
						}

						else if(e.getColumn()==1 && e.getFirstRow()==1) {
							sqlupdt="UPDATE DBO.TABLA_MENSUAL_SUBSIDIO_ISR SET "+ colName_LIMITE_SUPERIOR +" = '"+ modeloTablaMensualSubsidioISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where LIMITE_SUPERIOR= '"+listaTablaMensualSubsidioISR.get(3) +"'";
						}

						else if(e.getColumn()==1 && e.getFirstRow()==2) {

							sqlupdt="UPDATE DBO.TABLA_MENSUAL_SUBSIDIO_ISR SET "+ colName_LIMITE_SUPERIOR +" = '"+ modeloTablaMensualSubsidioISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where LIMITE_SUPERIOR= '"+listaTablaMensualSubsidioISR.get(6) +"'";
						}
						else if(e.getColumn()==1 && e.getFirstRow()==3) {

							sqlupdt="UPDATE DBO.TABLA_MENSUAL_SUBSIDIO_ISR SET "+ colName_LIMITE_SUPERIOR +" = '"+ modeloTablaMensualSubsidioISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where LIMITE_SUPERIOR= '"+listaTablaMensualSubsidioISR.get(9) +"'";
						}
						else if(e.getColumn()==1 && e.getFirstRow()==4) {

							sqlupdt="UPDATE DBO.TABLA_MENSUAL_SUBSIDIO_ISR SET "+ colName_LIMITE_SUPERIOR +" = '"+ modeloTablaMensualSubsidioISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where LIMITE_SUPERIOR= '"+listaTablaMensualSubsidioISR.get(12) +"'";
						}
						else if(e.getColumn()==1 && e.getFirstRow()==5) {

							sqlupdt="UPDATE DBO.TABLA_MENSUAL_SUBSIDIO_ISR SET "+ colName_LIMITE_SUPERIOR +" = '"+ modeloTablaMensualSubsidioISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where LIMITE_SUPERIOR= '"+listaTablaMensualSubsidioISR.get(15) +"'";
						}

						else if(e.getColumn()==1 && e.getFirstRow()==6) {

							sqlupdt="UPDATE DBO.TABLA_MENSUAL_SUBSIDIO_ISR SET "+ colName_LIMITE_SUPERIOR +" = '"+ modeloTablaMensualSubsidioISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where LIMITE_SUPERIOR= '"+listaTablaMensualSubsidioISR.get(18) +"'";
						}

						else if(e.getColumn()==1 && e.getFirstRow()==7) {

							sqlupdt="UPDATE DBO.TABLA_MENSUAL_SUBSIDIO_ISR SET "+ colName_LIMITE_SUPERIOR +" = '"+ modeloTablaMensualSubsidioISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where LIMITE_SUPERIOR= '"+listaTablaMensualSubsidioISR.get(21) +"'";
						}
						else if(e.getColumn()==1 && e.getFirstRow()==8) {

							sqlupdt="UPDATE DBO.TABLA_MENSUAL_SUBSIDIO_ISR SET "+ colName_LIMITE_SUPERIOR +" = '"+ modeloTablaMensualSubsidioISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where LIMITE_SUPERIOR= '"+listaTablaMensualSubsidioISR.get(24) +"'";
						}
						else if(e.getColumn()==1 && e.getFirstRow()==9) {

							sqlupdt="UPDATE DBO.TABLA_MENSUAL_SUBSIDIO_ISR SET "+ colName_LIMITE_SUPERIOR +" = '"+ modeloTablaMensualSubsidioISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where LIMITE_SUPERIOR= '"+listaTablaMensualSubsidioISR.get(27) +"'";
						}
						else if(e.getColumn()==1 && e.getFirstRow()==10) {

							sqlupdt="UPDATE DBO.TABLA_MENSUAL_SUBSIDIO_ISR SET "+ colName_LIMITE_SUPERIOR +" = '"+ modeloTablaMensualSubsidioISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where LIMITE_SUPERIOR= '"+listaTablaMensualSubsidioISR.get(30) +"'";
						}


						///////
						if(e.getColumn()==2 && e.getFirstRow()==0) {

							sqlupdt="UPDATE DBO.TABLA_MENSUAL_SUBSIDIO_ISR SET "+ colName_CANTIDAD +" = '"+ modeloTablaMensualSubsidioISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where CANTIDAD= '"+listaTablaMensualSubsidioISR.get(2) +"'";
						}

						else if(e.getColumn()==2 && e.getFirstRow()==1) {

							sqlupdt="UPDATE DBO.TABLA_MENSUAL_SUBSIDIO_ISR SET "+ colName_CANTIDAD +" = '"+ modeloTablaMensualSubsidioISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where CANTIDAD= '"+listaTablaMensualSubsidioISR.get(5) +"'";
						}

						else if(e.getColumn()==2 && e.getFirstRow()==2) {

							sqlupdt="UPDATE DBO.TABLA_MENSUAL_SUBSIDIO_ISR SET "+ colName_CANTIDAD +" = '"+ modeloTablaMensualSubsidioISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where CANTIDAD= '"+listaTablaMensualSubsidioISR.get(8) +"'";
						}

						else if(e.getColumn()==2 && e.getFirstRow()==3) {

							sqlupdt="UPDATE DBO.TABLA_MENSUAL_SUBSIDIO_ISR SET "+ colName_CANTIDAD +" = '"+ modeloTablaMensualSubsidioISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where CANTIDAD= '"+listaTablaMensualSubsidioISR.get(11) +"'";
						}
						else if(e.getColumn()==2 && e.getFirstRow()==4) {

							sqlupdt="UPDATE DBO.TABLA_MENSUAL_SUBSIDIO_ISR SET "+ colName_CANTIDAD +" = '"+ modeloTablaMensualSubsidioISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where CANTIDAD= '"+listaTablaMensualSubsidioISR.get(14) +"'";
						}
						else if(e.getColumn()==2 && e.getFirstRow()==5) {

							sqlupdt="UPDATE DBO.TABLA_MENSUAL_SUBSIDIO_ISR SET "+ colName_CANTIDAD +" = '"+ modeloTablaMensualSubsidioISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where CANTIDAD= '"+listaTablaMensualSubsidioISR.get(17) +"'";
						}
						else if(e.getColumn()==2 && e.getFirstRow()==6) {

							sqlupdt="UPDATE DBO.TABLA_MENSUAL_SUBSIDIO_ISR SET "+ colName_CANTIDAD +" = '"+ modeloTablaMensualSubsidioISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where CANTIDAD= '"+listaTablaMensualSubsidioISR.get(20) +"'";
						}
						else if(e.getColumn()==2 && e.getFirstRow()==7) {

							sqlupdt="UPDATE DBO.TABLA_MENSUAL_SUBSIDIO_ISR SET "+ colName_CANTIDAD +" = '"+ modeloTablaMensualSubsidioISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where CANTIDAD= '"+listaTablaMensualSubsidioISR.get(23) +"'";
						}
						else if(e.getColumn()==2 && e.getFirstRow()==8) {

							sqlupdt="UPDATE DBO.TABLA_MENSUAL_SUBSIDIO_ISR SET "+ colName_CANTIDAD +" = '"+ modeloTablaMensualSubsidioISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where CANTIDAD= '"+listaTablaMensualSubsidioISR.get(26) +"'";
						}
						else if(e.getColumn()==2 && e.getFirstRow()==9) {

							sqlupdt="UPDATE DBO.TABLA_MENSUAL_SUBSIDIO_ISR SET "+ colName_CANTIDAD +" = '"+ modeloTablaMensualSubsidioISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where CANTIDAD= '"+listaTablaMensualSubsidioISR.get(29) +"'";
						}
						else if(e.getColumn()==2 && e.getFirstRow()==10) {

							sqlupdt="UPDATE DBO.TABLA_MENSUAL_SUBSIDIO_ISR SET "+ colName_CANTIDAD +" = '"+ modeloTablaMensualSubsidioISR.getValueAt(e.getFirstRow(), e.getColumn()) +"' where CANTIDAD= '"+listaTablaMensualSubsidioISR.get(32) +"'";
						}

						System.out.println(sqlupdt);
						PoolNYCH nych1 = new PoolNYCH();
						Connection con1 = null;
						try {
							con1 = nych1.datasource.getConnection();
							PreparedStatement pps = con1.prepareStatement(sqlupdt);
							pps.executeUpdate();
							JOptionPane.showMessageDialog(null, "Datos Actualizados");
							//dispose();
						} catch (SQLException se) {
							se.printStackTrace();
							StringWriter errors = new StringWriter();
							se.printStackTrace(new PrintWriter(errors));
							LOG.info("Excepcion: "+ errors );
							JOptionPane.showMessageDialog(null, "Datos No Actualizados");
						}finally {
							try {
								con1.close();
							} catch (SQLException ep) {
								ep.printStackTrace();
								StringWriter errors = new StringWriter();
								ep.printStackTrace(new PrintWriter(errors));
								LOG.info("Excepcion: "+ errors );
								JOptionPane.showMessageDialog(null, ep, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
							}
						}
					}//fin del if e.getype

				}
			});

			tableMensualSubisidioISR.setModel(modeloTablaMensualSubsidioISR);
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


	/*
	 * Metodo Para la busquedda del catalogo de Datos ISR Anual
	 */
	public void mostrarTablaIsrAnual() {
		final DefaultTableModel modeloPuestos = new DefaultTableModel();
		modeloPuestos.addColumn("ID");
		modeloPuestos.addColumn("LIMITE INFERIOR");
		modeloPuestos.addColumn("LIMITE SUPERIOR");
		modeloPuestos.addColumn("CUOTA FIJA");
		modeloPuestos.addColumn("TASA EXCEDENTE");
		modeloPuestos.addColumn("COMENTARIO");
		tableTablaIsrAnual.setModel(modeloPuestos);
		tableTablaIsrAnual.setBackground(Color.WHITE);

		JTableHeader th = new JTableHeader();
		Color colorSilverLight=new Color(176, 196, 222);
		Color colorNegro=new Color(0, 0, 0);
		th = tableTablaIsrAnual.getTableHeader();
		Font fuente = new Font("Arial", Font.BOLD, 14); 
		th.setFont(fuente); 
		th.setBackground(colorSilverLight);
		th.setForeground(colorNegro);

		TableColumnModel columnModel = tableTablaIsrAnual.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(100);
		columnModel.getColumn(1).setPreferredWidth(200);
		columnModel.getColumn(2).setPreferredWidth(200);
		columnModel.getColumn(3).setPreferredWidth(200);
		columnModel.getColumn(4).setPreferredWidth(200);
		columnModel.getColumn(5).setPreferredWidth(800);

		String sqlSelect="";
		sqlSelect = "SELECT id_isr_anual,limite_inferior,limite_superior,cuota_fija,porcentaje_excedente,comentarios FROM dbo.tabla_isr_anual";
		String datos[] = new String[6];
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
				modeloPuestos.addRow(datos);
			}//FIN DEL WHILE

			modeloPuestos.addTableModelListener(new TableModelListener() {
				public void tableChanged(TableModelEvent e) {
					if(e.getType() == TableModelEvent.UPDATE) {
						JOptionPane.showMessageDialog(null, "Ha cambiado el valor, ¿Es correcto?");
						String colName_LIMITE_INFERIOR =  "LIMITE_INFERIOR";
						String colName_LIMITE_SUPERIOR =  "LIMITE_SUPERIOR";
						String colName_CUOTA_FIJA =  "CUOTA_FIJA";
						String colName_PORCENTAJE_EXCEDENTE = "PORCENTAJE_EXCEDENTE";


						ArrayList<Double> listaDatosISRAnual = new ArrayList<Double>();
						listaDatosISRAnual = getListaDatosISRAnual();
						System.out.println("*********");
						for(int i = 0; i<listaDatosISRAnual.size();i++){
							System.out.println("indiceDatos[isr] anual: "+ i + " |valorDatos[isr] anual: " + listaDatosISRAnual.get(i));
						}

						String sqlupdt ="";
						if(e.getColumn()==1 && e.getFirstRow()==0) {
							sqlupdt="UPDATE DBO.tabla_isr_anual SET "+ colName_LIMITE_INFERIOR +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where limite_inferior= '"+listaDatosISRAnual.get(0) +"'";
						}

						else if(e.getColumn()==1 && e.getFirstRow()==1) {
							sqlupdt="UPDATE DBO.tabla_isr_anual SET "+ colName_LIMITE_INFERIOR +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where limite_inferior= '"+listaDatosISRAnual.get(3) +"'";
						}

						else if(e.getColumn()==1 && e.getFirstRow()==2) {

							sqlupdt="UPDATE DBO.tabla_isr_anual SET "+ colName_LIMITE_INFERIOR +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where limite_inferior= '"+listaDatosISRAnual.get(6) +"'";
						}
						else if(e.getColumn()==1 && e.getFirstRow()==3) {

							sqlupdt="UPDATE DBO.tabla_isr_anual SET "+ colName_LIMITE_INFERIOR +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where limite_inferior= '"+listaDatosISRAnual.get(9) +"'";
						}
						else if(e.getColumn()==1 && e.getFirstRow()==4) {

							sqlupdt="UPDATE DBO.tabla_isr_anual SET "+ colName_LIMITE_INFERIOR +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where limite_inferior= '"+listaDatosISRAnual.get(12) +"'";
						}
						else if(e.getColumn()==1 && e.getFirstRow()==5) {

							sqlupdt="UPDATE DBO.tabla_isr_anual SET "+ colName_LIMITE_INFERIOR +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where limite_inferior= '"+listaDatosISRAnual.get(15) +"'";
						}

						else if(e.getColumn()==1 && e.getFirstRow()==6) {

							sqlupdt="UPDATE DBO.tabla_isr_anual SET "+ colName_LIMITE_INFERIOR +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where limite_inferior= '"+listaDatosISRAnual.get(18) +"'";
						}

						else if(e.getColumn()==1 && e.getFirstRow()==7) {

							sqlupdt="UPDATE DBO.tabla_isr_anual SET "+ colName_LIMITE_INFERIOR +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where limite_inferior= '"+listaDatosISRAnual.get(21) +"'";
						}
						else if(e.getColumn()==1 && e.getFirstRow()==8) {

							sqlupdt="UPDATE DBO.tabla_isr_anual SET "+ colName_LIMITE_INFERIOR +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where limite_inferior= '"+listaDatosISRAnual.get(24) +"'";
						}
						else if(e.getColumn()==1 && e.getFirstRow()==9) {

							sqlupdt="UPDATE DBO.tabla_isr_anual SET "+ colName_LIMITE_INFERIOR +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where limite_inferior= '"+listaDatosISRAnual.get(27) +"'";
						}
						else if(e.getColumn()==1 && e.getFirstRow()==10) {

							sqlupdt="UPDATE DBO.tabla_isr_anual SET "+ colName_LIMITE_INFERIOR +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where limite_inferior= '"+listaDatosISRAnual.get(30) +"'";
						}
						///////

						if(e.getColumn()==3 && e.getFirstRow()==0) {

							sqlupdt="UPDATE DBO.tabla_isr_anual SET "+ colName_CUOTA_FIJA +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where cuota_fija= '"+listaDatosISRAnual.get(1) +"'";
						}

						else if(e.getColumn()==3 && e.getFirstRow()==1) {

							sqlupdt="UPDATE DBO.tabla_isr_anual SET "+ colName_CUOTA_FIJA +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where cuota_fija= '"+listaDatosISRAnual.get(4) +"'";
						}

						else if(e.getColumn()==3 && e.getFirstRow()==2) {

							sqlupdt="UPDATE DBO.tabla_isr_anual SET "+ colName_CUOTA_FIJA +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where cuota_fija= '"+listaDatosISRAnual.get(7) +"'";
						}

						else if(e.getColumn()==3 && e.getFirstRow()==3) {

							sqlupdt="UPDATE DBO.tabla_isr_anual SET "+ colName_CUOTA_FIJA +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where cuota_fija= '"+listaDatosISRAnual.get(10) +"'";
						}
						else if(e.getColumn()==3 && e.getFirstRow()==4) {

							sqlupdt="UPDATE DBO.tabla_isr_anual SET "+ colName_CUOTA_FIJA +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where cuota_fija= '"+listaDatosISRAnual.get(13) +"'";
						}
						else if(e.getColumn()==3 && e.getFirstRow()==5) {

							sqlupdt="UPDATE DBO.tabla_isr_anual SET "+ colName_CUOTA_FIJA +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where cuota_fija= '"+listaDatosISRAnual.get(16) +"'";
						}
						else if(e.getColumn()==3 && e.getFirstRow()==6) {

							sqlupdt="UPDATE DBO.tabla_isr_anual SET "+ colName_CUOTA_FIJA +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where cuota_fija= '"+listaDatosISRAnual.get(19) +"'";
						}
						else if(e.getColumn()==3 && e.getFirstRow()==7) {

							sqlupdt="UPDATE DBO.tabla_isr_anual SET "+ colName_CUOTA_FIJA +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where cuota_fija= '"+listaDatosISRAnual.get(22) +"'";
						}
						else if(e.getColumn()==3 && e.getFirstRow()==8) {

							sqlupdt="UPDATE DBO.tabla_isr_anual SET "+ colName_CUOTA_FIJA +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where cuota_fija= '"+listaDatosISRAnual.get(25) +"'";
						}
						else if(e.getColumn()==3 && e.getFirstRow()==9) {

							sqlupdt="UPDATE DBO.tabla_isr_anual SET "+ colName_CUOTA_FIJA +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where cuota_fija= '"+listaDatosISRAnual.get(28) +"'";
						}
						else if(e.getColumn()==3 && e.getFirstRow()==10) {

							sqlupdt="UPDATE DBO.tabla_isr_anual SET "+ colName_CUOTA_FIJA +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where cuota_fija= '"+listaDatosISRAnual.get(31) +"'";
						}
						//////
						if(e.getColumn()==4 && e.getFirstRow()==0) {

							sqlupdt="UPDATE DBO.tabla_isr_anual SET "+ colName_PORCENTAJE_EXCEDENTE +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where PORCENTAJE_EXCEDENTE= '"+listaDatosISRAnual.get(2) +"'";
						}
						else if(e.getColumn()==4 && e.getFirstRow()==1) {

							sqlupdt="UPDATE DBO.tabla_isr_anual SET "+ colName_PORCENTAJE_EXCEDENTE +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where PORCENTAJE_EXCEDENTE= '"+listaDatosISRAnual.get(5) +"'";
						}
						else if(e.getColumn()==4 && e.getFirstRow()==2) {

							sqlupdt="UPDATE DBO.tabla_isr_anual SET "+ colName_PORCENTAJE_EXCEDENTE +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where PORCENTAJE_EXCEDENTE= '"+listaDatosISRAnual.get(8) +"'";
						}
						else if(e.getColumn()==4 && e.getFirstRow()==3) {

							sqlupdt="UPDATE DBO.tabla_isr_anual SET "+ colName_PORCENTAJE_EXCEDENTE +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where PORCENTAJE_EXCEDENTE= '"+listaDatosISRAnual.get(11) +"'";
						}
						else if(e.getColumn()==4 && e.getFirstRow()==4) {

							sqlupdt="UPDATE DBO.tabla_isr_anual SET "+ colName_PORCENTAJE_EXCEDENTE +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where PORCENTAJE_EXCEDENTE= '"+listaDatosISRAnual.get(14) +"'";
						}
						else if(e.getColumn()==4 && e.getFirstRow()==5) {

							sqlupdt="UPDATE DBO.tabla_isr_anual SET "+ colName_PORCENTAJE_EXCEDENTE +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where PORCENTAJE_EXCEDENTE= '"+listaDatosISRAnual.get(17) +"'";
						}
						else if(e.getColumn()==4 && e.getFirstRow()==6) {

							sqlupdt="UPDATE DBO.tabla_isr_anual SET "+ colName_PORCENTAJE_EXCEDENTE +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where PORCENTAJE_EXCEDENTE= '"+listaDatosISRAnual.get(20) +"'";
						}
						else if(e.getColumn()==4 && e.getFirstRow()==7) {

							sqlupdt="UPDATE DBO.tabla_isr_anual SET "+ colName_PORCENTAJE_EXCEDENTE +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where PORCENTAJE_EXCEDENTE= '"+listaDatosISRAnual.get(23) +"'";
						}
						else if(e.getColumn()==4 && e.getFirstRow()==8) {

							sqlupdt="UPDATE DBO.tabla_isr_anual SET "+ colName_PORCENTAJE_EXCEDENTE +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where PORCENTAJE_EXCEDENTE= '"+listaDatosISRAnual.get(26) +"'";
						}
						else if(e.getColumn()==4 && e.getFirstRow()==9) {

							sqlupdt="UPDATE DBO.tabla_isr_anual SET "+ colName_PORCENTAJE_EXCEDENTE +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where PORCENTAJE_EXCEDENTE= '"+listaDatosISRAnual.get(29) +"'";
						}
						else if(e.getColumn()==4 && e.getFirstRow()==10) {

							sqlupdt="UPDATE DBO.tabla_isr_anual SET "+ colName_PORCENTAJE_EXCEDENTE +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where PORCENTAJE_EXCEDENTE= '"+listaDatosISRAnual.get(32) +"'";
						}

						System.out.println(sqlupdt);
						PoolNYCH nych1 = new PoolNYCH();
						Connection con1 = null;
						try {
							con1 = nych1.datasource.getConnection();
							PreparedStatement pps = con1.prepareStatement(sqlupdt);
							pps.executeUpdate();
							JOptionPane.showMessageDialog(null, "Datos Actualizados");
							//dispose();
						} catch (SQLException se) {
							se.printStackTrace();
							StringWriter errors = new StringWriter();
							se.printStackTrace(new PrintWriter(errors));
							LOG.info("Excepcion: "+ errors );
							JOptionPane.showMessageDialog(null, "Datos No Actualizados");
						}finally {
							try {
								con1.close();
							} catch (SQLException ep) {
								ep.printStackTrace();
								StringWriter errors = new StringWriter();
								ep.printStackTrace(new PrintWriter(errors));
								LOG.info("Excepcion: "+ errors );
								JOptionPane.showMessageDialog(null, ep, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
							}
						}
					}//fin del if e.getype

				}
			});
			tableTablaIsrAnual.setModel(modeloPuestos);
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


	public static ArrayList<Double> getListaDatosISRAnual() {
		PoolNYCH nych = new PoolNYCH();
		Connection connect = null;
		Statement st;
		ResultSet resultSet = null;
		ArrayList<Double> listaDatosISR = new ArrayList<Double>();
		String sqlDatosISR = "select * from tabla_isr_anual ";
		try {
			connect = nych.datasource.getConnection();
			st = connect.createStatement();
			resultSet = st.executeQuery(sqlDatosISR);
		}catch(Exception exp) {
			exp.printStackTrace();
			StringWriter errors = new StringWriter();
			exp.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: "+ errors );
			JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
		}try {
			while(resultSet.next()){
				listaDatosISR.add(resultSet.getDouble(2));
				listaDatosISR.add(resultSet.getDouble(4));
				listaDatosISR.add(resultSet.getDouble(5));

				//				System.out.println("resultSet.getDouble(2) isr anual: "+resultSet.getDouble(2));
				//				System.out.println("resultSet.getDouble(4) isr anual: "+resultSet.getDouble(4));
				//				System.out.println("resultSet.getDouble(5) isr anual: "+resultSet.getDouble(5));
			}


		}catch(Exception et) {
			et.printStackTrace();
			StringWriter errors = new StringWriter();
			et.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: "+ errors );
			JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
		}finally {
			try {
				connect.close();
			} catch (SQLException ep) {
				ep.printStackTrace();
				StringWriter errors = new StringWriter();
				ep.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: "+ errors );
				JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
			}
		}
		return listaDatosISR;

	}
}




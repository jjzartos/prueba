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
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

import org.apache.poi.xwpf.usermodel.Borders;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.slf4j.LoggerFactory;

import com.toedter.calendar.JDateChooser;


public class InternalFrameFiniquito extends JInternalFrame {


	private static final long serialVersionUID = 1L;
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(InternalFrameFiniquito.class);
	InternalFrameEmpleado empleado = new InternalFrameEmpleado();
	JInternalFrame jFrameSlide = new JInternalFrame();
	JLabel lblFondo = new JLabel("");
	JTextField textFieldBuscarEmp;
	JTable tableFiniquito = new JTable();
	public JTable tableFiniquitoFalsa = new JTable();
	public String atributo = "";
	@SuppressWarnings("rawtypes")
	TableRowSorter rowSorter;
	int IdBusquedaEmple = 1;
	@SuppressWarnings("rawtypes")
	TableRowSorter rowSorterFalso;
	int IdBusquedaEmpleFalso = 1;
	JLabel label = new JLabel("Seleccione el Empleado(a):");
	JLabel lblIcon = new JLabel();
	JSeparator separator = new JSeparator();
	JButton btnGuardaFiniquito;
	JButton btnSeleccionar = new JButton("Seleccionar");
	JButton btnCalcularDatos = new JButton("Calcular Datos");
	JButton btnReCalcular = new JButton("Cálculo Manual");
	JDateChooser dateChooserFechaPago = new JDateChooser();
	JTextArea textAreaMotivos = new JTextArea(20,10);
	private XWPFDocument documento = new XWPFDocument();
	private XWPFDocument documentoReporte = new XWPFDocument();

	//panel liquidacion y sub
	private JPanel panelLiquidacionSlider = new JPanel();
	private JPanel panelPrimeroConceptosLiquidacionSlider = new JPanel();
	private JLabel lblNombreLiquidacion;
	private JLabel lblFechaIngresoLiquidacion;
	private JLabel lblFechaDeBajaLiquidacion;
	private JLabel lblSalarioDiarioLiquidacion;
	private JLabel lblDiasTrabajadosLiquidacion;
	private JLabel lblAntigüedadLiquidacion;
	private JLabel lblVacacionesGozadasLiquidacion;
	private JLabel lblPrimaVacacionalPagadaLiquidacion;
//	private JLabel lblClaveEmpleadoLiquidacion;
//	private JLabel lblTipoNominaLiquidacion;
//	private JLabel lblDepartamentoLiquidacion;

	private JPanel panelSegundoTextFieldLiquidacionSlider;
	private JTextField textFieldNombreLiquidacion;
	private JDateChooser dateChooserFechaIngresoLiquidacion = new JDateChooser();
	private JDateChooser dateChooserFechaBajaLiquidacion = new JDateChooser();
	public JTextField textFieldSDILiquidacion;
	private JTextField textFieldSDIAuxLiquidacion;
	private JTextField textFieldDiasTrabajadosLiquidacion;
	private JTextField textFieldDiasTrabajadoAuxLiquidacion;
	private JTextField textFieldAntiguedadLiquidacion;
	private JTextField textFieldVacGozadasLiquidacion;
	private JTextField textFieldVacaGozadasAuxLiquidacion;
	private JTextField textFieldPrimaVacPagadaLiquidacion;
	private JTextField textFieldPrimaVacaAuxLiquidacion;
//	private JTextField textFieldClaveEmpLiquidacion;
//	private JTextField textFieldDiasabledLiquidacion;
//	private JTextField textFieldTipoNominaLiquidacion;
//	private JTextField textFieldDepartamentoLiquidacion;

	private JPanel panelTerceroLiquidacionSlider;
	private JLabel lblConceptoLiquidacion;

	private JPanel panelCuartoProporcionDiasLiquidacionSlider;
	private JLabel lblProporcionDeDiasLiquidacion;
	private JLabel lblDeDasLiquidacion;

	private JPanel panelQuintoMontoLiquidacionSlider;
	private JLabel lblMontoLiquidacion;

	private JPanel panelSextoConceptosliquidacionSlider;
	private JLabel lblProporcionAguinaldoLiquidacion;
	private JLabel lblProporcinDeVacacionesLiquidacion;
	private JLabel lblPrimaVacacionalLiquidacion;
	private JLabel lblSalarioLiquidacion;
	private JLabel lblAyudaADespensaLiquidacion;
	private JLabel lblDescuentoDeIncapacidadLiquidacion;
	private JLabel lblPrimaDeAntigedadLiquidacion;
	private JLabel lblIndemnizacionLiquidacion;
	private JLabel lblSubtotalLiquidacion;
	private JLabel lblPrestamoLiquidacion;
	private JLabel lblIsrLiquidacion;

	private JPanel panelSeptimoTExtFieldLiquidacionSlider;
	private JTextField textFieldPropAguiLiquidacion;
	private JTextField textFieldPropVacaLiquidacion;
	private JTextField textFieldPrimaVacacionalLiquidacion;
	private JTextField textFieldSalarioLiquidacion;
	private JTextField textFieldAyudaADespensaLiquidacion;
	private JTextField textFieldDctoIncapLiquidacion;
	private JTextField textFieldPrimaAntigLiquidacion;
	private JTextField textFieldIndemnizacionLiquidacion;
	private JTextField textFieldsubtotalLiquidacion;
	private JTextField textFieldPrestamoLiquidacion;
	private JTextField textFieldIsrLiquidacion;
//	private JTextField textfieldVacioLiquidacion;


	private JPanel panelOctavoTextFieldMontoLiquidacionSlider;
	private JTextField textFieldPropAguiMontoLiquidacion;
	private JTextField textFieldPropVacMontoLiquidacion;
	private JTextField textFieldPrimVavMontoLiquidacion;
	private JTextField textFieldSalarioMontoLiquidacion;
	private JTextField textFieldAyuDesMontoLiquidacion;
	private JTextField textFieldDctoIncapMontoLiquidacion;
	private JTextField textFieldPrimaAntiMontoLiquidacion;
	private JTextField textFieldIndemizacionMontoLiquidacion;
	private JTextField textFieldSubTotalMontoLiquidacion;
	private JTextField textFieldPrestamoMontoLiquidacion;
	private JTextField textFieldISRMontoLiquidacion;
	private JTextField textFieldVacioMontoLiquidacion;


	//panel impuestos y sub
	private JPanel panelImpuestoSlider = new JPanel();
	private JLabel labelImpuesto = new JLabel("Impuesto");
	private JLabel lblEmpleadoImpuesto = new JLabel("EMPLEADO");

	private JPanel panelPrimeroImpuestosSlider = new JPanel();
	private JLabel lblAguinaldoImpuesto = new JLabel("Aguinaldo");
	private JLabel lblPrimaVacacionalImpuesto = new JLabel("Prima Vacacional");
	private JLabel lblVacacionesImpuesto = new JLabel("Vacaciones");
	private JLabel lblSalarioImpuesto = new JLabel("Salario");
	private JLabel lblPrimaDeAntigedadImpuesto = new JLabel("Prima de Antigüedad");
	private JLabel lblIndemnizacionImpuesto = new JLabel("Indemnización");

	private JPanel panelSegundoTitulosImpuestosSlider = new JPanel();
	private JLabel lblGravaImpuesto = new JLabel("GRAVA");
	private JLabel lblExcentoImpuesto = new JLabel("EXENTO");

	private JPanel panelTerceroTextFieldImpuestosSlider = new JPanel();
	private JTextField textFieldAguiGravaImpuesto;
	private JTextField textFieldAguiExentoImpuesto;
	private JTextField textFieldAguiAuxImpuesto;
	private JTextField textFieldPrimaVacGravaImpuesto;
	private JTextField textFieldPrimaVacacExentoImpuesto;
	private JTextField textFieldPrimaVacacAuxImpuesto;
	private JTextField textFieldVacacionesGravaImpuesto;
	private JTextField textFieldVacacionesExentoDisabledImpuesto;
	private JTextField textFieldVacacAuxImpuesto;
	private JTextField textFieldSalarioGravaImpuesto;
	private JTextField textFieldSalarioExentoDisabledImpuesto;
	private JTextField textFieldSalarioAuxImpuesto;
	private JTextField textFieldPrimaAntigGravaImpuesto;
	private JTextField textFieldPrimaAntigExentoDisabledImpuesto;
	private JTextField textFieldPrimaAntigAuxImpuesto;
	private JTextField textFieldIndemGravaImpuesto;
	private JTextField textFieldIndemExentoImpuesto;
	private JTextField textFieldIndemAuxImpuesto;
	private JTextField textFieldVacioGravaImpuesto;
	private JTextField textFieldVacioExentoImpuesto;
	private JTextField textFieldVacioAuxImpuesto;


	//panel calculo y subs
	private JPanel panelCalculoISRSlider= new JPanel();
	private JLabel lblEmpleadoCalculoISR;
	private JLabel lblCalculoIsr;

	private JPanel panelPrimeroConceptosCalculoISRSlider;
	private JLabel lblBaseGravableCalculoISR;
	private JLabel lblMenosLimiteInferiorCalculoISR;
	private JLabel lblTotalCalculoISR;
	private JLabel lblXlimiteinferiorCalculoISR;
	private JLabel lblSegundototalCalculoISR;
	private JLabel lblCuotaFijaCalculoISR;
	private JLabel lblTotalUltimoCalculoISR;
	private JLabel lblsubisioAlempleoCalculoISR;

	private JPanel panelSegundoTextFieldCalculoISRSlider;
	private JTextField textFieldBaseGravableCalculoISR;
	private JTextField textFieldBaseGravableAuxCalculoISR;
	private JTextField textFieldMenosLimiteInfGravableCalculoISR;
	private JTextField textFieldMenosLimiteInfGravableAuxCalculoISR;
	private JTextField textFieldTotalGravableCalculoISR;
	private JTextField textFieldTotalGravableAuxCalculoISR;
	private JTextField textFieldPorlimiteInferiorGravableCalculoISR;
	private JTextField textFieldPorLimiteInferiorGravableAuxCalculoISR;
	private JTextField textFieldSegundototalCalculoISR;
	private JTextField textFieldSegundoTotalAuxCalculoISR;
	private JTextField textFieldCuotaFijaGravableCalculoISR;
	private JTextField textFieldCoutaFijaGravableAuxCalculoISR;
	private JTextField textFieldUltimoTotalGravableCalculoISR;
	private JTextField textFieldUltimoTotalCFGravableAuxCalculoISR;
	private JTextField textFieldSubsidioGravableCalculoISR;
	private JTextField textFieldSubsidioGravableAuxCalculoISR;
	private JTextField textFieldVacioCalculoISR;
	private JTextField textFieldSegundoVacioCalculoISR;
	JLabel lblFechaPago = new JLabel("Fecha Pago");
	JButton btnArchivoFiniquito = new JButton("Crear Archivo Finiquito");
	JLabel lblTipoNominaOculta = new JLabel("New label");
	private final JButton btnVerEmpleados = new JButton("Ver Empleados");
	JLabel lblMoti = new JLabel("Motivos");


	public InternalFrameFiniquito() {
		//
		//		Toolkit tk = Toolkit.getDefaultToolkit();
		//		Dimension dim = tk.getScreenSize();
		//		int ancho = (int) dim.getWidth() - 850;
		//		int alto = (int) dim.getHeight() - 200;
		//		setBounds(FormularioPrincipal.desktopPane.getX(), FormularioPrincipal.desktopPane.getY(), ancho, alto);

		setToolTipText("Finiquito");
		setVisible(true);
		setTitle("Finiquito");
		getContentPane().setLayout(null);
		setBounds(0, 0, 920, 715);

		JPanel panelFiniquto = new JPanel();
		panelFiniquto.setBackground(SystemColor.controlHighlight);
		panelFiniquto.setBounds(0, 0, 904, 846);
		panelFiniquto.setLayout(null);
		getContentPane().add(panelFiniquto);

		btnSeleccionar.addActionListener(new ActionListener() {
			@SuppressWarnings("serial")
			public void actionPerformed(ActionEvent arg0) {

				jFrameSlide = new JInternalFrame(){
					{

						JOptionPane.showMessageDialog(null, "Antes de calcular, favor de llenar los datos marcados *");

						final JInternalPanelSlider<JInternalFrame> slider = new JInternalPanelSlider<JInternalFrame>(jFrameSlide);
						final JPanel jPanel = slider.getBasePanel();

						//panel calculo liquidacion
						panelLiquidacionSlider.setLayout(null);
//						panelLiquidacionSlider.setBounds(100, 680,  514, 775);
						panelLiquidacionSlider.setBackground(SystemColor.controlHighlight);
//						panelLiquidacionSlider.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(224, 69, 123)));
//						panelLiquidacionSlider.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));

						panelPrimeroConceptosLiquidacionSlider.setBorder(new LineBorder(new Color(0, 0, 0), 2));
						panelPrimeroConceptosLiquidacionSlider.setBounds(10, 6, 300, 250);
						panelLiquidacionSlider.add(panelPrimeroConceptosLiquidacionSlider);
						panelPrimeroConceptosLiquidacionSlider.setLayout(null);

						lblNombreLiquidacion = new JLabel("Nombre");
						lblNombreLiquidacion.setHorizontalAlignment(SwingConstants.RIGHT);
						lblNombreLiquidacion.setBounds(10, 16, 280, 14);
						panelPrimeroConceptosLiquidacionSlider.add(lblNombreLiquidacion);

						lblFechaIngresoLiquidacion = new JLabel("Fecha de Ingreso");
						lblFechaIngresoLiquidacion.setHorizontalAlignment(SwingConstants.RIGHT);
						lblFechaIngresoLiquidacion.setBounds(10, 44, 280, 14);
						panelPrimeroConceptosLiquidacionSlider.add(lblFechaIngresoLiquidacion);

						lblFechaDeBajaLiquidacion = new JLabel("Fecha de Baja *");
						lblFechaDeBajaLiquidacion.setHorizontalAlignment(SwingConstants.RIGHT);
						lblFechaDeBajaLiquidacion.setBounds(10, 76, 280, 14);
						panelPrimeroConceptosLiquidacionSlider.add(lblFechaDeBajaLiquidacion);

						lblSalarioDiarioLiquidacion = new JLabel("Salario Diario");
						lblSalarioDiarioLiquidacion.setHorizontalAlignment(SwingConstants.RIGHT);
						lblSalarioDiarioLiquidacion.setBounds(10, 107, 280, 14);
						panelPrimeroConceptosLiquidacionSlider.add(lblSalarioDiarioLiquidacion);

						lblDiasTrabajadosLiquidacion = new JLabel("Dias Trabajados");
						lblDiasTrabajadosLiquidacion.setHorizontalAlignment(SwingConstants.RIGHT);
						lblDiasTrabajadosLiquidacion.setBounds(10, 132, 280, 14);
						panelPrimeroConceptosLiquidacionSlider.add(lblDiasTrabajadosLiquidacion);

						lblAntigüedadLiquidacion = new JLabel("Antigüedad");
						lblAntigüedadLiquidacion.setHorizontalAlignment(SwingConstants.RIGHT);
						lblAntigüedadLiquidacion.setBounds(10, 162, 280, 14);
						panelPrimeroConceptosLiquidacionSlider.add(lblAntigüedadLiquidacion);

						lblVacacionesGozadasLiquidacion = new JLabel("Vacaciones Gozadas");
						lblVacacionesGozadasLiquidacion.setHorizontalAlignment(SwingConstants.RIGHT);
						lblVacacionesGozadasLiquidacion.setBounds(10, 191, 280, 14);
						panelPrimeroConceptosLiquidacionSlider.add(lblVacacionesGozadasLiquidacion);

						lblPrimaVacacionalPagadaLiquidacion = new JLabel("Prima Vacacional Pagada");
						lblPrimaVacacionalPagadaLiquidacion.setHorizontalAlignment(SwingConstants.RIGHT);
						lblPrimaVacacionalPagadaLiquidacion.setBounds(10, 220, 280, 14);
						panelPrimeroConceptosLiquidacionSlider.add(lblPrimaVacacionalPagadaLiquidacion);

//						lblClaveEmpleadoLiquidacion = new JLabel("Clave Empleado");
//						lblClaveEmpleadoLiquidacion.setHorizontalAlignment(SwingConstants.RIGHT);
//						lblClaveEmpleadoLiquidacion.setBounds(10, 247, 280, 14);
//						panelPrimeroConceptosLiquidacionSlider.add(lblClaveEmpleadoLiquidacion);

//						lblTipoNominaLiquidacion = new JLabel("Tipo Nomina");
//						lblTipoNominaLiquidacion.setHorizontalAlignment(SwingConstants.RIGHT);
//						lblTipoNominaLiquidacion.setBounds(10, 277, 280, 14);
//						panelPrimeroConceptosLiquidacionSlider.add(lblTipoNominaLiquidacion);
//
//						lblDepartamentoLiquidacion = new JLabel("Departamento");
//						lblDepartamentoLiquidacion.setHorizontalAlignment(SwingConstants.RIGHT);
//						lblDepartamentoLiquidacion.setBounds(10, 306, 280, 14);
//						panelPrimeroConceptosLiquidacionSlider.add(lblDepartamentoLiquidacion);


						panelSegundoTextFieldLiquidacionSlider = new JPanel();
						panelSegundoTextFieldLiquidacionSlider.setLayout(null);
						panelSegundoTextFieldLiquidacionSlider.setBorder(new LineBorder(new Color(0, 0, 0), 2));
						panelSegundoTextFieldLiquidacionSlider.setBounds(320, 6, 282, 250);
						panelLiquidacionSlider.add(panelSegundoTextFieldLiquidacionSlider);

						textFieldNombreLiquidacion = new JTextField();
						textFieldNombreLiquidacion.setBounds(10, 11, 262, 25);
						panelSegundoTextFieldLiquidacionSlider.add(textFieldNombreLiquidacion);
						textFieldNombreLiquidacion.setColumns(10);

						dateChooserFechaIngresoLiquidacion.setBounds(10, 40, 262, 25);
						panelSegundoTextFieldLiquidacionSlider.add(dateChooserFechaIngresoLiquidacion);

						dateChooserFechaBajaLiquidacion.setBounds(10, 69, 262, 25);
						panelSegundoTextFieldLiquidacionSlider.add(dateChooserFechaBajaLiquidacion);

						textFieldSDILiquidacion = new JTextField();
						textFieldSDILiquidacion.setBounds(10, 98, 118, 25);
						panelSegundoTextFieldLiquidacionSlider.add(textFieldSDILiquidacion);
						textFieldSDILiquidacion.setColumns(10);

						textFieldSDIAuxLiquidacion = new JTextField();
						textFieldSDIAuxLiquidacion.setToolTipText("sdi*30.4");
						textFieldSDIAuxLiquidacion.setForeground(new Color(255, 0, 0));
						textFieldSDIAuxLiquidacion.setColumns(10);
						textFieldSDIAuxLiquidacion.setBounds(154, 98, 118, 25);
						panelSegundoTextFieldLiquidacionSlider.add(textFieldSDIAuxLiquidacion);

						textFieldDiasTrabajadosLiquidacion = new JTextField();
						textFieldDiasTrabajadosLiquidacion.setForeground(Color.RED);
						textFieldDiasTrabajadosLiquidacion.setBounds(10, 127, 118, 25);
						panelSegundoTextFieldLiquidacionSlider.add(textFieldDiasTrabajadosLiquidacion);
						textFieldDiasTrabajadosLiquidacion.setColumns(10);

						textFieldDiasTrabajadoAuxLiquidacion = new JTextField();
						textFieldDiasTrabajadoAuxLiquidacion.setText("Días");
						textFieldDiasTrabajadoAuxLiquidacion.setColumns(10);
						textFieldDiasTrabajadoAuxLiquidacion.setBounds(154, 127, 118, 25);
						panelSegundoTextFieldLiquidacionSlider.add(textFieldDiasTrabajadoAuxLiquidacion);

						textFieldAntiguedadLiquidacion = new JTextField();
						textFieldAntiguedadLiquidacion.setForeground(Color.RED);
						textFieldAntiguedadLiquidacion.setBounds(10, 155, 262, 25);
						panelSegundoTextFieldLiquidacionSlider.add(textFieldAntiguedadLiquidacion);
						textFieldAntiguedadLiquidacion.setColumns(10);

						textFieldVacGozadasLiquidacion = new JTextField();
						textFieldVacGozadasLiquidacion.setText("0");
						textFieldVacGozadasLiquidacion.setBounds(10, 184, 118, 25);
						panelSegundoTextFieldLiquidacionSlider.add(textFieldVacGozadasLiquidacion);
						textFieldVacGozadasLiquidacion.setColumns(10);

						textFieldVacaGozadasAuxLiquidacion = new JTextField();
						textFieldVacaGozadasAuxLiquidacion.setText("Días");
						textFieldVacaGozadasAuxLiquidacion.setColumns(10);
						textFieldVacaGozadasAuxLiquidacion.setBounds(154, 184, 118, 25);
						panelSegundoTextFieldLiquidacionSlider.add(textFieldVacaGozadasAuxLiquidacion);

						textFieldPrimaVacPagadaLiquidacion = new JTextField();
						textFieldPrimaVacPagadaLiquidacion.setText("0");
						textFieldPrimaVacPagadaLiquidacion.setBounds(10, 213, 118, 25);
						panelSegundoTextFieldLiquidacionSlider.add(textFieldPrimaVacPagadaLiquidacion);
						textFieldPrimaVacPagadaLiquidacion.setColumns(10);

						textFieldPrimaVacaAuxLiquidacion = new JTextField();
						textFieldPrimaVacaAuxLiquidacion.setText("Días");
						textFieldPrimaVacaAuxLiquidacion.setColumns(10);
						textFieldPrimaVacaAuxLiquidacion.setBounds(154, 213, 118, 25);
						panelSegundoTextFieldLiquidacionSlider.add(textFieldPrimaVacaAuxLiquidacion);

//						textFieldClaveEmpLiquidacion = new JTextField();
//						textFieldClaveEmpLiquidacion.setBounds(10, 242, 118, 25);
//						panelSegundoTextFieldLiquidacionSlider.add(textFieldClaveEmpLiquidacion);
//						textFieldClaveEmpLiquidacion.setColumns(10);
//
//						textFieldDiasabledLiquidacion = new JTextField();
//						textFieldDiasabledLiquidacion.setEnabled(false);
//						textFieldDiasabledLiquidacion.setColumns(10);
//						textFieldDiasabledLiquidacion.setBounds(154, 242, 118, 25);
//						panelSegundoTextFieldLiquidacionSlider.add(textFieldDiasabledLiquidacion);

//						textFieldTipoNominaLiquidacion = new JTextField();
//						textFieldTipoNominaLiquidacion.setColumns(10);
//						textFieldTipoNominaLiquidacion.setBounds(10, 269, 262, 27);
//						panelSegundoTextFieldLiquidacionSlider.add(textFieldTipoNominaLiquidacion);
//
//						textFieldDepartamentoLiquidacion = new JTextField();
//						textFieldDepartamentoLiquidacion.setBounds(10, 300, 262, 25);
//						panelSegundoTextFieldLiquidacionSlider.add(textFieldDepartamentoLiquidacion);
//						textFieldDepartamentoLiquidacion.setColumns(10);

						panelTerceroLiquidacionSlider = new JPanel();
						panelTerceroLiquidacionSlider.setBorder(new LineBorder(new Color(0, 0, 0), 2));
						panelTerceroLiquidacionSlider.setBounds(10, 260, 300, 42);
						panelLiquidacionSlider.add(panelTerceroLiquidacionSlider);
						panelTerceroLiquidacionSlider.setLayout(null);

						lblConceptoLiquidacion = new JLabel("CONCEPTO");
						lblConceptoLiquidacion.setHorizontalAlignment(SwingConstants.CENTER);
						lblConceptoLiquidacion.setBounds(101, 11, 92, 23);
						panelTerceroLiquidacionSlider.add(lblConceptoLiquidacion);

						panelCuartoProporcionDiasLiquidacionSlider = new JPanel();
						panelCuartoProporcionDiasLiquidacionSlider.setBorder(new LineBorder(new Color(0, 0, 0), 2));
						panelCuartoProporcionDiasLiquidacionSlider.setBounds(320, 260, 136, 42);
						panelLiquidacionSlider.add(panelCuartoProporcionDiasLiquidacionSlider);
						panelCuartoProporcionDiasLiquidacionSlider.setLayout(null);

						lblProporcionDeDiasLiquidacion = new JLabel("PROPORCIÓN");
						lblProporcionDeDiasLiquidacion.setHorizontalAlignment(SwingConstants.CENTER);
						lblProporcionDeDiasLiquidacion.setBounds(10, 0, 121, 20);
						panelCuartoProporcionDiasLiquidacionSlider.add(lblProporcionDeDiasLiquidacion);

						lblDeDasLiquidacion = new JLabel("DE DÍAS");
						lblDeDasLiquidacion.setHorizontalAlignment(SwingConstants.CENTER);
						lblDeDasLiquidacion.setBounds(10, 20, 121, 16);
						panelCuartoProporcionDiasLiquidacionSlider.add(lblDeDasLiquidacion);

						panelQuintoMontoLiquidacionSlider = new JPanel();
						panelQuintoMontoLiquidacionSlider.setBorder(new LineBorder(new Color(0, 0, 0), 2));
						panelQuintoMontoLiquidacionSlider.setBounds(466, 260, 136, 42);
						panelLiquidacionSlider.add(panelQuintoMontoLiquidacionSlider);
						panelQuintoMontoLiquidacionSlider.setLayout(null);

						lblMontoLiquidacion = new JLabel("MONTO");
						lblMontoLiquidacion.setHorizontalAlignment(SwingConstants.CENTER);
						lblMontoLiquidacion.setBounds(23, 11, 92, 23);
						panelQuintoMontoLiquidacionSlider.add(lblMontoLiquidacion);

						panelSextoConceptosliquidacionSlider = new JPanel();
						panelSextoConceptosliquidacionSlider.setBorder(new LineBorder(new Color(0, 0, 0), 2));
						panelSextoConceptosliquidacionSlider.setBounds(10, 305, 300, 335);
						panelSextoConceptosliquidacionSlider.setLayout(null);
						panelLiquidacionSlider.add(panelSextoConceptosliquidacionSlider);

						lblProporcionAguinaldoLiquidacion = new JLabel("Proporción De Aguinaldo");
						lblProporcionAguinaldoLiquidacion.setBounds(0, 21, 280, 14);
						lblProporcionAguinaldoLiquidacion.setHorizontalAlignment(SwingConstants.RIGHT);
						panelSextoConceptosliquidacionSlider.add(lblProporcionAguinaldoLiquidacion);

						lblProporcinDeVacacionesLiquidacion = new JLabel("Proporción de Vacaciones");
						lblProporcinDeVacacionesLiquidacion.setBounds(0, 50, 280, 14);
						lblProporcinDeVacacionesLiquidacion.setHorizontalAlignment(SwingConstants.RIGHT);
						panelSextoConceptosliquidacionSlider.add(lblProporcinDeVacacionesLiquidacion);

						lblPrimaVacacionalLiquidacion = new JLabel("Prima Vacacional");
						lblPrimaVacacionalLiquidacion.setBounds(0, 80, 280, 14);
						lblPrimaVacacionalLiquidacion.setHorizontalAlignment(SwingConstants.RIGHT);
						panelSextoConceptosliquidacionSlider.add(lblPrimaVacacionalLiquidacion);

						lblSalarioLiquidacion = new JLabel("Salario *");
						lblSalarioLiquidacion.setBounds(0, 105, 280, 14);
						panelSextoConceptosliquidacionSlider.add(lblSalarioLiquidacion);
						lblSalarioLiquidacion.setHorizontalAlignment(SwingConstants.RIGHT);

						lblAyudaADespensaLiquidacion = new JLabel("Ayuda a Despensa *");
						lblAyudaADespensaLiquidacion.setBounds(0, 135, 280, 14);
						panelSextoConceptosliquidacionSlider.add(lblAyudaADespensaLiquidacion);
						lblAyudaADespensaLiquidacion.setHorizontalAlignment(SwingConstants.RIGHT);

						lblDescuentoDeIncapacidadLiquidacion = new JLabel("Descuento de Incapacidad");
						lblDescuentoDeIncapacidadLiquidacion.setBounds(0, 166, 280, 14);
						panelSextoConceptosliquidacionSlider.add(lblDescuentoDeIncapacidadLiquidacion);
						lblDescuentoDeIncapacidadLiquidacion.setHorizontalAlignment(SwingConstants.RIGHT);

						lblPrimaDeAntigedadLiquidacion = new JLabel("Prima de Antigüedad *");
						lblPrimaDeAntigedadLiquidacion.setBounds(0, 193, 280, 14);
						panelSextoConceptosliquidacionSlider.add(lblPrimaDeAntigedadLiquidacion);
						lblPrimaDeAntigedadLiquidacion.setHorizontalAlignment(SwingConstants.RIGHT);

						lblIndemnizacionLiquidacion = new JLabel("Indemnización");
						lblIndemnizacionLiquidacion.setBounds(0, 221, 280, 14);
						panelSextoConceptosliquidacionSlider.add(lblIndemnizacionLiquidacion);
						lblIndemnizacionLiquidacion.setHorizontalAlignment(SwingConstants.RIGHT);

						lblSubtotalLiquidacion = new JLabel("SubTotal");
						lblSubtotalLiquidacion.setBounds(0, 253, 280, 14);
						panelSextoConceptosliquidacionSlider.add(lblSubtotalLiquidacion);
						lblSubtotalLiquidacion.setHorizontalAlignment(SwingConstants.RIGHT);

						lblPrestamoLiquidacion = new JLabel("Préstamo");
						lblPrestamoLiquidacion.setBounds(0, 281, 280, 14);
						panelSextoConceptosliquidacionSlider.add(lblPrestamoLiquidacion);
						lblPrestamoLiquidacion.setHorizontalAlignment(SwingConstants.RIGHT);

						lblIsrLiquidacion = new JLabel("ISR");
						lblIsrLiquidacion.setBounds(0, 312, 280, 14);
						panelSextoConceptosliquidacionSlider.add(lblIsrLiquidacion);
						lblIsrLiquidacion.setHorizontalAlignment(SwingConstants.RIGHT);

						panelSeptimoTExtFieldLiquidacionSlider = new JPanel();
						panelSeptimoTExtFieldLiquidacionSlider.setBorder(new LineBorder(new Color(0, 0, 0), 2));
						panelSeptimoTExtFieldLiquidacionSlider.setBounds(320, 305, 136, 335);
						panelLiquidacionSlider.add(panelSeptimoTExtFieldLiquidacionSlider);
						panelSeptimoTExtFieldLiquidacionSlider.setLayout(null);

						textFieldPropAguiLiquidacion = new JTextField();
						textFieldPropAguiLiquidacion.setForeground(Color.RED);
						textFieldPropAguiLiquidacion.setToolTipText("(Dias trabajados)*40/365");
						textFieldPropAguiLiquidacion.setBounds(10, 11, 118, 25);
						textFieldPropAguiLiquidacion.setColumns(10);
						panelSeptimoTExtFieldLiquidacionSlider.add(textFieldPropAguiLiquidacion);

						textFieldPropVacaLiquidacion = new JTextField();
						textFieldPropVacaLiquidacion.setToolTipText("=(dias trabajados)*20/365-(vacaciones gozadas)");
						textFieldPropVacaLiquidacion.setForeground(Color.RED);
						textFieldPropVacaLiquidacion.setBounds(10, 40, 118, 25);
						panelSeptimoTExtFieldLiquidacionSlider.add(textFieldPropVacaLiquidacion);
						textFieldPropVacaLiquidacion.setColumns(10);

						textFieldPrimaVacacionalLiquidacion = new JTextField();
						textFieldPrimaVacacionalLiquidacion.setToolTipText("=(dias trabajados)*20/365-(Prima vacacional pagada)");
						textFieldPrimaVacacionalLiquidacion.setForeground(Color.RED);
						textFieldPrimaVacacionalLiquidacion.setBounds(10, 69, 118, 25);
						panelSeptimoTExtFieldLiquidacionSlider.add(textFieldPrimaVacacionalLiquidacion);
						textFieldPrimaVacacionalLiquidacion.setColumns(10);

						textFieldSalarioLiquidacion = new JTextField();
						textFieldSalarioLiquidacion.setBounds(10, 98, 118, 25);
						panelSeptimoTExtFieldLiquidacionSlider.add(textFieldSalarioLiquidacion);
						textFieldSalarioLiquidacion.setColumns(10);

						textFieldAyudaADespensaLiquidacion = new JTextField();
						textFieldAyudaADespensaLiquidacion.setBounds(10, 127, 118, 25);
						panelSeptimoTExtFieldLiquidacionSlider.add(textFieldAyudaADespensaLiquidacion);
						textFieldAyudaADespensaLiquidacion.setColumns(10);

						textFieldDctoIncapLiquidacion = new JTextField();
						textFieldDctoIncapLiquidacion.setBounds(10, 156, 118, 25);
						panelSeptimoTExtFieldLiquidacionSlider.add(textFieldDctoIncapLiquidacion);
						textFieldDctoIncapLiquidacion.setColumns(10);

						textFieldPrimaAntigLiquidacion = new JTextField();
						textFieldPrimaAntigLiquidacion.setBounds(10, 185, 118, 25);
						panelSeptimoTExtFieldLiquidacionSlider.add(textFieldPrimaAntigLiquidacion);
						textFieldPrimaAntigLiquidacion.setColumns(10);

						textFieldIndemnizacionLiquidacion = new JTextField();
						textFieldIndemnizacionLiquidacion.setBounds(10, 214, 118, 25);
						panelSeptimoTExtFieldLiquidacionSlider.add(textFieldIndemnizacionLiquidacion);
						textFieldIndemnizacionLiquidacion.setColumns(10);

						textFieldsubtotalLiquidacion = new JTextField();
						textFieldsubtotalLiquidacion.setBounds(10, 243, 118, 25);
						panelSeptimoTExtFieldLiquidacionSlider.add(textFieldsubtotalLiquidacion);
						textFieldsubtotalLiquidacion.setColumns(10);

						textFieldPrestamoLiquidacion = new JTextField();
						textFieldPrestamoLiquidacion.setText("( - )");
						textFieldPrestamoLiquidacion.setBounds(10, 272, 118, 25);
						panelSeptimoTExtFieldLiquidacionSlider.add(textFieldPrestamoLiquidacion);
						textFieldPrestamoLiquidacion.setColumns(10);

						textFieldIsrLiquidacion = new JTextField();
						textFieldIsrLiquidacion.setText("( - )");
						textFieldIsrLiquidacion.setBounds(10, 301, 118, 25);
						panelSeptimoTExtFieldLiquidacionSlider.add(textFieldIsrLiquidacion);
						textFieldIsrLiquidacion.setColumns(10);

//						textfieldVacioLiquidacion = new JTextField();
//						textfieldVacioLiquidacion.setBounds(10, 330, 118, 25);
//						panelSeptimoTExtFieldLiquidacionSlider.add(textfieldVacioLiquidacion);
//						textfieldVacioLiquidacion.setColumns(10);

						panelOctavoTextFieldMontoLiquidacionSlider = new JPanel();
						panelOctavoTextFieldMontoLiquidacionSlider.setBorder(new LineBorder(new Color(0, 0, 0), 2));
						panelOctavoTextFieldMontoLiquidacionSlider.setBounds(466, 305, 136, 358);
						panelLiquidacionSlider.add(panelOctavoTextFieldMontoLiquidacionSlider);
						panelOctavoTextFieldMontoLiquidacionSlider.setLayout(null);

						textFieldPropAguiMontoLiquidacion = new JTextField();
						textFieldPropAguiMontoLiquidacion.setForeground(Color.RED);
						textFieldPropAguiMontoLiquidacion.setColumns(10);
						textFieldPropAguiMontoLiquidacion.setBounds(10, 11, 118, 25);
						panelOctavoTextFieldMontoLiquidacionSlider.add(textFieldPropAguiMontoLiquidacion);

						textFieldPropVacMontoLiquidacion = new JTextField();
						textFieldPropVacMontoLiquidacion.setForeground(Color.RED);
						textFieldPropVacMontoLiquidacion.setColumns(10);
						textFieldPropVacMontoLiquidacion.setBounds(10, 40, 118, 25);
						panelOctavoTextFieldMontoLiquidacionSlider.add(textFieldPropVacMontoLiquidacion);

						textFieldPrimVavMontoLiquidacion = new JTextField();
						textFieldPrimVavMontoLiquidacion.setForeground(Color.RED);
						textFieldPrimVavMontoLiquidacion.setColumns(10);
						textFieldPrimVavMontoLiquidacion.setBounds(10, 69, 118, 25);
						panelOctavoTextFieldMontoLiquidacionSlider.add(textFieldPrimVavMontoLiquidacion);

						textFieldSalarioMontoLiquidacion = new JTextField();
						textFieldSalarioMontoLiquidacion.setForeground(Color.RED);
						textFieldSalarioMontoLiquidacion.setColumns(10);
						textFieldSalarioMontoLiquidacion.setBounds(10, 98, 118, 25);
						panelOctavoTextFieldMontoLiquidacionSlider.add(textFieldSalarioMontoLiquidacion);

						textFieldAyuDesMontoLiquidacion = new JTextField();
						textFieldAyuDesMontoLiquidacion.setForeground(Color.RED);
						textFieldAyuDesMontoLiquidacion.setColumns(10);
						textFieldAyuDesMontoLiquidacion.setBounds(10, 127, 118, 25);
						panelOctavoTextFieldMontoLiquidacionSlider.add(textFieldAyuDesMontoLiquidacion);

						textFieldDctoIncapMontoLiquidacion = new JTextField();
						textFieldDctoIncapMontoLiquidacion.setColumns(10);
						textFieldDctoIncapMontoLiquidacion.setBounds(10, 156, 118, 25);
						panelOctavoTextFieldMontoLiquidacionSlider.add(textFieldDctoIncapMontoLiquidacion);

						textFieldPrimaAntiMontoLiquidacion = new JTextField();
						textFieldPrimaAntiMontoLiquidacion.setForeground(Color.RED);
						textFieldPrimaAntiMontoLiquidacion.setColumns(10);
						textFieldPrimaAntiMontoLiquidacion.setBounds(10, 185, 118, 25);
						panelOctavoTextFieldMontoLiquidacionSlider.add(textFieldPrimaAntiMontoLiquidacion);

						textFieldIndemizacionMontoLiquidacion = new JTextField();
						textFieldIndemizacionMontoLiquidacion.setColumns(10);
						textFieldIndemizacionMontoLiquidacion.setBounds(10, 214, 118, 25);
						panelOctavoTextFieldMontoLiquidacionSlider.add(textFieldIndemizacionMontoLiquidacion);

						textFieldSubTotalMontoLiquidacion = new JTextField();
						textFieldSubTotalMontoLiquidacion.setForeground(Color.RED);
						textFieldSubTotalMontoLiquidacion.setColumns(10);
						textFieldSubTotalMontoLiquidacion.setBounds(10, 243, 118, 25);
						panelOctavoTextFieldMontoLiquidacionSlider.add(textFieldSubTotalMontoLiquidacion);

						textFieldPrestamoMontoLiquidacion = new JTextField();
						textFieldPrestamoMontoLiquidacion.setColumns(10);
						textFieldPrestamoMontoLiquidacion.setBounds(10, 272, 118, 25);
						panelOctavoTextFieldMontoLiquidacionSlider.add(textFieldPrestamoMontoLiquidacion);

						textFieldISRMontoLiquidacion = new JTextField();
						textFieldISRMontoLiquidacion.setForeground(Color.RED);
						textFieldISRMontoLiquidacion.setColumns(10);
						textFieldISRMontoLiquidacion.setBounds(10, 301, 118, 25);
						panelOctavoTextFieldMontoLiquidacionSlider.add(textFieldISRMontoLiquidacion);

						textFieldVacioMontoLiquidacion = new JTextField();
						textFieldVacioMontoLiquidacion.setForeground(new Color(32, 151, 95));
						textFieldVacioMontoLiquidacion.setColumns(10);
						textFieldVacioMontoLiquidacion.setBounds(10, 330, 118, 25);
						panelOctavoTextFieldMontoLiquidacionSlider.add(textFieldVacioMontoLiquidacion);

						btnCalcularDatos.setBounds(10, 642, 140, 20);
//						btnCalcularDatos.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(224,69,123)));
						panelLiquidacionSlider.add(btnCalcularDatos);
						btnCalcularDatos.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								//btnGuardaFiniquito.setEnabled(true);
								DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
								simbolos.setDecimalSeparator('.');
								DecimalFormat df = new DecimalFormat("####.##",simbolos);
								int fila = tableFiniquito.getSelectedRow();
								if(fila>=0) {
									panelLiquidacionSlider.setVisible(true);
									String id = tableFiniquito.getValueAt(fila, 0).toString();
									String empleado = tableFiniquito.getValueAt(fila, 1).toString();
									String ApPat = tableFiniquito.getValueAt(fila, 2).toString();
									String ApMat = tableFiniquito.getValueAt(fila, 3).toString();
									String fechaIngreso = tableFiniquitoFalsa.getValueAt(fila, 10).toString();
									//String salario = tableFiniquito.getValueAt(fila, 5).toString();
									//									lblEmpleadoIsr.setText(empleado + " " + ApPat + " " + ApMat);

									if(dateChooserFechaBajaLiquidacion.getDate()==null || textFieldSalarioLiquidacion.getText().equals("") || textFieldAyudaADespensaLiquidacion.getText().equals("")) {
										JOptionPane.showMessageDialog(null, "Introducir valores en los campos señalados");
										dateChooserFechaBajaLiquidacion.setBackground(new Color(236, 112, 99 ));
										textFieldSalarioLiquidacion.setBackground(new Color(236, 112, 99));
										textFieldAyudaADespensaLiquidacion.setBackground(new Color(236, 112, 99));
										textFieldPrimaAntigLiquidacion.setBackground(new Color(236, 112, 99));

									}else {
										dateChooserFechaBajaLiquidacion.setBackground(null);
										textFieldSalarioLiquidacion.setBackground(null);
										textFieldAyudaADespensaLiquidacion.setBackground(null);
										textFieldPrimaAntigLiquidacion.setBackground(null);
										//						double diasTrabaj = Double.parseDouble(textFieldDiasTrabajados.getText());
										//						double salarioMonto = diasTrabaj*Double.parseDouble(textFieldSalario.getText());
										//						textFieldSalarioMonto.setText(String.valueOf(df.format(salarioMonto)));
										int firstNumberDAY = 1;							
										//System.out.println("fecha seleccionada numberDAY: "+ dateChooserFechaIngreso.getDate());
										int numberDAYDown = dateChooserFechaBajaLiquidacion.getCalendar().get(Calendar.DAY_OF_YEAR);
										//System.out.println("fecha seleccionada numberDAYDown: "+ dateChooserFechaBaja.getDate());
										//System.out.println("-------------");
										//System.out.println("Es el dia " +firstNumberDAY+ " del año.");
										//System.out.println("Es el dia " +numberDAYDown+ " del año.");
										//System.out.println("-------------"); 
										int diasTrabajados = numberDAYDown-firstNumberDAY; 
										//System.out.println(numberDAYDown-numberDAY);
										textFieldDiasTrabajadosLiquidacion.setText(String.valueOf(diasTrabajados));

										//						int FechaAntigDia = dateChooserFechaIngreso.getCalendar().get(Calendar.DAY_OF_YEAR);
										//						int FechaAntigMes = dateChooserFechaIngreso.getCalendar().get(Calendar.MONTH)+1;
										//						int FechaAntigAño = dateChooserFechaIngreso.getCalendar().get(Calendar.YEAR);
										//						System.out.println("El dia en que entro es el numero: "+ FechaAntigDia);
										//						System.out.println("El mes en que entro es el numero: "+ FechaAntigMes);
										//						System.out.println("El año en que entro es el numero: "+ FechaAntigAño);
										//						int FechaHoy = dateChooserFechaBaja.getCalendar().get(Calendar.DAY_OF_YEAR);
										//						System.out.println("hoy es el dia numero: "+ FechaHoy);

										Date fecha = dateChooserFechaIngresoLiquidacion.getDate();
										//						System.out.println("fecha sin format: "+fecha);
										SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
										//System.out.println("fecha de ingreso: "+formatoDeFecha.format(fecha));

										DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
										LocalDate fechaNac = LocalDate.parse(formatoDeFecha.format(fecha), fmt);
										LocalDate ahora = LocalDate.now();

										Period periodo = Period.between(fechaNac, ahora);
										//System.out.printf("Tu antiguedad es: %s años, %s meses y %s días\r\n",
										//                    periodo.getYears(), periodo.getMonths(), periodo.getDays());
										int años = periodo.getYears();
										int meses = periodo.getMonths();
										int dias = periodo.getDays();

										//						System.out.println("periodo en años: "+años);
										//						System.out.println("periodo en meses: "+meses);
										//						System.out.println("periodo en dias: "+dias);
										textFieldAntiguedadLiquidacion.setText(String.valueOf(años) + " años, " + String.valueOf(meses) + " meses y " + String.valueOf(dias) + " dias.");

										double diasTrabaj = Double.parseDouble(textFieldDiasTrabajadosLiquidacion.getText());
										//						System.out.println("diasTrabaj: "+ diasTrabaj);

										double proporcionAguinaldo = diasTrabaj*40/365;
										textFieldPropAguiLiquidacion.setText(String.valueOf(df.format(proporcionAguinaldo)));
										//						System.out.println("La proporcion del aguinaldo en dias es: "+df.format(dt));

										double vacGozadas = Double.parseDouble(textFieldVacGozadasLiquidacion.getText());
										double proporcionVacaciones = diasTrabaj*20/365-vacGozadas;
										textFieldPropVacaLiquidacion.setText(String.valueOf(df.format(proporcionVacaciones)));
										//						System.out.println("La proporcion de las vacaciones en dias es: "+propVac);

										double primaVacPag = Double.parseDouble(textFieldPrimaVacPagadaLiquidacion.getText());
										double primaVacPagResultado = diasTrabaj*20/365-primaVacPag;
										textFieldPrimaVacacionalLiquidacion.setText(String.valueOf(df.format(primaVacPagResultado)));
										//						System.out.println("La proporcion del prima vacacional en dias es: "+primaVacPagResultado);

										double sdi = Double.parseDouble(textFieldSDILiquidacion.getText());

										double montoAguinaldo = sdi*proporcionAguinaldo;
										textFieldPropAguiMontoLiquidacion.setText(String.valueOf(df.format(montoAguinaldo)));
										System.out.println("Aguinaldo Monto: " + montoAguinaldo);

										double montoVacaciones = sdi*proporcionVacaciones;
										textFieldPropVacMontoLiquidacion.setText(String.valueOf(df.format(montoVacaciones)));
										System.out.println("Vacaciones Monto: " + montoVacaciones);

										double primaVacioneeMonto = sdi*primaVacPagResultado*0.3;
										textFieldPrimVavMontoLiquidacion.setText(String.valueOf(df.format(primaVacioneeMonto)));
										System.out.println("Prima Vacacional Monto: " + primaVacioneeMonto);

										double salarioProp = Double.parseDouble(textFieldSalarioLiquidacion.getText());
										double salarioMontoLiquidacion = sdi*salarioProp;
										textFieldSalarioMontoLiquidacion.setText(String.valueOf(df.format(salarioMontoLiquidacion)));
										System.out.println("Salario Diario Monto: " + salarioMontoLiquidacion);

										double ayudaProp = Double.parseDouble(textFieldAyudaADespensaLiquidacion.getText());
										double ayudaMontoLiquidadacion = 498.82/14*ayudaProp;
										textFieldAyuDesMontoLiquidacion.setText(String.valueOf(df.format(ayudaMontoLiquidadacion)));
										System.out.println("Ayuda Despensa Monto: " + ayudaMontoLiquidadacion);

										double primaAntiguedad = Double.parseDouble(textFieldPrimaAntigLiquidacion.getText());
										double primaAntiguedadAux = sdi*primaAntiguedad;
										textFieldPrimaAntiMontoLiquidacion.setText(String.valueOf(df.format(primaAntiguedadAux)));
										System.out.println("Prima Antiguedad Monto: " + primaAntiguedadAux);


										if(textFieldDctoIncapMontoLiquidacion.getText().isEmpty()) {
											textFieldDctoIncapMontoLiquidacion.setText("0");
										}
										double incapacidad = Double.parseDouble(textFieldDctoIncapMontoLiquidacion.getText());


										if(textFieldIndemizacionMontoLiquidacion.getText().isEmpty()) {
											textFieldIndemizacionMontoLiquidacion.setText("0");
										}
										double indemnizacion = Double.parseDouble(textFieldIndemizacionMontoLiquidacion.getText());


										double subTotal = montoAguinaldo+montoVacaciones+primaVacioneeMonto+salarioMontoLiquidacion+ayudaMontoLiquidadacion-incapacidad+primaAntiguedadAux+indemnizacion;
										textFieldSubTotalMontoLiquidacion.setText(String.valueOf(df.format(subTotal)));
										System.out.println("subtotalSET: "+montoAguinaldo+" + "+montoVacaciones+" + "+primaVacioneeMonto+" + "+salarioMontoLiquidacion+" + "+ayudaMontoLiquidadacion+" - "+incapacidad+"  + "+primaAntiguedadAux+ " + " +indemnizacion);
										System.out.println("subtotal: "+ subTotal);

										//double prestamoMonto = Double.parseDouble(textFieldPrestamoMontoLiquidacion.getText());

										//calculo panelImpuesto

										textFieldAguiGravaImpuesto.setText(String.valueOf(df.format(montoAguinaldo)));
										textFieldPrimaVacGravaImpuesto.setText(String.valueOf(df.format(primaVacioneeMonto)));
										textFieldVacacionesGravaImpuesto.setText(String.valueOf(df.format(montoVacaciones)));
										textFieldPrimaAntigGravaImpuesto.setText(String.valueOf(df.format(primaAntiguedadAux)));
										//textFieldIndemGravaImpuesto.setText("$ -);

										double primaAntiGravada = Double.parseDouble(textFieldPrimaAntigGravaImpuesto.getText());
										if(textFieldIndemGravaImpuesto.getText().isEmpty()){
											textFieldIndemGravaImpuesto.setText("0");
										}else {
											textFieldIndemGravaImpuesto.getText();
										}
										double primaIndemGravada = Double.parseDouble(textFieldIndemGravaImpuesto.getText());

										textFieldVacioGravaImpuesto.setText(String.valueOf(df.format(primaAntiGravada+primaIndemGravada)));

										textFieldAguiExentoImpuesto.setText(String.valueOf(df.format(88.36*30)));
										textFieldPrimaVacacExentoImpuesto.setText(String.valueOf(df.format((88.36*15)/2)));
										textFieldIndemExentoImpuesto.setText(String.valueOf(df.format((88.36*90)*12)));

										double VacAux = Double.parseDouble(textFieldVacacionesGravaImpuesto.getText());
										textFieldVacacAuxImpuesto.setText(String.valueOf(df.format(VacAux)));

										double aguiAux=0;
										double primaVacAux =0;
										double vacacionesAux=0;
										double salarioAux=0;
										double primaAntigAux=0;
										double indemAux =0;
										if(textFieldAguiAuxImpuesto.getText().isEmpty()){
											aguiAux = Double.parseDouble("0");
										}else {
											aguiAux = Double.parseDouble(textFieldAguiAuxImpuesto.getText());
										}

										if(textFieldPrimaVacacAuxImpuesto.getText().isEmpty()){
											primaVacAux = Double.parseDouble("0");
										}else {
											primaVacAux = Double.parseDouble(textFieldPrimaVacacAuxImpuesto.getText());
										}

										if(textFieldVacacAuxImpuesto.getText().isEmpty()){
											vacacionesAux = Double.parseDouble("0");
										}else {
											vacacionesAux = Double.parseDouble(textFieldVacacAuxImpuesto.getText());
										}

										if(textFieldSalarioAuxImpuesto.getText().isEmpty()){
											salarioAux = Double.parseDouble("0");
										}else {
											salarioAux = Double.parseDouble(textFieldSalarioAuxImpuesto.getText());
										}

										if(textFieldPrimaAntigAuxImpuesto.getText().isEmpty()){
											primaAntigAux = Double.parseDouble("0");
										}else {
											primaAntigAux = Double.parseDouble(textFieldPrimaAntigAuxImpuesto.getText());
										}

										if(textFieldIndemAuxImpuesto.getText().isEmpty()){
											indemAux = Double.parseDouble("0");
										}else {
											indemAux = Double.parseDouble(textFieldIndemAuxImpuesto.getText());
										}

										double totalImpuesto = aguiAux+primaVacAux+vacacionesAux+salarioAux+primaAntigAux+indemAux;
										System.out.println("Total impuesto: "+ totalImpuesto);
										textFieldVacioAuxImpuesto.setText(String.valueOf(df.format(totalImpuesto)));


										//calculo isr
										textFieldBaseGravableCalculoISR.setText(String.valueOf(df.format(totalImpuesto)));

										calcularISR();

										btnReCalcular.setVisible(true);


									}

								}

								lblFechaPago.setVisible(true);
								dateChooserFechaPago.setVisible(true);
								btnGuardaFiniquito.setVisible(true);
								textAreaMotivos.setVisible(true);
								lblMoti.setVisible(true);
							}
						});
						
						btnReCalcular.setBounds(327, 642, 120, 20);
						btnReCalcular.setBackground(new Color(224,69,123));
//						btnReCalcular.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(224,69,123)));
						panelLiquidacionSlider.add(btnReCalcular);
						btnReCalcular.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								System.out.println("Calculo Manual");
								Border border = BorderFactory.createLineBorder(Color.BLUE, 1);
								textFieldDiasTrabajadosLiquidacion.setBorder(border);
								textFieldSDILiquidacion.setBorder(border);
								textFieldAyudaADespensaLiquidacion.setBorder(border);
								textFieldPrimaAntigLiquidacion.setBorder(border);
								textFieldPrestamoMontoLiquidacion.setBorder(border);
								textFieldDctoIncapMontoLiquidacion.setBorder(border);
								textFieldIndemizacionMontoLiquidacion.setBorder(border);
								textFieldPropVacaLiquidacion.setBorder(border);


								textFieldSalarioLiquidacion.setEnabled(false);

								setJTexFieldChangedDiasTrabajados(textFieldDiasTrabajadosLiquidacion);
								setJTexFieldChangedSDI(textFieldSDILiquidacion);
								setJTexFieldChangedAD(textFieldAyudaADespensaLiquidacion);
								setJTexFieldChangedPrimaAntig(textFieldPrimaAntigLiquidacion);
								setJTexFieldChangedPrestamo(textFieldPrestamoMontoLiquidacion);
								setJTexFieldChangedIncapacidad(textFieldDctoIncapMontoLiquidacion);
								setJTexFieldChangedIndemnizacion(textFieldIndemizacionMontoLiquidacion);
								setJTexFieldChangedPropVacaciones(textFieldPropVacaLiquidacion);


							}
						});


						//panel calculo impuesto
						panelImpuestoSlider.setLayout(null);
						panelImpuestoSlider.setBounds(100, 1000,  614, 795);
						panelImpuestoSlider.setBackground(SystemColor.controlHighlight);
						panelImpuestoSlider.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));

						labelImpuesto.setBounds(10, 11, 228, 21);
						panelImpuestoSlider.add(labelImpuesto);

						lblEmpleadoImpuesto.setBounds(141, 11, 400, 22);
						panelImpuestoSlider.add(lblEmpleadoImpuesto);

						panelPrimeroImpuestosSlider.setBorder(new LineBorder(new Color(0, 0, 0), 2));
						panelPrimeroImpuestosSlider.setBounds(10, 37, 130, 331);
						panelImpuestoSlider.add(panelPrimeroImpuestosSlider);
						panelPrimeroImpuestosSlider.setLayout(null);

						lblAguinaldoImpuesto.setHorizontalAlignment(SwingConstants.RIGHT);
						lblAguinaldoImpuesto.setBounds(0, 53, 120, 14);
						panelPrimeroImpuestosSlider.add(lblAguinaldoImpuesto);

						lblPrimaVacacionalImpuesto.setHorizontalAlignment(SwingConstants.RIGHT);
						lblPrimaVacacionalImpuesto.setBounds(0, 93, 120, 14);
						panelPrimeroImpuestosSlider.add(lblPrimaVacacionalImpuesto);

						lblVacacionesImpuesto.setHorizontalAlignment(SwingConstants.RIGHT);
						lblVacacionesImpuesto.setBounds(0, 137, 120, 14);
						panelPrimeroImpuestosSlider.add(lblVacacionesImpuesto);

						lblSalarioImpuesto.setHorizontalAlignment(SwingConstants.RIGHT);
						lblSalarioImpuesto.setBounds(0, 176, 120, 14);
						panelPrimeroImpuestosSlider.add(lblSalarioImpuesto);

						lblPrimaDeAntigedadImpuesto.setHorizontalAlignment(SwingConstants.RIGHT);
						lblPrimaDeAntigedadImpuesto.setBounds(0, 215, 120, 14);
						panelPrimeroImpuestosSlider.add(lblPrimaDeAntigedadImpuesto);

						lblIndemnizacionImpuesto.setHorizontalAlignment(SwingConstants.RIGHT);
						lblIndemnizacionImpuesto.setBounds(0, 259, 120, 14);
						panelPrimeroImpuestosSlider.add(lblIndemnizacionImpuesto);

						panelSegundoTitulosImpuestosSlider.setBorder(new LineBorder(new Color(0, 0, 0), 2));
						panelSegundoTitulosImpuestosSlider.setBounds(141, 37, 400, 33);
						panelImpuestoSlider.add(panelSegundoTitulosImpuestosSlider);
						panelSegundoTitulosImpuestosSlider.setLayout(null);

						lblGravaImpuesto.setBounds(41, 11, 46, 14);
						panelSegundoTitulosImpuestosSlider.add(lblGravaImpuesto);

						lblExcentoImpuesto.setBounds(168, 11, 71, 14);
						panelSegundoTitulosImpuestosSlider.add(lblExcentoImpuesto);

						panelTerceroTextFieldImpuestosSlider.setBorder(new LineBorder(new Color(0, 0, 0), 2));
						panelTerceroTextFieldImpuestosSlider.setBounds(141, 71, 400, 297);
						panelImpuestoSlider.add(panelTerceroTextFieldImpuestosSlider);
						panelTerceroTextFieldImpuestosSlider.setLayout(null);

						textFieldAguiGravaImpuesto = new JTextField();
						textFieldAguiGravaImpuesto.setBounds(10, 11, 118, 30);
						panelTerceroTextFieldImpuestosSlider.add(textFieldAguiGravaImpuesto);
						textFieldAguiGravaImpuesto.setColumns(10);

						textFieldAguiExentoImpuesto = new JTextField();
						textFieldAguiExentoImpuesto.setColumns(10);
						textFieldAguiExentoImpuesto.setBounds(138, 11, 118, 30);
						panelTerceroTextFieldImpuestosSlider.add(textFieldAguiExentoImpuesto);

						textFieldAguiAuxImpuesto = new JTextField();
						textFieldAguiAuxImpuesto.setColumns(10);
						textFieldAguiAuxImpuesto.setBounds(266, 11, 118, 30);
						panelTerceroTextFieldImpuestosSlider.add(textFieldAguiAuxImpuesto);

						textFieldPrimaVacGravaImpuesto = new JTextField();
						textFieldPrimaVacGravaImpuesto.setColumns(10);
						textFieldPrimaVacGravaImpuesto.setBounds(10, 52, 118, 30);
						panelTerceroTextFieldImpuestosSlider.add(textFieldPrimaVacGravaImpuesto);

						textFieldPrimaVacacExentoImpuesto = new JTextField();
						textFieldPrimaVacacExentoImpuesto.setColumns(10);
						textFieldPrimaVacacExentoImpuesto.setBounds(138, 52, 118, 30);
						panelTerceroTextFieldImpuestosSlider.add(textFieldPrimaVacacExentoImpuesto);

						textFieldPrimaVacacAuxImpuesto = new JTextField();
						textFieldPrimaVacacAuxImpuesto.setColumns(10);
						textFieldPrimaVacacAuxImpuesto.setBounds(266, 52, 118, 30);
						panelTerceroTextFieldImpuestosSlider.add(textFieldPrimaVacacAuxImpuesto);

						textFieldVacacionesGravaImpuesto = new JTextField();
						textFieldVacacionesGravaImpuesto.setColumns(10);
						textFieldVacacionesGravaImpuesto.setBounds(10, 93, 118, 30);
						panelTerceroTextFieldImpuestosSlider.add(textFieldVacacionesGravaImpuesto);

						textFieldVacacionesExentoDisabledImpuesto = new JTextField();
						textFieldVacacionesExentoDisabledImpuesto.setEnabled(false);
						textFieldVacacionesExentoDisabledImpuesto.setColumns(10);
						textFieldVacacionesExentoDisabledImpuesto.setBounds(138, 93, 118, 30);
						panelTerceroTextFieldImpuestosSlider.add(textFieldVacacionesExentoDisabledImpuesto);

						textFieldVacacAuxImpuesto = new JTextField();
						textFieldVacacAuxImpuesto.setColumns(10);
						textFieldVacacAuxImpuesto.setBounds(266, 93, 118, 30);
						panelTerceroTextFieldImpuestosSlider.add(textFieldVacacAuxImpuesto);

						textFieldSalarioGravaImpuesto = new JTextField();
						textFieldSalarioGravaImpuesto.setColumns(10);
						textFieldSalarioGravaImpuesto.setBounds(10, 134, 118, 30);
						panelTerceroTextFieldImpuestosSlider.add(textFieldSalarioGravaImpuesto);

						textFieldSalarioExentoDisabledImpuesto = new JTextField();
						textFieldSalarioExentoDisabledImpuesto.setEnabled(false);
						textFieldSalarioExentoDisabledImpuesto.setColumns(10);
						textFieldSalarioExentoDisabledImpuesto.setBounds(138, 134, 118, 30);
						panelTerceroTextFieldImpuestosSlider.add(textFieldSalarioExentoDisabledImpuesto);

						textFieldSalarioAuxImpuesto = new JTextField();
						textFieldSalarioAuxImpuesto.setColumns(10);
						textFieldSalarioAuxImpuesto.setBounds(266, 134, 118, 30);
						panelTerceroTextFieldImpuestosSlider.add(textFieldSalarioAuxImpuesto);

						textFieldPrimaAntigGravaImpuesto = new JTextField();
						textFieldPrimaAntigGravaImpuesto.setColumns(10);
						textFieldPrimaAntigGravaImpuesto.setBounds(10, 175, 118, 30);
						panelTerceroTextFieldImpuestosSlider.add(textFieldPrimaAntigGravaImpuesto);

						textFieldPrimaAntigExentoDisabledImpuesto = new JTextField();
						textFieldPrimaAntigExentoDisabledImpuesto.setEnabled(false);
						textFieldPrimaAntigExentoDisabledImpuesto.setColumns(10);
						textFieldPrimaAntigExentoDisabledImpuesto.setBounds(138, 175, 118, 30);
						panelTerceroTextFieldImpuestosSlider.add(textFieldPrimaAntigExentoDisabledImpuesto);

						textFieldPrimaAntigAuxImpuesto = new JTextField();
						textFieldPrimaAntigAuxImpuesto.setColumns(10);
						textFieldPrimaAntigAuxImpuesto.setBounds(266, 175, 118, 30);
						panelTerceroTextFieldImpuestosSlider.add(textFieldPrimaAntigAuxImpuesto);

						textFieldIndemGravaImpuesto = new JTextField();
						textFieldIndemGravaImpuesto.setColumns(10);
						textFieldIndemGravaImpuesto.setBounds(10, 216, 118, 30);
						panelTerceroTextFieldImpuestosSlider.add(textFieldIndemGravaImpuesto);

						textFieldIndemExentoImpuesto = new JTextField();
						textFieldIndemExentoImpuesto.setColumns(10);
						textFieldIndemExentoImpuesto.setBounds(138, 216, 118, 30);
						panelTerceroTextFieldImpuestosSlider.add(textFieldIndemExentoImpuesto);

						textFieldIndemAuxImpuesto = new JTextField();
						textFieldIndemAuxImpuesto.setColumns(10);
						textFieldIndemAuxImpuesto.setBounds(266, 216, 118, 30);
						panelTerceroTextFieldImpuestosSlider.add(textFieldIndemAuxImpuesto);


						textFieldVacioGravaImpuesto = new JTextField();
						textFieldVacioGravaImpuesto.setForeground(Color.RED);
						textFieldVacioGravaImpuesto.setColumns(10);
						textFieldVacioGravaImpuesto.setBounds(10, 257, 118, 30);
						panelTerceroTextFieldImpuestosSlider.add(textFieldVacioGravaImpuesto);

						textFieldVacioExentoImpuesto = new JTextField();
						textFieldVacioExentoImpuesto.setColumns(10);
						textFieldVacioExentoImpuesto.setBounds(138, 257, 118, 30);
						panelTerceroTextFieldImpuestosSlider.add(textFieldVacioExentoImpuesto);

						textFieldVacioAuxImpuesto = new JTextField();
						textFieldVacioAuxImpuesto.setColumns(10);
						textFieldVacioAuxImpuesto.setBounds(266, 257, 118, 30);
						panelTerceroTextFieldImpuestosSlider.add(textFieldVacioAuxImpuesto);

						//panel calculo isr
						panelCalculoISRSlider.setLayout(null);
						panelCalculoISRSlider.setBounds(100, 1000,  614, 795);
						panelCalculoISRSlider.setBackground(SystemColor.controlHighlight);
						panelCalculoISRSlider.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));

						lblCalculoIsr = new JLabel("CALCULO ISR");
						lblCalculoIsr.setBounds(416, 7, 130, 22);
						panelCalculoISRSlider.add(lblCalculoIsr);

						lblEmpleadoCalculoISR = new JLabel("EMPLEADO ISR");
						lblEmpleadoCalculoISR.setBounds(10, 7, 353, 22);
						panelCalculoISRSlider.add(lblEmpleadoCalculoISR);

						panelPrimeroConceptosCalculoISRSlider = new JPanel();
						panelPrimeroConceptosCalculoISRSlider.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
						panelPrimeroConceptosCalculoISRSlider.setBounds(10, 40, 261, 278);
						panelCalculoISRSlider.add(panelPrimeroConceptosCalculoISRSlider);
						panelPrimeroConceptosCalculoISRSlider.setLayout(null);

						lblBaseGravableCalculoISR = new JLabel("Base Gravable");
						lblBaseGravableCalculoISR.setHorizontalAlignment(SwingConstants.RIGHT);
						lblBaseGravableCalculoISR.setBounds(131, 16, 120, 14);
						panelPrimeroConceptosCalculoISRSlider.add(lblBaseGravableCalculoISR);

						lblMenosLimiteInferiorCalculoISR = new JLabel("( - ) limite inferior");
						lblMenosLimiteInferiorCalculoISR.setHorizontalAlignment(SwingConstants.RIGHT);
						lblMenosLimiteInferiorCalculoISR.setBounds(131, 46, 120, 14);
						panelPrimeroConceptosCalculoISRSlider.add(lblMenosLimiteInferiorCalculoISR);

						lblTotalCalculoISR = new JLabel("Total");
						lblTotalCalculoISR.setHorizontalAlignment(SwingConstants.RIGHT);
						lblTotalCalculoISR.setBounds(131, 76, 120, 14);
						panelPrimeroConceptosCalculoISRSlider.add(lblTotalCalculoISR);

						lblXlimiteinferiorCalculoISR = new JLabel("( x ) % limite inferior");
						lblXlimiteinferiorCalculoISR.setHorizontalAlignment(SwingConstants.RIGHT);
						lblXlimiteinferiorCalculoISR.setBounds(131, 104, 120, 14);
						panelPrimeroConceptosCalculoISRSlider.add(lblXlimiteinferiorCalculoISR);

						lblSegundototalCalculoISR = new JLabel("total");
						lblSegundototalCalculoISR.setHorizontalAlignment(SwingConstants.RIGHT);
						lblSegundototalCalculoISR.setBounds(131, 132, 120, 14);
						panelPrimeroConceptosCalculoISRSlider.add(lblSegundototalCalculoISR);

						lblCuotaFijaCalculoISR = new JLabel("( + ) cuota fija");
						lblCuotaFijaCalculoISR.setHorizontalAlignment(SwingConstants.RIGHT);
						lblCuotaFijaCalculoISR.setBounds(131, 162, 120, 14);
						panelPrimeroConceptosCalculoISRSlider.add(lblCuotaFijaCalculoISR);

						lblTotalUltimoCalculoISR = new JLabel("Total");
						lblTotalUltimoCalculoISR.setHorizontalAlignment(SwingConstants.RIGHT);
						lblTotalUltimoCalculoISR.setBounds(131, 190, 120, 14);
						panelPrimeroConceptosCalculoISRSlider.add(lblTotalUltimoCalculoISR);

						lblsubisioAlempleoCalculoISR = new JLabel("( - ) subsidio al empleado");
						lblsubisioAlempleoCalculoISR.setHorizontalAlignment(SwingConstants.RIGHT);
						lblsubisioAlempleoCalculoISR.setBounds(59, 220, 192, 14);
						panelPrimeroConceptosCalculoISRSlider.add(lblsubisioAlempleoCalculoISR);

						panelSegundoTextFieldCalculoISRSlider = new JPanel();
						panelSegundoTextFieldCalculoISRSlider.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
						panelSegundoTextFieldCalculoISRSlider.setBounds(282, 40, 328, 278);
						panelCalculoISRSlider.add(panelSegundoTextFieldCalculoISRSlider);
						panelSegundoTextFieldCalculoISRSlider.setLayout(null);

						textFieldBaseGravableCalculoISR = new JTextField();
						textFieldBaseGravableCalculoISR.setColumns(10);
						textFieldBaseGravableCalculoISR.setBounds(10, 11, 118, 25);
						panelSegundoTextFieldCalculoISRSlider.add(textFieldBaseGravableCalculoISR);

						textFieldBaseGravableAuxCalculoISR = new JTextField();
						textFieldBaseGravableAuxCalculoISR.setColumns(10);
						textFieldBaseGravableAuxCalculoISR.setBounds(162, 11, 118, 25);
						panelSegundoTextFieldCalculoISRSlider.add(textFieldBaseGravableAuxCalculoISR);

						textFieldMenosLimiteInfGravableCalculoISR = new JTextField();
						textFieldMenosLimiteInfGravableCalculoISR.setForeground(Color.RED);
						textFieldMenosLimiteInfGravableCalculoISR.setColumns(10);
						textFieldMenosLimiteInfGravableCalculoISR.setBounds(10, 40, 118, 25);
						panelSegundoTextFieldCalculoISRSlider.add(textFieldMenosLimiteInfGravableCalculoISR);

						textFieldMenosLimiteInfGravableAuxCalculoISR = new JTextField();
						textFieldMenosLimiteInfGravableAuxCalculoISR.setColumns(10);
						textFieldMenosLimiteInfGravableAuxCalculoISR.setBounds(162, 40, 118, 25);
						panelSegundoTextFieldCalculoISRSlider.add(textFieldMenosLimiteInfGravableAuxCalculoISR);

						textFieldTotalGravableCalculoISR = new JTextField();
						textFieldTotalGravableCalculoISR.setColumns(10);
						textFieldTotalGravableCalculoISR.setBounds(10, 69, 118, 25);
						panelSegundoTextFieldCalculoISRSlider.add(textFieldTotalGravableCalculoISR);

						textFieldTotalGravableAuxCalculoISR = new JTextField();
						textFieldTotalGravableAuxCalculoISR.setColumns(10);
						textFieldTotalGravableAuxCalculoISR.setBounds(162, 69, 118, 25);
						panelSegundoTextFieldCalculoISRSlider.add(textFieldTotalGravableAuxCalculoISR);

						textFieldPorlimiteInferiorGravableCalculoISR = new JTextField();
						textFieldPorlimiteInferiorGravableCalculoISR.setForeground(Color.RED);
						textFieldPorlimiteInferiorGravableCalculoISR.setColumns(10);
						textFieldPorlimiteInferiorGravableCalculoISR.setBounds(10, 98, 118, 25);
						panelSegundoTextFieldCalculoISRSlider.add(textFieldPorlimiteInferiorGravableCalculoISR);


						textFieldPorLimiteInferiorGravableAuxCalculoISR = new JTextField();
						textFieldPorLimiteInferiorGravableAuxCalculoISR.setColumns(10);
						textFieldPorLimiteInferiorGravableAuxCalculoISR.setBounds(162, 98, 118, 25);
						panelSegundoTextFieldCalculoISRSlider.add(textFieldPorLimiteInferiorGravableAuxCalculoISR);

						textFieldSegundototalCalculoISR = new JTextField();
						textFieldSegundototalCalculoISR.setColumns(10);
						textFieldSegundototalCalculoISR.setBounds(10, 127, 118, 25);
						panelSegundoTextFieldCalculoISRSlider.add(textFieldSegundototalCalculoISR);

						textFieldSegundoTotalAuxCalculoISR = new JTextField();
						textFieldSegundoTotalAuxCalculoISR.setColumns(10);
						textFieldSegundoTotalAuxCalculoISR.setBounds(162, 127, 118, 25);
						panelSegundoTextFieldCalculoISRSlider.add(textFieldSegundoTotalAuxCalculoISR);

						textFieldCuotaFijaGravableCalculoISR = new JTextField();
						textFieldCuotaFijaGravableCalculoISR.setForeground(Color.RED);
						textFieldCuotaFijaGravableCalculoISR.setColumns(10);
						textFieldCuotaFijaGravableCalculoISR.setBounds(10, 156, 118, 25);
						panelSegundoTextFieldCalculoISRSlider.add(textFieldCuotaFijaGravableCalculoISR);

						textFieldCoutaFijaGravableAuxCalculoISR = new JTextField();
						textFieldCoutaFijaGravableAuxCalculoISR.setColumns(10);
						textFieldCoutaFijaGravableAuxCalculoISR.setBounds(162, 156, 118, 25);
						panelSegundoTextFieldCalculoISRSlider.add(textFieldCoutaFijaGravableAuxCalculoISR);

						textFieldUltimoTotalGravableCalculoISR = new JTextField();
						textFieldUltimoTotalGravableCalculoISR.setColumns(10);
						textFieldUltimoTotalGravableCalculoISR.setBounds(10, 185, 118, 25);
						panelSegundoTextFieldCalculoISRSlider.add(textFieldUltimoTotalGravableCalculoISR);


						textFieldUltimoTotalCFGravableAuxCalculoISR = new JTextField();
						textFieldUltimoTotalCFGravableAuxCalculoISR.setColumns(10);
						textFieldUltimoTotalCFGravableAuxCalculoISR.setBounds(162, 185, 118, 25);
						panelSegundoTextFieldCalculoISRSlider.add(textFieldUltimoTotalCFGravableAuxCalculoISR);

						textFieldSubsidioGravableCalculoISR = new JTextField();
						textFieldSubsidioGravableCalculoISR.setColumns(10);
						textFieldSubsidioGravableCalculoISR.setBounds(10, 214, 118, 25);
						panelSegundoTextFieldCalculoISRSlider.add(textFieldSubsidioGravableCalculoISR);

						textFieldSubsidioGravableAuxCalculoISR = new JTextField();
						textFieldSubsidioGravableAuxCalculoISR.setColumns(10);
						textFieldSubsidioGravableAuxCalculoISR.setBounds(162, 214, 118, 25);
						panelSegundoTextFieldCalculoISRSlider.add(textFieldSubsidioGravableAuxCalculoISR);

						textFieldVacioCalculoISR = new JTextField();
						textFieldVacioCalculoISR.setForeground(Color.RED);
						textFieldVacioCalculoISR.setColumns(10);
						textFieldVacioCalculoISR.setBounds(10, 243, 118, 25);
						panelSegundoTextFieldCalculoISRSlider.add(textFieldVacioCalculoISR);

						textFieldSegundoVacioCalculoISR = new JTextField();
						textFieldSegundoVacioCalculoISR.setColumns(10);
						textFieldSegundoVacioCalculoISR.setBounds(162, 243, 118, 25);
						panelSegundoTextFieldCalculoISRSlider.add(textFieldSegundoVacioCalculoISR);

						slider.addComponent(panelLiquidacionSlider);
						slider.addComponent(panelCalculoISRSlider);
						slider.addComponent(panelImpuestoSlider);

						setBounds(922, 0, 621, 728);
						//						setBounds(612, 0, 625, 875);
						getContentPane().add(jPanel);
						setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						setVisible(true);
						setTitle("Cálculos");
						setIconifiable(true);
						setClosable(true);
						setResizable(true);
						setMaximizable(true);
						
						setFrameIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("savings.png"))));

					}
				};

				FormularioPrincipal.desktopPane.add(jFrameSlide);

				DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
				simbolos.setDecimalSeparator('.');
				DecimalFormat df = new DecimalFormat("####.##", simbolos);

				int fila = tableFiniquito.getSelectedRow();
				if(fila>=0) {
					String id = tableFiniquito.getValueAt(fila, 0).toString();
					String empleado = tableFiniquito.getValueAt(fila, 1).toString();
					String ApPat = tableFiniquito.getValueAt(fila, 2).toString();
					String ApMat = tableFiniquito.getValueAt(fila, 3).toString();
					String salario = tableFiniquito.getValueAt(fila, 5).toString();
					String dpto = tableFiniquito.getValueAt(fila, 6).toString();
					String tipoNomina = tableFiniquitoFalsa.getValueAt(fila, 8).toString();
					String sdi = tableFiniquitoFalsa.getValueAt(fila, 9).toString();
					String fechaIngreso = tableFiniquitoFalsa.getValueAt(fila, 10).toString();

					double sdiPor30 = Double.parseDouble(sdi)*30.4;
					try {
						lblEmpleadoImpuesto.setText(empleado + " " + ApPat + " " + ApMat);
						lblEmpleadoCalculoISR.setText(empleado + " " + ApPat + " " + ApMat);
						textFieldNombreLiquidacion.setText(empleado + " " + ApPat + " " + ApMat);
//						textFieldClaveEmpLiquidacion.setText(id);
//						textFieldDepartamentoLiquidacion.setText(dpto);
						textFieldSalarioGravaImpuesto.setText(salario);
						textFieldSDILiquidacion.setText(sdi);
						textFieldSDIAuxLiquidacion.setText(String.valueOf(df.format(sdiPor30)));
//						textFieldTipoNominaLiquidacion.setText(tipoNomina.substring(47, tipoNomina.length()));

						Date fechaAltaIngreso =new SimpleDateFormat("yyyy-MM-dd").parse(tableFiniquitoFalsa.getValueAt(fila, 10).toString());
						dateChooserFechaIngresoLiquidacion.setDate(fechaAltaIngreso);

					} catch (ParseException PE) {
						PE.printStackTrace();
						StringWriter errors = new StringWriter();
						PE.printStackTrace(new PrintWriter(errors));
						LOG.info("Excepcion: " + errors);
						JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");
					}
				}else {
					JOptionPane.showMessageDialog(null, "No ha seleccionado ningún registro.");
				}


			}
		});
		btnSeleccionar.setBounds(287, 462, 152, 30);
		panelFiniquto.add(btnSeleccionar);

		JScrollPane scrollPaneDatosEmp = new JScrollPane();
		scrollPaneDatosEmp.setViewportBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		scrollPaneDatosEmp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneDatosEmp.setBounds(10, 114, 884, 337);
		scrollPaneDatosEmp.setViewportView(tableFiniquito);
		panelFiniquto.add(scrollPaneDatosEmp);

		label.setBounds(10, 11, 228, 21);
		panelFiniquto.add(label);

		lblIcon.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("icons8_Search_32px_2.png"))));
		lblIcon.setBounds(10, 43, 46, 28);
		panelFiniquto.add(lblIcon);

		textFieldBuscarEmp = new JTextField();
		textFieldBuscarEmp.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("unchecked")
			@Override
			public void keyReleased(KeyEvent arg0) {
				//				int row = tableFiniquito.getRowCount();
				rowSorter.setRowFilter(
						RowFilter.regexFilter(textFieldBuscarEmp.getText().toUpperCase(), IdBusquedaEmple));
				rowSorterFalso.setRowFilter(
						RowFilter.regexFilter(textFieldBuscarEmp.getText().toUpperCase(), IdBusquedaEmpleFalso));
				//				tableFiniquito.changeSelection(row, 1, false, false);
				//				tableFiniquito.setSelectionBackground(Color.BLUE);
			}
		});
		textFieldBuscarEmp.setColumns(10);
		textFieldBuscarEmp.setBorder(null);
		textFieldBuscarEmp.setBackground(SystemColor.controlHighlight);
		textFieldBuscarEmp.setBounds(58, 43, 312, 28);
		panelFiniquto.add(textFieldBuscarEmp);

		separator.setForeground(new Color(176, 196, 222));
		separator.setBackground(new Color(176, 196, 222));
		separator.setBounds(10, 72, 360, 2);
		panelFiniquto.add(separator);

		btnGuardaFiniquito = new JButton("Guardar Finiquito");
		btnGuardaFiniquito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				insertFiniquitoEnCalculoNomina();
				btnArchivoFiniquito.setVisible(true);

			}
		});
		btnGuardaFiniquito.setBounds(489, 632, 138, 25);
		panelFiniquto.add(btnGuardaFiniquito);
		lblFechaPago.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFechaPago.setBounds(489, 473, 85, 14);

		panelFiniquto.add(lblFechaPago);

		dateChooserFechaPago.setBounds(584, 462, 249, 30);
		panelFiniquto.add(dateChooserFechaPago);


		textAreaMotivos.setBounds(487, 516, 346, 105);
		textAreaMotivos.setWrapStyleWord(true);
		textAreaMotivos.setLineWrap (true);
		textAreaMotivos.setPreferredSize(new Dimension (10,100));
		panelFiniquto.add(textAreaMotivos);
		btnArchivoFiniquito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearArchivo();
			}
		});
		btnArchivoFiniquito.setBounds(634, 632, 201, 25);

		panelFiniquto.add(btnArchivoFiniquito);
		lblTipoNominaOculta.setBounds(377, 6, 361, 30);
		lblTipoNominaOculta.setVisible(false);
		panelFiniquto.add(lblTipoNominaOculta);

		//		JButton btnNewButton = new JButton("Reporte");
		//		btnNewButton.addActionListener(new ActionListener() {
		//			public void actionPerformed(ActionEvent arg0) {
		//
		//				javax.swing.filechooser.FileNameExtensionFilter filtroWord=new FileNameExtensionFilter(".docx", "docx");
		//				final JFileChooser miArchivo=new JFileChooser();
		//				miArchivo.setFileFilter(filtroWord);
		//				int aceptar=miArchivo.showSaveDialog(null);
		//				miArchivo.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		//				if(aceptar==JFileChooser.APPROVE_OPTION){
		//					File fileWord=miArchivo.getSelectedFile();
		//					String nombreAr=fileWord.getName();
		//					if(nombreAr.indexOf('.')==-1){
		//						nombreAr+=".docx";
		//						fileWord=new File(fileWord.getParentFile(), nombreAr);
		//					}
		//					if(fileWord.exists()){
		//						JOptionPane.showConfirmDialog(null,"El archivo ya existe, ¿Desea sobre escribirlo?");
		//					}else {
		//						JOptionPane.showMessageDialog(null,"El archivo no existe, se creará");
		//						//crearCarpeta.mkdir();
		//						try {
		//							if(fileWord.createNewFile()) {
		//								JOptionPane.showMessageDialog(null,"Archivo creado");
		//							}else {
		//								JOptionPane.showMessageDialog(null,"Archivo no creado");
		//							}
		//						} catch (IOException e1) {
		//							e1.printStackTrace();
		//							LOG.info("Excepción: " + e1);
		//						}
		//					}
		//					try {
		//						FileOutputStream output=new FileOutputStream(fileWord);
		//						Date fechaMov = Calendar.getInstance().getTime();
		//						SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
		//
		//
		//						int fila = tableFiniquito.getSelectedRow();
		//						if(fila>=0) {
		//							String clave =  tableFiniquito.getValueAt(fila, 0).toString();
		//							String nombre =  tableFiniquito.getValueAt(fila, 1).toString();
		//							String apPat =  tableFiniquito.getValueAt(fila, 2).toString();
		//							String apMat =  tableFiniquito.getValueAt(fila, 3).toString();
		//							String direccion= tableFiniquitoFalsa.getValueAt(fila, 16).toString();
		//							String localidad= tableFiniquitoFalsa.getValueAt(fila, 12).toString();
		//							String ciudad= tableFiniquitoFalsa.getValueAt(fila, 13).toString();
		//							String puesto =  tableFiniquito.getValueAt(fila, 4).toString();
		//							String nombreConcat = nombre+" "+apPat+" "+apMat;
		//							String salario = tableFiniquito.getValueAt(fila, 5).toString();
		//							String dependencia =  tableFiniquito.getValueAt(fila, 6).toString();
		////							String curp =  tableFiniquito.getValueAt(fila, ).toString();
		////							String rfc =  tableFiniquito.getValueAt(fila, ).toString();
		////							String regImss =  tableFiniquito.getValueAt(fila, ).toString();
		////							String FechaIngreso =  tableFiniquito.getValueAt(fila, ).toString();
		//							String curp = tableFiniquitoFalsa.getValueAt(fila, 17).toString();
		//							String rfc =  tableFiniquitoFalsa.getValueAt(fila, 18).toString();
		//							String regImss =  tableFiniquitoFalsa.getValueAt(fila, 19).toString();
		//							String fechaIngreso =  tableFiniquitoFalsa.getValueAt(fila, 20).toString();
		//							String periodo ="PERIODO CORRESPONDIENTE "+FormularioPrincipal.lblPeriodoNumero.getText();
		//							
		//							String textoClave = ""+ clave +"";
		//							String textoNombres = ""+ nombreConcat +"";
		//							String textoSalario = "SALARIO                         "+ salario +"";
		//							String textoPuesto = ""+ puesto +"";
		//							String textoDependencia = ""+ dependencia +"";
		//							String textoCurp = ""+ curp +"";
		//							String textoRFC = ""+ rfc +"";
		//							String textoRegImss = ""+ regImss +"";
		//							String textoFechaIngreso = ""+ fechaIngreso +"";
		//
		////							XWPFParagraph titulo = documentoReporte.createParagraph();
		////							XWPFRun runTitulo = titulo.createRun();
		////							titulo.setAlignment(ParagraphAlignment.LEFT);
		////							runTitulo.setFontFamily("Calibri");
		////							runTitulo.setBold(true);
		////							runTitulo.setFontSize(9);
		////							//runTitulo.setUnderline(UnderlinePatterns.WORDS);
		////							runTitulo.setText("Fecha: "+formatoDeFecha.format(fechaMov));
		////							runTitulo.addBreak();
		////							runTitulo.setText("NOMINA MUNCIPAL DE SAN FRANCISCO DEL RINCÓN");
		////							runTitulo.addBreak();
		////							runTitulo.setText(""+periodo);
		////							runTitulo.addBreak();
		////							runTitulo.setText("DEPENDENCIA: ");runTitulo.setText(""+textoDependencia);runTitulo.addBreak();
		////							runTitulo.setText("-------------------------------------------------------------------------------------------------------------------------------------------------------");
		////							runTitulo.addBreak();
		////							//runTexto.setBold(true);
		//////							runTitulo.setFontSize(9);
		////							runTitulo.setText("NOMBRE");runTitulo.setText(" "+textoNombres);runTitulo.setText("  PUESTO ");runTitulo.setText(" "+textoPuesto);runTitulo.addBreak();
		////							runTitulo.setText("CURP ");runTitulo.setText(" "+textoCurp);runTitulo.setText("  RFC ");runTitulo.setText(" "+textoRFC);
		////							runTitulo.setText("  REG.IMSS ");runTitulo.setText(" "+textoRegImss);
		////							runTitulo.setText("  FECHA INGRESO ");runTitulo.setText(" "+textoFechaIngreso.substring(0,10));
		////							runTitulo.addBreak();
		////							runTitulo.addBreak();
		////							runTitulo.setText("PERCEPCIONES																						DEDUCCIONES");	
		//							
		//							
		//							XWPFParagraph titulo = documentoReporte.createParagraph();
		//							XWPFRun runTitulo = titulo.createRun();
		//							titulo.setAlignment(ParagraphAlignment.LEFT);
		//							runTitulo.setFontFamily("Microsoft Sans Serif");
		//							//runTitulo.setBold(true);
		//							runTitulo.setFontSize(8);
		//							//runTitulo.setUnderline(UnderlinePatterns.WORDS);
		//							runTitulo.setText("Fecha: "+formatoDeFecha.format(fechaMov));
		//							runTitulo.addBreak();
		//							runTitulo.setText("NOMINA MUNCIPAL DE SAN FRANCISCO DEL RINCÓN");
		//							runTitulo.addBreak();
		//							runTitulo.setText(""+periodo);
		//							runTitulo.addBreak();
		//							runTitulo.setText("DEPENDENCIA: ");runTitulo.setText(""+textoDependencia);runTitulo.addBreak();
		//							runTitulo.setText("---------------------------------------------------------------------------------------------------------------------------------------------------------------");
		//							
		//							for(int i=0;i<6;i++) {
		//								//runTexto.setBold(true);
		////								runTitulo.setFontSize(9);
		//								runTitulo.setText("---------------------------------------------------------------------------------------------------------------------------------------------------------------");
		//								runTitulo.setText("NOMBRE");runTitulo.setText(" "+textoNombres);runTitulo.setText("  PUESTO ");runTitulo.setText(" "+textoPuesto);runTitulo.addBreak();
		//								runTitulo.setText("CURP ");runTitulo.setText(" "+textoCurp);runTitulo.setText("  RFC ");runTitulo.setText(" "+textoRFC);
		//								runTitulo.setText("  REG.IMSS ");runTitulo.setText(" "+textoRegImss);
		//								runTitulo.setText("  FECHA INGRESO ");runTitulo.setText(" "+textoFechaIngreso.substring(0,10));
		//								runTitulo.addBreak();
		//								runTitulo.addBreak();
		//								runTitulo.setText("              PERCEPCIONES                                                                        DEDUCCIONES");	
		//								runTitulo.addBreak();
		//								runTitulo.setText(textoSalario+"                                           "+textoSalario);runTitulo.addBreak();
		//								runTitulo.setText(textoSalario+"                                           "+textoSalario);runTitulo.addBreak();
		//								runTitulo.setText(textoSalario+"                                           "+textoSalario);runTitulo.addBreak();
		//								runTitulo.setText("                                                                                                  "+textoSalario);runTitulo.addBreak();
		//								runTitulo.setText("                                                                                                  "/*textoSalario*/);runTitulo.addBreak();
		//								runTitulo.setText("                                                                                                  "/*textoSalario*/);runTitulo.addBreak();
		//								runTitulo.addBreak();
		//								runTitulo.addBreak();
		//								runTitulo.setText("                                                                                            FIRMA______________________________________________");
		//								runTitulo.addBreak();
		//								runTitulo.addBreak();
		//							}
		//							
		//
		////							XWPFParagraph textoDeclaraciones = documentoReporte.createParagraph();
		////							XWPFRun runTexto = textoDeclaraciones.createRun();
		////							textoDeclaraciones.setAlignment(ParagraphAlignment.LEFT);
		////							runTexto.setFontFamily("Calibri");
		////							runTexto.setText("-------------------------------------------------------------------------------------------------------------------------------------------------------");
		////							runTexto.addBreak();
		////							//runTexto.setBold(true);
		////							runTexto.setFontSize(9);
		////							runTexto.setText("NOMBRES ");runTexto.setText(" "+textoNombres);runTexto.setText("  PUESTO ");runTexto.setText(" "+textoPuesto.substring(0, 46));runTexto.addBreak();
		////							runTexto.setText("CURP ");runTexto.setText(" "+textoCurp);runTexto.setText("  RFC ");runTexto.setText(" "+textoRFC);
		////							runTexto.setText("  REG.IMSS ");runTexto.setText(" "+textoRegImss);
		////							runTexto.setText("  FECHA INGRESO ");runTexto.setText(" "+textoFechaIngreso);
		////							runTexto.addBreak();
		////							runTexto.addBreak();
		////							runTexto.setText("PERCEPCIONES																						DEDUCCIONES");
		//							
		////							XWPFParagraph textoCampos = documentoReporte.createParagraph();
		////							XWPFRun  runTextoCampos = textoCampos.createRun();
		////							textoCampos.setAlignment(ParagraphAlignment.LEFT);
		////							runTexto.setFontFamily("Calibri");
		////							runTextoCampos.setFontSize(8);
		////							runTextoCampos.setText(""+textoNombres);
		//
		////							XWPFParagraph lista2A = documentoReporte.createParagraph();
		////							XWPFRun runlista2A = lista2A.createRun();
		////							runlista2A.setText(textoSalario);
		//							
		//							
		//						}else {
		//							JOptionPane.showMessageDialog(null, "Seleccione un empleado");
		//						}
		//						documentoReporte.write(output);
		//						output.close();
		//					}catch(Exception e) {
		//						e.printStackTrace();
		//					}finally {
		//						try {
		//							//con.close();
		//						} catch (Exception ep) {
		//							ep.printStackTrace();
		//							StringWriter errors = new StringWriter();
		//							ep.printStackTrace(new PrintWriter(errors));
		//							LOG.info("Excepcion: " + errors);
		//							JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");
		//						}
		//					}
		//
		//				}
		//			}
		//		});
		//		btnNewButton.setBounds(10, 347, 113, 61);
		//		panelFiniquto.add(btnNewButton);

		btnVerEmpleados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarDatosEmpleadoFiniquito(lblTipoNominaOculta.getText());
				mostrarDatosEmpleadoFiniquitoFalso(lblTipoNominaOculta.getText());
				btnVerEmpleados.setEnabled(false);

			}
		});
		btnVerEmpleados.setBounds(380, 43, 149, 30);

		panelFiniquto.add(btnVerEmpleados);

		lblMoti.setBounds(487, 498, 60, 14);
		panelFiniquto.add(lblMoti);

		lblFondo.setBounds(0, 0, 904, 694);
		lblFondo.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("walll.png"))));
		panelFiniquto.add(lblFondo);




	}



	public void crearArchivo(){
		javax.swing.filechooser.FileNameExtensionFilter filtroWord=new FileNameExtensionFilter(".docx", "docx");
		final JFileChooser miArchivo=new JFileChooser();
		miArchivo.setFileFilter(filtroWord);
		int aceptar=miArchivo.showSaveDialog(null);
		miArchivo.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		if(aceptar==JFileChooser.APPROVE_OPTION){
			File fileWord=miArchivo.getSelectedFile();
			String nombreAr=fileWord.getName();
			if(nombreAr.indexOf('.')==-1){
				nombreAr+=".docx";
				fileWord=new File(fileWord.getParentFile(), nombreAr);
			}
			if(fileWord.exists()){
				JOptionPane.showConfirmDialog(null,"El archivo ya existe, ¿Desea sobre escribirlo?");
			}else {
				JOptionPane.showMessageDialog(null,"El archivo no existe, se creará");
				//crearCarpeta.mkdir();
				try {
					if(fileWord.createNewFile()) {
						JOptionPane.showMessageDialog(null,"Archivo creado");
					}else {
						JOptionPane.showMessageDialog(null,"Archivo no creado");
					}
				} catch (IOException e1) {
					e1.printStackTrace();
					LOG.info("Excepción: " + e1);
				}
			}
			try {
				FileOutputStream output=new FileOutputStream(fileWord);

				int fila = tableFiniquito.getSelectedRow();
				String nombre =  tableFiniquito.getValueAt(fila, 1).toString();
				String apPat =  tableFiniquito.getValueAt(fila, 2).toString();
				String apMat =  tableFiniquito.getValueAt(fila, 3).toString();
				String direccion= tableFiniquitoFalsa.getValueAt(fila, 16).toString();
				String localidad= tableFiniquitoFalsa.getValueAt(fila, 12).toString();
				String ciudad= tableFiniquitoFalsa.getValueAt(fila, 13).toString();
				String puesto =  tableFiniquito.getValueAt(fila, 4).toString();
				String nombreConcat = nombre+" "+apPat+" "+apMat;
				String salario = tableFiniquito.getValueAt(fila, 5).toString();

				DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
				simbolos.setDecimalSeparator('.');
				DecimalFormat formateador = new DecimalFormat("####.##",simbolos);

				Date fechaIngreso = dateChooserFechaIngresoLiquidacion.getDate();
				Date fechaBaja = dateChooserFechaBajaLiquidacion.getDate();
				Date fechaPago = dateChooserFechaPago.getDate();
				Date fechaMov = Calendar.getInstance().getTime();
				SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");

				Numero_a_Letra NumLetra = new Numero_a_Letra();
				String sdiLetra = textFieldSDILiquidacion.getText();
				String subtotalLetra = textFieldSubTotalMontoLiquidacion.getText();
				String totalLetra = textFieldVacioMontoLiquidacion.getText();
				//				System.out.println("sdi en letra: "+sdiLetra);
				//				System.out.println("subtotal en letra: "+subtotalLetra);

				//				NumLetra.Convertir(sdiLetra, true);
				//				NumLetra.Convertir(subtotalLetra, true);
				//System.out.println( sdiLetra  + " , en letra:  "  + NumLetra.Convertir(sdiLetra,band()));
				//System.out.println( subtotalLetra  + " , en letra:  "  + NumLetra.Convertir(subtotalLetra,band()));

				String propDiasAgui =textFieldPropAguiLiquidacion.getText();
				String propDiasVacaciones =textFieldPropVacaLiquidacion.getText();
				String montoAgui =textFieldPropAguiMontoLiquidacion.getText();
				String diasTrab = textFieldDiasTrabajadosLiquidacion.getText();
				String sdi = textFieldSDILiquidacion.getText();
				String montoVac =textFieldPropVacMontoLiquidacion.getText();
				String montoPrimaVac = textFieldPrimVavMontoLiquidacion.getText();
				String montoPrimaAntig = textFieldPrimaAntiMontoLiquidacion.getText();
				String prestamo = textFieldPrestamoMontoLiquidacion.getText();
				String isr = textFieldISRMontoLiquidacion.getText();
				String indemnizacion = textFieldIndemizacionMontoLiquidacion.getText();
				String subtotal = textFieldSubTotalMontoLiquidacion.getText();
				String total = textFieldVacioMontoLiquidacion.getText();
				double valorAgui = 0;
				double valorVac = 0;
				double valorMontoAgui = 0;
				double valorDiasTrab = 0;
				double valorSdi = 0;
				double valorMontoVac = 0;
				double valorPrimaVac = 0;
				double valorPrimaAntig = 0;
				double valorPrestamo = 0;
				double valorIsr = 0;
				double valorIndem = 0;
				double valorSubtotal = 0;
				double valorTotal = 0;

				try
				{
					// parse() lanza una ParseException en caso de fallo que hay que capturar.
					Number numeroo = formateador.parse(propDiasAgui);
					Number numeroVac = formateador.parse(propDiasVacaciones);
					Number numeroMontoAgui = formateador.parse(montoAgui);
					Number numeroDiasTrab = formateador.parse(diasTrab);
					Number numeroSdi = formateador.parse(sdi);
					Number numeroMontoVac = formateador.parse(montoVac);
					Number numeroMontoPrimaVac = formateador.parse(montoPrimaVac);
					Number numeroMontoPrimaAntig = formateador.parse(montoPrimaAntig);
					Number numeroPrestamo = formateador.parse(prestamo);
					Number numeroIsr = formateador.parse(isr);
					Number numeroIndem = formateador.parse(indemnizacion);
					Number numeroSubtotal = formateador.parse(subtotal);
					Number numeroTotal = formateador.parse(total);
					valorAgui = numeroo.doubleValue();
					valorVac = numeroVac.doubleValue();
					valorMontoAgui = numeroMontoAgui.doubleValue();
					valorDiasTrab = numeroDiasTrab.intValue();
					valorSdi = numeroSdi.doubleValue();
					valorMontoVac = numeroMontoVac.doubleValue();
					valorPrimaVac = numeroMontoPrimaVac.doubleValue();
					valorPrimaAntig = numeroMontoPrimaAntig.doubleValue();
					valorPrestamo = numeroPrestamo.doubleValue();
					valorIsr = numeroIsr.doubleValue();
					valorIndem = numeroIndem.doubleValue();
					valorSubtotal = numeroSubtotal.doubleValue();
					valorTotal = numeroTotal.doubleValue();
				}
				catch (ParseException e)
				{
					e.printStackTrace();
					StringWriter errors = new StringWriter();
					e.printStackTrace(new PrintWriter(errors));
					LOG.info("Excepcion: "+ errors );
					//JOptionPane.showMessageDialog(null, el, "Error de conexion", JOptionPane.ERROR_MESSAGE);
					JOptionPane.showMessageDialog(null,"Error. El usuario ha escrito algo que no se puede convertir a número");
				}

				String title = "CONVENIO QUE CELEBRAN POR UNA PARTE EL ING. SERGIO ABEL MENDEZ BARBA, EN SU CARÁCTER DE SÍNDICO MUNICIPAL DE SAN FRANCISCO DEL RINCÓN, "
						+ "GUANAJUATO, Y POR LA OTRA PARTE EL C. "+ nombreConcat   +",  AL TENOR DE LOS SIGUIENTES:";

				String textoDeclaracion = "DECLARACIONES";
				String textoDeclaracion1="I.- DECLARA EL SÍNDICO MUNICIPAL.";
				String textoDeclaracion1A = "A) QUE COMPARECE A LA FIRMA DEL PRESENTE INSTRUMENTO LEGAL, ACREDITANDO SU PERSONALIDAD EN TÉRMINOS "
						+ "DE LA CONSTANCIA DE MAYORÍA Y VALIDEZ DE LA ELECCIÓN DE AYUNTAMIENTO 2015 – 2018, DE FECHA 10 DE JUNIO DEL AÑO 2015 Y CONFORME AL ACTA No. 986"
						+ " EN SESIÓN SOLEMNE DE INSTALACIÓN DE FECHA 10 DE OCTUBRE DEL 2015 EN EL QUE SE OTORGA EL NOMBRAMIENTO COMO SINDICO DEL H. AYUNTAMIENTO,"
						+ " ASÍ MISMO COMPARECE EN SU CARÁCTER DE REPRESENTANTE LEGAL DEL AYUNTAMIENTO DE SAN FRANCISCO DEL RINCÓN, GUANAJUATO, EN TÉRMINOS DE LO DISPUESTO POR LAS "
						+ "FRACCIONES I Y II DEL ARTÍCULO 71 Y DE LA FRACCIÓN IX DEL ARTÍCULO 110 DE LA LEY ORGÁNICA MUNICIPAL PARA EL ESTADO DE GUANAJUATO VIGENTE;";
				String textoDeclaracion1B = "B) QUE SU DOMICILIO LEGAL ES PALACIO MUNICIPAL S/N, ZONA CENTRO, DE LA CIUDAD DE SAN FRANCISCO DEL RINCÓN, GUANAJUATO.";
				String textoDeclaracion2 = "II.- DECLARA EL C. "+ nombreConcat +".";
				String textoDeclaracion2A = "A) QUE ES MEXICANO POR NACIMIENTO, MAYOR DE EDAD Y QUE CUENTA CON CAPACIDAD PLENA PARA OBLIGARSE EN TÉRMINOS DEL PRESENTE INSTRUMENTO LEGAL.";
				String textoDeclaracion2B = "B) QUE SEÑALA COMO DOMICILIO LEGAL, EL UBICADO EN CALLE "+ direccion +", COLONIA "+ localidad +", MUNICIPIO DE "+ ciudad +", GTO.";
				String textoDeclaracion2C = "C)  QUE INGRESÓ A LABORAR A LA PRESIDENCIA MUNICIPAL DE SAN FRANCISCO DEL RINCÓN, GUANAJUATO, CON LA CATEGORÍA DE "+puesto+","
						+ " EL DÍA "+formatoDeFecha.format(fechaIngreso) +" Y QUE TIENE ASIGNADO UN SALARIO DIARIO DE "+ valorSdi + " "+ NumLetra.Convertir(sdiLetra,band()) +".";
				String textoDeclaracion3 = "CLÁUSULAS";
				String textoDeclaracion3A = "PRIMERA.- AMBAS PARTES MANIFIESTAN QUE ES SU VOLUNTAD CELEBRAR EL PRESENTE CONVENIO, CON LA FINALIDAD DE DAR POR TERMINADA LA RELACIÓN LABORAL EXISTENTE,"
						+ " ENTRE EL MUNICIPIO DE SAN FRANCISCO DEL RINCÓN, ESTADO DE GUANAJUATO Y EL C. "+nombreConcat+", A PARTIR DEL DÍA "+formatoDeFecha.format(fechaBaja)+".";
				String textoDeclaracion3B = "SEGUNDA.- AMBAS PARTES MANIFIESTAN Y ACEPTAN, QUE PARA LA DETERMINACIÓN DE LOS CONCEPTOS QUE INTEGRAN EL MONTO TOTAL DEL FINIQUITO A PAGAR, "
						+ "SE TOMARÁN LOS SIGUIENTES DATOS:";
				String textoDeclaracionTabla1 = "	PUESTO:           					"+puesto+"";
				//				String textoDeclaracionTabla1A = puesto;
				String textoDeclaracionTabla1A = "	FECHA DE INGRESO: 				"+formatoDeFecha.format(fechaIngreso)+"";
				//				String textoDeclaracionTabla2A = formatoDeFecha.format(fechaIngreso);
				String textoDeclaracionTabla1B = "	FECHA DE BAJA:   				"+ formatoDeFecha.format(fechaBaja) +"";
				//				String textoDeclaracionTabla3A = formatoDeFecha.format(fechaBaja);
				String textoDeclaracionTabla1C = "	SALARIO DIARIO:  				"+ valorSdi +"";
				//				String textoDeclaracionTabla4A = String.valueOf(valorSdi);
				String textoDeclaracionTabla1D = "	DÍAS LABORADOS:   				"+ valorDiasTrab +"";
				//				String textoDeclaracionTabla5A = String.valueOf(valorDiasTrab);
				String textoDeclaracionTabla1E = "	DÍAS DE VACACIONES ADEUDADOS:   		"+ valorVac +"";
				String textoDeclaracionTabla1F = "	AGUINALDO ANUAL:   				"+ valorAgui +"";
				String textoDeclaracion3C = "TERCERA.- EL MONTO A PAGAR POR CONCEPTO DE FINIQUITO SERÁ DE "+ valorTotal  +" "+ NumLetra.Convertir(totalLetra,band()) +", EL CUAL SE INTEGRA POR LOS CONCEPTOS Y CANTIDADES SIGUIENTES: ";
				String textoDeclaracionTabla2 = "	CONCEPTO                         		IMPORTE";
				String textoDeclaracionTabla2A = "	AGUINALDO                      		"+ valorMontoAgui +"";
				String textoDeclaracionTabla2B = "	PRIMA VACACIONAL                    		"+ valorPrimaVac +"";
				String textoDeclaracionTabla2C = "	PRIMA DE ANTIGÜEDAD           		"+ valorPrimaAntig +"";
				String textoDeclaracionTabla2D = "	INDEMNIZACIÓN                 		"+ valorIndem +"";
				String textoDeclaracionTabla2E = "	SALARIO                      			"+ salario +"";
				String textoDeclaracionTabla2F = "	SUBTOTAL                       			"+ valorSubtotal +"";
				String textoDeclaracionTabla2G = "	PRESTAMO                       			"+ valorPrestamo +"";
				String textoDeclaracionTabla2H = "	ISR                       	 			"+ valorIsr +"";
				String textoDeclaracionTabla2I = "	TOTAL A PAGAR                       		"+ valorTotal +"";
				String textoDeclaracion3D = "CUARTA.- EL MONTO TOTAL A PAGAR REFERIDO EN LA CLÁUSULA QUE ANTECEDE SERÁ CUBIERTA POR EL MUNICIPIO DE SAN FRANCISCO DEL RINCÓN, GUANAJUATO, "
						+ "POR CONDUCTO DE LA TESORERÍA MUNICIPAL, EN UNA SOLA EXHIBICIÓN, MEDIANTE CUENTA ELECTRONICA, A MAS TARDAR EL DÍA "+ formatoDeFecha.format(fechaPago) +". ";
				String textoDeclaracion3E = "QUINTA.- AMBAS PARTES DECLARAN QUE EN LA CELEBRACIÓN DEL PRESENTE CONTRATO NO EXISTE ENGAÑO, DOLO, ERROR O MALA FE, "
						+ "VIOLENCIA O LESIÓN O CUALQUIER OTRO VICIO DEL CONSENTIMIENTO, QUE PUDIERA AFECTAR LA VALIDEZ Y EXISTENCIA DEL MISMO.";
				String textoDeclaracion3F ="SEXTA.- AMBAS PARTES SE COMPROMETEN A NO EJERCITAR  EN EL PRESENTE O  EN EL FUTURO, NINGUNA ACCIÓN CIVIL, PENAL, LABORAL, "
						+ "ADMINISTRATIVA O DE DERECHO EN CONTRA DE LA OTRA, NI DE QUIEN RESULTE RESPONSABLE DE LA FUENTE DE TRABAJO, POR LO CUAL SE OTORGAN EL FINIQUITO MÁS AMPLIO QUE EN DERECHO PROCEDA.";
				String textoDeclaracion3G = "SÉPTIMA.- LAS PARTES HACEN CONSTAR QUE DURANTE LA RELACIÓN LABORAL EN EL DESEMPEÑO DE SUS LABORES, EL C. "+  nombreConcat +", "
						+ "NO SUFRIÓ NINGÚN ACCIDENTE EN EL TRABAJO, NI TAMPOCO ADQUIRIÓ ENFERMEDAD PROFESIONAL ALGUNA, POR LO CUAL LIBERA AL MUNICIPIO DE  SAN FRANCISCO DEL RINCÓN, GUANAJUATO, "
						+ "DE CUALQUIER RESPONSABILIDAD AL RESPECTO CON MOTIVO DE LA TERMINACIÓN DE LA RELACIÓN LABORAL.";
				String textoDeclaracion3H = "LEÍDO QUE FUE A LAS PARTES EL PRESENTE INSTRUMENTO JURÍDICO Y CONFORMES CON SU CONTENIDO, ALCANCE Y FUERZA LEGAL, "
						+ "LO RATIFICAN EN TODAS Y CADA UNA DE SUS PARTES, FIRMANDO AL CALCE Y AL MARGEN PARA DEBIDA CONSTANCIA LEGAL, "
						+ "EN LA CIUDAD DE SAN FRANCISCO DEL RINCÓN, GUANAJUATO, A LOS "+ formatoDeFecha.format(fechaMov) +".";
				String textoFirmaSindico = "ING. SERGIO ABEL MENDEZ BARBA";
				String textoSindico = "SINDICO DEL H. AYUNTAMIENTO Y REPRESENTANTE\n"
						+ "LEGAL DE SAN FRANCISCO DEL RINCÓN,\n"
						+ "GUANAJUATO.";
				String textoFirmaCiudadano = "C. ";
				String textoCiudadno = nombreConcat;

				XWPFParagraph titulo =documento.createParagraph();
				XWPFRun runTitulo = titulo.createRun();
				titulo.setAlignment(ParagraphAlignment.BOTH);
				runTitulo.setBold(true);
				runTitulo.setFontSize(13);
				//runTitulo.setUnderline(UnderlinePatterns.WORDS);
				runTitulo.setText(title);
				runTitulo.setColor("111010");
				runTitulo.addBreak();

				XWPFParagraph textoDeclaraciones =documento.createParagraph();
				XWPFRun runTexto = textoDeclaraciones.createRun();
				textoDeclaraciones.setAlignment(ParagraphAlignment.CENTER);
				runTexto.setText(textoDeclaracion);
				runTexto.setBold(true);
				runTexto.setFontSize(11);
				runTexto.setColor("111010");
				runTexto.addBreak();

				XWPFParagraph declaracion1 =documento.createParagraph();
				XWPFRun rundeclaracion1 = declaracion1.createRun();
				declaracion1.setAlignment(ParagraphAlignment.LEFT);
				rundeclaracion1.setText(textoDeclaracion1);
				rundeclaracion1.setBold(true);
				rundeclaracion1.setFontSize(11);
				rundeclaracion1.setColor("111010");
				rundeclaracion1.addBreak();

				XWPFParagraph declaracion1A =documento.createParagraph();
				XWPFRun rundeclaracion1A = declaracion1A.createRun();
				declaracion1A.setAlignment(ParagraphAlignment.BOTH);
				rundeclaracion1A.setText(textoDeclaracion1A);
				rundeclaracion1A.setFontSize(11);
				rundeclaracion1A.setColor("111010");
				rundeclaracion1A.addBreak();

				XWPFParagraph declaracion1B =documento.createParagraph();
				XWPFRun rundeclaracion1B = declaracion1B.createRun();
				declaracion1B.setAlignment(ParagraphAlignment.BOTH);
				rundeclaracion1B.setText(textoDeclaracion1B);
				rundeclaracion1B.setFontSize(11);
				rundeclaracion1B.setColor("111010");
				rundeclaracion1B.addBreak();

				XWPFParagraph declaracion2 =documento.createParagraph();
				XWPFRun rundeclaracion2 = declaracion2.createRun();
				declaracion2.setAlignment(ParagraphAlignment.LEFT);
				rundeclaracion2.setText(textoDeclaracion2);
				rundeclaracion2.setBold(true);
				rundeclaracion2.setFontSize(11);
				rundeclaracion2.setColor("111010");
				rundeclaracion2.addBreak();


				XWPFParagraph declaracion2A =documento.createParagraph();
				XWPFRun rundeclaracion2A = declaracion2A.createRun();
				declaracion2A.setAlignment(ParagraphAlignment.BOTH);
				rundeclaracion2A.setText(textoDeclaracion2A);
				rundeclaracion2A.setFontSize(11);
				rundeclaracion2A.setColor("111010");
				rundeclaracion2A.addBreak();

				XWPFParagraph declaracion2B =documento.createParagraph();
				XWPFRun rundeclaracion2B = declaracion2B.createRun();
				declaracion2B.setAlignment(ParagraphAlignment.BOTH);
				rundeclaracion2B.setText(textoDeclaracion2B);
				rundeclaracion2B.setFontSize(11);
				rundeclaracion2B.setColor("111010");
				rundeclaracion2B.addBreak();

				XWPFParagraph declaracion2C =documento.createParagraph();
				XWPFRun rundeclaracion2C = declaracion2C.createRun();
				declaracion2C.setAlignment(ParagraphAlignment.BOTH);
				rundeclaracion2C.setText(textoDeclaracion2C);
				rundeclaracion2C.setFontSize(11);
				rundeclaracion2C.setColor("111010");
				rundeclaracion2C.addBreak();

				XWPFParagraph declaracion3 =documento.createParagraph();
				XWPFRun rundeclaracion3 = declaracion3.createRun();
				textoDeclaraciones.setAlignment(ParagraphAlignment.CENTER);
				rundeclaracion3.setText(textoDeclaracion3);
				rundeclaracion3.setBold(true);
				rundeclaracion3.setFontSize(11);
				rundeclaracion3.setColor("111010");
				rundeclaracion3.addBreak();

				XWPFParagraph declaracion3A =documento.createParagraph();
				XWPFRun rundeclaracion3A = declaracion3A.createRun();
				declaracion3A.setAlignment(ParagraphAlignment.BOTH);
				rundeclaracion3A.setText(textoDeclaracion3A);
				rundeclaracion3A.setFontSize(11);
				rundeclaracion3A.setColor("111010");
				rundeclaracion3A.addBreak();

				XWPFParagraph declaracion3B = documento.createParagraph();
				XWPFRun rundeclaracion3B = declaracion3B.createRun();
				declaracion3B.setAlignment(ParagraphAlignment.BOTH);
				rundeclaracion3B.setText(textoDeclaracion3B);
				rundeclaracion3B.setFontSize(11);
				rundeclaracion3B.setColor("111010");
				rundeclaracion3B.addBreak();
				rundeclaracion3B.addBreak();
				rundeclaracion3B.addBreak();
				rundeclaracion3B.addBreak();

				//for(int i=0;i<8;i++) {
				XWPFParagraph lista =documento.createParagraph();
				XWPFRun runlista = lista.createRun();

				runlista.setBold(true);
				runlista.setText(textoDeclaracionTabla1);
				//					runlista2.setText(textoDeclaracionTabla1A);
				runlista.addBreak();
				runlista.setText(textoDeclaracionTabla1A);
				runlista.addBreak();
				runlista.setText(textoDeclaracionTabla1B);
				runlista.addBreak();
				runlista.setText(textoDeclaracionTabla1C);
				runlista.addBreak();
				runlista.setText(textoDeclaracionTabla1D);
				runlista.addBreak();
				runlista.setText(textoDeclaracionTabla1E);
				runlista.addBreak();
				runlista.setText(textoDeclaracionTabla1F);
				runlista.addBreak();
				//}


				XWPFParagraph declaracion3C =documento.createParagraph();
				XWPFRun rundeclaracion3C = declaracion3C.createRun();
				declaracion3C.setAlignment(ParagraphAlignment.BOTH);
				rundeclaracion3C.setText(textoDeclaracion3C);
				rundeclaracion3C.setFontSize(11);
				rundeclaracion3C.setColor("111010");
				rundeclaracion3C.addBreak();

				XWPFParagraph lista2 =documento.createParagraph();
				//Set bottom border to paragraph
				lista2.setBorderBottom(Borders.BASIC_BLACK_DASHES);

				//Set left border to paragraph
				lista2.setBorderLeft(Borders.BASIC_BLACK_DASHES);

				//Set right border to paragraph
				lista2.setBorderRight(Borders.BASIC_BLACK_DASHES);

				//Set top border to paragraph
				lista2.setBorderTop(Borders.BASIC_BLACK_DASHES);
				XWPFRun runlista2 = lista2.createRun();
				runlista2.setBold(true);
				runlista2.addBreak();
				runlista2.setText(textoDeclaracionTabla2);
				runlista2.addBreak();


				XWPFParagraph lista2A =documento.createParagraph();
				XWPFRun runlista2A = lista2A.createRun();
				runlista2A.setBold(true);
				runlista2A.setText(textoDeclaracionTabla2A);
				runlista2A.addBreak();
				runlista2A.setText(textoDeclaracionTabla2B);
				runlista2A.addBreak();
				runlista2A.setText(textoDeclaracionTabla2C);
				runlista2A.addBreak();
				runlista2A.setText(textoDeclaracionTabla2D);
				runlista2A.addBreak();
				runlista2A.setText(textoDeclaracionTabla2E);
				runlista2A.addBreak();
				runlista2A.setText(textoDeclaracionTabla2F);
				runlista2A.addBreak();
				runlista2A.setText(textoDeclaracionTabla2G);
				runlista2A.addBreak();
				runlista2A.setText(textoDeclaracionTabla2H);
				runlista2A.addBreak();
				runlista2A.setText(textoDeclaracionTabla2I);
				runlista2A.addBreak();	
				runlista2A.addBreak();		
				runlista2A.addBreak();	

				XWPFParagraph declaracion3D =documento.createParagraph();
				XWPFRun rundeclaracion3D = declaracion3D.createRun();
				declaracion3D.setAlignment(ParagraphAlignment.BOTH);
				rundeclaracion3D.setText(textoDeclaracion3D);
				rundeclaracion3D.setFontSize(11);
				rundeclaracion3D.setColor("111010");
				rundeclaracion3D.addBreak();

				XWPFParagraph declaracion3E =documento.createParagraph();
				XWPFRun rundeclaracion3E = declaracion3E.createRun();
				declaracion3E.setAlignment(ParagraphAlignment.BOTH);
				rundeclaracion3E.setText(textoDeclaracion3E);
				rundeclaracion3E.setFontSize(11);
				rundeclaracion3E.setColor("111010");
				rundeclaracion3E.addBreak();

				XWPFParagraph declaracion3F =documento.createParagraph();
				XWPFRun rundeclaracion3F = declaracion3F.createRun();
				declaracion3F.setAlignment(ParagraphAlignment.BOTH);
				rundeclaracion3F.setText(textoDeclaracion3F);
				rundeclaracion3F.setFontSize(11);
				rundeclaracion3F.setColor("111010");
				rundeclaracion3F.addBreak();

				XWPFParagraph declaracion3G =documento.createParagraph();
				XWPFRun rundeclaracion3G = declaracion3G.createRun();
				declaracion3G.setAlignment(ParagraphAlignment.BOTH);
				rundeclaracion3G.setText(textoDeclaracion3G);
				rundeclaracion3G.setFontSize(11);
				rundeclaracion3G.setColor("111010");
				rundeclaracion3G.addBreak();

				XWPFParagraph declaracion3H =documento.createParagraph();
				XWPFRun rundeclaracion3H = declaracion3H.createRun();
				declaracion3H.setAlignment(ParagraphAlignment.BOTH);
				rundeclaracion3H.setText(textoDeclaracion3H);
				rundeclaracion3H.setFontSize(11);
				rundeclaracion3H.setColor("111010");
				rundeclaracion3H.addBreak();
				rundeclaracion3H.addBreak();
				rundeclaracion3H.addBreak();
				rundeclaracion3H.addBreak();
				rundeclaracion3H.addBreak();
				rundeclaracion3H.addBreak();
				rundeclaracion3H.addBreak();
				rundeclaracion3H.addBreak();
				rundeclaracion3H.addBreak();
				rundeclaracion3H.addBreak();
				rundeclaracion3H.addBreak();
				rundeclaracion3H.addBreak();
				rundeclaracion3H.addBreak();
				rundeclaracion3H.addBreak();


				XWPFParagraph declaracionFirmaSindico =documento.createParagraph();
				XWPFRun rundeclaracionFirmaSindico = declaracionFirmaSindico.createRun();
				declaracionFirmaSindico.setAlignment(ParagraphAlignment.LEFT);
				rundeclaracionFirmaSindico.setBold(true);
				rundeclaracionFirmaSindico.setText(textoFirmaSindico+"			"+textoFirmaCiudadano+textoCiudadno);
				rundeclaracionFirmaSindico.setFontSize(11);
				rundeclaracionFirmaSindico.setColor("111010");

				XWPFParagraph declaracionSindico =documento.createParagraph();
				XWPFRun rundeclaracionSindico = declaracionSindico.createRun();
				declaracionSindico.setAlignment(ParagraphAlignment.LEFT);
				rundeclaracionSindico.setText(textoSindico);
				rundeclaracionSindico.setFontSize(10);
				rundeclaracionSindico.setColor("111010");

				documento.write(output);
				output.close();
			}
			catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage().toString());
			}

			finally{
				try {
					if(System.getProperty("os.name").equals("Linux")){
						Runtime.getRuntime().exec("libreoffice"+fileWord.getAbsolutePath());
					}
					else{
						Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+fileWord.getAbsolutePath());
					}
				}
				catch (IOException ex) {
					//Logger.getLogger(Word.class.getName()).log(Level.SEVERE, null, ex);
				}finally {
					try {
						//con.close();
					} catch (Exception ep) {
						ep.printStackTrace();
						StringWriter errors = new StringWriter();
						ep.printStackTrace(new PrintWriter(errors));
						LOG.info("Excepcion: " + errors);
						JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");
					}
				}
			}
		}
	}

	public static boolean band(){
		if ( Math.random() > .5) {
			return true;
		}else{
			return false;
		}
	}

	public int insertFiniquitoEnCalculoNomina(){

		int resultado = 0;
		int claveInternaPercepcion=40;
		int claveInternaDeduccion=22;
		int claveFiniquito=6;
		double valorClaveInternaPercepcion=0.0;
		double valorClaveInternaDeduccion=0.0;
		PoolNYCH nych = new PoolNYCH();
		Connection conIncapacidad =null;
		int fila = tableFiniquito.getSelectedRow();
		String sqlInsert="";

		DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
		simbolos.setDecimalSeparator('.');
		DecimalFormat formateador = new DecimalFormat("####.##",simbolos);

		Date fechaIngreso = dateChooserFechaIngresoLiquidacion.getDate();
		Date fechaBaja = dateChooserFechaBajaLiquidacion.getDate();
		Date fechaPago = dateChooserFechaPago.getDate();
		Date fechaMov = Calendar.getInstance().getTime();
		SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");

		Numero_a_Letra NumLetra = new Numero_a_Letra();
		String sdiLetra = textFieldSDILiquidacion.getText();
		String subtotalLetra = textFieldSubTotalMontoLiquidacion.getText();
		System.out.println("sdi en letra: "+sdiLetra);
		System.out.println("subtotal en letra: "+subtotalLetra);

		NumLetra.Convertir(sdiLetra, true);
		NumLetra.Convertir(subtotalLetra, true);
		System.out.println( sdiLetra  + " , en letra:  "  + NumLetra.Convertir(sdiLetra,band()));
		System.out.println( subtotalLetra  + " , en letra:  "  + NumLetra.Convertir(subtotalLetra,band()));

		String propDiasAgui =textFieldPropAguiLiquidacion.getText();
		String propDiasVacaciones =textFieldPropVacaLiquidacion.getText();
		String montoAgui =textFieldPropAguiMontoLiquidacion.getText();
		String diasTrab = textFieldDiasTrabajadosLiquidacion.getText();
		String sdi = textFieldSDILiquidacion.getText();
		String montoVac =textFieldPropVacMontoLiquidacion.getText();
		String montoPrimaVac = textFieldPrimVavMontoLiquidacion.getText();
		String montoPrimaAntig = textFieldPrimaAntiMontoLiquidacion.getText();
		String prestamo = textFieldPrestamoMontoLiquidacion.getText();
		String isr = textFieldISRMontoLiquidacion.getText();
		String indemnizacion = textFieldIndemizacionMontoLiquidacion.getText();
		String subtotal = textFieldSubTotalMontoLiquidacion.getText();
		String total = textFieldVacioMontoLiquidacion.getText();
		double valorAgui = 0;
		double valorVac = 0;
		double valorMontoAgui = 0;
		double valorDiasTrab = 0;
		double valorSdi = 0;
		double valorMontoVac = 0;
		double valorPrimaVac = 0;
		double valorPrimaAntig = 0;
		double valorPrestamo = 0;
		double valorIsr = 0;
		double valorIndem = 0;
		double valorSubtotal = 0;
		double valorTotal = 0;
		try
		{
			// parse() lanza una ParseException en caso de fallo que hay que capturar.
			Number numeroo = formateador.parse(propDiasAgui);
			Number numeroVac = formateador.parse(propDiasVacaciones);
			Number numeroMontoAgui = formateador.parse(montoAgui);
			Number numeroDiasTrab = formateador.parse(diasTrab);
			Number numeroSdi = formateador.parse(sdi);
			Number numeroMontoVac = formateador.parse(montoVac);
			Number numeroMontoPrimaVac = formateador.parse(montoPrimaVac);
			Number numeroMontoPrimaAntig = formateador.parse(montoPrimaAntig);
			Number numeroPrestamo = formateador.parse(prestamo);
			Number numeroIsr = formateador.parse(isr);
			Number numeroIndem = formateador.parse(indemnizacion);
			Number numeroSubtotal = formateador.parse(subtotal);
			Number numeroTotal = formateador.parse(total);
			valorAgui = numeroo.doubleValue();
			valorVac = numeroVac.doubleValue();
			valorMontoAgui = numeroMontoAgui.doubleValue();
			valorDiasTrab = numeroDiasTrab.doubleValue();
			valorSdi = numeroSdi.doubleValue();
			valorMontoVac = numeroMontoVac.doubleValue();
			valorPrimaVac = numeroMontoPrimaVac.doubleValue();
			valorPrimaAntig = numeroMontoPrimaAntig.doubleValue();
			valorPrestamo = numeroPrestamo.doubleValue();
			valorIsr = numeroIsr.doubleValue();
			valorIndem = numeroIndem.doubleValue();
			valorSubtotal = numeroSubtotal.doubleValue();
			valorTotal = numeroTotal.doubleValue();
			//System.out.println("Valor: "+valorAgui);
		}
		catch (ParseException e)
		{
			e.printStackTrace();
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: "+ errors );
			//JOptionPane.showMessageDialog(null, el, "Error de conexion", JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null,"Error. El usuario ha escrito algo que no se puede convertir a número");
		}

		if(fila>=0) {
			String id= tableFiniquito.getValueAt(fila, 0).toString();
			String nombre= tableFiniquito.getValueAt(fila, 1).toString();
			String apPat= tableFiniquito.getValueAt(fila, 2).toString();
			String apMat= tableFiniquito.getValueAt(fila, 3).toString();
			String idPuesto= tableFiniquitoFalsa.getValueAt(fila, 15).toString();
			String unidad= tableFiniquitoFalsa.getValueAt(fila, 14).toString();
			String salario= tableFiniquito.getValueAt(fila, 5).toString();
			String idTipoNomina= tableFiniquitoFalsa.getValueAt(fila, 11).toString();
			String localidad= tableFiniquitoFalsa.getValueAt(fila, 12).toString();
			String ciudad= tableFiniquitoFalsa.getValueAt(fila, 13).toString();
			String direccion= tableFiniquitoFalsa.getValueAt(fila, 16).toString();

			sqlInsert="INSERT INTO dbo.BASE_FINIQUITOS (EMPLEADO,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,FECHA_MOVIMIENTO,ID_UNIDAD,FECHA_INGRESO,FECHA_BAJA,ANTIGUEDAD,DIAS_TRABAJADOS,SDI,SDI_EN_LETRA,ID_PUESTO,"
					+ "PROPORCION_DIAS_AGUINALDO,PROPORCION_DIAS_VACACIONES,MONTO_AGUINALDO,MONTO_VACACIONES,MONTO_PRIMA_VACACIONAL,MONTO_PRIMA_ANTIGUEDAD,SALARIO,PRESTAMO,ISR,INDEMNIZACION,SUBTOTAL,TOTAL,"
					+ "SUBTOTAL_LETRA,ID_EJERCICIO,FECHA_PAGO,DIRECCION,LOCALIDAD,CIUDAD,MOTIVO)" 
					+ ""
					+ "VALUES ("+ id +",'"+ nombre +"','"+ apPat +"','"+ apMat +"',"+"convert(datetime,'"+formatoDeFecha.format(fechaMov)+"',101),"+ unidad +","+"convert(datetime,'"+ formatoDeFecha.format(fechaIngreso)+"',101),"+"convert(datetime,'"+ formatoDeFecha.format(fechaBaja)+"',101),"
					+ "'"+textFieldAntiguedadLiquidacion.getText() +"',"+ valorDiasTrab +","+ valorSdi +","
					+ "'"+ NumLetra.Convertir(sdiLetra, true) +"',"+idPuesto+","+ valorAgui +","+valorVac+","+valorMontoAgui+","+valorMontoVac+","+valorPrimaVac+","+valorPrimaAntig+","
					+ ""+salario+","+valorPrestamo+","+valorIsr+","+valorIndem+","+valorSubtotal+","+valorTotal+",'"+NumLetra.Convertir(subtotalLetra, true)+"',"+idTipoNomina+","
					+ ""+"convert(datetime,'"+formatoDeFecha.format(fechaPago)+"',101),'"+direccion+"','"+localidad+"','"+ciudad+"','"+textAreaMotivos.getText()+"')";
			System.out.println("INSERT: "+sqlInsert);
			//sqlInsert="";
		}

		try {
			conIncapacidad = nych.datasource.getConnection();
			PreparedStatement pps = conIncapacidad.prepareStatement(sqlInsert);
			pps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Datos Guardados");
		} catch (SQLException el) { 
			el.printStackTrace();
			StringWriter errors = new StringWriter();
			el.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: "+ errors );
			//JOptionPane.showMessageDialog(null, el, "Error de conexion", JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
		}finally {
			try {
				conIncapacidad.close();
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


	@SuppressWarnings("unlikely-arg-type")
	public void mostrarDatosEmpleadoFiniquito(String  valor) {
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
		modelo.addColumn("APELLIDO PAT.");
		modelo.addColumn("APELLIDO MAT.");
		modelo.addColumn("PUESTO");
		modelo.addColumn("SALARIO");
		modelo.addColumn("UNIDAD RESPONSABLE");

		tableFiniquito.setModel(modelo);
		tableFiniquito.setBackground(Color.WHITE);

		JTableHeader th = new JTableHeader();
		Color colorSilverLight = new Color(176, 196, 222);
		Color colorNegro = new Color(0, 0, 0);
		th = tableFiniquito.getTableHeader();
		Font fuente = new Font("Arial", Font.BOLD, 14);
		th.setFont(fuente);
		th.setBackground(colorSilverLight);
		th.setForeground(colorNegro);

		TableColumnModel columnModel = tableFiniquito.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(100);
		columnModel.getColumn(1).setPreferredWidth(200);
		columnModel.getColumn(2).setPreferredWidth(200);
		columnModel.getColumn(3).setPreferredWidth(200);
		columnModel.getColumn(4).setPreferredWidth(300);
		columnModel.getColumn(5).setPreferredWidth(100);
		columnModel.getColumn(6).setPreferredWidth(300);

		String sqlSelect = "";
		if (valor.equals("")) {
			sqlSelect = "select CLAVE,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,CURP,RFC,SDI from dbo.empleados WHERE ELIMINAR_LOGICO='"
					+ 1 + "' and DBO.EMPLEADOS.ID_EJERCICIOS = '" + valor + "'";
		} else {
			sqlSelect = "SELECT DBO.EMPLEADOS.CLAVE,DBO.EMPLEADOS.NOMBRE,DBO.EMPLEADOS.APELLIDO_PATERNO,DBO.EMPLEADOS.APELLIDO_MATERNO,\r\n"
					+ "dbo.puestos.nombre_puesto,dbo.puestos.salario,DBO.DEPENDENCIAS.UNIDAD_RESPONSABLE \r\n"
					+ "from empleados\r\n"
					+ "left JOIN DBO.PUESTOS ON DBO.EMPLEADOS.ID_PUESTO = DBO.PUESTOS.no_puesto\r\n"
					+ "LEFT JOIN DBO.DEPENDENCIAS ON DBO.EMPLEADOS.ID_UNIDAD = DBO.DEPENDENCIAS.ID_UNIDAD\r\n"
					+ "LEFT JOIN DBO.EJERCICIOS ON DBO.EMPLEADOS.ID_EJERCICIOS = DBO.EJERCICIOS.ID_EJERCICIOS\r\n"
					+ "WHERE DBO.EMPLEADOS.id_ejercicios = '" + valor + "' and ELIMINAR_LOGICO='1' \r\n";//and dbo.empleados.clave=124
									System.out.println("sql finiquito: "+sqlSelect);
			Object datos[] = new String[12];
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

				rowSorter = new TableRowSorter(modelo);
				tableFiniquito.setRowSorter(rowSorter);
				tableFiniquito.setModel(modelo);
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

	}

	@SuppressWarnings("unlikely-arg-type")
	public void mostrarDatosEmpleadoFiniquitoFalso(String valor) {
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
		modeloFalso.addColumn("PUESTO");
		modeloFalso.addColumn("SALARIO");
		modeloFalso.addColumn("UNIDAD RESPONSABLE");
		modeloFalso.addColumn("ID PUESTO");
		modeloFalso.addColumn("TIPO NOMINA");
		modeloFalso.addColumn("SDI");
		modeloFalso.addColumn("FECHA INGRESO");
		modeloFalso.addColumn("ID TIPO NOMINA");
		modeloFalso.addColumn("LOCALIDAD");
		modeloFalso.addColumn("CIUDAD");
		modeloFalso.addColumn("ID UNIDAD");
		modeloFalso.addColumn("ID PUESTO");
		modeloFalso.addColumn("DIRECCION");
		modeloFalso.addColumn("CURP");
		modeloFalso.addColumn("RFC");
		modeloFalso.addColumn("REG IMSS");
		modeloFalso.addColumn("FECHA INGRESO");


		tableFiniquitoFalsa.setModel(modeloFalso);
		tableFiniquitoFalsa.setBackground(Color.WHITE);

		JTableHeader thFalsa = new JTableHeader();
		Color colorSilverLight = new Color(176, 196, 222);
		Color colorNegro = new Color(0, 0, 0);
		thFalsa = tableFiniquitoFalsa.getTableHeader();
		Font fuente = new Font("Arial", Font.BOLD, 14);
		thFalsa.setFont(fuente);
		thFalsa.setBackground(colorSilverLight);
		thFalsa.setForeground(colorNegro);

		TableColumnModel columnModelFalsa = tableFiniquitoFalsa.getColumnModel();
		columnModelFalsa.getColumn(0).setPreferredWidth(100);
		columnModelFalsa.getColumn(1).setPreferredWidth(200);
		columnModelFalsa.getColumn(2).setPreferredWidth(200);
		columnModelFalsa.getColumn(3).setPreferredWidth(200);
		columnModelFalsa.getColumn(4).setPreferredWidth(300);
		columnModelFalsa.getColumn(5).setPreferredWidth(100);
		columnModelFalsa.getColumn(6).setPreferredWidth(300);
		columnModelFalsa.getColumn(7).setPreferredWidth(100);
		columnModelFalsa.getColumn(8).setPreferredWidth(100);
		columnModelFalsa.getColumn(9).setPreferredWidth(100);
		columnModelFalsa.getColumn(10).setPreferredWidth(100);
		columnModelFalsa.getColumn(11).setPreferredWidth(100);
		columnModelFalsa.getColumn(12).setPreferredWidth(100);
		columnModelFalsa.getColumn(13).setPreferredWidth(100);
		columnModelFalsa.getColumn(14).setPreferredWidth(100);
		columnModelFalsa.getColumn(15).setPreferredWidth(100);
		columnModelFalsa.getColumn(16).setPreferredWidth(100);

		columnModelFalsa.getColumn(17).setPreferredWidth(100);
		columnModelFalsa.getColumn(18).setPreferredWidth(100);
		columnModelFalsa.getColumn(19).setPreferredWidth(100);
		columnModelFalsa.getColumn(20).setPreferredWidth(100);

		String sqlSelect = "";
		if (valor.equals("")) {
			sqlSelect = "select CLAVE,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,CURP,RFC,SDI from dbo.empleados WHERE ELIMINAR_LOGICO='"
					+ 1 + "' and DBO.EMPLEADOS.ID_EJERCICIOS = '" + valor + "'";
		} else {
			sqlSelect = "SELECT DBO.EMPLEADOS.CLAVE,DBO.EMPLEADOS.NOMBRE,DBO.EMPLEADOS.APELLIDO_PATERNO,DBO.EMPLEADOS.APELLIDO_MATERNO,\r\n"
					+ "dbo.puestos.nombre_puesto,dbo.puestos.salario,DBO.DEPENDENCIAS.UNIDAD_RESPONSABLE,dbo.puestos.no_puesto,dbo.ejercicios.nombre_ejercicio,dbo.empleados.sdi, \r\n"
					+" dbo.empleados.fecha_ingreso,dbo.ejercicios.id_ejercicios,dbo.empleados.localidad,dbo.empleados.ciudad,DBO.DEPENDENCIAS.id_unidad,dbo.puestos.no_puesto,dbo.empleados.direccion,\r\n"
					+" dbo.empleados.curp,dbo.empleados.rfc,dbo.empleados.registro_imss,dbo.empleados.fecha_ingreso\r\n"
					+ "from empleados\r\n"
					+ "left JOIN DBO.PUESTOS ON DBO.EMPLEADOS.ID_PUESTO = DBO.PUESTOS.no_puesto\r\n"
					+ "LEFT JOIN DBO.DEPENDENCIAS ON DBO.EMPLEADOS.ID_UNIDAD = DBO.DEPENDENCIAS.ID_UNIDAD\r\n"
					+ "LEFT JOIN DBO.EJERCICIOS ON DBO.EMPLEADOS.ID_EJERCICIOS = DBO.EJERCICIOS.ID_EJERCICIOS\r\n"
					+ "WHERE DBO.EMPLEADOS.id_ejercicios = '" + valor + "' and ELIMINAR_LOGICO='1' \r\n";//and dbo.empleados.clave=124
			//			System.out.println(sqlSelect);
			Object datosFalsa[] = new String[25];
			PoolNYCH nych = new PoolNYCH();
			Connection con = null;
			Statement st;
			ResultSet resultSetFalsa;
			try {
				con = nych.datasource.getConnection();
				st = con.createStatement();
				resultSetFalsa = st.executeQuery(sqlSelect);
				while (resultSetFalsa.next()) {
					datosFalsa[0] = resultSetFalsa.getString(1);
					datosFalsa[1] = resultSetFalsa.getString(2);
					datosFalsa[2] = resultSetFalsa.getString(3);
					datosFalsa[3] = resultSetFalsa.getString(4);
					datosFalsa[4] = resultSetFalsa.getString(5);
					datosFalsa[5] = resultSetFalsa.getString(6);
					datosFalsa[6] = resultSetFalsa.getString(7);
					datosFalsa[7] = resultSetFalsa.getString(8);
					datosFalsa[8] = resultSetFalsa.getString(9);
					datosFalsa[9] = resultSetFalsa.getString(10);
					datosFalsa[10] = resultSetFalsa.getString(11);
					datosFalsa[11] = resultSetFalsa.getString(12);
					datosFalsa[12] = resultSetFalsa.getString(13);
					datosFalsa[13] = resultSetFalsa.getString(14);
					datosFalsa[14] = resultSetFalsa.getString(15);
					datosFalsa[15] = resultSetFalsa.getString(16);
					datosFalsa[16] = resultSetFalsa.getString(17);

					datosFalsa[17] = resultSetFalsa.getString(18);
					datosFalsa[18] = resultSetFalsa.getString(19);
					datosFalsa[19] = resultSetFalsa.getString(20);
					datosFalsa[20] = resultSetFalsa.getString(21);
					modeloFalso.addRow(datosFalsa);
				}
				rowSorterFalso = new TableRowSorter(modeloFalso);
				tableFiniquitoFalsa.setRowSorter(rowSorterFalso);
				tableFiniquitoFalsa.setModel(modeloFalso);
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

	}


	public double calcularISR(){
		System.out.println("calcularISR");
		DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
		simbolos.setDecimalSeparator('.');
		DecimalFormat df = new DecimalFormat("####.##",simbolos);
		double resta = 0;
		double tasa = 0;
		double cuota = 0;
		double isr = 0;
		PoolNYCH nych = new PoolNYCH();
		Connection conSalario = null;
		Statement stSalario;
		ResultSet resultSetSalario;

		InternalFrameMovimientos mov =new InternalFrameMovimientos();
		ArrayList<Double> listaDatosISR = new ArrayList<Double>();
		listaDatosISR = mov.getListaTablaMensualISR();
		System.out.println("*********");
		for(int i = 0; i<listaDatosISR.size();i++){
			System.out.println("indiceTablaMensuaISR: "+ i + " |valorTablaMensuaISR: " + listaDatosISR.get(i));
		}
		System.out.println("*********");


		ArrayList<Double> listaSubsidioISR = new ArrayList<Double>();
		listaSubsidioISR = mov.getListaTablaMensualSubsidioISR();
		System.out.println("*********");
		for(int i = 0; i<listaSubsidioISR.size();i++){
			System.out.println("indiceTablaMensualSubsidioISR: "+ i + " |valorTablaMensualSubsidioISR: " + listaSubsidioISR.get(i));
		}
		System.out.println("*********");

		double cantidadMaximaParaSubsidioPorAño =  listaSubsidioISR.get(30);
		System.out.println("----------");
		System.out.println("Cantidad Maxima Para Subsidio Por Año En Curso: " + cantidadMaximaParaSubsidioPorAño);
		System.out.println("----------");

		int fila = tableFiniquito.getSelectedRow();
		if(fila>=0) {
			String clave = tableFiniquito.getValueAt(fila, 0).toString();
			String empleado = tableFiniquito.getValueAt(fila, 1).toString();
			String sal = tableFiniquito.getValueAt(fila, 5).toString();
			//			double salari =  Double.parseDouble(sal);
			System.out.println("clave: " + clave);
			System.out.println("empleado: " + empleado);
			//			System.out.println("Salario: " + salari);
			System.out.println("♣♣♣♣♣♣♣♣♣♣♣");


			double salario = Double.parseDouble(textFieldBaseGravableCalculoISR.getText());
			System.out.println("Salario: " + salario);

			if( salario > listaDatosISR.get(0)  && salario < listaDatosISR.get(1) ) {
				System.out.println("Cálculo para la cantidad de: " + salario);
				System.out.println("Limite Inferior:" + listaDatosISR.get(0));
				System.out.println("Limite Superior:" + listaDatosISR.get(1));
				resta = salario - listaDatosISR.get(0);
				tasa = resta * listaDatosISR.get(3)/100;
				System.out.println("porcentaje: " + listaDatosISR.get(3)+" %");
				cuota = tasa + listaDatosISR.get(2);
				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				if(salario>cantidadMaximaParaSubsidioPorAño) {
					System.out.println("sin subsidio");
					isr = cuota - listaSubsidioISR.get(32);
				}else {
					System.out.println("con subsidio");
					isr = cuota - listaSubsidioISR.get(2);
					System.out.println("subsidio para el salario: "+ salario +" -> "+listaSubsidioISR.get(2));
				}

				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				System.out.println("Resta(Salario - Limite Inferior): " + resta);
				System.out.println("tasa(Resta * Porcentaje): " + tasa);
				System.out.println("cuota(Tasa + Cuota Fija): " + cuota);
				System.out.println("Isr(cuota - subsidio): " + isr);
				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");


				textFieldMenosLimiteInfGravableCalculoISR.setText(String.valueOf(listaDatosISR.get(0)));
				double menosLimiteInf = Double.parseDouble(textFieldMenosLimiteInfGravableCalculoISR.getText());
				double baseTotal = salario-menosLimiteInf;

				textFieldTotalGravableCalculoISR.setText(String.valueOf(df.format(baseTotal)));

				textFieldPorlimiteInferiorGravableCalculoISR.setText(String.valueOf(listaDatosISR.get(3)));
				double porcentaje = Double.parseDouble(textFieldPorlimiteInferiorGravableCalculoISR.getText());
				double porcent = baseTotal*(porcentaje/100);

				textFieldSegundototalCalculoISR.setText(String.valueOf(df.format(porcent)));

				textFieldCuotaFijaGravableCalculoISR.setText(String.valueOf(listaDatosISR.get(2)));
				double cuotaF = Double.parseDouble(textFieldCuotaFijaGravableCalculoISR.getText());
				double cuotaFija = porcent+cuotaF;

				textFieldUltimoTotalGravableCalculoISR.setText(String.valueOf(df.format(cuotaFija)));

				textFieldVacioCalculoISR.setText(textFieldUltimoTotalGravableCalculoISR.getText());

				textFieldISRMontoLiquidacion.setText(textFieldVacioCalculoISR.getText());


				double subTotalMonto = Double.parseDouble(textFieldSubTotalMontoLiquidacion.getText());

				if(textFieldPrestamoMontoLiquidacion.getText().isEmpty()) {
					textFieldPrestamoMontoLiquidacion.setText("0");
				}
				double prestamoMonto = Double.parseDouble(textFieldPrestamoMontoLiquidacion.getText());
				double isrMonto = Double.parseDouble(textFieldISRMontoLiquidacion.getText());
				textFieldVacioMontoLiquidacion.setText(String.valueOf(df.format(subTotalMonto-prestamoMonto-isrMonto)));

			}


			if( salario > listaDatosISR.get(4)  && salario < listaDatosISR.get(5) ) {
				System.out.println("Cálculo para la cantidad de: " + salario);
				System.out.println("Limite Inferior:" + listaDatosISR.get(4));
				System.out.println("Limite Superior:" + listaDatosISR.get(5));
				resta = salario - listaDatosISR.get(4);
				tasa = resta * listaDatosISR.get(7)/100;
				System.out.println("porcentaje: " + listaDatosISR.get(7)+" %");
				cuota = tasa + listaDatosISR.get(6);
				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				if(salario>cantidadMaximaParaSubsidioPorAño) {
					System.out.println("sin subsidio");
					isr = cuota - listaSubsidioISR.get(32);
				}else {
					System.out.println("con subsidio");
					isr = cuota - listaSubsidioISR.get(2);
					System.out.println("subsidio para el salario: "+ salario +" -> "+listaSubsidioISR.get(2));
				}

				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				System.out.println("Resta(Salario - Limite Inferior): " + resta);
				System.out.println("tasa(Resta * Porcentaje): " + tasa);
				System.out.println("cuota(Tasa + Cuota Fija): " + cuota);
				System.out.println("Isr(cuota - subsidio): " + isr);
				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");


				textFieldMenosLimiteInfGravableCalculoISR.setText(String.valueOf(listaDatosISR.get(4)));
				double menosLimiteInf = Double.parseDouble(textFieldMenosLimiteInfGravableCalculoISR.getText());
				double baseTotal = salario-menosLimiteInf;

				textFieldTotalGravableCalculoISR.setText(String.valueOf(df.format(baseTotal)));

				textFieldPorlimiteInferiorGravableCalculoISR.setText(String.valueOf(listaDatosISR.get(7)));
				double porcentaje = Double.parseDouble(textFieldPorlimiteInferiorGravableCalculoISR.getText());
				double porcent = baseTotal*(porcentaje/100);

				textFieldSegundototalCalculoISR.setText(String.valueOf(df.format(porcent)));

				textFieldCuotaFijaGravableCalculoISR.setText(String.valueOf(listaDatosISR.get(6)));
				double cuotaF = Double.parseDouble(textFieldCuotaFijaGravableCalculoISR.getText());
				double cuotaFija = porcent+cuotaF;

				textFieldUltimoTotalGravableCalculoISR.setText(String.valueOf(df.format(cuotaFija)));

				textFieldVacioCalculoISR.setText(textFieldUltimoTotalGravableCalculoISR.getText());

				textFieldISRMontoLiquidacion.setText(textFieldVacioCalculoISR.getText());


				double subTotalMonto = Double.parseDouble(textFieldSubTotalMontoLiquidacion.getText());

				if(textFieldPrestamoMontoLiquidacion.getText().isEmpty()) {
					textFieldPrestamoMontoLiquidacion.setText("0");
				}
				double prestamoMonto = Double.parseDouble(textFieldPrestamoMontoLiquidacion.getText());
				double isrMonto = Double.parseDouble(textFieldISRMontoLiquidacion.getText());
				textFieldVacioMontoLiquidacion.setText(String.valueOf(df.format(subTotalMonto-prestamoMonto-isrMonto)));

			}//fin del if


			if( salario > listaDatosISR.get(8)  && salario < listaDatosISR.get(9) ) {
				System.out.println("Limite Inferior:" + listaDatosISR.get(8));
				System.out.println("Limite Superior:" + listaDatosISR.get(9));
				resta = salario - listaDatosISR.get(8);
				tasa = resta * listaDatosISR.get(11)/100;
				System.out.println("porcentaje: " + listaDatosISR.get(11)+" %");
				cuota = tasa + listaDatosISR.get(10);
				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				if(salario>cantidadMaximaParaSubsidioPorAño) {
					System.out.println("sin subsidio");
					isr = cuota - listaSubsidioISR.get(32);
				}else {
					System.out.println("con subsidio");
					isr = cuota - listaSubsidioISR.get(23);
					System.out.println("subsidio para el salario: "+ salario +" -> "+listaSubsidioISR.get(23));
				}

				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				System.out.println("Resta(Salario - Limite Inferior): " + resta);
				System.out.println("tasa(Resta * Porcentaje): " + tasa);
				System.out.println("cuota(Tasa + Cuota Fija): " + cuota);
				System.out.println("Isr(cuota - subsidio): " + isr);
				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");


				textFieldMenosLimiteInfGravableCalculoISR.setText(String.valueOf(listaDatosISR.get(8)));

				double menosLimiteInf = Double.parseDouble(textFieldMenosLimiteInfGravableCalculoISR.getText());
				double baseTotal = salario-menosLimiteInf;

				textFieldTotalGravableCalculoISR.setText(String.valueOf(df.format(baseTotal)));

				textFieldPorlimiteInferiorGravableCalculoISR.setText(String.valueOf(listaDatosISR.get(11)));
				double porcentaje = Double.parseDouble(textFieldPorlimiteInferiorGravableCalculoISR.getText());
				double porcent = baseTotal*(porcentaje/100);

				textFieldSegundototalCalculoISR.setText(String.valueOf(df.format(porcent)));

				textFieldCuotaFijaGravableCalculoISR.setText(String.valueOf(listaDatosISR.get(10)));
				double cuotaF = Double.parseDouble(textFieldCuotaFijaGravableCalculoISR.getText());
				double cuotaFija = porcent+cuotaF;

				textFieldUltimoTotalGravableCalculoISR.setText(String.valueOf(df.format(cuotaFija)));

				textFieldVacioCalculoISR.setText(textFieldUltimoTotalGravableCalculoISR.getText());

				textFieldISRMontoLiquidacion.setText(textFieldVacioCalculoISR.getText());


				double subTotalMonto = Double.parseDouble(textFieldSubTotalMontoLiquidacion.getText());

				if(textFieldPrestamoMontoLiquidacion.getText().isEmpty()) {
					textFieldPrestamoMontoLiquidacion.setText("0");
				}
				double prestamoMonto = Double.parseDouble(textFieldPrestamoMontoLiquidacion.getText());
				double isrMonto = Double.parseDouble(textFieldISRMontoLiquidacion.getText());
				textFieldVacioMontoLiquidacion.setText(String.valueOf(df.format(subTotalMonto-prestamoMonto-isrMonto)));

			}//fin del if

			if( salario > listaDatosISR.get(12)  && salario < listaDatosISR.get(13) ) {
				System.out.println("Limite Inferior:" + listaDatosISR.get(12));
				System.out.println("Limite Superior:" + listaDatosISR.get(13));
				resta = salario - listaDatosISR.get(12);
				tasa = resta * listaDatosISR.get(15)/100;
				System.out.println("porcentaje: " + listaDatosISR.get(15)+" %");
				cuota = tasa + listaDatosISR.get(14);
				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				if(salario>cantidadMaximaParaSubsidioPorAño) {
					System.out.println("sin subsidio");
					isr = cuota - listaSubsidioISR.get(32);
				}else {
					System.out.println("con subsidio");
					isr = cuota - listaSubsidioISR.get(32);
					System.out.println("subsidio para el salario: "+ salario +" -> "+listaSubsidioISR.get(32));
				}

				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				System.out.println("Resta(Salario - Limite Inferior): " + resta);
				System.out.println("tasa(Resta * Porcentaje): " + tasa);
				System.out.println("cuota(Tasa + Cuota Fija): " + cuota);
				System.out.println("Isr(cuota - subsidio): " + isr);
				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");


				textFieldMenosLimiteInfGravableCalculoISR.setText(String.valueOf(listaDatosISR.get(12)));

				double menosLimiteInf = Double.parseDouble(textFieldMenosLimiteInfGravableCalculoISR.getText());
				double baseTotal = salario-menosLimiteInf;

				textFieldTotalGravableCalculoISR.setText(String.valueOf(df.format(baseTotal)));

				textFieldPorlimiteInferiorGravableCalculoISR.setText(String.valueOf(listaDatosISR.get(15)));
				double porcentaje = Double.parseDouble(textFieldPorlimiteInferiorGravableCalculoISR.getText());
				double porcent = baseTotal*(porcentaje/100);

				textFieldSegundototalCalculoISR.setText(String.valueOf(df.format(porcent)));

				textFieldCuotaFijaGravableCalculoISR.setText(String.valueOf(listaDatosISR.get(14)));
				double cuotaF = Double.parseDouble(textFieldCuotaFijaGravableCalculoISR.getText());
				double cuotaFija = porcent+cuotaF;

				textFieldUltimoTotalGravableCalculoISR.setText(String.valueOf(df.format(cuotaFija)));

				textFieldVacioCalculoISR.setText(textFieldUltimoTotalGravableCalculoISR.getText());

				textFieldISRMontoLiquidacion.setText(textFieldVacioCalculoISR.getText());


				double subTotalMonto = Double.parseDouble(textFieldSubTotalMontoLiquidacion.getText());

				if(textFieldPrestamoMontoLiquidacion.getText().isEmpty()) {
					textFieldPrestamoMontoLiquidacion.setText("0");
				}
				double prestamoMonto = Double.parseDouble(textFieldPrestamoMontoLiquidacion.getText());
				double isrMonto = Double.parseDouble(textFieldISRMontoLiquidacion.getText());
				textFieldVacioMontoLiquidacion.setText(String.valueOf(df.format(subTotalMonto-prestamoMonto-isrMonto)));

			}//fin del if


			if( salario > listaDatosISR.get(16)  && salario < listaDatosISR.get(17) ) {
				System.out.println("Limite Inferior:" + listaDatosISR.get(16));
				System.out.println("Limite Superior:" + listaDatosISR.get(17));
				resta = salario - listaDatosISR.get(16);
				tasa = resta * listaDatosISR.get(19)/100;
				System.out.println("porcentaje: " + listaDatosISR.get(19)+" %");
				cuota = tasa + listaDatosISR.get(18);
				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				if(salario>cantidadMaximaParaSubsidioPorAño) {
					System.out.println("sin subsidio");
					isr = cuota - listaSubsidioISR.get(32);
				}else {
					System.out.println("con subsidio");
					isr = cuota - listaSubsidioISR.get(32);
					System.out.println("subsidio para el salario: "+ salario +" -> "+listaSubsidioISR.get(32));
				}

				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				System.out.println("Resta(Salario - Limite Inferior): " + resta);
				System.out.println("tasa(Resta * Porcentaje): " + tasa);
				System.out.println("cuota(Tasa + Cuota Fija): " + cuota);
				System.out.println("Isr(cuota - subsidio): " + isr);
				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");


				textFieldMenosLimiteInfGravableCalculoISR.setText(String.valueOf(listaDatosISR.get(16)));

				double menosLimiteInf = Double.parseDouble(textFieldMenosLimiteInfGravableCalculoISR.getText());
				double baseTotal = salario-menosLimiteInf;

				textFieldTotalGravableCalculoISR.setText(String.valueOf(df.format(baseTotal)));

				textFieldPorlimiteInferiorGravableCalculoISR.setText(String.valueOf(listaDatosISR.get(19)));
				double porcentaje = Double.parseDouble(textFieldPorlimiteInferiorGravableCalculoISR.getText());
				double porcent = baseTotal*(porcentaje/100);

				textFieldSegundototalCalculoISR.setText(String.valueOf(df.format(porcent)));

				textFieldCuotaFijaGravableCalculoISR.setText(String.valueOf(listaDatosISR.get(18)));
				double cuotaF = Double.parseDouble(textFieldCuotaFijaGravableCalculoISR.getText());
				double cuotaFija = porcent+cuotaF;

				textFieldUltimoTotalGravableCalculoISR.setText(String.valueOf(df.format(cuotaFija)));

				textFieldVacioCalculoISR.setText(textFieldUltimoTotalGravableCalculoISR.getText());

				textFieldISRMontoLiquidacion.setText(textFieldVacioCalculoISR.getText());


				double subTotalMonto = Double.parseDouble(textFieldSubTotalMontoLiquidacion.getText());

				if(textFieldPrestamoMontoLiquidacion.getText().isEmpty()) {
					textFieldPrestamoMontoLiquidacion.setText("0");
				}
				double prestamoMonto = Double.parseDouble(textFieldPrestamoMontoLiquidacion.getText());
				double isrMonto = Double.parseDouble(textFieldISRMontoLiquidacion.getText());
				textFieldVacioMontoLiquidacion.setText(String.valueOf(df.format(subTotalMonto-prestamoMonto-isrMonto)));

			}//fin del if

			if( salario > listaDatosISR.get(20)  && salario < listaDatosISR.get(21) ) {
				System.out.println("Limite Inferior:" + listaDatosISR.get(20));
				System.out.println("Limite Superior:" + listaDatosISR.get(21));
				resta = salario - listaDatosISR.get(20);
				tasa = resta * listaDatosISR.get(23)/100;
				System.out.println("porcentaje: " + listaDatosISR.get(23)+" %");
				cuota = tasa + listaDatosISR.get(22);
				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				if(salario>cantidadMaximaParaSubsidioPorAño) {
					System.out.println("sin subsidio");
					isr = cuota - listaSubsidioISR.get(32);
				}else {
					System.out.println("con subsidio");
					isr = cuota - listaSubsidioISR.get(32);
					System.out.println("subsidio para el salario: "+ salario +" -> "+listaSubsidioISR.get(32));
				}

				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				System.out.println("Resta(Salario - Limite Inferior): " + resta);
				System.out.println("tasa(Resta * Porcentaje): " + tasa);
				System.out.println("cuota(Tasa + Cuota Fija): " + cuota);
				System.out.println("Isr(cuota - subsidio): " + isr);
				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");


				textFieldMenosLimiteInfGravableCalculoISR.setText(String.valueOf(listaDatosISR.get(20)));

				double menosLimiteInf = Double.parseDouble(textFieldMenosLimiteInfGravableCalculoISR.getText());
				double baseTotal = salario-menosLimiteInf;

				textFieldTotalGravableCalculoISR.setText(String.valueOf(df.format(baseTotal)));

				textFieldPorlimiteInferiorGravableCalculoISR.setText(String.valueOf(listaDatosISR.get(23)));
				double porcentaje = Double.parseDouble(textFieldPorlimiteInferiorGravableCalculoISR.getText());
				double porcent = baseTotal*(porcentaje/100);

				textFieldSegundototalCalculoISR.setText(String.valueOf(df.format(porcent)));

				textFieldCuotaFijaGravableCalculoISR.setText(String.valueOf(listaDatosISR.get(22)));
				double cuotaF = Double.parseDouble(textFieldCuotaFijaGravableCalculoISR.getText());
				double cuotaFija = porcent+cuotaF;

				textFieldUltimoTotalGravableCalculoISR.setText(String.valueOf(df.format(cuotaFija)));

				textFieldVacioCalculoISR.setText(textFieldUltimoTotalGravableCalculoISR.getText());

				textFieldISRMontoLiquidacion.setText(textFieldVacioCalculoISR.getText());


				double subTotalMonto = Double.parseDouble(textFieldSubTotalMontoLiquidacion.getText());

				if(textFieldPrestamoMontoLiquidacion.getText().isEmpty()) {
					textFieldPrestamoMontoLiquidacion.setText("0");
				}
				double prestamoMonto = Double.parseDouble(textFieldPrestamoMontoLiquidacion.getText());
				double isrMonto = Double.parseDouble(textFieldISRMontoLiquidacion.getText());
				textFieldVacioMontoLiquidacion.setText(String.valueOf(df.format(subTotalMonto-prestamoMonto-isrMonto)));

			}//fin del if

			if( salario > listaDatosISR.get(24)  && salario < listaDatosISR.get(25) ) {
				System.out.println("Limite Inferior:" + listaDatosISR.get(24));
				System.out.println("Limite Superior:" + listaDatosISR.get(25));
				resta = salario - listaDatosISR.get(24);
				tasa = resta * listaDatosISR.get(27)/100;
				System.out.println("porcentaje: " + listaDatosISR.get(27)+" %");
				cuota = tasa + listaDatosISR.get(26);
				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				if(salario>cantidadMaximaParaSubsidioPorAño) {
					System.out.println("sin subsidio");
					isr = cuota - listaSubsidioISR.get(32);
				}else {
					System.out.println("con subsidio");
					isr = cuota - listaSubsidioISR.get(32);
					System.out.println("subsidio para el salario: "+ salario +" -> "+listaSubsidioISR.get(32));
				}

				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				System.out.println("Resta(Salario - Limite Inferior): " + resta);
				System.out.println("tasa(Resta * Porcentaje): " + tasa);
				System.out.println("cuota(Tasa + Cuota Fija): " + cuota);
				System.out.println("Isr(cuota - subsidio): " + isr);
				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");


				textFieldMenosLimiteInfGravableCalculoISR.setText(String.valueOf(listaDatosISR.get(24)));

				double menosLimiteInf = Double.parseDouble(textFieldMenosLimiteInfGravableCalculoISR.getText());
				double baseTotal = salario-menosLimiteInf;

				textFieldTotalGravableCalculoISR.setText(String.valueOf(df.format(baseTotal)));

				textFieldPorlimiteInferiorGravableCalculoISR.setText(String.valueOf(listaDatosISR.get(27)));
				double porcentaje = Double.parseDouble(textFieldPorlimiteInferiorGravableCalculoISR.getText());
				double porcent = baseTotal*(porcentaje/100);

				textFieldSegundototalCalculoISR.setText(String.valueOf(df.format(porcent)));

				textFieldCuotaFijaGravableCalculoISR.setText(String.valueOf(listaDatosISR.get(26)));
				double cuotaF = Double.parseDouble(textFieldCuotaFijaGravableCalculoISR.getText());
				double cuotaFija = porcent+cuotaF;

				textFieldUltimoTotalGravableCalculoISR.setText(String.valueOf(df.format(cuotaFija)));

				textFieldVacioCalculoISR.setText(textFieldUltimoTotalGravableCalculoISR.getText());

				textFieldISRMontoLiquidacion.setText(textFieldVacioCalculoISR.getText());


				double subTotalMonto = Double.parseDouble(textFieldSubTotalMontoLiquidacion.getText());

				if(textFieldPrestamoMontoLiquidacion.getText().isEmpty()) {
					textFieldPrestamoMontoLiquidacion.setText("0");
				}
				double prestamoMonto = Double.parseDouble(textFieldPrestamoMontoLiquidacion.getText());
				double isrMonto = Double.parseDouble(textFieldISRMontoLiquidacion.getText());
				textFieldVacioMontoLiquidacion.setText(String.valueOf(df.format(subTotalMonto-prestamoMonto-isrMonto)));

			}//fin del if

			if( salario > listaDatosISR.get(28)  && salario < listaDatosISR.get(29) ) {
				System.out.println("Limite Inferior:" + listaDatosISR.get(28));
				System.out.println("Limite Superior:" + listaDatosISR.get(29));
				resta = salario - listaDatosISR.get(28);
				tasa = resta * listaDatosISR.get(31)/100;
				System.out.println("porcentaje: " + listaDatosISR.get(31)+" %");
				cuota = tasa + listaDatosISR.get(30);
				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				if(salario>cantidadMaximaParaSubsidioPorAño) {
					System.out.println("sin subsidio");
					isr = cuota - listaSubsidioISR.get(32);
				}else {
					System.out.println("con subsidio");
					isr = cuota - listaSubsidioISR.get(32);
					System.out.println("subsidio para el salario: "+ salario +" -> "+listaSubsidioISR.get(32));
				}

				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				System.out.println("Resta(Salario - Limite Inferior): " + resta);
				System.out.println("tasa(Resta * Porcentaje): " + tasa);
				System.out.println("cuota(Tasa + Cuota Fija): " + cuota);
				System.out.println("Isr(cuota - subsidio): " + isr);
				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");


				textFieldMenosLimiteInfGravableCalculoISR.setText(String.valueOf(listaDatosISR.get(28)));

				double menosLimiteInf = Double.parseDouble(textFieldMenosLimiteInfGravableCalculoISR.getText());
				double baseTotal = salario-menosLimiteInf;

				textFieldTotalGravableCalculoISR.setText(String.valueOf(df.format(baseTotal)));

				textFieldPorlimiteInferiorGravableCalculoISR.setText(String.valueOf(listaDatosISR.get(31)));
				double porcentaje = Double.parseDouble(textFieldPorlimiteInferiorGravableCalculoISR.getText());
				double porcent = baseTotal*(porcentaje/100);

				textFieldSegundototalCalculoISR.setText(String.valueOf(df.format(porcent)));

				textFieldCuotaFijaGravableCalculoISR.setText(String.valueOf(listaDatosISR.get(30)));
				double cuotaF = Double.parseDouble(textFieldCuotaFijaGravableCalculoISR.getText());
				double cuotaFija = porcent+cuotaF;

				textFieldUltimoTotalGravableCalculoISR.setText(String.valueOf(df.format(cuotaFija)));

				textFieldVacioCalculoISR.setText(textFieldUltimoTotalGravableCalculoISR.getText());

				textFieldISRMontoLiquidacion.setText(textFieldVacioCalculoISR.getText());


				double subTotalMonto = Double.parseDouble(textFieldSubTotalMontoLiquidacion.getText());

				if(textFieldPrestamoMontoLiquidacion.getText().isEmpty()) {
					textFieldPrestamoMontoLiquidacion.setText("0");
				}
				double prestamoMonto = Double.parseDouble(textFieldPrestamoMontoLiquidacion.getText());
				double isrMonto = Double.parseDouble(textFieldISRMontoLiquidacion.getText());
				textFieldVacioMontoLiquidacion.setText(String.valueOf(df.format(subTotalMonto-prestamoMonto-isrMonto)));

			}//fin del if

			if( salario > listaDatosISR.get(32)  && salario < listaDatosISR.get(33) ) {
				System.out.println("Limite Inferior:" + listaDatosISR.get(22));
				System.out.println("Limite Superior:" + listaDatosISR.get(33));
				resta = salario - listaDatosISR.get(32);
				tasa = resta * listaDatosISR.get(35)/100;
				System.out.println("porcentaje: " + listaDatosISR.get(35)+" %");
				cuota = tasa + listaDatosISR.get(34);
				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				if(salario>cantidadMaximaParaSubsidioPorAño) {
					System.out.println("sin subsidio");
					isr = cuota - listaSubsidioISR.get(32);
				}else {
					System.out.println("con subsidio");
					isr = cuota - listaSubsidioISR.get(32);
					System.out.println("subsidio para el salario: "+ salario +" -> "+listaSubsidioISR.get(32));
				}

				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				System.out.println("Resta(Salario - Limite Inferior): " + resta);
				System.out.println("tasa(Resta * Porcentaje): " + tasa);
				System.out.println("cuota(Tasa + Cuota Fija): " + cuota);
				System.out.println("Isr(cuota - subsidio): " + isr);
				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");


				textFieldMenosLimiteInfGravableCalculoISR.setText(String.valueOf(listaDatosISR.get(32)));

				double menosLimiteInf = Double.parseDouble(textFieldMenosLimiteInfGravableCalculoISR.getText());
				double baseTotal = salario-menosLimiteInf;

				textFieldTotalGravableCalculoISR.setText(String.valueOf(df.format(baseTotal)));

				textFieldPorlimiteInferiorGravableCalculoISR.setText(String.valueOf(listaDatosISR.get(35)));
				double porcentaje = Double.parseDouble(textFieldPorlimiteInferiorGravableCalculoISR.getText());
				double porcent = baseTotal*(porcentaje/100);

				textFieldSegundototalCalculoISR.setText(String.valueOf(df.format(porcent)));

				textFieldCuotaFijaGravableCalculoISR.setText(String.valueOf(listaDatosISR.get(34)));
				double cuotaF = Double.parseDouble(textFieldCuotaFijaGravableCalculoISR.getText());
				double cuotaFija = porcent+cuotaF;

				textFieldUltimoTotalGravableCalculoISR.setText(String.valueOf(df.format(cuotaFija)));

				textFieldVacioCalculoISR.setText(textFieldUltimoTotalGravableCalculoISR.getText());

				textFieldISRMontoLiquidacion.setText(textFieldVacioCalculoISR.getText());


				double subTotalMonto = Double.parseDouble(textFieldSubTotalMontoLiquidacion.getText());

				if(textFieldPrestamoMontoLiquidacion.getText().isEmpty()) {
					textFieldPrestamoMontoLiquidacion.setText("0");
				}
				double prestamoMonto = Double.parseDouble(textFieldPrestamoMontoLiquidacion.getText());
				double isrMonto = Double.parseDouble(textFieldISRMontoLiquidacion.getText());
				textFieldVacioMontoLiquidacion.setText(String.valueOf(df.format(subTotalMonto-prestamoMonto-isrMonto)));

			}//fin del if

			if( salario > listaDatosISR.get(36)  && salario < listaDatosISR.get(37) ) {
				System.out.println("Limite Inferior:" + listaDatosISR.get(36));
				System.out.println("Limite Superior:" + listaDatosISR.get(37));
				resta = salario - listaDatosISR.get(36);
				tasa = resta * listaDatosISR.get(39)/100;
				System.out.println("porcentaje: " + listaDatosISR.get(39)+" %");
				cuota = tasa + listaDatosISR.get(38);
				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				if(salario>cantidadMaximaParaSubsidioPorAño) {
					System.out.println("sin subsidio");
					isr = cuota - listaSubsidioISR.get(32);
				}else {
					System.out.println("con subsidio");
					isr = cuota - listaSubsidioISR.get(32);
					System.out.println("subsidio para el salario: "+ salario +" -> "+listaSubsidioISR.get(32));
				}

				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				System.out.println("Resta(Salario - Limite Inferior): " + resta);
				System.out.println("tasa(Resta * Porcentaje): " + tasa);
				System.out.println("cuota(Tasa + Cuota Fija): " + cuota);
				System.out.println("Isr(cuota - subsidio): " + isr);
				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");


				textFieldMenosLimiteInfGravableCalculoISR.setText(String.valueOf(listaDatosISR.get(36)));

				double menosLimiteInf = Double.parseDouble(textFieldMenosLimiteInfGravableCalculoISR.getText());
				double baseTotal = salario-menosLimiteInf;

				textFieldTotalGravableCalculoISR.setText(String.valueOf(df.format(baseTotal)));

				textFieldPorlimiteInferiorGravableCalculoISR.setText(String.valueOf(listaDatosISR.get(39)));
				double porcentaje = Double.parseDouble(textFieldPorlimiteInferiorGravableCalculoISR.getText());
				double porcent = baseTotal*(porcentaje/100);

				textFieldSegundototalCalculoISR.setText(String.valueOf(df.format(porcent)));

				textFieldCuotaFijaGravableCalculoISR.setText(String.valueOf(listaDatosISR.get(38)));
				double cuotaF = Double.parseDouble(textFieldCuotaFijaGravableCalculoISR.getText());
				double cuotaFija = porcent+cuotaF;

				textFieldUltimoTotalGravableCalculoISR.setText(String.valueOf(df.format(cuotaFija)));

				textFieldVacioCalculoISR.setText(textFieldUltimoTotalGravableCalculoISR.getText());

				textFieldISRMontoLiquidacion.setText(textFieldVacioCalculoISR.getText());


				double subTotalMonto = Double.parseDouble(textFieldSubTotalMontoLiquidacion.getText());

				if(textFieldPrestamoMontoLiquidacion.getText().isEmpty()) {
					textFieldPrestamoMontoLiquidacion.setText("0");
				}
				double prestamoMonto = Double.parseDouble(textFieldPrestamoMontoLiquidacion.getText());
				double isrMonto = Double.parseDouble(textFieldISRMontoLiquidacion.getText());
				textFieldVacioMontoLiquidacion.setText(String.valueOf(df.format(subTotalMonto-prestamoMonto-isrMonto)));

			}//fin del if

			if( salario > listaDatosISR.get(40) ) {
				System.out.println("Limite Inferior:" + listaDatosISR.get(40));
				resta = salario - listaDatosISR.get(40);
				tasa = resta * listaDatosISR.get(43)/100;
				System.out.println("porcentaje: " + listaDatosISR.get(43)+" %");
				cuota = tasa + listaDatosISR.get(42);
				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				if(salario>cantidadMaximaParaSubsidioPorAño) {
					System.out.println("sin subsidio");
					isr = cuota - listaSubsidioISR.get(32);
				}else {
					System.out.println("con subsidio");
					isr = cuota - listaSubsidioISR.get(32);
					System.out.println("subsidio para el salario: "+ salario +" -> "+listaSubsidioISR.get(32));
				}

				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");
				System.out.println("Resta(Salario - Limite Inferior): " + resta);
				System.out.println("tasa(Resta * Porcentaje): " + tasa);
				System.out.println("cuota(Tasa + Cuota Fija): " + cuota);
				System.out.println("Isr(cuota - subsidio): " + isr);
				System.out.println("♣♣♣♣♣♣♣♣♣♣♣");


				textFieldMenosLimiteInfGravableCalculoISR.setText(String.valueOf(listaDatosISR.get(40)));

				double menosLimiteInf = Double.parseDouble(textFieldMenosLimiteInfGravableCalculoISR.getText());
				double baseTotal = salario-menosLimiteInf;

				textFieldTotalGravableCalculoISR.setText(String.valueOf(df.format(baseTotal)));

				textFieldPorlimiteInferiorGravableCalculoISR.setText(String.valueOf(listaDatosISR.get(43)));
				double porcentaje = Double.parseDouble(textFieldPorlimiteInferiorGravableCalculoISR.getText());
				double porcent = baseTotal*(porcentaje/100);

				textFieldSegundototalCalculoISR.setText(String.valueOf(df.format(porcent)));

				textFieldCuotaFijaGravableCalculoISR.setText(String.valueOf(listaDatosISR.get(42)));
				double cuotaF = Double.parseDouble(textFieldCuotaFijaGravableCalculoISR.getText());
				double cuotaFija = porcent+cuotaF;

				textFieldUltimoTotalGravableCalculoISR.setText(String.valueOf(df.format(cuotaFija)));

				textFieldVacioCalculoISR.setText(textFieldUltimoTotalGravableCalculoISR.getText());

				textFieldISRMontoLiquidacion.setText(textFieldVacioCalculoISR.getText());


				double subTotalMonto = Double.parseDouble(textFieldSubTotalMontoLiquidacion.getText());

				if(textFieldPrestamoMontoLiquidacion.getText().isEmpty()) {
					textFieldPrestamoMontoLiquidacion.setText("0");
				}
				double prestamoMonto = Double.parseDouble(textFieldPrestamoMontoLiquidacion.getText());
				double isrMonto = Double.parseDouble(textFieldISRMontoLiquidacion.getText());
				textFieldVacioMontoLiquidacion.setText(String.valueOf(df.format(subTotalMonto-prestamoMonto-isrMonto)));

			}//fin del if

		}
		return isr;
	}


	public void windowOpened(ActionEvent e) {
		final long SLEEP_TIME = 1 * 1000;
		SwingWorker<Void, Void> mySwingWorker = new SwingWorker<Void, Void>() {
			protected Void doInBackground() throws Exception {
				//

				//				mostrarDatosEmpleadoFiniquito(lblTipoNominaOculta.getText());
				//				mostrarDatosEmpleadoFiniquitoFalso(lblTipoNominaOculta.getText());

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


	private void setJTexFieldChangedDiasTrabajados(JTextField txt)
	{
		DocumentListener documentListener = new DocumentListener() {

			public void changedUpdate(DocumentEvent documentEvent) {
				printItDiasTrabajados(documentEvent);
			}

			public void insertUpdate(DocumentEvent documentEvent) {
				printItDiasTrabajados(documentEvent);
			}

			public void removeUpdate(DocumentEvent documentEvent) {
				printItDiasTrabajados(documentEvent);
			}
		};
		txt.getDocument().addDocumentListener(documentListener);

	}

	private void printItDiasTrabajados(DocumentEvent documentEvent) {
		DocumentEvent.EventType type = documentEvent.getType();

		if (type.equals(DocumentEvent.EventType.CHANGE))
		{

		}
		else if (type.equals(DocumentEvent.EventType.INSERT))
		{
			txtEjemploJTextFieldChangedDiasTrabajados();
		}
		else if (type.equals(DocumentEvent.EventType.REMOVE))
		{
			txtEjemploJTextFieldChangedDiasTrabajados();
		}
	}

	private void txtEjemploJTextFieldChangedDiasTrabajados()
	{
		textFieldDiasTrabajadosLiquidacion.setBackground(null);
		textFieldSalarioMontoLiquidacion.setBackground(null);
		textFieldAyuDesMontoLiquidacion.setBackground(null);
		//Copiar el contenido del jtextfield al jlabel
		//textFieldPropAguiLiquidacion.setText(textFieldDiasTrabajadosLiquidacion.getText());

		DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
		simbolos.setDecimalSeparator('.');
		DecimalFormat df = new DecimalFormat("####.##", simbolos);

		double sdi = Double.parseDouble(textFieldSDILiquidacion.getText());
		System.out.println("sdi: " + sdi);

		int longitudDiasTrabajados = textFieldDiasTrabajadosLiquidacion.getText().length();

		//		RestrictedTextField restricted = new RestrictedTextField(textFieldDiasTrabajadosLiquidacion);
		//restricted.setLimit(5);

		if(longitudDiasTrabajados<1) {
			double diasTrabaj = 0;
			System.out.println("dias trabajados: " + diasTrabaj);

			double proporcionAguinaldo = diasTrabaj*40/365;
			textFieldPropAguiLiquidacion.setText(String.valueOf(df.format(proporcionAguinaldo)));
			System.out.println("proporcionAguinaldo: " + proporcionAguinaldo);

			double montoAguinaldo = sdi*proporcionAguinaldo;
			textFieldPropAguiMontoLiquidacion.setText(String.valueOf(df.format(montoAguinaldo)));
			System.out.println("montoAguinaldo: " + montoAguinaldo);

			double vacGozadas = Double.parseDouble(textFieldVacGozadasLiquidacion.getText());
			double proporcionVacaciones = diasTrabaj*20/365-vacGozadas;
			textFieldPropVacaLiquidacion.setText(String.valueOf(df.format(proporcionVacaciones)));
			System.out.println("La proporcion de las vacaciones en dias es: "+proporcionVacaciones);

			double primaVacPag = Double.parseDouble(textFieldPrimaVacPagadaLiquidacion.getText());
			double primaVacPagResultado = diasTrabaj*20/365-primaVacPag;
			textFieldPrimaVacacionalLiquidacion.setText(String.valueOf(df.format(primaVacPagResultado)));
			System.out.println("primaVacPagResultado: " + primaVacPagResultado);

			double montoVacaciones = sdi*proporcionVacaciones;
			textFieldPropVacMontoLiquidacion.setText(String.valueOf(df.format(montoVacaciones)));
			System.out.println("montoVacaciones: " + montoVacaciones);

			double primaVacioneeMonto = sdi*primaVacPagResultado*0.3;
			textFieldPrimVavMontoLiquidacion.setText(String.valueOf(df.format(primaVacioneeMonto)));
			System.out.println("primaVacioneeMonto: " + primaVacioneeMonto);

			double salarioProp = Double.parseDouble(textFieldSalarioLiquidacion.getText());
			double salarioMontoLiquidacion = sdi*salarioProp;
			textFieldSalarioMontoLiquidacion.setText(String.valueOf(df.format(salarioMontoLiquidacion)));


			double ayudaProp = Double.parseDouble(textFieldAyudaADespensaLiquidacion.getText());
			double ayudaMontoLiquidadacion = 498.82/14*ayudaProp;
			textFieldAyuDesMontoLiquidacion.setText(String.valueOf(df.format(ayudaMontoLiquidadacion)));

			double primaAnti = Double.parseDouble(textFieldPrimaAntigLiquidacion.getText());
			double pAntig = sdi*primaAnti;
			textFieldPrimaAntiMontoLiquidacion.setText(String.valueOf(df.format(pAntig)));

			if(textFieldDctoIncapMontoLiquidacion.getText().isEmpty()) {
				textFieldDctoIncapMontoLiquidacion.setText("0");
			}
			double incapacidad = Double.parseDouble(textFieldDctoIncapMontoLiquidacion.getText());


			if(textFieldIndemizacionMontoLiquidacion.getText().isEmpty()) {
				textFieldIndemizacionMontoLiquidacion.setText("0");
			}
			double indemnizacion = Double.parseDouble(textFieldIndemizacionMontoLiquidacion.getText());


			double subTotal = montoAguinaldo+montoVacaciones+primaVacioneeMonto+salarioMontoLiquidacion+ayudaMontoLiquidadacion-incapacidad+pAntig+indemnizacion;
			textFieldSubTotalMontoLiquidacion.setText(String.valueOf(df.format(subTotal)));
			System.out.println("subtotalSET: "+montoAguinaldo+" + "+montoVacaciones+" + "+primaVacioneeMonto+" + "+salarioMontoLiquidacion+" + "+ayudaMontoLiquidadacion+" - "+incapacidad+"  + "+pAntig+ " + " +indemnizacion);
			System.out.println("subtotal: "+ subTotal);

			//set impuesto
			textFieldAguiGravaImpuesto.setText(String.valueOf(df.format(montoAguinaldo)));
			textFieldPrimaVacGravaImpuesto.setText(String.valueOf(df.format(primaVacioneeMonto)));
			textFieldVacacionesGravaImpuesto.setText(String.valueOf(df.format(montoVacaciones)));
			textFieldPrimaAntigGravaImpuesto.setText(String.valueOf(df.format(pAntig)));
			//textFieldIndemGravaImpuesto.setText("$ -);

			double primaAntiGravada = Double.parseDouble(textFieldPrimaAntigGravaImpuesto.getText());
			if(textFieldIndemGravaImpuesto.getText().isEmpty()){
				textFieldIndemGravaImpuesto.setText("0");
			}else {
				textFieldIndemGravaImpuesto.getText();
			}
			double primaIndemGravada = Double.parseDouble(textFieldIndemGravaImpuesto.getText());

			textFieldVacioGravaImpuesto.setText(String.valueOf(df.format(primaAntiGravada+primaIndemGravada)));

			textFieldAguiExentoImpuesto.setText(String.valueOf(df.format(88.36*30)));
			textFieldPrimaVacacExentoImpuesto.setText(String.valueOf(df.format((88.36*15)/2)));
			textFieldIndemExentoImpuesto.setText(String.valueOf(df.format((88.36*90)*12)));

			double VacAux = Double.parseDouble(textFieldVacacionesGravaImpuesto.getText());
			textFieldVacacAuxImpuesto.setText(String.valueOf(df.format(VacAux)));

			double aguiAux=0;
			double primaVacAux =0;
			double vacacionesAux=0;
			double salarioAux=0;
			double primaAntigAux=0;
			double indemAux =0;
			if(textFieldAguiAuxImpuesto.getText().isEmpty()){
				aguiAux = Double.parseDouble("0");
			}else {
				aguiAux = Double.parseDouble(textFieldAguiAuxImpuesto.getText());
			}

			if(textFieldPrimaVacacAuxImpuesto.getText().isEmpty()){
				primaVacAux = Double.parseDouble("0");
			}else {
				primaVacAux = Double.parseDouble(textFieldPrimaVacacAuxImpuesto.getText());
			}

			if(textFieldVacacAuxImpuesto.getText().isEmpty()){
				vacacionesAux = Double.parseDouble("0");
			}else {
				vacacionesAux = Double.parseDouble(textFieldVacacAuxImpuesto.getText());
			}

			if(textFieldSalarioAuxImpuesto.getText().isEmpty()){
				salarioAux = Double.parseDouble("0");
			}else {
				salarioAux = Double.parseDouble(textFieldSalarioAuxImpuesto.getText());
			}

			if(textFieldPrimaAntigAuxImpuesto.getText().isEmpty()){
				primaAntigAux = Double.parseDouble("0");
			}else {
				primaAntigAux = Double.parseDouble(textFieldPrimaAntigAuxImpuesto.getText());
			}

			if(textFieldIndemAuxImpuesto.getText().isEmpty()){
				indemAux = Double.parseDouble("0");
			}else {
				indemAux = Double.parseDouble(textFieldIndemAuxImpuesto.getText());
			}

			double totalImpuesto = aguiAux+primaVacAux+vacacionesAux+salarioAux+primaAntigAux+indemAux;
			System.out.println("Total impuesto: "+ totalImpuesto);
			textFieldVacioAuxImpuesto.setText(String.valueOf(df.format(totalImpuesto)));


			//calculo isr
			textFieldBaseGravableCalculoISR.setText(String.valueOf(df.format(totalImpuesto)));

			calcularISR();

		}else {
			double diasTrabaj = Double.parseDouble(textFieldDiasTrabajadosLiquidacion.getText());
			System.out.println("dias trabajados: " + diasTrabaj);

			double proporcionAguinaldo = diasTrabaj*40/365;
			textFieldPropAguiLiquidacion.setText(String.valueOf(df.format(proporcionAguinaldo)));
			System.out.println("proporcionAguinaldo: " + proporcionAguinaldo);

			double montoAguinaldo = sdi*proporcionAguinaldo;
			textFieldPropAguiMontoLiquidacion.setText(String.valueOf(df.format(montoAguinaldo)));
			System.out.println("montoAguinaldo: " + montoAguinaldo);

			double vacGozadas = Double.parseDouble(textFieldVacGozadasLiquidacion.getText());
			double proporcionVacaciones = diasTrabaj*20/365-vacGozadas;
			textFieldPropVacaLiquidacion.setText(String.valueOf(df.format(proporcionVacaciones)));
			System.out.println("La proporcion de las vacaciones en dias es: "+proporcionVacaciones);

			double primaVacPag = Double.parseDouble(textFieldPrimaVacPagadaLiquidacion.getText());
			double primaVacPagResultado = diasTrabaj*20/365-primaVacPag;
			textFieldPrimaVacacionalLiquidacion.setText(String.valueOf(df.format(primaVacPagResultado)));
			System.out.println("primaVacPagResultado: " + primaVacPagResultado);

			double montoVacaciones = sdi*proporcionVacaciones;
			textFieldPropVacMontoLiquidacion.setText(String.valueOf(df.format(montoVacaciones)));
			System.out.println("montoVacaciones: " + montoVacaciones);

			double primaVacioneeMonto = sdi*primaVacPagResultado*0.3;
			textFieldPrimVavMontoLiquidacion.setText(String.valueOf(df.format(primaVacioneeMonto)));
			System.out.println("primaVacioneeMonto: " + primaVacioneeMonto);

			double salarioProp = Double.parseDouble(textFieldSalarioLiquidacion.getText());
			double salarioMontoLiquidacion = sdi*salarioProp;
			textFieldSalarioMontoLiquidacion.setText(String.valueOf(df.format(salarioMontoLiquidacion)));

			double ayudaProp = Double.parseDouble(textFieldAyudaADespensaLiquidacion.getText());
			double ayudaMontoLiquidadacion = 498.82/14*ayudaProp;
			textFieldAyuDesMontoLiquidacion.setText(String.valueOf(df.format(ayudaMontoLiquidadacion)));

			double primaAnti = Double.parseDouble(textFieldPrimaAntigLiquidacion.getText());
			double pAntig = sdi*primaAnti;
			textFieldPrimaAntiMontoLiquidacion.setText(String.valueOf(df.format(pAntig)));

			if(textFieldDctoIncapMontoLiquidacion.getText().isEmpty()) {
				textFieldDctoIncapMontoLiquidacion.setText("0");
			}
			double incapacidad = Double.parseDouble(textFieldDctoIncapMontoLiquidacion.getText());


			if(textFieldIndemizacionMontoLiquidacion.getText().isEmpty()) {
				textFieldIndemizacionMontoLiquidacion.setText("0");
			}
			double indemnizacion = Double.parseDouble(textFieldIndemizacionMontoLiquidacion.getText());


			double subTotal = montoAguinaldo+montoVacaciones+primaVacioneeMonto+salarioMontoLiquidacion+ayudaMontoLiquidadacion-incapacidad+pAntig+indemnizacion;
			textFieldSubTotalMontoLiquidacion.setText(String.valueOf(df.format(subTotal)));
			System.out.println("subtotalSET: "+montoAguinaldo+" + "+montoVacaciones+" + "+primaVacioneeMonto+" + "+salarioMontoLiquidacion+" + "+ayudaMontoLiquidadacion+" - "+incapacidad+"  + "+pAntig+ " + " +indemnizacion);
			System.out.println("subtotal: "+ subTotal);

			//set impuesto
			textFieldAguiGravaImpuesto.setText(String.valueOf(df.format(montoAguinaldo)));
			textFieldPrimaVacGravaImpuesto.setText(String.valueOf(df.format(primaVacioneeMonto)));
			textFieldVacacionesGravaImpuesto.setText(String.valueOf(df.format(montoVacaciones)));
			textFieldPrimaAntigGravaImpuesto.setText(String.valueOf(df.format(pAntig)));
			//textFieldIndemGravaImpuesto.setText("$ -);

			double primaAntiGravada = Double.parseDouble(textFieldPrimaAntigGravaImpuesto.getText());
			if(textFieldIndemGravaImpuesto.getText().isEmpty()){
				textFieldIndemGravaImpuesto.setText("0");
			}else {
				textFieldIndemGravaImpuesto.getText();
			}
			double primaIndemGravada = Double.parseDouble(textFieldIndemGravaImpuesto.getText());

			textFieldVacioGravaImpuesto.setText(String.valueOf(df.format(primaAntiGravada+primaIndemGravada)));

			textFieldAguiExentoImpuesto.setText(String.valueOf(df.format(88.36*30)));
			textFieldPrimaVacacExentoImpuesto.setText(String.valueOf(df.format((88.36*15)/2)));
			textFieldIndemExentoImpuesto.setText(String.valueOf(df.format((88.36*90)*12)));

			double VacAux = Double.parseDouble(textFieldVacacionesGravaImpuesto.getText());
			textFieldVacacAuxImpuesto.setText(String.valueOf(df.format(VacAux)));

			double aguiAux=0;
			double primaVacAux =0;
			double vacacionesAux=0;
			double salarioAux=0;
			double primaAntigAux=0;
			double indemAux =0;
			if(textFieldAguiAuxImpuesto.getText().isEmpty()){
				aguiAux = Double.parseDouble("0");
			}else {
				aguiAux = Double.parseDouble(textFieldAguiAuxImpuesto.getText());
			}

			if(textFieldPrimaVacacAuxImpuesto.getText().isEmpty()){
				primaVacAux = Double.parseDouble("0");
			}else {
				primaVacAux = Double.parseDouble(textFieldPrimaVacacAuxImpuesto.getText());
			}

			if(textFieldVacacAuxImpuesto.getText().isEmpty()){
				vacacionesAux = Double.parseDouble("0");
			}else {
				vacacionesAux = Double.parseDouble(textFieldVacacAuxImpuesto.getText());
			}

			if(textFieldSalarioAuxImpuesto.getText().isEmpty()){
				salarioAux = Double.parseDouble("0");
			}else {
				salarioAux = Double.parseDouble(textFieldSalarioAuxImpuesto.getText());
			}

			if(textFieldPrimaAntigAuxImpuesto.getText().isEmpty()){
				primaAntigAux = Double.parseDouble("0");
			}else {
				primaAntigAux = Double.parseDouble(textFieldPrimaAntigAuxImpuesto.getText());
			}

			if(textFieldIndemAuxImpuesto.getText().isEmpty()){
				indemAux = Double.parseDouble("0");
			}else {
				indemAux = Double.parseDouble(textFieldIndemAuxImpuesto.getText());
			}

			double totalImpuesto = aguiAux+primaVacAux+vacacionesAux+salarioAux+primaAntigAux+indemAux;
			System.out.println("Total impuesto: "+ totalImpuesto);
			textFieldVacioAuxImpuesto.setText(String.valueOf(df.format(totalImpuesto)));


			//calculo isr
			textFieldBaseGravableCalculoISR.setText(String.valueOf(df.format(totalImpuesto)));

			calcularISR();

		}
	}

	private void setJTexFieldChangedSDI(JTextField txt)
	{
		DocumentListener documentListener = new DocumentListener() {

			public void changedUpdate(DocumentEvent documentEvent) {
				printItSDI(documentEvent);
			}

			public void insertUpdate(DocumentEvent documentEvent) {
				printItSDI(documentEvent);
			}

			public void removeUpdate(DocumentEvent documentEvent) {
				printItSDI(documentEvent);
			}
		};
		txt.getDocument().addDocumentListener(documentListener);

	}

	private void printItSDI(DocumentEvent documentEvent) {
		DocumentEvent.EventType type = documentEvent.getType();

		if (type.equals(DocumentEvent.EventType.CHANGE))
		{

		}
		else if (type.equals(DocumentEvent.EventType.INSERT))
		{
			txtEjemploJTextFieldChangedSDI();
		}
		else if (type.equals(DocumentEvent.EventType.REMOVE))
		{
			txtEjemploJTextFieldChangedSDI();
		}
	}

	private void txtEjemploJTextFieldChangedSDI(){
		textFieldSalarioMontoLiquidacion.setBackground(null);
		//Copiar el contenido del jtextfield al jlabel
		//textFieldPropAguiLiquidacion.setText(textFieldDiasTrabajadosLiquidacion.getText());

		DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
		simbolos.setDecimalSeparator('.');
		DecimalFormat df = new DecimalFormat("####.##", simbolos);

		int longitudSDI = textFieldSDILiquidacion.getText().length();
		System.out.println("longitudSDI: " + longitudSDI);

		//		int fila = tableFiniquito.getSelectedRow();
		//		String sdi = tableFiniquitoFalsa.getValueAt(fila, 9).toString();
		//		double sdiPor30 = Double.parseDouble(sdi)*30.4;

		if(longitudSDI<1) {
			int sdi = 0;
			System.out.println("dias trabajados: " + sdi);

			double salarioProp = Double.parseDouble(textFieldSalarioLiquidacion.getText());
			double salarioMontoLiquidacion = sdi*salarioProp;
			textFieldSalarioMontoLiquidacion.setText(String.valueOf(df.format(salarioMontoLiquidacion)));

			textFieldSDIAuxLiquidacion.setText(String.valueOf(df.format(sdi*30.4)));

			double primaAnti = Double.parseDouble(textFieldPrimaAntigLiquidacion.getText());
			textFieldPrimaAntiMontoLiquidacion.setText(String.valueOf(df.format(sdi*primaAnti)));


			///////
			double diasTrabaj = Double.parseDouble(textFieldDiasTrabajadosLiquidacion.getText());
			System.out.println("dias trabajados: " + diasTrabaj);

			double vacGozadas = Double.parseDouble(textFieldVacGozadasLiquidacion.getText());
			double proporcionVacaciones = diasTrabaj*20/365-vacGozadas;
			textFieldPropVacaLiquidacion.setText(String.valueOf(df.format(proporcionVacaciones)));
			System.out.println("La proporcion de las vacaciones en dias es: "+proporcionVacaciones);

			double primaVacPag = Double.parseDouble(textFieldPrimaVacPagadaLiquidacion.getText());
			double primaVacPagResultado = diasTrabaj*20/365-primaVacPag;
			textFieldPrimaVacacionalLiquidacion.setText(String.valueOf(df.format(primaVacPagResultado)));
			System.out.println("primaVacPagResultado: " + primaVacPagResultado);


			double proporcionAguinaldo = diasTrabaj*40/365;
			textFieldPropAguiLiquidacion.setText(String.valueOf(df.format(proporcionAguinaldo)));
			System.out.println("proporcionAguinaldo: " + proporcionAguinaldo);

			double montoAguinaldo = sdi*proporcionAguinaldo;
			textFieldPropAguiMontoLiquidacion.setText(String.valueOf(df.format(montoAguinaldo)));
			System.out.println("montoAguinaldo: " + montoAguinaldo);

			double montoVacaciones = sdi*proporcionVacaciones;
			textFieldPropVacMontoLiquidacion.setText(String.valueOf(df.format(montoVacaciones)));
			System.out.println("montoVacaciones: " + montoVacaciones);

			double primaVacioneeMonto = sdi*primaVacPagResultado*0.3;
			textFieldPrimVavMontoLiquidacion.setText(String.valueOf(df.format(primaVacioneeMonto)));
			System.out.println("primaVacioneeMonto: " + primaVacioneeMonto);

			double ayudaProp = Double.parseDouble(textFieldAyudaADespensaLiquidacion.getText());
			double ayudaMontoLiquidadacion = 498.82/14*ayudaProp;
			textFieldAyuDesMontoLiquidacion.setText(String.valueOf(df.format(ayudaMontoLiquidadacion)));

			double primaAntig = Double.parseDouble(textFieldPrimaAntigLiquidacion.getText());
			double pAntig = sdi*primaAntig;
			textFieldPrimaAntiMontoLiquidacion.setText(String.valueOf(df.format(pAntig)));


			if(textFieldDctoIncapMontoLiquidacion.getText().isEmpty()) {
				textFieldDctoIncapMontoLiquidacion.setText("0");
			}
			double incapacidad = Double.parseDouble(textFieldDctoIncapMontoLiquidacion.getText());


			if(textFieldIndemizacionMontoLiquidacion.getText().isEmpty()) {
				textFieldIndemizacionMontoLiquidacion.setText("0");
			}
			double indemnizacion = Double.parseDouble(textFieldIndemizacionMontoLiquidacion.getText());


			double subTotal = montoAguinaldo+montoVacaciones+primaVacioneeMonto+salarioMontoLiquidacion+ayudaMontoLiquidadacion-incapacidad+pAntig+indemnizacion;
			textFieldSubTotalMontoLiquidacion.setText(String.valueOf(df.format(subTotal)));
			System.out.println("subtotalSET: "+montoAguinaldo+" + "+montoVacaciones+" + "+primaVacioneeMonto+" + "+salarioMontoLiquidacion+" + "+ayudaMontoLiquidadacion+" - "+incapacidad+"  + "+pAntig+ " + " +indemnizacion);
			System.out.println("subtotal: "+ subTotal);

			calcularISR();

		}else {

			double sdi = Double.parseDouble(textFieldSDILiquidacion.getText());
			System.out.println("sdi: " + sdi);

			double salarioProp = Double.parseDouble(textFieldSalarioLiquidacion.getText());
			double salarioMontoLiquidacion = sdi*salarioProp;
			textFieldSalarioMontoLiquidacion.setText(String.valueOf(df.format(salarioMontoLiquidacion)));

			textFieldSDIAuxLiquidacion.setText(String.valueOf(df.format(sdi*30.4)));

			double primaAnti = Double.parseDouble(textFieldPrimaAntigLiquidacion.getText());
			textFieldPrimaAntiMontoLiquidacion.setText(String.valueOf(df.format(sdi*primaAnti)));


			double diasTrabaj = Double.parseDouble(textFieldDiasTrabajadosLiquidacion.getText());
			System.out.println("dias trabajados: " + diasTrabaj);

			double vacGozadas = Double.parseDouble(textFieldVacGozadasLiquidacion.getText());
			double proporcionVacaciones = diasTrabaj*20/365-vacGozadas;
			textFieldPropVacaLiquidacion.setText(String.valueOf(df.format(proporcionVacaciones)));
			System.out.println("La proporcion de las vacaciones en dias es: "+proporcionVacaciones);

			double primaVacPag = Double.parseDouble(textFieldPrimaVacPagadaLiquidacion.getText());
			double primaVacPagResultado = diasTrabaj*20/365-primaVacPag;
			textFieldPrimaVacacionalLiquidacion.setText(String.valueOf(df.format(primaVacPagResultado)));
			System.out.println("primaVacPagResultado: " + primaVacPagResultado);


			double proporcionAguinaldo = diasTrabaj*40/365;
			textFieldPropAguiLiquidacion.setText(String.valueOf(df.format(proporcionAguinaldo)));
			System.out.println("proporcionAguinaldo: " + proporcionAguinaldo);

			double montoAguinaldo = sdi*proporcionAguinaldo;
			textFieldPropAguiMontoLiquidacion.setText(String.valueOf(df.format(montoAguinaldo)));
			System.out.println("montoAguinaldo: " + montoAguinaldo);

			double montoVacaciones = sdi*proporcionVacaciones;
			textFieldPropVacMontoLiquidacion.setText(String.valueOf(df.format(montoVacaciones)));
			System.out.println("montoVacaciones: " + montoVacaciones);

			double primaVacioneeMonto = sdi*primaVacPagResultado*0.3;
			textFieldPrimVavMontoLiquidacion.setText(String.valueOf(df.format(primaVacioneeMonto)));
			System.out.println("primaVacioneeMonto: " + primaVacioneeMonto);

			double ayudaProp = Double.parseDouble(textFieldAyudaADespensaLiquidacion.getText());
			double ayudaMontoLiquidadacion = 498.82/14*ayudaProp;
			textFieldAyuDesMontoLiquidacion.setText(String.valueOf(df.format(ayudaMontoLiquidadacion)));

			double primaAntig = Double.parseDouble(textFieldPrimaAntigLiquidacion.getText());
			double pAntig = sdi*primaAntig;
			textFieldPrimaAntiMontoLiquidacion.setText(String.valueOf(df.format(pAntig)));


			if(textFieldDctoIncapMontoLiquidacion.getText().isEmpty()) {
				textFieldDctoIncapMontoLiquidacion.setText("0");
			}
			double incapacidad = Double.parseDouble(textFieldDctoIncapMontoLiquidacion.getText());


			if(textFieldIndemizacionMontoLiquidacion.getText().isEmpty()) {
				textFieldIndemizacionMontoLiquidacion.setText("0");
			}
			double indemnizacion = Double.parseDouble(textFieldIndemizacionMontoLiquidacion.getText());


			double subTotal = montoAguinaldo+montoVacaciones+primaVacioneeMonto+salarioMontoLiquidacion+ayudaMontoLiquidadacion+incapacidad-pAntig+indemnizacion;
			textFieldSubTotalMontoLiquidacion.setText(String.valueOf(df.format(subTotal)));
			System.out.println("subtotalSET: "+montoAguinaldo+" + "+montoVacaciones+" + "+primaVacioneeMonto+" + "+salarioMontoLiquidacion+" + "+ayudaMontoLiquidadacion+" - "+incapacidad+"  + "+pAntig+ " + " +indemnizacion);
			System.out.println("subtotal: "+ subTotal);

			calcularISR();



		}
	}//fin del metodo SDI

	private void setJTexFieldChangedAD(JTextField txt)
	{
		DocumentListener documentListener = new DocumentListener() {

			public void changedUpdate(DocumentEvent documentEvent) {
				printItAD(documentEvent);
			}

			public void insertUpdate(DocumentEvent documentEvent) {
				printItAD(documentEvent);
			}

			public void removeUpdate(DocumentEvent documentEvent) {
				printItAD(documentEvent);
			}
		};
		txt.getDocument().addDocumentListener(documentListener);

	}

	private void printItAD(DocumentEvent documentEvent) {
		DocumentEvent.EventType type = documentEvent.getType();

		if (type.equals(DocumentEvent.EventType.CHANGE))
		{

		}
		else if (type.equals(DocumentEvent.EventType.INSERT))
		{
			txtEjemploJTextFieldChangedAD();
		}
		else if (type.equals(DocumentEvent.EventType.REMOVE))
		{
			txtEjemploJTextFieldChangedAD();
		}
	}

	private void txtEjemploJTextFieldChangedAD(){
		textFieldAyuDesMontoLiquidacion.setBackground(null);
		//Copiar el contenido del jtextfield al jlabel
		//textFieldPropAguiLiquidacion.setText(textFieldDiasTrabajadosLiquidacion.getText());

		DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
		simbolos.setDecimalSeparator('.');
		DecimalFormat df = new DecimalFormat("####.##", simbolos);

		int longitudAD = textFieldAyudaADespensaLiquidacion.getText().length();
		System.out.println("longitudAD: " + longitudAD);

		if(longitudAD<1) {
			int ad = 0;
			double ayd = 498.82/14*ad;
			textFieldAyuDesMontoLiquidacion.setText(String.valueOf(df.format(ayd)));
			System.out.println("dias trabajados: " + ayd);

			calcularISR();

		}else {
			double ayu = Double.parseDouble(textFieldAyudaADespensaLiquidacion.getText());
			double ad = 498.82/14*ayu;
			textFieldAyuDesMontoLiquidacion.setText(String.valueOf(df.format(ad)));
			System.out.println("ad: " + ad);

			double sdi = Double.parseDouble(textFieldSDILiquidacion.getText());
			System.out.println("sdi: " + sdi);

			double primaAnti = Double.parseDouble(textFieldPrimaAntigLiquidacion.getText());
			textFieldPrimaAntiMontoLiquidacion.setText(String.valueOf(df.format(sdi*primaAnti)));

			double diasTrabaj = Double.parseDouble(textFieldDiasTrabajadosLiquidacion.getText());
			System.out.println("dias trabajados: " + diasTrabaj);

			double salarioProp = Double.parseDouble(textFieldSalarioLiquidacion.getText());
			double salarioMontoLiquidacion = sdi*salarioProp;
			textFieldSalarioMontoLiquidacion.setText(String.valueOf(df.format(salarioMontoLiquidacion)));

			double vacGozadas = Double.parseDouble(textFieldVacGozadasLiquidacion.getText());
			double proporcionVacaciones = diasTrabaj*20/365-vacGozadas;
			textFieldPropVacaLiquidacion.setText(String.valueOf(df.format(proporcionVacaciones)));
			System.out.println("La proporcion de las vacaciones en dias es: "+proporcionVacaciones);

			double primaVacPag = Double.parseDouble(textFieldPrimaVacPagadaLiquidacion.getText());
			double primaVacPagResultado = diasTrabaj*20/365-primaVacPag;
			textFieldPrimaVacacionalLiquidacion.setText(String.valueOf(df.format(primaVacPagResultado)));
			System.out.println("primaVacPagResultado: " + primaVacPagResultado);


			double proporcionAguinaldo = diasTrabaj*40/365;
			textFieldPropAguiLiquidacion.setText(String.valueOf(df.format(proporcionAguinaldo)));
			System.out.println("proporcionAguinaldo: " + proporcionAguinaldo);

			double montoAguinaldo = sdi*proporcionAguinaldo;
			textFieldPropAguiMontoLiquidacion.setText(String.valueOf(df.format(montoAguinaldo)));
			System.out.println("montoAguinaldo: " + montoAguinaldo);

			double montoVacaciones = sdi*proporcionVacaciones;
			textFieldPropVacMontoLiquidacion.setText(String.valueOf(df.format(montoVacaciones)));
			System.out.println("montoVacaciones: " + montoVacaciones);

			double primaVacioneeMonto = sdi*primaVacPagResultado*0.3;
			textFieldPrimVavMontoLiquidacion.setText(String.valueOf(df.format(primaVacioneeMonto)));
			System.out.println("primaVacioneeMonto: " + primaVacioneeMonto);

			double ayudaProp = Double.parseDouble(textFieldAyudaADespensaLiquidacion.getText());
			double ayudaMontoLiquidadacion = 498.82/14*ayudaProp;
			textFieldAyuDesMontoLiquidacion.setText(String.valueOf(df.format(ayudaMontoLiquidadacion)));

			double primaAntig = Double.parseDouble(textFieldPrimaAntigLiquidacion.getText());
			double pAntig = sdi*primaAntig;
			textFieldPrimaAntiMontoLiquidacion.setText(String.valueOf(df.format(pAntig)));


			if(textFieldDctoIncapMontoLiquidacion.getText().isEmpty()) {
				textFieldDctoIncapMontoLiquidacion.setText("0");
			}
			double incapacidad = Double.parseDouble(textFieldDctoIncapMontoLiquidacion.getText());


			if(textFieldIndemizacionMontoLiquidacion.getText().isEmpty()) {
				textFieldIndemizacionMontoLiquidacion.setText("0");
			}
			double indemnizacion = Double.parseDouble(textFieldIndemizacionMontoLiquidacion.getText());


			double subTotal = montoAguinaldo+montoVacaciones+primaVacioneeMonto+salarioMontoLiquidacion+ayudaMontoLiquidadacion-incapacidad+pAntig+indemnizacion;
			textFieldSubTotalMontoLiquidacion.setText(String.valueOf(df.format(subTotal)));
			System.out.println("subtotalSET: "+montoAguinaldo+" + "+montoVacaciones+" + "+primaVacioneeMonto+" + "+salarioMontoLiquidacion+" + "+ayudaMontoLiquidadacion+" - "+incapacidad+"  + "+pAntig+ " + " +indemnizacion);
			System.out.println("subtotal: "+ subTotal);

			calcularISR();
		}
	}//fin del metodo AYUDA

	private void setJTexFieldChangedPrimaAntig(JTextField txt){
		DocumentListener documentListener = new DocumentListener() {

			public void changedUpdate(DocumentEvent documentEvent) {
				printItPrimaAntig(documentEvent);
			}

			public void insertUpdate(DocumentEvent documentEvent) {
				printItPrimaAntig(documentEvent);
			}

			public void removeUpdate(DocumentEvent documentEvent) {
				printItPrimaAntig(documentEvent);
			}
		};
		txt.getDocument().addDocumentListener(documentListener);

	}

	private void printItPrimaAntig(DocumentEvent documentEvent) {
		DocumentEvent.EventType type = documentEvent.getType();

		if (type.equals(DocumentEvent.EventType.CHANGE))
		{

		}
		else if (type.equals(DocumentEvent.EventType.INSERT))
		{
			txtEjemploJTextFieldChangedprintItPrimaAntig();
		}
		else if (type.equals(DocumentEvent.EventType.REMOVE))
		{
			txtEjemploJTextFieldChangedprintItPrimaAntig();
		}
	}

	private void txtEjemploJTextFieldChangedprintItPrimaAntig(){
		textFieldPrimaAntigLiquidacion.setBackground(null);

		DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
		simbolos.setDecimalSeparator('.');
		DecimalFormat df = new DecimalFormat("####.##", simbolos);

		int longitudPrimaAntig = textFieldPrimaAntigLiquidacion.getText().length();
		System.out.println("longitudPrimaAntig: " + longitudPrimaAntig);

		if(longitudPrimaAntig<1) {
			double sdi = Double.parseDouble(textFieldSDILiquidacion.getText());
			System.out.println("sdi: " + sdi);

			int pa = 0;
			System.out.println("dias trabajados: " + pa);


			//			double pag = Double.parseDouble(textFieldPrimaAntigLiquidacion.getText());
			textFieldPrimaAntiMontoLiquidacion.setText(String.valueOf(df.format(sdi*pa)));

			//			double diasTrabaj = 0;
			//			System.out.println("dias trabajados: " + diasTrabaj);
			//
			//			double salarioProp = Double.parseDouble(textFieldSalarioLiquidacion.getText());
			//			double salarioMontoLiquidacion = sdi*salarioProp;
			//			textFieldSalarioMontoLiquidacion.setText(String.valueOf(df.format(salarioMontoLiquidacion)));
			//			
			//			double vacGozadas = Double.parseDouble(textFieldVacGozadasLiquidacion.getText());
			//			double proporcionVacaciones = diasTrabaj*20/365-vacGozadas;
			//			textFieldPropVacaLiquidacion.setText(String.valueOf(df.format(proporcionVacaciones)));
			//			System.out.println("La proporcion de las vacaciones en dias es: "+proporcionVacaciones);
			//
			//			double primaVacPag = Double.parseDouble(textFieldPrimaVacPagadaLiquidacion.getText());
			//			double primaVacPagResultado = diasTrabaj*20/365-primaVacPag;
			//			textFieldPrimaVacacionalLiquidacion.setText(String.valueOf(df.format(primaVacPagResultado)));
			//			System.out.println("primaVacPagResultado: " + primaVacPagResultado);
			//
			//
			//			double proporcionAguinaldo = diasTrabaj*40/365;
			//			textFieldPropAguiLiquidacion.setText(String.valueOf(df.format(proporcionAguinaldo)));
			//			System.out.println("proporcionAguinaldo: " + proporcionAguinaldo);
			//
			//			double montoAguinaldo = sdi*proporcionAguinaldo;
			//			textFieldPropAguiMontoLiquidacion.setText(String.valueOf(df.format(montoAguinaldo)));
			//			System.out.println("montoAguinaldo: " + montoAguinaldo);
			//			
			//			double montoVacaciones = sdi*proporcionVacaciones;
			//			textFieldPropVacMontoLiquidacion.setText(String.valueOf(df.format(montoVacaciones)));
			//			System.out.println("montoVacaciones: " + montoVacaciones);
			//
			//			double primaVacioneeMonto = sdi*primaVacPagResultado*0.3;
			//			textFieldPrimVavMontoLiquidacion.setText(String.valueOf(df.format(primaVacioneeMonto)));
			//			System.out.println("primaVacioneeMonto: " + primaVacioneeMonto);
			//			
			//			double ayudaProp = Double.parseDouble(textFieldAyudaADespensaLiquidacion.getText());
			//			double ayudaMontoLiquidadacion = 498.82/14*ayudaProp;
			//			textFieldAyuDesMontoLiquidacion.setText(String.valueOf(df.format(ayudaMontoLiquidadacion)));
			//			
			//			double primaAntig = Double.parseDouble(textFieldPrimaAntigLiquidacion.getText());
			//			double pAntig = sdi*primaAntig;
			//			textFieldPrimaAntiMontoLiquidacion.setText(String.valueOf(df.format(pAntig)));
			//
			//			
			//			double subTotal = montoAguinaldo+montoVacaciones+primaVacioneeMonto+salarioMontoLiquidacion+ayudaMontoLiquidadacion+pAntig;
			//			textFieldSubTotalMontoLiquidacion.setText(String.valueOf(df.format(subTotal)));
			//			System.out.println("subtotalSET: "+montoAguinaldo+" + "+montoVacaciones+" + "+primaVacioneeMonto+" + "+salarioMontoLiquidacion+" + "+ayudaMontoLiquidadacion+" + "+pAntig);

			calcularISR();

		}else {

			double sdi = Double.parseDouble(textFieldSDILiquidacion.getText());
			System.out.println("sdi: " + sdi);


			double primaAnti = Double.parseDouble(textFieldPrimaAntigLiquidacion.getText());
			textFieldPrimaAntiMontoLiquidacion.setText(String.valueOf(df.format(sdi*primaAnti)));

			double diasTrabaj = Double.parseDouble(textFieldDiasTrabajadosLiquidacion.getText());
			System.out.println("dias trabajados: " + diasTrabaj);

			double salarioProp = Double.parseDouble(textFieldSalarioLiquidacion.getText());
			double salarioMontoLiquidacion = sdi*salarioProp;
			textFieldSalarioMontoLiquidacion.setText(String.valueOf(df.format(salarioMontoLiquidacion)));

			double vacGozadas = Double.parseDouble(textFieldVacGozadasLiquidacion.getText());
			double proporcionVacaciones = diasTrabaj*20/365-vacGozadas;
			textFieldPropVacaLiquidacion.setText(String.valueOf(df.format(proporcionVacaciones)));
			System.out.println("La proporcion de las vacaciones en dias es: "+proporcionVacaciones);

			double primaVacPag = Double.parseDouble(textFieldPrimaVacPagadaLiquidacion.getText());
			double primaVacPagResultado = diasTrabaj*20/365-primaVacPag;
			textFieldPrimaVacacionalLiquidacion.setText(String.valueOf(df.format(primaVacPagResultado)));
			System.out.println("primaVacPagResultado: " + primaVacPagResultado);


			double proporcionAguinaldo = diasTrabaj*40/365;
			textFieldPropAguiLiquidacion.setText(String.valueOf(df.format(proporcionAguinaldo)));
			System.out.println("proporcionAguinaldo: " + proporcionAguinaldo);

			double montoAguinaldo = sdi*proporcionAguinaldo;
			textFieldPropAguiMontoLiquidacion.setText(String.valueOf(df.format(montoAguinaldo)));
			System.out.println("montoAguinaldo: " + montoAguinaldo);

			double montoVacaciones = sdi*proporcionVacaciones;
			textFieldPropVacMontoLiquidacion.setText(String.valueOf(df.format(montoVacaciones)));
			System.out.println("montoVacaciones: " + montoVacaciones);

			double primaVacioneeMonto = sdi*primaVacPagResultado*0.3;
			textFieldPrimVavMontoLiquidacion.setText(String.valueOf(df.format(primaVacioneeMonto)));
			System.out.println("primaVacioneeMonto: " + primaVacioneeMonto);

			double ayudaProp = Double.parseDouble(textFieldAyudaADespensaLiquidacion.getText());
			double ayudaMontoLiquidadacion = 498.82/14*ayudaProp;
			textFieldAyuDesMontoLiquidacion.setText(String.valueOf(df.format(ayudaMontoLiquidadacion)));

			double primaAntig = Double.parseDouble(textFieldPrimaAntigLiquidacion.getText());
			double pAntig = sdi*primaAntig;
			textFieldPrimaAntiMontoLiquidacion.setText(String.valueOf(df.format(pAntig)));

			if(textFieldDctoIncapMontoLiquidacion.getText().isEmpty()) {
				textFieldDctoIncapMontoLiquidacion.setText("0");
			}
			double incapacidad = Double.parseDouble(textFieldDctoIncapMontoLiquidacion.getText());


			if(textFieldIndemizacionMontoLiquidacion.getText().isEmpty()) {
				textFieldIndemizacionMontoLiquidacion.setText("0");
			}
			double indemnizacion = Double.parseDouble(textFieldIndemizacionMontoLiquidacion.getText());


			double subTotal = montoAguinaldo+montoVacaciones+primaVacioneeMonto+salarioMontoLiquidacion+ayudaMontoLiquidadacion-incapacidad+pAntig+indemnizacion;
			textFieldSubTotalMontoLiquidacion.setText(String.valueOf(df.format(subTotal)));
			System.out.println("subtotalSET: "+montoAguinaldo+" + "+montoVacaciones+" + "+primaVacioneeMonto+" + "+salarioMontoLiquidacion+" + "+ayudaMontoLiquidadacion+" - "+incapacidad+"  + "+pAntig+ " + " +indemnizacion);
			System.out.println("subtotal: "+ subTotal);

			calcularISR();

		}
	}//fin del metodo

	private void setJTexFieldChangedPrestamo(JTextField txt){
		DocumentListener documentListener = new DocumentListener() {

			public void changedUpdate(DocumentEvent documentEvent) {
				printItPrestamo(documentEvent);
			}

			public void insertUpdate(DocumentEvent documentEvent) {
				printItPrestamo(documentEvent);
			}

			public void removeUpdate(DocumentEvent documentEvent) {
				printItPrestamo(documentEvent);
			}
		};
		txt.getDocument().addDocumentListener(documentListener);

	}

	private void printItPrestamo(DocumentEvent documentEvent) {
		DocumentEvent.EventType type = documentEvent.getType();

		if (type.equals(DocumentEvent.EventType.CHANGE))
		{

		}
		else if (type.equals(DocumentEvent.EventType.INSERT))
		{
			txtEjemploJTextFieldChangedPrestamo();
		}
		else if (type.equals(DocumentEvent.EventType.REMOVE))
		{
			txtEjemploJTextFieldChangedPrestamo();
		}
	}

	private void txtEjemploJTextFieldChangedPrestamo(){
		textFieldPrestamoMontoLiquidacion.setBackground(null);

		DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
		simbolos.setDecimalSeparator('.');
		DecimalFormat df = new DecimalFormat("####.##", simbolos);

		int longitudPrestamos = textFieldPrestamoMontoLiquidacion.getText().length();
		System.out.println("longitudPrestamos: " + longitudPrestamos);

		if(longitudPrestamos<=0) {

			double prestamoMonto = 0;
			double subTotalMonto = Double.parseDouble(textFieldSubTotalMontoLiquidacion.getText());
			double isrMonto = Double.parseDouble(textFieldISRMontoLiquidacion.getText());
			textFieldVacioMontoLiquidacion.setText(String.valueOf(df.format(subTotalMonto-prestamoMonto-isrMonto)));

		}else {

			double subTotalMonto = Double.parseDouble(textFieldSubTotalMontoLiquidacion.getText());
			double prestamoMonto = Double.parseDouble(textFieldPrestamoMontoLiquidacion.getText());
			double isrMonto = Double.parseDouble(textFieldISRMontoLiquidacion.getText());
			textFieldVacioMontoLiquidacion.setText(String.valueOf(df.format(subTotalMonto-prestamoMonto-isrMonto)));


		}
	}//fin del metodo


	private void setJTexFieldChangedIncapacidad(JTextField txt){
		DocumentListener documentListener = new DocumentListener() {

			public void changedUpdate(DocumentEvent documentEvent) {
				printItIncapacidad(documentEvent);
			}

			public void insertUpdate(DocumentEvent documentEvent) {
				printItIncapacidad(documentEvent);
			}

			public void removeUpdate(DocumentEvent documentEvent) {
				printItIncapacidad(documentEvent);
			}
		};
		txt.getDocument().addDocumentListener(documentListener);

	}

	private void printItIncapacidad(DocumentEvent documentEvent) {
		DocumentEvent.EventType type = documentEvent.getType();

		if (type.equals(DocumentEvent.EventType.CHANGE))
		{

		}
		else if (type.equals(DocumentEvent.EventType.INSERT))
		{
			txtEjemploJTextFieldIncapacidad();
		}
		else if (type.equals(DocumentEvent.EventType.REMOVE))
		{
			txtEjemploJTextFieldIncapacidad();
		}
	}

	private void txtEjemploJTextFieldIncapacidad(){
		textFieldDctoIncapMontoLiquidacion.setBackground(null);

		DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
		simbolos.setDecimalSeparator('.');
		DecimalFormat df = new DecimalFormat("####.##", simbolos);

		int longitudIncapacidad = textFieldDctoIncapMontoLiquidacion.getText().length();
		System.out.println("longitudIncapacidad: " + longitudIncapacidad);

		if(longitudIncapacidad<1) {
			double diasTrabaj = Double.parseDouble(textFieldDiasTrabajadosLiquidacion.getText());
			//						System.out.println("diasTrabaj: "+ diasTrabaj);

			double proporcionAguinaldo = diasTrabaj*40/365;
			textFieldPropAguiLiquidacion.setText(String.valueOf(df.format(proporcionAguinaldo)));
			//						System.out.println("La proporcion del aguinaldo en dias es: "+df.format(dt));

			double vacGozadas = Double.parseDouble(textFieldVacGozadasLiquidacion.getText());
			double proporcionVacaciones = diasTrabaj*20/365-vacGozadas;
			textFieldPropVacaLiquidacion.setText(String.valueOf(df.format(proporcionVacaciones)));
			//						System.out.println("La proporcion de las vacaciones en dias es: "+propVac);

			double primaVacPag = Double.parseDouble(textFieldPrimaVacPagadaLiquidacion.getText());
			double primaVacPagResultado = diasTrabaj*20/365-primaVacPag;
			textFieldPrimaVacacionalLiquidacion.setText(String.valueOf(df.format(primaVacPagResultado)));
			//						System.out.println("La proporcion del prima vacacional en dias es: "+primaVacPagResultado);

			double sdi = Double.parseDouble(textFieldSDILiquidacion.getText());

			double montoAguinaldo = sdi*proporcionAguinaldo;
			textFieldPropAguiMontoLiquidacion.setText(String.valueOf(df.format(montoAguinaldo)));
			System.out.println("Aguinaldo Monto: " + montoAguinaldo);

			double montoVacaciones = sdi*proporcionVacaciones;
			textFieldPropVacMontoLiquidacion.setText(String.valueOf(df.format(montoVacaciones)));
			System.out.println("Vacaciones Monto: " + montoVacaciones);

			double primaVacioneeMonto = sdi*primaVacPagResultado*0.3;
			textFieldPrimVavMontoLiquidacion.setText(String.valueOf(df.format(primaVacioneeMonto)));
			System.out.println("Prima Vacacional Monto: " + primaVacioneeMonto);

			double salarioProp = Double.parseDouble(textFieldSalarioLiquidacion.getText());
			double salarioMontoLiquidacion = sdi*salarioProp;
			textFieldSalarioMontoLiquidacion.setText(String.valueOf(df.format(salarioMontoLiquidacion)));
			System.out.println("Salario Diario Monto: " + salarioMontoLiquidacion);

			double ayudaProp = Double.parseDouble(textFieldAyudaADespensaLiquidacion.getText());
			double ayudaMontoLiquidadacion = 498.82/14*ayudaProp;
			textFieldAyuDesMontoLiquidacion.setText(String.valueOf(df.format(ayudaMontoLiquidadacion)));
			System.out.println("Ayuda Despensa Monto: " + ayudaMontoLiquidadacion);

			double primaAntiguedad = Double.parseDouble(textFieldPrimaAntigLiquidacion.getText());
			double primaAntiguedadAux = sdi*primaAntiguedad;
			textFieldPrimaAntiMontoLiquidacion.setText(String.valueOf(df.format(primaAntiguedadAux)));
			System.out.println("Prima Antiguedad Monto: " + primaAntiguedadAux);


			double incapacidad = 0;
			System.out.println("incapacidad: "+incapacidad);

			double indemnizacion =  Double.parseDouble(textFieldIndemizacionMontoLiquidacion.getText());
			System.out.println("indemnizacion: "+indemnizacion);


			double subTotal = montoAguinaldo+montoVacaciones+primaVacioneeMonto+salarioMontoLiquidacion+ayudaMontoLiquidadacion-incapacidad+primaAntiguedadAux+indemnizacion;
			textFieldSubTotalMontoLiquidacion.setText(String.valueOf(df.format(subTotal)));
			System.out.println("subtotalSET: "+montoAguinaldo+" + "+montoVacaciones+" + "+primaVacioneeMonto+" + "+salarioMontoLiquidacion+" + "+ayudaMontoLiquidadacion+" - "+incapacidad+"  + "+primaAntiguedadAux+ " + " +indemnizacion);
			System.out.println("subtotal: "+ subTotal);


		}else {
			double diasTrabaj = Double.parseDouble(textFieldDiasTrabajadosLiquidacion.getText());
			//						System.out.println("diasTrabaj: "+ diasTrabaj);

			double proporcionAguinaldo = diasTrabaj*40/365;
			textFieldPropAguiLiquidacion.setText(String.valueOf(df.format(proporcionAguinaldo)));
			//						System.out.println("La proporcion del aguinaldo en dias es: "+df.format(dt));

			double vacGozadas = Double.parseDouble(textFieldVacGozadasLiquidacion.getText());
			double proporcionVacaciones = diasTrabaj*20/365-vacGozadas;
			textFieldPropVacaLiquidacion.setText(String.valueOf(df.format(proporcionVacaciones)));
			//						System.out.println("La proporcion de las vacaciones en dias es: "+propVac);

			double primaVacPag = Double.parseDouble(textFieldPrimaVacPagadaLiquidacion.getText());
			double primaVacPagResultado = diasTrabaj*20/365-primaVacPag;
			textFieldPrimaVacacionalLiquidacion.setText(String.valueOf(df.format(primaVacPagResultado)));
			//						System.out.println("La proporcion del prima vacacional en dias es: "+primaVacPagResultado);

			double sdi = Double.parseDouble(textFieldSDILiquidacion.getText());

			double montoAguinaldo = sdi*proporcionAguinaldo;
			textFieldPropAguiMontoLiquidacion.setText(String.valueOf(df.format(montoAguinaldo)));
			System.out.println("Aguinaldo Monto: " + montoAguinaldo);

			double montoVacaciones = sdi*proporcionVacaciones;
			textFieldPropVacMontoLiquidacion.setText(String.valueOf(df.format(montoVacaciones)));
			System.out.println("Vacaciones Monto: " + montoVacaciones);

			double primaVacioneeMonto = sdi*primaVacPagResultado*0.3;
			textFieldPrimVavMontoLiquidacion.setText(String.valueOf(df.format(primaVacioneeMonto)));
			System.out.println("Prima Vacacional Monto: " + primaVacioneeMonto);

			double salarioProp = Double.parseDouble(textFieldSalarioLiquidacion.getText());
			double salarioMontoLiquidacion = sdi*salarioProp;
			textFieldSalarioMontoLiquidacion.setText(String.valueOf(df.format(salarioMontoLiquidacion)));
			System.out.println("Salario Diario Monto: " + salarioMontoLiquidacion);

			double ayudaProp = Double.parseDouble(textFieldAyudaADespensaLiquidacion.getText());
			double ayudaMontoLiquidadacion = 498.82/14*ayudaProp;
			textFieldAyuDesMontoLiquidacion.setText(String.valueOf(df.format(ayudaMontoLiquidadacion)));
			System.out.println("Ayuda Despensa Monto: " + ayudaMontoLiquidadacion);

			double primaAntiguedad = Double.parseDouble(textFieldPrimaAntigLiquidacion.getText());
			double primaAntiguedadAux = sdi*primaAntiguedad;
			textFieldPrimaAntiMontoLiquidacion.setText(String.valueOf(df.format(primaAntiguedadAux)));
			System.out.println("Prima Antiguedad Monto: " + primaAntiguedadAux);

			double incapacidad = Double.parseDouble(textFieldDctoIncapMontoLiquidacion.getText());
			System.out.println("incapacidad: "+incapacidad);

			double indemnizacion = Double.parseDouble(textFieldIndemizacionMontoLiquidacion.getText());
			System.out.println("indemnizacion: "+indemnizacion);



			double subTotal = montoAguinaldo+montoVacaciones+primaVacioneeMonto+salarioMontoLiquidacion+ayudaMontoLiquidadacion-incapacidad+primaAntiguedadAux+indemnizacion;
			textFieldSubTotalMontoLiquidacion.setText(String.valueOf(df.format(subTotal)));
			System.out.println("subtotalSET: "+montoAguinaldo+" + "+montoVacaciones+" + "+primaVacioneeMonto+" + "+salarioMontoLiquidacion+" + "+ayudaMontoLiquidadacion+" - "+incapacidad+"  + "+primaAntiguedadAux+ " + " +indemnizacion);
			System.out.println("subtotal: "+ subTotal);


		}
	}//fin del metodo

	private void setJTexFieldChangedIndemnizacion(JTextField txt){
		DocumentListener documentListener = new DocumentListener() {

			public void changedUpdate(DocumentEvent documentEvent) {
				printItIndemnizacion(documentEvent);
			}

			public void insertUpdate(DocumentEvent documentEvent) {
				printItIndemnizacion(documentEvent);
			}

			public void removeUpdate(DocumentEvent documentEvent) {
				printItIndemnizacion(documentEvent);
			}
		};
		txt.getDocument().addDocumentListener(documentListener);

	}

	private void printItIndemnizacion(DocumentEvent documentEvent) {
		DocumentEvent.EventType type = documentEvent.getType();

		if (type.equals(DocumentEvent.EventType.CHANGE))
		{

		}
		else if (type.equals(DocumentEvent.EventType.INSERT))
		{
			txtEjemploJTextFieldIndemnizacion();
		}
		else if (type.equals(DocumentEvent.EventType.REMOVE))
		{
			txtEjemploJTextFieldIndemnizacion();
		}
	}

	private void txtEjemploJTextFieldIndemnizacion(){
		textFieldIndemizacionMontoLiquidacion.setBackground(null);

		DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
		simbolos.setDecimalSeparator('.');
		DecimalFormat df = new DecimalFormat("####.##", simbolos);

		int longitudIndeminzacion = textFieldIndemizacionMontoLiquidacion.getText().length();
		System.out.println("longitudIndeminzacion: " + longitudIndeminzacion);

		if(longitudIndeminzacion<1) {
			double diasTrabaj = Double.parseDouble(textFieldDiasTrabajadosLiquidacion.getText());
			//						System.out.println("diasTrabaj: "+ diasTrabaj);

			double proporcionAguinaldo = diasTrabaj*40/365;
			textFieldPropAguiLiquidacion.setText(String.valueOf(df.format(proporcionAguinaldo)));
			//						System.out.println("La proporcion del aguinaldo en dias es: "+df.format(dt));

			double vacGozadas = Double.parseDouble(textFieldVacGozadasLiquidacion.getText());
			double proporcionVacaciones = diasTrabaj*20/365-vacGozadas;
			textFieldPropVacaLiquidacion.setText(String.valueOf(df.format(proporcionVacaciones)));
			//						System.out.println("La proporcion de las vacaciones en dias es: "+propVac);

			double primaVacPag = Double.parseDouble(textFieldPrimaVacPagadaLiquidacion.getText());
			double primaVacPagResultado = diasTrabaj*20/365-primaVacPag;
			textFieldPrimaVacacionalLiquidacion.setText(String.valueOf(df.format(primaVacPagResultado)));
			//						System.out.println("La proporcion del prima vacacional en dias es: "+primaVacPagResultado);

			double sdi = Double.parseDouble(textFieldSDILiquidacion.getText());

			double montoAguinaldo = sdi*proporcionAguinaldo;
			textFieldPropAguiMontoLiquidacion.setText(String.valueOf(df.format(montoAguinaldo)));
			System.out.println("Aguinaldo Monto: " + montoAguinaldo);

			double montoVacaciones = sdi*proporcionVacaciones;
			textFieldPropVacMontoLiquidacion.setText(String.valueOf(df.format(montoVacaciones)));
			System.out.println("Vacaciones Monto: " + montoVacaciones);

			double primaVacioneeMonto = sdi*primaVacPagResultado*0.3;
			textFieldPrimVavMontoLiquidacion.setText(String.valueOf(df.format(primaVacioneeMonto)));
			System.out.println("Prima Vacacional Monto: " + primaVacioneeMonto);

			double salarioProp = Double.parseDouble(textFieldSalarioLiquidacion.getText());
			double salarioMontoLiquidacion = sdi*salarioProp;
			textFieldSalarioMontoLiquidacion.setText(String.valueOf(df.format(salarioMontoLiquidacion)));
			System.out.println("Salario Diario Monto: " + salarioMontoLiquidacion);

			double ayudaProp = Double.parseDouble(textFieldAyudaADespensaLiquidacion.getText());
			double ayudaMontoLiquidadacion = 498.82/14*ayudaProp;
			textFieldAyuDesMontoLiquidacion.setText(String.valueOf(df.format(ayudaMontoLiquidadacion)));
			System.out.println("Ayuda Despensa Monto: " + ayudaMontoLiquidadacion);

			double primaAntiguedad = Double.parseDouble(textFieldPrimaAntigLiquidacion.getText());
			double primaAntiguedadAux = sdi*primaAntiguedad;
			textFieldPrimaAntiMontoLiquidacion.setText(String.valueOf(df.format(primaAntiguedadAux)));
			System.out.println("Prima Antiguedad Monto: " + primaAntiguedadAux);


			double incapacidad = Double.parseDouble(textFieldDctoIncapMontoLiquidacion.getText());
			System.out.println("incapacidad: "+incapacidad);


			double indemnizacion = 0;
			System.out.println("indemnizacion: "+indemnizacion);


			double subTotal = montoAguinaldo+montoVacaciones+primaVacioneeMonto+salarioMontoLiquidacion+ayudaMontoLiquidadacion-incapacidad+primaAntiguedadAux+indemnizacion;
			textFieldSubTotalMontoLiquidacion.setText(String.valueOf(df.format(subTotal)));
			System.out.println("subtotalSET: "+montoAguinaldo+" + "+montoVacaciones+" + "+primaVacioneeMonto+" + "+salarioMontoLiquidacion+" + "+ayudaMontoLiquidadacion+" - "+incapacidad+"  + "+primaAntiguedadAux+ " + " +indemnizacion);
			System.out.println("subtotal: "+ subTotal);


		}else {
			double diasTrabaj = Double.parseDouble(textFieldDiasTrabajadosLiquidacion.getText());
			//						System.out.println("diasTrabaj: "+ diasTrabaj);

			double proporcionAguinaldo = diasTrabaj*40/365;
			textFieldPropAguiLiquidacion.setText(String.valueOf(df.format(proporcionAguinaldo)));
			//						System.out.println("La proporcion del aguinaldo en dias es: "+df.format(dt));

			double vacGozadas = Double.parseDouble(textFieldVacGozadasLiquidacion.getText());
			double proporcionVacaciones = diasTrabaj*20/365-vacGozadas;
			textFieldPropVacaLiquidacion.setText(String.valueOf(df.format(proporcionVacaciones)));
			//						System.out.println("La proporcion de las vacaciones en dias es: "+propVac);

			double primaVacPag = Double.parseDouble(textFieldPrimaVacPagadaLiquidacion.getText());
			double primaVacPagResultado = diasTrabaj*20/365-primaVacPag;
			textFieldPrimaVacacionalLiquidacion.setText(String.valueOf(df.format(primaVacPagResultado)));
			//						System.out.println("La proporcion del prima vacacional en dias es: "+primaVacPagResultado);

			double sdi = Double.parseDouble(textFieldSDILiquidacion.getText());

			double montoAguinaldo = sdi*proporcionAguinaldo;
			textFieldPropAguiMontoLiquidacion.setText(String.valueOf(df.format(montoAguinaldo)));
			System.out.println("Aguinaldo Monto: " + montoAguinaldo);

			double montoVacaciones = sdi*proporcionVacaciones;
			textFieldPropVacMontoLiquidacion.setText(String.valueOf(df.format(montoVacaciones)));
			System.out.println("Vacaciones Monto: " + montoVacaciones);

			double primaVacioneeMonto = sdi*primaVacPagResultado*0.3;
			textFieldPrimVavMontoLiquidacion.setText(String.valueOf(df.format(primaVacioneeMonto)));
			System.out.println("Prima Vacacional Monto: " + primaVacioneeMonto);

			double salarioProp = Double.parseDouble(textFieldSalarioLiquidacion.getText());
			double salarioMontoLiquidacion = sdi*salarioProp;
			textFieldSalarioMontoLiquidacion.setText(String.valueOf(df.format(salarioMontoLiquidacion)));
			System.out.println("Salario Diario Monto: " + salarioMontoLiquidacion);

			double ayudaProp = Double.parseDouble(textFieldAyudaADespensaLiquidacion.getText());
			double ayudaMontoLiquidadacion = 498.82/14*ayudaProp;
			textFieldAyuDesMontoLiquidacion.setText(String.valueOf(df.format(ayudaMontoLiquidadacion)));
			System.out.println("Ayuda Despensa Monto: " + ayudaMontoLiquidadacion);

			double primaAntiguedad = Double.parseDouble(textFieldPrimaAntigLiquidacion.getText());
			double primaAntiguedadAux = sdi*primaAntiguedad;
			textFieldPrimaAntiMontoLiquidacion.setText(String.valueOf(df.format(primaAntiguedadAux)));
			System.out.println("Prima Antiguedad Monto: " + primaAntiguedadAux);

			double incapacidad = Double.parseDouble(textFieldDctoIncapMontoLiquidacion.getText());
			System.out.println("incapacidad: "+incapacidad);

			double indemnizacion = Double.parseDouble(textFieldIndemizacionMontoLiquidacion.getText());
			System.out.println("indemnizacion: "+indemnizacion);

			double subTotal = montoAguinaldo+montoVacaciones+primaVacioneeMonto+salarioMontoLiquidacion+ayudaMontoLiquidadacion-incapacidad+primaAntiguedadAux+indemnizacion;
			textFieldSubTotalMontoLiquidacion.setText(String.valueOf(df.format(subTotal)));
			System.out.println("subtotalSET: "+montoAguinaldo+" + "+montoVacaciones+" + "+primaVacioneeMonto+" + "+salarioMontoLiquidacion+" + "+ayudaMontoLiquidadacion+" - "+incapacidad+"  + "+primaAntiguedadAux+ " + " +indemnizacion);
			System.out.println("subtotal: "+ subTotal);


		}
	}//fin del metodo

	private void setJTexFieldChangedPropVacaciones(JTextField txt){
		DocumentListener documentListener = new DocumentListener() {

			public void changedUpdate(DocumentEvent documentEvent) {
				printItPropVacaciones(documentEvent);
			}

			public void insertUpdate(DocumentEvent documentEvent) {
				printItPropVacaciones(documentEvent);
			}

			public void removeUpdate(DocumentEvent documentEvent) {
				printItPropVacaciones(documentEvent);
			}
		};
		txt.getDocument().addDocumentListener(documentListener);

	}

	private void printItPropVacaciones(DocumentEvent documentEvent) {
		DocumentEvent.EventType type = documentEvent.getType();

		if (type.equals(DocumentEvent.EventType.CHANGE))
		{

		}
		else if (type.equals(DocumentEvent.EventType.INSERT))
		{
			txtEjemploJTextFieldPropVacaciones();
		}
		else if (type.equals(DocumentEvent.EventType.REMOVE))
		{
			txtEjemploJTextFieldPropVacaciones();
		}
	}

	private void txtEjemploJTextFieldPropVacaciones(){
		textFieldPropVacaLiquidacion.setBackground(null);

		DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
		simbolos.setDecimalSeparator('.');
		DecimalFormat df = new DecimalFormat("####.##", simbolos);

		int longitudPropVacaciones = textFieldPropVacaLiquidacion.getText().length();
		System.out.println("longitudIndeminzacion: " + longitudPropVacaciones);

		if(longitudPropVacaciones<1) {

			double diasTrabaj = Double.parseDouble(textFieldDiasTrabajadosLiquidacion.getText());
			System.out.println("diasTrabaj: "+ diasTrabaj);

			double sdi = Double.parseDouble(textFieldSDILiquidacion.getText());
			System.out.println("sdi: " + sdi);

			double propVacSet = 0;
			System.out.println("propVacSet: " + propVacSet);


			double proporcionAguinaldo = diasTrabaj*40/365;
			textFieldPropAguiLiquidacion.setText(String.valueOf(df.format(proporcionAguinaldo)));
			System.out.println("La proporcion del aguinaldo en dias es: "+ df.format(proporcionAguinaldo));

			double primaVacPag = Double.parseDouble(textFieldPrimaVacPagadaLiquidacion.getText());
			double primaVacPagResultado = diasTrabaj*20/365-primaVacPag;
			textFieldPrimaVacacionalLiquidacion.setText(String.valueOf(df.format(primaVacPagResultado)));
			System.out.println("La proporcion del prima vacacional en dias es: "+primaVacPagResultado);

			double montoAguinaldo = sdi*proporcionAguinaldo;
			textFieldPropAguiMontoLiquidacion.setText(String.valueOf(df.format(montoAguinaldo)));
			System.out.println("Aguinaldo Monto: " + montoAguinaldo);

			double montoVacaciones = sdi*propVacSet;
			System.out.println("montoVacaciones: " + montoVacaciones);
			textFieldPropVacMontoLiquidacion.setText(String.valueOf(df.format(montoVacaciones)));
			System.out.println("Vacaciones Monto: " + montoVacaciones);


			double primaVacioneeMonto = sdi*primaVacPagResultado*0.3;
			textFieldPrimVavMontoLiquidacion.setText(String.valueOf(df.format(primaVacioneeMonto)));
			System.out.println("Prima Vacacional Monto: " + primaVacioneeMonto);

			double salarioProp = Double.parseDouble(textFieldSalarioLiquidacion.getText());
			double salarioMontoLiquidacion = sdi*salarioProp;
			textFieldSalarioMontoLiquidacion.setText(String.valueOf(df.format(salarioMontoLiquidacion)));
			System.out.println("Salario Diario Monto: " + salarioMontoLiquidacion);

			double ayudaProp = Double.parseDouble(textFieldAyudaADespensaLiquidacion.getText());
			double ayudaMontoLiquidadacion = 498.82/14*ayudaProp;
			textFieldAyuDesMontoLiquidacion.setText(String.valueOf(df.format(ayudaMontoLiquidadacion)));
			System.out.println("Ayuda Despensa Monto: " + ayudaMontoLiquidadacion);

			double primaAntiguedad = Double.parseDouble(textFieldPrimaAntigLiquidacion.getText());
			double primaAntiguedadAux = sdi*primaAntiguedad;
			textFieldPrimaAntiMontoLiquidacion.setText(String.valueOf(df.format(primaAntiguedadAux)));
			System.out.println("Prima Antiguedad Monto: " + primaAntiguedadAux);


			if(textFieldDctoIncapMontoLiquidacion.getText().isEmpty()) {
				textFieldDctoIncapMontoLiquidacion.setText("0");
			}
			double incapacidad = Double.parseDouble(textFieldDctoIncapMontoLiquidacion.getText());


			if(textFieldIndemizacionMontoLiquidacion.getText().isEmpty()) {
				textFieldIndemizacionMontoLiquidacion.setText("0");
			}
			double indemnizacion = Double.parseDouble(textFieldIndemizacionMontoLiquidacion.getText());


			double subTotal = montoAguinaldo+montoVacaciones+primaVacioneeMonto+salarioMontoLiquidacion+ayudaMontoLiquidadacion-incapacidad+primaAntiguedadAux+indemnizacion;
			textFieldSubTotalMontoLiquidacion.setText(String.valueOf(df.format(subTotal)));
			System.out.println("subtotalSET: "+montoAguinaldo+" + "+montoVacaciones+" + "+primaVacioneeMonto+" + "+salarioMontoLiquidacion+" + "+ayudaMontoLiquidadacion+" - "+incapacidad+"  + "+primaAntiguedadAux+ " + " +indemnizacion);
			System.out.println("subtotal: "+ subTotal);

			double subTotalMonto = Double.parseDouble(textFieldSubTotalMontoLiquidacion.getText());
			double prestamoMonto = Double.parseDouble(textFieldPrestamoMontoLiquidacion.getText());
			double isrMonto = Double.parseDouble(textFieldISRMontoLiquidacion.getText());
			textFieldVacioMontoLiquidacion.setText(String.valueOf(df.format(subTotalMonto-prestamoMonto-isrMonto)));

			//set impuesto
			textFieldAguiGravaImpuesto.setText(String.valueOf(df.format(montoAguinaldo)));
			textFieldPrimaVacGravaImpuesto.setText(String.valueOf(df.format(primaVacioneeMonto)));
			textFieldVacacionesGravaImpuesto.setText(String.valueOf(df.format(montoVacaciones)));
			textFieldPrimaAntigGravaImpuesto.setText(String.valueOf(df.format(primaAntiguedadAux)));
			//textFieldIndemGravaImpuesto.setText("$ -);

			double primaAntiGravada = Double.parseDouble(textFieldPrimaAntigGravaImpuesto.getText());
			if(textFieldIndemGravaImpuesto.getText().isEmpty()){
				textFieldIndemGravaImpuesto.setText("0");
			}else {
				textFieldIndemGravaImpuesto.getText();
			}
			double primaIndemGravada = Double.parseDouble(textFieldIndemGravaImpuesto.getText());

			textFieldVacioGravaImpuesto.setText(String.valueOf(df.format(primaAntiGravada+primaIndemGravada)));

			textFieldAguiExentoImpuesto.setText(String.valueOf(df.format(88.36*30)));
			textFieldPrimaVacacExentoImpuesto.setText(String.valueOf(df.format((88.36*15)/2)));
			textFieldIndemExentoImpuesto.setText(String.valueOf(df.format((88.36*90)*12)));

			double VacAux = Double.parseDouble(textFieldVacacionesGravaImpuesto.getText());
			textFieldVacacAuxImpuesto.setText(String.valueOf(df.format(VacAux)));

			double aguiAux=0;
			double primaVacAux =0;
			double vacacionesAux=0;
			double salarioAux=0;
			double primaAntigAux=0;
			double indemAux =0;
			if(textFieldAguiAuxImpuesto.getText().isEmpty()){
				aguiAux = Double.parseDouble("0");
			}else {
				aguiAux = Double.parseDouble(textFieldAguiAuxImpuesto.getText());
			}

			if(textFieldPrimaVacacAuxImpuesto.getText().isEmpty()){
				primaVacAux = Double.parseDouble("0");
			}else {
				primaVacAux = Double.parseDouble(textFieldPrimaVacacAuxImpuesto.getText());
			}

			if(textFieldVacacAuxImpuesto.getText().isEmpty()){
				vacacionesAux = Double.parseDouble("0");
			}else {
				vacacionesAux = Double.parseDouble(textFieldVacacAuxImpuesto.getText());
			}

			if(textFieldSalarioAuxImpuesto.getText().isEmpty()){
				salarioAux = Double.parseDouble("0");
			}else {
				salarioAux = Double.parseDouble(textFieldSalarioAuxImpuesto.getText());
			}

			if(textFieldPrimaAntigAuxImpuesto.getText().isEmpty()){
				primaAntigAux = Double.parseDouble("0");
			}else {
				primaAntigAux = Double.parseDouble(textFieldPrimaAntigAuxImpuesto.getText());
			}

			if(textFieldIndemAuxImpuesto.getText().isEmpty()){
				indemAux = Double.parseDouble("0");
			}else {
				indemAux = Double.parseDouble(textFieldIndemAuxImpuesto.getText());
			}

			double totalImpuesto = aguiAux+primaVacAux+vacacionesAux+salarioAux+primaAntigAux+indemAux;
			System.out.println("Total impuesto: "+ totalImpuesto);
			textFieldVacioAuxImpuesto.setText(String.valueOf(df.format(totalImpuesto)));


			//calculo isr
			textFieldBaseGravableCalculoISR.setText(String.valueOf(df.format(totalImpuesto)));

			calcularISR();

		}else {
			double diasTrabaj = Double.parseDouble(textFieldDiasTrabajadosLiquidacion.getText());
			System.out.println("diasTrabaj: "+ diasTrabaj);

			double sdi = Double.parseDouble(textFieldSDILiquidacion.getText());
			System.out.println("sdi: " + sdi);

			double propVacSet = Double.parseDouble(textFieldPropVacaLiquidacion.getText());
			System.out.println("propVacSet: " + propVacSet);

			double proporcionAguinaldo = diasTrabaj*40/365;
			textFieldPropAguiLiquidacion.setText(String.valueOf(df.format(proporcionAguinaldo)));
			System.out.println("La proporcion del aguinaldo en dias es: "+ df.format(proporcionAguinaldo));

			double primaVacPag = Double.parseDouble(textFieldPrimaVacPagadaLiquidacion.getText());
			double primaVacPagResultado = diasTrabaj*20/365-primaVacPag;
			textFieldPrimaVacacionalLiquidacion.setText(String.valueOf(df.format(primaVacPagResultado)));
			System.out.println("La proporcion del prima vacacional en dias es: "+primaVacPagResultado);

			double montoAguinaldo = sdi*proporcionAguinaldo;
			textFieldPropAguiMontoLiquidacion.setText(String.valueOf(df.format(montoAguinaldo)));
			System.out.println("Aguinaldo Monto: " + montoAguinaldo);

			double montoVacaciones = sdi*propVacSet;
			System.out.println("montoVacaciones: " + montoVacaciones);
			textFieldPropVacMontoLiquidacion.setText(String.valueOf(df.format(montoVacaciones)));
			System.out.println("Vacaciones Monto set: " + montoVacaciones);

			double primaVacioneeMonto = sdi*primaVacPagResultado*0.3;
			textFieldPrimVavMontoLiquidacion.setText(String.valueOf(df.format(primaVacioneeMonto)));
			System.out.println("Prima Vacacional Monto: " + primaVacioneeMonto);

			double salarioProp = Double.parseDouble(textFieldSalarioLiquidacion.getText());
			double salarioMontoLiquidacion = sdi*salarioProp;
			textFieldSalarioMontoLiquidacion.setText(String.valueOf(df.format(salarioMontoLiquidacion)));
			System.out.println("Salario Diario Monto: " + salarioMontoLiquidacion);

			double ayudaProp = Double.parseDouble(textFieldAyudaADespensaLiquidacion.getText());
			double ayudaMontoLiquidadacion = 498.82/14*ayudaProp;
			textFieldAyuDesMontoLiquidacion.setText(String.valueOf(df.format(ayudaMontoLiquidadacion)));
			System.out.println("Ayuda Despensa Monto: " + ayudaMontoLiquidadacion);

			double primaAntiguedad = Double.parseDouble(textFieldPrimaAntigLiquidacion.getText());
			double primaAntiguedadAux = sdi*primaAntiguedad;
			textFieldPrimaAntiMontoLiquidacion.setText(String.valueOf(df.format(primaAntiguedadAux)));
			System.out.println("Prima Antiguedad Monto: " + primaAntiguedadAux);


			if(textFieldDctoIncapMontoLiquidacion.getText().isEmpty()) {
				textFieldDctoIncapMontoLiquidacion.setText("0");
			}
			double incapacidad = Double.parseDouble(textFieldDctoIncapMontoLiquidacion.getText());


			if(textFieldIndemizacionMontoLiquidacion.getText().isEmpty()) {
				textFieldIndemizacionMontoLiquidacion.setText("0");
			}
			double indemnizacion = Double.parseDouble(textFieldIndemizacionMontoLiquidacion.getText());


			double subTotal = montoAguinaldo+montoVacaciones+primaVacioneeMonto+salarioMontoLiquidacion+ayudaMontoLiquidadacion-incapacidad+primaAntiguedadAux+indemnizacion;
			textFieldSubTotalMontoLiquidacion.setText(String.valueOf(df.format(subTotal)));
			System.out.println("subtotalSET: "+montoAguinaldo+" + "+montoVacaciones+" + "+primaVacioneeMonto+" + "+salarioMontoLiquidacion+" + "+ayudaMontoLiquidadacion+" - "+incapacidad+"  + "+primaAntiguedadAux+ " + " +indemnizacion);
			System.out.println("subtotal: "+ subTotal);

			double subTotalMonto = Double.parseDouble(textFieldSubTotalMontoLiquidacion.getText());
			double prestamoMonto = Double.parseDouble(textFieldPrestamoMontoLiquidacion.getText());
			double isrMonto = Double.parseDouble(textFieldISRMontoLiquidacion.getText());
			textFieldVacioMontoLiquidacion.setText(String.valueOf(df.format(subTotalMonto-prestamoMonto-isrMonto)));

			//set impuesto
			textFieldAguiGravaImpuesto.setText(String.valueOf(df.format(montoAguinaldo)));
			textFieldPrimaVacGravaImpuesto.setText(String.valueOf(df.format(primaVacioneeMonto)));
			textFieldVacacionesGravaImpuesto.setText(String.valueOf(df.format(montoVacaciones)));
			textFieldPrimaAntigGravaImpuesto.setText(String.valueOf(df.format(primaAntiguedadAux)));
			//textFieldIndemGravaImpuesto.setText("$ -);

			double primaAntiGravada = Double.parseDouble(textFieldPrimaAntigGravaImpuesto.getText());
			if(textFieldIndemGravaImpuesto.getText().isEmpty()){
				textFieldIndemGravaImpuesto.setText("0");
			}else {
				textFieldIndemGravaImpuesto.getText();
			}
			double primaIndemGravada = Double.parseDouble(textFieldIndemGravaImpuesto.getText());

			textFieldVacioGravaImpuesto.setText(String.valueOf(df.format(primaAntiGravada+primaIndemGravada)));

			textFieldAguiExentoImpuesto.setText(String.valueOf(df.format(88.36*30)));
			textFieldPrimaVacacExentoImpuesto.setText(String.valueOf(df.format((88.36*15)/2)));
			textFieldIndemExentoImpuesto.setText(String.valueOf(df.format((88.36*90)*12)));

			double VacAux = Double.parseDouble(textFieldVacacionesGravaImpuesto.getText());
			textFieldVacacAuxImpuesto.setText(String.valueOf(df.format(VacAux)));

			double aguiAux=0;
			double primaVacAux =0;
			double vacacionesAux=0;
			double salarioAux=0;
			double primaAntigAux=0;
			double indemAux =0;
			if(textFieldAguiAuxImpuesto.getText().isEmpty()){
				aguiAux = Double.parseDouble("0");
			}else {
				aguiAux = Double.parseDouble(textFieldAguiAuxImpuesto.getText());
			}

			if(textFieldPrimaVacacAuxImpuesto.getText().isEmpty()){
				primaVacAux = Double.parseDouble("0");
			}else {
				primaVacAux = Double.parseDouble(textFieldPrimaVacacAuxImpuesto.getText());
			}

			if(textFieldVacacAuxImpuesto.getText().isEmpty()){
				vacacionesAux = Double.parseDouble("0");
			}else {
				vacacionesAux = Double.parseDouble(textFieldVacacAuxImpuesto.getText());
			}

			if(textFieldSalarioAuxImpuesto.getText().isEmpty()){
				salarioAux = Double.parseDouble("0");
			}else {
				salarioAux = Double.parseDouble(textFieldSalarioAuxImpuesto.getText());
			}

			if(textFieldPrimaAntigAuxImpuesto.getText().isEmpty()){
				primaAntigAux = Double.parseDouble("0");
			}else {
				primaAntigAux = Double.parseDouble(textFieldPrimaAntigAuxImpuesto.getText());
			}

			if(textFieldIndemAuxImpuesto.getText().isEmpty()){
				indemAux = Double.parseDouble("0");
			}else {
				indemAux = Double.parseDouble(textFieldIndemAuxImpuesto.getText());
			}

			double totalImpuesto = aguiAux+primaVacAux+vacacionesAux+salarioAux+primaAntigAux+indemAux;
			System.out.println("Total impuesto: "+ totalImpuesto);
			textFieldVacioAuxImpuesto.setText(String.valueOf(df.format(totalImpuesto)));


			//calculo isr
			textFieldBaseGravableCalculoISR.setText(String.valueOf(df.format(totalImpuesto)));

			calcularISR();

		}
	}//fin del metodo
}
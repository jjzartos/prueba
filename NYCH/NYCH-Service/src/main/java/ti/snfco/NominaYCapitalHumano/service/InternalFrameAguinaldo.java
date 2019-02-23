package ti.snfco.NominaYCapitalHumano.service;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.Dialog.ModalityType;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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
import java.util.Calendar;
import java.util.Date;

import javax.swing.AbstractButton;
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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

import org.omg.PortableInterceptor.INACTIVE;
import org.slf4j.LoggerFactory;

import com.sun.jersey.api.representation.Form;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InternalFrameAguinaldo extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private final JPanel panel = new JPanel();
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(InternalFrameAguinaldo.class);
	private JTextField textFieldBuscarEmp;
	JTable tableAguinaldo = new JTable();
	TableRowSorter rowSorter;
	int IdBusquedaEmple = 1;
	JLabel lblNombre = new JLabel("nombre");
	JPanel panelRedAgui = new JPanel();
	JLabel lblAPAT = new JLabel("pat");
	JLabel lblAPMAT = new JLabel("mat");
	JLabel lblCurp = new JLabel("curp");
	JLabel lblRFC = new JLabel("rfc");
	JLabel lblUR = new JLabel("dependencia");
	JLabel lblPuesto = new JLabel("puesto");
	JLabel lblSalario = new JLabel("Salario:");
	JLabel lblAyudaADespensa = new JLabel("Ayuda a Despensa:");
	JLabel lblSal = new JLabel("0.0");
	JLabel lblAYU = new JLabel("494.82");
	JLabel label = new JLabel("Seleccione el Empleado(a):");
	JLabel lblFEchaIngreso = new JLabel("fecha ingreso");
	private JTextField textFielddias;
	private JTextField textFieldFaltas;
	private JLabel lblDiasp;
	JLabel lblDetalle = new JLabel("Detalle");
	JLabel lblflecha = new JLabel("");
	JLabel lblFondo = new JLabel("");
	JScrollPane scrollPaneAgui = new JScrollPane();
	JLabel lblAguinal = new JLabel("0.0");
	JLabel lblTipoNominaOculta = new JLabel("New label");
	final JTextField textfieldPrimPag = new JTextField("");
	JButton btnAjuste = new JButton("Ajuste");
	public static JLabel lblAguinGravado = new JLabel("0.0");
	public static JLabel lblPrimaGrav = new JLabel("0.0");

	public InternalFrameAguinaldo() {
		setBounds(0, 0, 1501, 670);
		getContentPane().setLayout(null);
		setTitle("Aguinaldos");
		panel.setBackground(SystemColor.controlHighlight);
		panel.setBorder(new TitledBorder(null, "Aguinaldos", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(176, 196, 222)));
		panel.setBounds(0, 0, 1834, 829);
		getContentPane().add(panel);
		panel.setLayout(null);

		textFieldBuscarEmp = new JTextField();
		textFieldBuscarEmp.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("unchecked")
			@Override
			public void keyReleased(KeyEvent arg0) {
				rowSorter.setRowFilter(
						RowFilter.regexFilter(textFieldBuscarEmp.getText().toUpperCase(), IdBusquedaEmple));
			}
		});
		textFieldBuscarEmp.setColumns(10);
		textFieldBuscarEmp.setBorder(null);
		textFieldBuscarEmp.setBackground(SystemColor.controlHighlight);
		textFieldBuscarEmp.setBounds(73, 52, 292, 28);
		panel.add(textFieldBuscarEmp);

		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(176, 196, 222));
		separator.setBackground(new Color(176, 196, 222));
		separator.setBounds(25, 81, 360, 2);
		panel.add(separator);

		scrollPaneAgui.setViewportBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		scrollPaneAgui.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneAgui.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPaneAgui.setBounds(25, 91, 878, 361);
		scrollPaneAgui.setViewportView(tableAguinaldo);
		panel.add(scrollPaneAgui);

		JButton btnseleccionar = new JButton("Seleccionar");
		btnseleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelRedAgui.setVisible(true);
				lblDetalle.setVisible(true);
				lblflecha.setVisible(true);
				int fila = tableAguinaldo.getSelectedRow();
				System.out.println("fila: " + fila);
				if (fila >= 0) {

					String id = tableAguinaldo.getValueAt(fila, 0).toString();
					System.out.println("id: " + id);

					ArrayList<String> listaClasif = new ArrayList<String>();
					listaClasif = getPrimaVacacionalPrimerSemestre(id);
					// System.out.println("tamaño de la lista: " + listaClasif.size());
					// System.out.println("valor de la prima vacacional: " + listaClasif.get(0));
					for (int i = 0; i < listaClasif.size(); i++) {
						// System.out.println("i: "+i);
						textfieldPrimPag.setText(String.valueOf((listaClasif.get(i))));
					}

					ArrayList<String> listaFaltas = new ArrayList<String>();
					listaFaltas = obtenerFaltasxEmpleado(id);
					// System.out.println("tamaño de la lista: " + listaFaltas.size());
					// System.out.println("valor de la faltas: " + listaFaltas.get(0));
					for (int i = 0; i < listaFaltas.size(); i++) {
						System.out.println("i: " + i);
						textFieldFaltas.setText(String.valueOf((listaFaltas.get(i))));
					}

					String nombre = tableAguinaldo.getValueAt(fila, 1).toString();
					String appat = tableAguinaldo.getValueAt(fila, 2).toString();
					String apmat = tableAguinaldo.getValueAt(fila, 3).toString();
					String curp = tableAguinaldo.getValueAt(fila, 9).toString();
					String rfc = tableAguinaldo.getValueAt(fila, 10).toString();
					String ur = tableAguinaldo.getValueAt(fila, 7).toString();
					String puesto = tableAguinaldo.getValueAt(fila, 5).toString();
					String sal = tableAguinaldo.getValueAt(fila, 6).toString();
					// String ayu = tableAguinaldo.getValueAt(fila, 9).toString();
					String fecha = tableAguinaldo.getValueAt(fila, 8).toString();
					// String ayuda = tableAguinaldo.getValueAt(fila, 1).toString();
					lblNombre.setText(nombre);
					lblAPAT.setText(appat);
					lblAPMAT.setText(apmat);
					lblCurp.setText(curp);
					lblRFC.setText(rfc);
					lblUR.setText(ur);
					lblPuesto.setText(puesto);
					lblSal.setText(sal);
					// lblAYU.setText(ayu);
					lblFEchaIngreso.setText(fecha);
					// lblAyudaADespensa.setText(ayuda);

				}
				// else {
				// JOptionPane.showMessageDialog(null, "Registro no Seleccionado");
				// }
			}
		});
		btnseleccionar.setBounds(277, 463, 121, 30);
		panel.add(btnseleccionar);

		JLabel lblIcon = new JLabel();
		lblIcon.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("icons8_Search_32px_2.png"))));
		lblIcon.setBounds(25, 52, 46, 28);
		panel.add(lblIcon);

		panelRedAgui.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		panelRedAgui.setBounds(913, 52, 569, 485);
		panel.add(panelRedAgui);
		panelRedAgui.setLayout(null);

		lblNombre.setBounds(20, 21, 162, 14);
		panelRedAgui.add(lblNombre);

		lblAPAT.setBounds(20, 46, 162, 14);
		panelRedAgui.add(lblAPAT);

		lblAPMAT.setBounds(20, 71, 162, 14);
		panelRedAgui.add(lblAPMAT);

		lblCurp.setBounds(410, 21, 162, 14);
		panelRedAgui.add(lblCurp);

		lblRFC.setBounds(410, 46, 162, 14);
		panelRedAgui.add(lblRFC);

		lblUR.setBounds(202, 71, 193, 14);
		panelRedAgui.add(lblUR);

		lblPuesto.setBounds(410, 71, 130, 14);
		panelRedAgui.add(lblPuesto);

		lblSalario.setBounds(20, 96, 162, 14);
		panelRedAgui.add(lblSalario);

		lblAyudaADespensa.setBounds(20, 121, 162, 14);
		panelRedAgui.add(lblAyudaADespensa);

		lblSal.setBounds(202, 96, 111, 14);
		panelRedAgui.add(lblSal);

		lblAYU.setBounds(202, 121, 111, 14);
		panelRedAgui.add(lblAYU);

		JLabel lblCurp_1 = new JLabel("Curp:");
		lblCurp_1.setBounds(202, 21, 162, 14);
		panelRedAgui.add(lblCurp_1);

		JLabel lblRfc = new JLabel("RFC:");
		lblRfc.setBounds(202, 46, 162, 14);
		panelRedAgui.add(lblRfc);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.DARK_GRAY);
		separator_1.setBackground(Color.DARK_GRAY);
		separator_1.setBounds(20, 138, 520, 5);
		panelRedAgui.add(separator_1);

		JLabel lblNewLabel = new JLabel("Percepción");
		lblNewLabel.setBounds(20, 269, 88, 14);
		panelRedAgui.add(lblNewLabel);

		JLabel lblDas = new JLabel("Días");
		lblDas.setBounds(20, 160, 46, 14);
		panelRedAgui.add(lblDas);

		JLabel lblFaltas = new JLabel("Faltas");
		lblFaltas.setBounds(20, 201, 46, 14);
		panelRedAgui.add(lblFaltas);

		JLabel lblDasTrabajados = new JLabel("Días Trabajados");
		lblDasTrabajados.setBounds(305, 333, 124, 14);
		panelRedAgui.add(lblDasTrabajados);

		JLabel lblProporcinAguinaldo = new JLabel("Proporción Aguinaldo");
		lblProporcinAguinaldo.setBounds(20, 296, 186, 14);
		panelRedAgui.add(lblProporcinAguinaldo);

		JLabel lblProporcinPrimaVacacional = new JLabel("Proporción Prima Vacacional");
		lblProporcinPrimaVacacional.setBounds(20, 321, 186, 14);
		panelRedAgui.add(lblProporcinPrimaVacacional);

		JLabel lblAguinaldoProporcional = new JLabel("Aguinaldo Proporcional");
		lblAguinaldoProporcional.setBounds(20, 346, 186, 14);
		panelRedAgui.add(lblAguinaldoProporcional);

		JLabel lblPrimaVacacionalProporcional = new JLabel("Prima Vacacional Proporcional");
		lblPrimaVacacionalProporcional.setBounds(20, 371, 186, 14);
		panelRedAgui.add(lblPrimaVacacionalProporcional);

		lblFEchaIngreso.setBounds(410, 96, 88, 14);
		panelRedAgui.add(lblFEchaIngreso);

		JLabel lblAguinaldo = new JLabel("Aguinaldo");
		lblAguinaldo.setBounds(305, 154, 110, 14);
		panelRedAgui.add(lblAguinaldo);

		JLabel lblExentoAguinaldo = new JLabel("Aguinaldo Exento ");
		lblExentoAguinaldo.setBounds(305, 179, 111, 14);
		panelRedAgui.add(lblExentoAguinaldo);

		JLabel lblAguinaldoGravado = new JLabel("Aguinaldo Gravado");
		lblAguinaldoGravado.setBounds(305, 204, 110, 14);
		panelRedAgui.add(lblAguinaldoGravado);

		JLabel lblPrimaVacacionalAnual = new JLabel("Prima Vacacional Anual");
		lblPrimaVacacionalAnual.setBounds(305, 229, 130, 14);
		panelRedAgui.add(lblPrimaVacacionalAnual);

		JLabel lblPrimaPagada = new JLabel("Prima Pagada 1er Semestre.");
		lblPrimaPagada.setBounds(20, 237, 186, 14);
		panelRedAgui.add(lblPrimaPagada);

		JLabel lblPrimaPorPagar = new JLabel("Prima Por Pagar");
		lblPrimaPorPagar.setBounds(305, 254, 130, 14);
		panelRedAgui.add(lblPrimaPorPagar);

		JLabel lblExentoPrimaVacacional = new JLabel("Exento Prima Vacacional");
		lblExentoPrimaVacacional.setBounds(305, 279, 141, 14);
		panelRedAgui.add(lblExentoPrimaVacacional);

		JLabel lblPrimaVacacionalGravada = new JLabel("Prima Vacacional Gravada");
		lblPrimaVacacionalGravada.setBounds(305, 304, 169, 14);
		panelRedAgui.add(lblPrimaVacacionalGravada);

		textFielddias = new JTextField();
		textFielddias.setText("365");
		textFielddias.setBounds(212, 154, 86, 30);
		panelRedAgui.add(textFielddias);
		textFielddias.setColumns(10);

		textFieldFaltas = new JTextField();
		textFieldFaltas.setColumns(10);
		textFieldFaltas.setBounds(212, 191, 86, 30);
		panelRedAgui.add(textFieldFaltas);

		lblDiasp = new JLabel();
		lblDiasp.setForeground(Color.BLACK);
		lblDiasp.setBounds(486, 325, 54, 30);
		panelRedAgui.add(lblDiasp);

		final JLabel lblPerce = new JLabel("0.0");
		lblPerce.setBounds(214, 269, 86, 14);
		panelRedAgui.add(lblPerce);

		final JLabel lblPropAgui = new JLabel("0.0");
		lblPropAgui.setBounds(214, 296, 86, 14);
		panelRedAgui.add(lblPropAgui);

		final JLabel lblPropPrim = new JLabel("0.0");
		lblPropPrim.setBounds(214, 321, 86, 14);
		panelRedAgui.add(lblPropPrim);

		final JLabel lblAguiPropo = new JLabel("0.0");
		lblAguiPropo.setForeground(new Color(184, 134, 11));
		lblAguiPropo.setBounds(214, 346, 86, 14);
		panelRedAgui.add(lblAguiPropo);

		final JLabel lblPrimPropo = new JLabel("0.0");
		lblPrimPropo.setBounds(214, 371, 86, 14);
		panelRedAgui.add(lblPrimPropo);
		lblAguinal.setForeground(new Color(184, 134, 11));

		lblAguinal.setBounds(486, 154, 86, 14);
		panelRedAgui.add(lblAguinal);

		final JLabel lblExentoAgui = new JLabel("0.0");
		lblExentoAgui.setBounds(486, 179, 86, 14);
		panelRedAgui.add(lblExentoAgui);

		lblAguinGravado.setBounds(486, 204, 86, 14);
		panelRedAgui.add(lblAguinGravado);

		final JLabel lblPrimaVAnual = new JLabel("0.0");
		lblPrimaVAnual.setBounds(486, 229, 86, 14);
		panelRedAgui.add(lblPrimaVAnual);

		textfieldPrimPag.setBounds(212, 229, 88, 30);
		panelRedAgui.add(textfieldPrimPag);

		final JLabel lblPrimxPagar = new JLabel("0.0");
		lblPrimxPagar.setBounds(486, 254, 86, 14);
		panelRedAgui.add(lblPrimxPagar);

		final JLabel lblExentoPrimV = new JLabel("0.0");
		lblExentoPrimV.setBounds(486, 279, 86, 14);
		panelRedAgui.add(lblExentoPrimV);

		lblPrimaGrav.setBounds(486, 304, 86, 14);
		panelRedAgui.add(lblPrimaGrav);

		JButton btnCalcularAg = new JButton("Calcular");
		btnCalcularAg.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("accept.png"))));
		btnCalcularAg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (textFielddias.getText().isEmpty() || textFieldFaltas.getText().isEmpty()
						|| textfieldPrimPag.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,
							"Por favor ingerese los dias trabajados, las faltas y la prima pagada");
				}

				int fila = tableAguinaldo.getSelectedRow();
				System.out.println("fila: " + fila);
				if (fila >= 0) {
					String salario = tableAguinaldo.getValueAt(fila, 6).toString();
					double salarioT = Double.parseDouble(salario);

					// String ayud = tableAguinaldo.getValueAt(fila, 9).toString();
					double ayu = Double.parseDouble(lblAYU.getText());
					System.out.println("Ayuda de despensa: " + ayu);

					// no suma la ayuda de despensa
					double perce = salarioT + (ayu);
					// double perce =6102.46;

					System.out.println("percepcion: " + perce);

					double dias = Double.parseDouble(textFielddias.getText());
					double faltas = Double.parseDouble(textFieldFaltas.getText());

					double diasP = dias - faltas;
					System.out.println("Dias a pagar: " + diasP);
					lblDiasp.setText(String.valueOf(diasP));

					double proporAgui = 40 * diasP / 365;
					System.out.println("proporcion aguinaldo en dias: " + proporAgui);

					double proporPrimaVac = diasP * 20 / 365;
					System.out.println("proporcion prima vacacional en dias: " + proporPrimaVac);

					double aguiProporcional = proporAgui * (perce / 14);
					System.out.println("Aguinaldo proporcional: " + aguiProporcional);

					double primaVacPropor = proporPrimaVac * (perce / 14) * 0.3;
					System.out.println("prima vacacional proporcional: " + primaVacPropor);

					double aguinaldo = aguiProporcional;

					double exentoAgui = 30 * 88.36;

					double aguiGravado = aguinaldo - exentoAgui;

					double primaVacAnual = primaVacPropor;

					double primaPagadda = Double.parseDouble(textfieldPrimPag.getText());

					double primaxPagar = primaVacAnual - primaPagadda;

					double exentoPrimaVacacional = 15 * 88.36;

					double primaVacGravada = primaVacPropor - exentoPrimaVacacional;

					DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
					simbolos.setDecimalSeparator('.');
					DecimalFormat df = new DecimalFormat("####.##", simbolos);
					lblPerce.setText(String.valueOf(df.format(perce)));
					lblPropAgui.setText(String.valueOf(df.format(proporAgui)));
					lblPropPrim.setText(String.valueOf(df.format(proporPrimaVac)));
					lblAguiPropo.setText(String.valueOf(df.format(aguiProporcional)));
					lblPrimPropo.setText(String.valueOf(df.format(primaVacPropor)));
					lblAguinal.setText(String.valueOf(df.format(aguinaldo)));
					lblExentoAgui.setText(String.valueOf(exentoAgui));
					lblAguinGravado.setText(String.valueOf(df.format(aguiGravado)));
					lblPrimaVAnual.setText(String.valueOf(df.format(primaVacAnual)));
					lblPrimxPagar.setText(String.valueOf(df.format(primaxPagar)));
					lblExentoPrimV.setText(String.valueOf(df.format(exentoPrimaVacacional)));
					lblPrimaGrav.setText(String.valueOf(df.format(primaVacGravada)));

				}

				insertarAguinaldo();

//				textFielddias.setText(null);
//				textFieldFaltas.setText(null);
//				textfieldPrimPag.setText(null);

//				lblPerce.setText("0.0");
//				lblPropAgui.setText("0.0");
//				lblPropPrim.setText("0.0");
//				lblAguiPropo.setText("0.0");
//				lblPrimPropo.setText("0.0");
//				lblAguinal.setText("0.0");
//				lblExentoAgui.setText("0.0");
//				lblAguinGravado.setText("0.0");
//				lblPrimaVAnual.setText("0.0");
//				lblPrimxPagar.setText("0.0");
//				lblExentoPrimV.setText("0.0");
//				lblPrimaGrav.setText("0.0");
//				lblDiasp.setText("0.0");
				
				btnAjuste.setVisible(true);

				// dispose();

			}
		});
		btnCalcularAg.setBounds(423, 425, 117, 30);
		panelRedAgui.add(btnCalcularAg);

		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.DARK_GRAY);
		separator_2.setBackground(Color.DARK_GRAY);
		separator_2.setBounds(20, 146, 520, 5);
		panelRedAgui.add(separator_2);
		label.setBounds(25, 20, 228, 21);

		panel.add(label);

		lblDetalle.setBounds(913, 20, 46, 28);
		panel.add(lblDetalle);

		lblflecha.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("download.png"))));
		lblflecha.setBounds(953, 20, 46, 28);
		panel.add(lblflecha);

		lblTipoNominaOculta.setBounds(394, 23, 302, 14);
		lblTipoNominaOculta.setVisible(false);
		panel.add(lblTipoNominaOculta);

		final JButton btnVerEmpleados = new JButton("Ver Empleados");
		btnVerEmpleados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarDatosEmpleado(lblTipoNominaOculta.getText());
				btnVerEmpleados.setEnabled(false);
			}
		});
		btnVerEmpleados.setBounds(394, 58, 149, 30);
		panel.add(btnVerEmpleados);

		btnAjuste.addActionListener(new ActionListener() {
			@SuppressWarnings({ "static-access", "unused" })
			public void actionPerformed(ActionEvent e) {
				InternalFrameAjusteISR internalFrameAjusteISR = new InternalFrameAjusteISR();
				FormularioPrincipal.desktopPane.add(internalFrameAjusteISR);
				// internalFrameAjusteISR.lblTipoNominaOculto.setText(FormularioPrincipal.lblIdTipoNomina.getText());
				internalFrameAjusteISR.show();
				internalFrameAjusteISR.setFrameIcon(new ImageIcon(
						Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("user_add.png"))));
				internalFrameAjusteISR.setIconifiable(true);
				internalFrameAjusteISR.setClosable(true);
				internalFrameAjusteISR.setResizable(true);
				internalFrameAjusteISR.setMaximizable(true);
				internalFrameAjusteISR.setVisible(true);
				internalFrameAjusteISR.toFront();
				internalFrameAjusteISR.mostrarTablaIsrAnual();
				internalFrameAjusteISR.textFieldDifContraLimite.setVisible(false);
				internalFrameAjusteISR.lblDiferenciaContraLimites.setVisible(false);
				internalFrameAjusteISR.textFieldImpMarginal.setVisible(false);
				internalFrameAjusteISR.lblImp.setVisible(false);
				internalFrameAjusteISR.lblIsrCausado.setVisible(false);
				internalFrameAjusteISR.textFieldIsrCausado.setVisible(false);
				internalFrameAjusteISR.mostrarSubsidioAnual();
				internalFrameAjusteISR.scrollPaneSubsidioAnual.setVisible(false);
				internalFrameAjusteISR.lblSubsidioAnual.setVisible(false);
				internalFrameAjusteISR.lblSubsidioAlEmpleo.setVisible(false);
				internalFrameAjusteISR.textFieldSubsidioAlempleo.setVisible(false);
				internalFrameAjusteISR.lblSubsidioPorPagar.setVisible(false);
				internalFrameAjusteISR.textFieldSubsidioPorPagar.setVisible(false);
				internalFrameAjusteISR.btnCalcularSubXPagar.setVisible(false);
				internalFrameAjusteISR.lblIsrMenosSubsidio.setVisible(false);
				internalFrameAjusteISR.textFieldIsrMenosSubxPagar.setVisible(false);
				internalFrameAjusteISR.lblIsrCobrado.setVisible(false);
				internalFrameAjusteISR.lblIsrPorCobrar.setVisible(false);
				internalFrameAjusteISR.textFieldisrCobrado.setVisible(false);
				internalFrameAjusteISR.textFieldIsrPorCobrar.setVisible(false);
				internalFrameAjusteISR.btnIsrCobrado.setVisible(false);
				internalFrameAjusteISR.btnSeleccionarsubsidioAnual.setVisible(false);
				internalFrameAjusteISR.lblIngresosTotales.setVisible(false);
				internalFrameAjusteISR.textFieldIngresosTotales.setVisible(false);

				DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
				simbolos.setDecimalSeparator('.');
				DecimalFormat formateador = new DecimalFormat("####.##", simbolos);
				int fila = tableAguinaldo.getSelectedRow();

				if (FormularioPrincipal.lblIsNomC.getText() == "OK") {

					String salario = tableAguinaldo.getValueAt(fila, 6).toString();
					double sal = Double.parseDouble(salario) / 14;
					System.out.println("sal: " + sal);

					String nombre = tableAguinaldo.getValueAt(fila, 1).toString();
					String ap = tableAguinaldo.getValueAt(fila, 2).toString();
					String am = tableAguinaldo.getValueAt(fila, 3).toString();
					String nombreC = nombre + " " + ap + " " + am;

					String salarioT = tableAguinaldo.getValueAt(fila, 6).toString();
					double salT = Double.parseDouble(salarioT);
//					System.out.println("salT: " + salT);
					
//					String aguinaldoGravado = lblAguinGravado.getText();
//					System.out.println("aguinaldoGravado: " + aguinaldoGravado);
//					double aguinaldGravado = Double.parseDouble(aguinaldoGravado) + salT;
//					System.out.println("salario Total: " + aguinaldGravado);

					internalFrameAjusteISR.textFieldSDI.setText(String.valueOf(sal));
					internalFrameAjusteISR.lblNombreCompleto.setText(nombre);
					internalFrameAjusteISR.textFieldSalarioTotal.setText(String.valueOf(salT));
					internalFrameAjusteISR.lblNombreCompleto.setText(nombreC);
//					internalFrameAjusteISR.textFieldIngresosTotales.setText(String.valueOf(aguinaldGravado));
					
					

				} else if (FormularioPrincipal.lblIsNomS.getText() == "OK") {

					String salarioS = tableAguinaldo.getValueAt(fila, 6).toString();
					double sal = Double.parseDouble(salarioS) / 7;

					String nombreS = tableAguinaldo.getValueAt(fila, 1).toString();
					String apS = tableAguinaldo.getValueAt(fila, 2).toString();
					String amS = tableAguinaldo.getValueAt(fila, 3).toString();
					String nombreCS = nombreS + " " + apS + " " + amS;

					String salarioTS = tableAguinaldo.getValueAt(fila, 6).toString();
					double salTS = Double.parseDouble(salarioTS) * 52;
					System.out.println("salTS: " + salTS);
					

					String aguinaldoGravadoS = lblAguinGravado.getText();
					System.out.println("aguinaldoGravadoS: " + aguinaldoGravadoS);
					double aguinaldGravado = Double.parseDouble(aguinaldoGravadoS) + salTS;
					System.out.println("salario TotalS: " + aguinaldGravado);

					internalFrameAjusteISR.textFieldSDI.setText(String.valueOf(sal));
					internalFrameAjusteISR.lblNombreCompleto.setText(nombreS);
					internalFrameAjusteISR.textFieldSalarioTotal.setText(String.valueOf(salTS));
					internalFrameAjusteISR.lblNombreCompleto.setText(nombreCS);
					internalFrameAjusteISR.textFieldIngresosTotales.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color( 98, 224, 237)));
					internalFrameAjusteISR.textFieldIngresosTotales.setText(String.valueOf(aguinaldGravado));

				} else if (FormularioPrincipal.lblIsNomJ.getText() == "OK") {

					String salario = tableAguinaldo.getValueAt(fila, 6).toString();
					double sal = Double.parseDouble(salario) / 30;

					String nombre = tableAguinaldo.getValueAt(fila, 1).toString();
					String ap = tableAguinaldo.getValueAt(fila, 2).toString();
					String am = tableAguinaldo.getValueAt(fila, 3).toString();
					String nombreC = nombre + " " + ap + " " + am;

					String salarioT = tableAguinaldo.getValueAt(fila, 6).toString();
					double salT = Double.parseDouble(salarioT) * 12;
					System.out.println("salT: " + salT);
					

					String aguinaldoGravado = lblAguinGravado.getText();
					System.out.println("aguinaldoGravado: " + aguinaldoGravado);
					double aguinaldGravado = Double.parseDouble(aguinaldoGravado) + salT;
					System.out.println("salario Total: " + aguinaldGravado);

					internalFrameAjusteISR.textFieldSDI.setText(String.valueOf(sal));
					internalFrameAjusteISR.lblNombreCompleto.setText(nombre);
					internalFrameAjusteISR.textFieldSalarioTotal.setText(String.valueOf(salT));
					internalFrameAjusteISR.lblNombreCompleto.setText(nombreC);
					internalFrameAjusteISR.textFieldIngresosTotales.setText(String.valueOf(aguinaldGravado));
				}

			}
		});
		btnAjuste.setBounds(913, 540, 89, 23);
		panel.add(btnAjuste);

		lblFondo.setBounds(0, 0, 1906, 983);
		lblFondo.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("walll.png"))));
		panel.add(lblFondo);

		// mostrarDatosEmpleado("1");

	}

	public static ArrayList<String> getPrimaVacacionalPrimerSemestre(String id) {
		PoolNYCH nych = new PoolNYCH();
		Connection connect = null;
		ResultSet resultSet = null;
		String sqlSelectEjercicio = "select valor_percepcion from CALCULO_NOMINA where ID_EMPLEADO = '" + id
				+ "' and ID_PERCEPCION = 18 and periodo = '" + FormularioPrincipal.lblPeriodoNumero.getText()
				+ "' and id_TIPO_NOMINA='" + FormularioPrincipal.lblIdTipoNomina.getText() + "'";
		// System.out.println("sqlSelectEjercicio: " + sqlSelectEjercicio);
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
		}
		return lista;
	}

	public static ArrayList<String> obtenerFaltasxEmpleado(String id) {
		PoolNYCH nych = new PoolNYCH();
		Connection connect = null;
		ResultSet resultSet = null;
		String sqlSelectEjercicio = "select count(id_falta) from FALTAS_EMPLEADO where ID_EMPLEADO = '" + id
				+ "' and periodo = '" + FormularioPrincipal.lblPeriodoNumero.getText() + "' and TIPO_NOMINA='"
				+ FormularioPrincipal.lblIdTipoNomina.getText() + "'";
		// System.out.println("sqlSelectEjercicio: " + sqlSelectEjercicio);
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
		}
		return lista;
	}

	public int insertarAguinaldo() {

		int resultado = 0;
		int claveInternaPercepcion = 40;
		int claveInternaDeduccion = 22;
		int clavePercepcionAguinaldo = 2;
		double valorClaveInternaPercepcion = 0.0;
		double valorClaveInternaDeduccion = 0.0;
		PoolNYCH nych = new PoolNYCH();
		Connection conPrima = null;
		String sqlInsert = "";

		// String text = textFieldTotPagar.getText();
		// double DouVal = 0.0;
		// try {
		// DouVal = Double.valueOf(text).doubleValue();
		// System.out.println("Double Valor: "+DouVal);
		// } catch (NumberFormatException e) {
		// //Log it if needed
		// // intVal = //default fallback value;
		// }

		DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
		simbolos.setDecimalSeparator('.');
		DecimalFormat formateador = new DecimalFormat("####.##", simbolos);
		int fila = tableAguinaldo.getSelectedRow();
		String dependencia = tableAguinaldo.getValueAt(fila, 11).toString();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date diaHoy = Calendar.getInstance().getTime();
		String texto = lblAguinal.getText();
		double valor = 0;
		try {
			// parse() lanza una ParseException en caso de fallo que hay que capturar.
			Number numero = formateador.parse(texto);
			valor = numero.doubleValue();
			System.out.println("Valor: " + valor);
		} catch (ParseException e) {
			e.printStackTrace();
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: " + errors);
			// JOptionPane.showMessageDialog(null, el, "Error de conexion",
			// JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null, "Error. El usuario ha escrito algo que no se puede convertir a número");
		}

		if (fila >= 0) {
			String id = tableAguinaldo.getValueAt(fila, 0).toString();
			String idPuesto = tableAguinaldo.getValueAt(fila, 4).toString();
			sqlInsert = "INSERT INTO dbo.CALCULO_NOMINA (ID_EMPLEADO,ID_PERCEPCION,ID_DEDUCCION,VALOR_PERCEPCION,VALOR_DEDUCCION,ID_PUESTO,FECHA_CALCULO,ID_DEPENDENCIA,ID_TIPO_NOMINA,ESTATUS,PERIODO)"
					+ "" + "VALUES (" + id + "," + clavePercepcionAguinaldo + "," + claveInternaDeduccion + "," + valor
					+ "," + "" + valorClaveInternaDeduccion + "," + idPuesto + ",'" + formatter.format(diaHoy) + "','"
					+ dependencia + "'," + "" + lblTipoNominaOculta.getText() + ",'" + 1 + "','"
					+ FormularioPrincipal.lblPeriodoNumero.getText() + "')";
			System.out.println("INSERT AGUINALDO: " + sqlInsert);
			sqlInsert = "";
		}

		try {
			conPrima = nych.datasource.getConnection();
			PreparedStatement pps = conPrima.prepareStatement(sqlInsert);
			pps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Datos Guardados");
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
				conPrima.close();
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
		return resultado;

	}

	public void mostrarDatosEmpleado(String valor) {
		DefaultTableModel modelo = new DefaultTableModel() {

			public boolean isCellEditable(int row, int column) {
				// return super.isCellEditable(row, column);
				if (column == 8) {
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
		modelo.addColumn("ID PUESTO");
		modelo.addColumn("PUESTO");
		modelo.addColumn("SALARIO");
		modelo.addColumn("UNIDAD_RESPONSABLE");
		modelo.addColumn("FECHA INGRESO");
		modelo.addColumn("CURP");
		modelo.addColumn("RFC");
		modelo.addColumn("ID DEPENDENCIA");
		// modelo.addColumn("AYUDA A DESPENSA");

		tableAguinaldo.setModel(modelo);
		tableAguinaldo.setBackground(Color.WHITE);

		JTableHeader th = new JTableHeader();
		Color colorSilverLight = new Color(176, 196, 222);
		Color colorNegro = new Color(0, 0, 0);
		th = tableAguinaldo.getTableHeader();
		Font fuente = new Font("Arial", Font.BOLD, 14);
		th.setFont(fuente);
		th.setBackground(colorSilverLight);
		th.setForeground(colorNegro);

		TableColumnModel columnModel = tableAguinaldo.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(80);
		columnModel.getColumn(1).setPreferredWidth(200);
		columnModel.getColumn(2).setPreferredWidth(200);
		columnModel.getColumn(3).setPreferredWidth(200);
		columnModel.getColumn(4).setPreferredWidth(200);
		columnModel.getColumn(5).setPreferredWidth(300);
		columnModel.getColumn(6).setPreferredWidth(200);
		columnModel.getColumn(7).setPreferredWidth(200);
		columnModel.getColumn(8).setPreferredWidth(200);
		columnModel.getColumn(9).setPreferredWidth(100);
		columnModel.getColumn(10).setPreferredWidth(100);
		columnModel.getColumn(11).setPreferredWidth(200);

		String sqlSelect = "";
		sqlSelect = "SELECT DBO.EMPLEADOS.CLAVE,DBO.EMPLEADOS.NOMBRE,DBO.EMPLEADOS.APELLIDO_PATERNO,DBO.EMPLEADOS.APELLIDO_MATERNO,\r\n"
				+ "dbo.puestos.no_puesto,dbo.puestos.nombre_puesto,dbo.puestos.salario,DBO.DEPENDENCIAS.UNIDAD_RESPONSABLE,dbo.empleados.fecha_ingreso,dbo.empleados.curp,dbo.empleados.rfc,DBO.DEPENDENCIAS.ID_UNIDAD\r\n"// DBO.CALCULO_NOMINA.valor_percepcion
				+ "from empleados\r\n" + "left JOIN DBO.PUESTOS ON DBO.EMPLEADOS.ID_PUESTO = DBO.PUESTOS.no_puesto\r\n"
				+ "LEFT JOIN DBO.DEPENDENCIAS ON DBO.EMPLEADOS.ID_UNIDAD = DBO.DEPENDENCIAS.ID_UNIDAD\r\n"
				+ "LEFT JOIN DBO.EJERCICIOS ON DBO.EMPLEADOS.ID_EJERCICIOS = DBO.EJERCICIOS.ID_EJERCICIOS\r\n"
				/*
				 * +
				 * "LEFT JOIN DBO.CALCULO_NOMINA ON DBO.EMPLEADOS.clave = DBO.CALCULO_NOMINA.id_empleado\r\n"
				 */
				+ "WHERE DBO.EMPLEADOS.id_ejercicios = '" + valor + "' and ELIMINAR_LOGICO='1'  \r\n";// AND
																										// dbo.calculo_nomina.id_percepcion=26
																										// falta nomina
																										// semanal en
																										// calculo
																										// nomina
		// System.out.println("sql aguinaldo: " + sqlSelect);
		PoolNYCH nych = new PoolNYCH();
		Connection con = null;
		String datos[] = new String[14];
		try {
			con = nych.datasource.getConnection();
			Statement st = con.createStatement();
			ResultSet resultSet = st.executeQuery(sqlSelect);
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
				datos[11] = resultSet.getString(12);
				modelo.addRow(datos);
			}
			rowSorter = new TableRowSorter(modelo);
			tableAguinaldo.setRowSorter(rowSorter);

			tableAguinaldo.setModel(modelo);
			System.out.println("" + tableAguinaldo.getRowCount());
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
				// JOptionPane.showMessageDialog(null, ep, "Error de desconexion",
				// JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");
			}
		}
	}// fin del metodo

	// metodo para el tiempo de busqueda
	// Este es el evento que se ejecuta cuando un JFrame se carga
	public void windowOpened(ActionEvent e) {
		final long SLEEP_TIME = 1 * 1000;
		SwingWorker<Void, Void> mySwingWorker = new SwingWorker<Void, Void>() {
			protected Void doInBackground() throws Exception {

				// mostrarDatosEmpleado(lblTipoNominaOculta.getText());

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
	
	
	
	
	
}

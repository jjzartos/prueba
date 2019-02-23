package ti.snfco.NominaYCapitalHumano.service;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.Normalizer.Form;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.synth.SynthSeparatorUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import org.slf4j.LoggerFactory;
import javax.swing.border.MatteBorder;
import java.awt.SystemColor;
import com.toedter.calendar.JDateChooser;


import javax.swing.UIManager;
import javax.swing.JSeparator;

public class InternalFramePeriodo extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(InternalFramePeriodo.class);
	private JTextField textFieldNuevoEjercicio =  new JTextField();
	JCheckBox chckbxEjercicioCatorcenal = new JCheckBox("Catorcenal");
	JCheckBox chckbxEjercicioSemanal = new JCheckBox("Semanal");
	JCheckBox chckbxEjercicioJubilados = new JCheckBox("Jubilados");
	String atributo = "id";
	String atributoCatorcenal;
	String atributoSemanal;
	String atributoJubilado;
	private JTextField textFieldNoCat;
	JLabel lblDif = new JLabel("0");
	JDateChooser dateChooserInicial = new JDateChooser();
	JDateChooser dateChooserFinal = new JDateChooser();
	JTable tableReporteFechasPeriodo = new JTable();
	JTable tableReporteFechasPeriodoSemanal = new JTable();
	JPanel panelFechasPeriodosCatorcenal = new JPanel();
	JTable tableDatosConsultaEjercicio = new JTable();
	private JTextField textField;
	private final JLabel lblFondo = new JLabel("");
	private final JLabel lblFondoPeriodo = new JLabel("");
	private final JLabel lblFondoPeriodoSemanal = new JLabel("");
	public JPanel panelPestañaNuevoEjercicio = new JPanel();
	JPanel panelNuevoEjercicio = new JPanel();
	JPanel panelFechasPeriodoSemanal = new JPanel();
	JTextField textFieldNumeroSemana;
	JDateChooser dateChooserFechaInicialSemanal = new JDateChooser();
	JDateChooser dateChooserFechaFinalSemanal = new JDateChooser();
	JLabel lblDiferenciasemanal = new JLabel("0");
	JLabel lblTipoNominaOculto = new JLabel("tipoNominaOcultoID");
	public JTabbedPane tabbedPanePestañas = new JTabbedPane(JTabbedPane.TOP);
	public JPanel panel = new JPanel();
	public JButton btnSeleccionar = new JButton("Seleccionar");
	JLabel lblPeriodoOculto = new JLabel("New label");
	JButton btnSelecc = new JButton("Seleccionar");
	JLabel lblTipoNominaOcultaSemana = new JLabel("New label");
	JLabel lblTipoNominaOcultaNombre = new JLabel("New label");
	private final JSeparator separator = new JSeparator();
	private final JSeparator separator_1 = new JSeparator();
	private final JSeparator separator_2 = new JSeparator();

	public InternalFramePeriodo() {
		try {
			setIcon(true);
			setMaximum(true);
		} catch (PropertyVetoException e1) {
			e1.printStackTrace();
		}
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(0, 0, 1500, 551);//solo para modificar el diseño, las dimensiones estan seteadas desde FormularioPrincipal.-------1500
//		setBounds(100, 100, 1200, 500);//solo para modificar el diseño, las dimensiones estan seteadas desde FormularioPrincipal.
		setVisible(true);
		setTitle("Periodos");
		getContentPane().setLayout(null);

		panel.setBounds(0, 0, 1555, 511);
		getContentPane().add(panel);
		panel.setLayout(null);

		tabbedPanePestañas.setBounds(0, 0, 1555, 520);
		panel.add(tabbedPanePestañas);

		panelPestañaNuevoEjercicio.setBackground(SystemColor.controlHighlight);
		panelPestañaNuevoEjercicio.setBorder(new TitledBorder(null, "Ejercicio", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(176, 196, 222)));
		panelPestañaNuevoEjercicio.setName("Nuevo");
		tabbedPanePestañas.addTab("Ejercicios", null, panelPestañaNuevoEjercicio, null);
		panelPestañaNuevoEjercicio.setLayout(null);

		panelNuevoEjercicio.setBorder(new LineBorder(new Color(176, 196, 222)));
		panelNuevoEjercicio.setBounds(10, 87, 534, 175);
		panelPestañaNuevoEjercicio.add(panelNuevoEjercicio);
		panelNuevoEjercicio.setLayout(null);

		JLabel lblNuevoEjercicio = new JLabel("Nombre del Ejercicio");
		lblNuevoEjercicio.setBounds(10, 15, 125, 24);
		panelNuevoEjercicio.add(lblNuevoEjercicio);

		textFieldNuevoEjercicio = new JTextField();
		textFieldNuevoEjercicio.setBounds(126, 11, 388, 32);
		panelNuevoEjercicio.add(textFieldNuevoEjercicio);
		textFieldNuevoEjercicio.setColumns(10);


		chckbxEjercicioCatorcenal.setBounds(126, 57, 97, 23);
		panelNuevoEjercicio.add(chckbxEjercicioCatorcenal);
		chckbxEjercicioCatorcenal.setBackground(SystemColor.control);


		chckbxEjercicioSemanal.setBounds(273, 57, 97, 23);
		panelNuevoEjercicio.add(chckbxEjercicioSemanal);
		chckbxEjercicioSemanal.setBackground(SystemColor.control);


		chckbxEjercicioJubilados.setBounds(417, 57, 97, 23);
		panelNuevoEjercicio.add(chckbxEjercicioJubilados);
		chckbxEjercicioJubilados.setBackground(SystemColor.control);

		JButton btnEjercicioGuardar = new JButton("Guardar");
		btnEjercicioGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertarRegistroEjercicio();
				mostrarDatosEjercicio("");
			}
		});
		btnEjercicioGuardar.setBounds(223, 103, 119, 32);
		panelNuevoEjercicio.add(btnEjercicioGuardar);
		btnEjercicioGuardar.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("accept.png"))));

		JButton btnEjercicioCancelar = new JButton("Cancelar");
		btnEjercicioCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});
		btnEjercicioCancelar.setBounds(395, 103, 119, 32);
		panelNuevoEjercicio.add(btnEjercicioCancelar);
		btnEjercicioCancelar.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("delete.png"))));

		JLabel lblNewLabel_1 = new JLabel("Se recomienda seguir la siguiente nomenclatura para la creación del nombre del ejercicio.");
		lblNewLabel_1.setBounds(10, 8, 534, 27);
		panelPestañaNuevoEjercicio.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("NOMINA MUNICIPAL SAN FRANCISCO DEL RINCÓN, año(catorcenal).");
		lblNewLabel_2.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_2.setBounds(10, 46, 425, 14);
		panelPestañaNuevoEjercicio.add(lblNewLabel_2);

		JLabel lblEjercicios = new JLabel("Ejercicios");
		lblEjercicios.setBounds(575, 11, 62, 32);
		panelPestañaNuevoEjercicio.add(lblEjercicios);

		//		textField = new JTextField();
		//		textField.setColumns(10);
		//		textField.setBounds(647, 11, 437, 32);
		//		panelPestañaNuevoEjercicio.add(textField);

		JScrollPane scrollPaneEjerc = new JScrollPane();
		scrollPaneEjerc.setViewportBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		scrollPaneEjerc.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneEjerc.setViewportView(tableDatosConsultaEjercicio);
		scrollPaneEjerc.setBounds(575, 54, 905, 208);
		panelPestañaNuevoEjercicio.add(scrollPaneEjerc);
		tabbedPanePestañas.setForegroundAt(0, new Color(0, 0, 0));

		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int fila = tableDatosConsultaEjercicio.getSelectedRow();
				if(fila>=0) {
					String tipoNomina =  tableDatosConsultaEjercicio.getValueAt(fila, 0).toString();
					String tipoNominaNombre =  tableDatosConsultaEjercicio.getValueAt(fila, 1).toString();
					String iscatorcenal =  tableDatosConsultaEjercicio.getValueAt(fila, 3).toString();
					String issemanal =  tableDatosConsultaEjercicio.getValueAt(fila, 4).toString();
					String isjubilados =  tableDatosConsultaEjercicio.getValueAt(fila, 5).toString();

					lblTipoNominaOculto.setText(tipoNomina);
					lblTipoNominaOcultaNombre.setText(tipoNominaNombre);
					
					FormularioPrincipal.lblTipoNominaNombre.setText(tipoNominaNombre);
					FormularioPrincipal.lblTipoNominaNombre.setVisible(true);
					FormularioPrincipal.lblNewLabel.setVisible(true);
					FormularioPrincipal.lblIdTipoNomina.setText(tipoNomina);
					FormularioPrincipal.lblIdTipoNomina.setVisible(true);
					FormularioPrincipal.lblIsNomina.setVisible(true);
					
					FormularioPrincipal.lblCatorcenal.setVisible(true);
					FormularioPrincipal.lblSemanal.setVisible(true);
					FormularioPrincipal.lblJubilados.setVisible(true);
					FormularioPrincipal.lblIsNomC.setVisible(true);
					FormularioPrincipal.lblIsNomS.setVisible(true);
					FormularioPrincipal.lblIsNomJ.setVisible(true);
					
					FormularioPrincipal.lblIsNomC.setText(iscatorcenal);
					FormularioPrincipal.lblIsNomS.setText(issemanal);
					FormularioPrincipal.lblIsNomJ.setText(isjubilados);
					
					FormularioPrincipal.lblImgDetalle.setVisible(true);
					
					//System.out.println("lbl Tipo Nomina: "+ lblTipoNominaOcultaNombre.getText());

					if(FormularioPrincipal.lblIsNomC.getText().equalsIgnoreCase("OK")) {
						System.out.println("Nomina Catocenal en IntenalFramePeriodo");
						int confirmado = JOptionPane.showConfirmDialog(null,"Seleccionó la nómina "+tipoNominaNombre+" ¿Es correcto?");

						if (JOptionPane.OK_OPTION == confirmado) {

							TabSwitchingGui sw = new TabSwitchingGui();
							sw.actionPerformed(arg0);
							tabbedPanePestañas.setEnabledAt(1, true);
							//tabbedPanePestañas.setEnabledAt(2, true);
							JOptionPane.showMessageDialog(null, "Seleccione el periodo para trabajar");	


						}else if(JOptionPane.NO_OPTION == confirmado){
							System.out.println("NO_OPTION");
							return;
						}else {
							JOptionPane.showMessageDialog(null, "Debe seleccionar el tipo de nómina, para continuar","Advertencia", JOptionPane.WARNING_MESSAGE );
						}
					}else if(FormularioPrincipal.lblIsNomS.getText().equalsIgnoreCase("OK")) {
						System.out.println("Nomina Semanal en IntenalFramePeriodo");
						int confirmado = JOptionPane.showConfirmDialog(null,"Seleccionó la nómina "+tipoNominaNombre+" ¿Es correcto?");/*"+fechaPeriodo+"*/ 

						if (JOptionPane.OK_OPTION == confirmado) {

							TabSwitchingGui sw = new TabSwitchingGui();
							sw.actionPerformedSemanal(arg0);

							tabbedPanePestañas.setEnabledAt(2, true);
							JOptionPane.showMessageDialog(null, "Seleccione el periodo para trabajar");	

						}else if(JOptionPane.NO_OPTION == confirmado){
							System.out.println("NO_OPTION");
							return;
						}else {
							JOptionPane.showMessageDialog(null, "Debe seleccionar el periodo, para continuar","Advertencia", JOptionPane.WARNING_MESSAGE );
						}
					}

				}else {
					JOptionPane.showMessageDialog(null, "No se ha seleccionado ningún Ejercicio");
				}
			}

		});
		btnSeleccionar.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("add.png"))));
		btnSeleccionar.setBounds(823, 268, 137, 30);
		panelPestañaNuevoEjercicio.add(btnSeleccionar);

		panelFechasPeriodosCatorcenal.setBackground(SystemColor.controlHighlight);
		panelFechasPeriodosCatorcenal.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Fechas Periodos Catorcenas", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(176, 196, 222)));
		tabbedPanePestañas.addTab("Fechas Periodos Catorcenal", null, panelFechasPeriodosCatorcenal, null);
		panelFechasPeriodosCatorcenal.setLayout(null);

		JLabel lblNewLabel_3 = new JLabel("Fecha Inicial:");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setBounds(73, 22, 105, 30);
		panelFechasPeriodosCatorcenal.add(lblNewLabel_3);

		JLabel lblFechaFinal = new JLabel("Fecha Final:");
		lblFechaFinal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFechaFinal.setBounds(73, 58, 105, 30);
		panelFechasPeriodosCatorcenal.add(lblFechaFinal);

		JLabel lblNewLabel_4 = new JLabel("Días entre Fecha Inicial y Fecha Final:");
		lblNewLabel_4.setBounds(10, 152, 234, 30);
		panelFechasPeriodosCatorcenal.add(lblNewLabel_4);


		lblDif.setBounds(242, 152, 97, 30);
		panelFechasPeriodosCatorcenal.add(lblDif);

		JButton btnNewButton = new JButton("Guardar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				insertarFechasPeriodos();
				mostrarDatosFechasPeriodo("");
			}
		});
		btnNewButton.setBounds(169, 193, 89, 30);
		panelFechasPeriodosCatorcenal.add(btnNewButton);

		JLabel lblNumeroCatorcena = new JLabel("Numero Catorcena:");
		lblNumeroCatorcena.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNumeroCatorcena.setBounds(10, 99, 170, 30);
		panelFechasPeriodosCatorcenal.add(lblNumeroCatorcena);

		textFieldNoCat = new JTextField();
		textFieldNoCat.setColumns(10);
		textFieldNoCat.setBounds(190, 99, 234, 30);
		panelFechasPeriodosCatorcenal.add(textFieldNoCat);


		dateChooserInicial.setBounds(190, 22, 234, 30);
		panelFechasPeriodosCatorcenal.add(dateChooserInicial);


		dateChooserFinal.setBounds(190, 63, 234, 30);
		panelFechasPeriodosCatorcenal.add(dateChooserFinal);

		JScrollPane scrollPaneFechasPeriodos = new JScrollPane();
		scrollPaneFechasPeriodos.setViewportBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		scrollPaneFechasPeriodos.setBounds(463, 24, 567, 280);
		scrollPaneFechasPeriodos.setViewportView(tableReporteFechasPeriodo);
		scrollPaneFechasPeriodos.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panelFechasPeriodosCatorcenal.add(scrollPaneFechasPeriodos);
		
		btnSelecc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = tableReporteFechasPeriodo.getSelectedRow();
				if(fila>=0) {
					String fechaInicio =  tableReporteFechasPeriodo.getValueAt(fila, 0).toString();
					String fechaHasta =  tableReporteFechasPeriodo.getValueAt(fila, 1).toString();
					String numeroPeriodo =  tableReporteFechasPeriodo.getValueAt(fila, 2).toString();

					lblPeriodoOculto.setText(numeroPeriodo);
					FormularioPrincipal.lblPeriodoNumero.setText(numeroPeriodo);
					FormularioPrincipal.lblPeriodoNumero.setVisible(true);
					FormularioPrincipal.lblNewLabel_1.setVisible(true);
					//System.out.println("lbl periodo oculto: "+ lblPeriodoOculto.getText());
					
					FormularioPrincipal.lblFechaDel.setText("del");
					FormularioPrincipal.lblFechaDel.setVisible(true);
					FormularioPrincipal.lblFechaDel.setVisible(true);
					
					FormularioPrincipal.lblFechaPeriodo.setText(fechaInicio);
					FormularioPrincipal.lblFechaPeriodo.setVisible(true);
					FormularioPrincipal.lblFechaPeriodo.setVisible(true);
					
					
					FormularioPrincipal.lblFechaHasta.setText("a");
					FormularioPrincipal.lblFechaHasta.setVisible(true);
					FormularioPrincipal.lblFechaHasta.setVisible(true);
					
					FormularioPrincipal.lblFechaHastaPeriodo.setText(fechaHasta);
					FormularioPrincipal.lblFechaHastaPeriodo.setVisible(true);
					FormularioPrincipal.lblFechaHastaPeriodo.setVisible(true);

					//JOptionPane.showMessageDialog(null, "Se seleccionó el periodo: " + fechaPeriodo + " de la nómina catorcenal.");
					int confirmado = JOptionPane.showConfirmDialog(null,"Seleccionó el periodo "+numeroPeriodo+" de la nomina catorcenal, ¿Es correcto?");

					if (JOptionPane.OK_OPTION == confirmado) {
						//System.out.println("confirmado");

						FormularioPrincipal.btnEmpleadoIcono.setEnabled(true);
						FormularioPrincipal.btnCatalogosIcono.setEnabled(true);
						FormularioPrincipal.btnMovimientosIcono.setEnabled(true);
						FormularioPrincipal.btnTimbradoIcon.setEnabled(true);
						FormularioPrincipal.btnReportesIcon.setEnabled(true);
						FormularioPrincipal.toolBar.setVisible(true);
						FormularioPrincipal.lblCalculos.setVisible(true);
						FormularioPrincipal.btnCalcularNominaTesoreria.setEnabled(true);

					}else if(JOptionPane.NO_OPTION == confirmado){
						System.out.println("NO_OPTION");
						return;
					}else {
						JOptionPane.showMessageDialog(null, "Debe seleccionar el periodo, para continuar","Advertencia", JOptionPane.WARNING_MESSAGE );
					}


					dispose();


				}else {
					JOptionPane.showMessageDialog(null, "No se ha seleccionado ningún Ejercicio");
				}
			}
		});

		btnSelecc.setBounds(659, 315, 164, 30);
		panelFechasPeriodosCatorcenal.add(btnSelecc);

		lblPeriodoOculto.setBounds(10, 252, 46, 14);
		lblPeriodoOculto.setVisible(false);
		panelFechasPeriodosCatorcenal.add(lblPeriodoOculto);

		lblFondo.setBounds(0, 0, 1906, 983);
		lblFondo.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("walll.png"))));
		panelFechasPeriodosCatorcenal.add(lblFondo);
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setForeground(new Color(211, 211, 211));
		separator_1.setBackground(Color.WHITE);
		separator_1.setBounds(434, 22, 5, 354);

		panelFechasPeriodosCatorcenal.add(separator_1);


		panelFechasPeriodoSemanal.setBackground(SystemColor.controlHighlight);
		panelFechasPeriodoSemanal.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Fechas Periodos Semanales", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(176, 196, 222)));
		panelFechasPeriodoSemanal.setLayout(null);
		tabbedPanePestañas.addTab("Fechas Periodos Semanales", null, panelFechasPeriodoSemanal, null);

		JLabel label_1 = new JLabel("Fecha Inicial:");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(93, 28, 105, 30);
		panelFechasPeriodoSemanal.add(label_1);

		dateChooserFechaInicialSemanal.setBounds(210, 28, 234, 30);
		panelFechasPeriodoSemanal.add(dateChooserFechaInicialSemanal);

		JLabel label_2 = new JLabel("Fecha Final:");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setBounds(93, 64, 105, 30);
		panelFechasPeriodoSemanal.add(label_2);

		dateChooserFechaFinalSemanal.setBounds(210, 69, 234, 30);
		panelFechasPeriodoSemanal.add(dateChooserFechaFinalSemanal);

		JLabel lblNumeroSemana = new JLabel("Numero Semana:");
		lblNumeroSemana.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNumeroSemana.setBounds(30, 105, 170, 30);
		panelFechasPeriodoSemanal.add(lblNumeroSemana);

		textFieldNumeroSemana = new JTextField();
		textFieldNumeroSemana.setColumns(10);
		textFieldNumeroSemana.setBounds(210, 105, 234, 30);
		panelFechasPeriodoSemanal.add(textFieldNumeroSemana);

		JLabel labelTexto = new JLabel("Días entre Fecha Inicial y Fecha Final:");
		labelTexto.setBounds(30, 158, 234, 30);
		panelFechasPeriodoSemanal.add(labelTexto);

		lblDiferenciasemanal.setBounds(262, 158, 97, 30);
		panelFechasPeriodoSemanal.add(lblDiferenciasemanal);

		JButton btnGuardarSemana = new JButton("Guardar");
		btnGuardarSemana.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertarFechasPeriodosSemanas();
				mostrarDatosFechasPeriodoSemanal("");
			}
		});
		btnGuardarSemana.setBounds(189, 199, 89, 30);
		panelFechasPeriodoSemanal.add(btnGuardarSemana);

		JScrollPane scrollPaneFechaSemanas = new JScrollPane();
		scrollPaneFechaSemanas.setViewportBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		scrollPaneFechaSemanas.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneFechaSemanas.setBounds(483, 30, 567, 280);
		scrollPaneFechaSemanas.setViewportView(tableReporteFechasPeriodoSemanal);
		panelFechasPeriodoSemanal.add(scrollPaneFechaSemanas);

		JButton btnSel = new JButton("Seleccionar");
		btnSel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int fila = tableReporteFechasPeriodoSemanal.getSelectedRow();
				if(fila>=0) {
					//String fecha =  tableReporteFechasPeriodo.getValueAt(fila, 0).toString();
					String fechaInicio =  tableReporteFechasPeriodoSemanal.getValueAt(fila, 0).toString();
					String fechaHasta =  tableReporteFechasPeriodoSemanal.getValueAt(fila, 1).toString();
					String fechaPeriodo =  tableReporteFechasPeriodoSemanal.getValueAt(fila, 2).toString();
					String numeroPeriodo =  tableReporteFechasPeriodoSemanal.getValueAt(fila, 2).toString();

					lblTipoNominaOcultaSemana.setText(fechaPeriodo);
					//System.out.println("lbl periodo oculto: "+ lblPeriodoOculto.getText());
					
//					lblPeriodoOculto.setText(numeroPeriodo);
					FormularioPrincipal.lblPeriodoNumero.setText(numeroPeriodo);
					FormularioPrincipal.lblPeriodoNumero.setVisible(true);
					FormularioPrincipal.lblNewLabel_1.setVisible(true);
					
					
					FormularioPrincipal.lblFechaPeriodo.setText(fechaInicio);
					FormularioPrincipal.lblFechaPeriodo.setVisible(true);
					FormularioPrincipal.lblFechaPeriodo.setVisible(true);
					
					FormularioPrincipal.lblFechaHastaPeriodo.setText(fechaHasta);
					FormularioPrincipal.lblFechaHastaPeriodo.setVisible(true);
					FormularioPrincipal.lblFechaHastaPeriodo.setVisible(true);
					

					//JOptionPane.showMessageDialog(null, "Se seleccionó el periodo: " + fechaPeriodo + " de la nómina catorcenal.");
					int confirmado = JOptionPane.showConfirmDialog(null,"Seleccionó el periodo "+fechaPeriodo+" de la nomina semanal, ¿Es correcto?");

					if (JOptionPane.OK_OPTION == confirmado) {
						//System.out.println("confirmado");

						FormularioPrincipal.btnEmpleadoIcono.setEnabled(true);
						FormularioPrincipal.btnCatalogosIcono.setEnabled(true);
						FormularioPrincipal.btnMovimientosIcono.setEnabled(true);
						FormularioPrincipal.btnTimbradoIcon.setEnabled(true);
						FormularioPrincipal.btnReportesIcon.setEnabled(true);
						FormularioPrincipal.toolBar.setVisible(true);
						FormularioPrincipal.lblCalculos.setVisible(true);
						FormularioPrincipal.lblTipoNominaNombre.setVisible(true);
						FormularioPrincipal.lblPeriodoNumero.setVisible(true);
						FormularioPrincipal.lblNewLabel.setVisible(true);
						FormularioPrincipal.lblNewLabel_1.setVisible(true);
						
						FormularioPrincipal.lblFechaPeriodo.setVisible(true);
						FormularioPrincipal.lblFechaDel.setVisible(true);
						FormularioPrincipal.lblFechaHasta.setVisible(true);
						FormularioPrincipal.lblFechaHastaPeriodo.setVisible(true);

					}else if(JOptionPane.NO_OPTION == confirmado){
						System.out.println("NO_OPTION");
						return;
					}else {
						JOptionPane.showMessageDialog(null, "Debe seleccionar el periodo, para continuar","Advertencia", JOptionPane.WARNING_MESSAGE );
					}


					dispose();


				}else {
					JOptionPane.showMessageDialog(null, "No se ha seleccionado ningún Ejercicio");
				}

			}
		});
		btnSel.setBounds(671, 320, 141, 30);
		panelFechasPeriodoSemanal.add(btnSel);

		lblTipoNominaOcultaSemana.setBounds(22, 261, 135, 30);
		lblTipoNominaOcultaSemana.setVisible(false);
		panelFechasPeriodoSemanal.add(lblTipoNominaOcultaSemana);

		lblFondoPeriodoSemanal.setBounds(0, 0, 1906, 983);
		lblFondoPeriodoSemanal.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("walll.png"))));
		panelFechasPeriodoSemanal.add(lblFondoPeriodoSemanal);
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setForeground(new Color(211, 211, 211));
		separator_2.setBackground(Color.WHITE);
		separator_2.setBounds(454, 28, 5, 354);

		panelFechasPeriodoSemanal.add(separator_2);

		lblTipoNominaOculto.setBounds(10, 294, 520, 16);
		lblTipoNominaOculto.setVisible(false);
		panelPestañaNuevoEjercicio.add(lblTipoNominaOculto);
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(new Color(211, 211, 211));
		separator.setBackground(Color.WHITE);
		separator.setBounds(554, 11, 5, 354);

		panelPestañaNuevoEjercicio.add(separator);

		lblTipoNominaOcultaNombre.setBounds(10, 317, 454, 14);
		lblTipoNominaOcultaNombre.setVisible(false);
		panelPestañaNuevoEjercicio.add(lblTipoNominaOcultaNombre);


		lblFondoPeriodo.setBounds(0, 0, 1752, 492);
		lblFondoPeriodo.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("walll.png"))));
		panelPestañaNuevoEjercicio.add(lblFondoPeriodo);

	}

	public int insertarFechasPeriodos() {
		int resultado = 0;
		Date fechaInicial = dateChooserInicial.getDate();
		Date fechaFinal = dateChooserFinal.getDate();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		int dias=(int) ((fechaFinal.getTime()-fechaInicial.getTime())/86400000);//86400000 milisegundos tiene un dia.
		System.out.println("Hay "+dias+" dias de diferencia");
		lblDif.setText(String.valueOf(dias));

		String sqlInsert="INSERT INTO DBO.NOMINA_CATORCENAS (FECHA_INICIAL,FECHA_FINAL,NUMERO_CATORCENA,DIAS_DIFERENCIA) "
				+ 		 "VALUES(convert(datetime,'"+ formatter.format(fechaInicial)+"',101), convert(datetime,'"+ formatter.format(fechaFinal)+"',101),'"+ textFieldNoCat.getText() +"','"+ Integer.parseInt(lblDif.getText()) +"')";
		//System.out.println("sql: "+ sqlInsert);


		PoolNYCH nych = new PoolNYCH();
		Connection con =null;
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
			JOptionPane.showMessageDialog(null, el, "Error de conexion", JOptionPane.ERROR_MESSAGE);
		}finally {
			try {
				con.close();
			} catch (SQLException ep) {
				ep.printStackTrace();
				StringWriter errors = new StringWriter();
				ep.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: "+ errors );
				JOptionPane.showMessageDialog(null, ep, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
			}
		}
		return resultado;
	}

	public int insertarFechasPeriodosSemanas() {
		int resultado = 0;
		Date fechaInicial = dateChooserFechaInicialSemanal.getDate();
		Date fechaFinal = dateChooserFechaFinalSemanal.getDate();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		//		System.out.println("inicial: " + fechaInicial);
		//		System.out.println("final: " + fechaFinal);

		int dias=(int) ((fechaFinal.getTime()-fechaInicial.getTime())/86400000);//86400000 milisegundos tiene un dia.
		System.out.println("Hay "+dias+" dias de diferencia");
		lblDiferenciasemanal.setText(String.valueOf(dias));

		String sqlInsert="INSERT INTO DBO.NOMINA_SEMANAS (FECHA_INICIAL,FECHA_FINAL,NUMERO_SEMANA,DIAS_DIFERENCIA) "
				+ 		 "VALUES(convert(datetime,'"+ formatter.format(fechaInicial)+"',101), convert(datetime,'"+ formatter.format(fechaFinal)+"',101),'"+ textFieldNumeroSemana.getText() +"','"+ Integer.parseInt(lblDiferenciasemanal.getText()) +"')";
		//System.out.println("sql: "+ sqlInsert);


		PoolNYCH nych = new PoolNYCH();
		Connection con =null;
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
			JOptionPane.showMessageDialog(null, el, "Error de conexion", JOptionPane.ERROR_MESSAGE);
		}finally {
			try {
				con.close();
			} catch (SQLException ep) {
				ep.printStackTrace();
				StringWriter errors = new StringWriter();
				ep.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: "+ errors );
				JOptionPane.showMessageDialog(null, ep, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
			}
		}
		return resultado;
	}


	/*
	 * Metodo Para la busquedda del catalogo de fechas periodos
	 */
	public void mostrarDatosFechasPeriodo(String valor) {
		//System.out.println("mostrarDatosFechasPeriodo");
		DefaultTableModel modeloEjer = new DefaultTableModel(){

			public boolean isCellEditable(int row, int column) {
				//return super.isCellEditable(row, column);
				if(column==2){
					return true;
				}else {
					return false;
				}
			}
		};
		modeloEjer.addColumn("FECHA INICIAL");
		modeloEjer.addColumn("FECHA FINAL");
		modeloEjer.addColumn("NUMERO CATORCENA");
		modeloEjer.addColumn("DIAS");
		tableReporteFechasPeriodo.setModel(modeloEjer);
		tableReporteFechasPeriodo.setBackground(Color.WHITE);

		JTableHeader th = new JTableHeader();
		Color colorSilverLight=new Color(176, 196, 222);
		Color colorNegro=new Color(0, 0, 0);
		th = tableReporteFechasPeriodo.getTableHeader();
		Font fuente = new Font("Arial", Font.BOLD, 14); 
		th.setFont(fuente); 
		th.setBackground(colorSilverLight);
		th.setForeground(colorNegro);

		TableColumnModel columnModel = tableReporteFechasPeriodo.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(150);
		columnModel.getColumn(1).setPreferredWidth(150);
		columnModel.getColumn(2).setPreferredWidth(200);
		columnModel.getColumn(3).setPreferredWidth(80);

		String sqlSelect = "SELECT fecha_inicial,fecha_final,numero_catorcena,dias_diferencia FROM dbo.NOMINA_CATORCENAS";

		String datosEjer[] = new String[4];
		PoolNYCH nych = new PoolNYCH();
		Connection con =null;
		Statement st;
		ResultSet resultSet;
		try {
			con = nych.datasource.getConnection();
			st = con.createStatement();
			resultSet = st.executeQuery(sqlSelect);
			while(resultSet.next()) {
				datosEjer[0] = resultSet.getString(1);
				datosEjer[1] = resultSet.getString(2);
				datosEjer[2] = resultSet.getString(3);
				datosEjer[3] = resultSet.getString(4);
				modeloEjer.addRow(datosEjer);
			}//FIN DEL WHILE
			tableReporteFechasPeriodo.setModel(modeloEjer);
		}catch (SQLException el) {
			el.printStackTrace();
			StringWriter errors = new StringWriter();
			el.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: "+ errors );
			JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo por favor");
		}finally {
			try {
				con.close();
			} catch (SQLException ep) {
				ep.printStackTrace();
				StringWriter errors = new StringWriter();
				ep.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: "+ errors );
				JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo por favor");
			}
		}//FIN DEL FINALLY
	}//FIN DEL METODO


	/*
	 * Metodo Para la busquedda del catalogo de fechas periodos
	 */
	public void mostrarDatosFechasPeriodoSemanal(String valor) {
		//System.out.println("mostrarDatosFechasPeriodo");
		DefaultTableModel modeloFechasPeriodosSemanas = new DefaultTableModel(){

			public boolean isCellEditable(int row, int column) {
				//return super.isCellEditable(row, column);
				if(column==2){
					return true;
				}else {
					return false;
				}
			}
		};
		modeloFechasPeriodosSemanas.addColumn("FECHA INICIAL");
		modeloFechasPeriodosSemanas.addColumn("FECHA FINAL");
		modeloFechasPeriodosSemanas.addColumn("NUMERO SEMANA");
		modeloFechasPeriodosSemanas.addColumn("DIAS");
		tableReporteFechasPeriodoSemanal.setModel(modeloFechasPeriodosSemanas);
		tableReporteFechasPeriodoSemanal.setBackground(Color.WHITE);

		JTableHeader th = new JTableHeader();
		Color colorSilverLight=new Color(176, 196, 222);
		Color colorNegro=new Color(0, 0, 0);
		th = tableReporteFechasPeriodoSemanal.getTableHeader();
		Font fuente = new Font("Arial", Font.BOLD, 14); 
		th.setFont(fuente); 
		th.setBackground(colorSilverLight);
		th.setForeground(colorNegro);

		TableColumnModel columnModel = tableReporteFechasPeriodoSemanal.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(150);
		columnModel.getColumn(1).setPreferredWidth(150);
		columnModel.getColumn(2).setPreferredWidth(200);
		columnModel.getColumn(3).setPreferredWidth(80);

		String sqlSelect = "SELECT fecha_inicial,fecha_final,numero_semana,dias_diferencia FROM dbo.NOMINA_SEMANAS";

		String datosFechasPeriodosSemanas[] = new String[4];
		PoolNYCH nych = new PoolNYCH();
		Connection con =null;
		Statement st;
		ResultSet resultSet;
		try {
			con = nych.datasource.getConnection();
			st = con.createStatement();
			resultSet = st.executeQuery(sqlSelect);
			while(resultSet.next()) {
				datosFechasPeriodosSemanas[0] = resultSet.getString(1);
				datosFechasPeriodosSemanas[1] = resultSet.getString(2);
				datosFechasPeriodosSemanas[2] = resultSet.getString(3);
				datosFechasPeriodosSemanas[3] = resultSet.getString(4);
				modeloFechasPeriodosSemanas.addRow(datosFechasPeriodosSemanas);
			}//FIN DEL WHILE
			tableReporteFechasPeriodoSemanal.setModel(modeloFechasPeriodosSemanas);
		}catch (SQLException el) {
			el.printStackTrace();
			StringWriter errors = new StringWriter();
			el.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: "+ errors );
			JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo por favor");
		}finally {
			try {
				con.close();
			} catch (SQLException ep) {
				ep.printStackTrace();
				StringWriter errors = new StringWriter();
				ep.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: "+ errors );
				JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo por favor");
			}
		}//FIN DEL FINALLY
	}//FIN DEL METODO


	/*
	 * Metodo Para la busquedda del catalogo ejercicios
	 */
	public void mostrarDatosEjercicio(String valor) {
		//System.out.println("mostrarDatosEjercicio");
		DefaultTableModel modeloEjer = new DefaultTableModel(){

			public boolean isCellEditable(int row, int column) {
				//return super.isCellEditable(row, column);
				if(column==2){
					return true;
				}else {
					return false;
				}
			}
		};
		modeloEjer.addColumn("ID");
		modeloEjer.addColumn("NOMBRE");
		modeloEjer.addColumn("CLAVE");
		modeloEjer.addColumn("CATORCENAL");
		modeloEjer.addColumn("SEMANAL");
		modeloEjer.addColumn("JUBILADOS");
		tableDatosConsultaEjercicio.setModel(modeloEjer);
		tableDatosConsultaEjercicio.setBackground(Color.WHITE);

		JTableHeader th = new JTableHeader();
		Color colorSilverLight=new Color(176, 196, 222);
		Color colorNegro=new Color(0, 0, 0);
		th = tableDatosConsultaEjercicio.getTableHeader();
		Font fuente = new Font("Arial", Font.BOLD, 14); 
		th.setFont(fuente); 
		th.setBackground(colorSilverLight);
		th.setForeground(colorNegro);

		TableColumnModel columnModel = tableDatosConsultaEjercicio.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(50);
		columnModel.getColumn(1).setPreferredWidth(400);
		columnModel.getColumn(2).setPreferredWidth(120);
		columnModel.getColumn(3).setPreferredWidth(100);
		columnModel.getColumn(4).setPreferredWidth(100);
		columnModel.getColumn(4).setPreferredWidth(100);

		String sqlSelectReg="";
		if(valor.equals("")) {
			sqlSelectReg = "SELECT ID_EJERCICIOS,nombre_ejercicio,clave_ejercicio,iscatorcenal,issemanal,isjubilados FROM dbo.EJERCICIOS";
		}else {
			sqlSelectReg = "SELECT ID_EJERCICIOS,nombre_ejercicio,clave_ejercicio,iscatorcenal,issemanal,isjubilados FROM dbo.EJERCICIOS WHERE "+atributo+""+"='"+ valor +"'"; 
		}

		String datosEjer[] = new String[6];
		PoolNYCH nych = new PoolNYCH();
		Connection con =null;
		Statement st;
		ResultSet resultSet;
		try {
			con = nych.datasource.getConnection();
			st = con.createStatement();
			resultSet = st.executeQuery(sqlSelectReg);
			while(resultSet.next()) {
				datosEjer[0] = resultSet.getString(1);
				datosEjer[1] = resultSet.getString(2);
				datosEjer[2] = resultSet.getString(3);
				datosEjer[3] = resultSet.getString(4);
				datosEjer[4] = resultSet.getString(5);
				datosEjer[5] = resultSet.getString(6);
				
//				System.out.println("valor: "+ datosEjer[3]);;
				if(datosEjer[3].equalsIgnoreCase("1")) {
					datosEjer[3] = "OK";
				}else {
					datosEjer[3] = "--";
				}
				
				if(datosEjer[4].equalsIgnoreCase("1")) {
					datosEjer[4] = "OK";
				}else {
					datosEjer[4] = "--";
				}
				
				if(datosEjer[5].equalsIgnoreCase("1")) {
					datosEjer[5] = "OK";
				}else {
					datosEjer[5] = "--";
				}
				modeloEjer.addRow(datosEjer);
			}//FIN DEL WHILE
			tableDatosConsultaEjercicio.setModel(modeloEjer);
		}catch (SQLException el) {
			el.printStackTrace();
			StringWriter errors = new StringWriter();
			el.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: "+ errors );
			JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo por favor");
		}finally {
			try {
				con.close();
			} catch (SQLException ep) {
				ep.printStackTrace();
				StringWriter errors = new StringWriter();
				ep.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: "+ errors );
				JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo por favor");
			}
		}//FIN DEL FINALLY
	}//FIN DEL METODO




	public int insertarRegistroEjercicio() {
		int resultado = 0;
		boolean selectedCatorcenal = chckbxEjercicioCatorcenal.isSelected();
		boolean selectedSemanal = chckbxEjercicioSemanal.isSelected();
		boolean selectedJubilados = chckbxEjercicioJubilados.isSelected();
		DateFormat outputFormat = new SimpleDateFormat("yyyy/MM/dd");
		DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date inputText;
		String outputText;
		Date fecha = java.util.Calendar.getInstance().getTime();

		if(selectedCatorcenal) {
			atributoCatorcenal = "1";
			atributoSemanal = "0";
			atributoJubilado = "0";
		}

		if(selectedSemanal) {
			atributoSemanal = "1";
			atributoCatorcenal = "0";
			atributoJubilado = "0";
		}

		if(selectedJubilados) {
			atributoJubilado = "1";
			atributoSemanal = "0";
			atributoCatorcenal = "0";
		}

		Date result = new Date();
		DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd-");
		SimpleDateFormat formatoDeFecha1 = new SimpleDateFormat("yyyy-MM-dd");
		String fechaConvertida = formatter.format(result);
		String consultaEjercicio ="select concat('E-',(select right(concat('"+fechaConvertida+"', (select count(*) from dbo.EJERCICIOS)+1),12)))";
		//String cons = "select left(MAX(clave_ejercicio),2)+right('0000'+ convert(varchar(10),RIGHT(max(clave_ejercicio),3)+1),3) from ejercicios)";

		String sqlInsert="INSERT INTO DBO.EJERCICIOS (NOMBRE_EJERCICIO,CLAVE_EJERCICIO,ISCATORCENAL,ISSEMANAL,ISJUBILADOS,FECHA) "
				+ 		 "VALUES('"+ textFieldNuevoEjercicio.getText() +"',("+ consultaEjercicio +"),'" + atributoCatorcenal +"', '"+ atributoSemanal +"','"+ atributoJubilado +"',+ convert(datetime,'"+ formatoDeFecha1.format(fecha)+"',101))";
		//System.out.println("sql: "+ sqlInsert);
		PoolNYCH nych = new PoolNYCH();
		Connection con =null;
		Statement st;
		ResultSet resultSet;
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
			JOptionPane.showMessageDialog(null, el, "Error de conexion", JOptionPane.ERROR_MESSAGE);
		}finally {
			try {
				con.close();
			} catch (SQLException ep) {
				ep.printStackTrace();
				StringWriter errors = new StringWriter();
				ep.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: "+ errors );
				JOptionPane.showMessageDialog(null, ep, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
			}
		}
		return resultado;
	}

	public String fechaFormat() {
		String convertido;
		Date result = new Date();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return convertido = formatter.format(result);
	}

	public  void windowOpened(ActionEvent e){
		final long SLEEP_TIME = 1/2 * 1000;
		SwingWorker<Void, Void> mySwingWorker = new SwingWorker<Void, Void>(){
			protected Void doInBackground() throws Exception {

				mostrarDatosEjercicio("");
				mostrarDatosFechasPeriodo("");
				mostrarDatosFechasPeriodoSemanal("");

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

	//	class ButtonHandler implements ActionListener{
	//	       public void actionPerformed(ActionEvent e){
	//	    	   tabbedPanePestañas.setSelectedIndex(1);
	//	       }
	//	}

	class TabSwitchingGui extends JFrame implements ActionListener {	


		//		 public TabSwitchingGui() {
		//			super("Switching Tabs");
		//			setDefaultCloseOperation(EXIT_ON_CLOSE);
		//			setSize(500,200);
		//			c = this.getContentPane();
		//			c.setLayout(new BorderLayout());
		//			initComponents();
		//		}

		/**
		 * Initialise the containers, layout and components.
		 */
		private void initComponents() {
			tabbedPanePestañas = new JTabbedPane();
			panelPestañaNuevoEjercicio = new JPanel();
			btnSeleccionar = new JButton();
			btnSelecc = new JButton();		

			// add actionListeners to the buttons // 
			btnSeleccionar.addActionListener(this);
			btnSelecc.addActionListener(this);		

			panelPestañaNuevoEjercicio.setLayout( new GridLayout(1,2));
			panelPestañaNuevoEjercicio.add(btnSeleccionar);
			panelPestañaNuevoEjercicio.add(btnSelecc);

			//addTestPanes();	

			// add both containers to the JFrame //
			add(tabbedPanePestañas, BorderLayout.NORTH);
			add(panelPestañaNuevoEjercicio, BorderLayout.SOUTH);		

		}

		/**
		 * Method just adds some tabs to the panel with
		 * automatically generated names and labels.
		 */
		//		private void addTestPanes() {
		//			for ( int i = 0; i < 5; ++i) {
		//				JPanel panel = new JPanel(false);
		//				JLabel lbl = new JLabel("Panel::"+(i+1));
		//				panel.setLayout(new FlowLayout()); // any layout would do
		//				panel.add(lbl);			
		//				tabbedPanePestañas.addTab("Item::" + i, panel);
		//			}
		//		}


		/**
		 * Implements the desired actions to perform on an event,
		 * in this case. Change the selected tab on a
		 * JTabbedPane.
		 */
		public void actionPerformed(ActionEvent evt) {		
			// check there is more than zero tabs
			if (tabbedPanePestañas.getTabCount() == 0) {
				System.err.println("No Tabs In Pane");
				return;
			}
			if (evt.getSource() == btnSeleccionar) {
				if(tabbedPanePestañas.getSelectedIndex() == 0)
					tabbedPanePestañas.setSelectedIndex(tabbedPanePestañas.getTabCount()-2);
				else 
					tabbedPanePestañas.setSelectedIndex(tabbedPanePestañas.getSelectedIndex()-1);			
			}
			//			if (evt.getSource() == btnSelecc) {			
			//				if(tabbedPanePestañas.getSelectedIndex() == tabbedPanePestañas.getTabCount()-1)
			//					tabbedPanePestañas.setSelectedIndex(0);
			//				else 
			//					tabbedPanePestañas.setSelectedIndex(tabbedPanePestañas.getSelectedIndex()+1);	
			//			}
		}

		public void actionPerformedSemanal(ActionEvent evt) {		
			// check there is more than zero tabs
			if (tabbedPanePestañas.getTabCount() == 0) {
				System.err.println("No Tabs In Pane");
				return;
			}
			if (evt.getSource() == btnSeleccionar) {
				if(tabbedPanePestañas.getSelectedIndex() == 0)
					tabbedPanePestañas.setSelectedIndex(tabbedPanePestañas.getTabCount()-1);
				else 
					tabbedPanePestañas.setSelectedIndex(tabbedPanePestañas.getSelectedIndex()-1);			
			}
			//			if (evt.getSource() == btnSelecc) {			
			//				if(tabbedPanePestañas.getSelectedIndex() == tabbedPanePestañas.getTabCount()-1)
			//					tabbedPanePestañas.setSelectedIndex(0);
			//				else 
			//					tabbedPanePestañas.setSelectedIndex(tabbedPanePestañas.getSelectedIndex()+1);	
			//			}
		}

		/*
		 * instantiate an instance of the GUI and show it to the world.
		 */
		//		public static void main(String[] args) {
		//			
		//			new TabSwitchingGui().setVisible(true);
		//		}


	}
}

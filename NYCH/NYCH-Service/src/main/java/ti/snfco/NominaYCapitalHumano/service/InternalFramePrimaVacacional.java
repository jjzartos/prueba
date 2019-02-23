package ti.snfco.NominaYCapitalHumano.service;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import org.slf4j.LoggerFactory;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.synth.SynthSeparatorUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.Dialog.ModalityType;
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
import java.util.Locale;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.border.MatteBorder;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import java.awt.SystemColor;
import javax.swing.SwingConstants;

public class InternalFramePrimaVacacional extends JInternalFrame {


	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(InternalFramePrimaVacacional.class);
	private static final long serialVersionUID = 1L;
	private JTextField textFieldBuscarEmp;
	JTable tableFechaPrimaVacacional = new JTable();
	TableRowSorter rowSorter;
	int IdBusquedaEmple = 1;
	JLabel lblAyudaDesp = new JLabel("ayudaDe$pensa");
	JLabel lblNombre = new JLabel("nombre");
	JLabel lblApPat = new JLabel("pat");
	JLabel lblApMat = new JLabel("mat");
	JLabel lblUR = new JLabel("dependencia");
	JLabel lblPuesto = new JLabel("puesto");
	JLabel lblFechaIngreso = new JLabel("fecha ingreso");
	JLabel lblSalario = new JLabel("$alario");
	JLabel lblSal = new JLabel("Salario:");
	JLabel lblAyudaADespensa = new JLabel("Ayuda a despensa:");
	JLabel lblSalarioTotal = new JLabel("Salario total:");
	JLabel lblSalTotal = new JLabel("0.0");
	JLabel lblSalarioDiario = new JLabel("Salario diario:");
	JLabel lblSalDiario = new JLabel("0.0");
	JTextField textFieldFaltas;
	JLabel lblFaltas = new JLabel("Faltas:");
	JLabel lblProporcion = new JLabel("Proporcion Prima Vacacional:");
	JLabel lblPropor = new JLabel("0.0");
	JLabel lblTotalAPagar = new JLabel("Total a Pagar:");
	JLabel lblTotPagar = new JLabel();
	JButton btnCalcular = new JButton("Calcular");
	JLabel lblDiasAPagar = new JLabel("Dias a Pagar:");
	JLabel lblIcon = new JLabel();
	JPanel panelprVac = new JPanel();
	private JTextField textFieldDias;
	private final JSeparator separator_2 = new JSeparator();
	JLabel lbldiasPP = new JLabel("");
	private final JSeparator separator_3 = new JSeparator();
	JLabel lblDetalle = new JLabel("Detalle");
	JLabel lblFlecha = new JLabel("");
	private final JLabel lblFondo = new JLabel("");
	JLabel lblTipoNominaOculta = new JLabel("New label");
	private final JButton btnVerEmpleados = new JButton("Ver Empleados");

	public InternalFramePrimaVacacional() {
		setBounds(0, 0, 1501, 670);
		setVisible(true);
		setTitle("Prima Vacacional");
		getContentPane().setLayout(null);

		JPanel panelPrimaVacacional = new JPanel();
		panelPrimaVacacional.setBackground(SystemColor.controlHighlight);
		panelPrimaVacacional.setBorder(new TitledBorder(null, "PRIMA VACACIONAL", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(216,224,232)));
		panelPrimaVacacional.setBounds(10, 0, 1971, 1051);
		getContentPane().add(panelPrimaVacacional);
		panelPrimaVacacional.setLayout(null);

		JLabel lblBuscarEmpleado = new JLabel("Seleccione el Empleado(a):");
		lblBuscarEmpleado.setBounds(20, 27, 228, 21);
		panelPrimaVacacional.add(lblBuscarEmpleado);

		textFieldBuscarEmp = new JTextField();
		textFieldBuscarEmp.setBackground(SystemColor.controlHighlight);
		//textFieldBuscarEmp.setBackground(new Color(240,240,240));
		textFieldBuscarEmp.setBorder(null);
		textFieldBuscarEmp.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				rowSorter.setRowFilter(RowFilter.regexFilter(textFieldBuscarEmp.getText().toUpperCase(), IdBusquedaEmple));
			}
		});
		textFieldBuscarEmp.setColumns(10);
		textFieldBuscarEmp.setBounds(68, 66, 292, 28);
		panelPrimaVacacional.add(textFieldBuscarEmp);

		JScrollPane scrollPanePrimaVacacional = new JScrollPane();
		scrollPanePrimaVacacional.setViewportBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		scrollPanePrimaVacacional.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPanePrimaVacacional.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPanePrimaVacacional.setBounds(20, 129, 993, 356);
		scrollPanePrimaVacacional.setViewportView(tableFechaPrimaVacacional);
		panelPrimaVacacional.add(scrollPanePrimaVacacional);

		JButton btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				redireccionarEmpleado();
				lblNombre.setVisible(true);
				lblApPat.setVisible(true);
				lblApMat.setVisible(true);
				lblUR.setVisible(true);
				lblPuesto.setVisible(true);
				lblFechaIngreso.setVisible(true);
				lblSalario.setVisible(true);
				lblAyudaDesp.setVisible(true);
				lblSal.setVisible(true);
				lblAyudaADespensa.setVisible(true);
				lblSalarioTotal.setVisible(true);
				lblSalTotal.setVisible(true);
				lblSalarioDiario.setVisible(true);
				lblSalDiario.setVisible(true);
				lblFaltas.setVisible(true);
				lblDiasAPagar.setVisible(true);
				lblProporcion.setVisible(true);
				lblPropor.setVisible(true);
				lblTotalAPagar.setVisible(true);
				lblTotPagar.setVisible(true);
				textFieldFaltas.setVisible(true);
				lbldiasPP.setVisible(false);
				//textFieldDiasaPagar.setEditable(false);
				textFieldDias.setVisible(true);
				btnCalcular.setVisible(true);
				panelprVac.setVisible(true);
				lblFlecha.setVisible(true);
				lblDetalle.setVisible(true);
			}
		});
		btnSeleccionar.setBounds(463, 496, 121, 30);
		btnSeleccionar.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("accept.png"))));
		panelPrimaVacacional.add(btnSeleccionar);

		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(176, 196, 222));
		separator.setBackground(new Color(176, 196, 222));
		separator.setBounds(20, 95, 360, 2);
		panelPrimaVacacional.add(separator);

		lblIcon.setBounds(20, 68, 46, 28);
		lblIcon.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("icons8_Search_32px_2.png"))));
		panelPrimaVacacional.add(lblIcon);
		panelprVac.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		panelprVac.setBounds(1023, 44, 435, 441);

		panelPrimaVacacional.add(panelprVac);
		panelprVac.setLayout(null);
		lblNombre.setBounds(23, 11, 205, 14);
		panelprVac.add(lblNombre);
		lblApPat.setBounds(23, 36, 166, 14);
		panelprVac.add(lblApPat);
		lblUR.setBounds(154, 89, 271, 14);
		panelprVac.add(lblUR);
		lblPuesto.setBounds(154, 114, 271, 14);
		panelprVac.add(lblPuesto);
		lblSal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSal.setBounds(241, 11, 79, 14);
		panelprVac.add(lblSal);
		lblAyudaADespensa.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAyudaADespensa.setBounds(205, 36, 121, 14);
		panelprVac.add(lblAyudaADespensa);
		lblSalario.setBounds(336, 11, 89, 14);
		panelprVac.add(lblSalario);
		lblAyudaDesp.setBounds(336, 36, 89, 14);
		panelprVac.add(lblAyudaDesp);
		lblApMat.setBounds(23, 64, 271, 14);
		panelprVac.add(lblApMat);
		lblFechaIngreso.setBounds(154, 139, 79, 14);
		panelprVac.add(lblFechaIngreso);
		textFieldFaltas = new JTextField();
		textFieldFaltas.setBounds(230, 232, 46, 30);
		panelprVac.add(textFieldFaltas);
		textFieldFaltas.setColumns(10);
		lblFaltas.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFaltas.setBounds(104, 238, 103, 14);
		panelprVac.add(lblFaltas);
		lblDiasAPagar.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDiasAPagar.setBounds(104, 274, 103, 14);
		panelprVac.add(lblDiasAPagar);
		lblSalTotal.setBounds(234, 302, 103, 14);
		panelprVac.add(lblSalTotal);
		lblSalarioTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSalarioTotal.setBounds(104, 309, 103, 14);
		panelprVac.add(lblSalarioTotal);
		lblSalarioDiario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSalarioDiario.setBounds(104, 337, 103, 14);
		panelprVac.add(lblSalarioDiario);
		lblSalDiario.setBounds(234, 330, 103, 14);
		panelprVac.add(lblSalDiario);
		lblProporcion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblProporcion.setBounds(27, 362, 180, 14);
		panelprVac.add(lblProporcion);
		lblPropor.setBounds(234, 357, 103, 14);
		panelprVac.add(lblPropor);
		lblTotalAPagar.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotalAPagar.setBounds(104, 387, 103, 14);
		panelprVac.add(lblTotalAPagar);
		//lblTotAPagar.setForeground(new Color(50, 205, 50));
		lblTotPagar.setBounds(230, 376, 46, 25);
		panelprVac.add(lblTotPagar);
		btnCalcular.setBounds(189, 405, 89, 25);
		panelprVac.add(btnCalcular);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(23, 195, 314, -8);
		panelprVac.add(separator_1);

		JLabel lblDias = new JLabel("Días:");
		lblDias.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDias.setBounds(161, 206, 46, 14);
		panelprVac.add(lblDias);

		textFieldDias = new JTextField();
		textFieldDias.setText("181");
		textFieldDias.setBounds(230, 192, 46, 33);
		panelprVac.add(textFieldDias);
		textFieldDias.setColumns(10);
		separator_2.setBounds(23, 178, 402, 2);

		panelprVac.add(separator_2);
		lbldiasPP.setForeground(new Color(50, 205, 50));
		lbldiasPP.setBounds(230, 269, 46, 22);

		panelprVac.add(lbldiasPP);
		separator_3.setBounds(233, 328, 46, 2);

		panelprVac.add(separator_3);

		JLabel lblNewLabel = new JLabel("Dependencia");
		lblNewLabel.setBounds(23, 89, 121, 14);
		panelprVac.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Puesto");
		lblNewLabel_1.setBounds(23, 114, 121, 14);
		panelprVac.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Fecha Ingreso");
		lblNewLabel_2.setBounds(23, 139, 121, 14);
		panelprVac.add(lblNewLabel_2);
		lblDetalle.setBounds(1023, 32, 46, 14);

		panelPrimaVacacional.add(lblDetalle);
		lblFlecha.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("download.png"))));
		lblFlecha.setBounds(1060, 27, 46, 20);

		panelPrimaVacacional.add(lblFlecha);


		btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				
				
				
				lbldiasPP.setVisible(true);
				lblTotPagar.setForeground(new Color(50, 205, 50));

				int fila = tableFechaPrimaVacacional.getSelectedRow();
				if(fila>=0) {
					String valorSalario= tableFechaPrimaVacacional.getValueAt(fila, 6).toString();
					String valorDespensa= tableFechaPrimaVacacional.getValueAt(fila, 9).toString();

					double sd =  Double.parseDouble(valorSalario);
					double des =  Double.parseDouble(valorDespensa);
					double salTotal = sd + des;
					System.out.println("Salario Total: "+salTotal);

					if(FormularioPrincipal.lblIsNomC.getText().equalsIgnoreCase("OK")) {
						System.out.println("nomina catorcenal - prima vacacional");


						double salDiario = salTotal / 14;
						System.out.println("Salario Diario C: "+salDiario);

						double dias =  Double.parseDouble(textFieldDias.getText());
						double faltas =  Double.parseDouble(textFieldFaltas.getText());

						double diasP =  dias-faltas;
						System.out.println("Dias a pagar: " + diasP);
						lbldiasPP.setText(String.valueOf(diasP));
						double proporcion = diasP*10/dias;
						System.out.println("Proporcion:" + proporcion);
						double totP = salDiario*proporcion*0.3;
						System.out.println("Total a pagar: "+ totP);

						DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
						simbolos.setDecimalSeparator('.');
						DecimalFormat df = new DecimalFormat("####.##",simbolos);
						lblSalTotal.setText(String.valueOf(df.format(salTotal)));
						lblSalDiario.setText(String.valueOf(df.format(salDiario)));
						lblPropor.setText(String.valueOf(df.format(proporcion)));
						lblTotPagar.setText(String.valueOf(df.format(totP)));
					}

					if(FormularioPrincipal.lblIsNomS.getText().equalsIgnoreCase("OK")) {
						System.out.println("nomina semanal - prima vacacional");

						double salDiario = salTotal / 7;
						System.out.println("Salario Diario S: "+salDiario);

						double dias =  Double.parseDouble(textFieldDias.getText());
						double faltas =  Double.parseDouble(textFieldFaltas.getText());

						double diasP =  dias-faltas;
						System.out.println("Dias a pagar: " + diasP);
						lbldiasPP.setText(String.valueOf(diasP));
						double proporcion = diasP*10/dias;
						System.out.println("Proporcion:" + proporcion);
						double totP = salDiario*proporcion*0.3;
						System.out.println("Total a pagar: "+ totP);

						DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
						simbolos.setDecimalSeparator('.');
						DecimalFormat df = new DecimalFormat("####.##",simbolos);
						lblSalTotal.setText(String.valueOf(df.format(salTotal)));
						lblSalDiario.setText(String.valueOf(df.format(salDiario)));
						lblPropor.setText(String.valueOf(df.format(proporcion)));
						lblTotPagar.setText(String.valueOf(df.format(totP)));
					}

					if(FormularioPrincipal.lblIsNomJ.getText().equalsIgnoreCase("OK")) {
						System.out.println("nomina jubilados - prima vacacional");

						double salDiario = salTotal / 30;
						System.out.println("Salario Diario S: "+salDiario);

						double dias =  Double.parseDouble(textFieldDias.getText());
						double faltas =  Double.parseDouble(textFieldFaltas.getText());

						double diasP =  dias-faltas;
						System.out.println("Dias a pagar: " + diasP);
						lbldiasPP.setText(String.valueOf(diasP));
						double proporcion = diasP*10/dias;
						System.out.println("Proporcion:" + proporcion);
						double totP = salDiario*proporcion*0.3;
						System.out.println("Total a pagar: "+ totP);

						DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
						simbolos.setDecimalSeparator('.');
						DecimalFormat df = new DecimalFormat("####.##",simbolos);
						lblSalTotal.setText(String.valueOf(df.format(salTotal)));
						lblSalDiario.setText(String.valueOf(df.format(salDiario)));
						lblPropor.setText(String.valueOf(df.format(proporcion)));
						lblTotPagar.setText(String.valueOf(df.format(totP)));
					}



					insertarPrimaVacacional();
					textFieldBuscarEmp.setText(null);
					textFieldDias.setText(null);
					textFieldFaltas.setText(null);
					lblSalTotal.setText(null);
					lblSalDiario.setText(null);
					lblPropor.setText(null);
					lblTotPagar.setText(null);

					//dispose();

				}
			}
		});
		lblTipoNominaOculta.setBounds(416, 30, 262, 14);
		lblTipoNominaOculta.setVisible(false);
		panelPrimaVacacional.add(lblTipoNominaOculta);
		btnVerEmpleados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarDatosEmpleado(lblTipoNominaOculta.getText());
				btnVerEmpleados.setEnabled(false);
			}
		});
		btnVerEmpleados.setBounds(395, 58, 149, 30);

		panelPrimaVacacional.add(btnVerEmpleados);



		lblFondo.setBounds(0, 0, 1906, 983);
		lblFondo.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("walll.png"))));
		panelPrimaVacacional.add(lblFondo);

	}

	public int insertarPrimaVacacional(){

		int resultado = 0;
		int claveInternaPercepcion=40;
		int claveInternaDeduccion=22;
		int clavePercepcionPrima=18;
		double valorClaveInternaPercepcion=0.0;
		double valorClaveInternaDeduccion=0.0;
		PoolNYCH nych = new PoolNYCH();
		Connection conPrima =null;
		int fila = tableFechaPrimaVacacional.getSelectedRow();
		String dependencia =  tableFechaPrimaVacacional.getValueAt(fila, 10).toString();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date diaHoy = Calendar.getInstance().getTime();
		String sqlInsert="";

		//		String text = textFieldTotPagar.getText();
		//		double DouVal = 0.0;
		//		try {
		//			DouVal = Double.valueOf(text).doubleValue();
		//			System.out.println("Double Valor: "+DouVal);
		//		} catch (NumberFormatException e) {
		//			//Log it if needed
		//			// intVal = //default fallback value;
		//		}


		DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
		simbolos.setDecimalSeparator('.');
		DecimalFormat formateador = new DecimalFormat("####.##",simbolos);

		String texto = lblTotPagar.getText();
		double valor = 0;
		try
		{
			// parse() lanza una ParseException en caso de fallo que hay que capturar.
			Number numero = formateador.parse(texto);
			valor = numero.doubleValue();
			System.out.println("Valor: "+valor);
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
			String id= tableFechaPrimaVacacional.getValueAt(fila, 0).toString();
			String idPuesto= tableFechaPrimaVacacional.getValueAt(fila, 4).toString();
			sqlInsert="INSERT INTO dbo.CALCULO_NOMINA (ID_EMPLEADO,ID_PERCEPCION,ID_DEDUCCION,VALOR_PERCEPCION,VALOR_DEDUCCION,ID_PUESTO,FECHA_CALCULO,ID_DEPENDENCIA,ID_TIPO_NOMINA,ESTATUS,PERIODO)" 
					+ ""
					+ "VALUES ("+ id +","+ clavePercepcionPrima +","+ claveInternaDeduccion +","+  valor +","
					+ ""+ valorClaveInternaDeduccion +","+ idPuesto +",'"+formatter.format(diaHoy)+"','"+dependencia+"',"
					+ ""+lblTipoNominaOculta.getText()+",'"+1+"','"+FormularioPrincipal.lblPeriodoNumero.getText()+"')";
			System.out.println("insert prima vacacional: "+sqlInsert);
			sqlInsert="";
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
			LOG.info("Excepcion: "+ errors );
			//JOptionPane.showMessageDialog(null, el, "Error de conexion", JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
		}finally {
			try {
				conPrima.close();
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



	public void redireccionarEmpleado() {
		InternalFrameMovimientos internalFrameMovimientos = new InternalFrameMovimientos();
		int fila = tableFechaPrimaVacacional.getSelectedRow();
		if(fila>=0) {
			//lblIdEmp.setText(tableFechaPrimaVacacional.getValueAt(fila, 0).toString());
			lblNombre.setText(tableFechaPrimaVacacional.getValueAt(fila, 1).toString());
			lblApPat.setText(tableFechaPrimaVacacional.getValueAt(fila, 2).toString());
			lblApMat.setText(tableFechaPrimaVacacional.getValueAt(fila, 3).toString());
			lblPuesto.setText(tableFechaPrimaVacacional.getValueAt(fila, 5).toString());
			lblFechaIngreso.setText(tableFechaPrimaVacacional.getValueAt(fila, 8).toString());
			lblUR.setText(tableFechaPrimaVacacional.getValueAt(fila, 7).toString());
			lblSalario.setText(tableFechaPrimaVacacional.getValueAt(fila, 6).toString());
			lblAyudaDesp.setText(tableFechaPrimaVacacional.getValueAt(fila, 9).toString());


		}else {
			JOptionPane.showMessageDialog(null, "Registro no Seleccionado");
		}
	}




	public void mostrarDatosEmpleado(String valor) {
		DefaultTableModel modelo = new DefaultTableModel()
		{

			public boolean isCellEditable(int row, int column) {
				//return super.isCellEditable(row, column);
				if(column==8){
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
		modelo.addColumn("ID PUESTO");
		modelo.addColumn("PUESTO");
		modelo.addColumn("SALARIO");
		modelo.addColumn("UNIDAD_RESPONSABLE");
		modelo.addColumn("FECHA INGRESO");
		modelo.addColumn("AYUDA A DESPENSA");
		modelo.addColumn("ID DESPENSA");


		tableFechaPrimaVacacional.setModel(modelo);
		tableFechaPrimaVacacional.setBackground(Color.WHITE);

		JTableHeader th = new JTableHeader();
		Color colorSilverLight=new Color(176, 196, 222);
		Color colorNegro=new Color(0, 0, 0);
		th = tableFechaPrimaVacacional.getTableHeader();
		Font fuente = new Font("Arial", Font.BOLD, 14); 
		th.setFont(fuente); 
		th.setBackground(colorSilverLight);
		th.setForeground(colorNegro);


		TableColumnModel columnModel = tableFechaPrimaVacacional.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(80);
		columnModel.getColumn(1).setPreferredWidth(200);
		columnModel.getColumn(2).setPreferredWidth(200);
		columnModel.getColumn(3).setPreferredWidth(200);
		columnModel.getColumn(4).setPreferredWidth(200);
		columnModel.getColumn(5).setPreferredWidth(300);
		columnModel.getColumn(6).setPreferredWidth(200);
		columnModel.getColumn(7).setPreferredWidth(200);
		columnModel.getColumn(8).setPreferredWidth(200);
		columnModel.getColumn(9).setPreferredWidth(200);
		columnModel.getColumn(10).setPreferredWidth(200);


		String sqlSelect="";
		sqlSelect = "SELECT DBO.EMPLEADOS.CLAVE,DBO.EMPLEADOS.NOMBRE,DBO.EMPLEADOS.APELLIDO_PATERNO,DBO.EMPLEADOS.APELLIDO_MATERNO,\r\n"
				+ "dbo.puestos.no_puesto,dbo.puestos.nombre_puesto,dbo.puestos.salario,DBO.DEPENDENCIAS.UNIDAD_RESPONSABLE,dbo.empleados.fecha_ingreso,DBO.CALCULO_NOMINA.valor_percepcion,dbo.dependencias.id_unidad\r\n"
				+ "from empleados\r\n"
				+ "left JOIN DBO.PUESTOS ON DBO.EMPLEADOS.ID_PUESTO = DBO.PUESTOS.no_puesto\r\n"
				+ "LEFT JOIN DBO.DEPENDENCIAS ON DBO.EMPLEADOS.ID_UNIDAD = DBO.DEPENDENCIAS.ID_UNIDAD\r\n"
				+ "LEFT JOIN DBO.EJERCICIOS ON DBO.EMPLEADOS.ID_EJERCICIOS = DBO.EJERCICIOS.ID_EJERCICIOS\r\n"
				+ "LEFT JOIN DBO.CALCULO_NOMINA ON DBO.EMPLEADOS.clave = DBO.CALCULO_NOMINA.id_empleado\r\n"
				+ "WHERE DBO.EMPLEADOS.id_ejercicios = '" + valor + "' and ELIMINAR_LOGICO='1' AND dbo.calculo_nomina.id_percepcion=26";//AND dbo.calculo_nomina.id_percepcion=26  \r\n";
		System.out.println("sql primavacacional: "+sqlSelect);
		PoolNYCH nych = new PoolNYCH();				
		Connection con =null;
		String datos[] = new String[12];
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
				datos[6] = resultSet.getString(7);
				datos[7] = resultSet.getString(8);
				datos[8] = resultSet.getString(9);
				datos[9] = resultSet.getString(10);
				datos[10] = resultSet.getString(11);

				//								double salario = Double.parseDouble(datos[7]);
				//								System.out.println("Salario: " + salario);
				//								double ayuDes = Double.parseDouble(datos[8]);
				//								System.out.println("Ayuda despensa: " + ayuDes);
				//								double salarioTotal = salario + ayuDes;
				//								System.out.println("Salario Total: " + salarioTotal);
				//								System.out.println("--");
				//								
				//								lblSalTotal.setText(String.valueOf(salarioTotal));




				modelo.addRow(datos);
			}
			rowSorter = new TableRowSorter(modelo);
			tableFechaPrimaVacacional.setRowSorter(rowSorter);

			tableFechaPrimaVacacional.setModel(modelo);
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

	//metodo para el tiempo de busqueda
	//Este es el evento que se ejecuta cuando un JFrame se carga
	public  void windowOpened(ActionEvent e){
		final long SLEEP_TIME = 1 * 1000;
		SwingWorker<Void, Void> mySwingWorker = new SwingWorker<Void, Void>(){
			protected Void doInBackground() throws Exception {

				//				mostrarDatosEmpleado(lblTipoNominaOculta.getText());

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

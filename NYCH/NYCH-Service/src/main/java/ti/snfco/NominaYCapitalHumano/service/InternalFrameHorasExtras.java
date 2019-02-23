package ti.snfco.NominaYCapitalHumano.service;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
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
import java.util.Calendar;
import java.util.Date;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

import org.slf4j.LoggerFactory;

import com.sun.jersey.api.representation.Form;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JRadioButton;

public class InternalFrameHorasExtras extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	InternalFrameEmpleado empleado = new InternalFrameEmpleado();
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(InternalFrameHorasExtras.class);
	private JTextField textFieldBuscarEmpleado;
	JTable tableDatosEmpleadosHE = new JTable();
	TableRowSorter rowSorter;
	int IdBusquedaEmple = 1;
	public String atributo = "";
	private TableRowSorter trsFiltro;
	JPanel panelHE = new JPanel();
	JLabel lblEmpleado = new JLabel("Empleado");
	private JTextField textFieldSalario;
	private JTextField textFieldSalarioEntreDias;
	public ButtonGroup groupButton = new ButtonGroup();
	private JTextField textFieldSalarioEntreOcho;
	private JTextField textFieldCuantasHE;
	private JTextField textFieldHEDobleApagar;
	JLabel lblFondo = new JLabel("");
	JLabel lblLeyendaHE = new JLabel("");
	JLabel lblTipoNominaOculta = new JLabel("New label");
	JLabel lblLeyendaHETriples = new JLabel("");
	private JTextField textFieldHETripleApagar;
	private JTextField textFieldHEaPagar;

	public InternalFrameHorasExtras() {
		//		Toolkit tk = Toolkit.getDefaultToolkit();
		//		Dimension dim = tk.getScreenSize();
		//		int ancho = (int) dim.getWidth() - 50;
		//		int alto = (int) dim.getHeight() - 220;
		//		setBounds(FormularioPrincipal.desktopPane.getX(), FormularioPrincipal.desktopPane.getY(), ancho, alto);
		setBounds(0, 0, 1501, 670);

		setToolTipText("Horas Extras");
		setVisible(true);
		setTitle("Horas Extras");
		getContentPane().setLayout(null);

		JPanel panelHorasExtras = new JPanel();
		panelHorasExtras.setBackground(SystemColor.controlHighlight);
		panelHorasExtras.setBounds(0, 0, 1545, 641);

		getContentPane().add(panelHorasExtras);
		panelHorasExtras.setLayout(null);

		JLabel label = new JLabel("Seleccione el Empleado(a):");
		label.setBounds(10, 28, 228, 21);
		panelHorasExtras.add(label);

		textFieldBuscarEmpleado = new JTextField();
		textFieldBuscarEmpleado.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("unchecked")
			@Override
			public void keyReleased(KeyEvent e) {
				//				int row = tableDatosEmpleadosHE.getRowCount();
				rowSorter.setRowFilter(
						RowFilter.regexFilter(textFieldBuscarEmpleado.getText().toUpperCase(), IdBusquedaEmple));
				//				tableDatosEmpleadosHE.changeSelection(row, 1, false, false);
				//				tableDatosEmpleadosHE.setSelectionBackground(Color.BLUE);
				// txtBusNombreKeyTyped(e);
			}
		});
		textFieldBuscarEmpleado.setColumns(10);
		textFieldBuscarEmpleado.setBorder(null);
		textFieldBuscarEmpleado.setBackground(SystemColor.controlHighlight);
		textFieldBuscarEmpleado.setBounds(58, 60, 312, 28);
		panelHorasExtras.add(textFieldBuscarEmpleado);

		JLabel lblIcon = new JLabel();
		lblIcon.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("icons8_Search_32px_2.png"))));
		lblIcon.setBounds(10, 60, 46, 28);
		panelHorasExtras.add(lblIcon);

		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(176, 196, 222));
		separator.setBackground(new Color(176, 196, 222));
		separator.setBounds(10, 89, 360, 2);
		panelHorasExtras.add(separator);

		JScrollPane scrollPaneDatosHorasExtras = new JScrollPane();
		scrollPaneDatosHorasExtras.setViewportBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		scrollPaneDatosHorasExtras.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneDatosHorasExtras.setBounds(10, 136, 980, 350);
		scrollPaneDatosHorasExtras.setViewportView(tableDatosEmpleadosHE);
		panelHorasExtras.add(scrollPaneDatosHorasExtras);

		JButton btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int fila = tableDatosEmpleadosHE.getSelectedRow();
				if (fila >= 0) {
					panelHE.setVisible(true);
					String id = tableDatosEmpleadosHE.getValueAt(fila, 0).toString();
					String empleado = tableDatosEmpleadosHE.getValueAt(fila, 1).toString();
					String ApPat = tableDatosEmpleadosHE.getValueAt(fila, 2).toString();
					String ApMat = tableDatosEmpleadosHE.getValueAt(fila, 3).toString();
					String salario = tableDatosEmpleadosHE.getValueAt(fila, 5).toString();
					lblEmpleado.setText(id + " - " + empleado + " " + ApPat + " " + ApMat);
					textFieldSalario.setText(salario);

				} else {
					JOptionPane.showMessageDialog(null, "No ha seleccionado ningún registro.");
				}
			}
		});
		btnSeleccionar.setBounds(381, 497, 152, 30);
		panelHorasExtras.add(btnSeleccionar);

		panelHE.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		panelHE.setBounds(1051, 136, 360, 494);
		panelHorasExtras.add(panelHE);
		panelHE.setLayout(null);

		lblEmpleado.setBounds(10, 11, 488, 14);
		panelHE.add(lblEmpleado);

		textFieldSalario = new JTextField();
		textFieldSalario.setBounds(140, 36, 134, 30);
		panelHE.add(textFieldSalario);
		textFieldSalario.setColumns(10);

		textFieldSalarioEntreDias = new JTextField();
		textFieldSalarioEntreDias.setColumns(10);
		textFieldSalarioEntreDias.setBounds(140, 172, 134, 30);
		panelHE.add(textFieldSalarioEntreDias);

		JButton btnNewButton = new JButton("Calcular");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
				simbolos.setDecimalSeparator('.');
				DecimalFormat df = new DecimalFormat("####.##", simbolos);

				int fila = tableDatosEmpleadosHE.getSelectedRow();
				if (fila >= 0) {

					String salario = tableDatosEmpleadosHE.getValueAt(fila, 5).toString();

					if(FormularioPrincipal.lblIsNomC.getText().equalsIgnoreCase("OK")) {

						double salarioE = Double.parseDouble(salario);
						System.out.println("Salario: " + salario);
						double salarioEntredias = salarioE / 14;
						System.out.println("salario entre dias: " + salarioEntredias);
						textFieldSalarioEntreDias.setText(String.valueOf(df.format(salarioEntredias)));

						double salarioEntreOcho = salarioEntredias / 8;
						System.out.println("salario entre 8 dias: " + salarioEntreOcho);
						textFieldSalarioEntreOcho.setText(String.valueOf(df.format(salarioEntreOcho)));

						System.out.println("horas extras: " + textFieldCuantasHE.getText());

						if(Integer.parseInt(textFieldCuantasHE.getText()) < 10) {
							int hours =  Integer.parseInt(textFieldCuantasHE.getText());
							System.out.println("hours: "+hours);

							double horasExtras = salarioEntreOcho * Double.parseDouble(textFieldCuantasHE.getText()) * 2;
							System.out.println("Cuantas horas extras son: " + horasExtras);
							textFieldHEDobleApagar.setText(String.valueOf(df.format(horasExtras)));
							textFieldHEaPagar.setText(String.valueOf(df.format(horasExtras)));
							lblLeyendaHE.setText("son "+ hours +" horas pagadas al doble");
							

						}else if(Integer.parseInt(textFieldCuantasHE.getText()) > 9){
							int hours =  Integer.parseInt(textFieldCuantasHE.getText()) - 9;
							System.out.println("hours: "+hours);

							double horasExtras = salarioEntreOcho * hours * 3;
							System.out.println("Cuantas horas extras son: " + horasExtras);
							textFieldHEDobleApagar.setText(String.valueOf(df.format(salarioEntreOcho*9*2)));
							textFieldHETripleApagar.setText(String.valueOf(df.format(horasExtras)));
							textFieldHEaPagar.setText(String.valueOf(df.format((salarioEntreOcho*9*2)+horasExtras)));
							lblLeyendaHE.setText("Son "+ (9) +" horas pagadas al doble.");
							lblLeyendaHETriples.setText("Son "+ hours +" horas pagadas al triple.");

						}

					}

					else if(FormularioPrincipal.lblIsNomS.getText().equalsIgnoreCase("OK")) {
						double salarioE = Double.parseDouble(salario);
						System.out.println("Salario: " + salario);
						double salarioEntredias = salarioE / 7;
						System.out.println("salario entre dias: " + salarioEntredias);
						textFieldSalarioEntreDias.setText(String.valueOf(df.format(salarioEntredias)));

						double salarioEntreOcho = salarioEntredias / 8;
						System.out.println("salario entre 8 dias: " + salarioEntreOcho);
						textFieldSalarioEntreOcho.setText(String.valueOf(df.format(salarioEntreOcho)));

						System.out.println("horas extras: " + textFieldCuantasHE.getText());

						if(Integer.parseInt(textFieldCuantasHE.getText()) < 10) {
							int hours =  Integer.parseInt(textFieldCuantasHE.getText());
							System.out.println("hours: "+hours);

							double horasExtras = salarioEntreOcho * Double.parseDouble(textFieldCuantasHE.getText()) * 2;
							System.out.println("Cuantas horas extras son: " + horasExtras);
							textFieldHEDobleApagar.setText(String.valueOf(df.format(horasExtras)));
							textFieldHEaPagar.setText(String.valueOf(df.format(horasExtras)));
							lblLeyendaHE.setText("son "+ hours +" horas pagadas al doble");
							

						}else if(Integer.parseInt(textFieldCuantasHE.getText()) > 9){
							int hours =  Integer.parseInt(textFieldCuantasHE.getText()) - 9;
							System.out.println("hours: "+hours);

							double horasExtras = salarioEntreOcho * hours * 3;
							System.out.println("Cuantas horas extras son: " + horasExtras);
							textFieldHEDobleApagar.setText(String.valueOf(df.format(salarioEntreOcho*9*2)));
							textFieldHETripleApagar.setText(String.valueOf(df.format(horasExtras)));
							textFieldHEaPagar.setText(String.valueOf(df.format((salarioEntreOcho*9*2)+horasExtras)));
							lblLeyendaHE.setText("Son "+ (9) +" horas pagadas al doble.");
							lblLeyendaHETriples.setText("Son "+ hours +" horas pagadas al triple.");

						}
					}



					insertHE();

										textFieldSalario.setText(null);
										textFieldSalarioEntreDias.setText(null);
										textFieldSalarioEntreOcho.setText(null);
										textFieldCuantasHE.setText(null);
										textFieldHEaPagar.setText(null);
										lblLeyendaHE.setText(null);
										lblLeyendaHETriples.setText(null);
										textFieldHEDobleApagar.setText(null);
										textFieldHETripleApagar.setText(null);

					dispose();


				} else {

				}

			}
		});
		btnNewButton.setBounds(140, 453, 89, 30);
		panelHE.add(btnNewButton);

		JLabel lblHrs = new JLabel("÷ 8 hrs.");
		lblHrs.setBounds(193, 202, 51, 14);
		panelHE.add(lblHrs);

		textFieldSalarioEntreOcho = new JTextField();
		textFieldSalarioEntreOcho.setColumns(10);
		textFieldSalarioEntreOcho.setBounds(140, 219, 134, 30);
		panelHE.add(textFieldSalarioEntreOcho);

		JLabel lblNewLabel_2 = new JLabel("¿Cuántas horas extras tiene el empleado?");
		lblNewLabel_2.setBounds(10, 88, 264, 14);
		panelHE.add(lblNewLabel_2);

		textFieldCuantasHE = new JTextField();
		textFieldCuantasHE.setBounds(140, 113, 134, 30);
		panelHE.add(textFieldCuantasHE);
		textFieldCuantasHE.setColumns(10);

		textFieldHEDobleApagar = new JTextField();
		textFieldHEDobleApagar.setForeground(new Color(34, 139, 34));
		textFieldHEDobleApagar.setColumns(10);
		textFieldHEDobleApagar.setBounds(140, 278, 134, 30);
		panelHE.add(textFieldHEDobleApagar);

		JLabel lblHorasExtrasA = new JLabel("Horas dobles a pagar");
		lblHorasExtrasA.setBounds(10, 286, 126, 14);
		panelHE.add(lblHorasExtrasA);
		lblLeyendaHE.setForeground(new Color(34, 139, 34));

		lblLeyendaHE.setBounds(10, 403, 340, 14);
		panelHE.add(lblLeyendaHE);

		JLabel lblNewLabel_3 = new JLabel("Salario");
		lblNewLabel_3.setBounds(10, 44, 76, 14);
		panelHE.add(lblNewLabel_3);

		JLabel lblSalarioDiario = new JLabel("Salario Diario");
		lblSalarioDiario.setBounds(10, 180, 76, 14);
		panelHE.add(lblSalarioDiario);

		JLabel lblSalarioXHora = new JLabel("Salario x Hora");
		lblSalarioXHora.setBounds(10, 227, 120, 14);
		panelHE.add(lblSalarioXHora);
		
		
		lblLeyendaHETriples.setBounds(10, 428, 340, 14);
		lblLeyendaHE.setForeground(new Color(34, 139, 34));
		panelHE.add(lblLeyendaHETriples);
		
		textFieldHETripleApagar = new JTextField();
		textFieldHETripleApagar.setForeground(new Color(34, 139, 34));
		textFieldHETripleApagar.setColumns(10);
		textFieldHETripleApagar.setBounds(140, 312, 134, 30);
		panelHE.add(textFieldHETripleApagar);
		
		JLabel lblHorasTriplesA = new JLabel("Horas triples a pagar");
		lblHorasTriplesA.setBounds(10, 320, 126, 14);
		panelHE.add(lblHorasTriplesA);
		
		textFieldHEaPagar = new JTextField();
		textFieldHEaPagar.setBounds(140, 353, 134, 30);
		panelHE.add(textFieldHEaPagar);
		textFieldHEaPagar.setColumns(10);
		
		JLabel lblTotalAPagar = new JLabel("Total a Pagar");
		lblTotalAPagar.setBounds(10, 361, 126, 14);
		panelHE.add(lblTotalAPagar);

		lblTipoNominaOculta.setBounds(414, 28, 353, 14);
		lblTipoNominaOculta.setVisible(false);
		panelHorasExtras.add(lblTipoNominaOculta);

		final JButton btnVerEmpleados = new JButton("Ver Empleados");
		btnVerEmpleados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarDatosEmpleadoHE(lblTipoNominaOculta.getText());
				btnVerEmpleados.setEnabled(false);
			}
		});
		btnVerEmpleados.setBounds(381, 58, 149, 30);
		panelHorasExtras.add(btnVerEmpleados);

		lblFondo.setBounds(0, 0, 1906, 983);
		lblFondo.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("walll.png"))));
		panelHorasExtras.add(lblFondo);

	}

	public int insertHE() {
		System.out.println("insertHE");
		int resultado = 0;
		int claveInternaDeduccion = 22;
		int clavePercepcionHE = 16;
		double valorClaveInternaDeduccion = 0.0;
		PoolNYCH nych = new PoolNYCH();
		Connection conHE = null;
		int fila = tableDatosEmpleadosHE.getSelectedRow();
		String dependencia =  tableDatosEmpleadosHE.getValueAt(fila, 8).toString();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date diaHoy = Calendar.getInstance().getTime();
		String sqlInsert = "";

		DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
		simbolos.setDecimalSeparator('.');
		DecimalFormat formateador = new DecimalFormat("####.##", simbolos);

		String texto = textFieldHEaPagar.getText();
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
			JOptionPane.showMessageDialog(null, "Error. El usuario ha escrito algo que no se puede convertir a número");
		}

		if (fila >= 0) {
			String id = tableDatosEmpleadosHE.getValueAt(fila, 0).toString();
			String idPuesto = tableDatosEmpleadosHE.getValueAt(fila, 7).toString();
			sqlInsert = "INSERT INTO dbo.CALCULO_NOMINA (ID_EMPLEADO,ID_PERCEPCION,ID_DEDUCCION,VALOR_PERCEPCION,VALOR_DEDUCCION,ID_PUESTO,FECHA_CALCULO,ID_DEPENDENCIA,ID_TIPO_NOMINA,ESTATUS,PERIODO)"
					+ "" + "VALUES (" + id + "," + clavePercepcionHE + "," + claveInternaDeduccion + "," + valor + ","
					+ "" + valorClaveInternaDeduccion + "," + idPuesto + ",'" +formatter.format(diaHoy) + "','"
					+ dependencia + "'," + "" + lblTipoNominaOculta.getText() + ",'"+1+"','"+FormularioPrincipal.lblPeriodoNumero.getText()+"')";
			System.out.println(sqlInsert);
//			sqlInsert="";
		}

		try {
			conHE = nych.datasource.getConnection();
			PreparedStatement pps = conHE.prepareStatement(sqlInsert);
			pps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Datos Guardados");
		} catch (SQLException el) {
			el.printStackTrace();
			StringWriter errors = new StringWriter();
			el.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: " + errors);
			JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");
		} finally {
			try {
				conHE.close();
			} catch (SQLException ep) {
				ep.printStackTrace();
				StringWriter errors = new StringWriter();
				ep.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: " + errors);
				JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");
			}
		}
		return resultado;

	}

	@SuppressWarnings("unlikely-arg-type")
	public void mostrarDatosEmpleadoHE(String valor) {
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
		modelo.addColumn("UNIDAD RESPONSABLE");
		modelo.addColumn("ID PUESTO");
		modelo.addColumn("ID DEPENDENCIA");

		tableDatosEmpleadosHE.setModel(modelo);
		tableDatosEmpleadosHE.setBackground(Color.WHITE);

		JTableHeader th = new JTableHeader();
		Color colorSilverLight = new Color(176, 196, 222);
		Color colorNegro = new Color(0, 0, 0);
		th = tableDatosEmpleadosHE.getTableHeader();
		Font fuente = new Font("Arial", Font.BOLD, 14);
		th.setFont(fuente);
		th.setBackground(colorSilverLight);
		th.setForeground(colorNegro);

		TableColumnModel columnModel = tableDatosEmpleadosHE.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(100);
		columnModel.getColumn(1).setPreferredWidth(200);
		columnModel.getColumn(2).setPreferredWidth(200);
		columnModel.getColumn(3).setPreferredWidth(200);
		columnModel.getColumn(4).setPreferredWidth(300);
		columnModel.getColumn(5).setPreferredWidth(100);
		columnModel.getColumn(6).setPreferredWidth(300);
		columnModel.getColumn(7).setPreferredWidth(100);
		columnModel.getColumn(8).setPreferredWidth(100);

		String sqlSelect = "";
		if (valor.equals("")) {
			sqlSelect = "select CLAVE,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,CURP,RFC,SDI from dbo.empleados WHERE ELIMINAR_LOGICO='"
					+ 1 + "' and DBO.EMPLEADOS.ID_EJERCICIOS = '" + valor + "'";// order by id_puesto
		} else {
			sqlSelect = "SELECT DBO.EMPLEADOS.CLAVE,DBO.EMPLEADOS.NOMBRE,DBO.EMPLEADOS.APELLIDO_PATERNO,DBO.EMPLEADOS.APELLIDO_MATERNO,\r\n"
					+ "	dbo.puestos.nombre_puesto,dbo.puestos.salario,DBO.DEPENDENCIAS.UNIDAD_RESPONSABLE,dbo.puestos.no_puesto,DBO.DEPENDENCIAS.ID_UNIDAD\r\n"
					+ "from empleados\r\n"
					+ "left JOIN DBO.PUESTOS ON DBO.EMPLEADOS.ID_PUESTO = DBO.PUESTOS.no_puesto\r\n"
					+ "LEFT JOIN DBO.DEPENDENCIAS ON DBO.EMPLEADOS.ID_UNIDAD = DBO.DEPENDENCIAS.ID_UNIDAD\r\n"
					+ "LEFT JOIN DBO.EJERCICIOS ON DBO.EMPLEADOS.ID_EJERCICIOS = DBO.EJERCICIOS.ID_EJERCICIOS\r\n"
					+ "WHERE DBO.EMPLEADOS.id_ejercicios = '" + valor + "' and ELIMINAR_LOGICO='1'\r\n";
			Object datos[] = new String[13];
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
					modelo.addRow(datos);
				}

				rowSorter = new TableRowSorter(modelo);
				tableDatosEmpleadosHE.setRowSorter(rowSorter);
				tableDatosEmpleadosHE.setModel(modelo);
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

	public void windowOpened(ActionEvent e) {
		final long SLEEP_TIME = 1 * 1000;
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

	@SuppressWarnings("unchecked")
	public void filtro() {
		int id = 0;
		//		if (comboBoxTipoNomina.getSelectedIndex() > 0) {
		//			id = comboBoxTipoNomina.getSelectedIndex();
		//			System.out.println("id: " + id);
		//		}
		trsFiltro.setRowFilter(RowFilter.regexFilter(textFieldBuscarEmpleado.getText().toUpperCase(), IdBusquedaEmple));
		// rowSorter.setRowFilter(RowFilter.regexFilter(textFieldBuscarEmpleado.getText().toUpperCase(),
		// IdBusquedaEmple));
		System.out.println("row: " + trsFiltro.getRowFilter());
	}

	private void txtBusNombreKeyTyped(java.awt.event.KeyEvent evt) {
		String texto = textFieldBuscarEmpleado.getText().toUpperCase();
		textFieldBuscarEmpleado.setText(texto);

		String selArt, desBd;
		String desBD = textFieldBuscarEmpleado.getText();
		int cdes = desBD.length();
		int row = 0;
		int i = 0;
		for (i = 0; i < tableDatosEmpleadosHE.getRowCount(); i++) {
			selArt = (String) tableDatosEmpleadosHE.getValueAt(i, 1);
			desBd = selArt.substring(0, cdes);
			if (desBd.equalsIgnoreCase(desBD.toUpperCase())) {
				row = i;
				i = tableDatosEmpleadosHE.getRowCount() + 1;
			}
		}
		tableDatosEmpleadosHE.changeSelection(row, 1, false, false);
		tableDatosEmpleadosHE.setSelectionBackground(Color.CYAN);

	}
}

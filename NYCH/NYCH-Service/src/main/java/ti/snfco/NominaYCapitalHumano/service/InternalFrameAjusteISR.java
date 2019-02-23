package ti.snfco.NominaYCapitalHumano.service;

import java.awt.SystemColor;
import java.awt.Toolkit;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.slf4j.LoggerFactory;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.MatteBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;

public class InternalFrameAjusteISR extends JInternalFrame {

	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(InternalFrameAjusteISR.class);
	private static final long serialVersionUID = 1L;
	public static JTextField textFieldSDI;
	public static JTextField textFieldSalarioTotal;
	public static JLabel lblNombreCompleto = new JLabel("Nombre completo");
	public static JTextField textFieldIngresosTotales;
	public static JTable tableTablaIsrAnual = new JTable();
	public static JTable tableSubsidioAnual = new JTable();
	public static JTextField textFieldDifContraLimite;
	public static JTextField textFieldImpMarginal;
	public static JTextField textFieldIsrCausado;
	public static JTextField textFieldSubsidioAlempleo = new JTextField();
	public static JTextField textFieldSubsidioPorPagar = new JTextField();
	public static JTextField textFieldIsrMenosSubxPagar;
	public static JTextField textFieldisrCobrado;
	public static JTextField textFieldIsrPorCobrar = new JTextField();
	JScrollPane scrollPaneISRAnual = new JScrollPane();
	JScrollPane scrollPaneSubsidioAnual = new JScrollPane();
	JButton btnSeleccionarsubsidioAnual = new JButton("Seleccionar");
	JButton btnCalcularSubXPagar = new JButton("...");
	JButton btnIsrCobrado = new JButton("...");
	JLabel lblSubsidioPorPagar = new JLabel("Subsidio por pagar");
	JLabel lblDiferenciaContraLimites = new JLabel("Diferencia contra limite");
	JLabel lblImp = new JLabel("Imp Marginal");
	JLabel lblIsrCausado = new JLabel("Isr causado");
	JLabel lblSubsidioAnual = new JLabel("Subsidio Anual");
	JLabel lblSubsidioAlEmpleo = new JLabel("Subsidio al empleo Pagado");
	JLabel lblIsrMenosSubsidio = new JLabel("Isr (-) subsidio por pagar");
	JLabel lblIsrCobrado = new JLabel("Isr cobrado");
	JLabel lblIsrPorCobrar = new JLabel("Isr por cobrar");
	JLabel lblIngresosTotales = new JLabel("Ingresos Totales");


	public InternalFrameAjusteISR() {
		setBounds(0, 45, 1500, 551);
		setVisible(true);
		setTitle("Ajuste al cálculo de ISR");
		getContentPane().setBackground(SystemColor.controlHighlight);
		getContentPane().setLayout(null);

		JLabel lblSDI = new JLabel("Salario Diario Real");
		lblSDI.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSDI.setBounds(0, 46, 113, 14);
		getContentPane().add(lblSDI);

		textFieldSDI = new JTextField();
		textFieldSDI.setBounds(123, 38, 129, 30);
		getContentPane().add(textFieldSDI);
		textFieldSDI.setColumns(10);

		JLabel lblSalarioTotal = new JLabel("Salario Total");
		lblSalarioTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSalarioTotal.setBounds(0, 79, 113, 14);
		getContentPane().add(lblSalarioTotal);

		textFieldSalarioTotal = new JTextField();
		textFieldSalarioTotal.setColumns(10);
		textFieldSalarioTotal.setBounds(123, 71, 129, 30);
		getContentPane().add(textFieldSalarioTotal);

		lblNombreCompleto.setBounds(10, 11, 319, 24);
		getContentPane().add(lblNombreCompleto);

		lblIngresosTotales.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIngresosTotales.setBounds(0, 112, 113, 14);
		getContentPane().add(lblIngresosTotales);

		textFieldIngresosTotales = new JTextField();
		textFieldIngresosTotales.setColumns(10);
		textFieldIngresosTotales.setBounds(123, 104, 129, 30);
		getContentPane().add(textFieldIngresosTotales);

		scrollPaneISRAnual.setViewportBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		scrollPaneISRAnual.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneISRAnual.setViewportView(tableTablaIsrAnual);
		scrollPaneISRAnual.setBounds(20, 172, 543, 300);

		getContentPane().add(scrollPaneISRAnual);

		JLabel lblNewLabel = new JLabel("Tabla Isr Anual");
		lblNewLabel.setBounds(203, 155, 129, 14);
		getContentPane().add(lblNewLabel);

		JButton btnNewButton = new JButton("Seleccionar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int fila = tableTablaIsrAnual.getSelectedRow();
				if(fila  >= 0) {
					String limiteInferior = tableTablaIsrAnual.getValueAt(fila, 1).toString();
					String limitesuperior = tableTablaIsrAnual.getValueAt(fila, 2).toString();
					String CuotaFija = tableTablaIsrAnual.getValueAt(fila, 3).toString();
					String tasaExcedente = tableTablaIsrAnual.getValueAt(fila, 4).toString();

					int confirmado = JOptionPane.showConfirmDialog(null, "Ha seleccionado el limite inferior: " + limiteInferior +" para la cantidad de: " + textFieldIngresosTotales.getText() + " es correcto?");
					if (JOptionPane.OK_OPTION == confirmado) {

						textFieldDifContraLimite.setVisible(true);
						lblDiferenciaContraLimites.setVisible(true);
						lblImp.setVisible(true);
						textFieldImpMarginal.setVisible(true);
						lblIsrCausado.setVisible(true);
						textFieldIsrCausado.setVisible(true);

						String ingreTot = textFieldIngresosTotales.getText();
						double difVsLimite = Double.parseDouble(ingreTot) - Double.parseDouble(limiteInferior);
						textFieldDifContraLimite.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(255, 255, 000)));
						textFieldDifContraLimite.setText(String.valueOf(difVsLimite));

						String impMargi = textFieldDifContraLimite.getText();
						double impMarginal = Double.parseDouble(impMargi) ;
						System.out.println("impuesto Marginal parseado: " + impMarginal);

						double	tasaEx = Double.parseDouble(tasaExcedente)/100;
						System.out.println("cuota fija " + tasaEx);

						double impMarginalFinal =  impMarginal * tasaEx;
						System.out.println("impuesto Marginal Final " + impMarginalFinal);
						textFieldImpMarginal.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(255, 255, 000)));
						textFieldImpMarginal.setText(String.valueOf(impMarginalFinal));

						double isrCausado = Double.parseDouble(textFieldImpMarginal.getText())+ Double.parseDouble(CuotaFija);
						System.out.println("isr causado: " + isrCausado);
						textFieldIsrCausado.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(255, 255, 000)));
						textFieldIsrCausado.setText(String.valueOf(isrCausado));

						lblSubsidioAnual.setVisible(true);
						scrollPaneSubsidioAnual.setVisible(true);
						lblIsrMenosSubsidio.setVisible(false);
						textFieldIsrMenosSubxPagar.setVisible(false);
						btnSeleccionarsubsidioAnual.setVisible(true);
						
						
						JOptionPane.showMessageDialog(null, "Seleccione la canttidad para el subsidio");
						

					}else if(JOptionPane.NO_OPTION == confirmado){
						textFieldDifContraLimite.setVisible(false);
						lblDiferenciaContraLimites.setVisible(false);
						lblImp.setVisible(false);
						textFieldImpMarginal.setVisible(false);
						lblIsrCausado.setVisible(false);
						textFieldIsrCausado.setVisible(false);
						lblSubsidioAnual.setVisible(false);
						scrollPaneSubsidioAnual.setVisible(false);
						lblSubsidioAlEmpleo.setVisible(false);
						textFieldSubsidioAlempleo.setVisible(false);
						lblSubsidioPorPagar.setVisible(false);
						textFieldSubsidioPorPagar.setVisible(false);
						btnCalcularSubXPagar.setVisible(false);
						lblIsrMenosSubsidio.setVisible(false);
						textFieldIsrMenosSubxPagar.setVisible(false);
						JOptionPane.showMessageDialog(null, "Seleccione el correcto");
					}

				}else {
					JOptionPane.showMessageDialog(null, "Seleccione el limite inferior");
				}

			}
		});
		btnNewButton.setBounds(434, 476, 132, 23);
		getContentPane().add(btnNewButton);

		lblDiferenciaContraLimites.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDiferenciaContraLimites.setBounds(300, 46, 129, 14);
		getContentPane().add(lblDiferenciaContraLimites);

		textFieldDifContraLimite = new JTextField();
		textFieldDifContraLimite.setColumns(10);
		textFieldDifContraLimite.setBounds(434, 38, 129, 30);
		getContentPane().add(textFieldDifContraLimite);

		textFieldImpMarginal = new JTextField();
		textFieldImpMarginal.setColumns(10);
		textFieldImpMarginal.setBounds(434, 71, 129, 30);
		getContentPane().add(textFieldImpMarginal);


		lblImp.setHorizontalAlignment(SwingConstants.RIGHT);
		lblImp.setBounds(300, 79, 129, 14);
		getContentPane().add(lblImp);


		lblIsrCausado.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIsrCausado.setBounds(300, 112, 129, 14);
		getContentPane().add(lblIsrCausado);

		textFieldIsrCausado = new JTextField();
		textFieldIsrCausado.setColumns(10);
		textFieldIsrCausado.setBounds(434, 104, 129, 30);
		getContentPane().add(textFieldIsrCausado);

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(new Color(211, 211, 211));
		separator.setBackground(Color.WHITE);
		separator.setBounds(573, 38, 5, 480);
		getContentPane().add(separator);

		scrollPaneSubsidioAnual.setViewportBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		scrollPaneSubsidioAnual.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneSubsidioAnual.setBounds(588, 172, 543, 300);
		scrollPaneSubsidioAnual.setViewportView(tableSubsidioAnual);
		getContentPane().add(scrollPaneSubsidioAnual);

		lblSubsidioAnual.setBounds(779, 155, 129, 14);
		getContentPane().add(lblSubsidioAnual);
		btnSeleccionarsubsidioAnual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int fila = tableSubsidioAnual.getSelectedRow();
				if(fila  >= 0) {
					String paraIngresosDe = tableSubsidioAnual.getValueAt(fila, 1).toString();
					String hastaIngresosDe = tableSubsidioAnual.getValueAt(fila, 2).toString();
					String cantidad = tableSubsidioAnual.getValueAt(fila, 3).toString();

					int confirmado = JOptionPane.showConfirmDialog(null, "Ha seleccionado el limite inferior: " + paraIngresosDe +" para la cantidad de: " + textFieldIngresosTotales.getText() + " es correcto?");
					if (JOptionPane.OK_OPTION == confirmado) {

						lblSubsidioAlEmpleo.setVisible(true);
						textFieldSubsidioAlempleo.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(237, 98, 110)));
						textFieldSubsidioAlempleo.setVisible(true);
						lblSubsidioPorPagar.setVisible(false);
						textFieldSubsidioPorPagar.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(237, 98, 110)));
						textFieldSubsidioPorPagar.setVisible(false);
						btnCalcularSubXPagar.setVisible(true);

						lblIsrMenosSubsidio.setVisible(false);
						lblIsrCobrado.setVisible(false);
						textFieldisrCobrado.setVisible(false);


					}else if(JOptionPane.NO_OPTION == confirmado){
						lblSubsidioAlEmpleo.setVisible(false);
						textFieldSubsidioAlempleo.setVisible(false);
						lblSubsidioPorPagar.setVisible(false);
						textFieldSubsidioPorPagar.setVisible(false);
						btnCalcularSubXPagar.setVisible(false);

						lblIsrMenosSubsidio.setVisible(true);
						textFieldIsrMenosSubxPagar.setVisible(true);
						lblIsrCobrado.setVisible(false);
						lblIsrPorCobrar.setVisible(false);
						textFieldisrCobrado.setVisible(false);
						textFieldIsrPorCobrar.setVisible(false);
						JOptionPane.showMessageDialog(null, "Seleccione el correcto");
					}

				}else {
					JOptionPane.showMessageDialog(null, "Seleccione la cantidad de subsidio");
				}

			}
		});
		btnSeleccionarsubsidioAnual.setBounds(1018, 476, 113, 23);

		getContentPane().add(btnSeleccionarsubsidioAnual);
		lblSubsidioAlEmpleo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSubsidioAlEmpleo.setBounds(588, 46, 174, 14);

		getContentPane().add(lblSubsidioAlEmpleo);
		textFieldSubsidioAlempleo.setColumns(10);
		textFieldSubsidioAlempleo.setBounds(772, 38, 129, 30);

		getContentPane().add(textFieldSubsidioAlempleo);
		lblSubsidioPorPagar.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSubsidioPorPagar.setBounds(581, 79, 181, 14);

		getContentPane().add(lblSubsidioPorPagar);
		textFieldSubsidioPorPagar.setColumns(10);
		textFieldSubsidioPorPagar.setBounds(772, 71, 129, 30);

		getContentPane().add(textFieldSubsidioPorPagar);
		btnCalcularSubXPagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = tableSubsidioAnual.getSelectedRow();
				if(fila  >= 0) {
					String paraIngresosDe = tableSubsidioAnual.getValueAt(fila, 1).toString();
					String hastaIngresosDe = tableSubsidioAnual.getValueAt(fila, 2).toString();
					String cantidad = tableSubsidioAnual.getValueAt(fila, 3).toString();


					lblSubsidioPorPagar.setVisible(true);
					textFieldSubsidioPorPagar.setVisible(true);
					lblIsrMenosSubsidio.setVisible(true); 
					

					double subsidioxPagar = Double.parseDouble(cantidad) - Double.parseDouble(textFieldSubsidioAlempleo.getText());
					textFieldSubsidioPorPagar.setText(String.valueOf(subsidioxPagar));

					double isrMenosxPagar = Double.parseDouble(textFieldIsrCausado.getText()) - Double.parseDouble(textFieldSubsidioPorPagar.getText());
					textFieldIsrMenosSubxPagar.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(237, 98, 110)));
					textFieldIsrMenosSubxPagar.setVisible(true);
					textFieldIsrMenosSubxPagar.setText(String.valueOf(isrMenosxPagar));

					lblIsrCobrado.setVisible(true);
					textFieldisrCobrado.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(243, 159, 29)));
					textFieldisrCobrado.setVisible(true);
					btnIsrCobrado.setVisible(true);

				}
			}
		});
		btnCalcularSubXPagar.setToolTipText("Calcular subsidio por pagar");
		btnCalcularSubXPagar.setBounds(904, 38, 26, 30);

		getContentPane().add(btnCalcularSubXPagar);

		lblIsrMenosSubsidio.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIsrMenosSubsidio.setBounds(581, 112, 181, 14);
		getContentPane().add(lblIsrMenosSubsidio);

		textFieldIsrMenosSubxPagar = new JTextField();
		textFieldIsrMenosSubxPagar.setColumns(10);
		textFieldIsrMenosSubxPagar.setBounds(772, 104, 129, 30);
		getContentPane().add(textFieldIsrMenosSubxPagar);

		lblIsrCobrado.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIsrCobrado.setBounds(941, 46, 97, 14);
		getContentPane().add(lblIsrCobrado);

		textFieldisrCobrado = new JTextField();
		textFieldisrCobrado.setColumns(10);
		textFieldisrCobrado.setBounds(1041, 38, 129, 30);
		getContentPane().add(textFieldisrCobrado);
		btnIsrCobrado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int fila = tableSubsidioAnual.getSelectedRow();
				if(fila  >= 0) {
					String paraIngresosDe = tableSubsidioAnual.getValueAt(fila, 1).toString();
					String hastaIngresosDe = tableSubsidioAnual.getValueAt(fila, 2).toString();
					String cantidad = tableSubsidioAnual.getValueAt(fila, 3).toString();


					lblIsrPorCobrar.setVisible(true);
					textFieldIsrPorCobrar.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(243, 159, 29)));
					textFieldIsrPorCobrar.setVisible(true);
					double isrxCobrar = Double.parseDouble(textFieldIsrMenosSubxPagar.getText()) - Double.parseDouble(textFieldisrCobrado.getText());
					textFieldIsrPorCobrar.setText(String.valueOf(isrxCobrar));

				}
			}
		});

		btnIsrCobrado.setToolTipText("Calcular subsidio por pagar");
		btnIsrCobrado.setBounds(1173, 38, 26, 30);
		getContentPane().add(btnIsrCobrado);
		lblIsrPorCobrar.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIsrPorCobrar.setBounds(941, 79, 97, 14);

		getContentPane().add(lblIsrPorCobrar);
		textFieldIsrPorCobrar.setColumns(10);
		textFieldIsrPorCobrar.setBounds(1041, 71, 129, 30);

		getContentPane().add(textFieldIsrPorCobrar);
		
		JButton btnCalcularSalarioPagado = new JButton("...");
		btnCalcularSalarioPagado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String respuesta = JOptionPane.showInputDialog(null, "Cuántas catorcenas trabajó?");
				double res = Double.parseDouble(respuesta);
				System.out.println("respuesta: " + res);
				
				double sal = Double.parseDouble(textFieldSalarioTotal.getText())  * res;
				System.out.println("sal: " + sal);
				
				textFieldSalarioTotal.setText(String.valueOf(sal));
				
				
				String aguinaldoGravado = InternalFrameAguinaldo.lblAguinGravado.getText();
				System.out.println("aguinaldoGravado: " + aguinaldoGravado);
				double IngresosTot = Double.parseDouble(aguinaldoGravado) + sal + Double.parseDouble(InternalFrameAguinaldo.lblPrimaGrav.getText());
				System.out.println("Ingresos Totales: " + IngresosTot);
				textFieldIngresosTotales.setText(String.valueOf(IngresosTot));
				
				lblIngresosTotales.setVisible(true);
				textFieldIngresosTotales.setVisible(true);
				
			}
		});
		btnCalcularSalarioPagado.setBounds(254, 70, 26, 31);
		getContentPane().add(btnCalcularSalarioPagado);
		
				JLabel lblFoto = new JLabel("");
				lblFoto.setIcon(
						new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("walll.png"))));
				lblFoto.setBounds(0, 0, 1535, 522);
				getContentPane().add(lblFoto);

	}

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

	/*
	 * Metodo Para la busquedda del catalogo de Datos subsidio Anual
	 */
	public void mostrarSubsidioAnual() {
		final DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("ID");
		modelo.addColumn("PARA INGRESOS DE");
		modelo.addColumn("HASTA INGRESOS DE");
		modelo.addColumn("SUBSIDIO");
		tableSubsidioAnual.setModel(modelo);
		tableSubsidioAnual.setBackground(Color.WHITE);

		JTableHeader th = new JTableHeader();
		Color colorSilverLight=new Color(176, 196, 222);
		Color colorNegro=new Color(0, 0, 0);
		th = tableSubsidioAnual.getTableHeader();
		Font fuente = new Font("Arial", Font.BOLD, 14); 
		th.setFont(fuente); 
		th.setBackground(colorSilverLight);
		th.setForeground(colorNegro);

		TableColumnModel columnModel = tableSubsidioAnual.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(100);
		columnModel.getColumn(1).setPreferredWidth(200);
		columnModel.getColumn(2).setPreferredWidth(200);
		columnModel.getColumn(3).setPreferredWidth(200);

		String sqlSelect="";
		sqlSelect = "SELECT ID_SUBSIDIO_ANUAL,PARA_INGRESOS_DE,HASTA_INGRESOS_DE,CANTIDAD_DE_SUBSIDIO FROM dbo.subsidio_anual";
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
				modelo.addRow(datos);
			}//FIN DEL WHILE

			//			modelo.addTableModelListener(new TableModelListener() {
			//				public void tableChanged(TableModelEvent e) {
			//					if(e.getType() == TableModelEvent.UPDATE) {
			//						JOptionPane.showMessageDialog(null, "Ha cambiado el valor, ¿Es correcto?");
			//						String colName_LIMITE_INFERIOR =  "LIMITE_INFERIOR";
			//						String colName_LIMITE_SUPERIOR =  "LIMITE_SUPERIOR";
			//						String colName_CUOTA_FIJA =  "CUOTA_FIJA";
			//						String colName_PORCENTAJE_EXCEDENTE = "PORCENTAJE_EXCEDENTE";
			//
			//						
			//						ArrayList<Double> listaDatosISRAnual = new ArrayList<Double>();
			//						listaDatosISRAnual = getListaDatosISRAnual();
			//						System.out.println("*********");
			//						for(int i = 0; i<listaDatosISRAnual.size();i++){
			//							System.out.println("indiceDatos[isr] anual: "+ i + " |valorDatos[isr] anual: " + listaDatosISRAnual.get(i));
			//						}
			//
			//						String sqlupdt ="";
			//						if(e.getColumn()==1 && e.getFirstRow()==0) {
			//							sqlupdt="UPDATE DBO.tabla_isr_anual SET "+ colName_LIMITE_INFERIOR +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where limite_inferior= '"+listaDatosISRAnual.get(0) +"'";
			//						}
			//
			//						else if(e.getColumn()==1 && e.getFirstRow()==1) {
			//							sqlupdt="UPDATE DBO.tabla_isr_anual SET "+ colName_LIMITE_INFERIOR +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where limite_inferior= '"+listaDatosISRAnual.get(3) +"'";
			//						}
			//
			//						else if(e.getColumn()==1 && e.getFirstRow()==2) {
			//
			//							sqlupdt="UPDATE DBO.tabla_isr_anual SET "+ colName_LIMITE_INFERIOR +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where limite_inferior= '"+listaDatosISRAnual.get(6) +"'";
			//						}
			//						else if(e.getColumn()==1 && e.getFirstRow()==3) {
			//
			//							sqlupdt="UPDATE DBO.tabla_isr_anual SET "+ colName_LIMITE_INFERIOR +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where limite_inferior= '"+listaDatosISRAnual.get(9) +"'";
			//						}
			//						else if(e.getColumn()==1 && e.getFirstRow()==4) {
			//
			//							sqlupdt="UPDATE DBO.tabla_isr_anual SET "+ colName_LIMITE_INFERIOR +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where limite_inferior= '"+listaDatosISRAnual.get(12) +"'";
			//						}
			//						else if(e.getColumn()==1 && e.getFirstRow()==5) {
			//
			//							sqlupdt="UPDATE DBO.tabla_isr_anual SET "+ colName_LIMITE_INFERIOR +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where limite_inferior= '"+listaDatosISRAnual.get(15) +"'";
			//						}
			//
			//						else if(e.getColumn()==1 && e.getFirstRow()==6) {
			//
			//							sqlupdt="UPDATE DBO.tabla_isr_anual SET "+ colName_LIMITE_INFERIOR +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where limite_inferior= '"+listaDatosISRAnual.get(18) +"'";
			//						}
			//
			//						else if(e.getColumn()==1 && e.getFirstRow()==7) {
			//
			//							sqlupdt="UPDATE DBO.tabla_isr_anual SET "+ colName_LIMITE_INFERIOR +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where limite_inferior= '"+listaDatosISRAnual.get(21) +"'";
			//						}
			//						else if(e.getColumn()==1 && e.getFirstRow()==8) {
			//
			//							sqlupdt="UPDATE DBO.tabla_isr_anual SET "+ colName_LIMITE_INFERIOR +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where limite_inferior= '"+listaDatosISRAnual.get(24) +"'";
			//						}
			//						else if(e.getColumn()==1 && e.getFirstRow()==9) {
			//
			//							sqlupdt="UPDATE DBO.tabla_isr_anual SET "+ colName_LIMITE_INFERIOR +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where limite_inferior= '"+listaDatosISRAnual.get(27) +"'";
			//						}
			//						else if(e.getColumn()==1 && e.getFirstRow()==10) {
			//
			//							sqlupdt="UPDATE DBO.tabla_isr_anual SET "+ colName_LIMITE_INFERIOR +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where limite_inferior= '"+listaDatosISRAnual.get(30) +"'";
			//						}
			//						///////
			//
			//						if(e.getColumn()==3 && e.getFirstRow()==0) {
			//
			//							sqlupdt="UPDATE DBO.tabla_isr_anual SET "+ colName_CUOTA_FIJA +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where cuota_fija= '"+listaDatosISRAnual.get(1) +"'";
			//						}
			//
			//						else if(e.getColumn()==3 && e.getFirstRow()==1) {
			//
			//							sqlupdt="UPDATE DBO.tabla_isr_anual SET "+ colName_CUOTA_FIJA +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where cuota_fija= '"+listaDatosISRAnual.get(4) +"'";
			//						}
			//
			//						else if(e.getColumn()==3 && e.getFirstRow()==2) {
			//
			//							sqlupdt="UPDATE DBO.tabla_isr_anual SET "+ colName_CUOTA_FIJA +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where cuota_fija= '"+listaDatosISRAnual.get(7) +"'";
			//						}
			//
			//						else if(e.getColumn()==3 && e.getFirstRow()==3) {
			//
			//							sqlupdt="UPDATE DBO.tabla_isr_anual SET "+ colName_CUOTA_FIJA +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where cuota_fija= '"+listaDatosISRAnual.get(10) +"'";
			//						}
			//						else if(e.getColumn()==3 && e.getFirstRow()==4) {
			//
			//							sqlupdt="UPDATE DBO.tabla_isr_anual SET "+ colName_CUOTA_FIJA +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where cuota_fija= '"+listaDatosISRAnual.get(13) +"'";
			//						}
			//						else if(e.getColumn()==3 && e.getFirstRow()==5) {
			//
			//							sqlupdt="UPDATE DBO.tabla_isr_anual SET "+ colName_CUOTA_FIJA +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where cuota_fija= '"+listaDatosISRAnual.get(16) +"'";
			//						}
			//						else if(e.getColumn()==3 && e.getFirstRow()==6) {
			//
			//							sqlupdt="UPDATE DBO.tabla_isr_anual SET "+ colName_CUOTA_FIJA +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where cuota_fija= '"+listaDatosISRAnual.get(19) +"'";
			//						}
			//						else if(e.getColumn()==3 && e.getFirstRow()==7) {
			//
			//							sqlupdt="UPDATE DBO.tabla_isr_anual SET "+ colName_CUOTA_FIJA +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where cuota_fija= '"+listaDatosISRAnual.get(22) +"'";
			//						}
			//						else if(e.getColumn()==3 && e.getFirstRow()==8) {
			//
			//							sqlupdt="UPDATE DBO.tabla_isr_anual SET "+ colName_CUOTA_FIJA +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where cuota_fija= '"+listaDatosISRAnual.get(25) +"'";
			//						}
			//						else if(e.getColumn()==3 && e.getFirstRow()==9) {
			//
			//							sqlupdt="UPDATE DBO.tabla_isr_anual SET "+ colName_CUOTA_FIJA +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where cuota_fija= '"+listaDatosISRAnual.get(28) +"'";
			//						}
			//						else if(e.getColumn()==3 && e.getFirstRow()==10) {
			//
			//							sqlupdt="UPDATE DBO.tabla_isr_anual SET "+ colName_CUOTA_FIJA +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where cuota_fija= '"+listaDatosISRAnual.get(31) +"'";
			//						}
			//						//////
			//						if(e.getColumn()==4 && e.getFirstRow()==0) {
			//
			//							sqlupdt="UPDATE DBO.tabla_isr_anual SET "+ colName_PORCENTAJE_EXCEDENTE +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where PORCENTAJE_EXCEDENTE= '"+listaDatosISRAnual.get(2) +"'";
			//						}
			//						else if(e.getColumn()==4 && e.getFirstRow()==1) {
			//
			//							sqlupdt="UPDATE DBO.tabla_isr_anual SET "+ colName_PORCENTAJE_EXCEDENTE +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where PORCENTAJE_EXCEDENTE= '"+listaDatosISRAnual.get(5) +"'";
			//						}
			//						else if(e.getColumn()==4 && e.getFirstRow()==2) {
			//
			//							sqlupdt="UPDATE DBO.tabla_isr_anual SET "+ colName_PORCENTAJE_EXCEDENTE +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where PORCENTAJE_EXCEDENTE= '"+listaDatosISRAnual.get(8) +"'";
			//						}
			//						else if(e.getColumn()==4 && e.getFirstRow()==3) {
			//
			//							sqlupdt="UPDATE DBO.tabla_isr_anual SET "+ colName_PORCENTAJE_EXCEDENTE +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where PORCENTAJE_EXCEDENTE= '"+listaDatosISRAnual.get(11) +"'";
			//						}
			//						else if(e.getColumn()==4 && e.getFirstRow()==4) {
			//
			//							sqlupdt="UPDATE DBO.tabla_isr_anual SET "+ colName_PORCENTAJE_EXCEDENTE +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where PORCENTAJE_EXCEDENTE= '"+listaDatosISRAnual.get(14) +"'";
			//						}
			//						else if(e.getColumn()==4 && e.getFirstRow()==5) {
			//
			//							sqlupdt="UPDATE DBO.tabla_isr_anual SET "+ colName_PORCENTAJE_EXCEDENTE +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where PORCENTAJE_EXCEDENTE= '"+listaDatosISRAnual.get(17) +"'";
			//						}
			//						else if(e.getColumn()==4 && e.getFirstRow()==6) {
			//
			//							sqlupdt="UPDATE DBO.tabla_isr_anual SET "+ colName_PORCENTAJE_EXCEDENTE +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where PORCENTAJE_EXCEDENTE= '"+listaDatosISRAnual.get(20) +"'";
			//						}
			//						else if(e.getColumn()==4 && e.getFirstRow()==7) {
			//
			//							sqlupdt="UPDATE DBO.tabla_isr_anual SET "+ colName_PORCENTAJE_EXCEDENTE +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where PORCENTAJE_EXCEDENTE= '"+listaDatosISRAnual.get(23) +"'";
			//						}
			//						else if(e.getColumn()==4 && e.getFirstRow()==8) {
			//
			//							sqlupdt="UPDATE DBO.tabla_isr_anual SET "+ colName_PORCENTAJE_EXCEDENTE +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where PORCENTAJE_EXCEDENTE= '"+listaDatosISRAnual.get(26) +"'";
			//						}
			//						else if(e.getColumn()==4 && e.getFirstRow()==9) {
			//
			//							sqlupdt="UPDATE DBO.tabla_isr_anual SET "+ colName_PORCENTAJE_EXCEDENTE +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where PORCENTAJE_EXCEDENTE= '"+listaDatosISRAnual.get(29) +"'";
			//						}
			//						else if(e.getColumn()==4 && e.getFirstRow()==10) {
			//
			//							sqlupdt="UPDATE DBO.tabla_isr_anual SET "+ colName_PORCENTAJE_EXCEDENTE +" = '"+ modeloPuestos.getValueAt(e.getFirstRow(), e.getColumn()) +"' where PORCENTAJE_EXCEDENTE= '"+listaDatosISRAnual.get(32) +"'";
			//						}
			//
			//						System.out.println(sqlupdt);
			//						PoolNYCH nych1 = new PoolNYCH();
			//						Connection con1 = null;
			//						try {
			//							con1 = nych1.datasource.getConnection();
			//							PreparedStatement pps = con1.prepareStatement(sqlupdt);
			//							pps.executeUpdate();
			//							JOptionPane.showMessageDialog(null, "Datos Actualizados");
			//							//dispose();
			//						} catch (SQLException se) {
			//							se.printStackTrace();
			//							StringWriter errors = new StringWriter();
			//							se.printStackTrace(new PrintWriter(errors));
			//							LOG.info("Excepcion: "+ errors );
			//							JOptionPane.showMessageDialog(null, "Datos No Actualizados");
			//						}finally {
			//							try {
			//								con1.close();
			//							} catch (SQLException ep) {
			//								ep.printStackTrace();
			//								StringWriter errors = new StringWriter();
			//								ep.printStackTrace(new PrintWriter(errors));
			//								LOG.info("Excepcion: "+ errors );
			//								JOptionPane.showMessageDialog(null, ep, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
			//							}
			//						}
			//					}//fin del if e.getype
			//
			//				}
			//			});
			tableSubsidioAnual.setModel(modelo);
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
}

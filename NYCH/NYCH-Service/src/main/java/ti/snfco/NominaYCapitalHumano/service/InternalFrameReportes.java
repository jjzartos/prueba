package ti.snfco.NominaYCapitalHumano.service;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog.ModalityType;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.border.MatteBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import org.slf4j.LoggerFactory;

public class InternalFrameReportes extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(InternalFrameReportes.class);
	InternalFrameNuevoEmpleado ifne = new InternalFrameNuevoEmpleado();
	InternalFrameEmpleado ife =  new InternalFrameEmpleado();
	JTable tableEmpledosDependencia = new JTable();
	JTable tableEmpledosDependenciaFalsa = new JTable();
	JTable tableEmpledosMovimientos = new JTable();
	JTable tableEmpledosAyuda =  new JTable();
	JTable tableMovDeducciones =  new JTable();
	JTable tableMovDeduccionIMSS =  new JTable();
	JTable tableMovDeduccionIMUVI =  new JTable();
	JTable tableMovDeduccionAnticipoNomina =  new JTable();
	JTable tableMovDeduccionPensionAlimentcia =  new JTable();
	@SuppressWarnings("rawtypes")
	JComboBox comboBox = new JComboBox();
	private XWPFDocument documentoReporte = new XWPFDocument();
	JButton btnGenerarReporte = new JButton("Generar Reporte");
	JLabel lblSalarios = new JLabel("Salarios");
	JLabel lblAyudaADespensa = new JLabel("Ayuda a Despensa");
	JLabel lblIsr = new JLabel("ISR");
	JLabel lblImss = new JLabel("Imss");
	JLabel lblImuvi = new JLabel("Imuvi");
	JLabel lblAnticipoDeNomina = new JLabel("Anticipo de Nomina");
	JLabel lblPensionAlimenticia = new JLabel("Pension Alimenticia");
	JScrollPane scrollPaneMov = new JScrollPane();
	JScrollPane scrollPaneAyuda = new JScrollPane();
	JScrollPane scrollPaneMovDeducciones = new JScrollPane();
	JScrollPane scrollPaneDedImss = new JScrollPane();
	JScrollPane scrollPaneImuvi = new JScrollPane();
	JScrollPane scrollPaneAnticipoNomina = new JScrollPane();
	JScrollPane scrollPanePensionAlimenticia = new JScrollPane();

	public InternalFrameReportes() {
		setBounds(0, 0, 1500, 701);
		setVisible(true);
		setTitle("Reportes");
		getContentPane().setLayout(null);

		JPanel panelReportes = new JPanel();
		//panelReportes.setBackground(new Color(147,140,147));
		panelReportes.setBounds(0, 0, 1484, 903);
		getContentPane().add(panelReportes);


		btnGenerarReporte.setBounds(581, 25, 120, 55);
		btnGenerarReporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


				javax.swing.filechooser.FileNameExtensionFilter filtroWord=new FileNameExtensionFilter(".docx", "docx");
				final JFileChooser miArchivo=new JFileChooser();
				
				miArchivo.setFileFilter(filtroWord);
				int aceptar=miArchivo.showSaveDialog(null);
				//miArchivo.setCurrentDirectory(new File("\\192.168.202.50\\ExpedientesNYCH"));
//				miArchivo.setCurrentDirectory(new File("C:\\Users\\DeveloperTI\\Desktop\\Nomina"));
				miArchivo.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				if(aceptar==JFileChooser.APPROVE_OPTION){
					File fileWord=miArchivo.getSelectedFile();
					String nombreAr=fileWord.getName();
					if(nombreAr.indexOf('.')==-1){
						nombreAr+=".docx";
						fileWord=new File(fileWord.getParentFile(), nombreAr);
					}
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
					JOptionPane.showMessageDialog(null,"Archivo creado");
					try {
						FileOutputStream output=new FileOutputStream(fileWord);
						Date fechaMov = Calendar.getInstance().getTime();
						SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
						//int fila = tableEmpledosDependencia.getSelectedRow();
						String periodo ="PERIODO CORRESPONDIENTE "+FormularioPrincipal.lblPeriodoNumero.getText();

						XWPFParagraph titulo1 = documentoReporte.createParagraph();
						XWPFRun runTitulo1 = titulo1.createRun();
						runTitulo1.setBold(true);
						//						runTitulo1.setUnderline(UnderlinePatterns.WORDS);

						//						XWPFParagraph image = documentoReporte.createParagraph();
						//						image.setAlignment(ParagraphAlignment.RIGHT);
						//							
						//						XWPFRun imageRun = image.createRun();
						//						imageRun.setTextPosition(20);
						//							
						//						Path imagePath = Paths.get(ClassLoader.getSystemResource("srfveda.png").toURI());
						//						imageRun.addPicture(Files.newInputStream(imagePath),
						//						XWPFDocument.PICTURE_TYPE_PNG, imagePath.getFileName().toString(),
						//						Units.toEMU(50), Units.toEMU(50));

						XWPFParagraph titulo = documentoReporte.createParagraph();
						XWPFRun runTitulo = titulo.createRun();
						titulo.setAlignment(ParagraphAlignment.LEFT);
						//titulo.setSpacingBetween(0.0);
						//							runTitulo.setFontFamily("Microsoft Sans Serif");
						runTitulo.setFontFamily("Calibri");
						//runTitulo.setBold(true);
						runTitulo.setFontSize(8);
						//runTitulo.setUnderline(UnderlinePatterns.WORDS);
						runTitulo1.setText("Fecha: "+formatoDeFecha.format(fechaMov));
						runTitulo1.addBreak();
						runTitulo1.setText("NOMINA MUNICIPAL DE SAN FRANCISCO DEL RINCÓN");
						runTitulo1.addBreak();
						runTitulo1.setText(""+periodo);
						runTitulo1.addBreak();
						runTitulo1.setText("DEPENDENCIA: ");runTitulo1.setText(""+tableEmpledosDependencia.getValueAt(0, 6));
						runTitulo.setText("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

						for(int i=0;i<tableEmpledosDependencia.getRowCount();i++) {
							String id = tableEmpledosDependencia.getValueAt(i, 0).toString();
							System.out.println("id de los empleados de la dependencia:" + id);
							System.out.println("----------------*");

							DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
							DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);

							String inputText = tableEmpledosDependenciaFalsa.getValueAt(i, 20).toString();
							Date date = inputFormat.parse(inputText);
							String outputText = outputFormat.format(date);

							runTitulo.setText("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
							runTitulo.setText("NOMBRE: ");runTitulo.setText(" "+tableEmpledosDependencia.getValueAt(i, 1)+" "+tableEmpledosDependencia.getValueAt(i, 2)+" "+tableEmpledosDependencia.getValueAt(i, 3));runTitulo.addBreak();
							runTitulo.setText("PUESTO: ");runTitulo.setText(" "+tableEmpledosDependencia.getValueAt(i, 4));runTitulo.addBreak();
							runTitulo.setText("CURP: ");runTitulo.setText(" "+tableEmpledosDependenciaFalsa.getValueAt(i, 17));runTitulo.addBreak();
							runTitulo.setText("RFC: ");runTitulo.setText(" "+tableEmpledosDependenciaFalsa.getValueAt(i, 18));runTitulo.addBreak();
							runTitulo.setText("REG.IMSS: ");runTitulo.setText(" "+tableEmpledosDependenciaFalsa.getValueAt(i, 19));runTitulo.addBreak();
							runTitulo.setText("FECHA INGRESO: ");runTitulo.setText(" "+outputText);runTitulo.addBreak();
							runTitulo.setText("CUENTA BANCARIA: "+tableEmpledosDependenciaFalsa.getValueAt(i, 21));
							runTitulo.addBreak();runTitulo.addBreak();
							runTitulo.setText("              PERCEPCIONES                                                                        DEDUCCIONES");	
							runTitulo.addBreak();
							runTitulo.setText("Salario                                         "+tableEmpledosMovimientos.getValueAt(i, 3));
							//ISR
							for(int s=0;s<tableMovDeducciones.getRowCount();s++) {
								String idISR = tableMovDeducciones.getValueAt(s, 0).toString();
								System.out.println("id ISR:" + idISR);
								String valorISR = tableMovDeducciones.getValueAt(s, 4).toString();
								String IdDentroDeGrupo = id;
								System.out.println("Id dentro de grupo:" + IdDentroDeGrupo);
								String idABuscar = idISR;
								System.out.println("id a Buscar:" + idABuscar);
								boolean resultado = idABuscar.matches(IdDentroDeGrupo);//.contains(IdDentroDeGrupo);
								System.out.println("se encontró?: " + resultado);
								System.out.println("*************");

								if(resultado){
									runTitulo.setText("                                    "+"ISR               "+valorISR	);runTitulo.addBreak();
								}else{
									//JOptionPane.showMessageDialog(null,"id no encontrado");
								}

							}//fin del for de isr
							//imss
							runTitulo.setText("Ayuda a Despensa                      "+tableEmpledosAyuda.getValueAt(i, 3));
							for(int im=0;im<tableMovDeduccionIMSS.getRowCount();im++) {
								String idIMSS = tableMovDeduccionIMSS.getValueAt(im, 0).toString();
								String valorIMSS = tableMovDeduccionIMSS.getValueAt(im, 4).toString();

								String IdDentroDeGrupo = id;
								//								System.out.println("Id Dentro de Conjunto de Id´s: "+ IdDentroDeGrupo);
								String idABuscar = idIMSS;
								//								System.out.println("Id A Buscar: "+ idABuscar);
								boolean resultado = idABuscar.matches(IdDentroDeGrupo);

								if(resultado){
									//								    System.out.println("id encontrado");
									runTitulo.setText("                                    "+"IMSS              "+valorIMSS	);runTitulo.addBreak();
								}else{
									//									System.out.println("id no encontrado");
									//JOptionPane.showMessageDialog(null,"id no encontrado");
								}

							}//fin del for de imss
							//anticipo de nomina
							//runTitulo.addBreak();
							for(int j=0;j<tableMovDeduccionAnticipoNomina.getRowCount();j++) {
								String idAntNom = tableMovDeduccionAnticipoNomina.getValueAt(j, 0).toString();
								String valorAntNom = tableMovDeduccionAnticipoNomina.getValueAt(j, 4).toString();

								String IdDentroDeGrupo = id;
								//								System.out.println("Id Dentro de Conjunto de Id´s: "+ IdDentroDeGrupo);
								String idABuscar = idAntNom;
								//								System.out.println("Id A Buscar: "+ idABuscar);
								boolean resultado = idABuscar.matches(IdDentroDeGrupo);

								if(resultado){
									//								    System.out.println("id encontrado");
									runTitulo.setText("                                                                                                     "+"ANTICIPO DE NOMINA              "+valorAntNom	);runTitulo.addBreak();
								}else{
									//									System.out.println("id no encontrado");
									//JOptionPane.showMessageDialog(null,"id no encontrado");
								}

							}//fin del for de anticipo de nomina
							
							//IMUVI
							for(int k=0;k<tableMovDeduccionIMUVI.getRowCount();k++) {
								String idImuvi = tableMovDeduccionIMUVI.getValueAt(k, 0).toString();
								String valorImuvi = tableMovDeduccionIMUVI.getValueAt(k, 4).toString();

								String IdDentroDeGrupo = id;
								//								System.out.println("Id Dentro de Conjunto de Id´s: "+ IdDentroDeGrupo);
								String idABuscar = idImuvi;
								//								System.out.println("Id A Buscar: "+ idABuscar);
								boolean resultado = idABuscar.matches(IdDentroDeGrupo);

								if(resultado){
									//								    System.out.println("id encontrado");
									runTitulo.setText("                                                                                                     "+"IMUVI                                           "+valorImuvi);runTitulo.addBreak();
								}else{
									//									System.out.println("id NO encontrado");
									//JOptionPane.showMessageDialog(null,"id no encontrado");
								}

							}//fin del for de imuvi
							//imuvi
							for(int h=0;h<tableMovDeduccionPensionAlimentcia.getRowCount();h++) {
								String idPA = tableMovDeduccionPensionAlimentcia.getValueAt(h, 0).toString();
								String valorPA = tableMovDeduccionPensionAlimentcia.getValueAt(h, 4).toString();

								String IdDentroDeGrupo = id;
								//								System.out.println("Id Dentro de Conjunto de Id´s: "+ IdDentroDeGrupo);
								String idABuscar = idPA;
								//								System.out.println("Id A Buscar: "+ idABuscar);
								boolean resultado = idABuscar.matches(IdDentroDeGrupo);

								if(resultado){
									//								    System.out.println("id encontrado");
									runTitulo.setText("                                                                                                     "+"PENSION ALIMENTICIA            "+valorPA);
								}else{
									//									System.out.println("id NO encontrado");
									//JOptionPane.showMessageDialog(null,"id no encontrado");
								}

							}//fin del for de imuvi
							runTitulo.addBreak();
							runTitulo.setText("                                                                                                  ");runTitulo.addBreak();
							runTitulo.setText("                                                                                                  ");runTitulo.addBreak();
							runTitulo.setText("                                                                                                                    FIRMA______________________________________________");
							runTitulo.addBreak();
						}

						//						}else {
						//							JOptionPane.showMessageDialog(null, "Seleccione un empleado");
						//						}
						documentoReporte.write(output);
						output.close();
						output.flush();
						dispose();
					}catch(Exception ec) {
						ec.printStackTrace();
//						JOptionPane.showMessageDialog(null, "No se puede crear el archivo porque tiene uno anterior abierto");
						JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");
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
		});
		panelReportes.setLayout(null);
		panelReportes.add(btnGenerarReporte);

		//		JButton btnReporteJasper = new JButton("Jasper Reports");
		//		btnReporteJasper.setBounds(156, 384, 114, 89);
		//		btnReporteJasper.addActionListener(new ActionListener() {
		//			public void actionPerformed(ActionEvent e) {
		//				Utilidades.windowOpened(e);
		//				PoolNYCH nych = new PoolNYCH();
		//				Connection con =null;
		//				try {
		//
		//					con = nych.datasource.getConnection();
		//
		//				}catch(Exception ep) {
		//					ep.printStackTrace();
		//					StringWriter errors = new StringWriter();
		//					ep.printStackTrace(new PrintWriter(errors));
		//					LOG.info("Excepcion: "+ errors );
		//				}
		//			}
		//		});
		//		panelReportes.add(btnReporteJasper);

		comboBox.setBounds(10, 48, 442, 30);
		panelReportes.add(comboBox);

		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboBox.getSelectedIndex()>=0){
					llenarTablaEmpleadosPorDependencia(comboBox.getSelectedIndex(),FormularioPrincipal.lblIdTipoNomina.getText());//empleados real
					llenarTablaEmpleadosPorDependenciaFalso(comboBox.getSelectedIndex());//empleados fake
					mostrarDatosMovimientosPercepciones(comboBox.getSelectedIndex());
					mostrarDatosAyuda(comboBox.getSelectedIndex());
					mostrarMovDeduccionISR(comboBox.getSelectedIndex());
					mostrarMovDeduccionIMSS(comboBox.getSelectedIndex());
					mostrarMovDeduccionIMUVI(comboBox.getSelectedIndex());
					mostrarMovDeduccionANTICIPONOMINA(comboBox.getSelectedIndex());
					mostrarMovDeduccionPENSIONALIMENTICIA(comboBox.getSelectedIndex());
					
					btnGenerarReporte.setVisible(true);
					lblSalarios.setVisible(true);
					lblAyudaADespensa.setVisible(true);
					lblIsr.setVisible(true);
					lblImss.setVisible(true);
					lblImuvi.setVisible(true);
					lblAnticipoDeNomina.setVisible(true);
					lblPensionAlimenticia.setVisible(true);
					scrollPaneMov.setVisible(true);
					scrollPaneAyuda.setVisible(true);
					scrollPaneMovDeducciones.setVisible(true);
					scrollPaneDedImss.setVisible(true);
					scrollPaneImuvi.setVisible(true);
					scrollPaneAnticipoNomina.setVisible(true);
					scrollPanePensionAlimenticia.setVisible(true);
				}
			}
		});
		btnNewButton.setBounds(462, 50, 109, 30);
		panelReportes.add(btnNewButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 114, 691, 216);
		scrollPane.setViewportView(tableEmpledosDependencia);
		panelReportes.add(scrollPane);

		JLabel lblNewLabel = new JLabel("Selecciona la dependencia");
		lblNewLabel.setBounds(20, 25, 328, 21);
		panelReportes.add(lblNewLabel);

		scrollPaneMov.setViewportBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		scrollPaneMov.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneMov.setBounds(10, 377, 691, 132);
		scrollPaneMov.setViewportView(tableEmpledosMovimientos);
		scrollPaneMov.setVisible(true);
		panelReportes.add(scrollPaneMov);

		scrollPaneAyuda.setViewportBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		scrollPaneAyuda.setBounds(10, 520, 691, 144);
		scrollPaneAyuda.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneAyuda.setViewportView(tableEmpledosAyuda);
		scrollPaneAyuda.setVisible(true);
		panelReportes.add(scrollPaneAyuda);

		scrollPaneMovDeducciones.setViewportBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		scrollPaneMovDeducciones.setBounds(714, 31, 760, 106);
		scrollPaneMovDeducciones.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneMovDeducciones.setViewportView(tableMovDeducciones);
		scrollPaneMovDeducciones.setVisible(true);
		panelReportes.add(scrollPaneMovDeducciones);

		scrollPaneDedImss.setViewportBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		scrollPaneDedImss.setBounds(711, 163, 763, 106);
		scrollPaneDedImss.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneDedImss.setViewportView(tableMovDeduccionIMSS);
		scrollPaneDedImss.setVisible(true);
		panelReportes.add(scrollPaneDedImss);

		scrollPaneImuvi.setViewportBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		scrollPaneImuvi.setBounds(711, 292, 763, 106);
		scrollPaneImuvi.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneImuvi.setViewportView(tableMovDeduccionIMUVI);
		scrollPaneImuvi.setVisible(true);
		panelReportes.add(scrollPaneImuvi);

		scrollPaneAnticipoNomina.setViewportBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		scrollPaneAnticipoNomina.setBounds(711, 419, 763, 116);
		scrollPaneAnticipoNomina.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneAnticipoNomina.setViewportView(tableMovDeduccionAnticipoNomina);
		scrollPaneAnticipoNomina.setVisible(true);
		panelReportes.add(scrollPaneAnticipoNomina);

		scrollPanePensionAlimenticia.setViewportBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(176, 196, 222)));
		scrollPanePensionAlimenticia.setBounds(711, 569, 763, 95);
		scrollPanePensionAlimenticia.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPanePensionAlimenticia.setViewportView(tableMovDeduccionPensionAlimentcia);
		scrollPanePensionAlimenticia.setVisible(true);
		panelReportes.add(scrollPanePensionAlimenticia);

		JLabel lblNewLabel_1 = new JLabel("Plantilla de la dependencia");
		lblNewLabel_1.setBounds(10, 89, 312, 14);
		panelReportes.add(lblNewLabel_1);

		lblSalarios.setBounds(10, 352, 312, 14);
		panelReportes.add(lblSalarios);

		lblAyudaADespensa.setBounds(10, 509, 312, 14);
		panelReportes.add(lblAyudaADespensa);

		lblIsr.setBounds(714, 11, 312, 14);
		panelReportes.add(lblIsr);

		lblImss.setBounds(711, 148, 312, 14);
		panelReportes.add(lblImss);

		lblImuvi.setBounds(711, 280, 312, 14);
		panelReportes.add(lblImuvi);

		lblAnticipoDeNomina.setBounds(711, 407, 312, 14);
		panelReportes.add(lblAnticipoDeNomina);

		lblPensionAlimenticia.setBounds(711, 544, 312, 14);
		panelReportes.add(lblPensionAlimenticia);

		JLabel lblfoto = new JLabel("");
		lblfoto.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("walll.png"))));
		lblfoto.setBounds(0, 11, 1484, 661);
		panelReportes.add(lblfoto);

	}

	public void mostrarMovDeduccionISR(int valor) {
		DefaultTableModel modeloMovimientosDeducciones = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {
				if (column == 11) {
					return true;
				} else {
					return false;
				}
			}
		};
		modeloMovimientosDeducciones.addColumn("ID EMPLEADO");
		modeloMovimientosDeducciones.addColumn("ID PUESTO");
		modeloMovimientosDeducciones.addColumn("ID UNIDAD");
		modeloMovimientosDeducciones.addColumn("ID DEDUCCION");
		modeloMovimientosDeducciones.addColumn("VALOR DEDUCCION");
		modeloMovimientosDeducciones.addColumn("PUESTO");
		modeloMovimientosDeducciones.addColumn("DEDUCCION DESCRIPCION");
		modeloMovimientosDeducciones.addColumn("DEPENDENCIA");

		tableMovDeducciones.setModel(modeloMovimientosDeducciones);

		JTableHeader th = new JTableHeader();
		Color colorSilverLight=new Color(176, 196, 222);
		Color colorNegro=new Color(0, 0, 0);
		th = tableMovDeducciones.getTableHeader();
		Font fuente = new Font("Arial", Font.BOLD, 14); 
		th.setFont(fuente); 
		th.setBackground(colorSilverLight);
		th.setForeground(colorNegro);

		TableColumnModel columnModel = tableMovDeducciones.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(150);
		columnModel.getColumn(1).setPreferredWidth(150);
		columnModel.getColumn(2).setPreferredWidth(300);
		columnModel.getColumn(3).setPreferredWidth(300);
		columnModel.getColumn(4).setPreferredWidth(300);
		columnModel.getColumn(5).setPreferredWidth(300);
		columnModel.getColumn(6).setPreferredWidth(300);
		columnModel.getColumn(7).setPreferredWidth(300);

		String sqlMov = "select dbo.REPORTE_NOMINA_DEDUCCIONES.ID_EMPLEADO,dbo.puestos.NO_PUESTO,dbo.REPORTE_NOMINA_DEDUCCIONES.ID_UNIDAD,dbo.REPORTE_NOMINA_DEDUCCIONES.ID_DEDUCCION,dbo.REPORTE_NOMINA_DEDUCCIONES.VALOR_DEDUCCION,\r\n" + 
				"dbo.puestos.NOMBRE_PUESTO as NOMBRE_PUESTO,dbo.ATRIBUTO_TIPO_DEDUCCION.DESCRIPCION AS DEDUCCION_DESCRIPCION,DBO.DEPENDENCIAS.UNIDAD_RESPONSABLE\r\n" + 
				"from REPORTE_NOMINA_DEDUCCIONES \r\n" + 
				"left JOIN DBO.PUESTOS ON DBO.REPORTE_NOMINA_DEDUCCIONES.ID_PUESTO = DBO.PUESTOS.no_puesto\r\n" + 
				"left JOIN DBO.ATRIBUTO_TIPO_DEDUCCION ON DBO.REPORTE_NOMINA_DEDUCCIONES.ID_DEDUCCION = DBO.ATRIBUTO_TIPO_DEDUCCION.ID_DEDUCCION\r\n" + 
				"LEFT JOIN DBO.DEPENDENCIAS ON DBO.REPORTE_NOMINA_DEDUCCIONES.ID_UNIDAD = DBO.DEPENDENCIAS.ID_UNIDAD \r\n" + 
				"WHERE  DBO.REPORTE_NOMINA_DEDUCCIONES.ID_UNIDAD="+valor+" and dbo.REPORTE_NOMINA_DEDUCCIONES.ID_DEDUCCION=2 and dbo.REPORTE_NOMINA_DEDUCCIONES.periodo is null\r\n" + 
				"order by dbo.REPORTE_NOMINA_DEDUCCIONES.ID_EMPLEADO";

//		System.out.println("mostrarMovDeduccionISR: " + sqlMov);
		String datos[] = new String[9];
		PoolNYCH nych = new PoolNYCH();
		Connection con =null;
		Statement st;
		ResultSet resultSet;
		try {
			con = nych.datasource.getConnection();
			st = con.createStatement();
			resultSet = st.executeQuery(sqlMov);
			while(resultSet.next()) {
				datos[0] = resultSet.getString(1);
				datos[1] = resultSet.getString(2);
				datos[2] = resultSet.getString(3);
				datos[3] = resultSet.getString(4);
				datos[4] = resultSet.getString(5);
				datos[5] = resultSet.getString(6);
				datos[6] = resultSet.getString(7);
				datos[7] = resultSet.getString(8);
				modeloMovimientosDeducciones.addRow(datos);
			}

			tableMovDeducciones.setModel(modeloMovimientosDeducciones);
		}catch(Exception el) {
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
		}//FIN DEL FINALLY
	}//fin del metodo

	public void mostrarMovDeduccionIMSS(int valor) {
		DefaultTableModel modeloMovimientosDeduccionImss = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {
				if (column == 11) {
					return true;
				} else {
					return false;
				}
			}
		};
		modeloMovimientosDeduccionImss.addColumn("ID EMPLEADO");
		modeloMovimientosDeduccionImss.addColumn("ID PUESTO");
		modeloMovimientosDeduccionImss.addColumn("ID UNIDAD");
		modeloMovimientosDeduccionImss.addColumn("ID DEDUCCION");
		modeloMovimientosDeduccionImss.addColumn("VALOR DEDUCCION");
		modeloMovimientosDeduccionImss.addColumn("PUESTO");
		modeloMovimientosDeduccionImss.addColumn("DEDUCCION DESCRIPCION");
		modeloMovimientosDeduccionImss.addColumn("DEPENDENCIA");

		tableMovDeduccionIMSS.setModel(modeloMovimientosDeduccionImss);

		JTableHeader th = new JTableHeader();
		Color colorSilverLight=new Color(176, 196, 222);
		Color colorNegro=new Color(0, 0, 0);
		th = tableMovDeduccionIMSS.getTableHeader();
		Font fuente = new Font("Arial", Font.BOLD, 14); 
		th.setFont(fuente); 
		th.setBackground(colorSilverLight);
		th.setForeground(colorNegro);

		TableColumnModel columnModel = tableMovDeduccionIMSS.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(150);
		columnModel.getColumn(1).setPreferredWidth(150);
		columnModel.getColumn(2).setPreferredWidth(300);
		columnModel.getColumn(3).setPreferredWidth(300);
		columnModel.getColumn(4).setPreferredWidth(300);
		columnModel.getColumn(5).setPreferredWidth(300);
		columnModel.getColumn(6).setPreferredWidth(300);
		columnModel.getColumn(7).setPreferredWidth(300);

		String sqlMov = "select dbo.REPORTE_NOMINA_DEDUCCIONES.ID_EMPLEADO,dbo.puestos.NO_PUESTO,dbo.REPORTE_NOMINA_DEDUCCIONES.ID_UNIDAD,dbo.REPORTE_NOMINA_DEDUCCIONES.ID_DEDUCCION,dbo.REPORTE_NOMINA_DEDUCCIONES.VALOR_DEDUCCION,\r\n" + 
				"dbo.puestos.NOMBRE_PUESTO as NOMBRE_PUESTO,dbo.ATRIBUTO_TIPO_DEDUCCION.DESCRIPCION AS DEDUCCION_DESCRIPCION,DBO.DEPENDENCIAS.UNIDAD_RESPONSABLE\r\n" + 
				"from REPORTE_NOMINA_DEDUCCIONES \r\n" + 
				"left JOIN DBO.PUESTOS ON DBO.REPORTE_NOMINA_DEDUCCIONES.ID_PUESTO = DBO.PUESTOS.no_puesto\r\n" + 
				"left JOIN DBO.ATRIBUTO_TIPO_DEDUCCION ON DBO.REPORTE_NOMINA_DEDUCCIONES.ID_DEDUCCION = DBO.ATRIBUTO_TIPO_DEDUCCION.ID_DEDUCCION\r\n" + 
				"LEFT JOIN DBO.DEPENDENCIAS ON DBO.REPORTE_NOMINA_DEDUCCIONES.ID_UNIDAD = DBO.DEPENDENCIAS.ID_UNIDAD \r\n" + 
				"WHERE  DBO.REPORTE_NOMINA_DEDUCCIONES.ID_UNIDAD="+valor+" and dbo.REPORTE_NOMINA_DEDUCCIONES.ID_DEDUCCION=1 and dbo.REPORTE_NOMINA_DEDUCCIONES.periodo is null\r\n" + 
				"order by dbo.REPORTE_NOMINA_DEDUCCIONES.ID_EMPLEADO";

//		System.out.println("mostrarMovDeduccionIMSS: " + sqlMov);
		String datos[] = new String[9];
		PoolNYCH nych = new PoolNYCH();
		Connection con =null;
		Statement st;
		ResultSet resultSet;
		try {
			con = nych.datasource.getConnection();
			st = con.createStatement();
			resultSet = st.executeQuery(sqlMov);
			while(resultSet.next()) {
				datos[0] = resultSet.getString(1);
				datos[1] = resultSet.getString(2);
				datos[2] = resultSet.getString(3);
				datos[3] = resultSet.getString(4);
				datos[4] = resultSet.getString(5);
				datos[5] = resultSet.getString(6);
				datos[6] = resultSet.getString(7);
				datos[7] = resultSet.getString(8);
				modeloMovimientosDeduccionImss.addRow(datos);
			}

			tableMovDeduccionIMSS.setModel(modeloMovimientosDeduccionImss);
		}catch(Exception el) {
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
		}//FIN DEL FINALLY
	}//fin del metodo

	public void mostrarMovDeduccionIMUVI(int valor) {
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
		modelo.addColumn("ID EMPLEADO");
		modelo.addColumn("ID PUESTO");
		modelo.addColumn("ID UNIDAD");
		modelo.addColumn("ID DEDUCCION");
		modelo.addColumn("VALOR DEDUCCION");
		modelo.addColumn("PUESTO");
		modelo.addColumn("DEDUCCION DESCRIPCION");
		modelo.addColumn("DEPENDENCIA");
		modelo.addColumn("PARCIALIDADES");

		tableMovDeduccionIMUVI.setModel(modelo);

		JTableHeader th = new JTableHeader();
		Color colorSilverLight=new Color(176, 196, 222);
		Color colorNegro=new Color(0, 0, 0);
		th = tableMovDeduccionIMUVI.getTableHeader();
		Font fuente = new Font("Arial", Font.BOLD, 14); 
		th.setFont(fuente); 
		th.setBackground(colorSilverLight);
		th.setForeground(colorNegro);

		TableColumnModel columnModel = tableMovDeduccionIMUVI.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(150);
		columnModel.getColumn(1).setPreferredWidth(150);
		columnModel.getColumn(2).setPreferredWidth(300);
		columnModel.getColumn(3).setPreferredWidth(300);
		columnModel.getColumn(4).setPreferredWidth(300);
		columnModel.getColumn(5).setPreferredWidth(300);
		columnModel.getColumn(6).setPreferredWidth(300);
		columnModel.getColumn(7).setPreferredWidth(300);
		columnModel.getColumn(8).setPreferredWidth(300);

		String sqlMov = "select dbo.REPORTE_NOMINA_DEDUCCIONES.ID_EMPLEADO,dbo.puestos.NO_PUESTO,dbo.REPORTE_NOMINA_DEDUCCIONES.ID_UNIDAD,dbo.REPORTE_NOMINA_DEDUCCIONES.ID_DEDUCCION,dbo.REPORTE_NOMINA_DEDUCCIONES.VALOR_DEDUCCION,\r\n" + 
				"dbo.puestos.NOMBRE_PUESTO as NOMBRE_PUESTO,dbo.ATRIBUTO_TIPO_DEDUCCION.DESCRIPCION AS DEDUCCION_DESCRIPCION,DBO.DEPENDENCIAS.UNIDAD_RESPONSABLE,DBO.REPORTE_NOMINA_DEDUCCIONES.PARCIALIDADES\r\n" + 
				"from REPORTE_NOMINA_DEDUCCIONES \r\n" + 
				"left JOIN DBO.PUESTOS ON DBO.REPORTE_NOMINA_DEDUCCIONES.ID_PUESTO = DBO.PUESTOS.no_puesto\r\n" + 
				"left JOIN DBO.ATRIBUTO_TIPO_DEDUCCION ON DBO.REPORTE_NOMINA_DEDUCCIONES.ID_DEDUCCION = DBO.ATRIBUTO_TIPO_DEDUCCION.ID_DEDUCCION\r\n" + 
				"LEFT JOIN DBO.DEPENDENCIAS ON DBO.REPORTE_NOMINA_DEDUCCIONES.ID_UNIDAD = DBO.DEPENDENCIAS.ID_UNIDAD \r\n" + 
				"WHERE  DBO.REPORTE_NOMINA_DEDUCCIONES.ID_UNIDAD="+valor+" and dbo.REPORTE_NOMINA_DEDUCCIONES.ID_DEDUCCION=23 and DBO.REPORTE_NOMINA_DEDUCCIONES.PERIODO = '"+ FormularioPrincipal.lblPeriodoNumero.getText() +"'\r\n" + 
				"order by dbo.REPORTE_NOMINA_DEDUCCIONES.ID_EMPLEADO";

//		System.out.println("mostrarMovDeduccionIMUVI: " + sqlMov);
		String datos[] = new String[9];
		PoolNYCH nych = new PoolNYCH();
		Connection con =null;
		Statement st;
		ResultSet resultSet;
		try {
			con = nych.datasource.getConnection();
			st = con.createStatement();
			resultSet = st.executeQuery(sqlMov);
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
				modelo.addRow(datos);
			}

			tableMovDeduccionIMUVI.setModel(modelo);
		}catch(Exception el) {
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
		}//FIN DEL FINALLY
	}//fin del metodo

	public void mostrarMovDeduccionANTICIPONOMINA(int valor) {
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
		modelo.addColumn("ID EMPLEADO");
		modelo.addColumn("ID PUESTO");
		modelo.addColumn("ID UNIDAD");
		modelo.addColumn("ID DEDUCCION");
		modelo.addColumn("VALOR DEDUCCION");
		modelo.addColumn("PUESTO");
		modelo.addColumn("DEDUCCION DESCRIPCION");
		modelo.addColumn("DEPENDENCIA");
		modelo.addColumn("PARCIALIDADES");

		tableMovDeduccionAnticipoNomina.setModel(modelo);

		JTableHeader th = new JTableHeader();
		Color colorSilverLight=new Color(176, 196, 222);
		Color colorNegro=new Color(0, 0, 0);
		th = tableMovDeduccionAnticipoNomina.getTableHeader();
		Font fuente = new Font("Arial", Font.BOLD, 14); 
		th.setFont(fuente); 
		th.setBackground(colorSilverLight);
		th.setForeground(colorNegro);

		TableColumnModel columnModel = tableMovDeduccionAnticipoNomina.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(150);
		columnModel.getColumn(1).setPreferredWidth(150);
		columnModel.getColumn(2).setPreferredWidth(150);
		columnModel.getColumn(3).setPreferredWidth(150);
		columnModel.getColumn(4).setPreferredWidth(200);
		columnModel.getColumn(5).setPreferredWidth(300);
		columnModel.getColumn(6).setPreferredWidth(300);
		columnModel.getColumn(7).setPreferredWidth(300);
		columnModel.getColumn(8).setPreferredWidth(150);

		String sqlMov = "select dbo.REPORTE_NOMINA_DEDUCCIONES.ID_EMPLEADO,dbo.puestos.NO_PUESTO,dbo.REPORTE_NOMINA_DEDUCCIONES.ID_UNIDAD,dbo.REPORTE_NOMINA_DEDUCCIONES.ID_DEDUCCION,dbo.REPORTE_NOMINA_DEDUCCIONES.VALOR_DEDUCCION,\r\n" + 
				"dbo.puestos.NOMBRE_PUESTO as NOMBRE_PUESTO,dbo.ATRIBUTO_TIPO_DEDUCCION.DESCRIPCION AS DEDUCCION_DESCRIPCION,DBO.DEPENDENCIAS.UNIDAD_RESPONSABLE,DBO.REPORTE_NOMINA_DEDUCCIONES.PARCIALIDADES\r\n" + 
				"from REPORTE_NOMINA_DEDUCCIONES \r\n" + 
				"left JOIN DBO.PUESTOS ON DBO.REPORTE_NOMINA_DEDUCCIONES.ID_PUESTO = DBO.PUESTOS.no_puesto\r\n" + 
				"left JOIN DBO.ATRIBUTO_TIPO_DEDUCCION ON DBO.REPORTE_NOMINA_DEDUCCIONES.ID_DEDUCCION = DBO.ATRIBUTO_TIPO_DEDUCCION.ID_DEDUCCION\r\n" + 
				"LEFT JOIN DBO.DEPENDENCIAS ON DBO.REPORTE_NOMINA_DEDUCCIONES.ID_UNIDAD = DBO.DEPENDENCIAS.ID_UNIDAD \r\n" + 
				"WHERE  DBO.REPORTE_NOMINA_DEDUCCIONES.ID_UNIDAD="+valor+" and dbo.REPORTE_NOMINA_DEDUCCIONES.ID_DEDUCCION=12 and DBO.REPORTE_NOMINA_DEDUCCIONES.PERIODO = '"+ FormularioPrincipal.lblPeriodoNumero.getText() +"'\r\n" + 
				"order by dbo.REPORTE_NOMINA_DEDUCCIONES.ID_EMPLEADO";

		System.out.println("mostrarMovDeduccionANTICIPONOMINA: " + sqlMov);
		String datos[] = new String[10];
		PoolNYCH nych = new PoolNYCH();
		Connection con =null;
		Statement st;
		ResultSet resultSet;
		try {
			con = nych.datasource.getConnection();
			st = con.createStatement();
			resultSet = st.executeQuery(sqlMov);
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
				modelo.addRow(datos);
			}

			tableMovDeduccionAnticipoNomina.setModel(modelo);
		}catch(Exception el) {
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
		}//FIN DEL FINALLY
	}//fin del metodo

	public void mostrarMovDeduccionPENSIONALIMENTICIA(int valor) {
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
		modelo.addColumn("ID EMPLEADO");
		modelo.addColumn("ID PUESTO");
		modelo.addColumn("ID UNIDAD");
		modelo.addColumn("ID DEDUCCION");
		modelo.addColumn("VALOR DEDUCCION");
		modelo.addColumn("PUESTO");
		modelo.addColumn("DEDUCCION DESCRIPCION");
		modelo.addColumn("DEPENDENCIA");

		tableMovDeduccionPensionAlimentcia.setModel(modelo);

		JTableHeader th = new JTableHeader();
		Color colorSilverLight=new Color(176, 196, 222);
		Color colorNegro=new Color(0, 0, 0);
		th = tableMovDeduccionPensionAlimentcia.getTableHeader();
		Font fuente = new Font("Arial", Font.BOLD, 14); 
		th.setFont(fuente); 
		th.setBackground(colorSilverLight);
		th.setForeground(colorNegro);

		TableColumnModel columnModel = tableMovDeduccionPensionAlimentcia.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(150);
		columnModel.getColumn(1).setPreferredWidth(150);
		columnModel.getColumn(2).setPreferredWidth(300);
		columnModel.getColumn(3).setPreferredWidth(300);
		columnModel.getColumn(4).setPreferredWidth(300);
		columnModel.getColumn(5).setPreferredWidth(300);
		columnModel.getColumn(6).setPreferredWidth(300);
		columnModel.getColumn(7).setPreferredWidth(300);

		String sqlMov = "select dbo.REPORTE_NOMINA_DEDUCCIONES.ID_EMPLEADO,dbo.puestos.NO_PUESTO,dbo.REPORTE_NOMINA_DEDUCCIONES.ID_UNIDAD,dbo.REPORTE_NOMINA_DEDUCCIONES.ID_DEDUCCION,dbo.REPORTE_NOMINA_DEDUCCIONES.VALOR_DEDUCCION,\r\n" + 
				"dbo.puestos.NOMBRE_PUESTO as NOMBRE_PUESTO,dbo.ATRIBUTO_TIPO_DEDUCCION.DESCRIPCION AS DEDUCCION_DESCRIPCION,DBO.DEPENDENCIAS.UNIDAD_RESPONSABLE\r\n" + 
				"from REPORTE_NOMINA_DEDUCCIONES \r\n" + 
				"left JOIN DBO.PUESTOS ON DBO.REPORTE_NOMINA_DEDUCCIONES.ID_PUESTO = DBO.PUESTOS.no_puesto\r\n" + 
				"left JOIN DBO.ATRIBUTO_TIPO_DEDUCCION ON DBO.REPORTE_NOMINA_DEDUCCIONES.ID_DEDUCCION = DBO.ATRIBUTO_TIPO_DEDUCCION.ID_DEDUCCION\r\n" + 
				"LEFT JOIN DBO.DEPENDENCIAS ON DBO.REPORTE_NOMINA_DEDUCCIONES.ID_UNIDAD = DBO.DEPENDENCIAS.ID_UNIDAD \r\n" + 
				"WHERE  DBO.REPORTE_NOMINA_DEDUCCIONES.ID_UNIDAD="+valor+" and dbo.REPORTE_NOMINA_DEDUCCIONES.ID_DEDUCCION=25 and DBO.REPORTE_NOMINA_DEDUCCIONES.PERIODO = '"+ FormularioPrincipal.lblPeriodoNumero.getText() +"'\r\n" + 
				"order by dbo.REPORTE_NOMINA_DEDUCCIONES.ID_EMPLEADO";

//		System.out.println("mostrarMovDeduccionPENSIONALIMENTICIA: " + sqlMov);
		String datos[] = new String[9];
		PoolNYCH nych = new PoolNYCH();
		Connection con =null;
		Statement st;
		ResultSet resultSet;
		try {
			con = nych.datasource.getConnection();
			st = con.createStatement();
			resultSet = st.executeQuery(sqlMov);
			while(resultSet.next()) {
				datos[0] = resultSet.getString(1);
				datos[1] = resultSet.getString(2);
				datos[2] = resultSet.getString(3);
				datos[3] = resultSet.getString(4);
				datos[4] = resultSet.getString(5);
				datos[5] = resultSet.getString(6);
				datos[6] = resultSet.getString(7);
				datos[7] = resultSet.getString(8);
				modelo.addRow(datos);
			}

			tableMovDeduccionPensionAlimentcia.setModel(modelo);
		}catch(Exception el) {
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
		}//FIN DEL FINALLY
	}//fin del metodo

	public void mostrarDatosMovimientosPercepciones(int valor) {
		DefaultTableModel modeloMovimientos = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {
				if (column == 11) {
					return true;
				} else {
					return false;
				}
			}
		};
		modeloMovimientos.addColumn("ID EMPLEADO");
		modeloMovimientos.addColumn("ID PERCEPCION");
		modeloMovimientos.addColumn("ID DEDUCCION");
		modeloMovimientos.addColumn("VALOR PRECEPCION");
		modeloMovimientos.addColumn("ID PUESTO");
		modeloMovimientos.addColumn("PUESTO");
		modeloMovimientos.addColumn("PERCEPCION_DESCRIPCION");
		modeloMovimientos.addColumn("DEDUCCION_DESCRIPCION");

		tableEmpledosMovimientos.setModel(modeloMovimientos);
		JTableHeader th = new JTableHeader();
		Color colorSilverLight=new Color(176, 196, 222);
		Color colorNegro=new Color(0, 0, 0);
		th = tableEmpledosMovimientos.getTableHeader();
		Font fuente = new Font("Arial", Font.BOLD, 14); 
		th.setFont(fuente); 
		th.setBackground(colorSilverLight);
		th.setForeground(colorNegro);

		TableColumnModel columnModel = tableEmpledosMovimientos.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(150);
		columnModel.getColumn(1).setPreferredWidth(150);
		columnModel.getColumn(2).setPreferredWidth(300);
		columnModel.getColumn(3).setPreferredWidth(300);
		columnModel.getColumn(4).setPreferredWidth(300);
		columnModel.getColumn(5).setPreferredWidth(300);
		columnModel.getColumn(6).setPreferredWidth(300);
		columnModel.getColumn(7).setPreferredWidth(300);
		//		columnModel.getColumn(8).setPreferredWidth(300);

		String sqlMov = "\r\n" + /*,DBO.CALCULO_NOMINA.VALOR_DEDUCCION*/
				"select DBO.CALCULO_NOMINA.ID_EMPLEADO,DBO.CALCULO_NOMINA.ID_PERCEPCION,DBO.CALCULO_NOMINA.ID_DEDUCCION,DBO.CALCULO_NOMINA.VALOR_PERCEPCION,DBO.CALCULO_NOMINA.ID_PUESTO, \r\n" + 
				"dbo.puestos.NOMBRE_PUESTO,dbo.ATRIBUTO_TIPO_PERCEPCION.DESCRIPCION as PERCEPCION_DESCRIPCION,dbo.ATRIBUTO_TIPO_DEDUCCION.DESCRIPCION AS DEDUCCION_DESCRIPCION\r\n" + 
				"from CALCULO_NOMINA\r\n" + 
				"left JOIN DBO.PUESTOS ON DBO.CALCULO_NOMINA.ID_PUESTO = DBO.PUESTOS.no_puesto\r\n" + 
				"left JOIN DBO.ATRIBUTO_TIPO_PERCEPCION ON DBO.CALCULO_NOMINA.ID_PERCEPCION = DBO.ATRIBUTO_TIPO_PERCEPCION.ID_PERCEPCION\r\n" + 
				"left JOIN DBO.ATRIBUTO_TIPO_DEDUCCION ON DBO.CALCULO_NOMINA.ID_DEDUCCION = DBO.ATRIBUTO_TIPO_DEDUCCION.ID_DEDUCCION where dbo.calculo_nomina.id_dependencia="+valor+"\r\n "+
				"AND DBO.CALCULO_NOMINA.ESTATUS=1 and dbo.CALCULO_NOMINA.ID_percepcion= 1 order by dbo.CALCULO_NOMINA.ID_EMPLEADO ";

		System.out.println("mostrarDatosMovimientosPercepciones: " + sqlMov);
		String datos[] = new String[9];
		PoolNYCH nych = new PoolNYCH();
		Connection con =null;
		Statement st;
		ResultSet resultSet;
		try {
			con = nych.datasource.getConnection();
			st = con.createStatement();
			resultSet = st.executeQuery(sqlMov);
			while(resultSet.next()) {
				datos[0] = resultSet.getString(1);
				datos[1] = resultSet.getString(2);
				datos[2] = resultSet.getString(3);
				datos[3] = resultSet.getString(4);
				datos[4] = resultSet.getString(5);
				datos[5] = resultSet.getString(6);
				datos[6] = resultSet.getString(7);
				datos[7] = resultSet.getString(8);
				modeloMovimientos.addRow(datos);
				
				System.out.println("1: "+ datos[0]);
				System.out.println("1: "+ datos[1]);
				System.out.println("1: "+ datos[2]);
				System.out.println("1: "+ datos[3]);
				System.out.println("1: "+ datos[4]);
				System.out.println("1: "+ datos[5]);
				System.out.println("1: "+ datos[6]);
				System.out.println("1: "+ datos[7]);
			}

			tableEmpledosMovimientos.setModel(modeloMovimientos);
		}catch(Exception el) {
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
		}//FIN DEL FINALLY
	}//fin del metodo

	public void mostrarDatosAyuda(int valor) {
		DefaultTableModel modeloMovimientosAyuda = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {
				if (column == 11) {
					return true;
				} else {
					return false;
				}
			}
		};
		modeloMovimientosAyuda.addColumn("ID EMPLEADO");
		modeloMovimientosAyuda.addColumn("ID PERCEPCION");
		modeloMovimientosAyuda.addColumn("ID DEDUCCION");
		modeloMovimientosAyuda.addColumn("VALOR PRECEPCION");
		//		modeloMovimientosAyuda.addColumn("VALOR DEDUCCION");
		modeloMovimientosAyuda.addColumn("ID PUESTO");
		modeloMovimientosAyuda.addColumn("PUESTO");
		modeloMovimientosAyuda.addColumn("PERCEPCION_DESCRIPCION");
		modeloMovimientosAyuda.addColumn("DEDUCCION_DESCRIPCION");

		tableEmpledosAyuda.setModel(modeloMovimientosAyuda);
		JTableHeader th = new JTableHeader();
		Color colorSilverLight=new Color(176, 196, 222);
		Color colorNegro=new Color(0, 0, 0);
		th = tableEmpledosAyuda.getTableHeader();
		Font fuente = new Font("Arial", Font.BOLD, 14); 
		th.setFont(fuente); 
		th.setBackground(colorSilverLight);
		th.setForeground(colorNegro);

		TableColumnModel columnModel = tableEmpledosAyuda.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(150);
		columnModel.getColumn(1).setPreferredWidth(150);
		columnModel.getColumn(2).setPreferredWidth(300);
		columnModel.getColumn(3).setPreferredWidth(300);
		columnModel.getColumn(4).setPreferredWidth(300);
		columnModel.getColumn(5).setPreferredWidth(300);
		columnModel.getColumn(6).setPreferredWidth(300);
		columnModel.getColumn(7).setPreferredWidth(300);
		//columnModel.getColumn(8).setPreferredWidth(300);

		String sqlMovAyuda = "\r\n" + /*,DBO.CALCULO_NOMINA.VALOR_DEDUCCION*/
				"select DBO.CALCULO_NOMINA.ID_EMPLEADO,DBO.CALCULO_NOMINA.ID_PERCEPCION,DBO.CALCULO_NOMINA.ID_DEDUCCION,DBO.CALCULO_NOMINA.VALOR_PERCEPCION,DBO.CALCULO_NOMINA.ID_PUESTO, \r\n" + 
				"dbo.puestos.NOMBRE_PUESTO,dbo.ATRIBUTO_TIPO_PERCEPCION.DESCRIPCION as PERCEPCION_DESCRIPCION,dbo.ATRIBUTO_TIPO_DEDUCCION.DESCRIPCION AS DEDUCCION_DESCRIPCION\r\n" + 
				"from CALCULO_NOMINA\r\n" + 
				"left JOIN DBO.PUESTOS ON DBO.CALCULO_NOMINA.ID_PUESTO = DBO.PUESTOS.no_puesto	\r\n" + 
				"left JOIN DBO.ATRIBUTO_TIPO_PERCEPCION ON DBO.CALCULO_NOMINA.ID_PERCEPCION = DBO.ATRIBUTO_TIPO_PERCEPCION.ID_PERCEPCION\r\n" + 
				"left JOIN DBO.ATRIBUTO_TIPO_DEDUCCION ON DBO.CALCULO_NOMINA.ID_DEDUCCION = DBO.ATRIBUTO_TIPO_DEDUCCION.ID_DEDUCCION where dbo.calculo_nomina.id_dependencia="+valor+"\r\n "+
				"AND DBO.CALCULO_NOMINA.ESTATUS=1 and dbo.CALCULO_NOMINA.ID_percepcion= 26 order by dbo.CALCULO_NOMINA.ID_EMPLEADO";/*order by dbo.calculo_nomina.id_empleado*//*where dbo.calculo_nomina.id_empleado=124*/

//		System.out.println("mostrarDatosAyuda: " + sqlMovAyuda);
		String datos[] = new String[9];
		PoolNYCH nych = new PoolNYCH();
		Connection con =null;
		Statement st;
		ResultSet resultSet;
		try {
			con = nych.datasource.getConnection();
			st = con.createStatement();
			resultSet = st.executeQuery(sqlMovAyuda);
			while(resultSet.next()) {
				datos[0] = resultSet.getString(1);
				datos[1] = resultSet.getString(2);
				datos[2] = resultSet.getString(3);
				datos[3] = resultSet.getString(4);
				datos[4] = resultSet.getString(5);
				datos[5] = resultSet.getString(6);
				datos[6] = resultSet.getString(7);
				datos[7] = resultSet.getString(8);
				//				datos[8] = resultSet.getString(9);
				modeloMovimientosAyuda.addRow(datos);
			}

			tableEmpledosAyuda.setModel(modeloMovimientosAyuda);
		}catch(Exception el) {
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
		}//FIN DEL FINALLY
	}//fin del metodo


	public void llenarTablaEmpleadosPorDependencia(int valor,String idTiponomina) {
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

		tableEmpledosDependencia.setModel(modelo);
		tableEmpledosDependencia.setBackground(Color.WHITE);

		JTableHeader th = new JTableHeader();
		Color colorSilverLight = new Color(176, 196, 222);
		Color colorNegro = new Color(0, 0, 0);
		th = tableEmpledosDependencia.getTableHeader();
		Font fuente = new Font("Arial", Font.BOLD, 14);
		th.setFont(fuente);
		th.setBackground(colorSilverLight);
		th.setForeground(colorNegro);

		TableColumnModel columnModel = tableEmpledosDependencia.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(100);
		columnModel.getColumn(1).setPreferredWidth(200);
		columnModel.getColumn(2).setPreferredWidth(200);
		columnModel.getColumn(3).setPreferredWidth(200);
		columnModel.getColumn(4).setPreferredWidth(300);
		columnModel.getColumn(5).setPreferredWidth(100);
		columnModel.getColumn(6).setPreferredWidth(300);

		String sqlSelect = "";
		sqlSelect = "SELECT DBO.EMPLEADOS.CLAVE,DBO.EMPLEADOS.NOMBRE,DBO.EMPLEADOS.APELLIDO_PATERNO,DBO.EMPLEADOS.APELLIDO_MATERNO,\r\n"
				+ "dbo.puestos.nombre_puesto,dbo.puestos.salario,DBO.DEPENDENCIAS.UNIDAD_RESPONSABLE \r\n"
				+ "from empleados\r\n"
				+ "left JOIN DBO.PUESTOS ON DBO.EMPLEADOS.ID_PUESTO = DBO.PUESTOS.no_puesto\r\n"
				+ "LEFT JOIN DBO.DEPENDENCIAS ON DBO.EMPLEADOS.ID_UNIDAD = DBO.DEPENDENCIAS.ID_UNIDAD\r\n"
				+ "LEFT JOIN DBO.EJERCICIOS ON DBO.EMPLEADOS.ID_EJERCICIOS = DBO.EJERCICIOS.ID_EJERCICIOS\r\n"
				+ "WHERE DBO.EMPLEADOS.id_unidad = '" + valor + "' and ELIMINAR_LOGICO='1' and DBO.EMPLEADOS.ID_EJERCICIOS="+idTiponomina  +"   \r\n";//and dbo.empleados.clave=124
//		System.out.println("llenarTablaEmpleadosPorDependencia: "+sqlSelect);
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

			//rowSorter = new TableRowSorter(modelo);
			//tableEmpledosDependencia.setRowSorter(rowSorter);
			tableEmpledosDependencia.setModel(modelo);
			//System.out.println("tabla: " + tableEmpledosDependencia.getRowCount());
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
	public void llenarTablaEmpleadosPorDependenciaFalso(int valor) {
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
		modeloFalso.addColumn("CUENTA");


		tableEmpledosDependenciaFalsa.setModel(modeloFalso);
		tableEmpledosDependenciaFalsa.setBackground(Color.WHITE);

		JTableHeader thFalsa = new JTableHeader();
		Color colorSilverLight = new Color(176, 196, 222);
		Color colorNegro = new Color(0, 0, 0);
		thFalsa = tableEmpledosDependenciaFalsa.getTableHeader();
		Font fuente = new Font("Arial", Font.BOLD, 14);
		thFalsa.setFont(fuente);
		thFalsa.setBackground(colorSilverLight);
		thFalsa.setForeground(colorNegro);

		TableColumnModel columnModelFalsa = tableEmpledosDependenciaFalsa.getColumnModel();
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
		columnModelFalsa.getColumn(20).setPreferredWidth(100);

		String sqlSelect = "";
		sqlSelect = "SELECT DBO.EMPLEADOS.CLAVE,DBO.EMPLEADOS.NOMBRE,DBO.EMPLEADOS.APELLIDO_PATERNO,DBO.EMPLEADOS.APELLIDO_MATERNO,\r\n"
				+ "dbo.puestos.nombre_puesto,dbo.puestos.salario,DBO.DEPENDENCIAS.UNIDAD_RESPONSABLE,dbo.puestos.no_puesto,dbo.ejercicios.nombre_ejercicio,dbo.empleados.sdi, \r\n"
				+" dbo.empleados.fecha_ingreso,dbo.ejercicios.id_ejercicios,dbo.empleados.localidad,dbo.empleados.ciudad,DBO.DEPENDENCIAS.id_unidad,dbo.puestos.no_puesto,dbo.empleados.direccion,\r\n"
				+" dbo.empleados.curp,dbo.empleados.rfc,dbo.empleados.registro_imss,dbo.empleados.fecha_ingreso,dbo.empleados.cuenta_bancaria\r\n"
				+ "from empleados\r\n"
				+ "left JOIN DBO.PUESTOS ON DBO.EMPLEADOS.ID_PUESTO = DBO.PUESTOS.no_puesto\r\n"
				+ "LEFT JOIN DBO.DEPENDENCIAS ON DBO.EMPLEADOS.ID_UNIDAD = DBO.DEPENDENCIAS.ID_UNIDAD\r\n"
				+ "LEFT JOIN DBO.EJERCICIOS ON DBO.EMPLEADOS.ID_EJERCICIOS = DBO.EJERCICIOS.ID_EJERCICIOS\r\n"
				+ "WHERE DBO.EMPLEADOS.id_unidad = '" + valor + "' and ELIMINAR_LOGICO='1' \r\n";//and dbo.empleados.clave=124
//		System.out.println("llenarTablaEmpleadosPorDependenciaFalso: "+sqlSelect);
		Object datosFalsa[] = new String[27];
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
				datosFalsa[21] = resultSetFalsa.getString(22);
				modeloFalso.addRow(datosFalsa);
			}
			//				rowSorterFalso = new TableRowSorter(modeloFalso);
			//				tableFiniquitoFalsa.setRowSorter(rowSorterFalso);
			tableEmpledosDependenciaFalsa.setModel(modeloFalso);
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


	public ArrayList<String> getListaEmpleadosNomina(){
		PoolNYCH poolNych = new PoolNYCH();
		Connection connect = null;
		ArrayList<String> lista = new ArrayList<String>();
		String sqlLista = "SELECT id_reporte_nomina,id_empleado,id_puesto,id_unidad,id_percepcion,valor_percepcion from REPORTE_NOMINA_PERCEPCIONES";
		Statement st;
		ResultSet resultSet = null;
		try {
			connect = poolNych.datasource.getConnection();
			st = connect.createStatement();
			resultSet = st.executeQuery(sqlLista);
			while(resultSet.next()){
				lista.add(resultSet.getString(1));
				lista.add(resultSet.getString(2));
				lista.add(resultSet.getString(3));
				lista.add(resultSet.getString(4));
				lista.add(resultSet.getString(5));
				lista.add(resultSet.getString(6));
			}
		}catch(Exception exp) {
			exp.printStackTrace();
			StringWriter errors = new StringWriter();
			exp.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: "+ errors );
			//JOptionPane.showMessageDialog(null, exp, "Error de conexion", JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
		}finally {
			try {
				connect.close();
			}catch (SQLException ep) {
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


	public void windowOpened(ActionEvent e) {
		final long SLEEP_TIME = 1 * 1000;
		SwingWorker<Void, Void> mySwingWorker = new SwingWorker<Void, Void>() {
			protected Void doInBackground() throws Exception {

				ArrayList<String> listaDependencias = new ArrayList<String>();
				listaDependencias = buscarClasificacionDependencia();
				comboBox.addItem("Seleccione Una");
				for (int i = 0; i < listaDependencias.size(); i++) {
					comboBox.addItem(listaDependencias.get(i));
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

	public static ArrayList<String> buscarClasificacionDependencia() {
		PoolNYCH nych = new PoolNYCH();
		Connection connect = null;
		ResultSet resultSet = null;
		String sqlSelectEjercicio = "select * from DEPENDENCIAS";
		//		String sqlSelectEjercicio ="select dbo.empleados.clave,dbo.empleados.ID_UNIDAD as unidad_empleados,dbo.empleados.ID_EJERCICIOS,dbo.DEPENDENCIAS.UNIDAD_RESPONSABLE,dbo.DEPENDENCIAS.ID_UNIDAD as unidad_dependencia from DEPENDENCIAS\r\n" + 
		//				"left join dbo.empleados on dbo.DEPENDENCIAS.ID_UNIDAD = dbo.empleados.ID_UNIDAD where dbo.empleados.ID_UNIDAD = "+ valor +"    ";
		//System.out.println("sqlSelectEjercicio: " + sqlSelectEjercicio);
		ArrayList<String> lista = new ArrayList<String>();
		try {
			connect = nych.datasource.getConnection();
			Statement st = connect.createStatement();
			resultSet = st.executeQuery(sqlSelectEjercicio);
			while (resultSet.next()) {
				lista.add(resultSet.getString("UNIDAD_RESPONSABLE"));
			}
		}catch (Exception exp) {
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
}

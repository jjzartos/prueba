package ti.snfco.NominaYCapitalHumano.service;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.slf4j.LoggerFactory;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.border.TitledBorder;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Robot;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class InternalFrameTimbrado extends JInternalFrame {


	private static final long serialVersionUID = 1L;
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(InternalFrameTimbrado.class);
	JTextArea textAreaDatos = new JTextArea(10,50);
	Timbrar timbrar = new Timbrar();
	JScrollPane scrollPane = new JScrollPane();


	public InternalFrameTimbrado() {
		setBounds(0, 0, 1571, 761);
		setVisible(true);
		setTitle("Timbrado");
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Timbrado", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(176, 196, 222)));
		panel.setBackground(SystemColor.controlHighlight);
		panel.setBounds(0, 0, 1555, 732);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblGenerarTimbrar = new JLabel("Generar timbrado");
		lblGenerarTimbrar.setBounds(10, 27, 185, 22);
		panel.add(lblGenerarTimbrar);
		//panel.add(textAreaDatos);

		JButton btnTimbrar = new JButton("Timbrar");
		btnTimbrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

								Date result = new Date();
								DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
								DateFormat formatterExtendido = new SimpleDateFormat("HH:mm:ss");
								String convertido = formatter.format(result);
								String convertidoExtendido = formatterExtendido.format(result);
								String carpeta= "C:\\SDK2\\ejemplos\\cfdi33";
								String archivo = "\\timbre_nomina12.ini";
								File crearCarpeta = new File(carpeta);
								File crearArchivo = new File(carpeta+archivo);
								if(crearArchivo.exists()) {
									JOptionPane.showMessageDialog(null,"Se ha creado el archivo en la ruta: " + carpeta);
								}else {
									JOptionPane.showMessageDialog(null,"No existen pero se creara el archivo en la ruta: "+carpeta);
									crearCarpeta.mkdirs();
									try {
										if(crearArchivo.createNewFile()) {
											JOptionPane.showMessageDialog(null,"archivo creado");
										}else{
											JOptionPane.showMessageDialog(null,"el archivo no fue creado");
										}
									}catch(Exception io) {
										io.printStackTrace();
										StringWriter errors = new StringWriter();
										io.printStackTrace(new PrintWriter(errors));
										LOG.info("Excepcion: "+ errors );
										JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
									}
								}
								
								try {
									FileWriter fw = new FileWriter(crearArchivo);
									BufferedWriter bw = new BufferedWriter(fw);
									PrintWriter pw = new PrintWriter(bw);
									pw.println("MODOINI=INI".trim());
									pw.println("cfdi=C:\\SDK2\\timbrados\\timbre_nomina12.xml".trim());
									pw.println("xml_debug=C:\\SDK2\\timbrados\\debug_timbre_nomina12.xml".trim());
									pw.println("remueve_acentos=NO".trim());
									pw.println("RESPUESTA_UTF8=SI".trim());
									pw.println("html_a_txt=NO".trim());
									pw.println("version_cfdi=3.3".trim());
									pw.println("complemento=nomina12".trim());
									pw.println("[PAC]");
									pw.println("usuario=DEMO700101XXX".trim());
									pw.println("pass=DEMO700101XXX".trim());
									pw.println("produccion=NO".trim());
									pw.println("[conf]".trim());
									pw.println("cer=C:\\SDK2\\certificados\\lan7008173r5.cer.pem".trim());
									pw.println("key=C:\\SDK2\\certificados\\lan7008173r5.key.pem".trim());
									pw.println("pass=12345678a".trim());
									pw.println("[factura]".trim());
									pw.println("serie=A".trim());
									pw.println("folio=100".trim());
									//pw.println("fecha_expedicion=".trim()+convertido.trim()+"T".trim()+convertidoExtendido.trim());//2018-07-20T12:30:32
									pw.println("fecha_expedicion=2018-07-18T12:30:32");
									pw.println("metodo_pago=PUE".trim());
									pw.println("forma_pago=99".trim());
									pw.println("tipocomprobante=N".trim());
									pw.println("moneda=MXN".trim());
									pw.println("tipocambio=1".trim());
									pw.println("LugarExpedicion=64488".trim());
									pw.println("RegimenFiscal=601".trim());
									pw.println("subtotal=500.00".trim());
									pw.println("descuento=250.00".trim());
									pw.println("total=250.00".trim());
									pw.println("[emisor]".trim());
									pw.println("rfc=LAN7008173R5".trim());
									pw.println("nombre=ACCEM SERVICIOS EMPRESARIALES SC".trim());
									pw.println("[receptor]".trim());
									pw.println("rfc=SOHM7509289MA".trim());
									pw.println("nombre=MIGUEL ANGEL SOSA HERNANDEZ".trim());
									pw.println("UsoCFDI=P01".trim());
									pw.println("[conceptos]".trim());
									pw.println("[conceptos.0]".trim());
									pw.println("cantidad=1.000".trim());
									pw.println("ClaveUnidad=ACT".trim());
									pw.println("ClaveProdServ=84111505".trim());
									pw.println("descripcion=Pago de nómina".trim());
									pw.println("valorunitario=500.00".trim());
									pw.println("importe=500.00".trim());
									pw.println("Descuento=250.0".trim());
									pw.println("[nomina12]".trim());
									pw.println("TipoNomina=O".trim());
									pw.println("FechaPago=2016-10-31".trim());
									pw.println("FechaInicialPago=2016-10-16".trim());
									pw.println("FechaFinalPago=2016-10-31".trim());
									pw.println("NumDiasPagados=15".trim());
									pw.println("TotalPercepciones=500.00".trim());
									pw.println("TotalDeducciones=250.00".trim());
									pw.println("TotalOtrosPagos=0.00".trim());
									pw.println("[nomina12.Emisor]".trim());
									pw.println("RegistroPatronal=546879213".trim());
									pw.println("RfcPatronOrigen=AAA010101AAA".trim());
									pw.println("[nomina12.Receptor]".trim());
									pw.println("ClaveEntFed=JAL".trim());
									pw.println("Curp=CACF880922HJCMSR03".trim());
									pw.println("NumEmpleado=060".trim());
									pw.println("PeriodicidadPago=04".trim());
									pw.println("TipoContrato=01".trim());
									pw.println("TipoRegimen=02".trim());
									pw.println("Antiguedad=P21W".trim());
									pw.println("Banco=021".trim());
									pw.println("CuentaBancaria=1234567890".trim());
									pw.println("FechaInicioRelLaboral=2016-01-01".trim());
									pw.println("NumSeguridadSocial=04078873454".trim());
									pw.println("Puesto=Desarrollador".trim());
									pw.println("RiesgoPuesto=2".trim());
									pw.println("SalarioBaseCotApor=435.50".trim());
									pw.println("SalarioDiarioIntegrado=435.50".trim());
									pw.println("[nomina12.Percepciones]".trim());
									pw.println("TotalGravado=250.00".trim());
									pw.println("TotalExento=250.00".trim());
									pw.println("TotalSueldos=500.00".trim());
									pw.println("[nomina12.Percepciones.0]".trim());
									pw.println("TipoPercepcion=001".trim());
									pw.println("Clave=001".trim());
									pw.println("Concepto=Sueldos, Salarios Rayas y Jornales".trim());
									pw.println("ImporteGravado=250.00".trim());
									pw.println("ImporteExento=250.00".trim());
									pw.println("[nomina12.Percepciones.AccionesOTitulos]".trim());
									pw.println("ValorMercado=0.00".trim());
									pw.println("PrecioAlOtorgarse=0.00".trim());
									pw.println("[nomina12.Deducciones]".trim());
									pw.println("TotalOtrasDeducciones=150.00".trim());
									pw.println("TotalImpuestosRetenidos=100.00".trim());
									pw.println("[nomina12.Deducciones.0]".trim());
									pw.println("TipoDeduccion=002".trim());
									pw.println("Clave=001".trim());
									pw.println("Concepto=ISR".trim());
									pw.println("Importe=100.00".trim());
									pw.close();
									bw.close();
				
								}catch(Exception fw) {
									fw.printStackTrace();
									StringWriter errors = new StringWriter();
									fw.printStackTrace(new PrintWriter(errors));
									LOG.info("Excepcion: "+ errors );
									JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
								}
				try{
					timbrar.exec();
					JOptionPane.showMessageDialog(null, "Archivo Creado");
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnTimbrar.setBounds(10, 63, 136, 30);
		panel.add(btnTimbrar);

		JButton btnLeerTimbrado = new JButton("Leer Timbrado");
		btnLeerTimbrado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Timbrar leerTimbre = new Timbrar();
				String datos = leerTimbre.muestraContenido("C:\\SDK2\\ejemplos\\cfdi33\\ejemplo_nomina12_respuesta.ini");

				textAreaDatos.setText(datos);
			}
		});
		btnLeerTimbrado.setBounds(10, 113, 136, 30);
		panel.add(btnLeerTimbrado);



		JButton btnAbrirTimbrado = new JButton("Abrir Timbrado");
		btnAbrirTimbrado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timbrar.abrirArchivo("C:\\SDK2\\ejemplos\\cfdi33\\ejemplo_nomina12_respuesta.ini");
			}
		});
		btnAbrirTimbrado.setBounds(10, 455, 136, 30);
		panel.add(btnAbrirTimbrado);

		JButton btnEjecutaryAbrirTimbrado = new JButton("Ejecutar Y Abrir Automaticamente");
		btnEjecutaryAbrirTimbrado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					timbrar.exec();
					Robot rob;
					rob = new Robot();
					System.out.println("Ejecutando...");
					rob.delay(500);
					System.out.println("Ejecutando...");
					rob.delay(500);

					timbrar.abrirArchivo("C:\\SDK2\\ejemplos\\cfdi33\\ejemplo_nomina12_respuesta.ini");
				}catch(AWTException ex) {
					ex.printStackTrace();
				}

			}
		});
		btnEjecutaryAbrirTimbrado.setBounds(10, 496, 288, 30);
		panel.add(btnEjecutaryAbrirTimbrado);

		textAreaDatos.setLineWrap(true);
		textAreaDatos.setWrapStyleWord(true);
		Font font = new Font("Verdana", Font.ITALIC, 12);
		textAreaDatos.setFont(font);


		scrollPane.setBounds(10, 154, 664, 294);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setViewportView(textAreaDatos);
		panel.add(scrollPane);
		
		JLabel lblFoto = new JLabel("");
		lblFoto.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("walll.png"))));
		lblFoto.setBounds(10, 11, 1538, 714);
		panel.add(lblFoto);

	}

	public static void execute(String comando) {
		try {
			String linea;
			Process p  = Runtime.getRuntime().exec(comando);
			try {
				BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
				System.out.println("iniciando...");
				while((linea = input.readLine())  != null) {
					System.out.println(linea);
				}
				System.out.println("termino...");
			}catch(Exception ioex) {
				ioex.printStackTrace();
				StringWriter errors = new StringWriter();
				ioex.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: " + errors);
				JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");
			ioex.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
			e.printStackTrace();
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: " + errors);
			JOptionPane.showMessageDialog(null, "Error de conexión, vuelva a intentarlo");
		}finally {
			try {
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


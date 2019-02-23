package ti.snfco.NominaYCapitalHumano.service;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

import org.slf4j.LoggerFactory;

import com.toedter.calendar.JDateChooser;

public class InternalFrameFechaNuevaInhabil extends JInternalFrame {


	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(InternalFrameFechaNuevaInhabil.class);
	private static final long serialVersionUID = 1L;
	public ButtonGroup groupButton = new ButtonGroup();
	public JRadioButton rdbtnprimer = new JRadioButton("PRIMER PERIODO");
	public JRadioButton rdbtnsegundo = new JRadioButton("SEGUNDO PERIODO");
	private final JButton btnNewButton = new JButton("Aceptar");
	JDateChooser dateChooser = new JDateChooser();


	public InternalFrameFechaNuevaInhabil() {
		setBounds(0,0, 1971, 1051);
		setVisible(true);
		setTitle("Días Inhábiles");
		getContentPane().setLayout(null);

		JPanel panelNuevaFechaDiaInhabil = new JPanel();
		panelNuevaFechaDiaInhabil.setBorder(new TitledBorder(null, "D\u00EDa Inhabiles", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(176, 196, 222)));
		panelNuevaFechaDiaInhabil.setBounds(0,0, 2012, 1051);
		getContentPane().add(panelNuevaFechaDiaInhabil);
		panelNuevaFechaDiaInhabil.setLayout(null);


		dateChooser.setBounds(33, 43, 238, 30);
		panelNuevaFechaDiaInhabil.add(dateChooser);

		rdbtnprimer.setBounds(33, 111, 135, 23);
		panelNuevaFechaDiaInhabil.add(rdbtnprimer);

		//rdbtnApPat.setBackground(new Color(147,140,147));
		rdbtnsegundo.setBounds(33, 149, 145, 23);
		panelNuevaFechaDiaInhabil.add(rdbtnsegundo);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertDiaInhabil();
				dispose();
				
			}
		});

		btnNewButton.setBounds(112, 224, 110, 30);
		btnNewButton.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("accept.png"))));
		panelNuevaFechaDiaInhabil.add(btnNewButton);

	}

	public int insertDiaInhabil() {

		boolean selectedPrimerPeriodo = rdbtnprimer.isSelected();
		boolean selectedSegundoPeriodo = rdbtnsegundo.isSelected();
		String atributoPeriodo ="";
		if(selectedPrimerPeriodo) {
			atributoPeriodo = "PRIMER PERIODO";
			//String atributoSegundoPeriodo = "SEGUNDO PERIODO";
		}

		if(selectedSegundoPeriodo) {
			//String atributoPrimerPeriodo = "PRIMER PERIODO";
			atributoPeriodo = "SEGUNDO PERIODO";
		}

		PoolNYCH nych = new PoolNYCH();
		Connection con =null;
		int resultado = 0;

		//Calendar calendario = GregorianCalendar.getInstance();
		Date fecha = dateChooser.getDate();
		//System.out.println("fecha sin format: "+fecha);
		SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
		//System.out.println(formatoDeFecha.format(fecha));
			
		//convert(datetime,"+ "'"+valor+"'" +", 101)
		String sqlInsert="insert into dbo.dias_inhabiles (dia_inhabil,periodo,clave,eliminar_logico) values("+"convert(datetime,'"+ formatoDeFecha.format(fecha)+"',101),'"+ atributoPeriodo +"','"+ 0 +"','"+ 1 +"')";
		System.out.println(sqlInsert);
		try {
			con = nych.datasource.getConnection();
			PreparedStatement pps = con.prepareStatement(sqlInsert);
			pps.executeUpdate();
			JOptionPane.showMessageDialog(null, "SE HA GUARDADO LA FECHA");
		} catch (SQLException el) {
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
		return resultado;
	}

}

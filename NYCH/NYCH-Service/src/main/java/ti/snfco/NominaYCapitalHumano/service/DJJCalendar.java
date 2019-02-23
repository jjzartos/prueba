package ti.snfco.NominaYCapitalHumano.service;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JInternalFrame;

import com.toedter.calendar.JCalendar;
import javax.swing.JPanel;

public class DJJCalendar extends JInternalFrame {

	
	private JCalendar calendar;
	 
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DJJCalendar frame = new DJJCalendar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DJJCalendar() {
		setBounds(100, 100, 1971, 1051);
		setVisible(true);
		setTitle("Calendario");
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1971, 1051);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		// Instanciar Componente
		calendar = new JCalendar();
		// Ubicar y agregar al panel
		calendar.setBounds(629, 5, 448, 242);
		
		calendar.setTodayButtonVisible(true);
		calendar.setTodayButtonText("Hoy Día");
		calendar.setNullDateButtonVisible(true);
		calendar.getDayChooser().addDateEvaluator(new FechasEspeciales());
		panel.add(calendar);

	}
	
//	public static List<Calendar> fechasEspeciales() {
//		 List<Calendar> fechas = new ArrayList<Calendar>();
//		  
//		 Calendar calendar = new GregorianCalendar(2015, Calendar.SEPTEMBER, 10);
//		 GregorianCalendar calendar =  new GregorianCalendar(2018, 3, 19);
//		 fechas.addAll((Collection<? extends Calendar>) calendar);
//		 calendar =  new GregorianCalendar(2018, 3, 20);
//		 fechas.addAll((Collection<? extends Calendar>) calendar);
//		 calendar =  new GregorianCalendar(2018, 3, 21);
//		 fechas.addAll((Collection<? extends Calendar>) calendar);
//		 fechas.add(calendar);
//		 calendar = new GregorianCalendar(2015, Calendar.NOVEMBER, 10);
//		 fechas.add(calendar);
//		 calendar = new GregorianCalendar(2015, Calendar.NOVEMBER, 18);
//		 fechas.add(calendar);
//		 
//		 return fechas;
//		}
	
//	public static List<String> tipFechas() {
//		 List<String> tips = new ArrayList<String>();
//		 
//		 tips.add("Septiembre 10");
//		 tips.add("Noviembre 10");
//		 tips.add("Noviembre 18");
//		 
//		 return tips;
//		}
}

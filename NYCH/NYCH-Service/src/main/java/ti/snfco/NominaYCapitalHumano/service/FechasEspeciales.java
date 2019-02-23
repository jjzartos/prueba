package ti.snfco.NominaYCapitalHumano.service;

import java.awt.Color;
import java.util.Calendar;
import java.util.Date;

import com.toedter.calendar.IDateEvaluator;

public class FechasEspeciales implements IDateEvaluator {

	Calendar calendar = Calendar.getInstance();
	private int i = 0;

	public FechasEspeciales() {

	}

	public boolean isSpecial(Date date) {
		//calendar.setTime(date);
		return calendar.get(Calendar.MONTH) == Calendar.MARCH && calendar.get(Calendar.DAY_OF_MONTH) == 14-1;
	}

	
	
	 
//	public boolean isSpecial(Date date) {
//	 calendar.setTime(date);
//	 for (i = 0; i < DJJCalendar.fechasEspeciales().size(); i++) {
//	  if (calendar.get(Calendar.MONTH) == DJJCalendar.fechasEspeciales().get(i).get(Calendar.MONTH)
//	    && calendar.get(Calendar.DAY_OF_MONTH) == DJJCalendar.fechasEspeciales().get(i).get(Calendar.DAY_OF_MONTH)) {
//	   return true;
//	  }
//	 }
//	 return false;
//	}
	
	
	
	public Color getSpecialForegroundColor() {
		return Color.RED;
	}

	public Color getSpecialBackroundColor() {
		return Color.YELLOW;
	}

	public String getSpecialTooltip() {
		return "No es día Laborable";
	}
	
	
//	public String getSpecialTooltip() {
//	 return DJJCalendar.tipFechas().get(i);
//	}

	public boolean isInvalid(Date date) {
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) == Calendar.MARCH && calendar.get(Calendar.DAY_OF_MONTH) == 13;
	}

	public Color getInvalidForegroundColor() {
		return Color.RED;
	}

	public Color getInvalidBackroundColor() {
		return Color.YELLOW;
	}

	public String getInvalidTooltip() {
		return "No es día Laborable";
	}
}
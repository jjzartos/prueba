package ti.snfco.NominaYCapitalHumano.service;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil
{
	public static Date resolvePrimeiroUltimo(Date data, boolean isPrimeiro)
	{
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.setTime(data);

		if(isPrimeiro)
		{
			calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			calendar.set(Calendar.AM_PM, Calendar.AM);
			calendar.set(Calendar.HOUR, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
		}
		else
		{
			calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
			calendar.set(Calendar.AM_PM, Calendar.PM);
			calendar.set(Calendar.HOUR, 11);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
		}

		return calendar.getTime();
	}

	public static void main(String[] args){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		Date primeiroDia = resolvePrimeiroUltimo(new Date(), true);
		System.out.println("primeiroDia: "+dateFormat.format(primeiroDia));
		Date ultimoDia = resolvePrimeiroUltimo(new Date(), false);
		System.out.println("ultimoDia: "+dateFormat.format(ultimoDia));
	}
}
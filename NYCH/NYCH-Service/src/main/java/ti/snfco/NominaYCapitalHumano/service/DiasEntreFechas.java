package ti.snfco.NominaYCapitalHumano.service;

import java.text.ParseException;

import java.text.SimpleDateFormat;

import java.util.Date;

 

public class DiasEntreFechas {

 

	public static void main(String[] args) throws ParseException {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

 

		Date fechaInicial=dateFormat.parse("2018-12-24");
		
		Date fechaFinal=dateFormat.parse("2018-12-31");

 

		int dias=(int) ((fechaFinal.getTime()-fechaInicial.getTime())/86400000);
		int d = dias -2;

 

		System.out.println("Hay "+dias+" dias de diferencia");
		System.out.println("fecha "+d +" de pago");

	}
	
	/*
	 


 
	  
	 */

}
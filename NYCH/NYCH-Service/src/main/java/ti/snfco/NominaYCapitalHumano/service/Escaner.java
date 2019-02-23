package ti.snfco.NominaYCapitalHumano.service;

import java.awt.FlowLayout;
import java.util.Scanner;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Escaner extends JFrame{
	 
	 JComboBox<String> combo=new JComboBox<String>();
	 
//	 public Escaner(){
//	  setLayout(new FlowLayout());
//	  PrintService[] ps = PrintServiceLookup.lookupPrintServices( null , null);
//	        for( int i=0 ; i< ps.length; i++)
//	        {
//	           combo.addItem(ps[i].getName());
//	        }
//	  add(new JLabel("Dispositivos de Impresion:"));
//	  add(combo);
//	 }
//	 
	 public static void main(String arg[]){
	  Escaner p=new Escaner();
	  p.setVisible(true);
	  p.setBounds(0, 0, 400, 120);
	  p.setLocationRelativeTo(null);
	  p.setDefaultCloseOperation(EXIT_ON_CLOSE);
	 }


	}
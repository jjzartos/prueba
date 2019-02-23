package ti.snfco.NominaYCapitalHumano.service;

import java.io.Console;
import java.text.NumberFormat;

public class AmortizationTable {

	public static void main(String[] args) { 

		Console console = System.console(); 
		String principalS = console.readLine("Please enter your principal> $"); 
		String lengthS = console.readLine("Please enter the length of your loan in months> "); 
		String interestS = console.readLine("Please enter your annual interest rate. Do not include the percent sign> ");

		double principal = Double.parseDouble(principalS); 
		int length = Integer.parseInt(lengthS); 
		double interest = Double.parseDouble(interestS);

		double monthlyInterest = interest / (12 * 100); 
		double monthlyPayment = principal * ( monthlyInterest / ( 1 - Math.pow((1 + monthlyInterest), (length*-1))));

		final int PAYMENT_WIDTH = 15; 
		final int AMOUNT_WIDTH = 15; 
		final int PRINCIPAL_WIDTH = 15; 
		final int INTEREST_WIDTH = 15;
		final int BALANCE_WIDTH = 15;

		String pattern = "%" + PAYMENT_WIDTH + "s%" + AMOUNT_WIDTH + "s%" + PRINCIPAL_WIDTH + "s%" + INTEREST_WIDTH + "s%" + BALANCE_WIDTH + "s";

		System.out.printf(pattern, "PAYMENT", "AMOUNT", "PRINCIPAL", "INTEREST", "BALANCE"); 
		System.out.println();

		NumberFormat nf = NumberFormat.getCurrencyInstance();

		for (int x = 1; x <= length; x++) { 
			double amountInterest = principal * monthlyInterest; 
			double amountPrincipal = monthlyPayment - amountInterest; principal = principal - amountPrincipal;

			System.out.printf(pattern, x, nf.format(monthlyPayment), nf.format(amountPrincipal), nf.format(amountInterest), nf.format(principal)); 
			System.out.println();

		}

	}
} 
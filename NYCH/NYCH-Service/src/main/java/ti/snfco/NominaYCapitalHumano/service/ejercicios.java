package ti.snfco.NominaYCapitalHumano.service;

public class ejercicios { 

	public static void swap(int i, int j) { 
		int temp = i; 
		i = j; 
		j = temp; 
	}

	public static void main (String []args) {
		int one = 1;
		int two = 2;
		swap(one, two);
		System.out.println("one = " + one + "; two = " + two);
	}

} 
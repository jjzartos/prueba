package ti.snfco.NominaYCapitalHumano.service;

import java.util.Scanner;

class Equipo {
	private String nombre;
	private int juegosJugados;
	private int juegosGanados;
	private int juegosEmpatados;
	private int juegosPerdidos;
	private int golesFavor;
	private int golesEnContra;

	public int calcularPuntos() {
		return juegosJugados * 3 + juegosEmpatados;
	}

//	public int calcularBono() {
//		return calcularPuntos() * 100 + golesFavor * 500 - juegosPerdidos * 500 + (juegosJugados % 2 == 0 ? 5000 : 0);
//	}

	public String toString() {
		return String.format("Nombre: %-20s, Bono: %-10d, Puntos: %-10d", nombre, null, calcularPuntos());
	}

	public static Equipo creaEquipo(String nombre, int jj, int jg, int je, int jp, int gf, int ge) {
		Equipo e = new Equipo();
		e.nombre = nombre;
		e.juegosJugados = jj;
		e.juegosGanados = jg;
		e.juegosEmpatados = je;
		e.juegosPerdidos = jp;
		e.golesFavor = gf;
		e.golesEnContra = ge;
		return e;
	}

}

public class Federacion {

	private static Scanner in = new Scanner(System.in);

	public static void main(String... args) {
		int numeroEquipos = readInt("Escriba el numero de equipos: ");
		Equipo[] equipos = new Equipo[numeroEquipos];
		for (int i = 0; i < numeroEquipos; i++) {
			equipos[i] = Equipo.creaEquipo(readString("\n\nNombre del equipo: "), readInt("Juego jugados: "),
					readInt("Juego ganados: "), readInt("Juego empatados: "), readInt("Juego perdidos: "),
					readInt("Goles a favor: "), readInt("Goles en contra: "));
		}
		for (Equipo e : equipos) {
			System.out.println(e);
		}
	}

	private static String readString(String message) {
		System.out.print(message);
		return in.next();
	}

	private static int readInt(String message) {
		System.out.print(message);
		return in.nextInt();
	}
}

package personajes;

import hechizos.Hechizo;
import hechizos.HechizoFactory;

public class PersonajeFactory {
	private static void asignarHechizos(Personaje p) {
		for (Hechizo h : HechizoFactory.obtenerTodos()) {
			if (p.puedeAprender(h)) {
				p.aprenderHechizo(h);
			}
		}
	}

	public static Estudiante crearEstudiante(String nombre) {
		int nivel = generarNivel(1, 60);
		Estudiante estudiante = new Estudiante(nombre, nivel, 100);

		asignarHechizos(estudiante);

		return estudiante;
	}

	public static Profesor crearProfesor(String nombre) {
		int nivel = generarNivel(50, 110);
		Profesor profesor = new Profesor(nombre, nivel, 110);

		asignarHechizos(profesor);

		return profesor;
	}

	public static Auror crearAuror(String nombre) {
		int nivel = generarNivel(100, 150);
		Auror auror = new Auror(nombre, nivel, 120);

		asignarHechizos(auror);

		return auror;
	}

	public static Seguidor crearSeguidor(String nombre) {
		int nivel = generarNivel(20, 100);
		Seguidor seguidor = new Seguidor(nombre, nivel, 100);

		asignarHechizos(seguidor);

		return seguidor;
	}

	public static Comandante crearComandante(String nombre) {
		int nivel = generarNivel(80, 150);
		Comandante comandante = new Comandante(nombre, nivel, 90);

		asignarHechizos(comandante);

		return comandante;
	}

	private static int generarNivel(int LI, int LS) {
		return (int) (Math.random() * (LS - LI + 1)) + LI;
	}
}

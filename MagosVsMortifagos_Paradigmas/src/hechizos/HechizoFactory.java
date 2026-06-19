package hechizos;

public class HechizoFactory {
	public static Hechizo crearHechizo(String nombre) {

		switch (nombre) {
		case "Expelliarmus":
			return new Expelliarmus();

		default:
			throw new IllegalArgumentException("Hechizo inexistente.");
		}
	}
}

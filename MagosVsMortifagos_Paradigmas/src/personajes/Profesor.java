package personajes;

public class Profesor extends Mago{
	
	public Profesor(String nombre, int nivelMagia, int puntosVida) {
		super(nombre, nivelMagia, puntosVida);
	}
	
	@Override
	public int modificadorCuracion(int base) {
		return super.modificadorCuracion(base) + 15;
	}
}

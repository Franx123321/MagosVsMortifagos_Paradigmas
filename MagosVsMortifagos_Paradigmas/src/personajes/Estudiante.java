package personajes;

public class Estudiante extends Mago{
	
	public Estudiante(String nombre, int nivelMagia, int puntosVida) {
		super(nombre, nivelMagia, puntosVida);
	}
	
	@Override
	public int modificadorDanio(int base) {
		return super.modificadorDanio(base) - 5;
	}
	
	@Override
	public int modificadorDefensa(int base) {
		return super.modificadorDefensa(base) + 10;
	}
}

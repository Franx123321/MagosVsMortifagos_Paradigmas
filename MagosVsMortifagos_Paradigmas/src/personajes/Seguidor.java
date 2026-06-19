package personajes;

public class Seguidor extends Mortifago{
	
	public Seguidor(String nombre, int nivelMagia, int puntosVida) {
		super(nombre, nivelMagia, puntosVida);
	}
	
	@Override
	public int modificadorDanio(int base) {
		return super.modificadorDanio(base) + 15;
	}
	
	@Override
	public int modificadorCuracion(int base) {
		return super.modificadorCuracion(base) - 20;
	}
	
	@Override
	public int modificadorDefensa(int base) {
		return super.modificadorDefensa(base) - 10;
	}
}

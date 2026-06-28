package personajes;

public class Auror extends Mago{
	
	public Auror(String nombre, int nivelMagia, int puntosVida) {
		super(nombre, nivelMagia, puntosVida);
	}
	
	@Override
	public int modificadorDanio(int base) {
		return super.modificadorDanio(base) + 25;
	}
}

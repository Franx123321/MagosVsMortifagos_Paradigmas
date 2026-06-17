package personajes;
import hechizos.Hechizo; 

public class Auror extends Mago{
	
	public Auror(String nombre, int nivelMagia, int puntosVida) {
		super(nombre, nivelMagia, puntosVida);
	}
	
	@Override
	public int modificadorDanio(int base) {
		return base + 15;
	}
	
	@Override
	public boolean puedeAprender(Hechizo h) {
		return super.puedeAprender(h) && h.getNivelReq() <= 150;
	}
}

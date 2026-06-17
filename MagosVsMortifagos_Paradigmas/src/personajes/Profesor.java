package personajes;
import hechizos.Hechizo; 

public class Profesor extends Mago{
	
	public Profesor(String nombre, int nivelMagia, int puntosVida) {
		super(nombre, nivelMagia, puntosVida);
	}
	
	@Override
	public int modificadorCuracion(int base) {
		return super.modificadorCuracion(base) + 15;
	}
	
	@Override
	public boolean puedeAprender(Hechizo h) {
		return super.puedeAprender(h) && h.getNivelReq() <= 100;
	}
}

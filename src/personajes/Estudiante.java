package personajes;
import hechizos.Hechizo; 

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
	
	@Override
	public boolean puedeAprender(Hechizo h) {
		return super.puedeAprender(h) && h.getNivelReq() <= 50;
	}
}

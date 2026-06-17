package personajes;
import java.util.List;
import java.util.ArrayList;
import hechizos.Hechizo; 


public abstract class Mortifago extends Personaje{
	public Mortifago(String nombre, int nivelMagia, int puntosVida) {
		super(nombre, nivelMagia, puntosVida);
	}
	
	@Override
	public boolean puedeAprender(Hechizo h) {
		return h.getTipoMagia() != TipoMagia.LUMINOSA;
	}
	
	@Override
	public int modificadorDanio(int base) {
		return base + 10;
	}
	
	@Override
	public int modificadorCuracion(int base) {
		return base - 5;
	}
	
	@Override
	public int modificadorDefensa(int base) {
		return base;
	}
}

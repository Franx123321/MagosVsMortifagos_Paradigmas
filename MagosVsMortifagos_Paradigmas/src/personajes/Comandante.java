package personajes;

import hechizos.Hechizo;

public class Comandante extends Mortifago{
	
	public Comandante(String nombre, int nivelMagia, int puntosVida) {
		super(nombre, nivelMagia, puntosVida);
	}
	
	@Override
	public int modificadorDanio(int base) {
		return super.modificadorDanio(base) + 25;
	}
	
	@Override
	public int modificadorCuracion(int base) {
		return super.modificadorCuracion(base) - 10;
	}
	
	@Override
	public int modificadorDefensa(int base) {
		return super.modificadorDefensa(base) - 5;
	}
	
	@Override
	public boolean puedeAprender(Hechizo h) {
		return super.puedeAprender(h) && h.getNivelReq() <= 100;
	}
}

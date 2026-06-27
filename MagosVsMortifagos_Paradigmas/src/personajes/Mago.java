package personajes;

import hechizos.Hechizo;
import hechizos.TipoMagia;

public abstract class Mago extends Personaje {

	public Mago(String nombre, int nivelMagia, int puntosVida) {
		super(nombre, nivelMagia, puntosVida);
	}

	@Override
	public boolean puedeAprender(Hechizo h) {
		return h.getTipoMagia() != TipoMagia.OSCURA && this.getNivelMagia() >= h.getNivelReq();
	}

	@Override
	public int modificadorDanio(int base) {
		return base;
	}

	@Override
	public int modificadorCuracion(int base) {
		return base + 10;
	}

	@Override
	public int modificadorDefensa(int base) {
		return base + 5;
	}
}

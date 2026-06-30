package hechizos;

import personajes.Estado;
import personajes.Personaje;

public abstract class HechizoDeDanio implements Hechizo {

	protected void aplicarDanio(Personaje lanzador, Personaje objetivo, int danioBase) {
		int danio = lanzador.modificadorDanio(danioBase);

		if (lanzador.tieneEstado(Estado.CONFUNDIDO)) {
			lanzador.recibirDanio(danio);
		} else {
			objetivo.recibirDanio(danio);
		}
	}

	@Override
	public boolean esTargetAliado() {
		return false;
	}

	@Override
	public boolean haceDanio() {
		return true;
	}
}

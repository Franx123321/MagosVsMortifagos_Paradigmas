package hechizos;

import personajes.Estado;
import personajes.Personaje;

public class Crucio extends HechizoDeDanio {
	@Override
	public void ejecutar(Personaje lanzador, Personaje objetivo) {
		if (Math.random() < 0.4) {
			return;
		}

		if (!lanzador.tieneEstado(Estado.CONFUNDIDO) && Math.random() < 0.3) {
			objetivo.agregarEstado(Estado.CONFUNDIDO, 2);
		}
		aplicarDanio(lanzador, objetivo, 70);
	}

	@Override
	public TipoMagia getTipoMagia() {
		return TipoMagia.OSCURA;
	}

	@Override
	public int getNivelReq() {
		return 95;
	}

	@Override
	public String getNombre() {
		return "Crucio";
	}
}

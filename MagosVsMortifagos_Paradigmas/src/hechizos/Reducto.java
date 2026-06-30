package hechizos;

import personajes.Estado;
import personajes.Personaje;

public class Reducto extends HechizoDeDanio {
	@Override
	public void ejecutar(Personaje lanzador, Personaje objetivo) {
		if (lanzador.tieneEstado(Estado.CONFUNDIDO)) {
			aplicarDanio(lanzador, objetivo, 50);
		} else {
			if (Math.random() < 0.4) {
				objetivo.agregarEstado(Estado.CONFUNDIDO, 2);
			}
			aplicarDanio(lanzador, objetivo, 50);
		}
	}

	@Override
	public TipoMagia getTipoMagia() {
		return TipoMagia.LUMINOSA;
	}

	@Override
	public int getNivelReq() {
		return 85;
	}

	@Override
	public String getNombre() {
		return "Reducto";
	}
}

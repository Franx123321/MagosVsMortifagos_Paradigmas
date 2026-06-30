package hechizos;

import personajes.Personaje;

public class Flipendo extends HechizoDeDanio {
	@Override
	public void ejecutar(Personaje lanzador, Personaje objetivo) {
		aplicarDanio(lanzador, objetivo, 30);
	}

	@Override
	public TipoMagia getTipoMagia() {
		return TipoMagia.NEUTRAL;
	}

	@Override
	public int getNivelReq() {
		return 30;
	}

	@Override
	public String getNombre() {
		return "Flipendo";
	}
}

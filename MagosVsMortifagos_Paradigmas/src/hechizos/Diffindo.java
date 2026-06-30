package hechizos;

import personajes.Personaje;

public class Diffindo extends HechizoDeDanio {
	@Override
	public void ejecutar(Personaje lanzador, Personaje objetivo) {
		aplicarDanio(lanzador, objetivo, 40);
	}

	@Override
	public TipoMagia getTipoMagia() {
		return TipoMagia.NEUTRAL;
	}

	@Override
	public int getNivelReq() {
		return 50;
	}

	@Override
	public String getNombre() {
		return "Diffindo";
	}
}

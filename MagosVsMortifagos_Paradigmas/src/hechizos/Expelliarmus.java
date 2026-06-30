package hechizos;

import personajes.Personaje;

public class Expelliarmus extends HechizoDeDanio {

	@Override
	public void ejecutar(Personaje lanzador, Personaje objetivo) {
		aplicarDanio(lanzador, objetivo, 20);
	}

	@Override
	public TipoMagia getTipoMagia() {
		return TipoMagia.NEUTRAL;
	}

	@Override
	public int getNivelReq() {
		return 10;
	}

	@Override
	public String getNombre() {
		return "Expelliarmus";
	}
}

package hechizos;

import personajes.Personaje;

public class Sectusempra extends HechizoDeDanio {
	@Override
	public void ejecutar(Personaje lanzador, Personaje objetivo) {
		aplicarDanio(lanzador, objetivo, 70);
	}

	@Override
	public TipoMagia getTipoMagia() {
		return TipoMagia.LUMINOSA;
	}

	@Override
	public int getNivelReq() {
		return 120;
	}

	@Override
	public String getNombre() {
		return "Sectusempra";
	}
}

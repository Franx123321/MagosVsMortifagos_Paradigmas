package hechizos;

import personajes.Personaje;

public class AvadaKedavra extends HechizoDeDanio {
	@Override
	public void ejecutar(Personaje lanzador, Personaje objetivo) {
		if (Math.random() < 0.8) {
			return;
		}
		aplicarDanio(lanzador, objetivo, objetivo.getPuntosVida() * 2);
	}

	@Override
	public TipoMagia getTipoMagia() {
		return TipoMagia.OSCURA;
	}

	@Override
	public int getNivelReq() {
		return 140;
	}

	@Override
	public String getNombre() {
		return "Avada Kedavra";
	}
}

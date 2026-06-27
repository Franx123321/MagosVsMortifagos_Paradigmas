package hechizos;

import personajes.Estado;
import personajes.Personaje;

public class AvadaKedavra implements Hechizo{
	@Override
	public void ejecutar(Personaje lanzador, Personaje objetivo) {
		if (Math.random() < 0.2) { //La idea es que tenga un 20% de prob de errar el hechizo y no alreves.
			return;
		} else {
			int danio = lanzador.modificadorDanio(objetivo.getPuntosVida() * 2);

			if (lanzador.tieneEstado(Estado.CONFUNDIDO)) {
				lanzador.recibirDanio(danio);
			} else {
				objetivo.recibirDanio(danio);
			}
		}
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

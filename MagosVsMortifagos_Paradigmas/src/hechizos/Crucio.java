package hechizos;

import personajes.Estado;
import personajes.Personaje;

public class Crucio implements Hechizo {
	@Override
	public void ejecutar(Personaje lanzador, Personaje objetivo) {
		if (Math.random() < 0.4) {
			return;
		} else {
			int danio = lanzador.modificadorDanio(70);

			if (lanzador.tieneEstado(Estado.CONFUNDIDO)) {
				lanzador.recibirDanio(danio);
			} else {
				if (Math.random() < 0.3) {
					objetivo.agregarEstado(Estado.CONFUNDIDO);
				}
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
		return 95;
	}

	@Override
	public String getNombre() {
		return "Crucio";
	}
	
	@Override
	public boolean esTargetAliado() {
	    return false;
	}
}

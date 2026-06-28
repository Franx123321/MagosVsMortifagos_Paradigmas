package hechizos;

import personajes.Estado;
import personajes.Personaje;

public class Diffindo implements Hechizo {
	@Override
	public void ejecutar(Personaje lanzador, Personaje objetivo) {
		int danio = lanzador.modificadorDanio(40);

		if (lanzador.tieneEstado(Estado.CONFUNDIDO)) {
			lanzador.recibirDanio(danio);
		} else {
			objetivo.recibirDanio(danio);
		}
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
	
	@Override
	public boolean esTargetAliado() {
	    return false;
	}
}

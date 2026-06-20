package hechizos;

import personajes.Estado;
import personajes.Personaje;

public class Reducto implements Hechizo {
	@Override
	public void ejecutar(Personaje lanzador, Personaje objetivo) {
		int danio = lanzador.modificadorDanio(50);

		if (lanzador.tieneEstado(Estado.CONFUNDIDO)) {
			lanzador.recibirDanio(danio);
		} else {
			if(Math.random() < 0.4) {
				objetivo.agregarEstado(Estado.CONFUNDIDO);
			}
			objetivo.recibirDanio(danio);
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

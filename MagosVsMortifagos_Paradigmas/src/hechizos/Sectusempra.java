package hechizos;

import personajes.Estado;
import personajes.Personaje;

public class Sectusempra implements Hechizo {
	@Override
	public void ejecutar(Personaje lanzador, Personaje objetivo) {
		int danio = lanzador.modificadorDanio(70);

		if (lanzador.tieneEstado(Estado.CONFUNDIDO)) {
			lanzador.recibirDanio(danio);
		} else {
			objetivo.recibirDanio(danio);
		}
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

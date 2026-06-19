package hechizos;

import personajes.Estado;
import personajes.Personaje;

public class Expelliarmus implements Hechizo {

	@Override
	public void ejecutar(Personaje lanzador, Personaje objetivo) {
		int danio = lanzador.modificadorDanio(20);

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
		return 10;
	}
	
	@Override
	public String getNombre() { 
		return "Expelliarmus";
	}
}

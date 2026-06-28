package hechizos;

import personajes.Estado;
import personajes.Personaje;

public class Reparifors implements Hechizo {
	@Override
	public void ejecutar(Personaje lanzador, Personaje objetivo) {
		objetivo.quitarEstado(Estado.CONFUNDIDO);
		objetivo.quitarEstado(Estado.PARALIZADO);
	}

	@Override
	public TipoMagia getTipoMagia() {
		return TipoMagia.NEUTRAL;
	}

	@Override
	public int getNivelReq() {
		return 75;
	}
	
	@Override
	public String getNombre() { 
		return "Reparifors";
	}
	
	@Override //Podría ser true tambien
	public boolean esTargetAliado() {
	    return false;
	}
}

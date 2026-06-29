package hechizos;

import personajes.Estado;
import personajes.Personaje;

public class Protego implements Hechizo {
	@Override
	public void ejecutar(Personaje lanzador, Personaje objetivo) {
		objetivo.agregarEstado(Estado.PROTEGIDO, 1);
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
		return "Protego";
	}
	
	@Override
	public boolean esTargetAliado() {
	    return true;
	}
}

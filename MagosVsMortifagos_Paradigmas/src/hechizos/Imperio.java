package hechizos;

import personajes.Estado;
import personajes.Personaje;

public class Imperio implements Hechizo {
	@Override
	public void ejecutar(Personaje lanzador, Personaje objetivo) {
		objetivo.agregarEstado(Estado.PARALIZADO, 1);
		objetivo.agregarEstado(Estado.CONFUNDIDO, 2);
	}

	@Override
	public TipoMagia getTipoMagia() {
		return TipoMagia.OSCURA;
	}

	@Override
	public int getNivelReq() {
		return 120;
	}

	@Override
	public String getNombre() {
		return "Imperio";
	}
	
	@Override
	public boolean esTargetAliado() {
	    return false;
	}
	
	@Override
	public boolean haceDanio() {
		return false;
	}
}

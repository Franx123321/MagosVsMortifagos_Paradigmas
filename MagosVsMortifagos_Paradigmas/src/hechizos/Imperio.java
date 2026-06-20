package hechizos;

import personajes.Estado;
import personajes.Personaje;

public class Imperio implements Hechizo {
	@Override
	public void ejecutar(Personaje lanzador, Personaje objetivo) {
		objetivo.agregarEstado(Estado.PARALIZADO);
		objetivo.agregarEstado(Estado.CONFUNDIDO);
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
}

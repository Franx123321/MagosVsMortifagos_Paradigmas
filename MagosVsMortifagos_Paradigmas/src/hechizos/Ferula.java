package hechizos;

import personajes.Personaje;

public class Ferula implements Hechizo {
	@Override
	public void ejecutar(Personaje lanzador, Personaje objetivo) {
		int curacion = lanzador.modificadorCuracion(35);

		objetivo.curarse(curacion);
	}

	@Override
	public TipoMagia getTipoMagia() {
		return TipoMagia.NEUTRAL;
	}

	@Override
	public int getNivelReq() {
		return 35;
	}
	
	@Override
	public String getNombre() { 
		return "Ferula";
	}
	
	@Override
	public boolean esTargetAliado() {
	    return true;
	}
}

package hechizos;

import personajes.Personaje;

public class Episkey implements Hechizo {
	@Override
	public void ejecutar(Personaje lanzador, Personaje objetivo) {
		int curacion = lanzador.modificadorCuracion(20);

		objetivo.curarse(curacion);
	}

	@Override
	public TipoMagia getTipoMagia() {
		return TipoMagia.NEUTRAL;
	}

	@Override
	public int getNivelReq() {
		return 15;
	}
	
	@Override
	public String getNombre() { 
		return "Episkey";
	}
	
	@Override
	public boolean esTargetAliado() {
	    return true;
	}
}

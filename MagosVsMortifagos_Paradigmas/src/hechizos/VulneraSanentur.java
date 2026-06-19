package hechizos;

import personajes.Personaje;

public class VulneraSanentur implements Hechizo {
	@Override
	public void ejecutar(Personaje lanzador, Personaje objetivo) {
		int curacion = lanzador.modificadorCuracion(60);

		objetivo.curarse(curacion);
	}

	@Override
	public TipoMagia getTipoMagia() {
		return TipoMagia.NEUTRAL;
	}

	@Override
	public int getNivelReq() {
		return 100;
	}
	
	@Override
	public String getNombre() { 
		return "Vulnera Sanentur";
	}
}

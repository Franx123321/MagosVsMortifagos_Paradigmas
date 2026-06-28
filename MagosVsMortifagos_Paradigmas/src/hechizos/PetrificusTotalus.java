package hechizos;

import personajes.Estado;
import personajes.Personaje;

public class PetrificusTotalus implements Hechizo {
	@Override
	public void ejecutar(Personaje lanzador, Personaje objetivo) {
		objetivo.agregarEstado(Estado.PARALIZADO);
	}

	@Override
	public TipoMagia getTipoMagia() {
		return TipoMagia.LUMINOSA;
	}

	@Override
	public int getNivelReq() {
		return 70;
	}

	@Override
	public String getNombre() {
		return "Petrificus Totalus";
	}
	
	@Override
	public boolean esTargetAliado() {
	    return false;
	}
}

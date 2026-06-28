package hechizos;

import personajes.Personaje;

public interface Hechizo {
	void ejecutar(Personaje lanzador, Personaje objetivo);

	TipoMagia getTipoMagia();

	int getNivelReq();

	String getNombre();
	boolean esTargetAliado();
}

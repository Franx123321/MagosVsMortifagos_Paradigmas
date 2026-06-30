package hechizos;

import personajes.Personaje;

public interface Hechizo {
	void ejecutar(Personaje lanzador, Personaje objetivo);

	TipoMagia getTipoMagia();

	int getNivelReq();

	String getNombre();
	
	boolean esTargetAliado();
	
	boolean haceDanio(); //si aparte de tener como objetivo un enemigo hace danio o solo le aplica un estado
}
